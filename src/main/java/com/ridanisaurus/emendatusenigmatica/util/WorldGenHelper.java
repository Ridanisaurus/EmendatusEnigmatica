package com.ridanisaurus.emendatusenigmatica.util;

import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraftforge.common.extensions.IForgeLevel;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.Arrays;
import java.util.List;

public class WorldGenHelper {
	public static String getDimensionAsString(IForgeLevel world) {
		if (world instanceof Level) {
			return ((Level) world).dimension().location().toString();
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

	public static <FC extends FeatureConfiguration, F extends Feature<FC>> ConfiguredFeature<FC, F> registerFeature(final String name, final ConfiguredFeature<FC, F> feature) {
		return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, Reference.MOD_ID + ":" + name, feature);
	}
}
