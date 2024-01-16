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
import com.ridanisaurus.emendatusenigmatica.datagen.base.EEBlockStateProvider;
import com.ridanisaurus.emendatusenigmatica.datagen.base.BlockStateBuilder;
import com.ridanisaurus.emendatusenigmatica.datagen.base.IFinishedGenericJSON;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.function.Consumer;

public class BlockStatesGen extends EEBlockStateProvider {
	private final EmendatusDataRegistry registry;

	public BlockStatesGen(DataGenerator gen, EmendatusDataRegistry registry) {
		super(gen);
		this.registry = registry;
	}

	@Override
	protected void buildBlockState(Consumer<IFinishedGenericJSON> consumer) {
		for (MaterialModel material : registry.getMaterials()) {
			List<String> processedType = material.getProcessedTypes();

			// Storage Blocks
			if (processedType.contains("storage_block")) {
				new BlockStateBuilder()
						.variant(new BlockStateBuilder.VariantBuilder("")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/" + material.getId() + "_block").toString())
						)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_block"));
				if (material.getProperties().hasOxidization()) {
					new BlockStateBuilder()
							.variant(new BlockStateBuilder.VariantBuilder("")
									.setModel(new ResourceLocation(Reference.MOD_ID, "block/exposed_" + material.getId()).toString())
							)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "exposed_" + material.getId()));
					new BlockStateBuilder()
							.variant(new BlockStateBuilder.VariantBuilder("")
									.setModel(new ResourceLocation(Reference.MOD_ID, "block/weathered_" + material.getId()).toString())
							)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "weathered_" + material.getId()));
					new BlockStateBuilder()
							.variant(new BlockStateBuilder.VariantBuilder("")
									.setModel(new ResourceLocation(Reference.MOD_ID, "block/oxidized_" + material.getId()).toString())
							)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "oxidized_" + material.getId()));
					new BlockStateBuilder()
							.variant(new BlockStateBuilder.VariantBuilder("")
									.setModel(new ResourceLocation(Reference.MOD_ID, "block/waxed_" + material.getId() + "_block").toString())
							)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "waxed_" + material.getId() + "_block"));
					new BlockStateBuilder()
							.variant(new BlockStateBuilder.VariantBuilder("")
									.setModel(new ResourceLocation(Reference.MOD_ID, "block/waxed_exposed_" + material.getId()).toString())
							)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "waxed_exposed_" + material.getId()));
					new BlockStateBuilder()
							.variant(new BlockStateBuilder.VariantBuilder("")
									.setModel(new ResourceLocation(Reference.MOD_ID, "block/waxed_weathered_" + material.getId()).toString())
							)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "waxed_weathered_" + material.getId()));
					new BlockStateBuilder()
							.variant(new BlockStateBuilder.VariantBuilder("")
									.setModel(new ResourceLocation(Reference.MOD_ID, "block/waxed_oxidized_" + material.getId()).toString())
							)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "waxed_oxidized_" + material.getId()));
				}
			}
			// Shard Blocks
			if (processedType.contains("cluster")) {
				new BlockStateBuilder()
						.variant(new BlockStateBuilder.VariantBuilder("")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/" + material.getId() + "_cluster_shard_block").toString())
						)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_cluster_shard_block"));
				new BlockStateBuilder()
						.variant(new BlockStateBuilder.VariantBuilder("facing=down")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/small_" + material.getId() + "_bud").toString()).setX(180)
						)
						.variant(new BlockStateBuilder.VariantBuilder("facing=east")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/small_" + material.getId() + "_bud").toString()).setX(90).setY(90)
						)
						.variant(new BlockStateBuilder.VariantBuilder("facing=north")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/small_" + material.getId() + "_bud").toString()).setX(90)
						)
						.variant(new BlockStateBuilder.VariantBuilder("facing=south")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/small_" + material.getId() + "_bud").toString()).setX(90).setY(180)
						)
						.variant(new BlockStateBuilder.VariantBuilder("facing=up")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/small_" + material.getId() + "_bud").toString())
						)
						.variant(new BlockStateBuilder.VariantBuilder("facing=west")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/small_" + material.getId() + "_bud").toString()).setX(90).setY(270)
						)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "small_" + material.getId() + "_bud"));
				new BlockStateBuilder()
						.variant(new BlockStateBuilder.VariantBuilder("facing=down")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/medium_" + material.getId() + "_bud").toString()).setX(180)
						)
						.variant(new BlockStateBuilder.VariantBuilder("facing=east")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/medium_" + material.getId() + "_bud").toString()).setX(90).setY(90)
						)
						.variant(new BlockStateBuilder.VariantBuilder("facing=north")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/medium_" + material.getId() + "_bud").toString()).setX(90)
						)
						.variant(new BlockStateBuilder.VariantBuilder("facing=south")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/medium_" + material.getId() + "_bud").toString()).setX(90).setY(180)
						)
						.variant(new BlockStateBuilder.VariantBuilder("facing=up")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/medium_" + material.getId() + "_bud").toString())
						)
						.variant(new BlockStateBuilder.VariantBuilder("facing=west")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/medium_" + material.getId() + "_bud").toString()).setX(90).setY(270)
						)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "medium_" + material.getId() + "_bud"));
				new BlockStateBuilder()
						.variant(new BlockStateBuilder.VariantBuilder("facing=down")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/large_" + material.getId() + "_bud").toString()).setX(180)
						)
						.variant(new BlockStateBuilder.VariantBuilder("facing=east")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/large_" + material.getId() + "_bud").toString()).setX(90).setY(90)
						)
						.variant(new BlockStateBuilder.VariantBuilder("facing=north")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/large_" + material.getId() + "_bud").toString()).setX(90)
						)
						.variant(new BlockStateBuilder.VariantBuilder("facing=south")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/large_" + material.getId() + "_bud").toString()).setX(90).setY(180)
						)
						.variant(new BlockStateBuilder.VariantBuilder("facing=up")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/large_" + material.getId() + "_bud").toString())
						)
						.variant(new BlockStateBuilder.VariantBuilder("facing=west")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/large_" + material.getId() + "_bud").toString()).setX(90).setY(270)
						)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "large_" + material.getId() + "_bud"));
				new BlockStateBuilder()
						.variant(new BlockStateBuilder.VariantBuilder("facing=down")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/" + material.getId() + "_cluster").toString()).setX(180)
						)
						.variant(new BlockStateBuilder.VariantBuilder("facing=east")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/" + material.getId() + "_cluster").toString()).setX(90).setY(90)
						)
						.variant(new BlockStateBuilder.VariantBuilder("facing=north")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/" + material.getId() + "_cluster").toString()).setX(90)
						)
						.variant(new BlockStateBuilder.VariantBuilder("facing=south")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/" + material.getId() + "_cluster").toString()).setX(90).setY(180)
						)
						.variant(new BlockStateBuilder.VariantBuilder("facing=up")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/" + material.getId() + "_cluster").toString())
						)
						.variant(new BlockStateBuilder.VariantBuilder("facing=west")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/" + material.getId() + "_cluster").toString()).setX(90).setY(270)
						)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_cluster"));
				new BlockStateBuilder()
						.variant(new BlockStateBuilder.VariantBuilder("")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/budding_" + material.getId()).toString()))
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "budding_" + material.getId()));
			}
			// Raw Blocks
			if (processedType.contains("raw")) {
				new BlockStateBuilder()
						.variant(new BlockStateBuilder.VariantBuilder("")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/raw_" + material.getId() + "_block").toString()))
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "raw_" + material.getId() + "_block"));
			}
			// Fluids
			if (processedType.contains("fluid")) {
				new BlockStateBuilder()
						.variant(new BlockStateBuilder.VariantBuilder("")
								.setModel(new ResourceLocation(Reference.MOD_ID, "block/" + material.getId()).toString()))
						.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId()));
			}
			// Ores
			for (StrataModel stratum : registry.getStrata()) {
				if (processedType.contains("ore")) {
					new BlockStateBuilder()
							.variant(new BlockStateBuilder.VariantBuilder("")
									.setModel(new ResourceLocation(Reference.MOD_ID, "block/" + getOreModelName(stratum, material)).toString()))
							.save(consumer, new ResourceLocation(Reference.MOD_ID, getOreModelName(stratum, material)));
					if (processedType.contains("sample")) {
						new BlockStateBuilder()
								.variant(new BlockStateBuilder.VariantBuilder("")
										.setModel(new ResourceLocation(Reference.MOD_ID, "block/" + getSampleModelName(stratum, material)).toString()))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, getSampleModelName(stratum, material)));
					}
				}
			}
		}
	}

	public static String getOreModelName(StrataModel stratum, MaterialModel material) {
		return material.getId() + (!stratum.getId().equals("minecraft_stone") ? "_" + stratum.getSuffix() : "") + "_ore";
	}

	public static String getSampleModelName(StrataModel stratum, MaterialModel material) {
		return material.getId() + "_" + stratum.getSuffix() + "_ore_sample";
	}

	@Override
	public String getName() {
		return "Emendatus Enigmatica Blockstates";
	}
}