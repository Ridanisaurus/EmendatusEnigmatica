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
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class GenericJSONBuilder {

	private String type;
	private String typeValue;
	private final List<String> features = Lists.newArrayList();
	private final Map<String, String> fieldValueString = Maps.newLinkedHashMap();;

	public GenericJSONBuilder(String type, String typeValue) {
		this.type = type;
		this.typeValue = typeValue;
	}

	public GenericJSONBuilder feature(String feature) {
		this.features.add(feature);
		return this;
	}

	public GenericJSONBuilder fieldString(String key, String value) {
		if (this.fieldValueString.containsKey(key)) {
			throw new IllegalArgumentException("Field Key '" + key + "' is already defined!");
		} else {
			this.fieldValueString.put(key, value);
			return this;
		}
	}

	public void save(Consumer<IFinishedGenericJSON> consumer, ResourceLocation jsonResourceLocation) {
		consumer.accept(new GenericJSONBuilder.Result(jsonResourceLocation, this.type, this.typeValue, this.features, this.fieldValueString));
	}

	public static class JsonItemBuilder {

		private List<JsonObject> outputs = new ArrayList<>();
		private boolean forceArray;

		public JsonItemBuilder(boolean forceArray) {
			this.forceArray = forceArray;
		}

		public JsonItemBuilder addOutput(Pair<String, Object>... elements) {
			JsonObject object = new JsonObject();
			for (Pair<String, Object> element : elements) {
				if (element.getValue() instanceof Number) {
					object.addProperty(element.getKey(), (Number) element.getValue());
				}
				if (element.getValue() instanceof String) {
					object.addProperty(element.getKey(), (String) element.getValue());
				}
				if (element.getValue() instanceof Boolean) {
					object.addProperty(element.getKey(), (Boolean) element.getValue());
				}
				if (element.getValue() instanceof Character) {
					object.addProperty(element.getKey(), (Character) element.getValue());
				}
				if (element.getValue() instanceof Pair) {
					object.add(element.getKey(), parsePair((Pair<String, Object>) element.getValue()));
				}
			}
			outputs.add(object);
			return this;
		}

		private static JsonObject parsePair(Pair<String, Object>... elements) {
			JsonObject object = new JsonObject();
			for (Pair<String, Object> element : elements) {
				if (element.getValue() instanceof Number) {
					object.addProperty(element.getKey(), (Number) element.getValue());
				}
				if (element.getValue() instanceof String) {
					object.addProperty(element.getKey(), (String) element.getValue());
				}
				if (element.getValue() instanceof Boolean) {
					object.addProperty(element.getKey(), (Boolean) element.getValue());
				}
				if (element.getValue() instanceof Character) {
					object.addProperty(element.getKey(), (Character) element.getValue());
				}
			}
			return object;
		}

		public JsonElement getOutput() {
			if (outputs.size() > 1 || forceArray) {
				JsonArray array = new JsonArray();
				outputs.forEach(array::add);
				return array;
			}
			return outputs.get(0);
		}

		public void setForceArray(boolean forceArray) {
			this.forceArray = forceArray;
		}
	}

	public class Result implements IFinishedGenericJSON {
		private final ResourceLocation id;
		private final String type;
		private final String typeValue;
		private final List<String> features;

		private final Map<String, String> fieldValueString;

		public Result(ResourceLocation id, String type, String typeValue, List<String> features, Map<String, String> fieldValueString) {
			this.id = id;
			this.type = type;
			this.typeValue = typeValue;
			this.fieldValueString = fieldValueString;
			this.features = features;
		}

		public void serializeJSONData(JsonObject recipeJson) {
			if (!this.type.isEmpty()) {
				recipeJson.addProperty("type", this.typeValue);
			}

			if (!this.fieldValueString.isEmpty()) {
				for (Map.Entry<String, String> entry : this.fieldValueString.entrySet()) {
					recipeJson.addProperty(entry.getKey(), entry.getValue());
				}
			}

			if (this.features.size() > 0) {
				JsonArray jsonarray = new JsonArray();
				for (String s : this.features) {
					jsonarray.add(s);
				}
				recipeJson.add("features", jsonarray);
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