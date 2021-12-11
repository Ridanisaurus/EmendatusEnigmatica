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
import com.ridanisaurus.emendatusenigmatica.registries.EECreateRegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EEMekanismRegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EETags;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import mekanism.api.chemical.gas.Gas;
import mekanism.api.datagen.recipe.builder.*;
import mekanism.api.recipes.inputs.FluidStackIngredient;
import mekanism.api.recipes.inputs.ItemStackIngredient;
import mekanism.api.recipes.inputs.chemical.GasStackIngredient;
import mekanism.api.recipes.inputs.chemical.SlurryStackIngredient;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.function.Consumer;

public class CreateDataGen {

	public static class CreateRecipes extends GenericRecipeProvider {

		public CreateRecipes(DataGenerator gen) {
			super(gen);
		}

		@Override
		protected void buildGenericRecipes(Consumer<IFinishedGenericRecipe> consumer) {
			for (MaterialModel material : EELoader.MATERIALS) {
				List<String> processedType = material.getProcessedType();
				if (processedType.contains("ingot")) {
					// Crushed Ore from Ore
					GenericRecipeBuilder.result(EERegistrar.nuggetMap.get(material.getId()).get(), 5)
							.define('I', Blocks.IRON_BLOCK)
							.define('i', EETags.MATERIAL_INGOT.apply(material.getId()))
							.pattern("III")
							.pattern(" i ")
							.pattern("iii")
							.unlockedBy("has_iron_block", has(Blocks.IRON_BLOCK))
							.type("create:crushing")
							.group("testGroup")
							.fieldString("TestKey1", "TestValue1")
							.fieldString("TestKey2", "TestValue2")
							.fieldInt("TestKey3", 1)
							.fieldInt("TestKey4", 2)
							.fieldFloat("TestKey5", 0.2f)
							.fieldFloat("TestKey6", 0.5f)
							.fieldItem("TestKey7", Items.GOLD_INGOT)

							.save(consumer, new ResourceLocation(Reference.MOD_ID, "test/" + material.getId()));
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
			return "Emendatus Enigmatica Create Recipes";
		}
	}
}