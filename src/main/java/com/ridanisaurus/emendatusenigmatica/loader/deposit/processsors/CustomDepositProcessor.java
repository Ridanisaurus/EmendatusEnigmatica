package com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.custom.LargeDepositModel;
import com.ridanisaurus.emendatusenigmatica.util.WorldGenHelper;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.LargeOreFeature;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.config.LargeOreFeatureConfig;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.rule.MultiStrataRuleTest;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomDepositProcessor implements IDepositProcessor {

    private JsonObject object;
    private LargeDepositModel model;

    public CustomDepositProcessor(JsonObject object) {

        this.object = object;
    }

    @Override
    public void load() {
        Optional<Pair<LargeDepositModel, JsonElement>> result = JsonOps.INSTANCE.withDecoder(LargeDepositModel.CODEC).apply(object).result();
        if (!result.isPresent()) {
            return;
        }
        model = result.get().getFirst();
    }

    @Override
    public void setupOres(BiomeLoadingEvent event) {
        event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, getOreFeature(new MultiStrataRuleTest(model.getConfig().getFillerTypes())));
    }

    private ConfiguredFeature<?, ?> getOreFeature(RuleTest filler) {
        return WorldGenHelper.registerFeature(model.getName(), new LargeOreFeature(LargeOreFeatureConfig.CODEC, model).withConfiguration(new LargeOreFeatureConfig(filler)));
    }
}
