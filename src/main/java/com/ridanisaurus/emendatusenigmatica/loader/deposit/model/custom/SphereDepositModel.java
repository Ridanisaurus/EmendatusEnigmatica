package com.ridanisaurus.emendatusenigmatica.loader.deposit.model.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonDepositModelBase;

import java.util.List;

public class SphereDepositModel extends CommonDepositModelBase {
    public static final Codec<SphereDepositModel> CODEC = RecordCodecBuilder.create(x -> x.group(
            Codec.STRING.fieldOf("type").forGetter(it -> it.type),
            Codec.list(Codec.STRING).fieldOf("dimensions").forGetter(it -> it.dimensions),
            Codec.STRING.fieldOf("registryName").forGetter(it -> it.name),
            SphereDepositConfigModel.CODEC.fieldOf("config").forGetter(it -> it.config)
    ).apply(x, SphereDepositModel::new));

    private final SphereDepositConfigModel config;

    public SphereDepositModel(String type, List<String> dimensions, String name, SphereDepositConfigModel config) {
        super(type, dimensions, name);
        this.config = config;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public SphereDepositConfigModel getConfig() {
        return config;
    }

    @Override
    public List<String> getDimensions() {
        return super.getDimensions();
    }

}