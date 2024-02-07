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
import com.ridanisaurus.emendatusenigmatica.util.ColorHelper;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;

import static com.ridanisaurus.emendatusenigmatica.loader.Validator.LOGGER;
import static com.ridanisaurus.emendatusenigmatica.loader.Validator.log;

public class MaterialColorsModel {
	// TODO 3.0: Change gasColor to chemicalColor
	public static final Codec<MaterialColorsModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.optionalFieldOf("fluidColor").forGetter(i -> Optional.of(i.fluidColor)),
			Codec.STRING.optionalFieldOf("gasColor").forGetter(i -> Optional.of(i.gasColor)),
			Codec.STRING.optionalFieldOf("particlesColor").forGetter(i -> Optional.of(i.particlesColor)),
			Codec.STRING.optionalFieldOf("materialColor").forGetter(i -> Optional.of(i.materialColor)),
			Codec.STRING.optionalFieldOf("oxidizationColor").forGetter(i -> Optional.of(i.oxidizationColor))
	).apply(x, (fluidColor, gasColor, particlesColor, materialColor, oxidizationColor) -> new MaterialColorsModel(
			fluidColor.orElse(null),
			gasColor.orElse(null),
			particlesColor.orElse(null),
			materialColor.orElse(null),
			oxidizationColor.orElse(null)
	)));

	private final String fluidColor;
	private final String gasColor;
	private final String particlesColor;
	private final String materialColor;
	private final String oxidizationColor;

	/**
	 * Holds verifying functions for each field.
	 * Function returns true if verification was successful, false otherwise to stop registration of the json.
	 * Adding suffix _rg will request the original object instead of just the value of the field.
	 */
	public static Map<String, BiFunction<JsonElement, Path, Boolean>> validators = new LinkedHashMap<>();

	static {
		validators.put("fluidColor", new Validator("fluidColor").HEX_COLOR);
		validators.put("materialColor", new Validator("materialColor").HEX_COLOR);

		validators.put("gasColor", new Validator("gasColor").HEX_COLOR);
		validators.put("particlesColor", new Validator("particlesColor").HEX_COLOR);
		validators.put("oxidizationColor", new Validator("oxidizationColor").HEX_COLOR);

		Validator gasValidator = new Validator("gasColor");

		validators.put("gasColor_rg", (element, path) -> {
			if (!gasValidator.assertParentObject(element, path)) return false;
			JsonObject obj = element.getAsJsonObject();
			JsonElement valueJson = obj.get(gasValidator.getName());

			if (!log || !gasValidator.checkForTEMP(obj, path, false)) {
				if (log) LOGGER.warn("Parent data is missing while verifying \"%s\" in file \"%s\", something is not right.".formatted(gasValidator.getName(), Validator.obfuscatePath(path)));
			} else {
				JsonElement requiredJson = obj.get("TEMP").getAsJsonObject().get("processedTypes");
				if (Objects.isNull(requiredJson)) {
                    LOGGER.warn("\"processedTypes\" are missing from file \"%s\". Can't accurately verify values of \"%s\"."
                            .formatted(Validator.obfuscatePath(path), gasValidator.getName())
                    );
                } else if (!requiredJson.isJsonArray()) {
                    LOGGER.warn("Expected \"processedTypes\" to be an array! Can't accurately verify values of \"%s\" in file \"%s\".".formatted(gasValidator.getName(), Validator.obfuscatePath(path)));
				}

				JsonArray types = requiredJson.getAsJsonArray();
				boolean infuse = types.contains(new JsonPrimitive("infuse_type"));
				boolean slurry = types.contains(new JsonPrimitive("slurry"));
				boolean gas	   = types.contains(new JsonPrimitive("gas"));
				if (!(infuse || slurry || gas) && Objects.nonNull(valueJson)) {
					LOGGER.warn("\"%s\" should not be present when \"infuse_type\", \"slurry\" or \"gas\" are not present in \"processedTypes\" in file \"%s\"."
						.formatted(gasValidator.getName(), Validator.obfuscatePath(path))
					);
				} else if (Objects.isNull(valueJson)) {
					LOGGER.warn("\"%s\" should be set when \"infuse_type\", \"slurry\" or \"gas\" are present in \"processedTypes\" in file \"%s\"."
						.formatted(gasValidator.getName(), Validator.obfuscatePath(path))
					);
				}
			}

			return gasValidator.HEX_COLOR.apply(obj.get(gasValidator.getName()), path);
		});

		PropertyDispatch.QuadFunction<Validator, String, JsonElement, Path, Boolean> validationFunction = (validator, fieldName, element, path) -> {
			if (!validator.assertParentObject(element, path)) return false;
			JsonObject obj = element.getAsJsonObject();
			JsonElement valueJson = obj.get(validator.getName());

			if (!log || !validator.checkForTEMP(obj, path, false)) {
				if (log) LOGGER.warn("Parent data is missing while verifying \"%s\" in file \"%s\", something is not right.".formatted(validator.getName(), Validator.obfuscatePath(path)));
			} else {
				JsonElement requiredJson = obj.get("TEMP").getAsJsonObject().get("properties");
				if (Objects.nonNull(requiredJson)) {
					if (!requiredJson.isJsonObject()) {
						LOGGER.warn("Expected \"properties\" to be an object! Can't accurately verify values of \"%s\" in file \"%s\"."
								.formatted(validator.getName(), Validator.obfuscatePath(path))
						);
					} else {
						JsonElement requiredValue = requiredJson.getAsJsonObject().get(fieldName);
						boolean required = false;
						if (Objects.nonNull(requiredValue)) {
							try {
								required = requiredValue.getAsBoolean();
							} catch (Exception e) {
								LOGGER.error("\"%s\" in Properties is not a boolean! Can't accurately verify values of \"%s\" in file \"%s\"."
										.formatted(fieldName, validator.getName(), Validator.obfuscatePath(path))
								);
							}
						}
						if (Objects.nonNull(valueJson) && !required) {
							LOGGER.warn("\"%s\" should not be present when \"%s\" is set to false in file \"%s\"."
									.formatted(validator.getName(), fieldName, Validator.obfuscatePath(path))
							);
						} else if (Objects.isNull(valueJson) && required) {
							LOGGER.warn("\"%s\" should be set when \"%s\" is set to true in file \"%s\"."
									.formatted(validator.getName(), fieldName, Validator.obfuscatePath(path))
							);
						}
					}
				}
			}

			return validator.HEX_COLOR.apply(obj.get(gasValidator.getName()), path);
		};

		validators.put("particlesColor_rg", (element, path) -> validationFunction.apply(new Validator("particlesColor"), "hasParticles", element, path));
		validators.put("oxidizationColor_rg", (element, path) -> validationFunction.apply(new Validator("oxidizationColor"), "hasOxidization", element, path));
	}

	public MaterialColorsModel(@Nullable String fluidColor, @Nullable String gasColor, @Nullable String particlesColor, @Nullable String materialColor, @Nullable String oxidizationColor) {
		this.fluidColor = fluidColor;
		this.gasColor = gasColor;
		this.particlesColor = particlesColor;
		this.materialColor = materialColor;
		this.oxidizationColor = oxidizationColor;
	}

	public MaterialColorsModel() {
		this.fluidColor = null;
		this.gasColor = null;
		this.particlesColor = null;
		this.materialColor = null;
		this.oxidizationColor = null;
	}

	public int getMaterialColor() {
		return materialColor != null ? ColorHelper.HEXtoDEC(materialColor) : -1;
	}

	public int getHighlightColor(int factor) {
		return materialColor != null ? ColorHelper.HEXtoDEC(ColorHelper.hueShift(materialColor, factor, true)) : -1;
	}

	public int getShadowColor(int factor) {
		return materialColor != null ? ColorHelper.HEXtoDEC(ColorHelper.hueShift(materialColor, factor, false)) : -1;
	}

	public int getOxidizationColor() {
		return oxidizationColor != null ? ColorHelper.HEXtoDEC(oxidizationColor) : -1;
	}

	public int getFluidColor() {
		return fluidColor != null ? ColorHelper.HEXtoDEC(fluidColor) : -1;
	}

	public int getGasColor() {
		return gasColor != null ? ColorHelper.HEXtoDEC(gasColor) : -1;
	}

	public int getParticlesColor() {
		return particlesColor != null ? ColorHelper.HEXtoDEC(particlesColor) : -1;
	}
}