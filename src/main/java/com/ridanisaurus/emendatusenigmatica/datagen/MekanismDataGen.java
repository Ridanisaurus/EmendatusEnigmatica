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
import com.ridanisaurus.emendatusenigmatica.registries.EEMekanismRegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EETags;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import mekanism.api.chemical.gas.Gas;
import mekanism.api.datagen.recipe.builder.ChemicalDissolutionRecipeBuilder;
import mekanism.api.datagen.recipe.builder.FluidSlurryToSlurryRecipeBuilder;
import mekanism.api.recipes.inputs.FluidStackIngredient;
import mekanism.api.recipes.inputs.ItemStackIngredient;
import mekanism.api.recipes.inputs.chemical.GasStackIngredient;
import mekanism.api.recipes.inputs.chemical.SlurryStackIngredient;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.List;
import java.util.function.Consumer;

public class MekanismDataGen {

	private static final Gas SULFURIC_ACID = Gas.getFromRegistry(new ResourceLocation(Reference.MEKANISM, "sulfuric_acid"));

	public static class RecipesGen extends RecipeProvider {

		public RecipesGen(DataGenerator gen) {
			super(gen);
		}

		@Override
		protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
			// TODO: Add more Mekanism Recipes
			for (MaterialModel material : EELoader.MATERIALS) {
				List<String> processedType = material.getProcessedType();
				if (processedType.contains("slurry")) {
					// Dirty Slurry from Ore in the Dissolution Chamber
					ChemicalDissolutionRecipeBuilder.dissolution(
							ItemStackIngredient.from(EETags.MATERIAL_ORE.apply(material.getId())),
							GasStackIngredient.from(SULFURIC_ACID, 1),
							EEMekanismRegistrar.dirtySlurryMap.get(material.getId()).get().getStack(1_000)
					).build(consumer, new ResourceLocation(Reference.MOD_ID, "dirty_slurry_from_ore/" + material.getId()));
					// Clean Slurry from Dirty Slurry in the Chemical Washer
					FluidSlurryToSlurryRecipeBuilder.washing(
							FluidStackIngredient.from(FluidTags.WATER, 5),
							SlurryStackIngredient.from(EEMekanismRegistrar.dirtySlurryMap.get(material.getId()).get(), 1),
							EEMekanismRegistrar.cleanSlurryMap.get(material.getId()).get().getStack(1)
					).build(consumer, new ResourceLocation(Reference.MOD_ID, "clean_slurry_from_dirty_slurry/" + material.getId()));
				}
			}
		}

		@Override
		public String getName() {
			return "Emendatus Enigmatica Mekanism Recipes";
		}
	}

	public static class LangGen extends LanguageProvider {
		public LangGen(DataGenerator gen) {
			super(gen, Reference.MOD_ID, "en_us");
		}

		@Override
		protected void addTranslations() {
			for (MaterialModel material : EELoader.MATERIALS) {
				List<String> processedType = material.getProcessedType();
				// Slurries
				if (processedType.contains("slurry")) {
					StringBuilder sb = new StringBuilder();
					sb.append("Dirty ");
					sb.append(material.getLocalisedName());
					sb.append(" Slurry");
					add(EEMekanismRegistrar.dirtySlurryMap.get(material.getId()).get().getTranslationKey(), sb.toString());

					StringBuilder sb2 = new StringBuilder();
					sb2.append("Clean ");
					sb2.append(material.getLocalisedName());
					sb2.append(" Slurry");
					add(EEMekanismRegistrar.cleanSlurryMap.get(material.getId()).get().getTranslationKey(), sb2.toString());
				}
				// Crystals
				if (processedType.contains("crystal")) {
					StringBuilder sb = new StringBuilder();
					sb.append(material.getLocalisedName());
					sb.append(" Crystal");
					add(EEMekanismRegistrar.crystalMap.get(material.getId()).get(), sb.toString());
				}
				// Shards
				if (processedType.contains("shard")) {
					StringBuilder sb = new StringBuilder();
					sb.append(material.getLocalisedName());
					sb.append(" Shard");
					add(EEMekanismRegistrar.shardMap.get(material.getId()).get(), sb.toString());
				}
				// Clumps
				if (processedType.contains("clump")) {
					StringBuilder sb = new StringBuilder();
					sb.append(material.getLocalisedName());
					sb.append(" Clump");
					add(EEMekanismRegistrar.clumpMap.get(material.getId()).get(), sb.toString());
				}
				// Dirty Dusts
				if (processedType.contains("dirty_dust")) {
					StringBuilder sb = new StringBuilder();
					sb.append("Dirty ");
					sb.append(material.getLocalisedName());
					sb.append(" Dust");
					add(EEMekanismRegistrar.dirtyDustMap.get(material.getId()).get(), sb.toString());
				}
			}
		}

		@Override
		public String getName() {
			return "Emendatus Enigmatica Mekanism Languages: en_us";
		}
	}

	public static class ItemModelsGen extends ItemModelProvider {

		public ItemModelsGen(DataGenerator generator, ExistingFileHelper existingFileHelper) {
			super(generator, Reference.MOD_ID, existingFileHelper);
		}

		@Override
		protected void registerModels() {
			for (MaterialModel material : EELoader.MATERIALS) {
				List<String> processedType = material.getProcessedType();
				// Crystals
				if (processedType.contains("crystal")) {
					ItemModelBuilder parent = getBuilder(material.getId() + "_crystal").parent(new ModelFile.UncheckedModelFile("item/generated"));
					if (material.getColor() == -1) {
						parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_crystal"));
					} else {
						parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/crystal"));
					}
				}
				// Shards
				if (processedType.contains("shard")) {
					ItemModelBuilder parent = getBuilder(material.getId() + "_shard").parent(new ModelFile.UncheckedModelFile("item/generated"));
					if (material.getColor() == -1) {
						parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_shard"));
					} else {
						parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/shard"));
					}
				}
				// Clumps
				if (processedType.contains("clump")) {
					ItemModelBuilder parent = getBuilder(material.getId() + "_clump").parent(new ModelFile.UncheckedModelFile("item/generated"));
					if (material.getColor() == -1) {
						parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_clump"));
					} else {
						parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/clump"));
					}
				}
				// Dirty Dusts
				if (processedType.contains("dirty_dust")) {
					ItemModelBuilder parent = getBuilder(material.getId() + "_dirty_dust").parent(new ModelFile.UncheckedModelFile("item/generated"));
					if (material.getColor() == -1) {
						parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_dirty_dust"));
					} else {
						parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/dirty_dust"));
					}
				}
			}

		}

		@Override
		public String getName() {
			return "Emendatus Enigmatica Mekanism ItemModels";
		}

	}

	public static class ItemTagsGen extends ItemTagsProvider {

		public ItemTagsGen(DataGenerator gen, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
			super(gen, blockTagProvider, Reference.MOD_ID, existingFileHelper);
		}

		@Override
		protected void addTags() {
			// Forge Tags
			Builder<Item> mekanismCrystals = tag(ItemTags.bind(new ResourceLocation(Reference.MEKANISM, "crystals").toString()));
			Builder<Item> mekanismShards = tag(ItemTags.bind(new ResourceLocation(Reference.MEKANISM, "shards").toString()));
			Builder<Item> mekanismClumps = tag(ItemTags.bind(new ResourceLocation(Reference.MEKANISM, "clumps").toString()));
			Builder<Item> mekanismDirtyDusts = tag(ItemTags.bind(new ResourceLocation(Reference.MEKANISM, "dirty_dusts").toString()));

			for (MaterialModel material : EELoader.MATERIALS) {
				List<String> processedType = material.getProcessedType();
				// Crystals
				if (processedType.contains("crystal")) {
					mekanismCrystals.add(EEMekanismRegistrar.crystalMap.get(material.getId()).get());
					Builder<Item> crystalTag = tag(ItemTags.bind(new ResourceLocation(Reference.MEKANISM, "crystals/" + material.getId()).toString()));
					crystalTag.add(EEMekanismRegistrar.crystalMap.get(material.getId()).get());
				}
				// Shards
				if (processedType.contains("shard")) {
					mekanismShards.add(EEMekanismRegistrar.shardMap.get(material.getId()).get());
					Builder<Item> shardTag = tag(ItemTags.bind(new ResourceLocation(Reference.MEKANISM, "shards/" + material.getId()).toString()));
					shardTag.add(EEMekanismRegistrar.shardMap.get(material.getId()).get());
				}
				// Clumps
				if (processedType.contains("clump")) {
					mekanismClumps.add(EEMekanismRegistrar.clumpMap.get(material.getId()).get());
					Builder<Item> clumpTag = tag(ItemTags.bind(new ResourceLocation(Reference.MEKANISM, "clumps/" + material.getId()).toString()));
					clumpTag.add(EEMekanismRegistrar.clumpMap.get(material.getId()).get());
				}
				// Dirty Dusts
				if (processedType.contains("dirty_dust")) {
					mekanismDirtyDusts.add(EEMekanismRegistrar.dirtyDustMap.get(material.getId()).get());
					Builder<Item> dirtyDustTag = tag(ItemTags.bind(new ResourceLocation(Reference.MEKANISM, "dirty_dusts/" + material.getId()).toString()));
					dirtyDustTag.add(EEMekanismRegistrar.dirtyDustMap.get(material.getId()).get());
				}
			}
		}
		@Override
		public String getName() {
			return "Emendatus Enigmatica Mekanism Item Tags";
		}
	}
}