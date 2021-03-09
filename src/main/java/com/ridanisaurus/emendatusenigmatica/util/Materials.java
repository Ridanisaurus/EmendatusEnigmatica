package com.ridanisaurus.emendatusenigmatica.util;

import com.ridanisaurus.emendatusenigmatica.blocks.*;
import com.ridanisaurus.emendatusenigmatica.items.BurnableItemBase;
import com.ridanisaurus.emendatusenigmatica.items.ItemBase;
import com.ridanisaurus.emendatusenigmatica.registries.ItemHandler;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public enum Materials {

  COAL("coal", "Coal", new String[] {"Ore", "Chunk", "Dust", "Cluster"}, BlockBase::new, OreCoal::new, ItemBase::new, Source.VANILLA, drop(() -> Items.COAL), 60, 60, 15, 17),
  IRON("iron", "Iron", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster"}, BlockBase::new, OreIron::new, ItemBase::new, Source.VANILLA, 32, 32, 17, 11),
  GOLD("gold", "Gold", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster"}, BlockBase::new, OreGold::new, ItemBase::new, Source.VANILLA, 16, 16, 7, 8),
  DIAMOND("diamond", "Diamond", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster"}, BlockBase::new, OreDiamond::new, ItemBase::new, Source.VANILLA, drop(() -> Items.DIAMOND), 8, 8, 3, 8),
  EMERALD("emerald", "Emerald", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster"}, BlockBase::new, OreEmerald::new, ItemBase::new, Source.VANILLA, drop(() -> Items.EMERALD), 156, 56, 3, 4),
  LAPIS("lapis", "Lapis Lazuli", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster"}, BlockBase::new, OreLapis::new, ItemBase::new, Source.VANILLA, drop(() -> Items.LAPIS_LAZULI, 4, 9), 8, 8, 4, 7),
  REDSTONE("redstone", "Redstone", new String[] {"Ore", "Chunk", "Cluster"}, BlockBase::new, OreRedstone::new, ItemBase::new, Source.VANILLA, drop(() -> Items.REDSTONE, 4, 5), 8, 8, 8, 11),
  QUARTZ("quartz", "Nether Quartz", new String[] {"Ore", "Chunk", "Dust", "Gear", "Cluster"}, BlockBase::new, OreNetherQuartz::new, ItemBase::new, Source.VANILLA, drop(() -> Items.QUARTZ), 60, 50, 16, 14),

  COPPER("copper", "Copper", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster"}, BlockBase::new, OreCopper::new, ItemBase::new, Source.MODDED, 52, 8, 17, 7),
  ALUMINUM("aluminum", "Aluminum", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster"}, BlockBase::new, OreAluminum::new, ItemBase::new, Source.MODDED, 60, 10, 8, 5),
  SILVER("silver", "Silver", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster"}, BlockBase::new, OreSilver::new, ItemBase::new, Source.MODDED, 34, 4, 8, 5),
  LEAD("lead", "Lead", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster"}, BlockBase::new, OreLead::new, ItemBase::new, Source.MODDED, 36, 4, 8, 5),
  NICKEL("nickel", "Nickel", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster"}, BlockBase::new, OreNickel::new, ItemBase::new, Source.MODDED, 32, 8, 8, 4),
  URANIUM("uranium", "Uranium", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster"}, BlockBase::new, OreUranium::new, ItemBase::new, Source.MODDED, 12, 8, 6, 6),
  OSMIUM("osmium", "Osmium", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster"}, BlockBase::new, OreOsmium::new, ItemBase::new, Source.MODDED, 32, 12, 15, 6),
  TIN("tin", "Tin", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster"}, BlockBase::new, OreTin::new, ItemBase::new, Source.MODDED, 47, 7, 15, 7),
  ZINC("zinc", "Zinc", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster"}, BlockBase::new, OreZinc::new, ItemBase::new, Source.MODDED, 42, 8, 9, 5),

  CERTUS_QUARTZ("certus_quartz", "Certus Quartz", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust", "4xRecipe", "Cluster"}, BlockBase::new, OreCertusQuartz::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("CERTUS_QUARTZ")).get()), 32, 32, 16, 8),
  CHARGED_CERTUS_QUARTZ("charged_certus_quartz", "Charged Certus Quartz", new String[] {"Ore", "Block", "Gem", "Chunk", "4xRecipe", "Cluster"}, BlockBase::new, OreChargedCertusQuartz::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("CHARGED_CERTUS_QUARTZ")).get()), 16, 16, 8, 4),
  FLUIX("fluix", "Fluix", new String[] {"Block", "Gem", "Dust", "4xRecipe"}, BlockBase::new, ItemBase::new, Source.MODDED),
  FLUORITE("fluorite", "Fluorite", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust", "Cluster"}, BlockBase::new, OreFluorite::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("FLUORITE")).get(), 2, 4), 16, 16, 5, 9),
  BITUMEN("bitumen", "Bitumen", new String[] {"Ore", "Block", "Gem", "Chunk", "Cluster"}, BlockBase::new, OreBitumen::new, () -> new BurnableItemBase(1600), Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("BITUMEN")).get(), 2, 6), 100, 60, 2, 18),
  CINNABAR("cinnabar", "Cinnabar", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust", "Cluster"}, BlockBase::new, OreCinnabar::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("CINNABAR")).get(), 1, 2), 10, 6, 4, 5),
  APATITE("apatite", "Apatite", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust", "Cluster"}, BlockBase::new, OreApatite::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("APATITE")).get(), 4, 9), 96, 32, 2, 22),
  SULFUR("sulfur", "Sulfur", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust", "Cluster"}, BlockBase::new, OreSulfur::new, () -> new BurnableItemBase(1200), Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("SULFUR")).get(), 3, 5), 8, 8, 3, 7),
  POTASSIUM_NITRATE("potassium_nitrate", "Potassium Nitrate", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust", "Cluster"}, BlockBase::new, OrePotassiumNitrate::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("POTASSIUM_NITRATE")).get(), 3, 5), 25, 7, 4, 5),
  ARCANE("arcane", "Arcane", new String[] {"Ore", "Block", "Gem", "Chunk", "Cluster"}, BlockBase::new, OreArcane::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("ARCANE")).get()), 33, 13, 5, 6),
  DIMENSIONAL("dimensional", "Dimensional Shard", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust", "Cluster"}, BlockBase::new, OreDimensional::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("DIMENSIONAL")).get(), 4 , 5), 10, 10, 2, 2),
  COKE("coke", "Coal Coke", new String[] {"Block", "Gem", "Dust"}, BlockBase::new, () -> new BurnableItemBase(3200), Source.MODDED),
  SILICON("silicon", "Silicon", new String[] {"Gem"}, BlockBase::new, ItemBase::new, Source.MODDED),

  BRONZE("bronze", "Bronze", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, ItemBase::new, Source.ALLOY),
  BRASS("brass", "Brass", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, ItemBase::new, Source.ALLOY),
  CONSTANTAN("constantan", "Constantan", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, ItemBase::new, Source.ALLOY),
  ELECTRUM("electrum", "Electrum", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, ItemBase::new, Source.ALLOY),
  STEEL("steel", "Steel", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, ItemBase::new, Source.ALLOY),
  INVAR("invar", "Invar", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, ItemBase::new, Source.ALLOY),
  SIGNALUM("signalum", "Signalum", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, ItemBase::new, Source.ALLOY),
  LUMIUM("lumium", "Lumium", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, ItemBase::new, Source.ALLOY),
  ENDERIUM("enderium", "Enderium", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, ItemBase::new, Source.ALLOY);

  public final String id;
  public final String localisedName;
  public final Supplier<Block> block;
  public final Supplier<OreBlock> oreBlock;
  public final Supplier<Item> item;
  private final Source source;

  public final OreDropInfo drop;

  public final int defaultSize;
  public final int defaultCount;
  public final int defaultBaseline;
  public final int defaultSpread;

  public final int netherBase;
  public final int netherSpread;
  public final int netherCount;
  public final int netherSize;
  public final int endBase;
  public final int endSpread;
  public final int endCount;
  public final int endSize;

  public final String[] type;

  Materials(String id, String localisedName, String[] type, Supplier<Block> block, @Nullable Supplier<OreBlock> oreBlock, Supplier<Item> item, Source source, @Nullable OreDropInfo nonSilkDrop, int baseline, int spread, int count, int size) {
    this.id = id;
    this.localisedName = localisedName;
    this.block = block;
    this.oreBlock = oreBlock;
    this.item = item;
    this.source = source;

    this.drop = nonSilkDrop;

    this.defaultBaseline = baseline;
    this.defaultSpread = spread;
    this.defaultCount = count;
    this.defaultSize = size;

    this.netherBase = baseline + 30;
    this.netherSpread = spread;
    this.netherCount = count;
    this.netherSize = size;
    this.endBase = baseline + 20;
    this.endSpread = spread;
    this.endCount = count;
    this.endSize = size;

    this.type = type;
  }

  Materials(String id, String localisedName, String[] type, Supplier<Block> block, Supplier<OreBlock> oreBlock, Supplier<Item> item, Source source, int baseline, int spread, int count, int size) {
    this.id = id;
    this.localisedName = localisedName;
    this.block = block;
    this.oreBlock = oreBlock;
    this.item = item;
    this.source = source;

    this.drop = null;

    this.defaultBaseline = baseline;
    this.defaultSpread = spread;
    this.defaultCount = count;
    this.defaultSize = size;

    this.netherBase = baseline + 30;
    this.netherSpread = spread;
    this.netherCount = count;
    this.netherSize = size;
    this.endBase = baseline + 20;
    this.endSpread = spread;
    this.endCount = count;
    this.endSize = size;

    this.type = type;
  }

  Materials(String id, String localisedName, String[] type, Supplier<Block> block, Supplier<Item> item, Source source) {
    this(id, localisedName, type, block, null, item, source, null, 0, 0, 0, 0);
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

  private enum Source {
    VANILLA,
    MODDED,
    ALLOY
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