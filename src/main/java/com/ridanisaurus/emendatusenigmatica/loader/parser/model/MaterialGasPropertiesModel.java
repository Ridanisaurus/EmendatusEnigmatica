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
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.Validator;
import net.minecraft.data.models.blockstates.PropertyDispatch;

import java.nio.file.Path;
import java.util.*;
import java.util.function.BiFunction;

import static com.ridanisaurus.emendatusenigmatica.loader.Validator.LOGGER;

public class MaterialGasPropertiesModel {
	public static final Codec<MaterialGasPropertiesModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.BOOL.optionalFieldOf("isBurnable").forGetter(i -> Optional.of(i.isBurnable)),
			Codec.INT.optionalFieldOf("burnTime").forGetter(i -> Optional.of(i.burnTime)),
			Codec.LONG.optionalFieldOf("energyDensity").forGetter(i -> Optional.of(i.energyDensity)),
			Codec.BOOL.optionalFieldOf("isRadioactive").forGetter(i -> Optional.of(i.isRadioactive)),
			Codec.DOUBLE.optionalFieldOf("radioactivity").forGetter(i -> Optional.of(i.radioactivity)),
			Codec.BOOL.optionalFieldOf("isCoolant").forGetter(i -> Optional.of(i.isCoolant)),
			Codec.STRING.optionalFieldOf("coolantType").forGetter(i -> Optional.of(i.coolantType)),
			Codec.DOUBLE.optionalFieldOf("thermalEnthalpy").forGetter(i -> Optional.of(i.thermalEnthalpy)),
			Codec.DOUBLE.optionalFieldOf("conductivity").forGetter(i -> Optional.of(i.conductivity))
	).apply(x, (isBurnable, burnTime, energyDensity, isRadioactive, radioactivity, isCoolant, coolantType, thermalEnthalpy, conductivity) -> new MaterialGasPropertiesModel(
			isBurnable.orElse(false),
			burnTime.orElse(0),
			energyDensity.orElse(0L),
			isRadioactive.orElse(false),
			radioactivity.orElse(0.0D),
			isCoolant.orElse(false),
			coolantType.orElse("cooled"),
			thermalEnthalpy.orElse(0.0D),
			conductivity.orElse(0.0D)
	)));

	private final boolean isBurnable;
	private final int burnTime;
	private final long energyDensity;
	private final boolean isRadioactive;
	private final double radioactivity;
	private final boolean isCoolant;
	private final String coolantType;
	private final double thermalEnthalpy;
	private final double conductivity;

	/**
	 * Holds verifying functions for each field.
	 * Function returns true if verification was successful, false otherwise to stop registration of the json.
	 * Adding suffix _rg will request the original object instead of just the value of the field.
	 */
	public static Map<String, BiFunction<JsonElement, Path, Boolean>> validators = new LinkedHashMap<>();

	static {
		validators.put("isBurnable", new Validator("isBurnable").REQUIRES_BOOLEAN);
		validators.put("energyDensity", new Validator("energyDensity").REQUIRES_INT);
		validators.put("isRadioactive", new Validator("isRadioactive").REQUIRES_BOOLEAN);
		validators.put("isCoolant", new Validator("isCoolant").REQUIRES_BOOLEAN);

		PropertyDispatch.QuadFunction<Pair<Validator, BiFunction<JsonElement, Path, Boolean>>, String, JsonElement, Path, Boolean> isFieldSetValidator = (validatorDetails, fieldName, element_rg, path) -> {
			Validator validator = validatorDetails.getFirst();
			if (!validator.assertParentObject(element_rg, path)) return false;

			JsonObject obj = element_rg.getAsJsonObject();
			JsonElement jsonBoolean = obj.get(fieldName);
			JsonElement jsonValue = obj.get(validator.getName());

			if (LOGGER.shouldLog) {
				if (Objects.isNull(jsonBoolean)) {
					if (Objects.nonNull(jsonValue)) {
						LOGGER.warn(
							"\"%s\" should not be present when \"%s\" is not present in file \"%s\"."
								.formatted(validator.getName(), fieldName, Validator.obfuscatePath(path))
						);
					}
				} else {
					try {
						boolean bol = jsonBoolean.getAsBoolean();
						if (bol && Objects.isNull(jsonValue)) {
							LOGGER.warn("\"%s\" should be specified if \"%s\" is true in file \"%s\".".formatted(validator.getName(), fieldName, Validator.obfuscatePath(path)));
						} else if (!bol && Objects.nonNull(jsonValue)) {
							LOGGER.warn("\"%s\" should not be present when \"%s\" is false in file \"%s\".".formatted(validator.getName(), fieldName, Validator.obfuscatePath(path)));
						}
					} catch (Exception e) {
						LOGGER.error("\"%s\" is not boolean! Can't accurately verify value of \"%s\" in file \"%s\".".formatted(fieldName, validator.getName(), Validator.obfuscatePath(path)));
					}
				}
			}

			return validatorDetails.getSecond().apply(jsonValue, path);
		};

		Validator burnTimeValidator = new Validator("burnTime");
		validators.put(burnTimeValidator.getName() + "_rg", (element_rg, path) ->
			isFieldSetValidator.apply(new Pair<>(burnTimeValidator, burnTimeValidator.REQUIRES_INT), "isBurnable", element_rg, path)
		);

		Validator radioactivityValidator = new Validator("radioactivity");
		validators.put(radioactivityValidator.getName() + "_rg", (element_rg, path) ->
			isFieldSetValidator.apply(new Pair<>(radioactivityValidator, radioactivityValidator.REQUIRES_INT), "isRadioactive", element_rg, path)
		);

		Validator coolantTypeValidator = new Validator("coolantType");
		validators.put(coolantTypeValidator.getName() + "_rg", (element_rg, path) ->
			isFieldSetValidator.apply(new Pair<>(coolantTypeValidator, coolantTypeValidator.getAcceptsOnlyValidation(List.of("cooled", "heated"), false)), "isCoolant", element_rg, path)
		);

		Validator thermalValidator = new Validator("thermalEnthalpy");
		validators.put(thermalValidator.getName() + "_rg", (element, path) ->
			isFieldSetValidator.apply(new Pair<>(thermalValidator, thermalValidator.REQUIRES_FLOAT), "isCoolant", element, path)
		);

		Validator conductivityValidator = new Validator("conductivity");
		validators.put(conductivityValidator.getName() + "_rg", (element, path) ->
			isFieldSetValidator.apply(new Pair<>(conductivityValidator, conductivityValidator.REQUIRES_FLOAT), "isCoolant", element, path)
		);
	}

	public MaterialGasPropertiesModel(boolean isBurnable, int burnTime, long energyDensity, boolean isRadioactive, double radioactivity,
	                                  boolean isCoolant, String coolantType, double thermalEnthalpy, double conductivity) {
		this.isBurnable = isBurnable;
		this.burnTime = burnTime;
		this.energyDensity = energyDensity;
		this.isRadioactive = isRadioactive;
		this.radioactivity = radioactivity;
		this.isCoolant = isCoolant;
		this.coolantType = coolantType;
		this.thermalEnthalpy = thermalEnthalpy;
		this.conductivity = conductivity;
	}

	public MaterialGasPropertiesModel() {
		this.isBurnable = false;
		this.burnTime = 0;
		this.energyDensity = 0L;
		this.isRadioactive = false;
		this.radioactivity = 0.0D;
		this.isCoolant = false;
		this.coolantType = "cooled";
		this.thermalEnthalpy = 0.0D;
		this.conductivity = 0.0D;
	}

	public boolean isBurnable() {
		return isBurnable;
	}

	public int getBurnTime() {
		return burnTime;
	}

	public long getEnergyDensity() {
		return energyDensity;
	}

	public boolean isRadioactive() {
		return isRadioactive;
	}

	public double getRadioactivity() {
		return radioactivity;
	}

	public boolean isCoolant() {
		return isCoolant;
	}

	public String getCoolantType() {
		return coolantType;
	}

	public double getThermalEnthalpy() {
		return thermalEnthalpy;
	}

	public double getConductivity() {
		return conductivity;
	}
}