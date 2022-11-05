package com.ridanisaurus.emendatusenigmatica.loader.deposit;

import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonDepositModelBase;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.geode.GeodeDepositModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sphere.SphereDepositModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.vanilla.VanillaDepositModel;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.RegistryObject;

public interface IDepositProcessor {
	void load();

	// TODO: This needs clean up!!
	VanillaDepositModel getVanillaModel();
	GeodeDepositModel getGeodeModel();
	SphereDepositModel getSphereModel();
	CommonDepositModelBase getModel();
}
