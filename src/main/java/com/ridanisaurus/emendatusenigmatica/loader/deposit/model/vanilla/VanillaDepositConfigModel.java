package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.vanilla;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class VanillaDepositConfigModel {
    public static final Codec<VanillaDepositConfigModel> CODEC = RecordCodecBuilder.create(x -> x.group(
            Codec.STRING.fieldOf("block").forGetter(it -> it.block),
            Codec.INT.fieldOf("chance").forGetter(it -> it.chance),
            Codec.INT.fieldOf("size").forGetter(it -> it.size),
            Codec.INT.fieldOf("minYLevel").forGetter(it -> it.minYLevel),
            Codec.INT.fieldOf("maxYLevel").forGetter(it -> it.maxYLevel)
    ).apply(x, VanillaDepositConfigModel::new));

    private final String block;
    private final int chance;
    private final int size;
    private final int minYLevel;
    private final int maxYLevel;

    public VanillaDepositConfigModel(String block, int chance, int size, int minYLevel, int maxYLevel) {

        this.block = block;
        this.chance = chance;
        this.size = size;
        this.minYLevel = minYLevel;
        this.maxYLevel = maxYLevel;
    }
}
