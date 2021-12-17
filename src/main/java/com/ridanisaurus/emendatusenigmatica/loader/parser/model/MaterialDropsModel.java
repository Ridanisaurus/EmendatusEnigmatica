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

public class MaterialDropsModel {
	public static final Codec<MaterialDropsModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.optionalFieldOf("drop").forGetter(i -> Optional.ofNullable(i.drop)),
			Codec.INT.optionalFieldOf("vanillaMin").forGetter(i -> Optional.of(i.vanillaMin)),
			Codec.INT.optionalFieldOf("vanillaMax").forGetter(i -> Optional.of(i.vanillaMax)),
			Codec.INT.optionalFieldOf("createMin").forGetter(i -> Optional.of(i.createMin)),
			Codec.INT.optionalFieldOf("createMax").forGetter(i -> Optional.of(i.createMax)),
			Codec.INT.optionalFieldOf("thermalMin").forGetter(i -> Optional.of(i.thermalMin)),
			Codec.INT.optionalFieldOf("thermalMax").forGetter(i -> Optional.of(i.thermalMax))
	).apply(x, (drop, vanillaMin, vanillaMax, createMin, createMax, thermalMin, thermalMax) -> new MaterialDropsModel(
			drop.orElse(""),
			vanillaMin.orElse(1),
			vanillaMax.orElse(1),
			createMin.orElse(1),
			createMax.orElse(1),
			thermalMin.orElse(1),
			thermalMax.orElse(1)
	)));

	private final String drop;
	private final int vanillaMin;
	private final int vanillaMax;
	private final int createMin;
	private final int createMax;
	private final int thermalMin;
	private final int thermalMax;

	public MaterialDropsModel(String drop, int vanillaMin, int vanillaMax, int createMin, int createMax, int thermalMin, int thermalMax) {
		this.drop = drop;
		this.vanillaMin = vanillaMin;
		this.vanillaMax = vanillaMax;
		this.createMin = createMin;
		this.createMax = createMax;
		this.thermalMin = thermalMin;
		this.thermalMax = thermalMax;
	}

	public MaterialDropsModel() {
		this.drop = "";
		this.vanillaMin = 1;
		this.vanillaMax = 1;
		this.createMin = 1;
		this.createMax = 1;
		this.thermalMin = 1;
		this.thermalMax = 1;
	}

	public String getDrop() {
		return drop;
	}

	public int getVanillaMin() {
		return vanillaMin;
	}

	public int getVanillaMax() {
		return vanillaMax;
	}

	public int getCreateMin() {
		return createMin;
	}

	public int getCreateMax() {
		return createMax;
	}

	public int getThermalMin() {
		return thermalMin;
	}

	public int getThermalMax() {
		return thermalMax;
	}

	public IItemProvider getDefaultItemDropAsItem() {
		return Registry.ITEM.get(new ResourceLocation(drop));
	}
}