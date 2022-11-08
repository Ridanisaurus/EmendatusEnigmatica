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

import com.ridanisaurus.emendatusenigmatica.reward.PatreonRewardLayer;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class ClientProxy implements IProxy{

	@Override
	public void init(FMLCommonSetupEvent event) {
		var skinMap = Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap();
		for (var render : new EntityRenderer[]{skinMap.get("default"), skinMap.get("slim")}) {
			if (render instanceof PlayerRenderer living)
				living.addLayer(new PatreonRewardLayer(living));
		}
	}

//	public <E extends Entity> void accept(EntityType<? extends E> entityType, EntityRendererProvider<E> entityRendererFactory) {
//		EntityRenderers.register(entityType, entityRendererFactory);
//	}

//	@SubscribeEvent
//	public static void addLayers(EntityRenderersEvent.AddLayers event) {
//		for (String skinName : event.getSkins()) {
//			if (event.getSkin(skinName) instanceof PlayerRenderer renderer) {
//				renderer.addLayer(new PatreonRewardLayer(renderer));
//			}
//		}
//	}

//	public void initRenderer(EntityRenderersEvent.AddLayers event) {
//		for (var playerModelType : event.getSkins()) {
//			if (event.getSkin(playerModelType) instanceof PlayerRenderer renderer) {
//				renderRewardLayer(renderer, renderer::addLayer);
//			}
//		}
//	}
//
//	public static void renderRewardLayer(PlayerRenderer renderer, Consumer<RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>>> consumer) {
//		consumer.accept(new PatreonRewardLayer(renderer));

//	}

//	public <E extends Entity> void accept(EntityType<? extends E> entityType, EntityRendererProvider<E> entityRendererFactory) {
//		EntityRenderers.register(entityType, entityRendererFactory);
//	}
}
