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

import com.ridanisaurus.emendatusenigmatica.datagen.base.EERecipeProvider;
import com.ridanisaurus.emendatusenigmatica.datagen.base.RecipeBuilder;
import com.ridanisaurus.emendatusenigmatica.datagen.base.IFinishedGenericRecipe;
import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.registries.EEBloodMagicRegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EETags;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.function.Consumer;

import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class BloodMagicDataGen {

	public static class BloodMagicRecipes extends EERecipeProvider {

		public BloodMagicRecipes(DataGenerator gen) {
			super(gen);
		}

		@Override
		protected void buildRecipes(Consumer<IFinishedGenericRecipe> consumer) {
			for (MaterialModel material : EELoader.MATERIALS) {
				List<String> processedType = material.getProcessedType();
				if (processedType.contains("dust") && processedType.contains("ore") && material.getProperties().getOreBlockType().equals("metal") && material.isModded()) {
					// Dust from Ore - Alchemy Table
					new RecipeBuilder("output", EERegistrar.dustMap.get(material.getId()).get(), 2)
							.forceOutputArray(false)
							.type("bloodmagic:alchemytable")
							.group("emendatusenigmatica:compat_recipe")
							.fieldJson("input", new RecipeBuilder.JsonItemBuilder(true)
									.tag(EETags.MATERIAL_ORE.apply(material.getId()))
									.tag(EETags.MATERIAL_ARC.apply("cuttingfluid")))
							.fieldInt("syphon", 400)
							.fieldInt("ticks", 200)
							.fieldInt("upgradeLevel", 1)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "dust/from_ore_alchemy/" + material.getId()));
				}
				if (processedType.contains("fragment") && processedType.contains("ore") && material.getProperties().getOreBlockType().equals("metal") && material.isModded()) {
					// Fragment from Ore - ARC
					new RecipeBuilder("output", EEBloodMagicRegistrar.fragmentMap.get(material.getId()).get(), 3)
							.forceOutputArray(false)
							.type("bloodmagic:arc")
							.group("emendatusenigmatica:compat_recipe")
							.fieldJson("input", new RecipeBuilder.JsonItemBuilder(false)
									.tag(EETags.MATERIAL_ORE.apply(material.getId())))
							.fieldJson("tool", new RecipeBuilder.JsonItemBuilder(false)
									.tag(EETags.MATERIAL_ARC.apply("explosive")))
							.fieldBoolean("consumeingredient", false)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "fragment/from_ore/" + material.getId()));
				}
				if (processedType.contains("gravel") && processedType.contains("fragment") && material.getProperties().getOreBlockType().equals("metal") && material.isModded()) {
					// Gravel from Fragment - ARC
					new RecipeBuilder("output", EEBloodMagicRegistrar.gravelMap.get(material.getId()).get(),1)
							.forceOutputArray(false)
							.type("bloodmagic:arc")
							.group("emendatusenigmatica:compat_recipe")
							.fieldJson("input", new RecipeBuilder.JsonItemBuilder(false)
									.tag(EETags.MATERIAL_FRAGMENT.apply(material.getId())))
							.fieldJson("tool", new RecipeBuilder.JsonItemBuilder(false)
									.tag(EETags.MATERIAL_ARC.apply("resonator")))
							.fieldJson("addedoutput", new RecipeBuilder.JsonItemBuilder(true)
									.objectWithChance("type", ForgeRegistries.ITEMS.getValue(new ResourceLocation(Reference.BLOODMAGIC, "corrupted_tinydust")), 1, 0.05)
									.objectWithChance("type", ForgeRegistries.ITEMS.getValue(new ResourceLocation(Reference.BLOODMAGIC, "corrupted_tinydust")), 1, 0.01))
							.fieldBoolean("consumeingredient", false)
							.save(consumer, new ResourceLocation(Reference.MOD_ID, "gravel/from_fragment/" + material.getId()));
				}
			}
		}

		@Override
		public String getName() {
			return "Emendatus Enigmatica Blood Magic Recipes";
		}
	}

	public static class BloodMagicItemModels extends ItemModelProvider {

		public BloodMagicItemModels(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
			super(generator, Reference.MOD_ID, existingFileHelper);
		}

		@Override
		protected void registerModels() {
			for (MaterialModel material : EELoader.MATERIALS) {
				List<String> processedType = material.getProcessedType();
				// Fragment
				if (processedType.contains("fragment")) {
					ItemModelBuilder parent = getBuilder(material.getId() + "_fragment").parent(new ModelFile.UncheckedModelFile("item/generated"));
					if (material.getColors().getHighlightColor() == -1) {
						parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_fragment"));
					} else {
						parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/fragment_0"))
								.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/fragment_1"))
								.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/fragment_2"));
					}
				}
				// Gravel
				if (processedType.contains("gravel")) {
					ItemModelBuilder parent = getBuilder(material.getId() + "_gravel").parent(new ModelFile.UncheckedModelFile("item/generated"));
					if (material.getColors().getHighlightColor() == -1) {
						parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_gravel"));
					} else {
						parent.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/gravel_0"))
								.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/gravel_1"))
								.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/gravel_2"));
					}
				}
			}

		}

		@Override
		public String getName() {
			return "Emendatus Enigmatica Blood Magic Item Models";
		}
	}

	public static class BloodMagicItemTags extends ItemTagsProvider {

		public BloodMagicItemTags(DataGenerator gen, BlockTagsProvider blockTagProvider, @Nullable ExistingFileHelper existingFileHelper) {
			super(gen, blockTagProvider, Reference.MOD_ID, existingFileHelper);
		}

		@Override
		protected void addTags() {
			TagAppender<Item> bloodMagicFragment = tag(ItemTags.create(new ResourceLocation(Reference.BLOODMAGIC, "fragments")));
			TagAppender<Item> bloodMagicGravel = tag(ItemTags.create(new ResourceLocation(Reference.BLOODMAGIC, "gravels")));

			for (MaterialModel material : EELoader.MATERIALS) {
				List<String> processedType = material.getProcessedType();
				// Fragments
				if (processedType.contains("fragment")) {
					bloodMagicFragment.add(EEBloodMagicRegistrar.fragmentMap.get(material.getId()).get());
					TagAppender<Item> fragmentTag = tag(ItemTags.create(new ResourceLocation(Reference.BLOODMAGIC, "fragments/" + material.getId())));
					fragmentTag.add(EEBloodMagicRegistrar.fragmentMap.get(material.getId()).get());
				}
				// Gravels
				if (processedType.contains("gravel")) {
					bloodMagicGravel.add(EEBloodMagicRegistrar.gravelMap.get(material.getId()).get());
					TagAppender<Item> gravelTag = tag(ItemTags.create(new ResourceLocation(Reference.BLOODMAGIC, "gravels/" + material.getId())));
					gravelTag.add(EEBloodMagicRegistrar.gravelMap.get(material.getId()).get());
				}
			}
		}

		@Override
		public String getName() {
			return "Emendatus Enigmatica Blood Magic Item Tags";
		}
	}
}