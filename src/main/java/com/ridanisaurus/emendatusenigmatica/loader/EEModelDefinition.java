package com.ridanisaurus.emendatusenigmatica.loader;

import com.mojang.serialization.Codec;
import com.ridanisaurus.emendatusenigmatica.api.IEEPlugin;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * <h1>Model Definition</h1>
 * EEModelDefinition is a class holding the required information for EE to parse, validate and decode a JSON model from the configuration file for the plugins to use.
 *
 * <h3>Implementation requirements</h3>
 * <ul>
 *  <li>Registry name has to be unique between models registered by your plugin.</li>
 *  <li>Folder path <b>mustn't</b> point to a location outside of EE configuration folder.</li>
 *  <li>All fields on the Codec must be set as <b>Optional</b>, with default values set.</li>
 *  <li>Validator of the model has to be included and configured for the model.</li>
 * </ul>
 *
 * When a folder conflict is detected, the conflicting paths get a suffix name, specified in {@link EmendatusPluginReference#name()} of your plugin.
 * <br><br>
 * You should keep the instance of this model definition as a public static field in your plugin class, to allow other plugins to extend your models.
 *
 * @param pluginClass Plugin owning the model.
 * @param registryName Registry name of the model (Unique per plugin)
 * @param folderPath Folder path in the config
 * @param codec Codec for the JSON schema.
 * @param validator Validator of the model.
 * @param registerFunction Post-Decoding ingest method.
 * @param <M> Class of the model to serialize.
 * @param <R> The registry class of your plugin.
 * @see EEModelExtension How to extend EEModelDefinition.
 * @see ValidationManager Validation System documentation.
 */
public record EEModelDefinition<M, R>(
    Class<? extends IEEPlugin<R>> pluginClass,
    String registryName,
    PathHolder folderPath,
    Codec<M> codec,
    ValidationManager validator,
    BiConsumer<M, R> registerFunction
) {
    public EEModelDefinition(
        @NotNull Class<? extends IEEPlugin<R>> pluginClass,
        @NotNull String registryName,
        @NotNull PathHolder folderPath,
        @NotNull Codec<M> codec,
        @NotNull ValidationManager validator,
        @NotNull BiConsumer<M, R> registerFunction
    ) {
        this.pluginClass = Objects.requireNonNull(pluginClass, "Plugin class can't be null.");
        this.registryName = Objects.requireNonNull(registryName, "Registry name can't be null.");
        this.folderPath = Objects.requireNonNull(folderPath, "PathHolder can't be .");
        this.codec = Objects.requireNonNull(codec, "Coded can't be null.");
        this.validator = Objects.requireNonNull(validator, "ValidationManager can't be null.");
        this.registerFunction = Objects.requireNonNull(registerFunction, "Register consumer can't be null.");

        Objects.requireNonNull(pluginClass.getAnnotation(EmendatusPluginReference.class), "Plugin annotation not present on the Plugin class.");
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

    public EmendatusPluginReference getPluginDetails() {
        return pluginClass.getAnnotation(EmendatusPluginReference.class);
    }

    @SuppressWarnings("unchecked")
    void genericRegister(Object model, Object registry) {
        registerFunction.accept((M) model, (R) registry);
    }

    public static final class PathHolder {
        private final String requestedPath;
        private Path realPath = null;

        public PathHolder(String requestedPath) {
            this.requestedPath = Objects.requireNonNull(requestedPath, "Folder name can't be null.");
        }

        public Path getPath() {
            return this.realPath;
        }

        String getRequestedPath() {
            return this.requestedPath;
        }

        Path updatePath(Path configs) {
            return updatePath(configs, null);
        }

        Path updatePath(Path configs, @Nullable String suffix) {
            return this.realPath = Objects.requireNonNull(configs, "Config path can't be null.").resolve(requestedPath + (Objects.isNull(suffix)? "": " " + suffix)).normalize();
        }
    }
}
