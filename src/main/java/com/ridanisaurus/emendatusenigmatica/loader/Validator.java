package com.ridanisaurus.emendatusenigmatica.loader;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.ridanisaurus.emendatusenigmatica.config.EEConfig;
import net.minecraftforge.fml.loading.FMLPaths;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;

/**
 * This class holds default validators for values from json configuration files.
 * @apiNote If not specified, the validator supports JsonArrays.
 */
public class Validator {
    //TODO: Validation of:
    // - Strata - DONE
    // - Compat Model - DONE
    // - Material Model - DONE
    // - All of the Material sub-models (pain)
    // - Deposits
    public static final boolean log = EEConfig.common.logConfigErrors.get();
    public static final Logger LOGGER = LoggerFactory.getLogger("EE Data Validation");
    /**
     * All values are accepted. Simply returns true.
     */
    public static final BiFunction<JsonElement, Path, Boolean> ALL = (data, path) -> true;
    /**
     * Value is required (can't be null).
     * @apiNote This only checks if provided JsonElement <i>is not null</i>! It doesn't check if an array is empty or not.
     */
    public final BiFunction<JsonElement, Path, Boolean> REQUIRED;
    /**
     * String has to be filled with something, but it's not required (can be null).
     * @apiNote Returns false if value is not String.
     */
    public final BiFunction<JsonElement, Path, Boolean> NON_EMPTY;
    /**
     * String has to be filled with something and is required (can't be null).
     */
    public final BiFunction<JsonElement, Path, Boolean> NON_EMPTY_REQUIRED;
    /**
     * ResourceLocation has to exist, but is not required (can be null).
     */
    public final BiFunction<JsonElement, Path, Boolean> RESOURCE_EXISTS;
    /**
     * ResourceLocation has to exist and is required (can't be null).
     */
    public final BiFunction<JsonElement, Path, Boolean> RESOURCE_EXISTS_REQUIRED;
    private final String name;

    public Validator(String fieldName) {
        name = fieldName;

        NON_EMPTY = (data, jsonPath) -> validateArray(data, jsonPath, (element, path) -> {
            try {
                if (Objects.isNull(element)) return true;
                boolean value = element.getAsString().isBlank();
                if (value && log) LOGGER.error("%s in file \"%s\" can't be blank!".formatted(name, obfuscatePath(path)));
                return !value;
            } catch (Exception e) {
                if (log) LOGGER.error("%s in file \"%s\" requires String value!".formatted(name, obfuscatePath(path)));
            }
            return false;
        });

        REQUIRED = (data, jsonPath) -> {
            boolean value = Objects.nonNull(data);
            if (!value && log) LOGGER.error("%s in file \"%s\" is missing!".formatted(name, obfuscatePath(jsonPath)));
            return value;
        };

        NON_EMPTY_REQUIRED = (data, jsonPath) -> REQUIRED.apply(data, jsonPath) && NON_EMPTY.apply(data, jsonPath);

        // It's very likely I can't verify this.
        RESOURCE_EXISTS = (data, jsonPath) -> true;

        RESOURCE_EXISTS_REQUIRED = (data, jsonPath) -> REQUIRED.apply(data, jsonPath) && RESOURCE_EXISTS.apply(data, jsonPath);
    }

    /**
     * Used to get validator of Number Range. Supports: int, long, float, double.
     * @param min Minimum value.
     * @param max Maximum value.
     * @return BiFunction used as validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getRange(double min, double max) {
        return (data, jsonPath) -> validateArray(data, jsonPath, (element, path) -> {
            try {
                double value = element.getAsDouble();
                if (value < min || value > max) {
                    if (log) LOGGER.error("%s in file \"%s\" is out of range! Provided: %f, Min: %f, Max: %f.".formatted(name, obfuscatePath(path), value, min, max));
                    return false;
                }
                return true;
            } catch (Exception e) {
                if (Objects.isNull(element)) return true;
                if (log) LOGGER.error("%s in file \"%s\" requires a numeric value!".formatted(name, obfuscatePath(path)));
            }
            return false;
        });
    }

    /**
     * Used to get validator of Number Range. Supports: int, long, float, double.
     * @param min Minimum value.
     * @param max Maximum value.
     * @return BiFunction used as validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getRequiredRange(double min, double max) {
        return (data, jsonPath) -> REQUIRED.apply(data, jsonPath) && getRange(min, max).apply(data, jsonPath);
    }

    /**
     * Used to get validator of another JsonObject.
     * @param validators Map with validators for JsonObject to verify.
     * @return BiFunction used as validator.
     * @apiNote This is a wrapper of {@link Validator#validateObject(JsonElement, Path, Map)}. See JavaDoc for that method for full documentation.
     */
    public BiFunction<JsonElement, Path, Boolean> getObjectValidation(Map<String, BiFunction<JsonElement, Path, Boolean>> validators) {
        return (element, path) -> validateObject(element, path, validators);
    }

    /**
     * Used to get validator of another JsonObject marked as REQUIRED.
     * @param validators Map with validators for JsonObject to verify.
     * @return BiFunction used as validator.
     * @apiNote This is a wrapper of {@link Validator#validateObject(JsonElement, Path, Map)}. See JavaDoc for that method for full documentation.
     */
    public BiFunction<JsonElement, Path, Boolean> getRequiredObjectValidation(Map<String, BiFunction<JsonElement, Path, Boolean>> validators) {
        return (element, path) -> REQUIRED.apply(element, path) && validateObject(element, path, validators);
    }

    /**
     * Used to get a validator that checks if value is one of the acceptable ones. Supports: String
     * @param values List with accepted String values.
     * @return BiFunction used as validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getAcceptsOnlyValidation(List<String> values) {
        return (data, jsonPath) -> validateArray(data, jsonPath, (element, path) -> {
            if (Objects.isNull(element)) return true;
            try {
                String value = element.getAsString();
                boolean validation = values.contains(value);
                if (log && !validation) LOGGER.error("%s in file \"%s\" contains illegal value (%s)! Accepted values are: %s".formatted(name, obfuscatePath(path), value, String.join(", ", values)));
                return validation;
            } catch (Exception e) {
                if (log) LOGGER.error("%s in file \"%s\" requires String value!".formatted(name, obfuscatePath(path)));
            }
            return false;
        });
    }

    /**
     * Used to get a validator that checks if value is one of the acceptable ones, marked as Required. Supports: String
     * @param values List with accepted String values.
     * @return BiFunction used as validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getRequiredAcceptsOnlyValidation(List<String> values) {
        return (element, path) -> REQUIRED.apply(element, path) && getAcceptsOnlyValidation(values).apply(element, path);
    }

    /**
     * Used to get a validator that checks the array for containing illegal pairs of values. Supports: String
     * @param pairs List of Pairs with values that are illegal at the same time.
     * @return BiFunction used as validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getIllegalPairsValidation(List<Pair<String, String>> pairs) {
        return (element, path) -> {
            if (Objects.isNull(element)) return true;
            if (!element.isJsonArray()) {
                if (log) LOGGER.error("Expected array for %s in file \"%s\".".formatted(name, obfuscatePath(path)));
                return false;
            }

            List<String> values = new ArrayList<>();
            AtomicBoolean validation = new AtomicBoolean(true);

            element.getAsJsonArray().forEach(entry -> {
                try {
                    values.add(entry.getAsString());
                } catch (Exception e) {
                    LOGGER.error("Non-String value present in the array of %s in file \"%s\".".formatted(name, obfuscatePath(path)));
                    validation.set(false);
                }
            });

            if (values.size() <= 1 && !validation.get()) return false;

            pairs.forEach(pair -> {
                if (values.contains(pair.getFirst()) && values.contains(pair.getSecond())) {
                    if (log) LOGGER.error("Illegal pair found (%s and %s) in the array %s of file \"%s\".".formatted(pair.getFirst(), pair.getSecond(), name, obfuscatePath(path)));
                    validation.set(false);
                }
            });

            return validation.get();
        };
    }

    /**
     * Used to get a validator that checks the array for containing illegal pairs of values, marked as Required. Supports: String
     * @param pairs List of Pairs with values that are illegal at the same time.
     * @return BiFunction used as validator.
     * @apiNote This uses NON_EMPTY_REQUIRED instead of REQUIRED, so if you want to allow empty string, use {@link Validator#getIllegalPairsValidation(List)} instead.
     */
    public BiFunction<JsonElement, Path, Boolean> getRequiredIllegalPairsValidation(List<Pair<String,String>> pairs) {
        return (element, path) -> NON_EMPTY_REQUIRED.apply(element, path) && getIllegalPairsValidation(pairs).apply(element, path);
    }

    /**
     * Used to validate a JsonArray with provided validator (BiFunction).
     * @param element JsonElement to verify. If not Array, it will verify just that element.
     * @param path Path of the json, only for informational purposes.
     * @param validator BiFunction used as a Validator.
     * @return true if all entries in the array pass validation, otherwise false. In case JsonElement is not an array, returns result of the validation.
     */
    public boolean validateArray(@Nullable JsonElement element, Path path, BiFunction<JsonElement, Path, Boolean> validator) {
        if (Objects.nonNull(element) && element.isJsonArray()) {
            AtomicBoolean validation = new AtomicBoolean(true);
            element.getAsJsonArray().forEach(entry -> {if (!validateArray(entry, path, validator)) validation.set(false);});
            return validation.get();
        }
        return validator.apply(element, path);
    }

    /**
     * Used to validate another JsonObject with provided validators for that object.
     * @param jsonElement Object to verify.
     * @param path Path to the file, only for informational purposes.
     * @param validators Map with validators for passed JsonObject.
     * @return True if validations pass, False if validations fail or jsonElement is not a JsonObject.
     * @apiNote Be aware that this might break if used in chained _rg requests. If you need to chain _rg requests to get parent of the object, you need to do your own implementation.
     */
    public boolean validateObject(JsonElement jsonElement, Path path, Map<String, BiFunction<JsonElement, Path, Boolean>> validators) {
        return validateArray(jsonElement, path, (element, jsonPath) -> {
            if (Objects.isNull(element)) return true;
            if (!element.isJsonObject()) {
                if (log) LOGGER.error("Expected object for key %s in %s.".formatted(name, obfuscatePath(jsonPath)));
                return false;
            }

            JsonObject object = element.getAsJsonObject();
            AtomicBoolean validation = new AtomicBoolean(true);

            object.entrySet().forEach(entry -> {
                if (log && !(validators.containsKey(entry.getKey()) || validators.containsKey(entry.getKey() + "_rg"))) {
                    LOGGER.warn("Unknown key (%s) found in file \"%s\".".formatted(entry.getKey(), obfuscatePath(jsonPath)));
                }
            });

            validators.forEach((field, validator) -> {
                if (!validator.apply(field.endsWith("_rg")? object: object.get(field), jsonPath)) validation.set(false);
            });
            return validation.get();
        });
    }

    /**
     * Utility method used for custom validators while using _rg suffix. Checks if parent JsonElement is JsonObject.
     * @param element JsonElement to check. Nullable.
     * @param path Path of the file.
     * @return True if JsonObject, otherwise false.
     */
    public boolean assertObject(@Nullable JsonElement element, Path path) {
        if (Objects.isNull(element) || !element.isJsonObject()) {
            if (log) LOGGER.error("Expected object for verification of key %s in file \"%s\"%s Something is terribly wrong.".formatted(name, obfuscatePath(path), Objects.isNull(element)? ", got null.": "."));
            return false;
        }
        return true;
    }

    /**
     * Utility method used for custom validators for fields that should not be arrays.
     * @param element JsonElement to check. Nullable.
     * @param path Path of the file.
     * @return False if JsonArray, otherwise true.
     */
    public boolean assertNotArray(@Nullable JsonElement element, Path path) {
        if (Objects.nonNull(element) && element.isJsonArray()) {
            if (log) LOGGER.error("Array found on non-array key %s in file \"%s\".".formatted(name, obfuscatePath(path)));
            return false;
        }
        return true;
    }

    /**
     * Utility method used for custom validators for fields that should be arrays.
     * @param element JsonElement to check. Nullable.
     * @param path Path of the file.
     * @return True if JsonArray, otherwise false.
     */
    public boolean assertArray(@Nullable JsonElement element, Path path) {
        if (Objects.isNull(element) || !element.isJsonArray()) {
            if (log) LOGGER.error("Expected array for verification of key %s in file \"%s\"%s".formatted(name, obfuscatePath(path), Objects.isNull(element)? ", got null.": "."));
            return false;
        }
        return true;
    }

    /**
     * Used to cut off part of the path that is not in minecraft directory.<br><br>
     * Input: <br>
     * {@code C:/Minecraft/config/emendatusenigmatica/strata/stone.json}<br>
     * Result: <br>
     * {@code strata/stone.json}
     * @param path Path to obfuscate.
     * @return String with an obfuscated path.
     */
    public static String obfuscatePath(Path path) {
        return FMLPaths.CONFIGDIR.get().resolve("emendatusenigmatica/").relativize(path).toString();
    }
}
