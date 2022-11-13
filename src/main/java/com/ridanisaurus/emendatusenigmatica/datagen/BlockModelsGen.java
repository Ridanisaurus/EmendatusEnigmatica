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

package com.ridanisaurus.emendatusenigmatica.datagen;

import com.ridanisaurus.emendatusenigmatica.datagen.base.BlockModelBuilder;
import com.ridanisaurus.emendatusenigmatica.datagen.base.EEBlockModelProvider;
import com.ridanisaurus.emendatusenigmatica.datagen.base.IFinishedGenericJSON;
import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.function.Consumer;

public class BlockModelsGen extends EEBlockModelProvider {
	public BlockModelsGen(DataGenerator gen) {
		super(gen);
	}

	@Override
	protected void buildBlockModel(Consumer<IFinishedGenericJSON> consumer) {
		for (MaterialModel material : EELoader.MATERIALS) {
			List<String> processedType = material.getProcessedType();
			// Storage Blocks
			if (processedType.contains("storage_block")) {
				if (material.getColors().getHighlightColor() == -1) {
					new BlockModelBuilder("minecraft:block/block")
							.renderType("solid")
							.texture("base",  new ResourceLocation(Reference.MOD_ID, "blocks/" + material.getId() + "_block"))
							.texture("particle",  new ResourceLocation(Reference.MOD_ID, "blocks/" + material.getId() + "_block"))
							.element()
							.cube("#base")
							.allFaces((d, u) -> u.tintindex(-1))
							.end()
							.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_block"));
				} else {
					new BlockModelBuilder("minecraft:block/block")
							.renderType("translucent")
							.texture("highlight", new ResourceLocation(Reference.MOD_ID, "blocks/templates/block_0"))
							.texture("base", new ResourceLocation(Reference.MOD_ID, "blocks/templates/block_1"))
							.texture("shade", new ResourceLocation(Reference.MOD_ID, "blocks/templates/block_2"))
							.texture("particle", new ResourceLocation(Reference.MOD_ID, "blocks/templates/block"))
							.element()
							.cube("#highlight")
							.allFaces((d, u) -> u.tintindex(0))
							.end()
							.element()
							.cube("#base")
							.allFaces((d, u) -> u.tintindex(1))
							.end()
							.element()
							.cube("#shade")
							.allFaces((d, u) -> u.tintindex(2))
							.end()
							.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_block"));
				}
			}
			// Raw Storage Blocks
			if (processedType.contains("raw")) {
				if (material.getColors().getHighlightColor() == -1) {
					new BlockModelBuilder("minecraft:block/block")
							.renderType("solid")
							.texture("base",  new ResourceLocation(Reference.MOD_ID, "blocks/raw_" + material.getId() + "_block"))
							.texture("particle",  new ResourceLocation(Reference.MOD_ID, "blocks/raw_" + material.getId() + "_block"))
							.element()
							.cube("#base")
							.allFaces((d, u) -> u.tintindex(-1))
							.end()
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "raw_" + material.getId() + "_block"));
				} else {
					new BlockModelBuilder("minecraft:block/block")
							.renderType("translucent")
							.texture("highlight", new ResourceLocation(Reference.MOD_ID, "blocks/templates/raw_block_0"))
							.texture("base", new ResourceLocation(Reference.MOD_ID, "blocks/templates/raw_block_1"))
							.texture("shade", new ResourceLocation(Reference.MOD_ID, "blocks/templates/raw_block_2"))
							.texture("particle", new ResourceLocation(Reference.MOD_ID, "blocks/templates/block"))
							.element()
							.cube("#highlight")
							.allFaces((d, u) -> u.tintindex(0))
							.end()
							.element()
							.cube("#base")
							.allFaces((d, u) -> u.tintindex(1))
							.end()
							.element()
							.cube("#shade")
							.allFaces((d, u) -> u.tintindex(2))
							.end()
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "raw_" + material.getId() + "_block"));
				}
			}
			// Ores
			for (StrataModel stratum : EELoader.STRATA) {
				if (processedType.contains("ore")) {
					if (material.getColors().getHighlightColor() == -1) {
						dynamicBlock(consumer, stratum.getBaseTexture().toString(), "blocks/overlays/" + material.getId(), getModelName(stratum, material));
					} else {
						if (material.getProperties().getOreBlockType().equals("gem")) {
							dynamicTintBlock(consumer, stratum.getBaseTexture().toString(), "blocks/overlays/templates/ore_gem", getModelName(stratum, material));
						} else {
							dynamicTintBlock(consumer, stratum.getBaseTexture().toString(), "blocks/overlays/templates/ore_metal", getModelName(stratum, material));
						}
					}
				}
			}
		}
	}

	public static String getModelName(StrataModel stratum, MaterialModel material) {
		return material.getId() + (!stratum.getId().equals("minecraft_stone") ? "_" + stratum.getSuffix() : "") + "_ore";
	}

	public void dynamicBlock(Consumer<IFinishedGenericJSON> consumer, String baseTexture, String overlayTexture, String path) {
		new BlockModelBuilder("minecraft:block/block")
				.texture("particle", new ResourceLocation(Reference.MOD_ID, overlayTexture))
				.setLoader("forge:composite")
				.child("solid", new BlockModelBuilder("minecraft:block/block")
						.texture("base", baseTexture)
						.element()
						.from(0, 0, 0)
						.to(16, 16, 16)
						.cube("#base")
						.allFaces((dir, uv) -> uv.tintindex(-1))
						.end()
						.renderType("solid")
				)
				.child("translucent", new BlockModelBuilder("minecraft:block/block")
						.texture("overlay", new ResourceLocation(Reference.MOD_ID, overlayTexture))
						.element()
						.from(0, 0, 0)
						.to(16, 16, 16)
						.cube("#overlay")
						.allFaces((dir, uv) -> uv.tintindex(0))
						.end()
						.renderType("translucent")
				).save(consumer, new ResourceLocation(Reference.MOD_ID, path));
	}

	public void dynamicTintBlock(Consumer<IFinishedGenericJSON> consumer, String baseTexture, String overlayTexture, String path) {
		new BlockModelBuilder("minecraft:block/block")
				.texture("particle", new ResourceLocation(Reference.MOD_ID, overlayTexture))
				.setLoader("forge:composite")
				.child("solid", new BlockModelBuilder("minecraft:block/block")
						.texture("base", baseTexture)
						.element()
						.from(0, 0, 0)
						.to(16, 16, 16)
						.cube("#base")
						.allFaces((dir, uv) -> uv.tintindex(-1))
						.end()
						.renderType("solid")
				)
				.child("translucent", new BlockModelBuilder("minecraft:block/block")
						.texture("overlay_0", new ResourceLocation(Reference.MOD_ID, overlayTexture))
						.texture("overlay_1", new ResourceLocation(Reference.MOD_ID, overlayTexture))
						.texture("overlay_2", new ResourceLocation(Reference.MOD_ID, overlayTexture))
						.element()
						.from(0, 0, 0)
						.to(16, 16, 16)
						.cube("#overlay_0")
						.allFaces((dir, uv) -> uv.tintindex(0))
						.end()
						.element()
						.from(0, 0, 0)
						.to(16, 16, 16)
						.cube("#overlay_1")
						.allFaces((dir, uv) -> uv.tintindex(1))
						.end()
						.element()
						.from(0, 0, 0)
						.to(16, 16, 16)
						.cube("#overlay_2")
						.allFaces((dir, uv) -> uv.tintindex(2))
						.end()
						.renderType("translucent")
				)
				.save(consumer, new ResourceLocation(Reference.MOD_ID, path));
	}

	@Override
	public String getName() {
		return "Emendatus Enigmatica Block Models";
	}
}
