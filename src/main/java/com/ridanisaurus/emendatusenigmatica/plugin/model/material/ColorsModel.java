/*
 * MIT License
 *
 * Copyright (c) 2020-2026. Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.plugin.model.material;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.ColorValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.material.colors.OxidizationColorValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.material.colors.ParticlesColorValidator;
import com.ridanisaurus.emendatusenigmatica.util.ColorHelper;
import org.jetbrains.annotations.Nullable;

public class ColorsModel {
	public static final Codec<ColorsModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.optionalFieldOf("fluidColor", "").forGetter(i -> i.fluidColor),
			Codec.STRING.optionalFieldOf("particlesColor", "").forGetter(i -> i.particlesColor),
			Codec.STRING.optionalFieldOf("materialColor", "").forGetter(i -> i.materialColor),
			Codec.STRING.optionalFieldOf("oxidizationColor", "").forGetter(i -> i.oxidizationColor)
	).apply(x, ColorsModel::new));

	public static final ValidationManager VALIDATION_MANAGER = ValidationManager.create()
		.addValidator("fluidColor",		new ColorValidator(false))
		.addValidator("materialColor",	new ColorValidator(false))
		.addValidator("particlesColor",	new ParticlesColorValidator())
		.addValidator("oxidizationColor",	new OxidizationColorValidator());

	private final String fluidColor;
	private final String particlesColor;
	private final String materialColor;
	private final String oxidizationColor;

	public ColorsModel(String fluidColor, String particlesColor, String materialColor, String oxidizationColor) {
		this.fluidColor = fluidColor;
		this.particlesColor = particlesColor;
		this.materialColor = materialColor;
		this.oxidizationColor = oxidizationColor;
	}

	public ColorsModel() {
		this.fluidColor = "";
		this.particlesColor = "";
		this.materialColor = "";
		this.oxidizationColor = "";
	}

	public int getMaterialColor() {
		return hasMaterialColor() ? ColorHelper.HEXtoDEC(materialColor) : -1;
	}

	public int getHighlightColor(int factor) {
		return hasMaterialColor() ? ColorHelper.HEXtoDEC(ColorHelper.hueShift(materialColor, factor, true)) : -1;
	}

	public int getShadowColor(int factor) {
		return hasMaterialColor() ? ColorHelper.HEXtoDEC(ColorHelper.hueShift(materialColor, factor, false)) : -1;
	}

	public int getOxidizationColor() {
		return hasOxidizationColor() ? ColorHelper.HEXtoDEC(oxidizationColor) : -1;
	}

	public int getFluidColor() {
		return hasFluidColor() ? ColorHelper.HEXtoDEC(fluidColor) : -1;
	}

	public int getParticlesColor() {
		return hasParticlesColor() ? ColorHelper.HEXtoDEC(particlesColor) : -1;
	}

	public boolean hasMaterialColor() {
		return !materialColor.isBlank();
	}

	public boolean hasOxidizationColor() {
		return !oxidizationColor.isBlank();
	}

	public boolean hasFluidColor() {
		return !fluidColor.isBlank();
	}

	public boolean hasParticlesColor() {
		return !particlesColor.isBlank();
	}
}