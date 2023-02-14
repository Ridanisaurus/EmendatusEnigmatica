package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.geode;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sample.SampleBlockDefinitionModel;

import java.util.List;

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
	public final boolean generateSamples;
	public final List<SampleBlockDefinitionModel> sampleBlocks;

	public GeodeDepositConfigModel(List<CommonBlockDefinitionModel> outerShellBlocks, List<CommonBlockDefinitionModel> innerShellBlocks, List<CommonBlockDefinitionModel> innerBlocks, List<CommonBlockDefinitionModel> fillBlocks, List<String> fillerTypes, List<String> clusters, int chance, double crackChance, int minYLevel, int maxYLevel, boolean generateSamples, List<SampleBlockDefinitionModel> sampleBlocks) {
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
		this.generateSamples = generateSamples;
		this.sampleBlocks = sampleBlocks;
	}
}
