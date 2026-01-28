package com.ridanisaurus.emendatusenigmatica.loader;

import com.google.common.base.Stopwatch;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.api.IEEPlugin;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationHelper;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.util.ExceptionHelper;
import com.ridanisaurus.emendatusenigmatica.util.FileHelper;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * <h1>EEModelLoader</h1>
 * EEModelLoader is a class that manages parsing, validation and decoding of JSON files, used to configure Emendatus Enigmatica.
 *
 * <h3>Registration of the models</h3>
 * TODO: Fill the docs
 *
 * <h3>Order of Operation</h3>
 * Provided order of operations is executed in registration order, starting from EE provided models.
 * <ul>
 * <li> Load a list of all JSON files of the model to memory.</li>
 * <li> Iterate over the list of JSON files:</li>
 * <li> Load the file and decode the JSON object.</li>
 * <li> Run {@link ValidationManager} of the model.</li>
 * <li> Instantiate Java Object of the model based on JSON object.</li>
 * <li> Run model registration.</li>
 * </ul>
 */
public class EEModelLoader {
    private static final Logger logger = LogUtils.getLogger();
    private final Map<EEModelDefinition<?,?>, List<EEModelExtension<?,?,?>>> registry = new HashMap<>();
    private EmendatusPluginReference currentPlugin;
    private boolean canRegister = false;

    public void registerDefinition(@NotNull EEModelDefinition<?,?> definition) {
        if (!canRegister) throw new IllegalStateException("Can't register definitions outside of the addon setup phase!");
        if (
            registry.containsKey(Objects.requireNonNull(definition, "Can't register a null definition!")) ||
            getPluginDefinitions(definition.pluginClass()).stream().anyMatch(it -> it.registryName().equals(definition.registryName()))
        ) {
            throw new IllegalArgumentException("Definition under name \"%s\" for plugin \"%s\" is already registered.".formatted(definition.registryName(), currentPlugin.name()));
        }

        var path = definition.folderPath().updatePath(Analytics.CONFIG_DIR).normalize();
        if (!path.startsWith(Analytics.CONFIG_DIR))
            throw new SecurityException("Requested path by plugin \"%s\" (%s) points outside of EE Configuration directory! (%s)"
                .formatted(currentPlugin.name(), definition.pluginClass(), path));

        registry.keySet().stream().filter(it -> it.folderPath().getRequestedPath().equals(definition.folderPath().getRequestedPath()))
            .forEach(it -> logger.warn(
                """
                Found path conflict between plugin "{}" and "{}", moving configuration paths to the respective addon folders.
                - "{}" > "{}" --> "{}"
                - "{}" > "{}" --> "{}"
                """,
                currentPlugin.name(), it.getPluginDetails().name(),
                currentPlugin.name(), path, definition.folderPath().updatePath(Analytics.CONFIG_DIR, "(%s)".formatted(currentPlugin.name())),
                it.getPluginDetails().name(), it.folderPath().getPath(), it.folderPath().updatePath(Analytics.CONFIG_DIR, "(%s)".formatted(it.getPluginDetails().name()))
        ));

        registry.put(definition, new ArrayList<>());
        logger.info("Registered new Model Definition \"{}\" from plugin \"{}\" ({}).", definition.registryName(), currentPlugin.name(), definition.pluginClass());
    }

    public void registerModelExtension(EEModelExtension<?,?,?> extension) {
        if (!canRegister) throw new IllegalStateException("Can't register definitions extensions outside of the addon setup phase!");
        if (!registry.containsKey(Objects.requireNonNull(extension).getExtendedDefinition()))
            throw new IllegalArgumentException("Extension \"%s\" tries to extend not registered Model Definition (%s)."
                .formatted(extension.getClass(), extension.getExtendedDefinition().registryName()));

        registry.get(extension.getExtendedDefinition()).add(extension);
        logger.info(
            "Registered new Model Definition Extension \"{}\" from plugin \"{}\" for model \"{}\" .",
            extension.getClass(), currentPlugin.name(), extension.getExtendedDefinition().registryName()
        );
    }

    private @NotNull Map<EEModelDefinition<?,?>, List<EEModelExtension<?,?,?>>> getPluginDefinitionsPairs(Class<? extends IEEPlugin<?>> plugin) {
        List<EEModelDefinition<?,?>> definitions = getPluginDefinitions(plugin);
        Map<EEModelDefinition<?,?>, List<EEModelExtension<?,?,?>>> ret = new LinkedHashMap<>();
        for (EEModelDefinition<?, ?> definition : definitions) {
            ret.put(definition, registry.get(definition));
        }
        return ret;
    }

    private @NotNull List<EEModelDefinition<?,?>> getPluginDefinitions(Class<? extends IEEPlugin<?>> plugin) {
        return registry.keySet().stream().filter(it -> it.pluginClass().equals(plugin)).toList();
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
        logger.debug("Loading EEModelDefinitions ({})", registry.size());
        try {
            for (EEModelDefinition<?, ?> definition : registry.keySet()) {
                Stopwatch s = Stopwatch.createStarted();
                logger.debug("Loading {}#{}...", definition.pluginClass(), definition.registryName());
                var path = definition.folderPath().getPath();
                if (Files.notExists(path)) {
                    logger.debug("Creating missing config directory \"{}\".", path);
                    Files.createDirectories(path);
                    Analytics.addPerformanceAnalytic("Model loading and validation: " + definition.registryName(), s);
                    continue;
                }

                var jsons = FileHelper.loadJsonsWithPaths(path);

                jsons.forEach((jsonPath, object) -> {
                    if (!definition.validator().validate(object, jsonPath)) return;

                    var result = JsonOps.INSTANCE.withDecoder(definition.codec()).apply(object).result();
                    if (result.isEmpty()) return;

                    var model = result.get().getFirst();
                    var pluginRegistry = pluginLoader.getRegistry(definition.pluginClass());
                    definition.genericRegister(model, pluginRegistry);

                    for (EEModelExtension<?,?,?> extension : registry.get(definition)) {
                        try {
                            if (!extension.validate(object, jsonPath)) continue;
                            var extensionModel = extension.serialize(object);
                            if (Objects.isNull(extensionModel)) continue;
                            extension.genericRegister(model, extensionModel, pluginRegistry);
                        } catch (Exception e) {
                            Analytics.error("Failed parsing extension: %s", ExceptionHelper.getAsString(e), "root", ValidationHelper.obfuscatePath(jsonPath));
                            logger.debug("Failed parsing extensions {}#{} from file {}.", definition.getPluginDetails().name(), extension.getClass(), jsonPath, e);
                        }
                    }
                });
                Analytics.addPerformanceAnalytic("Model loading and validation: " + definition.registryName(), s);
            }
        } catch (Exception e) {
            throw new RuntimeException("Critical exception caught while loading EEModelDefinitions!", e);
        }
        logger.debug("Finished loading EEModelDefinitions.");
    }
}
