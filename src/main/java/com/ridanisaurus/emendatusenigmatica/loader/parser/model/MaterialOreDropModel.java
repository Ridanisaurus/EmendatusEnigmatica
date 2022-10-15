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

public class MaterialOreDropModel {
	public static final Codec<MaterialOreDropModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.optionalFieldOf("drop").forGetter(i -> Optional.ofNullable(i.drop)),
			Codec.INT.optionalFieldOf("vanillaMin").forGetter(i -> Optional.of(i.vanillaMin)),
			Codec.INT.optionalFieldOf("vanillaMax").forGetter(i -> Optional.of(i.vanillaMax))
	).apply(x, (drop, vanillaMin, vanillaMax) -> new MaterialOreDropModel(
			drop.orElse(""),
			vanillaMin.orElse(1),
			vanillaMax.orElse(1)
	)));

	private final String drop;
	private final int vanillaMin;
	private final int vanillaMax;

	public MaterialOreDropModel(String drop, int vanillaMin, int vanillaMax) {
		this.drop = drop;
		this.vanillaMin = vanillaMin;
		this.vanillaMax = vanillaMax;
	}

	public MaterialOreDropModel() {
		this.drop = "";
		this.vanillaMin = 1;
		this.vanillaMax = 1;
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

	public IItemProvider getDefaultItemDropAsItem() {
		return Registry.ITEM.get(new ResourceLocation(drop));
	}
}