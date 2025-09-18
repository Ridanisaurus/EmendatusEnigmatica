package com.ridanisaurus.eecreateaddon;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(EECreateAddon.MOD_ID)
public class EECreateAddon
{
    public static final String MOD_ID = "ee_create_addon";
    public static final String CREATE = "create";

    public EECreateAddon()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EECreateRegistrar.finalize(modEventBus);
    }
}
