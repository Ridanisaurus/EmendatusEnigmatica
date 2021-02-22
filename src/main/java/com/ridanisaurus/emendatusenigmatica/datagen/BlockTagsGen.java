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

import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.registries.BlockHandler;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.OreHandler;
import com.ridanisaurus.emendatusenigmatica.util.Materials;
import com.ridanisaurus.emendatusenigmatica.util.ProcessedMaterials;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

import java.util.Arrays;
import java.util.List;

public class BlockTagsGen extends BlockTagsProvider {

  public BlockTagsGen(DataGenerator gen, ExistingFileHelper existingFileHelper) {
    super(gen, Reference.MOD_ID, existingFileHelper);
  }

  @Override
  protected void registerTags() {
    // Storage Blocks
    Builder<Block> forgeBlocks = getOrCreateBuilder(BlockTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks").toString()));

    for (MaterialModel material : EELoader.MATERIALS) {
      Builder<Block> blockTag = getOrCreateBuilder(BlockTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/" + material.getId()).toString()));
      forgeBlocks.add(EERegistrar.storageBlockMap.get(material.getId()).get());
      blockTag.add(EERegistrar.storageBlockMap.get(material.getId()).get());
    }

    // Ores
    Builder<Block> forgeOres = getOrCreateBuilder(BlockTags.createOptional(new ResourceLocation(Reference.FORGE_TAG, "ores")));
    for (RegistryObject<Block> ore : OreHandler.backingOreBlockTable.values()) {
      forgeOres.add(ore.get());
    }

    for (Materials material : Materials.values()) {
      List<String> toCreate = Arrays.asList(material.type);
      if (material.oreBlock != null && toCreate.contains("Ore")) {
        Builder<Block> oreTag = getOrCreateBuilder(BlockTags.createOptional(new ResourceLocation(Reference.FORGE_TAG, "ores/" + material.id)));
        for (RegistryObject<Block> strataOre : OreHandler.backingOreBlockTable.column(material).values()) {
          oreTag.add(strataOre.get());
        }
      }
    }

    // Minecraft Additional Tags (Beacon Base)
    Builder<Block> beaconBlocks = getOrCreateBuilder(BlockTags.makeWrapperTag(new ResourceLocation(Reference.MINECRAFT_TAG, "beacon_base_blocks").toString()));
    BlockHandler.backingStorageBlockTable.values().forEach(beaconBlock -> beaconBlocks.add(beaconBlock.get()));

    // Potassium Nitrate Compact
    getOrCreateBuilder(BlockTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/niter").toString()))
            .add(BlockHandler.backingStorageBlockTable.get(ProcessedMaterials.STORAGE_BLOCK, Materials.POTASSIUM_NITRATE).get());

    getOrCreateBuilder(BlockTags.makeWrapperTag(new ResourceLocation(Reference.FORGE_TAG, "storage_blocks/saltpeter").toString()))
            .add(BlockHandler.backingStorageBlockTable.get(ProcessedMaterials.STORAGE_BLOCK, Materials.POTASSIUM_NITRATE).get());

    // Misc
    getOrCreateBuilder(BlockTags.makeWrapperTag(new ResourceLocation(Reference.MOD_ID, "misc/enigmatic_fortunizer").toString()))
            .add(BlockHandler.ENIGMATIC_FORTUNIZER.get());
  }
}