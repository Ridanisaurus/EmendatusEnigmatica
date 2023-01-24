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

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.blocks.BasicWaxedBlock;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EETags;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.*;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class RecipesGen extends RecipeProvider {

	private final EmendatusDataRegistry registry;

	public RecipesGen(DataGenerator gen, EmendatusDataRegistry registry) {
		super(gen);
		this.registry = registry;
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

		for (MaterialModel material : registry.getMaterials()) {
			List<String> processedType = material.getProcessedTypes();
			if (material.isModded()) {
				if (processedType.contains("ingot")) {
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
						// Waxed
						if (material.getProperties().hasOxidization()) {
							ShapelessRecipeBuilder.shapeless(EERegistrar.waxedStorageBlockItemMap.get(material.getId()).get())
									.requires(EERegistrar.storageBlockItemMap.get(material.getId()).get())
									.requires(Items.HONEYCOMB)
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "waxed/from_block/" + material.getId() + "_block"));
							ShapelessRecipeBuilder.shapeless(EERegistrar.waxedExposedBlockItemMap.get(material.getId()).get())
									.requires(EERegistrar.exposedBlockItemMap.get(material.getId()).get())
									.requires(Items.HONEYCOMB)
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "waxed/from_block/exposed_" + material.getId()));
							ShapelessRecipeBuilder.shapeless(EERegistrar.waxedWeatheredBlockItemMap.get(material.getId()).get())
									.requires(EERegistrar.weatheredBlockItemMap.get(material.getId()).get())
									.requires(Items.HONEYCOMB)
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "waxed/from_block/weathered_" + material.getId()));
							ShapelessRecipeBuilder.shapeless(EERegistrar.waxedOxidizedBlockItemMap.get(material.getId()).get())
									.requires(EERegistrar.oxidizedBlockItemMap.get(material.getId()).get())
									.requires(Items.HONEYCOMB)
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "waxed/from_block/oxidized_" + material.getId()));
						}
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
								.pattern("I")
								.pattern("I")
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
					if (processedType.contains("helmet")) {
						// Helmet from Ingot
						ShapedRecipeBuilder.shaped(EERegistrar.helmetMap.get(material.getId()).get())
								.define('I', EETags.MATERIAL_INGOT.apply(material.getId()))
								.pattern("III")
								.pattern("I I")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "helmet/from_ingot/" + material.getId()));
					}
					if (processedType.contains("chestplate")) {
						// Chestplate from Ingot
						ShapedRecipeBuilder.shaped(EERegistrar.chestplateMap.get(material.getId()).get())
								.define('I', EETags.MATERIAL_INGOT.apply(material.getId()))
								.pattern("I I")
								.pattern("III")
								.pattern("III")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "chestplate/from_ingot/" + material.getId()));
					}
					if (processedType.contains("leggings")) {
						// Leggings from Ingot
						ShapedRecipeBuilder.shaped(EERegistrar.leggingsMap.get(material.getId()).get())
								.define('I', EETags.MATERIAL_INGOT.apply(material.getId()))
								.pattern("III")
								.pattern("I I")
								.pattern("I I")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "leggings/from_ingot/" + material.getId()));
					}
					if (processedType.contains("boots")) {
						// Boots from Ingot
						ShapedRecipeBuilder.shaped(EERegistrar.bootsMap.get(material.getId()).get())
								.define('I', EETags.MATERIAL_INGOT.apply(material.getId()))
								.pattern("I I")
								.pattern("I I")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "boots/from_ingot/" + material.getId()));
					}
					if (processedType.contains("shield")) {
						// Shield from Ingot
						UpgradeRecipeBuilder.smithing(
										Ingredient.of(Items.SHIELD),
										Ingredient.of(EETags.MATERIAL_INGOT.apply(material.getId())),
										EERegistrar.shieldMap.get(material.getId()).get())
								.unlocks("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "shield/from_ingot/" + material.getId()));
					}
					if (processedType.contains("sword")) {
						// Sword from Ingot
						ShapedRecipeBuilder.shaped(EERegistrar.swordMap.get(material.getId()).get())
								.define('I', EETags.MATERIAL_INGOT.apply(material.getId()))
								.define('#', Items.STICK)
								.pattern("I")
								.pattern("I")
								.pattern("#")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "sword/from_ingot/" + material.getId()));
					}
					if (processedType.contains("pickaxe")) {
						// Pickaxe from Ingot
						ShapedRecipeBuilder.shaped(EERegistrar.pickaxeMap.get(material.getId()).get())
								.define('I', EETags.MATERIAL_INGOT.apply(material.getId()))
								.define('#', Items.STICK)
								.pattern("III")
								.pattern(" # ")
								.pattern(" # ")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "pickaxe/from_ingot/" + material.getId()));
					}
					if (processedType.contains("axe")) {
						// Axe from Ingot
						ShapedRecipeBuilder.shaped(EERegistrar.axeMap.get(material.getId()).get())
								.define('I', EETags.MATERIAL_INGOT.apply(material.getId()))
								.define('#', Items.STICK)
								.pattern("II")
								.pattern("I#")
								.pattern(" #")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "axe/from_ingot/" + material.getId()));
					}
					if (processedType.contains("shovel")) {
						// Shovel from Ingot
						ShapedRecipeBuilder.shaped(EERegistrar.shovelMap.get(material.getId()).get())
								.define('I', EETags.MATERIAL_INGOT.apply(material.getId()))
								.define('#', Items.STICK)
								.pattern("I")
								.pattern("#")
								.pattern("#")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "shovel/from_ingot/" + material.getId()));
					}
					if (processedType.contains("hoe")) {
						// Hoe from Ingot
						ShapedRecipeBuilder.shaped(EERegistrar.hoeMap.get(material.getId()).get())
								.define('I', EETags.MATERIAL_INGOT.apply(material.getId()))
								.define('#', Items.STICK)
								.pattern("II")
								.pattern(" #")
								.pattern(" #")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "hoe/from_ingot/" + material.getId()));
					}
					if (processedType.contains("paxel") && processedType.contains("pickaxe") && processedType.contains("axe") && processedType.contains("shovel")) {
						// Paxel from Ingot
						ShapedRecipeBuilder.shaped(EERegistrar.paxelMap.get(material.getId()).get())
								.define('P', EERegistrar.pickaxeMap.get(material.getId()).get())
								.define('A', EERegistrar.axeMap.get(material.getId()).get())
								.define('S', EERegistrar.shovelMap.get(material.getId()).get())
								.define('#', Items.STICK)
								.pattern("PAS")
								.pattern(" # ")
								.pattern(" # ")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "paxel/from_ingot/" + material.getId()));
					}
				}
				if (processedType.contains("gem")) {
					if (processedType.contains("storage_block")) {
						if (material.getProperties().getBlockRecipeType() == 4) {
							// Block from Gem x4
							ShapedRecipeBuilder.shaped(EERegistrar.storageBlockItemMap.get(material.getId()).get())
									.define('#', EETags.MATERIAL_GEM.apply(material.getId()))
									.pattern("##")
									.pattern("##")
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "block/from_gem/" + material.getId()));
							// Gem from Block x4
							ShapelessRecipeBuilder.shapeless(EERegistrar.gemMap.get(material.getId()).get(), 4)
									.requires(EETags.MATERIAL_STORAGE_BLOCK.apply(material.getId()))
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_block/" + material.getId()));
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
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "block/from_gem/" + material.getId()));
							// Gem from Block x9
							ShapelessRecipeBuilder.shapeless(EERegistrar.gemMap.get(material.getId()).get(), 9)
									.requires(EETags.MATERIAL_STORAGE_BLOCK.apply(material.getId()))
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_block/" + material.getId()));
						}
						// Waxed
						if (material.getProperties().hasOxidization()) {
							ShapelessRecipeBuilder.shapeless(EERegistrar.waxedStorageBlockItemMap.get(material.getId()).get())
									.requires(EERegistrar.storageBlockItemMap.get(material.getId()).get())
									.requires(Items.HONEYCOMB)
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "waxed/from_block/" + material.getId() + "_block"));
							ShapelessRecipeBuilder.shapeless(EERegistrar.waxedExposedBlockItemMap.get(material.getId()).get())
									.requires(EERegistrar.exposedBlockItemMap.get(material.getId()).get())
									.requires(Items.HONEYCOMB)
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "waxed/from_block/exposed_" + material.getId()));
							ShapelessRecipeBuilder.shapeless(EERegistrar.waxedWeatheredBlockItemMap.get(material.getId()).get())
									.requires(EERegistrar.weatheredBlockItemMap.get(material.getId()).get())
									.requires(Items.HONEYCOMB)
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "waxed/from_block/weathered_" + material.getId()));
							ShapelessRecipeBuilder.shapeless(EERegistrar.waxedOxidizedBlockItemMap.get(material.getId()).get())
									.requires(EERegistrar.oxidizedBlockItemMap.get(material.getId()).get())
									.requires(Items.HONEYCOMB)
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "waxed/from_block/oxidized_" + material.getId()));
						}
					}
					if (processedType.contains("plate")) {
						// Plate from Gem
						ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
								.requires(EETags.MATERIAL_GEM.apply(material.getId()))
								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_gem/" + material.getId()));
					}
					if (processedType.contains("gear")) {
						// Gear from Gem
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
					if (processedType.contains("rod")) {
						// Rod from Gem
						ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get(), 2)
								.define('G', EETags.MATERIAL_GEM.apply(material.getId()))
								.pattern("G")
								.pattern("G")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_gem/" + material.getId()));
					}
					if (processedType.contains("helmet")) {
						// Helmet from Gem
						ShapedRecipeBuilder.shaped(EERegistrar.helmetMap.get(material.getId()).get())
								.define('G', EETags.MATERIAL_GEM.apply(material.getId()))
								.pattern("GGG")
								.pattern("G G")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "helmet/from_gem/" + material.getId()));
					}
					if (processedType.contains("chestplate")) {
						// Chestplate from Gem
						ShapedRecipeBuilder.shaped(EERegistrar.chestplateMap.get(material.getId()).get())
								.define('G', EETags.MATERIAL_GEM.apply(material.getId()))
								.pattern("G G")
								.pattern("GGG")
								.pattern("GGG")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "chestplate/from_gem/" + material.getId()));
					}
					if (processedType.contains("leggings")) {
						// Leggings from Gem
						ShapedRecipeBuilder.shaped(EERegistrar.leggingsMap.get(material.getId()).get())
								.define('G', EETags.MATERIAL_GEM.apply(material.getId()))
								.pattern("GGG")
								.pattern("G G")
								.pattern("G G")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "leggings/from_gem/" + material.getId()));
					}
					if (processedType.contains("boots")) {
						// Boots from Gem
						ShapedRecipeBuilder.shaped(EERegistrar.bootsMap.get(material.getId()).get())
								.define('G', EETags.MATERIAL_GEM.apply(material.getId()))
								.pattern("G G")
								.pattern("G G")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "boots/from_gem/" + material.getId()));
					}
					if (processedType.contains("shield")) {
						// Shield from Ingot
						UpgradeRecipeBuilder.smithing(
										Ingredient.of(Items.SHIELD),
										Ingredient.of(EETags.MATERIAL_GEM.apply(material.getId())),
										EERegistrar.shieldMap.get(material.getId()).get())
								.unlocks("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "shield/from_gem/" + material.getId()));
					}
					if (processedType.contains("sword")) {
						// Sword from Gem
						ShapedRecipeBuilder.shaped(EERegistrar.swordMap.get(material.getId()).get())
								.define('G', EETags.MATERIAL_GEM.apply(material.getId()))
								.define('#', Items.STICK)
								.pattern("G")
								.pattern("G")
								.pattern("#")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "sword/from_gem/" + material.getId()));
					}
					if (processedType.contains("pickaxe")) {
						// Pickaxe from Gem
						ShapedRecipeBuilder.shaped(EERegistrar.pickaxeMap.get(material.getId()).get())
								.define('G', EETags.MATERIAL_GEM.apply(material.getId()))
								.define('#', Items.STICK)
								.pattern("GGG")
								.pattern(" # ")
								.pattern(" # ")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "pickaxe/from_gem/" + material.getId()));
					}
					if (processedType.contains("axe")) {
						// Axe from Gem
						ShapedRecipeBuilder.shaped(EERegistrar.axeMap.get(material.getId()).get())
								.define('G', EETags.MATERIAL_GEM.apply(material.getId()))
								.define('#', Items.STICK)
								.pattern("GG")
								.pattern("G#")
								.pattern(" #")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "axe/from_gem/" + material.getId()));
					}
					if (processedType.contains("shovel")) {
						// Shovel from Gem
						ShapedRecipeBuilder.shaped(EERegistrar.shovelMap.get(material.getId()).get())
								.define('G', EETags.MATERIAL_GEM.apply(material.getId()))
								.define('#', Items.STICK)
								.pattern("G")
								.pattern("#")
								.pattern("#")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "shovel/from_gem/" + material.getId()));
					}
					if (processedType.contains("hoe")) {
						// Hoe from Gem
						ShapedRecipeBuilder.shaped(EERegistrar.hoeMap.get(material.getId()).get())
								.define('G', EETags.MATERIAL_GEM.apply(material.getId()))
								.define('#', Items.STICK)
								.pattern("GG")
								.pattern(" #")
								.pattern(" #")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "hoe/from_gem/" + material.getId()));
					}
					if (processedType.contains("paxel") && processedType.contains("pickaxe") && processedType.contains("axe") && processedType.contains("shovel")) {
						// Paxel from Ingot
						ShapedRecipeBuilder.shaped(EERegistrar.paxelMap.get(material.getId()).get())
								.define('P', EERegistrar.pickaxeMap.get(material.getId()).get())
								.define('A', EERegistrar.axeMap.get(material.getId()).get())
								.define('S', EERegistrar.shovelMap.get(material.getId()).get())
								.define('#', Items.STICK)
								.pattern("PAS")
								.pattern(" # ")
								.pattern(" # ")
								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
								.group(Reference.MOD_ID)
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "paxel/from_gem/" + material.getId()));
					}
				}
				if (processedType.contains("raw")) {
					// Raw Block from Raw Material
					ShapedRecipeBuilder.shaped(EERegistrar.rawBlockItemMap.get(material.getId()).get())
							.define('#', EETags.MATERIAL_RAW.apply(material.getId()))
							.pattern("###")
							.pattern("###")
							.pattern("###")
							.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
							.group(Reference.MOD_ID)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "raw/block/from_material/" + material.getId()));
					// Raw Material from Raw Block
					ShapelessRecipeBuilder.shapeless(EERegistrar.rawMap.get(material.getId()).get(), 9)
							.requires(EETags.MATERIAL_RAW_STORAGE_BLOCK.apply(material.getId()))
							.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
							.group(Reference.MOD_ID)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "raw/material/from_block/" + material.getId()));
				}
				if (processedType.contains("dust")) {
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
				if (processedType.contains("cluster")) {
					// Spyglass
					ShapedRecipeBuilder.shaped(Items.SPYGLASS)
							.define('#', EERegistrar.clusterShardMap.get(material.getId()).get())
							.define('X', EETags.MATERIAL_INGOT.apply("copper"))
							.pattern(" # ")
							.pattern(" X ")
							.pattern(" X ")
							.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
							.group(Reference.MOD_ID)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "spyglass/from_shard/" + material.getId()));
					// Tinted Glass
					ShapedRecipeBuilder.shaped(Blocks.TINTED_GLASS, 2)
							.define('G', Blocks.GLASS)
							.define('S', EERegistrar.clusterShardMap.get(material.getId()).get())
							.pattern(" S ")
							.pattern("SGS")
							.pattern(" S ")
							.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
							.group(Reference.MOD_ID)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "tinted_glass/from_shard/" + material.getId()));
					// Cluster Shard Block
					ShapedRecipeBuilder.shaped(EERegistrar.clusterShardBlockItemMap.get(material.getId()).get())
							.define('S', EERegistrar.clusterShardMap.get(material.getId()).get())
							.pattern("SS")
							.pattern("SS")
							.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
							.group(Reference.MOD_ID)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "block/from_shard/" + material.getId()));
				}
			}

			// Vanilla Compat
			if (material.isVanilla()) {
				if (material.getProperties().getMaterialType().equals("gem")) {
					Map<String, Item> vanillaGems = new HashMap<>();
					switch (material.getId()) {
						case "coal" -> vanillaGems.put(material.getId(), Items.COAL);
						case "diamond" -> vanillaGems.put(material.getId(), Items.DIAMOND);
						case "lapis" -> vanillaGems.put(material.getId(), Items.LAPIS_LAZULI);
						case "quartz" -> vanillaGems.put(material.getId(), Items.QUARTZ);
						case "redstone" -> vanillaGems.put(material.getId(), Items.REDSTONE);
					}

					for (Map.Entry<String, Item> mat : vanillaGems.entrySet()) {
						if (processedType.contains("plate")) {
							// Plate
							ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(mat.getKey()).get(), 1)
									.requires(mat.getValue())
									.requires(EERegistrar.ENIGMATIC_HAMMER.get())
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_gem/" + mat.getKey()));
						}
						if (processedType.contains("gear")) {
							// Gear
							ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(mat.getKey()).get())
									.define('G', mat.getValue())
									.define('N', Tags.Items.NUGGETS_IRON)
									.pattern(" G ")
									.pattern("GNG")
									.pattern(" G ")
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_gem/" + mat.getKey()));
						}
						if (processedType.contains("rod")) {
							// Rod
							ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(mat.getKey()).get())
									.define('G', mat.getValue())
									.pattern("G")
									.pattern("G")
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_gem/" + mat.getKey()));
						}
						if (processedType.contains("dust")) {
							// Dust from Ore
							ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(mat.getKey()).get(), 1)
									.requires(EETags.MATERIAL_ORE.apply(mat.getKey()))
									.requires(EERegistrar.ENIGMATIC_HAMMER.get())
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore/" + mat.getKey()));
						}
						if (processedType.contains("ore")) {
							// Ore Smelting
							SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply(mat.getKey())), mat.getValue(), 0.1F, 200)
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_ore/smelting/" + mat.getKey()));
							// Ore Blasting
							SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply(mat.getKey())), mat.getValue(), 0.1F, 100)
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_ore/blasting/" + mat.getKey()));
						}
						if (processedType.contains("helmet")) {
							// Helmet from Gem
							ShapedRecipeBuilder.shaped(EERegistrar.helmetMap.get(mat.getKey()).get())
									.define('G', mat.getValue())
									.pattern("GGG")
									.pattern("G G")
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "helmet/from_gem/" + mat.getKey()));
						}
						if (processedType.contains("chestplate")) {
							// Chestplate from Gem
							ShapedRecipeBuilder.shaped(EERegistrar.chestplateMap.get(mat.getKey()).get())
									.define('G', mat.getValue())
									.pattern("G G")
									.pattern("GGG")
									.pattern("GGG")
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "chestplate/from_gem/" + mat.getKey()));
						}
						if (processedType.contains("leggings")) {
							// Leggings from Gem
							ShapedRecipeBuilder.shaped(EERegistrar.leggingsMap.get(mat.getKey()).get())
									.define('G', mat.getValue())
									.pattern("GGG")
									.pattern("G G")
									.pattern("G G")
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "leggings/from_gem/" + mat.getKey()));
						}
						if (processedType.contains("boots")) {
							// Boots from Gem
							ShapedRecipeBuilder.shaped(EERegistrar.bootsMap.get(mat.getKey()).get())
									.define('G', mat.getValue())
									.pattern("G G")
									.pattern("G G")
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "boots/from_gem/" + mat.getKey()));
						}
						if (processedType.contains("shield")) {
							// Shield from Ingot
							UpgradeRecipeBuilder.smithing(
											Ingredient.of(Items.SHIELD),
											Ingredient.of(mat.getValue()),
											EERegistrar.shieldMap.get(mat.getKey()).get())
									.unlocks("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "shield/from_gem/" + mat.getKey()));
						}
						if (processedType.contains("sword")) {
							// Sword from Gem
							ShapedRecipeBuilder.shaped(EERegistrar.swordMap.get(mat.getKey()).get())
									.define('G', mat.getValue())
									.define('#', Items.STICK)
									.pattern("G")
									.pattern("G")
									.pattern("#")
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "sword/from_gem/" + mat.getKey()));
						}
						if (processedType.contains("pickaxe")) {
							// Pickaxe from Gem
							ShapedRecipeBuilder.shaped(EERegistrar.pickaxeMap.get(mat.getKey()).get())
									.define('G', mat.getValue())
									.define('#', Items.STICK)
									.pattern("GGG")
									.pattern(" # ")
									.pattern(" # ")
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "pickaxe/from_gem/" + mat.getKey()));
						}
						if (processedType.contains("axe")) {
							// Axe from Gem
							ShapedRecipeBuilder.shaped(EERegistrar.axeMap.get(mat.getKey()).get())
									.define('G', mat.getValue())
									.define('#', Items.STICK)
									.pattern("GG")
									.pattern("G#")
									.pattern(" #")
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "axe/from_gem/" + mat.getKey()));
						}
						if (processedType.contains("shovel")) {
							// Shovel from Gem
							ShapedRecipeBuilder.shaped(EERegistrar.shovelMap.get(mat.getKey()).get())
									.define('G', mat.getValue())
									.define('#', Items.STICK)
									.pattern("G")
									.pattern("#")
									.pattern("#")
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "shovel/from_gem/" + mat.getKey()));
						}
						if (processedType.contains("hoe")) {
							// Hoe from Gem
							ShapedRecipeBuilder.shaped(EERegistrar.hoeMap.get(mat.getKey()).get())
									.define('G', mat.getValue())
									.define('#', Items.STICK)
									.pattern("GG")
									.pattern(" #")
									.pattern(" #")
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "hoe/from_gem/" + mat.getKey()));
						}
						if (processedType.contains("paxel") && processedType.contains("pickaxe") && processedType.contains("axe") && processedType.contains("shovel")) {
							// Paxel from Ingot
							ShapedRecipeBuilder.shaped(EERegistrar.paxelMap.get(mat.getKey()).get())
									.define('P', EERegistrar.pickaxeMap.get(mat.getKey()).get())
									.define('A', EERegistrar.axeMap.get(mat.getKey()).get())
									.define('S', EERegistrar.shovelMap.get(mat.getKey()).get())
									.define('#', Items.STICK)
									.pattern("PAS")
									.pattern(" # ")
									.pattern(" # ")
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "paxel/from_gem/" + mat.getKey()));
						}
					}
				}
				if (material.getProperties().getMaterialType().equals("metal")) {
					Map<String, Item> vanillaMetals = new HashMap<>();
					switch (material.getId()) {
						case "copper" -> vanillaMetals.put(material.getId(), Items.COPPER_INGOT);
						case "gold" -> vanillaMetals.put(material.getId(), Items.GOLD_INGOT);
						case "iron" -> vanillaMetals.put(material.getId(), Items.IRON_INGOT);
					}
					for (Map.Entry<String, Item> mat : vanillaMetals.entrySet()) {
						if (processedType.contains("plate")) {
							// Plate
							ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(mat.getKey()).get(), 1)
									.requires(mat.getValue())
									.requires(EERegistrar.ENIGMATIC_HAMMER.get())
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_ingot/" + mat.getKey()));
						}
						if (processedType.contains("gear")) {
							// Gear
							ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(mat.getKey()).get())
									.define('I', mat.getValue())
									.define('N', Tags.Items.NUGGETS_IRON)
									.pattern(" I ")
									.pattern("INI")
									.pattern(" I ")
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_ingot/" + mat.getKey()));
						}
						if (processedType.contains("rod")) {
							// Rod
							ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(mat.getKey()).get())
									.define('I', mat.getValue())
									.pattern("I")
									.pattern("I")
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_ingot/" + mat.getKey()));
						}
						if (processedType.contains("dust")) {
							// Dust from Ore
							ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(mat.getKey()).get(), 1)
									.requires(EETags.MATERIAL_ORE.apply(mat.getKey()))
									.requires(EERegistrar.ENIGMATIC_HAMMER.get())
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore/" + mat.getKey()));
							// Dust from Raw
							ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(mat.getKey()).get(), 1)
									.requires(EETags.MATERIAL_RAW.apply(mat.getKey()))
									.requires(EERegistrar.ENIGMATIC_HAMMER.get())
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_raw/" + mat.getKey()));
							// Dust Smelting
							SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_DUST.apply(mat.getKey())), mat.getValue(), 0.7F, 200)
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_dust/smelting/" + mat.getKey()));
							// Dust Blasting
							SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_DUST.apply(mat.getKey())), mat.getValue(), 0.7F, 100)
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
										.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_dust/blasting/" + mat.getKey()));
						}
						if (processedType.contains("ore")) {
							// Ore Smelting
							SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply(mat.getKey())), mat.getValue(), 0.1F, 200)
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/smelting/" + mat.getKey()));
							// Ore Blasting
							SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply(mat.getKey())), mat.getValue(), 0.1F, 100)
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/blasting/" + mat.getKey()));
						}
						if (processedType.contains("helmet")) {
							// Helmet from Ingot
							ShapedRecipeBuilder.shaped(EERegistrar.helmetMap.get(mat.getKey()).get())
									.define('I', mat.getValue())
									.pattern("III")
									.pattern("I I")
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "helmet/from_ingot/" + mat.getKey()));
						}
						if (processedType.contains("chestplate")) {
							// Chestplate from Ingot
							ShapedRecipeBuilder.shaped(EERegistrar.chestplateMap.get(mat.getKey()).get())
									.define('I', mat.getValue())
									.pattern("I I")
									.pattern("III")
									.pattern("III")
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "chestplate/from_ingot/" + mat.getKey()));
						}
						if (processedType.contains("leggings")) {
							// Leggings from Ingot
							ShapedRecipeBuilder.shaped(EERegistrar.leggingsMap.get(mat.getKey()).get())
									.define('I', mat.getValue())
									.pattern("III")
									.pattern("I I")
									.pattern("I I")
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "leggings/from_ingot/" + mat.getKey()));
						}
						if (processedType.contains("boots")) {
							// Boots from Ingot
							ShapedRecipeBuilder.shaped(EERegistrar.bootsMap.get(mat.getKey()).get())
									.define('I', mat.getValue())
									.pattern("I I")
									.pattern("I I")
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "boots/from_ingot/" + mat.getKey()));
						}
						if (processedType.contains("shield")) {
							// Shield from Ingot
							UpgradeRecipeBuilder.smithing(
											Ingredient.of(Items.SHIELD),
											Ingredient.of(mat.getValue()),
											EERegistrar.shieldMap.get(mat.getKey()).get())
									.unlocks("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "shield/from_ingot/" + mat.getKey()));
						}
						if (processedType.contains("sword")) {
							// Sword from Ingot
							ShapedRecipeBuilder.shaped(EERegistrar.swordMap.get(mat.getKey()).get())
									.define('I', mat.getValue())
									.define('#', Items.STICK)
									.pattern("I")
									.pattern("I")
									.pattern("#")
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "sword/from_ingot/" + mat.getKey()));
						}
						if (processedType.contains("pickaxe")) {
							// Pickaxe from Ingot
							ShapedRecipeBuilder.shaped(EERegistrar.pickaxeMap.get(mat.getKey()).get())
									.define('I', mat.getValue())
									.define('#', Items.STICK)
									.pattern("III")
									.pattern(" # ")
									.pattern(" # ")
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "pickaxe/from_ingot/" + mat.getKey()));
						}
						if (processedType.contains("axe")) {
							// Axe from Ingot
							ShapedRecipeBuilder.shaped(EERegistrar.axeMap.get(mat.getKey()).get())
									.define('I', mat.getValue())
									.define('#', Items.STICK)
									.pattern("II")
									.pattern("I#")
									.pattern(" #")
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "axe/from_ingot/" + mat.getKey()));
						}
						if (processedType.contains("shovel")) {
							// Shovel from Ingot
							ShapedRecipeBuilder.shaped(EERegistrar.shovelMap.get(mat.getKey()).get())
									.define('I', mat.getValue())
									.define('#', Items.STICK)
									.pattern("I")
									.pattern("#")
									.pattern("#")
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "shovel/from_ingot/" + mat.getKey()));
						}
						if (processedType.contains("hoe")) {
							// Hoe from Ingot
							ShapedRecipeBuilder.shaped(EERegistrar.hoeMap.get(mat.getKey()).get())
									.define('I', mat.getValue())
									.define('#', Items.STICK)
									.pattern("II")
									.pattern(" #")
									.pattern(" #")
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "hoe/from_ingot/" + mat.getKey()));
						}
						if (processedType.contains("paxel") && processedType.contains("pickaxe") && processedType.contains("axe") && processedType.contains("shovel")) {
							// Paxel from Ingot
							ShapedRecipeBuilder.shaped(EERegistrar.paxelMap.get(mat.getKey()).get())
									.define('P', EERegistrar.pickaxeMap.get(mat.getKey()).get())
									.define('A', EERegistrar.axeMap.get(mat.getKey()).get())
									.define('S', EERegistrar.shovelMap.get(mat.getKey()).get())
									.define('#', Items.STICK)
									.pattern("PAS")
									.pattern(" # ")
									.pattern(" # ")
									.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
									.group(Reference.MOD_ID)
									.save(consumer, new ResourceLocation(Reference.MOD_ID, "paxel/from_ingot/" + mat.getKey()));
						}
					}
				}
			}
//			if (material.isVanilla()) {
//				if (material.getId().equals("coal")) {
//					if (processedType.contains("plate")) {
//						// Plate
//						ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
//								.requires(EETags.MATERIAL_GEM.apply("coal"))
//								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_gem/coal"));
//					}
//					if (processedType.contains("gear")) {
//						// Gear
//						ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
//								.define('I', EETags.MATERIAL_GEM.apply("coal"))
//								.define('N', EETags.MATERIAL_NUGGET.apply("iron"))
//								.pattern(" I ")
//								.pattern("INI")
//								.pattern(" I ")
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_gem/coal"));
//					}
//					if (processedType.contains("rod")) {
//						// Rod
//						ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
//								.define('I', EETags.MATERIAL_GEM.apply("coal"))
//								.pattern(" I ")
//								.pattern(" I ")
//								.pattern("   ")
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_gem/coal"));
//					}
//					if (processedType.contains("ore")) {
//						// Ore Smelting
//						SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("coal")), Items.COAL, 0.1F, 200)
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_ore/smelting/coal"));
//						// Ore Blasting
//						SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("coal")), Items.COAL, 0.1F, 100)
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_ore/blasting/coal"));
//					}
//					if (processedType.contains("dust")) {
//						// Dust from Ore
//						ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
//								.requires(EETags.MATERIAL_ORE.apply("coal"))
//								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore/coal"));
//					}
//				}
//				if (material.getId().equals("copper")) {
//					if (processedType.contains("plate")) {
//						// Plate
//						ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
//								.requires(EETags.MATERIAL_INGOT.apply("copper"))
//								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_ingot/copper"));
//					}
//					if (processedType.contains("gear")) {
//						// Gear
//						ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
//								.define('I', EETags.MATERIAL_INGOT.apply("copper"))
//								.define('N', EETags.MATERIAL_NUGGET.apply("iron"))
//								.pattern(" I ")
//								.pattern("INI")
//								.pattern(" I ")
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_ingot/copper"));
//					}
//					if (processedType.contains("rod")) {
//						// Rod
//						ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
//								.define('I', EETags.MATERIAL_INGOT.apply("copper"))
//								.pattern(" I ")
//								.pattern(" I ")
//								.pattern("   ")
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_ingot/copper"));
//					}
//					if (processedType.contains("dust")) {
//						// Dust Smelting
//						SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_DUST.apply("copper")), Items.COPPER_INGOT, 0.7F, 200)
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_dust/smelting/copper"));
//						// Dust Blasting
//						SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_DUST.apply("copper")), Items.COPPER_INGOT, 0.7F, 100)
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_dust/blasting/copper"));
//						// Dust from Ore
//						ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
//								.requires(EETags.MATERIAL_ORE.apply("copper"))
//								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore/copper"));
//						// Dust from Raw
//						ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
//								.requires(EETags.MATERIAL_RAW.apply("copper"))
//								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_raw/copper"));
//					}
//					if (processedType.contains("ore")) {
//						// Ore Smelting
//						SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("copper")), Items.COPPER_INGOT, 0.7F, 200)
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/smelting/copper"));
//						// Ore Blasting
//						SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("copper")), Items.COPPER_INGOT, 0.7F, 100)
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/blasting/copper"));
//					}
//				}
//				if (material.getId().equals("iron")) {
//					if (processedType.contains("plate")) {
//						// Plate
//						ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
//								.requires(EETags.MATERIAL_INGOT.apply("iron"))
//								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_ingot/iron"));
//					}
//					if (processedType.contains("gear")) {
//						// Gear
//						ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
//								.define('I', EETags.MATERIAL_INGOT.apply("iron"))
//								.define('N', EETags.MATERIAL_NUGGET.apply("iron"))
//								.pattern(" I ")
//								.pattern("INI")
//								.pattern(" I ")
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_ingot/iron"));
//					}
//					if (processedType.contains("rod")) {
//						// Rod
//						ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
//								.define('I', EETags.MATERIAL_INGOT.apply("iron"))
//								.pattern(" I ")
//								.pattern(" I ")
//								.pattern("   ")
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_ingot/iron"));
//					}
//					if (processedType.contains("dust")) {
//						// Dust Smelting
//						SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_DUST.apply("iron")), Items.IRON_INGOT, 0.7F, 200)
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_dust/smelting/iron"));
//						// Dust Blasting
//						SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_DUST.apply("iron")), Items.IRON_INGOT, 0.7F, 100)
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_dust/blasting/iron"));
//						// Dust from Ore
//						ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
//								.requires(EETags.MATERIAL_ORE.apply("iron"))
//								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore/iron"));
//						// Dust from Raw
//						ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
//								.requires(EETags.MATERIAL_RAW.apply("iron"))
//								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_raw/iron"));
//					}
//					if (processedType.contains("ore")) {
//						// Ore Smelting
//						SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("iron")), Items.IRON_INGOT, 0.7F, 200)
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/smelting/iron"));
//						// Ore Blasting
//						SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("iron")), Items.IRON_INGOT, 0.7F, 100)
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/blasting/iron"));
//					}
//				}
//				if (material.getId().equals("gold")) {
//					if (processedType.contains("plate")) {
//						// Plate
//						ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
//								.requires(EETags.MATERIAL_INGOT.apply("gold"))
//								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_ingot/gold"));
//					}
//					if (processedType.contains("gear")) {
//						// Gear
//						ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
//								.define('I', EETags.MATERIAL_INGOT.apply("gold"))
//								.define('N', EETags.MATERIAL_NUGGET.apply("iron"))
//								.pattern(" I ")
//								.pattern("INI")
//								.pattern(" I ")
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_ingot/gold"));
//					}
//					if (processedType.contains("rod")) {
//						// Rod
//						ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
//								.define('I', EETags.MATERIAL_INGOT.apply("gold"))
//								.pattern(" I ")
//								.pattern(" I ")
//								.pattern("   ")
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_ingot/gold"));
//					}
//					if (processedType.contains("dust")) {
//						// Dust Smelting
//						SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_DUST.apply("gold")), Items.GOLD_INGOT, 1.0F, 200)
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_dust/smelting/gold"));
//						// Dust Blasting
//						SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_DUST.apply("gold")), Items.GOLD_INGOT, 1.0F, 100)
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_dust/blasting/gold"));
//						// Dust from Ore
//						ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
//								.requires(EETags.MATERIAL_ORE.apply("gold"))
//								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore/gold"));
//						// Dust from Raw
//						ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
//								.requires(EETags.MATERIAL_RAW.apply("gold"))
//								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_raw/gold"));
//					}
//					if (processedType.contains("ore")) {
//						// Ore Smelting
//						SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("gold")), Items.GOLD_INGOT, 1.0F, 200)
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/smelting/gold"));
//						// Ore Blasting
//						SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("gold")), Items.GOLD_INGOT, 1.0F, 100)
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/blasting/gold"));
//					}
//				}
//				if (material.getId().equals("diamond")) {
//					if (processedType.contains("plate")) {
//						// Plate
//						ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
//								.requires(EETags.MATERIAL_GEM.apply("diamond"))
//								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_gem/diamond"));
//					}
//					if (processedType.contains("gear")) {
//						// Gear
//						ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
//								.define('I', EETags.MATERIAL_GEM.apply("diamond"))
//								.define('N', EETags.MATERIAL_NUGGET.apply("iron"))
//								.pattern(" I ")
//								.pattern("INI")
//								.pattern(" I ")
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_gem/diamond"));
//					}
//					if (processedType.contains("rod")) {
//						// Rod
//						ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
//								.define('I', EETags.MATERIAL_GEM.apply("diamond"))
//								.pattern(" I ")
//								.pattern(" I ")
//								.pattern("   ")
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_gem/diamond"));
//					}
//					if (processedType.contains("dust")) {
//						// Dust from Ore
//						ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
//								.requires(EETags.MATERIAL_ORE.apply("diamond"))
//								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore/diamond"));
//					}
//					if (processedType.contains("ore")) {
//						// Ore Smelting
//						SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("diamond")), Items.DIAMOND, 1.0F, 200)
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/smelting/diamond"));
//						// Ore Blasting
//						SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("diamond")), Items.DIAMOND, 1.0F, 100)
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/blasting/diamond"));
//					}
//				}
//				if (material.getId().equals("emerald")) {
//					if (processedType.contains("plate")) {
//						// Plate
//						ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
//								.requires(EETags.MATERIAL_GEM.apply("emerald"))
//								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_gem/emerald"));
//					}
//					if (processedType.contains("gear")) {
//						// Gear
//						ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
//								.define('I', EETags.MATERIAL_GEM.apply("emerald"))
//								.define('N', EETags.MATERIAL_NUGGET.apply("iron"))
//								.pattern(" I ")
//								.pattern("INI")
//								.pattern(" I ")
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_gem/emerald"));
//					}
//					if (processedType.contains("rod")) {
//						// Rod
//						ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
//								.define('I', EETags.MATERIAL_GEM.apply("emerald"))
//								.pattern(" I ")
//								.pattern(" I ")
//								.pattern("   ")
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_gem/emerald"));
//					}
//					if (processedType.contains("dust")) {
//						// Dust from Ore
//						ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
//								.requires(EETags.MATERIAL_ORE.apply("emerald"))
//								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore/emerald"));
//					}
//					if (processedType.contains("ore")) {
//						// Ore Smelting
//						SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("emerald")), Items.EMERALD, 1.0F, 200)
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/smelting/emerald"));
//						// Ore Blasting
//						SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("emerald")), Items.EMERALD, 1.0F, 100)
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/blasting/emerald"));
//					}
//				}
//				if (material.getId().equals("lapis")) {
//					if (processedType.contains("plate")) {
//						// Plate
//						ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
//								.requires(EETags.MATERIAL_GEM.apply("lapis"))
//								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_gem/lapis"));
//					}
//					if (processedType.contains("gear")) {
//						// Gear
//						ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
//								.define('I', EETags.MATERIAL_GEM.apply("lapis"))
//								.define('N', EETags.MATERIAL_NUGGET.apply("iron"))
//								.pattern(" I ")
//								.pattern("INI")
//								.pattern(" I ")
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_gem/lapis"));
//					}
//					if (processedType.contains("rod")) {
//						// Rod
//						ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
//								.define('I', EETags.MATERIAL_GEM.apply("lapis"))
//								.pattern(" I ")
//								.pattern(" I ")
//								.pattern("   ")
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_gem/lapis"));
//					}
//					if (processedType.contains("dust")) {
//						// Dust from Ore
//						ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
//								.requires(EETags.MATERIAL_ORE.apply("lapis"))
//								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore/lapis"));
//					}
//					if (processedType.contains("ore")) {
//						// Ore Smelting
//						SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("lapis")), Items.LAPIS_LAZULI, 0.2F, 200)
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/smelting/lapis"));
//						// Ore Blasting
//						SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("lapis")), Items.LAPIS_LAZULI, 0.2F, 100)
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/blasting/lapis"));
//					}
//				}
//				if (material.getId().equals("redstone")) {
//					if (processedType.contains("plate")) {
//						// Plate
//						ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
//								.requires(EETags.MATERIAL_GEM.apply("redstone"))
//								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_gem/redstone"));
//					}
//					if (processedType.contains("gear")) {
//						// Gear
//						ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
//								.define('I', EETags.MATERIAL_GEM.apply("redstone"))
//								.define('N', EETags.MATERIAL_NUGGET.apply("iron"))
//								.pattern(" I ")
//								.pattern("INI")
//								.pattern(" I ")
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_gem/redstone"));
//					}
//					if (processedType.contains("rod")) {
//						// Rod
//						ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
//								.define('I', EETags.MATERIAL_GEM.apply("redstone"))
//								.pattern(" I ")
//								.pattern(" I ")
//								.pattern("   ")
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_gem/redstone"));
//					}
//					if (processedType.contains("ore")) {
//						// Ore Smelting
//						SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("redstone")), Items.REDSTONE, 0.7F, 200)
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/smelting/redstone"));
//						// Ore Blasting
//						SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("redstone")), Items.REDSTONE, 0.7F, 100)
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/blasting/redstone"));
//					}
//				}
//				if (material.getId().equals("nether_quartz")) {
//					if (processedType.contains("plate")) {
//						// Plate
//						ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
//								.requires(EETags.MATERIAL_GEM.apply("quartz"))
//								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "plate/from_gem/nether_quartz"));
//					}
//					if (processedType.contains("gear")) {
//						// Gear
//						ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
//								.define('I', EETags.MATERIAL_GEM.apply("quartz"))
//								.define('N', EETags.MATERIAL_NUGGET.apply("iron"))
//								.pattern(" I ")
//								.pattern("INI")
//								.pattern(" I ")
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "gear/from_gem/nether_quartz"));
//					}
//					if (processedType.contains("rod")) {
//						// Rod
//						ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
//								.define('I', EETags.MATERIAL_GEM.apply("quartz"))
//								.pattern(" I ")
//								.pattern(" I ")
//								.pattern("   ")
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "rod/from_gem/nether_quartz"));
//					}
//					if (processedType.contains("dust")) {
//						// Dust from Ore
//						ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
//								.requires(EETags.MATERIAL_ORE.apply("quartz"))
//								.requires(EERegistrar.ENIGMATIC_HAMMER.get())
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.group(Reference.MOD_ID)
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore/nether_quartz"));
//					}
//					if (processedType.contains("ore")) {
//						// Ore Smelting
//						SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("quartz")), Items.REDSTONE, 0.2F, 200)
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/smelting/nether_quartz"));
//						// Ore Blasting
//						SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("quartz")), Items.REDSTONE, 0.2F, 100)
//								.unlockedBy("cobblestone", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.COBBLESTONE))
//								.save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot/from_ore/blasting/nether_quartz"));
//					}
//				}
//			}
		}
	}

	@Override
	public String getName() {
		return "Emendatus Enigmatica Recipes";
	}
}