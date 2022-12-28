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

package com.ridanisaurus.emendatusenigmatica.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.plugin.DefaultConfigPlugin;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.entity.BannerPattern;
import org.jetbrains.annotations.NotNull;

import java.util.List;

// Credit: Mekanism
public class ShieldTextureRenderer extends BlockEntityWithoutLevelRenderer {
	public static final ShieldTextureRenderer RENDERER = new ShieldTextureRenderer();

	public ShieldTextureRenderer() {
		super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
	}

	private ShieldModel shieldModel;

	@Override
	public void onResourceManagerReload(@NotNull ResourceManager resourceManager) {
		shieldModel = new ShieldModel(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.SHIELD));
	}

	@Override
	public void renderByItem(@NotNull ItemStack stack, @NotNull ItemTransforms.TransformType transformType, @NotNull PoseStack matrix, @NotNull MultiBufferSource renderer, int light, int overlayLight) {
			matrix.pushPose();
			matrix.scale(1, -1, -1);
			Material shieldMaterial = stack.getTagElement("BlockEntityTag") != null ? ModelBakery.SHIELD_BASE : ModelBakery.NO_PATTERN_SHIELD;
			Item shield = stack.getItem();
			for (MaterialModel material : DefaultConfigPlugin.MATERIALS) {
				if (material.getProcessedTypes().contains("shield") && shield == EERegistrar.shieldMap.get(material.getId()).get()) {
					shieldMaterial = new Material(TextureAtlas.LOCATION_BLOCKS, new ResourceLocation(Reference.MOD_ID, "models/armor/" + material.getId() + "_shield"));
				}
			}
			VertexConsumer buffer = shieldMaterial.sprite().wrap(ItemRenderer.getFoilBufferDirect(renderer, shieldModel.renderType(shieldMaterial.atlasLocation()), true, stack.hasFoil()));
			if (stack.getTagElement("BlockEntityTag") != null) {
				shieldModel.handle().render(matrix, buffer, light, overlayLight, 1, 1, 1, 1);
				List<Pair<Holder<BannerPattern>, DyeColor>> list = BannerBlockEntity.createPatterns(ShieldItem.getColor(stack), BannerBlockEntity.getItemPatterns(stack));
				BannerRenderer.renderPatterns(matrix, renderer, light, overlayLight, shieldModel.plate(), shieldMaterial, false, list);
			} else {
				shieldModel.renderToBuffer(matrix, buffer, light, overlayLight, 1, 1, 1, 1);
			}
			matrix.popPose();
	}
}