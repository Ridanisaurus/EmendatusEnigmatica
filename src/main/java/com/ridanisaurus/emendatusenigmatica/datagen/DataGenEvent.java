/*
 *  MIT License
 *
 *  Copyright (c) 2020 Ridanisaurus
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package com.ridanisaurus.emendatusenigmatica.datagen;

import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

//@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenEvent {

//	public static boolean eventGenerated = false;
//
//	@SubscribeEvent
//	public static void gatherData(GatherDataEvent event) {
//		EmendatusEnigmatica.LOGGER.info("Data Generator Loaded!");
//		DataGenerator generator = DataGeneratorFactory.createEEDataGenerator();
//		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
//
//		generator.addProvider(event.includeClient(), new BlockStatesAndModelsGen(generator, existingFileHelper));
//		generator.addProvider(event.includeClient(), new ItemModelsGen(generator, existingFileHelper));
//		generator.addProvider(event.includeClient(), new LangGen(generator));
//		if (EmendatusEnigmatica.CREATE_LOADED) generator.addProvider(event.includeClient(), new CreateDataGen.CreateItemModels(generator, existingFileHelper));
//		if (EmendatusEnigmatica.BLOODMAGIC_LOADED) generator.addProvider(event.includeClient(), new BloodMagicDataGen.BloodMagicItemModels(generator, existingFileHelper));
//		if (EmendatusEnigmatica.MEKANISM_LOADED) generator.addProvider(event.includeClient(), new MekanismDataGen.MekanismItemModels(generator, existingFileHelper));
//
//		BlockTagsGen blockTagsGeneration = new BlockTagsGen(generator, existingFileHelper);
//		generator.addProvider(event.includeServer(), new ItemTagsGen(generator, blockTagsGeneration, existingFileHelper));
//		generator.addProvider(event.includeServer(), blockTagsGeneration);
//		generator.addProvider(event.includeServer(), new BlockHarvestTagsGen.BlockHarvestLevelTagsGen(generator, existingFileHelper));
//		generator.addProvider(event.includeServer(), new BlockHarvestTagsGen.BlockHarvestToolTagsGen(generator, existingFileHelper));
//		generator.addProvider(event.includeServer(), new FluidTagsGen(generator, existingFileHelper));
//		generator.addProvider(event.includeServer(), new RecipesGen(generator));
//		generator.addProvider(event.includeServer(), new LootTablesGen(generator));
//		generator.addProvider(event.includeServer(), new OreFeatureDataGen(generator));
//
//		if (EmendatusEnigmatica.CREATE_LOADED) {
//			generator.addProvider(event.includeServer(), new CreateDataGen.CreateItemTags(generator, blockTagsGeneration, existingFileHelper));
//			generator.addProvider(event.includeServer(), new CreateDataGen.CreateRecipes(generator));
//		}
//		if (EmendatusEnigmatica.BLOODMAGIC_LOADED) {
//			generator.addProvider(event.includeServer(), new BloodMagicDataGen.BloodMagicItemTags(generator, blockTagsGeneration, existingFileHelper));
//			generator.addProvider(event.includeServer(), new BloodMagicDataGen.BloodMagicRecipes(generator));
//		}
//		if (EmendatusEnigmatica.ARSNOUVEAU_LOADED) generator.addProvider(event.includeServer(), new ArsNouveauDataGen.ArsNouveauRecipes(generator));
//		if (EmendatusEnigmatica.OCCULTISM_LOADED) generator.addProvider(event.includeServer(), new OccultismDataGen.OccultismRecipes(generator));
//		if (EmendatusEnigmatica.THERMALSERIES_LOADED) generator.addProvider(event.includeServer(), new ThermalDataGen.ThermalRecipes(generator));
//		if (EmendatusEnigmatica.MEKANISM_LOADED) {
//			generator.addProvider(event.includeServer(), new MekanismDataGen.MekanismItemTags(generator, blockTagsGeneration, existingFileHelper));
//			generator.addProvider(event.includeServer(), new MekanismDataGen.MekanismSlurryTags(generator, existingFileHelper));
//			generator.addProvider(event.includeServer(), new MekanismDataGen.MekanismRecipes(generator));
//		}
//		eventGenerated = true;
//	}
}
