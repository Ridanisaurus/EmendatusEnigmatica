package com.ridanisaurus.emendatusenigmatica.util;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public enum Strata {

  STONE("minecraft_stone", "stone", Blocks.STONE, "Stone"),
  ANDESITE("minecraft_andesite", "andesite", Blocks.ANDESITE, "Andesite"),

  CREATE_GABBRO("create_gabbro", "gabbro", new ResourceLocation("create:gabbro"), "Gabbro"),
  CREATE_LIMESTONE("create_limestone", "c_limestone", new ResourceLocation("create:limestone"), "Limestone"),
  CREATE_SCORIA("create_scoria", "scoria", new ResourceLocation("create:natural_scoria"), "Scoria"),
  CREATE_WEATHERED_LIMESTONE("create_weathered_limestone", "weathered_limestone", new ResourceLocation("create:weathered_limestone"), "Weathered Limestone"),

  QUARK_JASPER("quark_jasper", "jasper", new ResourceLocation("quark:jasper"), "Jasper"),
  QUARK_LIMESTONE("quark_limestone", "q_limestone", new ResourceLocation("quark:limestone"), "Limestone"),
  QUARK_MARBLE("quark_marble", "marble", new ResourceLocation("quark:marlbe"), "Marble"),
  QUARK_SLATE("quark_slate", "slate", new ResourceLocation("quark:slate"), "Slate"),
  ;

  public final String id;
  public final String suffix;
  public final Supplier<Block> block;
  public final String localisedName;

  Strata(String id, String suffix, Block block, String localisedName) {
    this.id = id;
    this.suffix = suffix;
    this.block = () -> block;
    this.localisedName = localisedName;
  }

  Strata(String id, String suffix, ResourceLocation loc, String localisedName) {
    this.id = id;
    this.suffix = suffix;
    this.block = () -> ForgeRegistries.BLOCKS.containsKey(loc) ? ForgeRegistries.BLOCKS.getValue(loc) : null;
    this.localisedName = localisedName;
  }

}
