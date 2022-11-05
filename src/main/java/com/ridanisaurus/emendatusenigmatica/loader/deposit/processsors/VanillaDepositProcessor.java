package com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonDepositModelBase;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.geode.GeodeDepositModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sphere.SphereDepositModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.vanilla.VanillaDepositModel;

import java.util.Optional;

public class VanillaDepositProcessor implements IDepositProcessor {

    private JsonObject object;
    private VanillaDepositModel model;

    public VanillaDepositProcessor(JsonObject object) {
        this.object = object;
    }

    @Override
    public void load() {
        Optional<Pair<VanillaDepositModel, JsonElement>> result = JsonOps.INSTANCE.withDecoder(VanillaDepositModel.CODEC).apply(object).result();
        if (!result.isPresent()) {
            return;
        }
        model = result.get().getFirst();
    }

    @Override
    public VanillaDepositModel getVanillaModel() {
        return model;
    }

    @Override
    public GeodeDepositModel getGeodeModel() {
        return null;
    }

    @Override
    public SphereDepositModel getSphereModel() {
        return null;
    }

    @Override
    public CommonDepositModelBase getModel() {
        return model;
    }
}