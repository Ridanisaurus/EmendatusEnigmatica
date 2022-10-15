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

package com.ridanisaurus.emendatusenigmatica.loader.parser.model.compat;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Optional;

public class CreateCrushingCompat {
	public static final Codec<CreateCrushingCompat> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.INT.optionalFieldOf("firstOutputCount").forGetter(i -> Optional.of(i.firstOutputCount)),
			Codec.INT.optionalFieldOf("secondOutputCount").forGetter(i -> Optional.of(i.secondOutputCount)),
			Codec.FLOAT.optionalFieldOf("secondOutputChance").forGetter(i -> Optional.of(i.secondOutputChance))
	).apply(x, (firstOutputCount, secondOutputCount, secondaryChance) -> new CreateCrushingCompat(
			firstOutputCount.orElse(1),
			secondOutputCount.orElse(0),
			secondaryChance.orElse(0f)
	)));

	private final int firstOutputCount;
	private final int secondOutputCount;
	private final float secondOutputChance;

	public CreateCrushingCompat(int firstOutputCount, int secondOutputCount, float secondOutputChance) {
		this.firstOutputCount = firstOutputCount;
		this.secondOutputCount = secondOutputCount;
		this.secondOutputChance = secondOutputChance;
	}

	public CreateCrushingCompat() {
		this.firstOutputCount = 1;
		this.secondOutputCount = 1;
		this.secondOutputChance = 1f;
	}

	public int getFirstOutputCount() {
		return firstOutputCount;
	}

	public int getSecondOutputCount() {
		return secondOutputCount;
	}

	public float getSecondOutputChance() {
		return secondOutputChance;
	}
}