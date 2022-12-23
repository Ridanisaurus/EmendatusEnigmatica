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
			Codec.INT.optionalFieldOf("damage").forGetter(i -> Optional.of(i.damage)),
			Codec.FLOAT.optionalFieldOf("speed").forGetter(i -> Optional.of(i.speed))
	).apply(x, (damage, speed) -> new ToolModel(
			damage.orElse(1),
			speed.orElse(1.0f)
	)));

	private final int damage;
	private final float speed;

	public ToolModel(int damage, float speed) {
		this.damage = damage;
		this.speed = speed;
	}

	public ToolModel() {
		this.damage = 1;
		this.speed = 1.0f;
	}

	public int getDamage() {
		return damage;
	}

	public float getSpeed() {
		return speed;
	}
}