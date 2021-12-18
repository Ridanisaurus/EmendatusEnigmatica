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

public class MaterialCreateCompatModel {
	public static final Codec<MaterialCreateCompatModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.INT.optionalFieldOf("crushingPrimaryCount").forGetter(i -> Optional.of(i.crushingPrimaryCount)),
			Codec.FLOAT.optionalFieldOf("crushingPrimaryChance").forGetter(i -> Optional.of(i.crushingPrimaryChance)),
			Codec.INT.optionalFieldOf("crushingSecondaryCount").forGetter(i -> Optional.of(i.crushingSecondaryCount)),
			Codec.FLOAT.optionalFieldOf("crushingSecondaryChance").forGetter(i -> Optional.of(i.crushingSecondaryChance))
	).apply(x, (crushingPrimaryCount, crushingPrimaryChance, crushingSecondaryCount, crushingSecondaryChance) -> new MaterialCreateCompatModel(
			crushingPrimaryCount.orElse(1),
			crushingPrimaryChance.orElse(1f),
			crushingSecondaryCount.orElse(1),
			crushingSecondaryChance.orElse(1f)
	)));

	private final int crushingPrimaryCount;
	private final float crushingPrimaryChance;
	private final int crushingSecondaryCount;
	private final float crushingSecondaryChance;

	public MaterialCreateCompatModel(int crushingPrimaryCount, float crushingPrimaryChance, int crushingSecondaryCount, float crushingSecondaryChance) {
		this.crushingPrimaryCount = crushingPrimaryCount;
		this.crushingPrimaryChance = crushingPrimaryChance;
		this.crushingSecondaryCount = crushingSecondaryCount;
		this.crushingSecondaryChance = crushingSecondaryChance;
	}

	public MaterialCreateCompatModel() {
		this.crushingPrimaryCount = 1;
		this.crushingPrimaryChance = 1f;
		this.crushingSecondaryCount = 1;
		this.crushingSecondaryChance = 1f;
	}

	public int getCrushingPrimaryCount() {
		return crushingPrimaryCount;
	}

	public float getCrushingPrimaryChance() {
		return crushingPrimaryChance;
	}

	public int getCrushingSecondaryCount() {
		return crushingSecondaryCount;
	}

	public float getCrushingSecondaryChance() {
		return crushingSecondaryChance;
	}
}