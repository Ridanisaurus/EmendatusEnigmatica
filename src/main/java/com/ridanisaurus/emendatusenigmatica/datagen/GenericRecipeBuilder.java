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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class GenericRecipeBuilder {

	private final Item result;
	private final int count;
	private final List<String> rows = Lists.newArrayList();
	private final Map<Character, Ingredient> key = Maps.newLinkedHashMap();
	private final Advancement.Builder advancement = Advancement.Builder.advancement();
	private String group;
	private String type;

	private final Map<String, String> fieldValueString = Maps.newLinkedHashMap();
	private final Map<String, Integer> fieldValueInt = Maps.newLinkedHashMap();
	private final Map<String, Float> fieldValueFloat = Maps.newLinkedHashMap();
	private final Map<String, IItemProvider> fieldValueItem = Maps.newLinkedHashMap();

	public GenericRecipeBuilder(IItemProvider item, int count) {
		this.result = item.asItem();
		this.count = count;
	}

	public static GenericRecipeBuilder result(IItemProvider item) {
		return result(item, 1);
	}

	public static GenericRecipeBuilder result(IItemProvider item, int count) {
		return new GenericRecipeBuilder(item, count);
	}

	public GenericRecipeBuilder fieldString(String key, String value) {
		if (this.fieldValueString.containsKey(key)) {
			throw new IllegalArgumentException("Field Key '" + key + "' is already defined!");
		} else {
			this.fieldValueString.put(key, value);
			return this;
		}
	}

	public GenericRecipeBuilder fieldInt(String key, int value) {
		if (this.fieldValueInt.containsKey(key)) {
			throw new IllegalArgumentException("Field Key '" + key + "' is already defined!");
		} else {
			this.fieldValueInt.put(key, value);
			return this;
		}
	}

	public GenericRecipeBuilder fieldFloat(String key, float value) {
		if (this.fieldValueFloat.containsKey(key)) {
			throw new IllegalArgumentException("Field Key '" + key + "' is already defined!");
		} else {
			this.fieldValueFloat.put(key, value);
			return this;
		}
	}

	public GenericRecipeBuilder fieldItem(String key, IItemProvider value) {
		if (this.fieldValueItem.containsKey(key)) {
			throw new IllegalArgumentException("Field Key '" + key + "' is already defined!");
		} else {
			this.fieldValueItem.put(key, value);
			return this;
		}
	}

	// DO I NEED KEY?
	public GenericRecipeBuilder define(Character key, ITag<Item> itemTag) {
		return this.define(key, Ingredient.of(itemTag));
	}

	public GenericRecipeBuilder define(Character key, IItemProvider item) {
		return this.define(key, Ingredient.of(item));
	}

	public GenericRecipeBuilder define(Character key, Ingredient ingredient) {
		if (this.key.containsKey(key)) {
			throw new IllegalArgumentException("Symbol '" + key + "' is already defined!");
		} else if (key == ' ') {
			throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
		} else {
			this.key.put(key, ingredient);
			return this;
		}
	}

	// DO I NEED PATTERN?
	public GenericRecipeBuilder pattern(String pattern) {
		if (!this.rows.isEmpty() && pattern.length() != this.rows.get(0).length()) {
			throw new IllegalArgumentException("Pattern must be the same width on every line!");
		} else {
			this.rows.add(pattern);
			return this;
		}
	}

	public GenericRecipeBuilder unlockedBy(String advancementName, ICriterionInstance advancement) {
		this.advancement.addCriterion(advancementName, advancement);
		return this;
	}

	public GenericRecipeBuilder group(String group) {
		this.group = group;
		return this;
	}

	public GenericRecipeBuilder type(String type) {
		this.type = type;
		return this;
	}

	public void save(Consumer<IFinishedGenericRecipe> consumer) {
		this.save(consumer, Registry.ITEM.getKey(this.result));
	}

	public void save(Consumer<IFinishedGenericRecipe> consumer, String recipeName) {
		ResourceLocation resourcelocation = Registry.ITEM.getKey(this.result);
		if ((new ResourceLocation(recipeName)).equals(resourcelocation)) {
			throw new IllegalStateException("Shaped Recipe " + recipeName + " should remove its 'save' argument");
		} else {
			this.save(consumer, new ResourceLocation(recipeName));
		}
	}

	public void save(Consumer<IFinishedGenericRecipe> consumer, ResourceLocation recipeResourceLocation) {
		this.ensureValid(recipeResourceLocation);
		this.advancement.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeResourceLocation)).rewards(AdvancementRewards.Builder.recipe(recipeResourceLocation)).requirements(IRequirementsStrategy.OR);
		consumer.accept(new GenericRecipeBuilder.Result(recipeResourceLocation, this.result, this.count, this.group == null ? "" : this.group, this.type, this.rows, this.key, this.advancement, new ResourceLocation(recipeResourceLocation.getNamespace(), "recipes/" + this.result.getItemCategory().getRecipeFolderName() + "/" + recipeResourceLocation.getPath()), this.fieldValueString, this.fieldValueInt, this.fieldValueFloat, this.fieldValueItem));
	}

	private void ensureValid(ResourceLocation resourceLocation) {
		if (this.rows.isEmpty()) {
			throw new IllegalStateException("No pattern is defined for shaped recipe " + resourceLocation + "!");
		} else {
			Set<Character> set = Sets.newHashSet(this.key.keySet());
			set.remove(' ');

			for(String s : this.rows) {
				for(int i = 0; i < s.length(); ++i) {
					char c0 = s.charAt(i);
					if (!this.key.containsKey(c0) && c0 != ' ') {
						throw new IllegalStateException("Pattern in recipe " + resourceLocation + " uses undefined symbol '" + c0 + "'");
					}

					set.remove(c0);
				}
			}

			if (!set.isEmpty()) {
				throw new IllegalStateException("Ingredients are defined but not used in pattern for recipe " + resourceLocation);
			} else if (this.rows.size() == 1 && this.rows.get(0).length() == 1) {
				throw new IllegalStateException("Shaped recipe " + resourceLocation + " only takes in a single item - should it be a shapeless recipe instead?");
			} else if (this.advancement.getCriteria().isEmpty()) {
				throw new IllegalStateException("No way of obtaining recipe " + resourceLocation);
			}
		}
	}

	// TODO: Add Array of Result Objects
	public class Result implements IFinishedGenericRecipe {
		private final ResourceLocation id;
		private final Item result;
		private final int count;
		private final String group;
		private final String type;
		private final List<String> pattern;
		private final Map<Character, Ingredient> key;
		private final Advancement.Builder advancement;
		private final ResourceLocation advancementId;

		private final Map<String, String> fieldValueString;
		private final Map<String, Integer> fieldValueInt;
		private final Map<String, Float> fieldValueFloat;
		private final Map<String, IItemProvider> fieldValueItem;

		public Result(ResourceLocation id, Item item, int count, String group, String type,List<String> pattern, Map<Character, Ingredient> keyMap, Advancement.Builder advancement, ResourceLocation advancementId, Map<String, String> fieldValueString, Map<String, Integer> fieldValueInt, Map<String, Float> fieldValueFloat, Map<String, IItemProvider> fieldValueItem) {
			this.id = id;
			this.result = item;
			this.count = count;
			this.group = group;
			this.type = type;
			this.pattern = pattern;
			this.key = keyMap;
			this.advancement = advancement;
			this.advancementId = advancementId;

			this.fieldValueString = fieldValueString;
			this.fieldValueInt = fieldValueInt;
			this.fieldValueFloat = fieldValueFloat;
			this.fieldValueItem = fieldValueItem;
		}

		public void serializeRecipeData(JsonObject recipeJson) {
			if (!this.group.isEmpty()) {
				recipeJson.addProperty("group", this.group);
			}

			recipeJson.addProperty("type", this.type);

			JsonArray jsonarray = new JsonArray();
			for(String s : this.pattern) {
				jsonarray.add(s);
			}
			recipeJson.add("pattern", jsonarray);

			JsonObject jsonobject = new JsonObject();
			for(Map.Entry<Character, Ingredient> entry : this.key.entrySet()) {
				jsonobject.add(String.valueOf(entry.getKey()), entry.getValue().toJson());
			}
			recipeJson.add("key", jsonobject);

			JsonObject jsonobject1 = new JsonObject();
			jsonobject1.addProperty("item", Registry.ITEM.getKey(this.result).toString());
			if (this.count > 1) {
				jsonobject1.addProperty("count", this.count);
			}
			recipeJson.add("result", jsonobject1);

			if (!this.fieldValueString.isEmpty()) {
				for(Map.Entry<String, String> entry : this.fieldValueString.entrySet()) {
					recipeJson.addProperty(entry.getKey(), entry.getValue());
				}
			}

			if (!this.fieldValueInt.isEmpty()) {
				for(Map.Entry<String, Integer> entry : this.fieldValueInt.entrySet()) {
					recipeJson.addProperty(entry.getKey(), entry.getValue());
				}
			}

			if (!this.fieldValueFloat.isEmpty()) {
				for(Map.Entry<String, Float> entry : this.fieldValueFloat.entrySet()) {
					recipeJson.addProperty(entry.getKey(), entry.getValue());
				}
			}

			if (!this.fieldValueItem.isEmpty()) {
				for(Map.Entry<String, IItemProvider> entry : this.fieldValueItem.entrySet()) {
					recipeJson.addProperty(entry.getKey(), Registry.ITEM.getKey(entry.getValue().asItem()).toString());
				}
			}
		}

		public String getType() {
			return this.type;
		}

		public ResourceLocation getId() {
			return this.id;
		}

		@Nullable
		public JsonObject serializeAdvancement() {
			return this.advancement.serializeToJson();
		}

		@Nullable
		public ResourceLocation getAdvancementId() {
			return this.advancementId;
		}
	}
}
