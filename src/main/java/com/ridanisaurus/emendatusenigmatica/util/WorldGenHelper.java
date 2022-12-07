package com.ridanisaurus.emendatusenigmatica.util;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class WorldGenHelper {
	public static List<PlacementModifier> orePlacement(PlacementModifier modifier_1, PlacementModifier modifier_2) {
		return List.of(modifier_1, InSquarePlacement.spread(), modifier_2, BiomeFilter.biome());
	}

	public static List<PlacementModifier> commonOrePlacement(int chancePerChunk, PlacementModifier modifier) {
		return orePlacement(CountPlacement.of(chancePerChunk), modifier);
	}

	public static List<PlacementModifier> rareOrePlacement(int chancePerChunk, PlacementModifier modifier) {
		return orePlacement(RarityFilter.onAverageOnceEvery(chancePerChunk), modifier);
	}
}
