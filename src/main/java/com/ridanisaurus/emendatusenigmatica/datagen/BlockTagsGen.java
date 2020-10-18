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
import com.ridanisaurus.emendatusenigmatica.util.Ores;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;

public class BlockTagsGen extends BlockTagsProvider {

  public BlockTagsGen(DataGenerator gen) {
    super(gen);
  }

  @Override
  protected void registerTags() {

    // Storage Blocks
    Builder<Block> forgeBlocks = getOrCreateBuilder(BlockTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks").toString()));
    BlockHandler.storageBlockTable.get().values().forEach(block -> forgeBlocks.add(block.get()));

    for (Materials material : Materials.values()) {
      Builder<Block> blockTag = getOrCreateBuilder(BlockTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/" + material.id).toString()));
      BlockHandler.storageBlockTable.get().column(material).values().forEach(block -> blockTag.add(block.get()));
    }

    // Ores
    Builder<Block> forgeOres = getOrCreateBuilder(BlockTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ores").toString()));
    OreHandler.oreBlockTable.get().values().forEach(ore -> forgeOres.add(ore.get()));

    for (Ores ore : Ores.values()) {
      Builder<Block> oreTag = getOrCreateBuilder(BlockTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "ores/" + ore.id).toString()));
      OreHandler.oreBlockTable.get().column(ore).values().forEach(strataOre -> oreTag.add(strataOre.get()));
    }

    // Minecraft Additional Tags (Beacon Base)
    Builder<Block> beaconBlocks = getOrCreateBuilder(BlockTags.makeWrapperTag(new ResourceLocation(Reference.MINECRAFT_TAG, "beacon_base_blocks").toString()));
    BlockHandler.storageBlockTable.get().values().forEach(beaconBlock -> beaconBlocks.add(beaconBlock.get()));

    // Misc
    getOrCreateBuilder(BlockTags.makeWrapperTag(new ResourceLocation(Reference.MOD_ID, "misc/enigmatic_exchanger").toString()))
            .add(BlockHandler.ENIGMATIC_EXCHANGER.get());
  }
}