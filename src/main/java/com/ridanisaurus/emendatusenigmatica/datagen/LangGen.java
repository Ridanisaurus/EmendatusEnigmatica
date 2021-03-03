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
import com.ridanisaurus.emendatusenigmatica.util.Reference;
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
    add("tooltip.emendatusenigmatica.ores.1", "Harvesting ores with Silktouch Enchanted tool will always drop a Chunk. The Vanilla Minecraft logic will apply when using non-Enchanted, or Fortune Enchanted tools.");
    add("tooltip.emendatusenigmatica.ores.2", "These Chunks act as an Ore Block in an Item form, and it can be smelted, doubled, tripled ..etc.");
    add("tooltip.emendatusenigmatica.ores.3", "Furthermore, these Chunks can be turned into their Stone Block Ore variant by using the Stonecutter or in a Crafting Grid.");

    // Misc
    add(EERegistrar.ENIGMATIC_FORTUNIZER.get(), "\u00A7aEnigmatic Fortunizer\u00A7r");
    add("container.emendatusenigmatica.enigmatic_fortunizer", "Enigmatic Fortunizer");
    add("tooltip.emendatusenigmatica.enigmatic_fortunizer.1", "The Enigmatic Fortunizer takes Gem-Based \u00A73\u00A7nChunks/Ores\u00A7r + a \u00A73\u00A7nPickaxe\u00A7r, and will output the appropriate \u00A79\u00A7nItem\u00A7r, applying enchantment effects where applicable such as \u00A7cFortune\u00A7r, \u00A7cUnbreaking\u00A7r, \u00A7cEfficiency\u00A7r... etc.\n" +
            "Once your \u00A73\u00A7nPickaxe\u00A7r reaches \u00A760\u00A7r Durability, the operation will stop, and your Pickaxe can be extracted for repairs.\n\n" +
            "\u00A76Example:\u00A7r Diamond Chunks + Pickaxe = Diamonds");
    add("tooltip.emendatusenigmatica.enigmatic_fortunizer.2", "Hold \u00A7c\u00A7l[SHIFT]\u00A7r for more information.");
    add(EERegistrar.ENIGMATIC_HAMMER.get(), "Enigmatic Hammer");

    for (MaterialModel material : EELoader.MATERIALS) {
      for (String processedType : material.getProcessedType()) {

        // Storage Blocks
        if (processedType.equals("storage_block") && !material.getId().equals("arcane")) {
          StringBuilder sb = new StringBuilder();
          sb.append("Block of ");
          sb.append(material.getLocalisedName());
          add(EERegistrar.storageBlockMap.get(material.getId()).get(), sb.toString());
        }
        if (processedType.equals("storage_block") && material.getId().equals("arcane")) {
          StringBuilder sb = new StringBuilder();
          sb.append("Block of Mana Gems");
          add(EERegistrar.storageBlockMap.get(material.getId()).get(), sb.toString());
        }

        // Ingots
        if (processedType.equals("ingot")) {
          StringBuilder sb = new StringBuilder();
          sb.append(material.getLocalisedName());
          sb.append(" Ingot");
          add(EERegistrar.ingotMap.get(material.getId()).get(), sb.toString());
        }

        // Gems
        if (processedType.equals("gem") && !material.getId().equals("arcane") && !material.getId().equals("coal_coke")) {
          StringBuilder sb = new StringBuilder();
          sb.append(material.getLocalisedName());
          sb.append(" Gem");
          add(EERegistrar.gemMap.get(material.getId()).get(), sb.toString());
        }
        if (processedType.equals("gem") && material.getId().equals("arcane")) {
          StringBuilder sb = new StringBuilder();
          sb.append("Mana Gem");
          add(EERegistrar.gemMap.get(material.getId()).get(), sb.toString());
        }
        if (processedType.equals("gem") && material.getId().equals("coal_coke")) {
          StringBuilder sb = new StringBuilder();
          sb.append("Coal Coke");
          add(EERegistrar.gemMap.get(material.getId()).get(), sb.toString());
        }

        // Nuggets
        if (processedType.equals("nugget")) {
          StringBuilder sb = new StringBuilder();
          sb.append(material.getLocalisedName());
          sb.append(" Nugget");
          add(EERegistrar.nuggetMap.get(material.getId()).get(), sb.toString());
        }

        // Dusts
        if (processedType.equals("dust") && !material.getId().equals("wood")) {
          StringBuilder sb = new StringBuilder();
          sb.append(material.getLocalisedName());
          sb.append(" Dust");
          add(EERegistrar.dustMap.get(material.getId()).get(), sb.toString());
        }
        if (processedType.equals("dust") && material.getId().equals("wood")) {
          StringBuilder sb = new StringBuilder();
          sb.append("Sawdust");
          add(EERegistrar.dustMap.get(material.getId()).get(), sb.toString());
        }

        // Plates
        if (processedType.equals("plate")) {
          StringBuilder sb = new StringBuilder();
          sb.append(material.getLocalisedName());
          sb.append(" Plate");
          add(EERegistrar.plateMap.get(material.getId()).get(), sb.toString());
        }

        // Gears
        if (processedType.equals("gear")) {
          StringBuilder sb = new StringBuilder();
          sb.append(material.getLocalisedName());
          sb.append(" Gear");
          add(EERegistrar.gearMap.get(material.getId()).get(), sb.toString());
        }

        // Rods
        if (processedType.equals("rod")) {
          StringBuilder sb = new StringBuilder();
          sb.append(material.getLocalisedName());
          sb.append(" Rod");
          add(EERegistrar.rodMap.get(material.getId()).get(), sb.toString());
        }

        // Chunks
        if (processedType.equals("chunk") && !material.getId().equals("arcane")) {
          StringBuilder sb = new StringBuilder();
          sb.append(material.getLocalisedName());
          sb.append(" Chunk");
          add(EERegistrar.chunkMap.get(material.getId()).get(), sb.toString());
        }
        if (processedType.equals("chunk") && material.getId().equals("arcane")) {
          StringBuilder sb = new StringBuilder();
          sb.append("Mana Chunk");
          add(EERegistrar.chunkMap.get(material.getId()).get(), sb.toString());
        }

        // Clusters
        if (processedType.equals("cluster") && !material.getId().equals("arcane")) {
          StringBuilder sb = new StringBuilder();
          sb.append(material.getLocalisedName());
          sb.append(" Cluster");
          add(EERegistrar.clusterMap.get(material.getId()).get(), sb.toString());
        }
        if (processedType.equals("cluster") && material.getId().equals("arcane")) {
          StringBuilder sb = new StringBuilder();
          sb.append("Mana Cluster");
          add(EERegistrar.clusterMap.get(material.getId()).get(), sb.toString());
        }
      }
    }

    for (MaterialModel material : EELoader.MATERIALS) {
      for (StrataModel stratum : EELoader.STRATA) {
        if (material.getProcessedType().contains("ore")) {

          StringBuilder sb = new StringBuilder();
          sb.append(material.getLocalisedName());
          sb.append(" Ore");

          if (!stratum.getId().equals("minecraft_stone")) {
            sb.append(" - ");
            sb.append(stratum.getLocalizedName());
          }

          add(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get(), sb.toString());
        }
      }
    }
  }
  @Override
  public String getName() {
    return "Emendatus Enigmatica Languages: en_us";
  }
}