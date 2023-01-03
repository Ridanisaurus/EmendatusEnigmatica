package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.dike;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sample.SampleBlockDefinitionModel;

import java.util.List;

public class DikeDepositConfigModel {
	public static final Codec<DikeDepositConfigModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.list(CommonBlockDefinitionModel.CODEC).fieldOf("blocks").forGetter(it -> it.blocks),
			Codec.list(Codec.STRING).fieldOf("fillerTypes").forGetter(it -> it.fillerTypes),
			Codec.INT.fieldOf("chance").forGetter(it -> it.chance),
			Codec.INT.fieldOf("size").forGetter(it -> it.size),
			Codec.INT.fieldOf("minYLevel").forGetter(it -> it.minYLevel),
			Codec.INT.fieldOf("maxYLevel").forGetter(it -> it.maxYLevel),
			Codec.BOOL.fieldOf("generateSamples").orElse(false).forGetter(it -> it.generateSamples),
			Codec.list(SampleBlockDefinitionModel.CODEC).fieldOf("sampleBlocks").orElse(List.of()).forGetter(it -> it.sampleBlocks)
	).apply(x, DikeDepositConfigModel::new));

	private final List<CommonBlockDefinitionModel> blocks;
	private final List<String> fillerTypes;
	private final int chance;
	private final int size;
	private final int minYLevel;
	private final int maxYLevel;
	private final boolean generateSamples;
	private final List<SampleBlockDefinitionModel> sampleBlocks;

	public DikeDepositConfigModel(List<CommonBlockDefinitionModel> blocks, List<String> fillerTypes, int chance, int size, int minYLevel, int maxYLevel, boolean generateSamples, List<SampleBlockDefinitionModel> sampleBlocks) {

		this.blocks = blocks;
		this.chance = chance;
		this.size = size;
		this.minYLevel = minYLevel;
		this.maxYLevel = maxYLevel;
		this.fillerTypes = fillerTypes;
		this.generateSamples = generateSamples;
		this.sampleBlocks = sampleBlocks;
	}

	public int getChance() {
		return chance;
	}

	public int getPlacementChance() {
		return (100 - chance) + 1;
	}

	public int getMaxYLevel() {
		return maxYLevel;
	}

	public int getMinYLevel() {
		return minYLevel;
	}

	public List<CommonBlockDefinitionModel> getBlocks() {
		return blocks;
	}

	public List<String> getFillerTypes() {
		return fillerTypes;
	}

	public int getSize() {
		return size;
	}

	public boolean getGenerateSamples() {
		return generateSamples;
	}

	public List<SampleBlockDefinitionModel> getSampleBlocks() {
		return sampleBlocks;
	}
}
