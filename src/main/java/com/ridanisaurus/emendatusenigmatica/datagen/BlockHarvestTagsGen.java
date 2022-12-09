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

import com.google.common.collect.Lists;
import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.datagen.base.EETagProvider;
import com.ridanisaurus.emendatusenigmatica.datagen.base.IFinishedGenericJSON;
import com.ridanisaurus.emendatusenigmatica.datagen.base.TagBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.function.Consumer;

public class BlockHarvestTagsGen {

	public static class BlockHarvestLevelTagsGen extends EETagProvider {
		private final EmendatusDataRegistry registry;

		public BlockHarvestLevelTagsGen(DataGenerator gen, EmendatusDataRegistry registry) {
			super(gen);
			this.registry = registry;
		}

		private final List<String> woodTool = Lists.newArrayList();
		private final List<String> stoneTool = Lists.newArrayList();
		private final List<String> ironTool = Lists.newArrayList();
		private final List<String> diamondTool = Lists.newArrayList();
		private final List<String> netheriteTool = Lists.newArrayList();

		@Override
		protected void buildTags(Consumer<IFinishedGenericJSON> consumer) {
			for (MaterialModel material : registry.getMaterials()) {
				List<String> processedType = material.getProcessedTypes();
				if (processedType.contains("storage_block")) {
					ResourceLocation block = EERegistrar.storageBlockMap.get(material.getId()).getId();
					harvestLevelSwitch(material, block);
				}
				if (processedType.contains("raw")) {
					ResourceLocation raw = EERegistrar.rawBlockMap.get(material.getId()).getId();
					harvestLevelSwitch(material, raw);
				}
				for (StrataModel stratum : registry.getStrata()) {
					if (processedType.contains("ore")) {
						ResourceLocation ore = EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).getId();
						harvestLevelSwitch(material, ore);
					}
				}
			}
			if (!woodTool.isEmpty()) new TagBuilder().tags(woodTool).save(consumer, new ResourceLocation(Reference.FORGE, "/blocks/needs_wood_tool"));
			if (!stoneTool.isEmpty()) new TagBuilder().tags(stoneTool).save(consumer, new ResourceLocation(Reference.MINECRAFT, "/blocks/needs_stone_tool"));
			if (!ironTool.isEmpty()) new TagBuilder().tags(ironTool).save(consumer, new ResourceLocation(Reference.MINECRAFT, "/blocks/needs_iron_tool"));
			if (!diamondTool.isEmpty()) new TagBuilder().tags(diamondTool).save(consumer, new ResourceLocation(Reference.MINECRAFT, "/blocks/needs_diamond_tool"));
			if (!netheriteTool.isEmpty()) new TagBuilder().tags(netheriteTool).save(consumer, new ResourceLocation(Reference.FORGE, "/blocks/needs_netherite_tool"));
		}

		private void harvestLevelSwitch(MaterialModel material, ResourceLocation loc) {
			switch (material.getProperties().getHarvestLevel()) {
				case 0 -> woodTool.add(loc.toString());
				case 1 -> stoneTool.add(loc.toString());
				case 2 -> ironTool.add(loc.toString());
				case 3 -> diamondTool.add(loc.toString());
				case 4 -> netheriteTool.add(loc.toString());
				default -> throw new IllegalStateException("Harvest level " + material.getProperties().getHarvestLevel() + " for " + material.getId() + " is out of Vanilla tier system bounds, and the tag should be added manually");
			}
		}

		@Override
		public String getName() {
			return "Emendatus Enigmatica Block Harvest Level Tags";
		}
	}

	public static class BlockHarvestToolTagsGen extends EETagProvider {
		private final EmendatusDataRegistry registry;

		public BlockHarvestToolTagsGen(DataGenerator gen, EmendatusDataRegistry registry) {
			super(gen);
			this.registry = registry;
		}

		private final List<String> shovel = Lists.newArrayList();
		private final List<String> hoe = Lists.newArrayList();
		private final List<String> axe = Lists.newArrayList();
		private final List<String> pickaxe = Lists.newArrayList();

		@Override
		protected void buildTags(Consumer<IFinishedGenericJSON> consumer) {
			for (MaterialModel material : registry.getMaterials()) {
				List<String> processedType = material.getProcessedTypes();
				if(processedType.contains("storage_block")) {
					ResourceLocation block = EERegistrar.storageBlockMap.get(material.getId()).getId();
					pickaxe.add(block.toString());
				}
				if(processedType.contains("raw")) {
					ResourceLocation raw = EERegistrar.rawBlockMap.get(material.getId()).getId();
					pickaxe.add(raw.toString());
				}
				for (StrataModel stratum : registry.getStrata()) {
					if(processedType.contains("ore")) {
						ResourceLocation ore = EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).getId();
						switch (stratum.getHarvestTool()) {
							case "shovel" -> shovel.add(ore.toString());
							case "hoe" -> hoe.add(ore.toString());
							case "axe" -> axe.add(ore.toString());
							case "pickaxe" -> pickaxe.add(ore.toString());
							default -> throw new IllegalStateException("Harvest tool " + stratum.getHarvestTool() + " for " + stratum.getId() + " is out of Vanilla tool system bounds, and the tag should be added manually");
						}
					}
				}
			}
			if (!shovel.isEmpty()) new TagBuilder().tags(shovel).save(consumer, new ResourceLocation(Reference.MINECRAFT, "/blocks/mineable/shovel"));
			if (!hoe.isEmpty()) new TagBuilder().tags(hoe).save(consumer, new ResourceLocation(Reference.MINECRAFT, "/blocks/mineable/hoe"));
			if (!axe.isEmpty()) new TagBuilder().tags(axe).save(consumer, new ResourceLocation(Reference.MINECRAFT, "/blocks/mineable/axe"));
			if (!pickaxe.isEmpty()) new TagBuilder().tags(pickaxe).save(consumer, new ResourceLocation(Reference.MINECRAFT, "/blocks/mineable/pickaxe"));
		}

		@Override
		public String getName() {
			return "Emendatus Enigmatica Block Harvest Tool Tags";
		}
	}
}