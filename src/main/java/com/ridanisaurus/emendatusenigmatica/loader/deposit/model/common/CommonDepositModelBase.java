package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common;

import java.util.List;

public class CommonDepositModelBase {
	protected String type;
	protected List<String> dimensions;
	protected List<String> blacklistBiomes;
	protected List<String> whitelistBiomes;
	protected String name;

	public CommonDepositModelBase(String type, List<String> dimensions, List<String> blacklistBiomes, List<String> whitelistBiomes, String name) {
		this.type = type;
		this.dimensions = dimensions;
		this.blacklistBiomes = blacklistBiomes;
		this.whitelistBiomes = whitelistBiomes;
		this.name = name;
	}

	// TODO: User getType instead of have 3 model methods in the IDepositProcessor
	public String getType() {
		return type;
	}

	public List<String> getDimensions() {
		return dimensions;
	}

	public List<String> getBlacklistBiomes() {
		return blacklistBiomes;
	}

	public List<String> getWhitelistBiomes() {
		return whitelistBiomes;
	}

	public String getName() {
		return name;
	}
}
