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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MaterialModel {
	public static final Codec<MaterialModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.fieldOf("id").forGetter(i -> i.id),
			Codec.STRING.fieldOf("source").forGetter(i -> i.source),
			Codec.STRING.fieldOf("localizedName").forGetter(i -> i.localizedName),
			Codec.list(Codec.STRING).fieldOf("processedType").forGetter(i -> i.processedType),
			MaterialPropertiesModel.CODEC.optionalFieldOf("properties").forGetter(i -> Optional.of(i.properties)),
			MaterialDropsModel.CODEC.optionalFieldOf("drops").forGetter(i -> Optional.of(i.drops)),
			MaterialColorsModel.CODEC.optionalFieldOf("colors").forGetter(i -> Optional.of(i.colors))
	).apply(x, (id, source, localizedName, processedType, properties, drops, colors) -> new MaterialModel(
			id,
			source,
			localizedName,
			processedType,
			properties.orElse(new MaterialPropertiesModel()),
			drops.orElse(new MaterialDropsModel()),
			colors.orElse(new MaterialColorsModel())
	)));

	private final String id;
	private final String source;
	private final String localizedName;
	private final List<String> processedType;
	private final MaterialPropertiesModel properties;
	private final MaterialDropsModel drops;
	private final MaterialColorsModel colors;

	public MaterialModel(String id, String source, String localizedName, List<String> processedType, MaterialPropertiesModel properties, MaterialDropsModel drops, MaterialColorsModel colors) {
		this.id = id;
		this.source = source;
		this.localizedName = localizedName;
		this.processedType = processedType;
		this.properties = properties;
		this.drops = drops;
		this.colors = colors;
	}

	public String getId() {
		return id;
	}

	public String getSource() {
		return source;
	}

	public boolean isModded() {
		return getSource().equals("modded");
	}

	public boolean isVanilla() {
		return getSource().equals("vanilla");
	}

	public String getLocalizedName() {
		return localizedName;
	}

	public List<String> getProcessedType() {
		return processedType;
	}

	public MaterialPropertiesModel getProperties() {
		return properties;
	}

	public MaterialDropsModel getDrops() {
		return drops;
	}

	public MaterialColorsModel getColors() {
		return colors;
	}
}