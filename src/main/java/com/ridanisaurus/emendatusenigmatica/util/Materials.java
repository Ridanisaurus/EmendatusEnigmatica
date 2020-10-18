package com.ridanisaurus.emendatusenigmatica.util;

import com.ridanisaurus.emendatusenigmatica.blocks.*;
import com.ridanisaurus.emendatusenigmatica.items.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.item.Item;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public enum Materials {

  COAL("coal", "Coal", BlockBase::new, OreCoal::new, ItemBase::new, Source.VANILLA, Type.INGOT, 17, 20, 0, 128),
  IRON("iron", "Iron", BlockBase::new, OreIron::new, ItemBase::new, Source.VANILLA, Type.INGOT, 9,20, 0, 64),
  GOLD("gold", "Gold", BlockBase::new, OreGold::new, ItemBase::new, Source.VANILLA, Type.INGOT, 9, 2, 0, 32),
  DIAMOND("diamond", "Diamond", BlockBase::new, OreDiamond::new, ItemBase::new, Source.VANILLA, Type.GEM, 8, 1, 0, 16),
  EMERALD("emerald", "Emerald", BlockBase::new, OreEmerald::new, ItemBase::new, Source.VANILLA, Type.GEM, 4, 1, 64, 128),
  LAPIS("lapis", "Lapis Lazuli", BlockBase::new, OreLapis::new, ItemBase::new, Source.VANILLA, Type.GEM, 7, 2, 0, 16),
  REDSTONE("redstone", "Redstone", BlockBase::new, OreRedstone::new, ItemBase::new, Source.VANILLA, Type.GEM, 8, 8, 0, 16),

  COPPER("copper", "Copper", BlockBase::new, OreCopper::new, ItemBase::new, Source.MODDED, Type.INGOT, 7, 20, 45, 60),
  ALUMINUM("aluminum", "Aluminum", BlockBase::new, OreAluminum::new, ItemBase::new, Source.MODDED, Type.INGOT, 3, 8, 50, 70),
  SILVER("silver", "Silver", BlockBase::new, OreSilver::new, ItemBase::new, Source.MODDED, Type.INGOT, 4, 8, 30, 38),
  LEAD("lead", "Lead", BlockBase::new, OreLead::new, ItemBase::new, Source.MODDED, Type.INGOT, 3, 8, 32, 40),
  NICKEL("nickel", "Nickel", BlockBase::new, OreNickel::new, ItemBase::new, Source.MODDED, Type.INGOT, 3, 8, 25, 40),
  URANIUM("uranium", "Uranium", BlockBase::new, OreUranium::new, ItemBase::new, Source.MODDED, Type.INGOT, 3, 4, 5, 20),
  OSMIUM("osmium", "Osmium", BlockBase::new, OreOsmium::new, ItemBase::new, Source.MODDED, Type.INGOT, 6, 20, 20, 45),
  TIN("tin", "Tin", BlockBase::new, OreTin::new, ItemBase::new, Source.MODDED, Type.INGOT, 6, 20, 40, 55),
  ZINC("zinc", "Zinc", BlockBase::new, OreZinc::new, ItemBase::new, Source.MODDED, Type.INGOT, 4, 8, 35, 50),
  CERTUS_QUARTZ("certus_quartz", "Certus Quartz", BlockBase::new, OreCertusQuartz::new, ItemBase::new, Source.MODDED, Type.GEM, 8, 16, 0, 64),
  CHARGED_CERTUS_QUARTZ("charged_certus_quartz", "Charged Certus Quartz", BlockBase::new, OreChargedCertusQuartz::new, ItemBase::new, Source.MODDED, Type.GEM, 4, 8, 0, 32),
  FLUIX("fluix", "Fluix Crystal", BlockBase::new, ItemBase::new, Source.MODDED, Type.GEM),
  FLUORITE("fluorite", "Fluorite", BlockBase::new, OreFluorite::new, ItemBase::new, Source.MODDED, Type.GEM, 12, 6, 0, 32),
  BITUMEN("bitumen", "Bitumen", BlockBase::new, OreBitumen::new, ItemBase::new, Source.MODDED, Type.GEM, 18, 25, 40, 160),

  BRONZE("bronze", "Bronze", BlockBase::new, ItemBase::new, Source.ALLOY, Type.INGOT),
  BRASS("brass", "Brass", BlockBase::new, ItemBase::new, Source.ALLOY, Type.INGOT),
  CONSTANTAN("constantan", "Constantan", BlockBase::new, ItemBase::new, Source.ALLOY, Type.INGOT),
  ELECTRUM("electrum", "Electrum", BlockBase::new, ItemBase::new, Source.ALLOY, Type.INGOT),
  STEEL("steel", "Steel", BlockBase::new, ItemBase::new, Source.ALLOY, Type.INGOT);

  public final String id;
  public final String localisedName;
  public final Supplier<Block> block;
  public final Supplier<OreBlock> oreBlock;
  public final Supplier<Item> item;
  private final Source source;
  private final Type type;

  public final OreDropInfo drop;

  public final int defaultSize;
  public final int defaultCount;
  public final int defaultMinY;
  public final int defaultMaxY;


  Materials(String id, String localisedName, Supplier<Block> block, @Nullable Supplier<OreBlock> oreBlock, Supplier<Item> item, Source source, Type type, @Nullable OreDropInfo nonSilkDrop, int s, int c, int minY, int maxY) {
    this.id = id;
    this.localisedName = localisedName;
    this.block = block;
    this.oreBlock = oreBlock;
    this.item = item;
    this.source = source;
    this.type = type;

    this.drop = nonSilkDrop;

    this.defaultSize = s;
    this.defaultCount = c;
    this.defaultMinY = minY;
    this.defaultMaxY = maxY;
  }

  Materials(String id, String localisedName, Supplier<Block> block, Supplier<OreBlock> oreBlock, Supplier<Item> item, Source source, Type type, int s, int c, int minY, int maxY) {
    this(id, localisedName, block, oreBlock, item, source, type, null, s, c, minY, maxY);
  }

  Materials(String id, String localisedName, Supplier<Block> block, Supplier<Item> item, Source source, Type type) {
    this(id, localisedName, block, null, item, source, type, null, 0, 0, 0, 0);
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

  public boolean isIngot() {
    return type == Type.INGOT;
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

  static OreDropInfo drop(Supplier<Item> item) {
    return drop(item, 1, 1);
  }

  static OreDropInfo drop(Supplier<Item> item, float min, float max) {
    return new OreDropInfo(item, min, max);
  }

  public static class OreDropInfo {
    public final Supplier<Item> item;
    public final float min;
    public final float max;

    public OreDropInfo(Supplier<Item> item, float min, float max) {
      this.item = item;
      this.min = min;
      this.max = max;
    }
  }
}