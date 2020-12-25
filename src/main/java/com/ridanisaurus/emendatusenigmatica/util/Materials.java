package com.ridanisaurus.emendatusenigmatica.util;

import com.ridanisaurus.emendatusenigmatica.blocks.*;
import com.ridanisaurus.emendatusenigmatica.items.ItemBase;
import com.ridanisaurus.emendatusenigmatica.registries.ItemHandler;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public enum Materials {

<<<<<<< Updated upstream
  COAL("coal", "Coal", new String[] {"Ore", "Chunk", "Dust"}, BlockBase::new, OreCoal::new, ItemBase::new, Source.VANILLA, drop(() -> Items.COAL),17, 20, 60, 60),
  IRON("iron", "Iron", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreIron::new, ItemBase::new, Source.VANILLA,  11,20, 32, 32),
  GOLD("gold", "Gold", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreGold::new, ItemBase::new, Source.VANILLA, 8, 7, 16, 16),
  DIAMOND("diamond", "Diamond", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreDiamond::new, ItemBase::new, Source.VANILLA, drop(() -> Items.DIAMOND), 8, 3, 8, 8),
  EMERALD("emerald", "Emerald", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreEmerald::new, ItemBase::new, Source.VANILLA, drop(() -> Items.EMERALD), 6, 1, 96, 32),
  LAPIS("lapis", "Lapis Lazuli", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreLapis::new, ItemBase::new, Source.VANILLA, drop(() -> Items.LAPIS_LAZULI, 4, 9), 7, 4, 8, 8),
  REDSTONE("redstone", "Redstone", new String[] {"Ore", "Chunk"}, BlockBase::new, OreRedstone::new, ItemBase::new, Source.VANILLA, drop(() -> Items.REDSTONE, 4, 5), 14, 9, 8, 8),
  QUARTZ("quartz", "Quartz", new String[] {"Dust", "Gear"}, BlockBase::new, ItemBase::new, Source.VANILLA),

  COPPER("copper", "Copper", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreCopper::new, ItemBase::new, Source.MODDED, 7, 20, 52, 8),
  ALUMINUM("aluminum", "Aluminum", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreAluminum::new, ItemBase::new, Source.MODDED, 5, 8, 60, 10),
  SILVER("silver", "Silver", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreSilver::new, ItemBase::new, Source.MODDED, 5, 8, 34, 4),
  LEAD("lead", "Lead", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreLead::new, ItemBase::new, Source.MODDED, 5, 8, 36, 4),
  NICKEL("nickel", "Nickel", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreNickel::new, ItemBase::new, Source.MODDED, 4, 8, 32, 8),
  URANIUM("uranium", "Uranium", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreUranium::new, ItemBase::new, Source.MODDED, 6, 6, 12, 8),
  OSMIUM("osmium", "Osmium", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreOsmium::new, ItemBase::new, Source.MODDED, 6, 17, 32, 12),
  TIN("tin", "Tin", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreTin::new, ItemBase::new, Source.MODDED, 7, 20, 47, 7),
  ZINC("zinc", "Zinc", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreZinc::new, ItemBase::new, Source.MODDED, 5, 9, 42, 8),

  CERTUS_QUARTZ("certus_quartz", "Certus Quartz", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust", "4xRecipe"}, BlockBase::new, OreCertusQuartz::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("CERTUS_QUARTZ")).get()), 8, 16, 32, 32),
  CHARGED_CERTUS_QUARTZ("charged_certus_quartz", "Charged Certus Quartz", new String[] {"Ore", "Block", "Gem", "Chunk", "4xRecipe"}, BlockBase::new, OreChargedCertusQuartz::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("CHARGED_CERTUS_QUARTZ")).get()), 4, 8, 16, 16),
  FLUIX("fluix", "Fluix Crystal", new String[] {"Block", "Gem", "Dust", "4xRecipe"}, BlockBase::new, ItemBase::new, Source.MODDED),
  FLUORITE("fluorite", "Fluorite", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust"}, BlockBase::new, OreFluorite::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("FLUORITE")).get(), 2, 4), 12, 6, 16, 16),
  BITUMEN("bitumen", "Bitumen", new String[] {"Ore", "Block", "Gem", "Chunk"}, BlockBase::new, OreBitumen::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("BITUMEN")).get(), 2, 6), 18, 2, 100, 60),
  CINNABAR("cinnabar", "Cinnabar", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust"}, BlockBase::new, OreCinnabar::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("CINNABAR")).get(), 1, 2), 5, 4, 10, 6),
  APATITE("apatite", "Apatite", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust"}, BlockBase::new, OreApatite::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("APATITE")).get(), 4, 9), 22, 2, 96, 32),
  SULFUR("sulfur", "Sulfur", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust"}, BlockBase::new, OreSulfur::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("SULFUR")).get(), 3, 5), 7, 3, 8, 8),
  POTASSIUM_NITRATE("potassium_nitrate", "Potassium Nitrate", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust"}, BlockBase::new, OrePotassiumNitrate::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("POTASSIUM_NITRATE")).get(), 3, 5), 5, 4, 25, 7),
  ARCANE("arcane", "Arcane", new String[] {"Ore", "Block", "Gem", "Chunk"}, BlockBase::new, OreArcane::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("ARCANE")).get()), 6, 5, 33, 13),
=======
  COAL("coal", "Coal", new String[] {"Ore", "Chunk", "Dust"}, BlockBase::new, OreCoal::new, ItemBase::new, Source.VANILLA, drop(() -> Items.COAL),17, 20, 60, 60, 30, 10),
  IRON("iron", "Iron", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreIron::new, ItemBase::new, Source.VANILLA,  11,20, 32, 32, 30, 10),
  GOLD("gold", "Gold", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreGold::new, ItemBase::new, Source.VANILLA, 8, 7, 16, 16, 30, 10),
  DIAMOND("diamond", "Diamond", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreDiamond::new, ItemBase::new, Source.VANILLA, drop(() -> Items.DIAMOND), 8, 3, 8, 8, 30, 10),
  EMERALD("emerald", "Emerald", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreEmerald::new, ItemBase::new, Source.VANILLA, drop(() -> Items.EMERALD), 6, 1, 96, 32, 30, 10),
  LAPIS("lapis", "Lapis Lazuli", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreLapis::new, ItemBase::new, Source.VANILLA, drop(() -> Items.LAPIS_LAZULI, 4, 9), 7, 4, 8, 8, 30, 10),
  REDSTONE("redstone", "Redstone", new String[] {"Ore", "Chunk"}, BlockBase::new, OreRedstone::new, ItemBase::new, Source.VANILLA, drop(() -> Items.REDSTONE, 4, 5), 14, 9, 8, 8, 30, 10),
  QUARTZ("quartz", "Quartz", new String[] {"Dust", "Gear"}, BlockBase::new, ItemBase::new, Source.VANILLA),

  COPPER("copper", "Copper", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreCopper::new, ItemBase::new, Source.MODDED, 7, 20, 52, 8, 30, 10),
  ALUMINUM("aluminum", "Aluminum", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreAluminum::new, ItemBase::new, Source.MODDED, 5, 8, 60, 10, 30, 10),
  SILVER("silver", "Silver", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreSilver::new, ItemBase::new, Source.MODDED, 5, 8, 34, 4, 30, 10),
  LEAD("lead", "Lead", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreLead::new, ItemBase::new, Source.MODDED, 5, 8, 36, 4, 30, 10),
  NICKEL("nickel", "Nickel", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreNickel::new, ItemBase::new, Source.MODDED, 4, 8, 32, 8, 30, 10),
  URANIUM("uranium", "Uranium", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreUranium::new, ItemBase::new, Source.MODDED, 6, 6, 12, 8, 30, 10),
  OSMIUM("osmium", "Osmium", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreOsmium::new, ItemBase::new, Source.MODDED, 6, 17, 32, 12, 30, 10),
  TIN("tin", "Tin", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreTin::new, ItemBase::new, Source.MODDED, 7, 20, 47, 7, 30, 10),
  ZINC("zinc", "Zinc", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod"}, BlockBase::new, OreZinc::new, ItemBase::new, Source.MODDED, 5, 9, 42, 8, 30, 10),

  CERTUS_QUARTZ("certus_quartz", "Certus Quartz", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust", "4xRecipe"}, BlockBase::new, OreCertusQuartz::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("CERTUS_QUARTZ")).get()), 8, 16, 32, 32, 30, 10),
  CHARGED_CERTUS_QUARTZ("charged_certus_quartz", "Charged Certus Quartz", new String[] {"Ore", "Block", "Gem", "Chunk", "4xRecipe"}, BlockBase::new, OreChargedCertusQuartz::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("CHARGED_CERTUS_QUARTZ")).get()), 4, 8, 16, 16, 30, 10),
  FLUIX("fluix", "Fluix Crystal", new String[] {"Block", "Gem", "Dust", "4xRecipe"}, BlockBase::new, ItemBase::new, Source.MODDED),
  FLUORITE("fluorite", "Fluorite", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust"}, BlockBase::new, OreFluorite::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("FLUORITE")).get(), 2, 4), 12, 6, 16, 16, 30, 10),
  BITUMEN("bitumen", "Bitumen", new String[] {"Ore", "Block", "Gem", "Chunk"}, BlockBase::new, OreBitumen::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("BITUMEN")).get(), 2, 6), 18, 2, 100, 60, 30, 10),
  CINNABAR("cinnabar", "Cinnabar", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust"}, BlockBase::new, OreCinnabar::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("CINNABAR")).get(), 1, 2), 5, 4, 10, 6, 30, 10),
  APATITE("apatite", "Apatite", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust"}, BlockBase::new, OreApatite::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("APATITE")).get(), 4, 9), 22, 2, 96, 32, 30, 10),
  SULFUR("sulfur", "Sulfur", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust"}, BlockBase::new, OreSulfur::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("SULFUR")).get(), 3, 5), 7, 3, 8, 8, 30, 10),
  POTASSIUM_NITRATE("potassium_nitrate", "Potassium Nitrate", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust"}, BlockBase::new, OrePotassiumNitrate::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("POTASSIUM_NITRATE")).get(), 3, 5), 5, 4, 25, 7, 30, 10),
  ARCANE("arcane", "Arcane", new String[] {"Ore", "Block", "Gem", "Chunk"}, BlockBase::new, OreArcane::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("ARCANE")).get()), 6, 5, 33, 13, 30, 10),
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream

  public final String[] type;

  Materials(String id, String localisedName, String[] type, Supplier<Block> block, @Nullable Supplier<OreBlock> oreBlock, Supplier<Item> item, Source source, @Nullable OreDropInfo nonSilkDrop, int s, int c, int baseline, int spread) {
=======

  public final int netherModifier;
  public final int endModifier;

  public final String[] type;

  Materials(String id, String localisedName, String[] type, Supplier<Block> block, @Nullable Supplier<OreBlock> oreBlock, Supplier<Item> item, Source source, @Nullable OreDropInfo nonSilkDrop, int s, int c, int baseline, int spread, int netherMod, int endMod) {
>>>>>>> Stashed changes
    this.id = id;
    this.localisedName = localisedName;
    this.block = block;
    this.oreBlock = oreBlock;
    this.item = item;
    this.source = source;

    this.drop = nonSilkDrop;

    this.defaultSize = s;
    this.defaultCount = c;
    this.defaultBaseline = baseline;
    this.defaultSpread = spread;
<<<<<<< Updated upstream
=======

    this.netherModifier = netherMod;
    this.endModifier = endMod;
>>>>>>> Stashed changes

    this.type = type;
  }

<<<<<<< Updated upstream
  Materials(String id, String localisedName, String[] type, Supplier<Block> block, Supplier<OreBlock> oreBlock, Supplier<Item> item, Source source, int s, int c, int baseline, int spread) {
    this(id, localisedName, type, block, oreBlock, item, source, null, s, c, baseline, spread);
=======
  Materials(String id, String localisedName, String[] type, Supplier<Block> block, Supplier<OreBlock> oreBlock, Supplier<Item> item, Source source, int s, int c, int baseline, int spread, int netherMod, int endMod) {
    this(id, localisedName, type, block, oreBlock, item, source, null, s, c, baseline, spread, netherMod, endMod);
>>>>>>> Stashed changes
  }

  Materials(String id, String localisedName, String[] type, Supplier<Block> block, Supplier<Item> item, Source source) {
    this(id, localisedName, type, block, null, item, source, null, 0, 0, 0, 0, 0,0);
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