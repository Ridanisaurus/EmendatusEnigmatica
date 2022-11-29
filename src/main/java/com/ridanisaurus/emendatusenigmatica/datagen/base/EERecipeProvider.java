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

package com.ridanisaurus.emendatusenigmatica.datagen.base;

import com.google.common.collect.Sets;
import com.google.common.hash.Hashing;
import com.google.common.hash.HashingOutputStream;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.critereon.*;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.Consumer;

public class EERecipeProvider implements DataProvider {
	protected final DataGenerator generator;

	public EERecipeProvider(DataGenerator gen) {
		this.generator = gen;
	}

	@Override
	public void run(CachedOutput directoryCache) throws IOException {
		Path path = this.generator.getOutputFolder();
		Set<ResourceLocation> set = Sets.newHashSet();
		buildRecipes((consumer) -> {
			if (!set.add(consumer.getId())) {
				throw new IllegalStateException("Duplicate recipe " + consumer.getId());
			} else {
				try {
					saveRecipe(directoryCache, consumer.serializeRecipe(), path.resolve("data/" + consumer.getId().getNamespace() + "/recipes/" + consumer.getId().getPath() + ".json"));
				} catch (IOException e) {
					e.printStackTrace();
				}
//				JsonObject jsonobject = consumer.serializeAdvancement();
//				if (jsonobject != null) {
//					try {
//						saveAdvancement(directoryCache, jsonobject, path.resolve("data/" + consumer.getId().getNamespace() + "/advancements/" + consumer.getAdvancementId().getPath() + ".json"));
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}

			}
		});
		if (this.getClass() == EERecipeProvider.class) //Forge: Subclasses don't need this.
			saveAdvancement(directoryCache, Advancement.Builder.advancement().addCriterion("impossible", new ImpossibleTrigger.TriggerInstance()).serializeToJson(), path.resolve("data/minecraft/advancements/recipes/root.json"));
	}

	private static void saveRecipe(CachedOutput directoryCache, JsonObject recipeJson, Path recipePath) throws IOException {
		ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
		HashingOutputStream hashingoutputstream = new HashingOutputStream(Hashing.sha1(), bytearrayoutputstream);
		Writer writer = new OutputStreamWriter(hashingoutputstream, StandardCharsets.UTF_8);
		JsonWriter jsonwriter = new JsonWriter(writer);
		jsonwriter.setSerializeNulls(false);
		jsonwriter.setIndent("  ");
		GsonHelper.writeValue(jsonwriter, recipeJson, KEY_COMPARATOR);
		jsonwriter.close();
		directoryCache.writeIfNeeded(recipePath, bytearrayoutputstream.toByteArray(), hashingoutputstream.hash());
	}

	protected void saveAdvancement(CachedOutput directoryCache, JsonObject recipeJson, Path recipePath) throws IOException {
		ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
		HashingOutputStream hashingoutputstream = new HashingOutputStream(Hashing.sha1(), bytearrayoutputstream);
		Writer writer = new OutputStreamWriter(hashingoutputstream, StandardCharsets.UTF_8);
		JsonWriter jsonwriter = new JsonWriter(writer);
		jsonwriter.setSerializeNulls(false);
		jsonwriter.setIndent("  ");
		GsonHelper.writeValue(jsonwriter, recipeJson, KEY_COMPARATOR);
		jsonwriter.close();
		directoryCache.writeIfNeeded(recipePath, bytearrayoutputstream.toByteArray(), hashingoutputstream.hash());
	}

	protected static EnterBlockTrigger.TriggerInstance insideOf(Block block) {
		return new EnterBlockTrigger.TriggerInstance(EntityPredicate.Composite.ANY, block, StatePropertiesPredicate.ANY);
	}

	protected static InventoryChangeTrigger.TriggerInstance has(ItemLike item) {
		return inventoryTrigger(ItemPredicate.Builder.item().of(item).build());
	}

	protected static InventoryChangeTrigger.TriggerInstance has(TagKey<Item> tag) {
		return inventoryTrigger(ItemPredicate.Builder.item().of(tag).build());
	}

	protected static InventoryChangeTrigger.TriggerInstance inventoryTrigger(ItemPredicate... itemPredicates) {
		return new InventoryChangeTrigger.TriggerInstance(EntityPredicate.Composite.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, itemPredicates);
	}

	protected void buildRecipes(Consumer<IFinishedGenericRecipe> consumer) {
		// It's called generic for a reason!
	}

	public String getName() {
		return null;
	}
}