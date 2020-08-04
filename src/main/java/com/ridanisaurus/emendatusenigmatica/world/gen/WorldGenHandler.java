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

package com.ridanisaurus.emendatusenigmatica.world.gen;

import com.ridanisaurus.emendatusenigmatica.registries.BlockHandler;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WorldGenHandler {

  // Vanilla Fillers
  public static OreFeatureConfig.FillerBlockType ANDESITE = OreFeatureConfig.FillerBlockType.create("ANDESITE", "andesite", new BlockMatcher(Blocks.ANDESITE));
  public static OreFeatureConfig.FillerBlockType STONE = OreFeatureConfig.FillerBlockType.create("STONE", "stone", new BlockMatcher(Blocks.STONE));

  private static OreFeatureConfig.FillerBlockType CREATE_GABBRO = null;
  private static OreFeatureConfig.FillerBlockType CREATE_LIMESTONE = null;
  private static OreFeatureConfig.FillerBlockType CREATE_SCORIA = null;
  private static OreFeatureConfig.FillerBlockType CREATE_WEATHERED_LIMESTONE = null;
  private static OreFeatureConfig.FillerBlockType QUARK_JASPER = null;
  private static OreFeatureConfig.FillerBlockType QUARK_LIMESTONE = null;
  private static OreFeatureConfig.FillerBlockType QUARK_MARBLE = null;
  private static OreFeatureConfig.FillerBlockType QUARK_SLATE = null;

  @ObjectHolder("create:gabbro")
  public static final Block blockCreateGabbro = null;

  @ObjectHolder("create:limestone")
  public static final Block blockCreateLimestone = null;

  @ObjectHolder("create:natural_scoria")
  public static final Block blockCreateScoria = null;

  @ObjectHolder("create:weathered_limestone")
  public static final Block blockCreateWeatheredLimestone = null;

  @ObjectHolder("quark:jasper")
  public static final Block blockQuarkJasper = null;

  @ObjectHolder("quark:limestone")
  public static final Block blockQuarkLimestone = null;

  @ObjectHolder("quark:marble")
  public static final Block blockQuarkMarble = null;

  @ObjectHolder("quark:slate")
  public static final Block blockQuarkSlate = null;

  @SubscribeEvent
  public static void generateWorld(FMLLoadCompleteEvent event) {

    if (blockCreateGabbro != null) {
      CREATE_GABBRO = OreFeatureConfig.FillerBlockType.create("GABBRO", "gabbro", new BlockMatcher(blockCreateGabbro.getBlock()));
    }
    if (blockCreateLimestone != null) {
      CREATE_LIMESTONE = OreFeatureConfig.FillerBlockType.create("C_LIMESTONE", "c_limestone", new BlockMatcher(blockCreateLimestone.getBlock()));
    }
    if (blockCreateScoria != null) {
      CREATE_SCORIA = OreFeatureConfig.FillerBlockType.create("SCORIA", "scoria", new BlockMatcher(blockCreateScoria.getBlock()));
    }
    if (blockCreateWeatheredLimestone != null) {
      CREATE_WEATHERED_LIMESTONE = OreFeatureConfig.FillerBlockType.create("WEATHERED_LIMESTONE", "weathered_limestone", new BlockMatcher(blockCreateWeatheredLimestone.getBlock()));
    }
    if (blockQuarkJasper != null) {
      QUARK_JASPER = OreFeatureConfig.FillerBlockType.create("JASPER", "jasper", new BlockMatcher(blockQuarkJasper.getBlock()));
    }
    if (blockQuarkLimestone != null) {
      QUARK_LIMESTONE = OreFeatureConfig.FillerBlockType.create("Q_LIMESTONE", "q_limestone", new BlockMatcher(blockQuarkLimestone.getBlock()));
    }
    if (blockQuarkMarble != null) {
      QUARK_MARBLE = OreFeatureConfig.FillerBlockType.create("MARBLE", "marble", new BlockMatcher(blockQuarkMarble.getBlock()));
    }
    if (blockQuarkSlate != null) {
      QUARK_SLATE = OreFeatureConfig.FillerBlockType.create("SLATE", "slate", new BlockMatcher(blockQuarkSlate.getBlock()));
    }

    for (Biome biome : ForgeRegistries.BIOMES) {
      overrideFeatures(biome);

      genOre(biome, 20, 0, 0, 128, STONE, BlockHandler.ORE_COAL.get().getDefaultState(), 17);
      genOre(biome, 20, 0, 0, 64, STONE, BlockHandler.ORE_IRON.get().getDefaultState(), 9);
      genOre(biome, 2, 0, 0, 32, STONE, BlockHandler.ORE_GOLD.get().getDefaultState(), 9);
      genOre(biome, 1, 0, 0, 16, STONE, BlockHandler.ORE_DIAMOND.get().getDefaultState(), 8);
      genOre(biome, 1, 0, 0, 16, STONE, BlockHandler.ORE_EMERALD.get().getDefaultState(), 4);
      genOre(biome, 2, 0, 0, 16, STONE, BlockHandler.ORE_LAPIS.get().getDefaultState(), 7);
      genOre(biome, 8, 0, 0, 16, STONE, BlockHandler.ORE_REDSTONE.get().getDefaultState(), 8);
      genOre(biome, 20, 45, 0, 60, STONE, BlockHandler.ORE_COPPER.get().getDefaultState(), 7);
      genOre(biome, 8, 50, 0, 70, STONE, BlockHandler.ORE_ALUMINUM.get().getDefaultState(), 3);
      genOre(biome, 8, 30, 0, 38, STONE, BlockHandler.ORE_SILVER.get().getDefaultState(), 4);
      genOre(biome, 8, 32, 0, 40, STONE, BlockHandler.ORE_LEAD.get().getDefaultState(), 3);
      genOre(biome, 8, 25, 0, 40, STONE, BlockHandler.ORE_NICKEL.get().getDefaultState(), 3);
      genOre(biome, 4, 5, 0, 20, STONE, BlockHandler.ORE_URANIUM.get().getDefaultState(), 3);
      genOre(biome, 20, 20, 0, 45, STONE, BlockHandler.ORE_OSMIUM.get().getDefaultState(), 6);
      genOre(biome, 20, 40, 0, 55, STONE, BlockHandler.ORE_TIN.get().getDefaultState(), 6);
      genOre(biome, 8, 35, 0, 50, STONE, BlockHandler.ORE_ZINC.get().getDefaultState(), 4);

      // Strata Ores - Andesite
      genOre(biome, 20, 0, 0, 128, ANDESITE, BlockHandler.ORE_COAL_ANDESITE.get().getDefaultState(), 17);
      genOre(biome, 20, 0, 0, 64, ANDESITE, BlockHandler.ORE_IRON_ANDESITE.get().getDefaultState(), 9);
      genOre(biome, 2, 0, 0, 32, ANDESITE, BlockHandler.ORE_GOLD_ANDESITE.get().getDefaultState(), 9);
      genOre(biome, 1, 0, 0, 16, ANDESITE, BlockHandler.ORE_DIAMOND_ANDESITE.get().getDefaultState(), 8);
      genOre(biome, 1, 0, 0, 16, ANDESITE, BlockHandler.ORE_EMERALD_ANDESITE.get().getDefaultState(), 4);
      genOre(biome, 2, 0, 0, 16, ANDESITE, BlockHandler.ORE_LAPIS_ANDESITE.get().getDefaultState(), 7);
      genOre(biome, 8, 0, 0, 16, ANDESITE, BlockHandler.ORE_REDSTONE_ANDESITE.get().getDefaultState(), 8);
      genOre(biome, 20, 45, 0, 60, ANDESITE, BlockHandler.ORE_COPPER_ANDESITE.get().getDefaultState(), 7);
      genOre(biome, 8, 50, 0, 70, ANDESITE, BlockHandler.ORE_ALUMINUM_ANDESITE.get().getDefaultState(), 3);
      genOre(biome, 8, 30, 0, 38, ANDESITE, BlockHandler.ORE_SILVER_ANDESITE.get().getDefaultState(), 4);
      genOre(biome, 8, 32, 0, 40, ANDESITE, BlockHandler.ORE_LEAD_ANDESITE.get().getDefaultState(), 3);
      genOre(biome, 8, 25, 0, 40, ANDESITE, BlockHandler.ORE_NICKEL_ANDESITE.get().getDefaultState(), 3);
      genOre(biome, 4, 5, 0, 20, ANDESITE, BlockHandler.ORE_URANIUM_ANDESITE.get().getDefaultState(), 3);
      genOre(biome, 20, 20, 0, 45, ANDESITE, BlockHandler.ORE_OSMIUM_ANDESITE.get().getDefaultState(), 6);
      genOre(biome, 20, 40, 0, 55, ANDESITE, BlockHandler.ORE_TIN_ANDESITE.get().getDefaultState(), 6);
      genOre(biome, 8, 35, 0, 50, ANDESITE, BlockHandler.ORE_ZINC_ANDESITE.get().getDefaultState(), 4);

      if(blockCreateGabbro != null) {
        // Strata Ores - Gabbro
        genOre(biome, 20, 0, 0, 128, CREATE_GABBRO, BlockHandler.ORE_COAL_GABBRO.get().getDefaultState(), 17);
        genOre(biome, 20, 0, 0, 64, CREATE_GABBRO, BlockHandler.ORE_IRON_GABBRO.get().getDefaultState(), 9);
        genOre(biome, 2, 0, 0, 32, CREATE_GABBRO, BlockHandler.ORE_GOLD_GABBRO.get().getDefaultState(), 9);
        genOre(biome, 1, 0, 0, 16, CREATE_GABBRO, BlockHandler.ORE_DIAMOND_GABBRO.get().getDefaultState(), 8);
        genOre(biome, 1, 0, 0, 16, CREATE_GABBRO, BlockHandler.ORE_EMERALD_GABBRO.get().getDefaultState(), 4);
        genOre(biome, 2, 0, 0, 16, CREATE_GABBRO, BlockHandler.ORE_LAPIS_GABBRO.get().getDefaultState(), 7);
        genOre(biome, 8, 0, 0, 16, CREATE_GABBRO, BlockHandler.ORE_REDSTONE_GABBRO.get().getDefaultState(), 8);
        genOre(biome, 20, 45, 0, 60, CREATE_GABBRO, BlockHandler.ORE_COPPER_GABBRO.get().getDefaultState(), 7);
        genOre(biome, 8, 50, 0, 70, CREATE_GABBRO, BlockHandler.ORE_ALUMINUM_GABBRO.get().getDefaultState(), 3);
        genOre(biome, 8, 30, 0, 38, CREATE_GABBRO, BlockHandler.ORE_SILVER_GABBRO.get().getDefaultState(), 4);
        genOre(biome, 8, 32, 0, 40, CREATE_GABBRO, BlockHandler.ORE_LEAD_GABBRO.get().getDefaultState(), 3);
        genOre(biome, 8, 25, 0, 40, CREATE_GABBRO, BlockHandler.ORE_NICKEL_GABBRO.get().getDefaultState(), 3);
        genOre(biome, 4, 5, 0, 20, CREATE_GABBRO, BlockHandler.ORE_URANIUM_GABBRO.get().getDefaultState(), 3);
        genOre(biome, 20, 20, 0, 45, CREATE_GABBRO, BlockHandler.ORE_OSMIUM_GABBRO.get().getDefaultState(), 6);
        genOre(biome, 20, 40, 0, 55, CREATE_GABBRO, BlockHandler.ORE_TIN_GABBRO.get().getDefaultState(), 6);
        genOre(biome, 8, 35, 0, 50, CREATE_GABBRO, BlockHandler.ORE_ZINC_GABBRO.get().getDefaultState(), 4);
      }
      if(blockCreateLimestone != null) {
        // Strata Ores - Limestone
        genOre(biome, 20, 0, 0, 128, CREATE_LIMESTONE, BlockHandler.ORE_COAL_C_LIMESTONE.get().getDefaultState(), 17);
        genOre(biome, 20, 0, 0, 64, CREATE_LIMESTONE, BlockHandler.ORE_IRON_C_LIMESTONE.get().getDefaultState(), 9);
        genOre(biome, 2, 0, 0, 32, CREATE_LIMESTONE, BlockHandler.ORE_GOLD_C_LIMESTONE.get().getDefaultState(), 9);
        genOre(biome, 1, 0, 0, 16, CREATE_LIMESTONE, BlockHandler.ORE_DIAMOND_C_LIMESTONE.get().getDefaultState(), 8);
        genOre(biome, 1, 0, 0, 16, CREATE_LIMESTONE, BlockHandler.ORE_EMERALD_C_LIMESTONE.get().getDefaultState(), 4);
        genOre(biome, 2, 0, 0, 16, CREATE_LIMESTONE, BlockHandler.ORE_LAPIS_C_LIMESTONE.get().getDefaultState(), 7);
        genOre(biome, 8, 0, 0, 16, CREATE_LIMESTONE, BlockHandler.ORE_REDSTONE_C_LIMESTONE.get().getDefaultState(), 8);
        genOre(biome, 20, 45, 0, 60, CREATE_LIMESTONE, BlockHandler.ORE_COPPER_C_LIMESTONE.get().getDefaultState(), 7);
        genOre(biome, 8, 50, 0, 70, CREATE_LIMESTONE, BlockHandler.ORE_ALUMINUM_C_LIMESTONE.get().getDefaultState(), 3);
        genOre(biome, 8, 30, 0, 38, CREATE_LIMESTONE, BlockHandler.ORE_SILVER_C_LIMESTONE.get().getDefaultState(), 4);
        genOre(biome, 8, 32, 0, 40, CREATE_LIMESTONE, BlockHandler.ORE_LEAD_C_LIMESTONE.get().getDefaultState(), 3);
        genOre(biome, 8, 25, 0, 40, CREATE_LIMESTONE, BlockHandler.ORE_NICKEL_C_LIMESTONE.get().getDefaultState(), 3);
        genOre(biome, 4, 5, 0, 20, CREATE_LIMESTONE, BlockHandler.ORE_URANIUM_C_LIMESTONE.get().getDefaultState(), 3);
        genOre(biome, 20, 20, 0, 45, CREATE_LIMESTONE, BlockHandler.ORE_OSMIUM_C_LIMESTONE.get().getDefaultState(), 6);
        genOre(biome, 20, 40, 0, 55, CREATE_LIMESTONE, BlockHandler.ORE_TIN_C_LIMESTONE.get().getDefaultState(), 6);
        genOre(biome, 8, 35, 0, 50, CREATE_LIMESTONE, BlockHandler.ORE_ZINC_C_LIMESTONE.get().getDefaultState(), 4);
      }
      if(blockCreateScoria != null) {
        // Strata Ores - Scoria
        genOre(biome, 20, 0, 0, 128, CREATE_SCORIA, BlockHandler.ORE_COAL_SCORIA.get().getDefaultState(), 17);
        genOre(biome, 20, 0, 0, 64, CREATE_SCORIA, BlockHandler.ORE_IRON_SCORIA.get().getDefaultState(), 9);
        genOre(biome, 2, 0, 0, 32, CREATE_SCORIA, BlockHandler.ORE_GOLD_SCORIA.get().getDefaultState(), 9);
        genOre(biome, 1, 0, 0, 16, CREATE_SCORIA, BlockHandler.ORE_DIAMOND_SCORIA.get().getDefaultState(), 8);
        genOre(biome, 1, 0, 0, 16, CREATE_SCORIA, BlockHandler.ORE_EMERALD_SCORIA.get().getDefaultState(), 4);
        genOre(biome, 2, 0, 0, 16, CREATE_SCORIA, BlockHandler.ORE_LAPIS_SCORIA.get().getDefaultState(), 7);
        genOre(biome, 8, 0, 0, 16, CREATE_SCORIA, BlockHandler.ORE_REDSTONE_SCORIA.get().getDefaultState(), 8);
        genOre(biome, 20, 45, 0, 60, CREATE_SCORIA, BlockHandler.ORE_COPPER_SCORIA.get().getDefaultState(), 7);
        genOre(biome, 8, 50, 0, 70, CREATE_SCORIA, BlockHandler.ORE_ALUMINUM_SCORIA.get().getDefaultState(), 3);
        genOre(biome, 8, 30, 0, 38, CREATE_SCORIA, BlockHandler.ORE_SILVER_SCORIA.get().getDefaultState(), 4);
        genOre(biome, 8, 32, 0, 40, CREATE_SCORIA, BlockHandler.ORE_LEAD_SCORIA.get().getDefaultState(), 3);
        genOre(biome, 8, 25, 0, 40, CREATE_SCORIA, BlockHandler.ORE_NICKEL_SCORIA.get().getDefaultState(), 3);
        genOre(biome, 4, 5, 0, 20, CREATE_SCORIA, BlockHandler.ORE_URANIUM_SCORIA.get().getDefaultState(), 3);
        genOre(biome, 20, 20, 0, 45, CREATE_SCORIA, BlockHandler.ORE_OSMIUM_SCORIA.get().getDefaultState(), 6);
        genOre(biome, 20, 40, 0, 55, CREATE_SCORIA, BlockHandler.ORE_TIN_SCORIA.get().getDefaultState(), 6);
        genOre(biome, 8, 35, 0, 50, CREATE_SCORIA, BlockHandler.ORE_ZINC_SCORIA.get().getDefaultState(), 4);
      }
      if(blockCreateWeatheredLimestone != null) {
        // Strata Ores - Scoria
        genOre(biome, 20, 0, 0, 128, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_COAL_WEATHERED_LIMESTONE.get().getDefaultState(), 17);
        genOre(biome, 20, 0, 0, 64, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_IRON_WEATHERED_LIMESTONE.get().getDefaultState(), 9);
        genOre(biome, 2, 0, 0, 32, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_GOLD_WEATHERED_LIMESTONE.get().getDefaultState(), 9);
        genOre(biome, 1, 0, 0, 16, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_DIAMOND_WEATHERED_LIMESTONE.get().getDefaultState(), 8);
        genOre(biome, 1, 0, 0, 16, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_EMERALD_WEATHERED_LIMESTONE.get().getDefaultState(), 4);
        genOre(biome, 2, 0, 0, 16, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_LAPIS_WEATHERED_LIMESTONE.get().getDefaultState(), 7);
        genOre(biome, 8, 0, 0, 16, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_REDSTONE_WEATHERED_LIMESTONE.get().getDefaultState(), 8);
        genOre(biome, 20, 45, 0, 60, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_COPPER_WEATHERED_LIMESTONE.get().getDefaultState(), 7);
        genOre(biome, 8, 50, 0, 70, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_ALUMINUM_WEATHERED_LIMESTONE.get().getDefaultState(), 3);
        genOre(biome, 8, 30, 0, 38, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_SILVER_WEATHERED_LIMESTONE.get().getDefaultState(), 4);
        genOre(biome, 8, 32, 0, 40, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_LEAD_WEATHERED_LIMESTONE.get().getDefaultState(), 3);
        genOre(biome, 8, 25, 0, 40, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_NICKEL_WEATHERED_LIMESTONE.get().getDefaultState(), 3);
        genOre(biome, 4, 5, 0, 20, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_URANIUM_WEATHERED_LIMESTONE.get().getDefaultState(), 3);
        genOre(biome, 20, 20, 0, 45, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_OSMIUM_WEATHERED_LIMESTONE.get().getDefaultState(), 6);
        genOre(biome, 20, 40, 0, 55, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_TIN_WEATHERED_LIMESTONE.get().getDefaultState(), 6);
        genOre(biome, 8, 35, 0, 50, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_ZINC_WEATHERED_LIMESTONE.get().getDefaultState(), 4);
      }
      if(blockQuarkJasper != null) {
        // Strata Ores - Jasper
        genOre(biome, 20, 0, 0, 128, QUARK_JASPER, BlockHandler.ORE_COAL_JASPER.get().getDefaultState(), 17);
        genOre(biome, 20, 0, 0, 64, QUARK_JASPER, BlockHandler.ORE_IRON_JASPER.get().getDefaultState(), 9);
        genOre(biome, 2, 0, 0, 32, QUARK_JASPER, BlockHandler.ORE_GOLD_JASPER.get().getDefaultState(), 9);
        genOre(biome, 1, 0, 0, 16, QUARK_JASPER, BlockHandler.ORE_DIAMOND_JASPER.get().getDefaultState(), 8);
        genOre(biome, 1, 0, 0, 16, QUARK_JASPER, BlockHandler.ORE_EMERALD_JASPER.get().getDefaultState(), 4);
        genOre(biome, 2, 0, 0, 16, QUARK_JASPER, BlockHandler.ORE_LAPIS_JASPER.get().getDefaultState(), 7);
        genOre(biome, 8, 0, 0, 16, QUARK_JASPER, BlockHandler.ORE_REDSTONE_JASPER.get().getDefaultState(), 8);
        genOre(biome, 20, 45, 0, 60, QUARK_JASPER, BlockHandler.ORE_COPPER_JASPER.get().getDefaultState(), 7);
        genOre(biome, 8, 50, 0, 70, QUARK_JASPER, BlockHandler.ORE_ALUMINUM_JASPER.get().getDefaultState(), 3);
        genOre(biome, 8, 30, 0, 38, QUARK_JASPER, BlockHandler.ORE_SILVER_JASPER.get().getDefaultState(), 4);
        genOre(biome, 8, 32, 0, 40, QUARK_JASPER, BlockHandler.ORE_LEAD_JASPER.get().getDefaultState(), 3);
        genOre(biome, 8, 25, 0, 40, QUARK_JASPER, BlockHandler.ORE_NICKEL_JASPER.get().getDefaultState(), 3);
        genOre(biome, 4, 5, 0, 20, QUARK_JASPER, BlockHandler.ORE_URANIUM_JASPER.get().getDefaultState(), 3);
        genOre(biome, 20, 20, 0, 45, QUARK_JASPER, BlockHandler.ORE_OSMIUM_JASPER.get().getDefaultState(), 6);
        genOre(biome, 20, 40, 0, 55, QUARK_JASPER, BlockHandler.ORE_TIN_JASPER.get().getDefaultState(), 6);
        genOre(biome, 8, 35, 0, 50, QUARK_JASPER, BlockHandler.ORE_ZINC_JASPER.get().getDefaultState(), 4);
      }
      if(blockQuarkLimestone != null) {
        // Strata Ores - Limestone (Quark)
        genOre(biome, 20, 0, 0, 128, QUARK_LIMESTONE, BlockHandler.ORE_COAL_Q_LIMESTONE.get().getDefaultState(), 17);
        genOre(biome, 20, 0, 0, 64, QUARK_LIMESTONE, BlockHandler.ORE_IRON_Q_LIMESTONE.get().getDefaultState(), 9);
        genOre(biome, 2, 0, 0, 32, QUARK_LIMESTONE, BlockHandler.ORE_GOLD_Q_LIMESTONE.get().getDefaultState(), 9);
        genOre(biome, 1, 0, 0, 16, QUARK_LIMESTONE, BlockHandler.ORE_DIAMOND_Q_LIMESTONE.get().getDefaultState(), 8);
        genOre(biome, 1, 0, 0, 16, QUARK_LIMESTONE, BlockHandler.ORE_EMERALD_Q_LIMESTONE.get().getDefaultState(), 4);
        genOre(biome, 2, 0, 0, 16, QUARK_LIMESTONE, BlockHandler.ORE_LAPIS_Q_LIMESTONE.get().getDefaultState(), 7);
        genOre(biome, 8, 0, 0, 16, QUARK_LIMESTONE, BlockHandler.ORE_REDSTONE_Q_LIMESTONE.get().getDefaultState(), 8);
        genOre(biome, 20, 45, 0, 60, QUARK_LIMESTONE, BlockHandler.ORE_COPPER_Q_LIMESTONE.get().getDefaultState(), 7);
        genOre(biome, 8, 50, 0, 70, QUARK_LIMESTONE, BlockHandler.ORE_ALUMINUM_Q_LIMESTONE.get().getDefaultState(), 3);
        genOre(biome, 8, 30, 0, 38, QUARK_LIMESTONE, BlockHandler.ORE_SILVER_Q_LIMESTONE.get().getDefaultState(), 4);
        genOre(biome, 8, 32, 0, 40, QUARK_LIMESTONE, BlockHandler.ORE_LEAD_Q_LIMESTONE.get().getDefaultState(), 3);
        genOre(biome, 8, 25, 0, 40, QUARK_LIMESTONE, BlockHandler.ORE_NICKEL_Q_LIMESTONE.get().getDefaultState(), 3);
        genOre(biome, 4, 5, 0, 20, QUARK_LIMESTONE, BlockHandler.ORE_URANIUM_Q_LIMESTONE.get().getDefaultState(), 3);
        genOre(biome, 20, 20, 0, 45, QUARK_LIMESTONE, BlockHandler.ORE_OSMIUM_Q_LIMESTONE.get().getDefaultState(), 6);
        genOre(biome, 20, 40, 0, 55, QUARK_LIMESTONE, BlockHandler.ORE_TIN_Q_LIMESTONE.get().getDefaultState(), 6);
        genOre(biome, 8, 35, 0, 50, QUARK_LIMESTONE, BlockHandler.ORE_ZINC_Q_LIMESTONE.get().getDefaultState(), 4);
      }
      if(blockQuarkMarble != null) {
        // Strata Ores - Marble
        genOre(biome, 20, 0, 0, 128, QUARK_MARBLE, BlockHandler.ORE_COAL_MARBLE.get().getDefaultState(), 17);
        genOre(biome, 20, 0, 0, 64, QUARK_MARBLE, BlockHandler.ORE_IRON_MARBLE.get().getDefaultState(), 9);
        genOre(biome, 2, 0, 0, 32, QUARK_MARBLE, BlockHandler.ORE_GOLD_MARBLE.get().getDefaultState(), 9);
        genOre(biome, 1, 0, 0, 16, QUARK_MARBLE, BlockHandler.ORE_DIAMOND_MARBLE.get().getDefaultState(), 8);
        genOre(biome, 1, 0, 0, 16, QUARK_MARBLE, BlockHandler.ORE_EMERALD_MARBLE.get().getDefaultState(), 4);
        genOre(biome, 2, 0, 0, 16, QUARK_MARBLE, BlockHandler.ORE_LAPIS_MARBLE.get().getDefaultState(), 7);
        genOre(biome, 8, 0, 0, 16, QUARK_MARBLE, BlockHandler.ORE_REDSTONE_MARBLE.get().getDefaultState(), 8);
        genOre(biome, 20, 45, 0, 60, QUARK_MARBLE, BlockHandler.ORE_COPPER_MARBLE.get().getDefaultState(), 7);
        genOre(biome, 8, 50, 0, 70, QUARK_MARBLE, BlockHandler.ORE_ALUMINUM_MARBLE.get().getDefaultState(), 3);
        genOre(biome, 8, 30, 0, 38, QUARK_MARBLE, BlockHandler.ORE_SILVER_MARBLE.get().getDefaultState(), 4);
        genOre(biome, 8, 32, 0, 40, QUARK_MARBLE, BlockHandler.ORE_LEAD_MARBLE.get().getDefaultState(), 3);
        genOre(biome, 8, 25, 0, 40, QUARK_MARBLE, BlockHandler.ORE_NICKEL_MARBLE.get().getDefaultState(), 3);
        genOre(biome, 4, 5, 0, 20, QUARK_MARBLE, BlockHandler.ORE_URANIUM_MARBLE.get().getDefaultState(), 3);
        genOre(biome, 20, 20, 0, 45, QUARK_MARBLE, BlockHandler.ORE_OSMIUM_MARBLE.get().getDefaultState(), 6);
        genOre(biome, 20, 40, 0, 55, QUARK_MARBLE, BlockHandler.ORE_TIN_MARBLE.get().getDefaultState(), 6);
        genOre(biome, 8, 35, 0, 50, QUARK_MARBLE, BlockHandler.ORE_ZINC_MARBLE.get().getDefaultState(), 4);
      }
      if(blockQuarkSlate != null) {
        // Strata Ores - Slate
        genOre(biome, 20, 0, 0, 128, QUARK_SLATE, BlockHandler.ORE_COAL_SLATE.get().getDefaultState(), 17);
        genOre(biome, 20, 0, 0, 64, QUARK_SLATE, BlockHandler.ORE_IRON_SLATE.get().getDefaultState(), 9);
        genOre(biome, 2, 0, 0, 32, QUARK_SLATE, BlockHandler.ORE_GOLD_SLATE.get().getDefaultState(), 9);
        genOre(biome, 1, 0, 0, 16, QUARK_SLATE, BlockHandler.ORE_DIAMOND_SLATE.get().getDefaultState(), 8);
        genOre(biome, 1, 0, 0, 16, QUARK_SLATE, BlockHandler.ORE_EMERALD_SLATE.get().getDefaultState(), 4);
        genOre(biome, 2, 0, 0, 16, QUARK_SLATE, BlockHandler.ORE_LAPIS_SLATE.get().getDefaultState(), 7);
        genOre(biome, 8, 0, 0, 16, QUARK_SLATE, BlockHandler.ORE_REDSTONE_SLATE.get().getDefaultState(), 8);
        genOre(biome, 20, 45, 0, 60, QUARK_SLATE, BlockHandler.ORE_COPPER_SLATE.get().getDefaultState(), 7);
        genOre(biome, 8, 50, 0, 70, QUARK_SLATE, BlockHandler.ORE_ALUMINUM_SLATE.get().getDefaultState(), 3);
        genOre(biome, 8, 30, 0, 38, QUARK_SLATE, BlockHandler.ORE_SILVER_SLATE.get().getDefaultState(), 4);
        genOre(biome, 8, 32, 0, 40, QUARK_SLATE, BlockHandler.ORE_LEAD_SLATE.get().getDefaultState(), 3);
        genOre(biome, 8, 25, 0, 40, QUARK_SLATE, BlockHandler.ORE_NICKEL_SLATE.get().getDefaultState(), 3);
        genOre(biome, 4, 5, 0, 20, QUARK_SLATE, BlockHandler.ORE_URANIUM_SLATE.get().getDefaultState(), 3);
        genOre(biome, 20, 20, 0, 45, QUARK_SLATE, BlockHandler.ORE_OSMIUM_SLATE.get().getDefaultState(), 6);
        genOre(biome, 20, 40, 0, 55, QUARK_SLATE, BlockHandler.ORE_TIN_SLATE.get().getDefaultState(), 6);
        genOre(biome, 8, 35, 0, 50, QUARK_SLATE, BlockHandler.ORE_ZINC_SLATE.get().getDefaultState(), 4);
      }
    }
  }

  private static void genOre(Biome biome, int count, int bottomOffset, int topOffset, int max, OreFeatureConfig.FillerBlockType filler, BlockState defaultBlockstate, int size) {
    CountRangeConfig range = new CountRangeConfig(count, bottomOffset, topOffset, max);
    OreFeatureConfig feature = new OreFeatureConfig(filler, defaultBlockstate, size);

    ConfiguredPlacement config = Placement.COUNT_RANGE.configure(range);

    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(feature).withPlacement(config));
  }

  public static void overrideFeatures(Biome biome){
    List<ConfiguredFeature> features = new ArrayList<ConfiguredFeature>();

    for (ConfiguredFeature<?,?> f : biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES)) {
      if (((DecoratedFeatureConfig)f.config).feature.feature instanceof OreFeature) {
        if (((OreFeatureConfig)((DecoratedFeatureConfig)f.config).feature.config).state.getBlock() == Blocks.COAL_ORE) {
          features.add(f);
        }
        if (((OreFeatureConfig)((DecoratedFeatureConfig)f.config).feature.config).state.getBlock() == Blocks.IRON_ORE) {
          features.add(f);
        }
        if (((OreFeatureConfig)((DecoratedFeatureConfig)f.config).feature.config).state.getBlock() == Blocks.GOLD_ORE) {
          features.add(f);
        }
        if (((OreFeatureConfig)((DecoratedFeatureConfig)f.config).feature.config).state.getBlock() == Blocks.DIAMOND_ORE) {
          features.add(f);
        }
        if (((OreFeatureConfig)((DecoratedFeatureConfig)f.config).feature.config).state.getBlock() == Blocks.EMERALD_ORE) {
          features.add(f);
        }
        if (((OreFeatureConfig)((DecoratedFeatureConfig)f.config).feature.config).state.getBlock() == Blocks.LAPIS_ORE) {
          features.add(f);
        }
        if (((OreFeatureConfig)((DecoratedFeatureConfig)f.config).feature.config).state.getBlock() == Blocks.REDSTONE_ORE) {
          features.add(f);
        }
      }
    }
    biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).removeAll(features);
  }
}