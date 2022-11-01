package com.ridanisaurus.emendatusenigmatica.loader.deposit;

import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.RegistryObject;

public interface IDepositProcessor {
	void load();

	void setupOres();
//	Feature<?> getFeature();
//	void setup();
	RegistryObject<PlacedFeature> getPlacedFeature();

}
