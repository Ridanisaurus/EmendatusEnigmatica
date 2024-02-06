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

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.Validator;

import java.nio.file.Path;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static com.ridanisaurus.emendatusenigmatica.loader.Validator.LOGGER;
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
	public static final Map<String, BiFunction<JsonElement, Path, Boolean>> verifiers = new LinkedHashMap<>();
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
			JsonElement burnTime = element.get(burnTimeValidator.getName());

			if (Objects.isNull(isBurnable)) {
				if (log && Objects.nonNull(burnTime)) {
					LOGGER.warn(
						"\"%s\" should not be present when \"isBurnable\" is not present in file \"%s\"."
							.formatted(burnTimeValidator.getName(), Validator.obfuscatePath(path))
					);
				}
				return true;
			}

			try {
				boolean bol = isBurnable.getAsBoolean();
				if (log && bol && Objects.isNull(burnTime)) {
					LOGGER.warn("\"%s\" should be specified if \"isBurnable\" is true in file \"%s\".".formatted(burnTimeValidator.getName(), Validator.obfuscatePath(path)));
				} else if (log && !bol && Objects.nonNull(burnTime)) {
					LOGGER.warn("\"%s\" should not be present when \"isBurnable\" is false in file \"%s\".".formatted(burnTimeValidator.getName(), Validator.obfuscatePath(path)));
				}
				return true;
			} catch (ClassCastException e) {
				if (log) LOGGER.error("\"isBurnable\" is not boolean! Can't verify value of \"%s\" in file \"%s\".".formatted(Validator.obfuscatePath(path)), burnTimeValidator.getName());
			}
			return false;
		});

		Validator blockRecipeValidator = new Validator("blockRecipeType");
		List<Integer> acceptedValues = List.of(4, 9);
		verifiers.put("blockRecipeType_rg", (element, path) -> {
			if (!blockRecipeValidator.assertObject(element, path)) return false;

			JsonObject obj = element.getAsJsonObject();
			JsonElement valueJson = obj.get(blockRecipeValidator.getName());
			JsonElement parent = obj.get("TEMP");

			if (Objects.isNull(valueJson)) return true;

			if (log && !blockRecipeValidator.checkForTEMP(obj, path, false)) {
				LOGGER.error("No Parent object found while validating field \"%s\" in file \"%s\", something is not right.".formatted(blockRecipeValidator.getName(), Validator.obfuscatePath(path)));
			} else if (log && blockRecipeValidator.assertObject(parent, path)) {
				JsonElement typesElement = parent.getAsJsonObject().get("processedTypes");
				if (Objects.isNull(typesElement)) {
					LOGGER.warn("\"processedTypes\" is missing! Can't accurately verify values of \"%s\" in file \"%s\".".formatted(blockRecipeValidator.getName(), Validator.obfuscatePath(path)));
				} else if (blockRecipeValidator.assertArray(typesElement, path)) {
					JsonArray types = typesElement.getAsJsonArray();
					boolean gem = types.contains(new JsonPrimitive("gem"));
					boolean storage_block = types.contains(new JsonPrimitive("storage_block"));
					if (!gem || !storage_block) {
						LOGGER.warn(
							"\"%s\" should not be present when \"gem\" %s \"storage_block\" aren't present in the \"processedTypes\" in file \"%s\"."
								.formatted(blockRecipeValidator.getName(), gem || storage_block? "or":"and", Validator.obfuscatePath(path))
						);
					}
				}
			}

			try {
				double value = valueJson.getAsDouble();
				if (log && Math.ceil(value) > value) {
					LOGGER.warn("\"%s\" in file \"%s\" should be an integer. Floating-point values are not supported for this field.".formatted(blockRecipeValidator.getName(), Validator.obfuscatePath(path)));
				}
				int val = Double.valueOf(value).intValue();
				boolean validation = acceptedValues.contains(val);
				if (log && !validation) {
					LOGGER.error("\"%s\" in file \"%s\" contains illegal value (%s)! Accepted values are: %s"
						.formatted(blockRecipeValidator.getName(), Validator.obfuscatePath(path), val, acceptedValues.stream().map(String::valueOf).collect(Collectors.joining(", ")))
					);
				}
				return validation;
			} catch (ClassCastException e) {
				if (log) LOGGER.error("\"%s\" in file \"%s\" requires a numeric value!".formatted(blockRecipeValidator.getName(), Validator.obfuscatePath(path)));
			}

			return false;
		});

		Validator GemTextureValidator = new Validator("gemTexture");
		verifiers.put("gemTexture_rg", (element, path) -> {
			if (!GemTextureValidator.assertObject(element, path)) return false;

			JsonObject obj = element.getAsJsonObject();
			JsonElement valueJson = obj.get(GemTextureValidator.getName());
			JsonElement parent = obj.get("TEMP");

			if (Objects.isNull(valueJson)) return true;

			if (log && !GemTextureValidator.checkForTEMP(obj, path, false)) {
				LOGGER.error("No Parent object found while validating field \"%s\" in file \"%s\", something is not right.".formatted(GemTextureValidator.getName(), Validator.obfuscatePath(path)));
			} else if (log && GemTextureValidator.assertObject(parent, path)) {
				JsonElement typesElement = parent.getAsJsonObject().get("processedTypes");
				if (Objects.isNull(typesElement)) {
					LOGGER.warn("\"processedTypes\" is missing! Can't accurately verify values of \"%s\" in file \"%s\".".formatted(GemTextureValidator.getName(), Validator.obfuscatePath(path)));
				} else if (GemTextureValidator.assertArray(typesElement, path)) {
					JsonArray types = typesElement.getAsJsonArray();
					if (!types.contains(new JsonPrimitive("gem"))) {
						LOGGER.warn(
							"\"%s\" should not be present when \"gem\" isn't present in the \"processedTypes\" in file \"%s\"."
								.formatted(GemTextureValidator.getName(), Validator.obfuscatePath(path))
						);
					}
				}
				JsonElement colors = parent.getAsJsonObject().get("colors");
				if (Objects.isNull(colors) || !colors.isJsonObject()) {
					LOGGER.warn(
						"\"%s\" should not be present when \"colors\" object isn't present in file \"%s\"."
							.formatted(GemTextureValidator.getName(), Validator.obfuscatePath(path))
					);
				} else if (Objects.isNull(colors.getAsJsonObject().get("materialColor"))) {
					LOGGER.warn(
						"\"%s\" should not be present in file \"%s\", the material isn't tint-based."
							.formatted(GemTextureValidator.getName(), Validator.obfuscatePath(path))
					);
				}
			}

			return GemTextureValidator.getIntRange(0, 9).apply(valueJson, path);
		});
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