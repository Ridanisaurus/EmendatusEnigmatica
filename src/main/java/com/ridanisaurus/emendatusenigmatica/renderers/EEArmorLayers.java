package com.ridanisaurus.emendatusenigmatica.renderers;

import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.client.Minecraft;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EEArmorLayers {

    public static final ModelLayerLocation ARMOR = new ModelLayerLocation(new ResourceLocation(Reference.MOD_ID, "layer_1"), "main");

    public static final ModelLayerLocation LEGS = new ModelLayerLocation(new ResourceLocation(Reference.MOD_ID, "layer_2"), "main");

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ARMOR, () -> LayerDefinition.create(HumanoidModel.createMesh(LayerDefinitions.OUTER_ARMOR_DEFORMATION, 0f), 64, 32));
        event.registerLayerDefinition(LEGS, () -> LayerDefinition.create(HumanoidModel.createMesh(LayerDefinitions.INNER_ARMOR_DEFORMATION, 0f), 64, 32));
    }

    @SubscribeEvent
    public static void registerRenderLayers(EntityRenderersEvent.AddLayers event) {
        for (EntityRenderer<?> entityRenderer : Minecraft.getInstance().getEntityRenderDispatcher().renderers.values()) {
            if (entityRenderer instanceof HumanoidMobRenderer<?, ?> hmr) {
                addRenderLayer(hmr, event.getEntityModels());
            } else if (entityRenderer instanceof ArmorStandRenderer asr) {
                addRenderLayer(asr, event.getEntityModels());
            }
        }
        for (String skin : event.getSkins()) {
            LivingEntityRenderer<?, ?> render = event.getSkin(skin);
            if (render instanceof PlayerRenderer pr) {
                addRenderLayer(pr, event.getEntityModels());
            }
        }
    }


    private static <T extends LivingEntity, M extends HumanoidModel<T>> void addRenderLayer(LivingEntityRenderer<T, M> render, EntityModelSet models) {
        render.addLayer(new DynamicArmorLayer<>(render, models));
    }
}
