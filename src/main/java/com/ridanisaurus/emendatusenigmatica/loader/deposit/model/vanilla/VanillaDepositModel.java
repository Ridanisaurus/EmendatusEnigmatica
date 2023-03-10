package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.vanilla;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonDepositModelBase;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sample.SampleBlockDefinitionModel;

import javax.annotation.Nullable;
import java.util.List;

public class VanillaDepositModel extends CommonDepositModelBase {
	public static final Codec<VanillaDepositModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.fieldOf("type").forGetter(it -> it.type),
			Codec.STRING.fieldOf("dimension").forGetter(it -> it.dimension),
			Codec.list(Codec.STRING).fieldOf("biomes").orElse(List.of()).forGetter(it -> it.biomes),
			Codec.STRING.fieldOf("registryName").forGetter(it -> it.name),
			VanillaDepositConfigModel.CODEC.fieldOf("config").forGetter(it -> it.config)
	).apply(x, VanillaDepositModel::new));

	private VanillaDepositConfigModel config;

	public VanillaDepositModel(String type, String dimension, List<String> biomes, String name, VanillaDepositConfigModel config) {
		super(type, dimension, biomes, name);
		this.config = config;
	}

	public String getType() {
		return super.getType();
	}

	public int getMinYLevel() {
		if (config.minYLevel < -64 || config.minYLevel > 320) throw new IllegalArgumentException("Min Y for " + name + " is out of Range [-64 - 320]");
		return config.minYLevel;
	}

	public int getMaxYLevel() {
		if (config.maxYLevel < -64 || config.maxYLevel > 320) throw new IllegalArgumentException("Max Y for " + name + " is out of Range [-64 - 320]");
		return config.maxYLevel;
	}

	public String getPlacement() {
		if (!config.placement.equals("uniform") && !config.placement.equals("triangle")) throw new IllegalArgumentException("Placement for " + name + " contains an invalid option.");
		return config.placement;
	}

	public String getRarity() {
		if (!config.rarity.equals("common") && !config.rarity.equals("rare")) throw new IllegalArgumentException("Rarity for " + name + " contains an invalid option.");
		return config.rarity;
	}

	public int getChance() {
		if (config.chance < 1 || config.chance > 100) throw new IllegalArgumentException("Chance for " + name + " is out of Range [1 - 100]");
		return config.chance;
	}

	public int getPlacementChance() {
		return config.rarity.equals("rare") ? (100 - getChance()) + 1 : getChance();
	}

	public int getSize() {
		if (config.size < 1 || config.size > 16) throw new IllegalArgumentException("Size for " + name + " is out of Range [1 - 16]");
		return config.size;
	}

	public List<String> getFillerTypes() {
		if (config.fillerTypes.isEmpty()) throw new IllegalArgumentException("Filler Types for " + name + " cannot be empty.");
		return config.fillerTypes;
	}

	@Nullable
	public String getBlock() {
		return config.block;
	}

	@Nullable
	public String getMaterial() {
		return config.material;
	}
}
