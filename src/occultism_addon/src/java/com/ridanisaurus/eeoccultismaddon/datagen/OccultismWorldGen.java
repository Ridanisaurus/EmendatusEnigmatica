package com.ridanisaurus.eeoccultismaddon.datagen;

import com.klikli_dev.occultism.datagen.worldgen.PlacedFeatures;
import com.ridanisaurus.eeoccultismaddon.EEOccultismPlugin;
import com.ridanisaurus.emendatusenigmatica.datagen.IFinishedGenericJSON;
import com.ridanisaurus.emendatusenigmatica.datagen.builder.FeatureBuilder;
import com.ridanisaurus.emendatusenigmatica.datagen.provider.EENeoFeatureProvider;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class OccultismWorldGen extends EENeoFeatureProvider {
    public OccultismWorldGen(DataGenerator gen, CompletableFuture<HolderLookup.Provider> providers) {
        super(gen, providers);
    }

    @Override
    protected void buildFeatures(HolderLookup.Provider provider, Consumer<IFinishedGenericJSON> consumer) {
        if (EEOccultismPlugin.disableIesnium.get()) {
            //TODO: Make sure iesnium ore is worth removing.
            new FeatureBuilder("neoforge:remove_features", "underground_ores")
                .biome("#minecraft:is_nether")
                .feature(PlacedFeatures.ORE_IESNIUM.location().toString())
                .save(consumer, Reference.getPath("compat/occultism/disable_iesnium_ore"));
        }

        if (EEOccultismPlugin.disableSilver.get()) {
            new FeatureBuilder("neoforge:remove_features", "underground_ores")
                .biome("#minecraft:is_overworld")
                .feature(PlacedFeatures.ORE_SILVER.location().toString())
                .save(consumer, Reference.getPath("compat/occultism/disable_silver_ore"));

            new FeatureBuilder("neoforge:remove_features", "underground_ores")
                .biome("#minecraft:is_overworld")
                .feature(PlacedFeatures.ORE_SILVER_DEEPSLATE.location().toString())
                .save(consumer, Reference.getPath("compat/occultism/disable_deepslate_silver_ore"));
        }
    }

    @Override
    public @NotNull String getName() {
        return "EE Occultism: Neo Features";
    }
}
