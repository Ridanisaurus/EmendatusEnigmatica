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

package com.ridanisaurus.emendatusenigmatica.plugin.model.material;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.NumberRangeValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.TypeValidator;

public class ArmorPieceModel {
	public static final Codec<ArmorPieceModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.INT.optionalFieldOf("protection", 0).forGetter(i -> i.protection),
			Codec.INT.optionalFieldOf("durability", 1).forGetter(i -> i.durability)
	).apply(x, ArmorPieceModel::new));

	public static final ValidationManager VALIDATION_MANAGER = ValidationManager.create()
		.addValidator("protection", new TypeValidator(Types.INTEGER, false))
		.addValidator("durability", new NumberRangeValidator(Types.INTEGER, 0, Integer.MAX_VALUE, false));

	private final int protection;
	private final int durability;

	public ArmorPieceModel(int protection, int durability) {
		this.protection = protection;
		this.durability = durability;
	}

	public ArmorPieceModel() {
		this.protection = 0;
		this.durability = 1;
	}

	public int getProtection() {
		return protection;
	}

	public int getDurability() {
		return durability;
	}
}