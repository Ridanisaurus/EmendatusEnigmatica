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

import com.ridanisaurus.emendatusenigmatica.items.templates.BasicShieldItem;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.renderers.ShieldTextureRenderer;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

// Credit: Mekanism
@EventBusSubscriber(modid = Reference.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ShieldTextureEvent {
	@SubscribeEvent
	public static void init(FMLClientSetupEvent event) {
		event.enqueueWork(() -> addShieldPropertyOverrides(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "blocking"),
				(stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F,
				EERegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BasicShieldItem).map(DeferredHolder::get).toArray(Item[]::new)));
	}

	private static void addShieldPropertyOverrides(ResourceLocation override, ItemPropertyFunction propertyGetter, ItemLike... shields) {
		for (ItemLike shield : shields) {
			ItemProperties.register(shield.asItem(), override, propertyGetter);
		}
	}

	@SubscribeEvent
	public static void registerClientReloadListeners(RegisterClientReloadListenersEvent event) {
		event.registerReloadListener(ShieldTextureRenderer.RENDERER);
	}
}