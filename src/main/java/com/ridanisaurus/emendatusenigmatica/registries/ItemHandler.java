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
import com.ridanisaurus.emendatusenigmatica.util.*;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

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

        //Storage Block Items
        if (processedMaterial == ProcessedMaterials.STORAGE_BLOCK && !material.isVanilla()) {
          String storageBlockName = "block_" + material.id;
          builder.put(processedMaterial, material, ITEMS.register(storageBlockName, () -> new BlockItemBase(BlockHandler.storageBlockTable.get().get(processedMaterial, material).get())));
        }

        // Ingots
        if (processedMaterial == ProcessedMaterials.INGOT && !material.isGem() && !material.isVanilla()) {
          String ingotName = "ingot_" + material.id;
          builder.put(processedMaterial, material, ITEMS.register(ingotName, material.item));
        }

        /*// Gems
        if (processedMaterial == ProcessedMaterials.GEM && material.isGem() && !material.isVanilla()) {
          String gemName = "gem_" + material.id;
          builder.put(processedMaterial, material, ITEMS.register(gemName, material.item));
        }*/

        // Nuggets
        if (processedMaterial == ProcessedMaterials.NUGGET && !material.isGem() && !material.isVanilla()) {
          String nuggetName = "nugget_" + material.id;
          builder.put(processedMaterial, material, ITEMS.register(nuggetName, material.item));
        }

        // Dusts
        if (processedMaterial == ProcessedMaterials.DUST && material != Materials.REDSTONE && material != Materials.BITUMEN && material != Materials.CHARGED_CERTUS_QUARTZ) {
          String dustName = "dust_" + material.id;
          builder.put(processedMaterial, material, ITEMS.register(dustName, material.item));
        }

        // Plates
        if (processedMaterial == ProcessedMaterials.PLATE && material != Materials.REDSTONE && material != Materials.COAL && !material.isGem()) {
          String plateName = "plate_" + material.id;
          builder.put(processedMaterial, material, ITEMS.register(plateName, material.item));
        }
      }
    }
    backingItemTable = builder.build();
  }

  // Gems
  public static final RegistryObject<Item> GEM_CERTUS_QUARTZ = ITEMS.register("gem_certus_quartz", ItemBase::new);
  public static final RegistryObject<Item> GEM_CHARGED_CERTUS_QUARTZ = ITEMS.register("gem_charged_certus_quartz", ItemBase::new);
  public static final RegistryObject<Item> GEM_FLUIX = ITEMS.register("gem_fluix", ItemBase::new);
  public static final RegistryObject<Item> GEM_FLUORITE = ITEMS.register("gem_fluorite", ItemBase::new);
  public static final RegistryObject<Item> GEM_BITUMEN = ITEMS.register("gem_bitumen", ItemBase::new);

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

  //Ore Chunks
  public static final RegistryObject<Item> CHUNK_COAL = ITEMS.register("chunk_coal", ItemBase::new);
  public static final RegistryObject<Item> CHUNK_IRON = ITEMS.register("chunk_iron", ItemBase::new);
  public static final RegistryObject<Item> CHUNK_GOLD = ITEMS.register("chunk_gold", ItemBase::new);
  public static final RegistryObject<Item> CHUNK_DIAMOND = ITEMS.register("chunk_diamond", ItemBase::new);
  public static final RegistryObject<Item> CHUNK_EMERALD = ITEMS.register("chunk_emerald", ItemBase::new);
  public static final RegistryObject<Item> CHUNK_LAPIS = ITEMS.register("chunk_lapis", ItemBase::new);
  public static final RegistryObject<Item> CHUNK_REDSTONE = ITEMS.register("chunk_redstone", ItemBase::new);
  public static final RegistryObject<Item> CHUNK_COPPER = ITEMS.register("chunk_copper", ItemBase::new);
  public static final RegistryObject<Item> CHUNK_ALUMINUM = ITEMS.register("chunk_aluminum", ItemBase::new);
  public static final RegistryObject<Item> CHUNK_SILVER = ITEMS.register("chunk_silver", ItemBase::new);
  public static final RegistryObject<Item> CHUNK_LEAD = ITEMS.register("chunk_lead", ItemBase::new);
  public static final RegistryObject<Item> CHUNK_NICKEL = ITEMS.register("chunk_nickel", ItemBase::new);
  public static final RegistryObject<Item> CHUNK_URANIUM = ITEMS.register("chunk_uranium", ItemBase::new);
  public static final RegistryObject<Item> CHUNK_OSMIUM = ITEMS.register("chunk_osmium", ItemBase::new);
  public static final RegistryObject<Item> CHUNK_TIN = ITEMS.register("chunk_tin", ItemBase::new);
  public static final RegistryObject<Item> CHUNK_ZINC = ITEMS.register("chunk_zinc", ItemBase::new);
  public static final RegistryObject<Item> CHUNK_CERTUS_QUARTZ = ITEMS.register("chunk_certus_quartz", ItemBase::new);
  public static final RegistryObject<Item> CHUNK_CHARGED_CERTUS_QUARTZ = ITEMS.register("chunk_charged_certus_quartz", ItemBase::new);
  public static final RegistryObject<Item> CHUNK_FLUORITE = ITEMS.register("chunk_fluorite", ItemBase::new);
  public static final RegistryObject<Item> CHUNK_BITUMEN = ITEMS.register("chunk_bitumen", ItemBase::new);

  //Ore Items
  private static Table<Strata, Ores, RegistryObject<Item>> backingOreItemTable;
  public static final Supplier<Table<Strata, Ores, RegistryObject<Item>>> oreItemTable = () -> Optional.ofNullable(backingOreItemTable).orElse(ImmutableTable.of());

  public static void oreItems() {
    ImmutableTable.Builder<Strata, Ores, RegistryObject<Item>> builder = new ImmutableTable.Builder<>();
    for (Strata stratum : Strata.values()) {
      for (Ores ore : Ores.values()) {
        String oreName = "ore_" + ore.id + (stratum != Strata.STONE ? "_" + stratum.suffix : "");
        builder.put(stratum, ore, ITEMS.register(oreName, () -> new BlockItemBase(OreHandler.oreBlockTable.get().get(stratum, ore).get())));
      }
    }
    backingOreItemTable = builder.build();
  }

  //Machine Items
  public static final RegistryObject<Item> ENIGMATIC_EXCHANGER_ITEM = ITEMS.register("enigmatic_exchanger", () -> new BlockItemBase(BlockHandler.ENIGMATIC_EXCHANGER.get()));
}