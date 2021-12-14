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
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.List;

public class ItemTagsGen extends ItemTagsProvider {

	public ItemTagsGen(DataGenerator gen, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
		super(gen, blockTagProvider, Reference.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		// Forge Tags
		Builder<Item> forgeBlocks = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE, "storage_blocks").toString()));
		Builder<Item> forgeIngots = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE, "ingots").toString()));
		Builder<Item> forgeGems = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE, "gems").toString()));
		Builder<Item> forgeNuggets = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE, "nuggets").toString()));
		Builder<Item> forgeDusts = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE, "dusts").toString()));
		Builder<Item> forgePlates = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE, "plates").toString()));
		Builder<Item> forgeGears = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE, "gears").toString()));
		Builder<Item> forgeRods = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE, "rods").toString()));
		Builder<Item> forgeRaw = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE, "raws").toString()));
//		Builder<Item> forgeClusters = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE, "clusters").toString()));
		Builder<Item> forgeOres = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE, "ores").toString()));
		Builder<Item> forgeBuckets = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE, "buckets").toString()));

		Builder<Item> beaconIngots = tag(ItemTags.bind(new ResourceLocation(Reference.MINECRAFT, "beacon_payment_items").toString()));
		Builder<Item> piglinLoved = tag(ItemTags.bind(new ResourceLocation(Reference.MINECRAFT, "piglin_loved").toString()));

		for (MaterialModel material : EELoader.MATERIALS) {
			List<String> processedType = material.getProcessedType();

			// Storage Blocks
			if (processedType.contains("storage_block")) {
				forgeBlocks.add(EERegistrar.storageBlockItemMap.get(material.getId()).get());
				Builder<Item> storageBlockTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE, "storage_blocks/" + material.getId()).toString()));
				storageBlockTag.add(EERegistrar.storageBlockItemMap.get(material.getId()).get());
			}
			// Ingots
			if (processedType.contains("ingot")) {
				forgeIngots.add(EERegistrar.ingotMap.get(material.getId()).get());
				beaconIngots.add(EERegistrar.ingotMap.get(material.getId()).get());
				Builder<Item> ingotTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE, "ingots/" + material.getId()).toString()));
				ingotTag.add(EERegistrar.ingotMap.get(material.getId()).get());
			}
			// Gems
			if (processedType.contains("gem")) {
				forgeGems.add(EERegistrar.gemMap.get(material.getId()).get());
				Builder<Item> gemTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE, "gems/" + material.getId()).toString()));
				gemTag.add(EERegistrar.gemMap.get(material.getId()).get());
			}
			// Nuggets
			if (processedType.contains("nugget")) {
				forgeNuggets.add(EERegistrar.nuggetMap.get(material.getId()).get());
				Builder<Item> nuggetTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE, "nuggets/" + material.getId()).toString()));
				nuggetTag.add(EERegistrar.nuggetMap.get(material.getId()).get());
			}
			// Dusts
			if (processedType.contains("dust")) {
				forgeDusts.add(EERegistrar.dustMap.get(material.getId()).get());
				Builder<Item> dustTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE, "dusts/" + material.getId()).toString()));
				dustTag.add(EERegistrar.dustMap.get(material.getId()).get());
				if (material.getId().equals("nether_quartz")) {
					tag(ItemTags.bind(new ResourceLocation(Reference.FORGE, "dusts/quartz").toString()));
				}
			}
			// Plates
			if (processedType.contains("plate")) {
				forgePlates.add(EERegistrar.plateMap.get(material.getId()).get());
				Builder<Item> plateTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE, "plates/" + material.getId()).toString()));
				plateTag.add(EERegistrar.plateMap.get(material.getId()).get());
			}
			// Gears
			if (processedType.contains("gear")) {
				forgeGears.add(EERegistrar.gearMap.get(material.getId()).get());
				Builder<Item> gearTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE, "gears/" + material.getId()).toString()));
				gearTag.add(EERegistrar.gearMap.get(material.getId()).get());
			}
			// Rods
			if (processedType.contains("rod")) {
				forgeRods.add(EERegistrar.rodMap.get(material.getId()).get());
				Builder<Item> rodTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE, "rods/" + material.getId()).toString()));
				rodTag.add(EERegistrar.rodMap.get(material.getId()).get());
			}
			// Chunks
			if (processedType.contains("raw")) {
				forgeRaw.add(EERegistrar.rawMap.get(material.getId()).get());
				forgeOres.add(EERegistrar.rawMap.get(material.getId()).get());
				Builder<Item> rawTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE, "raws/" + material.getId()).toString()));
				Builder<Item> oreTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE, "ores/" + material.getId()).toString()));
				rawTag.add(EERegistrar.rawMap.get(material.getId()).get());
				oreTag.add(EERegistrar.rawMap.get(material.getId()).get());
			}
//			// Clusters
//			if (processedType.contains("cluster")) {
//				forgeClusters.add(EERegistrar.clusterMap.get(material.getId()).get());
//				Builder<Item> clusterTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE, "clusters/" + material.getId()).toString()));
//				Builder<Item> oreClusterTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE, "ores_cluster/" + material.getId()).toString()));
//				clusterTag.add(EERegistrar.clusterMap.get(material.getId()).get());
//				oreClusterTag.add(EERegistrar.clusterMap.get(material.getId()).get());
//			}
			// Fluid Bucket
			if (processedType.contains("fluid")) {
				forgeBuckets.add(EERegistrar.fluidBucketMap.get(material.getId()).get());
				Builder<Item> bucketTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE, "buckets/" + material.getId()).toString()));
				bucketTag.add(EERegistrar.fluidBucketMap.get(material.getId()).get());
			}
			// Piglin Loved
			if (material.getId().equals("gold")) {
				piglinLoved.add(EERegistrar.dustMap.get(material.getId()).get());
				piglinLoved.add(EERegistrar.plateMap.get(material.getId()).get());
				piglinLoved.add(EERegistrar.gearMap.get(material.getId()).get());
				piglinLoved.add(EERegistrar.rodMap.get(material.getId()).get());
				piglinLoved.add(EERegistrar.rawMap.get(material.getId()).get());
			}
		}

		for (MaterialModel material : EELoader.MATERIALS) {
			for (StrataModel stratum : EELoader.STRATA) {
				List<String> processedType = material.getProcessedType();
				// Ores
				if (processedType.contains("ore")) {
					forgeOres.add(EERegistrar.oreBlockItemTable.get(stratum.getId(), material.getId()).get());
					Builder<Item> oreTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE, "ores/" + material.getId()).toString()));
					oreTag.add(EERegistrar.oreBlockItemTable.get(stratum.getId(), material.getId()).get());
				}
			}
		}
	}

	@Override
	public String getName() {
		return "Emendatus Enigmatica Item Tags";
	}
}