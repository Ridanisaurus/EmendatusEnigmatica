package com.ridanisaurus.emendatusenigmatica.util;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.Arrays;
import java.util.List;

public class WorldGenHelper {
	public static String getDimensionAsString(IWorld world) {
		if (world instanceof World) {
			return ((World) world).dimension().location().toString();
		}
		if (world instanceof WorldGenRegion) {
			return ((WorldGenRegion) world).getLevel().dimension().location().toString();
		}
		return null;
	}

	public static boolean biomeCheck(BiomeLoadingEvent event, List<String> whitelistBiomes, List<String> blacklistBiomes) {
		// Credit: https://github.com/InnovativeOnlineIndustries/Industrial-Foregoing/blob/259b70ac60714786d1f793124bd66284ac08f1e3/src/main/java/com/buuz135/industrial/recipe/LaserDrillOreRecipe.java#L210
		String[] whitelistBiomesArray = whitelistBiomes.stream().toArray(String[]::new);
		String[] blacklistBiomesArray = blacklistBiomes.stream().toArray(String[]::new);

		return whitelistBiomesArray.length == 0 ? Arrays.stream(blacklistBiomesArray).noneMatch(String -> String.equals(event.getName().toString())) : Arrays.stream(whitelistBiomesArray).anyMatch(String -> String.equals(event.getName().toString()));
	}

	public static <FC extends IFeatureConfig, F extends Feature<FC>> ConfiguredFeature<FC, F> registerFeature(final String name, final ConfiguredFeature<FC, F> feature) {
		return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, Reference.MOD_ID + ":" + name, feature);
	}
}
