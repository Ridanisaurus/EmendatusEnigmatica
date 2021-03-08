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

package com.ridanisaurus.emendatusenigmatica.Reward;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.player.PlayerModelPart;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


@OnlyIn(Dist.CLIENT)
public class PatreonSupporterRewardHandler extends LayerRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> {
	private static Map<String, ItemStack> rewardMap = new HashMap<>();

	static {
		load();
		EmendatusEnigmatica.LOGGER.info("resource loaded" + rewardMap);
	}

	public PatreonSupporterRewardHandler(IEntityRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> playerModel) {
		super(playerModel);
	}

	@Override
	public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, AbstractClientPlayerEntity player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		String name = player.getGameProfile().getName();
		if (player.hasPlayerInfo() && !player.isInvisible() && player.isWearing(PlayerModelPart.CAPE)) {
			matrixStack.push();
			getEntityModel().bipedHead.translateRotate(matrixStack);
			matrixStack.translate(0, -0.75, 0);
			matrixStack.scale(0.5f, -0.5f, -0.5f);
			matrixStack.rotate(Vector3f.YP.rotationDegrees(90.0F - player.rotationYaw));
			Minecraft.getInstance().getItemRenderer().renderItem(player, rewardMap.getOrDefault(name, ItemStack.EMPTY), ItemCameraTransforms.TransformType.NONE, false, matrixStack, buffer, player.world, 0xF000F0, OverlayTexture.NO_OVERLAY);
			matrixStack.pop();
		}
	}

	private static void load() {
		Thread thread = new Thread(() -> {
			Gson jsonParser = new Gson();
			try {
				URL url = new URL("https://raw.githubusercontent.com/Ridanisaurus/EmendatusEnigmatica/1.16-Current/supporters_list.json");
				try (JsonReader reader = new JsonReader(new InputStreamReader(url.openStream()))) {
					Supporter[] supportersList = jsonParser.fromJson(reader, Supporter[].class);
					for (Supporter supporter : supportersList) {
						ItemStack item = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(supporter.item)));
						rewardMap.put(supporter.name, item);
						EmendatusEnigmatica.LOGGER.info("Added resources for: " + supporter.name);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		thread.setName(Reference.MOD_ID + "_supporter_downloader");
		thread.start();
	}

	private static class Supporter {
		public String name;
		public String item;
	}
}