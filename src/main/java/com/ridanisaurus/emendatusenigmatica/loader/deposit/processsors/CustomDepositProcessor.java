package com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.custom.CustomDepositModel;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.Optional;

public class CustomDepositProcessor implements IDepositProcessor {

    private JsonObject object;
    private CustomDepositModel model;

    public CustomDepositProcessor(JsonObject object) {

        this.object = object;
    }

    @Override
    public void load() {
        Optional<Pair<CustomDepositModel, JsonElement>> result = JsonOps.INSTANCE.withDecoder(CustomDepositModel.CODEC).apply(object).result();
        if (!result.isPresent()) {
            return;
        }
        model = result.get().getFirst();
    }

    @Override
    public void setupOres(BiomeLoadingEvent event) {

    }
}
