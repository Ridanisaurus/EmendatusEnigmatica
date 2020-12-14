package com.ridanisaurus.emendatusenigmatica.util;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public enum Strata {

  STONE("minecraft_stone", "minecraft:block/stone", "stone", Blocks.STONE, "Stone", "minecraft"),
  ANDESITE("minecraft_andesite", "minecraft:block/andesite",  "andesite", Blocks.ANDESITE, "Andesite", "minecraft"),
  //NETHERRACK("minecraft_netherrack", "minecraft:block/netherrack",  "netherrack", Blocks.NETHERRACK, "Netherrack", "minecraft"),
  //BLACKSTONE("minecraft_blackstone", "minecraft:block/blackstone",  "blackstone", Blocks.BLACKSTONE, "Blackstone", "minecraft"),

  CREATE_GABBRO("create_gabbro", "create:block/palettes/gabbro/plain",  "gabbro", new ResourceLocation("create:gabbro"), "Gabbro", "create"),
  CREATE_LIMESTONE("create_limestone", "create:block/palettes/limestone/plain",  "c_limestone", new ResourceLocation("create:limestone"), "Limestone", "create"),
  CREATE_SCORIA("create_scoria", "create:block/palettes/natural_scoria",  "scoria", new ResourceLocation("create:natural_scoria"), "Scoria", "create"),
  CREATE_WEATHERED_LIMESTONE("create_weathered_limestone", "create:block/palettes/weathered_limestone/plain",  "weathered_limestone", new ResourceLocation("create:weathered_limestone"), "Weathered Limestone", "create"),

  QUARK_JASPER("quark_jasper", "quark:block/jasper",  "jasper", new ResourceLocation("quark:jasper"), "Jasper", "quark"),
  QUARK_MARBLE("quark_marble", "quark:block/marble",  "marble", new ResourceLocation("quark:marble"), "Marble", "quark"),
  QUARK_SLATE("quark_slate", "quark:block/slate",  "slate", new ResourceLocation("quark:slate"), "Slate", "quark");

  //create:block/palettes/gabbro/plain
  //emendatusenigmatica:blocks/strata/create_gabbro

  public final String id;
  public final String baseTexture;
  public final String suffix;
  public final Supplier<Block> block;
  public final String localisedName;
  public final String modid;

  Strata(String id, String baseTexture, String suffix, Block block, String localisedName, String modid) {
    this.id = id;
    this.baseTexture = baseTexture;
    this.suffix = suffix;
    this.block = () -> block;
    this.localisedName = localisedName;
    this.modid = modid;
  }

  Strata(String id, String baseTexture, String suffix, ResourceLocation loc, String localisedName, String modid) {
    this.id = id;
    this.baseTexture = baseTexture;
    this.suffix = suffix;
    this.block = () -> ForgeRegistries.BLOCKS.containsKey(loc) ? ForgeRegistries.BLOCKS.getValue(loc) : null;
    this.localisedName = localisedName;
    this.modid = modid;
  }

}