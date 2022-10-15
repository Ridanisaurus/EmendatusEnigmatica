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
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import java.util.Optional;

public class ThermalInductionCompat {
	public static final Codec<ThermalInductionCompat> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.INT.optionalFieldOf("firstOutputCount").forGetter(i -> Optional.of(i.firstOutputCount)),
			Codec.FLOAT.optionalFieldOf("firstOutputChance").forGetter(i -> Optional.of(i.firstOutputChance)),
			Codec.STRING.optionalFieldOf("secondOutput").forGetter(i -> Optional.ofNullable(i.secondOutput)),
			Codec.INT.optionalFieldOf("secondOutputCount").forGetter(i -> Optional.of(i.secondOutputCount)),
			Codec.FLOAT.optionalFieldOf("secondOutputChance").forGetter(i -> Optional.of(i.secondOutputChance)),
			Codec.STRING.optionalFieldOf("firstInput").forGetter(i -> Optional.ofNullable(i.firstInput)),
			Codec.INT.optionalFieldOf("firstInputCount").forGetter(i -> Optional.of(i.firstInputCount)),
			Codec.STRING.optionalFieldOf("secondInput").forGetter(i -> Optional.ofNullable(i.secondInput)),
			Codec.INT.optionalFieldOf("secondInputCount").forGetter(i -> Optional.of(i.secondInputCount)),
			Codec.STRING.optionalFieldOf("thirdInput").forGetter(i -> Optional.ofNullable(i.thirdInput)),
			Codec.INT.optionalFieldOf("thirdInputCount").forGetter(i -> Optional.of(i.thirdInputCount))
	).apply(x, (firstOutputCount, firstOutputChance, secondOutput, secondOutputCount, secondOutputChance, firstInput, firstInputCount, secondInput, secondInputCount, thirdInput, thirdInputCount) -> new ThermalInductionCompat(
			firstOutputCount.orElse(1),
			firstOutputChance.orElse(0.5f),
			secondOutput.orElse(""),
			secondOutputCount.orElse(0),
			secondOutputChance.orElse(0f),
			firstInput.orElse(""),
			firstInputCount.orElse(0),
			secondInput.orElse(""),
			secondInputCount.orElse(0),
			thirdInput.orElse(""),
			thirdInputCount.orElse(0)
	)));

	private final int firstOutputCount;
	private final float firstOutputChance;
	private final String secondOutput;
	private final int secondOutputCount;
	private final float secondOutputChance;
	private final String firstInput;
	private final int firstInputCount;
	private final String secondInput;
	private final int secondInputCount;
	private final String thirdInput;
	private final int thirdInputCount;

	public ThermalInductionCompat(int firstOutputCount, float firstOutputChance, String secondOutput, int secondOutputCount, float secondOutputChance, String firstInput, int firstInputCount, String secondInput, int secondInputCount, String thirdInput, int thirdInputCount) {
		this.firstOutputCount = firstOutputCount;
		this.firstOutputChance = firstOutputChance;
		this.secondOutput = secondOutput;
		this.secondOutputCount = secondOutputCount;
		this.secondOutputChance = secondOutputChance;
		this.firstInput = firstInput;
		this.firstInputCount = firstInputCount;
		this.secondInput = secondInput;
		this.secondInputCount = secondInputCount;
		this.thirdInput = thirdInput;
		this.thirdInputCount = thirdInputCount;
	}

	public ThermalInductionCompat() {
		this.firstOutputCount = 1;
		this.firstOutputChance = 1.0f;
		this.secondOutput = "";
		this.secondOutputCount = 0;
		this.secondOutputChance = 0f;
		this.firstInput = "";
		this.firstInputCount = 0;
		this.secondInput = "";
		this.secondInputCount = 0;
		this.thirdInput = "";
		this.thirdInputCount = 0;
	}

	public int getFirstOutputCount() {
		return firstOutputCount;
	}

	public float getFirstOutputChance() {
		return firstOutputChance;
	}

	public float getFirstOutputCombinedChance() {
		return firstOutputCount + firstOutputChance;
	}

	public IItemProvider getSecondOutput() {
		return Registry.ITEM.get(new ResourceLocation(secondOutput));
	}

	public int getSecondOutputCount() {
		return secondOutputCount;
	}

	public float getSecondOutputChance() {
		return secondOutputChance;
	}

	public float getSecondOutputCombinedChance() {
		return secondOutputCount + secondOutputChance;
	}

	public IItemProvider getFirstInput() {
		return Registry.ITEM.get(new ResourceLocation(firstInput));
	}

	public int getFirstInputCount() {
		return firstInputCount;
	}

	public IItemProvider getSecondInput() {
		return Registry.ITEM.get(new ResourceLocation(secondInput));
	}

	public int getSecondInputCount() {
		return secondInputCount;
	}

	public IItemProvider getThirdInput() {
		return Registry.ITEM.get(new ResourceLocation(thirdInput));
	}

	public int getThirdInputCount() {
		return thirdInputCount;
	}

	public boolean isThirdInputSet() {
		return !thirdInput.isEmpty();
	}
}