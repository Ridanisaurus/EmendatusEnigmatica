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

    private final JsonObject object;
    private VanillaDepositModel model;

    public VanillaDepositProcessor(JsonObject object) {
        this.object = object;
    }

    @Override
    public void load() {
        Optional<Pair<VanillaDepositModel, JsonElement>> result = JsonOps.INSTANCE.withDecoder(VanillaDepositModel.CODEC).apply(object).result();
        if (result.isEmpty()) return;
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
    public int getSize() {
        int size = model.getSize();
        if (size >= 1 && size < 6) {
            return 0; // Small
        } else if (size >= 6 && size < 11) {
            return 1; // Average
        } else if (size >= 11 && size < 17) {
            return 2; // Large
        } else {
            return -1;
        }
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

    @Override
    public String getPlacement() {
        return model.getPlacement();
    }

    @Override
    public String getRarity() {
        return model.getRarity();
    }
}