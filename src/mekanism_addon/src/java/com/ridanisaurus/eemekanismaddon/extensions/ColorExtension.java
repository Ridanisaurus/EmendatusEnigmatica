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
import com.ridanisaurus.emendatusenigmatica.util.ColorHelper;

import java.util.Optional;

public record ColorExtension(String chemicalColor, String heatedCoolantColor) {
    public static final Codec<ColorExtension> CODEC = RecordCodecBuilder.create(x -> x.group(
        Codec.STRING.optionalFieldOf("chemicalColor").forGetter(i -> Optional.of(i.chemicalColor)),
        Codec.STRING.optionalFieldOf("hotCoolantColor").forGetter(i -> Optional.of(i.heatedCoolantColor))
    ).apply(x, (chemical, coolant) -> new ColorExtension(
        chemical.orElse(null),
        coolant.orElse(null)
    )));

    public int getChemicalColor() {
        return chemicalColor != null ? ColorHelper.HEXtoDEC(chemicalColor): -1;
    }

    public int getHotCoolantColor() {
        return heatedCoolantColor != null? ColorHelper.HEXtoDEC(heatedCoolantColor): -1;
    }
}
