/*
 * MIT License
 *
 * Copyright (c) 2020-2024. Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.test;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayPolicy;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.NumberRangeValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.common.DepositBlockModel;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.EERegistryValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.MaxValidator;

import java.util.List;

public class TestDepositConfigModel {
	public static final Codec<TestDepositConfigModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.list(DepositBlockModel.CODEC).fieldOf("blocks").forGetter(it -> it.blocks),
			Codec.list(Codec.STRING).fieldOf("fillerTypes").forGetter(it -> it.fillerTypes),
			Codec.INT.fieldOf("chance").forGetter(it -> it.chance),
			Codec.INT.fieldOf("size").forGetter(it -> it.size),
			Codec.INT.fieldOf("minYLevel").forGetter(it -> it.minYLevel),
			Codec.INT.fieldOf("maxYLevel").forGetter(it -> it.maxYLevel)
	).apply(x, TestDepositConfigModel::new));

	public static final ValidationManager VALIDATION_MANAGER = ValidationManager.create()
		.addValidator("blocks",          DepositBlockModel.VALIDATION_MANAGER.getAsValidator(true), ArrayPolicy.REQUIRES_ARRAY.getNonEmpty())
//		.addValidator("fillerTypes",     new EERegistryValidator(ModelLoader.STRATA_IDS, EERegistryValidator.REFERENCE, "Strata", true), ArrayPolicy.REQUIRES_ARRAY.getNonEmpty())
		.addValidator("chance",          new NumberRangeValidator(Types.INTEGER, 1, 100, true))
		.addValidator("size",            new NumberRangeValidator(Types.INTEGER, 1, 48, true))
		.addValidator("minYLevel",       new NumberRangeValidator(Types.INTEGER, -64, 320, true))
		.addValidator("maxYLevel",       new MaxValidator(Types.INTEGER, "minYLevel", -64, 320, true));

	private final List<DepositBlockModel> blocks;
	private final List<String> fillerTypes;
	private final int chance;
	private final int size;
	private final int minYLevel;
	private final int maxYLevel;

	public TestDepositConfigModel(List<DepositBlockModel> blocks, List<String> fillerTypes, int chance, int size, int minYLevel, int maxYLevel) {

		this.blocks = blocks;
		this.chance = chance;
		this.size = size;
		this.minYLevel = minYLevel;
		this.maxYLevel = maxYLevel;
		this.fillerTypes = fillerTypes;
	}

	public int getChance() {
		return (100 - chance) + 1;
	}

	public int getMaxYLevel() {
		return maxYLevel;
	}

	public int getMinYLevel() {
		return minYLevel;
	}

	public List<DepositBlockModel> getBlocks() {
		return blocks;
	}

	public List<String> getFillerTypes() {
		return fillerTypes;
	}

	public int getSize() {
		return size;
	}
}
