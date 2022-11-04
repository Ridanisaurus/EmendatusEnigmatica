package com.ridanisaurus.emendatusenigmatica.util;

import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.common.extensions.IForgeLevel;
import net.minecraftforge.registries.DeferredRegister;

import java.util.List;

public class WorldGenHelper {
	// TODO: Review the Dim code to see if it still works, and re-enable it in the custom features
	public static String getDimensionAsString(IForgeLevel world) {
		if (world instanceof Level) {
			return ((Level) world).dimension().location().toString();
		}
		if (world instanceof WorldGenRegion) {
			return ((WorldGenRegion) world).getLevel().dimension().location().toString();
		}
		return null;
	}

//	public static boolean biomeCheck(BiomeLoadingEvent event, List<String> whitelistBiomes, List<String> blacklistBiomes) {
//		// Credit: https://github.com/InnovativeOnlineIndustries/Industrial-Foregoing/blob/259b70ac60714786d1f793124bd66284ac08f1e3/src/main/java/com/buuz135/industrial/recipe/LaserDrillOreRecipe.java#L210
//		String[] whitelistBiomesArray = whitelistBiomes.stream().toArray(String[]::new);
//		String[] blacklistBiomesArray = blacklistBiomes.stream().toArray(String[]::new);
//
//		return whitelistBiomesArray.length == 0 ? Arrays.stream(blacklistBiomesArray).noneMatch(String -> String.equals(event.getName().toString())) : Arrays.stream(whitelistBiomesArray).anyMatch(String -> String.equals(event.getName().toString()));
//	}

//	public static DeferredRegister<ConfiguredFeature<?,?>> getOreFeature() {
//		return EERegistrar.ORE_FEATURES;
//	}
//
//	public static DeferredRegister<PlacedFeature> getPlacedOreFeature() {
//		return EERegistrar.PLACED_ORE_FEATURES;
//	}

//	public static DeferredRegister<Codec<? extends BiomeModifier>> getBiomeSerializer() {
//		return EERegistrar.BIOME_SERIALIZERS;
//	}

	public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
		return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
	}

	public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
		return orePlacement(CountPlacement.of(p_195344_), p_195345_);
	}
}
