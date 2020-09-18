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
import com.ridanisaurus.emendatusenigmatica.util.Ores;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import com.ridanisaurus.emendatusenigmatica.util.Strata;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import static com.ridanisaurus.emendatusenigmatica.datagen.BlockStatesAndModelsGen.getModelName;

public class ItemModelsGen extends ItemModelProvider {

    public ItemModelsGen(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Reference.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        // Ingots
        getBuilder("ingot_copper")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/ingot_copper"));
        getBuilder("ingot_aluminum")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/ingot_aluminum"));
        getBuilder("ingot_silver")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/ingot_silver"));
        getBuilder("ingot_lead")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/ingot_lead"));
        getBuilder("ingot_nickel")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/ingot_nickel"));
        getBuilder("ingot_uranium")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/ingot_uranium"));
        getBuilder("ingot_osmium")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/ingot_osmium"));
        getBuilder("ingot_tin")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/ingot_tin"));
        getBuilder("ingot_zinc")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/ingot_zinc"));
        getBuilder("ingot_bronze")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/ingot_bronze"));
        getBuilder("ingot_brass")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/ingot_brass"));
        getBuilder("ingot_constantan")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/ingot_constantan"));
        getBuilder("ingot_electrum")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/ingot_electrum"));
        getBuilder("ingot_steel")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/ingot_steel"));

        // Nuggets
        getBuilder("nugget_copper")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/nugget_copper"));
        getBuilder("nugget_aluminum")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/nugget_aluminum"));
        getBuilder("nugget_silver")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/nugget_silver"));
        getBuilder("nugget_lead")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/nugget_lead"));
        getBuilder("nugget_nickel")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/nugget_nickel"));
        getBuilder("nugget_uranium")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/nugget_uranium"));
        getBuilder("nugget_osmium")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/nugget_osmium"));
        getBuilder("nugget_tin")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/nugget_tin"));
        getBuilder("nugget_zinc")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/nugget_zinc"));
        getBuilder("nugget_bronze")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/nugget_bronze"));
        getBuilder("nugget_brass")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/nugget_brass"));
        getBuilder("nugget_constantan")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/nugget_constantan"));
        getBuilder("nugget_electrum")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/nugget_electrum"));
        getBuilder("nugget_steel")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/nugget_steel"));

        // Dusts
        getBuilder("dust_copper")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_copper"));
        getBuilder("dust_aluminum")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_aluminum"));
        getBuilder("dust_silver")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_silver"));
        getBuilder("dust_lead")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_lead"));
        getBuilder("dust_nickel")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_nickel"));
        getBuilder("dust_uranium")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_uranium"));
        getBuilder("dust_osmium")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_osmium"));
        getBuilder("dust_tin")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_tin"));
        getBuilder("dust_zinc")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_zinc"));
        getBuilder("dust_bronze")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_bronze"));
        getBuilder("dust_brass")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_brass"));
        getBuilder("dust_constantan")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_constantan"));
        getBuilder("dust_electrum")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_electrum"));
        getBuilder("dust_steel")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_steel"));
        getBuilder("dust_charcoal")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_charcoal"));
        getBuilder("dust_coal")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_coal"));
        getBuilder("dust_diamond")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_diamond"));
        getBuilder("dust_emerald")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_emerald"));
        getBuilder("dust_gold")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_gold"));
        getBuilder("dust_iron")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_iron"));
        getBuilder("dust_lapis")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_lapis"));
        getBuilder("dust_obsidian")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_obsidian"));
        getBuilder("dust_quartz")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_quartz"));
        getBuilder("dust_certus_quartz")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_certus_quartz"));
        getBuilder("dust_fluix")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_fluix"));
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
        getBuilder("dust_fluorite")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/dust_fluorite"));

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

        // Plates
        getBuilder("plate_copper")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/plate_copper"));
        getBuilder("plate_aluminum")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/plate_aluminum"));
        getBuilder("plate_silver")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/plate_silver"));
        getBuilder("plate_lead")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/plate_lead"));
        getBuilder("plate_nickel")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/plate_nickel"));
        getBuilder("plate_uranium")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/plate_uranium"));
        getBuilder("plate_osmium")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/plate_osmium"));
        getBuilder("plate_tin")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/plate_tin"));
        getBuilder("plate_zinc")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/plate_zinc"));
        getBuilder("plate_bronze")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/plate_bronze"));
        getBuilder("plate_brass")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/plate_brass"));
        getBuilder("plate_constantan")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/plate_constantan"));
        getBuilder("plate_electrum")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/plate_electrum"));
        getBuilder("plate_steel")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/plate_steel"));
        getBuilder("plate_gold")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/plate_gold"));
        getBuilder("plate_iron")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/plate_iron"));
        getBuilder("plate_lapis")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/plate_lapis"));

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

        // Block Items
        withExistingParent(BlockHandler.BLOCK_COPPER.getId().getPath(), modLoc("block_copper"))
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "block/block_copper")));
        withExistingParent(BlockHandler.BLOCK_ALUMINUM.getId().getPath(), modLoc("block_aluminum"))
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "block/block_aluminum")));
        withExistingParent(BlockHandler.BLOCK_SILVER.getId().getPath(), modLoc("block_silver"))
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "block/block_silver")));
        withExistingParent(BlockHandler.BLOCK_LEAD.getId().getPath(), modLoc("block_lead"))
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "block/block_lead")));
        withExistingParent(BlockHandler.BLOCK_NICKEL.getId().getPath(), modLoc("block_nickel"))
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "block/block_nickel")));
        withExistingParent(BlockHandler.BLOCK_URANIUM.getId().getPath(), modLoc("block_uranium"))
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "block/block_uranium")));
        withExistingParent(BlockHandler.BLOCK_OSMIUM.getId().getPath(), modLoc("block_osmium"))
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "block/block_osmium")));
        withExistingParent(BlockHandler.BLOCK_TIN.getId().getPath(), modLoc("block_tin"))
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "block/block_tin")));
        withExistingParent(BlockHandler.BLOCK_ZINC.getId().getPath(), modLoc("block_zinc"))
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "block/block_zinc")));
        withExistingParent(BlockHandler.BLOCK_BRONZE.getId().getPath(), modLoc("block_bronze"))
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "block/block_bronze")));
        withExistingParent(BlockHandler.BLOCK_BRASS.getId().getPath(), modLoc("block_brass"))
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "block/block_brass")));
        withExistingParent(BlockHandler.BLOCK_CONSTANTAN.getId().getPath(), modLoc("block_constantan"))
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "block/block_constantan")));
        withExistingParent(BlockHandler.BLOCK_ELECTRUM.getId().getPath(), modLoc("block_electrum"))
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "block/block_electrum")));
        withExistingParent(BlockHandler.BLOCK_STEEL.getId().getPath(), modLoc("block_steel"))
                .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "block/block_steel")));

        // Ore Items
        for (Strata stratum : Strata.values()) {
            for (Ores ore : Ores.values()) {
                withExistingParent(BlockHandler.oreBlockTable.get().get(stratum, ore).getId().getPath(), modLoc(getModelName(stratum, ore)))
                        .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID, "block/" + getModelName(stratum, ore))));

            }
        }
    }

    @Override
    public String getName() {
        return "Emendatus Enigmatica ItemModels";
    }

}

