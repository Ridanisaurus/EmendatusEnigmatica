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

package com.ridanisaurus.emendatusenigmatica.plugin.model.depositnew;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.NumberRangeValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.RequiredValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.TypeValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.deposit.DepositType;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.FieldSetValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.MaxValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit.MaterialValidator;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DepositBlockModel {
	public static final Codec<DepositBlockModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.optionalFieldOf("block").forGetter(it -> Optional.ofNullable(it.block)),
			Codec.STRING.optionalFieldOf("tag").forGetter(it -> Optional.ofNullable(it.tag)),
			Codec.STRING.optionalFieldOf("material").forGetter(it -> Optional.ofNullable(it.material)),
			Codec.INT.fieldOf("weight").orElse(100).forGetter(it -> it.weight),
			Codec.INT.fieldOf("min").orElse(-500).forGetter(it -> it.min),
			Codec.INT.fieldOf("max").orElse(500).forGetter(it -> it.max)
	).apply(x, (s, s2, s3, i, i2, i3) -> new DepositBlockModel(s.orElse(null), s2.orElse(null), s3.orElse(null), i, i2, i3)));

	public static final ValidationManager VALIDATION_MANAGER = ValidationManager.create()
		.addValidator("block",    new RequiredValidator(false))
		.addValidator("tag",      new RequiredValidator(false))
		.addValidator("material", new MaterialValidator())
		.addValidator("weight",   new TypeValidator(Types.INTEGER, false))
		.addValidator("min",      new FieldSetValidator(
			"root.type",
			DepositType.DIKE.getType(),
			new NumberRangeValidator(Types.INTEGER, -64, 320, false), true)
		).addValidator("max",      new FieldSetValidator(
			"root.type",
			DepositType.DIKE.getType(),
			new MaxValidator(Types.INTEGER, -64, 320, false), true)
		);

	private final String material;
	protected final String block;
	protected final String tag;
	protected final int weight;
	protected final int min;
	protected final int max;

	public DepositBlockModel(@Nullable String block, @Nullable String tag, @Nullable String material, int weight, int min, int max) {
		this.block = block;
		this.tag = tag;
		this.material = material;
		this.weight = weight;
		this.min = min;
		this.max = max;
	}

	public @Nullable String getBlock() {
		return block;
	}

	public @Nullable String getTag() {
		return tag;
	}

	public @Nullable String getMaterial() {
		return material;
	}

	public int getWeight() {
		return weight;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}
}