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
