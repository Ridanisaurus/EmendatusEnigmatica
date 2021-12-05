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

import net.minecraft.data.TagsProvider.Builder;

public class ItemTagsGen extends ItemTagsProvider {

	public ItemTagsGen(DataGenerator gen, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
		super(gen, blockTagProvider, Reference.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		// Forge Tags
		Builder<Item> forgeBlocks = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks").toString()));
		Builder<Item> forgeIngots = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "ingots").toString()));
		Builder<Item> forgeGems = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "gems").toString()));
		Builder<Item> forgeNuggets = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "nuggets").toString()));
		Builder<Item> forgeDusts = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "dusts").toString()));
		Builder<Item> forgePlates = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "plates").toString()));
		Builder<Item> forgeGears = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "gears").toString()));
		Builder<Item> forgeRods = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "rods").toString()));
		Builder<Item> forgeChunks = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "chunks").toString()));
		Builder<Item> forgeClusters = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "clusters").toString()));
		Builder<Item> forgeOres = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "ores").toString()));
		Builder<Item> forgeBuckets = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "buckets").toString()));

		Builder<Item> beaconIngots = tag(ItemTags.bind(new ResourceLocation(Reference.MINECRAFT_TAG, "beacon_payment_items").toString()));
		Builder<Item> piglinLoved = tag(ItemTags.bind(new ResourceLocation(Reference.MINECRAFT_TAG, "piglin_loved").toString()));

		for (MaterialModel material : EELoader.MATERIALS) {
			List<String> processedType = material.getProcessedType();

			// Storage Blocks
			if (processedType.contains("storage_block")) {
				forgeBlocks.add(EERegistrar.storageBlockItemMap.get(material.getId()).get());
				Builder<Item> storageBlockTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/" + material.getId()).toString()));
				storageBlockTag.add(EERegistrar.storageBlockItemMap.get(material.getId()).get());
			}
			// Ingots
			if (processedType.contains("ingot")) {
				forgeIngots.add(EERegistrar.ingotMap.get(material.getId()).get());
				beaconIngots.add(EERegistrar.ingotMap.get(material.getId()).get());
				Builder<Item> ingotTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "ingots/" + material.getId()).toString()));
				ingotTag.add(EERegistrar.ingotMap.get(material.getId()).get());
			}
			// Gems
			if (processedType.contains("gem")) {
				forgeGems.add(EERegistrar.gemMap.get(material.getId()).get());
				Builder<Item> gemTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "gems/" + material.getId()).toString()));
				gemTag.add(EERegistrar.gemMap.get(material.getId()).get());
			}
			// Nuggets
			if (processedType.contains("nugget")) {
				forgeNuggets.add(EERegistrar.nuggetMap.get(material.getId()).get());
				Builder<Item> nuggetTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "nuggets/" + material.getId()).toString()));
				nuggetTag.add(EERegistrar.nuggetMap.get(material.getId()).get());
			}
			// Dusts
			if (processedType.contains("dust")) {
				forgeDusts.add(EERegistrar.dustMap.get(material.getId()).get());
				Builder<Item> dustTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "dusts/" + material.getId()).toString()));
				dustTag.add(EERegistrar.dustMap.get(material.getId()).get());
				if (material.getId().equals("nether_quartz")) {
					tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "dusts/quartz").toString()));
				}
			}
			// Plates
			if (processedType.contains("plate")) {
				forgePlates.add(EERegistrar.plateMap.get(material.getId()).get());
				Builder<Item> plateTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "plates/" + material.getId()).toString()));
				plateTag.add(EERegistrar.plateMap.get(material.getId()).get());
			}
			// Gears
			if (processedType.contains("gear")) {
				forgeGears.add(EERegistrar.gearMap.get(material.getId()).get());
				Builder<Item> gearTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "gears/" + material.getId()).toString()));
				gearTag.add(EERegistrar.gearMap.get(material.getId()).get());
			}
			// Rods
			if (processedType.contains("rod")) {
				forgeRods.add(EERegistrar.rodMap.get(material.getId()).get());
				Builder<Item> rodTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "rods/" + material.getId()).toString()));
				rodTag.add(EERegistrar.rodMap.get(material.getId()).get());
			}
			// Chunks
			if (processedType.contains("chunk")) {
				forgeChunks.add(EERegistrar.chunkMap.get(material.getId()).get());
				forgeOres.add(EERegistrar.chunkMap.get(material.getId()).get());
				Builder<Item> chunkTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "chunks/" + material.getId()).toString()));
				Builder<Item> oreTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "ores/" + material.getId()).toString()));
				chunkTag.add(EERegistrar.chunkMap.get(material.getId()).get());
				oreTag.add(EERegistrar.chunkMap.get(material.getId()).get());
			}
			// Clusters
			if (processedType.contains("cluster")) {
				forgeClusters.add(EERegistrar.clusterMap.get(material.getId()).get());
				Builder<Item> clusterTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "clusters/" + material.getId()).toString()));
				Builder<Item> oreClusterTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "ores_cluster/" + material.getId()).toString()));
				clusterTag.add(EERegistrar.clusterMap.get(material.getId()).get());
				oreClusterTag.add(EERegistrar.clusterMap.get(material.getId()).get());
			}
			// Fluid Bucket
			if (processedType.contains("fluid")) {
				forgeBuckets.add(EERegistrar.fluidBucketMap.get(material.getId()).get());
				Builder<Item> bucketTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "buckets/" + material.getId()).toString()));
				bucketTag.add(EERegistrar.fluidBucketMap.get(material.getId()).get());
			}
			// Piglin Loved
			if (material.getId().equals("gold")) {
				piglinLoved.add(EERegistrar.dustMap.get(material.getId()).get());
				piglinLoved.add(EERegistrar.plateMap.get(material.getId()).get());
				piglinLoved.add(EERegistrar.gearMap.get(material.getId()).get());
				piglinLoved.add(EERegistrar.rodMap.get(material.getId()).get());
				piglinLoved.add(EERegistrar.chunkMap.get(material.getId()).get());
			}
			// Silicon
			if (material.getId().equals("silicon")) {
				tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "silicon").toString()))
						.add(EERegistrar.gemMap.get(material.getId()).get());
			}
			// Coal Coke
			if (material.getId().equals("arcane")) {
				tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "gems/mana").toString()))
						.add(EERegistrar.gemMap.get(material.getId()).get());
			}
			// AE2 Tags
			if (material.getId().equals("fluix")) {
				tag(ItemTags.bind(new ResourceLocation(Reference.AE2_TAG, "crystals/fluix").toString()))
						.add(EERegistrar.gemMap.get(material.getId()).get());
				tag(ItemTags.bind(new ResourceLocation(Reference.AE2_TAG, "dusts/fluix").toString()))
						.add(EERegistrar.dustMap.get(material.getId()).get());
			}
			if (material.getId().equals("nether_quartz")) {
				tag(ItemTags.bind(new ResourceLocation(Reference.AE2_TAG, "dusts/nether_quartz_dust").toString()))
						.add(EERegistrar.dustMap.get(material.getId()).get());
				tag(ItemTags.bind(new ResourceLocation(Reference.AE2_TAG, "dusts/quartz").toString()))
						.add(EERegistrar.dustMap.get(material.getId()).get());
			}

			// Potassium Nitrate Compact
			if (material.getId().equals("potassium_nitrate")) {
				tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "dusts/niter").toString()))
						.add(EERegistrar.dustMap.get(material.getId()).get());
				tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "dusts/saltpeter").toString()))
						.add(EERegistrar.dustMap.get(material.getId()).get());
				tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "gems/niter").toString()))
						.add(EERegistrar.gemMap.get(material.getId()).get());
				tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "gems/saltpeter").toString()))
						.add(EERegistrar.gemMap.get(material.getId()).get());
				tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/niter").toString()))
						.add(EERegistrar.storageBlockItemMap.get(material.getId()).get());
				tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/saltpeter").toString()))
						.add(EERegistrar.storageBlockItemMap.get(material.getId()).get());
			}
		}

		for (MaterialModel material : EELoader.MATERIALS) {
			for (StrataModel stratum : EELoader.STRATA) {
				List<String> processedType = material.getProcessedType();
				// Ores
				if (processedType.contains("ore")) {
					forgeOres.add(EERegistrar.oreBlockItemTable.get(stratum.getId(), material.getId()).get());
					Builder<Item> oreTag = tag(ItemTags.bind(new ResourceLocation(Reference.FORGE_TAG, "ores/" + material.getId()).toString()));
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