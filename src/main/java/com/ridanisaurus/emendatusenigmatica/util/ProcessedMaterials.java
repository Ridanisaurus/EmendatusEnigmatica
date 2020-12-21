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
  ROD("rod", "Rod");

  public final String id;
  public final String localisedName;

  ProcessedMaterials(String id, String localisedName) {
    this.id = id;
    this.localisedName = localisedName;
  }
}