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

public class MaterialPropertiesModel {
	public static final Codec<MaterialPropertiesModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.optionalFieldOf("oreBlockType").forGetter(i -> Optional.ofNullable(i.oreBlockType)),
			Codec.INT.optionalFieldOf("harvestLevel").forGetter(i -> Optional.of(i.harvestLevel)),
			Codec.BOOL.optionalFieldOf("hasParticle").forGetter(i -> Optional.of(i.hasParticle)),
			Codec.BOOL.optionalFieldOf("isBurnable").forGetter(i -> Optional.of(i.isBurnable)),
			Codec.INT.optionalFieldOf("burnTime").forGetter(i -> Optional.of(i.burnTime))
	).apply(x, (oreBlockType, harvestLevel, hasParticle, isBurnable, burnTime) -> new MaterialPropertiesModel(
			oreBlockType.orElse(""),
			harvestLevel.orElse(1),
			hasParticle.orElse(false),
			isBurnable.orElse(false),
			burnTime.orElse(0)
	)));

	private final String oreBlockType;
	private final int harvestLevel;
	private final boolean hasParticle;
	private final boolean isBurnable;
	private final int burnTime;

	public MaterialPropertiesModel(String oreBlockType, int harvestLevel, boolean hasParticle, boolean isBurnable, int burnTime) {
		this.oreBlockType = oreBlockType;
		this.harvestLevel = harvestLevel;
		this.hasParticle = hasParticle;
		this.isBurnable = isBurnable;
		this.burnTime = burnTime;
	}

	public MaterialPropertiesModel() {
		this.oreBlockType = "";
		this.harvestLevel = 1;
		this.hasParticle = false;
		this.isBurnable = false;
		this.burnTime = 0;
	}

	public String getOreBlockType() {
		return oreBlockType;
	}

	public int getHarvestLevel() {
		return harvestLevel;
	}

	public boolean hasParticle() {
		return hasParticle;
	}

	public boolean isBurnable() {
		return isBurnable;
	}

	public int getBurnTime() {
		return burnTime;
	}
}
