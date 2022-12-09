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

package com.ridanisaurus.emendatusenigmatica.datagen.compat;

import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.datagen.base.EERecipeProvider;
import com.ridanisaurus.emendatusenigmatica.datagen.base.RecipeBuilder;
import com.ridanisaurus.emendatusenigmatica.datagen.base.IFinishedGenericRecipe;
import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EETags;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.function.Consumer;

public class OccultismDataGen {

	public static class OccultismRecipes extends EERecipeProvider {

		private final EmendatusDataRegistry registry;

		public OccultismRecipes(DataGenerator gen, EmendatusDataRegistry registry) {
			super(gen);
			this.registry = registry;
		}

		@Override
		protected void buildRecipes(Consumer<IFinishedGenericRecipe> consumer) {
			for (MaterialModel material : registry.getMaterials()) {
				List<String> processedType = material.getProcessedTypes();
				if (material.getCompat().getOccultismCompat()) {
					if (processedType.contains("dust") && processedType.contains("ore")) {
						// Dust from Ore - Crusher Spirit
						new RecipeBuilder("result")
								.forceOutputArray(false)
								.type("occultism:crushing")
								.group("emendatusenigmatica:compat_recipe")
								.fieldJson("ingredient", new RecipeBuilder.JsonItemBuilder(false)
										.tag(EETags.MATERIAL_ORE.apply(material.getId())))
								.fieldInt("crushing_time", 200)
								.addOutput(builder -> builder
										.stackWithCount(EERegistrar.dustMap.get(material.getId()).get(), 2)
								)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore_crusher_spirit/" + material.getId()));
					}
					if (processedType.contains("dust") && processedType.contains("ingot")) {
						// Dust from Ingot - Crusher Spirit
						new RecipeBuilder("result")
								.forceOutputArray(false)
								.type("occultism:crushing")
								.group("emendatusenigmatica:compat_recipe")
								.fieldJson("ingredient", new RecipeBuilder.JsonItemBuilder(false)
										.tag(EETags.MATERIAL_INGOT.apply(material.getId())))
								.fieldInt("crushing_time", 200)
								.fieldBoolean("ignore_crushing_multiplier", true)
								.addOutput(builder -> builder
										.stack(EERegistrar.dustMap.get(material.getId()).get())
								)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ingot_crusher_spirit/" + material.getId()));
					}
					if (processedType.contains("dust") && processedType.contains("raw")) {
						// Dust from Raw Material - Crusher Spirit
						new RecipeBuilder("result")
								.forceOutputArray(false)
								.type("occultism:crushing")
								.group("emendatusenigmatica:compat_recipe")
								.fieldJson("ingredient", new RecipeBuilder.JsonItemBuilder(false)
										.tag(EETags.MATERIAL_RAW.apply(material.getId())))
								.fieldInt("crushing_time", 200)
								.addOutput(builder -> builder
										.stackWithCount(EERegistrar.dustMap.get(material.getId()).get(), 2)
								)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_raw_crusher_spirit/" + material.getId()));
					}
				}
			}
		}

		@Override
		public String getName() {
			return "Emendatus Enigmatica Occultism Recipes";
		}
	}
}