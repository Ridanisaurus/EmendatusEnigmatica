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
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

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
    simpleBlock(BlockHandler.ORE_COAL.get(), models().cubeAll("ore_coal", new ResourceLocation(Reference.MOD_ID, "blocks/ore_coal")));
    simpleBlock(BlockHandler.ORE_IRON.get(), models().cubeAll("ore_iron", new ResourceLocation(Reference.MOD_ID, "blocks/ore_iron")));
    simpleBlock(BlockHandler.ORE_GOLD.get(), models().cubeAll("ore_gold", new ResourceLocation(Reference.MOD_ID, "blocks/ore_gold")));
    simpleBlock(BlockHandler.ORE_DIAMOND.get(), models().cubeAll("ore_diamond", new ResourceLocation(Reference.MOD_ID, "blocks/ore_diamond")));
    simpleBlock(BlockHandler.ORE_EMERALD.get(), models().cubeAll("ore_emerald", new ResourceLocation(Reference.MOD_ID, "blocks/ore_emerald")));
    simpleBlock(BlockHandler.ORE_LAPIS.get(), models().cubeAll("ore_lapis", new ResourceLocation(Reference.MOD_ID, "blocks/ore_lapis")));
    simpleBlock(BlockHandler.ORE_REDSTONE.get(), models().cubeAll("ore_redstone", new ResourceLocation(Reference.MOD_ID, "blocks/ore_redstone")));
    simpleBlock(BlockHandler.ORE_COPPER.get(), models().cubeAll("ore_copper", new ResourceLocation(Reference.MOD_ID, "blocks/ore_copper")));
    simpleBlock(BlockHandler.ORE_ALUMINUM.get(), models().cubeAll("ore_aluminum", new ResourceLocation(Reference.MOD_ID, "blocks/ore_aluminum")));
    simpleBlock(BlockHandler.ORE_SILVER.get(), models().cubeAll("ore_silver", new ResourceLocation(Reference.MOD_ID, "blocks/ore_silver")));
    simpleBlock(BlockHandler.ORE_LEAD.get(), models().cubeAll("ore_lead", new ResourceLocation(Reference.MOD_ID, "blocks/ore_lead")));
    simpleBlock(BlockHandler.ORE_NICKEL.get(), models().cubeAll("ore_nickel", new ResourceLocation(Reference.MOD_ID, "blocks/ore_nickel")));
    simpleBlock(BlockHandler.ORE_URANIUM.get(), models().cubeAll("ore_uranium", new ResourceLocation(Reference.MOD_ID, "blocks/ore_uranium")));
    simpleBlock(BlockHandler.ORE_OSMIUM.get(), models().cubeAll("ore_osmium", new ResourceLocation(Reference.MOD_ID, "blocks/ore_osmium")));
    simpleBlock(BlockHandler.ORE_TIN.get(), models().cubeAll("ore_tin", new ResourceLocation(Reference.MOD_ID, "blocks/ore_tin")));
    simpleBlock(BlockHandler.ORE_ZINC.get(), models().cubeAll("ore_zinc", new ResourceLocation(Reference.MOD_ID, "blocks/ore_zinc")));

    // Strata Ores - Andesite
    simpleBlock(BlockHandler.ORE_COAL_ANDESITE.get(), models().cubeAll("ore_coal_andesite", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/minecraft/ore_coal_andesite")));
    simpleBlock(BlockHandler.ORE_IRON_ANDESITE.get(), models().cubeAll("ore_iron_andesite", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/minecraft/ore_iron_andesite")));
    simpleBlock(BlockHandler.ORE_GOLD_ANDESITE.get(), models().cubeAll("ore_gold_andesite", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/minecraft/ore_gold_andesite")));
    simpleBlock(BlockHandler.ORE_DIAMOND_ANDESITE.get(), models().cubeAll("ore_diamond_andesite", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/minecraft/ore_diamond_andesite")));
    simpleBlock(BlockHandler.ORE_EMERALD_ANDESITE.get(), models().cubeAll("ore_emerald_andesite", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/minecraft/ore_emerald_andesite")));
    simpleBlock(BlockHandler.ORE_LAPIS_ANDESITE.get(), models().cubeAll("ore_lapis_andesite", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/minecraft/ore_lapis_andesite")));
    simpleBlock(BlockHandler.ORE_REDSTONE_ANDESITE.get(), models().cubeAll("ore_redstone_andesite", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/minecraft/ore_redstone_andesite")));
    simpleBlock(BlockHandler.ORE_COPPER_ANDESITE.get(), models().cubeAll("ore_copper_andesite", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/minecraft/ore_copper_andesite")));
    simpleBlock(BlockHandler.ORE_ALUMINUM_ANDESITE.get(), models().cubeAll("ore_aluminum_andesite", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/minecraft/ore_aluminum_andesite")));
    simpleBlock(BlockHandler.ORE_SILVER_ANDESITE.get(), models().cubeAll("ore_silver_andesite", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/minecraft/ore_silver_andesite")));
    simpleBlock(BlockHandler.ORE_LEAD_ANDESITE.get(), models().cubeAll("ore_lead_andesite", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/minecraft/ore_lead_andesite")));
    simpleBlock(BlockHandler.ORE_NICKEL_ANDESITE.get(), models().cubeAll("ore_nickel_andesite", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/minecraft/ore_nickel_andesite")));
    simpleBlock(BlockHandler.ORE_URANIUM_ANDESITE.get(), models().cubeAll("ore_uranium_andesite", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/minecraft/ore_uranium_andesite")));
    simpleBlock(BlockHandler.ORE_OSMIUM_ANDESITE.get(), models().cubeAll("ore_osmium_andesite", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/minecraft/ore_osmium_andesite")));
    simpleBlock(BlockHandler.ORE_TIN_ANDESITE.get(), models().cubeAll("ore_tin_andesite", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/minecraft/ore_tin_andesite")));
    simpleBlock(BlockHandler.ORE_ZINC_ANDESITE.get(), models().cubeAll("ore_zinc_andesite", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/minecraft/ore_zinc_andesite")));

    // Strata Ores - Gabbro
    simpleBlock(BlockHandler.ORE_COAL_GABBRO.get(), models().cubeAll("ore_coal_gabbro", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_coal_gabbro")));
    simpleBlock(BlockHandler.ORE_IRON_GABBRO.get(), models().cubeAll("ore_iron_gabbro", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_iron_gabbro")));
    simpleBlock(BlockHandler.ORE_GOLD_GABBRO.get(), models().cubeAll("ore_gold_gabbro", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_gold_gabbro")));
    simpleBlock(BlockHandler.ORE_DIAMOND_GABBRO.get(), models().cubeAll("ore_diamond_gabbro", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_diamond_gabbro")));
    simpleBlock(BlockHandler.ORE_EMERALD_GABBRO.get(), models().cubeAll("ore_emerald_gabbro", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_emerald_gabbro")));
    simpleBlock(BlockHandler.ORE_LAPIS_GABBRO.get(), models().cubeAll("ore_lapis_gabbro", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_lapis_gabbro")));
    simpleBlock(BlockHandler.ORE_REDSTONE_GABBRO.get(), models().cubeAll("ore_redstone_gabbro", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_redstone_gabbro")));
    simpleBlock(BlockHandler.ORE_COPPER_GABBRO.get(), models().cubeAll("ore_copper_gabbro", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_copper_gabbro")));
    simpleBlock(BlockHandler.ORE_ALUMINUM_GABBRO.get(), models().cubeAll("ore_aluminum_gabbro", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_aluminum_gabbro")));
    simpleBlock(BlockHandler.ORE_SILVER_GABBRO.get(), models().cubeAll("ore_silver_gabbro", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_silver_gabbro")));
    simpleBlock(BlockHandler.ORE_LEAD_GABBRO.get(), models().cubeAll("ore_lead_gabbro", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_lead_gabbro")));
    simpleBlock(BlockHandler.ORE_NICKEL_GABBRO.get(), models().cubeAll("ore_nickel_gabbro", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_nickel_gabbro")));
    simpleBlock(BlockHandler.ORE_URANIUM_GABBRO.get(), models().cubeAll("ore_uranium_gabbro", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_uranium_gabbro")));
    simpleBlock(BlockHandler.ORE_OSMIUM_GABBRO.get(), models().cubeAll("ore_osmium_gabbro", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_osmium_gabbro")));
    simpleBlock(BlockHandler.ORE_TIN_GABBRO.get(), models().cubeAll("ore_tin_gabbro", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_tin_gabbro")));
    simpleBlock(BlockHandler.ORE_ZINC_GABBRO.get(), models().cubeAll("ore_zinc_gabbro", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_zinc_gabbro")));

    // Strata Ores - Limestone (Create)
    simpleBlock(BlockHandler.ORE_COAL_C_LIMESTONE.get(), models().cubeAll("ore_coal_c_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_coal_limestone")));
    simpleBlock(BlockHandler.ORE_IRON_C_LIMESTONE.get(), models().cubeAll("ore_iron_c_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_iron_limestone")));
    simpleBlock(BlockHandler.ORE_GOLD_C_LIMESTONE.get(), models().cubeAll("ore_gold_c_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_gold_limestone")));
    simpleBlock(BlockHandler.ORE_DIAMOND_C_LIMESTONE.get(), models().cubeAll("ore_diamond_c_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_diamond_limestone")));
    simpleBlock(BlockHandler.ORE_EMERALD_C_LIMESTONE.get(), models().cubeAll("ore_emerald_c_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_emerald_limestone")));
    simpleBlock(BlockHandler.ORE_LAPIS_C_LIMESTONE.get(), models().cubeAll("ore_lapis_c_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_lapis_limestone")));
    simpleBlock(BlockHandler.ORE_REDSTONE_C_LIMESTONE.get(), models().cubeAll("ore_redstone_c_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_redstone_limestone")));
    simpleBlock(BlockHandler.ORE_COPPER_C_LIMESTONE.get(), models().cubeAll("ore_copper_c_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_copper_limestone")));
    simpleBlock(BlockHandler.ORE_ALUMINUM_C_LIMESTONE.get(), models().cubeAll("ore_aluminum_c_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_aluminum_limestone")));
    simpleBlock(BlockHandler.ORE_SILVER_C_LIMESTONE.get(), models().cubeAll("ore_silver_c_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_silver_limestone")));
    simpleBlock(BlockHandler.ORE_LEAD_C_LIMESTONE.get(), models().cubeAll("ore_lead_c_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_lead_limestone")));
    simpleBlock(BlockHandler.ORE_NICKEL_C_LIMESTONE.get(), models().cubeAll("ore_nickel_c_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_nickel_limestone")));
    simpleBlock(BlockHandler.ORE_URANIUM_C_LIMESTONE.get(), models().cubeAll("ore_uranium_c_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_uranium_limestone")));
    simpleBlock(BlockHandler.ORE_OSMIUM_C_LIMESTONE.get(), models().cubeAll("ore_osmium_c_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_osmium_limestone")));
    simpleBlock(BlockHandler.ORE_TIN_C_LIMESTONE.get(), models().cubeAll("ore_tin_c_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_tin_limestone")));
    simpleBlock(BlockHandler.ORE_ZINC_C_LIMESTONE.get(), models().cubeAll("ore_zinc_c_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_zinc_limestone")));

    // Strata Ores - Scoria
    simpleBlock(BlockHandler.ORE_COAL_SCORIA.get(), models().cubeAll("ore_coal_scoria", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_coal_scoria")));
    simpleBlock(BlockHandler.ORE_IRON_SCORIA.get(), models().cubeAll("ore_iron_scoria", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_iron_scoria")));
    simpleBlock(BlockHandler.ORE_GOLD_SCORIA.get(), models().cubeAll("ore_gold_scoria", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_gold_scoria")));
    simpleBlock(BlockHandler.ORE_DIAMOND_SCORIA.get(), models().cubeAll("ore_diamond_scoria", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_diamond_scoria")));
    simpleBlock(BlockHandler.ORE_EMERALD_SCORIA.get(), models().cubeAll("ore_emerald_scoria", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_emerald_scoria")));
    simpleBlock(BlockHandler.ORE_LAPIS_SCORIA.get(), models().cubeAll("ore_lapis_scoria", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_lapis_scoria")));
    simpleBlock(BlockHandler.ORE_REDSTONE_SCORIA.get(), models().cubeAll("ore_redstone_scoria", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_redstone_scoria")));
    simpleBlock(BlockHandler.ORE_COPPER_SCORIA.get(), models().cubeAll("ore_copper_scoria", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_copper_scoria")));
    simpleBlock(BlockHandler.ORE_ALUMINUM_SCORIA.get(), models().cubeAll("ore_aluminum_scoria", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_aluminum_scoria")));
    simpleBlock(BlockHandler.ORE_SILVER_SCORIA.get(), models().cubeAll("ore_silver_scoria", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_silver_scoria")));
    simpleBlock(BlockHandler.ORE_LEAD_SCORIA.get(), models().cubeAll("ore_lead_scoria", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_lead_scoria")));
    simpleBlock(BlockHandler.ORE_NICKEL_SCORIA.get(), models().cubeAll("ore_nickel_scoria", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_nickel_scoria")));
    simpleBlock(BlockHandler.ORE_URANIUM_SCORIA.get(), models().cubeAll("ore_uranium_scoria", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_uranium_scoria")));
    simpleBlock(BlockHandler.ORE_OSMIUM_SCORIA.get(), models().cubeAll("ore_osmium_scoria", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_osmium_scoria")));
    simpleBlock(BlockHandler.ORE_TIN_SCORIA.get(), models().cubeAll("ore_tin_scoria", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_tin_scoria")));
    simpleBlock(BlockHandler.ORE_ZINC_SCORIA.get(), models().cubeAll("ore_zinc_scoria", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_zinc_scoria")));

    // Strata Ore - Weathered Limestone
    simpleBlock(BlockHandler.ORE_COAL_WEATHERED_LIMESTONE.get(), models().cubeAll("ore_coal_weathered_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_coal_weathered_limestone")));
    simpleBlock(BlockHandler.ORE_IRON_WEATHERED_LIMESTONE.get(), models().cubeAll("ore_iron_weathered_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_iron_weathered_limestone")));
    simpleBlock(BlockHandler.ORE_GOLD_WEATHERED_LIMESTONE.get(), models().cubeAll("ore_gold_weathered_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_gold_weathered_limestone")));
    simpleBlock(BlockHandler.ORE_DIAMOND_WEATHERED_LIMESTONE.get(), models().cubeAll("ore_diamond_weathered_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_diamond_weathered_limestone")));
    simpleBlock(BlockHandler.ORE_EMERALD_WEATHERED_LIMESTONE.get(), models().cubeAll("ore_emerald_weathered_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_emerald_weathered_limestone")));
    simpleBlock(BlockHandler.ORE_LAPIS_WEATHERED_LIMESTONE.get(), models().cubeAll("ore_lapis_weathered_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_lapis_weathered_limestone")));
    simpleBlock(BlockHandler.ORE_REDSTONE_WEATHERED_LIMESTONE.get(), models().cubeAll("ore_redstone_weathered_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_redstone_weathered_limestone")));
    simpleBlock(BlockHandler.ORE_COPPER_WEATHERED_LIMESTONE.get(), models().cubeAll("ore_copper_weathered_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_copper_weathered_limestone")));
    simpleBlock(BlockHandler.ORE_ALUMINUM_WEATHERED_LIMESTONE.get(), models().cubeAll("ore_aluminum_weathered_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_aluminum_weathered_limestone")));
    simpleBlock(BlockHandler.ORE_SILVER_WEATHERED_LIMESTONE.get(), models().cubeAll("ore_silver_weathered_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_silver_weathered_limestone")));
    simpleBlock(BlockHandler.ORE_LEAD_WEATHERED_LIMESTONE.get(), models().cubeAll("ore_lead_weathered_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_lead_weathered_limestone")));
    simpleBlock(BlockHandler.ORE_NICKEL_WEATHERED_LIMESTONE.get(), models().cubeAll("ore_nickel_weathered_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_nickel_weathered_limestone")));
    simpleBlock(BlockHandler.ORE_URANIUM_WEATHERED_LIMESTONE.get(), models().cubeAll("ore_uranium_weathered_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_uranium_weathered_limestone")));
    simpleBlock(BlockHandler.ORE_OSMIUM_WEATHERED_LIMESTONE.get(), models().cubeAll("ore_osmium_weathered_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_osmium_weathered_limestone")));
    simpleBlock(BlockHandler.ORE_TIN_WEATHERED_LIMESTONE.get(), models().cubeAll("ore_tin_weathered_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_tin_weathered_limestone")));
    simpleBlock(BlockHandler.ORE_ZINC_WEATHERED_LIMESTONE.get(), models().cubeAll("ore_zinc_weathered_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/create/ore_zinc_weathered_limestone")));

    // Strata Ore - Jasper
    simpleBlock(BlockHandler.ORE_COAL_JASPER.get(), models().cubeAll("ore_coal_jasper", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_coal_jasper")));
    simpleBlock(BlockHandler.ORE_IRON_JASPER.get(), models().cubeAll("ore_iron_jasper", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_iron_jasper")));
    simpleBlock(BlockHandler.ORE_GOLD_JASPER.get(), models().cubeAll("ore_gold_jasper", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_gold_jasper")));
    simpleBlock(BlockHandler.ORE_DIAMOND_JASPER.get(), models().cubeAll("ore_diamond_jasper", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_diamond_jasper")));
    simpleBlock(BlockHandler.ORE_EMERALD_JASPER.get(), models().cubeAll("ore_emerald_jasper", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_emerald_jasper")));
    simpleBlock(BlockHandler.ORE_LAPIS_JASPER.get(), models().cubeAll("ore_lapis_jasper", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_lapis_jasper")));
    simpleBlock(BlockHandler.ORE_REDSTONE_JASPER.get(), models().cubeAll("ore_redstone_jasper", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_redstone_jasper")));
    simpleBlock(BlockHandler.ORE_COPPER_JASPER.get(), models().cubeAll("ore_copper_jasper", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_copper_jasper")));
    simpleBlock(BlockHandler.ORE_ALUMINUM_JASPER.get(), models().cubeAll("ore_aluminum_jasper", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_aluminum_jasper")));
    simpleBlock(BlockHandler.ORE_SILVER_JASPER.get(), models().cubeAll("ore_silver_jasper", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_silver_jasper")));
    simpleBlock(BlockHandler.ORE_LEAD_JASPER.get(), models().cubeAll("ore_lead_jasper", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_lead_jasper")));
    simpleBlock(BlockHandler.ORE_NICKEL_JASPER.get(), models().cubeAll("ore_nickel_jasper", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_nickel_jasper")));
    simpleBlock(BlockHandler.ORE_URANIUM_JASPER.get(), models().cubeAll("ore_uranium_jasper", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_uranium_jasper")));
    simpleBlock(BlockHandler.ORE_OSMIUM_JASPER.get(), models().cubeAll("ore_osmium_jasper", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_osmium_jasper")));
    simpleBlock(BlockHandler.ORE_TIN_JASPER.get(), models().cubeAll("ore_tin_jasper", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_tin_jasper")));
    simpleBlock(BlockHandler.ORE_ZINC_JASPER.get(), models().cubeAll("ore_zinc_jasper", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_zinc_jasper")));

    // Strata Ore - Limestone (Quark)
    simpleBlock(BlockHandler.ORE_COAL_Q_LIMESTONE.get(), models().cubeAll("ore_coal_q_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_coal_limestone")));
    simpleBlock(BlockHandler.ORE_IRON_Q_LIMESTONE.get(), models().cubeAll("ore_iron_q_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_iron_limestone")));
    simpleBlock(BlockHandler.ORE_GOLD_Q_LIMESTONE.get(), models().cubeAll("ore_gold_q_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_gold_limestone")));
    simpleBlock(BlockHandler.ORE_DIAMOND_Q_LIMESTONE.get(), models().cubeAll("ore_diamond_q_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_diamond_limestone")));
    simpleBlock(BlockHandler.ORE_EMERALD_Q_LIMESTONE.get(), models().cubeAll("ore_emerald_q_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_emerald_limestone")));
    simpleBlock(BlockHandler.ORE_LAPIS_Q_LIMESTONE.get(), models().cubeAll("ore_lapis_q_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_lapis_limestone")));
    simpleBlock(BlockHandler.ORE_REDSTONE_Q_LIMESTONE.get(), models().cubeAll("ore_redstone_q_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_redstone_limestone")));
    simpleBlock(BlockHandler.ORE_COPPER_Q_LIMESTONE.get(), models().cubeAll("ore_copper_q_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_copper_limestone")));
    simpleBlock(BlockHandler.ORE_ALUMINUM_Q_LIMESTONE.get(), models().cubeAll("ore_aluminum_q_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_aluminum_limestone")));
    simpleBlock(BlockHandler.ORE_SILVER_Q_LIMESTONE.get(), models().cubeAll("ore_silver_q_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_silver_limestone")));
    simpleBlock(BlockHandler.ORE_LEAD_Q_LIMESTONE.get(), models().cubeAll("ore_lead_q_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_lead_limestone")));
    simpleBlock(BlockHandler.ORE_NICKEL_Q_LIMESTONE.get(), models().cubeAll("ore_nickel_q_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_nickel_limestone")));
    simpleBlock(BlockHandler.ORE_URANIUM_Q_LIMESTONE.get(), models().cubeAll("ore_uranium_q_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_uranium_limestone")));
    simpleBlock(BlockHandler.ORE_OSMIUM_Q_LIMESTONE.get(), models().cubeAll("ore_osmium_q_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_osmium_limestone")));
    simpleBlock(BlockHandler.ORE_TIN_Q_LIMESTONE.get(), models().cubeAll("ore_tin_q_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_tin_limestone")));
    simpleBlock(BlockHandler.ORE_ZINC_Q_LIMESTONE.get(), models().cubeAll("ore_zinc_q_limestone", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_zinc_limestone")));

    // Strata Ore - Marble
    simpleBlock(BlockHandler.ORE_COAL_MARBLE.get(), models().cubeAll("ore_coal_marble", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_coal_marble")));
    simpleBlock(BlockHandler.ORE_IRON_MARBLE.get(), models().cubeAll("ore_iron_marble", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_iron_marble")));
    simpleBlock(BlockHandler.ORE_GOLD_MARBLE.get(), models().cubeAll("ore_gold_marble", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_gold_marble")));
    simpleBlock(BlockHandler.ORE_DIAMOND_MARBLE.get(), models().cubeAll("ore_diamond_marble", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_diamond_marble")));
    simpleBlock(BlockHandler.ORE_EMERALD_MARBLE.get(), models().cubeAll("ore_emerald_marble", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_emerald_marble")));
    simpleBlock(BlockHandler.ORE_LAPIS_MARBLE.get(), models().cubeAll("ore_lapis_marble", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_lapis_marble")));
    simpleBlock(BlockHandler.ORE_REDSTONE_MARBLE.get(), models().cubeAll("ore_redstone_marble", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_redstone_marble")));
    simpleBlock(BlockHandler.ORE_COPPER_MARBLE.get(), models().cubeAll("ore_copper_marble", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_copper_marble")));
    simpleBlock(BlockHandler.ORE_ALUMINUM_MARBLE.get(), models().cubeAll("ore_aluminum_marble", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_aluminum_marble")));
    simpleBlock(BlockHandler.ORE_SILVER_MARBLE.get(), models().cubeAll("ore_silver_marble", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_silver_marble")));
    simpleBlock(BlockHandler.ORE_LEAD_MARBLE.get(), models().cubeAll("ore_lead_marble", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_lead_marble")));
    simpleBlock(BlockHandler.ORE_NICKEL_MARBLE.get(), models().cubeAll("ore_nickel_marble", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_nickel_marble")));
    simpleBlock(BlockHandler.ORE_URANIUM_MARBLE.get(), models().cubeAll("ore_uranium_marble", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_uranium_marble")));
    simpleBlock(BlockHandler.ORE_OSMIUM_MARBLE.get(), models().cubeAll("ore_osmium_marble", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_osmium_marble")));
    simpleBlock(BlockHandler.ORE_TIN_MARBLE.get(), models().cubeAll("ore_tin_marble", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_tin_marble")));
    simpleBlock(BlockHandler.ORE_ZINC_MARBLE.get(), models().cubeAll("ore_zinc_marble", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_zinc_marble")));

    // Strata Ore - Slate
    simpleBlock(BlockHandler.ORE_COAL_SLATE.get(), models().cubeAll("ore_coal_slate", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_coal_slate")));
    simpleBlock(BlockHandler.ORE_IRON_SLATE.get(), models().cubeAll("ore_iron_slate", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_iron_slate")));
    simpleBlock(BlockHandler.ORE_GOLD_SLATE.get(), models().cubeAll("ore_gold_slate", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_gold_slate")));
    simpleBlock(BlockHandler.ORE_DIAMOND_SLATE.get(), models().cubeAll("ore_diamond_slate", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_diamond_slate")));
    simpleBlock(BlockHandler.ORE_EMERALD_SLATE.get(), models().cubeAll("ore_emerald_slate", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_emerald_slate")));
    simpleBlock(BlockHandler.ORE_LAPIS_SLATE.get(), models().cubeAll("ore_lapis_slate", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_lapis_slate")));
    simpleBlock(BlockHandler.ORE_REDSTONE_SLATE.get(), models().cubeAll("ore_redstone_slate", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_redstone_slate")));
    simpleBlock(BlockHandler.ORE_COPPER_SLATE.get(), models().cubeAll("ore_copper_slate", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_copper_slate")));
    simpleBlock(BlockHandler.ORE_ALUMINUM_SLATE.get(), models().cubeAll("ore_aluminum_slate", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_aluminum_slate")));
    simpleBlock(BlockHandler.ORE_SILVER_SLATE.get(), models().cubeAll("ore_silver_slate", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_silver_slate")));
    simpleBlock(BlockHandler.ORE_LEAD_SLATE.get(), models().cubeAll("ore_lead_slate", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_lead_slate")));
    simpleBlock(BlockHandler.ORE_NICKEL_SLATE.get(), models().cubeAll("ore_nickel_slate", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_nickel_slate")));
    simpleBlock(BlockHandler.ORE_URANIUM_SLATE.get(), models().cubeAll("ore_uranium_slate", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_uranium_slate")));
    simpleBlock(BlockHandler.ORE_OSMIUM_SLATE.get(), models().cubeAll("ore_osmium_slate", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_osmium_slate")));
    simpleBlock(BlockHandler.ORE_TIN_SLATE.get(), models().cubeAll("ore_tin_slate", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_tin_slate")));
    simpleBlock(BlockHandler.ORE_ZINC_SLATE.get(), models().cubeAll("ore_zinc_slate", new ResourceLocation(Reference.MOD_ID, "blocks/strata_ores/quark/ore_zinc_slate")));
  }

  @Override
  public String getName()
  {
    return "Emendatus Enigmatica BlockModels and BlockStates";
  }
}
