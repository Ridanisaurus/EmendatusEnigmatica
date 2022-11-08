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
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.DynamicFluidContainerModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.List;

public class ItemModelsGen extends ItemModelProvider {

    public ItemModelsGen(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Reference.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (MaterialModel material : EELoader.MATERIALS) {
            List<String> processedType = material.getProcessedType();
            // Storage Blocks
            if (processedType.contains("storage_block")) {
                withExistingParent(EERegistrar.storageBlockMap.get(material.getId()).getId().getPath(), modLoc(material.getId() + "_block"))
                        .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "block/" + material.getId() + "_block")));
            }

            // Ingots
            if (processedType.contains("ingot")) {
                ItemModelBuilder parent = getBuilder(material.getId() + "_ingot").parent(new ModelFile.UncheckedModelFile("item/generated"));

                if (material.getColors().getHighlightColor() == -1) {
                    parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_ingot"));
                } else {
                    parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/ingot_0"))
                            .texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/ingot_1"))
                            .texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/ingot_2"));
                }
            }

            // Nuggets
            if (processedType.contains("nugget")) {
                ItemModelBuilder parent = getBuilder(material.getId() + "_nugget")
                        .parent(new ModelFile.UncheckedModelFile("item/generated"));
                if (material.getColors().getHighlightColor() == -1) {
                    parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_nugget"));
                } else {
                    parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/nugget_0"))
                            .texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/nugget_1"))
                            .texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/nugget_2"));
                }

            }

            // Gems
            if (processedType.contains("gem")) {
                ItemModelBuilder parent = getBuilder(material.getId() + "_gem")
                        .parent(new ModelFile.UncheckedModelFile("item/generated"));
                if (material.getColors().getHighlightColor() == -1) {
                    parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_gem"));
                } else {
                    parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/gem_0"))
                            .texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/gem_1"))
                            .texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/gem_2"));
                }
            }

            // Dusts
            if (processedType.contains("dust")) {
                ItemModelBuilder parent = getBuilder(material.getId() + "_dust")
                        .parent(new ModelFile.UncheckedModelFile("item/generated"));
                if (material.getColors().getHighlightColor() == -1) {
                    parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_dust"));
                } else {
                    parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/dust_0"))
                            .texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/dust_1"))
                            .texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/dust_2"));
                }
            }

            // Plates
            if (processedType.contains("plate")) {
                ItemModelBuilder parent = getBuilder(material.getId() + "_plate")
                        .parent(new ModelFile.UncheckedModelFile("item/generated"));
                if (material.getColors().getHighlightColor() == -1) {
                    parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_plate"));
                } else {
                    parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/plate_0"))
                            .texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/plate_1"))
                            .texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/plate_2"));
                }
            }

            // Gears
            if (processedType.contains("gear")) {
                ItemModelBuilder parent = getBuilder(material.getId() + "_gear")
                        .parent(new ModelFile.UncheckedModelFile("item/generated"));
                if (material.getColors().getHighlightColor() == -1) {
                    parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_gear"));
                } else {
                    parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/gear_0"))
                            .texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/gear_1"))
                            .texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/gear_2"));
                }
            }

            // Rods
            if (processedType.contains("rod")) {
                ItemModelBuilder parent = getBuilder(material.getId() + "_rod")
                        .parent(new ModelFile.UncheckedModelFile("item/generated"));
                if (material.getColors().getHighlightColor() == -1) {
                    parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_rod"));
                } else {
                    parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/rod_0"))
                            .texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/rod_1"))
                            .texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/rod_2"));
                }
            }

            // Raw Materials
            if (processedType.contains("raw")) {
                ItemModelBuilder parent = getBuilder("raw_" + material.getId())
                        .parent(new ModelFile.UncheckedModelFile("item/generated"));
                if (material.getColors().getHighlightColor() == -1) {
                    parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/raw_" + material.getId()));
                } else {
                    parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/raw_0"))
                            .texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/raw_1"))
                            .texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/raw_2"));
                }
                withExistingParent(EERegistrar.rawBlockMap.get(material.getId()).getId().getPath(), modLoc("raw_" + material.getId() + "_block"))
                        .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "block/raw_" + material.getId() + "_block")));
            }
            // Fluid Buckets
            if (processedType.contains("fluid")) {
                ItemModelBuilder parent = getBuilder("molten_" + material.getId() + "_bucket");
                    parent.parent(new ModelFile.UncheckedModelFile("forge:item/bucket_drip"))
                        .customLoader(DynamicFluidContainerModelBuilder::begin)
                        .applyTint(true)
                        .fluid(EERegistrar.fluidSourceMap.get(material.getId()).get());
            }
            // Ores
            for (StrataModel stratum : EELoader.STRATA) {
                if (processedType.contains("ore")) {
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
        return "Emendatus Enigmatica Item Models";
    }

}