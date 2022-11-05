package com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonDepositModelBase;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.geode.GeodeDepositModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sphere.SphereDepositModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.vanilla.VanillaDepositModel;

import java.util.Optional;

public class SphereDepositProcessor implements IDepositProcessor {

	private JsonObject object;
	private SphereDepositModel model;

	public SphereDepositProcessor(JsonObject object) {
		this.object = object;
	}

	@Override
	public void load() {
		Optional<Pair<SphereDepositModel, JsonElement>> result = JsonOps.INSTANCE.withDecoder(SphereDepositModel.CODEC).apply(object).result();
		if (!result.isPresent()) {
			return;
		}
		model = result.get().getFirst();
	}

	@Override
	public VanillaDepositModel getVanillaModel() {
		return null;
	}

	@Override
	public GeodeDepositModel getGeodeModel() {
		return null;
	}

	@Override
	public SphereDepositModel getSphereModel() {
		return model;
	}

	@Override
	public CommonDepositModelBase getModel() {
		return model;
	}

}
