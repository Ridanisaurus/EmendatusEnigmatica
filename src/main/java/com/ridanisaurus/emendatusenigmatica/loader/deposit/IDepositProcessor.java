package com.ridanisaurus.emendatusenigmatica.loader.deposit;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public interface IDepositProcessor {
	void load();

	void setupOres(BiomeLoadingEvent event);
	Feature<?> getFeature();
	void setup();

}
