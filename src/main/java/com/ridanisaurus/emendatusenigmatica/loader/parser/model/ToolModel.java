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

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Optional;

public class ToolModel {
	public static final Codec<ToolModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.FLOAT.optionalFieldOf("damage").forGetter(i -> Optional.of(i.damage)),
			Codec.FLOAT.optionalFieldOf("speed").forGetter(i -> Optional.of(i.speed)),
			Codec.FLOAT.optionalFieldOf("modifier").forGetter(i -> Optional.of(i.modifier))
	).apply(x, (damage, speed, modifier) -> new ToolModel(
			damage.orElse(1.0f),
			speed.orElse(1.0f),
			modifier.orElse(1.0f)
	)));

	private final float damage;
	private final float speed;
	private final float modifier;

	public ToolModel(float damage, float speed, float modifier) {
		this.damage = damage;
		this.speed = speed;
		this.modifier = modifier;
	}

	public ToolModel() {
		this.damage = 1.0f;
		this.speed = 1.0f;
		this.modifier = 1.0f;
	}

	public float getDamage() {
		return damage;
	}

	public float getSpeed() {
		return speed;
	}

	public float getModifier() {
		return modifier;
	}
}