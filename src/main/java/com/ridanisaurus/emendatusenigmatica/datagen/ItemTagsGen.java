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
import com.ridanisaurus.emendatusenigmatica.util.Materials;
import com.ridanisaurus.emendatusenigmatica.util.ProcessedMaterials;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;
import java.util.List;

public class ItemTagsGen extends ItemTagsProvider {

  public ItemTagsGen(DataGenerator gen, BlockTagsProvider blockTagProvider) {
    super(gen, blockTagProvider);
  }

  @Override
  protected void registerTags() {
    // Forge Tags
    Builder<Item> forgeBlocks = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks").toString()));
    Builder<Item> forgeIngots = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ingots").toString()));
    Builder<Item> forgeGems = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "gems").toString()));
    Builder<Item> forgeNuggets = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "nuggets").toString()));
    Builder<Item> forgeDusts = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts").toString()));
    Builder<Item> forgePlates = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "plates").toString()));
    Builder<Item> forgeGears = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "gears").toString()));
    Builder<Item> forgeRods = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "rods").toString()));
    Builder<Item> forgeChunks = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "chunks").toString()));
    Builder<Item> forgeOres = getOrCreateBuilder(ItemTags.createOptional(new ResourceLocation(Reference.FORGE_TAG, "ores")));

    Builder<Item> beaconIngots = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.MINECRAFT_TAG, "beacon_payment_items").toString()));

    // Storage Block Items
    ItemHandler.backingItemTable.row(ProcessedMaterials.STORAGE_BLOCK).forEach((mat, storageBlock) -> {
      forgeBlocks.add(storageBlock.get());
      Builder<Item> blockTag = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/" + mat.id).toString()));
      blockTag.add(storageBlock.get());
    });

    // Ingots
    ItemHandler.backingItemTable.row(ProcessedMaterials.INGOT).forEach((mat, ingot) -> {
      forgeIngots.add(ingot.get());
      beaconIngots.add(ingot.get());
      Builder<Item> ingotTag = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ingots/" + mat.id).toString()));
      ingotTag.add(ingot.get());
    });

    // Gems
    ItemHandler.backingItemTable.row(ProcessedMaterials.GEM).forEach((mat, gem) -> {
      if (!mat.id.equals("arcane")) {
      forgeGems.add(gem.get());
      Builder<Item> gemTag = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "gems/" + mat.id).toString()));
      gemTag.add(gem.get());
      }
    });

    // Nuggets
    ItemHandler.backingItemTable.row(ProcessedMaterials.NUGGET).forEach((mat, nugget) -> {
      forgeNuggets.add(nugget.get());
      Builder<Item> nuggetTag = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "nuggets/" + mat.id).toString()));
      nuggetTag.add(nugget.get());
    });

    // Dusts
    ItemHandler.backingItemTable.row(ProcessedMaterials.DUST).forEach((mat, dust) -> {
      forgeDusts.add(dust.get());
      Builder<Item> dustTag = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/" + mat.id).toString()));
      dustTag.add(dust.get());
    });

    // Plates
    ItemHandler.backingItemTable.row(ProcessedMaterials.PLATE).forEach((mat, plate) -> {
      forgePlates.add(plate.get());
      Builder<Item> plateTag = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "plates/" + mat.id).toString()));
      plateTag.add(plate.get());
    });

    // Gears
    ItemHandler.backingItemTable.row(ProcessedMaterials.GEAR).forEach((mat, gear) -> {
      forgeGears.add(gear.get());
      Builder<Item> gearTag = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "gears/" + mat.id).toString()));
      gearTag.add(gear.get());
    });

    // Rods
    ItemHandler.backingItemTable.row(ProcessedMaterials.ROD).forEach((mat, rod) -> {
      forgeRods.add(rod.get());
      Builder<Item> rodTag = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "rods/" + mat.id).toString()));
      rodTag.add(rod.get());
    });

    // Chunks
    ItemHandler.backingItemTable.row(ProcessedMaterials.CHUNK).forEach((mat, chunk) -> {
      forgeChunks.add(chunk.get());
      Builder<Item> chunkTag = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "chunks/" + mat.id).toString()));
      chunkTag.add(chunk.get());
    });

    // Ore Items

    ItemHandler.backingOreItemTable.values().forEach(s -> forgeOres.add(s.get()));
    for (Materials material : Materials.values()) {
      List<String> toCreate = Arrays.asList(material.type);
      if (material.oreBlock != null && toCreate.contains("Ore")) {
        Builder<Item> oreTag = getOrCreateBuilder(ItemTags.createOptional(new ResourceLocation(Reference.FORGE_TAG, "ores/" + material.id)));
        ItemHandler.backingOreItemTable.column(material).values().forEach(s -> oreTag.add(s.get()));
        forgeOres.add(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, material).get());
        oreTag.add(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, material).get());
      }
    }

    // Dusts
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts").toString()))
            .add(ItemHandler.DUST_CHARCOAL.get())
            .add(ItemHandler.DUST_OBSIDIAN.get())
            .add(ItemHandler.DUST_ENDER.get())
            .add(ItemHandler.DUST_COKE.get())
            .add(ItemHandler.DUST_GRAPHITE.get())
            .add(ItemHandler.DUST_LITHIUM.get())
            .add(ItemHandler.DUST_WOOD.get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/charcoal").toString()))
            .add(ItemHandler.DUST_CHARCOAL.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/obsidian").toString()))
            .add(ItemHandler.DUST_OBSIDIAN.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/ender").toString()))
            .add(ItemHandler.DUST_ENDER.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/coke").toString()))
            .add(ItemHandler.DUST_COKE.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/graphite").toString()))
            .add(ItemHandler.DUST_GRAPHITE.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/lithium").toString()))
            .add(ItemHandler.DUST_LITHIUM.get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/wood").toString()))
            .add(ItemHandler.DUST_WOOD.get());

    // Mana Gem
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "gems/mana").toString()))
            .add(ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.ARCANE).get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "gems").toString()))
            .add(ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.ARCANE).get());

    // Potassium Nitrate Compact
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/niter").toString()))
            .add(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.POTASSIUM_NITRATE).get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "gems/niter").toString()))
            .add(ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.POTASSIUM_NITRATE).get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/niter").toString()))
            .add(ItemHandler.backingItemTable.get(ProcessedMaterials.STORAGE_BLOCK, Materials.POTASSIUM_NITRATE).get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts/saltpeter").toString()))
            .add(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.POTASSIUM_NITRATE).get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "gems/saltpeter").toString()))
            .add(ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.POTASSIUM_NITRATE).get());
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/saltpeter").toString()))
            .add(ItemHandler.backingItemTable.get(ProcessedMaterials.STORAGE_BLOCK, Materials.POTASSIUM_NITRATE).get());


    // AE2 Additional Tags
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.AE2_TAG, "dusts/quartz").toString()))
            .add(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.CERTUS_QUARTZ).get())
            .add(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.QUARTZ).get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.AE2_TAG, "dusts/certus_quartz").toString()))
            .add(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.CERTUS_QUARTZ).get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.AE2_TAG, "dusts/nether_quartz").toString()))
            .add(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.QUARTZ).get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.AE2_TAG, "crystals/certus_quartz").toString()))
            .add(ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.CERTUS_QUARTZ).get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.AE2_TAG, "crystals/charged_certus_quartz").toString()))
            .add(ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.CHARGED_CERTUS_QUARTZ).get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.AE2_TAG, "crystals/quartz").toString()))
            .add(ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.CERTUS_QUARTZ).get())
            .add(ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.CHARGED_CERTUS_QUARTZ).get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.AE2_TAG, "crystals/certus").toString()))
            .add(ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.CERTUS_QUARTZ).get())
            .add(ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.CHARGED_CERTUS_QUARTZ).get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.AE2_TAG, "crystals/fluix").toString()))
            .add(ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.FLUIX).get());
  }
}