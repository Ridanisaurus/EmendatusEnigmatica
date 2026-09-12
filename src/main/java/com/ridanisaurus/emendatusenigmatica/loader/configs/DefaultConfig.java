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

package com.ridanisaurus.emendatusenigmatica.loader.configs;

import com.google.gson.JsonObject;
import com.ridanisaurus.emendatusenigmatica.loader.EEModelExtension;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class DefaultConfig {
    private final String path;
    private final JsonObject config;
    private final @Nullable EEModelExtension<?, ?, ?, ?> extension;

    DefaultConfig(
        String path,
        JsonObject config,
        @Nullable EEModelExtension<?, ?, ?, ?> extension
    ) {
        this.config = Objects.requireNonNull(config, "Config object can't be null.");
        this.path = Objects.requireNonNull(path, "Path can't be null.");
        this.extension = extension;
    }

    DefaultConfig(String path, JsonObject config) {
        this(path, config, null);
    }

    @Contract(pure = true)
    public boolean isExtension() {
        return Objects.nonNull(extension);
    }

    public String getPath() {
        return path;
    }

    public JsonObject getConfig() {
        return config.deepCopy();
    }

    public @Nullable String getExtensionField() {
        if (extension != null) return extension.getExtensionOverrideField();
        return null;
    }

    JsonObject getRawConfig() {
        return config;
    }

    @Nullable EEModelExtension<?, ?, ?, ?> getExtension() {
        return extension;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (DefaultConfig) obj;
        return Objects.equals(this.path, that.path) &&
            Objects.equals(this.config, that.config) &&
            Objects.equals(this.extension, that.extension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, config, extension);
    }

    @Override
    public String toString() {
        return "DefaultConfig[" +
            "path=" + path + ", " +
            "config=" + config + ", " +
            "getExtension=" + extension + ']';
    }
}
