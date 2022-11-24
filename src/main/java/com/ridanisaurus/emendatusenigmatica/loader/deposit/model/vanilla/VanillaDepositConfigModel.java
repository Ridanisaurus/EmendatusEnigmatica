package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.vanilla;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class VanillaDepositConfigModel {
	public static final Codec<VanillaDepositConfigModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.optionalFieldOf("block").forGetter(it -> Optional.ofNullable(it.block)),
			Codec.STRING.optionalFieldOf("material").forGetter(it -> Optional.ofNullable(it.material)),
			Codec.list(Codec.STRING).fieldOf("fillerTypes").forGetter(it -> it.fillerTypes),
			Codec.INT.fieldOf("chance").forGetter(it -> it.chance),
			Codec.INT.fieldOf("size").forGetter(it -> it.size),
			Codec.INT.fieldOf("minYLevel").forGetter(it -> it.minYLevel),
			Codec.INT.fieldOf("maxYLevel").forGetter(it -> it.maxYLevel),
			Codec.STRING.optionalFieldOf("placement").forGetter(it -> Optional.ofNullable(it.placement)),
			Codec.STRING.optionalFieldOf("rarity").forGetter(it -> Optional.ofNullable(it.rarity))
	).apply(x, (s, s2, l, i, i2, i3, i4, s3, s4) -> new VanillaDepositConfigModel(s.orElse(null), s2.orElse(null), l, i, i2, i3, i4, s3.orElse("uniform"), s4.orElse("common"))));

	private final String block;
	private final String material;
	private final List<String> fillerTypes;
	private final int chance;
	private final int size;
	private final int minYLevel;
	private final int maxYLevel;
	private final String placement;
	private final String rarity;

	public VanillaDepositConfigModel(String block, String material, List<String> fillerTypes, int chance, int size, int minYLevel, int maxYLevel, String placement, String rarity) {

		this.block = block;
		this.material = material;
		this.fillerTypes = fillerTypes;
		this.chance = chance;
		this.size = size;
		this.minYLevel = minYLevel;
		this.maxYLevel = maxYLevel;
		this.placement = placement;
		this.rarity = rarity;
	}

	public int getMinYLevel() {
		return minYLevel;
	}

	public int getMaxYLevel() {
		return maxYLevel;
	}

	public String getPlacement() {
		return placement;
	}

	public String getRarity() {
		return rarity;
	}

	public int getChance() {
		return chance;
	}

	@Nullable
	public String getBlock() {
		return block;
	}

	public int getSize() {
		return size;
	}

	public String getMaterial() {
		return material;
	}

	public List<String> getFillerTypes() {
		return fillerTypes;
	}
}
