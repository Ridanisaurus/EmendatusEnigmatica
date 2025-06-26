package com.ridanisaurus.emendatusenigmatica.events;

import com.ridanisaurus.emendatusenigmatica.items.handlers.ShieldClientExtension;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@EventBusSubscriber(modid = Reference.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientExtensionsEvent {
    @SubscribeEvent
    public static void register(RegisterClientExtensionsEvent event) {
        EERegistrar.shieldMap.forEach((id, shield) -> event.registerItem(ShieldClientExtension.INSTANCE, shield));
    }
}
