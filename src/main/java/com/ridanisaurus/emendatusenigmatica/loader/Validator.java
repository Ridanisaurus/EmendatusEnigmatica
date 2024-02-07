package com.ridanisaurus.emendatusenigmatica.loader;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.ridanisaurus.emendatusenigmatica.config.EEConfig;
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
    //TODO: Validation of:
    // - Strata - DONE
    // - Compat Model - DONE
    // - Material Model - DONE
    // - All of the Material sub-models (pain)
    // - Deposits
    public static final boolean log = EEConfig.common.logConfigErrors.get();
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
                if (element.isJsonObject()) throw new ClassCastException();
                boolean value = element.getAsString().isBlank();
                if (value && log) LOGGER.error("\"%s\" in file \"%s\" can't be blank!".formatted(name, obfuscatePath(path)));
                return !value;
            } catch (ClassCastException e) {
                if (log) LOGGER.error("\"%s\" in file \"%s\" requires String value!".formatted(name, obfuscatePath(path)));
            }
            return false;
        });

        REQUIRED = (data, jsonPath) -> {
            boolean value = Objects.nonNull(data);
            if (!value && log) LOGGER.error("\"%s\" in file \"%s\" is missing!".formatted(name, obfuscatePath(jsonPath)));
            return value;
        };

        NON_EMPTY_REQUIRED = (data, jsonPath) -> REQUIRED.apply(data, jsonPath) && NON_EMPTY.apply(data, jsonPath);

        REQUIRES_BOOLEAN = (data, jsonPath) -> {
            if (Objects.isNull(data)) return true;
            try {
                if (!data.isJsonPrimitive()) throw new ClassCastException();
                data.getAsBoolean();
                return true;
            } catch (ClassCastException e) {
                if (log) LOGGER.error("\"%s\" in file \"%s\" requires Boolean (true/false) value!".formatted(name, obfuscatePath(jsonPath)));
            }
            return false;
        };

        REQUIRES_BOOLEAN_REQUIRED = (data, jsonPath) -> REQUIRED.apply(data, jsonPath) && REQUIRES_BOOLEAN.apply(data, jsonPath);

        REQUIRES_INT = (data, jsonPath) -> {
            if (Objects.isNull(data)) return true;
            try {
                if (!data.isJsonPrimitive()) throw new ClassCastException();
                double value = data.getAsDouble();
                if (log && Math.ceil(value) > value) {
                    LOGGER.warn("\"%s\" in file \"%s\" should be an integer. Floating-point values are not supported for this field.".formatted(name, obfuscatePath(jsonPath)));
                }
                return true;
            } catch (ClassCastException | NumberFormatException e) {
                if (log) LOGGER.error("\"%s\" in file \"%s\" requires numeric value!".formatted(name, obfuscatePath(jsonPath)));
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
                if (log) LOGGER.error("\"%s\" in file \"%s\" requires numeric value!".formatted(name, obfuscatePath(jsonPath)));
            }
            return false;
        };

        REQUIRES_FLOAT_REQUIRED = (data, jsonPath) -> REQUIRED.apply(data, jsonPath) && REQUIRES_INT.apply(data, jsonPath);

        RESOURCE_ID = (data, jsonPath) -> validateArray(data, jsonPath, (element, path) -> {
            //TODO: Probably replace this with ResourceLocation#isValidResourceLocation
            if (Objects.isNull(element)) return true;
            if (!NON_EMPTY.apply(element, path)) return false;
            String value = element.getAsString();
            String[] values = (value + " :").split(":");
            boolean validation = true;
            if (values.length != 2) {
                if (log) LOGGER.error("Provided Resource ID in \"%s\" in file \"%s\" isn't in a proper format! Expected: \"namespace:id\", got: \"%s\".".formatted(name, obfuscatePath(path), value));
                return false;
            }
            if (values[0].isBlank()) {
                if (log) LOGGER.error("Provided Resource ID in \"%s\" in file \"%s\" is missing the namespace! Expected: \"namespace:id\", got: \"%s\".".formatted(name, obfuscatePath(path), value));
                validation = false;
            }
            if (values[1].isBlank()) {
                if (log) LOGGER.error("Provided Resource ID in \"%s\" in file \"%s\" is missing the id! Expected: \"namespace:id\", got: \"%s\".".formatted(name, obfuscatePath(path), value));
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
                if (log) LOGGER.error("Hexadecimal color values should not start with #! Found in \"%s\" in file \"%s\"".formatted(name, obfuscatePath(path)));
                value = value.substring(1);
                validation = false;
            }
            if (value.length() != 6) {
                if (log) {
                    LOGGER.error("Too %s hexadecimal value for color in \"%s\" in file \"%s\"! String should have length 6 (RR GG BB (Red / Green / Blue value)), but it has %d."
                        .formatted(value.length() > 6? "long": "short", name, obfuscatePath(path), value.length())
                    );
                }
                validation = false;
            } else {
                try {
                    HexFormat.fromHexDigits(value);
                } catch (Exception e) {
                    if (log) {
                        LOGGER.error("Non-Hexadecimal character found in provided string (\"%s\") in \"%s\" in file \"%s\".".
                            formatted(value, name, obfuscatePath(path))
                        );
                    }
                    validation = false;
                }
            }

            return validation;
        };

        HEX_COLOR_REQUIRED = (element, path) -> REQUIRED.apply(element, path) && HEX_COLOR.apply(element, path);
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
                    if (log) LOGGER.error("\"%s\" in file \"%s\" is out of range! Provided: %f, Min: %f, Max: %f.".formatted(name, obfuscatePath(path), value, min, max));
                    return false;
                }
                return true;
            } catch (Exception e) {
                if (Objects.isNull(element)) return true;
                if (log) LOGGER.error("\"%s\" in file \"%s\" requires a numeric value!".formatted(name, obfuscatePath(path)));
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
        return (jsonElement, path) -> (array? assertArray(jsonElement, path): assertNotArray(jsonElement, path)) && getRange(min, max).apply(jsonElement, path);
    }

    /**
     * Used to get validator of Number Range marked as Required. Supports: int, long, float, double.
     * @param min Minimum value.
     * @param max Maximum value.
     * @return BiFunction used as validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getRequiredRange(double min, double max) {
        return (data, jsonPath) -> REQUIRED.apply(data, jsonPath) && getRange(min, max).apply(data, jsonPath);
    }

    /**
     * Used to get validator of Number Range marked as Required. Supports: int, long, float, double.
     * @param min Minimum value.
     * @param max Maximum value.
     * @param array Determines if value should be an array. True -> Accepts only arrays / False -> Accepts only non-arrays.
     * @return BiFunction used as validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getRequiredRange(double min, double max, boolean array) {
        return (jsonElement, path) -> REQUIRED.apply(jsonElement, path) && getRange(min, max, array).apply(jsonElement, path);
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
                if (log) {
                    double check = element.getAsDouble();
                    if (Math.ceil(check) > check) LOGGER.warn("\"%s\" in file \"%s\" should be an integer. Floating-point values are not supported for this field.".formatted(name, obfuscatePath(path)));
                }

                long value = element.getAsLong();
                if (value < min || value > max) {
                    if (log) LOGGER.error("\"%s\" in file \"%s\" is out of range! Provided: %d, Min: %d, Max: %d.".formatted(name, obfuscatePath(path), value, min, max));
                    return false;
                }
                return true;
            } catch (ClassCastException | NullPointerException e) {
                if (Objects.isNull(element)) return true;
                if (log) LOGGER.error("\"%s\" in file \"%s\" requires a numeric value!".formatted(name, obfuscatePath(path)));
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
        return (jsonElement, path) -> (array? assertArray(jsonElement, path): assertNotArray(jsonElement, path)) && getIntRange(min, max).apply(jsonElement, path);
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
        return (element, path) -> REQUIRED.apply(element, path) && getIntRange(min, max, array).apply(element, path);
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
     * Used to get validator of another JsonObject marked as Required.
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
                if (!element.isJsonPrimitive()) throw new ClassCastException();
                String value = element.getAsString();
                boolean validation = values.contains(value);
                if (log && !validation) LOGGER.error("\"%s\" in file \"%s\" contains illegal value (%s)! Accepted values are: %s".formatted(name, obfuscatePath(path), value, String.join(", ", values)));
                return validation;
            } catch (ClassCastException e) {
                if (log) LOGGER.error("\"%s\" in file \"%s\" requires String value!".formatted(name, obfuscatePath(path)));
            }
            return false;
        });
    }

    /**
     * Used to get a validator that checks if value is one of the acceptable ones. Supports: String
     * @param values List with accepted String values.
     * @return BiFunction used as validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getAcceptsOnlyValidation(List<String> values, boolean array) {
        return (jsonElement, path) -> (array? assertArray(jsonElement, path): assertNotArray(jsonElement, path)) && getAcceptsOnlyValidation(values).apply(jsonElement, path);
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
     * @return BiFunction used as validator.
     */
    public BiFunction<JsonElement, Path, Boolean> getRequiredAcceptsOnlyValidation(List<String> values, boolean array) {
        return (jsonElement, path) -> REQUIRED.apply(jsonElement, path) && getAcceptsOnlyValidation(values, array).apply(jsonElement, path);
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
            if (!element.isJsonArray()) {
                if (log) LOGGER.error("Expected array for \"%s\" in file \"%s\".".formatted(name, obfuscatePath(path)));
                return false;
            }

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
                    if (log) LOGGER.error("Illegal pair found (%s and %s) in the array \"%s\" of file \"%s\".".formatted(pair.getFirst(), pair.getSecond(), name, obfuscatePath(path)));
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
     * Used to pass the parent object to the validators.
     * Suffix _rg is required.
     * Parent object will be added under the "TEMP" field of the object
     * (or for all entries in the array) and validation will be launched as normal.
     * @param validators Map with validators.
     * @return BiFunction used as validator.
     * @apiNote Does not support arrays of arrays. Only Array of objects is supported.
     */
    public BiFunction<JsonElement, Path, Boolean> getPassParentToValidators(Map<String, BiFunction<JsonElement, Path, Boolean>> validators, boolean required) {
        return (parent, path) -> {
            if (!assertParentObject(parent, path)) return false;
            JsonObject parentObj = parent.getAsJsonObject();
            return passTempToValidators(parentObj, parentObj.get(name), path, validators, required);
        };
    }

    /**
     * Used to pass a provided object to the validators by adding it to the TEMP field of the object
     * (or for all entries in the array) and validation will be launched as normal.
     * @param validators Map with validators.
     * @return BiFunction used as validator.
     * @apiNote Does not support arrays of arrays. Only Array of objects is supported.
     */
    public boolean passTempToValidators(JsonObject tempObject, @Nullable JsonElement element, Path path, Map<String, BiFunction<JsonElement, Path, Boolean>> validators, boolean required) {
        if (Objects.isNull(element)) return (required? REQUIRED.apply(null, path): true);
        if (element.isJsonPrimitive() || element.isJsonNull()) {
            if (log) LOGGER.error("Expected array or object for \"%s\" in file \"%s\".".formatted(name, obfuscatePath(path)));
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
    public boolean checkForTEMP(JsonObject object, Path path, boolean shouldLog) {
        JsonElement tempElement = object.get("TEMP");
        boolean temp = Objects.nonNull(tempElement);
        if (temp && log && shouldLog) LOGGER.warn("Unknown key (\"TEMP\") found in file \"%s\".".formatted(obfuscatePath(path)));
        return temp && tempElement.isJsonObject();
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
            if (!assertObject(element, path)) return false;

            JsonObject object = element.getAsJsonObject();
            AtomicBoolean validation = new AtomicBoolean(true);

            object.entrySet().forEach(entry -> {
                if (log && !(validators.containsKey(entry.getKey()) || validators.containsKey(entry.getKey() + "_rg"))) {
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
            if (log) LOGGER.error("Expected object for verification of key \"%s\" in file \"%s\"%s".formatted(name, obfuscatePath(path), Objects.isNull(element)? ", got null.": "."));
            return false;
        }
        return true;
    }

    public boolean assertParentObject(@Nullable JsonElement element, Path path) {
        if (Objects.isNull(element) || !element.isJsonObject()) {
            if (log) LOGGER.error("Expected parent object for verification of key \"%s\" in file \"%s\"%s".formatted(name, obfuscatePath(path), Objects.isNull(element)? ", got null.": "."));
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
            if (log) LOGGER.error("Array found on non-array key \"%s\" in file \"%s\".".formatted(name, obfuscatePath(path)));
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
            if (log) LOGGER.error("Expected array for verification of key \"%s\" in file \"%s\"%s".formatted(name, obfuscatePath(path), Objects.isNull(element)? ", got null.": "."));
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
