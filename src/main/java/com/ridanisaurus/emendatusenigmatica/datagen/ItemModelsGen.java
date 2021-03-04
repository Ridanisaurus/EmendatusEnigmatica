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

import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
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

    for (MaterialModel material : EELoader.MATERIALS) {
      for (String processedType : material.getProcessedType()) {
        // Storage Blocks
        if (processedType.equals("storage_block")) {
          withExistingParent(EERegistrar.storageBlockMap.get(material.getId()).getId().getPath(), modLoc(material.getId() + "_block"))
                  .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "block/" + material.getId() + "_block")));
        }

        // Ingots
        if (processedType.equals("ingot")) {
          getBuilder(material.getId() + "_ingot")
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/ingot"));
        }

        // Nuggets
        if (processedType.equals("nugget")) {
          getBuilder(material.getId() + "_nugget")
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/nugget"));
        }

        // Gems
        if (processedType.equals("gem")) {
          getBuilder(material.getId() + "_gem")
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/gem"));
        }

        // Dusts
        if (processedType.equals("dust")) {
          getBuilder(material.getId() + "_dust")
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/dust"));
        }

        // Plates
        if (processedType.equals("plate")) {
          getBuilder(material.getId() + "_plate")
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/plate"));
        }

        // Gears
        if (processedType.equals("gear")) {
          getBuilder(material.getId() + "_gear")
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/gear"));
        }

        // Rods
        if (processedType.equals("rod")) {
          getBuilder(material.getId() + "_rod")
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/rod"));
        }

        // Chunks
        if (processedType.equals("chunk")) {
          getBuilder(material.getId() + "_chunk")
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/chunk_metal"));
        }
        // Cluster
        if (processedType.equals("cluster")) {
          getBuilder(material.getId() + "_cluster")
                  .parent(new ModelFile.UncheckedModelFile("item/generated"))
                  .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_cluster"));
        }
      }
    }

    // Ores
    for (MaterialModel material : EELoader.MATERIALS) {
      for (StrataModel stratum : EELoader.STRATA) {
        if (material.getProcessedType().contains("ore")) {
          withExistingParent(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).getId().getPath(), modLoc(getModelName(stratum, material)))
                  .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "block/" + getModelName(stratum, material))));
        }
      }
    }
  }

  public static String getModelName(StrataModel stratum, MaterialModel material) {
    return material.getId() + (!stratum.getId().equals("minecraft_stone") ? "_" + stratum.getSuffix() : "") + "_ore";
  }

  @Override
  public String getName() {
    return "Emendatus Enigmatica ItemModels";
  }

}