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

import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.datagen.base.BlockModelBuilder;
import com.ridanisaurus.emendatusenigmatica.datagen.base.EEBlockModelProvider;
import com.ridanisaurus.emendatusenigmatica.datagen.base.IFinishedGenericJSON;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.function.Consumer;

public class BlockModelsGen extends EEBlockModelProvider {
	private final EmendatusDataRegistry registry;

	public BlockModelsGen(DataGenerator gen, EmendatusDataRegistry registry) {
		super(gen);
		this.registry = registry;
	}

	@Override
	protected void buildBlockModel(Consumer<IFinishedGenericJSON> consumer) {
		for (MaterialModel material : registry.getMaterials()) {
			List<String> processedType = material.getProcessedTypes();
			// Storage Blocks
			if (processedType.contains("storage_block")) {
				if (material.getColors().getMaterialColor() == -1) {
					new BlockModelBuilder("minecraft:block/block")
							.texture("base",  new ResourceLocation(Reference.MOD_ID, "blocks/" + material.getId() + "_block"))
							.texture("particle",  new ResourceLocation(Reference.MOD_ID, "blocks/" + material.getId() + "_block"))
							.element()
							.cube("#base")
							.allFaces((d, u) -> u.tintindex(-1))
							.end()
							.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_block"));
				} else {
					if (material.getProperties().getMaterialType().equals("gem")) {
						new BlockModelBuilder("minecraft:block/block")
								.renderType("cutout")
								.texture("highlight2", new ResourceLocation(Reference.MOD_ID, "blocks/templates/block/gem/00"))
								.texture("highlight1", new ResourceLocation(Reference.MOD_ID, "blocks/templates/block/gem/01"))
								.texture("base", new ResourceLocation(Reference.MOD_ID, "blocks/templates/block/gem/02"))
								.texture("shadow1", new ResourceLocation(Reference.MOD_ID, "blocks/templates/block/gem/03"))
								.texture("shadow2", new ResourceLocation(Reference.MOD_ID, "blocks/templates/block/gem/04"))
								.texture("particle", new ResourceLocation(Reference.MOD_ID, "blocks/templates/block/gem/00"))
								.element()
								.cube("#highlight2")
								.allFaces((d, u) -> u.tintindex(0))
								.end()
								.element()
								.cube("#highlight1")
								.allFaces((d, u) -> u.tintindex(1))
								.end()
								.element()
								.cube("#base")
								.allFaces((d, u) -> u.tintindex(2))
								.end()
								.element()
								.cube("#shadow1")
								.allFaces((d, u) -> u.tintindex(3))
								.end()
								.element()
								.cube("#shadow2")
								.allFaces((d, u) -> u.tintindex(4))
								.end()
								.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_block"));
					} else {
						new BlockModelBuilder("minecraft:block/block")
								.renderType("cutout")
								.texture("highlight2", new ResourceLocation(Reference.MOD_ID, "blocks/templates/block/metal/00"))
								.texture("highlight1", new ResourceLocation(Reference.MOD_ID, "blocks/templates/block/metal/01"))
								.texture("base", new ResourceLocation(Reference.MOD_ID, "blocks/templates/block/metal/02"))
								.texture("shadow1", new ResourceLocation(Reference.MOD_ID, "blocks/templates/block/metal/03"))
								.texture("shadow2", new ResourceLocation(Reference.MOD_ID, "blocks/templates/block/metal/04"))
								.texture("particle", new ResourceLocation(Reference.MOD_ID, "blocks/templates/block/metal/00"))
								.element()
								.cube("#highlight2")
								.allFaces((d, u) -> u.tintindex(0))
								.end()
								.element()
								.cube("#highlight1")
								.allFaces((d, u) -> u.tintindex(1))
								.end()
								.element()
								.cube("#base")
								.allFaces((d, u) -> u.tintindex(2))
								.end()
								.element()
								.cube("#shadow1")
								.allFaces((d, u) -> u.tintindex(3))
								.end()
								.element()
								.cube("#shadow2")
								.allFaces((d, u) -> u.tintindex(4))
								.end()
								.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_block"));
					}
				}
			}
			// Raw Storage Blocks
			if (processedType.contains("raw")) {
				if (material.getColors().getMaterialColor() == -1) {
					new BlockModelBuilder("minecraft:block/block")
							.texture("base",  new ResourceLocation(Reference.MOD_ID, "blocks/raw_" + material.getId() + "_block"))
							.texture("particle",  new ResourceLocation(Reference.MOD_ID, "blocks/raw_" + material.getId() + "_block"))
							.element()
							.cube("#base")
							.allFaces((d, u) -> u.tintindex(-1))
							.end()
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "raw_" + material.getId() + "_block"));
				} else {
					new BlockModelBuilder("minecraft:block/block")
							.renderType("cutout")
							.texture("highlight2", new ResourceLocation(Reference.MOD_ID, "blocks/templates/raw_block/00"))
							.texture("highlight1", new ResourceLocation(Reference.MOD_ID, "blocks/templates/raw_block/01"))
							.texture("base", new ResourceLocation(Reference.MOD_ID, "blocks/templates/raw_block/02"))
							.texture("shadow1", new ResourceLocation(Reference.MOD_ID, "blocks/templates/raw_block/03"))
							.texture("shadow2", new ResourceLocation(Reference.MOD_ID, "blocks/templates/raw_block/04"))
							.texture("particle", new ResourceLocation(Reference.MOD_ID, "blocks/templates/raw_block/00"))
							.element()
							.cube("#highlight2")
							.allFaces((d, u) -> u.tintindex(0))
							.end()
							.element()
							.cube("#highlight1")
							.allFaces((d, u) -> u.tintindex(1))
							.end()
							.element()
							.cube("#base")
							.allFaces((d, u) -> u.tintindex(2))
							.end()
							.element()
							.cube("#shadow1")
							.allFaces((d, u) -> u.tintindex(3))
							.end()
							.element()
							.cube("#shadow2")
							.allFaces((d, u) -> u.tintindex(4))
							.end()
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "raw_" + material.getId() + "_block"));
				}
			}
			// Ores
			for (StrataModel stratum : registry.getStrata()) {
				if (processedType.contains("ore")) {
					if (material.getColors().getMaterialColor() == -1) {
						dynamicBlock(consumer, stratum.getBaseTexture().toString(), "blocks/overlays/" + material.getId(), getOreModelName(stratum, material));
					} else {
						if (material.getProperties().getMaterialType().equals("gem")) {
							dynamicTintBlock(consumer, stratum.getBaseTexture().toString(),
									"blocks/templates/ore/gem/00",
									"blocks/templates/ore/gem/01",
									"blocks/templates/ore/gem/02",
									"blocks/templates/ore/gem/03",
									"blocks/templates/ore/gem/04",
									"blocks/templates/ore/gem/shadow_drop",
									getOreModelName(stratum, material));
						} else {
							dynamicTintBlock(consumer, stratum.getBaseTexture().toString(),
									"blocks/templates/ore/metal/00",
									"blocks/templates/ore/metal/01",
									"blocks/templates/ore/metal/02",
									"blocks/templates/ore/metal/03",
									"blocks/templates/ore/metal/04",
									"blocks/templates/ore/metal/shadow_drop",
									getOreModelName(stratum, material));
						}
					}
				}
				if (processedType.contains("ore") && stratum.getSampleStrata()) {
					if (material.getColors().getMaterialColor() == -1) {
						dynamicBlock(consumer, stratum.getBaseTexture().toString(), "blocks/overlays/" + material.getId() + "_sample", getOreSampleModelName(stratum, material));
					} else {
						dynamicTintBlock(consumer, stratum.getBaseTexture().toString(),
								"blocks/templates/sample/00",
								"blocks/templates/sample/01",
								"blocks/templates/sample/02",
								"blocks/templates/sample//03",
								"blocks/templates/sample/04",
								"blocks/templates/sample/shadow_drop",
								getOreSampleModelName(stratum, material));
					}
				}
			}
		}
	}

	public static String getOreModelName(StrataModel stratum, MaterialModel material) {
		return material.getId() + (!stratum.getId().equals("minecraft_stone") ? "_" + stratum.getSuffix() : "") + "_ore";
	}

	public static String getOreSampleModelName(StrataModel stratum, MaterialModel material) {
		return material.getId() + "_" + stratum.getSuffix() + "_ore_sample";
	}

	public void dynamicBlock(Consumer<IFinishedGenericJSON> consumer, String strata, String overlayTexture, String path) {
		new BlockModelBuilder("minecraft:block/block")
				.texture("particle", strata)
				.setLoader("forge:composite")
				.child("solid", new BlockModelBuilder("minecraft:block/block")
						.texture("strata", strata)
						.element()
						.from(0, 0, 0)
						.to(16, 16, 16)
						.cube("#strata")
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
						.allFaces((dir, uv) -> uv.tintindex(-1))
						.end()
						.renderType("translucent")
				).save(consumer, new ResourceLocation(Reference.MOD_ID, path));
	}

	public void dynamicTintBlock(Consumer<IFinishedGenericJSON> consumer, String strata, String highlight2, String highlight1, String base, String shadow1, String shadow2, String drop, String path) {
		new BlockModelBuilder("minecraft:block/block")
				.texture("particle", strata)
				.setLoader("forge:composite")
				.child("solid", new BlockModelBuilder("minecraft:block/block")
						.texture("strata", strata)
						.element()
						.from(0, 0, 0)
						.to(16, 16, 16)
						.cube("#strata")
						.allFaces((dir, uv) -> uv.tintindex(-1))
						.end()
						.renderType("solid")
				)
				.child("cutout", new BlockModelBuilder("minecraft:block/block")
						.texture("highlight2", new ResourceLocation(Reference.MOD_ID, highlight2))
						.texture("highlight1", new ResourceLocation(Reference.MOD_ID, highlight1))
						.texture("base", new ResourceLocation(Reference.MOD_ID, base))
						.texture("shadow1", new ResourceLocation(Reference.MOD_ID, shadow1))
						.texture("shadow2", new ResourceLocation(Reference.MOD_ID, shadow2))
						.element()
						.from(0, 0, 0)
						.to(16, 16, 16)
						.cube("#highlight2")
						.allFaces((dir, uv) -> uv.tintindex(0))
						.end()
						.element()
						.from(0, 0, 0)
						.to(16, 16, 16)
						.cube("#highlight1")
						.allFaces((dir, uv) -> uv.tintindex(1))
						.end()
						.element()
						.from(0, 0, 0)
						.to(16, 16, 16)
						.cube("#base")
						.allFaces((dir, uv) -> uv.tintindex(2))
						.end()
						.element()
						.from(0, 0, 0)
						.to(16, 16, 16)
						.cube("#shadow1")
						.allFaces((dir, uv) -> uv.tintindex(3))
						.end()
						.element()
						.from(0, 0, 0)
						.to(16, 16, 16)
						.cube("#shadow2")
						.allFaces((dir, uv) -> uv.tintindex(4))
						.end()
						.renderType("cutout")
				)
				.child("translucent", new BlockModelBuilder("minecraft:block/block")
						.texture("drop", new ResourceLocation(Reference.MOD_ID, drop))
						.element()
						.from(0, 0, 0)
						.to(16, 16, 16)
						.cube("#drop")
						.allFaces((dir, uv) -> uv.tintindex(-1))
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
