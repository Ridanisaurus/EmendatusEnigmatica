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

import com.ridanisaurus.emendatusenigmatica.renderers.ArmorTextureRenderer;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid = Reference.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ArmorTextureEvent {
    public static final ModelLayerLocation ARMOR = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "layer_1"), "main");
    public static final ModelLayerLocation LEGS = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "layer_2"), "main");

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.@NotNull RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ARMOR, () -> LayerDefinition.create(HumanoidModel.createMesh(LayerDefinitions.OUTER_ARMOR_DEFORMATION, 0f), 64, 32));
        event.registerLayerDefinition(LEGS, () -> LayerDefinition.create(HumanoidModel.createMesh(LayerDefinitions.INNER_ARMOR_DEFORMATION, 0f), 64, 32));
    }

    @SubscribeEvent
    public static void registerRenderLayers(EntityRenderersEvent.AddLayers event) {
        event.getEntityTypes().forEach(entityType -> {
            var entityRenderer = event.getRenderer(entityType);
            if (entityRenderer instanceof HumanoidMobRenderer<?, ?> hmr) {
                addRenderLayer(hmr, event.getEntityModels());
            } else if (entityRenderer instanceof ArmorStandRenderer asr) {
                addRenderLayer(asr, event.getEntityModels());
            }
        });
        for (PlayerSkin.Model skin : event.getSkins()) {
            EntityRenderer<?> render = event.getSkin(skin);
            if (render instanceof PlayerRenderer pr) {
                addRenderLayer(pr, event.getEntityModels());
            }
        }
    }

    private static <T extends LivingEntity, M extends HumanoidModel<T>> void addRenderLayer(LivingEntityRenderer<T, M> render, EntityModelSet models) {
        render.addLayer(new ArmorTextureRenderer<>(render, models));
    }
}
