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
import mekanism.api.chemical.ChemicalTags;
import mekanism.api.chemical.gas.Gas;
import mekanism.api.chemical.slurry.Slurry;
import mekanism.api.datagen.recipe.builder.*;
import mekanism.api.datagen.tag.ChemicalTagsProvider;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.function.Consumer;

public class MekanismDataGen {

	private static final Gas SULFURIC_ACID = Gas.getFromRegistry(new ResourceLocation(Reference.MEKANISM, "sulfuric_acid"));
	private static final Gas HYDROGEN_CHLORIDE = Gas.getFromRegistry(new ResourceLocation(Reference.MEKANISM, "hydrogen_chloride"));
	private static final Gas OXYGEN = Gas.getFromRegistry(new ResourceLocation(Reference.MEKANISM, "oxygen"));

	public static class MekanismRecipes extends RecipeProvider {

		public MekanismRecipes(DataGenerator gen) {
			super(gen);
		}

		@Override
		protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
			for (MaterialModel material : EELoader.MATERIALS) {
				List<String> processedType = material.getProcessedType();
				if (processedType.contains("slurry") && processedType.contains("ore")) {
					// Dirty Slurry from Ore in the Dissolution Chamber
					ChemicalDissolutionRecipeBuilder.dissolution(
							IngredientCreatorAccess.item().from(EETags.MATERIAL_DUST.apply(material.getId())),
							IngredientCreatorAccess.gas().from(SULFURIC_ACID, 1),
							EEMekanismRegistrar.dirtySlurryMap.get(material.getId()).get().getStack(1_000)
					).build(consumer, new ResourceLocation(Reference.MOD_ID, "slurry/dirty/" + material.getId()));
					// Clean Slurry from Dirty Slurry in the Chemical Washer
					FluidSlurryToSlurryRecipeBuilder.washing(
							IngredientCreatorAccess.fluid().from(FluidTags.WATER, 5),
							IngredientCreatorAccess.slurry().from(EEMekanismRegistrar.dirtySlurryMap.get(material.getId()).get(), 1),
							EEMekanismRegistrar.cleanSlurryMap.get(material.getId()).get().getStack(1)
					).build(consumer, new ResourceLocation(Reference.MOD_ID, "slurry/clean/" + material.getId()));
				}
				if (processedType.contains("crystal") && processedType.contains("slurry")) {
					// Crystal from Clean Slurry
					ChemicalCrystallizerRecipeBuilder.crystallizing(
							IngredientCreatorAccess.slurry().from(EEMekanismRegistrar.cleanSlurryMap.get(material.getId()).get(),200),
							getItemStack(EEMekanismRegistrar.crystalMap.get(material.getId()).get())
							).build(consumer, new ResourceLocation(Reference.MOD_ID, "crystal/from_slurry/" + material.getId()));

				}
				if (processedType.contains("shard") && processedType.contains("crystal")) {
					// Shard from Crystal
					ItemStackChemicalToItemStackRecipeBuilder.injecting(
							IngredientCreatorAccess.item().from(EETags.MATERIAL_CRYSTAL.apply(material.getId())),
							IngredientCreatorAccess.gas().from(HYDROGEN_CHLORIDE, 1),
							getItemStack(EEMekanismRegistrar.shardMap.get(material.getId()).get())
					).build(consumer, new ResourceLocation(Reference.MOD_ID, "shard/from_crystal/" + material.getId()));
				}
				if (processedType.contains("shard") && processedType.contains("ore")) {
					// Shard from Ore
					ItemStackChemicalToItemStackRecipeBuilder.injecting(
							IngredientCreatorAccess.item().from(EETags.MATERIAL_ORE.apply(material.getId())),
							IngredientCreatorAccess.gas().from(HYDROGEN_CHLORIDE, 1),
							getItemStack(EEMekanismRegistrar.shardMap.get(material.getId()).get(), 4)
					).build(consumer, new ResourceLocation(Reference.MOD_ID, "shard/from_ore/" + material.getId()));
				}
				if (processedType.contains("clump") && processedType.contains("shard")) {
					// Clump from Shard
					ItemStackChemicalToItemStackRecipeBuilder.purifying(
							IngredientCreatorAccess.item().from(EETags.MATERIAL_SHARD.apply(material.getId())),
							IngredientCreatorAccess.gas().from(OXYGEN, 1),
							getItemStack(EEMekanismRegistrar.clumpMap.get(material.getId()).get())
					).build(consumer, new ResourceLocation(Reference.MOD_ID, "clump/from_shard/" + material.getId()));
				}
				if (processedType.contains("clump") && processedType.contains("ore")) {
					// Clump from Ore
					ItemStackChemicalToItemStackRecipeBuilder.purifying(
							IngredientCreatorAccess.item().from(EETags.MATERIAL_ORE.apply(material.getId())),
							IngredientCreatorAccess.gas().from(OXYGEN, 1),
							getItemStack(EEMekanismRegistrar.clumpMap.get(material.getId()).get(), 3)
					).build(consumer, new ResourceLocation(Reference.MOD_ID, "clump/from_ore/" + material.getId()));
				}
				if (processedType.contains("dirty_dust") && processedType.contains("clump")) {
					// Dirty Dust from Clump
					ItemStackToItemStackRecipeBuilder.crushing(
							IngredientCreatorAccess.item().from(EETags.MATERIAL_CLUMP.apply(material.getId())),
							getItemStack(EEMekanismRegistrar.dirtyDustMap.get(material.getId()).get())
					).build(consumer, new ResourceLocation(Reference.MOD_ID, "dirty_dust/from_clump/" + material.getId()));
				}
				if (processedType.contains("dust") && processedType.contains("dirty_dust")) {
					// Dust from Dirty Dust
					ItemStackToItemStackRecipeBuilder.enriching(
							IngredientCreatorAccess.item().from(EETags.MATERIAL_DIRTY_DUST.apply(material.getId())),
							getItemStack(EERegistrar.dustMap.get(material.getId()).get())
					).build(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_dirty_dust/" + material.getId()));
				}
			}
		}

		private ItemStack getItemStack(Item item, int size) {
			return new ItemStack(item, size);
		}

		private ItemStack getItemStack(Item item) {
			return getItemStack(item, 1);
		}

		@Override
		public String getName() {
			return "Emendatus Enigmatica Mekanism Recipes";
		}
	}

	public static class MekanismItemModels extends ItemModelProvider {

		public MekanismItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
			super(generator, Reference.MOD_ID, existingFileHelper);
		}

		@Override
		protected void registerModels() {
			for (MaterialModel material : EELoader.MATERIALS) {
				List<String> processedType = material.getProcessedType();
				// Crystals
				if (processedType.contains("crystal")) {
					ItemModelBuilder parent = getBuilder(material.getId() + "_crystal").parent(new ModelFile.UncheckedModelFile("item/generated"));
					if (material.getColors().getHighlightColor() == -1) {
						parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_crystal"));
					} else {
						parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/crystal_0"))
								.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/crystal_1"))
								.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/crystal_2"));
					}
				}
				// Shards
				if (processedType.contains("shard")) {
					ItemModelBuilder parent = getBuilder(material.getId() + "_shard").parent(new ModelFile.UncheckedModelFile("item/generated"));
					if (material.getColors().getHighlightColor() == -1) {
						parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_shard"));
					} else {
						parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/shard_0"))
								.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/shard_1"))
								.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/shard_2"));
					}
				}
				// Clumps
				if (processedType.contains("clump")) {
					ItemModelBuilder parent = getBuilder(material.getId() + "_clump").parent(new ModelFile.UncheckedModelFile("item/generated"));
					if (material.getColors().getHighlightColor() == -1) {
						parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_clump"));
					} else {
						parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/clump_0"))
								.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/clump_1"))
								.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/clump_2"));
					}
				}
				// Dirty Dusts
				if (processedType.contains("dirty_dust")) {
					ItemModelBuilder parent = getBuilder(material.getId() + "_dirty_dust").parent(new ModelFile.UncheckedModelFile("item/generated"));
					if (material.getColors().getHighlightColor() == -1) {
						parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_dirty_dust"));
					} else {
						parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/dirty_dust_0"))
								.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/dirty_dust_1"))
								.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/dirty_dust_2"));
					}
				}
			}

		}

		@Override
		public String getName() {
			return "Emendatus Enigmatica Mekanism Item Models";
		}

	}

	public static class MekanismItemTags extends ItemTagsProvider {

		public MekanismItemTags(DataGenerator gen, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
			super(gen, blockTagProvider, Reference.MOD_ID, existingFileHelper);
		}

		@Override
		protected void addTags() {
			TagAppender<Item> mekanismCrystals = tag(ItemTags.create(new ResourceLocation(Reference.MEKANISM, "crystals")));
			TagAppender<Item> mekanismShards = tag(ItemTags.create(new ResourceLocation(Reference.MEKANISM, "shards")));
			TagAppender<Item> mekanismClumps = tag(ItemTags.create(new ResourceLocation(Reference.MEKANISM, "clumps")));
			TagAppender<Item> mekanismDirtyDusts = tag(ItemTags.create(new ResourceLocation(Reference.MEKANISM, "dirty_dusts")));

			for (MaterialModel material : EELoader.MATERIALS) {
				List<String> processedType = material.getProcessedType();
				// Crystals
				if (processedType.contains("crystal")) {
					mekanismCrystals.add(EEMekanismRegistrar.crystalMap.get(material.getId()).get());
					TagAppender<Item> crystalTag = tag(ItemTags.create(new ResourceLocation(Reference.MEKANISM, "crystals/" + material.getId())));
					crystalTag.add(EEMekanismRegistrar.crystalMap.get(material.getId()).get());
				}
				// Shards
				if (processedType.contains("shard")) {
					mekanismShards.add(EEMekanismRegistrar.shardMap.get(material.getId()).get());
					TagAppender<Item> shardTag = tag(ItemTags.create(new ResourceLocation(Reference.MEKANISM, "shards/" + material.getId())));
					shardTag.add(EEMekanismRegistrar.shardMap.get(material.getId()).get());
				}
				// Clumps
				if (processedType.contains("clump")) {
					mekanismClumps.add(EEMekanismRegistrar.clumpMap.get(material.getId()).get());
					TagAppender<Item> clumpTag = tag(ItemTags.create(new ResourceLocation(Reference.MEKANISM, "clumps/" + material.getId())));
					clumpTag.add(EEMekanismRegistrar.clumpMap.get(material.getId()).get());
				}
				// Dirty Dusts
				if (processedType.contains("dirty_dust")) {
					mekanismDirtyDusts.add(EEMekanismRegistrar.dirtyDustMap.get(material.getId()).get());
					TagAppender<Item> dirtyDustTag = tag(ItemTags.create(new ResourceLocation(Reference.MEKANISM, "dirty_dusts/" + material.getId())));
					dirtyDustTag.add(EEMekanismRegistrar.dirtyDustMap.get(material.getId()).get());
				}
			}
		}
		@Override
		public String getName() {
			return "Emendatus Enigmatica Mekanism Item Tags";
		}
	}

	public static class MekanismSlurryTags extends ChemicalTagsProvider.SlurryTagsProvider {

		public MekanismSlurryTags(DataGenerator gen, ExistingFileHelper existingFileHelper) {
			super(gen, Reference.MOD_ID, existingFileHelper);
		}

		@Override
		protected void addTags() {
			// Forge Tags
			TagAppender<Slurry> mekanismDirtySlurries = tag(ChemicalTags.SLURRY.tag(new ResourceLocation(Reference.MEKANISM, "dirty")));
			TagAppender<Slurry> mekanismCleanSlurries = tag(ChemicalTags.SLURRY.tag(new ResourceLocation(Reference.MEKANISM, "clean")));

			for (MaterialModel material : EELoader.MATERIALS) {
				List<String> processedType = material.getProcessedType();
				// Crystals
				if (processedType.contains("slurry")) {
					mekanismDirtySlurries.add(EEMekanismRegistrar.dirtySlurryMap.get(material.getId()).get());
					TagAppender<Slurry> dirtySlurryTag = tag(ChemicalTags.SLURRY.tag(new ResourceLocation(Reference.MEKANISM, "slurries/dirty")));
					dirtySlurryTag.add(EEMekanismRegistrar.dirtySlurryMap.get(material.getId()).get());
					mekanismCleanSlurries.add(EEMekanismRegistrar.cleanSlurryMap.get(material.getId()).get());
					TagAppender<Slurry> cleanSlurryTag = tag(ChemicalTags.SLURRY.tag(new ResourceLocation(Reference.MEKANISM, "slurries/clean")));
					cleanSlurryTag.add(EEMekanismRegistrar.cleanSlurryMap.get(material.getId()).get());
				}
			}
		}

		@Override
		public String getName() {
			return "Emendatus Enigmatica Mekanism Slurry Tags";
		}
	}
}