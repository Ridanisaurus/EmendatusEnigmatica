/*
 *  MIT License
 *
 *  Copyright (c) 2020 Ridanisaurus
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package com.ridanisaurus.emendatusenigmatica.config;

import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WorldGenConfig {
  public static class Common {

    public final BooleanValue COAL_GENERATION_STATE;
    public final IntValue COAL_VEIN_SIZE;
    public final IntValue COAL_COUNT_PER_CHUNK;
    public final IntValue COAL_BOTTOMOFFSET;
    public final IntValue COAL_MAXIMUM_HEIGHT;
    public final BooleanValue IRON_GENERATION_STATE;
    public final IntValue IRON_VEIN_SIZE;
    public final IntValue IRON_COUNT_PER_CHUNK;
    public final IntValue IRON_BOTTOMOFFSET;
    public final IntValue IRON_MAXIMUM_HEIGHT;
    public final BooleanValue GOLD_GENERATION_STATE;
    public final IntValue GOLD_VEIN_SIZE;
    public final IntValue GOLD_COUNT_PER_CHUNK;
    public final IntValue GOLD_BOTTOMOFFSET;
    public final IntValue GOLD_MAXIMUM_HEIGHT;
    public final BooleanValue DIAMOND_GENERATION_STATE;
    public final IntValue DIAMOND_VEIN_SIZE;
    public final IntValue DIAMOND_COUNT_PER_CHUNK;
    public final IntValue DIAMOND_BOTTOMOFFSET;
    public final IntValue DIAMOND_MAXIMUM_HEIGHT;
    public final BooleanValue EMERALD_GENERATION_STATE;
    public final IntValue EMERALD_VEIN_SIZE;
    public final IntValue EMERALD_COUNT_PER_CHUNK;
    public final IntValue EMERALD_BOTTOMOFFSET;
    public final IntValue EMERALD_MAXIMUM_HEIGHT;
    public final BooleanValue LAPIS_GENERATION_STATE;
    public final IntValue LAPIS_VEIN_SIZE;
    public final IntValue LAPIS_COUNT_PER_CHUNK;
    public final IntValue LAPIS_BOTTOMOFFSET;
    public final IntValue LAPIS_MAXIMUM_HEIGHT;
    public final BooleanValue REDSTONE_GENERATION_STATE;
    public final IntValue REDSTONE_VEIN_SIZE;
    public final IntValue REDSTONE_COUNT_PER_CHUNK;
    public final IntValue REDSTONE_BOTTOMOFFSET;
    public final IntValue REDSTONE_MAXIMUM_HEIGHT;
    public final BooleanValue COPPER_GENERATION_STATE;
    public final IntValue COPPER_VEIN_SIZE;
    public final IntValue COPPER_COUNT_PER_CHUNK;
    public final IntValue COPPER_BOTTOMOFFSET;
    public final IntValue COPPER_MAXIMUM_HEIGHT;
    public final BooleanValue ALUMINUM_GENERATION_STATE;
    public final IntValue ALUMINUM_VEIN_SIZE;
    public final IntValue ALUMINUM_COUNT_PER_CHUNK;
    public final IntValue ALUMINUM_BOTTOMOFFSET;
    public final IntValue ALUMINUM_MAXIMUM_HEIGHT;
    public final BooleanValue SILVER_GENERATION_STATE;
    public final IntValue SILVER_VEIN_SIZE;
    public final IntValue SILVER_COUNT_PER_CHUNK;
    public final IntValue SILVER_BOTTOMOFFSET;
    public final IntValue SILVER_MAXIMUM_HEIGHT;
    public final BooleanValue LEAD_GENERATION_STATE;
    public final IntValue LEAD_VEIN_SIZE;
    public final IntValue LEAD_COUNT_PER_CHUNK;
    public final IntValue LEAD_BOTTOMOFFSET;
    public final IntValue LEAD_MAXIMUM_HEIGHT;
    public final BooleanValue NICKEL_GENERATION_STATE;
    public final IntValue NICKEL_VEIN_SIZE;
    public final IntValue NICKEL_COUNT_PER_CHUNK;
    public final IntValue NICKEL_BOTTOMOFFSET;
    public final IntValue NICKEL_MAXIMUM_HEIGHT;
    public final BooleanValue URANIUM_GENERATION_STATE;
    public final IntValue URANIUM_VEIN_SIZE;
    public final IntValue URANIUM_COUNT_PER_CHUNK;
    public final IntValue URANIUM_BOTTOMOFFSET;
    public final IntValue URANIUM_MAXIMUM_HEIGHT;
    public final BooleanValue OSMIUM_GENERATION_STATE;
    public final IntValue OSMIUM_VEIN_SIZE;
    public final IntValue OSMIUM_COUNT_PER_CHUNK;
    public final IntValue OSMIUM_BOTTOMOFFSET;
    public final IntValue OSMIUM_MAXIMUM_HEIGHT;
    public final BooleanValue TIN_GENERATION_STATE;
    public final IntValue TIN_VEIN_SIZE;
    public final IntValue TIN_COUNT_PER_CHUNK;
    public final IntValue TIN_BOTTOMOFFSET;
    public final IntValue TIN_MAXIMUM_HEIGHT;
    public final BooleanValue ZINC_GENERATION_STATE;
    public final IntValue ZINC_VEIN_SIZE;
    public final IntValue ZINC_COUNT_PER_CHUNK;
    public final IntValue ZINC_BOTTOMOFFSET;
    public final IntValue ZINC_MAXIMUM_HEIGHT;
    public final BooleanValue CERTUS_QUARTZ_GENERATION_STATE;
    public final IntValue CERTUS_QUARTZ_VEIN_SIZE;
    public final IntValue CERTUS_QUARTZ_COUNT_PER_CHUNK;
    public final IntValue CERTUS_QUARTZ_BOTTOMOFFSET;
    public final IntValue CERTUS_QUARTZ_MAXIMUM_HEIGHT;
    public final BooleanValue CHARGED_CERTUS_QUARTZ_GENERATION_STATE;
    public final IntValue CHARGED_CERTUS_QUARTZ_VEIN_SIZE;
    public final IntValue CHARGED_CERTUS_QUARTZ_COUNT_PER_CHUNK;
    public final IntValue CHARGED_CERTUS_QUARTZ_BOTTOMOFFSET;
    public final IntValue CHARGED_CERTUS_QUARTZ_MAXIMUM_HEIGHT;
    public final BooleanValue FLUORITE_GENERATION_STATE;
    public final IntValue FLUORITE_VEIN_SIZE;
    public final IntValue FLUORITE_COUNT_PER_CHUNK;
    public final IntValue FLUORITE_BOTTOMOFFSET;
    public final IntValue FLUORITE_MAXIMUM_HEIGHT;

    public final BooleanValue VANILLA_STONE_STRATA;
    public final BooleanValue VANILLA_ANDESITE_STRATA;
    public final BooleanValue CREATE_GABBRO_STRATA;
    public final BooleanValue CREATE_LIMESTONE_STRATA;
    public final BooleanValue CREATE_SCORIA_STRATA;
    public final BooleanValue CREATE_WEATHERED_LIMESTONE_STRATA;
    public final BooleanValue QUARK_JASPER_STRATA;
    public final BooleanValue QUARK_LIMESTONE_STRATA;
    public final BooleanValue QUARK_MARBLE_STRATA;
    public final BooleanValue QUARK_SLATE_STRATA;

    public Common(ForgeConfigSpec.Builder builder) {
      builder.comment("World Gen - Ore Generation Configuration");

      builder.push("Coal Ore");
      COAL_GENERATION_STATE = builder.comment("Activate/Deactivate the World Gen [Default: true]")
              .translation("coal.config.state")
              .worldRestart()
              .define("Active", true);
      COAL_VEIN_SIZE = builder.comment("Configure the ore Vein Size [Default: 17]")
              .translation("coal.config.vein_size")
              .worldRestart()
              .defineInRange("Size", 17, 0, 64);
      COAL_COUNT_PER_CHUNK = builder.comment("Ores count per Chunk [Default: 20]")
              .translation("coal.config.count_per_chunk")
              .worldRestart()
              .defineInRange("Count", 20, 0, 64);
      COAL_BOTTOMOFFSET = builder.comment("Minimum Y-Level [Default: 0]")
              .translation("coal.config.bottom_offset")
              .worldRestart()
              .defineInRange("Minimum", 0, 0, 256);
      COAL_MAXIMUM_HEIGHT = builder.comment("Maximum Y-Level [Default: 128]")
              .translation("coal.config.maximum_height")
              .worldRestart()
              .defineInRange("Maximum", 128, 0, 256);
      builder.pop();

      builder.push("Iron Ore");
      IRON_GENERATION_STATE = builder.comment("Activate/Deactivate the World Gen [Default: true]")
              .translation("iron.config.state")
              .worldRestart()
              .define("Active", true);
      IRON_VEIN_SIZE = builder.comment("Configure the ore Vein Size [Default: 9]")
              .translation("iron.config.vein_size")
              .worldRestart()
              .defineInRange("Size", 9, 0, 64);
      IRON_COUNT_PER_CHUNK = builder.comment("Ores count per Chunk [Default: 20]")
              .translation("iron.config.count_per_chunk")
              .worldRestart()
              .defineInRange("Count", 20, 0, 64);
      IRON_BOTTOMOFFSET = builder.comment("Minimum Y-Level [Default: 0]")
              .translation("iron.config.bottom_offset")
              .worldRestart()
              .defineInRange("Minimum", 0, 0, 256);
      IRON_MAXIMUM_HEIGHT = builder.comment("Maximum Y-Level [Default: 64]")
              .translation("iron.config.maximum_height")
              .worldRestart()
              .defineInRange("Maximum", 64, 0, 256);
      builder.pop();

      builder.push("Gold Ore");
      GOLD_GENERATION_STATE = builder.comment("Activate/Deactivate the World Gen [Default: true]")
              .translation("gold.config.state")
              .worldRestart()
              .define("Active", true);
      GOLD_VEIN_SIZE = builder.comment("Configure the ore Vein Size [Default: 9]")
              .translation("gold.config.vein_size")
              .worldRestart()
              .defineInRange("Size", 9, 0, 64);
      GOLD_COUNT_PER_CHUNK = builder.comment("Ores count per Chunk [Default: 2]")
              .translation("gold.config.count_per_chunk")
              .worldRestart()
              .defineInRange("Count", 2, 0, 64);
      GOLD_BOTTOMOFFSET = builder.comment("Minimum Y-Level [Default: 0]")
              .translation("gold.config.bottom_offset")
              .worldRestart()
              .defineInRange("Minimum", 0, 0, 256);
      GOLD_MAXIMUM_HEIGHT = builder.comment("Maximum Y-Level [Default: 32]")
              .translation("gold.config.maximum_height")
              .worldRestart()
              .defineInRange("Maximum", 32, 0, 256);
      builder.pop();

      builder.push("Diamond Ore");
      DIAMOND_GENERATION_STATE = builder.comment("Activate/Deactivate the World Gen [Default: true]")
              .translation("diamond.config.state")
              .worldRestart()
              .define("Active", true);
      DIAMOND_VEIN_SIZE = builder.comment("Configure the ore Vein Size [Default: 8]")
              .translation("diamond.config.vein_size")
              .worldRestart()
              .defineInRange("Size", 8, 0, 64);
      DIAMOND_COUNT_PER_CHUNK = builder.comment("Ores count per Chunk [Default: 1]")
              .translation("diamond.config.count_per_chunk")
              .worldRestart()
              .defineInRange("Count", 1, 0, 64);
      DIAMOND_BOTTOMOFFSET = builder.comment("Minimum Y-Level [Default: 0]")
              .translation("diamond.config.bottom_offset")
              .worldRestart()
              .defineInRange("Minimum", 0, 0, 256);
      DIAMOND_MAXIMUM_HEIGHT = builder.comment("Maximum Y-Level [Default: 16]")
              .translation("diamond.config.maximum_height")
              .worldRestart()
              .defineInRange("Maximum", 16, 0, 256);
      builder.pop();

      builder.push("Emerald Ore");
      EMERALD_GENERATION_STATE = builder.comment("Activate/Deactivate the World Gen [Default: true]")
              .translation("emerald.config.state")
              .worldRestart()
              .define("Active", true);
      EMERALD_VEIN_SIZE = builder.comment("Configure the ore Vein Size [Default: 4]")
              .translation("emerald.config.vein_size")
              .worldRestart()
              .defineInRange("Size", 4, 0, 64);
      EMERALD_COUNT_PER_CHUNK = builder.comment("Ores count per Chunk [Default: 1]")
              .translation("emerald.config.count_per_chunk")
              .worldRestart()
              .defineInRange("Count", 1, 0, 64);
      EMERALD_BOTTOMOFFSET = builder.comment("Minimum Y-Level [Default: 64]")
              .translation("emerald.config.bottom_offset")
              .worldRestart()
              .defineInRange("Minimum", 64, 0, 256);
      EMERALD_MAXIMUM_HEIGHT = builder.comment("Maximum Y-Level [Default: 128]")
              .translation("emerald.config.maximum_height")
              .worldRestart()
              .defineInRange("Maximum", 128, 0, 256);
      builder.pop();

      builder.push("Lapis Ore");
      LAPIS_GENERATION_STATE = builder.comment("Activate/Deactivate the World Gen [Default: true]")
              .translation("lapis.config.state")
              .worldRestart()
              .define("Active", true);
      LAPIS_VEIN_SIZE = builder.comment("Configure the ore Vein Size [Default: 7]")
              .translation("lapis.config.vein_size")
              .worldRestart()
              .defineInRange("Size", 7, 0, 64);
      LAPIS_COUNT_PER_CHUNK = builder.comment("Ores count per Chunk [Default: 2]")
              .translation("lapis.config.count_per_chunk")
              .worldRestart()
              .defineInRange("Count", 2, 0, 64);
      LAPIS_BOTTOMOFFSET = builder.comment("Minimum Y-Level [Default: 0]")
              .translation("lapis.config.bottom_offset")
              .worldRestart()
              .defineInRange("Minimum", 0, 0, 256);
      LAPIS_MAXIMUM_HEIGHT = builder.comment("Maximum Y-Level [Default: 16]")
              .translation("lapis.config.maximum_height")
              .worldRestart()
              .defineInRange("Maximum", 16, 0, 256);
      builder.pop();

      builder.push("Redstone Ore");
      REDSTONE_GENERATION_STATE = builder.comment("Activate/Deactivate the World Gen [Default: true]")
              .translation("redstone.config.state")
              .worldRestart()
              .define("Active", true);
      REDSTONE_VEIN_SIZE = builder.comment("Configure the ore Vein Size [Default: 8]")
              .translation("redstone.config.vein_size")
              .worldRestart()
              .defineInRange("Size", 8, 0, 64);
      REDSTONE_COUNT_PER_CHUNK = builder.comment("Ores count per Chunk [Default: 8]")
              .translation("redstone.config.count_per_chunk")
              .worldRestart()
              .defineInRange("Count", 8, 0, 64);
      REDSTONE_BOTTOMOFFSET = builder.comment("Minimum Y-Level [Default: 0]")
              .translation("redstone.config.bottom_offset")
              .worldRestart()
              .defineInRange("Minimum", 0, 0, 256);
      REDSTONE_MAXIMUM_HEIGHT = builder.comment("Maximum Y-Level [Default: 16]")
              .translation("redstone.config.maximum_height")
              .worldRestart()
              .defineInRange("Maximum", 16, 0, 256);
      builder.pop();

      builder.push("Copper Ore");
      COPPER_GENERATION_STATE = builder.comment("Activate/Deactivate the World Gen [Default: true]")
              .translation("copper.config.state")
              .worldRestart()
              .define("Active", true);
      COPPER_VEIN_SIZE = builder.comment("Configure the ore Vein Size [Default: 7]")
              .translation("copper.config.vein_size")
              .worldRestart()
              .defineInRange("Size", 7, 0, 64);
      COPPER_COUNT_PER_CHUNK = builder.comment("Ores count per Chunk [Default: 20]")
              .translation("copper.config.count_per_chunk")
              .worldRestart()
              .defineInRange("Count", 20, 0, 64);
      COPPER_BOTTOMOFFSET = builder.comment("Minimum Y-Level [Default: 45]")
              .translation("copper.config.bottom_offset")
              .worldRestart()
              .defineInRange("Minimum", 45, 0, 256);
      COPPER_MAXIMUM_HEIGHT = builder.comment("Maximum Y-Level [Default: 60]")
              .translation("copper.config.maximum_height")
              .worldRestart()
              .defineInRange("Maximum", 60, 0, 256);
      builder.pop();

      builder.push("Aluminum Ore");
      ALUMINUM_GENERATION_STATE = builder.comment("Activate/Deactivate the World Gen [Default: true]")
              .translation("aluminum.config.state")
              .worldRestart()
              .define("Active", true);
      ALUMINUM_VEIN_SIZE = builder.comment("Configure the ore Vein Size [Default: 3]")
              .translation("aluminum.config.vein_size")
              .worldRestart()
              .defineInRange("Size", 3, 0, 64);
      ALUMINUM_COUNT_PER_CHUNK = builder.comment("Ores count per Chunk [Default: 8]")
              .translation("aluminum.config.count_per_chunk")
              .worldRestart()
              .defineInRange("Count", 8, 0, 64);
      ALUMINUM_BOTTOMOFFSET = builder.comment("Minimum Y-Level [Default: 50]")
              .translation("aluminum.config.bottom_offset")
              .worldRestart()
              .defineInRange("Minimum", 50, 0, 256);
      ALUMINUM_MAXIMUM_HEIGHT = builder.comment("Maximum Y-Level [Default: 70]")
              .translation("aluminum.config.maximum_height")
              .worldRestart()
              .defineInRange("Maximum", 70, 0, 256);
      builder.pop();

      builder.push("Silver Ore");
      SILVER_GENERATION_STATE = builder.comment("Activate/Deactivate the World Gen [Default: true]")
              .translation("silver.config.state")
              .worldRestart()
              .define("Active", true);
      SILVER_VEIN_SIZE = builder.comment("Configure the ore Vein Size [Default: 4]")
              .translation("silver.config.vein_size")
              .worldRestart()
              .defineInRange("Size", 4, 0, 64);
      SILVER_COUNT_PER_CHUNK = builder.comment("Ores count per Chunk [Default: 8]")
              .translation("silver.config.count_per_chunk")
              .worldRestart()
              .defineInRange("Count", 8, 0, 64);
      SILVER_BOTTOMOFFSET = builder.comment("Minimum Y-Level [Default: 30]")
              .translation("silver.config.bottom_offset")
              .worldRestart()
              .defineInRange("Minimum", 30, 0, 256);
      SILVER_MAXIMUM_HEIGHT = builder.comment("Maximum Y-Level [Default: 38]")
              .translation("silver.config.maximum_height")
              .worldRestart()
              .defineInRange("Maximum", 38, 0, 256);
      builder.pop();

      builder.push("Lead Ore");
      LEAD_GENERATION_STATE = builder.comment("Activate/Deactivate the World Gen [Default: true]")
              .translation("lead.config.state")
              .worldRestart()
              .define("Active", true);
      LEAD_VEIN_SIZE = builder.comment("Configure the ore Vein Size [Default: 3]")
              .translation("lead.config.vein_size")
              .worldRestart()
              .defineInRange("Size", 3, 0, 64);
      LEAD_COUNT_PER_CHUNK = builder.comment("Ores count per Chunk [Default: 8]")
              .translation("lead.config.count_per_chunk")
              .worldRestart()
              .defineInRange("Count", 8, 0, 64);
      LEAD_BOTTOMOFFSET = builder.comment("Minimum Y-Level [Default: 32]")
              .translation("lead.config.bottom_offset")
              .worldRestart()
              .defineInRange("Minimum", 32, 0, 256);
      LEAD_MAXIMUM_HEIGHT = builder.comment("Maximum Y-Level [Default: 40]")
              .translation("lead.config.maximum_height")
              .worldRestart()
              .defineInRange("Maximum", 40, 0, 256);
      builder.pop();

      builder.push("Nickel Ore");
      NICKEL_GENERATION_STATE = builder.comment("Activate/Deactivate the World Gen [Default: true]")
              .translation("nickel.config.state")
              .worldRestart()
              .define("Active", true);
      NICKEL_VEIN_SIZE = builder.comment("Configure the ore Vein Size [Default: 3]")
              .translation("nickel.config.vein_size")
              .worldRestart()
              .defineInRange("Size", 3, 0, 64);
      NICKEL_COUNT_PER_CHUNK = builder.comment("Ores count per Chunk [Default: 8]")
              .translation("nickel.config.count_per_chunk")
              .worldRestart()
              .defineInRange("Count", 8, 0, 64);
      NICKEL_BOTTOMOFFSET = builder.comment("Minimum Y-Level [Default: 25]")
              .translation("nickel.config.bottom_offset")
              .worldRestart()
              .defineInRange("Minimum", 25, 0, 256);
      NICKEL_MAXIMUM_HEIGHT = builder.comment("Maximum Y-Level [Default: 40]")
              .translation("nickel.config.maximum_height")
              .worldRestart()
              .defineInRange("Maximum", 40, 0, 256);
      builder.pop();

      builder.push("Uranium Ore");
      URANIUM_GENERATION_STATE = builder.comment("Activate/Deactivate the World Gen [Default: true]")
              .translation("uranium.config.state")
              .worldRestart()
              .define("Active", true);
      URANIUM_VEIN_SIZE = builder.comment("Configure the ore Vein Size [Default: 3]")
              .translation("uranium.config.vein_size")
              .worldRestart()
              .defineInRange("Size", 3, 0, 64);
      URANIUM_COUNT_PER_CHUNK = builder.comment("Ores count per Chunk [Default: 4]")
              .translation("uranium.config.count_per_chunk")
              .worldRestart()
              .defineInRange("Count", 4, 0, 64);
      URANIUM_BOTTOMOFFSET = builder.comment("Minimum Y-Level [Default: 5]")
              .translation("uranium.config.bottom_offset")
              .worldRestart()
              .defineInRange("Minimum", 5, 0, 256);
      URANIUM_MAXIMUM_HEIGHT = builder.comment("Maximum Y-Level [Default: 20]")
              .translation("uranium.config.maximum_height")
              .worldRestart()
              .defineInRange("Maximum", 20, 0, 256);
      builder.pop();

      builder.push("Osmium Ore");
      OSMIUM_GENERATION_STATE = builder.comment("Activate/Deactivate the World Gen [Default: true]")
              .translation("osmium.config.state")
              .worldRestart()
              .define("Active", true);
      OSMIUM_VEIN_SIZE = builder.comment("Configure the ore Vein Size [Default: 6]")
              .translation("osmium.config.vein_size")
              .worldRestart()
              .defineInRange("Size", 6, 0, 64);
      OSMIUM_COUNT_PER_CHUNK = builder.comment("Ores count per Chunk [Default: 20]")
              .translation("osmium.config.count_per_chunk")
              .worldRestart()
              .defineInRange("Count", 20, 0, 64);
      OSMIUM_BOTTOMOFFSET = builder.comment("Minimum Y-Level [Default: 20]")
              .translation("osmium.config.bottom_offset")
              .worldRestart()
              .defineInRange("Minimum", 20, 0, 256);
      OSMIUM_MAXIMUM_HEIGHT = builder.comment("Maximum Y-Level [Default: 45]")
              .translation("osmium.config.maximum_height")
              .worldRestart()
              .defineInRange("Maximum", 45, 0, 256);
      builder.pop();

      builder.push("Tin Ore");
      TIN_GENERATION_STATE = builder.comment("Activate/Deactivate the World Gen [Default: true]")
              .translation("tin.config.state")
              .worldRestart()
              .define("Active", true);
      TIN_VEIN_SIZE = builder.comment("Configure the ore Vein Size [Default: 6]")
              .translation("tin.config.vein_size")
              .worldRestart()
              .defineInRange("Size", 6, 0, 64);
      TIN_COUNT_PER_CHUNK = builder.comment("Ores count per Chunk [Default: 20]")
              .translation("tin.config.count_per_chunk")
              .worldRestart()
              .defineInRange("Count", 20, 0, 64);
      TIN_BOTTOMOFFSET = builder.comment("Minimum Y-Level [Default: 40]")
              .translation("tin.config.bottom_offset")
              .worldRestart()
              .defineInRange("Minimum", 40, 0, 256);
      TIN_MAXIMUM_HEIGHT = builder.comment("Maximum Y-Level [Default: 55]")
              .translation("tin.config.maximum_height")
              .worldRestart()
              .defineInRange("Maximum", 55, 0, 256);
      builder.pop();

      builder.push("Zinc Ore");
      ZINC_GENERATION_STATE = builder.comment("Activate/Deactivate the World Gen [Default: true]")
              .translation("zinc.config.state")
              .worldRestart()
              .define("Active", true);
      ZINC_VEIN_SIZE = builder.comment("Configure the ore Vein Size [Default: 4]")
              .translation("zinc.config.vein_size")
              .worldRestart()
              .defineInRange("Size", 4, 0, 64);
      ZINC_COUNT_PER_CHUNK = builder.comment("Ores count per Chunk [Default: 8]")
              .translation("zinc.config.count_per_chunk")
              .worldRestart()
              .defineInRange("Count", 8, 0, 64);
      ZINC_BOTTOMOFFSET = builder.comment("Minimum Y-Level [Default: 35]")
              .translation("zinc.config.bottom_offset")
              .worldRestart()
              .defineInRange("Minimum", 35, 0, 256);
      ZINC_MAXIMUM_HEIGHT = builder.comment("Maximum Y-Level [Default: 50]")
              .translation("zinc.config.maximum_height")
              .worldRestart()
              .defineInRange("Maximum", 50, 0, 256);
      builder.pop();

      builder.push("Certus Quartz Ore");
      CERTUS_QUARTZ_GENERATION_STATE = builder.comment("Activate/Deactivate the World Gen [Default: true]")
              .translation("certus_quartz.config.state")
              .worldRestart()
              .define("Active", true);
      CERTUS_QUARTZ_VEIN_SIZE = builder.comment("Configure the ore Vein Size [Default: 8]")
              .translation("certus_quartz.config.vein_size")
              .worldRestart()
              .defineInRange("Size", 8, 0, 64);
      CERTUS_QUARTZ_COUNT_PER_CHUNK = builder.comment("Ores count per Chunk [Default: 16]")
              .translation("certus_quartz.config.count_per_chunk")
              .worldRestart()
              .defineInRange("Count", 16, 0, 64);
      CERTUS_QUARTZ_BOTTOMOFFSET = builder.comment("Minimum Y-Level [Default: 0]")
              .translation("certus_quartz.config.bottom_offset")
              .worldRestart()
              .defineInRange("Minimum", 0, 0, 256);
      CERTUS_QUARTZ_MAXIMUM_HEIGHT = builder.comment("Maximum Y-Level [Default: 64]")
              .translation("certus_quartz.config.maximum_height")
              .worldRestart()
              .defineInRange("Maximum", 64, 0, 256);
      builder.pop();

      builder.push("Charged Certus Quartz Ore");
      CHARGED_CERTUS_QUARTZ_GENERATION_STATE = builder.comment("Activate/Deactivate the World Gen [Default: true]")
              .translation("charged_certus_quartz.config.state")
              .worldRestart()
              .define("Active", true);
      CHARGED_CERTUS_QUARTZ_VEIN_SIZE = builder.comment("Configure the ore Vein Size [Default: 4]")
              .translation("charged_certus_quartz.config.vein_size")
              .worldRestart()
              .defineInRange("Size", 4, 0, 64);
      CHARGED_CERTUS_QUARTZ_COUNT_PER_CHUNK = builder.comment("Ores count per Chunk [Default: 8]")
              .translation("charged_certus_quartz.config.count_per_chunk")
              .worldRestart()
              .defineInRange("Count", 8, 0, 64);
      CHARGED_CERTUS_QUARTZ_BOTTOMOFFSET = builder.comment("Minimum Y-Level [Default: 0]")
              .translation("charged_certus_quartz.config.bottom_offset")
              .worldRestart()
              .defineInRange("Minimum", 0, 0, 256);
      CHARGED_CERTUS_QUARTZ_MAXIMUM_HEIGHT = builder.comment("Maximum Y-Level [Default: 32]")
              .translation("charged_certus_quartz.config.maximum_height")
              .worldRestart()
              .defineInRange("Maximum", 32, 0, 256);
      builder.pop();

      builder.push("Fluorite Ore");
      FLUORITE_GENERATION_STATE = builder.comment("Activate/Deactivate the World Gen [Default: true]")
              .translation("zinc.config.state")
              .worldRestart()
              .define("Active", true);
      FLUORITE_VEIN_SIZE = builder.comment("Configure the ore Vein Size [Default: 12]")
              .translation("zinc.config.vein_size")
              .worldRestart()
              .defineInRange("Size", 12, 0, 64);
      FLUORITE_COUNT_PER_CHUNK = builder.comment("Ores count per Chunk [Default: 6]")
              .translation("zinc.config.count_per_chunk")
              .worldRestart()
              .defineInRange("Count", 6, 0, 64);
      FLUORITE_BOTTOMOFFSET = builder.comment("Minimum Y-Level [Default: 0]")
              .translation("zinc.config.bottom_offset")
              .worldRestart()
              .defineInRange("Minimum", 0, 0, 256);
      FLUORITE_MAXIMUM_HEIGHT = builder.comment("Maximum Y-Level [Default: 32]")
              .translation("zinc.config.maximum_height")
              .worldRestart()
              .defineInRange("Maximum", 32, 0, 256);
      builder.pop();

      builder.comment("World Gen - Ore Generation on Strata Stone Type");

      builder.push("Vanilla Stone");
      VANILLA_STONE_STRATA = builder.translation("strata.config.vanilla_stone")
              .worldRestart()
              .define("Active", true);
      builder.pop();

      builder.push("Vanilla Andesite");
      VANILLA_ANDESITE_STRATA = builder.translation("strata.config.vanilla_andesite")
              .worldRestart()
              .define("Active", true);
      builder.pop();

      builder.push("Create Gabbro");
      CREATE_GABBRO_STRATA = builder.translation("strata.config.create_gabbro")
              .worldRestart()
              .define("Active", true);
      builder.pop();

      builder.push("Create Limestone");
      CREATE_LIMESTONE_STRATA = builder.translation("strata.config.create_limestone")
              .worldRestart()
              .define("Active", true);
      builder.pop();

      builder.push("Create Scoria");
      CREATE_SCORIA_STRATA = builder.translation("strata.config.create_scoria")
              .worldRestart()
              .define("Active", true);
      builder.pop();

      builder.push("Create Weathered Limestone");
      CREATE_WEATHERED_LIMESTONE_STRATA = builder.translation("strata.config.create_weathered_limestone")
              .worldRestart()
              .define("Active", true);
      builder.pop();

      builder.push("Quark Jasper");
      QUARK_JASPER_STRATA = builder.translation("strata.config.quark_jasper")
              .worldRestart()
              .define("Active", true);
      builder.pop();

      builder.push("Quark Limestone");
      QUARK_LIMESTONE_STRATA = builder.translation("strata.config.quark_limestone")
              .worldRestart()
              .define("Active", true);
      builder.pop();

      builder.push("Quark Marble");
      QUARK_MARBLE_STRATA = builder.translation("strata.config.quark_marble")
              .worldRestart()
              .define("Active", true);
      builder.pop();

      builder.push("Quark Slate");
      QUARK_SLATE_STRATA = builder.translation("strata.config.quark_slate")
              .worldRestart()
              .define("Active", true);
      builder.pop();
    }
  }

  public static boolean COAL_STATE;
  public static int COAL_SIZE;
  public static int COAL_COUNT;
  public static int COAL_BOFFSET;
  public static int COAL_MAX;
  public static boolean IRON_STATE;
  public static int IRON_SIZE;
  public static int IRON_COUNT;
  public static int IRON_BOFFSET;
  public static int IRON_MAX;
  public static boolean GOLD_STATE;
  public static int GOLD_SIZE;
  public static int GOLD_COUNT;
  public static int GOLD_BOFFSET;
  public static int GOLD_MAX;
  public static boolean DIAMOND_STATE;
  public static int DIAMOND_SIZE;
  public static int DIAMOND_COUNT;
  public static int DIAMOND_BOFFSET;
  public static int DIAMOND_MAX;
  public static boolean EMERALD_STATE;
  public static int EMERALD_SIZE;
  public static int EMERALD_COUNT;
  public static int EMERALD_BOFFSET;
  public static int EMERALD_MAX;
  public static boolean LAPIS_STATE;
  public static int LAPIS_SIZE;
  public static int LAPIS_COUNT;
  public static int LAPIS_BOFFSET;
  public static int LAPIS_MAX;
  public static boolean REDSTONE_STATE;
  public static int REDSTONE_SIZE;
  public static int REDSTONE_COUNT;
  public static int REDSTONE_BOFFSET;
  public static int REDSTONE_MAX;
  public static boolean COPPER_STATE;
  public static int COPPER_SIZE;
  public static int COPPER_COUNT;
  public static int COPPER_BOFFSET;
  public static int COPPER_MAX;
  public static boolean ALUMINUM_STATE;
  public static int ALUMINUM_SIZE;
  public static int ALUMINUM_COUNT;
  public static int ALUMINUM_BOFFSET;
  public static int ALUMINUM_MAX;
  public static boolean SILVER_STATE;
  public static int SILVER_SIZE;
  public static int SILVER_COUNT;
  public static int SILVER_BOFFSET;
  public static int SILVER_MAX;
  public static boolean LEAD_STATE;
  public static int LEAD_SIZE;
  public static int LEAD_COUNT;
  public static int LEAD_BOFFSET;
  public static int LEAD_MAX;
  public static boolean NICKEL_STATE;
  public static int NICKEL_SIZE;
  public static int NICKEL_COUNT;
  public static int NICKEL_BOFFSET;
  public static int NICKEL_MAX;
  public static boolean URANIUM_STATE;
  public static int URANIUM_SIZE;
  public static int URANIUM_COUNT;
  public static int URANIUM_BOFFSET;
  public static int URANIUM_MAX;
  public static boolean OSMIUM_STATE;
  public static int OSMIUM_SIZE;
  public static int OSMIUM_COUNT;
  public static int OSMIUM_BOFFSET;
  public static int OSMIUM_MAX;
  public static boolean TIN_STATE;
  public static int TIN_SIZE;
  public static int TIN_COUNT;
  public static int TIN_BOFFSET;
  public static int TIN_MAX;
  public static boolean ZINC_STATE;
  public static int ZINC_SIZE;
  public static int ZINC_COUNT;
  public static int ZINC_BOFFSET;
  public static int ZINC_MAX;
  public static boolean CERTUS_QUARTZ_STATE;
  public static int CERTUS_QUARTZ_SIZE;
  public static int CERTUS_QUARTZ_COUNT;
  public static int CERTUS_QUARTZ_BOFFSET;
  public static int CERTUS_QUARTZ_MAX;
  public static boolean CHARGED_CERTUS_QUARTZ_STATE;
  public static int CHARGED_CERTUS_QUARTZ_SIZE;
  public static int CHARGED_CERTUS_QUARTZ_COUNT;
  public static int CHARGED_CERTUS_QUARTZ_BOFFSET;
  public static int CHARGED_CERTUS_QUARTZ_MAX;
  public static boolean FLUORITE_STATE;
  public static int FLUORITE_SIZE;
  public static int FLUORITE_COUNT;
  public static int FLUORITE_BOFFSET;
  public static int FLUORITE_MAX;

  public static boolean VANILLA_STONE_STATE;
  public static boolean VANILLA_ANDESITE_STATE;
  public static boolean CREATE_GABBRO_STATE;
  public static boolean CREATE_LIMESTONE_STATE;
  public static boolean CREATE_SCORIA_STATE;
  public static boolean CREATE_WEATHERED_LIMESTONE_STATE;
  public static boolean QUARK_JASPER_STATE;
  public static boolean QUARK_LIMESTONE_STATE;
  public static boolean QUARK_MARBLE_STATE;
  public static boolean QUARK_SLATE_STATE;


  public static void bakeConfig(ModConfig config) {
    COAL_STATE = COMMON.COAL_GENERATION_STATE.get();
    COAL_SIZE = COMMON.COAL_VEIN_SIZE.get();
    COAL_COUNT = COMMON.COAL_COUNT_PER_CHUNK.get();
    COAL_BOFFSET = COMMON.COAL_BOTTOMOFFSET.get();
    COAL_MAX = COMMON.COAL_MAXIMUM_HEIGHT.get();
    IRON_STATE = COMMON.IRON_GENERATION_STATE.get();
    IRON_SIZE = COMMON.IRON_VEIN_SIZE.get();
    IRON_COUNT = COMMON.IRON_COUNT_PER_CHUNK.get();
    IRON_BOFFSET = COMMON.IRON_BOTTOMOFFSET.get();
    IRON_MAX = COMMON.IRON_MAXIMUM_HEIGHT.get();
    GOLD_STATE = COMMON.GOLD_GENERATION_STATE.get();
    GOLD_SIZE = COMMON.GOLD_VEIN_SIZE.get();
    GOLD_COUNT = COMMON.GOLD_COUNT_PER_CHUNK.get();
    GOLD_BOFFSET = COMMON.GOLD_BOTTOMOFFSET.get();
    GOLD_MAX = COMMON.GOLD_MAXIMUM_HEIGHT.get();
    DIAMOND_STATE = COMMON.DIAMOND_GENERATION_STATE.get();
    DIAMOND_SIZE = COMMON.DIAMOND_VEIN_SIZE.get();
    DIAMOND_COUNT = COMMON.DIAMOND_COUNT_PER_CHUNK.get();
    DIAMOND_BOFFSET = COMMON.DIAMOND_BOTTOMOFFSET.get();
    DIAMOND_MAX = COMMON.DIAMOND_MAXIMUM_HEIGHT.get();
    EMERALD_STATE = COMMON.EMERALD_GENERATION_STATE.get();
    EMERALD_SIZE = COMMON.EMERALD_VEIN_SIZE.get();
    EMERALD_COUNT = COMMON.EMERALD_COUNT_PER_CHUNK.get();
    EMERALD_BOFFSET = COMMON.EMERALD_BOTTOMOFFSET.get();
    EMERALD_MAX = COMMON.EMERALD_MAXIMUM_HEIGHT.get();
    LAPIS_STATE = COMMON.LAPIS_GENERATION_STATE.get();
    LAPIS_SIZE = COMMON.LAPIS_VEIN_SIZE.get();
    LAPIS_COUNT = COMMON.LAPIS_COUNT_PER_CHUNK.get();
    LAPIS_BOFFSET = COMMON.LAPIS_BOTTOMOFFSET.get();
    LAPIS_MAX = COMMON.LAPIS_MAXIMUM_HEIGHT.get();
    REDSTONE_STATE = COMMON.REDSTONE_GENERATION_STATE.get();
    REDSTONE_SIZE = COMMON.REDSTONE_VEIN_SIZE.get();
    REDSTONE_COUNT = COMMON.REDSTONE_COUNT_PER_CHUNK.get();
    REDSTONE_BOFFSET = COMMON.REDSTONE_BOTTOMOFFSET.get();
    REDSTONE_MAX = COMMON.REDSTONE_MAXIMUM_HEIGHT.get();
    COPPER_STATE = COMMON.COPPER_GENERATION_STATE.get();
    COPPER_SIZE = COMMON.COPPER_VEIN_SIZE.get();
    COPPER_COUNT = COMMON.COPPER_COUNT_PER_CHUNK.get();
    COPPER_BOFFSET = COMMON.COPPER_BOTTOMOFFSET.get();
    COPPER_MAX = COMMON.COPPER_MAXIMUM_HEIGHT.get();
    ALUMINUM_STATE = COMMON.ALUMINUM_GENERATION_STATE.get();
    ALUMINUM_SIZE = COMMON.ALUMINUM_VEIN_SIZE.get();
    ALUMINUM_COUNT = COMMON.ALUMINUM_COUNT_PER_CHUNK.get();
    ALUMINUM_BOFFSET = COMMON.ALUMINUM_BOTTOMOFFSET.get();
    ALUMINUM_MAX = COMMON.ALUMINUM_MAXIMUM_HEIGHT.get();
    SILVER_STATE = COMMON.SILVER_GENERATION_STATE.get();
    SILVER_SIZE = COMMON.SILVER_VEIN_SIZE.get();
    SILVER_COUNT = COMMON.SILVER_COUNT_PER_CHUNK.get();
    SILVER_BOFFSET = COMMON.SILVER_BOTTOMOFFSET.get();
    SILVER_MAX = COMMON.SILVER_MAXIMUM_HEIGHT.get();
    LEAD_STATE = COMMON.LEAD_GENERATION_STATE.get();
    LEAD_SIZE = COMMON.LEAD_VEIN_SIZE.get();
    LEAD_COUNT = COMMON.LEAD_COUNT_PER_CHUNK.get();
    LEAD_BOFFSET = COMMON.LEAD_BOTTOMOFFSET.get();
    LEAD_MAX = COMMON.LEAD_MAXIMUM_HEIGHT.get();
    NICKEL_STATE = COMMON.NICKEL_GENERATION_STATE.get();
    NICKEL_SIZE = COMMON.NICKEL_VEIN_SIZE.get();
    NICKEL_COUNT = COMMON.NICKEL_COUNT_PER_CHUNK.get();
    NICKEL_BOFFSET = COMMON.NICKEL_BOTTOMOFFSET.get();
    NICKEL_MAX = COMMON.NICKEL_MAXIMUM_HEIGHT.get();
    URANIUM_STATE = COMMON.URANIUM_GENERATION_STATE.get();
    URANIUM_SIZE = COMMON.URANIUM_VEIN_SIZE.get();
    URANIUM_COUNT = COMMON.URANIUM_COUNT_PER_CHUNK.get();
    URANIUM_BOFFSET = COMMON.URANIUM_BOTTOMOFFSET.get();
    URANIUM_MAX = COMMON.URANIUM_MAXIMUM_HEIGHT.get();
    OSMIUM_STATE = COMMON.OSMIUM_GENERATION_STATE.get();
    OSMIUM_SIZE = COMMON.OSMIUM_VEIN_SIZE.get();
    OSMIUM_COUNT = COMMON.OSMIUM_COUNT_PER_CHUNK.get();
    OSMIUM_BOFFSET = COMMON.OSMIUM_BOTTOMOFFSET.get();
    OSMIUM_MAX = COMMON.OSMIUM_MAXIMUM_HEIGHT.get();
    TIN_STATE = COMMON.TIN_GENERATION_STATE.get();
    TIN_SIZE = COMMON.TIN_VEIN_SIZE.get();
    TIN_COUNT = COMMON.TIN_COUNT_PER_CHUNK.get();
    TIN_BOFFSET = COMMON.TIN_BOTTOMOFFSET.get();
    TIN_MAX = COMMON.TIN_MAXIMUM_HEIGHT.get();
    ZINC_STATE = COMMON.ZINC_GENERATION_STATE.get();
    ZINC_SIZE = COMMON.ZINC_VEIN_SIZE.get();
    ZINC_COUNT = COMMON.ZINC_COUNT_PER_CHUNK.get();
    ZINC_BOFFSET = COMMON.ZINC_BOTTOMOFFSET.get();
    ZINC_MAX = COMMON.ZINC_MAXIMUM_HEIGHT.get();
    CERTUS_QUARTZ_STATE = COMMON.CERTUS_QUARTZ_GENERATION_STATE.get();
    CERTUS_QUARTZ_SIZE = COMMON.CERTUS_QUARTZ_VEIN_SIZE.get();
    CERTUS_QUARTZ_COUNT = COMMON.CERTUS_QUARTZ_COUNT_PER_CHUNK.get();
    CERTUS_QUARTZ_BOFFSET = COMMON.CERTUS_QUARTZ_BOTTOMOFFSET.get();
    CERTUS_QUARTZ_MAX = COMMON.CERTUS_QUARTZ_MAXIMUM_HEIGHT.get();
    CHARGED_CERTUS_QUARTZ_STATE = COMMON.CHARGED_CERTUS_QUARTZ_GENERATION_STATE.get();
    CHARGED_CERTUS_QUARTZ_SIZE = COMMON.CHARGED_CERTUS_QUARTZ_VEIN_SIZE.get();
    CHARGED_CERTUS_QUARTZ_COUNT = COMMON.CHARGED_CERTUS_QUARTZ_COUNT_PER_CHUNK.get();
    CHARGED_CERTUS_QUARTZ_BOFFSET = COMMON.CHARGED_CERTUS_QUARTZ_BOTTOMOFFSET.get();
    CHARGED_CERTUS_QUARTZ_MAX = COMMON.CHARGED_CERTUS_QUARTZ_MAXIMUM_HEIGHT.get();
    FLUORITE_STATE = COMMON.FLUORITE_GENERATION_STATE.get();
    FLUORITE_SIZE = COMMON.FLUORITE_VEIN_SIZE.get();
    FLUORITE_COUNT = COMMON.FLUORITE_COUNT_PER_CHUNK.get();
    FLUORITE_BOFFSET = COMMON.FLUORITE_BOTTOMOFFSET.get();
    FLUORITE_MAX = COMMON.FLUORITE_MAXIMUM_HEIGHT.get();

    VANILLA_STONE_STATE = COMMON.VANILLA_STONE_STRATA.get();
    VANILLA_ANDESITE_STATE = COMMON.VANILLA_ANDESITE_STRATA.get();
    CREATE_GABBRO_STATE = COMMON.CREATE_GABBRO_STRATA.get();
    CREATE_LIMESTONE_STATE = COMMON.CREATE_LIMESTONE_STRATA.get();
    CREATE_SCORIA_STATE = COMMON.CREATE_SCORIA_STRATA.get();
    CREATE_WEATHERED_LIMESTONE_STATE = COMMON.CREATE_WEATHERED_LIMESTONE_STRATA.get();
    QUARK_JASPER_STATE = COMMON.QUARK_JASPER_STRATA.get();
    QUARK_LIMESTONE_STATE = COMMON.QUARK_LIMESTONE_STRATA.get();
    QUARK_MARBLE_STATE = COMMON.QUARK_MARBLE_STRATA.get();
    QUARK_SLATE_STATE = COMMON.QUARK_SLATE_STRATA.get();
  }

  public static final ForgeConfigSpec COMMON_SPEC;
  public static final Common COMMON;
  static {
    final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
    COMMON_SPEC = specPair.getRight();
    COMMON = specPair.getLeft();
  }

  @SubscribeEvent
  public static void onModConfigEvent(final ModConfig.ModConfigEvent event) {

  }

  @SubscribeEvent
  public static void onLoad(final ModConfig.Loading event) {
    final ModConfig config = event.getConfig();
    // Rebake the configs when they change
    if (config.getSpec() == COMMON_SPEC) {
      bakeConfig(config);
      EmendatusEnigmatica.LOGGER.debug("Baked common config on Load");
    }
  }

  @SubscribeEvent
  public static void onReload(final ModConfig.Reloading event) {

  }
}
