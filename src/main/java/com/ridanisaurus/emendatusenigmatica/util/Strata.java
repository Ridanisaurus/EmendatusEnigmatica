package com.ridanisaurus.emendatusenigmatica.util;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public enum Strata {

  STONE("minecraft_stone", "minecraft:block/stone", "stone", Blocks.STONE, "Stone", "minecraft", "overworld"),
  GRANITE("minecraft_granite", "minecraft:block/granite", "granite", Blocks.GRANITE, "Granite", "minecraft", "overworld"),
  DIORITE("minecraft_diorite", "minecraft:block/diorite", "diorite", Blocks.DIORITE, "Diorite", "minecraft", "overworld"),
  ANDESITE("minecraft_andesite", "minecraft:block/andesite",  "andesite", Blocks.ANDESITE, "Andesite", "minecraft", "overworld"),
  NETHERRACK("minecraft_netherrack", "minecraft:block/netherrack",  "netherrack", Blocks.NETHERRACK, "Netherrack", "minecraft", "nether"),
  BLACKSTONE("minecraft_blackstone", "minecraft:block/blackstone",  "blackstone", Blocks.BLACKSTONE, "Blackstone", "minecraft", "nether"),
  END_STONE("minecraft_end_stone", "minecraft:block/end_stone",  "end_stone", Blocks.END_STONE, "End Stone", "minecraft", "end"),

  CREATE_GABBRO("create_gabbro", "create:block/palettes/gabbro/plain",  "gabbro", new ResourceLocation("create:gabbro"), "Gabbro", "create", "overworld"),
  CREATE_LIMESTONE("create_limestone", "create:block/palettes/limestone/plain",  "c_limestone", new ResourceLocation("create:limestone"), "Limestone", "create", "overworld"),
  CREATE_SCORIA("create_scoria", "create:block/palettes/natural_scoria",  "scoria", new ResourceLocation("create:natural_scoria"), "Scoria", "create", "overworld"),
  CREATE_WEATHERED_LIMESTONE("create_weathered_limestone", "create:block/palettes/weathered_limestone/plain",  "weathered_limestone", new ResourceLocation("create:weathered_limestone"), "Weathered Limestone", "create", "overworld"),

  QUARK_JASPER("quark_jasper", "quark:block/jasper",  "jasper", new ResourceLocation("quark:jasper"), "Jasper", "quark", "overworld"),
  QUARK_MARBLE("quark_marble", "quark:block/marble",  "marble", new ResourceLocation("quark:marble"), "Marble", "quark", "overworld"),
  QUARK_SLATE("quark_slate", "quark:block/slate",  "slate", new ResourceLocation("quark:slate"), "Slate", "quark", "overworld"),

  BYG_MOSSY_STONE("byg_mossy_stone", "byg:block/mossy_stone", "mossy_stone", new ResourceLocation("byg:mossy_stone"), "Mossy Stone", "byg", "overworld");

  //create:block/palettes/gabbro/plain
  //emendatusenigmatica:blocks/strata/create_gabbro

  public final String id;
  public final String baseTexture;
  public final String suffix;
  public final Supplier<Block> block;
  public final String localisedName;
  public final String modid;
  public final String dim;

  Strata(String id, String baseTexture, String suffix, Block block, String localisedName, String modid, String dim) {
    this.id = id;
    this.baseTexture = baseTexture;
    this.suffix = suffix;
    this.block = () -> block;
    this.localisedName = localisedName;
    this.modid = modid;
    this.dim = dim;
  }

  Strata(String id, String baseTexture, String suffix, ResourceLocation loc, String localisedName, String modid, String dim) {
    this.id = id;
    this.baseTexture = baseTexture;
    this.suffix = suffix;
    this.block = () -> ForgeRegistries.BLOCKS.containsKey(loc) ? ForgeRegistries.BLOCKS.getValue(loc) : null;
    this.localisedName = localisedName;
    this.modid = modid;
    this.dim = dim;
  }

}