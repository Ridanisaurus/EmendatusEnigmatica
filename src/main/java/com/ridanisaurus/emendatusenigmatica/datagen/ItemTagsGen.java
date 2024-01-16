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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ItemTagsGen extends EETagProvider {

	private final EmendatusDataRegistry registry;

	public ItemTagsGen(DataGenerator gen, EmendatusDataRegistry registry) {
		super(gen);
		this.registry = registry;
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
	private final List<String> forgeTools = Lists.newArrayList();
	private final List<String> forgeSwords = Lists.newArrayList();
	private final List<String> forgePickaxes = Lists.newArrayList();
	private final List<String> forgeAxes = Lists.newArrayList();
	private final List<String> forgeShovels = Lists.newArrayList();
	private final List<String> forgeHoes = Lists.newArrayList();
	private final List<String> forgePaxels = Lists.newArrayList();
	private final List<String> forgeShields = Lists.newArrayList();
	private final List<String> forgeHelmets = Lists.newArrayList();
	private final List<String> forgeChestplates = Lists.newArrayList();
	private final List<String> forgeLeggings = Lists.newArrayList();
	private final List<String> forgeBoots = Lists.newArrayList();
	private final Map<String, List<String>> oresPerMaterial = new HashMap<>();
	private final Map<String, List<String>> oresInGround = new HashMap<>();

	private final List<String> beaconIngots = Lists.newArrayList();

	@Override
	protected void buildTags(Consumer<IFinishedGenericJSON> consumer) {
		for (MaterialModel material : registry.getMaterials()) {
			List<String> processedType = material.getProcessedTypes();
			// Storage Blocks
			if (processedType.contains("storage_block")) {
				ResourceLocation block = EERegistrar.storageBlockItemMap.get(material.getId()).getId();
				if (!forgeBlocks.contains("#forge:storage_blocks/" + material.getId())) forgeBlocks.add("#forge:storage_blocks/" + material.getId());
				new TagBuilder().tag(block.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/storage_blocks/" + material.getId()));
			}
			// Ingots
			if (processedType.contains("ingot")) {
				ResourceLocation ingot = EERegistrar.ingotMap.get(material.getId()).getId();
				if (!forgeIngots.contains("#forge:ingots/" + material.getId())) forgeIngots.add("#forge:ingots/" + material.getId());
				beaconIngots.add(ingot.toString());
				new TagBuilder().tag(ingot.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/ingots/" + material.getId()));
			}
			// Gems
			if (processedType.contains("gem")) {
				ResourceLocation gem = EERegistrar.gemMap.get(material.getId()).getId();
				if (!forgeGems.contains("#forge:gems/" + material.getId())) forgeGems.add("#forge:gems/" + material.getId());
				new TagBuilder().tag(gem.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/gems/" + material.getId()));
			}
			// Nuggets
			if (processedType.contains("nugget")) {
				ResourceLocation nugget = EERegistrar.nuggetMap.get(material.getId()).getId();
				if (!forgeNuggets.contains("#forge:nuggets/" + material.getId())) forgeNuggets.add("#forge:nuggets/" + material.getId());
				new TagBuilder().tag(nugget.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/nuggets/" + material.getId()));
			}
			// Dusts
			if (processedType.contains("dust")) {
				ResourceLocation dust = EERegistrar.dustMap.get(material.getId()).getId();
				if (!forgeDusts.contains("#forge:dusts/" + material.getId())) forgeDusts.add("#forge:dusts/" + material.getId());
				new TagBuilder().tag(dust.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/dusts/" + material.getId()));
			}
			// Plates
			if (processedType.contains("plate")) {
				ResourceLocation plate = EERegistrar.plateMap.get(material.getId()).getId();
				if (!forgePlates.contains("#forge:plates/" + material.getId())) forgePlates.add("#forge:plates/" + material.getId());
				new TagBuilder().tag(plate.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/plates/" + material.getId()));
			}
			// Gears
			if (processedType.contains("gear")) {
				ResourceLocation gear = EERegistrar.gearMap.get(material.getId()).getId();
				if (!forgeGears.contains("#forge:gears/" + material.getId())) forgeGears.add("#forge:gears/" + material.getId());
				new TagBuilder().tag(gear.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/gears/" + material.getId()));
			}
			// Rods
			if (processedType.contains("rod")) {
				ResourceLocation rod = EERegistrar.rodMap.get(material.getId()).getId();
				if (!forgeRods.contains("#forge:rods/" + material.getId())) forgeRods.add("#forge:rods/" + material.getId());
				new TagBuilder().tag(rod.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/rods/" + material.getId()));
			}
			// Raw Materials
			if (processedType.contains("raw")) {
				ResourceLocation raw = EERegistrar.rawMap.get(material.getId()).getId();
				ResourceLocation rawBlock = EERegistrar.rawBlockItemMap.get(material.getId()).getId();
				if (!forgeRaw.contains("#forge:raw_materials/" + material.getId())) forgeRaw.add("#forge:raw_materials/" + material.getId());
				if (!forgeBlocks.contains("#forge:storage_blocks/raw_" + material.getId())) forgeBlocks.add("#forge:storage_blocks/raw_" + material.getId());
				new TagBuilder().tag(raw.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/raw_materials/" + material.getId()));
				new TagBuilder().tag(rawBlock.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/storage_blocks/raw_" + material.getId()));
			}
			// Tools
			if (processedType.contains("sword")) {
				ResourceLocation sword = EERegistrar.swordMap.get(material.getId()).getId();
				if (!forgeSwords.contains("#forge:tools/swords/" + material.getId())) forgeSwords.add("#forge:tools/swords/" + material.getId());
				new TagBuilder().tag(sword.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/tools/swords/" + material.getId()));
			}
			if (processedType.contains("pickaxe")) {
				ResourceLocation pickaxe = EERegistrar.pickaxeMap.get(material.getId()).getId();
				if (!forgePickaxes.contains("#forge:tools/pickaxes/" + material.getId())) forgePickaxes.add("#forge:tools/pickaxes/" + material.getId());
				new TagBuilder().tag(pickaxe.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/tools/pickaxes/" + material.getId()));
			}
			if (processedType.contains("axe")) {
				ResourceLocation axe = EERegistrar.axeMap.get(material.getId()).getId();
				if (!forgeAxes.contains("#forge:tools/axes/" + material.getId())) forgeAxes.add("#forge:tools/axes/" + material.getId());
				new TagBuilder().tag(axe.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/tools/axes/" + material.getId()));
			}
			if (processedType.contains("shovel")) {
				ResourceLocation shovel = EERegistrar.shovelMap.get(material.getId()).getId();
				if (!forgeShovels.contains("#forge:tools/shovels/" + material.getId())) forgeShovels.add("#forge:tools/shovels/" + material.getId());
				new TagBuilder().tag(shovel.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/tools/shovels/" + material.getId()));
			}
			if (processedType.contains("hoe")) {
				ResourceLocation hoe = EERegistrar.hoeMap.get(material.getId()).getId();
				if (!forgeHoes.contains("#forge:tools/hoes/" + material.getId())) forgeHoes.add("#forge:tools/hoes/" + material.getId());
				new TagBuilder().tag(hoe.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/tools/hoes/" + material.getId()));
			}
			if (processedType.contains("paxel")) {
				ResourceLocation paxel = EERegistrar.paxelMap.get(material.getId()).getId();
				if (!forgeTools.contains("#forge:tools/paxels")) forgeTools.add("#forge:tools/paxels");
				if (!forgePaxels.contains("#forge:tools/paxels/" + material.getId())) forgePaxels.add("#forge:tools/paxels/" + material.getId());
				new TagBuilder().tag(paxel.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/tools/paxels/" + material.getId()));
			}
			if (processedType.contains("shield")) {
				ResourceLocation shield = EERegistrar.shieldMap.get(material.getId()).getId();
				if (!forgeShields.contains("#forge:tools/shields/" + material.getId())) forgeShields.add("#forge:tools/shields/" + material.getId());
				new TagBuilder().tag(shield.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/tools/shields/" + material.getId()));
			}
			// Armor
			if (processedType.contains("helmet")) {
				ResourceLocation helmet = EERegistrar.helmetMap.get(material.getId()).getId();
				if (!forgeHelmets.contains("#forge:armors/helmets/" + material.getId())) forgeHelmets.add("#forge:armors/helmets/" + material.getId());
				new TagBuilder().tag(helmet.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/armors/helmets/" + material.getId()));
			}
			if (processedType.contains("chestplate")) {
				ResourceLocation chestplate = EERegistrar.chestplateMap.get(material.getId()).getId();
				if (!forgeChestplates.contains("#forge:armors/chestplates/" + material.getId())) forgeChestplates.add("#forge:armors/chestplates/" + material.getId());
				new TagBuilder().tag(chestplate.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/armors/chestplates/" + material.getId()));
			}
			if (processedType.contains("leggings")) {
				ResourceLocation leggings = EERegistrar.leggingsMap.get(material.getId()).getId();
				if (!forgeLeggings.contains("#forge:armors/leggings/" + material.getId())) forgeLeggings.add("#forge:armors/leggings/" + material.getId());
				new TagBuilder().tag(leggings.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/armors/leggings/" + material.getId()));
			}
			if (processedType.contains("boots")) {
				ResourceLocation boots = EERegistrar.bootsMap.get(material.getId()).getId();
				if (!forgeBoots.contains("#forge:armors/boots/" + material.getId())) forgeBoots.add("#forge:armors/boots/" + material.getId());
				new TagBuilder().tag(boots.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/armors/boots/" + material.getId()));
			}
			// Buckets
			if (processedType.contains("fluid")) {
				ResourceLocation bucket = EERegistrar.fluidBucketMap.get(material.getId()).getId();
				if (!forgeBuckets.contains("#forge:buckets/" + material.getId())) forgeBuckets.add("#forge:buckets/" + material.getId());
				new TagBuilder().tag(bucket.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/items/buckets/" + material.getId()));
			}
			// Ores
			for (StrataModel strata : registry.getStrata()) {
				if (processedType.contains("ore")) {
//					ResourceLocation ore = new ResourceLocation("minecraft:stone");
					if (material.getStrata().isEmpty() || material.getStrata().contains(strata.getId())) {
						ResourceLocation ore = EERegistrar.oreBlockItemTable.get(strata.getId(), material.getId()).getId();
						if (!forgeOres.contains("#forge:ores/" + material.getId())) forgeOres.add("#forge:ores/" + material.getId());
						oresPerMaterial.computeIfAbsent(material.getId(), s -> new ArrayList<>()).add(ore.toString());
						oresInGround.computeIfAbsent(strata.getSuffix(), s -> new ArrayList<>()).add(ore.toString());
					}

					if (processedType.contains("sample")) {
//						ResourceLocation sample = new ResourceLocation("minecraft:stone");
						if (material.getStrata().isEmpty() || material.getStrata().contains(strata.getId())) {
							ResourceLocation sample = EERegistrar.oreSampleBlockItemTable.get(strata.getId(), material.getId()).getId();
							if (!forgeOres.contains("#forge:ores/" + material.getId())) forgeOres.add("#forge:ores/" + material.getId());
							oresPerMaterial.computeIfAbsent(material.getId(), s -> new ArrayList<>()).add(sample.toString());
							oresInGround.computeIfAbsent(strata.getSuffix(), s -> new ArrayList<>()).add(sample.toString());
						}
					}
				}
			}
		}
		if (!oresPerMaterial.isEmpty())	oresPerMaterial.forEach((material, oreList) -> new TagBuilder().tags(oreList).save(consumer, new ResourceLocation(Reference.FORGE, "/items/ores/" + material)));
		if (!oresInGround.isEmpty()) oresInGround.forEach((strataPrefix, oreType) -> new TagBuilder().tags(oreType).save(consumer, new ResourceLocation(Reference.FORGE, "/items/ores_in_ground/" + strataPrefix)));

		if (!forgeBlocks.isEmpty()) new TagBuilder().tags(forgeBlocks).save(consumer, new ResourceLocation(Reference.FORGE, "/items/storage_blocks"));
		if (!forgeIngots.isEmpty()) new TagBuilder().tags(forgeIngots).save(consumer, new ResourceLocation(Reference.FORGE, "/items/ingots"));
		if (!forgeGems.isEmpty()) new TagBuilder().tags(forgeGems).save(consumer, new ResourceLocation(Reference.FORGE, "/items/gems"));
		if (!forgeNuggets.isEmpty()) new TagBuilder().tags(forgeNuggets).save(consumer, new ResourceLocation(Reference.FORGE, "/items/nuggets"));
		if (!forgeDusts.isEmpty()) new TagBuilder().tags(forgeDusts).save(consumer, new ResourceLocation(Reference.FORGE, "/items/dusts"));
		if (!forgePlates.isEmpty()) new TagBuilder().tags(forgePlates).save(consumer, new ResourceLocation(Reference.FORGE, "/items/plates"));
		if (!forgeGears.isEmpty()) new TagBuilder().tags(forgeGears).save(consumer, new ResourceLocation(Reference.FORGE, "/items/gears"));
		if (!forgeRods.isEmpty()) new TagBuilder().tags(forgeRods).save(consumer, new ResourceLocation(Reference.FORGE, "/items/rods"));
		if (!forgeRaw.isEmpty()) new TagBuilder().tags(forgeRaw).save(consumer, new ResourceLocation(Reference.FORGE, "/items/raw_materials"));
		if (!forgeSwords.isEmpty()) new TagBuilder().tags(forgeSwords).save(consumer, new ResourceLocation(Reference.FORGE, "/items/tools/swords"));
		if (!forgePickaxes.isEmpty()) new TagBuilder().tags(forgePickaxes).save(consumer, new ResourceLocation(Reference.FORGE, "/items/tools/pickaxes"));
		if (!forgeAxes.isEmpty()) new TagBuilder().tags(forgeAxes).save(consumer, new ResourceLocation(Reference.FORGE, "/items/tools/axes"));
		if (!forgeShovels.isEmpty()) new TagBuilder().tags(forgeShovels).save(consumer, new ResourceLocation(Reference.FORGE, "/items/tools/shovels"));
		if (!forgeHoes.isEmpty()) new TagBuilder().tags(forgeHoes).save(consumer, new ResourceLocation(Reference.FORGE, "/items/tools/hoes"));
		if (!forgePaxels.isEmpty()) new TagBuilder().tags(forgePaxels).save(consumer, new ResourceLocation(Reference.FORGE, "/items/tools/paxels"));
		if (!forgeTools.isEmpty()) new TagBuilder().tags(forgeTools).save(consumer, new ResourceLocation(Reference.FORGE, "/items/tools"));
		if (!forgeHelmets.isEmpty()) new TagBuilder().tags(forgeHelmets).save(consumer, new ResourceLocation(Reference.FORGE, "/items/armors/helmets"));
		if (!forgeChestplates.isEmpty()) new TagBuilder().tags(forgeChestplates).save(consumer, new ResourceLocation(Reference.FORGE, "/items/armors/chestplates"));
		if (!forgeLeggings.isEmpty()) new TagBuilder().tags(forgeLeggings).save(consumer, new ResourceLocation(Reference.FORGE, "/items/armors/leggings"));
		if (!forgeBoots.isEmpty()) new TagBuilder().tags(forgeBoots).save(consumer, new ResourceLocation(Reference.FORGE, "/items/armors/boots"));
		if (!forgeBuckets.isEmpty()) new TagBuilder().tags(forgeBuckets).save(consumer, new ResourceLocation(Reference.FORGE, "/items/buckets"));
		if (!forgeOres.isEmpty()) new TagBuilder().tags(forgeOres).save(consumer, new ResourceLocation(Reference.FORGE, "/items/ores"));

		if (!beaconIngots.isEmpty()) new TagBuilder().tags(beaconIngots).save(consumer, new ResourceLocation(Reference.MINECRAFT, "/items/beacon_payment_items"));

		// TODO: Add Tag list check for Addons
	}

	@Override
	public String getName() {
		return "Emendatus Enigmatica Item Tags";
	}
}