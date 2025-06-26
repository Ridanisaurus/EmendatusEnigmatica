/*
 * MIT License
 *
 * Copyright (c) 2020-2024. Ridanisaurus
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.ridanisaurus.emendatusenigmatica.events;

import com.ridanisaurus.emendatusenigmatica.blocks.templates.BasicStorageBlockItem;
import com.ridanisaurus.emendatusenigmatica.items.templates.*;
import com.ridanisaurus.emendatusenigmatica.items.handlers.BlockItemColorHandler;
import com.ridanisaurus.emendatusenigmatica.items.handlers.ItemColorHandler;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.DynamicFluidContainerModel;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = Reference.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ItemColorEvent {
	@SubscribeEvent
	public static void itemColorEvent(RegisterColorHandlersEvent.Item event) {
		event.register(new DynamicFluidContainerModel.Colors(), EERegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BucketItem).map(DeferredHolder::get).toArray(Item[]::new));
		event.register(new ItemColorHandler(), EERegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BasicItem)		.map(DeferredHolder::get).toArray(Item[]::new));
		event.register(new ItemColorHandler(), EERegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BasicSwordItem)	.map(DeferredHolder::get).toArray(Item[]::new));
		event.register(new ItemColorHandler(), EERegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BasicPickaxeItem)	.map(DeferredHolder::get).toArray(Item[]::new));
		event.register(new ItemColorHandler(), EERegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BasicAxeItem)		.map(DeferredHolder::get).toArray(Item[]::new));
		event.register(new ItemColorHandler(), EERegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BasicShovelItem)	.map(DeferredHolder::get).toArray(Item[]::new));
		event.register(new ItemColorHandler(), EERegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BasicHoeItem)		.map(DeferredHolder::get).toArray(Item[]::new));
		event.register(new ItemColorHandler(), EERegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BasicPaxelItem)	.map(DeferredHolder::get).toArray(Item[]::new));
		event.register(new ItemColorHandler(), EERegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BasicArmorItem)	.map(DeferredHolder::get).toArray(Item[]::new));
		event.register(new BlockItemColorHandler(), EERegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BlockItem || x.get() instanceof BasicStorageBlockItem).map(DeferredHolder::get).toArray(Item[]::new));
	}
}
