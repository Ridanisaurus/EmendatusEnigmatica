package com.ridanisaurus.emendatusenigmatica.util;

import com.ridanisaurus.emendatusenigmatica.blocks.*;
import com.ridanisaurus.emendatusenigmatica.items.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.function.Supplier;

public enum Materials {

  COAL("coal", "Coal", BlockBase::new, ItemBase::new, Source.VANILLA),
  IRON("iron", "Iron", BlockBase::new, ItemBase::new, Source.VANILLA),
  GOLD("gold", "Gold", BlockBase::new, ItemBase::new, Source.VANILLA),
  DIAMOND("diamond", "Diamond", BlockBase::new, ItemBase::new, Source.VANILLA),
  EMERALD("emerald", "Emerald", BlockBase::new, ItemBase::new, Source.VANILLA),
  LAPIS("lapis", "Lapis Lazuli", BlockBase::new, ItemBase::new, Source.VANILLA),
  REDSTONE("redstone", "Redstone", BlockBase::new, ItemBase::new, Source.VANILLA),

  COPPER("copper", "Copper", BlockBase::new, ItemBase::new, Source.MODDED),
  ALUMINUM("aluminum", "Aluminum", BlockBase::new, ItemBase::new, Source.MODDED),
  SILVER("silver", "Silver", BlockBase::new, ItemBase::new, Source.MODDED),
  LEAD("lead", "Lead", BlockBase::new, ItemBase::new, Source.MODDED),
  NICKEL("nickel", "Nickel", BlockBase::new, ItemBase::new, Source.MODDED),
  URANIUM("uranium", "Uranium", BlockBase::new, ItemBase::new, Source.MODDED),
  OSMIUM("osmium", "Osmium", BlockBase::new, ItemBase::new, Source.MODDED),
  TIN("tin", "Tin", BlockBase::new, ItemBase::new, Source.MODDED),
  ZINC("zinc", "Zinc", BlockBase::new, ItemBase::new, Source.MODDED),
  CERTUS_QUARTZ("certus_quartz", "Certus Quartz", BlockBase::new, ItemBase::new, Source.MODDED, Type.GEM),
  CHARGED_CERTUS_QUARTZ("charged_certus_quartz", "Charged Certus Quartz", BlockBase::new, ItemBase::new, Source.MODDED, Type.GEM),
  FLUIX("fluix", "Fluix Crystal", BlockBase::new, ItemBase::new, Source.MODDED, Type.GEM),
  FLUORITE("fluorite", "Fluorite", BlockBase::new, ItemBase::new, Source.MODDED, Type.GEM),
  BITUMEN("bitumen", "Bitumen", BlockBase::new, ItemBase::new, Source.MODDED, Type.GEM),

  BRONZE("bronze", "Bronze", BlockBase::new, ItemBase::new, Source.ALLOY),
  BRASS("brass", "Brass", BlockBase::new, ItemBase::new, Source.ALLOY),
  CONSTANTAN("constantan", "Constantan", BlockBase::new, ItemBase::new, Source.ALLOY),
  ELECTRUM("electrum", "Electrum", BlockBase::new, ItemBase::new, Source.ALLOY),
  STEEL("steel", "Steel", BlockBase::new, ItemBase::new, Source.ALLOY);

  public final String id;
  public final String localisedName;
  public final Supplier<Block> block;
  public final Supplier<Item> item;
  private final Source source;
  private final Type type;

  Materials(String id, String localisedName, Supplier<Block> block, Supplier<Item> item, Source source, Type type) {
    this.id = id;
    this.localisedName = localisedName;
    this.block = block;
    this.item = item;
    this.source = source;
    this.type = type;
  }

  Materials(String id, String localisedName, Supplier<Block> block, Supplier<Item> item, Source source) {
    this.id = id;
    this.localisedName = localisedName;
    this.block = block;
    this.item = item;
    this.source = source;
    this.type = Type.INGOT;
  }

  public boolean isVanilla() {
    return source == Source.VANILLA;
  }

  public boolean isModded() {
    return source == Source.MODDED;
  }

  public boolean isAlloy() {
    return source == Source.ALLOY;
  }

  public boolean isGem() {
    return type == Type.GEM;
  }

  private enum Source {
    VANILLA,
    MODDED,
    ALLOY
  }

  private enum Type {
    INGOT,
    GEM
  }
}