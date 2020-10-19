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

import com.ridanisaurus.emendatusenigmatica.registries.BlockHandler;
import com.ridanisaurus.emendatusenigmatica.registries.ItemHandler;
import com.ridanisaurus.emendatusenigmatica.util.Materials;
import com.ridanisaurus.emendatusenigmatica.util.ProcessedMaterials;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

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

    // Forge Tags
    Builder<Item> forgeBlocks = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks").toString()));
    Builder<Item> forgeIngots = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ingots").toString()));
    Builder<Item> forgeGems = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "gems").toString()));
    Builder<Item> forgeNuggets = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "nuggets").toString()));
    Builder<Item> forgeDusts = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "dusts").toString()));
    Builder<Item> forgePlates = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "plates").toString()));
    Builder<Item> forgeChunks = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "chunks").toString()));
    Builder<Item> forgeOres = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ores").toString()));

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

    // Gems
    ItemHandler.itemTable.get().row(ProcessedMaterials.GEM).forEach((mat, gem) -> {
      forgeGems.add(gem.get());
      Builder<Item> gemTag = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "gems/" + mat.id).toString()));
      gemTag.add(gem.get());
    });

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

    // Chunks
    ItemHandler.itemTable.get().row(ProcessedMaterials.CHUNK).forEach((mat, chunk) -> {
      forgeChunks.add(chunk.get());
      Builder<Item> chunkTag = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "chunks/" + mat.id).toString()));
      chunkTag.add(chunk.get());
    });

    // Ore Items

    ItemHandler.oreItemTable.get().values().forEach(s -> forgeOres.add(s.get()));
    for (Materials material : Materials.values()) {
      List<String> toCreate = Arrays.asList(material.type);
      if (material.oreBlock != null && toCreate.contains("Ore")) {
        Builder<Item> oreTag = getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ores/" + material.id).toString()));
        ItemHandler.oreItemTable.get().column(material).values().forEach(s -> oreTag.add(s.get()));
        forgeOres.add(ItemHandler.itemTable.get().get(ProcessedMaterials.CHUNK, material).get());
        oreTag.add(ItemHandler.itemTable.get().get(ProcessedMaterials.CHUNK, material).get());
      }
    }

    // AE2 Additional Tags
    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.AE2_TAG, "dusts/quartz").toString()))
            .add(ItemHandler.itemTable.get().get(ProcessedMaterials.GEM, Materials.CERTUS_QUARTZ).get())
            .add(ItemHandler.DUST_QUARTZ.get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.AE2_TAG, "dusts/certus_quartz").toString()))
            .add(ItemHandler.itemTable.get().get(ProcessedMaterials.GEM, Materials.CERTUS_QUARTZ).get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.AE2_TAG, "dusts/nether_quartz").toString()))
            .add(ItemHandler.DUST_QUARTZ.get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.AE2_TAG, "crystals/certus_quartz").toString()))
            .add(ItemHandler.itemTable.get().get(ProcessedMaterials.GEM, Materials.CERTUS_QUARTZ).get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.AE2_TAG, "crystals/charged_certus_quartz").toString()))
            .add(ItemHandler.itemTable.get().get(ProcessedMaterials.GEM, Materials.CHARGED_CERTUS_QUARTZ).get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.AE2_TAG, "crystals/quartz").toString()))
            .add(ItemHandler.itemTable.get().get(ProcessedMaterials.GEM, Materials.CERTUS_QUARTZ).get())
            .add(ItemHandler.itemTable.get().get(ProcessedMaterials.GEM, Materials.CHARGED_CERTUS_QUARTZ).get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.AE2_TAG, "crystals/certus").toString()))
            .add(ItemHandler.itemTable.get().get(ProcessedMaterials.GEM, Materials.CERTUS_QUARTZ).get())
            .add(ItemHandler.itemTable.get().get(ProcessedMaterials.GEM, Materials.CHARGED_CERTUS_QUARTZ).get());

    getOrCreateBuilder(ItemTags.makeWrapperTag(new ResourceLocation(Reference.AE2_TAG, "crystals/fluix").toString()))
            .add(ItemHandler.itemTable.get().get(ProcessedMaterials.GEM, Materials.FLUIX).get());
  }
}