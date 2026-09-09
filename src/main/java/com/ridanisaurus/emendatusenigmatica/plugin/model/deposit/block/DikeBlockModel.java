/*
 * MIT License
 *
 * Copyright (c) 2024-2026. Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.NumberRangeValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.RequiredValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.TypeValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.MaxValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit.MaterialValidator;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DikeBlockModel extends BlockModel {
	public static final Codec<DikeBlockModel> CODEC = RecordCodecBuilder.create(x -> x.group(
		Codec.STRING.optionalFieldOf("block", null).forGetter(BlockModel::getBlock),
		Codec.STRING.optionalFieldOf("tag", null).forGetter(BlockModel::getTag),
		Codec.STRING.optionalFieldOf("material", null).forGetter(BlockModel::getMaterial),
			Codec.INT.optionalFieldOf("weight", 1).forGetter(it -> it.getWeight().asInt()),
			Codec.INT.optionalFieldOf("min", -500).forGetter(it -> it.min),
			Codec.INT.optionalFieldOf("max", 500).forGetter(it -> it.max)
	).apply(x, DikeBlockModel::new));

	public static final ValidationManager VALIDATION_MANAGER = ValidationManager.create()
		.addValidator("block",    new RequiredValidator(false))
		.addValidator("tag",      new RequiredValidator(false))
		.addValidator("material", new MaterialValidator())
		.addValidator("weight",   new TypeValidator(Types.INTEGER, false))
		.addValidator("min",      new NumberRangeValidator(Types.INTEGER, -64, 320, false))
		.addValidator("max",      new MaxValidator(Types.INTEGER, -64, 320, false));

	private final int min;
	private final int max;

	public DikeBlockModel(@Nullable String block, @Nullable String tag, @Nullable String material, int weight, int min, int max) {
        super(block, tag, material, weight);
		this.min = min;
		this.max = max;
	}

	public int getMinY() {
		return min;
	}

	public int getMaxY() {
		return max;
	}
}