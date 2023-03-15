package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sphere;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sample.SampleBlockDefinitionModel;

import java.util.List;
import java.util.Optional;

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
