/*
 *  MIT License
 *
 *  Copyright (c) 2020 Ridanisaurus
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package com.ridanisaurus.emendatusenigmatica.loader.parser.model;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.Validator;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

public class ToolModel {
	public static final Codec<ToolModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.FLOAT.optionalFieldOf("damage").forGetter(i -> Optional.of(i.damage)),
			Codec.FLOAT.optionalFieldOf("speed").forGetter(i -> Optional.of(i.speed)),
			Codec.INT.optionalFieldOf("durability").forGetter(i -> Optional.of(i.durability))
	).apply(x, (damage, speed, durability) -> new ToolModel(
			damage.orElse(0.0f),
			speed.orElse(0.0f),
			durability.orElse(1)
	)));

	private final float damage;
	private final float speed;
	private final int durability;

	/**
	 * Holds verifying functions for each field.
	 * Function returns true if verification was successful, false otherwise to stop registration of the json.
	 * Adding suffix _rg will request the original object instead of just the value of the field.
	 */
	public static Map<String, BiFunction<JsonElement, Path, Boolean>> validators = new LinkedHashMap<>();

	static {
		validators.put("damage", new Validator("damage").REQUIRES_FLOAT);
		validators.put("speed", new Validator("speed").REQUIRES_FLOAT);
		validators.put("durability", new Validator("durability").REQUIRES_INT);
	}

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