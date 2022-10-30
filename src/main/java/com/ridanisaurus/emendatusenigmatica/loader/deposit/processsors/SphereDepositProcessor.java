package com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sphere.SphereDepositModel;
import com.ridanisaurus.emendatusenigmatica.util.WorldGenHelper;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.SphereOreFeature;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.config.SphereOreFeatureConfig;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.rule.MultiStrataRuleTest;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.Optional;

public class SphereDepositProcessor implements IDepositProcessor {

	private JsonObject object;
	private SphereDepositModel model;
	private Holder<ConfiguredFeature<SphereOreFeatureConfig, ?>> configured;
	private SphereOreFeature feature;

	public SphereDepositProcessor(JsonObject object) {
		this.object = object;
	}

	@Override
	public void load() {
		Optional<Pair<SphereDepositModel, JsonElement>> result = JsonOps.INSTANCE.withDecoder(SphereDepositModel.CODEC).apply(object).result();
		if (!result.isPresent()) {
			return;
		}
		model = result.get().getFirst();
		feature = new SphereOreFeature(SphereOreFeatureConfig.CODEC, model);
	}

	// TODO [TicTic] BiomeLoadingEvent is gone it seems
	@Override
	public void setupOres(BiomeLoadingEvent event) {
		if (WorldGenHelper.biomeCheck(event, model.getWhitelistBiomes(), model.getBlacklistBiomes())) {
			Holder<ConfiguredFeature<SphereOreFeatureConfig, ?>> oreFeature = getOreFeature();
			HeightRangePlacement placement = HeightRangePlacement.uniform(VerticalAnchor.absolute(model.getConfig().getMinYLevel()), VerticalAnchor.absolute(model.getConfig().getMaxYLevel()));
			var placed = PlacementUtils.register(model.getName(), oreFeature, placement);
			event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, placed);
		}
	}

	private Holder<ConfiguredFeature<SphereOreFeatureConfig, ?>> getOreFeature() {
		return configured;
	}

	// TODO [TicTic] Why is it a getting that is setting? Also, isn't the registry name already set during registration of the feature itself?
	@Override
	public Feature<?> getFeature() {
		return feature.setRegistryName(model.getName());
	}

	@Override
	public void setup() {
		configured = FeatureUtils.register(model.getName(), feature, new SphereOreFeatureConfig(new MultiStrataRuleTest(model.getConfig().getFillerTypes())));
	}
}
