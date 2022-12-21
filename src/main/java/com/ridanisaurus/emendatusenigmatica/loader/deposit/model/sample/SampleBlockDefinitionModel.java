package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sample;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Optional;

public class SampleBlockDefinitionModel {
	public static final Codec<SampleBlockDefinitionModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.optionalFieldOf("block").forGetter(it -> Optional.ofNullable(it.block)),
			Codec.STRING.optionalFieldOf("tag").forGetter(it -> Optional.ofNullable(it.tag)),
			Codec.STRING.optionalFieldOf("material").forGetter(it -> Optional.ofNullable(it.material)),
			Codec.INT.fieldOf("weight").orElse(100).forGetter(it -> it.weight),
			Codec.STRING.optionalFieldOf("strata").forGetter(it -> Optional.ofNullable(it.strata))
	).apply(x, (s, s2, s3, i, s4) -> new SampleBlockDefinitionModel(s.orElse(null), s2.orElse(null), s3.orElse(null), i, s4.orElse(null))));
	protected final String block;
	protected final String tag;
	private final String material;
	protected final int weight;
	private final String strata;

	public SampleBlockDefinitionModel(String block, String tag, String material, int weight, String strata) {
		this.block = block;
		this.tag = tag;
		this.weight = weight;
		this.material = material;
		this.strata = strata;
	}

	public int getWeight() {
		return weight;
	}

	public String getBlock() {
		return block;
	}

	public String getTag() {
		return tag;
	}

	public String getMaterial() {
		return material;
	}

	public String getStrata() {
		return strata;
	}
}