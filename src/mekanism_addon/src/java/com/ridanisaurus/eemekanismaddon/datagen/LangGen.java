package com.ridanisaurus.eemekanismaddon.datagen;

import com.ridanisaurus.eemekanismaddon.EEMekanismAddon;
import com.ridanisaurus.eemekanismaddon.registry.EEMekanismDataRegistry;
import com.ridanisaurus.eemekanismaddon.registry.EEMekanismRegistrar;
import com.ridanisaurus.emendatusenigmatica.plugin.DataRegistry;
import com.ridanisaurus.emendatusenigmatica.datagen.provider.EELangProvider;
import net.minecraft.data.DataGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LangGen extends EELangProvider {
    private final EEMekanismDataRegistry registry;
    private final DataRegistry vanillaRegistry;

    public LangGen(DataGenerator gen, EEMekanismDataRegistry registry, DataRegistry vanillaRegistry) {
        super(gen, EEMekanismAddon.MOD_ID, "en_us");
        this.vanillaRegistry = Objects.requireNonNull(vanillaRegistry);
        this.registry = Objects.requireNonNull(registry);
    }

    @Override
    protected void addTranslations() {
        // Configs
        add("emendatusenigmatica.configuration.mekanism-plugin", "Mekanism Plugin Options");
        add("emendatusenigmatica.configuration.mekanism-plugin.tooltip", "Options provided by EE Mekanism plugin.");
        add("emendatusenigmatica.configuration.mekanism-plugin.button", "Mekanism");
        add("ee_mekanism.config.disable_osmium_ore", "Disable Osmium Ore");

        registry.getExtensions().forEach((id, extension) -> {
            var material = vanillaRegistry.getMaterialModel(id);
            var types = material.getProcessedTypes();
            var name = material.getLocalizedName();
            if (types.contains("slurry")) {
                add(EEMekanismRegistrar.slurryMap.get(material.getId()).getCleanSlurry().get().getTranslationKey(), "Clean %s Slurry".formatted(name));
                add(EEMekanismRegistrar.slurryMap.get(material.getId()).get().getTranslationKey(), "Dirty %s Slurry".formatted(name));
            }

            if (types.contains("crystal"))
                add(EEMekanismRegistrar.crystalMap.getValue(material), "%s Crystal".formatted(name));

            if (types.contains("shard"))
                add(EEMekanismRegistrar.shardMap.getValue(material), "%s Shard".formatted(name));

            if (types.contains("clump"))
                add(EEMekanismRegistrar.clumpMap.getValue(material), "%s Clump".formatted(name));

            if (types.contains("dirty_dust"))
                add(EEMekanismRegistrar.dirtyDustMap.getValue(material), "Dirty %s Dust".formatted(name));

            if (types.contains("gas"))
                add(EEMekanismRegistrar.gasMap.get(material.getId()).getTranslationKey(), "Gaseous %s".formatted(name));

            if (types.contains("infuse_type"))
                add(EEMekanismRegistrar.infuseMap.get(material.getId()).getTranslationKey(), name);
        });
    }

    @Override
    public @NotNull String getName() {
        return "EE Mekanism Languages: en_us";
    }
}
