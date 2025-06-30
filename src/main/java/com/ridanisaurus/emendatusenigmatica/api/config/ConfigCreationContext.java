package com.ridanisaurus.emendatusenigmatica.api.config;

import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.Objects;

public class ConfigCreationContext {
    private final ModConfigSpec.Builder builder;
    private final ModConfig.Type type;
    private String addonName = null;

    public ConfigCreationContext(ModConfigSpec.Builder builder, ModConfig.Type type) {
        this.builder = Objects.requireNonNull(builder);
        this.type = Objects.requireNonNull(type);
    }

    public ConfigCreationContext setAddon(String name) {
        if (Objects.nonNull(addonName)) builder.pop();
        addonName = Objects.requireNonNull(name, "Addon name can't be null!");
        builder.push(name);
        return this;
    }

    public boolean isStartup() {
        return type == ModConfig.Type.STARTUP;
    }

    public boolean isClient() {
        return type == ModConfig.Type.CLIENT;
    }

    public ModConfigSpec.Builder getBuilder() {
        if (Objects.isNull(addonName)) throw new IllegalStateException("Tried defining a new configuration entry for null addon!");
        return builder;
    }
}
