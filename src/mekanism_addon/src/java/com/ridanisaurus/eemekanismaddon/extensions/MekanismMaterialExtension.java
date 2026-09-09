/*
 * MIT License
 *
 * Copyright (c) 2026. Ridanisaurus
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
import com.ridanisaurus.eemekanismaddon.validators.ChemicalColorValidator;
import com.ridanisaurus.eemekanismaddon.validators.CoolantColorValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.deprecation.DeprecatedFieldValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.material.ProcessedTypesContainValidator;

import java.util.Optional;

public class MekanismMaterialExtension  {
    public static final Codec<MekanismMaterialExtension> CODEC = RecordCodecBuilder.create(x -> x.group(
        ColorExtension.CODEC.optionalFieldOf("colors").forGetter(i -> Optional.of(i.color)),
        GasExtension.CODEC.optionalFieldOf("gas").forGetter(i -> Optional.of(i.gas))
    ).apply(x, (gasColor, gas) -> new MekanismMaterialExtension(
        gasColor.orElse(new ColorExtension(null, null)),
        gas.orElse(new GasExtension())
    )));

    public static final ValidationManager VALIDATION_MANAGER = ValidationManager.create()
        .addValidator("gas", new ProcessedTypesContainValidator("gas", GasExtension.VALIDATION_MANAGER.getAsValidator(false)));

    private final ColorExtension color;
    private final GasExtension gas;

    public MekanismMaterialExtension(ColorExtension color, GasExtension gas) {
        this.gas = gas;
        this.color = color;
    }

    public ColorExtension getColorData() {
        return this.color;
    }

    public GasExtension getGasData() {
        return this.gas;
    }
}
