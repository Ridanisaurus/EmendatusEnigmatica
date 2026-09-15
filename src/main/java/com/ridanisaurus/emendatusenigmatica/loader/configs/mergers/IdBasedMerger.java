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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ridanisaurus.emendatusenigmatica.api.config.mergers.IConfigMerger;
import com.ridanisaurus.emendatusenigmatica.loader.configs.DefaultConfig;
import org.apache.commons.io.FileSystem;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;

public class IdBasedMerger implements IConfigMerger {
    private final String field;

    public IdBasedMerger() {
        this("id");
    }

    public IdBasedMerger(String field) {
        this.field = Objects.requireNonNull(field, "Field can't be null!");
        if (this.field.isBlank()) throw new IllegalArgumentException("Field can't be blank!");
    }

    /**
     * Inspects both {@link DefaultConfig} objects for conflicts.
     *
     * @param config    Currently existing default configuration.
     * @param newConfig New default configuration.
     * @return <code>true</code> if conflict is found.
     */
    @Override
    public boolean checkForConflicts(@NotNull DefaultConfig config, @NotNull DefaultConfig newConfig) {
        var cId = config.getConfig().get(field);
        var nId = newConfig.getConfig().get(field);
        return Objects.equals(config.getPath(), newConfig.getPath()) || (Objects.nonNull(cId) && Objects.nonNull(nId) && Objects.equals(cId, nId));
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
        //TODO: Currently if split causes infinite loop!
        if (Objects.equals(config, newConfig)) return new MergeResult(config.getConfig());
        var mainObj = config.getConfig();
        var newObj = newConfig.getConfig();
        if (mainObj.isEmpty()) return new MergeResult(newObj);
        if (newObj.isEmpty()) return new MergeResult(mainObj);

        var cId = config.getConfig().get(field);
        var nId = newConfig.getConfig().get(field);
        if (Objects.nonNull(cId) && Objects.nonNull(nId) && !Objects.equals(cId, nId)) {
            String newPath = StringUtils.removeEnd(newConfig.getPath(), ".json") + newObj.get(field).getAsString() + ".json";
            return new MergeResult(mainObj, newObj, StringUtils.substringBeforeLast(newPath, "/") + "/" + FileSystem.getCurrent().toLegalFileName(StringUtils.substringAfterLast(newPath, "/"), '_'));
        }

        if (mergeJsonObjects(mainObj, newObj)) return new MergeResult(mainObj);

        String path = StringUtils.removeEnd(config.getPath(), ".json");
        if (Objects.equals(config.getOwner(), newConfig.getOwner())) {
            path +=  "_duplicate.json";
        } else {
            path += " (%s).json".formatted(config.getOwner().modId());
        }

        // Fallback if somehow ends up with the same name.
        if (Objects.equals(newConfig.getPath(), path))
            path = StringUtils.removeEnd(path, ".json") + System.currentTimeMillis() + ".json";

        return new MergeResult(config.getConfig(), newObj, path);
    }

    protected boolean mergeJsonObjects(JsonObject mainObj, @NotNull JsonObject newObj) {
        for (Map.Entry<String, JsonElement> pair : newObj.asMap().entrySet()) {
            var key = pair.getKey();
            var val = pair.getValue();
            // * <=> * (Different Type) -> Skip. Incompatible types.
            // We don't merge primitives if they already exist.
            if (mainObj.has(key)) {
                var ogVal = mainObj.get(key);

                // JsonObject <=> JsonObject -> Merge
                if (ogVal.isJsonObject() && val.isJsonObject()) {
                    if (!mergeJsonObjects(ogVal.getAsJsonObject(), val.getAsJsonObject())) return false;
                    continue;
                }

                // Array <=> Array -> Merge (Deduplicate)
                if (ogVal.isJsonArray() && val.isJsonArray()) {
                    var arr = mainObj.getAsJsonArray(key);
                    for (JsonElement element : val.getAsJsonArray()) {
                        if (arr.contains(element)) continue;
                        arr.add(element);
                    }
                    continue;
                }

                // Same values -> Skip
                if (Objects.equals(ogVal, val)) continue;
                // Impossible to merge -> Split the file.
                return false;
            }

            // Null <=> * -> Add to main
            mainObj.add(key, val);
        }

        return true;
    }
}
