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
			Codec.FLOAT.optionalFieldOf("hardness").forGetter(i -> Optional.of(i.hardness)),
			Codec.FLOAT.optionalFieldOf("resistance").forGetter(i -> Optional.of(i.resistance)),
			Codec.INT.optionalFieldOf("harvestLevel").forGetter(i -> Optional.of(i.harvestLevel))
	).apply(x, (f, f2, i) -> new MaterialPropertiesModel(f.orElse(3f), f2.orElse(3f), i.orElse(1))));

	private final float hardness;
	private final float resistance;
	private final int harvestLevel;

	public MaterialPropertiesModel(float hardness, float resistance, int harvestLevel) {
		this.hardness = hardness;
		this.resistance = resistance;
		this.harvestLevel = harvestLevel;
	}

	public MaterialPropertiesModel() {
		this.hardness = 3f;
		this.resistance = 3f;
		this.harvestLevel = 1;
	}

	public float getHardness() {
		return hardness;
	}

	public float getResistance() {
		return resistance;
	}

	public int getHarvestLevel() {
		return harvestLevel;
	}
}
