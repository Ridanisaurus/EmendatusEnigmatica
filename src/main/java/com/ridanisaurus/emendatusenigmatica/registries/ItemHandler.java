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

import com.ridanisaurus.emendatusenigmatica.blocks.BlockItemBase;
import com.ridanisaurus.emendatusenigmatica.items.ItemBase;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemHandler {

  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

  // Ingots
  public static final RegistryObject<Item> INGOT_COPPER = ITEMS.register("ingot_copper", ItemBase::new);
  public static final RegistryObject<Item> INGOT_ALUMINUM = ITEMS.register("ingot_aluminum", ItemBase::new);
  public static final RegistryObject<Item> INGOT_SILVER = ITEMS.register("ingot_silver", ItemBase::new);
  public static final RegistryObject<Item> INGOT_LEAD = ITEMS.register("ingot_lead", ItemBase::new);
  public static final RegistryObject<Item> INGOT_NICKEL = ITEMS.register("ingot_nickel", ItemBase::new);
  public static final RegistryObject<Item> INGOT_URANIUM = ITEMS.register("ingot_uranium", ItemBase::new);
  public static final RegistryObject<Item> INGOT_OSMIUM = ITEMS.register("ingot_osmium", ItemBase::new);
  public static final RegistryObject<Item> INGOT_TIN = ITEMS.register("ingot_tin", ItemBase::new);
  public static final RegistryObject<Item> INGOT_ZINC = ITEMS.register("ingot_zinc", ItemBase::new);
  public static final RegistryObject<Item> INGOT_BRONZE = ITEMS.register("ingot_bronze", ItemBase::new);
  public static final RegistryObject<Item> INGOT_BRASS = ITEMS.register("ingot_brass", ItemBase::new);
  public static final RegistryObject<Item> INGOT_CONSTANTAN = ITEMS.register("ingot_constantan", ItemBase::new);
  public static final RegistryObject<Item> INGOT_ELECTRUM = ITEMS.register("ingot_electrum", ItemBase::new);
  public static final RegistryObject<Item> INGOT_STEEL = ITEMS.register("ingot_steel", ItemBase::new);

  // Nuggets
  public static final RegistryObject<Item> NUGGET_COPPER = ITEMS.register("nugget_copper", ItemBase::new);
  public static final RegistryObject<Item> NUGGET_ALUMINUM = ITEMS.register("nugget_aluminum", ItemBase::new);
  public static final RegistryObject<Item> NUGGET_SILVER = ITEMS.register("nugget_silver", ItemBase::new);
  public static final RegistryObject<Item> NUGGET_LEAD = ITEMS.register("nugget_lead", ItemBase::new);
  public static final RegistryObject<Item> NUGGET_NICKEL = ITEMS.register("nugget_nickel", ItemBase::new);
  public static final RegistryObject<Item> NUGGET_URANIUM = ITEMS.register("nugget_uranium", ItemBase::new);
  public static final RegistryObject<Item> NUGGET_OSMIUM = ITEMS.register("nugget_osmium", ItemBase::new);
  public static final RegistryObject<Item> NUGGET_TIN = ITEMS.register("nugget_tin", ItemBase::new);
  public static final RegistryObject<Item> NUGGET_ZINC = ITEMS.register("nugget_zinc", ItemBase::new);
  public static final RegistryObject<Item> NUGGET_BRONZE = ITEMS.register("nugget_bronze", ItemBase::new);
  public static final RegistryObject<Item> NUGGET_BRASS = ITEMS.register("nugget_brass", ItemBase::new);
  public static final RegistryObject<Item> NUGGET_CONSTANTAN = ITEMS.register("nugget_constantan", ItemBase::new);
  public static final RegistryObject<Item> NUGGET_ELECTRUM = ITEMS.register("nugget_electrum", ItemBase::new);
  public static final RegistryObject<Item> NUGGET_STEEL = ITEMS.register("nugget_steel", ItemBase::new);

  // Dusts
  public static final RegistryObject<Item> DUST_COPPER = ITEMS.register("dust_copper", ItemBase::new);
  public static final RegistryObject<Item> DUST_ALUMINUM = ITEMS.register("dust_aluminum", ItemBase::new);
  public static final RegistryObject<Item> DUST_SILVER = ITEMS.register("dust_silver", ItemBase::new);
  public static final RegistryObject<Item> DUST_LEAD = ITEMS.register("dust_lead", ItemBase::new);
  public static final RegistryObject<Item> DUST_NICKEL = ITEMS.register("dust_nickel", ItemBase::new);
  public static final RegistryObject<Item> DUST_URANIUM = ITEMS.register("dust_uranium", ItemBase::new);
  public static final RegistryObject<Item> DUST_OSMIUM = ITEMS.register("dust_osmium", ItemBase::new);
  public static final RegistryObject<Item> DUST_TIN = ITEMS.register("dust_tin", ItemBase::new);
  public static final RegistryObject<Item> DUST_ZINC = ITEMS.register("dust_zinc", ItemBase::new);
  public static final RegistryObject<Item> DUST_BRONZE = ITEMS.register("dust_bronze", ItemBase::new);
  public static final RegistryObject<Item> DUST_BRASS = ITEMS.register("dust_brass", ItemBase::new);
  public static final RegistryObject<Item> DUST_CONSTANTAN = ITEMS.register("dust_constantan", ItemBase::new);
  public static final RegistryObject<Item> DUST_ELECTRUM = ITEMS.register("dust_electrum", ItemBase::new);
  public static final RegistryObject<Item> DUST_STEEL = ITEMS.register("dust_steel", ItemBase::new);
  public static final RegistryObject<Item> DUST_CHARCOAL = ITEMS.register("dust_charcoal", ItemBase::new);
  public static final RegistryObject<Item> DUST_COAL = ITEMS.register("dust_coal", ItemBase::new);
  public static final RegistryObject<Item> DUST_DIAMOND = ITEMS.register("dust_diamond", ItemBase::new);
  public static final RegistryObject<Item> DUST_EMERALD = ITEMS.register("dust_emerald", ItemBase::new);
  public static final RegistryObject<Item> DUST_GOLD = ITEMS.register("dust_gold", ItemBase::new);
  public static final RegistryObject<Item> DUST_IRON = ITEMS.register("dust_iron", ItemBase::new);
  public static final RegistryObject<Item> DUST_LAPIS = ITEMS.register("dust_lapis", ItemBase::new);
  public static final RegistryObject<Item> DUST_OBSIDIAN = ITEMS.register("dust_obsidian", ItemBase::new);
  public static final RegistryObject<Item> DUST_QUARTZ = ITEMS.register("dust_quartz", ItemBase::new);
  public static final RegistryObject<Item> DUST_CERTUS_QUARTZ = ITEMS.register("dust_certus_quartz", ItemBase::new);
  public static final RegistryObject<Item> DUST_FLUIX = ITEMS.register("dust_fluix", ItemBase::new);
  public static final RegistryObject<Item> DUST_ENDER = ITEMS.register("dust_ender", ItemBase::new);
  public static final RegistryObject<Item> DUST_COKE = ITEMS.register("dust_coke", ItemBase::new);
  public static final RegistryObject<Item> DUST_GRAPHITE = ITEMS.register("dust_graphite", ItemBase::new);
  public static final RegistryObject<Item> DUST_LITHIUM = ITEMS.register("dust_lithium", ItemBase::new);
  public static final RegistryObject<Item> DUST_SALTPETER = ITEMS.register("dust_saltpeter", ItemBase::new);
  public static final RegistryObject<Item> DUST_SULFUR = ITEMS.register("dust_sulfur", ItemBase::new);
  public static final RegistryObject<Item> DUST_FLUORITE = ITEMS.register("dust_fluorite", ItemBase::new);

  // Plates
  public static final RegistryObject<Item> PLATE_COPPER = ITEMS.register("plate_copper", ItemBase::new);
  public static final RegistryObject<Item> PLATE_ALUMINUM = ITEMS.register("plate_aluminum", ItemBase::new);
  public static final RegistryObject<Item> PLATE_SILVER = ITEMS.register("plate_silver", ItemBase::new);
  public static final RegistryObject<Item> PLATE_LEAD = ITEMS.register("plate_lead", ItemBase::new);
  public static final RegistryObject<Item> PLATE_NICKEL = ITEMS.register("plate_nickel", ItemBase::new);
  public static final RegistryObject<Item> PLATE_URANIUM = ITEMS.register("plate_uranium", ItemBase::new);
  public static final RegistryObject<Item> PLATE_OSMIUM = ITEMS.register("plate_osmium", ItemBase::new);
  public static final RegistryObject<Item> PLATE_TIN = ITEMS.register("plate_tin", ItemBase::new);
  public static final RegistryObject<Item> PLATE_ZINC = ITEMS.register("plate_zinc", ItemBase::new);
  public static final RegistryObject<Item> PLATE_BRONZE = ITEMS.register("plate_bronze", ItemBase::new);
  public static final RegistryObject<Item> PLATE_BRASS = ITEMS.register("plate_brass", ItemBase::new);
  public static final RegistryObject<Item> PLATE_CONSTANTAN = ITEMS.register("plate_constantan", ItemBase::new);
  public static final RegistryObject<Item> PLATE_ELECTRUM = ITEMS.register("plate_electrum", ItemBase::new);
  public static final RegistryObject<Item> PLATE_STEEL = ITEMS.register("plate_steel", ItemBase::new);
  public static final RegistryObject<Item> PLATE_GOLD = ITEMS.register("plate_gold", ItemBase::new);
  public static final RegistryObject<Item> PLATE_IRON = ITEMS.register("plate_iron", ItemBase::new);
  public static final RegistryObject<Item> PLATE_LAPIS = ITEMS.register("plate_lapis", ItemBase::new);

  // Gems
  public static final RegistryObject<Item> GEM_CERTUS_QUARTZ = ITEMS.register("gem_certus_quartz", ItemBase::new);
  public static final RegistryObject<Item> GEM_CHARGED_CERTUS_QUARTZ = ITEMS.register("gem_charged_certus_quartz", ItemBase::new);
  public static final RegistryObject<Item> GEM_FLUIX = ITEMS.register("gem_fluix", ItemBase::new);
  public static final RegistryObject<Item> GEM_FLUORITE = ITEMS.register("gem_fluorite", ItemBase::new);

  //Block Items
  public static final RegistryObject<Item> BLOCK_COPPER_ITEM = ITEMS.register("block_copper", () -> new BlockItemBase(BlockHandler.BLOCK_COPPER.get()));
  public static final RegistryObject<Item> BLOCK_ALUMINUM_ITEM = ITEMS.register("block_aluminum", () -> new BlockItemBase(BlockHandler.BLOCK_ALUMINUM.get()));
  public static final RegistryObject<Item> BLOCK_SILVER_ITEM = ITEMS.register("block_silver", () -> new BlockItemBase(BlockHandler.BLOCK_SILVER.get()));
  public static final RegistryObject<Item> BLOCK_LEAD_ITEM = ITEMS.register("block_lead", () -> new BlockItemBase(BlockHandler.BLOCK_LEAD.get()));
  public static final RegistryObject<Item> BLOCK_NICKEL_ITEM = ITEMS.register("block_nickel", () -> new BlockItemBase(BlockHandler.BLOCK_NICKEL.get()));
  public static final RegistryObject<Item> BLOCK_URANIUM_ITEM = ITEMS.register("block_uranium", () -> new BlockItemBase(BlockHandler.BLOCK_URANIUM.get()));
  public static final RegistryObject<Item> BLOCK_OSMIUM_ITEM = ITEMS.register("block_osmium", () -> new BlockItemBase(BlockHandler.BLOCK_OSMIUM.get()));
  public static final RegistryObject<Item> BLOCK_TIN_ITEM = ITEMS.register("block_tin", () -> new BlockItemBase(BlockHandler.BLOCK_TIN.get()));
  public static final RegistryObject<Item> BLOCK_ZINC_ITEM = ITEMS.register("block_zinc", () -> new BlockItemBase(BlockHandler.BLOCK_ZINC.get()));
  public static final RegistryObject<Item> BLOCK_BRONZE_ITEM = ITEMS.register("block_bronze",  () -> new BlockItemBase(BlockHandler.BLOCK_BRONZE.get()));
  public static final RegistryObject<Item> BLOCK_BRASS_ITEM = ITEMS.register("block_brass", () -> new BlockItemBase(BlockHandler.BLOCK_BRASS.get()));
  public static final RegistryObject<Item> BLOCK_CONSTANTAN_ITEM = ITEMS.register("block_constantan", () -> new BlockItemBase(BlockHandler.BLOCK_CONSTANTAN.get()));
  public static final RegistryObject<Item> BLOCK_ELECTRUM_ITEM = ITEMS.register("block_electrum", () -> new BlockItemBase(BlockHandler.BLOCK_ELECTRUM.get()));
  public static final RegistryObject<Item> BLOCK_STEEL_ITEM = ITEMS.register("block_steel", () -> new BlockItemBase(BlockHandler.BLOCK_STEEL.get()));

  //Ore Items
  public static final RegistryObject<Item> ORE_COAL_ITEM = ITEMS.register("ore_coal", () -> new BlockItemBase(BlockHandler.ORE_COAL.get()));
  public static final RegistryObject<Item> ORE_IRON_ITEM = ITEMS.register("ore_iron", () -> new BlockItemBase(BlockHandler.ORE_IRON.get()));
  public static final RegistryObject<Item> ORE_GOLD_ITEM = ITEMS.register("ore_gold", () -> new BlockItemBase(BlockHandler.ORE_GOLD.get()));
  public static final RegistryObject<Item> ORE_DIAMOND_ITEM = ITEMS.register("ore_diamond", () -> new BlockItemBase(BlockHandler.ORE_DIAMOND.get()));
  public static final RegistryObject<Item> ORE_EMERALD_ITEM = ITEMS.register("ore_emerald", () -> new BlockItemBase(BlockHandler.ORE_EMERALD.get()));
  public static final RegistryObject<Item> ORE_LAPIS_ITEM = ITEMS.register("ore_lapis", () -> new BlockItemBase(BlockHandler.ORE_LAPIS.get()));
  public static final RegistryObject<Item> ORE_REDSTONE_ITEM = ITEMS.register("ore_redstone", () -> new BlockItemBase(BlockHandler.ORE_REDSTONE.get()));
  public static final RegistryObject<Item> ORE_COPPER_ITEM = ITEMS.register("ore_copper", () -> new BlockItemBase(BlockHandler.ORE_COPPER.get()));
  public static final RegistryObject<Item> ORE_ALUMINUM_ITEM = ITEMS.register("ore_aluminum", () -> new BlockItemBase(BlockHandler.ORE_ALUMINUM.get()));
  public static final RegistryObject<Item> ORE_SILVER_ITEM = ITEMS.register("ore_silver", () -> new BlockItemBase(BlockHandler.ORE_SILVER.get()));
  public static final RegistryObject<Item> ORE_LEAD_ITEM = ITEMS.register("ore_lead", () -> new BlockItemBase(BlockHandler.ORE_LEAD.get()));
  public static final RegistryObject<Item> ORE_NICKEL_ITEM = ITEMS.register("ore_nickel", () -> new BlockItemBase(BlockHandler.ORE_NICKEL.get()));
  public static final RegistryObject<Item> ORE_URANIUM_ITEM = ITEMS.register("ore_uranium", () -> new BlockItemBase(BlockHandler.ORE_URANIUM.get()));
  public static final RegistryObject<Item> ORE_OSMIUM_ITEM = ITEMS.register("ore_osmium", () -> new BlockItemBase(BlockHandler.ORE_OSMIUM.get()));
  public static final RegistryObject<Item> ORE_TIN_ITEM = ITEMS.register("ore_tin", () -> new BlockItemBase(BlockHandler.ORE_TIN.get()));
  public static final RegistryObject<Item> ORE_ZINC_ITEM = ITEMS.register("ore_zinc", () -> new BlockItemBase(BlockHandler.ORE_ZINC.get()));
  public static final RegistryObject<Item> ORE_CERTUS_QUARTZ_ITEM = ITEMS.register("ore_certus_quartz", () -> new BlockItemBase(BlockHandler.ORE_CERTUS_QUARTZ.get()));
  public static final RegistryObject<Item> ORE_CHARGED_CERTUS_QUARTZ_ITEM = ITEMS.register("ore_charged_certus_quartz", () -> new BlockItemBase(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ.get()));
  public static final RegistryObject<Item> ORE_FLUORITE_ITEM = ITEMS.register("ore_fluorite", () -> new BlockItemBase(BlockHandler.ORE_FLUORITE.get()));

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

  // Strata Ore Items - Andesite
  public static final RegistryObject<Item> ORE_COAL_ITEM_ANDESITE = ITEMS.register("ore_coal_andesite", () -> new BlockItemBase(BlockHandler.ORE_COAL_ANDESITE.get()));
  public static final RegistryObject<Item> ORE_IRON_ITEM_ANDESITE = ITEMS.register("ore_iron_andesite", () -> new BlockItemBase(BlockHandler.ORE_IRON_ANDESITE.get()));
  public static final RegistryObject<Item> ORE_GOLD_ITEM_ANDESITE = ITEMS.register("ore_gold_andesite", () -> new BlockItemBase(BlockHandler.ORE_GOLD_ANDESITE.get()));
  public static final RegistryObject<Item> ORE_DIAMOND_ITEM_ANDESITE = ITEMS.register("ore_diamond_andesite", () -> new BlockItemBase(BlockHandler.ORE_DIAMOND_ANDESITE.get()));
  public static final RegistryObject<Item> ORE_EMERALD_ITEM_ANDESITE = ITEMS.register("ore_emerald_andesite", () -> new BlockItemBase(BlockHandler.ORE_EMERALD_ANDESITE.get()));
  public static final RegistryObject<Item> ORE_LAPIS_ITEM_ANDESITE = ITEMS.register("ore_lapis_andesite", () -> new BlockItemBase(BlockHandler.ORE_LAPIS_ANDESITE.get()));
  public static final RegistryObject<Item> ORE_REDSTONE_ITEM_ANDESITE = ITEMS.register("ore_redstone_andesite", () -> new BlockItemBase(BlockHandler.ORE_REDSTONE_ANDESITE.get()));
  public static final RegistryObject<Item> ORE_COPPER_ITEM_ANDESITE = ITEMS.register("ore_copper_andesite", () -> new BlockItemBase(BlockHandler.ORE_COPPER_ANDESITE.get()));
  public static final RegistryObject<Item> ORE_ALUMINUM_ITEM_ANDESITE = ITEMS.register("ore_aluminum_andesite", () -> new BlockItemBase(BlockHandler.ORE_ALUMINUM_ANDESITE.get()));
  public static final RegistryObject<Item> ORE_SILVER_ITEM_ANDESITE = ITEMS.register("ore_silver_andesite", () -> new BlockItemBase(BlockHandler.ORE_SILVER_ANDESITE.get()));
  public static final RegistryObject<Item> ORE_LEAD_ITEM_ANDESITE = ITEMS.register("ore_lead_andesite", () -> new BlockItemBase(BlockHandler.ORE_LEAD_ANDESITE.get()));
  public static final RegistryObject<Item> ORE_NICKEL_ITEM_ANDESITE = ITEMS.register("ore_nickel_andesite", () -> new BlockItemBase(BlockHandler.ORE_NICKEL_ANDESITE.get()));
  public static final RegistryObject<Item> ORE_URANIUM_ITEM_ANDESITE = ITEMS.register("ore_uranium_andesite", () -> new BlockItemBase(BlockHandler.ORE_URANIUM_ANDESITE.get()));
  public static final RegistryObject<Item> ORE_OSMIUM_ITEM_ANDESITE = ITEMS.register("ore_osmium_andesite", () -> new BlockItemBase(BlockHandler.ORE_OSMIUM_ANDESITE.get()));
  public static final RegistryObject<Item> ORE_TIN_ITEM_ANDESITE = ITEMS.register("ore_tin_andesite", () -> new BlockItemBase(BlockHandler.ORE_TIN_ANDESITE.get()));
  public static final RegistryObject<Item> ORE_ZINC_ITEM_ANDESITE = ITEMS.register("ore_zinc_andesite", () -> new BlockItemBase(BlockHandler.ORE_ZINC_ANDESITE.get()));
  public static final RegistryObject<Item> ORE_CERTUS_QUARTZ_ITEM_ANDESITE = ITEMS.register("ore_certus_quartz_andesite", () -> new BlockItemBase(BlockHandler.ORE_CERTUS_QUARTZ_ANDESITE.get()));
  public static final RegistryObject<Item> ORE_CHARGED_CERTUS_QUARTZ_ITEM_ANDESITE = ITEMS.register("ore_charged_certus_quartz_andesite", () -> new BlockItemBase(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_ANDESITE.get()));
  public static final RegistryObject<Item> ORE_FLUORITE_ITEM_ANDESITE = ITEMS.register("ore_fluorite_andesite", () -> new BlockItemBase(BlockHandler.ORE_FLUORITE_ANDESITE.get()));

  // Strata Ore Items - Gabbro
  public static final RegistryObject<Item> ORE_COAL_ITEM_GABBRO = ITEMS.register("ore_coal_gabbro", () -> new BlockItemBase(BlockHandler.ORE_COAL_GABBRO.get()));
  public static final RegistryObject<Item> ORE_IRON_ITEM_GABBRO = ITEMS.register("ore_iron_gabbro", () -> new BlockItemBase(BlockHandler.ORE_IRON_GABBRO.get()));
  public static final RegistryObject<Item> ORE_GOLD_ITEM_GABBRO = ITEMS.register("ore_gold_gabbro", () -> new BlockItemBase(BlockHandler.ORE_GOLD_GABBRO.get()));
  public static final RegistryObject<Item> ORE_DIAMOND_ITEM_GABBRO = ITEMS.register("ore_diamond_gabbro", () -> new BlockItemBase(BlockHandler.ORE_DIAMOND_GABBRO.get()));
  public static final RegistryObject<Item> ORE_EMERALD_ITEM_GABBRO = ITEMS.register("ore_emerald_gabbro", () -> new BlockItemBase(BlockHandler.ORE_EMERALD_GABBRO.get()));
  public static final RegistryObject<Item> ORE_LAPIS_ITEM_GABBRO = ITEMS.register("ore_lapis_gabbro", () -> new BlockItemBase(BlockHandler.ORE_LAPIS_GABBRO.get()));
  public static final RegistryObject<Item> ORE_REDSTONE_ITEM_GABBRO = ITEMS.register("ore_redstone_gabbro", () -> new BlockItemBase(BlockHandler.ORE_REDSTONE_GABBRO.get()));
  public static final RegistryObject<Item> ORE_COPPER_ITEM_GABBRO = ITEMS.register("ore_copper_gabbro", () -> new BlockItemBase(BlockHandler.ORE_COPPER_GABBRO.get()));
  public static final RegistryObject<Item> ORE_ALUMINUM_ITEM_GABBRO = ITEMS.register("ore_aluminum_gabbro", () -> new BlockItemBase(BlockHandler.ORE_ALUMINUM_GABBRO.get()));
  public static final RegistryObject<Item> ORE_SILVER_ITEM_GABBRO = ITEMS.register("ore_silver_gabbro", () -> new BlockItemBase(BlockHandler.ORE_SILVER_GABBRO.get()));
  public static final RegistryObject<Item> ORE_LEAD_ITEM_GABBRO = ITEMS.register("ore_lead_gabbro", () -> new BlockItemBase(BlockHandler.ORE_LEAD_GABBRO.get()));
  public static final RegistryObject<Item> ORE_NICKEL_ITEM_GABBRO = ITEMS.register("ore_nickel_gabbro", () -> new BlockItemBase(BlockHandler.ORE_NICKEL_GABBRO.get()));
  public static final RegistryObject<Item> ORE_URANIUM_ITEM_GABBRO = ITEMS.register("ore_uranium_gabbro", () -> new BlockItemBase(BlockHandler.ORE_URANIUM_GABBRO.get()));
  public static final RegistryObject<Item> ORE_OSMIUM_ITEM_GABBRO = ITEMS.register("ore_osmium_gabbro", () -> new BlockItemBase(BlockHandler.ORE_OSMIUM_GABBRO.get()));
  public static final RegistryObject<Item> ORE_TIN_ITEM_GABBRO = ITEMS.register("ore_tin_gabbro", () -> new BlockItemBase(BlockHandler.ORE_TIN_GABBRO.get()));
  public static final RegistryObject<Item> ORE_ZINC_ITEM_GABBRO = ITEMS.register("ore_zinc_gabbro", () -> new BlockItemBase(BlockHandler.ORE_ZINC_GABBRO.get()));
  public static final RegistryObject<Item> ORE_CERTUS_QUARTZ_ITEM_GABBRO = ITEMS.register("ore_certus_quartz_gabbro", () -> new BlockItemBase(BlockHandler.ORE_CERTUS_QUARTZ_GABBRO.get()));
  public static final RegistryObject<Item> ORE_CHARGED_CERTUS_QUARTZ_ITEM_GABBRO = ITEMS.register("ore_charged_certus_quartz_gabbro", () -> new BlockItemBase(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_GABBRO.get()));
  public static final RegistryObject<Item> ORE_FLUORITE_ITEM_GABBRO = ITEMS.register("ore_fluorite_gabbro", () -> new BlockItemBase(BlockHandler.ORE_FLUORITE_GABBRO.get()));

  // Strata Ore Items - Limestone (Create)
  public static final RegistryObject<Item> ORE_COAL_ITEM_C_LIMESTONE = ITEMS.register("ore_coal_c_limestone", () -> new BlockItemBase(BlockHandler.ORE_COAL_C_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_IRON_ITEM_C_LIMESTONE = ITEMS.register("ore_iron_c_limestone", () -> new BlockItemBase(BlockHandler.ORE_IRON_C_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_GOLD_ITEM_C_LIMESTONE = ITEMS.register("ore_gold_c_limestone", () -> new BlockItemBase(BlockHandler.ORE_GOLD_C_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_DIAMOND_ITEM_C_LIMESTONE = ITEMS.register("ore_diamond_c_limestone", () -> new BlockItemBase(BlockHandler.ORE_DIAMOND_C_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_EMERALD_ITEM_C_LIMESTONE = ITEMS.register("ore_emerald_c_limestone", () -> new BlockItemBase(BlockHandler.ORE_EMERALD_C_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_LAPIS_ITEM_C_LIMESTONE = ITEMS.register("ore_lapis_c_limestone", () -> new BlockItemBase(BlockHandler.ORE_LAPIS_C_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_REDSTONE_ITEM_C_LIMESTONE = ITEMS.register("ore_redstone_c_limestone", () -> new BlockItemBase(BlockHandler.ORE_REDSTONE_C_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_COPPER_ITEM_C_LIMESTONE = ITEMS.register("ore_copper_c_limestone", () -> new BlockItemBase(BlockHandler.ORE_COPPER_C_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_ALUMINUM_ITEM_C_LIMESTONE = ITEMS.register("ore_aluminum_c_limestone", () -> new BlockItemBase(BlockHandler.ORE_ALUMINUM_C_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_SILVER_ITEM_C_LIMESTONE = ITEMS.register("ore_silver_c_limestone", () -> new BlockItemBase(BlockHandler.ORE_SILVER_C_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_LEAD_ITEM_C_LIMESTONE = ITEMS.register("ore_lead_c_limestone", () -> new BlockItemBase(BlockHandler.ORE_LEAD_C_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_NICKEL_ITEM_C_LIMESTONE = ITEMS.register("ore_nickel_c_limestone", () -> new BlockItemBase(BlockHandler.ORE_NICKEL_C_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_URANIUM_ITEM_C_LIMESTONE = ITEMS.register("ore_uranium_c_limestone", () -> new BlockItemBase(BlockHandler.ORE_URANIUM_C_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_OSMIUM_ITEM_C_LIMESTONE = ITEMS.register("ore_osmium_c_limestone", () -> new BlockItemBase(BlockHandler.ORE_OSMIUM_C_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_TIN_ITEM_C_LIMESTONE = ITEMS.register("ore_tin_c_limestone", () -> new BlockItemBase(BlockHandler.ORE_TIN_C_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_ZINC_ITEM_C_LIMESTONE = ITEMS.register("ore_zinc_c_limestone", () -> new BlockItemBase(BlockHandler.ORE_ZINC_C_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_CERTUS_QUARTZ_ITEM_C_LIMESTONE = ITEMS.register("ore_certus_quartz_c_limestone", () -> new BlockItemBase(BlockHandler.ORE_CERTUS_QUARTZ_C_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_CHARGED_CERTUS_QUARTZ_ITEM_C_LIMESTONE = ITEMS.register("ore_charged_certus_quartz_c_limestone", () -> new BlockItemBase(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_C_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_FLUORITE_ITEM_C_LIMESTONE = ITEMS.register("ore_fluorite_c_limestone", () -> new BlockItemBase(BlockHandler.ORE_FLUORITE_C_LIMESTONE.get()));

  // Strata Ore Items - Scoria
  public static final RegistryObject<Item> ORE_COAL_ITEM_SCORIA = ITEMS.register("ore_coal_scoria", () -> new BlockItemBase(BlockHandler.ORE_COAL_SCORIA.get()));
  public static final RegistryObject<Item> ORE_IRON_ITEM_SCORIA = ITEMS.register("ore_iron_scoria", () -> new BlockItemBase(BlockHandler.ORE_IRON_SCORIA.get()));
  public static final RegistryObject<Item> ORE_GOLD_ITEM_SCORIA = ITEMS.register("ore_gold_scoria", () -> new BlockItemBase(BlockHandler.ORE_GOLD_SCORIA.get()));
  public static final RegistryObject<Item> ORE_DIAMOND_ITEM_SCORIA = ITEMS.register("ore_diamond_scoria", () -> new BlockItemBase(BlockHandler.ORE_DIAMOND_SCORIA.get()));
  public static final RegistryObject<Item> ORE_EMERALD_ITEM_SCORIA = ITEMS.register("ore_emerald_scoria", () -> new BlockItemBase(BlockHandler.ORE_EMERALD_SCORIA.get()));
  public static final RegistryObject<Item> ORE_LAPIS_ITEM_SCORIA = ITEMS.register("ore_lapis_scoria", () -> new BlockItemBase(BlockHandler.ORE_LAPIS_SCORIA.get()));
  public static final RegistryObject<Item> ORE_REDSTONE_ITEM_SCORIA = ITEMS.register("ore_redstone_scoria", () -> new BlockItemBase(BlockHandler.ORE_REDSTONE_SCORIA.get()));
  public static final RegistryObject<Item> ORE_COPPER_ITEM_SCORIA = ITEMS.register("ore_copper_scoria", () -> new BlockItemBase(BlockHandler.ORE_COPPER_SCORIA.get()));
  public static final RegistryObject<Item> ORE_ALUMINUM_ITEM_SCORIA = ITEMS.register("ore_aluminum_scoria", () -> new BlockItemBase(BlockHandler.ORE_ALUMINUM_SCORIA.get()));
  public static final RegistryObject<Item> ORE_SILVER_ITEM_SCORIA = ITEMS.register("ore_silver_scoria", () -> new BlockItemBase(BlockHandler.ORE_SILVER_SCORIA.get()));
  public static final RegistryObject<Item> ORE_LEAD_ITEM_SCORIA = ITEMS.register("ore_lead_scoria", () -> new BlockItemBase(BlockHandler.ORE_LEAD_SCORIA.get()));
  public static final RegistryObject<Item> ORE_NICKEL_ITEM_SCORIA = ITEMS.register("ore_nickel_scoria", () -> new BlockItemBase(BlockHandler.ORE_NICKEL_SCORIA.get()));
  public static final RegistryObject<Item> ORE_URANIUM_ITEM_SCORIA = ITEMS.register("ore_uranium_scoria", () -> new BlockItemBase(BlockHandler.ORE_URANIUM_SCORIA.get()));
  public static final RegistryObject<Item> ORE_OSMIUM_ITEM_SCORIA = ITEMS.register("ore_osmium_scoria", () -> new BlockItemBase(BlockHandler.ORE_OSMIUM_SCORIA.get()));
  public static final RegistryObject<Item> ORE_TIN_ITEM_SCORIA = ITEMS.register("ore_tin_scoria", () -> new BlockItemBase(BlockHandler.ORE_TIN_SCORIA.get()));
  public static final RegistryObject<Item> ORE_ZINC_ITEM_SCORIA = ITEMS.register("ore_zinc_scoria", () -> new BlockItemBase(BlockHandler.ORE_ZINC_SCORIA.get()));
  public static final RegistryObject<Item> ORE_CERTUS_QUARTZ_ITEM_SCORIA = ITEMS.register("ore_certus_quartz_scoria", () -> new BlockItemBase(BlockHandler.ORE_CERTUS_QUARTZ_SCORIA.get()));
  public static final RegistryObject<Item> ORE_CHARGED_CERTUS_QUARTZ_ITEM_SCORIA = ITEMS.register("ore_charged_certus_quartz_scoria", () -> new BlockItemBase(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_SCORIA.get()));
  public static final RegistryObject<Item> ORE_FLUORITE_ITEM_SCORIA = ITEMS.register("ore_fluorite_scoria", () -> new BlockItemBase(BlockHandler.ORE_FLUORITE_SCORIA.get()));

  // Strata Ore Items - Weathered Limestone
  public static final RegistryObject<Item> ORE_COAL_ITEM_WEATHERED_LIMESTONE = ITEMS.register("ore_coal_weathered_limestone", () -> new BlockItemBase(BlockHandler.ORE_COAL_WEATHERED_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_IRON_ITEM_WEATHERED_LIMESTONE = ITEMS.register("ore_iron_weathered_limestone", () -> new BlockItemBase(BlockHandler.ORE_IRON_WEATHERED_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_GOLD_ITEM_WEATHERED_LIMESTONE = ITEMS.register("ore_gold_weathered_limestone", () -> new BlockItemBase(BlockHandler.ORE_GOLD_WEATHERED_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_DIAMOND_ITEM_WEATHERED_LIMESTONE = ITEMS.register("ore_diamond_weathered_limestone", () -> new BlockItemBase(BlockHandler.ORE_DIAMOND_WEATHERED_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_EMERALD_ITEM_WEATHERED_LIMESTONE = ITEMS.register("ore_emerald_weathered_limestone", () -> new BlockItemBase(BlockHandler.ORE_EMERALD_WEATHERED_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_LAPIS_ITEM_WEATHERED_LIMESTONE = ITEMS.register("ore_lapis_weathered_limestone", () -> new BlockItemBase(BlockHandler.ORE_LAPIS_WEATHERED_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_REDSTONE_ITEM_WEATHERED_LIMESTONE = ITEMS.register("ore_redstone_weathered_limestone", () -> new BlockItemBase(BlockHandler.ORE_REDSTONE_WEATHERED_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_COPPER_ITEM_WEATHERED_LIMESTONE = ITEMS.register("ore_copper_weathered_limestone", () -> new BlockItemBase(BlockHandler.ORE_COPPER_WEATHERED_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_ALUMINUM_ITEM_WEATHERED_LIMESTONE = ITEMS.register("ore_aluminum_weathered_limestone", () -> new BlockItemBase(BlockHandler.ORE_ALUMINUM_WEATHERED_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_SILVER_ITEM_WEATHERED_LIMESTONE = ITEMS.register("ore_silver_weathered_limestone", () -> new BlockItemBase(BlockHandler.ORE_SILVER_WEATHERED_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_LEAD_ITEM_WEATHERED_LIMESTONE = ITEMS.register("ore_lead_weathered_limestone", () -> new BlockItemBase(BlockHandler.ORE_LEAD_WEATHERED_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_NICKEL_ITEM_WEATHERED_LIMESTONE = ITEMS.register("ore_nickel_weathered_limestone", () -> new BlockItemBase(BlockHandler.ORE_NICKEL_WEATHERED_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_URANIUM_ITEM_WEATHERED_LIMESTONE = ITEMS.register("ore_uranium_weathered_limestone", () -> new BlockItemBase(BlockHandler.ORE_URANIUM_WEATHERED_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_OSMIUM_ITEM_WEATHERED_LIMESTONE = ITEMS.register("ore_osmium_weathered_limestone", () -> new BlockItemBase(BlockHandler.ORE_OSMIUM_WEATHERED_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_TIN_ITEM_WEATHERED_LIMESTONE = ITEMS.register("ore_tin_weathered_limestone", () -> new BlockItemBase(BlockHandler.ORE_TIN_WEATHERED_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_ZINC_ITEM_WEATHERED_LIMESTONE = ITEMS.register("ore_zinc_weathered_limestone", () -> new BlockItemBase(BlockHandler.ORE_ZINC_WEATHERED_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_CERTUS_QUARTZ_ITEM_WEATHERED_LIMESTONE = ITEMS.register("ore_certus_quartz_weathered_limestone", () -> new BlockItemBase(BlockHandler.ORE_CERTUS_QUARTZ_WEATHERED_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_CHARGED_CERTUS_QUARTZ_ITEM_WEATHERED_LIMESTONE = ITEMS.register("ore_charged_certus_quartz_weathered_limestone", () -> new BlockItemBase(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_WEATHERED_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_FLUORITE_ITEM_WEATHERED_LIMESTONE = ITEMS.register("ore_fluorite_weathered_limestone", () -> new BlockItemBase(BlockHandler.ORE_FLUORITE_WEATHERED_LIMESTONE.get()));

  // Strata Ore Items - Jasper
  public static final RegistryObject<Item> ORE_COAL_ITEM_JASPER = ITEMS.register("ore_coal_jasper", () -> new BlockItemBase(BlockHandler.ORE_COAL_JASPER.get()));
  public static final RegistryObject<Item> ORE_IRON_ITEM_JASPER = ITEMS.register("ore_iron_jasper", () -> new BlockItemBase(BlockHandler.ORE_IRON_JASPER.get()));
  public static final RegistryObject<Item> ORE_GOLD_ITEM_JASPER = ITEMS.register("ore_gold_jasper", () -> new BlockItemBase(BlockHandler.ORE_GOLD_JASPER.get()));
  public static final RegistryObject<Item> ORE_DIAMOND_ITEM_JASPER = ITEMS.register("ore_diamond_jasper", () -> new BlockItemBase(BlockHandler.ORE_DIAMOND_JASPER.get()));
  public static final RegistryObject<Item> ORE_EMERALD_ITEM_JASPER = ITEMS.register("ore_emerald_jasper", () -> new BlockItemBase(BlockHandler.ORE_EMERALD_JASPER.get()));
  public static final RegistryObject<Item> ORE_LAPIS_ITEM_JASPER = ITEMS.register("ore_lapis_jasper", () -> new BlockItemBase(BlockHandler.ORE_LAPIS_JASPER.get()));
  public static final RegistryObject<Item> ORE_REDSTONE_ITEM_JASPER = ITEMS.register("ore_redstone_jasper", () -> new BlockItemBase(BlockHandler.ORE_REDSTONE_JASPER.get()));
  public static final RegistryObject<Item> ORE_COPPER_ITEM_JASPER = ITEMS.register("ore_copper_jasper", () -> new BlockItemBase(BlockHandler.ORE_COPPER_JASPER.get()));
  public static final RegistryObject<Item> ORE_ALUMINUM_ITEM_JASPER = ITEMS.register("ore_aluminum_jasper", () -> new BlockItemBase(BlockHandler.ORE_ALUMINUM_JASPER.get()));
  public static final RegistryObject<Item> ORE_SILVER_ITEM_JASPER = ITEMS.register("ore_silver_jasper", () -> new BlockItemBase(BlockHandler.ORE_SILVER_JASPER.get()));
  public static final RegistryObject<Item> ORE_LEAD_ITEM_JASPER = ITEMS.register("ore_lead_jasper", () -> new BlockItemBase(BlockHandler.ORE_LEAD_JASPER.get()));
  public static final RegistryObject<Item> ORE_NICKEL_ITEM_JASPER = ITEMS.register("ore_nickel_jasper", () -> new BlockItemBase(BlockHandler.ORE_NICKEL_JASPER.get()));
  public static final RegistryObject<Item> ORE_URANIUM_ITEM_JASPER = ITEMS.register("ore_uranium_jasper", () -> new BlockItemBase(BlockHandler.ORE_URANIUM_JASPER.get()));
  public static final RegistryObject<Item> ORE_OSMIUM_ITEM_JASPER = ITEMS.register("ore_osmium_jasper", () -> new BlockItemBase(BlockHandler.ORE_OSMIUM_JASPER.get()));
  public static final RegistryObject<Item> ORE_TIN_ITEM_JASPER = ITEMS.register("ore_tin_jasper", () -> new BlockItemBase(BlockHandler.ORE_TIN_JASPER.get()));
  public static final RegistryObject<Item> ORE_ZINC_ITEM_JASPER = ITEMS.register("ore_zinc_jasper", () -> new BlockItemBase(BlockHandler.ORE_ZINC_JASPER.get()));
  public static final RegistryObject<Item> ORE_CERTUS_QUARTZ_ITEM_JASPER = ITEMS.register("ore_certus_quartz_jasper", () -> new BlockItemBase(BlockHandler.ORE_CERTUS_QUARTZ_JASPER.get()));
  public static final RegistryObject<Item> ORE_CHARGED_CERTUS_QUARTZ_ITEM_JASPER = ITEMS.register("ore_charged_certus_quartz_jasper", () -> new BlockItemBase(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_JASPER.get()));
  public static final RegistryObject<Item> ORE_FLUORITE_ITEM_JASPER = ITEMS.register("ore_fluorite_jasper", () -> new BlockItemBase(BlockHandler.ORE_FLUORITE_JASPER.get()));

  // Strata Ore Items - Limestone (Quark)
  public static final RegistryObject<Item> ORE_COAL_ITEM_Q_LIMESTONE = ITEMS.register("ore_coal_q_limestone", () -> new BlockItemBase(BlockHandler.ORE_COAL_Q_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_IRON_ITEM_Q_LIMESTONE = ITEMS.register("ore_iron_q_limestone", () -> new BlockItemBase(BlockHandler.ORE_IRON_Q_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_GOLD_ITEM_Q_LIMESTONE = ITEMS.register("ore_gold_q_limestone", () -> new BlockItemBase(BlockHandler.ORE_GOLD_Q_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_DIAMOND_ITEM_Q_LIMESTONE = ITEMS.register("ore_diamond_q_limestone", () -> new BlockItemBase(BlockHandler.ORE_DIAMOND_Q_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_EMERALD_ITEM_Q_LIMESTONE = ITEMS.register("ore_emerald_q_limestone", () -> new BlockItemBase(BlockHandler.ORE_EMERALD_Q_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_LAPIS_ITEM_Q_LIMESTONE = ITEMS.register("ore_lapis_q_limestone", () -> new BlockItemBase(BlockHandler.ORE_LAPIS_Q_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_REDSTONE_ITEM_Q_LIMESTONE = ITEMS.register("ore_redstone_q_limestone", () -> new BlockItemBase(BlockHandler.ORE_REDSTONE_Q_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_COPPER_ITEM_Q_LIMESTONE = ITEMS.register("ore_copper_q_limestone", () -> new BlockItemBase(BlockHandler.ORE_COPPER_Q_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_ALUMINUM_ITEM_Q_LIMESTONE = ITEMS.register("ore_aluminum_q_limestone", () -> new BlockItemBase(BlockHandler.ORE_ALUMINUM_Q_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_SILVER_ITEM_Q_LIMESTONE = ITEMS.register("ore_silver_q_limestone", () -> new BlockItemBase(BlockHandler.ORE_SILVER_Q_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_LEAD_ITEM_Q_LIMESTONE = ITEMS.register("ore_lead_q_limestone", () -> new BlockItemBase(BlockHandler.ORE_LEAD_Q_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_NICKEL_ITEM_Q_LIMESTONE = ITEMS.register("ore_nickel_q_limestone", () -> new BlockItemBase(BlockHandler.ORE_NICKEL_Q_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_URANIUM_ITEM_Q_LIMESTONE = ITEMS.register("ore_uranium_q_limestone", () -> new BlockItemBase(BlockHandler.ORE_URANIUM_Q_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_OSMIUM_ITEM_Q_LIMESTONE = ITEMS.register("ore_osmium_q_limestone", () -> new BlockItemBase(BlockHandler.ORE_OSMIUM_Q_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_TIN_ITEM_Q_LIMESTONE = ITEMS.register("ore_tin_q_limestone", () -> new BlockItemBase(BlockHandler.ORE_TIN_Q_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_ZINC_ITEM_Q_LIMESTONE = ITEMS.register("ore_zinc_q_limestone", () -> new BlockItemBase(BlockHandler.ORE_ZINC_Q_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_CERTUS_QUARTZ_ITEM_Q_LIMESTONE = ITEMS.register("ore_certus_quartz_q_limestone", () -> new BlockItemBase(BlockHandler.ORE_CERTUS_QUARTZ_Q_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_CHARGED_CERTUS_QUARTZ_ITEM_Q_LIMESTONE = ITEMS.register("ore_charged_certus_quartz_q_limestone", () -> new BlockItemBase(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_Q_LIMESTONE.get()));
  public static final RegistryObject<Item> ORE_FLUORITE_ITEM_Q_LIMESTONE = ITEMS.register("ore_fluorite_q_limestone", () -> new BlockItemBase(BlockHandler.ORE_FLUORITE_Q_LIMESTONE.get()));

  // Strata Ore Items - Marble
  public static final RegistryObject<Item> ORE_COAL_ITEM_MARBLE = ITEMS.register("ore_coal_marble", () -> new BlockItemBase(BlockHandler.ORE_COAL_MARBLE.get()));
  public static final RegistryObject<Item> ORE_IRON_ITEM_MARBLE = ITEMS.register("ore_iron_marble", () -> new BlockItemBase(BlockHandler.ORE_IRON_MARBLE.get()));
  public static final RegistryObject<Item> ORE_GOLD_ITEM_MARBLE = ITEMS.register("ore_gold_marble", () -> new BlockItemBase(BlockHandler.ORE_GOLD_MARBLE.get()));
  public static final RegistryObject<Item> ORE_DIAMOND_ITEM_MARBLE = ITEMS.register("ore_diamond_marble", () -> new BlockItemBase(BlockHandler.ORE_DIAMOND_MARBLE.get()));
  public static final RegistryObject<Item> ORE_EMERALD_ITEM_MARBLE = ITEMS.register("ore_emerald_marble", () -> new BlockItemBase(BlockHandler.ORE_EMERALD_MARBLE.get()));
  public static final RegistryObject<Item> ORE_LAPIS_ITEM_MARBLE = ITEMS.register("ore_lapis_marble", () -> new BlockItemBase(BlockHandler.ORE_LAPIS_MARBLE.get()));
  public static final RegistryObject<Item> ORE_REDSTONE_ITEM_MARBLE = ITEMS.register("ore_redstone_marble", () -> new BlockItemBase(BlockHandler.ORE_REDSTONE_MARBLE.get()));
  public static final RegistryObject<Item> ORE_COPPER_ITEM_MARBLE = ITEMS.register("ore_copper_marble", () -> new BlockItemBase(BlockHandler.ORE_COPPER_MARBLE.get()));
  public static final RegistryObject<Item> ORE_ALUMINUM_ITEM_MARBLE = ITEMS.register("ore_aluminum_marble", () -> new BlockItemBase(BlockHandler.ORE_ALUMINUM_MARBLE.get()));
  public static final RegistryObject<Item> ORE_SILVER_ITEM_MARBLE = ITEMS.register("ore_silver_marble", () -> new BlockItemBase(BlockHandler.ORE_SILVER_MARBLE.get()));
  public static final RegistryObject<Item> ORE_LEAD_ITEM_MARBLE = ITEMS.register("ore_lead_marble", () -> new BlockItemBase(BlockHandler.ORE_LEAD_MARBLE.get()));
  public static final RegistryObject<Item> ORE_NICKEL_ITEM_MARBLE = ITEMS.register("ore_nickel_marble", () -> new BlockItemBase(BlockHandler.ORE_NICKEL_MARBLE.get()));
  public static final RegistryObject<Item> ORE_URANIUM_ITEM_MARBLE = ITEMS.register("ore_uranium_marble", () -> new BlockItemBase(BlockHandler.ORE_URANIUM_MARBLE.get()));
  public static final RegistryObject<Item> ORE_OSMIUM_ITEM_MARBLE = ITEMS.register("ore_osmium_marble", () -> new BlockItemBase(BlockHandler.ORE_OSMIUM_MARBLE.get()));
  public static final RegistryObject<Item> ORE_TIN_ITEM_MARBLE = ITEMS.register("ore_tin_marble", () -> new BlockItemBase(BlockHandler.ORE_TIN_MARBLE.get()));
  public static final RegistryObject<Item> ORE_ZINC_ITEM_MARBLE = ITEMS.register("ore_zinc_marble", () -> new BlockItemBase(BlockHandler.ORE_ZINC_MARBLE.get()));
  public static final RegistryObject<Item> ORE_CERTUS_QUARTZ_ITEM_MARBLE = ITEMS.register("ore_certus_quartz_marble", () -> new BlockItemBase(BlockHandler.ORE_CERTUS_QUARTZ_MARBLE.get()));
  public static final RegistryObject<Item> ORE_CHARGED_CERTUS_QUARTZ_ITEM_MARBLE = ITEMS.register("ore_charged_certus_quartz_marble", () -> new BlockItemBase(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_MARBLE.get()));
  public static final RegistryObject<Item> ORE_FLUORITE_ITEM_MARBLE = ITEMS.register("ore_fluorite_marble", () -> new BlockItemBase(BlockHandler.ORE_FLUORITE_MARBLE.get()));

  // Strata Ore Items - Slate
  public static final RegistryObject<Item> ORE_COAL_ITEM_SLATE = ITEMS.register("ore_coal_slate", () -> new BlockItemBase(BlockHandler.ORE_COAL_SLATE.get()));
  public static final RegistryObject<Item> ORE_IRON_ITEM_SLATE = ITEMS.register("ore_iron_slate", () -> new BlockItemBase(BlockHandler.ORE_IRON_SLATE.get()));
  public static final RegistryObject<Item> ORE_GOLD_ITEM_SLATE = ITEMS.register("ore_gold_slate", () -> new BlockItemBase(BlockHandler.ORE_GOLD_SLATE.get()));
  public static final RegistryObject<Item> ORE_DIAMOND_ITEM_SLATE = ITEMS.register("ore_diamond_slate", () -> new BlockItemBase(BlockHandler.ORE_DIAMOND_SLATE.get()));
  public static final RegistryObject<Item> ORE_EMERALD_ITEM_SLATE = ITEMS.register("ore_emerald_slate", () -> new BlockItemBase(BlockHandler.ORE_EMERALD_SLATE.get()));
  public static final RegistryObject<Item> ORE_LAPIS_ITEM_SLATE = ITEMS.register("ore_lapis_slate", () -> new BlockItemBase(BlockHandler.ORE_LAPIS_SLATE.get()));
  public static final RegistryObject<Item> ORE_REDSTONE_ITEM_SLATE = ITEMS.register("ore_redstone_slate", () -> new BlockItemBase(BlockHandler.ORE_REDSTONE_SLATE.get()));
  public static final RegistryObject<Item> ORE_COPPER_ITEM_SLATE = ITEMS.register("ore_copper_slate", () -> new BlockItemBase(BlockHandler.ORE_COPPER_SLATE.get()));
  public static final RegistryObject<Item> ORE_ALUMINUM_ITEM_SLATE = ITEMS.register("ore_aluminum_slate", () -> new BlockItemBase(BlockHandler.ORE_ALUMINUM_SLATE.get()));
  public static final RegistryObject<Item> ORE_SILVER_ITEM_SLATE = ITEMS.register("ore_silver_slate", () -> new BlockItemBase(BlockHandler.ORE_SILVER_SLATE.get()));
  public static final RegistryObject<Item> ORE_LEAD_ITEM_SLATE = ITEMS.register("ore_lead_slate", () -> new BlockItemBase(BlockHandler.ORE_LEAD_SLATE.get()));
  public static final RegistryObject<Item> ORE_NICKEL_ITEM_SLATE = ITEMS.register("ore_nickel_slate", () -> new BlockItemBase(BlockHandler.ORE_NICKEL_SLATE.get()));
  public static final RegistryObject<Item> ORE_URANIUM_ITEM_SLATE = ITEMS.register("ore_uranium_slate", () -> new BlockItemBase(BlockHandler.ORE_URANIUM_SLATE.get()));
  public static final RegistryObject<Item> ORE_OSMIUM_ITEM_SLATE = ITEMS.register("ore_osmium_slate", () -> new BlockItemBase(BlockHandler.ORE_OSMIUM_SLATE.get()));
  public static final RegistryObject<Item> ORE_TIN_ITEM_SLATE = ITEMS.register("ore_tin_slate", () -> new BlockItemBase(BlockHandler.ORE_TIN_SLATE.get()));
  public static final RegistryObject<Item> ORE_ZINC_ITEM_SLATE = ITEMS.register("ore_zinc_slate", () -> new BlockItemBase(BlockHandler.ORE_ZINC_SLATE.get()));
  public static final RegistryObject<Item> ORE_CERTUS_QUARTZ_ITEM_SLATE = ITEMS.register("ore_certus_quartz_slate", () -> new BlockItemBase(BlockHandler.ORE_CERTUS_QUARTZ_SLATE.get()));
  public static final RegistryObject<Item> ORE_CHARGED_CERTUS_QUARTZ_ITEM_SLATE = ITEMS.register("ore_charged_certus_quartz_slate", () -> new BlockItemBase(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_SLATE.get()));
  public static final RegistryObject<Item> ORE_FLUORITE_ITEM_SLATE = ITEMS.register("ore_fluorite_slate", () -> new BlockItemBase(BlockHandler.ORE_FLUORITE_SLATE.get()));

  //Machine Items
  public static final RegistryObject<Item> ENIGMATIC_EXCHANGER_ITEM = ITEMS.register("enigmatic_exchanger", () -> new BlockItemBase(BlockHandler.ENIGMATIC_EXCHANGER.get()));
}
