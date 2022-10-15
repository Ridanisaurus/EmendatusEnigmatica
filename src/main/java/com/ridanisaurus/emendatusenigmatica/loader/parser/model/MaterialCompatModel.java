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
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.compat.MaterialCreateModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.compat.MaterialThermalModel;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import java.util.Optional;

public class MaterialCompatModel {
	public static final Codec<MaterialCompatModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			MaterialCreateModel.CODEC.optionalFieldOf("create").forGetter(i -> Optional.of(i.create)),
			MaterialThermalModel.CODEC.optionalFieldOf("thermal").forGetter(i -> Optional.of(i.thermal))
	).apply(x, (create, thermal) -> new MaterialCompatModel(
			create.orElse(new MaterialCreateModel()),
			thermal.orElse(new MaterialThermalModel())
	)));

	private final MaterialCreateModel create;
	private final MaterialThermalModel thermal;

	public MaterialCompatModel(MaterialCreateModel create, MaterialThermalModel thermal) {
		this.create = create;
		this.thermal = thermal;
	}

	public MaterialCompatModel() {
		this.create = new MaterialCreateModel();
		this.thermal = new MaterialThermalModel();
	}

	public MaterialCreateModel getCreateCompat() {
		return create;
	}

	public MaterialThermalModel getThermalCompat() {
		return thermal;
	}
}