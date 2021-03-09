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
import com.ridanisaurus.emendatusenigmatica.blocks.BlockItemBase;
import com.ridanisaurus.emendatusenigmatica.items.ItemBase;
import com.ridanisaurus.emendatusenigmatica.items.ItemHammer;
import com.ridanisaurus.emendatusenigmatica.util.*;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemHandler {

  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

  public static Table<ProcessedMaterials, Materials, RegistryObject<Item>> backingItemTable;
  public static Map<ResourceLocation, Materials> materialsByName = new HashMap<>();
  //public static final Supplier<Table<ProcessedMaterials, Materials, RegistryObject<Item>>> itemTable = () -> Optional.ofNullable(backingItemTable).orElse(ImmutableTable.of());

  public static void itemInit() {
    ImmutableTable.Builder<ProcessedMaterials, Materials, RegistryObject<Item>> builder = new ImmutableTable.Builder<>();
    for (ProcessedMaterials processedMaterial : ProcessedMaterials.values()) {
      for (Materials material : Materials.values()) {
        List<String> toCreate = Arrays.asList(material.type);

        //Storage Block Items
        if (processedMaterial == ProcessedMaterials.STORAGE_BLOCK && toCreate.contains("Block")) {
          String storageBlockName = material.id + "_block";
          if(material.id.equals("bitumen")) {
            builder.put(processedMaterial, material, ITEMS.register(storageBlockName, () -> new BlockItemBase(BlockHandler.backingStorageBlockTable.get(processedMaterial, material).get(), 16000)));
          } else if(material.id.equals("coke")) {
            builder.put(processedMaterial, material, ITEMS.register(storageBlockName, () -> new BlockItemBase(BlockHandler.backingStorageBlockTable.get(processedMaterial, material).get(), 32000)));
          } else if(material.id.equals("sulfur")) {
            builder.put(processedMaterial, material, ITEMS.register(storageBlockName, () -> new BlockItemBase(BlockHandler.backingStorageBlockTable.get(processedMaterial, material).get(), 12000)));
          }
          else {
            builder.put(processedMaterial, material, ITEMS.register(storageBlockName, () -> new BlockItemBase(BlockHandler.backingStorageBlockTable.get(processedMaterial, material).get(), 0)));
          }
        }
        // Ingots
        if (processedMaterial == ProcessedMaterials.INGOT && toCreate.contains("Ingot")) {
          String ingotName = material.id + "_ingot";
          builder.put(processedMaterial, material, ITEMS.register(ingotName, material.item));
        }
        // Gems
        if (processedMaterial == ProcessedMaterials.GEM && toCreate.contains("Gem")) {
          String gemName = material.id + "_gem";
          builder.put(processedMaterial, material, ITEMS.register(gemName, material.item));
        }
        // Nuggets
        if (processedMaterial == ProcessedMaterials.NUGGET && toCreate.contains("Nugget")) {
          String nuggetName = material.id + "_nugget";
          builder.put(processedMaterial, material, ITEMS.register(nuggetName, material.item));
        }
        // Dusts
        if (processedMaterial == ProcessedMaterials.DUST && toCreate.contains("Dust")) {
          String dustName = material.id + "_dust";
          builder.put(processedMaterial, material, ITEMS.register(dustName, material.item));
        }
        // Plates
        if (processedMaterial == ProcessedMaterials.PLATE && toCreate.contains("Plate")) {
          String plateName = material.id + "_plate";
          builder.put(processedMaterial, material, ITEMS.register(plateName, material.item));
        }
        // Gears
        if (processedMaterial == ProcessedMaterials.GEAR && toCreate.contains("Gear")) {
          String gearName = material.id + "_gear";
          builder.put(processedMaterial, material, ITEMS.register(gearName, material.item));
        }
        // Rods
        if (processedMaterial == ProcessedMaterials.ROD && toCreate.contains("Rod")) {
          String rodName = material.id + "_rod";
          builder.put(processedMaterial, material, ITEMS.register(rodName, material.item));
        }
        // Chunks
        if (processedMaterial == ProcessedMaterials.CHUNK && toCreate.contains("Chunk")) {
          String chunkName = material.id + "_chunk";
          builder.put(processedMaterial, material, ITEMS.register(chunkName, ItemBase::new));
          materialsByName.put(new ResourceLocation(Reference.MOD_ID, chunkName),material);
        }
        // Cluster
        if (processedMaterial == ProcessedMaterials.CLUSTER && toCreate.contains("Cluster")) {
          String clusterName = material.id + "_cluster";
          builder.put(processedMaterial, material, ITEMS.register(clusterName, ItemBase::new));
          materialsByName.put(new ResourceLocation(Reference.MOD_ID, clusterName),material);
        }
      }
    }
    backingItemTable = builder.build();
  }

  // Dusts
  public static final RegistryObject<Item> DUST_CHARCOAL = ITEMS.register("charcoal_dust", ItemBase::new);
  public static final RegistryObject<Item> DUST_OBSIDIAN = ITEMS.register("obsidian_dust", ItemBase::new);
  public static final RegistryObject<Item> DUST_ENDER = ITEMS.register("ender_dust", ItemBase::new);
  public static final RegistryObject<Item> DUST_GRAPHITE = ITEMS.register("graphite_dust", ItemBase::new);
  public static final RegistryObject<Item> DUST_LITHIUM = ITEMS.register("lithium_dust", ItemBase::new);
  public static final RegistryObject<Item> DUST_WOOD = ITEMS.register("wood_dust", ItemBase::new);

  //Ore Items
  public static Table<Strata, Materials, RegistryObject<Item>> backingOreItemTable;
  //public static final Supplier<Table<Strata, Materials, RegistryObject<Item>>> oreItemTable = () -> Optional.ofNullable(backingOreItemTable).orElse(ImmutableTable.of());

  public static void oreItems() {
    ImmutableTable.Builder<Strata, Materials, RegistryObject<Item>> builder = new ImmutableTable.Builder<>();
    for (Strata stratum : Strata.values()) {
      for (Materials material : Materials.values()) {
        List<String> toCreate = Arrays.asList(material.type);
        if (material.oreBlock != null && toCreate.contains("Ore")) {
          String oreName = material.id + (stratum != Strata.STONE ? "_" + stratum.suffix : "") + "_ore";
          builder.put(stratum, material, ITEMS.register(oreName, () -> new BlockItemBase(OreHandler.backingOreBlockTable.get(stratum, material).get(), 0)));
          materialsByName.put(new ResourceLocation(Reference.MOD_ID, oreName),material);
        }
      }
    }
    backingOreItemTable = builder.build();
  }

  // Machine Items
  public static final RegistryObject<Item> ENIGMATIC_FORTUNIZER_ITEM = ITEMS.register("enigmatic_fortunizer", () -> new BlockItemBase(BlockHandler.ENIGMATIC_FORTUNIZER.get(), 0));

  // Hammer
  public static final RegistryObject<Item> ENIGMATIC_HAMMER = ITEMS.register("enigmatic_hammer", ItemHammer::new);
}