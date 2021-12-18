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
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import java.util.Optional;

public class MaterialThermalCompatModel {
	// TODO: Refactor this part, it looks messy
	public static final Codec<MaterialThermalCompatModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.INT.optionalFieldOf("pulverizerPrimaryCount").forGetter(i -> Optional.of(i.pulverizerPrimaryCount)),
			Codec.FLOAT.optionalFieldOf("pulverizerPrimaryChance").forGetter(i -> Optional.of(i.pulverizerPrimaryChance)),
			Codec.STRING.optionalFieldOf("pulverizerSecondaryOutput").forGetter(i -> Optional.ofNullable(i.pulverizerSecondaryOutput)),
			Codec.INT.optionalFieldOf("pulverizerSecondaryCount").forGetter(i -> Optional.of(i.pulverizerSecondaryCount)),
			Codec.FLOAT.optionalFieldOf("pulverizerSecondaryChance").forGetter(i -> Optional.of(i.pulverizerSecondaryChance)),
			Codec.STRING.optionalFieldOf("pulverizerTertiaryOutput").forGetter(i -> Optional.ofNullable(i.pulverizerTertiaryOutput)),
			Codec.INT.optionalFieldOf("pulverizerTertiaryCount").forGetter(i -> Optional.of(i.pulverizerTertiaryCount)),
			Codec.FLOAT.optionalFieldOf("pulverizerTertiaryChance").forGetter(i -> Optional.of(i.pulverizerTertiaryChance)),
			Codec.INT.optionalFieldOf("inductionPrimaryCount").forGetter(i -> Optional.of(i.inductionPrimaryCount)),
			Codec.FLOAT.optionalFieldOf("inductionPrimaryChance").forGetter(i -> Optional.of(i.inductionPrimaryChance)),
			Codec.STRING.optionalFieldOf("inductionSecondaryOutput").forGetter(i -> Optional.ofNullable(i.inductionSecondaryOutput)),
			Codec.INT.optionalFieldOf("inductionSecondaryCount").forGetter(i -> Optional.of(i.inductionSecondaryCount)),
			Codec.FLOAT.optionalFieldOf("inductionSecondaryChance").forGetter(i -> Optional.of(i.inductionSecondaryChance)),
			Codec.STRING.optionalFieldOf("inductionTertiaryOutput").forGetter(i -> Optional.ofNullable(i.inductionTertiaryOutput)),
			Codec.INT.optionalFieldOf("inductionTertiaryCount").forGetter(i -> Optional.of(i.inductionTertiaryCount)),
			Codec.FLOAT.optionalFieldOf("inductionTertiaryChance").forGetter(i -> Optional.of(i.inductionTertiaryChance))
	).apply(x, (p1Count, p1Chance, p2Output, p2Count, p2Chance, p3Output, p3Count, p3Chance, i1Count, i1Chance, i2Output, i2Count, i2Chance, i3Output, i3Count, i3Chance) -> new MaterialThermalCompatModel(
			p1Count.orElse(1),
			p1Chance.orElse(1f),
			p2Output.orElse(""),
			p2Count.orElse(1),
			p2Chance.orElse(1f),
			p3Output.orElse(""),
			p3Count.orElse(1),
			p3Chance.orElse(1f),
			i1Count.orElse(1),
			i1Chance.orElse(1f),
			i2Output.orElse(""),
			i2Count.orElse(1),
			i2Chance.orElse(1f),
			i3Output.orElse(""),
			i3Count.orElse(1),
			i3Chance.orElse(1f)
	)));

	private final int pulverizerPrimaryCount;
	private final float pulverizerPrimaryChance;
	private final String pulverizerSecondaryOutput;
	private final int pulverizerSecondaryCount;
	private final float pulverizerSecondaryChance;
	private final String pulverizerTertiaryOutput;
	private final int pulverizerTertiaryCount;
	private final float pulverizerTertiaryChance;

	private final int inductionPrimaryCount;
	private final float inductionPrimaryChance;
	private final String inductionSecondaryOutput;
	private final int inductionSecondaryCount;
	private final float inductionSecondaryChance;
	private final String inductionTertiaryOutput;
	private final int inductionTertiaryCount;
	private final float inductionTertiaryChance;

	public MaterialThermalCompatModel(int pulverizerPrimaryCount, float pulverizerPrimaryChance, String pulverizerSecondaryOutput, int pulverizerSecondaryCount, float pulverizerSecondaryChance, String pulverizerTertiaryOutput, int pulverizerTertiaryCount, float pulverizerTertiaryChance, int inductionPrimaryCount, float inductionPrimaryChance, String inductionSecondaryOutput, int inductionSecondaryCount, float inductionSecondaryChance, String inductionTertiaryOutput, int inductionTertiaryCount, float inductionTertiaryChance) {
		this.pulverizerPrimaryCount = pulverizerPrimaryCount;
		this.pulverizerPrimaryChance = pulverizerPrimaryChance;
		this.pulverizerSecondaryOutput = pulverizerSecondaryOutput;
		this.pulverizerSecondaryCount = pulverizerSecondaryCount;
		this.pulverizerSecondaryChance = pulverizerSecondaryChance;
		this.pulverizerTertiaryOutput = pulverizerTertiaryOutput;
		this.pulverizerTertiaryCount = pulverizerTertiaryCount;
		this.pulverizerTertiaryChance = pulverizerTertiaryChance;
		this.inductionPrimaryCount = inductionPrimaryCount;
		this.inductionPrimaryChance = inductionPrimaryChance;
		this.inductionSecondaryOutput = inductionSecondaryOutput;
		this.inductionSecondaryCount = inductionSecondaryCount;
		this.inductionSecondaryChance = inductionSecondaryChance;
		this.inductionTertiaryOutput = inductionTertiaryOutput;
		this.inductionTertiaryCount = inductionTertiaryCount;
		this.inductionTertiaryChance = inductionTertiaryChance;
	}

	public MaterialThermalCompatModel() {
		this.pulverizerPrimaryCount = 1;
		this.pulverizerPrimaryChance = 1f;
		this.pulverizerSecondaryOutput = "";
		this.pulverizerSecondaryCount = 1;
		this.pulverizerSecondaryChance = 1f;
		this.pulverizerTertiaryOutput = "";
		this.pulverizerTertiaryCount = 1;
		this.pulverizerTertiaryChance = 1f;

		this.inductionPrimaryCount = 1;
		this.inductionPrimaryChance = 1f;
		this.inductionSecondaryOutput = "";
		this.inductionSecondaryCount = 1;
		this.inductionSecondaryChance = 1f;
		this.inductionTertiaryOutput = "";
		this.inductionTertiaryCount = 1;
		this.inductionTertiaryChance = 1f;
	}

	public int getPulverizerPrimaryCount() {
		return pulverizerPrimaryCount;
	}

	public float getPulverizerPrimaryChance() {
		return pulverizerPrimaryChance;
	}

	public boolean isPulverizerSecondarySet() {
		return !pulverizerSecondaryOutput.isEmpty();
	}

	public IItemProvider getPulverizerSecondaryOutput() {
		return Registry.ITEM.get(new ResourceLocation(pulverizerSecondaryOutput));
	}

	public int getPulverizerSecondaryCount() {
		return pulverizerSecondaryCount;
	}

	public float getPulverizerSecondaryChance() {
		return pulverizerSecondaryChance;
	}

	public boolean isPulverizerTertiarySet() {
		return !pulverizerTertiaryOutput.isEmpty();
	}


	public IItemProvider getPulverizerTertiaryOutput() {
		return Registry.ITEM.get(new ResourceLocation(pulverizerTertiaryOutput));
	}

	public int getPulverizerTertiaryCount() {
		return pulverizerTertiaryCount;
	}

	public float getPulverizerTertiaryChance() {
		return pulverizerTertiaryChance;
	}
	public int getInductionPrimaryCount() {
		return inductionPrimaryCount;
	}

	public float getInductionPrimaryChance() {
		return inductionPrimaryChance;
	}

	public boolean isInductionSecondarySet() {
		return !inductionSecondaryOutput.isEmpty();
	}

	public IItemProvider getInductionSecondaryOutput() {
		return Registry.ITEM.get(new ResourceLocation(inductionSecondaryOutput));
	}

	public int getInductionSecondaryCount() {
		return inductionSecondaryCount;
	}

	public float getInductionSecondaryChance() {
		return inductionSecondaryChance;
	}

	public boolean isInductionTertiarySet() {
		return !inductionTertiaryOutput.isEmpty();
	}


	public IItemProvider getInductionTertiaryOutput() {
		return Registry.ITEM.get(new ResourceLocation(inductionTertiaryOutput));
	}

	public int getInductionTertiaryCount() {
		return inductionTertiaryCount;
	}

	public float getInductionTertiaryChance() {
		return inductionTertiaryChance;
	}
}