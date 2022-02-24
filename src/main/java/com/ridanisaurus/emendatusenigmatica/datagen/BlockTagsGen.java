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
import com.ridanisaurus.emendatusenigmatica.registries.OreHandler;
import com.ridanisaurus.emendatusenigmatica.util.Materials;
import com.ridanisaurus.emendatusenigmatica.util.ProcessedMaterials;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Arrays;
import java.util.List;

public class BlockTagsGen extends BlockTagsProvider {

  public BlockTagsGen(DataGenerator gen, ExistingFileHelper existingFileHelper) {
    super(gen, Reference.MOD_ID, existingFileHelper);
  }

  @Override
  protected void addTags() {
    // Storage Blocks
    var forgeBlocks = tag(BlockTags.bind(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks").toString()));
    BlockHandler.backingStorageBlockTable.values().forEach(block -> forgeBlocks.add(block.get()));

    for (Materials material : Materials.values()) {
      List<String> toCreate = Arrays.asList(material.type);
      if (toCreate.contains("Block")) {
        var blockTag = tag(BlockTags.bind(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/" + material.id).toString()));
        BlockHandler.backingStorageBlockTable.column(material).values().forEach(block -> blockTag.add(block.get()));
      }
    }

    // Ores
    var forgeOres = tag(BlockTags.createOptional(new ResourceLocation(Reference.FORGE_TAG, "ores")));
    OreHandler.backingOreBlockTable.values().forEach(ore -> forgeOres.add(ore.get()));

    for (Materials material : Materials.values()) {
      List<String> toCreate = Arrays.asList(material.type);
      if (material.oreBlock != null && toCreate.contains("Ore")) {
        TagAppender<Block> oreTag = tag(BlockTags.createOptional(new ResourceLocation(Reference.FORGE_TAG, "ores/" + material.id)));
        OreHandler.backingOreBlockTable.column(material).values().forEach(strataOre -> oreTag.add(strataOre.get()));
      }
    }

    // Minecraft Additional Tags (Beacon Base)
    var beaconBlocks = tag(BlockTags.bind(new ResourceLocation(Reference.MINECRAFT_TAG, "beacon_base_blocks").toString()));
    BlockHandler.backingStorageBlockTable.values().forEach(beaconBlock -> beaconBlocks.add(beaconBlock.get()));
    // Minecraft Additional Tags (Angers Piglin)
    var guardedBlocks = tag(BlockTags.bind(new ResourceLocation(Reference.MINECRAFT_TAG, "guarded_by_piglins").toString()));
    OreHandler.backingOreBlockTable.column(Materials.GOLD).values().forEach(guardedBlock -> guardedBlocks.add(guardedBlock.get()));

    // Mana/Arcane Ore
    var manaOres = tag(BlockTags.bind(new ResourceLocation(Reference.FORGE_TAG, "ores/mana").toString()));
    OreHandler.backingOreBlockTable.column(Materials.ARCANE).values().forEach(manaOre -> manaOres.add(manaOre.get()));

    // Potassium Nitrate Compact
    tag(BlockTags.bind(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/niter").toString()))
            .add(BlockHandler.backingStorageBlockTable.get(ProcessedMaterials.STORAGE_BLOCK, Materials.POTASSIUM_NITRATE).get());

    tag(BlockTags.bind(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/saltpeter").toString()))
            .add(BlockHandler.backingStorageBlockTable.get(ProcessedMaterials.STORAGE_BLOCK, Materials.POTASSIUM_NITRATE).get());

    // Misc
    tag(BlockTags.bind(new ResourceLocation(Reference.MOD_ID, "misc/enigmatic_fortunizer").toString()))
            .add(BlockHandler.ENIGMATIC_FORTUNIZER.get());
  }
}