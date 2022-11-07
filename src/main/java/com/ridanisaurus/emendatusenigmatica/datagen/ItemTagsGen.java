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
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.List;

import net.minecraft.data.tags.TagsProvider.TagAppender;

public class ItemTagsGen extends ItemTagsProvider {

	public ItemTagsGen(DataGenerator gen, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
		super(gen, blockTagProvider, Reference.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		// Forge Tags
		TagAppender<Item> forgeBlocks = tag(ItemTags.create(new ResourceLocation(Reference.FORGE, "storage_blocks")));
		TagAppender<Item> forgeIngots = tag(ItemTags.create(new ResourceLocation(Reference.FORGE, "ingots")));
		TagAppender<Item> forgeGems = tag(ItemTags.create(new ResourceLocation(Reference.FORGE, "gems")));
		TagAppender<Item> forgeNuggets = tag(ItemTags.create(new ResourceLocation(Reference.FORGE, "nuggets")));
		TagAppender<Item> forgeDusts = tag(ItemTags.create(new ResourceLocation(Reference.FORGE, "dusts")));
		TagAppender<Item> forgePlates = tag(ItemTags.create(new ResourceLocation(Reference.FORGE, "plates")));
		TagAppender<Item> forgeGears = tag(ItemTags.create(new ResourceLocation(Reference.FORGE, "gears")));
		TagAppender<Item> forgeRods = tag(ItemTags.create(new ResourceLocation(Reference.FORGE, "rods")));
		TagAppender<Item> forgeRaw = tag(ItemTags.create(new ResourceLocation(Reference.FORGE, "raws")));
		TagAppender<Item> forgeOres = tag(ItemTags.create(new ResourceLocation(Reference.FORGE, "ores")));
		TagAppender<Item> forgeBuckets = tag(ItemTags.create(new ResourceLocation(Reference.FORGE, "buckets")));

		TagAppender<Item> beaconIngots = tag(ItemTags.create(new ResourceLocation(Reference.MINECRAFT, "beacon_payment_items")));
		TagAppender<Item> piglinLoved = tag(ItemTags.create(new ResourceLocation(Reference.MINECRAFT, "piglin_loved")));

		for (MaterialModel material : EELoader.MATERIALS) {
			List<String> processedType = material.getProcessedType();

			// Storage Blocks
			if (processedType.contains("storage_block")) {
				forgeBlocks.add(EERegistrar.storageBlockItemMap.get(material.getId()).get());
				TagAppender<Item> storageBlockTag = tag(ItemTags.create(new ResourceLocation(Reference.FORGE, "storage_blocks/" + material.getId())));
				storageBlockTag.add(EERegistrar.storageBlockItemMap.get(material.getId()).get());
			}
			// Ingots
			if (processedType.contains("ingot")) {
				forgeIngots.add(EERegistrar.ingotMap.get(material.getId()).get());
				beaconIngots.add(EERegistrar.ingotMap.get(material.getId()).get());
				TagAppender<Item> ingotTag = tag(ItemTags.create(new ResourceLocation(Reference.FORGE, "ingots/" + material.getId())));
				ingotTag.add(EERegistrar.ingotMap.get(material.getId()).get());
			}
			// Gems
			if (processedType.contains("gem")) {
				forgeGems.add(EERegistrar.gemMap.get(material.getId()).get());
				TagAppender<Item> gemTag = tag(ItemTags.create(new ResourceLocation(Reference.FORGE, "gems/" + material.getId())));
				gemTag.add(EERegistrar.gemMap.get(material.getId()).get());
			}
			// Nuggets
			if (processedType.contains("nugget")) {
				forgeNuggets.add(EERegistrar.nuggetMap.get(material.getId()).get());
				TagAppender<Item> nuggetTag = tag(ItemTags.create(new ResourceLocation(Reference.FORGE, "nuggets/" + material.getId())));
				nuggetTag.add(EERegistrar.nuggetMap.get(material.getId()).get());
			}
			// Dusts
			if (processedType.contains("dust")) {
				forgeDusts.add(EERegistrar.dustMap.get(material.getId()).get());
				TagAppender<Item> dustTag = tag(ItemTags.create(new ResourceLocation(Reference.FORGE, "dusts/" + material.getId())));
				dustTag.add(EERegistrar.dustMap.get(material.getId()).get());
				if (material.getId().equals("nether_quartz")) {
					tag(ItemTags.create(new ResourceLocation(Reference.FORGE, "dusts/quartz")));
				}
			}
			// Plates
			if (processedType.contains("plate")) {
				forgePlates.add(EERegistrar.plateMap.get(material.getId()).get());
				TagAppender<Item> plateTag = tag(ItemTags.create(new ResourceLocation(Reference.FORGE, "plates/" + material.getId())));
				plateTag.add(EERegistrar.plateMap.get(material.getId()).get());
			}
			// Gears
			if (processedType.contains("gear")) {
				forgeGears.add(EERegistrar.gearMap.get(material.getId()).get());
				TagAppender<Item> gearTag = tag(ItemTags.create(new ResourceLocation(Reference.FORGE, "gears/" + material.getId())));
				gearTag.add(EERegistrar.gearMap.get(material.getId()).get());
			}
			// Rods
			if (processedType.contains("rod")) {
				forgeRods.add(EERegistrar.rodMap.get(material.getId()).get());
				TagAppender<Item> rodTag = tag(ItemTags.create(new ResourceLocation(Reference.FORGE, "rods/" + material.getId())));
				rodTag.add(EERegistrar.rodMap.get(material.getId()).get());
			}
			// Raw Materials
			// TODO: Review this
			if (processedType.contains("raw")) {
				forgeRaw.add(EERegistrar.rawMap.get(material.getId()).get());
				forgeOres.add(EERegistrar.rawMap.get(material.getId()).get());
				forgeBlocks.add(EERegistrar.rawBlockItemMap.get(material.getId()).get());
				TagAppender<Item> rawTag = tag(ItemTags.create(new ResourceLocation(Reference.FORGE, "raws/" + material.getId())));
				TagAppender<Item> oreTag = tag(ItemTags.create(new ResourceLocation(Reference.FORGE, "ores/" + material.getId())));
				TagAppender<Item> rawBlockTag = tag(ItemTags.create(new ResourceLocation(Reference.FORGE, "storage_blocks/raw_" + material.getId())));
				rawTag.add(EERegistrar.rawMap.get(material.getId()).get());
				oreTag.add(EERegistrar.rawMap.get(material.getId()).get());
				rawBlockTag.add(EERegistrar.rawBlockItemMap.get(material.getId()).get());
			}
			// Fluid Bucket
			if (processedType.contains("fluid")) {
				forgeBuckets.add(EERegistrar.fluidBucketMap.get(material.getId()).get());
				TagAppender<Item> bucketTag = tag(ItemTags.create(new ResourceLocation(Reference.FORGE, "buckets/" + material.getId())));
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
					TagAppender<Item> oreTag = tag(ItemTags.create(new ResourceLocation(Reference.FORGE, "ores/" + material.getId())));
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