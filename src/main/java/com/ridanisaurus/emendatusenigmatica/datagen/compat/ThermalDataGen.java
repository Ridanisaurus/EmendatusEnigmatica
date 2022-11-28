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
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.compat.CompatModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.compat.CompatProcessor;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EETags;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.Consumer;

public class ThermalDataGen {

	public static class ThermalRecipes extends EERecipeProvider {

		public ThermalRecipes(DataGenerator gen) {
			super(gen);
		}

		@Override
		protected void buildRecipes(Consumer<IFinishedGenericRecipe> consumer) {
			for (MaterialModel material : EELoader.MATERIALS) {
				List<String> processedType = material.getProcessedTypes();
				for (CompatModel compat : EELoader.COMPAT) {

					if (compat.getId().equals(material.getId()) && CompatProcessor.recipesTable.contains("thermal", "pulverizer") && material.isModded()) {
						// Metal Ore to Dust
						if (CompatProcessor.outputMap.containsKey("ore") && processedType.contains("ore") && material.getProperties().getMaterialType().equals("metal")) {
							new RecipeBuilder("results")
									.type("thermal:pulverizer")
									.group("emendatusenigmatica:compat_recipe")
									.fieldFloat("experience", 0.2f)
									.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(false)
											.tag(EETags.MATERIAL_ORE.apply(material.getId()))
									)
									.addOutput(builder -> builder
											.stackWithoutCount(EERegistrar.dustMap.get(material.getId()).get(), 2.0f)
											.stacksWithCombinedChance(CompatProcessor.outputMap.get("ore"))
									)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore_pulverizer/" + material.getId()));
						}
						// Gem Ore to Gem
						if (CompatProcessor.outputMap.containsKey("ore") && processedType.contains("ore") && material.getProperties().getMaterialType().equals("gem")) {
							new RecipeBuilder("results")
									.type("thermal:pulverizer")
									.group("emendatusenigmatica:compat_recipe")
									.fieldFloat("experience", 0.2f)
									.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(false)
											.tag(EETags.MATERIAL_ORE.apply(material.getId())))
									.addOutput(builder -> builder
											.stackWithoutCount((processedType.contains("gem") ? EERegistrar.gemMap.get(material.getId()).get() : material.getOreDrop().getDefaultItemDropAsItem()), 1.0f)
											.stacksWithCombinedChance(CompatProcessor.outputMap.get("ore"))
									)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_ore_pulverizer/" + material.getId()));
						}
						if (CompatProcessor.outputMap.containsKey("raw")) {
							new RecipeBuilder("results")
									.type("thermal:pulverizer")
									.group("emendatusenigmatica:compat_recipe")
									.fieldFloat("experience", 0.2f)
									.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(false)
											.tag(EETags.MATERIAL_RAW.apply(material.getId()))
									)
									.addOutput(builder -> builder
											.stackWithoutCount(EERegistrar.dustMap.get(material.getId()).get(), 1.0f)
											.stacksWithCombinedChance(CompatProcessor.outputMap.get("raw"))
									)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_raw_pulverizer/" + material.getId()));
						}
					}
				}
			}

			for (MaterialModel material : EELoader.MATERIALS) {
				List<String> processedType = material.getProcessedTypes();
				// Press
				if (processedType.contains("raw") && material.isModded()) {
					// Raw Block to Raw Material
					// TODO Fix this
//					new RecipeBuilder("results", EERegistrar.rawMap.get(material.getId()).get(), 9)
//							.type("thermal:press")
//							.group("emendatusenigmatica:compat_recipe")
//							.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true)
//									.stack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal:press_unpacking_die")))
//									.stack(EERegistrar.rawBlockItemMap.get(material.getId()).get())
//							)
//							.fieldInt("energy", 400)
//							.save(consumer, new ResourceLocation(Reference.MOD_ID, "raw/from_raw_block_press/" + material.getId()));
					// Raw Material to Raw Block
					// TODO Fix this
//					new RecipeBuilder("results", EERegistrar.rawBlockItemMap.get(material.getId()).get())
//							.type("thermal:press")
//							.group("emendatusenigmatica:compat_recipe")
//							.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true)
//									.stack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal:press_packing_3x3_die")))
//									.stackWithCount(EERegistrar.rawMap.get(material.getId()).get(), 9)
//							)
//							.fieldInt("energy", 400)
//							.save(consumer, new ResourceLocation(Reference.MOD_ID, "raw_block/from_raw_press/" + material.getId()));
				}
				if (processedType.contains("storage_block") && material.isModded()) {
					// Ingot
					if (processedType.contains("ingot")) {
						// Storage Block to Ingot
						// TODO Fix this
//						new RecipeBuilder("results", EERegistrar.ingotMap.get(material.getId()).get(), 9)
//								.type("thermal:press")
//								.group("emendatusenigmatica:compat_recipe")
//								.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true)
//										.stack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal:press_unpacking_die")))
//										.stack(EERegistrar.storageBlockItemMap.get(material.getId()).get())
//								)
//								.fieldInt("energy", 400)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_storage_block_press/" + material.getId()));
						// Ingot to Storage Block
						// TODO Fix this
//						new RecipeBuilder("results", EERegistrar.storageBlockItemMap.get(material.getId()).get())
//								.type("thermal:press")
//								.group("emendatusenigmatica:compat_recipe")
//								.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true)
//										.stack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal:press_packing_3x3_die")))
//										.stackWithCount(EERegistrar.ingotMap.get(material.getId()).get(), 9)
//								)
//								.fieldInt("energy", 400)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "storage_block/from_ingot_press/" + material.getId()));
					}
					// Gem
					if (processedType.contains("gem")) {
						if (material.getProperties().getBlockRecipeType() == 9) {
							// Storage Block to Gem - x9
							// TODO Fix this
//							new RecipeBuilder("results", EERegistrar.gemMap.get(material.getId()).get(), 9)
//									.type("thermal:press")
//									.group("emendatusenigmatica:compat_recipe")
//									.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true)
//											.stack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal:press_unpacking_die")))
//											.stack(EERegistrar.storageBlockItemMap.get(material.getId()).get())
//									)
//									.fieldInt("energy", 400)
//									.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_storage_block_press/9x/" + material.getId()));
							// Gem to Storage Block - x9
							// TODO Fix this
//							new RecipeBuilder("results", EERegistrar.storageBlockItemMap.get(material.getId()).get())
//									.type("thermal:press")
//									.group("emendatusenigmatica:compat_recipe")
//									.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true)
//											.stack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal:press_packing_3x3_die")))
//											.stackWithCount(EERegistrar.gemMap.get(material.getId()).get(), 9)
//									)
//									.fieldInt("energy", 400)
//									.save(consumer, new ResourceLocation(Reference.MOD_ID, "storage_block/from_gem_press/9x/" + material.getId()));
						}
						if (material.getProperties().getBlockRecipeType() == 4) {
							// Storage Block to Gem - x4
							// TODO Fix this
//							new RecipeBuilder("results", EERegistrar.gemMap.get(material.getId()).get(), 4)
//									.type("thermal:press")
//									.group("emendatusenigmatica:compat_recipe")
//									.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true)
//											.stack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal:press_unpacking_die")))
//											.stack(EERegistrar.storageBlockItemMap.get(material.getId()).get())
//									)
//									.fieldInt("energy", 400)
//									.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_storage_block_press/4x/" + material.getId()));
							// Gem to Storage Block - x4
							// TODO Fix this
//							new RecipeBuilder("results", EERegistrar.storageBlockItemMap.get(material.getId()).get())
//									.type("thermal:press")
//									.group("emendatusenigmatica:compat_recipe")
//									.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true)
//											.stack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal:press_packing_2x2_die")))
//											.stackWithCount(EERegistrar.gemMap.get(material.getId()).get(), 4)
//									)
//									.fieldInt("energy", 400)
//									.save(consumer, new ResourceLocation(Reference.MOD_ID, "storage_block/from_gem_press/4x/" + material.getId()));
						}
					}
				}

				// Pulverizer
				ItemLike secondOutput = material.getCompat().getThermalCompat().getPulverizerCompat().getSecondOutput();
				float secondOutputChance = material.getCompat().getThermalCompat().getPulverizerCompat().getSecondOutputCombinedChance();

				if (processedType.contains("dust") && material.isModded()) {
					// Ingot to Dust
					// TODO Fix this
					if (processedType.contains("ingot")) {
//						new RecipeBuilder("results", EERegistrar.dustMap.get(material.getId()).get(), 1)
//								.type("thermal:pulverizer")
//								.group("emendatusenigmatica:compat_recipe")
//								.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(false)
//										.tag(EETags.MATERIAL_INGOT.apply(material.getId())))
//								.fieldFloat("energy_mod", 0.5f)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ingot_pulverizer/" + material.getId()));
					}
					// Gem to Dust
					// TODO Fix this
					if (processedType.contains("gem")) {
//						new RecipeBuilder("results", EERegistrar.dustMap.get(material.getId()).get(), 1)
//								.type("thermal:pulverizer")
//								.group("emendatusenigmatica:compat_recipe")
//								.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(false)
//										.tag(EETags.MATERIAL_GEM.apply(material.getId())))
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_gem_pulverizer/" + material.getId()));
					}
				}

				// Induction Smelter
				// Dust to Ingot
				// TODO Fix this
				if (processedType.contains("ingot") && processedType.contains("dust") && material.isModded()) {
//					new RecipeBuilder("results", EERegistrar.ingotMap.get(material.getId()).get(), 1)
//							.type("thermal:smelter")
//							.group("emendatusenigmatica:compat_recipe")
//							.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(false)
//									.tag(EETags.MATERIAL_DUST.apply(material.getId())))
//							.fieldFloat("energy_mod", 0.5f)
//							.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_dust_induction/" + material.getId()));
				}
				// Ore to Ingot
				// TODO Fix this
				if (processedType.contains("ore") && processedType.contains("ingot") && material.getProperties().getMaterialType().equals("metal") && material.isModded()) {
//					new RecipeBuilder("results", EERegistrar.ingotMap.get(material.getId()).get(), 1.0f)
//							.type("thermal:smelter")
//							.group("emendatusenigmatica:compat_recipe")
//							.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(false)
//									.tag(EETags.MATERIAL_ORE.apply(material.getId())))
//							.addOutput(builder -> builder
//									.stackWithoutCount(material.getCompat().getThermalCompat().getInductionCompat().getSecondOutput(), material.getCompat().getThermalCompat().getInductionCompat().getSecondOutputCombinedChance())
//									.stackWithoutCount(ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal:rich_slag")), 0.2f))
//							.fieldFloat("experience", 0.2f)
//							.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore_induction/" + material.getId()));
				}
				// Ore to Gem
				// TODO Fix this
				if (processedType.contains("ore") && material.getProperties().getMaterialType().equals("gem") && material.isModded()) {
//					new RecipeBuilder("results", (processedType.contains("gem") ? EERegistrar.gemMap.get(material.getId()).get() : material.getOreDrop().getDefaultItemDropAsItem()), material.getCompat().getThermalCompat().getInductionCompat().getFirstOutputCombinedChance())
//							.type("thermal:smelter")
//							.group("emendatusenigmatica:compat_recipe")
//							.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(false)
//									.tag(EETags.MATERIAL_ORE.apply(material.getId())))
//							.addOutput(builder -> builder
//									.stackWithoutCount(material.getCompat().getThermalCompat().getInductionCompat().getSecondOutput(), material.getCompat().getThermalCompat().getInductionCompat().getSecondOutputCombinedChance())
//									.stackWithoutCount(ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal:rich_slag")), 0.15f))
//							.fieldFloat("experience", 0.5f)
//							.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_ore_induction/" + material.getId()));
				}

				// Alloy Induction
				// Two Input
				// TODO Fix this
				if (processedType.contains("ingot") && material.getProperties().getMaterialType().equals("alloy") && !material.getCompat().getThermalCompat().getInductionCompat().isThirdInputSet()) {
//					new RecipeBuilder("results", EERegistrar.ingotMap.get(material.getId()).get(), material.getCompat().getThermalCompat().getInductionCompat().getFirstOutputCount())
//							.type("thermal:smelter")
//							.group("emendatusenigmatica:compat_recipe")
//							.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true)
//									.stackWithCount(material.getCompat().getThermalCompat().getInductionCompat().getFirstInput(), material.getCompat().getThermalCompat().getInductionCompat().getFirstInputCount())
//									.stackWithCount(material.getCompat().getThermalCompat().getInductionCompat().getSecondInput(), material.getCompat().getThermalCompat().getInductionCompat().getSecondInputCount())
//							)
//							.fieldInt("energy", 8000)
//							.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_alloy_induction/" + material.getId()));
				}
				// Three Input
				// TODO Fix this
				if (processedType.contains("ingot") && material.getProperties().getMaterialType().equals("alloy") && material.getCompat().getThermalCompat().getInductionCompat().isThirdInputSet()) {
//					new RecipeBuilder("results", EERegistrar.ingotMap.get(material.getId()).get(), material.getCompat().getThermalCompat().getInductionCompat().getFirstOutputCount())
//							.type("thermal:smelter")
//							.group("emendatusenigmatica:compat_recipe")
//							.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true)
//									.stackWithCount(material.getCompat().getThermalCompat().getInductionCompat().getFirstInput(), material.getCompat().getThermalCompat().getInductionCompat().getFirstInputCount())
//									.stackWithCount(material.getCompat().getThermalCompat().getInductionCompat().getSecondInput(), material.getCompat().getThermalCompat().getInductionCompat().getSecondInputCount())
//									.stackWithCount(material.getCompat().getThermalCompat().getInductionCompat().getThirdInput(), material.getCompat().getThermalCompat().getInductionCompat().getThirdInputCount())
//							)
//							.fieldInt("energy", 16000)
//							.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_alloy_induction/" + material.getId()));
				}

			}
		}

		@Override
		public String getName() {
			return "Emendatus Enigmatica Thermal Recipes";
		}
	}
}