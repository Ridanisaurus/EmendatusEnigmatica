package com.ridanisaurus.emendatusenigmatica.api.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.ridanisaurus.emendatusenigmatica.api.config.exceptions.DCValidationException;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * Wrapper of a JsonObject and associated Path, with helper methods, verification and saving options.
 */
public class DCData {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final AbstractDCType type;
    private final Path path;
    private JsonObject object;
    /**
     * The Name of the addon this data was registered from.
     */
    private String origin = "unregistered";
    /**
     * Determines if the data stored in this DCData object has been saved to the drive.
     * Trying to modify the stored object after this point will result in exceptions.
     */
    private boolean spent = false;

    /**
     * Constructs DCData.
     * @param object JsonObject to serialize into a json file.
     * @param name Name of the file to create.
     * @param type Type of this configuration.
     * @throws IllegalArgumentException when Path points outside EE Configuration directory.
     */
    public DCData(@NotNull JsonObject object, @NotNull String name, AbstractDCType type, String origin) {
        this.origin = Objects.requireNonNull(name, "Origin can't be null!");
        this.object = Objects.requireNonNull(object, "JsonObject for default configuration can't be null!");
        if (!Objects.requireNonNull(name, "Name for default configuration can't be null!").endsWith(".json")) name += ".json";
        this.type = Objects.requireNonNull(type);
        this.path = type.getBasePath().resolve(name).toAbsolutePath().normalize();
        try {
            type.validate(this);
        } catch (Exception e) {
            throw new RuntimeException("Failed validation of default configuration at \"%s\" from \"%s\"!".formatted(path, origin), e);
        }
    }

    protected void setOrigin(String name) {
        this.origin = Objects.requireNonNull(name, "Origin can't be null!");
    }

    public String getOrigin() {
        return this.origin;
    }

    public Path getPath() {
        return this.path.toAbsolutePath();
    }

    public AbstractDCType getType() {
        return this.type;
    }

    /**
     * @return <b>A copy of</b> the wrapped JsonObject.
     * @see DCData#updateWrappedObject(JsonObject)
     */
    public JsonObject getWrappedObject() {
        if (isSpent()) throw new IllegalStateException("Wrapped object unavailable after saving to the drive!");
        // We use DeepCopy of the object, to avoid leaking the actual object reference outside DCData
        // Should help avoiding CMEs if some addon decides to update the object from stored reference.
        return this.object.deepCopy();
    }

    /**
     * @param object Object to override the previously wrapped value
     * @implNote The object will be cloned using {@link JsonObject#deepCopy()}
     */
    public void updateWrappedObject(JsonObject object) {
        if (isSpent()) throw new IllegalStateException("Can't update wrapped object after saving to the drive!");
        this.object = object.deepCopy();
    }

    /**
     * Spent attribute is set after data was saved to the drive.
     * Trying to modify the stored JSONObject after save will cause exceptions.
     * @return True if data stored by this DCData Object was saved to the drive.
     */
    public boolean isSpent() {
        return spent;
    }

    /**
     * Serialized wrapped JsonObject and saves it in the specified path.
     * @param ex Executor to run the IO operation on.
     * @return CompletableFuture running the save operation.
     */
    public CompletableFuture<Void> save(Executor ex) {
        spent = true;
        return CompletableFuture.runAsync(() -> {
            if (Objects.isNull(object)) throw new IllegalStateException("JsonObject is null!");
            if (Files.exists(path)) throw new IllegalStateException("DefaultConfiguration at \"%s\" already exist!".formatted(path));

            try {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
                String jsonString = gson.toJson(object);
                Files.writeString(path, jsonString);
            } catch (Exception e) {
                throw new RuntimeException("Exception while saving DefaultConfiguration at \"%s\" from \"%s\".".formatted(path, origin), e);
            }
        }, ex);
    }

    /**
     * Used to determine if this object is unique.
     * @param typeList List of already registered configurations.
     * @return True if unique, false otherwise.
     */
    public boolean isUnique(List<DCData> typeList) {
        return this.type.isUnique(this, typeList);
    }
}
