package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.dike;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sample.SampleBlockDefinitionModel;

import java.util.List;

public class DikeDepositConfigModel {
	public static final Codec<DikeDepositConfigModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.list(CommonBlockDefinitionModel.CODEC).fieldOf("blocks").orElse(List.of()).forGetter(it -> it.blocks),
			Codec.list(Codec.STRING).fieldOf("fillerTypes").orElse(List.of()).forGetter(it -> it.fillerTypes),
			Codec.INT.fieldOf("chance").orElse(0).forGetter(it -> it.chance),
			Codec.INT.fieldOf("size").orElse(0).forGetter(it -> it.size),
			Codec.INT.fieldOf("minYLevel").orElse(0).forGetter(it -> it.minYLevel),
			Codec.INT.fieldOf("maxYLevel").orElse(0).forGetter(it -> it.maxYLevel),
			Codec.BOOL.fieldOf("generateSamples").orElse(false).forGetter(it -> it.generateSamples),
			Codec.list(SampleBlockDefinitionModel.CODEC).fieldOf("sampleBlocks").orElse(List.of()).forGetter(it -> it.sampleBlocks)
	).apply(x, DikeDepositConfigModel::new));

	public final List<CommonBlockDefinitionModel> blocks;
	public final List<String> fillerTypes;
	public final int chance;
	public final int size;
	public final int minYLevel;
	public final int maxYLevel;
	public final boolean generateSamples;
	public final List<SampleBlockDefinitionModel> sampleBlocks;

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
}
