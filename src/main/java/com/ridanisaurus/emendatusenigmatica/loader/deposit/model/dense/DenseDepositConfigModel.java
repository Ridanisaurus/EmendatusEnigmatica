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

package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.dense;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sample.SampleBlockDefinitionModel;

import java.util.List;

public class DenseDepositConfigModel {
	public static final Codec<DenseDepositConfigModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.list(CommonBlockDefinitionModel.CODEC).fieldOf("blocks").forGetter(it -> it.blocks),
			Codec.list(Codec.STRING).fieldOf("fillerTypes").forGetter(it -> it.fillerTypes),
			Codec.INT.fieldOf("chance").forGetter(it -> it.chance),
			Codec.INT.fieldOf("size").forGetter(it -> it.size),
			Codec.INT.fieldOf("minYLevel").forGetter(it -> it.minYLevel),
			Codec.INT.fieldOf("maxYLevel").forGetter(it -> it.maxYLevel),
			Codec.BOOL.fieldOf("generateSamples").orElse(false).forGetter(it -> it.generateSamples),
			Codec.list(SampleBlockDefinitionModel.CODEC).fieldOf("sampleBlocks").orElse(List.of()).forGetter(it -> it.sampleBlocks)
	).apply(x, DenseDepositConfigModel::new));

	private final List<CommonBlockDefinitionModel> blocks;
	private final List<String> fillerTypes;
	private final int chance;
	private final int size;
	private final int minYLevel;
	private final int maxYLevel;
	private final boolean generateSamples;
	private final List<SampleBlockDefinitionModel> sampleBlocks;

	public DenseDepositConfigModel(List<CommonBlockDefinitionModel> blocks, List<String> fillerTypes, int chance, int size, int minYLevel, int maxYLevel, boolean generateSamples, List<SampleBlockDefinitionModel> sampleBlocks) {

		this.blocks = blocks;
		this.chance = chance;
		this.size = size;
		this.minYLevel = minYLevel;
		this.maxYLevel = maxYLevel;
		this.fillerTypes = fillerTypes;
		this.generateSamples = generateSamples;
		this.sampleBlocks = sampleBlocks;
	}

	public int getChance() {
		return chance;
	}

	public int getPlacementChance() {
		return (100 - chance) + 1;
	}

	public int getMaxYLevel() {
		return maxYLevel;
	}

	public int getMinYLevel() {
		return minYLevel;
	}

	public List<CommonBlockDefinitionModel> getBlocks() {
		return blocks;
	}

	public List<String> getFillerTypes() {
		return fillerTypes;
	}

	public int getSize() {
		return size;
	}

	public boolean getGenerateSamples() {
		return generateSamples;
	}

	public List<SampleBlockDefinitionModel> getSampleBlocks() {
		return sampleBlocks;
	}
}
