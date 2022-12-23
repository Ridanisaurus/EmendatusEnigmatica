package com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonDepositModelBase;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.test.TestDepositModel;

import java.util.Optional;

public class TestDepositProcessor implements IDepositProcessor {

	private JsonObject object;
	private TestDepositModel model;

	public TestDepositProcessor(JsonObject object) {
		this.object = object;
	}

	@Override
	public void load() {
		Optional<Pair<TestDepositModel, JsonElement>> result = JsonOps.INSTANCE.withDecoder(TestDepositModel.CODEC).apply(object).result();
		if (!result.isPresent()) {
			return;
		}
		model = result.get().getFirst();
	}

	public TestDepositModel getTestModel() {
		return model;
	}

	@Override
	public String getType() {
		return model.getType();
	}

	@Override
	public CommonDepositModelBase getCommonModel() {
		return model;
	}
}
