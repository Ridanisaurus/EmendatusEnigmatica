package com.ridanisaurus.emendatusenigmatica.loader.deposit;

import com.google.gson.JsonObject;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public interface IDepositProcessor {
    void load();
    void setupOres(BiomeLoadingEvent event);
}
