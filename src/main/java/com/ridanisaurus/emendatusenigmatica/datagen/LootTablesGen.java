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
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class LootTablesGen extends BaseLootTableProvider {

	public LootTablesGen(DataGenerator gen) {
		super(gen);
	}

	@Override
	protected void addTables() {
		for (MaterialModel material : EELoader.MATERIALS) {
			List<String> processedType = material.getProcessedType();
			// Storage Blocks
			if (processedType.contains("storage_block")) {
				blockLootTable.put(EERegistrar.storageBlockMap.get(material.getId()).get(), createBlockLootTable(EERegistrar.storageBlockMap.get(material.getId()).get()));
			}
			// Raw Storage Blocks
			if (processedType.contains("raw")) {
				blockLootTable.put(EERegistrar.rawBlockMap.get(material.getId()).get(), createBlockLootTable(EERegistrar.rawBlockMap.get(material.getId()).get()));
			}
			// Ores
			for (StrataModel stratum : EELoader.STRATA) {
				if (processedType.contains("ore") && processedType.contains("raw")) {
					// TODO Revisit this for RAW drop count
					if (material.getOreDrop().getDrop().isEmpty()) {
						blockLootTable.put(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get(),
								createItemLootTable(EERegistrar.rawMap.get(material.getId()).get()));

					} else {
						blockLootTable.put(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get(),
								createCountTable(EERegistrar.rawMap.get(material.getId()).get(),
										ForgeRegistries.ITEMS.getValue(new ResourceLocation(material.getOreDrop().getDrop())),
										material.getOreDrop().getVanillaMin(), material.getOreDrop().getVanillaMax()));

					}
				}
			}
		}
	}
}