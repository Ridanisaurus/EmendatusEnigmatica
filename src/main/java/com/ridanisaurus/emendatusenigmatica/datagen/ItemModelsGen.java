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

    // Chunks
    getBuilder("chunk_coal")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/chunk_coal"));
    getBuilder("chunk_iron")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/chunk_iron"));
    getBuilder("chunk_gold")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/chunk_gold"));
    getBuilder("chunk_diamond")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/chunk_diamond"));
    getBuilder("chunk_emerald")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/chunk_emerald"));
    getBuilder("chunk_lapis")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/chunk_lapis"));
    getBuilder("chunk_redstone")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/chunk_redstone"));
    getBuilder("chunk_copper")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/chunk_copper"));
    getBuilder("chunk_aluminum")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/chunk_aluminum"));
    getBuilder("chunk_silver")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/chunk_silver"));
    getBuilder("chunk_lead")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/chunk_lead"));
    getBuilder("chunk_nickel")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/chunk_nickel"));
    getBuilder("chunk_uranium")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/chunk_uranium"));
    getBuilder("chunk_uranium")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/chunk_uranium"));
    getBuilder("chunk_osmium")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/chunk_osmium"));
    getBuilder("chunk_tin")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/chunk_tin"));
    getBuilder("chunk_zinc")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/chunk_zinc"));
    getBuilder("chunk_certus_quartz")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/chunk_certus_quartz"));
    getBuilder("chunk_charged_certus_quartz")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/chunk_charged_certus_quartz"));
    getBuilder("chunk_fluorite")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/chunk_fluorite"));
    getBuilder("chunk_bitumen")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/chunk_bitumen"));

    // Gems
    getBuilder("gem_certus_quartz")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/gem_certus_quartz"));
    getBuilder("gem_charged_certus_quartz")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/gem_charged_certus_quartz"));
    getBuilder("gem_fluix")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/gem_fluix"));
    getBuilder("gem_fluorite")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/gem_fluorite"));
    getBuilder("gem_bitumen")
            .parent(new ModelFile.UncheckedModelFile("item/generated"))
            .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/gem_bitumen"));

    for (ProcessedMaterials processedMaterial : ProcessedMaterials.values()) {
      for (Materials material : Materials.values()) {
        // Storage Blocks Items
        if (processedMaterial == ProcessedMaterials.STORAGE_BLOCK && !material.isVanilla()) {
          withExistingParent(BlockHandler.storageBlockTable.get().get(processedMaterial, material).getId().getPath(), modLoc("block_" + material.id))
                  .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "block/block_" + material.id)));
        }

        // Ingots
        if (processedMaterial == ProcessedMaterials.INGOT && !material.isGem() && !material.isVanilla()) {
          getBuilder("ingot_" + material.id)
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/ingot_" + material.id));
        }

        /*// Gems
        if (processedMaterial == ProcessedMaterials.GEM && material.isGem() && !material.isVanilla()) {
          withExistingParent(ItemHandler.itemTable.get().get(processedMaterial, material).getId().getPath(), modLoc("gem_" + material.id))
                  .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "item/gem_" + material.id)));
        }*/

        // Nuggets
        if (processedMaterial == ProcessedMaterials.NUGGET && !material.isGem() && !material.isVanilla()) {
          getBuilder("nugget_" + material.id)
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/nugget_" + material.id));
        }

        // Dusts
        if (processedMaterial == ProcessedMaterials.DUST && material != Materials.REDSTONE && material != Materials.BITUMEN && material != Materials.CHARGED_CERTUS_QUARTZ) {
          getBuilder("dust_" + material.id)
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_" + material.id));
        }

        // Plates
        if (processedMaterial == ProcessedMaterials.PLATE && material != Materials.REDSTONE && material != Materials.COAL && !material.isGem()) {
          getBuilder("plate_" + material.id)
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/plate_" + material.id));
        }

      }
    }

    // Ore Items
    for (Strata stratum : Strata.values()) {
      for (Ores ore : Ores.values()) {
        withExistingParent(OreHandler.oreBlockTable.get().get(stratum, ore).getId().getPath(), modLoc(getModelName(stratum, ore)))
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "block/" + getModelName(stratum, ore))));

      }
    }
  }

  public static String getModelName(Strata stratum, Ores ore) {
    return "ore_" + ore.id + (stratum != Strata.STONE ? "_" + stratum.suffix : "");
  }

  @Override
  public String getName() {
    return "Emendatus Enigmatica ItemModels";
  }

}