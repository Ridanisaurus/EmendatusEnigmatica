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
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.event.world.BiomeLoadingEvent;

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
	// TODO: [BUUZ] The whole ConfiguredFeature generation system seems to have changed, and I can't seem to figure out how to shift the below code to use the new Holder system
	@Override
	public void setupOres(BiomeLoadingEvent event) {
		if (WorldGenHelper.biomeCheck(event, model.getWhitelistBiomes(), model.getBlacklistBiomes())) {
			event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, getOreFeature(new MultiStrataRuleTest(model.getConfig().getFillerTypes())));
		}
	}

	private Holder<PlacedFeature> getOreFeature(RuleTest filler) {
		return WorldGenHelper.registerFeature(model.getName(), new SphereOreFeature(SphereOreFeatureConfig.CODEC, model) .configured(new SphereOreFeatureConfig(filler)));
	}
}
