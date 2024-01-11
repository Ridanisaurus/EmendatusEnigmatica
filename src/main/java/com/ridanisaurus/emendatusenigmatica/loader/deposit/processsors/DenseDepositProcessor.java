package com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonDepositModelBase;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.dense.DenseDepositModel;

import java.util.List;
import java.util.Optional;

public class DenseDepositProcessor implements IDepositProcessor {

	private JsonObject object;
	private DenseDepositModel model;

	public DenseDepositProcessor(JsonObject object) {
		this.object = object;
	}

	@Override
	public void load() {
		Optional<Pair<DenseDepositModel, JsonElement>> result = JsonOps.INSTANCE.withDecoder(DenseDepositModel.CODEC).apply(object).result();
		if (!result.isPresent()) {
			return;
		}
		model = result.get().getFirst();
	}

	public DenseDepositModel getDenseModel() {
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
		return model.getBlocks();
	}

	@Override
	public int getChance() {
		return model.getChance();
	}

	@Override
	public int getMaxY() {
		return model.getMaxYLevel();
	}

	@Override
	public int getMinY() {
		return model.getMinYLevel();
	}

	@Override
	public boolean hasSurfaceSample() {
		return model.hasSamples();
	}
}
