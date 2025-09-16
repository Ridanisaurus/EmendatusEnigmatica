package com.ridanisaurus.eemekanismaddon;

import com.ridanisaurus.eemekanismaddon.registry.EEMekanismRegistrar;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod(EEMekanismAddon.MOD_ID)
public class EEMekanismAddon {
    public static final String MOD_ID = "ee_mekanism_addon";

    public EEMekanismAddon(@NotNull IEventBus modEventBus, @NotNull ModContainer modContainer) {
        EEMekanismRegistrar.finalize(modEventBus);
    }
}