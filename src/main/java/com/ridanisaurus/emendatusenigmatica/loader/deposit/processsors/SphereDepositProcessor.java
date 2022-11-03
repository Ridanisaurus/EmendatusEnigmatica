package com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.geode.GeodeDepositModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sphere.SphereDepositModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.vanilla.VanillaDepositModel;
import com.ridanisaurus.emendatusenigmatica.util.WorldGenHelper;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.SphereOreFeature;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.config.SphereOreFeatureConfig;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.rule.MultiStrataRuleTest;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.RegistryObject;

import java.util.Optional;

public class SphereDepositProcessor implements IDepositProcessor {

	private JsonObject object;
	private SphereDepositModel model;

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
	}

	@Override
	public void setup() {
//		RegistryObject<ConfiguredFeature<?, ?>> oreFeature = WorldGenHelper.getOreFeature().register(model.getName(),
//				() -> new ConfiguredFeature<>(new SphereOreFeature(SphereOreFeatureConfig.CODEC, model), new SphereOreFeatureConfig(new MultiStrataRuleTest(model.getConfig().getFillerTypes())))
//		);
//		HeightRangePlacement placement = HeightRangePlacement.triangle(VerticalAnchor.absolute(model.getConfig().getMinYLevel()), VerticalAnchor.absolute(model.getConfig().getMaxYLevel()));
//		orePlacedFeature = WorldGenHelper.getPlacedOreFeature().register(model.getName(),
//				() -> new PlacedFeature(oreFeature.getHolder().get(), WorldGenHelper.commonOrePlacement((int) model.getConfig().getChance(), placement))
//		);
	}

	@Override
	public VanillaDepositModel getVanillaModel() {
		return null;
	}

	@Override
	public GeodeDepositModel getGeodeModel() {
		return null;
	}

	@Override
	public SphereDepositModel getSphereModel() {
		return model;
	}

}
