package com.ridanisaurus.emendatusenigmatica.util;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public enum Strata {

  STONE("minecraft_stone", "minecraft:block/stone", "stone", Blocks.STONE, "Stone"),
  ANDESITE("minecraft_andesite", "minecraft:block/andesite",  "andesite", Blocks.ANDESITE, "Andesite"),

  CREATE_GABBRO("create_gabbro", "emendatusenigmatica:blocks/strata/create_gabbro",  "gabbro", new ResourceLocation("create:gabbro"), "Gabbro"),
  CREATE_LIMESTONE("create_limestone", "emendatusenigmatica:blocks/strata/create_limestone",  "c_limestone", new ResourceLocation("create:limestone"), "Limestone"),
  CREATE_SCORIA("create_scoria", "emendatusenigmatica:blocks/strata/create_scoria",  "scoria", new ResourceLocation("create:natural_scoria"), "Scoria"),
  CREATE_WEATHERED_LIMESTONE("create_weathered_limestone", "emendatusenigmatica:blocks/strata/create_weathered_limestone",  "weathered_limestone", new ResourceLocation("create:weathered_limestone"), "Weathered Limestone"),

  QUARK_JASPER("quark_jasper", "emendatusenigmatica:blocks/strata/quark_jasper",  "jasper", new ResourceLocation("quark:jasper"), "Jasper"),
  //QUARK_LIMESTONE("quark_limestone", "emendatusenigmatica:blocks/strata/quark_limestone",  "q_limestone", new ResourceLocation("quark:limestone"), "Limestone"),
  QUARK_MARBLE("quark_marble", "emendatusenigmatica:blocks/strata/quark_marble",  "marble", new ResourceLocation("quark:marble"), "Marble"),
  QUARK_SLATE("quark_slate", "emendatusenigmatica:blocks/strata/quark_slate",  "slate", new ResourceLocation("quark:slate"), "Slate"),
  ;

  public final String id;
  public final String baseTexture;
  public final String suffix;
  public final Supplier<Block> block;
  public final String localisedName;

  Strata(String id, String baseTexture, String suffix, Block block, String localisedName) {
    this.id = id;
    this.baseTexture = baseTexture;
    this.suffix = suffix;
    this.block = () -> block;
    this.localisedName = localisedName;
  }

  Strata(String id, String baseTexture,  String suffix, ResourceLocation loc, String localisedName) {
    this.id = id;
    this.baseTexture = baseTexture;
    this.suffix = suffix;
    this.block = () -> ForgeRegistries.BLOCKS.containsKey(loc) ? ForgeRegistries.BLOCKS.getValue(loc) : null;
    this.localisedName = localisedName;
  }

}