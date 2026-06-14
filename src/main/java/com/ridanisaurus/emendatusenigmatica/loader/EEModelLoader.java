package com.ridanisaurus.emendatusenigmatica.loader;

import com.google.common.base.Stopwatch;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.api.IEEPlugin;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationHelper;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.AcceptsAllValidator;
import com.ridanisaurus.emendatusenigmatica.util.ExceptionHelper;
import com.ridanisaurus.emendatusenigmatica.util.FileHelper;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.nio.file.Files;
import java.util.*;

/**
 * <h1>EEModelLoader</h1>
 * EEModelLoader is a class that manages parsing, validation and decoding of JSON files, used to configure Emendatus Enigmatica.
 *
 * <h3>Model definitions and extensions.</h3>
 * {@link EEModelDefinition}s and {@link EEModelExtension}s are used to define and modify models for JSON files used by the addons.
 * Refer to the documentation in both of these classes for additional details.
 *
 * <h3>Order of Operation</h3>
 * Provided order of operations is executed in registration order, starting from EE provided models.
 * <ul>
 * <li> Load all JSON files of the model to memory.</li>
 * <li> Run {@link ValidationManager} of the model for each file.</li>
 * <li> Instantiate Java Object of the model based on JSON object.</li>
 * <li> Run model registration.</li>
 * <li> Run {@link ValidationManager} of the extensions, if present.</li>
 * <li> Instantiate Java Object of the model extension.</li>
 * <li> Run model extension registration.</li>
 * </ul>
 * @see EEModelDefinition EEModelDefinition documentation.
 * @see EEModelExtension EEModelExtension documentation.
 * @see EEPluginLoader EEPluginLoader for handling plugin registries.
 * @see ValidationManager Validation System documentation.
 * @see #registerDefinition(EEModelDefinition)
 * @see #registerModelExtension(EEModelExtension)
 */
public class EEModelLoader {
    private static final Logger logger = LogUtils.getLogger();
    private final Map<EEModelDefinition<?,?>, List<EEModelExtension<?,?,?,?>>> registry = new LinkedHashMap<>();
    private EmendatusPluginReference currentPlugin;
    private boolean canRegister = false;

    public void registerDefinition(@NotNull EEModelDefinition<?,?> definition) {
        if (!canRegister) throw new IllegalStateException("Can't register definitions outside of the addon setup phase!");
        if (
            registry.containsKey(Objects.requireNonNull(definition, "Can't register a null definition!")) ||
            getPluginDefinitions(definition.getOwningPlugin()).stream().anyMatch(it -> it.getRegistryName().equals(definition.getRegistryName()))
        ) {
            throw new IllegalArgumentException("Definition under name \"%s\" for plugin \"%s\" is already registered.".formatted(definition.getRegistryName(), currentPlugin.name()));
        }

        if (!definition.getOwningAnnotation().equals(currentPlugin))
            throw new SecurityException("Plugin \"%s\" tried registering definition under different addon's ownership (\"%s\")."
                .formatted(currentPlugin.name(), definition.getOwningAnnotation().name()));

        var path = definition.folderPath().updatePath(Analytics.CONFIG_DIR).normalize();
        if (!path.startsWith(Analytics.CONFIG_DIR))
            throw new SecurityException("Requested path by plugin \"%s\" (\"%s\") points outside of EE Configuration directory! (\"%s\")"
                .formatted(currentPlugin.name(), definition.getOwningPlugin().getName(), path));

        registry.keySet().stream().filter(it -> it.folderPath().getRequestedPath().equals(definition.folderPath().getRequestedPath()))
            .forEach(it -> logger.warn(
                """
                Found path conflict between plugin "{}" and "{}", moving configuration paths to the respective addon folders.
                - "{}" > "{}" --> "{}"
                - "{}" > "{}" --> "{}"
                """,
                currentPlugin.name(), it.getOwningAnnotation().name(),
                currentPlugin.name(), path, definition.folderPath().updatePath(Analytics.CONFIG_DIR, "(%s)".formatted(currentPlugin.name())),
                it.getOwningAnnotation().name(), it.folderPath().getPath(), it.folderPath().updatePath(Analytics.CONFIG_DIR, "(%s)".formatted(it.getOwningAnnotation().name()))
        ));

        registry.put(definition, new ArrayList<>());
        logger.info("Registered new Model Definition \"{}\" from plugin \"{}\".", definition.getRegistryName(), currentPlugin.name());
    }

    public void registerModelExtension(EEModelExtension<?,?,?,?> extension) {
        if (!canRegister) throw new IllegalStateException("Can't register definitions extensions outside of the addon setup phase!");
        if (!registry.containsKey(Objects.requireNonNull(extension).getExtendedDefinition()))
            throw new IllegalArgumentException("Extension \"%s\" tries to extend not registered Model Definition (\"%s\")."
                .formatted(extension.getClass(), extension.getExtendedDefinition().getRegistryName()));

        if (!extension.getOwningAnnotation().equals(currentPlugin))
            throw new SecurityException("Plugin \"%s\" tried registering definition extension under different addon's ownership (\"%s\")."
                .formatted(currentPlugin.name(), extension.getOwningAnnotation().name()));

        registry.get(extension.getExtendedDefinition()).add(extension);
        if (Objects.nonNull(extension.getRootValidator())) {
            var validator = extension.getExtendedDefinition().validator();
            var fields = validator.getRegisteredFields();

            for (String field : extension.getRootValidator().getRegisteredFields()) {
                if (fields.contains(field)) continue;
                validator.addValidator(field, new AcceptsAllValidator());
            }
        }

        logger.info(
            "Registered new Model Definition Extension from plugin \"{}\" for model \"{}\".",
            currentPlugin.name(), extension.getExtendedDefinition().getRegistryName()
        );
    }

    public @NotNull Map<EEModelDefinition<?,?>, List<EEModelExtension<?,?,?,?>>> getRegistryCopy() {
        return Map.copyOf(registry);
    }

    public @NotNull List<EEModelDefinition<?,?>> getRegisteredDefinitions() {
        return List.copyOf(registry.keySet());
    }

    public @NotNull Map<EEModelDefinition<?,?>, List<EEModelExtension<?,?,?,?>>> getPluginDefinitionsPairs(Class<? extends IEEPlugin<?>> plugin) {
        List<EEModelDefinition<?,?>> definitions = getPluginDefinitions(plugin);
        Map<EEModelDefinition<?,?>, List<EEModelExtension<?,?,?,?>>> ret = new LinkedHashMap<>();
        for (EEModelDefinition<?, ?> definition : definitions) {
            ret.put(definition, registry.get(definition));
        }
        return ret;
    }

    public @NotNull List<EEModelDefinition<?,?>> getPluginDefinitions(Class<? extends IEEPlugin<?>> plugin) {
        return registry.keySet().stream().filter(it -> it.getOwningPlugin().equals(plugin)).toList();
    }

    protected void startRegistration() {
        canRegister = true;
    }

    protected void finishRegistration() {
        canRegister = false;
        currentPlugin = null;
    }

    protected void setCurrentPlugin(EmendatusPluginReference plugin) {
        if (!canRegister) throw new IllegalStateException("Not in registration phase.");
        this.currentPlugin = Objects.requireNonNull(plugin, "Plugin can't be null in ModelLoader registration context.");
    }

    protected void load(EEPluginLoader pluginLoader) {
        logger.info("Loading EEModelDefinitions ({})", registry.size());
        try {
            for (EEModelDefinition<?, ?> definition : registry.keySet()) {
                Stopwatch s = Stopwatch.createStarted();
                logger.info("Loading {}#{}...", definition.getOwningPlugin().getName(), definition.getRegistryName());
                var path = definition.folderPath().getPath();
                if (Files.notExists(path)) {
                    logger.info("Creating missing config directory \"{}\".", path);
                    Files.createDirectories(path);
                    Analytics.addPerformanceAnalytic("Model loading and validation: " + definition.getRegistryName(), s);
                    continue;
                }

                var jsons = FileHelper.loadJsonsWithPaths(path);

                jsons.forEach((jsonPath, object) -> {
                    if (!definition.validator().validate(object, jsonPath)) return;

                    var result = JsonOps.INSTANCE.withDecoder(definition.codec()).apply(object).result();
                    if (result.isEmpty()) return;

                    var model = result.get().getFirst();
                    var definitionRegistry = pluginLoader.getRegistry(definition.getOwningPlugin());
                    definition.genericRegister(model, definitionRegistry);

                    for (EEModelExtension<?,?,?,?> extension : registry.get(definition)) {
                        try {
                            if (!extension.validate(object, jsonPath)) continue;
                            var extensionModel = extension.serialize(object);
                            if (Objects.isNull(extensionModel)) continue;
                            extension.genericRegister(model, extensionModel, definitionRegistry, pluginLoader.getRegistry(extension.getOwningPlugin()));
                        } catch (Exception e) {
                            Analytics.error("Failed parsing extension: %s", ExceptionHelper.getAsString(e), "root", ValidationHelper.obfuscatePath(jsonPath));
                            logger.info("Failed parsing extensions {}#{} from file {}.", definition.getOwningAnnotation().name(), extension.getClass(), jsonPath, e);
                        }
                    }
                });
                Analytics.addPerformanceAnalytic("Model loading and validation: " + definition.getRegistryName(), s);
            }
        } catch (Exception e) {
            throw new RuntimeException("Critical exception caught while loading EEModelDefinitions!", e);
        }
        logger.debug("Finished loading EEModelDefinitions.");
    }
}
