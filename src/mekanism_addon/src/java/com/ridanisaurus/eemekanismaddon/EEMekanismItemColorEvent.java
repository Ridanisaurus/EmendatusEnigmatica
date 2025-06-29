package com.ridanisaurus.eemekanismaddon;

import com.ridanisaurus.emendatusenigmatica.items.BasicItem;
import com.ridanisaurus.emendatusenigmatica.items.ItemColorHandler;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = EEMekanismAddon.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EEMekanismItemColorEvent {
	@SubscribeEvent
	public static void itemColorEvent(RegisterColorHandlersEvent.Item event) {
		event.register(new ItemColorHandler(), EEMekanismRegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BasicItem).map(RegistryObject::get).toArray(Item[]::new));
	}
}
