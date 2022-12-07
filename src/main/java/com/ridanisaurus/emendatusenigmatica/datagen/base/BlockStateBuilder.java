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

public class BlockStateBuilder {

	private final Map<String, objectBuilder> variantObject = Maps.newLinkedHashMap();

	public BlockStateBuilder() {}

	public BlockStateBuilder variant(objectBuilder builder) {
		this.variantObject.put("variants", builder);
		return this;
	}

	public void save(Consumer<IFinishedGenericJSON> consumer, ResourceLocation resourceLocation) {
		consumer.accept(new BlockStateBuilder.Result(resourceLocation, this.variantObject));
	}

	public static class objectBuilder {
		private List<JsonObject> jsonObject = new ArrayList<>();
		private boolean forceArray;

		public objectBuilder(boolean forceArray) {
			this.forceArray = forceArray;
		}

		public objectBuilder addObject(Pair<String, Object>... elements) {
			JsonObject object = new JsonObject();

			for (Pair<String, Object> element : elements) {
				if (element.getValue() instanceof Pair) {
					object.add(element.getKey(), parsePair((Pair<String, Object>) element.getValue()));
				}
			}
			jsonObject.add(object);
			return this;
		}

		private static JsonObject parsePair(Pair<String, Object>... elements) {
			JsonObject object = new JsonObject();
			for (Pair<String, Object> element : elements) {
				if (element.getValue() instanceof String) {
					object.addProperty(element.getKey(), (String) element.getValue());
				}
			}
			return object;
		}

		public objectBuilder model(String pairValue) {
			return addObject(Pair.of("",
					Pair.of("model", pairValue)));
		}

		public JsonElement getOutput() {
			if (jsonObject.size() > 1 || forceArray) {
				JsonArray array = new JsonArray();
				jsonObject.forEach(array::add);
				return array;
			}
			return jsonObject.get(0);
		}
	}

	public class Result implements IFinishedGenericJSON {
		private final ResourceLocation id;
		private final Map<String, objectBuilder> fieldValueJson;

		public Result(ResourceLocation id, Map<String, objectBuilder> fieldValueJson) {
			this.id = id;
			this.fieldValueJson = fieldValueJson;
		}

		@Override
		public void serializeJSONData(JsonObject json) {
			if (!this.fieldValueJson.isEmpty()) {
				for (Map.Entry<String, objectBuilder> entry : this.fieldValueJson.entrySet()) {
					json.add(entry.getKey(), entry.getValue().getOutput());
				}
			}
		}

		@Override
		public ResourceLocation getId() {
			return this.id;
		}
	}
}