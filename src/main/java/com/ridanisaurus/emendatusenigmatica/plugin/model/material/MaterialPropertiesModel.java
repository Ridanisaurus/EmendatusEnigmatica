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
import com.ridanisaurus.emendatusenigmatica.plugin.validators.enums.PTCMode;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.material.BurnTimeValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.material.properties.GemTextureValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.material.ProcessedTypesContainValidator;

import java.util.*;

public class MaterialPropertiesModel {
	public static final Codec<MaterialPropertiesModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.fieldOf("materialType").forGetter(i -> i.materialType),
			Codec.INT.optionalFieldOf("harvestLevel").forGetter(i -> Optional.of(i.harvestLevel)),
			Codec.INT.optionalFieldOf("blockRecipeType").forGetter(i -> Optional.of(i.blockRecipeType)),
			Codec.INT.optionalFieldOf("gemTexture").forGetter(i -> Optional.of(i.gemTexture)),
			Codec.BOOL.optionalFieldOf("hasParticles").forGetter(i -> Optional.of(i.hasParticles)),
			Codec.BOOL.optionalFieldOf("hasOxidization").forGetter(i -> Optional.of(i.hasOxidization)),
			Codec.BOOL.optionalFieldOf("isEmissive").forGetter(i -> Optional.of(i.isEmissive)),
			Codec.BOOL.optionalFieldOf("isBurnable").forGetter(i -> Optional.of(i.isBurnable)),
			Codec.INT.optionalFieldOf("burnTime").forGetter(i -> Optional.of(i.burnTime))
	).apply(x, (materialType, harvestLevel, blockRecipeType, gemTexture, hasParticles, hasOxidization, isEmissive, isBurnable, burnTime) -> new MaterialPropertiesModel(
			materialType,
			harvestLevel.orElse(0),
			blockRecipeType.orElse(9),
			gemTexture.orElse(1),
			hasParticles.orElse(false),
			hasOxidization.orElse(false),
			isEmissive.orElse(false),
			isBurnable.orElse(false),
			burnTime.orElse(0)
	)));

	public static final ValidationManager VALIDATION_MANAGER = ValidationManager.create()
		.addValidator("materialType",		new ValuesValidator(List.of("metal", "gem", "alloy"), FilterMode.WHITELIST, true))
		.addValidator("harvestLevel",		new NumberRangeValidator(Types.INTEGER, 0, 4, false))
		.addValidator("hasParticles",		new TypeValidator(Types.BOOLEAN, false))
		.addValidator("hasOxidization",	new TypeValidator(Types.BOOLEAN, false))
		.addValidator("isEmissive",		new TypeValidator(Types.BOOLEAN, false))
		.addValidator("isBurnable",		new TypeValidator(Types.BOOLEAN, false))
		.addValidator("burnTime",			new BurnTimeValidator())
		.addValidator("gemTexture",		new GemTextureValidator())
		.addValidator("blockRecipeType",	new ProcessedTypesContainValidator(
			List.of("gem", "storage_block"),
			new NumberValuesValidator(List.of(4, 9), FilterMode.WHITELIST, false),
			PTCMode.REQUIRED_ALL_VALUE
		));

	private final String materialType;
	private final int harvestLevel;
	private final boolean hasParticles;
	private final int blockRecipeType;
	private final int gemTexture;
	private final boolean hasOxidization;
	private final boolean isEmissive;
	private final boolean isBurnable;
	private final int burnTime;

	public MaterialPropertiesModel(String materialType, int harvestLevel, int blockRecipeType, int gemTexture,
	                               boolean hasParticles, boolean hasOxidization, boolean isEmissive, boolean isBurnable, int burnTime) {
		this.materialType = materialType;
		this.harvestLevel = harvestLevel;
		this.blockRecipeType = blockRecipeType;
		this.gemTexture = gemTexture;
		this.hasParticles = hasParticles;
		this.hasOxidization = hasOxidization;
		this.isEmissive = isEmissive;
		this.isBurnable = isBurnable;
		this.burnTime = burnTime;
	}

	public MaterialPropertiesModel() {
		this.materialType = "metal";
		this.harvestLevel = 0;
		this.blockRecipeType = 9;
		this.gemTexture = 1;
		this.hasParticles = false;
		this.hasOxidization = false;
		this.isEmissive = false;
		this.isBurnable = false;
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
		return isBurnable;
	}

	public int getBurnTime() {
		return burnTime;
	}
}