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
    getBuilder("charcoal_dust")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/charcoal_dust"));
    getBuilder("obsidian_dust")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/obsidian_dust"));
    getBuilder("ender_dust")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/ender_dust"));
    getBuilder("graphite_dust")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/graphite_dust"));
    getBuilder("lithium_dust")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/lithium_dust"));
    getBuilder("wood_dust")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/wood_dust"));

    for (ProcessedMaterials processedMaterial : ProcessedMaterials.values()) {
      for (Materials material : Materials.values()) {
        List<String> toCreate = Arrays.asList(material.type);
        // Storage Blocks Items
        if (processedMaterial == ProcessedMaterials.STORAGE_BLOCK && toCreate.contains("Block")) {
          withExistingParent(BlockHandler.backingStorageBlockTable.get(processedMaterial, material).getId().getPath(), modLoc(material.id + "_block"))
                  .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "block/" + material.id + "_block")));
        }
        // Ingots
        if (processedMaterial == ProcessedMaterials.INGOT && toCreate.contains("Ingot")) {
          getBuilder(material.id + "_ingot")
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.id + "_ingot"));
        }
        // Gems
        if (processedMaterial == ProcessedMaterials.GEM && toCreate.contains("Gem")) {
          getBuilder(material.id + "_gem")
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.id + "_gem"));
        }
        // Nuggets
        if (processedMaterial == ProcessedMaterials.NUGGET && toCreate.contains("Nugget")) {
          getBuilder(material.id + "_nugget")
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.id + "_nugget"));
        }
        // Dusts
        if (processedMaterial == ProcessedMaterials.DUST && toCreate.contains("Dust")) {
          getBuilder(material.id + "_dust")
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.id + "_dust"));
        }
        // Plates
        if (processedMaterial == ProcessedMaterials.PLATE && toCreate.contains("Plate")) {
          getBuilder(material.id + "_plate")
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.id + "_plate"));
        }
        // Gears
        if (processedMaterial == ProcessedMaterials.GEAR && toCreate.contains("Gear")) {
          getBuilder(material.id + "_gear")
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.id + "_gear"));
        }
        // Rods
        if (processedMaterial == ProcessedMaterials.ROD && toCreate.contains("Rod")) {
          getBuilder(material.id + "_rod")
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.id + "_rod"));
        }
        // Chunks
        if (processedMaterial == ProcessedMaterials.CHUNK && toCreate.contains("Chunk")) {
          getBuilder(material.id + "_chunk")
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.id + "_chunk"));
        }
        // Clusters
        if (processedMaterial == ProcessedMaterials.CLUSTER && toCreate.contains("Cluster")) {
          getBuilder(material.id + "_cluster")
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.id + "_cluster"));
        }
        // Clumps
        if (processedMaterial == ProcessedMaterials.CLUMP && toCreate.contains("Clump")) {
          getBuilder(material.id + "_clump")
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.id + "_clump"));
        }
        // Crystals
        if (processedMaterial == ProcessedMaterials.CRYSTAL && toCreate.contains("Crystal")) {
          getBuilder(material.id + "_crystal")
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.id + "_crystal"));
        }
        // Dirty Dusts
        if (processedMaterial == ProcessedMaterials.DIRTY_DUST && toCreate.contains("DirtyDust")) {
          getBuilder(material.id + "_dirty_dust")
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.id + "_dirty_dust"));
        }
        // Shards
        if (processedMaterial == ProcessedMaterials.SHARD && toCreate.contains("Shard")) {
          getBuilder(material.id + "_shard")
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.id + "_shard"));
        }
      }
    }
    // Ore Items
    for (Strata stratum : Strata.values()) {
      for (Materials material : Materials.values()) {
        List<String> toCreate = Arrays.asList(material.type);
        if (material.oreBlock != null && toCreate.contains("Ore")) {
          withExistingParent(OreHandler.backingOreBlockTable.get(stratum, material).getId().getPath(), modLoc(getModelName(stratum, material)))
                  .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "block/" + getModelName(stratum, material))));
        }

      }
    }
  }

  public static String getModelName(Strata stratum, Materials material) {
    return material.id + (stratum != Strata.STONE ? "_" + stratum.suffix : "") + "_ore";
  }

  @Override
  public String getName() {
    return "Emendatus Enigmatica ItemModels";
  }

}