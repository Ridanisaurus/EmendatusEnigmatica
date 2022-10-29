package com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.geode.GeodeDepositModel;
import com.ridanisaurus.emendatusenigmatica.util.WorldGenHelper;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.GeodeOreFeature;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.config.GeodeOreFeatureConfig;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.rule.MultiStrataRuleTest;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.Optional;

public class GeodeDepositProcessor implements IDepositProcessor {

	private final JsonObject object;
	private GeodeDepositModel model;

	public GeodeDepositProcessor(JsonObject object) {

		this.object = object;
	}

	@Override
	public void load() {
		Optional<Pair<GeodeDepositModel, JsonElement>> result = JsonOps.INSTANCE.withDecoder(GeodeDepositModel.CODEC).apply(object).result();
		if (!result.isPresent()) {
			return;
		}
		model = result.get().getFirst();
	}
	// TODO: [BUUZ] The whole ConfiguredFeature generation system seems to have changed, and I can't seem to figure out how to shift the below code to use the new Holder system
	@Override
	public void setupOres(BiomeLoadingEvent event) {
		if (WorldGenHelper.biomeCheck(event, model.getWhitelistBiomes(), model.getBlacklistBiomes())) {
			Holder<ConfiguredFeature<GeodeOreFeatureConfig, ?>> oreFeature = getOreFeature(new MultiStrataRuleTest(model.getConfig().getFillerTypes()));
			HeightRangePlacement placement = HeightRangePlacement.uniform(VerticalAnchor.absolute(model.getConfig().getMinYLevel()), VerticalAnchor.absolute(model.getConfig().getMaxYLevel()));
			var placed = PlacementUtils.register(model.getName(), oreFeature, placement);
			event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, placed);
		}
	}
	private Holder<ConfiguredFeature<GeodeOreFeatureConfig, ?>> getOreFeature(RuleTest filler) {
		return FeatureUtils.register(model.getName(), new GeodeOreFeature(GeodeOreFeatureConfig.CODEC, model), new GeodeOreFeatureConfig(filler));
	}
}
