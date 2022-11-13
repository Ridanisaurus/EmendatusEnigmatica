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

import com.google.common.collect.Lists;
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

public class ItemTagsGen extends EETagProvider {

	public ItemTagsGen(DataGenerator gen) {
		super(gen);
	}

	private final List<String> forgeBlocks = Lists.newArrayList();
	private final List<String> forgeIngots = Lists.newArrayList();
	private final List<String> forgeGems = Lists.newArrayList();
	private final List<String> forgeNuggets = Lists.newArrayList();
	private final List<String> forgeDusts = Lists.newArrayList();
	private final List<String> forgePlates = Lists.newArrayList();
	private final List<String> forgeGears = Lists.newArrayList();
	private final List<String> forgeRods = Lists.newArrayList();
	private final List<String> forgeRaw = Lists.newArrayList();
	private final List<String> forgeBuckets = Lists.newArrayList();
	private final List<String> forgeOres = Lists.newArrayList();

	private final List<String> beaconIngots = Lists.newArrayList();

	@Override
	protected void buildTags(Consumer<IFinishedGenericJSON> consumer) {
		for (MaterialModel material : EELoader.MATERIALS) {
			List<String> processedType = material.getProcessedType();
			// Storage Blocks
			if (processedType.contains("storage_block")) {
				ResourceLocation block = EERegistrar.storageBlockItemMap.get(material.getId()).getId();
				forgeBlocks.add(block.toString());
				new TagBuilder().tag(block.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/storage_blocks/" + material.getId()));
			}
			// Ingots
			if (processedType.contains("ingot")) {
				ResourceLocation ingot = EERegistrar.ingotMap.get(material.getId()).getId();
				forgeIngots.add(ingot.toString());
				beaconIngots.add(ingot.toString());
				new TagBuilder().tag(ingot.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/ingots/" + material.getId()));
			}
			// Gems
			if (processedType.contains("gem")) {
				ResourceLocation gem = EERegistrar.gemMap.get(material.getId()).getId();
				forgeGems.add(gem.toString());
				new TagBuilder().tag(gem.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/gems/" + material.getId()));
			}
			// Nuggets
			if (processedType.contains("nugget")) {
				ResourceLocation nugget = EERegistrar.nuggetMap.get(material.getId()).getId();
				forgeNuggets.add(nugget.toString());
				new TagBuilder().tag(nugget.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/nuggets/" + material.getId()));
			}
			// Dusts
			if (processedType.contains("dust")) {
				ResourceLocation dust = EERegistrar.dustMap.get(material.getId()).getId();
				forgeDusts.add(dust.toString());
				new TagBuilder().tag(dust.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/dusts/" + material.getId()));
			}
			// Plates
			if (processedType.contains("plate")) {
				ResourceLocation plate = EERegistrar.plateMap.get(material.getId()).getId();
				forgePlates.add(plate.toString());
				new TagBuilder().tag(plate.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/plates/" + material.getId()));
			}
			// Gears
			if (processedType.contains("gear")) {
				ResourceLocation gear = EERegistrar.gearMap.get(material.getId()).getId();
				forgeGears.add(gear.toString());
				new TagBuilder().tag(gear.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/gears/" + material.getId()));
			}
			// Rods
			if (processedType.contains("rod")) {
				ResourceLocation rod = EERegistrar.rodMap.get(material.getId()).getId();
				forgeRods.add(rod.toString());
				new TagBuilder().tag(rod.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/rods/" + material.getId()));
			}
			// Raw Materials
			if (processedType.contains("raw")) {
				ResourceLocation raw = EERegistrar.rawMap.get(material.getId()).getId();
				ResourceLocation rawBlock = EERegistrar.rawBlockItemMap.get(material.getId()).getId();
				forgeRaw.add(raw.toString());
				forgeBlocks.add(rawBlock.toString());
				new TagBuilder().tag(raw.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/raw_materials/" + material.getId()));
				new TagBuilder().tag(rawBlock.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/storage_blocks/raw_" + material.getId()));
			}
			// Buckets
			if (processedType.contains("fluid")) {
				ResourceLocation bucket = EERegistrar.fluidBucketMap.get(material.getId()).getId();
				forgeBuckets.add(bucket.toString());
				new TagBuilder().tag(bucket.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/buckets/" + material.getId()));
			}
			// Ores
			for (StrataModel stratum : EELoader.STRATA) {
				if (processedType.contains("ore")) {
					ResourceLocation ore = EERegistrar.oreBlockItemTable.get(stratum.getId(), material.getId()).getId();
					forgeOres.add(ore.toString());
					new TagBuilder().tag(ore.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/ores/" + getModelName(stratum, material)));
				}
			}
		}

		new TagBuilder().tags(forgeBlocks).save(consumer, new ResourceLocation(Reference.FORGE, "/items/storage_blocks"));
		new TagBuilder().tags(forgeIngots).save(consumer, new ResourceLocation(Reference.FORGE, "/items/ingots"));
		new TagBuilder().tags(forgeGems).save(consumer, new ResourceLocation(Reference.FORGE, "/items/gems"));
		new TagBuilder().tags(forgeNuggets).save(consumer, new ResourceLocation(Reference.FORGE, "/items/nuggets"));
		new TagBuilder().tags(forgeDusts).save(consumer, new ResourceLocation(Reference.FORGE, "/items/dusts"));
		new TagBuilder().tags(forgePlates).save(consumer, new ResourceLocation(Reference.FORGE, "/items/plates"));
		new TagBuilder().tags(forgeGears).save(consumer, new ResourceLocation(Reference.FORGE, "/items/gears"));
		new TagBuilder().tags(forgeRods).save(consumer, new ResourceLocation(Reference.FORGE, "/items/rods"));
		new TagBuilder().tags(forgeRaw).save(consumer, new ResourceLocation(Reference.FORGE, "/items/raw_materials"));
		new TagBuilder().tags(forgeBuckets).save(consumer, new ResourceLocation(Reference.FORGE, "/items/buckets"));
		new TagBuilder().tags(forgeOres).save(consumer, new ResourceLocation(Reference.FORGE, "/items/ores"));

		new TagBuilder().tags(beaconIngots).save(consumer, new ResourceLocation(Reference.FORGE, "/items/beacon_payment_items"));
	}

	public static String getModelName(StrataModel stratum, MaterialModel material) {
		return material.getId() + (!stratum.getId().equals("minecraft_stone") ? "_" + stratum.getSuffix() : "") + "_ore";
	}

	@Override
	public String getName() {
		return "Emendatus Enigmatica Item Tags";
	}
}