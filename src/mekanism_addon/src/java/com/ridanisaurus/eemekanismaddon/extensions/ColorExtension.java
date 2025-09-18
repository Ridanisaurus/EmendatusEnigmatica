package com.ridanisaurus.eemekanismaddon.extensions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Optional;

public record ColorExtension(String color) {
    public static final Codec<ColorExtension> CODEC = RecordCodecBuilder.create(x -> x.group(
        Codec.STRING.optionalFieldOf("chemicalColor").forGetter(i -> Optional.of(i.color))
    ).apply(x, (color) -> new ColorExtension(
        color.orElse(null)
    )));
}
