package com.ridanisaurus.emendatusenigmatica.api.config;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Used to register DefaultConfiguration objects from the EE Addons. Provides basic methods for compatibility checking.
 *
 * @implNote Take a note that with multiple addons creating configurations for the same material,
 * conflicts may arise. It is recommended that the addon provides a way to disable generation for each configuration registered.
 * @apiNote Before full 2.2 release - it might be wise to rework the registry to parse the details of each object,
 * for advanced filtering and compatibility checks. Currently, that is omitted for performance reasons.
 */
@ApiStatus.Experimental
public class DefaultConfigRegistry {
    private final List<DefaultConfigurationData> dataList = new ArrayList<>();

    /**
     * Registers new DefaultConfiguration object.
     * @param data Configuration to register.
     * @throws IllegalArgumentException when data for the same path is already registered.
     */
    public void register(@NotNull DefaultConfigurationData data) {
        if (isPathRegistered(Objects.requireNonNull(data).getPath()))
            throw new IllegalStateException("Data for the path \"%s\" is already registered!".formatted(data.getPath()));
        dataList.add(data);
    }

    /**
     * Utility method that before registering, checks if a path is already registered.
     * @param data Configuration to register.
     * @return False if there is a Path conflict - otherwise true.
     */
    public boolean tryRegister(@NotNull DefaultConfigurationData data) {
        if (isPathRegistered(Objects.requireNonNull(data).getPath())) return false;
        register(data);
        return true;
    }

    /**
     * @param path Path to check.
     * @return True if a provided path is already registered, false otherwise.
     */
    public boolean isPathRegistered(Path path) {
        return dataList.stream().anyMatch(it -> Objects.equals(it.getPath().toString(), path.toAbsolutePath().toString()));
    }

    /**
     * @return copy of the currently registered entries.
     * @apiNote Should not be used by addons, use provided helper methods instead.
     */
    @ApiStatus.Internal
    public List<DefaultConfigurationData> getEntries() {
        return List.copyOf(dataList);
    }
}
