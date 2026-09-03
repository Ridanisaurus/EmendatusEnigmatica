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
