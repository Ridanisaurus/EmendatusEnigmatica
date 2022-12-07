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
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

public class StrataModel {
	public static final Codec<StrataModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.fieldOf("id").forGetter(i -> i.id),
			Codec.STRING.fieldOf("baseTexture").forGetter(i -> i.baseTexture.toString()),
			Codec.STRING.fieldOf("suffix").forGetter(i -> i.suffix),
			Codec.STRING.fieldOf("fillerType").forGetter(i -> i.fillerType.toString()),
			Codec.STRING.fieldOf("localizedName").forGetter(i -> i.localizedName),
			Codec.STRING.optionalFieldOf("harvestTool").forGetter(i -> Optional.ofNullable(i.harvestTool)),
			Codec.FLOAT.optionalFieldOf("hardness").forGetter(i -> Optional.of(i.hardness)),
			Codec.FLOAT.optionalFieldOf("resistance").forGetter(i -> Optional.of(i.resistance))
	).apply(x, (s, s2, s3, s4, s5, s6, f, f2) -> new StrataModel(
			s,
			new ResourceLocation(s2),
			s3,
			new ResourceLocation(s4),
			s5,
			s6.orElse("pickaxe"),
			f.orElse(3f),
			f2.orElse(3f)
	)));

	private final String id;
	private final ResourceLocation baseTexture;
	private final String suffix;
	private final ResourceLocation fillerType;
	private final String localizedName;
	private final String harvestTool;
	private final float hardness;
	private final float resistance;

	public StrataModel(String id, ResourceLocation baseTexture, String suffix, ResourceLocation fillerType, String localizedName, String harvestTool, float hardness, float resistance) {
		this.id = id;
		this.baseTexture = baseTexture;
		this.suffix = suffix;
		this.fillerType = fillerType;
		this.localizedName = localizedName;
		this.harvestTool = harvestTool;
		this.hardness = hardness;
		this.resistance = resistance;
	}

	public String getId() {
		return id;
	}

	public ResourceLocation getBaseTexture() {
		return baseTexture;
	}

	public String getSuffix() {
		return suffix;
	}

	public ResourceLocation getFillerType() {
		return fillerType;
	}

	public String getLocalizedName() {
		return localizedName;
	}

	public String getHarvestTool() {
		return harvestTool;
	}

	public float getHardness() {
		return hardness;
	}

	public float getResistance() {
		return resistance;
	}
}
