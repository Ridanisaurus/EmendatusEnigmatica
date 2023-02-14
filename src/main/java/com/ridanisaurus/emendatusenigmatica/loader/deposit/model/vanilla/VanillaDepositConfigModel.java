package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.vanilla;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sample.SampleBlockDefinitionModel;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class VanillaDepositConfigModel {
	public static final Codec<VanillaDepositConfigModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.optionalFieldOf("block").orElse(null).forGetter(it -> Optional.ofNullable(it.block)),
			Codec.STRING.optionalFieldOf("material").orElse(null).forGetter(it -> Optional.ofNullable(it.material)),
			Codec.list(Codec.STRING).fieldOf("fillerTypes").orElse(List.of()).forGetter(it -> it.fillerTypes),
			Codec.INT.fieldOf("chance").orElse(0).forGetter(it -> it.chance),
			Codec.INT.fieldOf("size").orElse(0).forGetter(it -> it.size),
			Codec.INT.fieldOf("minYLevel").orElse(0).forGetter(it -> it.minYLevel),
			Codec.INT.fieldOf("maxYLevel").orElse(0).forGetter(it -> it.maxYLevel),
			Codec.STRING.fieldOf("placement").orElse("uniform").forGetter(it -> it.placement),
			Codec.STRING.fieldOf("rarity").orElse("common").forGetter(it -> it.rarity)
	).apply(x, (s, s2, l, i, i2, i3, i4, s3, s4) -> new VanillaDepositConfigModel(s.orElse(null), s2.orElse(null), l, i, i2, i3, i4, s3, s4)));

	public final String block;
	public final String material;
	public final List<String> fillerTypes;
	public final int chance;
	public final int size;
	public final int minYLevel;
	public final int maxYLevel;
	public final String placement;
	public final String rarity;

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
}
