package com.ridanisaurus.emendatusenigmatica.util;

import com.ridanisaurus.emendatusenigmatica.blocks.BlockBase;
import com.ridanisaurus.emendatusenigmatica.blocks.OreAluminum;
import com.ridanisaurus.emendatusenigmatica.blocks.OreApatite;
import com.ridanisaurus.emendatusenigmatica.blocks.OreArcane;
import com.ridanisaurus.emendatusenigmatica.blocks.OreBitumen;
import com.ridanisaurus.emendatusenigmatica.blocks.OreCertusQuartz;
import com.ridanisaurus.emendatusenigmatica.blocks.OreChargedCertusQuartz;
import com.ridanisaurus.emendatusenigmatica.blocks.OreCinnabar;
import com.ridanisaurus.emendatusenigmatica.blocks.OreCoal;
import com.ridanisaurus.emendatusenigmatica.blocks.OreCobalt;
import com.ridanisaurus.emendatusenigmatica.blocks.OreCopper;
import com.ridanisaurus.emendatusenigmatica.blocks.OreDiamond;
import com.ridanisaurus.emendatusenigmatica.blocks.OreDimensional;
import com.ridanisaurus.emendatusenigmatica.blocks.OreEmerald;
import com.ridanisaurus.emendatusenigmatica.blocks.OreFluorite;
import com.ridanisaurus.emendatusenigmatica.blocks.OreGold;
import com.ridanisaurus.emendatusenigmatica.blocks.OreIridium;
import com.ridanisaurus.emendatusenigmatica.blocks.OreIron;
import com.ridanisaurus.emendatusenigmatica.blocks.OreLapis;
import com.ridanisaurus.emendatusenigmatica.blocks.OreLead;
import com.ridanisaurus.emendatusenigmatica.blocks.OreNetherQuartz;
import com.ridanisaurus.emendatusenigmatica.blocks.OreNickel;
import com.ridanisaurus.emendatusenigmatica.blocks.OreOsmium;
import com.ridanisaurus.emendatusenigmatica.blocks.OrePeridot;
import com.ridanisaurus.emendatusenigmatica.blocks.OrePotassiumNitrate;
import com.ridanisaurus.emendatusenigmatica.blocks.OreRedstone;
import com.ridanisaurus.emendatusenigmatica.blocks.OreRuby;
import com.ridanisaurus.emendatusenigmatica.blocks.OreSapphire;
import com.ridanisaurus.emendatusenigmatica.blocks.OreSilver;
import com.ridanisaurus.emendatusenigmatica.blocks.OreSulfur;
import com.ridanisaurus.emendatusenigmatica.blocks.OreTin;
import com.ridanisaurus.emendatusenigmatica.blocks.OreUranium;
import com.ridanisaurus.emendatusenigmatica.blocks.OreZinc;
import com.ridanisaurus.emendatusenigmatica.items.BurnableItemBase;
import com.ridanisaurus.emendatusenigmatica.items.ItemBase;
import com.ridanisaurus.emendatusenigmatica.registries.ItemHandler;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.OreBlock;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public enum Materials {

  COAL("coal", "Coal", new String[] {"Ore", "Chunk", "Dust", "Cluster", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreCoal::new, ItemBase::new, Source.VANILLA, drop(() -> Items.COAL), 0xFF252525, 60, 60, 15, 17),
  IRON("iron", "Iron", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster", "Fluid"}, BlockBase::new, OreIron::new, ItemBase::new, Source.VANILLA, 0xFFa8a8a8, 32, 32, 17, 11),
  GOLD("gold", "Gold", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster", "Fluid"}, BlockBase::new, OreGold::new, ItemBase::new, Source.VANILLA, 0xFFfad64a, 16, 16, 7, 8),
  DIAMOND("diamond", "Diamond", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreDiamond::new, ItemBase::new, Source.VANILLA, drop(() -> Items.DIAMOND), 0xFF4aedd9, 8, 8, 3, 8),
  EMERALD("emerald", "Emerald", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreEmerald::new, ItemBase::new, Source.VANILLA, drop(() -> Items.EMERALD), 0xFF00aa2c, 156, 56, 3, 4),
  LAPIS("lapis", "Lapis Lazuli", new String[] {"Ore", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreLapis::new, ItemBase::new, Source.VANILLA, drop(() -> Items.LAPIS_LAZULI, 4, 9), 0xFF275fb5, 8, 8, 4, 7),
  REDSTONE("redstone", "Redstone", new String[] {"Ore", "Chunk", "Cluster", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreRedstone::new, ItemBase::new, Source.VANILLA, drop(() -> Items.REDSTONE, 4, 5), 0xFFaa0f01, 8, 8, 8, 11),
  QUARTZ("quartz", "Nether Quartz", new String[] {"Ore", "Chunk", "Dust", "Gear", "Cluster", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreNetherQuartz::new, ItemBase::new, Source.VANILLA, drop(() -> Items.QUARTZ), 0xFFd4caba, 60, 50, 16, 14),
  ANCIENT_DEBRIS("ancient_debris", "Ancient Debris", new String[] {"Crushed", "Fluid"}, BlockBase::new, ItemBase::new, Source.VANILLA, 0xFF3c3232),

  COPPER("copper", "Copper", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreCopper::new, ItemBase::new, Source.MODDED, 0xFFcc6d51, 52, 8, 17, 7),
  ALUMINUM("aluminum", "Aluminum", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreAluminum::new, ItemBase::new, Source.MODDED, 0xFFdfedf2, 60, 10, 8, 5),
  SILVER("silver", "Silver", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreSilver::new, ItemBase::new, Source.MODDED, 0xFFc5dffe, 34, 4, 8, 5),
  LEAD("lead", "Lead", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreLead::new, ItemBase::new, Source.MODDED, 0xFF414a6a, 36, 4, 8, 5),
  NICKEL("nickel", "Nickel", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreNickel::new, ItemBase::new, Source.MODDED, 0xFFa0a883, 32, 8, 8, 4),
  URANIUM("uranium", "Uranium", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreUranium::new, ItemBase::new, Source.MODDED, 0xFF799c46, 12, 8, 6, 6),
  OSMIUM("osmium", "Osmium", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreOsmium::new, ItemBase::new, Source.MODDED, 0xFF95c9c9, 32, 12, 15, 6),
  TIN("tin", "Tin", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreTin::new, ItemBase::new, Source.MODDED, 0xFFbccbe5, 47, 7, 15, 7),
  ZINC("zinc", "Zinc", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreZinc::new, ItemBase::new, Source.MODDED, 0xFFd1d1a5, 42, 8, 9, 5),
  COBALT("cobalt", "Cobalt", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreCobalt::new, ItemBase::new, Source.MODDED, 0xFF1589b3, 32, 16, 4, 8),
  IRIDIUM("iridium", "Iridium", new String[] {"Ore", "Block", "Ingot", "Nugget", "Chunk", "Dust", "Plate", "Gear", "Rod", "Cluster", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreIridium::new, ItemBase::new, Source.MODDED, 0xFFc0f3f6, 12, 8, 6, 6),

  CERTUS_QUARTZ("certus_quartz", "Certus Quartz", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust", "4xRecipe", "Cluster", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreCertusQuartz::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("CERTUS_QUARTZ")).get()), 0xFF85b4e5, 32, 32, 16, 8),
  CHARGED_CERTUS_QUARTZ("charged_certus_quartz", "Charged Certus Quartz", new String[] {"Ore", "Block", "Gem", "Chunk", "4xRecipe", "Cluster", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreChargedCertusQuartz::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("CHARGED_CERTUS_QUARTZ")).get()), 0xFFace9ff, 16, 16, 8, 4),
  FLUIX("fluix", "Fluix", new String[] {"Block", "Gem", "Dust", "4xRecipe", "Fluid"}, BlockBase::new, ItemBase::new, Source.MODDED, 0xFF6e5cb8),
  FLUORITE("fluorite", "Fluorite", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust", "Cluster", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreFluorite::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("FLUORITE")).get(), 2, 4), 0xFF8899ba, 16, 16, 5, 9),
  BITUMEN("bitumen", "Bitumen", new String[] {"Ore", "Block", "Gem", "Chunk", "Cluster", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreBitumen::new, () -> new BurnableItemBase(1600), Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("BITUMEN")).get(), 2, 6), 0xFF35424f, 100, 60, 2, 18),
  CINNABAR("cinnabar", "Cinnabar", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust", "Cluster", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreCinnabar::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("CINNABAR")).get(), 1, 2), 0xFF4f002c, 10, 6, 4, 5),
  APATITE("apatite", "Apatite", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust", "Cluster", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreApatite::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("APATITE")).get(), 4, 9), 0xFF27abbd, 96, 32, 2, 22),
  SULFUR("sulfur", "Sulfur", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust", "Cluster", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreSulfur::new, () -> new BurnableItemBase(1200), Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("SULFUR")).get(), 3, 5), 0xFFded531, 8, 8, 3, 7),
  POTASSIUM_NITRATE("potassium_nitrate", "Potassium Nitrate", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust", "Cluster", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OrePotassiumNitrate::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("POTASSIUM_NITRATE")).get(), 3, 5), 0xFFe0dde4, 25, 7, 4, 5),
  ARCANE("arcane", "Arcane", new String[] {"Ore", "Block", "Gem", "Chunk", "Cluster", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreArcane::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("ARCANE")).get()), 0xFFdd7ee0, 33, 13, 5, 6),
  DIMENSIONAL("dimensional", "Dimensional Shard", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust", "Cluster", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreDimensional::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("DIMENSIONAL")).get(), 4 , 5), 0xFF8dd4d3, 10, 10, 2, 2),
  RUBY("ruby", "Ruby", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust", "Cluster", "Gear", "Plate", "Rod",  "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreRuby::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("RUBY")).get()), 0xFFa21926, 20, 1, 8, 12),
  SAPPHIRE("sapphire", "Sapphire", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust", "Cluster", "Gear", "Plate", "Rod",  "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OreSapphire::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("SAPPHIRE")).get()), 0xFF2e29ba, 20, 1, 8, 12),
  PERIDOT("peridot", "Peridot", new String[] {"Ore", "Block", "Gem", "Chunk", "Dust", "Cluster", "Gear", "Plate", "Rod", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, OrePeridot::new, ItemBase::new, Source.MODDED, drop(() -> ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.valueOf("PERIDOT")).get()), 0xFF94ac35, 20, 1, 8, 12),
  COKE("coke", "Coal Coke", new String[] {"Block", "Gem", "Dust"}, BlockBase::new, () -> new BurnableItemBase(3200), Source.MODDED, 0),
  SILICON("silicon", "Silicon", new String[] {"Gem"}, BlockBase::new, ItemBase::new, Source.MODDED, 0),

  BRONZE("bronze", "Bronze", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear", "Rod", "Crushed", "Fluid"}, BlockBase::new, ItemBase::new, Source.ALLOY, 0xFFd38c53),
  BRASS("brass", "Brass", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear", "Rod", "Fluid"}, BlockBase::new, ItemBase::new, Source.ALLOY, 0xFFc7d477),
  CONSTANTAN("constantan", "Constantan", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear", "Rod", "Crushed", "Fluid"}, BlockBase::new, ItemBase::new, Source.ALLOY, 0xFFdbab81),
  ELECTRUM("electrum", "Electrum", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear", "Rod", "Crushed", "Fluid"}, BlockBase::new, ItemBase::new, Source.ALLOY, 0xFFedda6f),
  STEEL("steel", "Steel", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear", "Rod", "Crushed", "Fluid"}, BlockBase::new, ItemBase::new, Source.ALLOY, 0xFF818185),
  INVAR("invar", "Invar", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear", "Rod", "Crushed", "Fluid"}, BlockBase::new, ItemBase::new, Source.ALLOY, 0xFF9aaaa2),
  SIGNALUM("signalum", "Signalum", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear", "Rod", "Crushed", "Fluid"}, BlockBase::new, ItemBase::new, Source.ALLOY, 0xFFfc8638),
  LUMIUM("lumium", "Lumium", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear", "Rod", "Crushed", "Fluid"}, BlockBase::new, ItemBase::new, Source.ALLOY, 0xFFe5f3b5),
  ENDERIUM("enderium", "Enderium", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear", "Rod", "Crushed", "Fluid"}, BlockBase::new, ItemBase::new, Source.ALLOY, 0xFF1d7e80),
  CAST_IRON("cast_iron", "Cast Iron", new String[] {"Block", "Ingot", "Nugget", "Dust", "Plate", "Gear", "Rod", "Fluid"}, BlockBase::new, ItemBase::new, Source.ALLOY, 0xFF555b6b),

  THALLASIUM("thallasium", "Thallasium", new String[] {"Dust", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, ItemBase::new, Source.MODDED, 0xFF367e97),
  IESNIUM("iesnium", "Iesnium", new String[] {"CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, ItemBase::new, Source.MODDED, 0xFF55a5b7),
  REGALIUM("regalium", "Regalium", new String[] {"Dust", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, ItemBase::new, Source.MODDED, 0xFFa86e42),
  UTHERIUM("utherium", "Utherium", new String[] {"Dust", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, ItemBase::new, Source.MODDED, 0xFFc1424b),
  FROSTSTEEL("froststeel", "Froststeel", new String[] {"Dust", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, ItemBase::new, Source.MODDED, 0xFF6886ae),
  CLOGGRUM("cloggrum", "Cloggrum", new String[] {"Dust", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, ItemBase::new, Source.MODDED, 0xFF796a5d),
  NEBU("nebu", "Nebu", new String[] {"Dust", "CleanSlurry", "DirtySlurry", "Clump", "Crystal", "DirtyDust", "Shard", "Crushed", "Fragment", "Gravel", "Fluid"}, BlockBase::new, ItemBase::new, Source.MODDED, 0xFFd19607);

  public final String id;
  public final String localisedName;
  public final Supplier<Block> block;
  public final Supplier<OreBlock> oreBlock;
  public final Supplier<Item> item;
  private final Source source;

  public final OreDropInfo drop;

  public final int tintColour;

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

  Materials(String id, String localisedName, String[] type, Supplier<Block> block, @Nullable Supplier<OreBlock> oreBlock, Supplier<Item> item, Source source, @Nullable OreDropInfo nonSilkDrop, int tintColour, int baseline, int spread, int count, int size) {
    this.id = id;
    this.localisedName = localisedName;
    this.block = block;
    this.oreBlock = oreBlock;
    this.item = item;
    this.source = source;

    this.drop = nonSilkDrop;

    this.tintColour = tintColour;

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

  Materials(String id, String localisedName, String[] type, Supplier<Block> block, Supplier<OreBlock> oreBlock, Supplier<Item> item, Source source, int tintColour, int baseline, int spread, int count, int size) {
    this.id = id;
    this.localisedName = localisedName;
    this.block = block;
    this.oreBlock = oreBlock;
    this.item = item;
    this.source = source;

    this.drop = null;

    this.tintColour = tintColour;

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

  Materials(String id, String localisedName, String[] type, Supplier<Block> block, Supplier<Item> item, Source source, int tintColour) {
    this(id, localisedName, type, block, null, item, source, null, tintColour, 0, 0, 0, 0);
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