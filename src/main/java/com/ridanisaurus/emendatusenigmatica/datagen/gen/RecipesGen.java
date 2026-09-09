/*
 * MIT License
 *
 * Copyright (c) 2020-2026. Ridanisaurus
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
package com.ridanisaurus.emendatusenigmatica.datagen.gen;

import com.ridanisaurus.emendatusenigmatica.plugin.DataRegistry;
import com.ridanisaurus.emendatusenigmatica.plugin.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EETags;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class RecipesGen extends RecipeProvider {

	private final DataRegistry registry;

	public RecipesGen(@NotNull DataGenerator gen, DataRegistry registry, CompletableFuture<HolderLookup.Provider> providers) {
		super(gen.getPackOutput(), providers);
		this.registry = registry;
	}

	@Override
	public @NotNull String getName() {
		return "Emendatus Enigmatica: Recipes";
	}

	@Override
	protected void buildRecipes(@NotNull RecipeOutput out) {
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.ENIGMATIC_HAMMER::get)
			.pattern(" IN")
			.pattern(" SI")
			.pattern("S  ")
			.define('I', EETags.MATERIAL_INGOT.apply("iron"))
			.define('N', EETags.MATERIAL_NUGGET.apply("iron"))
			.define('S', EETags.MATERIAL_ROD.apply("wooden"))
			.group(Reference.MOD_ID)
			.unlockedBy("hammer_unlock", has(Items.IRON_INGOT))
			.save(out);

		var ingotTag = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "ingots/iron"));
		ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.SHIELD_TEMPLATE::get)
			.pattern("LNL")
			.pattern("PIP")
			.pattern("PPP")
			.define('L', TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace("logs")))
			.define('N', TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", "nuggets/iron")))
			.define('P', TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace("planks")))
			.define('I', ingotTag)
			.group(Reference.MOD_ID)
			.unlockedBy("template_unlock", has(ingotTag))
			.save(out);

		for(MaterialModel material : registry.getRegisteredMaterials()) {
			if (material.isModded()) {
                generateModdedMaterial(material, out);
            } else {
                generateVanillaMaterial(material, out);
            }
		}
	}

	private void generateModdedMaterial(MaterialModel model, RecipeOutput out) {
		List<String> processedType = model.getProcessedTypes();
		if (processedType.contains("ingot")) {
			TagKey<Item> baseIngotTag = EETags.MATERIAL_INGOT.apply(model.getId());
			if (processedType.contains("storage_block")) {
				if (processedType.contains("raw")) {
					// Raw Block from Raw Material
					ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EERegistrar.rawBlockItemMap.getValue(model))
						.define('#', EETags.MATERIAL_RAW.apply(model.getId()))
						.pattern("###")
						.pattern("###")
						.pattern("###")
						.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "raw/block/from_material/" + model.getId()));

					// Raw Material from Raw Block
					ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EERegistrar.rawMap.getValue(model), 9)
						.requires(EETags.MATERIAL_RAW_STORAGE_BLOCK.apply(model.getId()))
						.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "raw/material/from_block/" + model.getId()));
				}

				// Ingot from Block
				ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EERegistrar.ingotMap.getValue(model), 9)
					.requires(EETags.MATERIAL_STORAGE_BLOCK.apply(model.getId()))
					.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "ingot/from_block/" + model.getId()));

				// Block from Ingot
				ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EERegistrar.storageBlockItemMap.getValue(model))
					.define('#', baseIngotTag)
					.pattern("###")
					.pattern("###")
					.pattern("###")
					.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "block/from_ingot/" + model.getId()));

				// Waxed
				if (model.getProperties().hasOxidization()) {
					ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, EERegistrar.waxedStorageBlockItemMap.getValue(model))
						.requires(EERegistrar.storageBlockItemMap.getValue(model))
						.requires(Items.HONEYCOMB)
						.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "waxed/from_block/" + model.getId() + "_block"));

					ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, EERegistrar.waxedExposedBlockItemMap.getValue(model))
						.requires(EERegistrar.exposedBlockItemMap.getValue(model))
						.requires(Items.HONEYCOMB)
						.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "waxed/from_block/exposed_" + model.getId()));

					ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, EERegistrar.waxedWeatheredBlockItemMap.getValue(model))
						.requires(EERegistrar.weatheredBlockItemMap.getValue(model))
						.requires(Items.HONEYCOMB)
						.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "waxed/from_block/weathered_" + model.getId()));

					ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, EERegistrar.waxedOxidizedBlockItemMap.getValue(model))
						.requires(EERegistrar.oxidizedBlockItemMap.getValue(model))
						.requires(Items.HONEYCOMB)
						.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "waxed/from_block/oxidized_" + model.getId()));
				}
			}

			if (processedType.contains("nugget")) {
				// Ingot from Nugget
				ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EERegistrar.ingotMap.getValue(model))
					.define('#', EETags.MATERIAL_NUGGET.apply(model.getId()))
					.pattern("###")
					.pattern("###")
					.pattern("###")
					.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "ingot/from_nugget/" + model.getId()));

				// Nugget from Ingot
				ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EERegistrar.nuggetMap.getValue(model), 9)
					.requires(baseIngotTag)
					.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "nugget/from_ingot/" + model.getId()));
			}

			if (processedType.contains("dust")) {
				// Ingot from Dust
				SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_DUST.apply(model.getId())),
						RecipeCategory.MISC,
						EERegistrar.ingotMap.getValue(model), 0.5F, 200)
					.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "ingot/from_dust/smelting/" + model.getId()));

				SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_DUST.apply(model.getId())),
						RecipeCategory.MISC,
						EERegistrar.ingotMap.getValue(model), 0.5F, 100)
					.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "ingot/from_dust/blasting/" + model.getId()));
			}

			if (processedType.contains("plate")) {
				// Plate from Ingot
				ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EERegistrar.plateMap.getValue(model), 1)
					.requires(baseIngotTag)
					.requires(EERegistrar.ENIGMATIC_HAMMER.get())
					.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "plate/from_ingot/" + model.getId()));
			}

			if (processedType.contains("gear")) {
				// Gear from Ingot
				ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EERegistrar.gearMap.getValue(model))
					.define('I', baseIngotTag)
					.define('N', Tags.Items.NUGGETS_IRON)
					.pattern(" I ")
					.pattern("INI")
					.pattern(" I ")
					.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "gear/from_ingot/" + model.getId()));
			}

			if (processedType.contains("rod")) {
				// Rod from Ingot
				ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EERegistrar.rodMap.getValue(model), 2)
					.define('I', baseIngotTag)
					.pattern("I")
					.pattern("I")
					.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "rod/from_ingot/" + model.getId()));
			}

			if (processedType.contains("ore")) {
				// Ingot from Smelting Ore
				SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply(model.getId())),
						RecipeCategory.MISC,
						EERegistrar.ingotMap.getValue(model), 1.0F, 200)
					.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "ingot/from_ore/smelting/" + model.getId()));
				// Ingot from Blasting Ore
				SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply(model.getId())),
						RecipeCategory.MISC,
						EERegistrar.ingotMap.getValue(model), 1.0F, 100)
					.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "ingot/from_ore/blasting/" + model.getId()));
			}

			if (processedType.contains("raw")) {
				// Ingot from Smelting Raw Material
				SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_RAW.apply(model.getId())),
						RecipeCategory.MISC,
						EERegistrar.ingotMap.getValue(model), 1.0F, 200)
					.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "ingot/from_raw/smelting/" + model.getId()));
				// Ingot from Blasting Raw Material
				SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_RAW.apply(model.getId())),
						RecipeCategory.MISC,
						EERegistrar.ingotMap.getValue(model), 1.0F, 100)
					.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "ingot/from_raw/blasting/" + model.getId()));
			}

			if (processedType.contains("armor")) {
				// Helmet from Ingot
				ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.helmetMap.getValue(model))
					.define('I', baseIngotTag)
					.pattern("III")
					.pattern("I I")
					.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "helmet/from_ingot/" + model.getId()));
				// Chestplate from Ingot
				ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.chestplateMap.getValue(model))
					.define('I', baseIngotTag)
					.pattern("I I")
					.pattern("III")
					.pattern("III")
					.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "chestplate/from_ingot/" + model.getId()));
				// Leggings from Ingot
				ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.leggingsMap.getValue(model))
					.define('I', baseIngotTag)
					.pattern("III")
					.pattern("I I")
					.pattern("I I")
					.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "leggings/from_ingot/" + model.getId()));
				// Boots from Ingot
				ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.bootsMap.getValue(model))
					.define('I', baseIngotTag)
					.pattern("I I")
					.pattern("I I")
					.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "boots/from_ingot/" + model.getId()));
			}

			if (processedType.contains("shield")) {
				// Shield from Ingot
				SmithingTransformRecipeBuilder.smithing(
						Ingredient.of(EERegistrar.SHIELD_TEMPLATE),
						Ingredient.of(Items.SHIELD),
						Ingredient.of(baseIngotTag),
						RecipeCategory.TOOLS,
						EERegistrar.shieldMap.get(model).get())
					.unlocks(model.getId() + "_unlock", has(baseIngotTag))
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "shield/from_ingot/" + model.getId()));
			}

			if (processedType.contains("sword")) {
				// Sword from Ingot
				ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.swordMap.getValue(model))
					.define('I', baseIngotTag)
					.define('#', Items.STICK)
					.pattern("I")
					.pattern("I")
					.pattern("#")
					.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "sword/from_ingot/" + model.getId()));
			}

			if (processedType.contains("pickaxe")) {
				// Pickaxe from Ingot
				ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.pickaxeMap.getValue(model))
					.define('I', baseIngotTag)
					.define('#', Items.STICK)
					.pattern("III")
					.pattern(" # ")
					.pattern(" # ")
					.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "pickaxe/from_ingot/" + model.getId()));
			}

			if (processedType.contains("axe")) {
				// Axe from Ingot
				ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.axeMap.getValue(model))
					.define('I', baseIngotTag)
					.define('#', Items.STICK)
					.pattern("II")
					.pattern("I#")
					.pattern(" #")
					.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "axe/from_ingot/" + model.getId()));
			}

			if (processedType.contains("shovel")) {
				// Shovel from Ingot
				ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.shovelMap.getValue(model))
					.define('I', baseIngotTag)
					.define('#', Items.STICK)
					.pattern("I")
					.pattern("#")
					.pattern("#")
					.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "shovel/from_ingot/" + model.getId()));
			}

			if (processedType.contains("hoe")) {
				// Hoe from Ingot
				ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.hoeMap.getValue(model))
					.define('I', baseIngotTag)
					.define('#', Items.STICK)
					.pattern("II")
					.pattern(" #")
					.pattern(" #")
					.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "hoe/from_ingot/" + model.getId()));
			}

			if (processedType.contains("paxel") && processedType.contains("pickaxe") && processedType.contains("axe") && processedType.contains("shovel")) {
				// Paxel from Ingot
				ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.paxelMap.getValue(model))
					.define('P', EERegistrar.pickaxeMap.getValue(model))
					.define('A', EERegistrar.axeMap.getValue(model))
					.define('S', EERegistrar.shovelMap.getValue(model))
					.define('#', Items.STICK)
					.pattern("PAS")
					.pattern(" # ")
					.pattern(" # ")
					.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "paxel/from_ingot/" + model.getId()));
			}
		}

		// Gem recipes
		if (processedType.contains("gem")) {
			TagKey<Item> baseGemTag = EETags.MATERIAL_GEM.apply(model.getId());
			if (processedType.contains("storage_block")) {
				if (processedType.contains("raw")) {
					// Raw Block from Raw Material
					ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EERegistrar.rawBlockItemMap.getValue(model))
						.define('#', EETags.MATERIAL_RAW.apply(model.getId()))
						.pattern("###")
						.pattern("###")
						.pattern("###")
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "raw/block/from_material/" + model.getId()));

					// Raw Material from Raw Block
					ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EERegistrar.rawMap.getValue(model), 9)
						.requires(EETags.MATERIAL_RAW_STORAGE_BLOCK.apply(model.getId()))
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "raw/material/from_block/" + model.getId()));
				}

				if (model.getProperties().getBlockRecipeType() == 4) {
					// Block from Gem x4
					ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EERegistrar.storageBlockItemMap.getValue(model))
						.define('#', baseGemTag)
						.pattern("##")
						.pattern("##")
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "block/from_gem/" + model.getId()));

					//Gem from Block x4
					ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EERegistrar.gemMap.getValue(model), 4)
						.requires(EETags.MATERIAL_STORAGE_BLOCK.apply(model.getId()))
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "gem/from_block/" + model.getId()));
				}

				if (model.getProperties().getBlockRecipeType() == 9) {
					// Block from Gem x9
					ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EERegistrar.storageBlockItemMap.getValue(model))
						.define('#', baseGemTag)
						.pattern("###")
						.pattern("###")
						.pattern("###")
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "block/from_gem/" + model.getId()));

					// Gem from Block x9
					ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EERegistrar.gemMap.getValue(model), 9)
						.requires(EETags.MATERIAL_STORAGE_BLOCK.apply(model.getId()))
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "gem/from_block/" + model.getId()));
				}

				//Waxed
				if (model.getProperties().hasOxidization()) {
					ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, EERegistrar.waxedStorageBlockItemMap.getValue(model))
						.requires(EERegistrar.storageBlockItemMap.getValue(model))
						.requires(Items.HONEYCOMB)
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "waxed/from_block/" + model.getId() + "_block"));
					ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, EERegistrar.waxedExposedBlockItemMap.getValue(model))
						.requires(EERegistrar.exposedBlockItemMap.getValue(model))
						.requires(Items.HONEYCOMB)
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "waxed/from_block/exposed_" + model.getId()));
					ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, EERegistrar.waxedWeatheredBlockItemMap.getValue(model))
						.requires(EERegistrar.weatheredBlockItemMap.getValue(model))
						.requires(Items.HONEYCOMB)
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "waxed/from_block/weathered_" + model.getId()));
					ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, EERegistrar.waxedOxidizedBlockItemMap.getValue(model))
						.requires(EERegistrar.oxidizedBlockItemMap.getValue(model))
						.requires(Items.HONEYCOMB)
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "waxed/from_block/oxidized_" + model.getId()));
				}
			}

			if (processedType.contains("plate")) {
				// Plate from Gem
				ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EERegistrar.plateMap.getValue(model), 1)
					.requires(baseGemTag)
					.requires(EERegistrar.ENIGMATIC_HAMMER.get())
					.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "plate/from_gem/" + model.getId()));
			}

			if (processedType.contains("gear")) {
				// Gear from Gem
				ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EERegistrar.gearMap.getValue(model))
					.define('G', baseGemTag)
					.define('N', Tags.Items.NUGGETS_IRON)
					.pattern(" G ")
					.pattern("GNG")
					.pattern(" G ")
					.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "gear/from_gem/" + model.getId()));
			}

			if (processedType.contains("rod")) {
				// Rod from Gem
				ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EERegistrar.rodMap.getValue(model), 2)
					.define('G', baseGemTag)
					.pattern("G")
					.pattern("G")
					.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "rod/from_gem/" + model.getId()));
			}

			if (processedType.contains("armor")) {
				// Helmet from Gem
				ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.helmetMap.getValue(model))
					.define('G', baseGemTag)
					.pattern("GGG")
					.pattern("G G")
					.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "helmet/from_gem/" + model.getId()));
				// Chestplate from Gem
				ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.chestplateMap.getValue(model))
					.define('G', baseGemTag)
					.pattern("G G")
					.pattern("GGG")
					.pattern("GGG")
					.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "chestplate/from_gem/" + model.getId()));
				// Leggings from Gem
				ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.leggingsMap.getValue(model))
					.define('G', baseGemTag)
					.pattern("GGG")
					.pattern("G G")
					.pattern("G G")
					.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "leggings/from_gem/" + model.getId()));
				// Boots from Gem
				ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.bootsMap.getValue(model))
					.define('G', baseGemTag)
					.pattern("G G")
					.pattern("G G")
					.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "boots/from_gem/" + model.getId()));
			}

			if (processedType.contains("shield")) {
				// Shield from Gem
				SmithingTransformRecipeBuilder.smithing(
						Ingredient.of(EERegistrar.SHIELD_TEMPLATE),
						Ingredient.of(Items.SHIELD),
						Ingredient.of(baseGemTag),
						RecipeCategory.TOOLS,
						EERegistrar.shieldMap.get(model).get())
					.unlocks(model.getId() + "_unlock", has(baseGemTag))
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "shield/from_gem/" + model.getId()));

			}

			if (processedType.contains("sword")) {
				// Sword from Gem
				ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.swordMap.getValue(model))
					.define('G', baseGemTag)
					.define('#', Items.STICK)
					.pattern("G")
					.pattern("G")
					.pattern("#")
					.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "sword/from_gem/" + model.getId()));
			}

			if (processedType.contains("pickaxe")) {
				// Pickaxe from Gem
				ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.pickaxeMap.getValue(model))
					.define('G', baseGemTag)
					.define('#', Items.STICK)
					.pattern("GGG")
					.pattern(" # ")
					.pattern(" # ")
					.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "pickaxe/from_gem/" + model.getId()));
			}

			if (processedType.contains("axe")) {
				// Axe from Gem
				ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.axeMap.getValue(model))
					.define('G', baseGemTag)
					.define('#', Items.STICK)
					.pattern("GG")
					.pattern("G#")
					.pattern(" #")
					.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "axe/from_gem/" + model.getId()));
			}

			if (processedType.contains("shovel")) {
				// Shovel from Gem
				ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.shovelMap.getValue(model))
					.define('G', baseGemTag)
					.define('#', Items.STICK)
					.pattern("G")
					.pattern("#")
					.pattern("#")
					.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "shovel/from_gem/" + model.getId()));
			}

			if (processedType.contains("hoe")) {
				// Hoe from Gem
				ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.hoeMap.getValue(model))
					.define('G', baseGemTag)
					.define('#', Items.STICK)
					.pattern("GG")
					.pattern(" #")
					.pattern(" #")
					.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "hoe/from_gem/" + model.getId()));
			}

			if (processedType.contains("paxel") && processedType.contains("pickaxe") && processedType.contains("axe") && processedType.contains("shovel")) {
				// Paxel from Gem
				ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.paxelMap.getValue(model))
					.define('P', EERegistrar.pickaxeMap.getValue(model))
					.define('A', EERegistrar.axeMap.getValue(model))
					.define('S', EERegistrar.shovelMap.getValue(model))
					.define('#', Items.STICK)
					.pattern("PAS")
					.pattern(" # ")
					.pattern(" # ")
					.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "paxel/from_gem/" + model.getId()));
			}

			if (processedType.contains("dust")) {
				if (processedType.contains("ore")) {
					// Dust from Ore
					ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EERegistrar.dustMap.getValue(model), 1)
						.requires(EETags.MATERIAL_ORE.apply(model.getId()))
						.requires(EERegistrar.ENIGMATIC_HAMMER.get())
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "dust/from_ore/" + model.getId()));
				}

				if (processedType.contains("raw")) {
					// Dust from Raw
					ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EERegistrar.dustMap.getValue(model), 1)
						.requires(EETags.MATERIAL_RAW.apply(model.getId()))
						.requires(EERegistrar.ENIGMATIC_HAMMER.get())
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "dust/from_raw/" + model.getId()));
				}
			}

			if (processedType.contains("cluster")) {
				// Spyglass
				ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Items.SPYGLASS)
					.define('#', EERegistrar.clusterShardMap.getValue(model))
					.define('X', EETags.MATERIAL_INGOT.apply("copper"))
					.pattern(" # ")
					.pattern(" X ")
					.pattern(" X ")
					.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "spyglass/from_shard/" + model.getId()));
				// Tinted Glass
				ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.TINTED_GLASS, 2)
					.define('G', Blocks.GLASS)
					.define('S', EERegistrar.clusterShardMap.getValue(model))
					.pattern(" S ")
					.pattern("SGS")
					.pattern(" S ")
					.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "tinted_glass/from_shard/" + model.getId()));
				// Cluster Shard Block
				ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EERegistrar.clusterShardBlockItemMap.getValue(model))
					.define('S', EERegistrar.clusterShardMap.getValue(model))
					.pattern("SS")
					.pattern("SS")
					.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
					.group(Reference.MOD_ID)
					.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "block/from_shard/" + model.getId()));
			}
		}
	}

	private void generateVanillaMaterial(MaterialModel model, RecipeOutput out) {
		List<String> processedType = model.getProcessedTypes();
		//TODO: Implement better vanilla compat :D?
		if (model.getProperties().getMaterialType().equals("gem")) {
			Map<String, Item> vanillaGems = new HashMap<>();
			switch (model.getId()) {
				case "coal" -> vanillaGems.put(model.getId(), Items.COAL);
				case "diamond" -> vanillaGems.put(model.getId(), Items.DIAMOND);
				case "lapis" -> vanillaGems.put(model.getId(), Items.LAPIS_LAZULI);
				case "quartz" -> vanillaGems.put(model.getId(), Items.QUARTZ);
				case "redstone" -> vanillaGems.put(model.getId(), Items.REDSTONE);
			}

			for (Map.Entry<String, Item> mat : vanillaGems.entrySet()) {
				TagKey<Item> baseGemTag = EETags.MATERIAL_GEM.apply(model.getId());
				if (processedType.contains("plate")) {
					// Plate
					ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EERegistrar.plateMap.getValue(mat.getKey()), 1)
						.requires(mat.getValue())
						.requires(EERegistrar.ENIGMATIC_HAMMER.get())
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "plate/from_gem/" + mat.getKey()));
				}

				if (processedType.contains("gear")) {
					// Gear
					ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EERegistrar.gearMap.getValue(mat.getKey()))
						.define('G', mat.getValue())
						.define('N', Tags.Items.NUGGETS_IRON)
						.pattern(" G ")
						.pattern("GNG")
						.pattern(" G ")
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "gear/from_gem/" + mat.getKey()));
				}

				if (processedType.contains("rod")) {
					// Rod
					ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EERegistrar.rodMap.getValue(mat.getKey()))
						.define('G', mat.getValue())
						.pattern("G")
						.pattern("G")
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "rod/from_gem/" + mat.getKey()));
				}

				if (processedType.contains("dust")) {
					// Dust from Ore
					ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EERegistrar.dustMap.getValue(mat.getKey()), 1)
						.requires(EETags.MATERIAL_ORE.apply(mat.getKey()))
						.requires(EERegistrar.ENIGMATIC_HAMMER.get())
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "dust/from_ore/" + mat.getKey()));
				}

				if (processedType.contains("ore")) {
					// Ore Smelting
					SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply(mat.getKey())),
							RecipeCategory.MISC,
							mat.getValue(), 0.1F, 200)
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "gem/from_ore/smelting/" + mat.getKey()));

					// Ore Blasting
					SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply(mat.getKey())),
							RecipeCategory.MISC,
							mat.getValue(), 0.1F, 100)
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "gem/from_ore/blasting/" + mat.getKey()));
				}

				if (processedType.contains("armor")) {
					// Helmet from Gem
					ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.helmetMap.getValue(mat.getKey()))
						.define('G', mat.getValue())
						.pattern("GGG")
						.pattern("G G")
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "helmet/from_gem/" + mat.getKey()));
					// Chestplate from Gem
					ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.chestplateMap.getValue(mat.getKey()))
						.define('G', mat.getValue())
						.pattern("G G")
						.pattern("GGG")
						.pattern("GGG")
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "chestplate/from_gem/" + mat.getKey()));
					// Leggings from Gem
					ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.leggingsMap.getValue(mat.getKey()))
						.define('G', mat.getValue())
						.pattern("GGG")
						.pattern("G G")
						.pattern("G G")
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "leggings/from_gem/" + mat.getKey()));
					// Boots from Gem
					ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.bootsMap.getValue(mat.getKey()))
						.define('G', mat.getValue())
						.pattern("G G")
						.pattern("G G")
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "boots/from_gem/" + mat.getKey()));
				}

				if (processedType.contains("shield")) {
					// Shield from Ingot
					SmithingTransformRecipeBuilder.smithing(
							Ingredient.of(EERegistrar.SHIELD_TEMPLATE),
							Ingredient.of(Items.SHIELD),
							Ingredient.of(baseGemTag),
							RecipeCategory.TOOLS,
							EERegistrar.shieldMap.get(model).get())
						.unlocks(model.getId() + "_unlock", has(baseGemTag))
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "shield/from_gem/" + model.getId()));
				}

				if (processedType.contains("sword")) {
					// Sword from Gem
					ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.swordMap.getValue(mat.getKey()))
						.define('G', mat.getValue())
						.define('#', Items.STICK)
						.pattern("G")
						.pattern("G")
						.pattern("#")
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "sword/from_gem/" + mat.getKey()));
				}

				if (processedType.contains("pickaxe")) {
					// Pickaxe from Gem
					ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.pickaxeMap.getValue(mat.getKey()))
						.define('G', mat.getValue())
						.define('#', Items.STICK)
						.pattern("GGG")
						.pattern(" # ")
						.pattern(" # ")
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "pickaxe/from_gem/" + mat.getKey()));
				}

				if (processedType.contains("axe")) {
					// Axe from Gem
					ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.axeMap.getValue(mat.getKey()))
						.define('G', mat.getValue())
						.define('#', Items.STICK)
						.pattern("GG")
						.pattern("G#")
						.pattern(" #")
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "axe/from_gem/" + mat.getKey()));
				}

				if (processedType.contains("shovel")) {
					// Shovel from Gem
					ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.shovelMap.getValue(mat.getKey()))
						.define('G', mat.getValue())
						.define('#', Items.STICK)
						.pattern("G")
						.pattern("#")
						.pattern("#")
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "shovel/from_gem/" + mat.getKey()));
				}

				if (processedType.contains("hoe")) {
					// Hoe from Gem
					ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.hoeMap.getValue(mat.getKey()))
						.define('G', mat.getValue())
						.define('#', Items.STICK)
						.pattern("GG")
						.pattern(" #")
						.pattern(" #")
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "hoe/from_gem/" + mat.getKey()));
				}

				if (processedType.contains("paxel") && processedType.contains("pickaxe") && processedType.contains("axe") && processedType.contains("shovel")) {
					// Paxel from Ingot
					ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.paxelMap.getValue(mat.getKey()))
						.define('P', EERegistrar.pickaxeMap.getValue(mat.getKey()))
						.define('A', EERegistrar.axeMap.getValue(mat.getKey()))
						.define('S', EERegistrar.shovelMap.getValue(mat.getKey()))
						.define('#', Items.STICK)
						.pattern("PAS")
						.pattern(" # ")
						.pattern(" # ")
						.unlockedBy(model.getId() + "_unlock", has(baseGemTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "paxel/from_gem/" + mat.getKey()));
				}
			}
		}
		if (model.getProperties().getMaterialType().equals("metal")) {
			Map<String, Item> vanillaMetals = new HashMap<>();

			switch (model.getId()) {
				case "copper" -> vanillaMetals.put(model.getId(), Items.COPPER_INGOT);
				case "gold" -> vanillaMetals.put(model.getId(), Items.GOLD_INGOT);
				case "iron" -> vanillaMetals.put(model.getId(), Items.IRON_INGOT);
			}

			for (Map.Entry<String, Item> mat : vanillaMetals.entrySet()) {
				TagKey<Item> baseIngotTag = EETags.MATERIAL_INGOT.apply(model.getId());
				if (processedType.contains("plate")) {
					// Plate
					ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EERegistrar.plateMap.getValue(mat.getKey()), 1)
						.requires(mat.getValue())
						.requires(EERegistrar.ENIGMATIC_HAMMER.get())
						.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "plate/from_ingot/" + mat.getKey()));
				}

				if (processedType.contains("gear")) {
					// Gear
					ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EERegistrar.gearMap.getValue(mat.getKey()))
						.define('I', mat.getValue())
						.define('N', Tags.Items.NUGGETS_IRON)
						.pattern(" I ")
						.pattern("INI")
						.pattern(" I ")
						.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "gear/from_ingot/" + mat.getKey()));
				}

				if (processedType.contains("rod")) {
					// Rod
					ShapedRecipeBuilder.shaped(RecipeCategory.MISC, EERegistrar.rodMap.getValue(mat.getKey()))
						.define('I', mat.getValue())
						.pattern("I")
						.pattern("I")
						.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "rod/from_ingot/" + mat.getKey()));
				}

				if (processedType.contains("dust")) {
					// Dust from Ore
					ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EERegistrar.dustMap.getValue(mat.getKey()), 1)
						.requires(EETags.MATERIAL_ORE.apply(mat.getKey()))
						.requires(EERegistrar.ENIGMATIC_HAMMER.get())
						.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "dust/from_ore/" + mat.getKey()));

					// Dust from Raw
					ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, EERegistrar.dustMap.getValue(mat.getKey()), 1)
						.requires(EETags.MATERIAL_RAW.apply(mat.getKey()))
						.requires(EERegistrar.ENIGMATIC_HAMMER.get())
						.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "dust/from_raw/" + mat.getKey()));

					// Dust Smelting
					SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_DUST.apply(mat.getKey())),
							RecipeCategory.MISC,
							mat.getValue(), 0.7F, 200)
						.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "ingot/from_dust/smelting/" + mat.getKey()));

					// Dust Blasting
					SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_DUST.apply(mat.getKey())),
							RecipeCategory.MISC,
							mat.getValue(), 0.7F, 100)
						.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "ingot/from_dust/blasting/" + mat.getKey()));
				}

				if (processedType.contains("ore")) {
					// Ore Smelting
					SimpleCookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply(mat.getKey())),
							RecipeCategory.MISC,
							mat.getValue(), 0.1F, 200)
						.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "ingot/from_ore/smelting/" + mat.getKey()));

					// Ore Blasting
					SimpleCookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply(mat.getKey())),
							RecipeCategory.MISC,
							mat.getValue(), 0.1F, 100)
						.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "ingot/from_ore/blasting/" + mat.getKey()));
				}

				if (processedType.contains("armor")) {
					// Helmet from Ingot
					ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.helmetMap.getValue(mat.getKey()))
						.define('I', mat.getValue())
						.pattern("III")
						.pattern("I I")
						.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "helmet/from_ingot/" + mat.getKey()));
					// Chestplate from Ingot
					ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.chestplateMap.getValue(mat.getKey()))
						.define('I', mat.getValue())
						.pattern("I I")
						.pattern("III")
						.pattern("III")
						.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "chestplate/from_ingot/" + mat.getKey()));
					// Leggings from Ingot
					ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.leggingsMap.getValue(mat.getKey()))
						.define('I', mat.getValue())
						.pattern("III")
						.pattern("I I")
						.pattern("I I")
						.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "leggings/from_ingot/" + mat.getKey()));
					// Boots from Ingot
					ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.bootsMap.getValue(mat.getKey()))
						.define('I', mat.getValue())
						.pattern("I I")
						.pattern("I I")
						.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "boots/from_ingot/" + mat.getKey()));
				}

				if (processedType.contains("shield")) {
					// Shield from Ingot
					SmithingTransformRecipeBuilder.smithing(
							Ingredient.of(EERegistrar.SHIELD_TEMPLATE),
							Ingredient.of(Items.SHIELD),
							Ingredient.of(baseIngotTag),
							RecipeCategory.TOOLS,
							EERegistrar.shieldMap.get(model).get())
						.unlocks(model.getId() + "_unlock", has(baseIngotTag))
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "shield/from_gem/" + model.getId()));
				}

				if (processedType.contains("sword")) {
					// Sword from Ingot
					ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.swordMap.getValue(mat.getKey()))
						.define('I', mat.getValue())
						.define('#', Items.STICK)
						.pattern("I")
						.pattern("I")
						.pattern("#")
						.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "sword/from_ingot/" + mat.getKey()));
				}

				if (processedType.contains("pickaxe")) {
					// Pickaxe from Ingot
					ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.pickaxeMap.getValue(mat.getKey()))
						.define('I', mat.getValue())
						.define('#', Items.STICK)
						.pattern("III")
						.pattern(" # ")
						.pattern(" # ")
						.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "pickaxe/from_ingot/" + mat.getKey()));
				}

				if (processedType.contains("axe")) {
					// Axe from Ingot
					ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.axeMap.getValue(mat.getKey()))
						.define('I', mat.getValue())
						.define('#', Items.STICK)
						.pattern("II")
						.pattern("I#")
						.pattern(" #")
						.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "axe/from_ingot/" + mat.getKey()));
				}

				if (processedType.contains("shovel")) {
					// Shovel from Ingot
					ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.shovelMap.getValue(mat.getKey()))
						.define('I', mat.getValue())
						.define('#', Items.STICK)
						.pattern("I")
						.pattern("#")
						.pattern("#")
						.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "shovel/from_ingot/" + mat.getKey()));
				}

				if (processedType.contains("hoe")) {
					// Hoe from Ingot
					ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.hoeMap.getValue(mat.getKey()))
						.define('I', mat.getValue())
						.define('#', Items.STICK)
						.pattern("II")
						.pattern(" #")
						.pattern(" #")
						.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "hoe/from_ingot/" + mat.getKey()));
				}

				if (processedType.contains("paxel") && processedType.contains("pickaxe") && processedType.contains("axe") && processedType.contains("shovel")) {
					// Paxel from Ingot
					ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EERegistrar.paxelMap.getValue(mat.getKey()))
						.define('P', EERegistrar.pickaxeMap.getValue(mat.getKey()))
						.define('A', EERegistrar.axeMap.getValue(mat.getKey()))
						.define('S', EERegistrar.shovelMap.getValue(mat.getKey()))
						.define('#', Items.STICK)
						.pattern("PAS")
						.pattern(" # ")
						.pattern(" # ")
						.unlockedBy(model.getId() + "_unlock", has(baseIngotTag))
						.group(Reference.MOD_ID)
						.save(out, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "paxel/from_ingot/" + mat.getKey()));
				}
			}
		}
	}
}