package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.geode;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sample.SampleBlockDefinitionModel;

import java.util.List;

public class GeodeDepositConfigModel {
	public static final Codec<GeodeDepositConfigModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.list(CommonBlockDefinitionModel.CODEC).fieldOf("outerShellBlocks").forGetter(i -> i.outerShellBlocks),
			Codec.list(CommonBlockDefinitionModel.CODEC).fieldOf("innerShellBlocks").forGetter(i -> i.innerShellBlocks),
			Codec.list(CommonBlockDefinitionModel.CODEC).fieldOf("innerBlocks").forGetter(i -> i.innerBlocks),
			Codec.list(CommonBlockDefinitionModel.CODEC).fieldOf("fillBlocks").forGetter(i -> i.fillBlocks),
			Codec.list(Codec.STRING).fieldOf("fillerTypes").forGetter(it -> it.fillerTypes),
			Codec.list(Codec.STRING).fieldOf("clusters").orElse(List.of()).forGetter(i -> i.clusters),
			Codec.INT.fieldOf("chance").forGetter(it -> it.chance),
			Codec.DOUBLE.fieldOf("crackChance").forGetter(it -> it.crackChance),
			Codec.INT.fieldOf("minYLevel").forGetter(it -> it.minYLevel),
			Codec.INT.fieldOf("maxYLevel").forGetter(it -> it.maxYLevel),
			Codec.BOOL.fieldOf("generateSamples").orElse(false).forGetter(it -> it.generateSamples),
			Codec.list(SampleBlockDefinitionModel.CODEC).fieldOf("sampleBlocks").orElse(List.of()).forGetter(it -> it.sampleBlocks)
	).apply(x, GeodeDepositConfigModel::new));

	private final List<CommonBlockDefinitionModel> outerShellBlocks;
	private final List<CommonBlockDefinitionModel> innerShellBlocks;
	private final List<CommonBlockDefinitionModel> innerBlocks;
	private final List<CommonBlockDefinitionModel> fillBlocks;
	private final List<String> fillerTypes;
	private final List<String> clusters;
	private final int chance;
	private final double crackChance;
	private final int minYLevel;
	private final int maxYLevel;
	private final boolean generateSamples;
	private final List<SampleBlockDefinitionModel> sampleBlocks;

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

	public List<String> getFillerTypes() {
		return fillerTypes;
	}

		public List<String> getClusters() {
		return clusters;
	}

	public int getMaxYLevel() {
		return maxYLevel;
	}

	public int getMinYLevel() {
		return minYLevel;
	}

	public int getChance() {
		return chance;
	}

	public int getPlacementChance() {
		return (100 - chance) + 1;
	}

	public double getCrackChance() {
		return crackChance;
	}

	public List<CommonBlockDefinitionModel> getOuterShellBlocks() {
		return outerShellBlocks;
	}

	public List<CommonBlockDefinitionModel> getInnerShellBlocks() {
		return innerShellBlocks;
	}

	public List<CommonBlockDefinitionModel> getInnerBlocks() {
		return innerBlocks;
	}

	public List<CommonBlockDefinitionModel> getFillBlocks() {
		return fillBlocks;
	}

	public boolean getGenerateSamples() {
		return generateSamples;
	}

	public List<SampleBlockDefinitionModel> getSampleBlocks() {
		return sampleBlocks;
	}
}
