package com.ridanisaurus.eemekanismaddon.extensions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.plugin.extensions.MaterialExtensionData;
import com.ridanisaurus.emendatusenigmatica.util.ColorHelper;

import java.util.Optional;

public class MekanismMaterialExtension extends MaterialExtensionData {
    public static final Codec<MekanismMaterialExtension> CODEC = RecordCodecBuilder.create(x -> x.group(
        ColorExtension.CODEC.optionalFieldOf("color").forGetter(i -> Optional.of(new ColorExtension(i.chemicalColor))),
        GasExtension.CODEC.optionalFieldOf("gas").forGetter(i -> Optional.of(i.gas))
    ).apply(x, (gasColor, gas) -> new MekanismMaterialExtension(
        gasColor.orElse(new ColorExtension(null)).color(),
        gas.orElse(new GasExtension())
    )));

    private final String chemicalColor;
    private final GasExtension gas;

    public MekanismMaterialExtension(String chemicalColor, GasExtension gas) {
        this.chemicalColor = chemicalColor;
        this.gas = gas;
    }

    public int getChemicalColor() {
        return chemicalColor != null ? ColorHelper.HEXtoDEC(chemicalColor) : -1;
    }

    public GasExtension getGasData() {
        return this.gas;
    }
}
