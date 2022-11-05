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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class GenericFeatureBuilder {

	private String type;
	private final List<String> biomes = Lists.newArrayList();
	private final List<String> features = Lists.newArrayList();
	private String step;
	private final Map<String, String> fieldValueString = Maps.newLinkedHashMap();;

	public GenericFeatureBuilder(String type, String step) {
		this.type = type;
		this.step = step;
	}

	public GenericFeatureBuilder feature(String singleFeature) {
		this.features.add(singleFeature);
		return this;
	}

	public GenericFeatureBuilder features(List<String> multipleFeatures) {
		this.features.addAll(multipleFeatures);
		return this;
	}

	public GenericFeatureBuilder biome(String singleBiome) {
		this.biomes.add(singleBiome);
		return this;
	}

	public GenericFeatureBuilder biomes(List<String> multipleBiomes) {
		this.biomes.addAll(multipleBiomes);
		return this;
	}

	public GenericFeatureBuilder fieldString(String key, String value) {
		if (this.fieldValueString.containsKey(key)) {
			throw new IllegalArgumentException("Field Key '" + key + "' is already defined!");
		} else {
			this.fieldValueString.put(key, value);
			return this;
		}
	}

	public void save(Consumer<IFinishedGenericJSON> consumer, ResourceLocation jsonResourceLocation) {
		consumer.accept(new GenericFeatureBuilder.Result(jsonResourceLocation, this.type, this.biomes, this.features, this.step, this.fieldValueString));
	}

	public class Result implements IFinishedGenericJSON {
		private final ResourceLocation id;
		private final String type;
		private final List<String> biomes;
		private final List<String> features;
		private final String step;

		private final Map<String, String> fieldValueString;

		public Result(ResourceLocation id, String type, List<String> biomes, List<String> features, String step, Map<String, String> fieldValueString) {
			this.id = id;
			this.type = type;
			this.biomes = biomes;
			this.features = features;
			this.step = step;
			this.fieldValueString = fieldValueString;
		}

		public void serializeJSONData(JsonObject recipeJson) {
			if (!this.type.isEmpty()) {
				recipeJson.addProperty("type", this.type);
			}

			if (this.features.size() > 1) {
				JsonArray jsonarray = new JsonArray();
				for (String s : this.features) {
					jsonarray.add(s);
				}
				recipeJson.add("features", jsonarray);
			}

			if (this.features.size() == 1) {
				recipeJson.addProperty("features", this.features.get(0));
			}

			if (this.biomes.size() > 1) {
				JsonArray jsonarray = new JsonArray();
				for (String b : this.biomes) {
					jsonarray.add(b);
				}
				recipeJson.add("biomes", jsonarray);
			}

			if (this.biomes.size() == 1) {
				recipeJson.addProperty("biomes", this.biomes.get(0));
			}

			if (!this.step.isEmpty()) {
				recipeJson.addProperty("step", this.step);
			}

			if (!this.fieldValueString.isEmpty()) {
				for (Map.Entry<String, String> entry : this.fieldValueString.entrySet()) {
					recipeJson.addProperty(entry.getKey(), entry.getValue());
				}
			}
		}

		public String getType() {
			return this.type;
		}

		public ResourceLocation getId() {
			return this.id;
		}
	}
}