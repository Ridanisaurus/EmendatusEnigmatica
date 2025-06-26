package com.ridanisaurus.eethermaladdon;

import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.api.IEmendatusPlugin;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import net.minecraft.data.DataGenerator;

import java.util.List;

@EmendatusPluginReference(modid = EEThermalAddon.MOD_ID, name = "config")
public class EEThermalPlugin implements IEmendatusPlugin {
	@Override
	public void load(EmendatusDataRegistry emendatusDataRegistry) {

	}

	@Override
	public void registerMinecraft(List<MaterialModel> materialModels, List<StrataModel> strataModels) {

	}

	@Override
	public void registerDynamicDataGen(DataGenerator generator, EmendatusDataRegistry registry) {
		generator.addProvider(true, new EEThermalDataGen.ThermalRecipes(generator, registry));
	}

	@Override
	public void finish(EmendatusDataRegistry emendatusDataRegistry) {

	}
}
