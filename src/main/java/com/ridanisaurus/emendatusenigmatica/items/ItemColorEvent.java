/*
 *  MIT License
 *
 *  Copyright (c) 2020 Ridanisaurus
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package com.ridanisaurus.emendatusenigmatica.items;

import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.blocks.BasicStorageBlockItem;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.model.DynamicFluidContainerModel;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ItemColorEvent {
	@SubscribeEvent
	public static void itemColorEvent(RegisterColorHandlersEvent.Item event) {
		event.register(new DynamicFluidContainerModel.Colors(), EERegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BucketItem).map(RegistryObject::get).toArray(Item[]::new));
		event.register(new ItemColorHandler(), EERegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BasicItem).map(RegistryObject::get).toArray(Item[]::new));
		event.register(new ItemColorHandler(), EERegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BasicSwordItem).map(RegistryObject::get).toArray(Item[]::new));
		event.register(new ItemColorHandler(), EERegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BasicPickaxeItem).map(RegistryObject::get).toArray(Item[]::new));
		event.register(new ItemColorHandler(), EERegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BasicAxeItem).map(RegistryObject::get).toArray(Item[]::new));
		event.register(new ItemColorHandler(), EERegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BasicShovelItem).map(RegistryObject::get).toArray(Item[]::new));
		event.register(new ItemColorHandler(), EERegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BasicHoeItem).map(RegistryObject::get).toArray(Item[]::new));
		event.register(new ItemColorHandler(), EERegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BasicPaxelItem).map(RegistryObject::get).toArray(Item[]::new));
//		if(EmendatusEnigmatica.BLOODMAGIC_LOADED)event.register(new ItemColorHandler(), EEBloodMagicRegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BasicItem).map(RegistryObject::get).toArray(Item[]::new));
		event.register(new BlockItemColorHandler(), EERegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BlockItem || x.get() instanceof BasicStorageBlockItem).map(RegistryObject::get).toArray(Item[]::new));
	}
}
