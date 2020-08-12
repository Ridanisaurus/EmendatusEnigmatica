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

import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.config.WorldGenConfig;
import com.ridanisaurus.emendatusenigmatica.registries.BlockHandler;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.resources.IResource;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WorldGenHandler {

  // Fillers
  private static final OreFeatureConfig.FillerBlockType VANILLA_STONE = OreFeatureConfig.FillerBlockType.create("EE_STONE", "ee_stone", BlockMatcher.forBlock(Blocks.STONE));
  private static final OreFeatureConfig.FillerBlockType VANILLA_ANDESITE = OreFeatureConfig.FillerBlockType.create("EE_ANDESITE", "ee_andesite", BlockMatcher.forBlock(Blocks.ANDESITE));
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
      CREATE_GABBRO = OreFeatureConfig.FillerBlockType.create("EE_GABBRO", "ee_gabbro", new BlockMatcher(blockCreateGabbro.getBlock()));
    }
    if (blockCreateLimestone != null) {
      CREATE_LIMESTONE = OreFeatureConfig.FillerBlockType.create("EE_C_LIMESTONE", "ee_c_limestone", new BlockMatcher(blockCreateLimestone.getBlock()));
    }
    if (blockCreateScoria != null) {
      CREATE_SCORIA = OreFeatureConfig.FillerBlockType.create("EE_SCORIA", "ee_scoria", new BlockMatcher(blockCreateScoria.getBlock()));
    }
    if (blockCreateWeatheredLimestone != null) {
      CREATE_WEATHERED_LIMESTONE = OreFeatureConfig.FillerBlockType.create("EE_WEATHERED_LIMESTONE", "ee_weathered_limestone", new BlockMatcher(blockCreateWeatheredLimestone.getBlock()));
    }
    if (blockQuarkJasper != null) {
      QUARK_JASPER = OreFeatureConfig.FillerBlockType.create("EE_JASPER", "ee_jasper", new BlockMatcher(blockQuarkJasper.getBlock()));
    }
    if (blockQuarkLimestone != null) {
      QUARK_LIMESTONE = OreFeatureConfig.FillerBlockType.create("EE_Q_LIMESTONE", "ee_q_limestone", new BlockMatcher(blockQuarkLimestone.getBlock()));
    }
    if (blockQuarkMarble != null) {
      QUARK_MARBLE = OreFeatureConfig.FillerBlockType.create("EE_MARBLE", "ee_marble", new BlockMatcher(blockQuarkMarble.getBlock()));
    }
    if (blockQuarkSlate != null) {
      QUARK_SLATE = OreFeatureConfig.FillerBlockType.create("EE_SLATE", "ee_slate", new BlockMatcher(blockQuarkSlate.getBlock()));
    }

    for (Biome biome : ForgeRegistries.BIOMES) {
      overrideFeatures(biome);

      // Strata Ores - Stone
      if(WorldGenConfig.VANILLA_STONE_STATE) {
        if(WorldGenConfig.COAL_STATE) {
          genOre(biome, WorldGenConfig.COAL_COUNT, WorldGenConfig.COAL_BOFFSET, WorldGenConfig.COAL_MAX, VANILLA_STONE, BlockHandler.ORE_COAL.get().getDefaultState(), WorldGenConfig.COAL_SIZE);
        }
        if(WorldGenConfig.IRON_STATE) {
          genOre(biome, WorldGenConfig.IRON_COUNT, WorldGenConfig.IRON_BOFFSET, WorldGenConfig.IRON_MAX, VANILLA_STONE, BlockHandler.ORE_IRON.get().getDefaultState(), WorldGenConfig.IRON_SIZE);
        }
        if(WorldGenConfig.GOLD_STATE) {
          genOre(biome, WorldGenConfig.GOLD_COUNT, WorldGenConfig.GOLD_BOFFSET, WorldGenConfig.GOLD_MAX, VANILLA_STONE, BlockHandler.ORE_GOLD.get().getDefaultState(), WorldGenConfig.GOLD_SIZE);
        }
        if(WorldGenConfig.DIAMOND_STATE) {
          genOre(biome, WorldGenConfig.DIAMOND_COUNT, WorldGenConfig.DIAMOND_BOFFSET, WorldGenConfig.DIAMOND_MAX, VANILLA_STONE, BlockHandler.ORE_DIAMOND.get().getDefaultState(), WorldGenConfig.DIAMOND_SIZE);
        }
        if(WorldGenConfig.EMERALD_STATE) {
          genOre(biome, WorldGenConfig.EMERALD_COUNT, WorldGenConfig.EMERALD_BOFFSET, WorldGenConfig.EMERALD_MAX, VANILLA_STONE, BlockHandler.ORE_EMERALD.get().getDefaultState(), WorldGenConfig.EMERALD_SIZE);
        }
        if(WorldGenConfig.LAPIS_STATE) {
          genOre(biome, WorldGenConfig.LAPIS_COUNT, WorldGenConfig.LAPIS_BOFFSET, WorldGenConfig.LAPIS_MAX, VANILLA_STONE, BlockHandler.ORE_LAPIS.get().getDefaultState(), WorldGenConfig.LAPIS_SIZE);
        }
        if(WorldGenConfig.REDSTONE_STATE) {
          genOre(biome, WorldGenConfig.REDSTONE_COUNT, WorldGenConfig.REDSTONE_BOFFSET, WorldGenConfig.REDSTONE_MAX, VANILLA_STONE, BlockHandler.ORE_REDSTONE.get().getDefaultState(), WorldGenConfig.REDSTONE_SIZE);
        }
        if(WorldGenConfig.COPPER_STATE) {
          genOre(biome, WorldGenConfig.COPPER_COUNT, WorldGenConfig.COPPER_BOFFSET, WorldGenConfig.COPPER_MAX, VANILLA_STONE, BlockHandler.ORE_COPPER.get().getDefaultState(), WorldGenConfig.COPPER_SIZE);
        }
        if(WorldGenConfig.ALUMINUM_STATE) {
          genOre(biome, WorldGenConfig.ALUMINUM_COUNT, WorldGenConfig.ALUMINUM_BOFFSET, WorldGenConfig.ALUMINUM_MAX, VANILLA_STONE, BlockHandler.ORE_ALUMINUM.get().getDefaultState(), WorldGenConfig.ALUMINUM_SIZE);
        }
        if(WorldGenConfig.SILVER_STATE) {
          genOre(biome, WorldGenConfig.SILVER_COUNT, WorldGenConfig.SILVER_BOFFSET, WorldGenConfig.SILVER_MAX, VANILLA_STONE, BlockHandler.ORE_SILVER.get().getDefaultState(), WorldGenConfig.SILVER_SIZE);
        }
        if(WorldGenConfig.LEAD_STATE) {
          genOre(biome, WorldGenConfig.LEAD_COUNT, WorldGenConfig.LEAD_BOFFSET, WorldGenConfig.LEAD_MAX, VANILLA_STONE, BlockHandler.ORE_LEAD.get().getDefaultState(), WorldGenConfig.LEAD_SIZE);
        }
        if(WorldGenConfig.NICKEL_STATE) {
          genOre(biome, WorldGenConfig.NICKEL_COUNT, WorldGenConfig.NICKEL_BOFFSET, WorldGenConfig.NICKEL_MAX, VANILLA_STONE, BlockHandler.ORE_NICKEL.get().getDefaultState(), WorldGenConfig.NICKEL_SIZE);
        }
        if(WorldGenConfig.URANIUM_STATE) {
          genOre(biome, WorldGenConfig.URANIUM_COUNT, WorldGenConfig.URANIUM_BOFFSET, WorldGenConfig.URANIUM_MAX, VANILLA_STONE, BlockHandler.ORE_URANIUM.get().getDefaultState(), WorldGenConfig.URANIUM_SIZE);
        }
        if(WorldGenConfig.OSMIUM_STATE) {
          genOre(biome, WorldGenConfig.OSMIUM_COUNT, WorldGenConfig.OSMIUM_BOFFSET, WorldGenConfig.OSMIUM_MAX, VANILLA_STONE, BlockHandler.ORE_OSMIUM.get().getDefaultState(), WorldGenConfig.OSMIUM_SIZE);
        }
        if(WorldGenConfig.TIN_STATE) {
          genOre(biome, WorldGenConfig.TIN_COUNT, WorldGenConfig.TIN_BOFFSET, WorldGenConfig.TIN_MAX, VANILLA_STONE, BlockHandler.ORE_TIN.get().getDefaultState(), WorldGenConfig.TIN_SIZE);
        }
        if(WorldGenConfig.ZINC_STATE) {
          genOre(biome, WorldGenConfig.ZINC_COUNT, WorldGenConfig.ZINC_BOFFSET, WorldGenConfig.ZINC_MAX, VANILLA_STONE, BlockHandler.ORE_ZINC.get().getDefaultState(), WorldGenConfig.ZINC_SIZE);
        }
        if(WorldGenConfig.CERTUS_QUARTZ_STATE) {
          genOre(biome, WorldGenConfig.CERTUS_QUARTZ_COUNT, WorldGenConfig.CERTUS_QUARTZ_BOFFSET, WorldGenConfig.CERTUS_QUARTZ_MAX, VANILLA_STONE, BlockHandler.ORE_CERTUS_QUARTZ.get().getDefaultState(), WorldGenConfig.CERTUS_QUARTZ_SIZE);
        }
        if(WorldGenConfig.CHARGED_CERTUS_QUARTZ_STATE) {
          genOre(biome, WorldGenConfig.CHARGED_CERTUS_QUARTZ_COUNT, WorldGenConfig.CHARGED_CERTUS_QUARTZ_BOFFSET, WorldGenConfig.CHARGED_CERTUS_QUARTZ_MAX, VANILLA_STONE, BlockHandler.ORE_CHARGED_CERTUS_QUARTZ.get().getDefaultState(), WorldGenConfig.CHARGED_CERTUS_QUARTZ_SIZE);
        }
      }
      // Strata Ores - Andesite
      if(WorldGenConfig.VANILLA_ANDESITE_STATE) {
        if(WorldGenConfig.COAL_STATE) {
          genOre(biome, WorldGenConfig.COAL_COUNT, WorldGenConfig.COAL_BOFFSET, WorldGenConfig.COAL_MAX, VANILLA_ANDESITE, BlockHandler.ORE_COAL_ANDESITE.get().getDefaultState(), WorldGenConfig.COAL_SIZE);
        }
        if(WorldGenConfig.IRON_STATE) {
          genOre(biome, WorldGenConfig.IRON_COUNT, WorldGenConfig.IRON_BOFFSET, WorldGenConfig.IRON_MAX, VANILLA_ANDESITE, BlockHandler.ORE_IRON_ANDESITE.get().getDefaultState(), WorldGenConfig.IRON_SIZE);
        }
        if(WorldGenConfig.GOLD_STATE) {
          genOre(biome, WorldGenConfig.GOLD_COUNT, WorldGenConfig.GOLD_BOFFSET, WorldGenConfig.GOLD_MAX, VANILLA_ANDESITE, BlockHandler.ORE_GOLD_ANDESITE.get().getDefaultState(), WorldGenConfig.GOLD_SIZE);
        }
        if(WorldGenConfig.DIAMOND_STATE) {
          genOre(biome, WorldGenConfig.DIAMOND_COUNT, WorldGenConfig.DIAMOND_BOFFSET, WorldGenConfig.DIAMOND_MAX, VANILLA_ANDESITE, BlockHandler.ORE_DIAMOND_ANDESITE.get().getDefaultState(), WorldGenConfig.DIAMOND_SIZE);
        }
        if(WorldGenConfig.EMERALD_STATE) {
          genOre(biome, WorldGenConfig.EMERALD_COUNT, WorldGenConfig.EMERALD_BOFFSET, WorldGenConfig.EMERALD_MAX, VANILLA_ANDESITE, BlockHandler.ORE_EMERALD_ANDESITE.get().getDefaultState(), WorldGenConfig.EMERALD_SIZE);
        }
        if(WorldGenConfig.LAPIS_STATE) {
          genOre(biome, WorldGenConfig.LAPIS_COUNT, WorldGenConfig.LAPIS_BOFFSET, WorldGenConfig.LAPIS_MAX, VANILLA_ANDESITE, BlockHandler.ORE_LAPIS_ANDESITE.get().getDefaultState(), WorldGenConfig.LAPIS_SIZE);
        }
        if(WorldGenConfig.REDSTONE_STATE) {
          genOre(biome, WorldGenConfig.REDSTONE_COUNT, WorldGenConfig.REDSTONE_BOFFSET, WorldGenConfig.REDSTONE_MAX, VANILLA_ANDESITE, BlockHandler.ORE_REDSTONE_ANDESITE.get().getDefaultState(), WorldGenConfig.REDSTONE_SIZE);
        }
        if(WorldGenConfig.COPPER_STATE) {
          genOre(biome, WorldGenConfig.COPPER_COUNT, WorldGenConfig.COPPER_BOFFSET, WorldGenConfig.COPPER_MAX, VANILLA_ANDESITE, BlockHandler.ORE_COPPER_ANDESITE.get().getDefaultState(), WorldGenConfig.COPPER_SIZE);
        }
        if(WorldGenConfig.ALUMINUM_STATE) {
          genOre(biome, WorldGenConfig.ALUMINUM_COUNT, WorldGenConfig.ALUMINUM_BOFFSET, WorldGenConfig.ALUMINUM_MAX, VANILLA_ANDESITE, BlockHandler.ORE_ALUMINUM_ANDESITE.get().getDefaultState(), WorldGenConfig.ALUMINUM_SIZE);
        }
        if(WorldGenConfig.SILVER_STATE) {
          genOre(biome, WorldGenConfig.SILVER_COUNT, WorldGenConfig.SILVER_BOFFSET, WorldGenConfig.SILVER_MAX, VANILLA_ANDESITE, BlockHandler.ORE_SILVER_ANDESITE.get().getDefaultState(), WorldGenConfig.SILVER_SIZE);
        }
        if(WorldGenConfig.LEAD_STATE) {
          genOre(biome, WorldGenConfig.LEAD_COUNT, WorldGenConfig.LEAD_BOFFSET, WorldGenConfig.LEAD_MAX, VANILLA_ANDESITE, BlockHandler.ORE_LEAD_ANDESITE.get().getDefaultState(), WorldGenConfig.LEAD_SIZE);
        }
        if(WorldGenConfig.NICKEL_STATE) {
          genOre(biome, WorldGenConfig.NICKEL_COUNT, WorldGenConfig.NICKEL_BOFFSET, WorldGenConfig.NICKEL_MAX, VANILLA_ANDESITE, BlockHandler.ORE_NICKEL_ANDESITE.get().getDefaultState(), WorldGenConfig.NICKEL_SIZE);
        }
        if(WorldGenConfig.URANIUM_STATE) {
          genOre(biome, WorldGenConfig.URANIUM_COUNT, WorldGenConfig.URANIUM_BOFFSET, WorldGenConfig.URANIUM_MAX, VANILLA_ANDESITE, BlockHandler.ORE_URANIUM_ANDESITE.get().getDefaultState(), WorldGenConfig.URANIUM_SIZE);
        }
        if(WorldGenConfig.OSMIUM_STATE) {
          genOre(biome, WorldGenConfig.OSMIUM_COUNT, WorldGenConfig.OSMIUM_BOFFSET, WorldGenConfig.OSMIUM_MAX, VANILLA_ANDESITE, BlockHandler.ORE_OSMIUM_ANDESITE.get().getDefaultState(), WorldGenConfig.OSMIUM_SIZE);
        }
        if(WorldGenConfig.TIN_STATE) {
          genOre(biome, WorldGenConfig.TIN_COUNT, WorldGenConfig.TIN_BOFFSET, WorldGenConfig.TIN_MAX, VANILLA_ANDESITE, BlockHandler.ORE_TIN_ANDESITE.get().getDefaultState(), WorldGenConfig.TIN_SIZE);
        }
        if(WorldGenConfig.ZINC_STATE) {
          genOre(biome, WorldGenConfig.ZINC_COUNT, WorldGenConfig.ZINC_BOFFSET, WorldGenConfig.ZINC_MAX, VANILLA_ANDESITE, BlockHandler.ORE_ZINC_ANDESITE.get().getDefaultState(), WorldGenConfig.ZINC_SIZE);
        }
        if(WorldGenConfig.CERTUS_QUARTZ_STATE) {
          genOre(biome, WorldGenConfig.CERTUS_QUARTZ_COUNT, WorldGenConfig.CERTUS_QUARTZ_BOFFSET, WorldGenConfig.CERTUS_QUARTZ_MAX, VANILLA_ANDESITE, BlockHandler.ORE_CERTUS_QUARTZ_ANDESITE.get().getDefaultState(), WorldGenConfig.CERTUS_QUARTZ_SIZE);
        }
        if(WorldGenConfig.CHARGED_CERTUS_QUARTZ_STATE) {
          genOre(biome, WorldGenConfig.CHARGED_CERTUS_QUARTZ_COUNT, WorldGenConfig.CHARGED_CERTUS_QUARTZ_BOFFSET, WorldGenConfig.CHARGED_CERTUS_QUARTZ_MAX, VANILLA_ANDESITE, BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_ANDESITE.get().getDefaultState(), WorldGenConfig.CHARGED_CERTUS_QUARTZ_SIZE);
        }
      }
      if(blockCreateGabbro != null && WorldGenConfig.CREATE_GABBRO_STATE) {
        // Strata Ores - Gabbro
        if(WorldGenConfig.COAL_STATE) {
          genOre(biome, WorldGenConfig.COAL_COUNT, WorldGenConfig.COAL_BOFFSET, WorldGenConfig.COAL_MAX, CREATE_GABBRO, BlockHandler.ORE_COAL_GABBRO.get().getDefaultState(), WorldGenConfig.COAL_SIZE);
        }
        if(WorldGenConfig.IRON_STATE) {
          genOre(biome, WorldGenConfig.IRON_COUNT, WorldGenConfig.IRON_BOFFSET, WorldGenConfig.IRON_MAX, CREATE_GABBRO, BlockHandler.ORE_IRON_GABBRO.get().getDefaultState(), WorldGenConfig.IRON_SIZE);
        }
        if(WorldGenConfig.GOLD_STATE) {
          genOre(biome, WorldGenConfig.GOLD_COUNT, WorldGenConfig.GOLD_BOFFSET, WorldGenConfig.GOLD_MAX, CREATE_GABBRO, BlockHandler.ORE_GOLD_GABBRO.get().getDefaultState(), WorldGenConfig.GOLD_SIZE);
        }
        if(WorldGenConfig.DIAMOND_STATE) {
          genOre(biome, WorldGenConfig.DIAMOND_COUNT, WorldGenConfig.DIAMOND_BOFFSET, WorldGenConfig.DIAMOND_MAX, CREATE_GABBRO, BlockHandler.ORE_DIAMOND_GABBRO.get().getDefaultState(), WorldGenConfig.DIAMOND_SIZE);
        }
        if(WorldGenConfig.EMERALD_STATE) {
          genOre(biome, WorldGenConfig.EMERALD_COUNT, WorldGenConfig.EMERALD_BOFFSET, WorldGenConfig.EMERALD_MAX, CREATE_GABBRO, BlockHandler.ORE_EMERALD_GABBRO.get().getDefaultState(), WorldGenConfig.EMERALD_SIZE);
        }
        if(WorldGenConfig.LAPIS_STATE) {
          genOre(biome, WorldGenConfig.LAPIS_COUNT, WorldGenConfig.LAPIS_BOFFSET, WorldGenConfig.LAPIS_MAX, CREATE_GABBRO, BlockHandler.ORE_LAPIS_GABBRO.get().getDefaultState(), WorldGenConfig.LAPIS_SIZE);
        }
        if(WorldGenConfig.REDSTONE_STATE) {
          genOre(biome, WorldGenConfig.REDSTONE_COUNT, WorldGenConfig.REDSTONE_BOFFSET, WorldGenConfig.REDSTONE_MAX, CREATE_GABBRO, BlockHandler.ORE_REDSTONE_GABBRO.get().getDefaultState(), WorldGenConfig.REDSTONE_SIZE);
        }
        if(WorldGenConfig.COPPER_STATE) {
          genOre(biome, WorldGenConfig.COPPER_COUNT, WorldGenConfig.COPPER_BOFFSET, WorldGenConfig.COPPER_MAX, CREATE_GABBRO, BlockHandler.ORE_COPPER_GABBRO.get().getDefaultState(), WorldGenConfig.COPPER_SIZE);
        }
        if(WorldGenConfig.ALUMINUM_STATE) {
          genOre(biome, WorldGenConfig.ALUMINUM_COUNT, WorldGenConfig.ALUMINUM_BOFFSET, WorldGenConfig.ALUMINUM_MAX, CREATE_GABBRO, BlockHandler.ORE_ALUMINUM_GABBRO.get().getDefaultState(), WorldGenConfig.ALUMINUM_SIZE);
        }
        if(WorldGenConfig.SILVER_STATE) {
          genOre(biome, WorldGenConfig.SILVER_COUNT, WorldGenConfig.SILVER_BOFFSET, WorldGenConfig.SILVER_MAX, CREATE_GABBRO, BlockHandler.ORE_SILVER_GABBRO.get().getDefaultState(), WorldGenConfig.SILVER_SIZE);
        }
        if(WorldGenConfig.LEAD_STATE) {
          genOre(biome, WorldGenConfig.LEAD_COUNT, WorldGenConfig.LEAD_BOFFSET, WorldGenConfig.LEAD_MAX, CREATE_GABBRO, BlockHandler.ORE_LEAD_GABBRO.get().getDefaultState(), WorldGenConfig.LEAD_SIZE);
        }
        if(WorldGenConfig.NICKEL_STATE) {
          genOre(biome, WorldGenConfig.NICKEL_COUNT, WorldGenConfig.NICKEL_BOFFSET, WorldGenConfig.NICKEL_MAX, CREATE_GABBRO, BlockHandler.ORE_NICKEL_GABBRO.get().getDefaultState(), WorldGenConfig.NICKEL_SIZE);
        }
        if(WorldGenConfig.URANIUM_STATE) {
          genOre(biome, WorldGenConfig.URANIUM_COUNT, WorldGenConfig.URANIUM_BOFFSET, WorldGenConfig.URANIUM_MAX, CREATE_GABBRO, BlockHandler.ORE_URANIUM_GABBRO.get().getDefaultState(), WorldGenConfig.URANIUM_SIZE);
        }
        if(WorldGenConfig.OSMIUM_STATE) {
          genOre(biome, WorldGenConfig.OSMIUM_COUNT, WorldGenConfig.OSMIUM_BOFFSET, WorldGenConfig.OSMIUM_MAX, CREATE_GABBRO, BlockHandler.ORE_OSMIUM_GABBRO.get().getDefaultState(), WorldGenConfig.OSMIUM_SIZE);
        }
        if(WorldGenConfig.TIN_STATE) {
          genOre(biome, WorldGenConfig.TIN_COUNT, WorldGenConfig.TIN_BOFFSET, WorldGenConfig.TIN_MAX, CREATE_GABBRO, BlockHandler.ORE_TIN_GABBRO.get().getDefaultState(), WorldGenConfig.TIN_SIZE);
        }
        if(WorldGenConfig.ZINC_STATE) {
          genOre(biome, WorldGenConfig.ZINC_COUNT, WorldGenConfig.ZINC_BOFFSET, WorldGenConfig.ZINC_MAX, CREATE_GABBRO, BlockHandler.ORE_ZINC_GABBRO.get().getDefaultState(), WorldGenConfig.ZINC_SIZE);
        }
        if(WorldGenConfig.CERTUS_QUARTZ_STATE) {
          genOre(biome, WorldGenConfig.CERTUS_QUARTZ_COUNT, WorldGenConfig.CERTUS_QUARTZ_BOFFSET, WorldGenConfig.CERTUS_QUARTZ_MAX, CREATE_GABBRO, BlockHandler.ORE_CERTUS_QUARTZ_GABBRO.get().getDefaultState(), WorldGenConfig.CERTUS_QUARTZ_SIZE);
        }
        if(WorldGenConfig.CHARGED_CERTUS_QUARTZ_STATE) {
          genOre(biome, WorldGenConfig.CHARGED_CERTUS_QUARTZ_COUNT, WorldGenConfig.CHARGED_CERTUS_QUARTZ_BOFFSET, WorldGenConfig.CHARGED_CERTUS_QUARTZ_MAX, CREATE_GABBRO, BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_GABBRO.get().getDefaultState(), WorldGenConfig.CHARGED_CERTUS_QUARTZ_SIZE);
        }
      }
      if(blockCreateLimestone != null && WorldGenConfig.CREATE_LIMESTONE_STATE) {
        // Strata Ores - Limestone
        if(WorldGenConfig.COAL_STATE) {
          genOre(biome, WorldGenConfig.COAL_COUNT, WorldGenConfig.COAL_BOFFSET, WorldGenConfig.COAL_MAX, CREATE_LIMESTONE, BlockHandler.ORE_COAL_C_LIMESTONE.get().getDefaultState(), WorldGenConfig.COAL_SIZE);
        }
        if(WorldGenConfig.IRON_STATE) {
          genOre(biome, WorldGenConfig.IRON_COUNT, WorldGenConfig.IRON_BOFFSET, WorldGenConfig.IRON_MAX, CREATE_LIMESTONE, BlockHandler.ORE_IRON_C_LIMESTONE.get().getDefaultState(), WorldGenConfig.IRON_SIZE);
        }
        if(WorldGenConfig.GOLD_STATE) {
          genOre(biome, WorldGenConfig.GOLD_COUNT, WorldGenConfig.GOLD_BOFFSET, WorldGenConfig.GOLD_MAX, CREATE_LIMESTONE, BlockHandler.ORE_GOLD_C_LIMESTONE.get().getDefaultState(), WorldGenConfig.GOLD_SIZE);
        }
        if(WorldGenConfig.DIAMOND_STATE) {
          genOre(biome, WorldGenConfig.DIAMOND_COUNT, WorldGenConfig.DIAMOND_BOFFSET, WorldGenConfig.DIAMOND_MAX, CREATE_LIMESTONE, BlockHandler.ORE_DIAMOND_C_LIMESTONE.get().getDefaultState(), WorldGenConfig.DIAMOND_SIZE);
        }
        if(WorldGenConfig.EMERALD_STATE) {
          genOre(biome, WorldGenConfig.EMERALD_COUNT, WorldGenConfig.EMERALD_BOFFSET, WorldGenConfig.EMERALD_MAX, CREATE_LIMESTONE, BlockHandler.ORE_EMERALD_C_LIMESTONE.get().getDefaultState(), WorldGenConfig.EMERALD_SIZE);
        }
        if(WorldGenConfig.LAPIS_STATE) {
          genOre(biome, WorldGenConfig.LAPIS_COUNT, WorldGenConfig.LAPIS_BOFFSET, WorldGenConfig.LAPIS_MAX, CREATE_LIMESTONE, BlockHandler.ORE_LAPIS_C_LIMESTONE.get().getDefaultState(), WorldGenConfig.LAPIS_SIZE);
        }
        if(WorldGenConfig.REDSTONE_STATE) {
          genOre(biome, WorldGenConfig.REDSTONE_COUNT, WorldGenConfig.REDSTONE_BOFFSET, WorldGenConfig.REDSTONE_MAX, CREATE_LIMESTONE, BlockHandler.ORE_REDSTONE_C_LIMESTONE.get().getDefaultState(), WorldGenConfig.REDSTONE_SIZE);
        }
        if(WorldGenConfig.COPPER_STATE) {
          genOre(biome, WorldGenConfig.COPPER_COUNT, WorldGenConfig.COPPER_BOFFSET, WorldGenConfig.COPPER_MAX, CREATE_LIMESTONE, BlockHandler.ORE_COPPER_C_LIMESTONE.get().getDefaultState(), WorldGenConfig.COPPER_SIZE);
        }
        if(WorldGenConfig.ALUMINUM_STATE) {
          genOre(biome, WorldGenConfig.ALUMINUM_COUNT, WorldGenConfig.ALUMINUM_BOFFSET, WorldGenConfig.ALUMINUM_MAX, CREATE_LIMESTONE, BlockHandler.ORE_ALUMINUM_C_LIMESTONE.get().getDefaultState(), WorldGenConfig.ALUMINUM_SIZE);
        }
        if(WorldGenConfig.SILVER_STATE) {
          genOre(biome, WorldGenConfig.SILVER_COUNT, WorldGenConfig.SILVER_BOFFSET, WorldGenConfig.SILVER_MAX, CREATE_LIMESTONE, BlockHandler.ORE_SILVER_C_LIMESTONE.get().getDefaultState(), WorldGenConfig.SILVER_SIZE);
        }
        if(WorldGenConfig.LEAD_STATE) {
          genOre(biome, WorldGenConfig.LEAD_COUNT, WorldGenConfig.LEAD_BOFFSET, WorldGenConfig.LEAD_MAX, CREATE_LIMESTONE, BlockHandler.ORE_LEAD_C_LIMESTONE.get().getDefaultState(), WorldGenConfig.LEAD_SIZE);
        }
        if(WorldGenConfig.NICKEL_STATE) {
          genOre(biome, WorldGenConfig.NICKEL_COUNT, WorldGenConfig.NICKEL_BOFFSET, WorldGenConfig.NICKEL_MAX, CREATE_LIMESTONE, BlockHandler.ORE_NICKEL_C_LIMESTONE.get().getDefaultState(), WorldGenConfig.NICKEL_SIZE);
        }
        if(WorldGenConfig.URANIUM_STATE) {
          genOre(biome, WorldGenConfig.URANIUM_COUNT, WorldGenConfig.URANIUM_BOFFSET, WorldGenConfig.URANIUM_MAX, CREATE_LIMESTONE, BlockHandler.ORE_URANIUM_C_LIMESTONE.get().getDefaultState(), WorldGenConfig.URANIUM_SIZE);
        }
        if(WorldGenConfig.OSMIUM_STATE) {
          genOre(biome, WorldGenConfig.OSMIUM_COUNT, WorldGenConfig.OSMIUM_BOFFSET, WorldGenConfig.OSMIUM_MAX, CREATE_LIMESTONE, BlockHandler.ORE_OSMIUM_C_LIMESTONE.get().getDefaultState(), WorldGenConfig.OSMIUM_SIZE);
        }
        if(WorldGenConfig.TIN_STATE) {
          genOre(biome, WorldGenConfig.TIN_COUNT, WorldGenConfig.TIN_BOFFSET, WorldGenConfig.TIN_MAX, CREATE_LIMESTONE, BlockHandler.ORE_TIN_C_LIMESTONE.get().getDefaultState(), WorldGenConfig.TIN_SIZE);
        }
        if(WorldGenConfig.ZINC_STATE) {
          genOre(biome, WorldGenConfig.ZINC_COUNT, WorldGenConfig.ZINC_BOFFSET, WorldGenConfig.ZINC_MAX, CREATE_LIMESTONE, BlockHandler.ORE_ZINC_C_LIMESTONE.get().getDefaultState(), WorldGenConfig.ZINC_SIZE);
        }
        if(WorldGenConfig.CERTUS_QUARTZ_STATE) {
          genOre(biome, WorldGenConfig.CERTUS_QUARTZ_COUNT, WorldGenConfig.CERTUS_QUARTZ_BOFFSET, WorldGenConfig.CERTUS_QUARTZ_MAX, CREATE_LIMESTONE, BlockHandler.ORE_CERTUS_QUARTZ_C_LIMESTONE.get().getDefaultState(), WorldGenConfig.CERTUS_QUARTZ_SIZE);
        }
        if(WorldGenConfig.CHARGED_CERTUS_QUARTZ_STATE) {
          genOre(biome, WorldGenConfig.CHARGED_CERTUS_QUARTZ_COUNT, WorldGenConfig.CHARGED_CERTUS_QUARTZ_BOFFSET, WorldGenConfig.CHARGED_CERTUS_QUARTZ_MAX, CREATE_LIMESTONE, BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_C_LIMESTONE.get().getDefaultState(), WorldGenConfig.CHARGED_CERTUS_QUARTZ_SIZE);
        }
      }
      if(blockCreateScoria != null && WorldGenConfig.CREATE_SCORIA_STATE) {
        // Strata Ores - Scoria
        if(WorldGenConfig.COAL_STATE) {
          genOre(biome, WorldGenConfig.COAL_COUNT, WorldGenConfig.COAL_BOFFSET, WorldGenConfig.COAL_MAX, CREATE_SCORIA, BlockHandler.ORE_COAL_SCORIA.get().getDefaultState(), WorldGenConfig.COAL_SIZE);
        }
        if(WorldGenConfig.IRON_STATE) {
          genOre(biome, WorldGenConfig.IRON_COUNT, WorldGenConfig.IRON_BOFFSET, WorldGenConfig.IRON_MAX, CREATE_SCORIA, BlockHandler.ORE_IRON_SCORIA.get().getDefaultState(), WorldGenConfig.IRON_SIZE);
        }
        if(WorldGenConfig.GOLD_STATE) {
          genOre(biome, WorldGenConfig.GOLD_COUNT, WorldGenConfig.GOLD_BOFFSET, WorldGenConfig.GOLD_MAX, CREATE_SCORIA, BlockHandler.ORE_GOLD_SCORIA.get().getDefaultState(), WorldGenConfig.GOLD_SIZE);
        }
        if(WorldGenConfig.DIAMOND_STATE) {
          genOre(biome, WorldGenConfig.DIAMOND_COUNT, WorldGenConfig.DIAMOND_BOFFSET, WorldGenConfig.DIAMOND_MAX, CREATE_SCORIA, BlockHandler.ORE_DIAMOND_SCORIA.get().getDefaultState(), WorldGenConfig.DIAMOND_SIZE);
        }
        if(WorldGenConfig.EMERALD_STATE) {
          genOre(biome, WorldGenConfig.EMERALD_COUNT, WorldGenConfig.EMERALD_BOFFSET, WorldGenConfig.EMERALD_MAX, CREATE_SCORIA, BlockHandler.ORE_EMERALD_SCORIA.get().getDefaultState(), WorldGenConfig.EMERALD_SIZE);
        }
        if(WorldGenConfig.LAPIS_STATE) {
          genOre(biome, WorldGenConfig.LAPIS_COUNT, WorldGenConfig.LAPIS_BOFFSET, WorldGenConfig.LAPIS_MAX, CREATE_SCORIA, BlockHandler.ORE_LAPIS_SCORIA.get().getDefaultState(), WorldGenConfig.LAPIS_SIZE);
        }
        if(WorldGenConfig.REDSTONE_STATE) {
          genOre(biome, WorldGenConfig.REDSTONE_COUNT, WorldGenConfig.REDSTONE_BOFFSET, WorldGenConfig.REDSTONE_MAX, CREATE_SCORIA, BlockHandler.ORE_REDSTONE_SCORIA.get().getDefaultState(), WorldGenConfig.REDSTONE_SIZE);
        }
        if(WorldGenConfig.COPPER_STATE) {
          genOre(biome, WorldGenConfig.COPPER_COUNT, WorldGenConfig.COPPER_BOFFSET, WorldGenConfig.COPPER_MAX, CREATE_SCORIA, BlockHandler.ORE_COPPER_SCORIA.get().getDefaultState(), WorldGenConfig.COPPER_SIZE);
        }
        if(WorldGenConfig.ALUMINUM_STATE) {
          genOre(biome, WorldGenConfig.ALUMINUM_COUNT, WorldGenConfig.ALUMINUM_BOFFSET, WorldGenConfig.ALUMINUM_MAX, CREATE_SCORIA, BlockHandler.ORE_ALUMINUM_SCORIA.get().getDefaultState(), WorldGenConfig.ALUMINUM_SIZE);
        }
        if(WorldGenConfig.SILVER_STATE) {
          genOre(biome, WorldGenConfig.SILVER_COUNT, WorldGenConfig.SILVER_BOFFSET, WorldGenConfig.SILVER_MAX, CREATE_SCORIA, BlockHandler.ORE_SILVER_SCORIA.get().getDefaultState(), WorldGenConfig.SILVER_SIZE);
        }
        if(WorldGenConfig.LEAD_STATE) {
          genOre(biome, WorldGenConfig.LEAD_COUNT, WorldGenConfig.LEAD_BOFFSET, WorldGenConfig.LEAD_MAX, CREATE_SCORIA, BlockHandler.ORE_LEAD_SCORIA.get().getDefaultState(), WorldGenConfig.LEAD_SIZE);
        }
        if(WorldGenConfig.NICKEL_STATE) {
          genOre(biome, WorldGenConfig.NICKEL_COUNT, WorldGenConfig.NICKEL_BOFFSET, WorldGenConfig.NICKEL_MAX, CREATE_SCORIA, BlockHandler.ORE_NICKEL_SCORIA.get().getDefaultState(), WorldGenConfig.NICKEL_SIZE);
        }
        if(WorldGenConfig.URANIUM_STATE) {
          genOre(biome, WorldGenConfig.URANIUM_COUNT, WorldGenConfig.URANIUM_BOFFSET, WorldGenConfig.URANIUM_MAX, CREATE_SCORIA, BlockHandler.ORE_URANIUM_SCORIA.get().getDefaultState(), WorldGenConfig.URANIUM_SIZE);
        }
        if(WorldGenConfig.OSMIUM_STATE) {
          genOre(biome, WorldGenConfig.OSMIUM_COUNT, WorldGenConfig.OSMIUM_BOFFSET, WorldGenConfig.OSMIUM_MAX, CREATE_SCORIA, BlockHandler.ORE_OSMIUM_SCORIA.get().getDefaultState(), WorldGenConfig.OSMIUM_SIZE);
        }
        if(WorldGenConfig.TIN_STATE) {
          genOre(biome, WorldGenConfig.TIN_COUNT, WorldGenConfig.TIN_BOFFSET, WorldGenConfig.TIN_MAX, CREATE_SCORIA, BlockHandler.ORE_TIN_SCORIA.get().getDefaultState(), WorldGenConfig.TIN_SIZE);
        }
        if(WorldGenConfig.ZINC_STATE) {
          genOre(biome, WorldGenConfig.ZINC_COUNT, WorldGenConfig.ZINC_BOFFSET, WorldGenConfig.ZINC_MAX, CREATE_SCORIA, BlockHandler.ORE_ZINC_SCORIA.get().getDefaultState(), WorldGenConfig.ZINC_SIZE);
        }
        if(WorldGenConfig.CERTUS_QUARTZ_STATE) {
          genOre(biome, WorldGenConfig.CERTUS_QUARTZ_COUNT, WorldGenConfig.CERTUS_QUARTZ_BOFFSET, WorldGenConfig.CERTUS_QUARTZ_MAX, CREATE_SCORIA, BlockHandler.ORE_CERTUS_QUARTZ_SCORIA.get().getDefaultState(), WorldGenConfig.CERTUS_QUARTZ_SIZE);
        }
        if(WorldGenConfig.CHARGED_CERTUS_QUARTZ_STATE) {
          genOre(biome, WorldGenConfig.CHARGED_CERTUS_QUARTZ_COUNT, WorldGenConfig.CHARGED_CERTUS_QUARTZ_BOFFSET, WorldGenConfig.CHARGED_CERTUS_QUARTZ_MAX, CREATE_SCORIA, BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_SCORIA.get().getDefaultState(), WorldGenConfig.CHARGED_CERTUS_QUARTZ_SIZE);
        }
      }
      if(blockCreateWeatheredLimestone != null && WorldGenConfig.CREATE_WEATHERED_LIMESTONE_STATE) {
        // Strata Ores - Weathered Limestone
        if(WorldGenConfig.COAL_STATE) {
          genOre(biome, WorldGenConfig.COAL_COUNT, WorldGenConfig.COAL_BOFFSET, WorldGenConfig.COAL_MAX, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_COAL_WEATHERED_LIMESTONE.get().getDefaultState(), WorldGenConfig.COAL_SIZE);
        }
        if(WorldGenConfig.IRON_STATE) {
          genOre(biome, WorldGenConfig.IRON_COUNT, WorldGenConfig.IRON_BOFFSET, WorldGenConfig.IRON_MAX, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_IRON_WEATHERED_LIMESTONE.get().getDefaultState(), WorldGenConfig.IRON_SIZE);
        }
        if(WorldGenConfig.GOLD_STATE) {
          genOre(biome, WorldGenConfig.GOLD_COUNT, WorldGenConfig.GOLD_BOFFSET, WorldGenConfig.GOLD_MAX, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_GOLD_WEATHERED_LIMESTONE.get().getDefaultState(), WorldGenConfig.GOLD_SIZE);
        }
        if(WorldGenConfig.DIAMOND_STATE) {
          genOre(biome, WorldGenConfig.DIAMOND_COUNT, WorldGenConfig.DIAMOND_BOFFSET, WorldGenConfig.DIAMOND_MAX, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_DIAMOND_WEATHERED_LIMESTONE.get().getDefaultState(), WorldGenConfig.DIAMOND_SIZE);
        }
        if(WorldGenConfig.EMERALD_STATE) {
          genOre(biome, WorldGenConfig.EMERALD_COUNT, WorldGenConfig.EMERALD_BOFFSET, WorldGenConfig.EMERALD_MAX, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_EMERALD_WEATHERED_LIMESTONE.get().getDefaultState(), WorldGenConfig.EMERALD_SIZE);
        }
        if(WorldGenConfig.LAPIS_STATE) {
          genOre(biome, WorldGenConfig.LAPIS_COUNT, WorldGenConfig.LAPIS_BOFFSET, WorldGenConfig.LAPIS_MAX, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_LAPIS_WEATHERED_LIMESTONE.get().getDefaultState(), WorldGenConfig.LAPIS_SIZE);
        }
        if(WorldGenConfig.REDSTONE_STATE) {
          genOre(biome, WorldGenConfig.REDSTONE_COUNT, WorldGenConfig.REDSTONE_BOFFSET, WorldGenConfig.REDSTONE_MAX, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_REDSTONE_WEATHERED_LIMESTONE.get().getDefaultState(), WorldGenConfig.REDSTONE_SIZE);
        }
        if(WorldGenConfig.COPPER_STATE) {
          genOre(biome, WorldGenConfig.COPPER_COUNT, WorldGenConfig.COPPER_BOFFSET, WorldGenConfig.COPPER_MAX, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_COPPER_WEATHERED_LIMESTONE.get().getDefaultState(), WorldGenConfig.COPPER_SIZE);
        }
        if(WorldGenConfig.ALUMINUM_STATE) {
          genOre(biome, WorldGenConfig.ALUMINUM_COUNT, WorldGenConfig.ALUMINUM_BOFFSET, WorldGenConfig.ALUMINUM_MAX, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_ALUMINUM_WEATHERED_LIMESTONE.get().getDefaultState(), WorldGenConfig.ALUMINUM_SIZE);
        }
        if(WorldGenConfig.SILVER_STATE) {
          genOre(biome, WorldGenConfig.SILVER_COUNT, WorldGenConfig.SILVER_BOFFSET, WorldGenConfig.SILVER_MAX, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_SILVER_WEATHERED_LIMESTONE.get().getDefaultState(), WorldGenConfig.SILVER_SIZE);
        }
        if(WorldGenConfig.LEAD_STATE) {
          genOre(biome, WorldGenConfig.LEAD_COUNT, WorldGenConfig.LEAD_BOFFSET, WorldGenConfig.LEAD_MAX, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_LEAD_WEATHERED_LIMESTONE.get().getDefaultState(), WorldGenConfig.LEAD_SIZE);
        }
        if(WorldGenConfig.NICKEL_STATE) {
          genOre(biome, WorldGenConfig.NICKEL_COUNT, WorldGenConfig.NICKEL_BOFFSET, WorldGenConfig.NICKEL_MAX, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_NICKEL_WEATHERED_LIMESTONE.get().getDefaultState(), WorldGenConfig.NICKEL_SIZE);
        }
        if(WorldGenConfig.URANIUM_STATE) {
          genOre(biome, WorldGenConfig.URANIUM_COUNT, WorldGenConfig.URANIUM_BOFFSET, WorldGenConfig.URANIUM_MAX, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_URANIUM_WEATHERED_LIMESTONE.get().getDefaultState(), WorldGenConfig.URANIUM_SIZE);
        }
        if(WorldGenConfig.OSMIUM_STATE) {
          genOre(biome, WorldGenConfig.OSMIUM_COUNT, WorldGenConfig.OSMIUM_BOFFSET, WorldGenConfig.OSMIUM_MAX, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_OSMIUM_WEATHERED_LIMESTONE.get().getDefaultState(), WorldGenConfig.OSMIUM_SIZE);
        }
        if(WorldGenConfig.TIN_STATE) {
          genOre(biome, WorldGenConfig.TIN_COUNT, WorldGenConfig.TIN_BOFFSET, WorldGenConfig.TIN_MAX, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_TIN_WEATHERED_LIMESTONE.get().getDefaultState(), WorldGenConfig.TIN_SIZE);
        }
        if(WorldGenConfig.ZINC_STATE) {
          genOre(biome, WorldGenConfig.ZINC_COUNT, WorldGenConfig.ZINC_BOFFSET, WorldGenConfig.ZINC_MAX, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_ZINC_WEATHERED_LIMESTONE.get().getDefaultState(), WorldGenConfig.ZINC_SIZE);
        }
        if(WorldGenConfig.CERTUS_QUARTZ_STATE) {
          genOre(biome, WorldGenConfig.CERTUS_QUARTZ_COUNT, WorldGenConfig.CERTUS_QUARTZ_BOFFSET, WorldGenConfig.CERTUS_QUARTZ_MAX, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_CERTUS_QUARTZ_WEATHERED_LIMESTONE.get().getDefaultState(), WorldGenConfig.CERTUS_QUARTZ_SIZE);
        }
        if(WorldGenConfig.CHARGED_CERTUS_QUARTZ_STATE) {
          genOre(biome, WorldGenConfig.CHARGED_CERTUS_QUARTZ_COUNT, WorldGenConfig.CHARGED_CERTUS_QUARTZ_BOFFSET, WorldGenConfig.CHARGED_CERTUS_QUARTZ_MAX, CREATE_WEATHERED_LIMESTONE, BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_WEATHERED_LIMESTONE.get().getDefaultState(), WorldGenConfig.CHARGED_CERTUS_QUARTZ_SIZE);
        }
      }
      if(blockQuarkJasper != null && WorldGenConfig.QUARK_JASPER_STATE) {
        // Strata Ores - Jasper
        if(WorldGenConfig.COAL_STATE) {
          genOre(biome, WorldGenConfig.COAL_COUNT, WorldGenConfig.COAL_BOFFSET, WorldGenConfig.COAL_MAX, QUARK_JASPER, BlockHandler.ORE_COAL_JASPER.get().getDefaultState(), WorldGenConfig.COAL_SIZE);
        }
        if(WorldGenConfig.IRON_STATE) {
          genOre(biome, WorldGenConfig.IRON_COUNT, WorldGenConfig.IRON_BOFFSET, WorldGenConfig.IRON_MAX, QUARK_JASPER, BlockHandler.ORE_IRON_JASPER.get().getDefaultState(), WorldGenConfig.IRON_SIZE);
        }
        if(WorldGenConfig.GOLD_STATE) {
          genOre(biome, WorldGenConfig.GOLD_COUNT, WorldGenConfig.GOLD_BOFFSET, WorldGenConfig.GOLD_MAX, QUARK_JASPER, BlockHandler.ORE_GOLD_JASPER.get().getDefaultState(), WorldGenConfig.GOLD_SIZE);
        }
        if(WorldGenConfig.DIAMOND_STATE) {
          genOre(biome, WorldGenConfig.DIAMOND_COUNT, WorldGenConfig.DIAMOND_BOFFSET, WorldGenConfig.DIAMOND_MAX, QUARK_JASPER, BlockHandler.ORE_DIAMOND_JASPER.get().getDefaultState(), WorldGenConfig.DIAMOND_SIZE);
        }
        if(WorldGenConfig.EMERALD_STATE) {
          genOre(biome, WorldGenConfig.EMERALD_COUNT, WorldGenConfig.EMERALD_BOFFSET, WorldGenConfig.EMERALD_MAX, QUARK_JASPER, BlockHandler.ORE_EMERALD_JASPER.get().getDefaultState(), WorldGenConfig.EMERALD_SIZE);
        }
        if(WorldGenConfig.LAPIS_STATE) {
          genOre(biome, WorldGenConfig.LAPIS_COUNT, WorldGenConfig.LAPIS_BOFFSET, WorldGenConfig.LAPIS_MAX, QUARK_JASPER, BlockHandler.ORE_LAPIS_JASPER.get().getDefaultState(), WorldGenConfig.LAPIS_SIZE);
        }
        if(WorldGenConfig.REDSTONE_STATE) {
          genOre(biome, WorldGenConfig.REDSTONE_COUNT, WorldGenConfig.REDSTONE_BOFFSET, WorldGenConfig.REDSTONE_MAX, QUARK_JASPER, BlockHandler.ORE_REDSTONE_JASPER.get().getDefaultState(), WorldGenConfig.REDSTONE_SIZE);
        }
        if(WorldGenConfig.COPPER_STATE) {
          genOre(biome, WorldGenConfig.COPPER_COUNT, WorldGenConfig.COPPER_BOFFSET, WorldGenConfig.COPPER_MAX, QUARK_JASPER, BlockHandler.ORE_COPPER_JASPER.get().getDefaultState(), WorldGenConfig.COPPER_SIZE);
        }
        if(WorldGenConfig.ALUMINUM_STATE) {
          genOre(biome, WorldGenConfig.ALUMINUM_COUNT, WorldGenConfig.ALUMINUM_BOFFSET, WorldGenConfig.ALUMINUM_MAX, QUARK_JASPER, BlockHandler.ORE_ALUMINUM_JASPER.get().getDefaultState(), WorldGenConfig.ALUMINUM_SIZE);
        }
        if(WorldGenConfig.SILVER_STATE) {
          genOre(biome, WorldGenConfig.SILVER_COUNT, WorldGenConfig.SILVER_BOFFSET, WorldGenConfig.SILVER_MAX, QUARK_JASPER, BlockHandler.ORE_SILVER_JASPER.get().getDefaultState(), WorldGenConfig.SILVER_SIZE);
        }
        if(WorldGenConfig.LEAD_STATE) {
          genOre(biome, WorldGenConfig.LEAD_COUNT, WorldGenConfig.LEAD_BOFFSET, WorldGenConfig.LEAD_MAX, QUARK_JASPER, BlockHandler.ORE_LEAD_JASPER.get().getDefaultState(), WorldGenConfig.LEAD_SIZE);
        }
        if(WorldGenConfig.NICKEL_STATE) {
          genOre(biome, WorldGenConfig.NICKEL_COUNT, WorldGenConfig.NICKEL_BOFFSET, WorldGenConfig.NICKEL_MAX, QUARK_JASPER, BlockHandler.ORE_NICKEL_JASPER.get().getDefaultState(), WorldGenConfig.NICKEL_SIZE);
        }
        if(WorldGenConfig.URANIUM_STATE) {
          genOre(biome, WorldGenConfig.URANIUM_COUNT, WorldGenConfig.URANIUM_BOFFSET, WorldGenConfig.URANIUM_MAX, QUARK_JASPER, BlockHandler.ORE_URANIUM_JASPER.get().getDefaultState(), WorldGenConfig.URANIUM_SIZE);
        }
        if(WorldGenConfig.OSMIUM_STATE) {
          genOre(biome, WorldGenConfig.OSMIUM_COUNT, WorldGenConfig.OSMIUM_BOFFSET, WorldGenConfig.OSMIUM_MAX, QUARK_JASPER, BlockHandler.ORE_OSMIUM_JASPER.get().getDefaultState(), WorldGenConfig.OSMIUM_SIZE);
        }
        if(WorldGenConfig.TIN_STATE) {
          genOre(biome, WorldGenConfig.TIN_COUNT, WorldGenConfig.TIN_BOFFSET, WorldGenConfig.TIN_MAX, QUARK_JASPER, BlockHandler.ORE_TIN_JASPER.get().getDefaultState(), WorldGenConfig.TIN_SIZE);
        }
        if(WorldGenConfig.ZINC_STATE) {
          genOre(biome, WorldGenConfig.ZINC_COUNT, WorldGenConfig.ZINC_BOFFSET, WorldGenConfig.ZINC_MAX, QUARK_JASPER, BlockHandler.ORE_ZINC_JASPER.get().getDefaultState(), WorldGenConfig.ZINC_SIZE);
        }
        if(WorldGenConfig.CERTUS_QUARTZ_STATE) {
          genOre(biome, WorldGenConfig.CERTUS_QUARTZ_COUNT, WorldGenConfig.CERTUS_QUARTZ_BOFFSET, WorldGenConfig.CERTUS_QUARTZ_MAX, QUARK_JASPER, BlockHandler.ORE_CERTUS_QUARTZ_JASPER.get().getDefaultState(), WorldGenConfig.CERTUS_QUARTZ_SIZE);
        }
        if(WorldGenConfig.CHARGED_CERTUS_QUARTZ_STATE) {
          genOre(biome, WorldGenConfig.CHARGED_CERTUS_QUARTZ_COUNT, WorldGenConfig.CHARGED_CERTUS_QUARTZ_BOFFSET, WorldGenConfig.CHARGED_CERTUS_QUARTZ_MAX, QUARK_JASPER, BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_JASPER.get().getDefaultState(), WorldGenConfig.CHARGED_CERTUS_QUARTZ_SIZE);
        }
      }
      if(blockQuarkLimestone != null && WorldGenConfig.QUARK_LIMESTONE_STATE) {
        // Strata Ores - Limestone (Quark)
        if(WorldGenConfig.COAL_STATE) {
          genOre(biome, WorldGenConfig.COAL_COUNT, WorldGenConfig.COAL_BOFFSET, WorldGenConfig.COAL_MAX, QUARK_LIMESTONE, BlockHandler.ORE_COAL_Q_LIMESTONE.get().getDefaultState(), WorldGenConfig.COAL_SIZE);
        }
        if(WorldGenConfig.IRON_STATE) {
          genOre(biome, WorldGenConfig.IRON_COUNT, WorldGenConfig.IRON_BOFFSET, WorldGenConfig.IRON_MAX, QUARK_LIMESTONE, BlockHandler.ORE_IRON_Q_LIMESTONE.get().getDefaultState(), WorldGenConfig.IRON_SIZE);
        }
        if(WorldGenConfig.GOLD_STATE) {
          genOre(biome, WorldGenConfig.GOLD_COUNT, WorldGenConfig.GOLD_BOFFSET, WorldGenConfig.GOLD_MAX, QUARK_LIMESTONE, BlockHandler.ORE_GOLD_Q_LIMESTONE.get().getDefaultState(), WorldGenConfig.GOLD_SIZE);
        }
        if(WorldGenConfig.DIAMOND_STATE) {
          genOre(biome, WorldGenConfig.DIAMOND_COUNT, WorldGenConfig.DIAMOND_BOFFSET, WorldGenConfig.DIAMOND_MAX, QUARK_LIMESTONE, BlockHandler.ORE_DIAMOND_Q_LIMESTONE.get().getDefaultState(), WorldGenConfig.DIAMOND_SIZE);
        }
        if(WorldGenConfig.EMERALD_STATE) {
          genOre(biome, WorldGenConfig.EMERALD_COUNT, WorldGenConfig.EMERALD_BOFFSET, WorldGenConfig.EMERALD_MAX, QUARK_LIMESTONE, BlockHandler.ORE_EMERALD_Q_LIMESTONE.get().getDefaultState(), WorldGenConfig.EMERALD_SIZE);
        }
        if(WorldGenConfig.LAPIS_STATE) {
          genOre(biome, WorldGenConfig.LAPIS_COUNT, WorldGenConfig.LAPIS_BOFFSET, WorldGenConfig.LAPIS_MAX, QUARK_LIMESTONE, BlockHandler.ORE_LAPIS_Q_LIMESTONE.get().getDefaultState(), WorldGenConfig.LAPIS_SIZE);
        }
        if(WorldGenConfig.REDSTONE_STATE) {
          genOre(biome, WorldGenConfig.REDSTONE_COUNT, WorldGenConfig.REDSTONE_BOFFSET, WorldGenConfig.REDSTONE_MAX, QUARK_LIMESTONE, BlockHandler.ORE_REDSTONE_Q_LIMESTONE.get().getDefaultState(), WorldGenConfig.REDSTONE_SIZE);
        }
        if(WorldGenConfig.COPPER_STATE) {
          genOre(biome, WorldGenConfig.COPPER_COUNT, WorldGenConfig.COPPER_BOFFSET, WorldGenConfig.COPPER_MAX, QUARK_LIMESTONE, BlockHandler.ORE_COPPER_Q_LIMESTONE.get().getDefaultState(), WorldGenConfig.COPPER_SIZE);
        }
        if(WorldGenConfig.ALUMINUM_STATE) {
          genOre(biome, WorldGenConfig.ALUMINUM_COUNT, WorldGenConfig.ALUMINUM_BOFFSET, WorldGenConfig.ALUMINUM_MAX, QUARK_LIMESTONE, BlockHandler.ORE_ALUMINUM_Q_LIMESTONE.get().getDefaultState(), WorldGenConfig.ALUMINUM_SIZE);
        }
        if(WorldGenConfig.SILVER_STATE) {
          genOre(biome, WorldGenConfig.SILVER_COUNT, WorldGenConfig.SILVER_BOFFSET, WorldGenConfig.SILVER_MAX, QUARK_LIMESTONE, BlockHandler.ORE_SILVER_Q_LIMESTONE.get().getDefaultState(), WorldGenConfig.SILVER_SIZE);
        }
        if(WorldGenConfig.LEAD_STATE) {
          genOre(biome, WorldGenConfig.LEAD_COUNT, WorldGenConfig.LEAD_BOFFSET, WorldGenConfig.LEAD_MAX, QUARK_LIMESTONE, BlockHandler.ORE_LEAD_Q_LIMESTONE.get().getDefaultState(), WorldGenConfig.LEAD_SIZE);
        }
        if(WorldGenConfig.NICKEL_STATE) {
          genOre(biome, WorldGenConfig.NICKEL_COUNT, WorldGenConfig.NICKEL_BOFFSET, WorldGenConfig.NICKEL_MAX, QUARK_LIMESTONE, BlockHandler.ORE_NICKEL_Q_LIMESTONE.get().getDefaultState(), WorldGenConfig.NICKEL_SIZE);
        }
        if(WorldGenConfig.URANIUM_STATE) {
          genOre(biome, WorldGenConfig.URANIUM_COUNT, WorldGenConfig.URANIUM_BOFFSET, WorldGenConfig.URANIUM_MAX, QUARK_LIMESTONE, BlockHandler.ORE_URANIUM_Q_LIMESTONE.get().getDefaultState(), WorldGenConfig.URANIUM_SIZE);
        }
        if(WorldGenConfig.OSMIUM_STATE) {
          genOre(biome, WorldGenConfig.OSMIUM_COUNT, WorldGenConfig.OSMIUM_BOFFSET, WorldGenConfig.OSMIUM_MAX, QUARK_LIMESTONE, BlockHandler.ORE_OSMIUM_Q_LIMESTONE.get().getDefaultState(), WorldGenConfig.OSMIUM_SIZE);
        }
        if(WorldGenConfig.TIN_STATE) {
          genOre(biome, WorldGenConfig.TIN_COUNT, WorldGenConfig.TIN_BOFFSET, WorldGenConfig.TIN_MAX, QUARK_LIMESTONE, BlockHandler.ORE_TIN_Q_LIMESTONE.get().getDefaultState(), WorldGenConfig.TIN_SIZE);
        }
        if(WorldGenConfig.ZINC_STATE) {
          genOre(biome, WorldGenConfig.ZINC_COUNT, WorldGenConfig.ZINC_BOFFSET, WorldGenConfig.ZINC_MAX, QUARK_LIMESTONE, BlockHandler.ORE_ZINC_Q_LIMESTONE.get().getDefaultState(), WorldGenConfig.ZINC_SIZE);
        }
        if(WorldGenConfig.CERTUS_QUARTZ_STATE) {
          genOre(biome, WorldGenConfig.CERTUS_QUARTZ_COUNT, WorldGenConfig.CERTUS_QUARTZ_BOFFSET, WorldGenConfig.CERTUS_QUARTZ_MAX, QUARK_LIMESTONE, BlockHandler.ORE_CERTUS_QUARTZ_Q_LIMESTONE.get().getDefaultState(), WorldGenConfig.CERTUS_QUARTZ_SIZE);
        }
        if(WorldGenConfig.CHARGED_CERTUS_QUARTZ_STATE) {
          genOre(biome, WorldGenConfig.CHARGED_CERTUS_QUARTZ_COUNT, WorldGenConfig.CHARGED_CERTUS_QUARTZ_BOFFSET, WorldGenConfig.CHARGED_CERTUS_QUARTZ_MAX, QUARK_LIMESTONE, BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_Q_LIMESTONE.get().getDefaultState(), WorldGenConfig.CHARGED_CERTUS_QUARTZ_SIZE);
        }
      }
      if(blockQuarkMarble != null && WorldGenConfig.QUARK_MARBLE_STATE) {
        // Strata Ores - Marble
        if(WorldGenConfig.COAL_STATE) {
          genOre(biome, WorldGenConfig.COAL_COUNT, WorldGenConfig.COAL_BOFFSET, WorldGenConfig.COAL_MAX, QUARK_MARBLE, BlockHandler.ORE_COAL_MARBLE.get().getDefaultState(), WorldGenConfig.COAL_SIZE);
        }
        if(WorldGenConfig.IRON_STATE) {
          genOre(biome, WorldGenConfig.IRON_COUNT, WorldGenConfig.IRON_BOFFSET, WorldGenConfig.IRON_MAX, QUARK_MARBLE, BlockHandler.ORE_IRON_MARBLE.get().getDefaultState(), WorldGenConfig.IRON_SIZE);
        }
        if(WorldGenConfig.GOLD_STATE) {
          genOre(biome, WorldGenConfig.GOLD_COUNT, WorldGenConfig.GOLD_BOFFSET, WorldGenConfig.GOLD_MAX, QUARK_MARBLE, BlockHandler.ORE_GOLD_MARBLE.get().getDefaultState(), WorldGenConfig.GOLD_SIZE);
        }
        if(WorldGenConfig.DIAMOND_STATE) {
          genOre(biome, WorldGenConfig.DIAMOND_COUNT, WorldGenConfig.DIAMOND_BOFFSET, WorldGenConfig.DIAMOND_MAX, QUARK_MARBLE, BlockHandler.ORE_DIAMOND_MARBLE.get().getDefaultState(), WorldGenConfig.DIAMOND_SIZE);
        }
        if(WorldGenConfig.EMERALD_STATE) {
          genOre(biome, WorldGenConfig.EMERALD_COUNT, WorldGenConfig.EMERALD_BOFFSET, WorldGenConfig.EMERALD_MAX, QUARK_MARBLE, BlockHandler.ORE_EMERALD_MARBLE.get().getDefaultState(), WorldGenConfig.EMERALD_SIZE);
        }
        if(WorldGenConfig.LAPIS_STATE) {
          genOre(biome, WorldGenConfig.LAPIS_COUNT, WorldGenConfig.LAPIS_BOFFSET, WorldGenConfig.LAPIS_MAX, QUARK_MARBLE, BlockHandler.ORE_LAPIS_MARBLE.get().getDefaultState(), WorldGenConfig.LAPIS_SIZE);
        }
        if(WorldGenConfig.REDSTONE_STATE) {
          genOre(biome, WorldGenConfig.REDSTONE_COUNT, WorldGenConfig.REDSTONE_BOFFSET, WorldGenConfig.REDSTONE_MAX, QUARK_MARBLE, BlockHandler.ORE_REDSTONE_MARBLE.get().getDefaultState(), WorldGenConfig.REDSTONE_SIZE);
        }
        if(WorldGenConfig.COPPER_STATE) {
          genOre(biome, WorldGenConfig.COPPER_COUNT, WorldGenConfig.COPPER_BOFFSET, WorldGenConfig.COPPER_MAX, QUARK_MARBLE, BlockHandler.ORE_COPPER_MARBLE.get().getDefaultState(), WorldGenConfig.COPPER_SIZE);
        }
        if(WorldGenConfig.ALUMINUM_STATE) {
          genOre(biome, WorldGenConfig.ALUMINUM_COUNT, WorldGenConfig.ALUMINUM_BOFFSET, WorldGenConfig.ALUMINUM_MAX, QUARK_MARBLE, BlockHandler.ORE_ALUMINUM_MARBLE.get().getDefaultState(), WorldGenConfig.ALUMINUM_SIZE);
        }
        if(WorldGenConfig.SILVER_STATE) {
          genOre(biome, WorldGenConfig.SILVER_COUNT, WorldGenConfig.SILVER_BOFFSET, WorldGenConfig.SILVER_MAX, QUARK_MARBLE, BlockHandler.ORE_SILVER_MARBLE.get().getDefaultState(), WorldGenConfig.SILVER_SIZE);
        }
        if(WorldGenConfig.LEAD_STATE) {
          genOre(biome, WorldGenConfig.LEAD_COUNT, WorldGenConfig.LEAD_BOFFSET, WorldGenConfig.LEAD_MAX, QUARK_MARBLE, BlockHandler.ORE_LEAD_MARBLE.get().getDefaultState(), WorldGenConfig.LEAD_SIZE);
        }
        if(WorldGenConfig.NICKEL_STATE) {
          genOre(biome, WorldGenConfig.NICKEL_COUNT, WorldGenConfig.NICKEL_BOFFSET, WorldGenConfig.NICKEL_MAX, QUARK_MARBLE, BlockHandler.ORE_NICKEL_MARBLE.get().getDefaultState(), WorldGenConfig.NICKEL_SIZE);
        }
        if(WorldGenConfig.URANIUM_STATE) {
          genOre(biome, WorldGenConfig.URANIUM_COUNT, WorldGenConfig.URANIUM_BOFFSET, WorldGenConfig.URANIUM_MAX, QUARK_MARBLE, BlockHandler.ORE_URANIUM_MARBLE.get().getDefaultState(), WorldGenConfig.URANIUM_SIZE);
        }
        if(WorldGenConfig.OSMIUM_STATE) {
          genOre(biome, WorldGenConfig.OSMIUM_COUNT, WorldGenConfig.OSMIUM_BOFFSET, WorldGenConfig.OSMIUM_MAX, QUARK_MARBLE, BlockHandler.ORE_OSMIUM_MARBLE.get().getDefaultState(), WorldGenConfig.OSMIUM_SIZE);
        }
        if(WorldGenConfig.TIN_STATE) {
          genOre(biome, WorldGenConfig.TIN_COUNT, WorldGenConfig.TIN_BOFFSET, WorldGenConfig.TIN_MAX, QUARK_MARBLE, BlockHandler.ORE_TIN_MARBLE.get().getDefaultState(), WorldGenConfig.TIN_SIZE);
        }
        if(WorldGenConfig.ZINC_STATE) {
          genOre(biome, WorldGenConfig.ZINC_COUNT, WorldGenConfig.ZINC_BOFFSET, WorldGenConfig.ZINC_MAX, QUARK_MARBLE, BlockHandler.ORE_ZINC_MARBLE.get().getDefaultState(), WorldGenConfig.ZINC_SIZE);
        }
        if(WorldGenConfig.CERTUS_QUARTZ_STATE) {
          genOre(biome, WorldGenConfig.CERTUS_QUARTZ_COUNT, WorldGenConfig.CERTUS_QUARTZ_BOFFSET, WorldGenConfig.CERTUS_QUARTZ_MAX, QUARK_MARBLE, BlockHandler.ORE_CERTUS_QUARTZ_MARBLE.get().getDefaultState(), WorldGenConfig.CERTUS_QUARTZ_SIZE);
        }
        if(WorldGenConfig.CHARGED_CERTUS_QUARTZ_STATE) {
          genOre(biome, WorldGenConfig.CHARGED_CERTUS_QUARTZ_COUNT, WorldGenConfig.CHARGED_CERTUS_QUARTZ_BOFFSET, WorldGenConfig.CHARGED_CERTUS_QUARTZ_MAX, QUARK_MARBLE, BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_MARBLE.get().getDefaultState(), WorldGenConfig.CHARGED_CERTUS_QUARTZ_SIZE);
        }
      }
      if(blockQuarkSlate != null && WorldGenConfig.QUARK_SLATE_STATE) {
        // Strata Ores - Slate
        if(WorldGenConfig.COAL_STATE) {
          genOre(biome, WorldGenConfig.COAL_COUNT, WorldGenConfig.COAL_BOFFSET, WorldGenConfig.COAL_MAX, QUARK_SLATE, BlockHandler.ORE_COAL_SLATE.get().getDefaultState(), WorldGenConfig.COAL_SIZE);
        }
        if(WorldGenConfig.IRON_STATE) {
          genOre(biome, WorldGenConfig.IRON_COUNT, WorldGenConfig.IRON_BOFFSET, WorldGenConfig.IRON_MAX, QUARK_SLATE, BlockHandler.ORE_IRON_SLATE.get().getDefaultState(), WorldGenConfig.IRON_SIZE);
        }
        if(WorldGenConfig.GOLD_STATE) {
          genOre(biome, WorldGenConfig.GOLD_COUNT, WorldGenConfig.GOLD_BOFFSET, WorldGenConfig.GOLD_MAX, QUARK_SLATE, BlockHandler.ORE_GOLD_SLATE.get().getDefaultState(), WorldGenConfig.GOLD_SIZE);
        }
        if(WorldGenConfig.DIAMOND_STATE) {
          genOre(biome, WorldGenConfig.DIAMOND_COUNT, WorldGenConfig.DIAMOND_BOFFSET, WorldGenConfig.DIAMOND_MAX, QUARK_SLATE, BlockHandler.ORE_DIAMOND_SLATE.get().getDefaultState(), WorldGenConfig.DIAMOND_SIZE);
        }
        if(WorldGenConfig.EMERALD_STATE) {
          genOre(biome, WorldGenConfig.EMERALD_COUNT, WorldGenConfig.EMERALD_BOFFSET, WorldGenConfig.EMERALD_MAX, QUARK_SLATE, BlockHandler.ORE_EMERALD_SLATE.get().getDefaultState(), WorldGenConfig.EMERALD_SIZE);
        }
        if(WorldGenConfig.LAPIS_STATE) {
          genOre(biome, WorldGenConfig.LAPIS_COUNT, WorldGenConfig.LAPIS_BOFFSET, WorldGenConfig.LAPIS_MAX, QUARK_SLATE, BlockHandler.ORE_LAPIS_SLATE.get().getDefaultState(), WorldGenConfig.LAPIS_SIZE);
        }
        if(WorldGenConfig.REDSTONE_STATE) {
          genOre(biome, WorldGenConfig.REDSTONE_COUNT, WorldGenConfig.REDSTONE_BOFFSET, WorldGenConfig.REDSTONE_MAX, QUARK_SLATE, BlockHandler.ORE_REDSTONE_SLATE.get().getDefaultState(), WorldGenConfig.REDSTONE_SIZE);
        }
        if(WorldGenConfig.COPPER_STATE) {
          genOre(biome, WorldGenConfig.COPPER_COUNT, WorldGenConfig.COPPER_BOFFSET, WorldGenConfig.COPPER_MAX, QUARK_SLATE, BlockHandler.ORE_COPPER_SLATE.get().getDefaultState(), WorldGenConfig.COPPER_SIZE);
        }
        if(WorldGenConfig.ALUMINUM_STATE) {
          genOre(biome, WorldGenConfig.ALUMINUM_COUNT, WorldGenConfig.ALUMINUM_BOFFSET, WorldGenConfig.ALUMINUM_MAX, QUARK_SLATE, BlockHandler.ORE_ALUMINUM_SLATE.get().getDefaultState(), WorldGenConfig.ALUMINUM_SIZE);
        }
        if(WorldGenConfig.SILVER_STATE) {
          genOre(biome, WorldGenConfig.SILVER_COUNT, WorldGenConfig.SILVER_BOFFSET, WorldGenConfig.SILVER_MAX, QUARK_SLATE, BlockHandler.ORE_SILVER_SLATE.get().getDefaultState(), WorldGenConfig.SILVER_SIZE);
        }
        if(WorldGenConfig.LEAD_STATE) {
          genOre(biome, WorldGenConfig.LEAD_COUNT, WorldGenConfig.LEAD_BOFFSET, WorldGenConfig.LEAD_MAX, QUARK_SLATE, BlockHandler.ORE_LEAD_SLATE.get().getDefaultState(), WorldGenConfig.LEAD_SIZE);
        }
        if(WorldGenConfig.NICKEL_STATE) {
          genOre(biome, WorldGenConfig.NICKEL_COUNT, WorldGenConfig.NICKEL_BOFFSET, WorldGenConfig.NICKEL_MAX, QUARK_SLATE, BlockHandler.ORE_NICKEL_SLATE.get().getDefaultState(), WorldGenConfig.NICKEL_SIZE);
        }
        if(WorldGenConfig.URANIUM_STATE) {
          genOre(biome, WorldGenConfig.URANIUM_COUNT, WorldGenConfig.URANIUM_BOFFSET, WorldGenConfig.URANIUM_MAX, QUARK_SLATE, BlockHandler.ORE_URANIUM_SLATE.get().getDefaultState(), WorldGenConfig.URANIUM_SIZE);
        }
        if(WorldGenConfig.OSMIUM_STATE) {
          genOre(biome, WorldGenConfig.OSMIUM_COUNT, WorldGenConfig.OSMIUM_BOFFSET, WorldGenConfig.OSMIUM_MAX, QUARK_SLATE, BlockHandler.ORE_OSMIUM_SLATE.get().getDefaultState(), WorldGenConfig.OSMIUM_SIZE);
        }
        if(WorldGenConfig.TIN_STATE) {
          genOre(biome, WorldGenConfig.TIN_COUNT, WorldGenConfig.TIN_BOFFSET, WorldGenConfig.TIN_MAX, QUARK_SLATE, BlockHandler.ORE_TIN_SLATE.get().getDefaultState(), WorldGenConfig.TIN_SIZE);
        }
        if(WorldGenConfig.ZINC_STATE) {
          genOre(biome, WorldGenConfig.ZINC_COUNT, WorldGenConfig.ZINC_BOFFSET, WorldGenConfig.ZINC_MAX, QUARK_SLATE, BlockHandler.ORE_ZINC_SLATE.get().getDefaultState(), WorldGenConfig.ZINC_SIZE);
        }
        if(WorldGenConfig.CERTUS_QUARTZ_STATE) {
          genOre(biome, WorldGenConfig.CERTUS_QUARTZ_COUNT, WorldGenConfig.CERTUS_QUARTZ_BOFFSET, WorldGenConfig.CERTUS_QUARTZ_MAX, QUARK_SLATE, BlockHandler.ORE_CERTUS_QUARTZ_SLATE.get().getDefaultState(), WorldGenConfig.CERTUS_QUARTZ_SIZE);
        }
        if(WorldGenConfig.CHARGED_CERTUS_QUARTZ_STATE) {
          genOre(biome, WorldGenConfig.CHARGED_CERTUS_QUARTZ_COUNT, WorldGenConfig.CHARGED_CERTUS_QUARTZ_BOFFSET, WorldGenConfig.CHARGED_CERTUS_QUARTZ_MAX, QUARK_SLATE, BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_SLATE.get().getDefaultState(), WorldGenConfig.CHARGED_CERTUS_QUARTZ_SIZE);
        }
      }
    }
  }

  private static void genOre(Biome biome, int count, int bottomOffset, int max, OreFeatureConfig.FillerBlockType filler, BlockState defaultBlockstate, int size) {
    CountRangeConfig range = new CountRangeConfig(count, bottomOffset, 0, max);
    OreFeatureConfig feature = new OreFeatureConfig(filler, defaultBlockstate, size);

    ConfiguredPlacement config = Placement.COUNT_RANGE.configure(range);

    biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(feature).withPlacement(config));
  }

  public static void overrideFeatures(Biome biome){
    List<ConfiguredFeature> features = new ArrayList<>();
    List<Block> vanillaOres = new ArrayList<>();
    vanillaOres.add(Blocks.COAL_ORE);
    vanillaOres.add(Blocks.IRON_ORE);
    vanillaOres.add(Blocks.GOLD_ORE);
    vanillaOres.add(Blocks.DIAMOND_ORE);
    vanillaOres.add(Blocks.EMERALD_ORE);
    vanillaOres.add(Blocks.LAPIS_ORE);
    vanillaOres.add(Blocks.REDSTONE_ORE);

    for (ConfiguredFeature<?,?> loopedFeatures : biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES)) {

      if(loopedFeatures.config instanceof DecoratedFeatureConfig) {
        if (((DecoratedFeatureConfig) loopedFeatures.config).feature.feature instanceof OreFeature) {
          Block block = ((OreFeatureConfig) ((DecoratedFeatureConfig) loopedFeatures.config).feature.config).state.getBlock();
          if (vanillaOres.contains(block)) {
            features.add(loopedFeatures);
          }
        }
      }
    }
    biome.getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).removeAll(features);
  }
}