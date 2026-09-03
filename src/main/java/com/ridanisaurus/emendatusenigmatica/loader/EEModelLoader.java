package com.ridanisaurus.emendatusenigmatica.loader;

import com.google.common.base.Stopwatch;
import com.google.gson.JsonObject;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.api.IEEPlugin;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationHelper;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayPolicy;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.AcceptsAllValidator;
import com.ridanisaurus.emendatusenigmatica.util.ExceptionHelper;
import com.ridanisaurus.emendatusenigmatica.util.FileHelper;
import com.ridanisaurus.emendatusenigmatica.util.summary.SummaryHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * <h1>EEModelLoader</h1>
 * EEModelLoader is a class that manages parsing, validation and decoding of JSON files,
 * used to configure Emendatus Enigmatica and it's plugins.
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
 * <li> Validate and parse registered {@link EEModelExtension Model Extensions}.</li>
 * <li> Override data for the specified extension, if present.</li>
 * <li> Run {@link ValidationManager} of the extensions, if present.</li>
 * <li> Instantiate Java Object of the model extension.</li>
 * <li> Run model extension registration.</li>
 * </ul>
 *
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

    /**
     * Used to register a Model Definition.
     * @param definition {@link EEModelDefinition} to register.
     */
    public void registerDefinition(@NotNull EEModelDefinition<?,?> definition) {
        if (!canRegister) throw new IllegalStateException("Can't register definitions outside of the addon setup phase!");
        if (
            registry.containsKey(Objects.requireNonNull(definition, "Can't register a null definition!")) ||
            getPluginDefinitions(definition.getOwningPlugin()).stream().anyMatch(it -> it.getRegistryName().equals(definition.getRegistryName()))
        ) throw new IllegalArgumentException("Definition under name \"%s\" from plugin \"%s\" is already registered."
            .formatted(definition.getRegistryName(), currentPlugin.name()));

        if (!definition.getOwningAnnotation().equals(currentPlugin))
            throw new SecurityException("Plugin \"%s\" tried registering definition under different addon's ownership (\"%s\")."
                .formatted(currentPlugin.name(), definition.getOwningAnnotation().name()));

        var path = definition.folderPath().updatePath(SummaryHandler.CONFIG_DIR).normalize();
        if (!path.startsWith(SummaryHandler.CONFIG_DIR))
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
                currentPlugin.name(), path, definition.folderPath().updatePath(SummaryHandler.CONFIG_DIR, "(%s %s)".formatted(currentPlugin.name(), definition.getRegistryName())),
                it.getOwningAnnotation().name(), it.folderPath().getPath(), it.folderPath().updatePath(SummaryHandler.CONFIG_DIR, "(%s %s)".formatted(it.getOwningAnnotation().name(), it.getRegistryName()))
        ));

        if (definition.validator().getRegisteredFields().contains("extensionOverrides"))
            throw new IllegalArgumentException("Definition under name \"%s\" from plugin \"%s\" defines a reserved field \"extensionOverrides\"!".formatted(
                definition.getRegistryName(), currentPlugin.name()
            ));

        registry.put(definition, new ArrayList<>());
        logger.info("Registered new Model Definition \"{}\" from plugin \"{}\".", definition.getRegistryName(), currentPlugin.name());
    }

    /**
     * Used to register a Model Definition Extension.
     * @param extension {@link EEModelExtension} to register
     */
    public void registerModelExtension(EEModelExtension<?,?,?,?> extension) {
        if (!canRegister) throw new IllegalStateException("Can't register definitions extensions outside of the addon setup phase!");
        if (!registry.containsKey(Objects.requireNonNull(extension, "Can't register a null definition extension!").getExtendedDefinition()))
            throw new IllegalArgumentException("Extension \"%s\" tries to extend not registered Model Definition (\"%s\")."
                .formatted(extension.getClass(), extension.getExtendedDefinition().getRegistryName()));

        if (!extension.getOwningAnnotation().equals(currentPlugin))
            throw new SecurityException("Plugin \"%s\" tried registering definition extension under different addon's ownership (\"%s\")."
                .formatted(currentPlugin.name(), extension.getOwningAnnotation().name()));

        var extensions = registry.get(extension.getExtendedDefinition());
        if (
            extensions.contains(extension) ||
            extensions.stream().anyMatch(it -> it.getRegistryName().equals(extension.getRegistryName()))
        ) throw new IllegalArgumentException("Definition extension for \"%s\" under name \"%s\" from plugin \"%s\" is already registered."
            .formatted(extension.getExtendedDefinition().getRegistryName(), extension.getRegistryName(), currentPlugin.name()));

        if (Objects.nonNull(extension.getRootValidator())) {
            if (extension.getRootValidator().getRegisteredFields().contains("extensionOverrides"))
                throw new IllegalArgumentException("Definition extension for \"%s\" under name \"%s\" from plugin \"%s\" defines a reserved field \"extensionOverrides\"!"
                    .formatted(extension.getExtendedDefinition().getRegistryName(), extension.getRegistryName(), currentPlugin.name()
            ));

            var baseValidator = extension.getExtendedDefinition().validator();
            var baseFields = baseValidator.getRegisteredFields();
            var extValidator = extension.getRootValidator();
            var extFields = extValidator.getRegisteredFields();

            for (String field : extFields)
                if (!baseFields.contains(field)) baseValidator.addValidator(field, new AcceptsAllValidator());

            for (String field : baseFields)
                if (!extFields.contains(field)) extValidator.addValidator(field, new AcceptsAllValidator());
        }

        extensions.add(extension);

        logger.info(
            "Registered new Model Definition Extension \"{}\" from plugin \"{}\" for model \"{}\".",
            extension.getRegistryName(), currentPlugin.name(), extension.getExtendedDefinition().getRegistryName()
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
                    SummaryHandler.addPerformanceAnalytic("Model loading and validation: " + definition.getRegistryName(), s);
                    continue;
                }

                ValidationManager overridesField = ValidationManager.create();
                definition.validator().addValidator("extensionOverrides", overridesField.getAsValidator(false), ArrayPolicy.DISALLOWS_ARRAYS.getNonEmpty());
                for (EEModelExtension<?,?,?,?> extension : registry.get(definition)) {
                    overridesField.addValidator(extension.getExtensionOverrideField(), SimpleObjectValidator.INSTANCE);
                    if (Objects.nonNull(extension.getRootValidator()))
                        extension.getRootValidator().addValidator("extensionOverrides", new AcceptsAllValidator());
                }

                var jsons = FileHelper.loadJsonsWithPaths(path);

                jsons.forEach((jsonPath, object) -> {
                    if (!definition.validator().validate(object, jsonPath)) return;

                    var result = JsonOps.INSTANCE.withDecoder(definition.codec()).apply(object).result();
                    if (result.isEmpty()) return;

                    var model = result.get().getFirst();
                    var definitionRegistry = pluginLoader.getRegistry(definition.getOwningPlugin());

                    for (EEModelExtension<?,?,?,?> extension : registry.get(definition)) {
                        try {
                            var extObject = handleOverrides(extension, object, jsonPath);
                            if (Objects.isNull(extObject)) continue;
                            if (!extension.validate(extObject, jsonPath)) continue;
                            var extensionModel = extension.serialize(extObject);
                            if (Objects.isNull(extensionModel)) continue;
                            extension.genericRegister(model, extensionModel, definitionRegistry, pluginLoader.getRegistry(extension.getOwningPlugin()));
                        } catch (Exception e) {
                            SummaryHandler.error(
                                "Failed parsing extension: %s:%s"
                                    .formatted(extension.getOwningAnnotation().name(), extension.getRegistryName()),
                                ExceptionHelper.getAsString(e),
                                "root",
                                ValidationHelper.obfuscatePath(jsonPath)
                            );
                            logger.warn("Failed parsing extensions {}#{} from file {}.", definition.getOwningAnnotation().name(), extension.getRegistryName(), jsonPath, e);
                        }
                    }

                    definition.genericRegister(model, definitionRegistry);
                });
                SummaryHandler.addPerformanceAnalytic("Model loading and validation: " + definition.getRegistryName(), s);
            }
        } catch (Exception e) {
            throw new RuntimeException("Critical exception caught while loading EEModelDefinitions!", e);
        }
        logger.debug("Finished loading EEModelDefinitions.");
    }

    private @Nullable JsonObject handleOverrides(@NotNull EEModelExtension<?,?,?,?> extension, JsonObject ogObject, Path jsonPath) {
        String overridePath = "root.extensionOverrides.\"" + extension.getExtensionOverrideField() + "\"";
        if (!ValidationHelper.isOtherFieldPresent(ogObject, overridePath)) return ogObject;
        JsonObject overrides = Objects.requireNonNull(ValidationHelper.getElementFromPath(ogObject, overridePath)).getAsJsonObject();
        JsonObject ret = ogObject.deepCopy();
        overrideFields(ret, overrides);
        if (!extension
            .getExtendedDefinition()
            .validator()
            .validate(ret, jsonPath.getParent().resolve("%s (%s)".formatted(jsonPath.getFileName(), extension.getExtensionOverrideField())))
        ) return null;

        return ret;
    }

    private void overrideFields(JsonObject object, @NotNull JsonObject overrides) {
        overrides.asMap().forEach((field, value) -> {
            if (value.isJsonNull()) {
                object.remove(field);
                return;
            }

            if (value.isJsonObject()) {
                if (object.get(field).isJsonObject()) {
                    overrideFields(object.getAsJsonObject(field), value.getAsJsonObject());
                } else {
                    object.add(field, value);
                }
                return;
            }

            object.add(field, value);
        });
    }
}
