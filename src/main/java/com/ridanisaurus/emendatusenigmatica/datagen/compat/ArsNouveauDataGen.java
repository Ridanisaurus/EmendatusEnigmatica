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

import com.ridanisaurus.emendatusenigmatica.datagen.base.RecipeBuilder;
import com.ridanisaurus.emendatusenigmatica.datagen.base.EERecipeProvider;
import com.ridanisaurus.emendatusenigmatica.datagen.base.IFinishedGenericRecipe;
import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.Consumer;

public class ArsNouveauDataGen {

	public static class ArsNouveauRecipes extends EERecipeProvider {

		public ArsNouveauRecipes(DataGenerator gen) {
			super(gen);
		}

		@Override
		protected void buildRecipes(Consumer<IFinishedGenericRecipe> consumer) {
			for (MaterialModel material : EELoader.MATERIALS) {
				List<String> processedType = material.getProcessedTypes();
				for (StrataModel stratum : EELoader.STRATA) {
					if (processedType.contains("dust") && processedType.contains("ore")) {
						// Dust from Ore - Crush Spell
						new RecipeBuilder("output", EERegistrar.dustMap.get(material.getId()).get(), 2, 1)
								.forceOutputArray(true)
								.type("ars_nouveau:crush")
								.group("emendatusenigmatica:compat_recipe")
								.fieldJson("input", new RecipeBuilder.JsonItemBuilder(false)
										.stack(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get()))
								.addOutput(builder -> builder
										.stackWithChance((ForgeRegistries.ITEMS.getValue(stratum.getFillerType()) == Items.AIR ? Items.COBBLESTONE : ForgeRegistries.ITEMS.getValue(stratum.getFillerType())), 1, 1))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore_crush_spell/" + material.getId() + "_" + stratum.getId()));
					}
				}
			}
		}

		@Override
		public String getName() {
			return "Emendatus Enigmatica Ars Nouveau Recipes";
		}
	}
}