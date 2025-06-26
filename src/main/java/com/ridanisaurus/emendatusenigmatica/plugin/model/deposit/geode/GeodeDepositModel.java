/*
 * MIT License
 *
 * Copyright (c) 2024. Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.geode;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.common.CommonDepositModelBase;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.sample.SampleBlockDefinitionModel;

import java.util.List;

public class GeodeDepositModel extends CommonDepositModelBase {
	public static final Codec<GeodeDepositModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.fieldOf("type").forGetter(it -> it.type),
			Codec.STRING.fieldOf("dimension").forGetter(it -> it.dimension),
			Codec.list(Codec.STRING).fieldOf("biomes").orElse(List.of()).forGetter(it -> it.biomes),
			Codec.STRING.fieldOf("registryName").forGetter(it -> it.name),
			GeodeDepositConfigModel.CODEC.fieldOf("config").forGetter(it -> it.config)
	).apply(x, GeodeDepositModel::new));

	private final GeodeDepositConfigModel config;

	public GeodeDepositModel(String type, String dimension, List<String> biomes, String name, GeodeDepositConfigModel config) {
		super(type, dimension, biomes, name);
		this.config = config;
	}

	public String getType() {
		return super.getType();
	}

	public List<String> getFillerTypes() {
		return config.fillerTypes;
	}

	public List<String> getClusters() {
		return config.clusters;
	}

	public int getMaxYLevel() {
		return config.maxYLevel;
	}

	public int getMinYLevel() {
		return config.minYLevel;
	}

	public int getChance() {
		return config.chance;
	}

	public int getPlacementChance() {
		return config.rarity.equals("rare") ? (100 - getChance()) + 1 : getChance();
	}

	public double getCrackChance() {
		return config.crackChance;
	}

	public List<CommonBlockDefinitionModel> getOuterShellBlocks() {
		return config.outerShellBlocks;
	}

	public List<CommonBlockDefinitionModel> getInnerShellBlocks() {
		return config.innerShellBlocks;
	}

	public List<CommonBlockDefinitionModel> getInnerBlocks() {
		return config.innerBlocks;
	}

	public List<CommonBlockDefinitionModel> getFillBlocks() {
		// TODO: Check to see if this can be empty
		// NOTE: Currently checked if not empty in the validation step. See #218 *Kanzaji
		return config.fillBlocks;
	}

	public String getPlacement() {
		return config.placement;
	}

	public String getRarity() {
		return config.rarity;
	}

	public boolean hasSamples() {
		return config.generateSamples;
	}

	public List<SampleBlockDefinitionModel> getSampleBlocks() {
		return config.sampleBlocks;
	}
}
