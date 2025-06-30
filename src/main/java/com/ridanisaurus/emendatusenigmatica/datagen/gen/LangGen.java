/*
 * MIT License
 *
 * Copyright (c) 2020-2024. Ridanisaurus
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
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

package com.ridanisaurus.emendatusenigmatica.datagen.gen;

import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.datagen.provider.EELangProvider;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LangGen extends EELangProvider {
	private final EmendatusDataRegistry registry;

	public LangGen(DataGenerator gen, EmendatusDataRegistry registry) {
		super(gen, Reference.MOD_ID, "en_us");
		this.registry = registry;
	}

	@Override
	protected void addTranslations() {
        for (MaterialModel material : registry.getMaterials()) {
			List<String> processedType = material.getProcessedTypes();
			// Ingots
			if (processedType.contains("ingot"))
				add(EERegistrar.ingotMap.getValue(material), material.getLocalizedName() + " Ingot");

			// Gems
			if (processedType.contains("gem"))
				add(EERegistrar.gemMap.getValue(material), material.getLocalizedName() + " Gem");

			// Nuggets
			if (processedType.contains("nugget"))
				add(EERegistrar.nuggetMap.getValue(material), material.getLocalizedName() + " Nugget");

			// Dusts
			if (processedType.contains("dust"))
				add(EERegistrar.dustMap.getValue(material), material.getLocalizedName() + " Dust");

			// Plates
			if (processedType.contains("plate"))
				add(EERegistrar.plateMap.getValue(material), material.getLocalizedName() + " Plate");

			// Gears
			if (processedType.contains("gear"))
				add(EERegistrar.gearMap.getValue(material), material.getLocalizedName() + " Gear");

			// Rods
			if (processedType.contains("rod"))
				add(EERegistrar.rodMap.getValue(material), material.getLocalizedName() + " Rod");

			// Swords
			if (processedType.contains("sword"))
				add(EERegistrar.swordMap.getValue(material), material.getLocalizedName() + " Sword");

			// Pickaxes
			if (processedType.contains("pickaxe"))
				add(EERegistrar.pickaxeMap.getValue(material), material.getLocalizedName() + " Pickaxe");

			// Axes
			if (processedType.contains("axe"))
				add(EERegistrar.axeMap.getValue(material), material.getLocalizedName() + " Axe");

			// Shovels
			if (processedType.contains("shovel"))
				add(EERegistrar.shovelMap.getValue(material), material.getLocalizedName() + " Shovel");

			// Hoes
			if (processedType.contains("hoe"))
				add(EERegistrar.hoeMap.getValue(material), material.getLocalizedName() + " Hoe");

			// Paxels
			if (processedType.contains("paxel"))
				add(EERegistrar.paxelMap.getValue(material), material.getLocalizedName() + " Paxel");

			// Shields
			if (processedType.contains("shield"))
				add(EERegistrar.shieldMap.getValue(material), material.getLocalizedName() + " Shield");

			// Raw
			if (processedType.contains("raw")) {
				add(EERegistrar.rawMap.getValue(material), "Raw " + material.getLocalizedName());
				if (processedType.contains("storage_blocks"))
					add(EERegistrar.rawBlockMap.getValue(material), "Block of Raw " + material.getLocalizedName());
			}

			// Fluids
			if (processedType.contains("fluid")) {
				add(EERegistrar.fluidTypeMap.getValue(material).getDescriptionId(), material.getLocalizedName());
				add(EERegistrar.fluidBucketMap.getValue(material), material.getLocalizedName() + " Bucket");
			}

			// Armor
			if (processedType.contains("armor")) {
				// Helmet
				add(EERegistrar.helmetMap.getValue(material), material.getLocalizedName() + " Helmet");
				// Chestplate
				add(EERegistrar.chestplateMap.getValue(material), material.getLocalizedName() + " Chestplate");
				// Leggings
				add(EERegistrar.leggingsMap.getValue(material), material.getLocalizedName() + " Leggings");
				// Boots
				add(EERegistrar.bootsMap.getValue(material), material.getLocalizedName() + " Boots");
			}

			// Storage Blocks
			if (processedType.contains("storage_block")) {
				add(EERegistrar.storageBlockMap.getValue(material), "Block of " + material.getLocalizedName());
				if (material.getProperties().hasOxidization()) {
					add(EERegistrar.exposedBlockMap.getValue(material), "Exposed " + material.getLocalizedName());
					add(EERegistrar.weatheredBlockMap.getValue(material), "Weathered " + material.getLocalizedName());
					add(EERegistrar.oxidizedBlockMap.getValue(material), "Oxidized " + material.getLocalizedName());
					add(EERegistrar.waxedStorageBlockMap.getValue(material), "Waxed Block of " + material.getLocalizedName());
					add(EERegistrar.waxedExposedBlockMap.getValue(material), "Waxed Exposed " + material.getLocalizedName());
					add(EERegistrar.waxedWeatheredBlockMap.getValue(material), "Waxed Weathered " + material.getLocalizedName());
					add(EERegistrar.waxedOxidizedBlockMap.getValue(material), "Waxed Oxidized " + material.getLocalizedName());
				}
			}

			// Cluster
			if (processedType.contains("cluster")) {
				add(EERegistrar.clusterShardBlockMap.getValue(material), "Block of " + material.getLocalizedName() + " Cluster Shard");
				add(EERegistrar.buddingBlockMap.getValue(material), "Budding " + material.getLocalizedName());
				add(EERegistrar.smallBudBlockMap.getValue(material), "Small " + material.getLocalizedName() + " Bud");
				add(EERegistrar.mediumBudBlockMap.getValue(material), "Medium " + material.getLocalizedName() + " Bud");
				add(EERegistrar.largeBudBlockMap.getValue(material), "Large " + material.getLocalizedName() + " Bud");
				add(EERegistrar.clusterBlockMap.getValue(material), material.getLocalizedName() + " Cluster");
				add(EERegistrar.clusterShardMap.getValue(material), material.getLocalizedName() + " Cluster Shard");
			}

			// Ores
			if (processedType.contains("ore")) {
				for (StrataModel strata : registry.getStrata()) {
					if (!material.getStrata().isEmpty() && !material.getStrata().contains(strata.getId())) continue;

					StringBuilder sb = new StringBuilder();
					sb.append(material.getLocalizedName());
					sb.append(" Ore");

					if (!strata.getId().equals("minecraft_stone") && material.getStrata().size() != 1) {
						sb.append(" - ");
						sb.append(strata.getLocalizedName());
					}

					add(EERegistrar.oreBlockTable.get(strata.getId(), material.getId()).get(), sb.toString());

					//TODO: Rework Sample System.
//					if (processedType.contains("sample")) {
//						StringBuilder sb2 = new StringBuilder();
//						sb2.append(material.getLocalizedName());
//						sb2.append(" Rich ");
//						sb2.append(strata.getLocalizedName());
//						if (material.getStrata().isEmpty() || material.getStrata().contains(strata.getId())) {
//							add(EERegistrar.oreSampleBlockTable.get(strata.getId(), material.getId()).get(), sb2.toString());
//						}
//					}
				}
			}
		}
	}

	@Override
	public @NotNull String getName() {
		return "Emendatus Enigmatica Languages: en_us";
	}
}