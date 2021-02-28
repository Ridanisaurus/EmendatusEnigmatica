package com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.custom.CustomDepositModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.vanilla.VanillaDepositModel;
import net.minecraft.block.BlockState;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.*;

import java.util.Optional;

public class VanillaDepositProcessor implements IDepositProcessor {

    private JsonObject object;
    private VanillaDepositModel model;

    public VanillaDepositProcessor(JsonObject object) {

        this.object = object;
    }

    @Override
    public void load() {
        Optional<Pair<VanillaDepositModel, JsonElement>> result = JsonOps.INSTANCE.withDecoder(VanillaDepositModel.CODEC).apply(object).result();
        if (!result.isPresent()) {
            return;
        }
        model = result.get().getFirst();
    }

    @Override
    public void setupOres(BiomeLoadingEvent event) {
    }

    private static ConfiguredFeature<?, ?> getOreFeature(int count, int size, int baseline, int spread, RuleTest filler, BlockState state) {
        Feature<OreFeatureConfig> oreFeature = Feature.ORE;
        return oreFeature.withConfiguration(new OreFeatureConfig(filler, state, size))
                .withPlacement(Placement.DEPTH_AVERAGE.configure(new DepthAverageConfig(baseline, spread)))
                .square() // square vein
                .func_242731_b(count) // max count per chunk
                ;
    }
}
