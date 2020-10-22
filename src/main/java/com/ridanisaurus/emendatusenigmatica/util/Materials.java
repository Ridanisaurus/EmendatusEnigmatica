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

  COAL("coal", "Coal", new String[] {"Ore", "Chunk", "Dust"}, BlockBase::new, OreCoal::new, ItemBase::new, Source.VANILLA, drop(() -> Items.COAL),17, 20, 0, 128),
  IRON("iron", "Iron", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear"}, BlockBase::new, OreIron::new, ItemBase::new, Source.VANILLA,  9,20, 0, 64),
  GOLD("gold", "Gold", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear"}, BlockBase::new, OreGold::new, ItemBase::new, Source.VANILLA, 9, 2, 0, 32),
  DIAMOND("diamond", "Diamond", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear"}, BlockBase::new, OreDiamond::new, ItemBase::new, Source.VANILLA, drop(() -> Items.DIAMOND), 8, 1, 0, 16),
  EMERALD("emerald", "Emerald", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear"}, BlockBase::new, OreEmerald::new, ItemBase::new, Source.VANILLA, drop(() -> Items.EMERALD), 4, 1, 64, 128),
  LAPIS("lapis", "Lapis Lazuli", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear"}, BlockBase::new, OreLapis::new, ItemBase::new, Source.VANILLA, drop(() -> Items.LAPIS_LAZULI, 4, 9), 7, 2, 0, 16),
  REDSTONE("redstone", "Redstone", new String[] {"Ore", "Chunk"}, BlockBase::new, OreRedstone::new, ItemBase::new, Source.VANILLA, drop(() -> Items.REDSTONE, 4, 5), 8, 8, 0, 16),
  QUARTZ("quartz", "Quartz", new String[] {"Dust", "Gear"}, BlockBase::new, ItemBase::new, Source.VANILLA),

  COPPER("copper", "Copper", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear"}, BlockBase::new, OreCopper::new, ItemBase::new, Source.MODDED, 7, 20, 45, 60),
  ALUMINUM("aluminum", "Aluminum", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear"}, BlockBase::new, OreAluminum::new, ItemBase::new, Source.MODDED, 3, 8, 50, 70),
  SILVER("silver", "Silver", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear"}, BlockBase::new, OreSilver::new, ItemBase::new, Source.MODDED, 4, 8, 30, 38),
  LEAD("lead", "Lead", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear"}, BlockBase::new, OreLead::new, ItemBase::new, Source.MODDED, 3, 8, 32, 40),
  NICKEL("nickel", "Nickel", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear"}, BlockBase::new, OreNickel::new, ItemBase::new, Source.MODDED, 3, 8, 25, 40),
  URANIUM("uranium", "Uranium", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear"}, BlockBase::new, OreUranium::new, ItemBase::new, Source.MODDED, 3, 4, 5, 20),
  OSMIUM("osmium", "Osmium", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear"}, BlockBase::new, OreOsmium::new, ItemBase::new, Source.MODDED, 6, 20, 20, 45),
  TIN("tin", "Tin", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear"}, BlockBase::new, OreTin::new, ItemBase::new, Source.MODDED, 6, 20, 40, 55),
  ZINC("zinc", "Zinc", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear"}, BlockBase::new, OreZinc::new, ItemBase::new, Source.MODDED, 4, 8, 35, 50),

  CERTUS_QUARTZ("certus_quartz", "Certus Quartz", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust", "4xRecipe"}, BlockBase::new, OreCertusQuartz::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.itemTable.get().get(ProcessedMaterials.GEM, Materials.valueOf("CERTUS_QUARTZ")).get()), 8, 16, 0, 64),
  CHARGED_CERTUS_QUARTZ("charged_certus_quartz", "Charged Certus Quartz", new String[] {"Ore", "Block", "Gem", "Chunk", "4xRecipe"}, BlockBase::new, OreChargedCertusQuartz::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.itemTable.get().get(ProcessedMaterials.GEM, Materials.valueOf("CHARGED_CERTUS_QUARTZ")).get()), 4, 8, 0, 32),
  FLUIX("fluix", "Fluix Crystal", new String[] {"Block", "Gem", "Dust", "4xRecipe"}, BlockBase::new, ItemBase::new, Source.MODDED),
  FLUORITE("fluorite", "Fluorite", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust"}, BlockBase::new, OreFluorite::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.itemTable.get().get(ProcessedMaterials.GEM, Materials.valueOf("FLUORITE")).get(), 2, 4), 12, 6, 0, 32),
  BITUMEN("bitumen", "Bitumen", new String[] {"Ore", "Block", "Gem", "Chunk"}, BlockBase::new, OreBitumen::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.itemTable.get().get(ProcessedMaterials.GEM, Materials.valueOf("BITUMEN")).get(), 2, 6), 18, 25, 40, 160),
  CINNABAR("cinnabar", "Cinnabar", new String[] {"Ore", "Block", "Gem", "Chunk"}, BlockBase::new, OreCinnabar::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.itemTable.get().get(ProcessedMaterials.GEM, Materials.valueOf("CINNABAR")).get(), 1, 2), 5, 4, 0, 16),
  APATITE("apatite", "Apatite", new String[] {"Ore", "Block", "Gem", "Chunk"}, BlockBase::new, OreApatite::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.itemTable.get().get(ProcessedMaterials.GEM, Materials.valueOf("APATITE")).get(), 4, 9), 13, 2, 0, 16),
  SULFUR("sulfur", "Sulfur", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust"}, BlockBase::new, OreSulfur::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.itemTable.get().get(ProcessedMaterials.GEM, Materials.valueOf("SULFUR")).get(), 3, 5), 7, 3, 0, 40),
  POTASSIUM_NITRATE("potassium_nitrate", "Potassium Nitrate", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust"}, BlockBase::new, OrePotassiumNitrate::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.itemTable.get().get(ProcessedMaterials.GEM, Materials.valueOf("POTASSIUM_NITRATE")).get(), 3, 5), 7, 3, 0, 24),

  BRONZE("bronze", "Bronze", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear"}, BlockBase::new, ItemBase::new, Source.ALLOY),
  BRASS("brass", "Brass", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear"}, BlockBase::new, ItemBase::new, Source.ALLOY),
  CONSTANTAN("constantan", "Constantan", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear"}, BlockBase::new, ItemBase::new, Source.ALLOY),
  ELECTRUM("electrum", "Electrum", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear"}, BlockBase::new, ItemBase::new, Source.ALLOY),
  STEEL("steel", "Steel", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear"}, BlockBase::new, ItemBase::new, Source.ALLOY),
  INVAR("invar", "Invar", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear"}, BlockBase::new, ItemBase::new, Source.ALLOY),
  SIGNALUM("signalum", "Signalum", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear"}, BlockBase::new, ItemBase::new, Source.ALLOY),
  LUMIUM("lumium", "Lumium", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear"}, BlockBase::new, ItemBase::new, Source.ALLOY),
  ENDERIUM("enderium", "Enderium", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear"}, BlockBase::new, ItemBase::new, Source.ALLOY);

  public final String id;
  public final String localisedName;
  public final Supplier<Block> block;
  public final Supplier<OreBlock> oreBlock;
  public final Supplier<Item> item;
  private final Source source;

  public final OreDropInfo drop;

  public final int defaultSize;
  public final int defaultCount;
  public final int defaultMinY;
  public final int defaultMaxY;

  public final String[] type;

  Materials(String id, String localisedName, String[] type, Supplier<Block> block, @Nullable Supplier<OreBlock> oreBlock, Supplier<Item> item, Source source, @Nullable OreDropInfo nonSilkDrop, int s, int c, int minY, int maxY) {
    this.id = id;
    this.localisedName = localisedName;
    this.block = block;
    this.oreBlock = oreBlock;
    this.item = item;
    this.source = source;

    this.drop = nonSilkDrop;

    this.defaultSize = s;
    this.defaultCount = c;
    this.defaultMinY = minY;
    this.defaultMaxY = maxY;

    this.type = type;
  }

  Materials(String id, String localisedName, String[] type, Supplier<Block> block, Supplier<OreBlock> oreBlock, Supplier<Item> item, Source source, int s, int c, int minY, int maxY) {
    this(id, localisedName, type, block, oreBlock, item, source, null, s, c, minY, maxY);
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