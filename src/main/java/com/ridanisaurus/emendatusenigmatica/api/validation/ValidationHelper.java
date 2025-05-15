/*
 * MIT License
 *
 * Copyright (c) 2024. Ridanisaurus
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.ridanisaurus.emendatusenigmatica.api.validation;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class holds utility methods for validators, that allow for easy(ish) access to other fields.
 * <br><br>
 * Take a note that those methods <i>don't perform any logging</i> and will simply return null / false on errors.
 * @implSpec Checking if a field exists before trying to access is, while normally a good idea,
 * should not be performed by using {@link ValidationHelper#isOtherFieldPresent(JsonObject, String)}.<br>
 * Use {@link ValidationHelper#getElementFromPath(JsonObject, String)} directly, and check if returned value isn't null.<br>
 * This is due to performance concerns, as the same path would need to be decomposed twice.
 */
public class ValidationHelper {
    /**
     * Used to check if the other field is present.
     * @param rootElement Root Element of the validated object.
     * @param path Path to the desired field to validate.
     * @return True if field exists and contains some value, false if field was not found.
     * @apiNote This method should only be used in scenarios,
     * when the validator behavior is meant to change if another field is present.
     * See the {@link ValidationHelper} Impl Spec for more details.
     */
    public static boolean isOtherFieldPresent(JsonObject rootElement, String path) {
        return Objects.nonNull(getElementFromPath(rootElement, path));
    }

    /**
     * Used to check if the other field is set to a specified String value.
     * @param rootElement Root Element of the validated object.
     * @param path Path to the desired field to validate.
     * @param value Value to check the field for.
     * @return True if field exists, is a String type and contains a specified value, false otherwise.
     */
    public static boolean isOtherFieldSet(JsonObject rootElement, String path, String value) {
        var element = getElementFromPathAs(rootElement, path, Types.STRING);
        if (Objects.isNull(element)) return false;
        return element.getAsString().equals(value);
    }

    /**
     * Used to check if an array at specified path contains specified values.
     * @param rootElement Root Element of the validated object.
     * @param path Path to the array.
     * @param values Values to check the array for.
     * @return True if an array exists, and all values were found, otherwise false.
     */
    public static boolean doesArrayContainAny(JsonObject rootElement, String path, @NotNull List<String> values) {
        if (values.isEmpty()) return false;
        var element = getElementFromPath(rootElement, path);
        if (Objects.isNull(element) || !element.isJsonArray()) return false;
        var array = element.getAsJsonArray();
        for (String value : values)
            if (array.contains(new JsonPrimitive(value))) return true;
        return false;
    }

    /**
     * Used to check if an array at specified path contains specified values.
     * @param rootElement Root Element of the validated object.
     * @param path Path to the array.
     * @param values Values to check the array for.
     * @return True if an array exists, and all values were found, otherwise false.
     */
    public static boolean doesArrayContain(JsonObject rootElement, String path, @NotNull List<String> values) {
        if (values.isEmpty()) return false;
        var element = getElementFromPath(rootElement, path);
        if (Objects.isNull(element) || !element.isJsonArray()) return false;
        var array = element.getAsJsonArray();
        for (String value : values)
            if (!array.contains(new JsonPrimitive(value))) return false;
        return true;
    }

    /**
     * Used to get elements found in an array at specified path.
     * @param rootElement Root Element of the validated object.
     * @param path Path to the array.
     * @param values Values to check the array for.
     * @return List of found elements. Will not contain more elements than the list provided.
     */
    public static @Nullable List<String> getContainedInArray(JsonObject rootElement, String path, @NotNull List<String> values) {
        if (values.isEmpty()) return null;
        var element = getElementFromPath(rootElement, path);
        if (Objects.isNull(element) || !element.isJsonArray()) return null;
        List<String> result = new ArrayList<>();
        var array = element.getAsJsonArray();
        for (String value : values)
            if (array.contains(new JsonPrimitive(value))) result.add(value);
        return result;
    }

    /**
     * Used to check if an array at specified path contains a specified value.
     * @param rootElement Root Element of the validated object.
     * @param path Path to the array.
     * @param value Value to check the array for.
     * @return True if an array exists and contains provided value, false otherwise.
     */
    public static boolean doesArrayContain(JsonObject rootElement, String path, String value) {
        var element = getElementFromPath(rootElement, path);
        if (Objects.isNull(element) || !element.isJsonArray()) return false;
        return element.getAsJsonArray().contains(new JsonPrimitive(value));
    }

    /**
     * Used to get the element from a specified path, with TypeValidation applied.
     * @param rootElement Root Element of the validated object.
     * @param path Path to the field.
     * @param type Type the field is expected to be.
     * @return JsonElement if field exists and passes type validation, null otherwise.
     */
    public static @Nullable JsonElement getElementFromPathAs(JsonObject rootElement, String path, Types type) {
        var element = getElementFromPath(rootElement, path);
        if (Objects.isNull(element) || !element.isJsonPrimitive()) return null;
        var primitive = element.getAsJsonPrimitive();
        return switch (Objects.requireNonNull(type)) {
            case BOOLEAN -> primitive.isBoolean()? primitive: null;
            case INTEGER, FLOAT -> primitive.isNumber()? primitive: null;
            case STRING, STRING_EMPTY -> primitive.isString()? primitive: null;
        };
    }

    /**
     * Used to get raw JsonElement from a specified path.
     * @param rootElement Root Element of the validated object.
     * @param path Path to the field.
     * @return JsonElement found at that field, or null if nothing was found.
     */
    public static @Nullable JsonElement getElementFromPath(JsonObject rootElement, String path) {
        List<String> pathElements = decomposePath(path);
        JsonElement currentElement = rootElement;
        for (String subPath : pathElements) {
            if (Objects.isNull(currentElement) || !currentElement.isJsonObject()) return null;

            if (!subPath.contains("[")) {
                currentElement = currentElement.getAsJsonObject().get(subPath);
                continue;
            }

            int index;
            try {
                index = Integer.parseInt(StringUtils.substringBetween(subPath, "[", "]"));
            } catch (NumberFormatException ex) { return null; }
            currentElement = currentElement.getAsJsonObject().get(StringUtils.substringBefore(subPath, "["));
            if (Objects.isNull(currentElement) || !currentElement.isJsonArray()) return null;
            currentElement = currentElement.getAsJsonArray().get(index);
        }
        return currentElement;
    }

    private static @NotNull List<String> decomposePath(@NotNull String path) {
        if (!path.startsWith("root")) throw new IllegalArgumentException("Invalid path! All paths should start with \"root\". Path: " + path);
        var result = new ArrayList<>(List.of(path.split("\\.")));
        result.removeFirst(); // Should get rid of the root element.
        return result;
    }

    /**
     * Used to cut off part of the path that is not in minecraft directory.<br>
     * <h4>Input:</h4>
     * <blockquote>
     * C:/Minecraft/config/emendatusenigmatica/strata/stone.json
     * </blockquote>
     * <h4>Result:</h4>
     * <blockquote>
     * strata/stone.json
     * </blockquote>
     * @param path Path to obfuscate.
     * @return String with an obfuscated path.
     */
    public static @NotNull String obfuscatePath(Path path) {
        return Analytics.CONFIG_DIR.relativize(path).toString();
    }
}
