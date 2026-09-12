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

package com.ridanisaurus.emendatusenigmatica.api.config.mergers;

import com.google.gson.JsonObject;
import com.ridanisaurus.emendatusenigmatica.loader.configs.DefaultConfig;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public interface IConfigMerger {
    /**
     * Inspects both {@link DefaultConfig} objects for conflicts.
     * @param config Currently existing default configuration.
     * @param newConfig New default configuration.
     * @return <code>true</code> if conflict is found.
     */
    boolean checkForConflicts(@NotNull DefaultConfig config, @NotNull DefaultConfig newConfig);

    /**
     * Attempts to merge two {@link DefaultConfig} objects.
     * @param config Currently existing default configuration.
     * @param newConfig New default configuration.
     * @return {@link MergeResult} object.
     */
    MergeResult merge(@NotNull DefaultConfig config, @NotNull DefaultConfig newConfig);

    /**
     * Record used to store results of a default configuration merge.
     * @param result Main result of the merge. Required.
     * @param merged Determines if the result is merged, or split.
     * @param additional Additional result of the merge, required if merged is false.
     * @param path Path for the additional JsonObject, required if merged is false.
     */
    record MergeResult(JsonObject result, boolean merged, @Nullable JsonObject additional, @Nullable String path) {
        public MergeResult(JsonObject result) {
            this(Objects.requireNonNull(result), true, null, null);
        }

        public MergeResult(JsonObject result, @NotNull JsonObject additional, @NotNull String path) {
            this(result, false, Objects.requireNonNull(additional), Objects.requireNonNull(path));
        }

        public boolean validate(String originalPath) {
            return Objects.nonNull(result) && !result.isEmpty() && (merged || (Objects.nonNull(additional) && !additional.isEmpty() && Objects.nonNull(path) && path.endsWith(".json") && !originalPath.equals(path)));
        }

        @Contract(pure = true)
        public @NotNull String getMergeType() {
            if (merged) return "merging.";
            return "splitting files.";
        }
    };
}
