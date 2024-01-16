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
	public int getSize() {
		int size = model.getSize();
		if (size >= 1 && size < 17) {
			return 0; // Small
		} else if (size >= 17 && size < 33) {
			return 1; // Average
		} else if (size >= 33 && size < 49) {
			return 2; // Large
		} else {
			return -1;
		}
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

	@Override
	public String getPlacement() {
		return model.getPlacement();
	}

	@Override
	public String getRarity() {
		return model.getRarity();
	}
}
