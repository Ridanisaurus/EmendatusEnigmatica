package com.ridanisaurus.eemekanismaddon.extensions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.plugin.extensions.MaterialExtensionData;
import com.ridanisaurus.emendatusenigmatica.util.ColorHelper;

import java.util.Optional;

public class MekanismMaterialExtension extends MaterialExtensionData {
    public static final Codec<MekanismMaterialExtension> CODEC = RecordCodecBuilder.create(x -> x.group(
        ColorWrapper.CODEC.optionalFieldOf("color").forGetter(i -> Optional.of(new ColorWrapper(i.chemicalColor)))
    ).apply(x, (gasColor) -> new MekanismMaterialExtension(
        gasColor.orElse(new ColorWrapper(null)).color
    )));

    private final String chemicalColor;

    public MekanismMaterialExtension(String chemicalColor) {
        this.chemicalColor = chemicalColor;
    }

    public int getChemicalColor() {
        return chemicalColor != null ? ColorHelper.HEXtoDEC(chemicalColor) : -1;
    }

    private record ColorWrapper(String color) {
        private static final Codec<ColorWrapper> CODEC = RecordCodecBuilder.create(x -> x.group(
            Codec.STRING.optionalFieldOf("chemicalColor").forGetter(i -> Optional.of(i.color))
        ).apply(x, (color) -> new ColorWrapper(
            color.orElse(null)
        )));
    }
}
