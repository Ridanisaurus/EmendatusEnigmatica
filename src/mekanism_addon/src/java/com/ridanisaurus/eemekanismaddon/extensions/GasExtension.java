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

package com.ridanisaurus.eemekanismaddon.extensions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.eemekanismaddon.validators.CoolantValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.NumberRangeValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.TypeValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.deprecation.DeprecatedFieldValidator;

// Names used for better accessor names of records.
public record GasExtension(
	int getBurnTime,
	long getEnergyDensity,
	double getRadioactivity,
	boolean isCoolant,
	double getThermalEnthalpy,
	double getConductivity
) {
	public static final Codec<GasExtension> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.INT.optionalFieldOf("burnTime", 0).forGetter(i -> i.getBurnTime),
			Codec.LONG.optionalFieldOf("energyDensity", 0L).forGetter(i -> i.getEnergyDensity),
			Codec.DOUBLE.optionalFieldOf("radioactivity", 0d).forGetter(i -> i.getRadioactivity),
			Codec.BOOL.optionalFieldOf("isCoolant", false).forGetter(i -> i.isCoolant),
			Codec.DOUBLE.optionalFieldOf("thermalEnthalpy", 0d).forGetter(i -> i.getThermalEnthalpy),
			Codec.DOUBLE.optionalFieldOf("conductivity", 0d).forGetter(i -> i.getConductivity)
	).apply(x, GasExtension::new));

	//TODO: Finish.
	public static final ValidationManager VALIDATION_MANAGER = ValidationManager.create()
		.addValidator("burnTime",			new NumberRangeValidator(Types.INTEGER, 0, Integer.MAX_VALUE, false))
		.addValidator("energyDensity",	new TypeValidator(Types.INTEGER, false))
		.addValidator("radioactivity",	new NumberRangeValidator(Types.FLOAT, 0, Float.MAX_VALUE, false))
		.addValidator("isCoolant",		new TypeValidator(Types.BOOLEAN, false))
		.addValidator("thermalEnthalpy",	new CoolantValidator())
		.addValidator("conductivity",		new CoolantValidator())
		.addValidator("coolantType",		new DeprecatedFieldValidator())
		.addValidator("isBurnable",		new DeprecatedFieldValidator("burnTime"))
		.addValidator("isRadioactive",	new DeprecatedFieldValidator("radioactivity"));

	public GasExtension() {
		this(0, 0, 0, false, 0, 0);
	}

	public boolean isBurnable() {
		return this.getBurnTime > 0 && this.getEnergyDensity > 0;
	}

	public boolean isRadioactive() {
		return this.getRadioactivity > 0;
	}
}