package com.ridanisaurus.emendatusenigmatica.util;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public enum Strata {

  STONE("minecraft_stone", "minecraft:block/stone", "stone", Blocks.STONE, "Stone"),
  ANDESITE("minecraft_andesite", "minecraft:block/andesite",  "andesite", Blocks.ANDESITE, "Andesite"),

  CREATE_GABBRO("create_gabbro", "create:block/palettes/gabbro/plain",  "gabbro", new ResourceLocation("create:gabbro"), "Gabbro"),
  CREATE_LIMESTONE("create_limestone", "create:block/palettes/limestone/plain",  "c_limestone", new ResourceLocation("create:limestone"), "Limestone"),
  CREATE_SCORIA("create_scoria", "create:block/palettes/natural_scoria",  "scoria", new ResourceLocation("create:natural_scoria"), "Scoria"),
  CREATE_WEATHERED_LIMESTONE("create_weathered_limestone", "create:block/palettes/weathered_limestone/plain",  "weathered_limestone", new ResourceLocation("create:weathered_limestone"), "Weathered Limestone"),

  QUARK_JASPER("quark_jasper", "quark:block/jasper",  "jasper", new ResourceLocation("quark:jasper"), "Jasper"),
  QUARK_MARBLE("quark_marble", "quark:block/marble",  "marble", new ResourceLocation("quark:marble"), "Marble"),
  QUARK_SLATE("quark_slate", "quark:block/slate",  "slate", new ResourceLocation("quark:slate"), "Slate");

  //create:block/palettes/gabbro/plain
  //emendatusenigmatica:blocks/strata/create_gabbro

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

  Strata(String id, String baseTexture, String suffix, ResourceLocation loc, String localisedName) {
    this.id = id;
    this.baseTexture = baseTexture;
    this.suffix = suffix;
    this.block = () -> ForgeRegistries.BLOCKS.containsKey(loc) ? ForgeRegistries.BLOCKS.getValue(loc) : null;
    this.localisedName = localisedName;
  }

}