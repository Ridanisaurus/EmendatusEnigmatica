package com.ridanisaurus.emendatusenigmatica.util;

public enum ProcessedMaterials {

  INGOT("ingot", "Ingot"),
  STORAGE_BLOCK("storage_block", "Block"),
  NUGGET("nugget", "Nugget"),
  GEM("gem", "Gem"),
  DUST("dust", "Dust"),
  CHUNK("chunk", "Chunk"),
  GEAR("gear", "Gear"),
  PLATE("plate", "Plate"),
  ROD("rod", "Rod"),
  CLUSTER("cluster", "Cluster"),
  CLEAN_SLURRY("clean_slurry", "Clean Slurry"),
  DIRTY_SLURRY("dirty_slurry", "Dirty Slurry"),
  CLUMP("clump", "Clump"),
  CRYSTAL("crystal", "Crystal"),
  DIRTY_DUST("dirty_dust", "Dirty Dust"),
  SHARD("shard", "Shard"),
  CRUSHED("crushed", "Crushed"),
  FRAGMENT("fragment", "Fragment"),
  GRAVEL("gravel", "Gravel"),
  FLUID("fluid", "Fluid");

  public final String id;
  public final String localisedName;

  ProcessedMaterials(String id, String localisedName) {
    this.id = id;
    this.localisedName = localisedName;
  }
}