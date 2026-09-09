/*
 * MIT License
 *
 * Copyright (c) 2020-2024. Ridanisaurus
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
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.FilterMode;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.NumberRangeValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.NumberValuesValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.TypeValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.ValuesValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.deprecation.DeprecatedFieldValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.enums.PTCMode;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.material.properties.GemTextureValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.material.ProcessedTypesContainValidator;

import java.util.*;

public class PropertiesModel {
	public static final Codec<PropertiesModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.fieldOf("materialType").forGetter(i -> i.materialType),
			Codec.INT.optionalFieldOf("harvestLevel", 0).forGetter(i -> i.harvestLevel),
			Codec.INT.optionalFieldOf("blockRecipeType", 9).forGetter(i -> i.blockRecipeType),
			Codec.INT.optionalFieldOf("gemTexture", 1).forGetter(i -> i.gemTexture),
			Codec.BOOL.optionalFieldOf("hasParticles", false).forGetter(i -> i.hasParticles),
			Codec.BOOL.optionalFieldOf("hasOxidization", false).forGetter(i -> i.hasOxidization),
			Codec.BOOL.optionalFieldOf("isEmissive", false).forGetter(i -> i.isEmissive),
			Codec.INT.optionalFieldOf("burnTime", 0).forGetter(i -> i.burnTime)
	).apply(x, PropertiesModel::new));

	public static final ValidationManager VALIDATION_MANAGER = ValidationManager.create()
		//TODO: Add better Alloy Support
		.addValidator("materialType",		new ValuesValidator(List.of("metal", "gem", "alloy"), FilterMode.WHITELIST, true))
		.addValidator("harvestLevel",		new NumberRangeValidator(Types.INTEGER, 0, 4, false))
		.addValidator("hasParticles",		new TypeValidator(Types.BOOLEAN, false))
		.addValidator("hasOxidization",	new TypeValidator(Types.BOOLEAN, false))
		.addValidator("isEmissive",		new TypeValidator(Types.BOOLEAN, false))
		.addValidator("burnTime",			new NumberRangeValidator(Types.INTEGER, 0, Integer.MAX_VALUE, false))
		.addValidator("gemTexture",		new GemTextureValidator())
		.addValidator("blockRecipeType",	new ProcessedTypesContainValidator(
			List.of("gem", "storage_block"),
			new NumberValuesValidator(List.of(4, 9), FilterMode.WHITELIST, false),
			PTCMode.REQUIRED_ALL_VALUE
		))
		.addValidator("isBurnable", new DeprecatedFieldValidator("root.properties.burnTime"));

	private final String materialType;
	private final int harvestLevel;
	private final boolean hasParticles;
	private final int blockRecipeType;
	private final int gemTexture;
	private final boolean hasOxidization;
	private final boolean isEmissive;
	private final int burnTime;

	public PropertiesModel(String materialType, int harvestLevel, int blockRecipeType, int gemTexture,
						   boolean hasParticles, boolean hasOxidization, boolean isEmissive, int burnTime) {
		this.materialType = materialType;
		this.harvestLevel = harvestLevel;
		this.blockRecipeType = blockRecipeType;
		this.gemTexture = gemTexture;
		this.hasParticles = hasParticles;
		this.hasOxidization = hasOxidization;
		this.isEmissive = isEmissive;
		this.burnTime = burnTime;
	}

	public PropertiesModel() {
		this.materialType = "metal";
		this.harvestLevel = 0;
		this.blockRecipeType = 9;
		this.gemTexture = 1;
		this.hasParticles = false;
		this.hasOxidization = false;
		this.isEmissive = false;
		this.burnTime = 0;
	}

	public String getMaterialType() {
		return materialType;
	}

	public int getHarvestLevel() {
		return harvestLevel;
	}

	public int getBlockRecipeType() {
		return blockRecipeType;
	}

	public int getGemTexture() {
		return gemTexture;
	}

	public boolean hasParticles() {
		return hasParticles;
	}

	public boolean hasOxidization() {
		return hasOxidization;
	}

	public boolean isEmissive() {
		return isEmissive;
	}

	public boolean isBurnable() {
		return burnTime > 0;
	}

	public int getBurnTime() {
		return burnTime;
	}
}