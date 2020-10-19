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
import com.ridanisaurus.emendatusenigmatica.registries.ItemHandler;
import com.ridanisaurus.emendatusenigmatica.registries.OreHandler;
import com.ridanisaurus.emendatusenigmatica.util.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Arrays;
import java.util.List;

public class ItemModelsGen extends ItemModelProvider {

  public ItemModelsGen(DataGenerator generator, ExistingFileHelper existingFileHelper) {
    super(generator, Reference.MOD_ID, existingFileHelper);
  }

  @Override
  protected void registerModels() {
    // Dusts
    getBuilder("dust_charcoal")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_charcoal"));
    getBuilder("dust_obsidian")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_obsidian"));
    getBuilder("dust_ender")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_ender"));
    getBuilder("dust_coke")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_coke"));
    getBuilder("dust_graphite")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_graphite"));
    getBuilder("dust_lithium")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_lithium"));
    getBuilder("dust_saltpeter")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_saltpeter"));
    getBuilder("dust_sulfur")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_sulfur"));
    getBuilder("dust_quartz")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_quartz"));

    for (ProcessedMaterials processedMaterial : ProcessedMaterials.values()) {
      for (Materials material : Materials.values()) {
        List<String> toCreate = Arrays.asList(material.type);
        // Storage Blocks Items
        if (processedMaterial == ProcessedMaterials.STORAGE_BLOCK && toCreate.contains("Block")) {
          withExistingParent(BlockHandler.storageBlockTable.get().get(processedMaterial, material).getId().getPath(), modLoc("block_" + material.id))
                  .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "block/block_" + material.id)));
        }
        // Ingots
        if (processedMaterial == ProcessedMaterials.INGOT && toCreate.contains("Ingot")) {
          getBuilder("ingot_" + material.id)
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/ingot_" + material.id));
        }
        // Gems
        if (processedMaterial == ProcessedMaterials.GEM && toCreate.contains("Gem")) {
          getBuilder("gem_" + material.id)
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/gem_" + material.id));
        }
        // Nuggets
        if (processedMaterial == ProcessedMaterials.NUGGET && toCreate.contains("Nugget")) {
          getBuilder("nugget_" + material.id)
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/nugget_" + material.id));
        }
        // Dusts
        if (processedMaterial == ProcessedMaterials.DUST && toCreate.contains("Dust")) {
          getBuilder("dust_" + material.id)
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_" + material.id));
        }
        // Plates
        if (processedMaterial == ProcessedMaterials.PLATE && toCreate.contains("Plate")) {
          getBuilder("plate_" + material.id)
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/plate_" + material.id));
        }
        // Chunks
        if (processedMaterial == ProcessedMaterials.CHUNK && toCreate.contains("Chunk")) {
          getBuilder("chunk_" + material.id)
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/chunk_" + material.id));
        }
      }
    }
    // Ore Items
    for (Strata stratum : Strata.values()) {
      for (Materials material : Materials.values()) {
        List<String> toCreate = Arrays.asList(material.type);
        if (material.oreBlock != null && toCreate.contains("Ore")) {
          withExistingParent(OreHandler.oreBlockTable.get().get(stratum, material).getId().getPath(), modLoc(getModelName(stratum, material)))
                  .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "block/" + getModelName(stratum, material))));
        }

      }
    }
  }

  public static String getModelName(Strata stratum, Materials material) {
    return "ore_" + material.id + (stratum != Strata.STONE ? "_" + stratum.suffix : "");
  }

  @Override
  public String getName() {
    return "Emendatus Enigmatica ItemModels";
  }

}