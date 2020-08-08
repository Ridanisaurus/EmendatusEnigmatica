/*
 * MIT License
 *
 * Copyright (c) 2020 Ridanisaurus
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software").toString(), to deal
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

import com.ridanisaurus.emendatusenigmatica.registries.ItemHandler;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class ItemTagsGen extends ItemTagsProvider {

  public ItemTagsGen(DataGenerator gen, BlockTagsProvider blockTagProvider) { super(gen, blockTagProvider); }

  @Override
  protected void registerTags() {
    // Ingots
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ingots").toString()))
            .add(ItemHandler.INGOT_COPPER.get())
            .add(ItemHandler.INGOT_ALUMINUM.get())
            .add(ItemHandler.INGOT_SILVER.get())
            .add(ItemHandler.INGOT_LEAD.get())
            .add(ItemHandler.INGOT_NICKEL.get())
            .add(ItemHandler.INGOT_URANIUM.get())
            .add(ItemHandler.INGOT_OSMIUM.get())
            .add(ItemHandler.INGOT_TIN.get())
            .add(ItemHandler.INGOT_ZINC.get())
            .add(ItemHandler.INGOT_BRONZE.get())
            .add(ItemHandler.INGOT_BRASS.get())
            .add(ItemHandler.INGOT_CONSTANTAN.get())
            .add(ItemHandler.INGOT_ELECTRUM.get())
            .add(ItemHandler.INGOT_STEEL.get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ingots/copper").toString()))
            .add(ItemHandler.INGOT_COPPER.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ingots/aluminum").toString()))
            .add(ItemHandler.INGOT_ALUMINUM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ingots/silver").toString()))
            .add(ItemHandler.INGOT_SILVER.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ingots/lead").toString()))
            .add(ItemHandler.INGOT_LEAD.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ingots/nickel").toString()))
            .add(ItemHandler.INGOT_NICKEL.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ingots/uranium").toString()))
            .add(ItemHandler.INGOT_URANIUM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ingots/osmium").toString()))
            .add(ItemHandler.INGOT_OSMIUM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ingots/tin").toString()))
            .add(ItemHandler.INGOT_TIN.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ingots/zinc").toString()))
            .add(ItemHandler.INGOT_ZINC.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ingots/bronze").toString()))
            .add(ItemHandler.INGOT_BRONZE.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ingots/brass").toString()))
            .add(ItemHandler.INGOT_BRASS.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ingots/constantan").toString()))
            .add(ItemHandler.INGOT_CONSTANTAN.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ingots/electrum").toString()))
            .add(ItemHandler.INGOT_ELECTRUM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ingots/steel").toString()))
            .add(ItemHandler.INGOT_STEEL.get());

    // Nuggets
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "nuggets").toString()))
            .add(ItemHandler.NUGGET_COPPER.get())
            .add(ItemHandler.NUGGET_ALUMINUM.get())
            .add(ItemHandler.NUGGET_SILVER.get())
            .add(ItemHandler.NUGGET_LEAD.get())
            .add(ItemHandler.NUGGET_NICKEL.get())
            .add(ItemHandler.NUGGET_URANIUM.get())
            .add(ItemHandler.NUGGET_OSMIUM.get())
            .add(ItemHandler.NUGGET_TIN.get())
            .add(ItemHandler.NUGGET_ZINC.get())
            .add(ItemHandler.NUGGET_BRONZE.get())
            .add(ItemHandler.NUGGET_BRASS.get())
            .add(ItemHandler.NUGGET_CONSTANTAN.get())
            .add(ItemHandler.NUGGET_ELECTRUM.get())
            .add(ItemHandler.NUGGET_STEEL.get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "nuggets/copper").toString()))
            .add(ItemHandler.NUGGET_COPPER.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "nuggets/aluminum").toString()))
            .add(ItemHandler.NUGGET_ALUMINUM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "nuggets/silver").toString()))
            .add(ItemHandler.NUGGET_SILVER.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "nuggets/lead").toString()))
            .add(ItemHandler.NUGGET_LEAD.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "nuggets/nickel").toString()))
            .add(ItemHandler.NUGGET_NICKEL.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "nuggets/uranium").toString()))
            .add(ItemHandler.NUGGET_URANIUM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "nuggets/osmium").toString()))
            .add(ItemHandler.NUGGET_OSMIUM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "nuggets/tin").toString()))
            .add(ItemHandler.NUGGET_TIN.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "nuggets/zinc").toString()))
            .add(ItemHandler.NUGGET_ZINC.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "nuggets/bronze").toString()))
            .add(ItemHandler.NUGGET_BRONZE.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "nuggets/brass").toString()))
            .add(ItemHandler.NUGGET_BRASS.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "nuggets/constantan").toString()))
            .add(ItemHandler.NUGGET_CONSTANTAN.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "nuggets/electrum").toString()))
            .add(ItemHandler.NUGGET_ELECTRUM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "nuggets/steel").toString()))
            .add(ItemHandler.NUGGET_STEEL.get());

    // Dusts
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts").toString()))
            .add(ItemHandler.DUST_COPPER.get())
            .add(ItemHandler.DUST_ALUMINUM.get())
            .add(ItemHandler.DUST_SILVER.get())
            .add(ItemHandler.DUST_LEAD.get())
            .add(ItemHandler.DUST_NICKEL.get())
            .add(ItemHandler.DUST_URANIUM.get())
            .add(ItemHandler.DUST_OSMIUM.get())
            .add(ItemHandler.DUST_TIN.get())
            .add(ItemHandler.DUST_ZINC.get())
            .add(ItemHandler.DUST_BRONZE.get())
            .add(ItemHandler.DUST_BRASS.get())
            .add(ItemHandler.DUST_CONSTANTAN.get())
            .add(ItemHandler.DUST_ELECTRUM.get())
            .add(ItemHandler.DUST_STEEL.get())
            .add(ItemHandler.DUST_CHARCOAL.get())
            .add(ItemHandler.DUST_COAL.get())
            .add(ItemHandler.DUST_DIAMOND.get())
            .add(ItemHandler.DUST_EMERALD.get())
            .add(ItemHandler.DUST_GOLD.get())
            .add(ItemHandler.DUST_IRON.get())
            .add(ItemHandler.DUST_LAPIS.get())
            .add(ItemHandler.DUST_OBSIDIAN.get())
            .add(ItemHandler.DUST_QUARTZ.get())
            .add(ItemHandler.DUST_CERTUS_QUARTZ.get())
            .add(ItemHandler.DUST_SKYSTONE.get())
            .add(ItemHandler.DUST_FLUIX.get())
            .add(ItemHandler.DUST_ENDER.get())
            .add(ItemHandler.DUST_COKE.get())
            .add(ItemHandler.DUST_GRAPHITE.get())
            .add(ItemHandler.DUST_LITHIUM.get())
            .add(ItemHandler.DUST_SALTPETER.get())
            .add(ItemHandler.DUST_SULFUR.get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/copper").toString()))
            .add(ItemHandler.DUST_COPPER.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/aluminum").toString()))
            .add(ItemHandler.DUST_ALUMINUM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/silver").toString()))
            .add(ItemHandler.DUST_SILVER.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/lead").toString()))
            .add(ItemHandler.DUST_LEAD.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/nickel").toString()))
            .add(ItemHandler.DUST_NICKEL.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/uranium").toString()))
            .add(ItemHandler.DUST_URANIUM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/osmium").toString()))
            .add(ItemHandler.DUST_OSMIUM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/tin").toString()))
            .add(ItemHandler.DUST_TIN.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/zinc").toString()))
            .add(ItemHandler.DUST_ZINC.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/bronze").toString()))
            .add(ItemHandler.DUST_BRONZE.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/brass").toString()))
            .add(ItemHandler.DUST_BRASS.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/constantan").toString()))
            .add(ItemHandler.DUST_CONSTANTAN.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/electrum").toString()))
            .add(ItemHandler.DUST_ELECTRUM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/steel").toString()))
            .add(ItemHandler.DUST_STEEL.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/charcoal").toString()))
            .add(ItemHandler.DUST_CHARCOAL.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/coal").toString()))
            .add(ItemHandler.DUST_COAL.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/diamond").toString()))
            .add(ItemHandler.DUST_DIAMOND.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/emerald").toString()))
            .add(ItemHandler.DUST_EMERALD.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/gold").toString()))
            .add(ItemHandler.DUST_GOLD.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/iron").toString()))
            .add(ItemHandler.DUST_IRON.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/lapis").toString()))
            .add(ItemHandler.DUST_LAPIS.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/obsidian").toString()))
            .add(ItemHandler.DUST_OBSIDIAN.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/quartz").toString()))
            .add(ItemHandler.DUST_QUARTZ.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/certus_quartz").toString()))
            .add(ItemHandler.DUST_CERTUS_QUARTZ.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/skystone").toString()))
            .add(ItemHandler.DUST_SKYSTONE.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/fluix").toString()))
            .add(ItemHandler.DUST_FLUIX.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/ender").toString()))
            .add(ItemHandler.DUST_ENDER.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/coke").toString()))
            .add(ItemHandler.DUST_COKE.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/graphite").toString()))
            .add(ItemHandler.DUST_GRAPHITE.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/lithium").toString()))
            .add(ItemHandler.DUST_LITHIUM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/saltpeter").toString()))
            .add(ItemHandler.DUST_SALTPETER.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/sulfur").toString()))
            .add(ItemHandler.DUST_SULFUR.get());

    // Gems
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "gems").toString()))
            .add(ItemHandler.GEM_CERTUS_QUARTZ.get())
            .add(ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get())
            .add(ItemHandler.GEM_FLUIX.get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "gems/certus_quartz").toString()))
            .add(ItemHandler.GEM_CERTUS_QUARTZ.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "gems/charged_certus_quartz").toString()))
            .add(ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "gems/fluix").toString()))
            .add(ItemHandler.GEM_FLUIX.get());

    // Plates
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "plates").toString()))
            .add(ItemHandler.PLATE_COPPER.get())
            .add(ItemHandler.PLATE_ALUMINUM.get())
            .add(ItemHandler.PLATE_SILVER.get())
            .add(ItemHandler.PLATE_LEAD.get())
            .add(ItemHandler.PLATE_NICKEL.get())
            .add(ItemHandler.PLATE_URANIUM.get())
            .add(ItemHandler.PLATE_OSMIUM.get())
            .add(ItemHandler.PLATE_TIN.get())
            .add(ItemHandler.PLATE_ZINC.get())
            .add(ItemHandler.PLATE_BRONZE.get())
            .add(ItemHandler.PLATE_BRASS.get())
            .add(ItemHandler.PLATE_CONSTANTAN.get())
            .add(ItemHandler.PLATE_ELECTRUM.get())
            .add(ItemHandler.PLATE_STEEL.get())
            .add(ItemHandler.PLATE_GOLD.get())
            .add(ItemHandler.PLATE_IRON.get())
            .add(ItemHandler.PLATE_LAPIS.get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "plates/copper").toString()))
            .add(ItemHandler.PLATE_COPPER.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "plates/aluminum").toString()))
            .add(ItemHandler.PLATE_ALUMINUM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "plates/silver").toString()))
            .add(ItemHandler.PLATE_SILVER.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "plates/lead").toString()))
            .add(ItemHandler.PLATE_LEAD.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "plates/nickel").toString()))
            .add(ItemHandler.PLATE_NICKEL.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "plates/uranium").toString()))
            .add(ItemHandler.PLATE_URANIUM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "plates/osmium").toString()))
            .add(ItemHandler.PLATE_OSMIUM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "plates/tin").toString()))
            .add(ItemHandler.PLATE_TIN.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "plates/zinc").toString()))
            .add(ItemHandler.PLATE_ZINC.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "plates/bronze").toString()))
            .add(ItemHandler.PLATE_BRONZE.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "plates/brass").toString()))
            .add(ItemHandler.PLATE_BRASS.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "plates/constantan").toString()))
            .add(ItemHandler.PLATE_CONSTANTAN.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "plates/electrum").toString()))
            .add(ItemHandler.PLATE_ELECTRUM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "plates/steel").toString()))
            .add(ItemHandler.PLATE_STEEL.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "plates/gold").toString()))
            .add(ItemHandler.PLATE_GOLD.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "plates/iron").toString()))
            .add(ItemHandler.PLATE_IRON.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "plates/lapis").toString()))
            .add(ItemHandler.PLATE_LAPIS.get());

    // Chunks
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "chunks").toString()))
            .add(ItemHandler.CHUNK_COAL.get())
            .add(ItemHandler.CHUNK_IRON.get())
            .add(ItemHandler.CHUNK_GOLD.get())
            .add(ItemHandler.CHUNK_DIAMOND.get())
            .add(ItemHandler.CHUNK_EMERALD.get())
            .add(ItemHandler.CHUNK_LAPIS.get())
            .add(ItemHandler.CHUNK_REDSTONE.get())
            .add(ItemHandler.CHUNK_COPPER.get())
            .add(ItemHandler.CHUNK_ALUMINUM.get())
            .add(ItemHandler.CHUNK_SILVER.get())
            .add(ItemHandler.CHUNK_LEAD.get())
            .add(ItemHandler.CHUNK_NICKEL.get())
            .add(ItemHandler.CHUNK_URANIUM.get())
            .add(ItemHandler.CHUNK_OSMIUM.get())
            .add(ItemHandler.CHUNK_TIN.get())
            .add(ItemHandler.CHUNK_ZINC.get())
            .add(ItemHandler.CHUNK_CERTUS_QUARTZ.get())
            .add(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "chunks/coal").toString()))
            .add(ItemHandler.CHUNK_COAL.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "chunks/iron").toString()))
            .add(ItemHandler.CHUNK_IRON.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "chunks/gold").toString()))
            .add(ItemHandler.CHUNK_GOLD.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "chunks/diamond").toString()))
            .add(ItemHandler.CHUNK_DIAMOND.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "chunks/emerald").toString()))
            .add(ItemHandler.CHUNK_EMERALD.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "chunks/lapis").toString()))
            .add(ItemHandler.CHUNK_LAPIS.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "chunks/redstone").toString()))
            .add(ItemHandler.CHUNK_REDSTONE.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "chunks/copper").toString()))
            .add(ItemHandler.CHUNK_COPPER.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "chunks/aluminum").toString()))
            .add(ItemHandler.CHUNK_ALUMINUM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "chunks/silver").toString()))
            .add(ItemHandler.CHUNK_SILVER.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "chunks/lead").toString()))
            .add(ItemHandler.CHUNK_LEAD.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "chunks/nickel").toString()))
            .add(ItemHandler.CHUNK_NICKEL.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "chunks/uranium").toString()))
            .add(ItemHandler.CHUNK_URANIUM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "chunks/osmium").toString()))
            .add(ItemHandler.CHUNK_OSMIUM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "chunks/tin").toString()))
            .add(ItemHandler.CHUNK_TIN.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "chunks/zinc").toString()))
            .add(ItemHandler.CHUNK_ZINC.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "chunks/certus_quartz").toString()))
            .add(ItemHandler.CHUNK_CERTUS_QUARTZ.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "chunks/charged_certus_quartz").toString()))
            .add(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get());

    // Block Items
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks").toString()))
            .add(ItemHandler.BLOCK_COPPER_ITEM.get())
            .add(ItemHandler.BLOCK_ALUMINUM_ITEM.get())
            .add(ItemHandler.BLOCK_SILVER_ITEM.get())
            .add(ItemHandler.BLOCK_LEAD_ITEM.get())
            .add(ItemHandler.BLOCK_NICKEL_ITEM.get())
            .add(ItemHandler.BLOCK_URANIUM_ITEM.get())
            .add(ItemHandler.BLOCK_OSMIUM_ITEM.get())
            .add(ItemHandler.BLOCK_TIN_ITEM.get())
            .add(ItemHandler.BLOCK_ZINC_ITEM.get())
            .add(ItemHandler.BLOCK_BRONZE_ITEM.get())
            .add(ItemHandler.BLOCK_BRASS_ITEM.get())
            .add(ItemHandler.BLOCK_CONSTANTAN_ITEM.get())
            .add(ItemHandler.BLOCK_ELECTRUM_ITEM.get())
            .add(ItemHandler.BLOCK_STEEL_ITEM.get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/copper").toString()))
            .add(ItemHandler.BLOCK_COPPER_ITEM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/aluminum").toString()))
            .add(ItemHandler.BLOCK_ALUMINUM_ITEM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/silver").toString()))
            .add(ItemHandler.BLOCK_SILVER_ITEM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/lead").toString()))
            .add(ItemHandler.BLOCK_LEAD_ITEM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/nickel").toString()))
            .add(ItemHandler.BLOCK_NICKEL_ITEM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/uranium").toString()))
            .add(ItemHandler.BLOCK_URANIUM_ITEM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/osmium").toString()))
            .add(ItemHandler.BLOCK_OSMIUM_ITEM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/tin").toString()))
            .add(ItemHandler.BLOCK_TIN_ITEM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/zinc").toString()))
            .add(ItemHandler.BLOCK_ZINC_ITEM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/bronze").toString()))
            .add(ItemHandler.BLOCK_BRONZE_ITEM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/brass").toString()))
            .add(ItemHandler.BLOCK_BRASS_ITEM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/constantan").toString()))
            .add(ItemHandler.BLOCK_CONSTANTAN_ITEM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/electrum").toString()))
            .add(ItemHandler.BLOCK_ELECTRUM_ITEM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/steel").toString()))
            .add(ItemHandler.BLOCK_STEEL_ITEM.get());

    // Ore Items
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ores").toString()))
            .add(ItemHandler.ORE_COAL_ITEM.get())
            .add(ItemHandler.ORE_IRON_ITEM.get())
            .add(ItemHandler.ORE_GOLD_ITEM.get())
            .add(ItemHandler.ORE_DIAMOND_ITEM.get())
            .add(ItemHandler.ORE_EMERALD_ITEM.get())
            .add(ItemHandler.ORE_LAPIS_ITEM.get())
            .add(ItemHandler.ORE_REDSTONE_ITEM.get())
            .add(ItemHandler.ORE_COPPER_ITEM.get())
            .add(ItemHandler.ORE_ALUMINUM_ITEM.get())
            .add(ItemHandler.ORE_SILVER_ITEM.get())
            .add(ItemHandler.ORE_LEAD_ITEM.get())
            .add(ItemHandler.ORE_NICKEL_ITEM.get())
            .add(ItemHandler.ORE_URANIUM_ITEM.get())
            .add(ItemHandler.ORE_OSMIUM_ITEM.get())
            .add(ItemHandler.ORE_TIN_ITEM.get())
            .add(ItemHandler.ORE_ZINC_ITEM.get())
            .add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM.get())
            .add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM.get())
            .add(ItemHandler.ORE_COAL_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_IRON_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_GOLD_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_DIAMOND_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_EMERALD_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_LAPIS_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_REDSTONE_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_COPPER_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_ALUMINUM_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_SILVER_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_LEAD_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_NICKEL_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_URANIUM_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_OSMIUM_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_TIN_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_ZINC_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_COAL_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_IRON_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_GOLD_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_DIAMOND_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_EMERALD_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_LAPIS_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_REDSTONE_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_COPPER_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_ALUMINUM_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_SILVER_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_LEAD_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_NICKEL_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_URANIUM_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_OSMIUM_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_TIN_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_ZINC_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_COAL_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_IRON_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_GOLD_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_DIAMOND_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_EMERALD_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_LAPIS_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_REDSTONE_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_COPPER_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_ALUMINUM_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_SILVER_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_LEAD_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_NICKEL_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_URANIUM_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_OSMIUM_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_TIN_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_ZINC_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_COAL_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_IRON_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_GOLD_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_DIAMOND_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_EMERALD_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_LAPIS_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_REDSTONE_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_COPPER_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_ALUMINUM_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_SILVER_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_LEAD_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_NICKEL_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_URANIUM_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_OSMIUM_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_TIN_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_ZINC_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_COAL_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_IRON_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_GOLD_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_DIAMOND_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_EMERALD_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_LAPIS_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_REDSTONE_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_COPPER_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_ALUMINUM_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_SILVER_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_LEAD_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_NICKEL_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_URANIUM_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_OSMIUM_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_TIN_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_ZINC_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_COAL_ITEM_JASPER.get())
            .add(ItemHandler.ORE_IRON_ITEM_JASPER.get())
            .add(ItemHandler.ORE_GOLD_ITEM_JASPER.get())
            .add(ItemHandler.ORE_DIAMOND_ITEM_JASPER.get())
            .add(ItemHandler.ORE_EMERALD_ITEM_JASPER.get())
            .add(ItemHandler.ORE_LAPIS_ITEM_JASPER.get())
            .add(ItemHandler.ORE_REDSTONE_ITEM_JASPER.get())
            .add(ItemHandler.ORE_COPPER_ITEM_JASPER.get())
            .add(ItemHandler.ORE_ALUMINUM_ITEM_JASPER.get())
            .add(ItemHandler.ORE_SILVER_ITEM_JASPER.get())
            .add(ItemHandler.ORE_LEAD_ITEM_JASPER.get())
            .add(ItemHandler.ORE_NICKEL_ITEM_JASPER.get())
            .add(ItemHandler.ORE_URANIUM_ITEM_JASPER.get())
            .add(ItemHandler.ORE_OSMIUM_ITEM_JASPER.get())
            .add(ItemHandler.ORE_TIN_ITEM_JASPER.get())
            .add(ItemHandler.ORE_ZINC_ITEM_JASPER.get())
            .add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM_JASPER.get())
            .add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM_JASPER.get())
            .add(ItemHandler.ORE_COAL_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_IRON_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_GOLD_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_DIAMOND_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_EMERALD_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_LAPIS_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_REDSTONE_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_COPPER_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_ALUMINUM_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_SILVER_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_LEAD_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_NICKEL_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_URANIUM_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_OSMIUM_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_TIN_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_ZINC_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_COAL_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_IRON_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_GOLD_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_DIAMOND_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_EMERALD_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_LAPIS_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_REDSTONE_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_COPPER_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_ALUMINUM_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_SILVER_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_LEAD_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_NICKEL_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_URANIUM_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_OSMIUM_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_TIN_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_ZINC_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_COAL_ITEM_SLATE.get())
            .add(ItemHandler.ORE_IRON_ITEM_SLATE.get())
            .add(ItemHandler.ORE_GOLD_ITEM_SLATE.get())
            .add(ItemHandler.ORE_DIAMOND_ITEM_SLATE.get())
            .add(ItemHandler.ORE_EMERALD_ITEM_SLATE.get())
            .add(ItemHandler.ORE_LAPIS_ITEM_SLATE.get())
            .add(ItemHandler.ORE_REDSTONE_ITEM_SLATE.get())
            .add(ItemHandler.ORE_COPPER_ITEM_SLATE.get())
            .add(ItemHandler.ORE_ALUMINUM_ITEM_SLATE.get())
            .add(ItemHandler.ORE_SILVER_ITEM_SLATE.get())
            .add(ItemHandler.ORE_LEAD_ITEM_SLATE.get())
            .add(ItemHandler.ORE_NICKEL_ITEM_SLATE.get())
            .add(ItemHandler.ORE_URANIUM_ITEM_SLATE.get())
            .add(ItemHandler.ORE_OSMIUM_ITEM_SLATE.get())
            .add(ItemHandler.ORE_TIN_ITEM_SLATE.get())
            .add(ItemHandler.ORE_ZINC_ITEM_SLATE.get())
            .add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM_SLATE.get())
            .add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM_SLATE.get())
            .add(ItemHandler.CHUNK_COAL.get())
            .add(ItemHandler.CHUNK_IRON.get())
            .add(ItemHandler.CHUNK_GOLD.get())
            .add(ItemHandler.CHUNK_DIAMOND.get())
            .add(ItemHandler.CHUNK_EMERALD.get())
            .add(ItemHandler.CHUNK_LAPIS.get())
            .add(ItemHandler.CHUNK_REDSTONE.get())
            .add(ItemHandler.CHUNK_COPPER.get())
            .add(ItemHandler.CHUNK_ALUMINUM.get())
            .add(ItemHandler.CHUNK_SILVER.get())
            .add(ItemHandler.CHUNK_LEAD.get())
            .add(ItemHandler.CHUNK_NICKEL.get())
            .add(ItemHandler.CHUNK_URANIUM.get())
            .add(ItemHandler.CHUNK_OSMIUM.get())
            .add(ItemHandler.CHUNK_TIN.get())
            .add(ItemHandler.CHUNK_ZINC.get())
            .add(ItemHandler.CHUNK_CERTUS_QUARTZ.get())
            .add(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ores/coal").toString()))
            .add(ItemHandler.ORE_COAL_ITEM.get())
            .add(ItemHandler.ORE_COAL_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_COAL_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_COAL_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_COAL_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_COAL_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_COAL_ITEM_JASPER.get())
            .add(ItemHandler.ORE_COAL_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_COAL_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_COAL_ITEM_SLATE.get())
            .add(ItemHandler.CHUNK_COAL.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ores/iron").toString()))
            .add(ItemHandler.ORE_IRON_ITEM.get())
            .add(ItemHandler.ORE_IRON_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_IRON_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_IRON_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_IRON_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_IRON_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_IRON_ITEM_JASPER.get())
            .add(ItemHandler.ORE_IRON_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_IRON_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_IRON_ITEM_SLATE.get())
            .add(ItemHandler.CHUNK_IRON.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ores/gold").toString()))
            .add(ItemHandler.ORE_GOLD_ITEM.get())
            .add(ItemHandler.ORE_GOLD_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_GOLD_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_GOLD_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_GOLD_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_GOLD_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_GOLD_ITEM_JASPER.get())
            .add(ItemHandler.ORE_GOLD_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_GOLD_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_GOLD_ITEM_SLATE.get())
            .add(ItemHandler.CHUNK_GOLD.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ores/diamond").toString()))
            .add(ItemHandler.ORE_DIAMOND_ITEM.get())
            .add(ItemHandler.ORE_DIAMOND_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_DIAMOND_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_DIAMOND_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_DIAMOND_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_DIAMOND_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_DIAMOND_ITEM_JASPER.get())
            .add(ItemHandler.ORE_DIAMOND_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_DIAMOND_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_DIAMOND_ITEM_SLATE.get())
            .add(ItemHandler.CHUNK_DIAMOND.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ores/emerald").toString()))
            .add(ItemHandler.ORE_EMERALD_ITEM.get())
            .add(ItemHandler.ORE_EMERALD_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_EMERALD_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_EMERALD_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_EMERALD_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_EMERALD_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_EMERALD_ITEM_JASPER.get())
            .add(ItemHandler.ORE_EMERALD_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_EMERALD_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_EMERALD_ITEM_SLATE.get())
            .add(ItemHandler.CHUNK_EMERALD.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ores/lapis").toString()))
            .add(ItemHandler.ORE_LAPIS_ITEM.get())
            .add(ItemHandler.ORE_LAPIS_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_LAPIS_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_LAPIS_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_LAPIS_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_LAPIS_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_LAPIS_ITEM_JASPER.get())
            .add(ItemHandler.ORE_LAPIS_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_LAPIS_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_LAPIS_ITEM_SLATE.get())
            .add(ItemHandler.CHUNK_LAPIS.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ores/redstone").toString()))
            .add(ItemHandler.ORE_REDSTONE_ITEM.get())
            .add(ItemHandler.ORE_REDSTONE_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_REDSTONE_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_REDSTONE_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_REDSTONE_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_REDSTONE_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_REDSTONE_ITEM_JASPER.get())
            .add(ItemHandler.ORE_REDSTONE_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_REDSTONE_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_REDSTONE_ITEM_SLATE.get())
            .add(ItemHandler.CHUNK_REDSTONE.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ores/copper").toString()))
            .add(ItemHandler.ORE_COPPER_ITEM.get())
            .add(ItemHandler.ORE_COPPER_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_COPPER_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_COPPER_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_COPPER_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_COPPER_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_COPPER_ITEM_JASPER.get())
            .add(ItemHandler.ORE_COPPER_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_COPPER_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_COPPER_ITEM_SLATE.get())
            .add(ItemHandler.CHUNK_COPPER.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ores/aluminum").toString()))
            .add(ItemHandler.ORE_ALUMINUM_ITEM.get())
            .add(ItemHandler.ORE_ALUMINUM_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_ALUMINUM_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_ALUMINUM_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_ALUMINUM_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_ALUMINUM_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_ALUMINUM_ITEM_JASPER.get())
            .add(ItemHandler.ORE_ALUMINUM_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_ALUMINUM_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_ALUMINUM_ITEM_SLATE.get())
            .add(ItemHandler.CHUNK_ALUMINUM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ores/silver").toString()))
            .add(ItemHandler.ORE_SILVER_ITEM.get())
            .add(ItemHandler.ORE_SILVER_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_SILVER_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_SILVER_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_SILVER_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_SILVER_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_SILVER_ITEM_JASPER.get())
            .add(ItemHandler.ORE_SILVER_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_SILVER_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_SILVER_ITEM_SLATE.get())
            .add(ItemHandler.CHUNK_SILVER.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ores/lead").toString()))
            .add(ItemHandler.ORE_LEAD_ITEM.get())
            .add(ItemHandler.ORE_LEAD_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_LEAD_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_LEAD_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_LEAD_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_LEAD_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_LEAD_ITEM_JASPER.get())
            .add(ItemHandler.ORE_LEAD_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_LEAD_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_LEAD_ITEM_SLATE.get())
            .add(ItemHandler.CHUNK_LEAD.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ores/nickel").toString()))
            .add(ItemHandler.ORE_NICKEL_ITEM.get())
            .add(ItemHandler.ORE_NICKEL_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_NICKEL_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_NICKEL_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_NICKEL_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_NICKEL_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_NICKEL_ITEM_JASPER.get())
            .add(ItemHandler.ORE_NICKEL_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_NICKEL_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_NICKEL_ITEM_SLATE.get())
            .add(ItemHandler.CHUNK_NICKEL.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ores/uranium").toString()))
            .add(ItemHandler.ORE_URANIUM_ITEM.get())
            .add(ItemHandler.ORE_URANIUM_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_URANIUM_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_URANIUM_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_URANIUM_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_URANIUM_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_URANIUM_ITEM_JASPER.get())
            .add(ItemHandler.ORE_URANIUM_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_URANIUM_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_URANIUM_ITEM_SLATE.get())
            .add(ItemHandler.CHUNK_URANIUM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ores/osmium").toString()))
            .add(ItemHandler.ORE_OSMIUM_ITEM.get())
            .add(ItemHandler.ORE_OSMIUM_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_OSMIUM_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_OSMIUM_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_OSMIUM_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_OSMIUM_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_OSMIUM_ITEM_JASPER.get())
            .add(ItemHandler.ORE_OSMIUM_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_OSMIUM_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_OSMIUM_ITEM_SLATE.get())
            .add(ItemHandler.CHUNK_OSMIUM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ores/tin").toString()))
            .add(ItemHandler.ORE_TIN_ITEM.get())
            .add(ItemHandler.ORE_TIN_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_TIN_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_TIN_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_TIN_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_TIN_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_TIN_ITEM_JASPER.get())
            .add(ItemHandler.ORE_TIN_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_TIN_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_TIN_ITEM_SLATE.get())
            .add(ItemHandler.CHUNK_TIN.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ores/zinc").toString()))
            .add(ItemHandler.ORE_ZINC_ITEM.get())
            .add(ItemHandler.ORE_ZINC_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_ZINC_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_ZINC_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_ZINC_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_ZINC_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_ZINC_ITEM_JASPER.get())
            .add(ItemHandler.ORE_ZINC_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_ZINC_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_ZINC_ITEM_SLATE.get())
            .add(ItemHandler.CHUNK_ZINC.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ores/certus_quartz").toString()))
            .add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM.get())
            .add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM_JASPER.get())
            .add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM_SLATE.get())
            .add(ItemHandler.CHUNK_CERTUS_QUARTZ.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ores/charged_certus_quartz").toString()))
            .add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM.get())
            .add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM_ANDESITE.get())
            .add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM_GABBRO.get())
            .add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM_C_LIMESTONE.get())
            .add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM_SCORIA.get())
            .add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM_WEATHERED_LIMESTONE.get())
            .add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM_JASPER.get())
            .add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM_Q_LIMESTONE.get())
            .add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM_MARBLE.get())
            .add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM_SLATE.get())
            .add(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get());

    // Minecraft Additional Tags
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.MINECRAFT_TAG, "beacon_payment_items").toString()))
            .add(ItemHandler.INGOT_COPPER.get())
            .add(ItemHandler.INGOT_ALUMINUM.get())
            .add(ItemHandler.INGOT_SILVER.get())
            .add(ItemHandler.INGOT_LEAD.get())
            .add(ItemHandler.INGOT_NICKEL.get())
            .add(ItemHandler.INGOT_URANIUM.get())
            .add(ItemHandler.INGOT_OSMIUM.get())
            .add(ItemHandler.INGOT_TIN.get())
            .add(ItemHandler.INGOT_ZINC.get())
            .add(ItemHandler.INGOT_BRONZE.get())
            .add(ItemHandler.INGOT_BRASS.get())
            .add(ItemHandler.INGOT_CONSTANTAN.get())
            .add(ItemHandler.INGOT_ELECTRUM.get())
            .add(ItemHandler.INGOT_STEEL.get());

    // AE2 Additional Tags
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.AE2_TAG, "dusts/quartz").toString()))
            .add(ItemHandler.DUST_CERTUS_QUARTZ.get())
            .add(ItemHandler.DUST_QUARTZ.get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.AE2_TAG, "dusts/certus_quartz").toString()))
            .add(ItemHandler.DUST_CERTUS_QUARTZ.get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.AE2_TAG, "dusts/nether_quartz").toString()))
            .add(ItemHandler.DUST_QUARTZ.get());

  }
}