package com.ridanisaurus.emendatusenigmatica.api.config;

import com.mojang.datafixers.util.Pair;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.*;
import java.util.function.Predicate;

/**
 * Used to register {@link DCData DefaultConfiguration} objects from the EE Addons.
 * Provides methods for compatibility checking and modification of different configs.
 *
 * @implNote Take a note that with multiple addons creating configurations for the same material,
 * conflicts may arise. It is recommended that the addon provides a way to disable generation for each configuration registered.
 */
@ApiStatus.Experimental
public class DCCreationContext {
    private final Map<AbstractDCType, List<DCData>> dataByType = new HashMap<>();
    private String currentAddon = null;

    @ApiStatus.Internal
    public DCCreationContext setCurrentAddon(String name) {
        this.currentAddon = name;
        return this;
    }

    /**
     * Registers new DefaultConfiguration object.
     * @param data Configuration to register.
     * @throws IllegalArgumentException when data for the same path is already registered or validation fails.
     */
    public void register(@NotNull DCData data) {
        if (Objects.isNull(currentAddon)) throw new IllegalStateException("DCCreationContext was not set up correctly! Can't register new configurations, when currentAddon is null.");
        var res = isPathRegistered(Objects.requireNonNull(data).getPath());
        if (res.getFirst())
            throw new IllegalStateException(
                "Data for the path \"%s\" requested by \"%s\" is already registered by \"%s\"!".formatted(data.getPath(), currentAddon, res.getSecond().getOrigin())
            );

        var typeList = dataByType.computeIfAbsent(data.getType(), it -> new ArrayList<>());
        if (!data.isUnique(typeList)) throw new IllegalStateException("Default Configuration at \"%s\" from \"%s\" is not unique!".formatted(data.getPath(), currentAddon));
        typeList.add(data);
        data.setOrigin(currentAddon);
    }

    public String getOrigin() {
        return this.currentAddon;
    }

    /**
     * @param path Path to check.
     * @return True if a provided path is already registered, false otherwise.
     */
    public Pair<Boolean, DCData> isPathRegistered(@NotNull Path path) {
        var s = path.toAbsolutePath().toString();
        var list = getEntries().stream().filter(data -> data.getPath().toString().equals(s)).toList();
        return new Pair<>(!list.isEmpty(), list.isEmpty()? null: list.getFirst());
    }

    /**
     * Runs the provided predicate against all entries registered under provided type.
     * @param type Type of the queried configs.
     * @param predicate Predicate to execute.
     * @return True if any of the registered configs match, false otherwise.
     */
    public boolean anyMatch(AbstractDCType type, Predicate<DCData> predicate) {
        return dataByType.get(type).stream().anyMatch(predicate);
    }

    /**
     * Used to get DCData based on the provided predicate.
     * @param type Type of the configuration to get.
     * @param predicate Predicate used to filter the configs.
     * @return List of found configs.
     */
    public List<DCData> getData(AbstractDCType type, Predicate<DCData> predicate) {
        return dataByType.get(type).stream().filter(predicate).toList();
    }

    /**
     * @return copy of the currently registered entries.
     * @apiNote Should not be used by addons, use provided helper methods instead.
     */
    @ApiStatus.Internal
    public List<DCData> getEntries() {
        List<DCData> ar = new ArrayList<>();
        dataByType.values().forEach(ar::addAll);
        return ar;
    }
}
