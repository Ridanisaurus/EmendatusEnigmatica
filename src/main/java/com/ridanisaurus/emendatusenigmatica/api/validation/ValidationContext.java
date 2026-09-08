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
import com.ridanisaurus.emendatusenigmatica.api.IEEPlugin;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayHandlingPolicy;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayPolicy;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.loader.EEPluginLoader;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Used to hold all necessary information for the validator.
 * @param validationElement Element currently validated
 * @param rootObject Root Json Object (if other fields are necessary).
 * @param currentPath Current Path inside the JSON file.
 * @param jsonFilePath Path to the json file, obfuscated.
 * @param arrayPolicy Field's {@link ArrayHandlingPolicy}.
 * @param logHandler Handler used to handle warn/error logging for this context.
 * @implSpec Please do not store any reference to this object outside the validation method.
 */
public record ValidationContext(
    JsonElement validationElement,
    @NotNull JsonObject rootObject,
    @NotNull String currentPath,
    @NotNull String jsonFilePath,
    @NotNull ArrayHandlingPolicy arrayPolicy,
    @NotNull EEPluginLoader pluginLoader,
    @NotNull IValidationLogHandler logHandler
) {

    /**
     * Used to hold all necessary information for the validator.
     * @param validationElement Element currently validated
     * @param rootObject Root Json Object (if other fields are necessary).
     * @param currentPath Current Path inside the JSON file.
     * @param jsonFilePath Path to the json file, obfuscated.
     * @param arrayPolicy Legacy reference to {@link ArrayPolicy}
     * @implSpec Please do not store any reference to this object outside the validation method.
     */
    public ValidationContext(
        JsonElement validationElement,
        @NotNull JsonObject rootObject,
        @NotNull String currentPath,
        @NotNull String jsonFilePath,
        @NotNull ArrayHandlingPolicy arrayPolicy,
        @NotNull EEPluginLoader pluginLoader
    ) {
        this(validationElement, rootObject, currentPath, jsonFilePath, arrayPolicy, pluginLoader, new IValidationLogHandler() {});
    }

    /**
     * Used to hold all necessary information for the validator.
     * @param validationElement Element currently validated
     * @param rootObject Root Json Object (if other fields are necessary).
     * @param currentPath Current Path inside the JSON file.
     * @param jsonFilePath Path to the json file, obfuscated.
     * @param arrayPolicy Legacy reference to {@link ArrayPolicy}
     * @implSpec Please do not store any reference to this object outside the validation method.
     * @deprecated Legacy ArrayPolicy method. Consider using updated {@link ValidationContext ArrayHandlingPolicy constructor}.
     */
    @Deprecated
    public ValidationContext(
        JsonElement validationElement,
        @NotNull JsonObject rootObject,
        @NotNull String currentPath,
        @NotNull String jsonFilePath,
        @NotNull ArrayPolicy arrayPolicy,
        @NotNull EEPluginLoader pluginLoader
    ) {
        this(validationElement, rootObject, currentPath, jsonFilePath, arrayPolicy.get(), pluginLoader);
    }

    /**
     * Utility method to get ValidationContext from previous data, but with updated information for another field of the object.
     * @param field Field name to base the information update on.
     * @param arrayPolicy ArrayPolicy of that field.
     * @return ValidationContext with validationElement and currentPath updated.
     * @throws IllegalArgumentException when validationElement of previous data is not a JsonObject!
     */
    @Contract("_, _ -> new")
    public @NotNull ValidationContext getWithField(String field, ArrayHandlingPolicy arrayPolicy) {
        if (!this.validationElement.isJsonObject()) throw new IllegalArgumentException("ValidationElement is not a json object! Requested field: " + field + " | Old Data: " + this);
        return new ValidationContext(
            this.validationElement.getAsJsonObject().get(field),
            this.rootObject,
            this.currentPath + "." + field,
            this.jsonFilePath,
            arrayPolicy,
            pluginLoader,
            logHandler
        );
    }

    /**
     * Utility method to get ValidationContext from previous data, but with updated ArrayHandlingPolicy.
     * @param arrayPolicy new ArrayHandlingPolicy.
     * @return ValidationContext for the same object, with new ArrayHandlingPolicy.
     */
    @Contract("_ -> new")
    public @NotNull ValidationContext getWithAHP(ArrayHandlingPolicy arrayPolicy) {
        return new ValidationContext(
            this.validationElement,
            this.rootObject,
            this.currentPath,
            this.jsonFilePath,
            arrayPolicy,
            pluginLoader,
            logHandler
        );
    }


    public @NotNull String getParentPath() {
        return StringUtils.substringBeforeLast(this.currentPath, ".");
    }

    public @NotNull String getParentFieldPath(String fieldName) {
        return getParentPath() + "." + fieldName;
    }

    public @Nullable JsonElement getParentField(String fieldName) {
        return ValidationHelper.getElementFromPath(this.rootObject, getParentFieldPath(fieldName));
    }

    public @Nullable JsonElement getParentFieldAs(Types type, String fieldName) {
        return ValidationHelper.getElementFromPathAs(this.rootObject, getParentFieldPath(fieldName), type);
    }

    /**
     * Utility method to get provided plugin's registry.
     * @param pluginClass Plugin owning the requested registry.
     * @return Registry of the plugin, or null of {@link Void}.
     * @param <R> Class of the registry.
     */
    public <R> R getPluginRegistry(Class<? extends IEEPlugin<R>> pluginClass) {
        return pluginLoader.getRegistry(pluginClass);
    }

    /**
     * Used to add warn messages for the current file.
     * @param msg Message to add
     */
    public void warn(String msg) {
        warn(msg, null, currentPath(), jsonFilePath());
    }

    /**
     * Used to add warn messages for the current file.
     * @param msg Message to add
     * @param additional Additional details to be printed after "message". This gets written directly into the file!
     */
    public void warn(String msg, String additional) {
        warn(msg, additional, currentPath(), jsonFilePath());
    }

    /**
     * Used to add warn messages for the current file.
     * @param msg Message to add
     * @param elementPath Path to the element in question.
     * @param jsonPath Path to the JSON file.
     */
    public void warn(String msg, String elementPath, String jsonPath) {
        warn(msg, null, elementPath, jsonPath);
    }

    /**
     * Used to add warn messages for the current file.
     * @param msg Message to add
     * @param additional Additional details to be printed after "message". This gets written directly into the file!
     * @param elementPath Path to the element in question.
     * @param jsonPath Path to the JSON file.
     */
    public void warn(String msg, String additional, String elementPath, String jsonPath) {
        logHandler.warn(msg, additional, elementPath, jsonPath);
    }

    /**
     * Used to add error messages for the current file.
     * @param msg Message to add.
     */
    public void error(String msg) {
        error(msg, null, currentPath(), jsonFilePath());
    }

    /**
     * Used to add error messages for the current file.
     * @param msg Message to add.
     * @param additional Additional details to be printed after "cause". This gets written directly into the file!
     */
    public void error(String msg, @Nullable String additional) {
        error(msg, additional, currentPath(), jsonFilePath());
    }

    /**
     * Used to add error messages for the current file.
     * @param msg Message to add.
     * @param elementPath Path to the element in question.
     * @param jsonPath Obfuscated path to the JSON file.
     */
    public void error(String msg, String elementPath, String jsonPath) {
        error(msg, null, elementPath, jsonPath);
    }

    /**
     * Used to add error messages for the current file.
     * @param msg Message to add.
     * @param additional Additional details to be printed after "cause". This gets written directly into the file!
     * @param elementPath Path to the element in question.
     * @param jsonPath Obfuscated path to the JSON file.
     */
    public void error(String msg, @Nullable String additional, String elementPath, String jsonPath) {
        logHandler.error(msg, additional, elementPath, jsonPath);
    }
}
