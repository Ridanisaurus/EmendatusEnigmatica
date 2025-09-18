package com.ridanisaurus.eecreateaddon;

import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.api.IEmendatusPlugin;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import net.minecraft.data.DataGenerator;

import java.util.List;

@EmendatusPluginReference(modid = EECreateAddon.MOD_ID, name = "config")
public class EECreatePlugin implements IEmendatusPlugin {
	@Override
	public void load(EmendatusDataRegistry emendatusDataRegistry) {

	}

	@Override
	public void registerMinecraft(List<MaterialModel> materialModels, List<StrataModel> strataModels) {
		for (MaterialModel material : materialModels) {
			if (material.getProcessedTypes().contains("crushed_ore")) {
                EECreateRegistrar.registerCrushedOres(material);
            }
		}
	}

	@Override
	public void registerDynamicDataGen(DataGenerator generator, EmendatusDataRegistry registry) {
		generator.addProvider(true, new EECreateDataGen.ItemModels(generator, registry));
		generator.addProvider(true, new EECreateDataGen.Lang(generator, registry));
		generator.addProvider(true, new EECreateDataGen.ItemTags(generator, registry));
		generator.addProvider(true, new EECreateDataGen.VanillaRecipes(generator, registry));
		generator.addProvider(true, new EECreateDataGen.CreateRecipes(generator, registry));
	}

	@Override
	public void finish(EmendatusDataRegistry emendatusDataRegistry) {

	}
}
