package com.ridanisaurus.emendatusenigmatica;

import com.ridanisaurus.emendatusenigmatica.config.ConfigMenu;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.jetbrains.annotations.NotNull;

@Mod(value = Reference.MOD_ID, dist = Dist.CLIENT)
public class EmendatusEnigmaticaClient {
    public EmendatusEnigmaticaClient(@NotNull IEventBus modEventBus, @NotNull ModContainer modContainer) {
        // Config screen
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, (client, last) -> new ConfigMenu(last));
    }
}
