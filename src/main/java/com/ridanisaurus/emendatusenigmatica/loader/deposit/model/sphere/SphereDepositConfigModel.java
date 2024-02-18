package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sphere;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.Validator;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.DepositValidators;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sample.SampleBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.plugin.DefaultConfigPlugin;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class SphereDepositConfigModel {
	public static final Codec<SphereDepositConfigModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.list(CommonBlockDefinitionModel.CODEC).fieldOf("blocks").orElse(List.of()).forGetter(it -> it.blocks),
			Codec.list(Codec.STRING).fieldOf("fillerTypes").orElse(List.of()).forGetter(it -> it.fillerTypes),
			Codec.INT.fieldOf("chance").orElse(0).forGetter(it -> it.chance),
			Codec.INT.fieldOf("radius").orElse(0).forGetter(it -> it.radius),
			Codec.INT.fieldOf("minYLevel").orElse(0).forGetter(it -> it.minYLevel),
			Codec.INT.fieldOf("maxYLevel").orElse(0).forGetter(it -> it.maxYLevel),
			Codec.STRING.fieldOf("placement").orElse("uniform").forGetter(it -> it.placement),
			Codec.STRING.fieldOf("rarity").orElse("rare").forGetter(it -> it.rarity),
			Codec.BOOL.fieldOf("generateSamples").orElse(false).forGetter(it -> it.generateSamples),
			Codec.list(SampleBlockDefinitionModel.CODEC).fieldOf("sampleBlocks").orElse(List.of()).forGetter(it -> it.sampleBlocks)
	).apply(x, SphereDepositConfigModel::new));

	public final List<CommonBlockDefinitionModel> blocks;
	public final List<String> fillerTypes;
	public final int chance;
	public final int radius;
	public final int minYLevel;
	public final int maxYLevel;
	public final String placement;
	public final String rarity;
	public final boolean generateSamples;
	public final List<SampleBlockDefinitionModel> sampleBlocks;

	/**
	 * Holds verifying functions for each field.
	 * Function returns true if verification was successful, false otherwise to stop registration of the json.
	 * Adding suffix _rg will request the original object instead of just the value of the field.
	 */
	public static Map<String, BiFunction<JsonElement, Path, Boolean>> validators = new LinkedHashMap<>();

	static {
		validators.put("blocks", 		new Validator("blocks").getRequiredObjectValidation(CommonBlockDefinitionModel.validators, true));
		validators.put("fillerTypes", 	new Validator("fillerTypes").getRequiredRegisteredIDValidation(DefaultConfigPlugin.STRATA_IDS, "Strata Registry", true));
		validators.put("chance", 		new Validator("chance").getRequiredIntRange(1, 100, false));
		validators.put("radius", 		new Validator("radius").getRequiredIntRange(1, 16, false));
		validators.put("minYLevel", 	new Validator("minYLevel").getRequiredIntRange(-64, 320, false));
		validators.put("maxYLevel_rg", 	DepositValidators.getMaxYLevelValidation(new Validator("maxYLevel"), "minYLevel"));
		validators.put("placement", 	new Validator("placement").getAcceptsOnlyValidation(List.of("uniform", "triangle"), false));
		validators.put("rarity", 		new Validator("rarity").getAcceptsOnlyValidation(List.of("common", "rare"), false));
		validators.put("generateSamples",new Validator("generateSamples").REQUIRES_BOOLEAN);
		validators.put("sampleBlocks", 	new Validator("sampleBlocks").getObjectValidation(SampleBlockDefinitionModel.validators, true));
	}

	public SphereDepositConfigModel(List<CommonBlockDefinitionModel> blocks, List<String> fillerTypes, int chance, int radius, int minYLevel, int maxYLevel, String placement, String rarity, boolean generateSamples, List<SampleBlockDefinitionModel> sampleBlocks) {
		this.blocks = blocks;
		this.chance = chance;
		this.radius = radius;
		this.minYLevel = minYLevel;
		this.maxYLevel = maxYLevel;
		this.fillerTypes = fillerTypes;
		this.placement = placement;
		this.rarity = rarity;
		this.generateSamples = generateSamples;
		this.sampleBlocks = sampleBlocks;
	}
}
