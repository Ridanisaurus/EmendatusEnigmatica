package com.ridanisaurus.emendatusenigmatica.api.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * Wrapper of a JsonObject and associated Path, with additional verification in place.
 */
public class DefaultConfigurationData {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final JsonObject object;
    private final Path path;
    private final String internalPath;

    /**
     * Constructs DefaultConfigurationData.
     * @param object JsonObject to serialize into a json file.
     * @param path Relative path inside the EE Config directory.
     * @param name Name of the file to create.
     * @throws IllegalArgumentException when Path points outside EE Configuration directory.
     */
    public DefaultConfigurationData(@NotNull JsonObject object, @NotNull String path, @NotNull String name) {
        this.object = Objects.requireNonNull(object, "JsonObject for default configuration can't be null!");
        if (this.object.isEmpty()) throw new IllegalArgumentException("JsonObject for default configuration can't be empty!");

        if (!Objects.requireNonNull(name, "Name for default configuration can't be null!").endsWith(".json")) name += ".json";

        this.path = Analytics.CONFIG_DIR.resolve(path).resolve(name).toAbsolutePath().normalize();

        if (!this.path.startsWith(Analytics.CONFIG_DIR.toAbsolutePath())) throw new IllegalArgumentException("Provided path goes outside EmendatusEnigmatica config directory.");
        internalPath = null;
    }

    /**
     * Constructs DefaultConfigurationData.
     * @param internalPath Path where your configuration file is stored inside a jar.
     * @param path Relative path inside the EE Config directory.
     * @param name Name of the file.
     * @throws IllegalArgumentException when Path points outside EE Configuration directory.
     */
    public DefaultConfigurationData(@NotNull String internalPath, @NotNull String path, @NotNull String name) {
        if (!Objects.requireNonNull(name, "Name for default configuration can't be null!").endsWith(".json")) name += ".json";

        this.internalPath = "/" + Objects.requireNonNull(internalPath, "Internal Path for default configuration can't be null!") + "/" + name;
        this.path = Analytics.CONFIG_DIR.resolve(path).resolve(name).toAbsolutePath().normalize();

        if (!this.path.startsWith(Analytics.CONFIG_DIR.toAbsolutePath())) throw new IllegalArgumentException("Provided path goes outside EmendatusEnigmatica config directory.");
        this.object = null;
    }

    /**
     * Constructs DefaultConfigurationData based on the MaterialModel.
     * @param model MaterialModel to save.
     * @param path Path inside the <code>material</code> folder, can be null.
     * @param name Name of the file in the <code>material</code> folder.
     */
    public DefaultConfigurationData(@NotNull MaterialModel model, String path, @NotNull String name) {
        this(JsonOps.INSTANCE.withEncoder(MaterialModel.CODEC).apply(model).getOrThrow().getAsJsonObject(), "material/" + Objects.requireNonNullElse(path, ""), name);
    }

    /**
     * Constructs DefaultConfigurationData based on the StrataModel.
     * @param model StrataModel to save.
     * @param path Path inside the <code>strata</code> folder, can be null.
     * @param name Name of the file in the <code>strata</code> folder.
     */
    public DefaultConfigurationData(@NotNull StrataModel model, String path, @NotNull String name) {
        this(JsonOps.INSTANCE.withEncoder(StrataModel.CODEC).apply(model).getOrThrow().getAsJsonObject(), "strata/" + Objects.requireNonNullElse(path, ""), name);
    }

    public Path getPath() {
        return this.path.toAbsolutePath();
    }

    /**
     * Serialized wrapped JsonObject and saves it in the specified path.
     * @param ex Executor to run the IO operation on.
     * @return CompletableFuture running the save operation.
     */
    public CompletableFuture<Void> save(Executor ex) {
        return CompletableFuture.runAsync(() -> {
            if (Objects.isNull(object) && Objects.isNull(internalPath)) throw new IllegalStateException("Both JsonObject and Internal path are null! Something is broken.");
            if (Files.exists(path)) throw new IllegalStateException("DefaultConfiguration at \"%s\" already exist!".formatted(path));

            try {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
                if (Objects.isNull(object)) {
                    try (var stream = DefaultConfigurationData.class.getResourceAsStream(internalPath)) {
                        Files.write(path, Objects.requireNonNull(stream).readAllBytes());
                    } catch (Exception e) {
                        Files.deleteIfExists(path);
                        throw new RuntimeException("Failed getting internal file under \"%s\" path. Is your Jar corrupted?".formatted(internalPath), e);
                    }
                    return;
                }
                String jsonString = gson.toJson(object);
                Files.writeString(path, jsonString);
            } catch (Exception e) {
                throw new RuntimeException("Exception while saving DefaultConfiguration at \"%s\".".formatted(path), e);
            }
        }, ex);
    }
}
