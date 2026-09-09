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

package com.ridanisaurus.emendatusenigmatica.loader;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.Objects;

public final class PathHolder {
    private final String requestedPath;
    private Path realPath = null;

    @Contract(pure = true)
    public PathHolder(String requestedPath) {
        this.requestedPath = Objects.requireNonNull(requestedPath, "Folder name can't be null.");
    }

    public PathHolder(@NotNull Path requestedPath) {
        this(requestedPath.toAbsolutePath().toString());
    }

    @Contract(pure = true)
    public Path getPath() {
        return this.realPath;
    }

    @Contract(pure = true)
    String getRequestedPath() {
        return this.requestedPath;
    }

    Path updatePath(Path configs) {
        return updatePath(configs, null);
    }

    Path updatePath(Path configs, @Nullable String suffix) {
        return this.realPath = Objects.requireNonNull(configs, "Config path can't be null.").resolve(requestedPath + (Objects.isNull(suffix) ? "" : " " + suffix)).normalize();
    }
}
