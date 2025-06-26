/*
 * MIT License
 *
 * Copyright (c) 2024. Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ridanisaurus.emendatusenigmatica.config.EEConfig;
import com.ridanisaurus.emendatusenigmatica.events.ArmorTextureEvent;
import com.ridanisaurus.emendatusenigmatica.items.templates.BasicArmorItem;
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
import net.neoforged.neoforge.client.ClientHooks;
import org.jetbrains.annotations.NotNull;

public class ArmorTextureRenderer<E extends LivingEntity, M extends HumanoidModel<E>> extends RenderLayer<E, M> {

    private final HumanoidModel<E> body;
    private final HumanoidModel<E> legs;

    public ArmorTextureRenderer(RenderLayerParent<E, M> renderLayerParent, @NotNull EntityModelSet entityModelSet) {
        super(renderLayerParent);
        this.body = new HumanoidModel<>(entityModelSet.bakeLayer(ArmorTextureEvent.ARMOR));
        this.legs = new HumanoidModel<>(entityModelSet.bakeLayer(ArmorTextureEvent.LEGS));
    }

    @Override
    public void render(@NotNull PoseStack matrixStackIn, @NotNull MultiBufferSource bufferIn, int packedLightIn, @NotNull E entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        for (int i = 0; i < 5; i++) {
            var renderType = RenderType.armorCutoutNoCull(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "textures/armor/layer_1/0" + i + ".png"));
            renderArmorPiece(matrixStackIn, bufferIn, entity, EquipmentSlot.CHEST, packedLightIn, body, renderType, i);
            renderArmorPiece(matrixStackIn, bufferIn, entity, EquipmentSlot.FEET, packedLightIn, body, renderType, i);
            renderArmorPiece(matrixStackIn, bufferIn, entity, EquipmentSlot.HEAD, packedLightIn, body, renderType, i);
            renderArmorPiece(matrixStackIn, bufferIn, entity, EquipmentSlot.LEGS, packedLightIn, legs,
                RenderType.armorCutoutNoCull(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "textures/armor/layer_2/0" + i + ".png")), i);
        }
    }

    private void renderArmorPiece(PoseStack matrixStack, MultiBufferSource buffer, @NotNull E entity, EquipmentSlot slot, int light, HumanoidModel<E> p_model, RenderType renderType, int colorIndex) {
        ItemStack stack = entity.getItemBySlot(slot);
        if (stack.getItem() instanceof BasicArmorItem armorItem && armorItem.getEquipmentSlot() == slot && armorItem.getMaterialModel().getColors().getMaterialColor() != -1) {
            this.getParentModel().copyPropertiesTo(p_model);
            this.setModelSlotVisible(p_model, slot);
            Model model = ClientHooks.getArmorModel(entity, stack, slot, p_model);
            boolean glint = EEConfig.client.oldSchoolGlint.get() && stack.hasFoil();

            this.doRender(matrixStack, buffer, light, glint, model, armorItem.getColorForIndex(colorIndex), renderType);
        }
    }

    private void doRender(PoseStack matrixStack, MultiBufferSource buffer, int light, boolean glint, @NotNull Model model, int color, RenderType renderType) {
        VertexConsumer vertexConsumer = ItemRenderer.getArmorFoilBuffer(buffer, renderType, glint);
        model.renderToBuffer(matrixStack, vertexConsumer, light, OverlayTexture.NO_OVERLAY, color);
    }

    protected void setModelSlotVisible(@NotNull HumanoidModel<E> model, @NotNull EquipmentSlot slotIn) {
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
}