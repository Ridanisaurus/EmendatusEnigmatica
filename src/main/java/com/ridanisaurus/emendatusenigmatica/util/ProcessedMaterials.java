package com.ridanisaurus.emendatusenigmatica.util;

import com.ridanisaurus.emendatusenigmatica.blocks.BlockBase;
import net.minecraft.block.Block;

import java.util.function.Supplier;

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
  SHARD("shard", "Shard");

  public final String id;
  public final String localisedName;

  ProcessedMaterials(String id, String localisedName) {
    this.id = id;
    this.localisedName = localisedName;
  }
}