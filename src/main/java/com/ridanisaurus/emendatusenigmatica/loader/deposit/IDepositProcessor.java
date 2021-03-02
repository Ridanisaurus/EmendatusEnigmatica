package com.ridanisaurus.emendatusenigmatica.loader.deposit;

import net.minecraftforge.event.world.BiomeLoadingEvent;

public interface IDepositProcessor {
    void load();
    void setupOres(BiomeLoadingEvent event);
    // TODO extend Feature for custom and vanilla ore gen with dim list
}
