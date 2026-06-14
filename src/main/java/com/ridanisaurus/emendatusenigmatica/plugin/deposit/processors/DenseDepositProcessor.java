/*
 * MIT License
 *
 * Copyright (c) 2024. Ridanisaurus
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.ridanisaurus.emendatusenigmatica.plugin.deposit.processors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.plugin.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.common.DepositBlockModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.common.CommonDepositModelBase;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.dense.DenseDepositModel;

import java.util.List;
import java.util.Optional;

public class DenseDepositProcessor implements IDepositProcessor {

	private final JsonObject object;
	private DenseDepositModel model;

	public DenseDepositProcessor(JsonObject object) {
		this.object = object;
	}

	@Override
	public void load() {
		Optional<Pair<DenseDepositModel, JsonElement>> result = JsonOps.INSTANCE.withDecoder(DenseDepositModel.CODEC).apply(object).result();
		if (result.isEmpty()) return;
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
	public List<DepositBlockModel> getBlocks() {
		return model.getBlocks();
	}

	@Override
	public List<String> getFillerTypes() {
		return model.getFillerTypes();
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

	@Override
	public String getName() {
		return model.getName();
	}
}
