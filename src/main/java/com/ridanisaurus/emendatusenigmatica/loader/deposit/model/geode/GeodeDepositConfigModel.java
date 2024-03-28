package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.geode;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.Validator;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sample.SampleBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.plugin.DefaultConfigPlugin;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class GeodeDepositConfigModel {
	public static final Codec<GeodeDepositConfigModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.list(CommonBlockDefinitionModel.CODEC).fieldOf("outerShellBlocks").orElse(List.of()).forGetter(i -> i.outerShellBlocks),
			Codec.list(CommonBlockDefinitionModel.CODEC).fieldOf("innerShellBlocks").orElse(List.of()).forGetter(i -> i.innerShellBlocks),
			Codec.list(CommonBlockDefinitionModel.CODEC).fieldOf("innerBlocks").orElse(List.of()).forGetter(i -> i.innerBlocks),
			Codec.list(CommonBlockDefinitionModel.CODEC).fieldOf("fillBlocks").orElse(List.of()).forGetter(i -> i.fillBlocks),
			Codec.list(Codec.STRING).fieldOf("fillerTypes").orElse(List.of()).forGetter(it -> it.fillerTypes),
			Codec.list(Codec.STRING).fieldOf("clusters").orElse(List.of()).forGetter(i -> i.clusters),
			Codec.INT.fieldOf("chance").orElse(0).forGetter(it -> it.chance),
			Codec.DOUBLE.fieldOf("crackChance").orElse(0D).forGetter(it -> it.crackChance),
			Codec.INT.fieldOf("minYLevel").orElse(0).forGetter(it -> it.minYLevel),
			Codec.INT.fieldOf("maxYLevel").orElse(0).forGetter(it -> it.maxYLevel),
			Codec.STRING.fieldOf("placement").orElse("uniform").forGetter(it -> it.placement),
			Codec.STRING.fieldOf("rarity").orElse("rare").forGetter(it -> it.rarity),
			Codec.BOOL.fieldOf("generateSamples").orElse(false).forGetter(it -> it.generateSamples),
			Codec.list(SampleBlockDefinitionModel.CODEC).fieldOf("sampleBlocks").orElse(List.of()).forGetter(it -> it.sampleBlocks)
	).apply(x, GeodeDepositConfigModel::new));

	public final List<CommonBlockDefinitionModel> outerShellBlocks;
	public final List<CommonBlockDefinitionModel> innerShellBlocks;
	public final List<CommonBlockDefinitionModel> innerBlocks;
	public final List<CommonBlockDefinitionModel> fillBlocks;
	public final List<String> fillerTypes;
	public final List<String> clusters;
	public final int chance;
	public final double crackChance;
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
		validators.put("outerShellBlocks", 	new Validator("outerShellBlocks").getRequiredObjectValidation(CommonBlockDefinitionModel.validators, true));
		validators.put("innerShellBlocks",	new Validator("innerShellBlocks").getRequiredObjectValidation(CommonBlockDefinitionModel.validators, true));
		validators.put("innerBlocks", 		new Validator("innerBlocks").getRequiredObjectValidation(CommonBlockDefinitionModel.validators, true));
		validators.put("fillBlocks", 		new Validator("fillBlocks").getRequiredObjectValidation(CommonBlockDefinitionModel.validators, true));
		validators.put("fillerTypes", 		new Validator("fillerTypes").getRequiredRegisteredIDValidation(DefaultConfigPlugin.STRATA_IDS, "Strata Registry", true));
		validators.put("clusters", 			new Validator("clusters").getResourceIDValidation(true));
		validators.put("chance", 			new Validator("chance").getRequiredIntRange(1, 100, false));
		validators.put("crackChance", 		new Validator("crackChance").getRequiredRange(0, 1, false));
		validators.put("minYLevel", 		new Validator("minYLevel").getRequiredIntRange(-64, 320, false));
		validators.put("maxYLevel_rg", 		new Validator("maxYLevel").getMaxYLevelValidation("minYLevel"));
		validators.put("placement", 		new Validator("placement").getAcceptsOnlyValidation(List.of("uniform", "triangle"), false));
		validators.put("rarity", 			new Validator("rarity").getAcceptsOnlyValidation(List.of("common", "rare"), false));
		validators.put("generateSamples",	new Validator("generateSamples").REQUIRES_BOOLEAN);
		Validator sampleValidator = new Validator("sampleBlocks");
		validators.put("sampleBlocks_rg", sampleValidator.getIfOtherFieldSet("generateSamples", sampleValidator.getRequiredObjectValidation(SampleBlockDefinitionModel.validators, true)));
	}

	public GeodeDepositConfigModel(List<CommonBlockDefinitionModel> outerShellBlocks, List<CommonBlockDefinitionModel> innerShellBlocks, List<CommonBlockDefinitionModel> innerBlocks, List<CommonBlockDefinitionModel> fillBlocks, List<String> fillerTypes, List<String> clusters, int chance, double crackChance, int minYLevel, int maxYLevel, String placement, String rarity, boolean generateSamples, List<SampleBlockDefinitionModel> sampleBlocks) {
		this.outerShellBlocks = outerShellBlocks;
		this.innerShellBlocks = innerShellBlocks;
		this.innerBlocks = innerBlocks;
		this.fillBlocks = fillBlocks;
		this.fillerTypes = fillerTypes;
		this.clusters = clusters;
		this.chance = chance;
		this.crackChance = crackChance;
		this.minYLevel = minYLevel;
		this.maxYLevel = maxYLevel;
		this.placement = placement;
		this.rarity = rarity;
		this.generateSamples = generateSamples;
		this.sampleBlocks = sampleBlocks;
	}
}
