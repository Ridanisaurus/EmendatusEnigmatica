/*
 * MIT License
 *
 * Copyright (c) 2026. Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.loader.configs.mergers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.ridanisaurus.emendatusenigmatica.api.config.mergers.IConfigMerger;
import com.ridanisaurus.emendatusenigmatica.loader.configs.DefaultConfig;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class DefaultConfigurationMerger implements IConfigMerger {
    /**
     * Inspects both {@link DefaultConfig} objects for conflicts.
     *
     * @param config    Currently existing default configuration.
     * @param newConfig New default configuration.
     * @return <code>true</code> if conflict is found.
     */
    @Override
    public boolean checkForConflicts(@NotNull DefaultConfig config, @NotNull DefaultConfig newConfig) {
        return Objects.equals(config.getPath(), newConfig.getPath());
    }

    /**
     * Attempts to merge two {@link DefaultConfig} objects.
     *
     * @param config    Currently existing default configuration.
     * @param newConfig New default configuration.
     * @return {@link MergeResult} object.
     */
    @Override
    public MergeResult merge(@NotNull DefaultConfig config, @NotNull DefaultConfig newConfig) {
        //TODO: Finish.
        var mainObj = config.getConfig();
        var newObj = newConfig.getConfig();
        if (mainObj.isEmpty()) return new MergeResult(newObj);
        if (newObj.isEmpty()) return new MergeResult(mainObj);

        mergeJsonObjects(mainObj, newObj, newConfig.getExtensionField());
    }

    protected void mergeJsonObjects(JsonObject mainObj, JsonObject newObj, String extensionField) {
        newObj.asMap().forEach((key, value) -> {
            if (!mainObj.has(key)) {
                mainObj.add(key, value);
                return;
            }
            JsonElement field = mainObj.get(key);
            JsonElement newField = newObj.get(key);
            if (field.isJsonObject()) {
                if (newField.isJsonObject())
                    mergeJsonObjects(field.getAsJsonObject(), newField.getAsJsonObject(), extensionField);
                return;
            }

            if (field.isJsonArray()) {
                if (newField.isJsonArray())
                    mergeJsonArrays(field.getAsJsonArray(), newField.getAsJsonArray());
                return;
            }
        });
    }

    protected void mergeJsonArrays(JsonArray mainArray, JsonArray newArray) {
        for (JsonElement element : newArray) {
            if (mainArray.contains(element)) continue;
            mainArray.add(newArray);
        }
    }
}
