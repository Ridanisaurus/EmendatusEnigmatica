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

import com.google.common.collect.Lists;
import com.ridanisaurus.emendatusenigmatica.datagen.base.*;
import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.registries.EEMekanismRegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EETags;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import mekanism.api.chemical.gas.Gas;
import mekanism.api.datagen.recipe.builder.*;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

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
				List<String> processedType = material.getProcessedTypes();
				if (processedType.contains("slurry")) {
					if(processedType.contains("ore")) {
						// Dirty Slurry from Ore in the Dissolution Chamber
						ChemicalDissolutionRecipeBuilder.dissolution(
								IngredientCreatorAccess.item().from(EETags.MATERIAL_ORE.apply(material.getId())),
								IngredientCreatorAccess.gas().from(SULFURIC_ACID, 1),
								EEMekanismRegistrar.dirtySlurryMap.get(material.getId()).get().getStack(1_000)
						).build(consumer, new ResourceLocation(Reference.MOD_ID, "slurry/dirty/from_ore/" + material.getId()));
					}
					if(processedType.contains("raw")) {
						// Dirty Slurry from Raw Material in the Dissolution Chamber
						ChemicalDissolutionRecipeBuilder.dissolution(
								IngredientCreatorAccess.item().from(EETags.MATERIAL_RAW.apply(material.getId()), 3),
								IngredientCreatorAccess.gas().from(SULFURIC_ACID, 1),
								EEMekanismRegistrar.dirtySlurryMap.get(material.getId()).get().getStack(2_000)
						).build(consumer, new ResourceLocation(Reference.MOD_ID, "slurry/dirty/from_raw/" + material.getId()));
						// Dirty Slurry from Raw Storage Block in the Dissolution Chamber
						ChemicalDissolutionRecipeBuilder.dissolution(
								IngredientCreatorAccess.item().from(EETags.MATERIAL_RAW_STORAGE_BLOCK.apply(material.getId())),
								IngredientCreatorAccess.gas().from(SULFURIC_ACID, 2),
								EEMekanismRegistrar.dirtySlurryMap.get(material.getId()).get().getStack(6_000)
						).build(consumer, new ResourceLocation(Reference.MOD_ID, "slurry/dirty/from_raw_block/" + material.getId()));
					}
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
				if (processedType.contains("shard")) {
					if(processedType.contains("crystal")) {
						// Shard from Crystal
						ItemStackChemicalToItemStackRecipeBuilder.injecting(
								IngredientCreatorAccess.item().from(EETags.MATERIAL_CRYSTAL.apply(material.getId())),
								IngredientCreatorAccess.gas().from(HYDROGEN_CHLORIDE, 1),
								getItemStack(EEMekanismRegistrar.shardMap.get(material.getId()).get())
						).build(consumer, new ResourceLocation(Reference.MOD_ID, "shard/from_crystal/" + material.getId()));
					}
					if (processedType.contains("ore")) {
						// Shard from Ore
						ItemStackChemicalToItemStackRecipeBuilder.injecting(
								IngredientCreatorAccess.item().from(EETags.MATERIAL_ORE.apply(material.getId())),
								IngredientCreatorAccess.gas().from(HYDROGEN_CHLORIDE, 1),
								getItemStack(EEMekanismRegistrar.shardMap.get(material.getId()).get(), 4)
						).build(consumer, new ResourceLocation(Reference.MOD_ID, "shard/from_ore/" + material.getId()));
					}
					if(processedType.contains("raw")) {
						// Shard from Raw Material
						ItemStackChemicalToItemStackRecipeBuilder.injecting(
								IngredientCreatorAccess.item().from(EETags.MATERIAL_RAW.apply(material.getId()), 3),
								IngredientCreatorAccess.gas().from(HYDROGEN_CHLORIDE, 1),
								getItemStack(EEMekanismRegistrar.shardMap.get(material.getId()).get(), 8)
						).build(consumer, new ResourceLocation(Reference.MOD_ID, "shard/from_raw/" + material.getId()));
						// Shard from Raw Block
						ItemStackChemicalToItemStackRecipeBuilder.injecting(
								IngredientCreatorAccess.item().from(EETags.MATERIAL_RAW_STORAGE_BLOCK.apply(material.getId())),
								IngredientCreatorAccess.gas().from(HYDROGEN_CHLORIDE, 2),
								getItemStack(EEMekanismRegistrar.shardMap.get(material.getId()).get(), 24)
						).build(consumer, new ResourceLocation(Reference.MOD_ID, "shard/from_raw_block/" + material.getId()));
					}
				}

				if (processedType.contains("clump")) {
					if (processedType.contains("shard")) {
						// Clump from Shard
						ItemStackChemicalToItemStackRecipeBuilder.purifying(
								IngredientCreatorAccess.item().from(EETags.MATERIAL_SHARD.apply(material.getId())),
								IngredientCreatorAccess.gas().from(OXYGEN, 1),
								getItemStack(EEMekanismRegistrar.clumpMap.get(material.getId()).get())
						).build(consumer, new ResourceLocation(Reference.MOD_ID, "clump/from_shard/" + material.getId()));
					}
					if (processedType.contains("ore")) {
						// Clump from Ore
						ItemStackChemicalToItemStackRecipeBuilder.purifying(
								IngredientCreatorAccess.item().from(EETags.MATERIAL_ORE.apply(material.getId())),
								IngredientCreatorAccess.gas().from(OXYGEN, 1),
								getItemStack(EEMekanismRegistrar.clumpMap.get(material.getId()).get(), 3)
						).build(consumer, new ResourceLocation(Reference.MOD_ID, "clump/from_ore/" + material.getId()));
					}
					if (processedType.contains("raw")) {
						// Clump from Raw Material
						ItemStackChemicalToItemStackRecipeBuilder.purifying(
								IngredientCreatorAccess.item().from(EETags.MATERIAL_RAW.apply(material.getId())),
								IngredientCreatorAccess.gas().from(OXYGEN, 1),
								getItemStack(EEMekanismRegistrar.clumpMap.get(material.getId()).get(), 2)
						).build(consumer, new ResourceLocation(Reference.MOD_ID, "clump/from_raw/" + material.getId()));
						// Clump from Raw Block
						ItemStackChemicalToItemStackRecipeBuilder.purifying(
								IngredientCreatorAccess.item().from(EETags.MATERIAL_RAW_STORAGE_BLOCK.apply(material.getId())),
								IngredientCreatorAccess.gas().from(OXYGEN, 2),
								getItemStack(EEMekanismRegistrar.clumpMap.get(material.getId()).get(), 18)
						).build(consumer, new ResourceLocation(Reference.MOD_ID, "clump/from_raw_block/" + material.getId()));
					}
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

	public static class MekanismItemModels extends EEItemModelProvider {

		public MekanismItemModels(DataGenerator generator) {
			super(generator);
		}

		@Override
		protected void buildItemModels(Consumer<IFinishedGenericJSON> consumer) {
			for (MaterialModel material : EELoader.MATERIALS) {
				List<String> processedType = material.getProcessedTypes();
				// Crystals
				if (processedType.contains("crystal")) {
					ItemModelBuilder crystalBuilder = new ItemModelBuilder("minecraft:item/generated");
					if (material.getColors().getMaterialColor() == -1) {
						crystalBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_crystal").toString());
					} else {
						crystalBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/crystal/00").toString())
								.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/crystal/01").toString())
								.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/crystal/02").toString())
								.texture("layer3", new ResourceLocation(Reference.MOD_ID, "items/templates/crystal/03").toString())
								.texture("layer4", new ResourceLocation(Reference.MOD_ID, "items/templates/crystal/04").toString());
					}
					crystalBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_crystal"));
				}
				// Shards
				if (processedType.contains("shard")) {
					ItemModelBuilder shardBuilder = new ItemModelBuilder("minecraft:item/generated");
					if (material.getColors().getMaterialColor() == -1) {
						shardBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_shard").toString());
					} else {
						shardBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/shard/00").toString())
								.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/shard/01").toString())
								.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/shard/02").toString())
								.texture("layer3", new ResourceLocation(Reference.MOD_ID, "items/templates/shard/03").toString())
								.texture("layer4", new ResourceLocation(Reference.MOD_ID, "items/templates/shard/04").toString());
					}
					shardBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_shard"));
				}
				// Clumps
				if (processedType.contains("clump")) {
					ItemModelBuilder clumpBuilder = new ItemModelBuilder("minecraft:item/generated");
					if (material.getColors().getMaterialColor() == -1) {
						clumpBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_clump").toString());
					} else {
						clumpBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/clump/00").toString())
								.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/clump/01").toString())
								.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/clump/02").toString())
								.texture("layer3", new ResourceLocation(Reference.MOD_ID, "items/templates/clump/03").toString())
								.texture("layer4", new ResourceLocation(Reference.MOD_ID, "items/templates/clump/04").toString());
					}
					clumpBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_clump"));
				}
				// Dirty Dusts
				if (processedType.contains("dirty_dust")) {
					ItemModelBuilder dirtyDustBuilder = new ItemModelBuilder("minecraft:item/generated");
					if (material.getColors().getMaterialColor() == -1) {
						dirtyDustBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_dirty_dust").toString());
					} else {
						dirtyDustBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/dirty_dust/00").toString())
								.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/dirty_dust/01").toString())
								.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/dirty_dust/02").toString())
								.texture("layer3", new ResourceLocation(Reference.MOD_ID, "items/templates/dirty_dust/03").toString())
								.texture("layer4", new ResourceLocation(Reference.MOD_ID, "items/templates/dirty_dust/04").toString());
					}
					dirtyDustBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_dirty_dust"));
				}
			}

		}

		@Override
		public String getName() {
			return "Emendatus Enigmatica Mekanism Item Models";
		}

	}

	public static class MekanismItemTags extends EETagProvider {

		public MekanismItemTags(DataGenerator gen) {
			super(gen);
		}

		private final List<String> mekanismCrystals = Lists.newArrayList();
		private final List<String> mekanismShards = Lists.newArrayList();
		private final List<String> mekanismClumps = Lists.newArrayList();
		private final List<String> mekanismDirtyDusts = Lists.newArrayList();

		@Override
		protected void buildTags(Consumer<IFinishedGenericJSON> consumer) {
			for (MaterialModel material : EELoader.MATERIALS) {
				List<String> processedType = material.getProcessedTypes();
				// Crystals
				if (processedType.contains("crystal")) {
					ResourceLocation crystal = EEMekanismRegistrar.crystalMap.get(material.getId()).getId();
					if (!mekanismCrystals.contains("#mekanism:crystals/" + material.getId())) mekanismCrystals.add("#mekanism:crystals/" + material.getId());
					new TagBuilder().tag(crystal.toString()).save(consumer, new ResourceLocation(Reference.MEKANISM, "/items/crystals/" + material.getId()));
				}
				// Shards
				if (processedType.contains("shard")) {
					ResourceLocation shard = EEMekanismRegistrar.shardMap.get(material.getId()).getId();
					if (!mekanismShards.contains("#mekanism:shards/" + material.getId())) mekanismShards.add("#mekanism:shards/" + material.getId());
					new TagBuilder().tag(shard.toString()).save(consumer, new ResourceLocation(Reference.MEKANISM, "/items/shards/" + material.getId()));
				}
				// Clumps
				if (processedType.contains("clump")) {
					ResourceLocation clump = EEMekanismRegistrar.clumpMap.get(material.getId()).getId();
					if (!mekanismClumps.contains("#mekanism:clumps/" + material.getId())) mekanismClumps.add("#mekanism:clumps/" + material.getId());
					new TagBuilder().tag(clump.toString()).save(consumer, new ResourceLocation(Reference.MEKANISM, "/items/clumps/" + material.getId()));
				}
				// Dirty Dusts
				if (processedType.contains("dirty_dust")) {
					ResourceLocation dirtyDust = EEMekanismRegistrar.dirtyDustMap.get(material.getId()).getId();
					if (!mekanismDirtyDusts.contains("#mekanism:dirty_dusts/" + material.getId())) mekanismDirtyDusts.add("#mekanism:dirty_dusts/" + material.getId());
					new TagBuilder().tag(dirtyDust.toString()).save(consumer, new ResourceLocation(Reference.MEKANISM, "/items/dirty_dusts/" + material.getId()));
				}
			}
			new TagBuilder().tags(mekanismCrystals).save(consumer, new ResourceLocation(Reference.MEKANISM, "/items/crystals"));
			new TagBuilder().tags(mekanismShards).save(consumer, new ResourceLocation(Reference.MEKANISM, "/items/shards"));
			new TagBuilder().tags(mekanismClumps).save(consumer, new ResourceLocation(Reference.MEKANISM, "/items/clumps"));
			new TagBuilder().tags(mekanismDirtyDusts).save(consumer, new ResourceLocation(Reference.MEKANISM, "/items/dirty_dusts"));
		}
		@Override
		public String getName() {
			return "Emendatus Enigmatica Mekanism Item Tags";
		}
	}

	public static class MekanismSlurryTags extends EETagProvider {

		public MekanismSlurryTags(DataGenerator gen) {
			super(gen);
		}

		private final List<String> mekanismCleanSlurry = Lists.newArrayList();
		private final List<String> mekanismDirtySlurry = Lists.newArrayList();

		@Override
		protected void buildTags(Consumer<IFinishedGenericJSON> consumer) {
			for (MaterialModel material : EELoader.MATERIALS) {
				List<String> processedType = material.getProcessedTypes();
				// Slurries
				if (processedType.contains("slurry")) {
					ResourceLocation cleanSlurry = EEMekanismRegistrar.cleanSlurryMap.get(material.getId()).getId();
					mekanismCleanSlurry.add(cleanSlurry.toString());
					ResourceLocation dirtySlurry = EEMekanismRegistrar.dirtySlurryMap.get(material.getId()).getId();
					mekanismDirtySlurry.add(dirtySlurry.toString());
				}
			}
			new TagBuilder().tags(mekanismCleanSlurry).save(consumer, new ResourceLocation(Reference.MEKANISM, "/mekanism/clean"));
			new TagBuilder().tags(mekanismDirtySlurry).save(consumer, new ResourceLocation(Reference.MEKANISM, "/mekanism/dirty"));
		}

		@Override
		public String getName() {
			return "Emendatus Enigmatica Mekanism Slurry Tags";
		}
	}
}