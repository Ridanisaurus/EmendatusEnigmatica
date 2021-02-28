package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonDepositModelBase;

import java.util.List;

public class CustomDepositModel extends CommonDepositModelBase {
    public static final Codec<CustomDepositModel> CODEC = RecordCodecBuilder.create(x -> x.group(
            Codec.STRING.fieldOf("type").forGetter(it -> it.type),
            Codec.list(Codec.STRING).fieldOf("dimensions").forGetter(it -> it.dimensions),
            CustomDepositConfigModel.CODEC.fieldOf("config").forGetter(it -> it.config)
    ).apply(x, CustomDepositModel::new));

    private final CustomDepositConfigModel config;

    public CustomDepositModel(String type, List<String> dimensions, CustomDepositConfigModel config) {
        super(type, dimensions);
        this.config = config;
    }
}
