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
import com.ridanisaurus.emendatusenigmatica.util.Ores;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import com.ridanisaurus.emendatusenigmatica.util.Strata;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class LangGen extends LanguageProvider {
  public LangGen(DataGenerator gen) {
    super(gen, Reference.MOD_ID, "en_us");
  }

  @Override
  protected void addTranslations() {
    // Item Group
    add("itemGroup." + Reference.MOD_ID, Reference.MOD_NAME);

    // JEI Compat
    add("tooltip.emendatusenigmatica.ores.1", "Harvesting ores with Silktouch Enchanted tool will always drop a Chunk, while using non-Enchanted, or Fortune Enchanted tools will use the same logic used in Vanilla Minecraft.\n\n");
    add("tooltip.emendatusenigmatica.ores.2", "These Chunks act as an Ore Block where it can be smelted, or have its output doubled/tripled ..etc. through other means of ore processing setups.\n\n\n");
    add("tooltip.emendatusenigmatica.ores.3", "Furthermore, these Chunks can be turned into their Stone Block Ore variant by using the Stonecutter.");

    // Misc
    add(BlockHandler.ENIGMATIC_EXCHANGER.get(), "Enigmatic Exchanger");

    // Storage Blocks
    add(BlockHandler.BLOCK_COPPER.get(), "Copper Block");
    add(BlockHandler.BLOCK_ALUMINUM.get(), "Aluminum Block");
    add(BlockHandler.BLOCK_SILVER.get(), "Silver Block");
    add(BlockHandler.BLOCK_LEAD.get(), "Lead Block");
    add(BlockHandler.BLOCK_NICKEL.get(), "Nickel Block");
    add(BlockHandler.BLOCK_URANIUM.get(), "Uranium Block");
    add(BlockHandler.BLOCK_OSMIUM.get(), "Osmium Block");
    add(BlockHandler.BLOCK_TIN.get(), "Tin Block");
    add(BlockHandler.BLOCK_ZINC.get(), "Zinc Block");
    add(BlockHandler.BLOCK_BRONZE.get(), "Bronze Block");
    add(BlockHandler.BLOCK_BRASS.get(), "Brass Block");
    add(BlockHandler.BLOCK_CONSTANTAN.get(), "Constantan Block");
    add(BlockHandler.BLOCK_ELECTRUM.get(), "Electrum Block");
    add(BlockHandler.BLOCK_STEEL.get(), "Steel Block");

    // Ores
    for (Strata stratum : Strata.values()) {
      for (Ores ore : Ores.values()) {
        StringBuilder sb = new StringBuilder();
        sb.append(ore.localisedName);
        sb.append(" Ore");
        if (stratum != Strata.STONE) {
          sb.append(" - ");
          sb.append(stratum.localisedName);
        }
        add(OreHandler.oreBlockTable.get().get(stratum, ore).get(), sb.toString());
      }
    }

    // Ingots
    add(ItemHandler.INGOT_COPPER.get(), "Copper Ingot");
    add(ItemHandler.INGOT_ALUMINUM.get(), "Aluminum Ingot");
    add(ItemHandler.INGOT_SILVER.get(), "Silver Ingot");
    add(ItemHandler.INGOT_LEAD.get(), "Lead Ingot");
    add(ItemHandler.INGOT_NICKEL.get(), "Nickel Ingot");
    add(ItemHandler.INGOT_URANIUM.get(), "Uranium Ingot");
    add(ItemHandler.INGOT_OSMIUM.get(), "Osmium Ingot");
    add(ItemHandler.INGOT_TIN.get(), "Tin Ingot");
    add(ItemHandler.INGOT_ZINC.get(), "Zinc Ingot");
    add(ItemHandler.INGOT_BRONZE.get(), "Bronze Ingot");
    add(ItemHandler.INGOT_BRASS.get(), "Brass Ingot");
    add(ItemHandler.INGOT_CONSTANTAN.get(), "Constantan Ingot");
    add(ItemHandler.INGOT_ELECTRUM.get(), "Electrum Ingot");
    add(ItemHandler.INGOT_STEEL.get(), "Steel Ingot");

    // Nuggets
    add(ItemHandler.NUGGET_COPPER.get(), "Copper Nugget");
    add(ItemHandler.NUGGET_ALUMINUM.get(), "Aluminum Nugget");
    add(ItemHandler.NUGGET_SILVER.get(), "Silver Nugget");
    add(ItemHandler.NUGGET_LEAD.get(), "Lead Nugget");
    add(ItemHandler.NUGGET_NICKEL.get(), "Nickel Nugget");
    add(ItemHandler.NUGGET_URANIUM.get(), "Uranium Nugget");
    add(ItemHandler.NUGGET_OSMIUM.get(), "Osmium Nugget");
    add(ItemHandler.NUGGET_TIN.get(), "Tin Nugget");
    add(ItemHandler.NUGGET_ZINC.get(), "Zinc Nugget");
    add(ItemHandler.NUGGET_BRONZE.get(), "Bronze Nugget");
    add(ItemHandler.NUGGET_BRASS.get(), "Brass Nugget");
    add(ItemHandler.NUGGET_CONSTANTAN.get(), "Constantan Nugget");
    add(ItemHandler.NUGGET_ELECTRUM.get(), "Electrum Nugget");
    add(ItemHandler.NUGGET_STEEL.get(), "Steel Nugget");

    // Dusts
    add(ItemHandler.DUST_COPPER.get(), "Copper Dust");
    add(ItemHandler.DUST_ALUMINUM.get(), "Aluminum Dust");
    add(ItemHandler.DUST_SILVER.get(), "Silver Dust");
    add(ItemHandler.DUST_LEAD.get(), "Lead Dust");
    add(ItemHandler.DUST_NICKEL.get(), "Nickel Dust");
    add(ItemHandler.DUST_URANIUM.get(), "Uranium Dust");
    add(ItemHandler.DUST_OSMIUM.get(), "Osmium Dust");
    add(ItemHandler.DUST_TIN.get(), "Tin Dust");
    add(ItemHandler.DUST_ZINC.get(), "Zinc Dust");
    add(ItemHandler.DUST_BRONZE.get(), "Bronze Dust");
    add(ItemHandler.DUST_BRASS.get(), "Brass Dust");
    add(ItemHandler.DUST_CONSTANTAN.get(), "Constantan Dust");
    add(ItemHandler.DUST_ELECTRUM.get(), "Electrum Dust");
    add(ItemHandler.DUST_STEEL.get(), "Steel Dust");
    add(ItemHandler.DUST_CHARCOAL.get(), "Charcoal Dust");
    add(ItemHandler.DUST_COAL.get(), "Coal Dust");
    add(ItemHandler.DUST_DIAMOND.get(), "Diamond Dust");
    add(ItemHandler.DUST_EMERALD.get(), "Emerald Dust");
    add(ItemHandler.DUST_GOLD.get(), "Gold Dust");
    add(ItemHandler.DUST_IRON.get(), "Iron Dust");
    add(ItemHandler.DUST_LAPIS.get(), "Lapis lazuli Dust");
    add(ItemHandler.DUST_OBSIDIAN.get(), "Obsidian Dust");
    add(ItemHandler.DUST_QUARTZ.get(), "Quartz Dust");
    add(ItemHandler.DUST_CERTUS_QUARTZ.get(), "Certus Quartz Dust");
    add(ItemHandler.DUST_FLUIX.get(), "Fluix Dust");
    add(ItemHandler.DUST_ENDER.get(), "Ender Dust");
    add(ItemHandler.DUST_COKE.get(), "Coke Dust");
    add(ItemHandler.DUST_GRAPHITE.get(), "Graphite Dust");
    add(ItemHandler.DUST_LITHIUM.get(), "Lithium Dust");
    add(ItemHandler.DUST_SALTPETER.get(), "Saltpeter Dust");
    add(ItemHandler.DUST_SULFUR.get(), "Sulfur Dust");
    add(ItemHandler.DUST_FLUORITE.get(), "Fluorite Dust");

    // Gems
    add(ItemHandler.GEM_CERTUS_QUARTZ.get(), "Certus Quartz Crystal");
    add(ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get(), "Charged Certus Quartz Crystal");
    add(ItemHandler.GEM_FLUIX.get(), "Fluix Crystal");
    add(ItemHandler.GEM_FLUORITE.get(), "Fluorite");

    // Plates
    add(ItemHandler.PLATE_COPPER.get(), "Copper Plate");
    add(ItemHandler.PLATE_ALUMINUM.get(), "Aluminum Plate");
    add(ItemHandler.PLATE_SILVER.get(), "Silver Plate");
    add(ItemHandler.PLATE_LEAD.get(), "Lead Plate");
    add(ItemHandler.PLATE_NICKEL.get(), "Nickel Plate");
    add(ItemHandler.PLATE_URANIUM.get(), "Uranium Plate");
    add(ItemHandler.PLATE_OSMIUM.get(), "Osmium Plate");
    add(ItemHandler.PLATE_TIN.get(), "Tin Plate");
    add(ItemHandler.PLATE_ZINC.get(), "Zinc Plate");
    add(ItemHandler.PLATE_BRONZE.get(), "Bronze Plate");
    add(ItemHandler.PLATE_BRASS.get(), "Brass Plate");
    add(ItemHandler.PLATE_CONSTANTAN.get(), "Constantan Plate");
    add(ItemHandler.PLATE_ELECTRUM.get(), "Electrum Plate");
    add(ItemHandler.PLATE_STEEL.get(), "Steel Plate");
    add(ItemHandler.PLATE_GOLD.get(), "Gold Plate");
    add(ItemHandler.PLATE_IRON.get(), "Iron Plate");
    add(ItemHandler.PLATE_LAPIS.get(), "Lapis lazuli Plate");

    // Chunks
    add(ItemHandler.CHUNK_COAL.get(), "Coal Chunk");
    add(ItemHandler.CHUNK_IRON.get(), "Iron Chunk");
    add(ItemHandler.CHUNK_GOLD.get(), "Gold Chunk");
    add(ItemHandler.CHUNK_DIAMOND.get(), "Diamond Chunk");
    add(ItemHandler.CHUNK_EMERALD.get(), "Emerald Chunk");
    add(ItemHandler.CHUNK_LAPIS.get(), "Lapis lazuli Chunk");
    add(ItemHandler.CHUNK_REDSTONE.get(), "Redstone Chunk");
    add(ItemHandler.CHUNK_COPPER.get(), "Copper Chunk");
    add(ItemHandler.CHUNK_ALUMINUM.get(), "Aluminum Chunk");
    add(ItemHandler.CHUNK_SILVER.get(), "Silver Chunk");
    add(ItemHandler.CHUNK_LEAD.get(), "Lead Chunk");
    add(ItemHandler.CHUNK_NICKEL.get(), "Nickel Chunk");
    add(ItemHandler.CHUNK_URANIUM.get(), "Uranium Chunk");
    add(ItemHandler.CHUNK_OSMIUM.get(), "Osmium Chunk");
    add(ItemHandler.CHUNK_TIN.get(), "Tin Chunk");
    add(ItemHandler.CHUNK_ZINC.get(), "Zinc Chunk");
    add(ItemHandler.CHUNK_CERTUS_QUARTZ.get(), "Certus Quartz Chunk");
    add(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get(), "Charged Certus Quartz Chunk");
    add(ItemHandler.CHUNK_FLUORITE.get(), "Fluorite Chunk");
  }
}