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

package com.ridanisaurus.emendatusenigmatica.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ridanisaurus.emendatusenigmatica.items.templates.BasicShieldItem;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

// Credit: Mekanism
public class ShieldTextureRenderer extends BlockEntityWithoutLevelRenderer
{
    public static final ShieldTextureRenderer RENDERER = new ShieldTextureRenderer();
    private ShieldModel shieldModel;

    public ShieldTextureRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    @Override
    public void onResourceManagerReload(@NotNull ResourceManager resourceManager) {
        shieldModel = new ShieldModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.SHIELD));
    }

    @Override
    public void renderByItem(
        @NotNull ItemStack stack,
        @NotNull ItemDisplayContext displayContext,
        @NotNull PoseStack matrix,
        @NotNull MultiBufferSource renderer,
        int light,
        int overlayLight
    ) {
        matrix.pushPose();
        matrix.scale(1.0F, -1.0F, -1.0F);
        Item shield = stack.getItem();
        if (shield instanceof BasicShieldItem bsi) {
            if (bsi.getMaterialModel().getColors().getMaterialColor() == -1) {
                var rl = ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "textures/models/armor/" + bsi.getMaterialModel().getId() + "_shield.png");
                var renderType = RenderType.entityCutoutNoCull(rl);
                doRender(stack, matrix, renderer, light, overlayLight, shieldModel, 0xFFFFFF, renderType);
            } else {
                for (int i = 0; i < 5; i++) {
                    var rl = ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "textures/armor/shield/0" + i + ".png");
                    var renderType = RenderType.entityCutoutNoCull(rl);
                    doRender(stack, matrix, renderer, light, overlayLight, shieldModel, bsi.getColorForIndex(i), renderType);
                }
                var rl = ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "textures/armor/shield/wood.png");
                var renderType = RenderType.entityCutoutNoCull(rl);
                doRender(stack, matrix, renderer, light, overlayLight, shieldModel, 0xFFFFFF, renderType);
            }
        }
        matrix.popPose();
    }

private void doRender(@NotNull ItemStack stack, PoseStack matrix, MultiBufferSource renderer, int light, int overlayLight, ShieldModel model, int color, RenderType renderType) {
        VertexConsumer vertexConsumer = ItemRenderer.getFoilBufferDirect(renderer, renderType, false, stack.hasFoil());
        if (stack.get(DataComponents.BLOCK_ENTITY_DATA) != null) {
            model.handle().render(matrix, vertexConsumer, light, overlayLight, color);
        } else {
            model.renderToBuffer(matrix, vertexConsumer, light, overlayLight, color);
        }
    }
}
