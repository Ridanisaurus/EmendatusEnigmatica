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
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EECreateRegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.*;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.function.Consumer;

import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class CreateDataGen {

	public static class CreateRecipes extends EERecipeProvider {

		public CreateRecipes(DataGenerator gen) {
			super(gen);
		}

		@Override
		protected void buildRecipes(Consumer<IFinishedGenericRecipe> consumer) {
			for (MaterialModel material : EELoader.MATERIALS) {
				List<String> processedType = material.getProcessedType();
				for (StrataModel stratum : EELoader.STRATA) {
					if (processedType.contains("crushed_ore") && processedType.contains("ore") && material.getProperties().getOreBlockType().equals("metal") && material.isModded()) {
						// Crushed Ore from Ore - Crushing
						new RecipeBuilder("results", EECreateRegistrar.crushedOreMap.get(material.getId()).get(), material.getCompat().getCreateCompat().getCrushingCompat().getFirstOutputCount())
								.type("create:crushing")
								.group("emendatusenigmatica:compat_recipe")
								.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true).stack(EERegistrar.oreBlockItemTable.get(stratum.getId(), material.getId()).get()))
								.fieldInt("processingTime", 250)
								.addOutput(builder -> builder
										.stackWithChance(EECreateRegistrar.crushedOreMap.get(material.getId()).get(), material.getCompat().getCreateCompat().getCrushingCompat().getSecondOutputCount(), material.getCompat().getCreateCompat().getCrushingCompat().getSecondOutputChance())
										.stackWithChance(ForgeRegistries.ITEMS.getValue(new ResourceLocation(Reference.CREATE, "experience_nugget")), 1, 0.75)
										.stackWithChance((ForgeRegistries.ITEMS.getValue(stratum.getFillerType()) == Items.AIR ? Items.COBBLESTONE : ForgeRegistries.ITEMS.getValue(stratum.getFillerType())), 1, 0.125))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "crushed/from_ore_crushing/" + material.getId() + "_" + stratum.getId()));
					}
					if (processedType.contains("ore") && material.getProperties().getOreBlockType().equals("gem") && material.isModded()) {
						// Gem from Ore - Crushing
						new RecipeBuilder("results", (processedType.contains("gem") ? EERegistrar.gemMap.get(material.getId()).get() : material.getOreDrop().getDefaultItemDropAsItem()), material.getCompat().getCreateCompat().getCrushingCompat().getFirstOutputCount())
								.type("create:crushing")
								.group("emendatusenigmatica:compat_recipe")
								.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true).stack(EERegistrar.oreBlockItemTable.get(stratum.getId(), material.getId()).get()))
								.fieldInt("processingTime", 350)
								.addOutput(builder -> builder
										.stackWithChance((processedType.contains("gem") ? EERegistrar.gemMap.get(material.getId()).get() : material.getOreDrop().getDefaultItemDropAsItem()), material.getCompat().getCreateCompat().getCrushingCompat().getSecondOutputCount(), material.getCompat().getCreateCompat().getCrushingCompat().getSecondOutputChance())
										.stackWithChance(ForgeRegistries.ITEMS.getValue(new ResourceLocation(Reference.CREATE, "experience_nugget")), 1, 0.75)
										.stackWithChance((ForgeRegistries.ITEMS.getValue(stratum.getFillerType()) == Items.AIR ? Items.COBBLESTONE : ForgeRegistries.ITEMS.getValue(stratum.getFillerType())), 1, 0.125))
								.save(consumer, new ResourceLocation(Reference.MOD_ID, "gem/from_ore_crushing/" + material.getId() + "_" + stratum.getId()));
					}
				}

				// Nuggets from Crushed Ore
				if (processedType.contains("crushed_ore") && processedType.contains("nugget") && material.isModded()) {
					// Crushed Ore from Raw
					new RecipeBuilder("results", EERegistrar.nuggetMap.get(material.getId()).get(), 9)
							.type("create:splashing")
							.group("emendatusenigmatica:compat_recipe")
							.fieldJson("ingredients", new RecipeBuilder.JsonItemBuilder(true).stack(EECreateRegistrar.crushedOreMap.get(material.getId()).get()))
							.addOutput(builder -> builder
									.stackWithChance(material.getCompat().getCreateCompat().getSplashingCompat().getSecondOutput(), material.getCompat().getCreateCompat().getSplashingCompat().getSecondOutputCount(), material.getCompat().getCreateCompat().getSplashingCompat().getSecondOutputChance()))
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "nugget/from_crushed_splashing/" + material.getId()));
				}
			}
		}

		@Override
		public String getName() {
			return "Emendatus Enigmatica Create Recipes";
		}
	}

	public static class CreateItemModels extends ItemModelProvider {

		public CreateItemModels(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
			super(generator, Reference.MOD_ID, existingFileHelper);
		}

		@Override
		protected void registerModels() {
			for (MaterialModel material : EELoader.MATERIALS) {
				List<String> processedType = material.getProcessedType();
				// Crushed Ore
				if (processedType.contains("crushed_ore")) {
					ItemModelBuilder parent = getBuilder("crushed_" + material.getId() + "_ore").parent(new ModelFile.UncheckedModelFile("item/generated"));
					if (material.getColors().getHighlightColor() == -1) {
						parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_crushed"));
					} else {
						parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/crushed_0"))
								.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/crushed_1"))
								.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/crushed_2"));
					}
				}
			}

		}

		@Override
		public String getName() {
			return "Emendatus Enigmatica Create Item Models";
		}
	}

	public static class CreateItemTags extends ItemTagsProvider {

		public CreateItemTags(DataGenerator gen, BlockTagsProvider blockTagProvider, @Nullable ExistingFileHelper existingFileHelper) {
			super(gen, blockTagProvider, Reference.MOD_ID, existingFileHelper);
		}

		@Override
		protected void addTags() {
			TagAppender<Item> createCrushedOres = tag(ItemTags.create(new ResourceLocation(Reference.CREATE, "crushed_ores")));

			for (MaterialModel material : EELoader.MATERIALS) {
				List<String> processedType = material.getProcessedType();
				// Crystals
				if (processedType.contains("crushed_ore")) {
					createCrushedOres.add(EECreateRegistrar.crushedOreMap.get(material.getId()).get());
					TagAppender<Item> crushedOreTag = tag(ItemTags.create(new ResourceLocation(Reference.CREATE, "crushed_ores/" + material.getId())));
					crushedOreTag.add(EECreateRegistrar.crushedOreMap.get(material.getId()).get());
				}
			}
		}
		@Override
		public String getName() {
			return "Emendatus Enigmatica Create Item Tags";
		}
	}
}