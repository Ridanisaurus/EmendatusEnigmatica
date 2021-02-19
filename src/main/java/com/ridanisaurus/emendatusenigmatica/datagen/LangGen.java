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

import java.util.Arrays;
import java.util.List;

public class LangGen extends LanguageProvider {
  public LangGen(DataGenerator gen) {
    super(gen, Reference.MOD_ID, "en_us");
  }

  @Override
  protected void addTranslations() {
    // Item Group
    add("itemGroup." + Reference.MOD_ID, Reference.MOD_NAME);

    // JEI Compat
    add("tooltip.emendatusenigmatica.ores.1", "Harvesting ores with Silktouch Enchanted tool will always drop a Chunk. The Vanilla Minecraft logic will apply when using non-Enchanted, or Fortune Enchanted tools.");
    add("tooltip.emendatusenigmatica.ores.2", "These Chunks act as an Ore Block in an Item form, and it can be smelted, doubled, tripled ..etc.");
    add("tooltip.emendatusenigmatica.ores.3", "Furthermore, these Chunks can be turned into their Stone Block Ore variant by using the Stonecutter or in a Crafting Grid.");

    // Misc
    add(BlockHandler.ENIGMATIC_FORTUNIZER.get(), "Enigmatic Fortunizer");
    add("container.emendatusenigmatica.enigmatic_fortunizer", "Enigmatic Fortunizer");
    add(ItemHandler.ENIGMATIC_HAMMER.get(), "Enigmatic Hammer");

    for (ProcessedMaterials processedMaterial : ProcessedMaterials.values()) {
      for (Materials material : Materials.values()) {
        List<String> toCreate = Arrays.asList(material.type);
        // Storage Blocks
        if (processedMaterial == ProcessedMaterials.STORAGE_BLOCK && toCreate.contains("Block") && !material.id.equals("arcane")) {
          StringBuilder sb = new StringBuilder();
          sb.append("Block of ");
          sb.append(material.localisedName);
          add(BlockHandler.backingStorageBlockTable.get(processedMaterial, material).get(), sb.toString());
        }

        // Ingots
        if (processedMaterial == ProcessedMaterials.INGOT && toCreate.contains("Ingot")) {
          StringBuilder sb = new StringBuilder();
          sb.append(material.localisedName);
          sb.append(" Ingot");
          add(ItemHandler.backingItemTable.get(processedMaterial, material).get(), sb.toString());
        }

        // Gems
        if (processedMaterial == ProcessedMaterials.GEM && toCreate.contains("Gem") && !material.id.equals("arcane")) {
          StringBuilder sb = new StringBuilder();
          sb.append(material.localisedName);
          add(ItemHandler.backingItemTable.get(processedMaterial, material).get(), sb.toString());
        }

        // Nuggets
        if (processedMaterial == ProcessedMaterials.NUGGET && toCreate.contains("Nugget")) {
          StringBuilder sb = new StringBuilder();
          sb.append(material.localisedName);
          sb.append(" Nugget");
          add(ItemHandler.backingItemTable.get(processedMaterial, material).get(), sb.toString());
        }

        // Dusts
        if (processedMaterial == ProcessedMaterials.DUST && toCreate.contains("Dust")) {
          StringBuilder sb = new StringBuilder();
          sb.append(material.localisedName);
          sb.append(" Dust");
          add(ItemHandler.backingItemTable.get(processedMaterial, material).get(), sb.toString());
        }

        // Plates
        if (processedMaterial == ProcessedMaterials.PLATE && toCreate.contains("Plate")) {
          StringBuilder sb = new StringBuilder();
          sb.append(material.localisedName);
          sb.append(" Plate");
          add(ItemHandler.backingItemTable.get(processedMaterial, material).get(), sb.toString());
        }

        // Gears
        if (processedMaterial == ProcessedMaterials.GEAR && toCreate.contains("Gear")) {
          StringBuilder sb = new StringBuilder();
          sb.append(material.localisedName);
          sb.append(" Gear");
          add(ItemHandler.backingItemTable.get(processedMaterial, material).get(), sb.toString());
        }

        // Rods
        if (processedMaterial == ProcessedMaterials.ROD && toCreate.contains("Rod")) {
          StringBuilder sb = new StringBuilder();
          sb.append(material.localisedName);
          sb.append(" Rod");
          add(ItemHandler.backingItemTable.get(processedMaterial, material).get(), sb.toString());
        }

        // Chunks
        if (processedMaterial == ProcessedMaterials.CHUNK && toCreate.contains("Chunk") && !material.id.equals("arcane")) {
          StringBuilder sb = new StringBuilder();
          sb.append(material.localisedName);
          sb.append(" Chunk");
          add(ItemHandler.backingItemTable.get(processedMaterial, material).get(), sb.toString());
        }
      }
    }

    // Ores
    for (Strata stratum : Strata.values()) {
      for (Materials material : Materials.values()) {
        List<String> toCreate = Arrays.asList(material.type);
        if (material.oreBlock != null && toCreate.contains("Ore")) {
          StringBuilder sb = new StringBuilder();
          sb.append(material.localisedName);
          sb.append(" Ore");
          if (stratum != Strata.STONE) {
            sb.append(" - ");
            sb.append(stratum.localisedName);
          }
          add(OreHandler.backingOreBlockTable.get(stratum, material).get(), sb.toString());
        }
      }
    }

    add(BlockHandler.backingStorageBlockTable.get(ProcessedMaterials.STORAGE_BLOCK, Materials.ARCANE).get(), "Block of Mana Gems");
    add(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.ARCANE).get(), "Mana Chunk");
    add(ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.ARCANE).get(), "Mana Gem");

    add(ItemHandler.DUST_CHARCOAL.get(), "Charcoal Dust");
    add(ItemHandler.DUST_ENDER.get(), "Ender Dust");
    add(ItemHandler.DUST_GRAPHITE.get(), "Graphite Dust");
    add(ItemHandler.DUST_LITHIUM.get(), "Lithium Dust");
    add(ItemHandler.DUST_OBSIDIAN.get(), "Obsidian Dust");
    add(ItemHandler.DUST_WOOD.get(), "Sawdust");
  }
}