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
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.*;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.util.List;
import java.util.function.Consumer;

public class RecipesGen extends RecipeProvider {

	public RecipesGen(DataGenerator gen) {
		super(gen);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

		ShapedRecipeBuilder.shaped(EERegistrar.ENIGMATIC_HAMMER::get)
				.pattern(" IN")
				.pattern(" SI")
				.pattern("S  ")
				.define('I', EETags.MATERIAL_INGOT.apply("iron"))
				.define('N', EETags.MATERIAL_NUGGET.apply("iron"))
				.define('S', EETags.MATERIAL_ROD.apply("wooden"))
				.group("emendatusenigmatica")
				.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
				.save(consumer);



		// Ore Smelting & Blasting
//		SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("coal")), Items.COAL, 0.7F, 200)
//				.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//				.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_ore/smelting/coal"));
//		SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("coal")), Items.COAL, 0.7F, 100)
//				.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//				.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_ore/blasting/coal"));


		for (MaterialModel material : EELoader.MATERIALS) {
			List<String> processedType = material.getProcessedTypes();

			if (processedType.contains("ingot") && material.isModded()) {
				if (processedType.contains("storage_block")) {
					// Ingot from Block
					ShapelessRecipeBuilder.shapeless(EERegistrar.ingotMap.get(material.getId()).get(), 9)
							.requires(EETags.MATERIAL_STORAGE_BLOCK.apply(material.getId()))
							.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
							.group(Reference.MOD_ID)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_block/" + material.getId()));
					// Block from Ingot
					ShapedRecipeBuilder.shaped(EERegistrar.storageBlockItemMap.get(material.getId()).get())
							.define('#', EETags.MATERIAL_INGOT.apply(material.getId()))
							.pattern("###")
							.pattern("###")
							.pattern("###")
							.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
							.group(Reference.MOD_ID)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "block/from_ingot/" + material.getId()));
				}
				if (processedType.contains("nugget")) {
					// Ingot from Nugget
					ShapedRecipeBuilder.shaped(EERegistrar.ingotMap.get(material.getId()).get())
							.define('#', EETags.MATERIAL_NUGGET.apply(material.getId()))
							.pattern("###")
							.pattern("###")
							.pattern("###")
							.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
							.group(Reference.MOD_ID)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_nugget/" + material.getId()));

					// Nugget from Ingot
					ShapelessRecipeBuilder.shapeless(EERegistrar.nuggetMap.get(material.getId()).get(), 9)
							.requires(EETags.MATERIAL_INGOT.apply(material.getId()))
							.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
							.group(Reference.MOD_ID)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "nugget/from_ingot/" + material.getId()));
				}
				if (processedType.contains("dust")) {
					// Ingot from Dust
					SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_DUST.apply(material.getId())),
									EERegistrar.ingotMap.get(material.getId()).get(), 0.5F, 200)
							.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_dust/smelting/" + material.getId()));
					SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_DUST.apply(material.getId())),
									EERegistrar.ingotMap.get(material.getId()).get(), 0.5F, 100)
							.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_dust/blasting/" + material.getId()));
				}
				if (processedType.contains("plate")) {
					// Plate from Ingot
					ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
							.requires(EETags.MATERIAL_INGOT.apply(material.getId()))
							.requires(EERegistrar.ENIGMATIC_HAMMER.get())
							.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
							.group(Reference.MOD_ID)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_ingot/" + material.getId()));
				}
				if (processedType.contains("gear")) {
					// Gear from Ingot
					ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
							.define('I', EETags.MATERIAL_INGOT.apply(material.getId()))
							.define('N', Tags.Items.NUGGETS_IRON)
							.pattern(" I ")
							.pattern("INI")
							.pattern(" I ")
							.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
							.group(Reference.MOD_ID)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_ingot/" + material.getId()));
				}
				if (processedType.contains("rod")) {
					// Rod from Ingot
					ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get(), 2)
							.define('I', EETags.MATERIAL_INGOT.apply(material.getId()))
							.pattern(" I ")
							.pattern(" I ")
							.pattern("   ")
							.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
							.group(Reference.MOD_ID)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_ingot/" + material.getId()));
				}
				if (processedType.contains("ore")) {
					// Ingot from Smelting Ore
					SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply(material.getId())),
									EERegistrar.ingotMap.get(material.getId()).get(), 1.0F, 200)
							.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/smelting/" + material.getId()));
					// Ingot from Blasting Ore
					SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply(material.getId())),
									EERegistrar.ingotMap.get(material.getId()).get(), 1.0F, 100)
							.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/blasting/" + material.getId()));
				}
				if (processedType.contains("raw")) {
					// Ingot from Smelting Raw Material
					SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_RAW.apply(material.getId())),
									EERegistrar.ingotMap.get(material.getId()).get(), 1.0F, 200)
							.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_raw/smelting/" + material.getId()));
					// Ingot from Blasting Raw Material
					SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_RAW.apply(material.getId())),
									EERegistrar.ingotMap.get(material.getId()).get(), 1.0F, 100)
							.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_raw/blasting/" + material.getId()));
				}
				if (processedType.contains("crushed_ore")) {
					// Ingot from Smelting Crushed Ore
					SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_CRUSHED_ORE.apply(material.getId())),
									EERegistrar.ingotMap.get(material.getId()).get(), 0.1F, 200)
							.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_crushed_ore/smelting/" + material.getId()));
					// Ingot from Blasting Crushed Ore
					SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_CRUSHED_ORE.apply(material.getId())),
									EERegistrar.ingotMap.get(material.getId()).get(), 0.1F, 100)
							.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_crushed_ore/blasting/" + material.getId()));
				}
			}

			if (processedType.contains("gem") && material.isModded()) {
				if (processedType.contains("storage_block")) {
					if (material.getProperties().getBlockRecipeType() == 4) {
						// Block from Gem x4
						ShapedRecipeBuilder.shaped(EERegistrar.storageBlockItemMap.get(material.getId()).get())
								.define('#', EETags.MATERIAL_GEM.apply(material.getId()))
								.pattern("##")
								.pattern("##")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "block/from_gem/4x/" + material.getId()));
						// Gem from Block x4
						ShapelessRecipeBuilder.shapeless(EERegistrar.gemMap.get(material.getId()).get(), 4)
								.requires(EETags.MATERIAL_STORAGE_BLOCK.apply(material.getId()))
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_block/4x/" + material.getId()));
					}
					if (material.getProperties().getBlockRecipeType() == 9) {
						// Block from Gem x9
						ShapedRecipeBuilder.shaped(EERegistrar.storageBlockItemMap.get(material.getId()).get())
								.define('#', EETags.MATERIAL_GEM.apply(material.getId()))
								.pattern("###")
								.pattern("###")
								.pattern("###")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "block/from_gem/9x/" + material.getId()));
						// Gem from Block x9
						ShapelessRecipeBuilder.shapeless(EERegistrar.gemMap.get(material.getId()).get(), 9)
								.requires(EETags.MATERIAL_STORAGE_BLOCK.apply(material.getId()))
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_block/9x/" + material.getId()));
					}
				}
				// Plate from Gem
				if (processedType.contains("plate")) {
					ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
							.requires(EETags.MATERIAL_GEM.apply(material.getId()))
							.requires(EERegistrar.ENIGMATIC_HAMMER.get())
							.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
							.group(Reference.MOD_ID)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_gem/" + material.getId()));
				}
				// Gear from Gem
				if (processedType.contains("gear")) {
					ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
							.define('G', EETags.MATERIAL_GEM.apply(material.getId()))
							.define('N', Tags.Items.NUGGETS_IRON)
							.pattern(" G ")
							.pattern("GNG")
							.pattern(" G ")
							.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
							.group(Reference.MOD_ID)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_gem/" + material.getId()));
				}

				// Rod from Gem
				if (processedType.contains("rod")) {
					ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get(), 2)
							.define('G', EETags.MATERIAL_GEM.apply(material.getId()))
							.pattern(" G ")
							.pattern(" G ")
							.pattern("   ")
							.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
							.group(Reference.MOD_ID)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_gem/" + material.getId()));
				}
			}

			if (processedType.contains("raw") && material.isModded()) {
				// Raw Block from Raw Material
				ShapedRecipeBuilder.shaped(EERegistrar.rawBlockItemMap.get(material.getId()).get())
						.define('#', EETags.MATERIAL_RAW.apply(material.getId()))
						.pattern("###")
						.pattern("###")
						.pattern("###")
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "block/from_raw_material/" + material.getId()));
				// Raw Material from Raw Block
				ShapelessRecipeBuilder.shapeless(EERegistrar.rawMap.get(material.getId()).get(), 9)
						.requires(EETags.MATERIAL_RAW_STORAGE_BLOCK.apply(material.getId()))
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "raw_material/from_raw_storage_block/" + material.getId()));
			}

			if (processedType.contains("dust") && material.isModded()) {
				if (processedType.contains("ore")) {
					// Dust from Ore
					ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
							.requires(EETags.MATERIAL_ORE.apply(material.getId()))
							.requires(EERegistrar.ENIGMATIC_HAMMER.get())
							.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
							.group(Reference.MOD_ID)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore/" + material.getId()));
				}
				if (processedType.contains("raw")) {
					// Dust from Raw
					ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
							.requires(EETags.MATERIAL_RAW.apply(material.getId()))
							.requires(EERegistrar.ENIGMATIC_HAMMER.get())
							.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
							.group(Reference.MOD_ID)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_raw/" + material.getId()));
				}
			}

			// Vanilla Compat

			if (material.isVanilla()) {
				if (material.getId().equals("coal")) {
					if (processedType.contains("plate")) {
						// Plate
						ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
								.requires(EETags.MATERIAL_GEM.apply("coal"))
								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_gem/coal"));
					}
					if (processedType.contains("gear")) {
						// Gear
						ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
								.define('I', EETags.MATERIAL_GEM.apply("coal"))
								.define('N', EETags.MATERIAL_NUGGET.apply("iron"))
								.pattern(" I ")
								.pattern("INI")
								.pattern(" I ")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_gem/coal"));
					}
					if (processedType.contains("rod")) {
						// Rod
						ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
								.define('I', EETags.MATERIAL_GEM.apply("coal"))
								.pattern(" I ")
								.pattern(" I ")
								.pattern("   ")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_gem/coal"));
					}
					if (processedType.contains("ore")) {
						// Ore Smelting
						SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("coal")), Items.COAL, 0.1F, 200)
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/smelting/coal"));
						// Ore Blasting
						SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("coal")), Items.COAL, 0.1F, 100)
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/blasting/coal"));
					}
					if (processedType.contains("dust")) {
						// Dust from Ore
						ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
								.requires(EETags.MATERIAL_ORE.apply("coal"))
								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore/coal"));
					}
				}

				if (material.getId().equals("copper")) {
					if (processedType.contains("plate")) {
						// Plate
						ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
								.requires(EETags.MATERIAL_INGOT.apply("copper"))
								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_ingot/copper"));
					}
					if (processedType.contains("gear")) {
						// Gear
						ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
								.define('I', EETags.MATERIAL_INGOT.apply("copper"))
								.define('N', EETags.MATERIAL_NUGGET.apply("iron"))
								.pattern(" I ")
								.pattern("INI")
								.pattern(" I ")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_ingot/copper"));
					}
					if (processedType.contains("rod")) {
						// Rod
						ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
								.define('I', EETags.MATERIAL_INGOT.apply("copper"))
								.pattern(" I ")
								.pattern(" I ")
								.pattern("   ")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_ingot/copper"));
					}
					if (processedType.contains("dust")) {
						// Dust Smelting
						SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_DUST.apply("copper")), Items.COPPER_INGOT, 0.7F, 200)
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_dust/smelting/copper"));
						// Dust Blasting
						SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_DUST.apply("copper")), Items.COPPER_INGOT, 0.7F, 100)
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_dust/blasting/copper"));
						// Dust from Ore
						ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
								.requires(EETags.MATERIAL_ORE.apply("copper"))
								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore/copper"));
						// Dust from Raw
						ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
								.requires(EETags.MATERIAL_RAW.apply("copper"))
								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_raw/copper"));
					}
					if (processedType.contains("ore")) {
						// Ore Smelting
						SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("copper")), Items.COPPER_INGOT, 0.7F, 200)
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/smelting/copper"));
						// Ore Blasting
						SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("copper")), Items.COPPER_INGOT, 0.7F, 100)
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/blasting/copper"));
					}
				}

				if (material.getId().equals("iron")) {
					if (processedType.contains("plate")) {
						// Plate
						ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
								.requires(EETags.MATERIAL_INGOT.apply("iron"))
								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_ingot/iron"));
					}
					if (processedType.contains("gear")) {
						// Gear
						ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
								.define('I', EETags.MATERIAL_INGOT.apply("iron"))
								.define('N', EETags.MATERIAL_NUGGET.apply("iron"))
								.pattern(" I ")
								.pattern("INI")
								.pattern(" I ")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_ingot/iron"));
					}
					if (processedType.contains("rod")) {
						// Rod
						ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
								.define('I', EETags.MATERIAL_INGOT.apply("iron"))
								.pattern(" I ")
								.pattern(" I ")
								.pattern("   ")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_ingot/iron"));
					}
					if (processedType.contains("dust")) {
						// Dust Smelting
						SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_DUST.apply("iron")), Items.IRON_INGOT, 0.7F, 200)
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_dust/smelting/iron"));
						// Dust Blasting
						SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_DUST.apply("iron")), Items.IRON_INGOT, 0.7F, 100)
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_dust/blasting/iron"));
						// Dust from Ore
						ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
								.requires(EETags.MATERIAL_ORE.apply("iron"))
								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore/iron"));
						// Dust from Raw
						ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
								.requires(EETags.MATERIAL_RAW.apply("iron"))
								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_raw/iron"));
					}
					if (processedType.contains("ore")) {
						// Ore Smelting
						SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("iron")), Items.IRON_INGOT, 0.7F, 200)
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/smelting/iron"));
						// Ore Blasting
						SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("iron")), Items.IRON_INGOT, 0.7F, 100)
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/blasting/iron"));
					}
				}

				if (material.getId().equals("gold")) {
					if (processedType.contains("plate")) {
						// Plate
						ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
								.requires(EETags.MATERIAL_INGOT.apply("gold"))
								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_ingot/gold"));
					}
					if (processedType.contains("gear")) {
						// Gear
						ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
								.define('I', EETags.MATERIAL_INGOT.apply("gold"))
								.define('N', EETags.MATERIAL_NUGGET.apply("iron"))
								.pattern(" I ")
								.pattern("INI")
								.pattern(" I ")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_ingot/gold"));
					}
					if (processedType.contains("rod")) {
						// Rod
						ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
								.define('I', EETags.MATERIAL_INGOT.apply("gold"))
								.pattern(" I ")
								.pattern(" I ")
								.pattern("   ")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_ingot/gold"));
					}
					if (processedType.contains("dust")) {
						// Dust Smelting
						SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_DUST.apply("gold")), Items.GOLD_INGOT, 1.0F, 200)
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_dust/smelting/gold"));
						// Dust Blasting
						SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_DUST.apply("gold")), Items.GOLD_INGOT, 1.0F, 100)
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_dust/blasting/gold"));
						// Dust from Ore
						ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
								.requires(EETags.MATERIAL_ORE.apply("gold"))
								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore/gold"));
						// Dust from Raw
						ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
								.requires(EETags.MATERIAL_RAW.apply("gold"))
								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_raw/gold"));
					}
					if (processedType.contains("ore")) {
						// Ore Smelting
						SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("gold")), Items.GOLD_INGOT, 1.0F, 200)
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/smelting/gold"));
						// Ore Blasting
						SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("gold")), Items.GOLD_INGOT, 1.0F, 100)
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/blasting/gold"));
					}
				}

				if (material.getId().equals("diamond")) {
					if (processedType.contains("plate")) {
						// Plate
						ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
								.requires(EETags.MATERIAL_GEM.apply("diamond"))
								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_gem/diamond"));
					}
					if (processedType.contains("gear")) {
						// Gear
						ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
								.define('I', EETags.MATERIAL_GEM.apply("diamond"))
								.define('N', EETags.MATERIAL_NUGGET.apply("iron"))
								.pattern(" I ")
								.pattern("INI")
								.pattern(" I ")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_gem/diamond"));
					}
					if (processedType.contains("rod")) {
						// Rod
						ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
								.define('I', EETags.MATERIAL_GEM.apply("diamond"))
								.pattern(" I ")
								.pattern(" I ")
								.pattern("   ")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_gem/diamond"));
					}
					if (processedType.contains("dust")) {
						// Dust from Ore
						ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
								.requires(EETags.MATERIAL_ORE.apply("diamond"))
								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore/diamond"));
					}
					if (processedType.contains("ore")) {
						// Ore Smelting
						SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("diamond")), Items.DIAMOND, 1.0F, 200)
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/smelting/diamond"));
						// Ore Blasting
						SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("diamond")), Items.DIAMOND, 1.0F, 100)
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/blasting/diamond"));
					}
				}

				if (material.getId().equals("emerald")) {
					if (processedType.contains("plate")) {
						// Plate
						ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
								.requires(EETags.MATERIAL_GEM.apply("emerald"))
								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_gem/emerald"));
					}
					if (processedType.contains("gear")) {
						// Gear
						ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
								.define('I', EETags.MATERIAL_GEM.apply("emerald"))
								.define('N', EETags.MATERIAL_NUGGET.apply("iron"))
								.pattern(" I ")
								.pattern("INI")
								.pattern(" I ")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_gem/emerald"));
					}
					if (processedType.contains("rod")) {
						// Rod
						ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
								.define('I', EETags.MATERIAL_GEM.apply("emerald"))
								.pattern(" I ")
								.pattern(" I ")
								.pattern("   ")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_gem/emerald"));
					}
					if (processedType.contains("dust")) {
						// Dust from Ore
						ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
								.requires(EETags.MATERIAL_ORE.apply("emerald"))
								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore/emerald"));
					}
					if (processedType.contains("ore")) {
						// Ore Smelting
						SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("emerald")), Items.EMERALD, 1.0F, 200)
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/smelting/emerald"));
						// Ore Blasting
						SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("emerald")), Items.EMERALD, 1.0F, 100)
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/blasting/emerald"));
					}
				}

				if (material.getId().equals("lapis")) {
					if (processedType.contains("plate")) {
						// Plate
						ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
								.requires(EETags.MATERIAL_GEM.apply("lapis"))
								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_gem/lapis"));
					}
					if (processedType.contains("gear")) {
						// Gear
						ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
								.define('I', EETags.MATERIAL_GEM.apply("lapis"))
								.define('N', EETags.MATERIAL_NUGGET.apply("iron"))
								.pattern(" I ")
								.pattern("INI")
								.pattern(" I ")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_gem/lapis"));
					}
					if (processedType.contains("rod")) {
						// Rod
						ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
								.define('I', EETags.MATERIAL_GEM.apply("lapis"))
								.pattern(" I ")
								.pattern(" I ")
								.pattern("   ")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_gem/lapis"));
					}
					if (processedType.contains("dust")) {
						// Dust from Ore
						ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
								.requires(EETags.MATERIAL_ORE.apply("lapis"))
								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore/lapis"));
					}
					if (processedType.contains("ore")) {
						// Ore Smelting
						SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("lapis")), Items.LAPIS_LAZULI, 0.2F, 200)
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/smelting/lapis"));
						// Ore Blasting
						SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("lapis")), Items.LAPIS_LAZULI, 0.2F, 100)
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/blasting/lapis"));
					}
				}

				if (material.getId().equals("redstone")) {
					if (processedType.contains("plate")) {
						// Plate
						ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
								.requires(EETags.MATERIAL_GEM.apply("redstone"))
								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_gem/redstone"));
					}
					if (processedType.contains("gear")) {
						// Gear
						ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
								.define('I', EETags.MATERIAL_GEM.apply("redstone"))
								.define('N', EETags.MATERIAL_NUGGET.apply("iron"))
								.pattern(" I ")
								.pattern("INI")
								.pattern(" I ")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_gem/redstone"));
					}
					if (processedType.contains("rod")) {
						// Rod
						ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
								.define('I', EETags.MATERIAL_GEM.apply("redstone"))
								.pattern(" I ")
								.pattern(" I ")
								.pattern("   ")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_gem/redstone"));
					}
					if (processedType.contains("ore")) {
						// Ore Smelting
						SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("redstone")), Items.REDSTONE, 0.7F, 200)
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/smelting/redstone"));
						// Ore Blasting
						SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("redstone")), Items.REDSTONE, 0.7F, 100)
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/blasting/redstone"));
					}
				}

				if (material.getId().equals("nether_quartz")) {
					if (processedType.contains("plate")) {
						// Plate
						ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
								.requires(EETags.MATERIAL_GEM.apply("quartz"))
								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_gem/nether_quartz"));
					}
					if (processedType.contains("gear")) {
						// Gear
						ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
								.define('I', EETags.MATERIAL_GEM.apply("quartz"))
								.define('N', EETags.MATERIAL_NUGGET.apply("iron"))
								.pattern(" I ")
								.pattern("INI")
								.pattern(" I ")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_gem/nether_quartz"));
					}
					if (processedType.contains("rod")) {
						// Rod
						ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
								.define('I', EETags.MATERIAL_GEM.apply("quartz"))
								.pattern(" I ")
								.pattern(" I ")
								.pattern("   ")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_gem/nether_quartz"));
					}
					if (processedType.contains("dust")) {
						// Dust from Ore
						ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
								.requires(EETags.MATERIAL_ORE.apply("quartz"))
								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore/nether_quartz"));
					}
					if (processedType.contains("ore")) {
						// Ore Smelting
						SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("quartz")), Items.REDSTONE, 0.2F, 200)
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/smelting/nether_quartz"));
						// Ore Blasting
						SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("quartz")), Items.REDSTONE, 0.2F, 100)
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/blasting/nether_quartz"));
					}
				}
			}
		}
	}

	@Override
	public String getName() {
		return "Emendatus Enigmatica Recipes";
	}
}