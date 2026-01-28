package com.ridanisaurus.eemekanismaddon.extensions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Optional;

public class MekanismMaterialExtension  {
    public static final Codec<MekanismMaterialExtension> CODEC = RecordCodecBuilder.create(x -> x.group(
        ColorExtension.CODEC.optionalFieldOf("chemicalColor").forGetter(i -> Optional.of(i.color)),
        GasExtension.CODEC.optionalFieldOf("gas").forGetter(i -> Optional.of(i.gas))
    ).apply(x, (gasColor, gas) -> new MekanismMaterialExtension(
        gasColor.orElse(new ColorExtension(null, null)),
        gas.orElse(new GasExtension())
    )));

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
