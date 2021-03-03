package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.geode;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonDepositModelBase;

import java.util.List;

public class GeodeDepositModel extends CommonDepositModelBase {
    public static final Codec<GeodeDepositModel> CODEC = RecordCodecBuilder.create(x -> x.group(
            Codec.STRING.fieldOf("type").forGetter(it -> it.type),
            Codec.list(Codec.STRING).fieldOf("dimensions").forGetter(it -> it.dimensions),
            Codec.STRING.fieldOf("registryName").forGetter(it -> it.name),
            GeodeDepositConfigModel.CODEC.fieldOf("config").forGetter(it -> it.config)
    ).apply(x, GeodeDepositModel::new));

    private final GeodeDepositConfigModel config;

    public GeodeDepositModel(String type, List<String> dimensions, String name, GeodeDepositConfigModel config) {
        super(type, dimensions, name);
        this.config = config;
    }

    public GeodeDepositConfigModel getConfig() {
        return config;
    }
}
