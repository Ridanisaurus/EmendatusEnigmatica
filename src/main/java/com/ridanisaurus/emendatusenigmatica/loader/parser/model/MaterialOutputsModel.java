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

public class MaterialOutputsModel {
	public static final Codec<MaterialOutputsModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.optionalFieldOf("drop").forGetter(i -> Optional.ofNullable(i.drop)),
			Codec.INT.optionalFieldOf("vanillaMin").forGetter(i -> Optional.of(i.vanillaMin)),
			Codec.INT.optionalFieldOf("vanillaMax").forGetter(i -> Optional.of(i.vanillaMax)),
			MaterialCreateCompatModel.CODEC.optionalFieldOf("createCompat").forGetter(i -> Optional.of(i.createCompat)),
			MaterialThermalCompatModel.CODEC.optionalFieldOf("thermalCompat").forGetter(i -> Optional.of(i.thermalCompat))
	).apply(x, (drop, vanillaMin, vanillaMax, createCompat, thermalCompat) -> new MaterialOutputsModel(
			drop.orElse(""),
			vanillaMin.orElse(1),
			vanillaMax.orElse(1),
			createCompat.orElse(new MaterialCreateCompatModel()),
			thermalCompat.orElse(new MaterialThermalCompatModel())
	)));

	private final String drop;
	private final int vanillaMin;
	private final int vanillaMax;
	private final MaterialCreateCompatModel createCompat;
	private final MaterialThermalCompatModel thermalCompat;

	public MaterialOutputsModel(String drop, int vanillaMin, int vanillaMax, MaterialCreateCompatModel createCompat, MaterialThermalCompatModel thermalCompat) {
		this.drop = drop;
		this.vanillaMin = vanillaMin;
		this.vanillaMax = vanillaMax;
		this.createCompat = createCompat;
		this.thermalCompat = thermalCompat;
	}

	public MaterialOutputsModel() {
		this.drop = "";
		this.vanillaMin = 1;
		this.vanillaMax = 1;
		this.createCompat = new MaterialCreateCompatModel();
		this.thermalCompat = new MaterialThermalCompatModel();
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


	public MaterialCreateCompatModel getCreateCompat() {
		return createCompat;
	}

	public MaterialThermalCompatModel getThermalCompat() {
		return thermalCompat;
	}

	public IItemProvider getDefaultItemDropAsItem() {
		return Registry.ITEM.get(new ResourceLocation(drop));
	}
}