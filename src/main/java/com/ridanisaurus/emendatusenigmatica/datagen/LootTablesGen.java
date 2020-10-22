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

import com.ridanisaurus.emendatusenigmatica.registries.BlockHandler;
import com.ridanisaurus.emendatusenigmatica.registries.ItemHandler;
import com.ridanisaurus.emendatusenigmatica.registries.OreHandler;
import com.ridanisaurus.emendatusenigmatica.util.Materials;
import com.ridanisaurus.emendatusenigmatica.util.ProcessedMaterials;
import com.ridanisaurus.emendatusenigmatica.util.Strata;
import net.minecraft.data.DataGenerator;

import java.util.Arrays;
import java.util.List;

public class LootTablesGen extends BaseLootTableProvider {

  public LootTablesGen(DataGenerator gen) {
    super(gen);
  }

  @Override
  protected void addTables() {
    // Storage Blocks
    for (ProcessedMaterials processedMaterial : ProcessedMaterials.values()) {
      for (Materials material : Materials.values()) {
        List<String> toCreate = Arrays.asList(material.type);
        if (processedMaterial == ProcessedMaterials.STORAGE_BLOCK && toCreate.contains("Block")) {
          blockLootTable.put(BlockHandler.backingStorageBlockTable.get(processedMaterial, material).get(), createBlockLootTable(BlockHandler.backingStorageBlockTable.get(processedMaterial, material).get()));
        }
      }
    }

    // Ores
    for (Strata stratum : Strata.values()) {
      for (Materials material : Materials.values()) {
        List<String> toCreate = Arrays.asList(material.type);
        if (material.oreBlock != null && toCreate.contains("Ore")) {
          blockLootTable.put(OreHandler.backingOreBlockTable.get(stratum, material).get(),
                  material.drop == null
                          ? createItemLootTable(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, material).get())
                          : createCountTable(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, material).get(), material.drop.item.get(), material.drop.min, material.drop.max));
        }
      }
    }
  }
}