/*
 * MIT License
 *
 * Copyright (c) 2020 Ridanisaurus
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.ridanisaurus.emendatusenigmatica.datagen;

import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EETags;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.function.Consumer;

public class ThermalDataGen {

	public static class ThermalRecipes extends GenericRecipeProvider {

		public ThermalRecipes(DataGenerator gen) {
			super(gen);
		}

		@Override
		protected void buildGenericRecipes(Consumer<IFinishedGenericRecipe> consumer) {
			for (MaterialModel material : EELoader.MATERIALS) {
				List<String> processedType = material.getProcessedType();
				// Pulverizer
				// Ingot to Dust
				if (processedType.contains("ingot") && processedType.contains("dust") && material.isModded()) {
					new GenericRecipeBuilder("results", EERegistrar.dustMap.get(material.getId()).get(), 1)
							.type("thermal:pulverizer")
							.group("emendatusenigmatica:compat_recipe")
							.fieldJson("ingredients", new GenericRecipeBuilder.JsonItemBuilder(false)
									.tag(EETags.MATERIAL_INGOT.apply(material.getId())))
							.fieldFloat("energy_mod", 0.5f)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ingot_pulverizer/" + material.getId()));
				}
				// Gem to Dust
				if (processedType.contains("gem") && processedType.contains("dust") && material.isModded()) {
					new GenericRecipeBuilder("results", EERegistrar.dustMap.get(material.getId()).get(), 1)
							.type("thermal:pulverizer")
							.group("emendatusenigmatica:compat_recipe")
							.fieldJson("ingredients", new GenericRecipeBuilder.JsonItemBuilder(false)
									.tag(EETags.MATERIAL_GEM.apply(material.getId())))
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_gem_pulverizer/" + material.getId()));
				}
				// Ore to Dust
				if (processedType.contains("ore") && processedType.contains("dust") && material.getProperties().getOreBlockType().equals("metal") && material.isModded()) {
					new GenericRecipeBuilder("results", EERegistrar.dustMap.get(material.getId()).get(), 2.0f)
							.type("thermal:pulverizer")
							.group("emendatusenigmatica:compat_recipe")
							.fieldJson("ingredients", new GenericRecipeBuilder.JsonItemBuilder(false)
									.tag(EETags.MATERIAL_ORE.apply(material.getId())))
							.addOutput(builder -> builder
									.stackWithoutCount(material.getCompat().getThermalCompat().getPulverizerCompat().getSecondOutput(), material.getCompat().getThermalCompat().getPulverizerCompat().getSecondOutputCombinedChance())
									.stackWithoutCount(Items.GRAVEL, 0.2f))
							.fieldFloat("experience", 0.2f)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore_pulverizer/" + material.getId()));
				}
				// Ore to Gem
				if (processedType.contains("ore") && material.getProperties().getOreBlockType().equals("gem") && material.isModded()) {
					new GenericRecipeBuilder("results", (processedType.contains("gem") ? EERegistrar.gemMap.get(material.getId()).get() : material.getOreDrop().getDefaultItemDropAsItem()), material.getCompat().getThermalCompat().getPulverizerCompat().getFirstOutputCombinedChance())
							.type("thermal:pulverizer")
							.group("emendatusenigmatica:compat_recipe")
							.fieldJson("ingredients", new GenericRecipeBuilder.JsonItemBuilder(false)
									.tag(EETags.MATERIAL_ORE.apply(material.getId())))
							.addOutput(builder -> builder
									.stackWithoutCount(material.getCompat().getThermalCompat().getPulverizerCompat().getSecondOutput(), material.getCompat().getThermalCompat().getPulverizerCompat().getSecondOutputCombinedChance())
									.stackWithoutCount(Items.GRAVEL, 0.2f))
							.fieldFloat("experience", 0.2f)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_ore_pulverizer/" + material.getId()));
				}
				// Induction Smelter
				// Dust to Ingot
				if (processedType.contains("ingot") && processedType.contains("dust") && material.isModded()) {
					new GenericRecipeBuilder("results", EERegistrar.ingotMap.get(material.getId()).get(), 1)
							.type("thermal:smelter")
							.group("emendatusenigmatica:compat_recipe")
							.fieldJson("ingredients", new GenericRecipeBuilder.JsonItemBuilder(false)
									.tag(EETags.MATERIAL_DUST.apply(material.getId())))
							.fieldFloat("energy_mod", 0.5f)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_dust_induction/" + material.getId()));
				}
				// Ore to Ingot
				if (processedType.contains("ore") && processedType.contains("ingot") && material.getProperties().getOreBlockType().equals("metal") && material.isModded()) {
					new GenericRecipeBuilder("results", EERegistrar.ingotMap.get(material.getId()).get(), 1.0f)
							.type("thermal:smelter")
							.group("emendatusenigmatica:compat_recipe")
							.fieldJson("ingredients", new GenericRecipeBuilder.JsonItemBuilder(false)
									.tag(EETags.MATERIAL_ORE.apply(material.getId())))
							.addOutput(builder -> builder
									.stackWithoutCount(material.getCompat().getThermalCompat().getInductionCompat().getSecondOutput(), material.getCompat().getThermalCompat().getInductionCompat().getSecondOutputCombinedChance())
									.stackWithoutCount(Registry.ITEM.get(new ResourceLocation("thermal:rich_slag")), 0.2f))
							.fieldFloat("experience", 0.2f)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore_induction/" + material.getId()));
				}
				// Ore to Gem
				if (processedType.contains("ore") && material.getProperties().getOreBlockType().equals("gem") && material.isModded()) {
					new GenericRecipeBuilder("results", (processedType.contains("gem") ? EERegistrar.gemMap.get(material.getId()).get() : material.getOreDrop().getDefaultItemDropAsItem()), material.getCompat().getThermalCompat().getInductionCompat().getFirstOutputCombinedChance())
							.type("thermal:smelter")
							.group("emendatusenigmatica:compat_recipe")
							.fieldJson("ingredients", new GenericRecipeBuilder.JsonItemBuilder(false)
									.tag(EETags.MATERIAL_ORE.apply(material.getId())))
							.addOutput(builder -> builder
									.stackWithoutCount(material.getCompat().getThermalCompat().getInductionCompat().getSecondOutput(), material.getCompat().getThermalCompat().getInductionCompat().getSecondOutputCombinedChance())
									.stackWithoutCount(Registry.ITEM.get(new ResourceLocation("thermal:rich_slag")), 0.15f))
							.fieldFloat("experience", 0.5f)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_ore_induction/" + material.getId()));
				}

				// Alloy Induction
				// Two Input
				if (processedType.contains("ingot") && material.isAlloy() && !material.getCompat().getThermalCompat().getInductionCompat().isThirdInputSet()) {
					new GenericRecipeBuilder("results", EERegistrar.ingotMap.get(material.getId()).get(), material.getCompat().getThermalCompat().getInductionCompat().getFirstOutputCount())
							.type("thermal:smelter")
							.group("emendatusenigmatica:compat_recipe")
							.fieldJson("ingredients", new GenericRecipeBuilder.JsonItemBuilder(true)
									.stackWithCount(material.getCompat().getThermalCompat().getInductionCompat().getFirstInput(), material.getCompat().getThermalCompat().getInductionCompat().getFirstInputCount())
									.stackWithCount(material.getCompat().getThermalCompat().getInductionCompat().getSecondInput(), material.getCompat().getThermalCompat().getInductionCompat().getSecondInputCount())
							)
							.fieldInt("energy", 8000)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_alloy_induction/" + material.getId()));
				}
				// Three Input
				if (processedType.contains("ingot") && material.isAlloy() && material.getCompat().getThermalCompat().getInductionCompat().isThirdInputSet()) {
					new GenericRecipeBuilder("results", EERegistrar.ingotMap.get(material.getId()).get(), material.getCompat().getThermalCompat().getInductionCompat().getFirstOutputCount())
							.type("thermal:smelter")
							.group("emendatusenigmatica:compat_recipe")
							.fieldJson("ingredients", new GenericRecipeBuilder.JsonItemBuilder(true)
									.stackWithCount(material.getCompat().getThermalCompat().getInductionCompat().getFirstInput(), material.getCompat().getThermalCompat().getInductionCompat().getFirstInputCount())
									.stackWithCount(material.getCompat().getThermalCompat().getInductionCompat().getSecondInput(), material.getCompat().getThermalCompat().getInductionCompat().getSecondInputCount())
									.stackWithCount(material.getCompat().getThermalCompat().getInductionCompat().getThirdInput(), material.getCompat().getThermalCompat().getInductionCompat().getThirdInputCount())
							)
							.fieldInt("energy", 16000)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_alloy_induction/" + material.getId()));
				}

			}
		}

		@Override
		public String getName() {
			return "Emendatus Enigmatica Thermal Recipes";
		}
	}
}