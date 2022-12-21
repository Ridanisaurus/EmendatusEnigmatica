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
						.variant(new BlockStateBuilder.objectBuilder(false)
								.model(new ResourceLocation(Reference.MOD_ID, "block/" + material.getId() + "_block").toString()))
						.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_block"));
			}
			// Raw Blocks
			if (processedType.contains("raw")) {
				new BlockStateBuilder()
						.variant(new BlockStateBuilder.objectBuilder(false)
								.model(new ResourceLocation(Reference.MOD_ID, "block/raw_" + material.getId() + "_block").toString()))
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "raw_" + material.getId() + "_block"));
			}
			// Fluids
			if (processedType.contains("fluid")) {
				new BlockStateBuilder()
						.variant(new BlockStateBuilder.objectBuilder(false)
								.model(new ResourceLocation(Reference.MOD_ID, "block/" + material.getId()).toString()))
						.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId()));
			}
			// Ores
			for (StrataModel stratum : registry.getStrata()) {
				if (processedType.contains("ore")) {
					new BlockStateBuilder()
							.variant(new BlockStateBuilder.objectBuilder(false)
									.model(new ResourceLocation(Reference.MOD_ID, "block/" + getOreModelName(stratum, material)).toString()))
							.save(consumer, new ResourceLocation(Reference.MOD_ID, getOreModelName(stratum, material)));
				}
				if (processedType.contains("ore") && stratum.getSampleStrata()) {
					new BlockStateBuilder()
							.variant(new BlockStateBuilder.objectBuilder(false)
									.model(new ResourceLocation(Reference.MOD_ID, "block/" + getOreSampleModelName(stratum, material)).toString()))
							.save(consumer, new ResourceLocation(Reference.MOD_ID, getOreSampleModelName(stratum, material)));
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

	@Override
	public String getName() {
		return "Emendatus Enigmatica Blockstates";
	}
}