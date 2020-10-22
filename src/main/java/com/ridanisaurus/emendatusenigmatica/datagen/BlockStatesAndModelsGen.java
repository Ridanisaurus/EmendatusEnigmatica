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

package com.ridanisaurus.emendatusenigmatica.datagen;

import com.ridanisaurus.emendatusenigmatica.registries.BlockHandler;
import com.ridanisaurus.emendatusenigmatica.registries.OreHandler;
import com.ridanisaurus.emendatusenigmatica.util.*;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Arrays;
import java.util.List;

public class BlockStatesAndModelsGen extends BlockStateProvider {

  public BlockStatesAndModelsGen(DataGenerator gen, ExistingFileHelper exFileHelper) {
    super(gen, Reference.MOD_ID, exFileHelper);
  }

  @Override
  protected void registerStatesAndModels() {

    // Storage Blocks
    for (ProcessedMaterials processedMaterial : ProcessedMaterials.values()) {
      for (Materials material : Materials.values()) {
        List<String> toCreate = Arrays.asList(material.type);
        if (processedMaterial == ProcessedMaterials.STORAGE_BLOCK && toCreate.contains("Block")) {
          Block block = BlockHandler.backingStorageBlockTable.get(processedMaterial, material).get();
          ResourceLocation loc = block.getRegistryName();
          simpleBlock(BlockHandler.backingStorageBlockTable.get(processedMaterial, material).get(),
                  models().cubeAll(material.id + "_block",
                          new ResourceLocation(Reference.MOD_ID, "blocks/" + material.id + "_block")));
        }
      }
    }

    // Ores
    for (Strata stratum : Strata.values()) {
      for (Materials material : Materials.values()) {
        List<String> toCreate = Arrays.asList(material.type);
        if (material.oreBlock != null && toCreate.contains("Ore")) {
          Block block = OreHandler.backingOreBlockTable.get(stratum, material).get();
          ResourceLocation loc = block.getRegistryName();
          dynamicBlock(loc, getBaseTexture(stratum), getOverlayTexture(material));
          simpleBlock(block, new ModelFile.UncheckedModelFile(modLoc("block/" + loc.toString().split(":")[1])));
        }
      }
    }
  }

  public void dynamicBlock(ResourceLocation loc, String baseTexture, String overlayTexture) {
    models().getBuilder(loc.getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("block/cube_overlayered"))).texture("base", baseTexture).texture("overlay", overlayTexture);
  }

  public static String getBaseTexture(Strata stratum) {
    return stratum.baseTexture;
  }

  public static String getOverlayTexture(Materials material) {
    return "blocks/overlays/" + material.id;
  }

  @Override
  public String getName() {
    return "Emendatus Enigmatica BlockModels and BlockStates";
  }
}