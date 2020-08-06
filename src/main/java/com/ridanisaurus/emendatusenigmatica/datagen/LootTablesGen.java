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
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Items;

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

    // Strata Ores - Stone
    blockLootTable.put(BlockHandler.ORE_COAL.get(), createSpecialTable(ItemHandler.CHUNK_COAL.get(), Items.COAL));
    blockLootTable.put(BlockHandler.ORE_IRON.get(), createItemLootTable(ItemHandler.CHUNK_IRON.get()));
    blockLootTable.put(BlockHandler.ORE_GOLD.get(), createItemLootTable(ItemHandler.CHUNK_GOLD.get()));
    blockLootTable.put(BlockHandler.ORE_DIAMOND.get(), createSpecialTable(ItemHandler.CHUNK_DIAMOND.get(), Items.DIAMOND));
    blockLootTable.put(BlockHandler.ORE_EMERALD.get(), createSpecialTable(ItemHandler.CHUNK_EMERALD.get(), Items.EMERALD));
    blockLootTable.put(BlockHandler.ORE_LAPIS.get(), createSpecialTable(ItemHandler.CHUNK_LAPIS.get(), Items.LAPIS_LAZULI));
    blockLootTable.put(BlockHandler.ORE_REDSTONE.get(), createSpecialTable(ItemHandler.CHUNK_REDSTONE.get(), Items.REDSTONE));
    blockLootTable.put(BlockHandler.ORE_COPPER.get(), createItemLootTable(ItemHandler.CHUNK_COPPER.get()));
    blockLootTable.put(BlockHandler.ORE_ALUMINUM.get(), createItemLootTable(ItemHandler.CHUNK_ALUMINUM.get()));
    blockLootTable.put(BlockHandler.ORE_SILVER.get(), createItemLootTable(ItemHandler.CHUNK_SILVER.get()));
    blockLootTable.put(BlockHandler.ORE_LEAD.get(), createItemLootTable(ItemHandler.CHUNK_LEAD.get()));
    blockLootTable.put(BlockHandler.ORE_NICKEL.get(), createItemLootTable(ItemHandler.CHUNK_NICKEL.get()));
    blockLootTable.put(BlockHandler.ORE_URANIUM.get(), createItemLootTable(ItemHandler.CHUNK_URANIUM.get()));
    blockLootTable.put(BlockHandler.ORE_OSMIUM.get(), createItemLootTable(ItemHandler.CHUNK_OSMIUM.get()));
    blockLootTable.put(BlockHandler.ORE_TIN.get(), createItemLootTable(ItemHandler.CHUNK_TIN.get()));
    blockLootTable.put(BlockHandler.ORE_ZINC.get(), createItemLootTable(ItemHandler.CHUNK_ZINC.get()));
    blockLootTable.put(BlockHandler.ORE_CERTUS_QUARTZ.get(), createSpecialTable(ItemHandler.CHUNK_CERTUS_QUARTZ.get(), ItemHandler.GEM_CERTUS_QUARTZ.get()));
    blockLootTable.put(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ.get(), createSpecialTable(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get(), ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get()));


    // Strata Ores - Andesite
    blockLootTable.put(BlockHandler.ORE_COAL_ANDESITE.get(), createSpecialTable(ItemHandler.CHUNK_COAL.get(), Items.COAL));
    blockLootTable.put(BlockHandler.ORE_IRON_ANDESITE.get(), createItemLootTable(ItemHandler.CHUNK_IRON.get()));
    blockLootTable.put(BlockHandler.ORE_GOLD_ANDESITE.get(), createItemLootTable(ItemHandler.CHUNK_GOLD.get()));
    blockLootTable.put(BlockHandler.ORE_DIAMOND_ANDESITE.get(), createSpecialTable(ItemHandler.CHUNK_DIAMOND.get(), Items.DIAMOND));
    blockLootTable.put(BlockHandler.ORE_EMERALD_ANDESITE.get(), createSpecialTable(ItemHandler.CHUNK_EMERALD.get(), Items.EMERALD));
    blockLootTable.put(BlockHandler.ORE_LAPIS_ANDESITE.get(), createSpecialTable(ItemHandler.CHUNK_LAPIS.get(), Items.LAPIS_LAZULI));
    blockLootTable.put(BlockHandler.ORE_REDSTONE_ANDESITE.get(), createSpecialTable(ItemHandler.CHUNK_REDSTONE.get(), Items.REDSTONE));
    blockLootTable.put(BlockHandler.ORE_COPPER_ANDESITE.get(), createItemLootTable(ItemHandler.CHUNK_COPPER.get()));
    blockLootTable.put(BlockHandler.ORE_ALUMINUM_ANDESITE.get(), createItemLootTable(ItemHandler.CHUNK_ALUMINUM.get()));
    blockLootTable.put(BlockHandler.ORE_SILVER_ANDESITE.get(), createItemLootTable(ItemHandler.CHUNK_SILVER.get()));
    blockLootTable.put(BlockHandler.ORE_LEAD_ANDESITE.get(), createItemLootTable(ItemHandler.CHUNK_LEAD.get()));
    blockLootTable.put(BlockHandler.ORE_NICKEL_ANDESITE.get(), createItemLootTable(ItemHandler.CHUNK_NICKEL.get()));
    blockLootTable.put(BlockHandler.ORE_URANIUM_ANDESITE.get(), createItemLootTable(ItemHandler.CHUNK_URANIUM.get()));
    blockLootTable.put(BlockHandler.ORE_OSMIUM_ANDESITE.get(), createItemLootTable(ItemHandler.CHUNK_OSMIUM.get()));
    blockLootTable.put(BlockHandler.ORE_TIN_ANDESITE.get(), createItemLootTable(ItemHandler.CHUNK_TIN.get()));
    blockLootTable.put(BlockHandler.ORE_ZINC_ANDESITE.get(), createItemLootTable(ItemHandler.CHUNK_ZINC.get()));
    blockLootTable.put(BlockHandler.ORE_CERTUS_QUARTZ_ANDESITE.get(), createSpecialTable(ItemHandler.CHUNK_CERTUS_QUARTZ.get(), ItemHandler.GEM_CERTUS_QUARTZ.get()));
    blockLootTable.put(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_ANDESITE.get(), createSpecialTable(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get(), ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get()));

    // Strata Ores - Gabbro
    blockLootTable.put(BlockHandler.ORE_COAL_GABBRO.get(), createSpecialTable(ItemHandler.CHUNK_COAL.get(), Items.COAL));
    blockLootTable.put(BlockHandler.ORE_IRON_GABBRO.get(), createItemLootTable(ItemHandler.CHUNK_IRON.get()));
    blockLootTable.put(BlockHandler.ORE_GOLD_GABBRO.get(), createItemLootTable(ItemHandler.CHUNK_GOLD.get()));
    blockLootTable.put(BlockHandler.ORE_DIAMOND_GABBRO.get(), createSpecialTable(ItemHandler.CHUNK_DIAMOND.get(), Items.DIAMOND));
    blockLootTable.put(BlockHandler.ORE_EMERALD_GABBRO.get(), createSpecialTable(ItemHandler.CHUNK_EMERALD.get(), Items.EMERALD));
    blockLootTable.put(BlockHandler.ORE_LAPIS_GABBRO.get(), createSpecialTable(ItemHandler.CHUNK_LAPIS.get(), Items.LAPIS_LAZULI));
    blockLootTable.put(BlockHandler.ORE_REDSTONE_GABBRO.get(), createSpecialTable(ItemHandler.CHUNK_REDSTONE.get(), Items.REDSTONE));
    blockLootTable.put(BlockHandler.ORE_COPPER_GABBRO.get(), createItemLootTable(ItemHandler.CHUNK_COPPER.get()));
    blockLootTable.put(BlockHandler.ORE_ALUMINUM_GABBRO.get(), createItemLootTable(ItemHandler.CHUNK_ALUMINUM.get()));
    blockLootTable.put(BlockHandler.ORE_SILVER_GABBRO.get(), createItemLootTable(ItemHandler.CHUNK_SILVER.get()));
    blockLootTable.put(BlockHandler.ORE_LEAD_GABBRO.get(), createItemLootTable(ItemHandler.CHUNK_LEAD.get()));
    blockLootTable.put(BlockHandler.ORE_NICKEL_GABBRO.get(), createItemLootTable(ItemHandler.CHUNK_NICKEL.get()));
    blockLootTable.put(BlockHandler.ORE_URANIUM_GABBRO.get(), createItemLootTable(ItemHandler.CHUNK_URANIUM.get()));
    blockLootTable.put(BlockHandler.ORE_OSMIUM_GABBRO.get(), createItemLootTable(ItemHandler.CHUNK_OSMIUM.get()));
    blockLootTable.put(BlockHandler.ORE_TIN_GABBRO.get(), createItemLootTable(ItemHandler.CHUNK_TIN.get()));
    blockLootTable.put(BlockHandler.ORE_ZINC_GABBRO.get(), createItemLootTable(ItemHandler.CHUNK_ZINC.get()));
    blockLootTable.put(BlockHandler.ORE_CERTUS_QUARTZ_GABBRO.get(), createSpecialTable(ItemHandler.CHUNK_CERTUS_QUARTZ.get(), ItemHandler.GEM_CERTUS_QUARTZ.get()));
    blockLootTable.put(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_GABBRO.get(), createSpecialTable(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get(), ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get()));

    // Strata Ores - Limestone (Create)
    blockLootTable.put(BlockHandler.ORE_COAL_C_LIMESTONE.get(), createSpecialTable(ItemHandler.CHUNK_COAL.get(), Items.COAL));
    blockLootTable.put(BlockHandler.ORE_IRON_C_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_IRON.get()));
    blockLootTable.put(BlockHandler.ORE_GOLD_C_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_GOLD.get()));
    blockLootTable.put(BlockHandler.ORE_DIAMOND_C_LIMESTONE.get(), createSpecialTable(ItemHandler.CHUNK_DIAMOND.get(), Items.DIAMOND));
    blockLootTable.put(BlockHandler.ORE_EMERALD_C_LIMESTONE.get(), createSpecialTable(ItemHandler.CHUNK_EMERALD.get(), Items.EMERALD));
    blockLootTable.put(BlockHandler.ORE_LAPIS_C_LIMESTONE.get(), createSpecialTable(ItemHandler.CHUNK_LAPIS.get(), Items.LAPIS_LAZULI));
    blockLootTable.put(BlockHandler.ORE_REDSTONE_C_LIMESTONE.get(), createSpecialTable(ItemHandler.CHUNK_REDSTONE.get(), Items.REDSTONE));
    blockLootTable.put(BlockHandler.ORE_COPPER_C_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_COPPER.get()));
    blockLootTable.put(BlockHandler.ORE_ALUMINUM_C_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_ALUMINUM.get()));
    blockLootTable.put(BlockHandler.ORE_SILVER_C_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_SILVER.get()));
    blockLootTable.put(BlockHandler.ORE_LEAD_C_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_LEAD.get()));
    blockLootTable.put(BlockHandler.ORE_NICKEL_C_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_NICKEL.get()));
    blockLootTable.put(BlockHandler.ORE_URANIUM_C_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_URANIUM.get()));
    blockLootTable.put(BlockHandler.ORE_OSMIUM_C_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_OSMIUM.get()));
    blockLootTable.put(BlockHandler.ORE_TIN_C_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_TIN.get()));
    blockLootTable.put(BlockHandler.ORE_ZINC_C_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_ZINC.get()));
    blockLootTable.put(BlockHandler.ORE_CERTUS_QUARTZ_C_LIMESTONE.get(), createSpecialTable(ItemHandler.CHUNK_CERTUS_QUARTZ.get(), ItemHandler.GEM_CERTUS_QUARTZ.get()));
    blockLootTable.put(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_C_LIMESTONE.get(), createSpecialTable(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get(), ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get()));

    // Strata Ores - Scoria
    blockLootTable.put(BlockHandler.ORE_COAL_SCORIA.get(), createSpecialTable(ItemHandler.CHUNK_COAL.get(), Items.COAL));
    blockLootTable.put(BlockHandler.ORE_IRON_SCORIA.get(), createItemLootTable(ItemHandler.CHUNK_IRON.get()));
    blockLootTable.put(BlockHandler.ORE_GOLD_SCORIA.get(), createItemLootTable(ItemHandler.CHUNK_GOLD.get()));
    blockLootTable.put(BlockHandler.ORE_DIAMOND_SCORIA.get(), createSpecialTable(ItemHandler.CHUNK_DIAMOND.get(), Items.DIAMOND));
    blockLootTable.put(BlockHandler.ORE_EMERALD_SCORIA.get(), createSpecialTable(ItemHandler.CHUNK_EMERALD.get(), Items.EMERALD));
    blockLootTable.put(BlockHandler.ORE_LAPIS_SCORIA.get(), createSpecialTable(ItemHandler.CHUNK_LAPIS.get(), Items.LAPIS_LAZULI));
    blockLootTable.put(BlockHandler.ORE_REDSTONE_SCORIA.get(), createSpecialTable(ItemHandler.CHUNK_REDSTONE.get(), Items.REDSTONE));
    blockLootTable.put(BlockHandler.ORE_COPPER_SCORIA.get(), createItemLootTable(ItemHandler.CHUNK_COPPER.get()));
    blockLootTable.put(BlockHandler.ORE_ALUMINUM_SCORIA.get(), createItemLootTable(ItemHandler.CHUNK_ALUMINUM.get()));
    blockLootTable.put(BlockHandler.ORE_SILVER_SCORIA.get(), createItemLootTable(ItemHandler.CHUNK_SILVER.get()));
    blockLootTable.put(BlockHandler.ORE_LEAD_SCORIA.get(), createItemLootTable(ItemHandler.CHUNK_LEAD.get()));
    blockLootTable.put(BlockHandler.ORE_NICKEL_SCORIA.get(), createItemLootTable(ItemHandler.CHUNK_NICKEL.get()));
    blockLootTable.put(BlockHandler.ORE_URANIUM_SCORIA.get(), createItemLootTable(ItemHandler.CHUNK_URANIUM.get()));
    blockLootTable.put(BlockHandler.ORE_OSMIUM_SCORIA.get(), createItemLootTable(ItemHandler.CHUNK_OSMIUM.get()));
    blockLootTable.put(BlockHandler.ORE_TIN_SCORIA.get(), createItemLootTable(ItemHandler.CHUNK_TIN.get()));
    blockLootTable.put(BlockHandler.ORE_ZINC_SCORIA.get(), createItemLootTable(ItemHandler.CHUNK_ZINC.get()));
    blockLootTable.put(BlockHandler.ORE_CERTUS_QUARTZ_SCORIA.get(), createSpecialTable(ItemHandler.CHUNK_CERTUS_QUARTZ.get(), ItemHandler.GEM_CERTUS_QUARTZ.get()));
    blockLootTable.put(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_SCORIA.get(), createSpecialTable(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get(), ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get()));

    // Strata Ores - Weathered Limestone
    blockLootTable.put(BlockHandler.ORE_COAL_WEATHERED_LIMESTONE.get(), createSpecialTable(ItemHandler.CHUNK_COAL.get(), Items.COAL));
    blockLootTable.put(BlockHandler.ORE_IRON_WEATHERED_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_IRON.get()));
    blockLootTable.put(BlockHandler.ORE_GOLD_WEATHERED_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_GOLD.get()));
    blockLootTable.put(BlockHandler.ORE_DIAMOND_WEATHERED_LIMESTONE.get(), createSpecialTable(ItemHandler.CHUNK_DIAMOND.get(), Items.DIAMOND));
    blockLootTable.put(BlockHandler.ORE_EMERALD_WEATHERED_LIMESTONE.get(), createSpecialTable(ItemHandler.CHUNK_EMERALD.get(), Items.EMERALD));
    blockLootTable.put(BlockHandler.ORE_LAPIS_WEATHERED_LIMESTONE.get(), createSpecialTable(ItemHandler.CHUNK_LAPIS.get(), Items.LAPIS_LAZULI));
    blockLootTable.put(BlockHandler.ORE_REDSTONE_WEATHERED_LIMESTONE.get(), createSpecialTable(ItemHandler.CHUNK_REDSTONE.get(), Items.REDSTONE));
    blockLootTable.put(BlockHandler.ORE_COPPER_WEATHERED_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_COPPER.get()));
    blockLootTable.put(BlockHandler.ORE_ALUMINUM_WEATHERED_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_ALUMINUM.get()));
    blockLootTable.put(BlockHandler.ORE_SILVER_WEATHERED_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_SILVER.get()));
    blockLootTable.put(BlockHandler.ORE_LEAD_WEATHERED_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_LEAD.get()));
    blockLootTable.put(BlockHandler.ORE_NICKEL_WEATHERED_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_NICKEL.get()));
    blockLootTable.put(BlockHandler.ORE_URANIUM_WEATHERED_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_URANIUM.get()));
    blockLootTable.put(BlockHandler.ORE_OSMIUM_WEATHERED_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_OSMIUM.get()));
    blockLootTable.put(BlockHandler.ORE_TIN_WEATHERED_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_TIN.get()));
    blockLootTable.put(BlockHandler.ORE_ZINC_WEATHERED_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_ZINC.get()));
    blockLootTable.put(BlockHandler.ORE_CERTUS_QUARTZ_WEATHERED_LIMESTONE.get(), createSpecialTable(ItemHandler.CHUNK_CERTUS_QUARTZ.get(), ItemHandler.GEM_CERTUS_QUARTZ.get()));
    blockLootTable.put(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_WEATHERED_LIMESTONE.get(), createSpecialTable(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get(), ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get()));

    // Strata Ores - Jasper
    blockLootTable.put(BlockHandler.ORE_COAL_JASPER.get(), createSpecialTable(ItemHandler.CHUNK_COAL.get(), Items.COAL));
    blockLootTable.put(BlockHandler.ORE_IRON_JASPER.get(), createItemLootTable(ItemHandler.CHUNK_IRON.get()));
    blockLootTable.put(BlockHandler.ORE_GOLD_JASPER.get(), createItemLootTable(ItemHandler.CHUNK_GOLD.get()));
    blockLootTable.put(BlockHandler.ORE_DIAMOND_JASPER.get(), createSpecialTable(ItemHandler.CHUNK_DIAMOND.get(), Items.DIAMOND));
    blockLootTable.put(BlockHandler.ORE_EMERALD_JASPER.get(), createSpecialTable(ItemHandler.CHUNK_EMERALD.get(), Items.EMERALD));
    blockLootTable.put(BlockHandler.ORE_LAPIS_JASPER.get(), createSpecialTable(ItemHandler.CHUNK_LAPIS.get(), Items.LAPIS_LAZULI));
    blockLootTable.put(BlockHandler.ORE_REDSTONE_JASPER.get(), createSpecialTable(ItemHandler.CHUNK_REDSTONE.get(), Items.REDSTONE));
    blockLootTable.put(BlockHandler.ORE_COPPER_JASPER.get(), createItemLootTable(ItemHandler.CHUNK_COPPER.get()));
    blockLootTable.put(BlockHandler.ORE_ALUMINUM_JASPER.get(), createItemLootTable(ItemHandler.CHUNK_ALUMINUM.get()));
    blockLootTable.put(BlockHandler.ORE_SILVER_JASPER.get(), createItemLootTable(ItemHandler.CHUNK_SILVER.get()));
    blockLootTable.put(BlockHandler.ORE_LEAD_JASPER.get(), createItemLootTable(ItemHandler.CHUNK_LEAD.get()));
    blockLootTable.put(BlockHandler.ORE_NICKEL_JASPER.get(), createItemLootTable(ItemHandler.CHUNK_NICKEL.get()));
    blockLootTable.put(BlockHandler.ORE_URANIUM_JASPER.get(), createItemLootTable(ItemHandler.CHUNK_URANIUM.get()));
    blockLootTable.put(BlockHandler.ORE_OSMIUM_JASPER.get(), createItemLootTable(ItemHandler.CHUNK_OSMIUM.get()));
    blockLootTable.put(BlockHandler.ORE_TIN_JASPER.get(), createItemLootTable(ItemHandler.CHUNK_TIN.get()));
    blockLootTable.put(BlockHandler.ORE_ZINC_JASPER.get(), createItemLootTable(ItemHandler.CHUNK_ZINC.get()));
    blockLootTable.put(BlockHandler.ORE_CERTUS_QUARTZ_JASPER.get(), createSpecialTable(ItemHandler.CHUNK_CERTUS_QUARTZ.get(), ItemHandler.GEM_CERTUS_QUARTZ.get()));
    blockLootTable.put(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_JASPER.get(), createSpecialTable(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get(), ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get()));

    // Strata Ores - Limestone (Quark)
    blockLootTable.put(BlockHandler.ORE_COAL_Q_LIMESTONE.get(), createSpecialTable(ItemHandler.CHUNK_COAL.get(), Items.COAL));
    blockLootTable.put(BlockHandler.ORE_IRON_Q_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_IRON.get()));
    blockLootTable.put(BlockHandler.ORE_GOLD_Q_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_GOLD.get()));
    blockLootTable.put(BlockHandler.ORE_DIAMOND_Q_LIMESTONE.get(), createSpecialTable(ItemHandler.CHUNK_DIAMOND.get(), Items.DIAMOND));
    blockLootTable.put(BlockHandler.ORE_EMERALD_Q_LIMESTONE.get(), createSpecialTable(ItemHandler.CHUNK_EMERALD.get(), Items.EMERALD));
    blockLootTable.put(BlockHandler.ORE_LAPIS_Q_LIMESTONE.get(), createSpecialTable(ItemHandler.CHUNK_LAPIS.get(), Items.LAPIS_LAZULI));
    blockLootTable.put(BlockHandler.ORE_REDSTONE_Q_LIMESTONE.get(), createSpecialTable(ItemHandler.CHUNK_REDSTONE.get(), Items.REDSTONE));
    blockLootTable.put(BlockHandler.ORE_COPPER_Q_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_COPPER.get()));
    blockLootTable.put(BlockHandler.ORE_ALUMINUM_Q_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_ALUMINUM.get()));
    blockLootTable.put(BlockHandler.ORE_SILVER_Q_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_SILVER.get()));
    blockLootTable.put(BlockHandler.ORE_LEAD_Q_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_LEAD.get()));
    blockLootTable.put(BlockHandler.ORE_NICKEL_Q_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_NICKEL.get()));
    blockLootTable.put(BlockHandler.ORE_URANIUM_Q_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_URANIUM.get()));
    blockLootTable.put(BlockHandler.ORE_OSMIUM_Q_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_OSMIUM.get()));
    blockLootTable.put(BlockHandler.ORE_TIN_Q_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_TIN.get()));
    blockLootTable.put(BlockHandler.ORE_ZINC_Q_LIMESTONE.get(), createItemLootTable(ItemHandler.CHUNK_ZINC.get()));
    blockLootTable.put(BlockHandler.ORE_CERTUS_QUARTZ_Q_LIMESTONE.get(), createSpecialTable(ItemHandler.CHUNK_CERTUS_QUARTZ.get(), ItemHandler.GEM_CERTUS_QUARTZ.get()));
    blockLootTable.put(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_Q_LIMESTONE.get(), createSpecialTable(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get(), ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get()));

    // Strata Ores - Marble
    blockLootTable.put(BlockHandler.ORE_COAL_MARBLE.get(), createSpecialTable(ItemHandler.CHUNK_COAL.get(), Items.COAL));
    blockLootTable.put(BlockHandler.ORE_IRON_MARBLE.get(), createItemLootTable(ItemHandler.CHUNK_IRON.get()));
    blockLootTable.put(BlockHandler.ORE_GOLD_MARBLE.get(), createItemLootTable(ItemHandler.CHUNK_GOLD.get()));
    blockLootTable.put(BlockHandler.ORE_DIAMOND_MARBLE.get(), createSpecialTable(ItemHandler.CHUNK_DIAMOND.get(), Items.DIAMOND));
    blockLootTable.put(BlockHandler.ORE_EMERALD_MARBLE.get(), createSpecialTable(ItemHandler.CHUNK_EMERALD.get(), Items.EMERALD));
    blockLootTable.put(BlockHandler.ORE_LAPIS_MARBLE.get(), createSpecialTable(ItemHandler.CHUNK_LAPIS.get(), Items.LAPIS_LAZULI));
    blockLootTable.put(BlockHandler.ORE_REDSTONE_MARBLE.get(), createSpecialTable(ItemHandler.CHUNK_REDSTONE.get(), Items.REDSTONE));
    blockLootTable.put(BlockHandler.ORE_COPPER_MARBLE.get(), createItemLootTable(ItemHandler.CHUNK_COPPER.get()));
    blockLootTable.put(BlockHandler.ORE_ALUMINUM_MARBLE.get(), createItemLootTable(ItemHandler.CHUNK_ALUMINUM.get()));
    blockLootTable.put(BlockHandler.ORE_SILVER_MARBLE.get(), createItemLootTable(ItemHandler.CHUNK_SILVER.get()));
    blockLootTable.put(BlockHandler.ORE_LEAD_MARBLE.get(), createItemLootTable(ItemHandler.CHUNK_LEAD.get()));
    blockLootTable.put(BlockHandler.ORE_NICKEL_MARBLE.get(), createItemLootTable(ItemHandler.CHUNK_NICKEL.get()));
    blockLootTable.put(BlockHandler.ORE_URANIUM_MARBLE.get(), createItemLootTable(ItemHandler.CHUNK_URANIUM.get()));
    blockLootTable.put(BlockHandler.ORE_OSMIUM_MARBLE.get(), createItemLootTable(ItemHandler.CHUNK_OSMIUM.get()));
    blockLootTable.put(BlockHandler.ORE_TIN_MARBLE.get(), createItemLootTable(ItemHandler.CHUNK_TIN.get()));
    blockLootTable.put(BlockHandler.ORE_ZINC_MARBLE.get(), createItemLootTable(ItemHandler.CHUNK_ZINC.get()));
    blockLootTable.put(BlockHandler.ORE_CERTUS_QUARTZ_MARBLE.get(), createSpecialTable(ItemHandler.CHUNK_CERTUS_QUARTZ.get(), ItemHandler.GEM_CERTUS_QUARTZ.get()));
    blockLootTable.put(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_MARBLE.get(), createSpecialTable(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get(), ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get()));

    // Strata Ores - Slate
    blockLootTable.put(BlockHandler.ORE_COAL_SLATE.get(), createSpecialTable(ItemHandler.CHUNK_COAL.get(), Items.COAL));
    blockLootTable.put(BlockHandler.ORE_IRON_SLATE.get(), createItemLootTable(ItemHandler.CHUNK_IRON.get()));
    blockLootTable.put(BlockHandler.ORE_GOLD_SLATE.get(), createItemLootTable(ItemHandler.CHUNK_GOLD.get()));
    blockLootTable.put(BlockHandler.ORE_DIAMOND_SLATE.get(), createSpecialTable(ItemHandler.CHUNK_DIAMOND.get(), Items.DIAMOND));
    blockLootTable.put(BlockHandler.ORE_EMERALD_SLATE.get(), createSpecialTable(ItemHandler.CHUNK_EMERALD.get(), Items.EMERALD));
    blockLootTable.put(BlockHandler.ORE_LAPIS_SLATE.get(), createSpecialTable(ItemHandler.CHUNK_LAPIS.get(), Items.LAPIS_LAZULI));
    blockLootTable.put(BlockHandler.ORE_REDSTONE_SLATE.get(), createSpecialTable(ItemHandler.CHUNK_REDSTONE.get(), Items.REDSTONE));
    blockLootTable.put(BlockHandler.ORE_COPPER_SLATE.get(), createItemLootTable(ItemHandler.CHUNK_COPPER.get()));
    blockLootTable.put(BlockHandler.ORE_ALUMINUM_SLATE.get(), createItemLootTable(ItemHandler.CHUNK_ALUMINUM.get()));
    blockLootTable.put(BlockHandler.ORE_SILVER_SLATE.get(), createItemLootTable(ItemHandler.CHUNK_SILVER.get()));
    blockLootTable.put(BlockHandler.ORE_LEAD_SLATE.get(), createItemLootTable(ItemHandler.CHUNK_LEAD.get()));
    blockLootTable.put(BlockHandler.ORE_NICKEL_SLATE.get(), createItemLootTable(ItemHandler.CHUNK_NICKEL.get()));
    blockLootTable.put(BlockHandler.ORE_URANIUM_SLATE.get(), createItemLootTable(ItemHandler.CHUNK_URANIUM.get()));
    blockLootTable.put(BlockHandler.ORE_OSMIUM_SLATE.get(), createItemLootTable(ItemHandler.CHUNK_OSMIUM.get()));
    blockLootTable.put(BlockHandler.ORE_TIN_SLATE.get(), createItemLootTable(ItemHandler.CHUNK_TIN.get()));
    blockLootTable.put(BlockHandler.ORE_ZINC_SLATE.get(), createItemLootTable(ItemHandler.CHUNK_ZINC.get()));
    blockLootTable.put(BlockHandler.ORE_CERTUS_QUARTZ_SLATE.get(), createSpecialTable(ItemHandler.CHUNK_CERTUS_QUARTZ.get(), ItemHandler.GEM_CERTUS_QUARTZ.get()));
    blockLootTable.put(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_SLATE.get(), createSpecialTable(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get(), ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get()));
  }
}