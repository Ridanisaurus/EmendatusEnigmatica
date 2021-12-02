package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.geode;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonDepositModelBase;

import java.util.List;

public class GeodeDepositConfigModel {
	public static final Codec<GeodeDepositConfigModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.list(CommonBlockDefinitionModel.CODEC).fieldOf("outerShellBlocks").forGetter(i -> i.outerShellBlocks),
			Codec.list(CommonBlockDefinitionModel.CODEC).fieldOf("innerShellBlocks").forGetter(i -> i.innerShellBlocks),
			Codec.list(CommonBlockDefinitionModel.CODEC).fieldOf("innerBlocks").forGetter(i -> i.innerBlocks),
			Codec.list(Codec.STRING).fieldOf("fillerTypes").forGetter(it -> it.fillerTypes),
			Codec.DOUBLE.fieldOf("chance").forGetter(it -> it.chance),
			Codec.INT.fieldOf("chanceChunkSkip").forGetter(it -> it.chanceChunkSkip),
			Codec.INT.fieldOf("radius").forGetter(it -> it.radius),
			Codec.INT.fieldOf("minYLevel").forGetter(it -> it.minYLevel),
			Codec.INT.fieldOf("maxYLevel").forGetter(it -> it.maxYLevel)
	).apply(x, GeodeDepositConfigModel::new));

	private final List<CommonBlockDefinitionModel> outerShellBlocks;
	private final List<CommonBlockDefinitionModel> innerShellBlocks;
	private final List<CommonBlockDefinitionModel> innerBlocks;
	private final List<String> fillerTypes;
	private final double chance;
	private final int chanceChunkSkip;
	private final int radius;
	private final int minYLevel;
	private final int maxYLevel;

	public GeodeDepositConfigModel(List<CommonBlockDefinitionModel> outerShellBlocks, List<CommonBlockDefinitionModel> innerShellBlocks, List<CommonBlockDefinitionModel> innerBlocks, List<String> fillerTypes, double chance, int chanceChunkSkip, int radius, int minYLevel, int maxYLevel) {
		this.outerShellBlocks = outerShellBlocks;
		this.innerShellBlocks = innerShellBlocks;
		this.innerBlocks = innerBlocks;
		this.fillerTypes = fillerTypes;
		this.chance = chance;
		this.chanceChunkSkip = chanceChunkSkip;
		this.radius = radius;
		this.minYLevel = minYLevel;
		this.maxYLevel = maxYLevel;
	}

	public int getRadius() {
		return radius;
	}

	public List<String> getFillerTypes() {
		return fillerTypes;
	}

	public int getMaxYLevel() {
		return maxYLevel;
	}

	public int getMinYLevel() {
		return minYLevel;
	}

	public double getChance() {
		return chance;
	}

	public int getChanceChunkSkip() {
		return chanceChunkSkip;
	}

	public List<CommonBlockDefinitionModel> getInnerBlocks() {
		return innerBlocks;
	}

	public List<CommonBlockDefinitionModel> getOuterShellBlocks() {
		return outerShellBlocks;
	}

	public List<CommonBlockDefinitionModel> getInnerShellBlocks() {
		return innerShellBlocks;
	}
}
