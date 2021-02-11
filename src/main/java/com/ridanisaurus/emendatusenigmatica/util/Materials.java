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

  COAL("coal", "Coal", new String[] {"Ore", "Chunk", "Dust"}, BlockBase::new, OreCoal::new, ItemBase::new, Source.VANILLA, drop(() -> Items.COAL),17, 20, 60, 60, 0, 0, 0, 0, 0, 0, 0, 0),
  IRON("iron", "Iron", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreIron::new, ItemBase::new, Source.VANILLA,  11,20, 32, 32, 0, 0, 0, 0, 0, 0, 0, 0),
  GOLD("gold", "Gold", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreGold::new, ItemBase::new, Source.VANILLA, 8, 7, 16, 16, 0, 0, 0, 0, 0, 0, 0, 0),
  DIAMOND("diamond", "Diamond", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreDiamond::new, ItemBase::new, Source.VANILLA, drop(() -> Items.DIAMOND), 8, 3, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0),
  EMERALD("emerald", "Emerald", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreEmerald::new, ItemBase::new, Source.VANILLA, drop(() -> Items.EMERALD), 6, 1, 96, 32, 0, 0, 0, 0, 0, 0, 0, 0),
  LAPIS("lapis", "Lapis Lazuli", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreLapis::new, ItemBase::new, Source.VANILLA, drop(() -> Items.LAPIS_LAZULI, 4, 9), 7, 4, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0),
  REDSTONE("redstone", "Redstone", new String[] {"Ore", "Chunk"}, BlockBase::new, OreRedstone::new, ItemBase::new, Source.VANILLA, drop(() -> Items.REDSTONE, 4, 5), 14, 9, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0),
  QUARTZ("quartz", "Quartz", new String[] {"Dust", "Gear"}, BlockBase::new, ItemBase::new, Source.VANILLA),

  COPPER("copper", "Copper", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreCopper::new, ItemBase::new, Source.MODDED, 7, 20, 52, 8, 0, 0, 0, 0, 0, 0, 0, 0),
  ALUMINUM("aluminum", "Aluminum", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreAluminum::new, ItemBase::new, Source.MODDED, 5, 8, 60, 10, 0, 0, 0, 0, 0, 0, 0, 0),
  SILVER("silver", "Silver", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreSilver::new, ItemBase::new, Source.MODDED, 5, 8, 34, 4, 0, 0, 0, 0, 0, 0, 0, 0),
  LEAD("lead", "Lead", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreLead::new, ItemBase::new, Source.MODDED, 5, 8, 36, 4, 0, 0, 0, 0, 0, 0, 0, 0),
  NICKEL("nickel", "Nickel", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreNickel::new, ItemBase::new, Source.MODDED, 4, 8, 32, 8, 0, 0, 0, 0, 0, 0, 0, 0),
  URANIUM("uranium", "Uranium", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreUranium::new, ItemBase::new, Source.MODDED, 6, 6, 12, 8, 0, 0, 0, 0, 0, 0, 0, 0),
  OSMIUM("osmium", "Osmium", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreOsmium::new, ItemBase::new, Source.MODDED, 6, 17, 32, 12, 0, 0, 0, 0, 0, 0, 0, 0),
  TIN("tin", "Tin", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreTin::new, ItemBase::new, Source.MODDED, 7, 20, 47, 7, 0, 0, 0, 0, 0, 0, 0, 0),
  ZINC("zinc", "Zinc", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreZinc::new, ItemBase::new, Source.MODDED, 5, 9, 42, 8, 0, 0, 0, 0, 0, 0, 0, 0),

  CERTUS_QUARTZ("certus_quartz", "Certus Quartz", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust", "4xRecipe"}, BlockBase::new, OreCertusQuartz::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("CERTUS_QUARTZ")).get()), 8, 16, 32, 32, 0, 0, 0, 0, 0, 0, 0, 0),
  CHARGED_CERTUS_QUARTZ("charged_certus_quartz", "Charged Certus Quartz", new String[] {"Ore", "Block", "Gem", "Chunk", "4xRecipe"}, BlockBase::new, OreChargedCertusQuartz::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("CHARGED_CERTUS_QUARTZ")).get()), 4, 8, 16, 16, 0, 0, 0, 0, 0, 0, 0, 0),
  FLUIX("fluix", "Fluix", new String[] {"Block", "Gem", "Dust", "4xRecipe"}, BlockBase::new, ItemBase::new, Source.MODDED),
  FLUORITE("fluorite", "Fluorite", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust"}, BlockBase::new, OreFluorite::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("FLUORITE")).get(), 2, 4), 12, 6, 16, 16, 0, 0, 0, 0, 0, 0, 0, 0),
  BITUMEN("bitumen", "Bitumen", new String[] {"Ore", "Block", "Gem", "Chunk"}, BlockBase::new, OreBitumen::new, () -> new BurnableItemBase(1600), Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("BITUMEN")).get(), 2, 6), 18, 2, 100, 60, 0, 0, 0, 0, 0, 0, 0, 0),
  CINNABAR("cinnabar", "Cinnabar", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust"}, BlockBase::new, OreCinnabar::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("CINNABAR")).get(), 1, 2), 5, 4, 10, 6, 0, 0, 0, 0, 0, 0, 0, 0),
  APATITE("apatite", "Apatite", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust"}, BlockBase::new, OreApatite::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("APATITE")).get(), 4, 9), 22, 2, 96, 32, 0, 0, 0, 0, 0, 0, 0, 0),
  SULFUR("sulfur", "Sulfur", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust"}, BlockBase::new, OreSulfur::new, () -> new BurnableItemBase(1200), Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("SULFUR")).get(), 3, 5), 7, 3, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0),
  POTASSIUM_NITRATE("potassium_nitrate", "Potassium Nitrate", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust"}, BlockBase::new, OrePotassiumNitrate::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("POTASSIUM_NITRATE")).get(), 3, 5), 5, 4, 25, 7, 0, 0, 0, 0, 0, 0, 0, 0),
  ARCANE("arcane", "Arcane", new String[] {"Ore", "Block", "Gem", "Chunk"}, BlockBase::new, OreArcane::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("ARCANE")).get()), 6, 5, 33, 13, 0, 0, 0, 0, 0, 0, 0, 0),
  DIMENSIONAL("dimensional", "Dimensional Shard", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust"}, BlockBase::new, OreDimensional::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("DIMENSIONAL")).get(), 4 , 5), 8, 8, 21, 19, 0, 0, 0, 0, 0, 0, 0, 0),
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

  Materials(String id, String localisedName, String[] type, Supplier<Block> block, @Nullable Supplier<OreBlock> oreBlock, Supplier<Item> item, Source source, @Nullable OreDropInfo nonSilkDrop, int size, int count, int baseline, int spread, int netherBase, int netherSpread, int netherCount, int netherSize, int endBase, int endSpread, int endCount, int endSize) {
    this.id = id;
    this.localisedName = localisedName;
    this.block = block;
    this.oreBlock = oreBlock;
    this.item = item;
    this.source = source;

    this.drop = nonSilkDrop;

    this.defaultSize = size;
    this.defaultCount = count;
    this.defaultBaseline = baseline;
    this.defaultSpread = spread;
    this.netherBase = netherBase;
    this.netherSpread = netherSpread;
    this.netherCount = netherCount;
    this.netherSize = netherSize;
    this.endBase = endBase;
    this.endSpread = endSpread;
    this.endCount = endCount;
    this.endSize = endSize;

    this.type = type;
  }

  Materials(String id, String localisedName, String[] type, Supplier<Block> block, Supplier<OreBlock> oreBlock, Supplier<Item> item, Source source, int size, int count, int baseline, int spread, int netherBase, int netherSpread, int netherCount, int netherSize, int endBase, int endSpread, int endCount, int endSize) {
    this(id, localisedName, type, block, oreBlock, item, source, null, size, count, baseline, spread, netherBase, netherSpread, netherCount, netherSize, endBase, endSpread, endCount, endSize);
  }

  Materials(String id, String localisedName, String[] type, Supplier<Block> block, Supplier<Item> item, Source source) {
    this(id, localisedName, type, block, null, item, source, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
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