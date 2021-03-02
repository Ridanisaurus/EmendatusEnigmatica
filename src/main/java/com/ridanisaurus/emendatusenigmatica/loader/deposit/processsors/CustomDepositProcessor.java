package com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.custom.CustomDepositModel;
import com.ridanisaurus.emendatusenigmatica.util.WorldGenHelper;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.CustomOreFeature;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.config.CustomOreFeatureConfig;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.rule.MultiStrataRuleTest;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.Optional;

public class CustomDepositProcessor implements IDepositProcessor {

    private JsonObject object;
    private CustomDepositModel model;

    public CustomDepositProcessor(JsonObject object) {

        this.object = object;
    }

    @Override
    public void load() {
        Optional<Pair<CustomDepositModel, JsonElement>> result = JsonOps.INSTANCE.withDecoder(CustomDepositModel.CODEC).apply(object).result();
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
        return WorldGenHelper.registerFeature(model.getName(), new CustomOreFeature(CustomOreFeatureConfig.CODEC, model).withConfiguration(new CustomOreFeatureConfig(filler)));
    }
}
