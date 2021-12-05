package com.ridanisaurus.emendatusenigmatica.util;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;

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

	public static <FC extends IFeatureConfig, F extends Feature<FC>> ConfiguredFeature<FC, F> registerFeature(final String name, final ConfiguredFeature<FC, F> feature) {
		return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, Reference.MOD_ID + ":" + name, feature);
	}
}
