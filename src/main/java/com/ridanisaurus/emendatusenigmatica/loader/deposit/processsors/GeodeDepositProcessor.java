package com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonDepositModelBase;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.geode.GeodeDepositModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GeodeDepositProcessor implements IDepositProcessor {

	private final JsonObject object;
	private GeodeDepositModel model;

	public GeodeDepositProcessor(JsonObject object) {

		this.object = object;
	}

	@Override
	public void load() {
		Optional<Pair<GeodeDepositModel, JsonElement>> result = JsonOps.INSTANCE.withDecoder(GeodeDepositModel.CODEC).apply(object).result();
		if (result.isEmpty()) return;
		model = result.get().getFirst();
	}

	public GeodeDepositModel getGeodeModel() {
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
		List<CommonBlockDefinitionModel> innerBlocks = model.getInnerBlocks();
		List<CommonBlockDefinitionModel> fillBlocks = model.getFillBlocks();
		List<CommonBlockDefinitionModel> innerShellBlocks = model.getInnerShellBlocks();
		List<CommonBlockDefinitionModel> outerShellBlocks = model.getOuterShellBlocks();

		List<CommonBlockDefinitionModel> mergedList = new ArrayList<>();
		mergedList.addAll(innerBlocks);
		mergedList.addAll(fillBlocks);
		mergedList.addAll(innerShellBlocks);
		mergedList.addAll(outerShellBlocks);
		// TODO: If minecraft:air then skip
		return mergedList;
	}

	@Override
	public int getChance() {
		return model.getChance();
	}

	@Override
	public int getSize() {
		return 1;
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
