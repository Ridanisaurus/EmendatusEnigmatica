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
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.TypeValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.material.BurnTimeValidator;
import com.ridanisaurus.eemekanismaddon.validators.gas.CoolantTypeValidator;
import com.ridanisaurus.eemekanismaddon.validators.gas.CoolantValidator;
import com.ridanisaurus.eemekanismaddon.validators.gas.RadioactivityValidator;

import java.util.*;

// Names used for better accessor names of records.
public record GasExtension(
	boolean isBurnable,
	int getBurnTime,
	long getEnergyDensity,
	boolean isRadioactive,
	double getRadioactivity,
	boolean isCoolant,
	String getCoolantType,
	double getThermalEnthalpy,
	double getConductivity
) {
	public static final Codec<GasExtension> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.BOOL.optionalFieldOf("isBurnable").forGetter(i -> Optional.of(i.isBurnable)),
			Codec.INT.optionalFieldOf("burnTime").forGetter(i -> Optional.of(i.getBurnTime)),
			Codec.LONG.optionalFieldOf("energyDensity").forGetter(i -> Optional.of(i.getEnergyDensity)),
			Codec.BOOL.optionalFieldOf("isRadioactive").forGetter(i -> Optional.of(i.isRadioactive)),
			Codec.DOUBLE.optionalFieldOf("radioactivity").forGetter(i -> Optional.of(i.getRadioactivity)),
			Codec.BOOL.optionalFieldOf("isCoolant").forGetter(i -> Optional.of(i.isCoolant)),
			Codec.STRING.optionalFieldOf("coolantType").forGetter(i -> Optional.of(i.getCoolantType)),
			Codec.DOUBLE.optionalFieldOf("thermalEnthalpy").forGetter(i -> Optional.of(i.getThermalEnthalpy)),
			Codec.DOUBLE.optionalFieldOf("conductivity").forGetter(i -> Optional.of(i.getConductivity))
	).apply(x, (isBurnable, burnTime, energyDensity, isRadioactive, radioactivity, isCoolant, coolantType, thermalEnthalpy, conductivity) -> new GasExtension(
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

	public static final ValidationManager VALIDATION_MANAGER = ValidationManager.create()
		.addValidator("isBurnable",		new TypeValidator(Types.BOOLEAN, false))
		.addValidator("isRadioactive",	new TypeValidator(Types.BOOLEAN, false))
		.addValidator("isCoolant",		new TypeValidator(Types.BOOLEAN, false))
		.addValidator("energyDensity",	new TypeValidator(Types.INTEGER, false))
		.addValidator("radioactivity",	new RadioactivityValidator())
		.addValidator("coolantType",		new CoolantTypeValidator())
		.addValidator("thermalEnthalpy",	new CoolantValidator())
		.addValidator("conductivity",		new CoolantValidator())
		.addValidator("burnTime",			new BurnTimeValidator());

	public GasExtension() {
		this(false, 0, 0, false, 0, false, "cooled", 0, 0);
	}
}