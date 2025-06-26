/*
 * MIT License
 *
 * Copyright (c) 2020-2024. Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.datagen.builder;

import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ridanisaurus.emendatusenigmatica.datagen.IFinishedGenericJSON;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class FluidModelBuilder {

	private final Map<String, ObjectBuilder> textures = Maps.newLinkedHashMap();

	public FluidModelBuilder() {}

	public FluidModelBuilder textures(ObjectBuilder builder) {
		this.textures.put("textures", builder);
		return this;
	}

	public void save(@NotNull Consumer<IFinishedGenericJSON> consumer, ResourceLocation resourceLocation) {
		consumer.accept(new Result(resourceLocation, this.textures));
	}

	public static class ObjectBuilder {
		private final List<JsonObject> jsonObject = new ArrayList<>();
		private final boolean forceArray;

		public ObjectBuilder(boolean forceArray) {
			this.forceArray = forceArray;
		}

		@Contract("_ -> this")
		@SafeVarargs
        public final ObjectBuilder addObject(Pair<String, Object> @NotNull ... elements) {
			JsonObject object = new JsonObject();

			for (Pair<String, Object> element : elements) {
				if (element.getValue() instanceof String) {
					object.addProperty(element.getKey(), (String) element.getValue());
				}
			}
			jsonObject.add(object);
			return this;
		}

		public ObjectBuilder particle(String pairValue) {
			return addObject(Pair.of("particle", pairValue));
		}

		public JsonElement getOutput() {
			if (jsonObject.size() > 1 || forceArray) {
				JsonArray array = new JsonArray();
				jsonObject.forEach(array::add);
				return array;
			}
			return jsonObject.getFirst();
		}
	}

	public static class Result implements IFinishedGenericJSON {
		private final ResourceLocation id;
		private final Map<String, ObjectBuilder> fieldValueJson;

		public Result(ResourceLocation id, Map<String, ObjectBuilder> fieldValueJson) {
			this.id = id;
			this.fieldValueJson = fieldValueJson;
		}

		@Override
		public void serializeJSONData(JsonObject json) {
			if (!this.fieldValueJson.isEmpty()) {
				for (Map.Entry<String, ObjectBuilder> entry : this.fieldValueJson.entrySet()) {
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