package com.ridanisaurus.emendatusenigmatica.loader;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.loading.FMLPaths;
import org.jetbrains.annotations.Nullable;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * This class holds default validators for values from json configuration files.
 * @apiNote If not specified, the validator supports JsonArrays.
 */
public class Validator {
    public static final ValidatorLogger LOGGER = new ValidatorLogger(LoggerFactory.getLogger("EE Data Validation"));
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
     * Array has to contain at least one element, but it's not required (can be null).
     * @apiNote Returns False if non-array is passed. Effectively works as non-required {@link Validator#assertArray(JsonElement, Path)}.
     */
    public BiFunction<JsonElement, Path, Boolean> NON_EMPTY_ARRAY;
    /**
     * Array has to contain least one element and is required (can't be null).
     * @apiNote Returns False if non-array is passed. Effectively works as {@link Validator#assertArray(JsonElement, Path)}.
     */
    public BiFunction<JsonElement, Path, Boolean> NON_EMPTY_ARRAY_REQUIRED;
    /**
     * The Field has to be boolean, but it's not required (can be null).
     */
    public final BiFunction<JsonElement, Path, Boolean> REQUIRES_BOOLEAN;
    /**
     * The Field has to be boolean and is required (can't be null).
     */
    public final BiFunction<JsonElement, Path, Boolean> REQUIRES_BOOLEAN_REQUIRED;
    /**
     * The Field has to be integer-like, but it's not required (can be null).
     */
    public final BiFunction<JsonElement, Path, Boolean> REQUIRES_INT;
    /**
     * The Field has to be integer-like and is required (can't be null).
     */
    public final BiFunction<JsonElement, Path, Boolean> REQUIRES_INT_REQUIRED;
    /**
     * The Field has to be a float, but it's not required (can be null).
     * @apiNote Accepts Integer
     */
    public final BiFunction<JsonElement, Path, Boolean> REQUIRES_FLOAT;
    /**
     * The Field has to be a float and is required (can't be null).
     * @apiNote Accepts Integer
     */
    public final BiFunction<JsonElement, Path, Boolean> REQUIRES_FLOAT_REQUIRED;
    /**
     * String has to be in the format of minecraft item id "namespace:id", but it's not required (can be null).
     * @apiNote Does not accept empty Strings.
     */
    public final BiFunction<JsonElement, Path, Boolean> RESOURCE_ID;
    /**
     * String has to be in the format of minecraft item id "namespace:id" and is required (can't be null).
     * @apiNote Does not accept empty Strings.
     */
    public final BiFunction<JsonElement, Path, Boolean> RESOURCE_ID_REQUIRED;
    /**
     * String has to be correct hex color, without #.<br>
     * Example:<br>
     * "#aabbcc" -> Incorrect, contains # at the beginning.<br>
     * "ggiijj" -> Illegal hex value (Different length than 6 also counts)<br>
     * "15ffb3" -> Correct, hex value like that exists, so it's a color :D
     */
    public final BiFunction<JsonElement, Path, Boolean> HEX_COLOR;
    /**
     * String has to be correct hex color, without #, marked as Required.<br>
     * Example:<br>
     * "#aabbcc" -> Incorrect, contains # at the beginning.<br>
     * "ggiijj" -> Illegal hex value (Different length than 6 also counts)<br>
     * "15ffb3" -> Correct, hex value like that exists, so it's a color :D
     */
    public final BiFunction<JsonElement, Path, Boolean> HEX_COLOR_REQUIRED;
    private final String name;

    public Validator(String fieldName) {
        name = fieldName;

        NON_EMPTY = (data, jsonPath) -> validateArray(data, jsonPath, (element, path) -> {
            if (Objects.isNull(element)) return true;
            try {
                if (!element.isJsonPrimitive()) throw new ClassCastException();
                boolean value = element.getAsString().isBlank();
                if (value) LOGGER.error("\"%s\" in file \"%s\" can't be blank!".formatted(name, obfuscatePath(path)));
                return !value;
            } catch (ClassCastException e) {
                LOGGER.error("\"%s\" in file \"%s\" requires String value!".formatted(name, obfuscatePath(path)));
            }
            return false;
        });

        REQUIRED = (data, jsonPath) -> {
            boolean value = Objects.nonNull(data);
            if (!value) LOGGER.error("\"%s\" in file \"%s\" is missing!".formatted(name, obfuscatePath(jsonPath)));
            return value;
        };

        NON_EMPTY_REQUIRED = (data, jsonPath) -> REQUIRED.apply(data, jsonPath) && NON_EMPTY.apply(data, jsonPath);

        NON_EMPTY_ARRAY = (data, jsonPath) -> {
            if (Objects.isNull(data)) return true;
            if (!assertArray(data, jsonPath)) return false;
            if (data.getAsJsonArray().isEmpty()) {
                LOGGER.error("\"%s\" array in file \"%s\" can't be empty!".formatted(name, obfuscatePath(jsonPath)));
                return false;
            }
            return true;
        };

        NON_EMPTY_ARRAY_REQUIRED = (data, jsonPath) -> REQUIRED.apply(data, jsonPath) && NON_EMPTY_ARRAY.apply(data, jsonPath);

        REQUIRES_BOOLEAN = (data, jsonPath) -> {
            if (Objects.isNull(data)) return true;
            try {
                if (!data.isJsonPrimitive()) throw new ClassCastException();
                data.getAsBoolean();
                return true;
            } catch (ClassCastException e) {
                LOGGER.error("\"%s\" in file \"%s\" requires Boolean (true/false) value!".formatted(name, obfuscatePath(jsonPath)));
            }
            return false;
        };

        REQUIRES_BOOLEAN_REQUIRED = (data, jsonPath) -> REQUIRED.apply(data, jsonPath) && REQUIRES_BOOLEAN.apply(data, jsonPath);

        REQUIRES_INT = (data, jsonPath) -> {
            if (Objects.isNull(data)) return true;
            try {
                if (!data.isJsonPrimitive()) throw new ClassCastException();
                double value = data.getAsDouble();
                if (LOGGER.shouldLog && Math.ceil(value) > value) {
                    LOGGER.warn("\"%s\" in file \"%s\" should be an integer. Floating-point values are not supported for this field.".formatted(name, obfuscatePath(jsonPath)));
                }
                return true;
            } catch (ClassCastException | NumberFormatException e) {
                LOGGER.error("\"%s\" in file \"%s\" requires numeric value!".formatted(name, obfuscatePath(jsonPath)));
            }
            return false;
        };

        REQUIRES_INT_REQUIRED = (data, jsonPath) -> REQUIRED.apply(data, jsonPath) && REQUIRES_INT.apply(data, jsonPath);

        REQUIRES_FLOAT = (data, jsonPath) -> {
            if (Objects.isNull(data)) return true;
            try {
                if (!data.isJsonPrimitive()) throw new ClassCastException();
                data.getAsDouble();
                return true;
            } catch (ClassCastException | NumberFormatException e) {
                LOGGER.error("\"%s\" in file \"%s\" requires numeric value!".formatted(name, obfuscatePath(jsonPath)));
            }
            return false;
        };

        REQUIRES_FLOAT_REQUIRED = (data, jsonPath) -> REQUIRED.apply(data, jsonPath) && REQUIRES_INT.apply(data, jsonPath);

        RESOURCE_ID = (data, jsonPath) -> validateArray(data, jsonPath, (element, path) -> {
            if (Objects.isNull(element)) return true;
            if (!NON_EMPTY.apply(element, path)) return false;

            String value = element.getAsString();
            String[] values = (value + " :").split(":");
            boolean validation = true;
            if (values.length != 2) {
                LOGGER.error("Provided Resource ID in \"%s\" in file \"%s\" isn't in a proper format! Expected: \"namespace:id\", got: \"%s\".".formatted(name, obfuscatePath(path), value));
                return false;
            }
            if (values[0].isBlank()) {
                LOGGER.error("Provided Resource ID in \"%s\" in file \"%s\" is missing the namespace! Expected: \"namespace:id\", got: \"%s\".".formatted(name, obfuscatePath(path), value));
                validation = false;
            }
            if (values[1].isBlank()) {
                LOGGER.error("Provided Resource ID in \"%s\" in file \"%s\" is missing the id! Expected: \"namespace:id\", got: \"%s\".".formatted(name, obfuscatePath(path), value));
                validation = false;
            }
            // Only check if the resource location is valid if any above didn't catch issues.
            // Otherwise, this would trigger false errors that might confuse end user.
            if (validation && !ResourceLocation.isValidResourceLocation(value)) {
                LOGGER.error("Provided Resource ID in \"%s\" in file \"%s\" contains non [a-z0-9/._-] character, got \"%s\".".formatted(name, obfuscatePath(path), value));
                validation = false;
            }
            return validation;
        });

        RESOURCE_ID_REQUIRED = (data, jsonPath) -> REQUIRED.apply(data, jsonPath) && RESOURCE_ID.apply(data, jsonPath);

        HEX_COLOR = (element, path) -> {
            if (Objects.isNull(element)) return true;
            if (!NON_EMPTY.apply(element, path)) return false;

            String value = element.getAsString();
            boolean validation = true;

            if (value.startsWith("#")) {
                LOGGER.error("Hexadecimal color values should not start with #! Found in \"%s\" in file \"%s\"".formatted(name, obfuscatePath(path)));
                value = value.substring(1);
                validation = false;
            }
            if (value.length() != 6) {
                LOGGER.error("Too %s hexadecimal value for color in \"%s\" in file \"%s\"! String should have length 6 (RR GG BB (Red / Green / Blue value)), but it has %d."
                    .formatted(value.length() > 6? "long": "short", name, obfuscatePath(path), value.length())
                );
                validation = false;
            } else {
                try {
                    HexFormat.fromHexDigits(value);
                } catch (Exception e) {
                    LOGGER.error("Non-Hexadecimal character found in provided string (\"%s\") in \"%s\" in file \"%s\".".
                        formatted(value, name, obfuscatePath(path))
                    );
                    validation = false;
                }
            }

            return validation;
        };

        HEX_COLOR_REQUIRED = (element, path) -> REQUIRED.apply(element, path) && HEX_COLOR.apply(element, path);
    }

    /**
     * Used to get {@link Validator#HEX_COLOR} with an array policy wrapper.
     * @param array True -> Require / False Prohibit arrays.
     * @return Validator used as a wrapper of basic {@link Validator#HEX_COLOR} validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getHexColorValidation(boolean array) {
       return (element, path) -> HEX_COLOR.apply(element, path) && (Objects.isNull(element) || (array? assertArray(element, path): assertNotArray(element, path)) );
    }

    /**
     * Used to get {@link Validator#HEX_COLOR_REQUIRED} with an array policy wrapper.
     * @param array True -> Require / False Prohibit arrays.
     * @return Validator used as a wrapper of basic {@link Validator#HEX_COLOR_REQUIRED} validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getRequiredHexColorValidation(boolean array) {
        return (element, path) -> HEX_COLOR_REQUIRED.apply(element, path) && (array? NON_EMPTY_ARRAY_REQUIRED.apply(element, path): assertNotArray(element, path));
    }

    /**
     * Used to get {@link Validator#RESOURCE_ID} with an array policy wrapper.
     * @param array True -> Require / False Prohibit arrays.
     * @return Validator used as a wrapper of basic {@link Validator#RESOURCE_ID} validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getResourceIDValidation(boolean array) {
        return (element, path) -> RESOURCE_ID.apply(element, path) && (Objects.isNull(element) || (array? assertArray(element, path): assertNotArray(element, path)));
    }

    /**
     * Used to get {@link Validator#RESOURCE_ID_REQUIRED} with an array policy wrapper.
     * @param array True -> Require / False Prohibit arrays.
     * @return Validator used as a wrapper of basic {@link Validator#RESOURCE_ID_REQUIRED} validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getRequiredResourceIDValidation(boolean array) {
        return (element, path) -> RESOURCE_ID_REQUIRED.apply(element, path) && (array? NON_EMPTY_ARRAY_REQUIRED.apply(element, path): assertNotArray(element, path));
    }

    /**
     * Used to get {@link Validator#NON_EMPTY} with an array policy wrapper.
     * @param array True -> Require / False Prohibit arrays.
     * @return Validator used as a wrapper of basic {@link Validator#NON_EMPTY} validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getNonEmptyValidation(boolean array) {
        return (element, path) -> NON_EMPTY.apply(element, path) && (Objects.isNull(element) || (array? assertArray(element, path): assertNotArray(element, path)));
    }

    /**
     * Used to get {@link Validator#NON_EMPTY_REQUIRED} with an array policy wrapper.
     * @param array True -> Require / False Prohibit arrays.
     * @return Validator used as a wrapper of basic {@link Validator#NON_EMPTY_REQUIRED} validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getRequiredNonEmptyValidation(boolean array) {
        return (element, path) -> NON_EMPTY_REQUIRED.apply(element, path) && (array? NON_EMPTY_ARRAY_REQUIRED.apply(element, path): assertNotArray(element, path));
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
                if (!element.isJsonPrimitive()) throw new ClassCastException();
                double value = element.getAsDouble();
                if (value < min || value > max) {
                    LOGGER.error("\"%s\" in file \"%s\" is out of range! Provided: %f, Min: %f, Max: %f.".formatted(name, obfuscatePath(path), value, min, max));
                    return false;
                }
                return true;
            } catch (Exception e) {
                if (Objects.isNull(element)) return true;
                LOGGER.error("\"%s\" in file \"%s\" requires a numeric value!".formatted(name, obfuscatePath(path)));
            }
            return false;
        });
    }

    /**
     * Used to get validator of Number Range. Supports: int, long, float, double.
     * @param min Minimum value.
     * @param max Maximum value.
     * @param array Determines if value should be an array. True -> Accepts only arrays / False -> Accepts only non-arrays.
     * @return BiFunction used as validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getRange(double min, double max, boolean array) {
        return (element, path) ->
            Objects.isNull(element) || (array? assertArray(element, path): assertNotArray(element, path)) && getRange(min, max).apply(element, path);
    }

    /**
     * Used to get validator of Number Range marked as Required. Supports: int, long, float, double.
     * @param min Minimum value.
     * @param max Maximum value.
     * @return BiFunction used as validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getRequiredRange(double min, double max) {
        return (element, path) -> REQUIRED.apply(element, path) && getRange(min, max).apply(element, path);
    }

    /**
     * Used to get validator of Number Range marked as Required. Supports: int, long, float, double.
     * @param min Minimum value.
     * @param max Maximum value.
     * @param array Determines if value should be an array. True -> Accepts only arrays / False -> Accepts only non-arrays.
     * @return BiFunction used as validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getRequiredRange(double min, double max, boolean array) {
        return (element, path) -> (array? NON_EMPTY_ARRAY_REQUIRED.apply(element, path): assertNotArray(element, path)) && getRange(min, max).apply(element, path);
    }

    /**
     * Used to get validator of Number Range, checking additionally for illegal floating-point values. Supports: int, long, (Will issue warning) -> float, double
     * @param min Minimum value.
     * @param max Maximum value.
     * @return BiFunction used as validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getIntRange(long min, long max) {
        return (data, jsonPath) -> validateArray(data, jsonPath, (element, path) -> {
            try {
                if (!element.isJsonPrimitive()) throw new ClassCastException();
                if (LOGGER.shouldLog) {
                    double check = element.getAsDouble();
                    if (Math.ceil(check) > check) LOGGER.warn("\"%s\" in file \"%s\" should be an integer. Floating-point values are not supported for this field.".formatted(name, obfuscatePath(path)));
                }

                long value = element.getAsLong();
                if (value < min || value > max) {
                    LOGGER.error("\"%s\" in file \"%s\" is out of range! Provided: %d, Min: %d, Max: %d.".formatted(name, obfuscatePath(path), value, min, max));
                    return false;
                }
                return true;
            } catch (ClassCastException | NullPointerException e) {
                if (Objects.isNull(element)) return true;
                LOGGER.error("\"%s\" in file \"%s\" requires a numeric value!".formatted(name, obfuscatePath(path)));
            }
            return false;
        });
    }

    /**
     * Used to get validator of Number Range, checking additionally for illegal floating-point values. Supports: int, long, (Will issue warning) -> float, double
     * @param min Minimum value.
     * @param max Maximum value.
     * @param array Determines if value should be an array. True -> Accepts only arrays / False -> Accepts only non-arrays.
     * @return BiFunction used as validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getIntRange(long min, long max, boolean array) {
        return (jsonElement, path) ->
            Objects.isNull(jsonElement) || (array? assertArray(jsonElement, path): assertNotArray(jsonElement, path)) && getIntRange(min, max).apply(jsonElement, path);
    }

    /**
     * Used to get validator of Number Range, marked as Required, checking additionally for illegal floating-point values.
     * Supports: int, long, (Will issue warning) -> float, double
     * @param min Minimum value.
     * @param max Maximum value.
     * @return BiFunction used as validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getRequiredIntRange(long min, long max) {
        return (element, path) -> REQUIRED.apply(element, path) && getIntRange(min, max).apply(element, path);
    }

    /**
     * Used to get validator of Number Range, marked as Required, checking additionally for illegal floating-point values.
     * Supports: int, long, (Will issue warning) -> float, double
     * @param min Minimum value.
     * @param max Maximum value.
     * @param array Determines if value should be an array. True -> Accepts only arrays / False -> Accepts only non-arrays.
     * @return BiFunction used as validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getRequiredIntRange(long min, long max, boolean array) {
        return (element, path) ->
            (array? NON_EMPTY_ARRAY_REQUIRED.apply(element, path): assertNotArray(element, path)) && getIntRange(min, max).apply(element, path);
    }

    /**
     * Used to get validator of Max Y Level range. Automatically changes the Int Range depending on the value of min field.
     * @param minName Name of the min y level field.
     * @return BiFunction used as validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getMaxYLevelValidation(String minName) {
        return (element, path) -> {
            if (!assertParentObject(element, path)) return false;
            JsonObject parent = element.getAsJsonObject();
            JsonElement value = parent.get(name);
            JsonElement min = parent.get(minName);
            int minValue = -64;

            if (!REQUIRED.apply(value, path)) return false;

            if (Objects.isNull(min)) {
                LOGGER.warn("Field \"%s\" is missing! Can't accurately verify \"%s\" in file \"%s\".".formatted(minName, name, Validator.obfuscatePath(path)));
            } else {
                try {
                    if (!min.isJsonPrimitive()) throw new ClassCastException();
                    minValue = min.getAsInt();
                    if (minValue > 320) {
                        LOGGER.error("Field \"%s\" is above max 320! Can't accurately verify \"%s\" in file \"%s\".".formatted(minName, name, Validator.obfuscatePath(path)));
                        minValue = -64;
                    }
                } catch (ClassCastException e) {
                    LOGGER.error("Field \"%s\" requires a Numeric value! Can't accurately verify \"%s\" in file \"%s\".".formatted(minName, name, Validator.obfuscatePath(path)));
                }
            }

            return getIntRange(minValue, 320, false).apply(value, path);
        };
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
     * Used to get validator of another JsonObject.
     * @param validators Map with validators for JsonObject to verify.
     * @param array Determines if expected is an array value (true) or not (false);
     * @return BiFunction used as validator.
     * @apiNote This is a wrapper of {@link Validator#validateObject(JsonElement, Path, Map)}. See JavaDoc for that method for full documentation.
     */
    public BiFunction<JsonElement, Path, Boolean> getObjectValidation(Map<String, BiFunction<JsonElement, Path, Boolean>> validators, boolean array) {
        return (element, path) -> Objects.isNull(element) || (array? assertArray(element, path): assertNotArray(element, path)) && validateObject(element, path, validators);
    }

    /**
     * Used to get validator of another JsonObject marked as Required.
     * @param validators Map with validators for JsonObject to verify.
     * @return BiFunction used as validator.
     * @apiNote This is a wrapper of {@link Validator#validateObject(JsonElement, Path, Map)}. See JavaDoc for that method for full documentation.
     */
    public BiFunction<JsonElement, Path, Boolean> getRequiredObjectValidation(Map<String, BiFunction<JsonElement, Path, Boolean>> validators) {
        return (element, path) -> REQUIRED.apply(element, path) && validateObject(element, path, validators);
    }

    /**
     * Used to get validator of another JsonObject marked as Required.
     * @param validators Map with validators for JsonObject to verify.
     * @param array Determines if expected is an array value (true) or not (false);
     * @return BiFunction used as validator.
     * @apiNote This is a wrapper of {@link Validator#validateObject(JsonElement, Path, Map)}. See JavaDoc for that method for full documentation.
     */
    public BiFunction<JsonElement, Path, Boolean> getRequiredObjectValidation(Map<String, BiFunction<JsonElement, Path, Boolean>> validators, boolean array) {
        return (element, path) -> (array? NON_EMPTY_ARRAY_REQUIRED.apply(element, path): assertNotArray(element, path)) && validateObject(element, path, validators);
    }

    /**
     * Custom version of {@link Validator#getRequiredIllegalValuesValidation(List)} that is used
     * to check if ID isn't already registered in the provided registry.
     * @param values List with IDS to check against.
     * @return BiFunction used as validator.
     * @apiNote Take a note this implements "NON_EMPTY_REQUIRED" and assertNotArray validations.
     */
    public BiFunction<JsonElement, Path, Boolean> getIDValidation(List<String> values) {
        return (element, path) -> {
            if (!assertNotArray(element, path) || !NON_EMPTY_REQUIRED.apply(element, path)) return false;
            String value = element.getAsString();
            if (values.contains(value)) {
                LOGGER.error("\"%s\" (%s) found in file \"%s\" is already registered!".formatted(name, value, obfuscatePath(path)));
                return false;
            }
            if (!ResourceLocation.isValidPath(value)) {
                LOGGER.error("\"%s\" (%s) found in file \"%s\" contains non [a-z0-9/._-] character!".formatted(name, value, obfuscatePath(path)));
                return false;
            }
            return true;
        };
    }

    /**
     * Custom version of {@link Validator#getRequiredAcceptsOnlyValidation(List)} that is used
     * to check if ID is registered in the provided registry.
     * @param values List with IDS to check against.
     * @param registryType String with a registry type. Used only for logging.
     * @return BiFunction used as validator.
     * @apiNote Log entry template:<br>
     * {@code "FIELD_NAME" (VALUE) found in file "PATH" isn't registered in "registryType"!}
     */
    public BiFunction<JsonElement, Path, Boolean> getRegisteredIDValidation(List<String> values, String registryType) {
        return (jsonElement, jsonPath) -> validateArray(jsonElement, jsonPath, (element, path) -> {
            if (!NON_EMPTY_REQUIRED.apply(element, path)) return false;
            String value = element.getAsString();
            boolean isRegistered = values.contains(value);
            if (!isRegistered) LOGGER.error("\"%s\" (%s) found in file \"%s\" isn't registered in \"%s\"!".formatted(name, value, obfuscatePath(path), registryType));
            return isRegistered;
        });
    }

    /**
     * Custom version of {@link Validator#getRequiredAcceptsOnlyValidation(List)} that is used
     * to check if ID is registered in the provided registry.
     * @param values List with IDS to check against.
     * @param registryType String with a registry type. Used only for logging.
     * @param array Determines if expected is an array value (true) or not (false);
     * @return BiFunction used as validator.
     * @apiNote Log entry template:<br>
     * {@code "FIELD_NAME" (VALUE) found in file "PATH" isn't registered in "registryType"!}
     */
    public BiFunction<JsonElement, Path, Boolean> getRegisteredIDValidation(List<String> values, String registryType, boolean array) {
        return (element, path) ->
            Objects.isNull(element) || (array? assertArray(element, path): assertNotArray(element, path)) && getRegisteredIDValidation(values, registryType).apply(element, path);
    }

    /**
     * Custom version of {@link Validator#getRequiredAcceptsOnlyValidation(List)} that is used
     * to check if ID is registered in the provided registry. Marked as Required.
     * @param values List with IDS to check against.
     * @param registryType String with a registry type. Used only for logging.
     * @return BiFunction used as validator.
     * @apiNote Log entry template:<br>
     * {@code "FIELD_NAME" (VALUE) found in file "PATH" isn't registered in "registryType"!}
     */
    public BiFunction<JsonElement, Path, Boolean> getRequiredRegisteredIDValidation(List<String> values, String registryType) {
        return (element, path) -> REQUIRED.apply(element, path) && getRegisteredIDValidation(values, registryType).apply(element, path);
    }

    /**
     * Custom version of {@link Validator#getRequiredAcceptsOnlyValidation(List)} that is used
     * to check if ID is registered in the provided registry. Marked as Required.
     * @param values List with IDS to check against.
     * @param registryType String with a registry type. Used only for logging.
     * @param array Determines if expected is an array value (true) or not (false);
     * @return BiFunction used as validator.
     * @apiNote Log entry template:<br>
     * {@code "FIELD_NAME" (VALUE) found in file "PATH" isn't registered in "registryType"!}
     */
    public BiFunction<JsonElement, Path, Boolean> getRequiredRegisteredIDValidation(List<String> values, String registryType, boolean array) {
        return (element, path) ->
            (array? NON_EMPTY_ARRAY_REQUIRED.apply(element, path): assertNotArray(element, path)) && getRegisteredIDValidation(values, registryType).apply(element, path);
    }

    /**
     * Used to get a validator that checks if value is not one of the illegal ones. Supports: String
     * @param values List with illegal String values.
     * @return BiFunction used as validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getIllegalValuesValidation(List<String> values) {
        return (data, jsonPath) -> validateArray(data, jsonPath, (element, path) -> {
            if (Objects.isNull(element)) return true;
            try {
                if (!element.isJsonPrimitive()) throw new ClassCastException();
                String value = element.getAsString();
                boolean isIllegal = values.contains(value);
                if (isIllegal) LOGGER.error("\"%s\" in file \"%s\" contains illegal value (%s)! Illegal values are: %s".formatted(name, obfuscatePath(path), value, String.join(", ", values)));
                return !isIllegal;
            } catch (ClassCastException e) {
                LOGGER.error("\"%s\" in file \"%s\" requires String value!".formatted(name, obfuscatePath(path)));
            }
            return false;
        });
    }

    /**
     * Used to get a validator that checks if value is not one of the illegal ones. Supports: String
     * @param values List with illegal String values.
     * @param array Determines if expected is an array value (true) or not (false);
     * @return BiFunction used as validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getIllegalValuesValidation(List<String> values, boolean array) {
        return (element, path) ->
            Objects.isNull(element) || (array? assertArray(element, path): assertNotArray(element, path)) && getIllegalValuesValidation(values).apply(element, path);
    }

    /**
     * Used to get a validator that checks if value is not one of the illegal ones. Supports: String
     * @param values List with illegal String values.
     * @return BiFunction used as validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getRequiredIllegalValuesValidation(List<String> values) {
        return (element, path) -> REQUIRED.apply(element, path) && getIllegalValuesValidation(values).apply(element, path);
    }

    /**
     * Used to get a validator that checks if value is not one of the illegal ones. Supports: String
     * @param values List with illegal String values.
     * @param array Determines if expected is an array value (true) or not (false);
     * @return BiFunction used as validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getRequiredIllegalValuesValidation(List<String> values, boolean array) {
        return (element, path) ->
            (array? NON_EMPTY_ARRAY_REQUIRED.apply(element, path): assertNotArray(element, path)) && getIllegalValuesValidation(values).apply(element, path);
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
                if (!element.isJsonPrimitive()) throw new ClassCastException();
                String value = element.getAsString();
                boolean validation = values.contains(value);
                if (!validation) LOGGER.error("\"%s\" in file \"%s\" contains illegal value (%s)! Accepted values are: %s".formatted(name, obfuscatePath(path), value, String.join(", ", values)));
                return validation;
            } catch (ClassCastException e) {
                LOGGER.error("\"%s\" in file \"%s\" requires String value!".formatted(name, obfuscatePath(path)));
            }
            return false;
        });
    }

    /**
     * Used to get a validator that checks if value is one of the acceptable ones. Supports: String
     * @param values List with accepted String values.
     * @param array Determines if expected is an array value (true) or not (false);
     * @return BiFunction used as validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getAcceptsOnlyValidation(List<String> values, boolean array) {
        return (element, path) ->
            Objects.isNull(element) || (array? assertArray(element, path): assertNotArray(element, path)) && getAcceptsOnlyValidation(values).apply(element, path);
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
     * Used to get a validator that checks if value is one of the acceptable ones, marked as Required. Supports: String
     * @param values List with accepted String values.
     * @param array Determines if expected is an array value (true) or not (false);
     * @return BiFunction used as validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getRequiredAcceptsOnlyValidation(List<String> values, boolean array) {
        return (element, path) ->
            (array? NON_EMPTY_ARRAY_REQUIRED.apply(element, path): assertNotArray(element, path)) && getAcceptsOnlyValidation(values).apply(element, path);
    }

    /**
     * Used to get a validator that checks the array for containing illegal pairs of values. Supports: String
     * @param pairs List of Pairs with values that are illegal at the same time.
     * @apiNote This validator can only be used for array values.
     * @return BiFunction used as validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getIllegalPairsValidation(List<Pair<String, String>> pairs) {
        return (element, path) -> {
            if (Objects.isNull(element)) return true;
            if (!assertArray(element, path)) return false;

            List<String> values = new ArrayList<>();
            AtomicBoolean validation = new AtomicBoolean(true);

            element.getAsJsonArray().forEach(entry -> {
                try {
                    values.add(entry.getAsString());
                } catch (Exception e) {
                    LOGGER.error("Non-String value present in the array of \"%s\" in file \"%s\".".formatted(name, obfuscatePath(path)));
                    validation.set(false);
                }
            });

            if (values.size() <= 1 && !validation.get()) return false;

            pairs.forEach(pair -> {
                if (values.contains(pair.getFirst()) && values.contains(pair.getSecond())) {
                    LOGGER.error("Illegal pair found (%s and %s) in the array \"%s\" of file \"%s\".".formatted(pair.getFirst(), pair.getSecond(), name, obfuscatePath(path)));
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
        return (element, path) -> NON_EMPTY_ARRAY_REQUIRED.apply(element, path) && getIllegalPairsValidation(pairs).apply(element, path);
    }

    /**
     * Used to pass the parent object to the validators.
     * Suffix _rg is required.
     * Parent object will be added under the "TEMP" field of the object
     * (or for all entries in the array) and validation will be launched as normal.
     * @param validators Map with validators.
     * @param required Determines if the value is required or not.
     * @return BiFunction used as validator.
     * @apiNote Does not support arrays of arrays. Only Array of objects is supported.
     * @implSpec Map of validators is required to be modifiable. Otherwise, an exception will be thrown, causing the game crash.
     */
    public BiFunction<JsonElement, Path, Boolean> getPassParentToValidators(Map<String, BiFunction<JsonElement, Path, Boolean>> validators, boolean required) {
        return (parent, path) -> {
            if (!assertParentObject(parent, path)) return false;
            JsonObject parentObj = parent.getAsJsonObject();
            return passTempToValidators(parentObj, parentObj.get(name), path, validators, required);
        };
    }

    /**
     * Used to pass the parent object to the validators.
     * Suffix _rg is required.
     * Parent object will be added under the "TEMP" field of the object
     * (or for all entries in the array) and validation will be launched as normal.
     * @param validators Map with validators.
     * @param required Determines if the value is required or not.
     * @param array Determines if the value is meant to be an array or not.
     * @return BiFunction used as validator.
     * @apiNote Does not support arrays of arrays. Only Array of objects is supported.
     * @implSpec Map of validators is required to be modifiable. Otherwise, an exception will be thrown, causing the game crash.
     */
    public BiFunction<JsonElement, Path, Boolean> getPassParentToValidators(Map<String, BiFunction<JsonElement, Path, Boolean>> validators, boolean required, boolean array) {
        return (parent, path) -> {
            if (!assertParentObject(parent, path)) return false;
            JsonObject parentObj = parent.getAsJsonObject();
            JsonElement child = parentObj.get(name);
            if (Objects.nonNull(child) && (array? !NON_EMPTY_ARRAY_REQUIRED.apply(child, path) : !assertNotArray(child, path))) return false;
            return passTempToValidators(parentObj, child, path, validators, required);
        };
    }

    /**
     * Used to execute the specified validator only if the specified fieldName is set. Requires the specified field to be a boolean.
     * Prints "Field x should not be present when x field is not set" if fieldName doesn't exist.
     * @param fieldName Name of the Field which should be set.
     * @param validator Validator to execute if fieldName is present.
     * @return BiFunction used as a validator.
     * @apiNote Requires use of _rg suffix.
     */
    public BiFunction<JsonElement, Path, Boolean> getIfOtherFieldSet(String fieldName, BiFunction<JsonElement, Path, Boolean> validator) {
        return (element, path) -> {
            if (!assertParentObject(element, path)) return false;
            JsonObject parent = element.getAsJsonObject();
            JsonElement validatedField = parent.get(name);

            if (isOtherFieldSet(fieldName, parent)) return validator.apply(validatedField, path);
            if (Objects.nonNull(validatedField)) LOGGER.warn("\"%s\" should not be present when \"%s\" is not set in file \"%s\"".formatted(name, fieldName, Validator.obfuscatePath(path)));
            return true;
        };
    }

    /**
     * Used to execute the specified validator only if the specified fieldName is present.
     * Prints "Field x should not be present when x field is not present" if fieldName doesn't exist.
     * @param fieldName Name of the Field which should be set.
     * @param validator Validator to execute if fieldName is present.
     * @return BiFunction used as a validator.
     * @apiNote Requires use of _rg suffix.
     */
    public BiFunction<JsonElement, Path, Boolean> getIfOtherFieldPresent(String fieldName, BiFunction<JsonElement, Path, Boolean> validator) {
        return (element, path) -> {
            if (!assertParentObject(element, path)) return false;
            JsonObject parent = element.getAsJsonObject();
            JsonElement validatedField = parent.get(name);

            if (isOtherFieldPresent(fieldName, parent)) return validator.apply(validatedField, path);
            if (Objects.nonNull(validatedField)) LOGGER.warn("\"%s\" should not be present when \"%s\" is not present in file \"%s\"".formatted(name, fieldName, Validator.obfuscatePath(path)));
            return true;
        };
    }

    /**
     * Utility method for custom validators. Used to check if specified field is set in the provided object.
     * @param fieldName Name of the field to check.
     * @param object JsonObject to check if the specified field is set.
     * @return Returns false if the value is not a boolean, otherwise returns set value of the field.
     */
    public static boolean isOtherFieldSet(String fieldName, JsonObject object) {
        JsonElement field = object.get(fieldName);
        return Objects.nonNull(field) && field.isJsonPrimitive() && field.getAsJsonPrimitive().isBoolean() && field.getAsJsonPrimitive().getAsBoolean();
    }

    /**
     * Utility method for custom validators. Used to check if specified field is present in the provided object.
     * @param fieldName Name of the field to check.
     * @param object JsonObject to check if the specified field is set.
     * @return True if field exists, otherwise false.
     * @apiNote If you need to also check if the boolean value of this field is true/false, use {@link Validator#isOtherFieldSet(String, JsonObject)} instead.
     */
    public static boolean isOtherFieldPresent(String fieldName, JsonObject object) {
        return Objects.nonNull(object.get(fieldName));
    }

    /**
     * Used to pass a provided object to the validators by adding it to the TEMP field of the object
     * (or for all entries in the array) and validation will be launched as normal.
     * @param tempObject Object to pass in the TEMP field.
     * @param element JsonObject or JsonArray of JsonObject, to which TEMP field should be added.
     * @param validators Map with validators.
     * @param required Determines if the value is required or not.
     * @return BiFunction used as validator.
     * @apiNote Does not support arrays of arrays. Only Array of objects is supported.
     * @implSpec Map of validators is required to be modifiable. Otherwise, an exception will be thrown, causing the game crash.
     */
    public boolean passTempToValidators(JsonObject tempObject, @Nullable JsonElement element, Path path, Map<String, BiFunction<JsonElement, Path, Boolean>> validators, boolean required) {
        if (Objects.isNull(element)) return (required? REQUIRED.apply(null, path): true);
        if (!element.isJsonObject() && !element.isJsonArray()) {
            LOGGER.error("Expected array or object for \"%s\" in file \"%s\".".formatted(name, obfuscatePath(path)));
            return false;
        }

        validators.put("TEMP", Validator.ALL);

        Consumer<JsonObject> addTemp = jsonObject -> {
            checkForTEMP(jsonObject, path, true);
            jsonObject.add("TEMP", tempObject.deepCopy());;
        };

        AtomicBoolean validation = new AtomicBoolean(true);
        if (element.isJsonObject()) {
            addTemp.accept(element.getAsJsonObject());
        } else {
            element.getAsJsonArray().forEach(entry -> {
                // If Support for arrays of arrays is required, this is the place it needs to be added.
                if (!assertObject(entry, path)) {
                    validation.set(false);
                    return;
                }
                addTemp.accept(entry.getAsJsonObject());
            });
        }

        boolean result = validateObject(element, path, validators);
        validators.remove("TEMP");
        return result && validation.get();
    }

    /**
     * Utility method checking if TEMP field is present. Used mostly in scenarios where parent is required.
     * @param object JsonObject to check for TEMP field.
     * @param path Path of the file.
     * @return True if TEMP exists and is JsonObject, otherwise false.
     */
    public static boolean checkForTEMP(JsonObject object, Path path, boolean shouldLog) {
        JsonElement tempElement = object.get("TEMP");
        boolean temp = Objects.nonNull(tempElement);
        if (temp && shouldLog) LOGGER.warn("Unknown key (\"TEMP\") found in file \"%s\".".formatted(obfuscatePath(path)));
        return temp && tempElement.isJsonObject();
    }

    /**
     * Used to validate a JsonArray with provided validator (BiFunction).
     * @param element JsonElement to verify. If not Array, it will verify just that element.
     * @param path Path of the json, only for informational purposes.
     * @param validator BiFunction used as a Validator.
     * @return true if all entries in the array pass validation, otherwise false. In case JsonElement is not an array, returns result of the validation.
     */
    public static boolean validateArray(@Nullable JsonElement element, Path path, BiFunction<JsonElement, Path, Boolean> validator) {
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
     * @implSpec Map of validators is required to be modifiable. Otherwise, an exception could be thrown, causing the game crash.
     */
    public boolean validateObject(JsonElement jsonElement, Path path, Map<String, BiFunction<JsonElement, Path, Boolean>> validators) {
        return validateArray(jsonElement, path, (element, jsonPath) -> {
            if (Objects.isNull(element)) return true;
            if (!assertObject(element, path)) return false;

            JsonObject object = element.getAsJsonObject();
            AtomicBoolean validation = new AtomicBoolean(true);

            if (LOGGER.shouldLog) object.entrySet().forEach(entry -> {
                if (!(validators.containsKey(entry.getKey()) || validators.containsKey(entry.getKey() + "_rg"))) {
                    LOGGER.warn("Unknown key (\"%s\") found in file \"%s\".".formatted(entry.getKey(), obfuscatePath(jsonPath)));
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
            LOGGER.error("Expected object for verification of key \"%s\" in file \"%s\"%s".formatted(name, obfuscatePath(path), Objects.isNull(element)? ", got null.": "."));
            return false;
        }
        return true;
    }

    public boolean assertParentObject(@Nullable JsonElement element, Path path) {
        if (Objects.isNull(element) || !element.isJsonObject()) {
            LOGGER.error("Expected parent object for verification of key \"%s\" in file \"%s\"%s".formatted(name, obfuscatePath(path), Objects.isNull(element)? ", got null.": "."));
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
            LOGGER.error("Array found on non-array key \"%s\" in file \"%s\".".formatted(name, obfuscatePath(path)));
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
            LOGGER.error("Expected array for verification of key \"%s\" in file \"%s\"%s".formatted(name, obfuscatePath(path), Objects.isNull(element)? ", got null.": "."));
            return false;
        }
        return true;
    }

    /**
     * Utility method used for custom validators, allowing to get the name of the field stored in used validator.
     * @return Field Name of this Validator
     */
    public String getName() {
        return this.name;
    }

    /**
     * Used as a cache of ConfigDir, instead of calling get each time obfuscatePath executes.
     */
    private static Path CONFIGDIR = null;
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
        if (Objects.isNull(CONFIGDIR)) CONFIGDIR = FMLPaths.CONFIGDIR.get().resolve("emendatusenigmatica/");
        return CONFIGDIR.relativize(path).toString();
    }
}
