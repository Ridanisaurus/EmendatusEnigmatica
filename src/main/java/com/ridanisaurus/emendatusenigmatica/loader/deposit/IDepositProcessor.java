package com.ridanisaurus.emendatusenigmatica.loader.deposit;

import com.mojang.serialization.Codec;
import com.ridanisaurus.emendatusenigmatica.world.gen.OreBiomeModifier;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.RegistryObject;

public interface IDepositProcessor {
	void load();

	void setup();
//	Feature<?> getFeature();
//	void setup();
	RegistryObject<PlacedFeature> getPlacedFeature();
//	RegistryObject<Codec<OreBiomeModifier>> getOreBiomeModifier();

}
