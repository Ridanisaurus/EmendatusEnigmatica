/*
 * MIT License
 *
 * Copyright (c) 2020 Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.datagen;

import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class LootTablesGen extends BaseLootTableProvider {

	public LootTablesGen(DataGenerator gen) {
		super(gen);
	}

	@Override
	protected void addTables() {
		// Storage Blocks
		for (MaterialModel material : EELoader.MATERIALS) {
			for (String processedType : material.getProcessedType()) {
				if (processedType.equals("storage_block")) {
					blockLootTable.put(EERegistrar.storageBlockMap.get(material.getId()).get(), createBlockLootTable(EERegistrar.storageBlockMap.get(material.getId()).get()));
				}
			}
		}

		// Ores
		for (MaterialModel material : EELoader.MATERIALS) {
			for (StrataModel stratum : EELoader.STRATA) {
				if (material.getProcessedType().contains("ore")) {
					if (material.getDefaultItemDrop().isEmpty()) {
						if (material.getOreBlockDropType().equals("block")) {
							blockLootTable.put(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get(),
									createItemLootTable(EERegistrar.oreBlockItemTable.get(stratum.getId(), material.getId()).get()));
						}
						else if (material.getOreBlockDropType().equals("cluster")) {
							blockLootTable.put(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get(),
									createItemLootTable(EERegistrar.clusterMap.get(material.getId()).get()));
						} else {
							blockLootTable.put(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get(),
									createItemLootTable(EERegistrar.chunkMap.get(material.getId()).get()));
						}
					}
					else {
						if (material.getOreBlockDropType().equals("block")) {
							blockLootTable.put(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get(),
									createCountTable(EERegistrar.oreBlockItemTable.get(stratum.getId(), material.getId()).get(),
											ForgeRegistries.ITEMS.getValue(new ResourceLocation(material.getDefaultItemDrop())),
											material.getDropMin(), material.getDropMax()));
						}
						else if (material.getOreBlockDropType().equals("cluster")) {
							blockLootTable.put(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get(),
									createCountTable(EERegistrar.clusterMap.get(material.getId()).get(),
											ForgeRegistries.ITEMS.getValue(new ResourceLocation(material.getDefaultItemDrop())),
											material.getDropMin(), material.getDropMax()));
						} else {
							blockLootTable.put(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get(),
									createCountTable(EERegistrar.chunkMap.get(material.getId()).get(),
											ForgeRegistries.ITEMS.getValue(new ResourceLocation(material.getDefaultItemDrop())),
											material.getDropMin(), material.getDropMax()));
						}
					}
				}
			}
		}
	}
}