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
import com.ridanisaurus.emendatusenigmatica.util.*;
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

    for (ProcessedMaterials processedMaterial : ProcessedMaterials.values()) {
      for (Materials material : Materials.values()) {
        // Storage Blocks
        if (processedMaterial == ProcessedMaterials.STORAGE_BLOCK && !material.isVanilla()) {
          StringBuilder sb = new StringBuilder();
          sb.append(material.localisedName);
          sb.append(" Block");
          add(BlockHandler.storageBlockTable.get().get(processedMaterial, material).get(), sb.toString());
        }

        // Ingots
        if (processedMaterial == ProcessedMaterials.INGOT && !material.isGem() && !material.isVanilla()) {
          StringBuilder sb = new StringBuilder();
          sb.append(material.localisedName);
          sb.append(" Ingot");
          add(ItemHandler.itemTable.get().get(processedMaterial, material).get(), sb.toString());
        }

        /*// Gems
        if (processedMaterial == ProcessedMaterials.GEM && material.isGem() && !material.isVanilla()) {
          StringBuilder sb = new StringBuilder();
          sb.append(material.localisedName);
          sb.append(" Test");
          add(ItemHandler.itemTable.get().get(processedMaterial, material).get(), sb.toString());
        }*/

        // Nuggets
        if (processedMaterial == ProcessedMaterials.NUGGET && !material.isGem() && !material.isVanilla()) {
          StringBuilder sb = new StringBuilder();
          sb.append(material.localisedName);
          sb.append(" Nugget");
          add(ItemHandler.itemTable.get().get(processedMaterial, material).get(), sb.toString());
        }

        // Dusts
        if (processedMaterial == ProcessedMaterials.DUST && material != Materials.REDSTONE && material != Materials.BITUMEN && material != Materials.CHARGED_CERTUS_QUARTZ) {
          StringBuilder sb = new StringBuilder();
          sb.append(material.localisedName);
          sb.append(" Dust");
          add(ItemHandler.itemTable.get().get(processedMaterial, material).get(), sb.toString());
        }

        // Plates
        if (processedMaterial == ProcessedMaterials.PLATE && material != Materials.REDSTONE && material != Materials.COAL && !material.isGem()) {
          StringBuilder sb = new StringBuilder();
          sb.append(material.localisedName);
          sb.append(" Plate");
          add(ItemHandler.itemTable.get().get(processedMaterial, material).get(), sb.toString());
        }
      }
    }

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

    add(ItemHandler.DUST_CHARCOAL.get(), "Charcoal Dust");
    add(ItemHandler.DUST_COKE.get(), "Coke Dust");
    add(ItemHandler.DUST_ENDER.get(), "Ender Dust");
    add(ItemHandler.DUST_GRAPHITE.get(), "Graphite Dust");
    add(ItemHandler.DUST_LITHIUM.get(), "Lithium Dust");
    add(ItemHandler.DUST_OBSIDIAN.get(), "Obsidian Dust");
    add(ItemHandler.DUST_QUARTZ.get(), "Nether Quartz Dust");
    add(ItemHandler.DUST_SALTPETER.get(), "Saltpeter Dust");
    add(ItemHandler.DUST_SULFUR.get(), "Sulfur Dust");

    add(ItemHandler.GEM_CERTUS_QUARTZ.get(), "Certus Quartz Crystal");
    add(ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get(), "Charged Certus Quartz Crystal");
    add(ItemHandler.GEM_FLUIX.get(), "Fluix Crystal");
    add(ItemHandler.GEM_FLUORITE.get(), "Fluorite");
    add(ItemHandler.GEM_BITUMEN.get(), "Bitumen");

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
    add(ItemHandler.CHUNK_BITUMEN.get(), "Bitumen Chunk");
  }
}