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

import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockHarvestTagsGen {

	public static class BlockHarvestLevelTagsGen extends BlockTagsProvider {
		public BlockHarvestLevelTagsGen(DataGenerator gen, @Nullable ExistingFileHelper existingFileHelper) {
			super(gen, Reference.MOD_ID, existingFileHelper);
		}

		@Override
		protected void addTags() {
			TagAppender<Block> woodTool = tag(Tags.Blocks.NEEDS_WOOD_TOOL);
			TagAppender<Block> stoneTool = tag(BlockTags.NEEDS_STONE_TOOL);
			TagAppender<Block> ironTool = tag(BlockTags.NEEDS_IRON_TOOL);
			TagAppender<Block> diamondTool = tag(BlockTags.NEEDS_DIAMOND_TOOL);
			TagAppender<Block> goldTool = tag(Tags.Blocks.NEEDS_GOLD_TOOL);
			TagAppender<Block> netheriteTool = tag(Tags.Blocks.NEEDS_NETHERITE_TOOL);

			for (MaterialModel material : EELoader.MATERIALS) {
				List<String> processedType = material.getProcessedType();
				if(processedType.contains("storage_block")) {
					switch (material.getProperties().getHarvestLevel()) {
						case 0 -> {
							woodTool.add(EERegistrar.storageBlockMap.get(material.getId()).get());
							goldTool.add(EERegistrar.storageBlockMap.get(material.getId()).get());
						}
						case 1 -> stoneTool.add(EERegistrar.storageBlockMap.get(material.getId()).get());
						case 2 -> ironTool.add(EERegistrar.storageBlockMap.get(material.getId()).get());
						case 3 -> diamondTool.add(EERegistrar.storageBlockMap.get(material.getId()).get());
						case 4 -> netheriteTool.add(EERegistrar.storageBlockMap.get(material.getId()).get());
						default -> throw new IllegalStateException("Harvest level " + material.getProperties().getHarvestLevel() + " for " + material.getId() + " is out of Vanilla tier system bounds, and the tag should be added manually");
					}
				}
				for (StrataModel stratum : EELoader.STRATA) {
					if(processedType.contains("ore")) {
						switch (material.getProperties().getHarvestLevel()) {
							case 0 -> {
								woodTool.add(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get());
								goldTool.add(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get());
							}
							case 1 -> stoneTool.add(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get());
							case 2 -> ironTool.add(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get());
							case 3 -> diamondTool.add(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get());
							case 4 -> netheriteTool.add(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get());
							default -> throw new IllegalStateException("Harvest level " + material.getProperties().getHarvestLevel() + " for " + material.getId() + " is out of Vanilla tier system bounds, and the tag should be added manually");
						}
					}
				}
			}
		}

		@Override
		public String getName() {
			return "Emendatus Enigmatica Block Harvest Level Tags";
		}
	}

	public static class BlockHarvestToolTagsGen extends BlockTagsProvider {
		public BlockHarvestToolTagsGen(DataGenerator gen, @Nullable ExistingFileHelper existingFileHelper) {
			super(gen, Reference.MOD_ID, existingFileHelper);
		}

		@Override
		protected void addTags() {
			TagAppender<Block> shovel = tag(BlockTags.MINEABLE_WITH_SHOVEL);
			TagAppender<Block> hoe = tag(BlockTags.MINEABLE_WITH_HOE);
			TagAppender<Block> axe = tag(BlockTags.MINEABLE_WITH_AXE);
			TagAppender<Block> pickaxe = tag(BlockTags.MINEABLE_WITH_PICKAXE);

			for (MaterialModel material : EELoader.MATERIALS) {
				List<String> processedType = material.getProcessedType();
				for (StrataModel stratum : EELoader.STRATA) {
					if(processedType.contains("ore")) {
						switch (stratum.getHarvestTool()) {
							case "shovel" -> shovel.add(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get());
							case "hoe" -> hoe.add(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get());
							case "axe" -> axe.add(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get());
							case "pickaxe" -> pickaxe.add(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get());
							default -> throw new IllegalStateException("Harvest tool " + stratum.getHarvestTool() + " for " + stratum.getId() + " is out of Vanilla tool system bounds, and the tag should be added manually");
						}
					}
				}
			}
		}

		@Override
		public String getName() {
			return "Emendatus Enigmatica Block Harvest Tool Tags";
		}
	}

}
