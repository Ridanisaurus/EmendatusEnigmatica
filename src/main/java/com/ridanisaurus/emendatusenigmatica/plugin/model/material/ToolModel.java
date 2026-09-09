/*
 * MIT License
 *
 * Copyright (c) 2020-2026. Ridanisaurus
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

public class ToolModel {
	public static final Codec<ToolModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.FLOAT.optionalFieldOf("damage", 0F).forGetter(i -> i.damage),
			Codec.FLOAT.optionalFieldOf("speed", 0F).forGetter(i -> i.speed),
			Codec.INT.optionalFieldOf("durability", 1).forGetter(i -> i.durability)
	).apply(x, ToolModel::new));

	public static final ValidationManager VALIDATION_MANAGER = ValidationManager.create()
		.addValidator("damage", 		new NumberRangeValidator(Types.FLOAT, 0, Float.MAX_VALUE, false))
		.addValidator("speed", 		new NumberRangeValidator(Types.FLOAT, 0, Float.MAX_VALUE, false))
		.addValidator("durability", 	new NumberRangeValidator(Types.INTEGER, 1, Integer.MAX_VALUE, false));

	private final float damage;
	private final float speed;
	private final int durability;

	public ToolModel(float damage, float speed, int durability) {
		this.damage = damage;
		this.speed = speed;
		this.durability = durability;
	}

	public ToolModel() {
		this.damage = 0.0f;
		this.speed = 0.0f;
		this.durability = 1;
	}

	public float getDamage() {
		return damage;
	}

	public float getSpeed() {
		return speed;
	}

	public int getDurability() {
		return durability;
	}
}