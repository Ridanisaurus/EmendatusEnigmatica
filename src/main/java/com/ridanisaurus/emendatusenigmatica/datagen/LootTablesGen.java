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
import com.ridanisaurus.emendatusenigmatica.registries.OreHandler;
import com.ridanisaurus.emendatusenigmatica.util.Ores;
import com.ridanisaurus.emendatusenigmatica.util.Strata;
import net.minecraft.data.DataGenerator;

public class LootTablesGen extends BaseLootTableProvider {

  public LootTablesGen(DataGenerator gen) {
    super(gen);
  }

  @Override
  protected void addTables() {
    // Storage Blocks
    blockLootTable.put(BlockHandler.BLOCK_COPPER.get(), createBlockLootTable(BlockHandler.BLOCK_COPPER.get()));
    blockLootTable.put(BlockHandler.BLOCK_ALUMINUM.get(), createBlockLootTable(BlockHandler.BLOCK_ALUMINUM.get()));
    blockLootTable.put(BlockHandler.BLOCK_SILVER.get(), createBlockLootTable(BlockHandler.BLOCK_SILVER.get()));
    blockLootTable.put(BlockHandler.BLOCK_LEAD.get(), createBlockLootTable(BlockHandler.BLOCK_LEAD.get()));
    blockLootTable.put(BlockHandler.BLOCK_NICKEL.get(), createBlockLootTable(BlockHandler.BLOCK_NICKEL.get()));
    blockLootTable.put(BlockHandler.BLOCK_URANIUM.get(), createBlockLootTable(BlockHandler.BLOCK_URANIUM.get()));
    blockLootTable.put(BlockHandler.BLOCK_OSMIUM.get(), createBlockLootTable(BlockHandler.BLOCK_OSMIUM.get()));
    blockLootTable.put(BlockHandler.BLOCK_TIN.get(), createBlockLootTable(BlockHandler.BLOCK_TIN.get()));
    blockLootTable.put(BlockHandler.BLOCK_ZINC.get(), createBlockLootTable(BlockHandler.BLOCK_ZINC.get()));
    blockLootTable.put(BlockHandler.BLOCK_BRONZE.get(), createBlockLootTable(BlockHandler.BLOCK_BRONZE.get()));
    blockLootTable.put(BlockHandler.BLOCK_BRASS.get(), createBlockLootTable(BlockHandler.BLOCK_BRASS.get()));
    blockLootTable.put(BlockHandler.BLOCK_CONSTANTAN.get(), createBlockLootTable(BlockHandler.BLOCK_CONSTANTAN.get()));
    blockLootTable.put(BlockHandler.BLOCK_ELECTRUM.get(), createBlockLootTable(BlockHandler.BLOCK_ELECTRUM.get()));
    blockLootTable.put(BlockHandler.BLOCK_STEEL.get(), createBlockLootTable(BlockHandler.BLOCK_STEEL.get()));

    // Ores
    for (Strata stratum : Strata.values()) {
      for (Ores ore : Ores.values()) {
        blockLootTable.put(OreHandler.oreBlockTable.get().get(stratum, ore).get(),
                ore.drop == null
                        ? createItemLootTable(ore.chunk.get())
                        : createCountTable(ore.chunk.get(), ore.drop.item.get(), ore.drop.min, ore.drop.max));
      }
    }
  }
}