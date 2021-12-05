/*
 * MIT License
 *
 * Copyright (c) 2020 Ridanisaurus
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software").toString(), to deal
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

package com.ridanisaurus.emendatusenigmatica.datagen;

import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.List;

import net.minecraft.data.TagsProvider.Builder;

public class BlockTagsGen extends BlockTagsProvider {

	public BlockTagsGen(DataGenerator gen, ExistingFileHelper existingFileHelper) {
		super(gen, Reference.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		Builder<Block> forgeBlocks = tag(BlockTags.bind(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks").toString()));
		Builder<Block> forgeOres = tag(BlockTags.bind(new ResourceLocation(Reference.FORGE_TAG, "ores").toString()));
		Builder<Block> beaconBlocks = tag(BlockTags.bind(new ResourceLocation(Reference.MINECRAFT_TAG, "beacon_base_blocks").toString()));

		// Storage Blocks
		for (MaterialModel material : EELoader.MATERIALS) {
			List<String> processedType = material.getProcessedType();
			if (processedType.contains("storage_block")) {
				forgeBlocks.add(EERegistrar.storageBlockMap.get(material.getId()).get());
				beaconBlocks.add(EERegistrar.storageBlockMap.get(material.getId()).get());
				Builder<Block> storageBlockTag = tag(BlockTags.bind(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/" + material.getId()).toString()));
				storageBlockTag.add(EERegistrar.storageBlockMap.get(material.getId()).get());
			}
			// Potassium Nitrate
			if (material.getId().equals("potassium_nitrate")) {
				tag(BlockTags.bind(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/niter").toString()))
						.add(EERegistrar.storageBlockMap.get(material.getId()).get());
				tag(BlockTags.bind(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/saltpeter").toString()))
						.add(EERegistrar.storageBlockMap.get(material.getId()).get());
			}
		}

		// Ores
		for (MaterialModel material : EELoader.MATERIALS) {
			for (StrataModel stratum : EELoader.STRATA) {
				List<String> processedType = material.getProcessedType();
				// Ores
				if (processedType.contains("ore")) {
					forgeOres.add(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get());
					Builder<Block> oreTag = tag(BlockTags.bind(new ResourceLocation(Reference.FORGE_TAG, "ores/" + material.getId()).toString()));
					oreTag.add(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get());
				}
			}
		}

		for (MaterialModel material : EELoader.MATERIALS) {
			List<String> processedType = material.getProcessedType();
			if (processedType.contains("storage_block")) {
				forgeBlocks.add(EERegistrar.storageBlockMap.get(material.getId()).get());
				Builder<Block> storageBlockTag = tag(BlockTags.bind(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/" + material.getId()).toString()));
				storageBlockTag.add(EERegistrar.storageBlockMap.get(material.getId()).get());
			}
		}

		// Misc
		tag(BlockTags.bind(new ResourceLocation(Reference.MOD_ID, "misc/enigmatic_fortunizer").toString()))
				.add(EERegistrar.ENIGMATIC_FORTUNIZER.get());
	}

	@Override
	public String getName() {
		return "Emendatus Enigmatica Block Tags";
	}
}