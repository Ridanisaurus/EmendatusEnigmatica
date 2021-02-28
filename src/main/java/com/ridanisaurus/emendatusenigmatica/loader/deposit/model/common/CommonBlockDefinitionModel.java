package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Optional;

public class CommonBlockDefinitionModel {
    public static final Codec<CommonBlockDefinitionModel> CODEC = RecordCodecBuilder.create(x -> x.group(
            Codec.STRING.optionalFieldOf("block").forGetter(it -> Optional.ofNullable(it.block)),
            Codec.STRING.optionalFieldOf("tag").forGetter(it -> Optional.ofNullable(it.tag)),
            Codec.INT.fieldOf("weight").forGetter(it -> it.weight)
    ).apply(x, (s, s2, i) -> new CommonBlockDefinitionModel(s.orElse(null), s2.orElse(null), i)));
    protected final String block;
    protected final String tag;
    protected final int weight;

    public CommonBlockDefinitionModel(String block, String tag, int weight){

        this.block = block;
        this.tag = tag;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public String getBlock() {
        return block;
    }

    public String getTag() {
        return tag;
    }
}
