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

package com.ridanisaurus.emendatusenigmatica.registries;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.blocks.BlockItemBase;
import com.ridanisaurus.emendatusenigmatica.items.ItemBase;
import com.ridanisaurus.emendatusenigmatica.util.*;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class ItemHandler {

  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

  private static Table<ProcessedMaterials, Materials, RegistryObject<Item>> backingItemTable;
  public static final Supplier<Table<ProcessedMaterials, Materials, RegistryObject<Item>>> itemTable = () -> Optional.ofNullable(backingItemTable).orElse(ImmutableTable.of());

  public static void itemInit() {
    ImmutableTable.Builder<ProcessedMaterials, Materials, RegistryObject<Item>> builder = new ImmutableTable.Builder<>();
    for (ProcessedMaterials processedMaterial : ProcessedMaterials.values()) {
      for (Materials material : Materials.values()) {
        List<String> toCreate = Arrays.asList(material.type);

        //Storage Block Items
        if (processedMaterial == ProcessedMaterials.STORAGE_BLOCK && toCreate.contains("Block")) {
          String storageBlockName = "block_" + material.id;
          builder.put(processedMaterial, material, ITEMS.register(storageBlockName, () -> new BlockItemBase(BlockHandler.storageBlockTable.get().get(processedMaterial, material).get())));
        }
        // Ingots
        if (processedMaterial == ProcessedMaterials.INGOT && toCreate.contains("Ingot")) {
          String ingotName = "ingot_" + material.id;
          builder.put(processedMaterial, material, ITEMS.register(ingotName, material.item));
        }
        // Gems
        if (processedMaterial == ProcessedMaterials.GEM && toCreate.contains("Gem")) {
          String gemName = "gem_" + material.id;
          builder.put(processedMaterial, material, ITEMS.register(gemName, material.item));
        }
        // Nuggets
        if (processedMaterial == ProcessedMaterials.NUGGET && toCreate.contains("Nugget")) {
          String nuggetName = "nugget_" + material.id;
          builder.put(processedMaterial, material, ITEMS.register(nuggetName, material.item));
        }
        // Dusts
        if (processedMaterial == ProcessedMaterials.DUST && toCreate.contains("Dust")) {
          String dustName = "dust_" + material.id;
          builder.put(processedMaterial, material, ITEMS.register(dustName, material.item));
        }
        // Plates
        if (processedMaterial == ProcessedMaterials.PLATE && toCreate.contains("Plate")) {
          String plateName = "plate_" + material.id;
          builder.put(processedMaterial, material, ITEMS.register(plateName, material.item));
        }
        // Chunks
        if (processedMaterial == ProcessedMaterials.CHUNK && toCreate.contains("Chunk")) {
          String chunkName = "chunk_" + material.id;
          builder.put(processedMaterial, material, ITEMS.register(chunkName, material.item));
        }
      }
    }
    backingItemTable = builder.build();
  }

  // Dusts
  public static final RegistryObject<Item> DUST_CHARCOAL = ITEMS.register("dust_charcoal", ItemBase::new);
  public static final RegistryObject<Item> DUST_OBSIDIAN = ITEMS.register("dust_obsidian", ItemBase::new);
  public static final RegistryObject<Item> DUST_QUARTZ = ITEMS.register("dust_quartz", ItemBase::new);
  public static final RegistryObject<Item> DUST_ENDER = ITEMS.register("dust_ender", ItemBase::new);
  public static final RegistryObject<Item> DUST_COKE = ITEMS.register("dust_coke", ItemBase::new);
  public static final RegistryObject<Item> DUST_GRAPHITE = ITEMS.register("dust_graphite", ItemBase::new);
  public static final RegistryObject<Item> DUST_LITHIUM = ITEMS.register("dust_lithium", ItemBase::new);
  public static final RegistryObject<Item> DUST_SALTPETER = ITEMS.register("dust_saltpeter", ItemBase::new);
  public static final RegistryObject<Item> DUST_SULFUR = ITEMS.register("dust_sulfur", ItemBase::new);

  //Ore Items
  private static Table<Strata, Materials, RegistryObject<Item>> backingOreItemTable;
  public static final Supplier<Table<Strata, Materials, RegistryObject<Item>>> oreItemTable = () -> Optional.ofNullable(backingOreItemTable).orElse(ImmutableTable.of());

  public static void oreItems() {
    ImmutableTable.Builder<Strata, Materials, RegistryObject<Item>> builder = new ImmutableTable.Builder<>();
    for (Strata stratum : Strata.values()) {
      for (Materials material : Materials.values()) {
        List<String> toCreate = Arrays.asList(material.type);
        if (material.oreBlock != null && toCreate.contains("Ore")) {
          String oreName = "ore_" + material.id + (stratum != Strata.STONE ? "_" + stratum.suffix : "");
          builder.put(stratum, material, ITEMS.register(oreName, () -> new BlockItemBase(OreHandler.oreBlockTable.get().get(stratum, material).get())));
        }
      }
    }
    backingOreItemTable = builder.build();
  }

  //Machine Items
  public static final RegistryObject<Item> ENIGMATIC_EXCHANGER_ITEM = ITEMS.register("enigmatic_exchanger", () -> new BlockItemBase(BlockHandler.ENIGMATIC_EXCHANGER.get()));
}