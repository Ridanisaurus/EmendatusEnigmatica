package com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonDepositModelBase;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.geode.GeodeDepositModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sphere.SphereDepositModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.vanilla.VanillaDepositModel;
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

	@Override
	public void setup() {
//		RegistryObject<ConfiguredFeature<?, ?>> oreFeature = WorldGenHelper.getOreFeature().register(
//				model.getName(), () -> new ConfiguredFeature<>(new GeodeOreFeature(GeodeOreFeatureConfig.CODEC, model), new GeodeOreFeatureConfig(new MultiStrataRuleTest(model.getConfig().getFillerTypes())))
//		);
//		HeightRangePlacement placement = HeightRangePlacement.triangle(VerticalAnchor.absolute(model.getConfig().getMinYLevel()), VerticalAnchor.absolute(model.getConfig().getMaxYLevel()));
//		orePlacedFeature = WorldGenHelper.getPlacedOreFeature().register(
//				model.getName(), () -> new PlacedFeature(oreFeature.getHolder().get(), WorldGenHelper.commonOrePlacement((int) model.getConfig().getChance(), placement))
//		);
	}

	@Override
	public VanillaDepositModel getVanillaModel() {
		return null;
	}

	@Override
	public GeodeDepositModel getGeodeModel() {
		return model;
	}

	@Override
	public SphereDepositModel getSphereModel() {
		return null;
	}
}
