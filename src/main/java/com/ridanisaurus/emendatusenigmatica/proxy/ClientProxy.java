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

package com.ridanisaurus.emendatusenigmatica.proxy;

import com.ridanisaurus.emendatusenigmatica.reward.PatreonSupporterRewardHandler;
import com.ridanisaurus.emendatusenigmatica.inventory.EnigmaticFortunizerScreen;
import com.ridanisaurus.emendatusenigmatica.registries.ContainerHandler;
import com.ridanisaurus.emendatusenigmatica.registries.OreHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.Map;
import java.util.function.Supplier;

public class ClientProxy implements IProxy {

	@Override
	public void preInit(FMLCommonSetupEvent event) {
		ScreenManager.register(ContainerHandler.ENIGMATIC_FORTUNIZER_CONTAINER.get(), EnigmaticFortunizerScreen::new);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		Map<String, PlayerRenderer> skinMap = Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap();
		for (PlayerRenderer render : new PlayerRenderer[]{skinMap.get("default"), skinMap.get("slim")})
			render.addLayer(new PatreonSupporterRewardHandler(render));
	}

	@Override
	public void postInit(FMLCommonSetupEvent event) {
		for (RegistryObject<Block> block : OreHandler.BLOCKS.getEntries()) {
			RenderTypeLookup.setRenderLayer(block.get(), layer -> layer == RenderType.solid() || layer == RenderType.translucent());
		}
	}

	@Override
	public <T extends Entity> void registerEntityRenderer(EntityType<T> entityClass, Supplier<IRenderFactory<T>> renderFactory) {
		RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory.get());
	}

}