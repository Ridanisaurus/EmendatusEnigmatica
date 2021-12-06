package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sphere;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonDepositModelBase;

import java.util.List;

public class SphereDepositModel extends CommonDepositModelBase {
	public static final Codec<SphereDepositModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.fieldOf("type").forGetter(it -> it.type),
			Codec.list(Codec.STRING).fieldOf("dimensions").forGetter(it -> it.dimensions),
			Codec.list(Codec.STRING).fieldOf("blacklistBiomes").forGetter(it -> it.blacklistBiomes),
			Codec.list(Codec.STRING).fieldOf("whitelistBiomes").forGetter(it -> it.whitelistBiomes),
			Codec.STRING.fieldOf("registryName").forGetter(it -> it.name),
			SphereDepositConfigModel.CODEC.fieldOf("config").forGetter(it -> it.config)
	).apply(x, SphereDepositModel::new));

	private final SphereDepositConfigModel config;

	public SphereDepositModel(String type, List<String> dimensions, List<String> blacklistBiomes, List<String> whitelistBiomes, String name, SphereDepositConfigModel config) {
		super(type, dimensions, blacklistBiomes, whitelistBiomes, name);
		this.config = config;
	}

	@Override
	public String getName() {
		return super.getName();
	}

	public SphereDepositConfigModel getConfig() {
		return config;
	}

	@Override
	public List<String> getDimensions() {
		return super.getDimensions();
	}

	@Override
	public List<String> getBlacklistBiomes() {
		return super.getBlacklistBiomes();
	}

	@Override
	public List<String> getWhitelistBiomes() {
		return super.getWhitelistBiomes();
	}
}