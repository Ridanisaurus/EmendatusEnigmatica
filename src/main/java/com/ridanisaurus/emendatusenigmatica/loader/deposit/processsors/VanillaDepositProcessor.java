package com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonDepositModelBase;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.vanilla.VanillaDepositModel;

import java.util.List;
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

    public VanillaDepositModel getVanillaModel() {
        return model;
    }

    @Override
    public String getType() {
        return model.getType();
    }

    @Override
    public CommonDepositModelBase getCommonModel() {
        return model;
    }

    @Override
    public List<CommonBlockDefinitionModel> getBlocks() {
        return List.of();
    }

    @Override
    public int getChance() {
        return model.getChance();
    }

    @Override
    public int getMaxY() {
        return model.getMaxYLevel();
    }

    @Override
    public int getMinY() {
        return model.getMinYLevel();
    }

    @Override
    public boolean hasSurfaceSample() {
        return false;
    }
}