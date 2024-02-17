package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CommonBlockDefinitionModel {
	public static final Codec<CommonBlockDefinitionModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.optionalFieldOf("block").forGetter(it -> Optional.ofNullable(it.block)),
			Codec.STRING.optionalFieldOf("tag").forGetter(it -> Optional.ofNullable(it.tag)),
			Codec.STRING.optionalFieldOf("material").forGetter(it -> Optional.ofNullable(it.material)),
			Codec.INT.fieldOf("weight").orElse(100).forGetter(it -> it.weight),
			Codec.INT.fieldOf("min").orElse(-500).forGetter(it -> it.min),
			Codec.INT.fieldOf("max").orElse(500).forGetter(it -> it.max)
	).apply(x, (s, s2, s3, i, i2, i3) -> new CommonBlockDefinitionModel(s.orElse(null), s2.orElse(null), s3.orElse(null), i, i2, i3)));
	protected final String block;
	protected final String tag;
	private final String material;
	protected final int weight;
	protected final int min;
	protected final int max;

	public CommonBlockDefinitionModel(@Nullable String block, @Nullable String tag, @Nullable String material, int weight, int min, int max) {
		this.block = block;
		this.tag = tag;
		this.material = material;
		this.weight = weight;
		this.min = min;
		this.max = max;
	}

	public @Nullable String getBlock() {
		return block;
	}

	public @Nullable String getTag() {
		return tag;
	}

	public @Nullable String getMaterial() {
		return material;
	}

	public int getWeight() {
		return weight;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}
}