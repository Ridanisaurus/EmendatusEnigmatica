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
import com.ridanisaurus.emendatusenigmatica.util.Ores;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import com.ridanisaurus.emendatusenigmatica.util.Strata;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStatesAndModelsGen extends BlockStateProvider {

  public BlockStatesAndModelsGen(DataGenerator gen, ExistingFileHelper exFileHelper) {
    super(gen, Reference.MOD_ID, exFileHelper);
  }

  @Override
  protected void registerStatesAndModels() {

    // Storage Blocks
    simpleBlock(BlockHandler.BLOCK_COPPER.get(), models().cubeAll("block_copper", new ResourceLocation(Reference.MOD_ID, "blocks/block_copper")));
    simpleBlock(BlockHandler.BLOCK_ALUMINUM.get(), models().cubeAll("block_aluminum", new ResourceLocation(Reference.MOD_ID, "blocks/block_aluminum")));
    simpleBlock(BlockHandler.BLOCK_SILVER.get(), models().cubeAll("block_silver", new ResourceLocation(Reference.MOD_ID, "blocks/block_silver")));
    simpleBlock(BlockHandler.BLOCK_LEAD.get(), models().cubeAll("block_lead", new ResourceLocation(Reference.MOD_ID, "blocks/block_lead")));
    simpleBlock(BlockHandler.BLOCK_NICKEL.get(), models().cubeAll("block_nickel", new ResourceLocation(Reference.MOD_ID, "blocks/block_nickel")));
    simpleBlock(BlockHandler.BLOCK_URANIUM.get(), models().cubeAll("block_uranium", new ResourceLocation(Reference.MOD_ID, "blocks/block_uranium")));
    simpleBlock(BlockHandler.BLOCK_OSMIUM.get(), models().cubeAll("block_osmium", new ResourceLocation(Reference.MOD_ID, "blocks/block_osmium")));
    simpleBlock(BlockHandler.BLOCK_TIN.get(), models().cubeAll("block_tin", new ResourceLocation(Reference.MOD_ID, "blocks/block_tin")));
    simpleBlock(BlockHandler.BLOCK_ZINC.get(), models().cubeAll("block_zinc", new ResourceLocation(Reference.MOD_ID, "blocks/block_zinc")));
    simpleBlock(BlockHandler.BLOCK_BRONZE.get(), models().cubeAll("block_bronze", new ResourceLocation(Reference.MOD_ID, "blocks/block_bronze")));
    simpleBlock(BlockHandler.BLOCK_BRASS.get(), models().cubeAll("block_brass", new ResourceLocation(Reference.MOD_ID, "blocks/block_brass")));
    simpleBlock(BlockHandler.BLOCK_CONSTANTAN.get(), models().cubeAll("block_constantan", new ResourceLocation(Reference.MOD_ID, "blocks/block_constantan")));
    simpleBlock(BlockHandler.BLOCK_ELECTRUM.get(), models().cubeAll("block_electrum", new ResourceLocation(Reference.MOD_ID, "blocks/block_electrum")));
    simpleBlock(BlockHandler.BLOCK_STEEL.get(), models().cubeAll("block_steel", new ResourceLocation(Reference.MOD_ID, "blocks/block_steel")));

    // Ores
    for (Strata stratum : Strata.values()) {
      for (Ores ore : Ores.values()) {
        Block block = OreHandler.oreBlockTable.get().get(stratum, ore).get();
        ResourceLocation loc = block.getRegistryName();
        dynamicBlock(loc, getBaseTexture(stratum), getOverlayTexture(ore));
        simpleBlock(block, new ModelFile.UncheckedModelFile(modLoc("block/" + loc.toString().split(":")[1])));
      }
    }
  }

  public void dynamicBlock(ResourceLocation loc, String baseTexture, String overlayTexture) {
    models().getBuilder(loc.getPath()).parent(new ModelFile.UncheckedModelFile(modLoc("block/cube_overlayered"))).texture("base", baseTexture).texture("overlay", overlayTexture);
  }

  public static String getModelName(Strata stratum, Ores ore) {
    return "ore_" + ore.id + (stratum != Strata.STONE ? "_" + stratum.suffix : "");
  }

  public static String getBaseTexture(Strata stratum) {
    return stratum.baseTexture;
  }

  public static String getOverlayTexture(Ores ore) {
    return "blocks/overlays/" + ore.id;
  }

  @Override
  public String getName() {
    return "Emendatus Enigmatica BlockModels and BlockStates";
  }
}