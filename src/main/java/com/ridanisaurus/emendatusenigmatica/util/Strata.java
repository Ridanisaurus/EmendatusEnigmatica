package com.ridanisaurus.emendatusenigmatica.util;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public enum Strata {
	STONE("minecraft_stone", "minecraft:block/stone", "stone", Blocks.STONE, "Stone", "minecraft", "overworld", true),
	ANDESITE("minecraft_andesite", "minecraft:block/andesite", "andesite", Blocks.ANDESITE, "Andesite", "minecraft", "overworld", false),
	GRANITE("minecraft_granite", "minecraft:block/granite", "granite", Blocks.GRANITE, "Granite", "minecraft", "overworld", false),
	DIORITE("minecraft_diorite", "minecraft:block/diorite", "diorite", Blocks.DIORITE, "Diorite", "minecraft", "overworld", false),
	SAND("minecraft_sand", "minecraft:block/sand", "sand", Blocks.SAND, "Sand", "minecraft", "overworld", false),
	GRAVEL("minecraft_gravel", "minecraft:block/gravel", "gravel", Blocks.GRAVEL, "Gravel", "minecraft", "overworld", false),
	NETHERRACK("minecraft_netherrack", "minecraft:block/netherrack", "netherrack", Blocks.NETHERRACK, "Netherrack", "minecraft", "nether", false),
	BLACKSTONE("minecraft_blackstone", "minecraft:block/blackstone", "blackstone", Blocks.BLACKSTONE, "Blackstone", "minecraft", "nether", false),
	BASALT("minecraft_basalt", "minecraft:block/basalt_side", "basalt", Blocks.BASALT, "Basalt", "minecraft", "nether", false),
	SOUL_SOIL("minecraft_soul_soil", "minecraft:block/soul_soil", "soul_soil", Blocks.SOUL_SOIL, "Soul Soil", "minecraft", "nether", false),
	END_STONE("minecraft_end_stone", "minecraft:block/end_stone", "end_stone", Blocks.END_STONE, "End Stone", "minecraft", "end", false),

	CREATE_GABBRO("create_gabbro", "create:block/palettes/gabbro/plain", "gabbro", new ResourceLocation("create:gabbro"), "Gabbro", "create", "overworld", false),
	CREATE_LIMESTONE("create_limestone", "create:block/palettes/limestone/plain", "c_limestone", new ResourceLocation("create:limestone"), "Limestone", "create", "overworld", false),
	CREATE_SCORIA("create_scoria", "create:block/palettes/natural_scoria", "scoria", new ResourceLocation("create:natural_scoria"), "Scoria", "create", "overworld", false),
	CREATE_WEATHERED_LIMESTONE("create_weathered_limestone", "create:block/palettes/weathered_limestone/plain", "weathered_limestone", new ResourceLocation("create:weathered_limestone"), "Weathered Limestone", "create", "overworld", false),

	QUARK_JASPER("quark_jasper", "quark:block/jasper", "jasper", new ResourceLocation("quark:jasper"), "Jasper", "quark", "overworld", false),
	QUARK_MARBLE("quark_marble", "quark:block/marble", "marble", new ResourceLocation("quark:marble"), "Marble", "quark", "overworld", false),
	QUARK_SLATE("quark_slate", "quark:block/slate", "slate", new ResourceLocation("quark:slate"), "Slate", "quark", "overworld", false),

	BYG_MOSSY_STONE("byg_mossy_stone", "byg:block/mossy_stone", "mossy_stone", new ResourceLocation("byg:mossy_stone"), "Mossy Stone", "byg", "overworld", false),
	BYG_BRIMSTONE("byg_brimstone", "byg:block/brimstone", "brimstone", new ResourceLocation("byg:brimstone"), "Brimstone", "byg", "nether", false),
	BYG_SUBZERO_ASH("byg_subzero_ash", "byg:block/subzero_ash", "subzero_ash", new ResourceLocation("byg:subzero_ash_block"), "Subzero Ash Block", "byg", "nether", false),
	BYG_BLUE_NETHERRACK("byg_blue_netherrack", "byg:block/blue_netherrack", "blue_netherrack", new ResourceLocation("byg:blue_netherrack"), "Blue Netherrack", "byg", "nether", false),
	BYG_NYLIUM_SOUL_SOIL("byg_nylium_soul_soil", "byg:block/nylium_soul_soil", "nylium_soul_soil", new ResourceLocation("byg:nylium_soul_soil"), "Nylium Soul Soil", "byg", "nether", false),
	BYG_ETHER_STONE("byg_ether_stone", "byg:block/ether_stone", "ether_stone", new ResourceLocation("byg:ether_stone"), "Ether Stone", "byg", "end", false),
	BYG_CRYPTIC_STONE("byg_cryptic_stone", "byg:block/cryptic_stone", "cryptic_stone", new ResourceLocation("byg:cryptic_stone"), "Cryptic Stone", "byg", "end", false),

	BETTEREND_FLAVOLITE("betterend_flavolite", "betterendforge:block/flavolite", "flavolite", new ResourceLocation("betterendforge:flavolite"), "Flavolite", "betterendforge", "end", false),
	BETTEREND_SULPHURIC_ROCK("betterend_sulphuric_rock", "betterendforge:block/sulphuric_rock", "sulphuric_rock", new ResourceLocation("betterendforge:sulphuric_rock"), "Sulphuric Rock", "betterendforge", "end", false),
	BETTEREND_VIOLECITE("betterend_violecite", "betterendforge:block/violecite", "violecite", new ResourceLocation("betterendforge:violecite"), "Violecite", "betterendforge", "end", false),

	ASTRAL_MARBLE("astral_marble", "astralsorcery:block/marble_raw", "raw_marble", new ResourceLocation("astralsorcery:marble_raw"), "Marble", "astralsorcery", "overworld", false);

	//create:block/palettes/gabbro/plain
	//emendatusenigmatica:blocks/strata/create_gabbro

	public final String id;
	public final String baseTexture;
	public final String suffix;
	public final Supplier<Block> block;
	public final String localisedName;
	public final String modid;
	public final String dim;
	public final boolean state;

	Strata(String id, String baseTexture, String suffix, Block block, String localisedName, String modid, String dim, boolean state) {
		this.id = id;
		this.baseTexture = baseTexture;
		this.suffix = suffix;
		this.block = () -> block;
		this.localisedName = localisedName;
		this.modid = modid;
		this.dim = dim;
		this.state = state;
	}

	Strata(String id, String baseTexture, String suffix, ResourceLocation loc, String localisedName, String modid, String dim, boolean state) {
		this.id = id;
		this.baseTexture = baseTexture;
		this.suffix = suffix;
		this.block = () -> ForgeRegistries.BLOCKS.containsKey(loc) ? ForgeRegistries.BLOCKS.getValue(loc) : null;
		this.localisedName = localisedName;
		this.modid = modid;
		this.dim = dim;
		this.state = state;
	}
}