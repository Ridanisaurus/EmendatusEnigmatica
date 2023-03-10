package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.geode;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonDepositModelBase;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sample.SampleBlockDefinitionModel;

import java.util.List;

public class GeodeDepositModel extends CommonDepositModelBase {
	public static final Codec<GeodeDepositModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.fieldOf("type").forGetter(it -> it.type),
			Codec.STRING.fieldOf("dimension").forGetter(it -> it.dimension),
			Codec.list(Codec.STRING).fieldOf("biomes").orElse(List.of()).forGetter(it -> it.biomes),
			Codec.STRING.fieldOf("registryName").forGetter(it -> it.name),
			GeodeDepositConfigModel.CODEC.fieldOf("config").forGetter(it -> it.config)
	).apply(x, GeodeDepositModel::new));

	private final GeodeDepositConfigModel config;

	public GeodeDepositModel(String type, String dimension, List<String> biomes, String name, GeodeDepositConfigModel config) {
		super(type, dimension, biomes, name);
		this.config = config;
	}

	public GeodeDepositConfigModel getConfig() {
		return config;
	}

	public String getType() {
		return super.getType();
	}

	public List<String> getFillerTypes() {
		if (config.fillerTypes.isEmpty()) throw new IllegalArgumentException("Filler Types for " + name + " cannot be empty.");
		return config.fillerTypes;
	}

	public List<String> getClusters() {
		return config.clusters;
	}

	public int getMaxYLevel() {
		if (config.maxYLevel < -64 || config.maxYLevel > 320) throw new IllegalArgumentException("Max Y for " + name + " is out of Range [-64 - 320]");
		return config.maxYLevel;
	}

	public int getMinYLevel() {
		if (config.minYLevel < -64 || config.minYLevel > 320) throw new IllegalArgumentException("Min Y for " + name + " is out of Range [-64 - 320]");
		return config.minYLevel;
	}

	public int getChance() {
		if (config.chance < 1 || config.chance > 100) throw new IllegalArgumentException("Chance for " + name + " is out of Range [1 - 100]");
		return config.chance;
	}

	public int getPlacementChance() {
		return config.rarity.equals("rare") ? (100 - getChance()) + 1 : getChance();
	}

	public double getCrackChance() {
		if (config.crackChance < 0.0D || config.crackChance > 1.0D) throw new IllegalArgumentException("Crack Chance for " + name + " is out of Range [0.0 - 1.0]");
		return config.crackChance;
	}

	public List<CommonBlockDefinitionModel> getOuterShellBlocks() {
		if (config.outerShellBlocks.isEmpty()) throw new IllegalArgumentException("Outer Shell Blocks for " + name + " cannot be empty.");
		return config.outerShellBlocks;
	}

	public List<CommonBlockDefinitionModel> getInnerShellBlocks() {
		if (config.innerShellBlocks.isEmpty()) throw new IllegalArgumentException("Inner Shell Blocks for " + name + " cannot be empty.");
		return config.innerShellBlocks;
	}

	public List<CommonBlockDefinitionModel> getInnerBlocks() {
		if (config.innerBlocks.isEmpty()) throw new IllegalArgumentException("Inner Blocks for " + name + " cannot be empty.");
		return config.innerBlocks;
	}

	public List<CommonBlockDefinitionModel> getFillBlocks() {
		// TODO: Check to see if this can be empty
		if (config.fillBlocks.isEmpty()) throw new IllegalArgumentException("Fill Blocks for " + name + " cannot be empty.");
		return config.fillBlocks;
	}

	public String getPlacement() {
		if (!config.placement.equals("uniform") && !config.placement.equals("triangle")) throw new IllegalArgumentException("Placement for " + name + " is invalid.");
		return config.placement;
	}

	public String getRarity() {
		if (!config.rarity.equals("common") && !config.rarity.equals("rare")) throw new IllegalArgumentException("Rarity for " + name + " is invalid.");
		return config.rarity;
	}

	public boolean getGenerateSamples() {
		return config.generateSamples;
	}

	public List<SampleBlockDefinitionModel> getSampleBlocks() {
		if (getGenerateSamples() && config.sampleBlocks.isEmpty()) throw new IllegalArgumentException("Sample Blocks for " + name + " cannot be empty if generateSamples is set to true.");
		return config.sampleBlocks;
	}
}
