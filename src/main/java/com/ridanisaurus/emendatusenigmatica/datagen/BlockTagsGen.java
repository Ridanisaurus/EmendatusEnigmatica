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
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.List;

import net.minecraft.data.tags.TagsProvider.TagAppender;

public class BlockTagsGen extends BlockTagsProvider {

	public BlockTagsGen(DataGenerator gen, ExistingFileHelper existingFileHelper) {
		super(gen, Reference.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		TagAppender<Block> forgeBlocks = tag(BlockTags.create(new ResourceLocation(Reference.FORGE, "storage_blocks")));
		TagAppender<Block> forgeOres = tag(BlockTags.create(new ResourceLocation(Reference.FORGE, "ores")));
		TagAppender<Block> beaconBlocks = tag(BlockTags.create(new ResourceLocation(Reference.MINECRAFT, "beacon_base_blocks")));

		// Storage Blocks
		for (MaterialModel material : EELoader.MATERIALS) {
			List<String> processedType = material.getProcessedType();
			if (processedType.contains("storage_block")) {
				forgeBlocks.add(EERegistrar.storageBlockMap.get(material.getId()).get());
				beaconBlocks.add(EERegistrar.storageBlockMap.get(material.getId()).get());
				TagAppender<Block> storageBlockTag = tag(BlockTags.create(new ResourceLocation(Reference.FORGE, "storage_blocks/" + material.getId())));
				storageBlockTag.add(EERegistrar.storageBlockMap.get(material.getId()).get());
			}
			// Ores
			for (StrataModel stratum : EELoader.STRATA) {
				if (processedType.contains("ore")) {
					forgeOres.add(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get());
					TagAppender<Block> oreTag = tag(BlockTags.create(new ResourceLocation(Reference.FORGE, "ores/" + material.getId())));
					oreTag.add(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get());
				}
			}
		}
	}

	@Override
	public String getName() {
		return "Emendatus Enigmatica Block Tags";
	}
}