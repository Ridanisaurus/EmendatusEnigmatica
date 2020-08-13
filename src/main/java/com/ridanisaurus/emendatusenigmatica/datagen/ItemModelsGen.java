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
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;

import java.util.Objects;

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
    withExistingParent(BlockHandler.BLOCK_COPPER.get().getRegistryName().getPath(), modLoc("block_copper"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/block_copper")));
    withExistingParent(BlockHandler.BLOCK_ALUMINUM.get().getRegistryName().getPath(), modLoc("block_aluminum"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/block_aluminum")));
    withExistingParent(BlockHandler.BLOCK_SILVER.get().getRegistryName().getPath(), modLoc("block_silver"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/block_silver")));
    withExistingParent(BlockHandler.BLOCK_LEAD.get().getRegistryName().getPath(), modLoc("block_lead"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/block_lead")));
    withExistingParent(BlockHandler.BLOCK_NICKEL.get().getRegistryName().getPath(), modLoc("block_nickel"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/block_nickel")));
    withExistingParent(BlockHandler.BLOCK_URANIUM.get().getRegistryName().getPath(), modLoc("block_uranium"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/block_uranium")));
    withExistingParent(BlockHandler.BLOCK_OSMIUM.get().getRegistryName().getPath(), modLoc("block_osmium"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/block_osmium")));
    withExistingParent(BlockHandler.BLOCK_TIN.get().getRegistryName().getPath(), modLoc("block_tin"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/block_tin")));
    withExistingParent(BlockHandler.BLOCK_ZINC.get().getRegistryName().getPath(), modLoc("block_zinc"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/block_zinc")));
    withExistingParent(BlockHandler.BLOCK_BRONZE.get().getRegistryName().getPath(), modLoc("block_bronze"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/block_bronze")));
    withExistingParent(BlockHandler.BLOCK_BRASS.get().getRegistryName().getPath(), modLoc("block_brass"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/block_brass")));
    withExistingParent(BlockHandler.BLOCK_CONSTANTAN.get().getRegistryName().getPath(), modLoc("block_constantan"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/block_constantan")));
    withExistingParent(BlockHandler.BLOCK_ELECTRUM.get().getRegistryName().getPath(), modLoc("block_electrum"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/block_electrum")));
    withExistingParent(BlockHandler.BLOCK_STEEL.get().getRegistryName().getPath(), modLoc("block_steel"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/block_steel")));

    // Ore Items
    withExistingParent(BlockHandler.ORE_COAL.get().getRegistryName().getPath(), modLoc("ore_coal"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_coal")));
    withExistingParent(BlockHandler.ORE_IRON.get().getRegistryName().getPath(), modLoc("ore_iron"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_iron")));
    withExistingParent(BlockHandler.ORE_GOLD.get().getRegistryName().getPath(), modLoc("ore_gold"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_gold")));
    withExistingParent(BlockHandler.ORE_DIAMOND.get().getRegistryName().getPath(), modLoc("ore_diamond"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_diamond")));
    withExistingParent(BlockHandler.ORE_EMERALD.get().getRegistryName().getPath(), modLoc("ore_emerald"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_emerald")));
    withExistingParent(BlockHandler.ORE_LAPIS.get().getRegistryName().getPath(), modLoc("ore_lapis"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_lapis")));
    withExistingParent(BlockHandler.ORE_REDSTONE.get().getRegistryName().getPath(), modLoc("ore_redstone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_redstone")));
    withExistingParent(BlockHandler.ORE_COPPER.get().getRegistryName().getPath(), modLoc("ore_copper"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_copper")));
    withExistingParent(BlockHandler.ORE_ALUMINUM.get().getRegistryName().getPath(), modLoc("ore_aluminum"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_aluminum")));
    withExistingParent(BlockHandler.ORE_SILVER.get().getRegistryName().getPath(), modLoc("ore_silver"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_silver")));
    withExistingParent(BlockHandler.ORE_LEAD.get().getRegistryName().getPath(), modLoc("ore_lead"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_lead")));
    withExistingParent(BlockHandler.ORE_NICKEL.get().getRegistryName().getPath(), modLoc("ore_nickel"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_nickel")));
    withExistingParent(BlockHandler.ORE_URANIUM.get().getRegistryName().getPath(), modLoc("ore_uranium"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_uranium")));
    withExistingParent(BlockHandler.ORE_OSMIUM.get().getRegistryName().getPath(), modLoc("ore_osmium"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_osmium")));
    withExistingParent(BlockHandler.ORE_TIN.get().getRegistryName().getPath(), modLoc("ore_tin"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_tin")));
    withExistingParent(BlockHandler.ORE_ZINC.get().getRegistryName().getPath(), modLoc("ore_zinc"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_zinc")));
    withExistingParent(BlockHandler.ORE_CERTUS_QUARTZ.get().getRegistryName().getPath(), modLoc("ore_certus_quartz"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_certus_quartz")));
    withExistingParent(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ.get().getRegistryName().getPath(), modLoc("ore_charged_certus_quartz"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_charged_certus_quartz")));
    withExistingParent(BlockHandler.ORE_FLUORITE.get().getRegistryName().getPath(), modLoc("ore_fluorite"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_fluorite")));

    // Strata Ore Items - Andesite
    withExistingParent(BlockHandler.ORE_COAL_ANDESITE.get().getRegistryName().getPath(), modLoc("ore_coal_andesite"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_coal_andesite")));
    withExistingParent(BlockHandler.ORE_IRON_ANDESITE.get().getRegistryName().getPath(), modLoc("ore_iron_andesite"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_iron_andesite")));
    withExistingParent(BlockHandler.ORE_GOLD_ANDESITE.get().getRegistryName().getPath(), modLoc("ore_gold_andesite"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_gold_andesite")));
    withExistingParent(BlockHandler.ORE_DIAMOND_ANDESITE.get().getRegistryName().getPath(), modLoc("ore_diamond_andesite"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_diamond_andesite")));
    withExistingParent(BlockHandler.ORE_EMERALD_ANDESITE.get().getRegistryName().getPath(), modLoc("ore_emerald_andesite"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_emerald_andesite")));
    withExistingParent(BlockHandler.ORE_LAPIS_ANDESITE.get().getRegistryName().getPath(), modLoc("ore_lapis_andesite"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_lapis_andesite")));
    withExistingParent(BlockHandler.ORE_REDSTONE_ANDESITE.get().getRegistryName().getPath(), modLoc("ore_redstone_andesite"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_redstone_andesite")));
    withExistingParent(BlockHandler.ORE_COPPER_ANDESITE.get().getRegistryName().getPath(), modLoc("ore_copper_andesite"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_copper_andesite")));
    withExistingParent(BlockHandler.ORE_ALUMINUM_ANDESITE.get().getRegistryName().getPath(), modLoc("ore_aluminum_andesite"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_aluminum_andesite")));
    withExistingParent(BlockHandler.ORE_SILVER_ANDESITE.get().getRegistryName().getPath(), modLoc("ore_silver_andesite"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_silver_andesite")));
    withExistingParent(BlockHandler.ORE_LEAD_ANDESITE.get().getRegistryName().getPath(), modLoc("ore_lead_andesite"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_lead_andesite")));
    withExistingParent(BlockHandler.ORE_NICKEL_ANDESITE.get().getRegistryName().getPath(), modLoc("ore_nickel_andesite"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_nickel_andesite")));
    withExistingParent(BlockHandler.ORE_URANIUM_ANDESITE.get().getRegistryName().getPath(), modLoc("ore_uranium_andesite"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_uranium_andesite")));
    withExistingParent(BlockHandler.ORE_OSMIUM_ANDESITE.get().getRegistryName().getPath(), modLoc("ore_osmium_andesite"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_osmium_andesite")));
    withExistingParent(BlockHandler.ORE_TIN_ANDESITE.get().getRegistryName().getPath(), modLoc("ore_tin_andesite"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_tin_andesite")));
    withExistingParent(BlockHandler.ORE_ZINC_ANDESITE.get().getRegistryName().getPath(), modLoc("ore_zinc_andesite"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_zinc_andesite")));
    withExistingParent(BlockHandler.ORE_CERTUS_QUARTZ_ANDESITE.get().getRegistryName().getPath(), modLoc("ore_certus_quartz_andesite"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_certus_quartz_andesite")));
    withExistingParent(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_ANDESITE.get().getRegistryName().getPath(), modLoc("ore_charged_certus_quartz_andesite"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_charged_certus_quartz_andesite")));
    withExistingParent(BlockHandler.ORE_FLUORITE_ANDESITE.get().getRegistryName().getPath(), modLoc("ore_fluorite_andesite"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_fluorite_andesite")));

    // Strata Ore Items - Gabbro
    withExistingParent(BlockHandler.ORE_COAL_GABBRO.get().getRegistryName().getPath(), modLoc("ore_coal_gabbro"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_coal_gabbro")));
    withExistingParent(BlockHandler.ORE_IRON_GABBRO.get().getRegistryName().getPath(), modLoc("ore_iron_gabbro"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_iron_gabbro")));
    withExistingParent(BlockHandler.ORE_GOLD_GABBRO.get().getRegistryName().getPath(), modLoc("ore_gold_gabbro"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_gold_gabbro")));
    withExistingParent(BlockHandler.ORE_DIAMOND_GABBRO.get().getRegistryName().getPath(), modLoc("ore_diamond_gabbro"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_diamond_gabbro")));
    withExistingParent(BlockHandler.ORE_EMERALD_GABBRO.get().getRegistryName().getPath(), modLoc("ore_emerald_gabbro"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_emerald_gabbro")));
    withExistingParent(BlockHandler.ORE_LAPIS_GABBRO.get().getRegistryName().getPath(), modLoc("ore_lapis_gabbro"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_lapis_gabbro")));
    withExistingParent(BlockHandler.ORE_REDSTONE_GABBRO.get().getRegistryName().getPath(), modLoc("ore_redstone_gabbro"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_redstone_gabbro")));
    withExistingParent(BlockHandler.ORE_COPPER_GABBRO.get().getRegistryName().getPath(), modLoc("ore_copper_gabbro"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_copper_gabbro")));
    withExistingParent(BlockHandler.ORE_ALUMINUM_GABBRO.get().getRegistryName().getPath(), modLoc("ore_aluminum_gabbro"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_aluminum_gabbro")));
    withExistingParent(BlockHandler.ORE_SILVER_GABBRO.get().getRegistryName().getPath(), modLoc("ore_silver_gabbro"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_silver_gabbro")));
    withExistingParent(BlockHandler.ORE_LEAD_GABBRO.get().getRegistryName().getPath(), modLoc("ore_lead_gabbro"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_lead_gabbro")));
    withExistingParent(BlockHandler.ORE_NICKEL_GABBRO.get().getRegistryName().getPath(), modLoc("ore_nickel_gabbro"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_nickel_gabbro")));
    withExistingParent(BlockHandler.ORE_URANIUM_GABBRO.get().getRegistryName().getPath(), modLoc("ore_uranium_gabbro"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_uranium_gabbro")));
    withExistingParent(BlockHandler.ORE_OSMIUM_GABBRO.get().getRegistryName().getPath(), modLoc("ore_osmium_gabbro"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_osmium_gabbro")));
    withExistingParent(BlockHandler.ORE_TIN_GABBRO.get().getRegistryName().getPath(), modLoc("ore_tin_gabbro"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_tin_gabbro")));
    withExistingParent(BlockHandler.ORE_ZINC_GABBRO.get().getRegistryName().getPath(), modLoc("ore_zinc_gabbro"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_zinc_gabbro")));
    withExistingParent(BlockHandler.ORE_CERTUS_QUARTZ_GABBRO.get().getRegistryName().getPath(), modLoc("ore_certus_quartz_gabbro"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_certus_quartz_gabbro")));
    withExistingParent(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_GABBRO.get().getRegistryName().getPath(), modLoc("ore_charged_certus_quartz_gabbro"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_charged_certus_quartz_gabbro")));
    withExistingParent(BlockHandler.ORE_FLUORITE_GABBRO.get().getRegistryName().getPath(), modLoc("ore_fluorite_gabbro"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_fluorite_gabbro")));

    // Strata Ore Items - Limestone (Create)
    withExistingParent(Objects.requireNonNull(BlockHandler.ORE_COAL_C_LIMESTONE.get().getRegistryName()).getPath(), modLoc("ore_coal_c_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_coal_c_limestone")));
    withExistingParent(BlockHandler.ORE_IRON_C_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_iron_c_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_iron_c_limestone")));
    withExistingParent(BlockHandler.ORE_GOLD_C_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_gold_c_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_gold_c_limestone")));
    withExistingParent(BlockHandler.ORE_DIAMOND_C_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_diamond_c_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_diamond_c_limestone")));
    withExistingParent(BlockHandler.ORE_EMERALD_C_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_emerald_c_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_emerald_c_limestone")));
    withExistingParent(BlockHandler.ORE_LAPIS_C_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_lapis_c_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_lapis_c_limestone")));
    withExistingParent(BlockHandler.ORE_REDSTONE_C_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_redstone_c_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_redstone_c_limestone")));
    withExistingParent(BlockHandler.ORE_COPPER_C_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_copper_c_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_copper_c_limestone")));
    withExistingParent(BlockHandler.ORE_ALUMINUM_C_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_aluminum_c_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_aluminum_c_limestone")));
    withExistingParent(BlockHandler.ORE_SILVER_C_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_silver_c_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_silver_c_limestone")));
    withExistingParent(BlockHandler.ORE_LEAD_C_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_lead_c_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_lead_c_limestone")));
    withExistingParent(BlockHandler.ORE_NICKEL_C_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_nickel_c_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_nickel_c_limestone")));
    withExistingParent(BlockHandler.ORE_URANIUM_C_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_uranium_c_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_uranium_c_limestone")));
    withExistingParent(BlockHandler.ORE_OSMIUM_C_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_osmium_c_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_osmium_c_limestone")));
    withExistingParent(BlockHandler.ORE_TIN_C_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_tin_c_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_tin_c_limestone")));
    withExistingParent(BlockHandler.ORE_ZINC_C_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_zinc_c_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_zinc_c_limestone")));
    withExistingParent(BlockHandler.ORE_CERTUS_QUARTZ_C_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_certus_quartz_c_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_certus_quartz_c_limestone")));
    withExistingParent(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_C_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_charged_certus_quartz_c_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_charged_certus_quartz_c_limestone")));
    withExistingParent(BlockHandler.ORE_FLUORITE_C_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_fluorite_c_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_fluorite_c_limestone")));

    // Strata Ore Items - Scoria
    withExistingParent(BlockHandler.ORE_COAL_SCORIA.get().getRegistryName().getPath(), modLoc("ore_coal_scoria"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_coal_scoria")));
    withExistingParent(BlockHandler.ORE_IRON_SCORIA.get().getRegistryName().getPath(), modLoc("ore_iron_scoria"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_iron_scoria")));
    withExistingParent(BlockHandler.ORE_GOLD_SCORIA.get().getRegistryName().getPath(), modLoc("ore_gold_scoria"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_gold_scoria")));
    withExistingParent(BlockHandler.ORE_DIAMOND_SCORIA.get().getRegistryName().getPath(), modLoc("ore_diamond_scoria"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_diamond_scoria")));
    withExistingParent(BlockHandler.ORE_EMERALD_SCORIA.get().getRegistryName().getPath(), modLoc("ore_emerald_scoria"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_emerald_scoria")));
    withExistingParent(BlockHandler.ORE_LAPIS_SCORIA.get().getRegistryName().getPath(), modLoc("ore_lapis_scoria"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_lapis_scoria")));
    withExistingParent(BlockHandler.ORE_REDSTONE_SCORIA.get().getRegistryName().getPath(), modLoc("ore_redstone_scoria"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_redstone_scoria")));
    withExistingParent(BlockHandler.ORE_COPPER_SCORIA.get().getRegistryName().getPath(), modLoc("ore_copper_scoria"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_copper_scoria")));
    withExistingParent(BlockHandler.ORE_ALUMINUM_SCORIA.get().getRegistryName().getPath(), modLoc("ore_aluminum_scoria"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_aluminum_scoria")));
    withExistingParent(BlockHandler.ORE_SILVER_SCORIA.get().getRegistryName().getPath(), modLoc("ore_silver_scoria"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_silver_scoria")));
    withExistingParent(BlockHandler.ORE_LEAD_SCORIA.get().getRegistryName().getPath(), modLoc("ore_lead_scoria"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_lead_scoria")));
    withExistingParent(BlockHandler.ORE_NICKEL_SCORIA.get().getRegistryName().getPath(), modLoc("ore_nickel_scoria"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_nickel_scoria")));
    withExistingParent(BlockHandler.ORE_URANIUM_SCORIA.get().getRegistryName().getPath(), modLoc("ore_uranium_scoria"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_uranium_scoria")));
    withExistingParent(BlockHandler.ORE_OSMIUM_SCORIA.get().getRegistryName().getPath(), modLoc("ore_osmium_scoria"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_osmium_scoria")));
    withExistingParent(BlockHandler.ORE_TIN_SCORIA.get().getRegistryName().getPath(), modLoc("ore_tin_scoria"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_tin_scoria")));
    withExistingParent(BlockHandler.ORE_ZINC_SCORIA.get().getRegistryName().getPath(), modLoc("ore_zinc_scoria"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_zinc_scoria")));
    withExistingParent(BlockHandler.ORE_CERTUS_QUARTZ_SCORIA.get().getRegistryName().getPath(), modLoc("ore_certus_quartz_scoria"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_certus_quartz_scoria")));
    withExistingParent(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_SCORIA.get().getRegistryName().getPath(), modLoc("ore_charged_certus_quartz_scoria"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_charged_certus_quartz_scoria")));
    withExistingParent(BlockHandler.ORE_FLUORITE_SCORIA.get().getRegistryName().getPath(), modLoc("ore_fluorite_scoria"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_fluorite_scoria")));

    // Strata Ore Items - Weathered Limestone
    withExistingParent(BlockHandler.ORE_COAL_WEATHERED_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_coal_weathered_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_coal_weathered_limestone")));
    withExistingParent(BlockHandler.ORE_IRON_WEATHERED_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_iron_weathered_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_iron_weathered_limestone")));
    withExistingParent(BlockHandler.ORE_GOLD_WEATHERED_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_gold_weathered_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_gold_weathered_limestone")));
    withExistingParent(BlockHandler.ORE_DIAMOND_WEATHERED_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_diamond_weathered_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_diamond_weathered_limestone")));
    withExistingParent(BlockHandler.ORE_EMERALD_WEATHERED_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_emerald_weathered_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_emerald_weathered_limestone")));
    withExistingParent(BlockHandler.ORE_LAPIS_WEATHERED_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_lapis_weathered_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_lapis_weathered_limestone")));
    withExistingParent(BlockHandler.ORE_REDSTONE_WEATHERED_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_redstone_weathered_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_redstone_weathered_limestone")));
    withExistingParent(BlockHandler.ORE_COPPER_WEATHERED_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_copper_weathered_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_copper_weathered_limestone")));
    withExistingParent(BlockHandler.ORE_ALUMINUM_WEATHERED_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_aluminum_weathered_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_aluminum_weathered_limestone")));
    withExistingParent(BlockHandler.ORE_SILVER_WEATHERED_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_silver_weathered_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_silver_weathered_limestone")));
    withExistingParent(BlockHandler.ORE_LEAD_WEATHERED_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_lead_weathered_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_lead_weathered_limestone")));
    withExistingParent(BlockHandler.ORE_NICKEL_WEATHERED_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_nickel_weathered_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_nickel_weathered_limestone")));
    withExistingParent(BlockHandler.ORE_URANIUM_WEATHERED_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_uranium_weathered_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_uranium_weathered_limestone")));
    withExistingParent(BlockHandler.ORE_OSMIUM_WEATHERED_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_osmium_weathered_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_osmium_weathered_limestone")));
    withExistingParent(BlockHandler.ORE_TIN_WEATHERED_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_tin_weathered_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_tin_weathered_limestone")));
    withExistingParent(BlockHandler.ORE_ZINC_WEATHERED_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_zinc_weathered_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_zinc_weathered_limestone")));
    withExistingParent(BlockHandler.ORE_CERTUS_QUARTZ_WEATHERED_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_certus_quartz_weathered_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_certus_quartz_weathered_limestone")));
    withExistingParent(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_WEATHERED_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_charged_certus_quartz_weathered_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_charged_certus_quartz_weathered_limestone")));
    withExistingParent(BlockHandler.ORE_FLUORITE_WEATHERED_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_fluorite_weathered_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_fluorite_weathered_limestone")));

    // Strata Ore Items - Jasper
    withExistingParent(BlockHandler.ORE_COAL_JASPER.get().getRegistryName().getPath(), modLoc("ore_coal_jasper"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_coal_jasper")));
    withExistingParent(BlockHandler.ORE_IRON_JASPER.get().getRegistryName().getPath(), modLoc("ore_iron_jasper"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_iron_jasper")));
    withExistingParent(BlockHandler.ORE_GOLD_JASPER.get().getRegistryName().getPath(), modLoc("ore_gold_jasper"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_gold_jasper")));
    withExistingParent(BlockHandler.ORE_DIAMOND_JASPER.get().getRegistryName().getPath(), modLoc("ore_diamond_jasper"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_diamond_jasper")));
    withExistingParent(BlockHandler.ORE_EMERALD_JASPER.get().getRegistryName().getPath(), modLoc("ore_emerald_jasper"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_emerald_jasper")));
    withExistingParent(BlockHandler.ORE_LAPIS_JASPER.get().getRegistryName().getPath(), modLoc("ore_lapis_jasper"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_lapis_jasper")));
    withExistingParent(BlockHandler.ORE_REDSTONE_JASPER.get().getRegistryName().getPath(), modLoc("ore_redstone_jasper"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_redstone_jasper")));
    withExistingParent(BlockHandler.ORE_COPPER_JASPER.get().getRegistryName().getPath(), modLoc("ore_copper_jasper"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_copper_jasper")));
    withExistingParent(BlockHandler.ORE_ALUMINUM_JASPER.get().getRegistryName().getPath(), modLoc("ore_aluminum_jasper"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_aluminum_jasper")));
    withExistingParent(BlockHandler.ORE_SILVER_JASPER.get().getRegistryName().getPath(), modLoc("ore_silver_jasper"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_silver_jasper")));
    withExistingParent(BlockHandler.ORE_LEAD_JASPER.get().getRegistryName().getPath(), modLoc("ore_lead_jasper"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_lead_jasper")));
    withExistingParent(BlockHandler.ORE_NICKEL_JASPER.get().getRegistryName().getPath(), modLoc("ore_nickel_jasper"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_nickel_jasper")));
    withExistingParent(BlockHandler.ORE_URANIUM_JASPER.get().getRegistryName().getPath(), modLoc("ore_uranium_jasper"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_uranium_jasper")));
    withExistingParent(BlockHandler.ORE_OSMIUM_JASPER.get().getRegistryName().getPath(), modLoc("ore_osmium_jasper"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_osmium_jasper")));
    withExistingParent(BlockHandler.ORE_TIN_JASPER.get().getRegistryName().getPath(), modLoc("ore_tin_jasper"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_tin_jasper")));
    withExistingParent(BlockHandler.ORE_ZINC_JASPER.get().getRegistryName().getPath(), modLoc("ore_zinc_jasper"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_zinc_jasper")));
    withExistingParent(BlockHandler.ORE_CERTUS_QUARTZ_JASPER.get().getRegistryName().getPath(), modLoc("ore_certus_quartz_jasper"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_certus_quartz_jasper")));
    withExistingParent(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_JASPER.get().getRegistryName().getPath(), modLoc("ore_charged_certus_quartz_jasper"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_charged_certus_quartz_jasper")));
    withExistingParent(BlockHandler.ORE_FLUORITE_JASPER.get().getRegistryName().getPath(), modLoc("ore_fluorite_jasper"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_fluorite_jasper")));

    // Strata Ore Items - Limestone (Quark)
    withExistingParent(BlockHandler.ORE_COAL_Q_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_coal_q_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_coal_q_limestone")));
    withExistingParent(BlockHandler.ORE_IRON_Q_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_iron_q_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_iron_q_limestone")));
    withExistingParent(BlockHandler.ORE_GOLD_Q_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_gold_q_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_gold_q_limestone")));
    withExistingParent(BlockHandler.ORE_DIAMOND_Q_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_diamond_q_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_diamond_q_limestone")));
    withExistingParent(BlockHandler.ORE_EMERALD_Q_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_emerald_q_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_emerald_q_limestone")));
    withExistingParent(BlockHandler.ORE_LAPIS_Q_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_lapis_q_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_lapis_q_limestone")));
    withExistingParent(BlockHandler.ORE_REDSTONE_Q_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_redstone_q_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_redstone_q_limestone")));
    withExistingParent(BlockHandler.ORE_COPPER_Q_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_copper_q_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_copper_q_limestone")));
    withExistingParent(BlockHandler.ORE_ALUMINUM_Q_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_aluminum_q_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_aluminum_q_limestone")));
    withExistingParent(BlockHandler.ORE_SILVER_Q_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_silver_q_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_silver_q_limestone")));
    withExistingParent(BlockHandler.ORE_LEAD_Q_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_lead_q_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_lead_q_limestone")));
    withExistingParent(BlockHandler.ORE_NICKEL_Q_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_nickel_q_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_nickel_q_limestone")));
    withExistingParent(BlockHandler.ORE_URANIUM_Q_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_uranium_q_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_uranium_q_limestone")));
    withExistingParent(BlockHandler.ORE_OSMIUM_Q_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_osmium_q_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_osmium_q_limestone")));
    withExistingParent(BlockHandler.ORE_TIN_Q_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_tin_q_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_tin_q_limestone")));
    withExistingParent(BlockHandler.ORE_ZINC_Q_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_zinc_q_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_zinc_q_limestone")));
    withExistingParent(BlockHandler.ORE_CERTUS_QUARTZ_Q_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_certus_quartz_q_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_certus_quartz_q_limestone")));
    withExistingParent(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_Q_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_charged_certus_quartz_q_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_charged_certus_quartz_q_limestone")));
    withExistingParent(BlockHandler.ORE_FLUORITE_Q_LIMESTONE.get().getRegistryName().getPath(), modLoc("ore_fluorite_q_limestone"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_fluorite_q_limestone")));

    // Strata Ore Items - Marble
    withExistingParent(BlockHandler.ORE_COAL_MARBLE.get().getRegistryName().getPath(), modLoc("ore_coal_marble"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_coal_marble")));
    withExistingParent(BlockHandler.ORE_IRON_MARBLE.get().getRegistryName().getPath(), modLoc("ore_iron_marble"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_iron_marble")));
    withExistingParent(BlockHandler.ORE_GOLD_MARBLE.get().getRegistryName().getPath(), modLoc("ore_gold_marble"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_gold_marble")));
    withExistingParent(BlockHandler.ORE_DIAMOND_MARBLE.get().getRegistryName().getPath(), modLoc("ore_diamond_marble"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_diamond_marble")));
    withExistingParent(BlockHandler.ORE_EMERALD_MARBLE.get().getRegistryName().getPath(), modLoc("ore_emerald_marble"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_emerald_marble")));
    withExistingParent(BlockHandler.ORE_LAPIS_MARBLE.get().getRegistryName().getPath(), modLoc("ore_lapis_marble"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_lapis_marble")));
    withExistingParent(BlockHandler.ORE_REDSTONE_MARBLE.get().getRegistryName().getPath(), modLoc("ore_redstone_marble"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_redstone_marble")));
    withExistingParent(BlockHandler.ORE_COPPER_MARBLE.get().getRegistryName().getPath(), modLoc("ore_copper_marble"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_copper_marble")));
    withExistingParent(BlockHandler.ORE_ALUMINUM_MARBLE.get().getRegistryName().getPath(), modLoc("ore_aluminum_marble"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_aluminum_marble")));
    withExistingParent(BlockHandler.ORE_SILVER_MARBLE.get().getRegistryName().getPath(), modLoc("ore_silver_marble"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_silver_marble")));
    withExistingParent(BlockHandler.ORE_LEAD_MARBLE.get().getRegistryName().getPath(), modLoc("ore_lead_marble"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_lead_marble")));
    withExistingParent(BlockHandler.ORE_NICKEL_MARBLE.get().getRegistryName().getPath(), modLoc("ore_nickel_marble"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_nickel_marble")));
    withExistingParent(BlockHandler.ORE_URANIUM_MARBLE.get().getRegistryName().getPath(), modLoc("ore_uranium_marble"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_uranium_marble")));
    withExistingParent(BlockHandler.ORE_OSMIUM_MARBLE.get().getRegistryName().getPath(), modLoc("ore_osmium_marble"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_osmium_marble")));
    withExistingParent(BlockHandler.ORE_TIN_MARBLE.get().getRegistryName().getPath(), modLoc("ore_tin_marble"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_tin_marble")));
    withExistingParent(BlockHandler.ORE_ZINC_MARBLE.get().getRegistryName().getPath(), modLoc("ore_zinc_marble"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_zinc_marble")));
    withExistingParent(BlockHandler.ORE_CERTUS_QUARTZ_MARBLE.get().getRegistryName().getPath(), modLoc("ore_certus_quartz_marble"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_certus_quartz_marble")));
    withExistingParent(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_MARBLE.get().getRegistryName().getPath(), modLoc("ore_charged_certus_quartz_marble"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_charged_certus_quartz_marble")));
    withExistingParent(BlockHandler.ORE_FLUORITE_MARBLE.get().getRegistryName().getPath(), modLoc("ore_fluorite_marble"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_fluorite_marble")));

    // Strata Ore Items - Slate
    withExistingParent(BlockHandler.ORE_COAL_SLATE.get().getRegistryName().getPath(), modLoc("ore_coal_slate"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_coal_slate")));
    withExistingParent(BlockHandler.ORE_IRON_SLATE.get().getRegistryName().getPath(), modLoc("ore_iron_slate"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_iron_slate")));
    withExistingParent(BlockHandler.ORE_GOLD_SLATE.get().getRegistryName().getPath(), modLoc("ore_gold_slate"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_gold_slate")));
    withExistingParent(BlockHandler.ORE_DIAMOND_SLATE.get().getRegistryName().getPath(), modLoc("ore_diamond_slate"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_diamond_slate")));
    withExistingParent(BlockHandler.ORE_EMERALD_SLATE.get().getRegistryName().getPath(), modLoc("ore_emerald_slate"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_emerald_slate")));
    withExistingParent(BlockHandler.ORE_LAPIS_SLATE.get().getRegistryName().getPath(), modLoc("ore_lapis_slate"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_lapis_slate")));
    withExistingParent(BlockHandler.ORE_REDSTONE_SLATE.get().getRegistryName().getPath(), modLoc("ore_redstone_slate"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_redstone_slate")));
    withExistingParent(BlockHandler.ORE_COPPER_SLATE.get().getRegistryName().getPath(), modLoc("ore_copper_slate"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_copper_slate")));
    withExistingParent(BlockHandler.ORE_ALUMINUM_SLATE.get().getRegistryName().getPath(), modLoc("ore_aluminum_slate"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_aluminum_slate")));
    withExistingParent(BlockHandler.ORE_SILVER_SLATE.get().getRegistryName().getPath(), modLoc("ore_silver_slate"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_silver_slate")));
    withExistingParent(BlockHandler.ORE_LEAD_SLATE.get().getRegistryName().getPath(), modLoc("ore_lead_slate"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_lead_slate")));
    withExistingParent(BlockHandler.ORE_NICKEL_SLATE.get().getRegistryName().getPath(), modLoc("ore_nickel_slate"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_nickel_slate")));
    withExistingParent(BlockHandler.ORE_URANIUM_SLATE.get().getRegistryName().getPath(), modLoc("ore_uranium_slate"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_uranium_slate")));
    withExistingParent(BlockHandler.ORE_OSMIUM_SLATE.get().getRegistryName().getPath(), modLoc("ore_osmium_slate"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_osmium_slate")));
    withExistingParent(BlockHandler.ORE_TIN_SLATE.get().getRegistryName().getPath(), modLoc("ore_tin_slate"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_tin_slate")));
    withExistingParent(BlockHandler.ORE_ZINC_SLATE.get().getRegistryName().getPath(), modLoc("ore_zinc_slate"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_zinc_slate")));
    withExistingParent(BlockHandler.ORE_CERTUS_QUARTZ_SLATE.get().getRegistryName().getPath(), modLoc("ore_certus_quartz_slate"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_certus_quartz_slate")));
    withExistingParent(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_SLATE.get().getRegistryName().getPath(), modLoc("ore_charged_certus_quartz_slate"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_charged_certus_quartz_slate")));
    withExistingParent(BlockHandler.ORE_FLUORITE_SLATE.get().getRegistryName().getPath(), modLoc("ore_fluorite_slate"))
            .parent(new ModelFile.UncheckedModelFile(new ResourceLocation(Reference.MOD_ID,"block/ore_fluorite_slate")));
  }

  @Override
  public String getName()
  {
    return "Emendatus Enigmatica ItemModels";
  }

}

