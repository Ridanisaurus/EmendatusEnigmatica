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
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ForgeItemTagsProvider;

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

		// Ingot from Dust
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_DUST.apply("iron")), Items.GOLD_INGOT, 0.7F, 200)
				.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
				.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_dust/smelting/iron"));
		SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_DUST.apply("iron")), Items.GOLD_INGOT, 0.7F, 100)
				.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
				.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_dust/blasting/iron"));

		SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_DUST.apply("gold")), Items.GOLD_INGOT, 0.7F, 200)
				.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
				.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_dust/smelting/gold"));
		SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_DUST.apply("gold")), Items.GOLD_INGOT, 0.7F, 100)
				.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
				.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_dust/blasting/gold"));

		// Ore Smelting & Blasting
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("coal")), Items.COAL, 0.7F, 200)
				.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
				.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_ore/smelting/coal"));
		SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("coal")), Items.COAL, 0.7F, 100)
				.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
				.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_ore/blasting/coal"));

		SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("iron")), Items.IRON_INGOT, 0.7F, 200)
				.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
				.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/smelting/iron"));
		SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("iron")), Items.IRON_INGOT, 0.7F, 100)
				.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
				.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/blasting/iron"));

		SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("gold")), Items.GOLD_INGOT, 0.7F, 200)
				.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
				.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/smelting/gold"));
		SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("gold")), Items.GOLD_INGOT, 0.7F, 100)
				.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
				.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/blasting/gold"));

		SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("diamond")), Items.DIAMOND, 0.7F, 200)
				.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
				.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_ore/smelting/diamond"));
		SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("diamond")), Items.DIAMOND, 0.7F, 100)
				.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
				.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_ore/blasting/diamond"));

		SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("emerald")), Items.EMERALD, 0.7F, 200)
				.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
				.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_ore/smelting/emerald"));
		SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("emerald")), Items.EMERALD, 0.7F, 100)
				.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
				.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_ore/blasting/emerald"));

		SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("lapis")), Items.LAPIS_LAZULI, 0.7F, 200)
				.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
				.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_ore/smelting/lapis"));
		SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("lapis")), Items.LAPIS_LAZULI, 0.7F, 100)
				.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
				.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_ore/blasting/lapis"));

		SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("redstone")), Items.REDSTONE, 0.7F, 200)
				.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
				.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_ore/smelting/redstone"));
		SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("redstone")), Items.REDSTONE, 0.7F, 100)
				.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
				.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_ore/blasting/redstone"));

		for (MaterialModel material : EELoader.MATERIALS) {
			List<String> processedType = material.getProcessedType();

			// Ingot from Block
			if (processedType.contains("storage_block") && processedType.contains("ingot")) {
				ShapelessRecipeBuilder.shapeless(EERegistrar.ingotMap.get(material.getId()).get(), 9)
						.requires(EETags.MATERIAL_STORAGE_BLOCK.apply(material.getId()))
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_block/" + material.getId()));
			}

			// Ingot from Nugget
			if (processedType.contains("nugget") && processedType.contains("ingot")) {
				ShapedRecipeBuilder.shaped(EERegistrar.ingotMap.get(material.getId()).get())
						.define('#', EETags.MATERIAL_NUGGET.apply(material.getId()))
						.pattern("###")
						.pattern("###")
						.pattern("###")
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_nugget/" + material.getId()));
			}

			// Nugget from Ingot
			if (processedType.contains("ingot") && processedType.contains("nugget")) {
				ShapelessRecipeBuilder.shapeless(EERegistrar.nuggetMap.get(material.getId()).get(), 9)
						.requires(EETags.MATERIAL_INGOT.apply(material.getId()))
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "nugget/from_ingot/" + material.getId()));
			}

			// Ingot from Dust
			if (processedType.contains("dust") && processedType.contains("ingot")) {
				SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_DUST.apply(material.getId())),
								EERegistrar.ingotMap.get(material.getId()).get(), 0.7F, 200)
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_dust/smelting/" + material.getId()));
				SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_DUST.apply(material.getId())),
								EERegistrar.ingotMap.get(material.getId()).get(), 0.7F, 100)
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_dust/blasting/" + material.getId()));
			}

			// Block from Ingot
			if (processedType.contains("ingot") && processedType.contains("storage_block")) {
				ShapedRecipeBuilder.shaped(EERegistrar.storageBlockItemMap.get(material.getId()).get())
						.define('#', EETags.MATERIAL_INGOT.apply(material.getId()))
						.pattern("###")
						.pattern("###")
						.pattern("###")
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "block/from_ingot/" + material.getId()));
			}
			// TODO [RID] Look into moving the block recipe identifier to its own field instead of processedType
			// Block from Gem x4
			if (processedType.contains("gem") && processedType.contains("storage_block") && processedType.contains("4x_block_recipe")) {
				ShapedRecipeBuilder.shaped(EERegistrar.storageBlockItemMap.get(material.getId()).get())
						.define('#', EETags.MATERIAL_GEM.apply(material.getId()))
						.pattern("##")
						.pattern("##")
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "block/from_gem/4x/" + material.getId()));
			}
			// Block from Gem x9
			if (processedType.contains("gem") && processedType.contains("storage_block") && !processedType.contains("4x_block_recipe")) {
				ShapedRecipeBuilder.shaped(EERegistrar.storageBlockItemMap.get(material.getId()).get())
						.define('#', EETags.MATERIAL_GEM.apply(material.getId()))
						.pattern("###")
						.pattern("###")
						.pattern("###")
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "block/from_gem/9x/" + material.getId()));
			}
			// Gem from Block x4
			if (processedType.contains("gem") && processedType.contains("storage_block") && processedType.contains("4x_block_recipe")) {
				ShapelessRecipeBuilder.shapeless(EERegistrar.gemMap.get(material.getId()).get(), 4)
						.requires(EETags.MATERIAL_STORAGE_BLOCK.apply(material.getId()))
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_block/4x/" + material.getId()));
			}
			// Gem from Block x9
			if (processedType.contains("gem") && processedType.contains("storage_block") && !processedType.contains("4x_block_recipe")) {
				ShapelessRecipeBuilder.shapeless(EERegistrar.gemMap.get(material.getId()).get(), 9)
						.requires(EETags.MATERIAL_STORAGE_BLOCK.apply(material.getId()))
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_block/9x/" + material.getId()));
			}

			// TODO Requires review
			// RawBlock from Raw 
			if (processedType.contains("raw")) {
				ShapedRecipeBuilder.shaped(EERegistrar.rawBlockItemMap.get(material.getId()).get())
						.define('#', EETags.MATERIAL_RAW.apply(material.getId()))
						.pattern("###")
						.pattern("###")
						.pattern("###")
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "block/from_raw_material/" + material.getId()));
			}
			// Raw from RawBlock
			if (processedType.contains("raw")) {
				ShapelessRecipeBuilder.shapeless(EERegistrar.rawMap.get(material.getId()).get(), 9)
						.requires(EETags.MATERIAL_RAW_STORAGE_BLOCK.apply(material.getId()))
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "raw_material/from_raw_storage_block/" + material.getId()));
			}

			// Dust from Ore
			if (processedType.contains("ore") && processedType.contains("dust")) {
				ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
						.requires(EETags.MATERIAL_ORE.apply(material.getId()))
						.requires(EERegistrar.ENIGMATIC_HAMMER.get())
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore/" + material.getId()));
			}

			// Plate from Ingot
			if (processedType.contains("ingot") && processedType.contains("plate")) {
				ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
						.requires(EETags.MATERIAL_INGOT.apply(material.getId()))
						.requires(EERegistrar.ENIGMATIC_HAMMER.get())
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_ingot/" + material.getId()));
			}

			// Plate from Gem
			if (processedType.contains("gem") && processedType.contains("plate")) {
				ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
						.requires(EETags.MATERIAL_GEM.apply(material.getId()))
						.requires(EERegistrar.ENIGMATIC_HAMMER.get())
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_gem/" + material.getId()));
			}

			// Gear from Ingot
			if (processedType.contains("ingot") && processedType.contains("gear")) {
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

			// Gear from Gem
			if (processedType.contains("gem") && processedType.contains("gear")) {
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

			// Rod from Ingot
			if (processedType.contains("ingot") && processedType.contains("rod")) {
				ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get(), 2)
						.define('I', EETags.MATERIAL_INGOT.apply(material.getId()))
						.pattern(" I ")
						.pattern(" I ")
						.pattern("   ")
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_ingot/" + material.getId()));
			}
			// Rod from Gem
			if (processedType.contains("gem") && processedType.contains("rod")) {
				ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get(), 2)
						.define('G', EETags.MATERIAL_GEM.apply(material.getId()))
						.pattern(" G ")
						.pattern(" G ")
						.pattern("   ")
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_gem/" + material.getId()));
			}


			// Gears, Rods and Plates
			if (material.getId().equals("iron")) {
				ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
						.define('I', EETags.MATERIAL_INGOT.apply("iron"))
						.define('N', EETags.MATERIAL_NUGGET.apply("iron"))
						.pattern(" I ")
						.pattern("INI")
						.pattern(" I ")
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_ingot/iron"));

				ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
						.define('I', EETags.MATERIAL_INGOT.apply("iron"))
						.pattern(" I ")
						.pattern(" I ")
						.pattern("   ")
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_ingot/iron"));

				ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
						.requires(EETags.MATERIAL_INGOT.apply("iron"))
						.requires(EERegistrar.ENIGMATIC_HAMMER.get())
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_ingot/iron"));
			}
			if (material.getId().equals("gold")) {
				ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
						.define('I', EETags.MATERIAL_INGOT.apply("iron"))
						.define('N', EETags.MATERIAL_NUGGET.apply("gold"))
						.pattern(" I ")
						.pattern("INI")
						.pattern(" I ")
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_ingot/gold"));

				ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
						.define('I', EETags.MATERIAL_INGOT.apply("gold"))
						.pattern(" I ")
						.pattern(" I ")
						.pattern("   ")
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_ingot/gold"));

				ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
						.requires(EETags.MATERIAL_INGOT.apply("gold"))
						.requires(EERegistrar.ENIGMATIC_HAMMER.get())
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_ingot/gold"));
			}
			if (material.getId().equals("diamond")) {
				ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
						.define('G', EETags.MATERIAL_GEM.apply("diamond"))
						.define('N', EETags.MATERIAL_NUGGET.apply("iron"))
						.pattern(" G ")
						.pattern("GNG")
						.pattern(" G ")
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_gem/diamond"));

				ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
						.define('G', EETags.MATERIAL_GEM.apply("diamond"))
						.pattern(" G ")
						.pattern(" G ")
						.pattern("   ")
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_gem/diamond"));

				ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
						.requires(EETags.MATERIAL_GEM.apply("diamond"))
						.requires(EERegistrar.ENIGMATIC_HAMMER.get())
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_gem/diamond"));
			}
			if (material.getId().equals("emerald")) {
				ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
						.define('G', EETags.MATERIAL_GEM.apply("emerald"))
						.define('N', EETags.MATERIAL_NUGGET.apply("iron"))
						.pattern(" G ")
						.pattern("GNG")
						.pattern(" G ")
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_gem/emerald"));

				ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
						.define('G', EETags.MATERIAL_GEM.apply("emerald"))
						.pattern(" G ")
						.pattern(" G ")
						.pattern("   ")
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_gem/emerald"));

				ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
						.requires(EETags.MATERIAL_GEM.apply("emerald"))
						.requires(EERegistrar.ENIGMATIC_HAMMER.get())
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_gem/emerald"));
			}
			if (material.getId().equals("lapis")) {
				ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
						.define('G', EETags.MATERIAL_GEM.apply("lapis"))
						.define('N', EETags.MATERIAL_NUGGET.apply("iron"))
						.pattern(" G ")
						.pattern("GNG")
						.pattern(" G ")
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_gem/lapis"));

				ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
						.define('G', EETags.MATERIAL_GEM.apply("lapis"))
						.pattern(" G ")
						.pattern(" G ")
						.pattern("   ")
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_gem/lapis"));

				ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
						.requires(EETags.MATERIAL_GEM.apply("lapis"))
						.requires(EERegistrar.ENIGMATIC_HAMMER.get())
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_gem/lapis"));
			}
			if (material.getId().equals("nether_quartz")) {
				ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
						.define('G', EETags.MATERIAL_GEM.apply("quartz"))
						.define('N', EETags.MATERIAL_NUGGET.apply("iron"))
						.pattern(" G ")
						.pattern("GNG")
						.pattern(" G ")
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_gem/nether_quartz"));
			}

			// Dust from Ore
			if (material.getId().equals("nether_quartz")) {
				ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
						.requires(EETags.MATERIAL_ORE.apply("quartz"))
						.requires(EERegistrar.ENIGMATIC_HAMMER.get())
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
						.group(Reference.MOD_ID)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore/nether_quartz"));
			}

			// Ore Smelting & Blasting
			if (processedType.contains("ingot") && processedType.contains("ore")) {
				SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply(material.getId())),
								EERegistrar.ingotMap.get(material.getId()).get(), 0.7F, 200)
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/smelting/" + material.getId() + "_" + stratum.getId()));
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/smelting/" + material.getId()));

				SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply(material.getId())),
								EERegistrar.ingotMap.get(material.getId()).get(), 0.7F, 100)
						.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/blasting/" + material.getId() + "_" + stratum.getId()));
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/blasting/" + material.getId()));
			}
		}
	}

	@Override
	public String getName() {
		return "Emendatus Enigmatica Recipes";
	}
}