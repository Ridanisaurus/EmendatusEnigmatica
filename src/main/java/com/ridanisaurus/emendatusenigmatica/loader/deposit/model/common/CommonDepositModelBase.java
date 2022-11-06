package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common;

import java.util.List;

public class CommonDepositModelBase {
	protected String type;
	protected String dimension;
	protected List<String> biomes;
	protected String name;

	public CommonDepositModelBase(String type, String dimension, List<String> biomes, String name) {
		this.type = type;
		this.dimension = dimension;
		this.biomes = biomes;
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public String getDimension() {
		return dimension;
	}

	public List<String> getBiomes() {
		return biomes;
	}

	public String getName() {
		return name;
	}
}
