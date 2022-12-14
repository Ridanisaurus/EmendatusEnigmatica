///*
// *  MIT License
// *
// *  Copyright (c) 2020 Ridanisaurus
// *
// *  Permission is hereby granted, free of charge, to any person obtaining a copy
// *  of this software and associated documentation files (the "Software"), to deal
// *  in the Software without restriction, including without limitation the rights
// *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// *  copies of the Software, and to permit persons to whom the Software is
// *  furnished to do so, subject to the following conditions:
// *
// *  The above copyright notice and this permission notice shall be included in all
// *  copies or substantial portions of the Software.
// *
// *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// *  SOFTWARE.
// */
//
//package com.ridanisaurus.emendatusenigmatica.datagen.compat;
//
//import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
//import com.ridanisaurus.emendatusenigmatica.datagen.base.RecipeBuilder;
//import com.ridanisaurus.emendatusenigmatica.datagen.base.EERecipeProvider;
//import com.ridanisaurus.emendatusenigmatica.datagen.base.IFinishedGenericRecipe;
//import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
//import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
//import com.ridanisaurus.emendatusenigmatica.loader.parser.model.CompatModel;
//import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
//import com.ridanisaurus.emendatusenigmatica.registries.EETags;
//import com.ridanisaurus.emendatusenigmatica.util.Reference;
//import net.minecraft.data.DataGenerator;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraftforge.registries.ForgeRegistries;
//
//import java.util.List;
//import java.util.function.Consumer;
//
//public class ThermalDataGen {
//
//	public static class ThermalRecipes extends EERecipeProvider {
//
//		private final EmendatusDataRegistry registry;
//
//		public ThermalRecipes(DataGenerator gen, EmendatusDataRegistry registry) {
//			super(gen);
//			this.registry = registry;
//		}
//
//		@Override
//		protected void buildRecipes(Consumer<IFinishedGenericRecipe> consumer) {
//			for (MaterialModel material : registry.getMaterials()) {
//				if (material.getCompat().getThermalCompat()) {
//					List<String> processedType = material.getProcessedTypes();
//					// PULVERIZER
//					for (CompatModel compat : registry.getCompat()) {
//						if (compat.getId().equals(material.getId()) && material.isModded()) {
//							for (CompatModel.CompatRecipesModel recipe : compat.getRecipes()) {
//								if (recipe.getMod().equals("thermal") && recipe.getMachine().equals("pulverizer")) {
//									for (CompatModel.CompatValuesModel value : recipe.getValues()) {
//										if (value.getType().equals("ore") && processedType.contains("ore")) {
//											// Metal Ore to Dust
//											if (material.getProperties().getMaterialType().equals("metal") && processedType.contains("dust")) {
//												new RecipeBuilder("results")
//														.type("thermal:pulverizer")
//														.group("emendatusenigmatica:compat_recipe")
//														.fieldFloat("experience", 0.2f)
//														.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(false)
//																.tag(EETags.MATERIAL_ORE.apply(material.getId()))
//														)
//														.addOutput(builder -> builder
//																.stackWithoutCount(EERegistrar.dustMap.get(material.getId()).get(), 2.0f)
//																.stacksWithCombinedChance(value.getOutput())
//														)
//														.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore_pulverizer/" + material.getId()));
//											}
//											// Gem Ore to Gem
//											if (material.getProperties().getMaterialType().equals("gem")) {
//												new RecipeBuilder("results")
//														.type("thermal:pulverizer")
//														.group("emendatusenigmatica:compat_recipe")
//														.fieldFloat("experience", 0.2f)
//														.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(false)
//																.tag(EETags.MATERIAL_ORE.apply(material.getId())))
//														.addOutput(builder -> builder
//																.stackWithoutCount((processedType.contains("gem") ? EERegistrar.gemMap.get(material.getId()).get() : material.getOreDrop().getDefaultItemDropAsItem()), 1.0f)
//																.stacksWithCombinedChance(value.getOutput())
//														)
//														.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_ore_pulverizer/" + material.getId()));
//											}
//										}
//										// Raw Material to Dust
//										if (value.getType().equals("raw") && processedType.contains("raw") && processedType.contains("dust")) {
//											new RecipeBuilder("results")
//													.type("thermal:pulverizer")
//													.group("emendatusenigmatica:compat_recipe")
//													.fieldFloat("experience", 0.2f)
//													.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(false)
//															.tag(EETags.MATERIAL_RAW.apply(material.getId()))
//													)
//													.addOutput(builder -> builder
//															.stackWithoutCount(EERegistrar.dustMap.get(material.getId()).get(), 1.25f)
//															.stacksWithCombinedChance(value.getOutput())
//													)
//													.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_raw_pulverizer/" + material.getId()));
//										}
//									}
//								}
//							}
//						}
//					}
//					if (processedType.contains("dust") && material.isModded()) {
//						// Ingot to Dust
//						if (processedType.contains("ingot")) {
//							new RecipeBuilder("results")
//									.type("thermal:pulverizer")
//									.group("emendatusenigmatica:compat_recipe")
//									.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(false)
//											.tag(EETags.MATERIAL_INGOT.apply(material.getId())))
//									.fieldFloat("energy_mod", 0.5f)
//									.addOutput(builder -> builder
//											.stackWithCount(EERegistrar.dustMap.get(material.getId()).get(), 1)
//									)
//									.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ingot_pulverizer/" + material.getId()));
//						}
//						// Gem to Dust
//						if (processedType.contains("gem")) {
//							new RecipeBuilder("results")
//									.type("thermal:pulverizer")
//									.group("emendatusenigmatica:compat_recipe")
//									.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(false)
//											.tag(EETags.MATERIAL_GEM.apply(material.getId())))
//									.addOutput(builder -> builder
//											.stackWithCount(EERegistrar.dustMap.get(material.getId()).get(), 1)
//									)
//									.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_gem_pulverizer/" + material.getId()));
//						}
//					}
//
//					// INDUCTION SMELTER
//					for (CompatModel compat : registry.getCompat()) {
//						if (compat.getId().equals(material.getId()) && material.isModded()) {
//							for (CompatModel.CompatRecipesModel recipe : compat.getRecipes()) {
//								if (recipe.getMod().equals("thermal") && recipe.getMachine().equals("induction_smelter")) {
//									for (CompatModel.CompatValuesModel value : recipe.getValues()) {
//										if (value.getType().equals("ore") && processedType.contains("ore")) {
//											// Metal Ore to Ingot
//											if (processedType.contains("ingot") && material.getProperties().getMaterialType().equals("metal")) {
//												new RecipeBuilder("results")
//														.type("thermal:smelter")
//														.group("emendatusenigmatica:compat_recipe")
//														.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(false)
//																.tag(EETags.MATERIAL_ORE.apply(material.getId())))
//														.addOutput(builder -> builder
//																.stackWithoutCount(EERegistrar.ingotMap.get(material.getId()).get(), 1.0f)
//																.stacksWithCombinedChance(value.getOutput())
//														)
//														.fieldFloat("experience", 0.2f)
//														.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore_induction/" + material.getId()));
//											}
//
//											// Gem Ore to Gem
//											if (material.getProperties().getMaterialType().equals("gem")) {
//												new RecipeBuilder("results")
//														.type("thermal:smelter")
//														.group("emendatusenigmatica:compat_recipe")
//														.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(false)
//																.tag(EETags.MATERIAL_ORE.apply(material.getId())))
//														.addOutput(builder -> builder
//																.stackWithoutCount((processedType.contains("gem") ? EERegistrar.gemMap.get(material.getId()).get() : material.getOreDrop().getDefaultItemDropAsItem()), 1.0f)
//																.stacksWithCombinedChance(value.getOutput())
//														)
//														.fieldFloat("experience", 0.5f)
//														.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_ore_induction/" + material.getId()));
//											}
//										}
//										// Alloy
//										if (value.getType().equals("alloy") && processedType.contains("ingot") && material.getProperties().getMaterialType().equals("alloy")) {
//											new RecipeBuilder("results")
//													.type("thermal:smelter")
//													.group("emendatusenigmatica:compat_recipe")
//													.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true)
//															.stacksWithCount(value.getInput())
//													)
//													.fieldInt("energy", 8000)
//													.addOutput(builder -> builder
//															.stack(EERegistrar.ingotMap.get(material.getId()).get())
//															.stacksWithCount(value.getOutput())
//													)
//													.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_alloy_induction/" + material.getId()));
//										}
//									}
//								}
//							}
//						}
//					}
//					// Dust to Ingot
//					if (processedType.contains("ingot") && processedType.contains("dust") && material.isModded()) {
//						new RecipeBuilder("results")
//								.type("thermal:smelter")
//								.group("emendatusenigmatica:compat_recipe")
//								.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(false)
//										.tag(EETags.MATERIAL_DUST.apply(material.getId())))
//								.fieldFloat("energy_mod", 0.5f)
//								.addOutput(builder -> builder
//										.stackWithCount(EERegistrar.ingotMap.get(material.getId()).get(), 1)
//								)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_dust_induction/" + material.getId()));
//					}
//
//					// MULTISERVO PRESS
//					if (processedType.contains("raw") && material.isModded()) {
//						// Raw Block to Raw Material
//						new RecipeBuilder("results")
//								.type("thermal:press")
//								.group("emendatusenigmatica:compat_recipe")
//								.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true)
//										.stack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal:press_unpacking_die")))
//										.stack(EERegistrar.rawBlockItemMap.get(material.getId()).get())
//								)
//								.fieldInt("energy", 400)
//								.addOutput(builder -> builder
//										.stackWithCount(EERegistrar.rawMap.get(material.getId()).get(), 9)
//								)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "raw/from_raw_block_press/" + material.getId()));
//						// Raw Material to Raw Block
//						new RecipeBuilder("results")
//								.type("thermal:press")
//								.group("emendatusenigmatica:compat_recipe")
//								.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true)
//										.stack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal:press_packing_3x3_die")))
//										.stackWithCount(EERegistrar.rawMap.get(material.getId()).get(), 9)
//								)
//								.fieldInt("energy", 400)
//								.addOutput(builder -> builder
//										.stack(EERegistrar.rawBlockItemMap.get(material.getId()).get())
//								)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "raw_block/from_raw_press/" + material.getId()));
//					}
//					if (processedType.contains("storage_block") && material.isModded()) {
//						// Ingot
//						if (processedType.contains("ingot")) {
//							// Storage Block to Ingot
//							new RecipeBuilder("results")
//									.type("thermal:press")
//									.group("emendatusenigmatica:compat_recipe")
//									.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true)
//											.stack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal:press_unpacking_die")))
//											.stack(EERegistrar.storageBlockItemMap.get(material.getId()).get())
//									)
//									.fieldInt("energy", 400)
//									.addOutput(builder -> builder
//											.stackWithCount(EERegistrar.ingotMap.get(material.getId()).get(), 9)
//									)
//									.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_storage_block_press/" + material.getId()));
//							// Ingot to Storage Block
//							new RecipeBuilder("results")
//									.type("thermal:press")
//									.group("emendatusenigmatica:compat_recipe")
//									.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true)
//											.stack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal:press_packing_3x3_die")))
//											.stackWithCount(EERegistrar.ingotMap.get(material.getId()).get(), 9)
//									)
//									.fieldInt("energy", 400)
//									.addOutput(builder -> builder
//											.stack(EERegistrar.storageBlockItemMap.get(material.getId()).get())
//									)
//									.save(consumer, new ResourceLocation(Reference.MOD_ID, "storage_block/from_ingot_press/" + material.getId()));
//						}
//						// Gem
//						if (processedType.contains("gem")) {
//							if (material.getProperties().getBlockRecipeType() == 9) {
//								// Storage Block to Gem - x9
//								new RecipeBuilder("results")
//										.type("thermal:press")
//										.group("emendatusenigmatica:compat_recipe")
//										.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true)
//												.stack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal:press_unpacking_die")))
//												.stack(EERegistrar.storageBlockItemMap.get(material.getId()).get())
//										)
//										.fieldInt("energy", 400)
//										.addOutput(builder -> builder
//												.stackWithCount(EERegistrar.gemMap.get(material.getId()).get(), 9)
//										)
//										.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_storage_block_press/9x/" + material.getId()));
//								// Gem to Storage Block - x9
//								new RecipeBuilder("results")
//										.type("thermal:press")
//										.group("emendatusenigmatica:compat_recipe")
//										.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true)
//												.stack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal:press_packing_3x3_die")))
//												.stackWithCount(EERegistrar.gemMap.get(material.getId()).get(), 9)
//										)
//										.fieldInt("energy", 400)
//										.addOutput(builder -> builder
//												.stack(EERegistrar.storageBlockItemMap.get(material.getId()).get())
//										)
//										.save(consumer, new ResourceLocation(Reference.MOD_ID, "storage_block/from_gem_press/9x/" + material.getId()));
//							}
//							if (material.getProperties().getBlockRecipeType() == 4) {
//								// Storage Block to Gem - x4
//								new RecipeBuilder("results")
//										.type("thermal:press")
//										.group("emendatusenigmatica:compat_recipe")
//										.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true)
//												.stack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal:press_unpacking_die")))
//												.stack(EERegistrar.storageBlockItemMap.get(material.getId()).get())
//										)
//										.fieldInt("energy", 400)
//										.addOutput(builder -> builder
//												.stackWithCount(EERegistrar.gemMap.get(material.getId()).get(), 4)
//										)
//										.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_storage_block_press/4x/" + material.getId()));
//								// Gem to Storage Block - x4
//								new RecipeBuilder("results")
//										.type("thermal:press")
//										.group("emendatusenigmatica:compat_recipe")
//										.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true)
//												.stack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal:press_packing_2x2_die")))
//												.stackWithCount(EERegistrar.gemMap.get(material.getId()).get(), 4)
//										)
//										.fieldInt("energy", 400)
//										.addOutput(builder -> builder
//												.stack(EERegistrar.storageBlockItemMap.get(material.getId()).get())
//										)
//										.save(consumer, new ResourceLocation(Reference.MOD_ID, "storage_block/from_gem_press/4x/" + material.getId()));
//							}
//						}
//					}
//					if (processedType.contains("nugget") && processedType.contains("ingot") && material.isModded()) {
//						// Ingot to Nuggets
//						new RecipeBuilder("results")
//								.type("thermal:press")
//								.group("emendatusenigmatica:compat_recipe")
//								.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true)
//										.stack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal:press_unpacking_die")))
//										.stack(EERegistrar.ingotMap.get(material.getId()).get())
//								)
//								.fieldInt("energy", 400)
//								.addOutput(builder -> builder
//										.stackWithCount(EERegistrar.nuggetMap.get(material.getId()).get(), 9)
//								)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "nugget/from_ingot_press/" + material.getId()));
//						// Nuggets to Ingot
//						new RecipeBuilder("results")
//								.type("thermal:press")
//								.group("emendatusenigmatica:compat_recipe")
//								.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true)
//										.stack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal:press_packing_3x3_die")))
//										.stackWithCount(EERegistrar.nuggetMap.get(material.getId()).get(), 9)
//								)
//								.fieldInt("energy", 400)
//								.addOutput(builder -> builder
//										.stack(EERegistrar.ingotMap.get(material.getId()).get())
//								)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_nugget_press/" + material.getId()));
//					}
//					if (processedType.contains("gear") && material.isModded()) {
//						// Ingot to Gear
//						if (processedType.contains("ingot")) {
//							new RecipeBuilder("results")
//									.type("thermal:press")
//									.group("emendatusenigmatica:compat_recipe")
//									.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true)
//											.stack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal:press_gear_die")))
//											.tagWithCount(EETags.MATERIAL_INGOT.apply(material.getId()), 4)
//									)
//									.addOutput(builder -> builder
//											.stack(EERegistrar.gearMap.get(material.getId()).get())
//									)
//									.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_ingot_press/" + material.getId()));
//						}
//						// Gem to Gear
//						if (processedType.contains("gem")) {
//							new RecipeBuilder("results")
//									.type("thermal:press")
//									.group("emendatusenigmatica:compat_recipe")
//									.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true)
//											.stack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("thermal:press_gear_die")))
//											.tagWithCount(EETags.MATERIAL_GEM.apply(material.getId()), 4)
//									)
//									.addOutput(builder -> builder
//											.stack(EERegistrar.gearMap.get(material.getId()).get())
//									)
//									.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_gem_press/" + material.getId()));
//						}
//					}
//					if (processedType.contains("plate") && material.isModded()) {
//						// Ingot to Plate
//						if (processedType.contains("ingot")) {
//							new RecipeBuilder("results")
//									.type("thermal:press")
//									.group("emendatusenigmatica:compat_recipe")
//									.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true)
//											.tag(EETags.MATERIAL_INGOT.apply(material.getId()))
//									)
//									.addOutput(builder -> builder
//											.stack(EERegistrar.plateMap.get(material.getId()).get())
//									)
//									.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_ingot_press/" + material.getId()));
//						}
//						// Gem to Plate
//						if (processedType.contains("gem")) {
//							new RecipeBuilder("results")
//									.type("thermal:press")
//									.group("emendatusenigmatica:compat_recipe")
//									.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true)
//											.tag(EETags.MATERIAL_GEM.apply(material.getId()))
//									)
//									.addOutput(builder -> builder
//											.stack(EERegistrar.plateMap.get(material.getId()).get())
//									)
//									.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_gem_press/" + material.getId()));
//						}
//					}
//				}
//			}
//		}
//
//		@Override
//		public String getName() {
//			return "Emendatus Enigmatica Thermal Recipes";
//		}
//	}
//}