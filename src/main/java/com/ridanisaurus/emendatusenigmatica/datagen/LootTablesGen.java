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

import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.datagen.base.EELootProvider;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;

public class LootTablesGen extends EELootProvider {
	private final EmendatusDataRegistry registry;

	public LootTablesGen(DataGenerator dataGeneratorIn, EmendatusDataRegistry registry) {
		super(dataGeneratorIn);
		this.registry = registry;
	}

	@Override
	protected void addTables() {
		for (MaterialModel material : registry.getMaterials()) {
			List<String> processedType = material.getProcessedTypes();

			// Storage Blocks
			if (processedType.contains("storage_block")) {
				blockLootTable.put(EERegistrar.storageBlockMap.get(material.getId()).get(),
						selfDrop(EERegistrar.storageBlockMap.get(material.getId()).get())
				);
			}
			// Clusters
			if (processedType.contains("cluster")) {
				// Block
				blockLootTable.put(EERegistrar.clusterShardBlockMap.get(material.getId()).get(),
						selfDrop(EERegistrar.clusterShardBlockMap.get(material.getId()).get())
				);
				// Small Bud
				blockLootTable.put(EERegistrar.smallBudBlockMap.get(material.getId()).get(),
						dropWhenSilkTouch(EERegistrar.smallBudBlockMap.get(material.getId()).get())
				);
				// Medium Bud
				blockLootTable.put(EERegistrar.mediumBudBlockMap.get(material.getId()).get(),
						dropWhenSilkTouch(EERegistrar.mediumBudBlockMap.get(material.getId()).get())
				);
				// Large Bud
				blockLootTable.put(EERegistrar.mediumBudBlockMap.get(material.getId()).get(),
						dropWhenSilkTouch(EERegistrar.mediumBudBlockMap.get(material.getId()).get())
				);
				// Cluster
				blockLootTable.put(EERegistrar.clusterBlockMap.get(material.getId()).get(),
						dropWhenSilkTouchWithSetCount(EERegistrar.clusterBlockMap.get(material.getId()).get(), EERegistrar.clusterShardMap.get(material.getId()).get(), 2.0f, 4.0f)
				);

			}
			// Raw Storage Blocks
			if (processedType.contains("raw")) {
				blockLootTable.put(EERegistrar.rawBlockMap.get(material.getId()).get(),
						selfDrop(EERegistrar.rawBlockMap.get(material.getId()).get())
				);
			}
			// Ores
			for (StrataModel stratum : registry.getStrata()) {
//				if (processedType.contains("ore") && processedType.contains("raw") && material.getProperties().getMaterialType().equals("metal")) {
//					blockLootTable.put(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get(),
//							createOreDrop(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get(),
//									EERegistrar.rawMap.get(material.getId()).get())
//					);
//				}
				if (processedType.contains("ore") && material.getProperties().getMaterialType().equals("metal")) {
					if (material.getOreDrop().getMax() == 1) {
						blockLootTable.put(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get(),
								specialDrop(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get(),
										(processedType.contains("raw") && material.getOreDrop().getDrop().isEmpty() ? EERegistrar.rawMap.get(material.getId()).get() : material.getOreDrop().getDefaultItemDropAsItem().asItem()))
						);
					} else {
						blockLootTable.put(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get(),
								specialCountDrop(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get(),
										(processedType.contains("raw") && material.getOreDrop().getDrop().isEmpty() ? EERegistrar.rawMap.get(material.getId()).get() : material.getOreDrop().getDefaultItemDropAsItem()),
										UniformGenerator.between(material.getOreDrop().getMin(), material.getOreDrop().getMax()))
						);
					}
				}
				if (processedType.contains("ore") && material.getProperties().getMaterialType().equals("gem")) {
					if (material.getOreDrop().getMax() == 1) {
						blockLootTable.put(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get(),
								specialDrop(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get(),
										(processedType.contains("gem") && material.getOreDrop().getDrop().isEmpty() ? EERegistrar.gemMap.get(material.getId()).get() : material.getOreDrop().getDefaultItemDropAsItem().asItem()))
						);
					} else {
						blockLootTable.put(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get(),
								specialCountDrop(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get(),
										(processedType.contains("gem") && material.getOreDrop().getDrop().isEmpty() ? EERegistrar.gemMap.get(material.getId()).get() : material.getOreDrop().getDefaultItemDropAsItem()),
										UniformGenerator.between(material.getOreDrop().getMin(), material.getOreDrop().getMax()))
						);
					}
				}
				if (processedType.contains("ore") && stratum.getSampleStrata()) {
					if (material.getOreDrop().getMax() == 1) {
						blockLootTable.put(EERegistrar.oreSampleBlockTable.get(stratum.getId(), material.getId()).get(),
								specialDrop(EERegistrar.oreSampleBlockTable.get(stratum.getId(), material.getId()).get(),
										(processedType.contains("raw") && material.getOreDrop().getDrop().isEmpty() ? EERegistrar.rawMap.get(material.getId()).get() : material.getOreDrop().getDefaultItemDropAsItem().asItem()))
						);
					} else {
						blockLootTable.put(EERegistrar.oreSampleBlockTable.get(stratum.getId(), material.getId()).get(),
								specialCountDrop(EERegistrar.oreSampleBlockTable.get(stratum.getId(), material.getId()).get(),
										(processedType.contains("raw") && material.getOreDrop().getDrop().isEmpty() ? EERegistrar.rawMap.get(material.getId()).get() : material.getOreDrop().getDefaultItemDropAsItem()),
										UniformGenerator.between(material.getOreDrop().getMin(), material.getOreDrop().getMax()))
						);
					}
				}
			}
		}
	}
}
