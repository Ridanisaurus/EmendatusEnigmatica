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
import com.ridanisaurus.emendatusenigmatica.util.Ores;
import com.ridanisaurus.emendatusenigmatica.util.Strata;
import com.ridanisaurus.emendatusenigmatica.blocks.BlockItemBase;
import com.ridanisaurus.emendatusenigmatica.items.ItemBase;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Optional;
import java.util.function.Supplier;

public class ItemHandler {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

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

    //Ore Items
    private static Table<Strata, Ores, RegistryObject<Item>> backingOreItemTable;
    public static final Supplier<Table<Strata, Ores, RegistryObject<Item>>> oreItemTable = () -> Optional.ofNullable(backingOreItemTable).orElse(ImmutableTable.of());

    public static void oreItems() {
        ImmutableTable.Builder<Strata, Ores, RegistryObject<Item>> builder = new ImmutableTable.Builder<>();
        for (Strata stratum : Strata.values()) {
            for (Ores ore : Ores.values()) {
                String oreName = "ore_" + ore.id + (stratum != Strata.STONE ? "_" + stratum.suffix : "");
                builder.put(stratum, ore, ITEMS.register(oreName, () -> new BlockItemBase(BlockHandler.oreBlockTable.get().get(stratum, ore).get())));
            }
        }
        backingOreItemTable = builder.build();
    }

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
    public static final RegistryObject<Item> BLOCK_BRONZE_ITEM = ITEMS.register("block_bronze", () -> new BlockItemBase(BlockHandler.BLOCK_BRONZE.get()));
    public static final RegistryObject<Item> BLOCK_BRASS_ITEM = ITEMS.register("block_brass", () -> new BlockItemBase(BlockHandler.BLOCK_BRASS.get()));
    public static final RegistryObject<Item> BLOCK_CONSTANTAN_ITEM = ITEMS.register("block_constantan", () -> new BlockItemBase(BlockHandler.BLOCK_CONSTANTAN.get()));
    public static final RegistryObject<Item> BLOCK_ELECTRUM_ITEM = ITEMS.register("block_electrum", () -> new BlockItemBase(BlockHandler.BLOCK_ELECTRUM.get()));
    public static final RegistryObject<Item> BLOCK_STEEL_ITEM = ITEMS.register("block_steel", () -> new BlockItemBase(BlockHandler.BLOCK_STEEL.get()));

    //Machine Items
    public static final RegistryObject<Item> ENIGMATIC_EXCHANGER_ITEM = ITEMS.register("enigmatic_exchanger", () -> new BlockItemBase(BlockHandler.ENIGMATIC_EXCHANGER.get()));
}
