package com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonDepositModelBase;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.test.TestDepositModel;

import java.util.List;
import java.util.Optional;

public class TestDepositProcessor implements IDepositProcessor {

	private final JsonObject object;
	private TestDepositModel model;

	public TestDepositProcessor(JsonObject object) {
		this.object = object;
	}

	@Override
	public void load() {
		Optional<Pair<TestDepositModel, JsonElement>> result = JsonOps.INSTANCE.withDecoder(TestDepositModel.CODEC).apply(object).result();
		if (result.isEmpty()) return;
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

	@Override
	public List<CommonBlockDefinitionModel> getBlocks() {
		return List.of();
	}

	@Override
	public int getChance() {
		return 0;
	}

	@Override
	public int getSize() {
		return 0;
	}

	@Override
	public int getMaxY() {
		return 0;
	}

	@Override
	public int getMinY() {
		return 0;
	}

	@Override
	public boolean hasSurfaceSample() {
		return false;
	}

	@Override
	public String getPlacement() {
		return "";
	}

	@Override
	public String getRarity() {
		return "";
	}
}
