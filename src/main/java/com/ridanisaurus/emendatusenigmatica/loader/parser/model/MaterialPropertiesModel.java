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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.Validator;

import java.nio.file.Path;
import java.util.*;
import java.util.function.BiFunction;

import static com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica.LOGGER;
import static com.ridanisaurus.emendatusenigmatica.loader.Validator.log;

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

	private final String materialType;
	private final int harvestLevel;
	private final boolean hasParticles;
	private final int blockRecipeType;
	private final int gemTexture;
	private final boolean hasOxidization;
	private final boolean isEmissive;
	private final boolean isBurnable;
	private final int burnTime;

	/**
	 * Holds verifying functions for each field.
	 * Function returns true if verification was successful, false otherwise to stop registration of the json.
	 * Adding suffix _rg will request the original object instead of just the value of the field.
	 */
	public static final Map<String, BiFunction<JsonElement, Path, Boolean>> verifiers = new HashMap<>();
	static {
		verifiers.put("materialType", new Validator("materialType").getRequiredAcceptsOnlyValidation(List.of("metal", "gem", "alloy")));
		verifiers.put("harvestLevel", new Validator("harvestLevel").getRange(0, 4));
		verifiers.put("hasParticles", Validator.ALL);
		verifiers.put("hasOxidation", Validator.ALL);
		verifiers.put("isEmissive", Validator.ALL);
		verifiers.put("isBurnable", Validator.ALL);
		Validator burnTimeValidator = new Validator("burnTime");
		verifiers.put("burnTime_rg", (element_rg, path) -> {
			if (!burnTimeValidator.assertObject(element_rg, path)) return false;

			JsonObject element = element_rg.getAsJsonObject();
			JsonElement isBurnable = element.get("isBurnable");
			JsonElement burnTime = element.get("burnTime");

			if (Objects.isNull(isBurnable)) {
				if (log && Objects.nonNull(burnTime)) LOGGER.warn("burnTime should not be present when isBurnable is not present in file \"%s\".".formatted(Validator.obfuscatePath(path)));
				return true;
			}

			try {
				boolean bol = isBurnable.getAsBoolean();
				if (log && bol && Objects.isNull(burnTime)) {
					LOGGER.warn("burnTime should be specified if isBurnable is true in file \"%s\".".formatted(Validator.obfuscatePath(path)));
				} else if (log && !bol && Objects.nonNull(burnTime)) {
					LOGGER.warn("burnTime should not be present when isBurnable is false in file \"%s\".".formatted(Validator.obfuscatePath(path)));
				}
				return true;
			} catch (ClassCastException e) {
				if (log) LOGGER.error("isBurnable is not boolean! Can't verify value of burnTime in file \"%s\".".formatted(Validator.obfuscatePath(path)));
			}
			return false;
		});

		//TODO. Finish this from here. Left: BlockRecipeType and GemTexture (requiring rework of everything above)
	}

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