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
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonDepositModelBase;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sample.SampleBlockDefinitionModel;

import java.util.List;

public class DenseDepositModel extends CommonDepositModelBase {
	public static final Codec<DenseDepositModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.fieldOf("type").forGetter(it -> it.type),
			Codec.STRING.fieldOf("dimension").forGetter(it -> it.dimension),
			Codec.list(Codec.STRING).fieldOf("biomes").orElse(List.of()).forGetter(it -> it.biomes),
			Codec.STRING.fieldOf("registryName").forGetter(it -> it.name),
			DenseDepositConfigModel.CODEC.fieldOf("config").forGetter(it -> it.config)
	).apply(x, DenseDepositModel::new));

	private final DenseDepositConfigModel config;

	public DenseDepositModel(String type, String dimension, List<String> biomes, String name, DenseDepositConfigModel config) {
		super(type, dimension, biomes, name);
		this.config = config;
	}

	public String getType() {
		return super.getType();
	}

	public int getChance() {
		if (config.chance < 1 || config.chance > 100) throw new IllegalArgumentException("Chance for " + name + " is out of Range [1 - 100]");
		return config.chance;
	}

	public int getPlacementChance() {
		return config.rarity.equals("rare") ? (100 - getChance()) + 1 : getChance();
	}

	public int getMaxYLevel() {
		if (config.maxYLevel < -64 || config.maxYLevel > 320) throw new IllegalArgumentException("Max Y for " + name + " is out of Range [-64 - 320]");
		return config.maxYLevel;
	}

	public int getMinYLevel() {
		if (config.minYLevel < -64 || config.minYLevel > 320) throw new IllegalArgumentException("Min Y for " + name + " is out of Range [-64 - 320]");
		return config.minYLevel;
	}

	public List<CommonBlockDefinitionModel> getBlocks() {
		if (config.blocks.isEmpty()) throw new IllegalArgumentException("Blocks for " + name + " cannot be empty.");
		return config.blocks;
	}

	public List<String> getFillerTypes() {
		if (config.fillerTypes.isEmpty()) throw new IllegalArgumentException("Filler Types for " + name + " cannot be empty.");
		return config.fillerTypes;
	}

	public int getSize() {
		if (config.size < 1 || config.size > 48) throw new IllegalArgumentException("Size for " + name + " is out of Range [1 - 48]");
		return config.size;
	}

	public String getPlacement() {
		if (!config.placement.equals("uniform") && !config.placement.equals("triangle")) throw new IllegalArgumentException("Placement for " + name + " is invalid.");
		return config.placement;
	}

	public String getRarity() {
		if (!config.rarity.equals("common") && !config.rarity.equals("rare")) throw new IllegalArgumentException("Rarity for " + name + " is invalid.");
		return config.rarity;
	}

	public boolean hasSamples() {
		return config.generateSamples;
	}

	public List<SampleBlockDefinitionModel> getSampleBlocks() {
		if (hasSamples() && config.sampleBlocks.isEmpty()) throw new IllegalArgumentException("Sample Blocks for " + name + " cannot be empty if generateSamples is set to true.");
		return config.sampleBlocks;
	}
}