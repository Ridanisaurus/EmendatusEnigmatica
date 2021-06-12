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
import com.ridanisaurus.emendatusenigmatica.registries.FluidHandler;
import com.ridanisaurus.emendatusenigmatica.registries.OreHandler;
import com.ridanisaurus.emendatusenigmatica.util.*;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.MultiLayerModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockStatesAndModelsGen extends BlockStateProvider {

  public BlockStatesAndModelsGen(DataGenerator gen, ExistingFileHelper exFileHelper) {
    super(gen, Reference.MOD_ID, exFileHelper);
  }

  @Override
  protected void registerStatesAndModels() {

    for (ProcessedMaterials processedMaterial : ProcessedMaterials.values()) {
      for (Materials material : Materials.values()) {
        List<String> toCreate = Arrays.asList(material.type);

        // Storage Blocks
        if (processedMaterial == ProcessedMaterials.STORAGE_BLOCK && toCreate.contains("Block")) {
          Block block = BlockHandler.backingStorageBlockTable.get(processedMaterial, material).get();
          ResourceLocation loc = block.getRegistryName();
          simpleBlock(block,
                  models().cubeAll(material.id + "_block",
                          new ResourceLocation(Reference.MOD_ID, "blocks/" + material.id + "_block")));
        }

        // Fluid Blocks
        if (processedMaterial == ProcessedMaterials.FLUID && toCreate.contains("Fluid")) {
          FlowingFluidBlock fluidBlock = FluidHandler.fluidBlockByMaterial.get(material.id).get();
          ResourceLocation loc = fluidBlock.getRegistryName();
          simpleBlock(fluidBlock,
                  models().getBuilder(loc.getPath()).texture("particle", new ResourceLocation(Reference.MOD_ID, "fluids/fluid_still")));
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

  /*public void dynamicBlock(ResourceLocation loc, String baseTexture, String overlayTexture) {
    models().getBuilder(loc.getPath()).parent(new ModelFile.UncheckedModelFile(mcLoc("block/block"))).texture("base", baseTexture).texture("overlay", overlayTexture);
  }*/

  public void dynamicBlock(ResourceLocation loc, String baseTexture, String overlayTexture) {
    models().getBuilder(loc.getPath()).parent(new ModelFile.UncheckedModelFile(mcLoc("block/block")))
            .texture("particle", modLoc(overlayTexture))
            .transforms()
            .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
            .rotation(75F, 45F, 0F)
            .translation(0F, 2.5F, 0)
            .scale(0.375F, 0.375F, 0.375F)
            .end()
            .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
            .rotation(75F, 45F, 0F)
            .translation(0F, 2.5F, 0)
            .scale(0.375F, 0.375F, 0.375F)
            .end()
            .end()
            .customLoader(MultiLayerModelBuilder::begin)
            .submodel(RenderType.getSolid(), this.models().nested().parent(new ModelFile.UncheckedModelFile(mcLoc("block/block")))
                    .texture("base", baseTexture)
                    .element()
                    .from(0, 0, 0)
                    .to(16, 16,16)
                    .cube("#base")
                    //.allFaces((dir, uv) -> uv.uvs(0F,0.0F, 16F,16F))
                    .end()
            )
            .submodel(RenderType.getTranslucent(), this.models().nested().parent(new ModelFile.UncheckedModelFile(mcLoc("block/block")))
                    .texture("overlay", new ResourceLocation(Reference.MOD_ID, overlayTexture))
                    .element()
                    .from(0, 0, 0)
                    .to(16, 16,16)
                    .cube("#overlay")
                    //.allFaces((dir, uv) -> uv.uvs(0F,0F, 16F,16F))
                    .end()
            )
            .end();
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