package com.ridanisaurus.emendatusenigmatica.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ridanisaurus.emendatusenigmatica.items.BasicArmorItem;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;

import java.util.ArrayList;
import java.util.List;

public class DynamicArmorLayer <E extends LivingEntity, M extends HumanoidModel<E>> extends RenderLayer<E, M> {

    private HumanoidModel<E> body;
    private HumanoidModel<E> legs;

    public DynamicArmorLayer(RenderLayerParent<E, M> renderLayerParent, EntityModelSet entityModelSet) {
        super(renderLayerParent);
        this.body = new HumanoidModel<>(entityModelSet.bakeLayer(EEArmorLayers.ARMOR));
        this.legs = new HumanoidModel<>(entityModelSet.bakeLayer(EEArmorLayers.LEGS));
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, E entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        for (int i = 0; i < 5; i++) {
            var rl = new ResourceLocation(Reference.MOD_ID, "textures/armor/layer_2/0"+i+ ".png");
            var renderType = RenderType.armorCutoutNoCull(rl);
            renderSlot(matrixStackIn, bufferIn, entity, EquipmentSlot.LEGS, packedLightIn, legs,
                    partialTicks, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, renderType, i);
        }
        for (int i = 0; i < 5; i++) {
            var rl = new ResourceLocation(Reference.MOD_ID, "textures/armor/layer_1/0"+i+ ".png");
            var renderType = RenderType.armorCutoutNoCull(rl);
            renderSlot(matrixStackIn, bufferIn, entity, EquipmentSlot.CHEST, packedLightIn, body,
                    partialTicks, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, renderType, i);
            renderSlot(matrixStackIn, bufferIn, entity, EquipmentSlot.FEET, packedLightIn, body,
                    partialTicks, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, renderType, i);
            renderSlot(matrixStackIn, bufferIn, entity, EquipmentSlot.HEAD, packedLightIn, body,
                    partialTicks, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, renderType, i);
        }

    }

    private void renderSlot(PoseStack matrixStack, MultiBufferSource buffer, E entity, EquipmentSlot slot, int light, HumanoidModel<E> model, float partialTicks, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, RenderType renderType, int colorIndex) {
        ItemStack stack = entity.getItemBySlot(slot);
        if (stack.getItem() instanceof BasicArmorItem armor && armor.getSlot() == slot) {
            this.getParentModel().copyPropertiesTo(model);
            model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
            model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            this.setModelSlotVisible(model, slot);
            Model model1 = ForgeHooksClient.getArmorModel(entity, stack, slot, model);
            boolean glint = stack.hasFoil();

            // secondary texture layer in all slots
            float[] secondary = decomposeColorF(armor.getColorForIndex(colorIndex));
            this.doRender(matrixStack, buffer, light, glint, model1, secondary[1], secondary[2], secondary[3], slot, renderType);
        }
    }

    private void doRender(PoseStack matrixStack, MultiBufferSource buffer, int light, boolean glint, Model model, float r, float g, float b, EquipmentSlot slot, RenderType renderType) {
        VertexConsumer vertexConsumer = ItemRenderer.getArmorFoilBuffer(buffer, renderType, false, glint);
        model.renderToBuffer(matrixStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, r, g, b, 1.0F);
    }

    protected void setModelSlotVisible(HumanoidModel<E> model, EquipmentSlot slotIn) {
        model.setAllVisible(false);
        switch (slotIn) {
            case HEAD -> {
                model.head.visible = true;
                model.hat.visible = true;
            }
            case CHEST -> {
                model.body.visible = true;
                model.rightArm.visible = true;
                model.leftArm.visible = true;
            }
            case LEGS -> {
                model.body.visible = true;
                model.rightLeg.visible = true;
                model.leftLeg.visible = true;
            }
            case FEET -> {
                model.rightLeg.visible = true;
                model.leftLeg.visible = true;
            }
        }
    }

    public static float[] decomposeColorF(int color) {
        float[] res = new float[4];
        res[0] = (color >> 24 & 0xff) / 255f;
        res[1] = (color >> 16 & 0xff) / 255f;
        res[2] = (color >> 8  & 0xff) / 255f;
        res[3] = (color       & 0xff) / 255f;
        return res;
    }
}
