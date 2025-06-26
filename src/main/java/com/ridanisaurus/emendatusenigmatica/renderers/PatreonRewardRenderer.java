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

import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.mojang.blaze3d.vertex.PoseStack;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.config.EEConfig;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;

import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class PatreonRewardRenderer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>>  {
	private static final Map<String, ItemStack> REWARD_MAP = new HashMap<>();

	static {
		new FetchThread();
	}

	public PatreonRewardRenderer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> playerModel) {
		super(playerModel);
	}

	@Override
	public void render(@NotNull PoseStack matrixStack, @NotNull MultiBufferSource buffer, int packedLight, @NotNull AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (EEConfig.client.showPatreonReward.get()) {
			String name = player.getGameProfile().getName();
			Level world = player.getCommandSenderWorld();
			matrixStack.pushPose();
			getParentModel().head.translateAndRotate(matrixStack);
			matrixStack.translate(0, -1, 0);
			matrixStack.scale(0.30f, -0.30f, -0.30f);
			matrixStack.mulPose(new Quaternionf(0, 1.0, 0, 0.3f).rotateY((float)((world.getGameTime() % 360)*(Math.PI/180))));

			Minecraft.getInstance().getItemRenderer().renderStatic(
				player,
				REWARD_MAP.getOrDefault(name, ItemStack.EMPTY),
				ItemDisplayContext.NONE,
				false,
				matrixStack,
				buffer,
				player.clientLevel,
				0xF000F0,
				OverlayTexture.NO_OVERLAY,
				player.getId()
			);
			matrixStack.popPose();
		}
	}

	private static class FetchThread extends Thread {

		public FetchThread() {
			this.setName(Reference.MOD_ID + "_support_fetcher");
			this.setDaemon(true);
			this.start();
		}

		@Override
		public void run() {
			try {
				var url = URI.create("https://raw.githubusercontent.com/Ridanisaurus/EmendatusEnigmatica/EEV2-1.19/patreon_supporters_list.json").toURL();
				var reader = new JsonReader(new InputStreamReader(url.openStream()));

				var main = JsonParser.parseReader(reader).getAsJsonObject();
				for (var entry : main.entrySet()) {
//					var object = entry.getValue().getAsJsonObject();
					var name = entry.getKey();
					var item = new ItemStack(BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "felinium_jaminite_ingot")));
					REWARD_MAP.put(name, item);
				}
				EmendatusEnigmatica.logger.info("Fetching supporter information was successful!");
				reader.close();
			} catch (Exception e) {
				EmendatusEnigmatica.logger.warn("Fetching supporter information failed", e);
			}
		}
	}
}