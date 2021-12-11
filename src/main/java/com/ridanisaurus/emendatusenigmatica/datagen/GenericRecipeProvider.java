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

package com.ridanisaurus.emendatusenigmatica.datagen;

import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.criterion.*;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

public class GenericRecipeProvider implements IDataProvider {
	private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
	protected final DataGenerator generator;

	public GenericRecipeProvider(DataGenerator gen) {
		this.generator = gen;
	}

	public void run(DirectoryCache directoryCache) throws IOException {
		Path path = this.generator.getOutputFolder();
		Set<ResourceLocation> set = Sets.newHashSet();
		buildGenericRecipes((consumer) -> {
			if (!set.add(consumer.getId())) {
				throw new IllegalStateException("Duplicate recipe " + consumer.getId());
			} else {
				saveRecipe(directoryCache, consumer.serializeRecipe(), path.resolve("data/" + consumer.getId().getNamespace() + "/recipes/" + consumer.getId().getPath() + ".json"));
				JsonObject jsonobject = consumer.serializeAdvancement();
				if (jsonobject != null) {
					saveAdvancement(directoryCache, jsonobject, path.resolve("data/" + consumer.getId().getNamespace() + "/advancements/" + consumer.getAdvancementId().getPath() + ".json"));
				}

			}
		});
		if (this.getClass() == GenericRecipeProvider.class) //Forge: Subclasses don't need this.
			saveAdvancement(directoryCache, Advancement.Builder.advancement().addCriterion("impossible", new ImpossibleTrigger.Instance()).serializeToJson(), path.resolve("data/minecraft/advancements/recipes/root.json"));
	}

	private static void saveRecipe(DirectoryCache directoryCache, JsonObject recipeJson, Path recipePath) {
		try {
			String s = GSON.toJson((JsonElement)recipeJson);
			String s1 = SHA1.hashUnencodedChars(s).toString();
			if (!Objects.equals(directoryCache.getHash(recipePath), s1) || !Files.exists(recipePath)) {
				Files.createDirectories(recipePath.getParent());

				try (BufferedWriter bufferedwriter = Files.newBufferedWriter(recipePath)) {
					bufferedwriter.write(s);
				}
			}

			directoryCache.putNew(recipePath, s1);
		} catch (IOException ioexception) {
			EmendatusEnigmatica.LOGGER.error("Couldn't save recipe {}", recipePath, ioexception);
		}

	}

	protected void saveAdvancement(DirectoryCache directoryCache, JsonObject recipeJson, Path recipePath) {
		try {
			String s = GSON.toJson((JsonElement)recipeJson);
			String s1 = SHA1.hashUnencodedChars(s).toString();
			if (!Objects.equals(directoryCache.getHash(recipePath), s1) || !Files.exists(recipePath)) {
				Files.createDirectories(recipePath.getParent());

				try (BufferedWriter bufferedwriter = Files.newBufferedWriter(recipePath)) {
					bufferedwriter.write(s);
				}
			}

			directoryCache.putNew(recipePath, s1);
		} catch (IOException ioexception) {
			EmendatusEnigmatica.LOGGER.error("Couldn't save recipe advancement {}", recipePath, ioexception);
		}

	}

	protected static EnterBlockTrigger.Instance insideOf(Block block) {
		return new EnterBlockTrigger.Instance(EntityPredicate.AndPredicate.ANY, block, StatePropertiesPredicate.ANY);
	}

	protected static InventoryChangeTrigger.Instance has(IItemProvider item) {
		return inventoryTrigger(ItemPredicate.Builder.item().of(item).build());
	}

	protected static InventoryChangeTrigger.Instance has(ITag<Item> tag) {
		return inventoryTrigger(ItemPredicate.Builder.item().of(tag).build());
	}

	protected static InventoryChangeTrigger.Instance inventoryTrigger(ItemPredicate... itemPredicates) {
		return new InventoryChangeTrigger.Instance(EntityPredicate.AndPredicate.ANY, MinMaxBounds.IntBound.ANY, MinMaxBounds.IntBound.ANY, MinMaxBounds.IntBound.ANY, itemPredicates);
	}

	protected void buildGenericRecipes(Consumer<IFinishedGenericRecipe> consumer) {
		// It's called generic for a reason!
	}

	public String getName() {
		return "Generic Recipes";
	}
}