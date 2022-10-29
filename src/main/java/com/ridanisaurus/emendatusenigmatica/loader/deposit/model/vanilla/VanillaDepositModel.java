package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.vanilla;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonDepositModelBase;

import java.util.List;

public class VanillaDepositModel extends CommonDepositModelBase {
	public static final Codec<VanillaDepositModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.fieldOf("type").forGetter(it -> it.type),
			Codec.list(Codec.STRING).fieldOf("dimensions").forGetter(it -> it.dimensions),
			Codec.list(Codec.STRING).fieldOf("blacklistBiomes").forGetter(it -> it.blacklistBiomes),
			Codec.list(Codec.STRING).fieldOf("whitelistBiomes").forGetter(it -> it.whitelistBiomes),
			Codec.STRING.fieldOf("registryName").forGetter(it -> it.name),
			VanillaDepositConfigModel.CODEC.fieldOf("config").forGetter(it -> it.config)
	).apply(x, VanillaDepositModel::new));

	private VanillaDepositConfigModel config;

	public VanillaDepositModel(String type, List<String> dimensions,List<String> blacklistBiomes, List<String> whitelistBiomes, String name, VanillaDepositConfigModel config) {
		super(type, dimensions, blacklistBiomes, whitelistBiomes, name);
		this.config = config;
	}

	public VanillaDepositConfigModel getConfig() {
		return config;
	}
}