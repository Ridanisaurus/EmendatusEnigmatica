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
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayPolicy;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Used to hold all necessary information for the validator.
 * @param validationElement Element currently validated
 * @param rootObject Root Json Object (if other fields are necessary)
 * @param currentPath Current Path inside the JSON file.
 * @param jsonFilePath Path to the json file, obfuscated.
 * @implSpec Please do not store any reference to this object outside the validation method.
 */
public record ValidationData(JsonElement validationElement, JsonObject rootObject, String currentPath, String jsonFilePath, ArrayPolicy arrayPolicy) {
    /**
     * Utility method to get ValidationData from previous data, but with updated information for another field of the object.
     * @param field Field name to base the information update on.
     * @param arrayPolicy ArrayPolicy of that field.
     * @return ValidationData with validationElement and currentPath updated.
     * @throws IllegalArgumentException when validationElement of previous data is not a JsonObject!
     */
    @Contract("_, _ -> new")
    public @NotNull ValidationData getWithField(String field, ArrayPolicy arrayPolicy) {
        if (!this.validationElement.isJsonObject()) throw new IllegalArgumentException("ValidationElement is not a json object! Requested field: " + field + " | Old Data: " + this);
        return new ValidationData(
            this.validationElement.getAsJsonObject().get(field),
            this.rootObject,
            this.currentPath + "." + field,
            this.jsonFilePath,
            arrayPolicy
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
}
