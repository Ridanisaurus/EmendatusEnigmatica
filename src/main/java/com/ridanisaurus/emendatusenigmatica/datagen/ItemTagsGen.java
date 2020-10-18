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
import com.ridanisaurus.emendatusenigmatica.util.Ores;
import com.ridanisaurus.emendatusenigmatica.util.ProcessedMaterials;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class ItemTagsGen extends ItemTagsProvider {

  public ItemTagsGen(DataGenerator gen, BlockTagsProvider blockTagProvider) {
    super(gen, blockTagProvider);
  }

  @Override
  protected void registerTags() {
    // Dusts
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts").toString()))
            .add(ItemHandler.DUST_CHARCOAL.get())
            .add(ItemHandler.DUST_OBSIDIAN.get())
            .add(ItemHandler.DUST_QUARTZ.get())
            .add(ItemHandler.DUST_ENDER.get())
            .add(ItemHandler.DUST_COKE.get())
            .add(ItemHandler.DUST_GRAPHITE.get())
            .add(ItemHandler.DUST_LITHIUM.get())
            .add(ItemHandler.DUST_SALTPETER.get())
            .add(ItemHandler.DUST_SULFUR.get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/charcoal").toString()))
            .add(ItemHandler.DUST_CHARCOAL.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/obsidian").toString()))
            .add(ItemHandler.DUST_OBSIDIAN.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/quartz").toString()))
            .add(ItemHandler.DUST_QUARTZ.get());
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
            .add(ItemHandler.GEM_FLUIX.get())
            .add(ItemHandler.GEM_FLUORITE.get());


    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "gems/certus_quartz").toString()))
            .add(ItemHandler.GEM_CERTUS_QUARTZ.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "gems/charged_certus_quartz").toString()))
            .add(ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "gems/certus").toString()))
            .add(ItemHandler.GEM_CERTUS_QUARTZ.get())
            .add(ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "gems/fluix").toString()))
            .add(ItemHandler.GEM_FLUIX.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "gems/fluorite").toString()))
            .add(ItemHandler.GEM_FLUORITE.get());

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
            .add(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get())
            .add(ItemHandler.CHUNK_FLUORITE.get());

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
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "chunks/fluorite").toString()))
            .add(ItemHandler.CHUNK_FLUORITE.get());

    // Forge Tags
    Builder<Item> forgeBlocks = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks").toString()));
    Builder<Item> forgeIngots = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ingots").toString()));
    //Builder<Item> forgeGems = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "gems").toString()));
    Builder<Item> forgeNuggets = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "nuggets").toString()));
    Builder<Item> forgeDusts = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts").toString()));
    Builder<Item> forgePlates = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "plates").toString()));

    Builder<Item> beaconIngots = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.MINECRAFT_TAG, "beacon_payment_items").toString()));

    // Storage Block Items
    ItemHandler.itemTable.get().row(ProcessedMaterials.STORAGE_BLOCK).forEach((mat, storageBlock) -> {
      forgeBlocks.add(storageBlock.get());
      Builder<Item> blockTag = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/" + mat.id).toString()));
      blockTag.add(storageBlock.get());
    });

    // Ingots
    ItemHandler.itemTable.get().row(ProcessedMaterials.INGOT).forEach((mat, ingot) -> {
      forgeIngots.add(ingot.get());
      beaconIngots.add(ingot.get());
      Builder<Item> ingotTag = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ingots/" + mat.id).toString()));
      ingotTag.add(ingot.get());
    });

    /*// Gems
    ItemHandler.itemTable.get().row(ProcessedMaterials.GEM).forEach((mat, gem) -> {
      forgeGems.add(gem.get());
      Builder<Item> gemTag = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "gems/" + mat.id).toString()));
      gemTag.add(gem.get());
    });*/

    // Nuggets
    ItemHandler.itemTable.get().row(ProcessedMaterials.NUGGET).forEach((mat, nugget) -> {
      forgeNuggets.add(nugget.get());
      Builder<Item> nuggetTag = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "nuggets/" + mat.id).toString()));
      nuggetTag.add(nugget.get());
    });

    // Dusts
    ItemHandler.itemTable.get().row(ProcessedMaterials.DUST).forEach((mat, dust) -> {
      forgeDusts.add(dust.get());
      Builder<Item> dustTag = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/" + mat.id).toString()));
      dustTag.add(dust.get());
    });

    // Plates
    ItemHandler.itemTable.get().row(ProcessedMaterials.PLATE).forEach((mat, plate) -> {
      forgePlates.add(plate.get());
      Builder<Item> plateTag = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "plates/" + mat.id).toString()));
      plateTag.add(plate.get());
    });

    // Ore Items
    Builder<Item> forgeOres = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ores").toString()));
    ItemHandler.oreItemTable.get().values().forEach(s -> forgeOres.add(s.get()));

    for (Ores ore : Ores.values()) {
      Builder<Item> oreTag = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ores/" + ore.id).toString()));

      // #forge:ores
      forgeOres.add(ore.chunk.get());

      // #forge:ores/{id}
      ItemHandler.oreItemTable.get().column(ore).values().forEach(s -> oreTag.add(s.get()));
      oreTag.add(ore.chunk.get());
    }

    /*// AE2 Additional Tags
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.AE2_TAG, "dusts/quartz").toString()))
            .add(ItemHandler.DUST_CERTUS_QUARTZ.get())
            .add(ItemHandler.DUST_QUARTZ.get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.AE2_TAG, "dusts/certus_quartz").toString()))
            .add(ItemHandler.DUST_CERTUS_QUARTZ.get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.AE2_TAG, "dusts/nether_quartz").toString()))
            .add(ItemHandler.DUST_QUARTZ.get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.AE2_TAG, "crystals/certus_quartz").toString()))
            .add(ItemHandler.GEM_CERTUS_QUARTZ.get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.AE2_TAG, "crystals/charged_certus_quartz").toString()))
            .add(ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.AE2_TAG, "crystals/quartz").toString()))
            .add(ItemHandler.GEM_CERTUS_QUARTZ.get())
            .add(ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.AE2_TAG, "crystals/certus").toString()))
            .add(ItemHandler.GEM_CERTUS_QUARTZ.get())
            .add(ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.AE2_TAG, "crystals/fluix").toString()))
            .add(ItemHandler.GEM_FLUIX.get());*/
  }
}