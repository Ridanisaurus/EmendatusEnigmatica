package com.ridanisaurus.emendatusenigmatica.loader;

import com.mojang.serialization.Codec;
import com.ridanisaurus.emendatusenigmatica.api.IEEPlugin;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * <h1>Model Definition</h1>
 * EEModelDefinition is a class holding the required information for EE to parse,
 * validate and decode a JSON model from the configuration file for the plugins to use.
 *
 * <h3>Implementation requirements</h3>
 * EEModelDefinition usage is required to comply with the following:
 * <ul>
 *  <li>Registry name has to be unique between models registered by your plugin.</li>
 *  <li>Folder path <b>mustn't</b> point to a location outside of EE configuration folder.</li>
 *  <li>All fields on the Codec must be set as <b>Optional</b>, with default values set.</li>
 *  <li>Validator of the model has to be included and configured for the model.</li>
 * </ul>
 *
 * <h3>Conflict Resolution</h3>
 * <ul>
 * <li>When a folder conflict is detected, the conflicting paths get a suffix name,
 * specified in {@link EmendatusPluginReference#name()} of your plugin.</li>
 * <li><code>extensionOverrides</code> is a reserved field for {@link EEModelExtension} conflict resolution system,
 * allowing the end user to override data for specific extensions,
 * and <b>mustn't</b> be included in any definition.</li>
 * </ul>
 *
 * @apiNote You should keep the instance of this model definition as a public static field in your plugin class,
 * to allow other plugins to easily extend your models.
 * @param getOwningPlugin Plugin owning the model.
 * @param getRegistryName Registry name of the model (Unique per plugin)
 * @param folderPath Folder path in the config
 * @param codec Codec for the JSON schema.
 * @param validator Validator of the model.
 * @param registerFunction Post-Decoding ingest method.
 * @param <M> Class of the model to serialize.
 * @param <R> The registry class of your plugin.
 * @see EEModelLoader Model loader documentation.
 * @see EEModelExtension Model extensions documentation.
 * @see ValidationManager Validation System documentation.
 */
public record EEModelDefinition<M, R>(
    Class<? extends IEEPlugin<R>> getOwningPlugin,
    String getRegistryName,
    PathHolder folderPath,
    Codec<M> codec,
    ValidationManager validator,
    BiConsumer<M, R> registerFunction
) {
    public EEModelDefinition(
        @NotNull Class<? extends IEEPlugin<R>> getOwningPlugin,
        @NotNull String getRegistryName,
        @NotNull PathHolder folderPath,
        @NotNull Codec<M> codec,
        @NotNull ValidationManager validator,
        @NotNull BiConsumer<M, R> registerFunction
    ) {
        this.getOwningPlugin = Objects.requireNonNull(getOwningPlugin, "Plugin class can't be null.");
        this.getRegistryName = Objects.requireNonNull(getRegistryName, "Registry name can't be null.");
        this.folderPath = Objects.requireNonNull(folderPath, "PathHolder can't be .");
        this.codec = Objects.requireNonNull(codec, "Coded can't be null.");
        this.validator = Objects.requireNonNull(validator, "ValidationManager can't be null.");
        this.registerFunction = Objects.requireNonNull(registerFunction, "Register consumer can't be null.");

        Objects.requireNonNull(getOwningPlugin.getAnnotation(EmendatusPluginReference.class), "Plugin annotation not present on the Plugin class.");
    }

    public EEModelDefinition(
        @NotNull Class<? extends IEEPlugin<R>> pluginClass,
        @NotNull String registryName,
        @NotNull String folderPath,
        @NotNull Codec<M> codec,
        @NotNull ValidationManager validator,
        @NotNull BiConsumer<M, R> registerFunction
    ) {
        this(pluginClass, registryName, new PathHolder(folderPath), codec, validator, registerFunction);
    }

    public EmendatusPluginReference getOwningAnnotation() {
        return getOwningPlugin.getAnnotation(EmendatusPluginReference.class);
    }

    @SuppressWarnings("unchecked")
    void genericRegister(Object model, Object registry) {
        registerFunction.accept((M) model, (R) registry);
    }
}
