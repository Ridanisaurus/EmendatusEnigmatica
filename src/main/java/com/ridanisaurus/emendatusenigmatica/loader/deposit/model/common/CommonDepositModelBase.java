package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common;

import java.util.List;

public class CommonDepositModelBase {
	protected String type;
	protected List<String> dimensions;
	protected List<String> biomes;
	protected String name;

	public CommonDepositModelBase(String type, List<String> dimensions, List<String> biomes, String name) {
		this.type = type;
		this.dimensions = dimensions;
		this.biomes = biomes;
		this.name = name;
	}

	// TODO: Use getType instead of have 3 model methods in the IDepositProcessor
	public String getType() {
		return type;
	}

	public List<String> getDimensions() {
		return dimensions;
	}

	public List<String> getBiomes() {
		return biomes;
	}

	public String getName() {
		return name;
	}
}
