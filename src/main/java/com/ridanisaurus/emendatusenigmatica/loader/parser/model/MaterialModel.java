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

import java.util.List;
import java.util.Optional;

public class MaterialModel {
	public static final Codec<MaterialModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.fieldOf("id").forGetter(i -> i.id),
			Codec.STRING.fieldOf("localisedName").forGetter(i -> i.localisedName),
			Codec.STRING.optionalFieldOf("color").forGetter(i -> i.color),
          Codec.list(Codec.STRING).fieldOf("processedType").forGetter(i -> i.processedType),
          Codec.BOOL.optionalFieldOf("isBurnable").forGetter(i -> Optional.of(i.isBurnable)),
          Codec.INT.optionalFieldOf("burnTime").forGetter(i -> Optional.of(i.burnTime)),
          Codec.STRING.optionalFieldOf("oreBlockType").forGetter(i -> Optional.ofNullable(i.oreBlockType)),
          Codec.STRING.optionalFieldOf("oreBlockDropType").forGetter(i -> Optional.ofNullable(i.oreBlockDropType)),
			MaterialPropertiesModel.CODEC.optionalFieldOf("properties").forGetter(i -> Optional.of(i.properties)),
			Codec.STRING.optionalFieldOf("defaultItemDrop").forGetter(i -> Optional.ofNullable(i.defaultItemDrop)),
			Codec.INT.optionalFieldOf("dropMin").forGetter(i -> Optional.of(i.dropMin)),
			Codec.INT.optionalFieldOf("dropMax").forGetter(i -> Optional.of(i.dropMax))
	).apply(x, (s, s2, c, sl, b, i, s3, s4, mm, s5, i2, i3) -> new MaterialModel(s, s2, c, sl, b.orElse(false), i.orElse(0), s3.orElse(""), s4.orElse("chunk"), mm.orElse(new MaterialPropertiesModel()), s5.orElse(""), i2.orElse(1), i3.orElse(1))));

	private final String id;
	private final String localisedName;
	private final List<String> processedType;
	private final boolean isBurnable;
	private final int burnTime;
	private final String oreBlockType;
	private final String oreBlockDropType;
	private final MaterialPropertiesModel properties;
	private final String defaultItemDrop;
	private final int dropMin;
	private final int dropMax;
  private final Optional<String> color;

	public MaterialModel(String id, String localisedName, Optional<String> color, List<String> processedType, boolean isBurnable, int burnTime, String oreBlockType, String oreBlockDropType, MaterialPropertiesModel properties, String defaultItemDrop, int dropMin, int dropMax) {
		this.id = id;
		this.localisedName = localisedName;
		this.processedType = processedType;
		this.isBurnable = isBurnable;
		this.burnTime = burnTime;
		this.oreBlockType = oreBlockType;
		this.oreBlockDropType = oreBlockDropType;
    this.properties = properties;
    this.defaultItemDrop = defaultItemDrop;
    this.dropMin = dropMin;
    this.dropMax = dropMax;
    this.color = color;
  }

  public String getId() {
    return id;
  }

  public String getLocalisedName() {
    return localisedName;
  }

  public List<String> getProcessedType() {
    return processedType;
  }

  public String getOreBlockType() {
    return oreBlockType;
  }

  public String getDefaultItemDrop() {
    return defaultItemDrop;
  }

  public MaterialPropertiesModel getProperties() {
    return properties;
  }

  public int getDropMin() {
    return dropMin;
  }

  public int getDropMax() {
    return dropMax;
  }

  public boolean isBurnable() {
    return isBurnable;
  }

  public int getBurnTime() {
    return burnTime;
  }

  public int getColor() {
    return color.map(x -> Integer.parseInt(x, 16)).orElse(0xFFFFFF);
  }
	public int getBurnTime() {
		return burnTime;
	}

	public String getOreBlockDropType() {
		return oreBlockDropType;
	}
}
