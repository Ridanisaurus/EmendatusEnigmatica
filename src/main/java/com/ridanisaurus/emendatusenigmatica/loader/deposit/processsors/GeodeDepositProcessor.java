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
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.RegistryObject;

import java.util.Optional;

public class GeodeDepositProcessor implements IDepositProcessor {

	private final JsonObject object;
	private GeodeDepositModel model;
	public RegistryObject<PlacedFeature> orePlacedFeature;
//	public RegistryObject<Codec<OreBiomeModifier>> oreBiomeModifier;
//	private GeodeOreFeature feature;
//	private Holder<ConfiguredFeature<GeodeOreFeatureConfig, ?>> configured;

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
//		feature = new GeodeOreFeature(GeodeOreFeatureConfig.CODEC, model);
	}

	// TODO [TicTic] BiomeLoadingEvent is gone it seems
	@Override
	public void setup() {
		RegistryObject<ConfiguredFeature<?, ?>> oreFeature = WorldGenHelper.getOreFeature().register(
				model.getName(), () -> new ConfiguredFeature<>(new GeodeOreFeature(GeodeOreFeatureConfig.CODEC, model), new GeodeOreFeatureConfig(new MultiStrataRuleTest(model.getConfig().getFillerTypes())))
		);
		HeightRangePlacement placement = HeightRangePlacement.triangle(VerticalAnchor.absolute(model.getConfig().getMinYLevel()), VerticalAnchor.absolute(model.getConfig().getMaxYLevel()));
		orePlacedFeature = WorldGenHelper.getPlacedOreFeature().register(
				model.getName(), () -> new PlacedFeature(oreFeature.getHolder().get(), WorldGenHelper.commonOrePlacement((int) model.getConfig().getChance(), placement))
		);
//		oreBiomeModifier = WorldGenHelper.getBiomeSerializer().register("ore_biome_modifiers", () -> OreBiomeModifier.CODEC);
//		if (WorldGenHelper.biomeCheck(event, model.getWhitelistBiomes(), model.getBlacklistBiomes())) {
//			Holder<ConfiguredFeature<GeodeOreFeatureConfig, ?>> oreFeature = getOreFeature();
//			HeightRangePlacement placement = HeightRangePlacement.uniform(VerticalAnchor.absolute(model.getConfig().getMinYLevel()), VerticalAnchor.absolute(model.getConfig().getMaxYLevel()));
//			var placed = PlacementUtils.register(model.getName(), oreFeature, placement);
//			event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, placed);
//		}
	}

	@Override
	public RegistryObject<PlacedFeature> getPlacedFeature() {
		return orePlacedFeature;
	}

//	@Override
//	public RegistryObject<Codec<OreBiomeModifier>> getOreBiomeModifier() {
//		return oreBiomeModifier;
//	}

//	private Holder<ConfiguredFeature<GeodeOreFeatureConfig, ?>> getOreFeature() {
//		return configured;
//	}

	// TODO [TicTic] Why is it a getting that is setting? Also, isn't the registry name already set during registration of the feature itself?
//	@Override
//	public Feature<?> getFeature() {
//		return feature.setRegistryName(model.getName());
//	}

//	@Override
//	public void setup() {
////		configured = FeatureUtils.register(model.getName(), feature, new GeodeOreFeatureConfig(new MultiStrataRuleTest(model.getConfig().getFillerTypes())));
//	}
}
