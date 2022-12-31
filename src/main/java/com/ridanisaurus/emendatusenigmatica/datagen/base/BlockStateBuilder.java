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

	private final Map<String, VariantBuilder> variants = Maps.newLinkedHashMap();

	public BlockStateBuilder() {}

	public BlockStateBuilder variant(VariantBuilder builder) {
		this.variants.put(builder.getFaceName(), builder);
		return this;
	}

	public void save(Consumer<IFinishedGenericJSON> consumer, ResourceLocation resourceLocation) {
		consumer.accept(new BlockStateBuilder.Result(resourceLocation, this.variants));
	}

	public static class VariantBuilder {
		private final String faceName;
		private String model;
		private int x = -1;
		private int y = -1;

		public VariantBuilder(String faceName) {
			this.faceName = faceName;
		}

		public String getFaceName() {
			return faceName;
		}

		public VariantBuilder setModel(String model) {
			this.model = model;
			return this;
		}

		public VariantBuilder setX(int x) {
			this.x = x;
			return this;
		}

		public VariantBuilder setY(int y) {
			this.y = y;
			return this;
		}

		JsonObject toJson() {
			JsonObject ret = new JsonObject();
			ret.addProperty("model", model);
			if (x != -1) {
				ret.addProperty("x", x);
			}
			if (y != -1) {
				ret.addProperty("y", y);
			}
			return ret;
		}
	}

	public class Result implements IFinishedGenericJSON {
		private final ResourceLocation id;
		private final Map<String, VariantBuilder> variants;

		public Result(ResourceLocation id, Map<String, VariantBuilder> variants) {
			this.id = id;
			this.variants = variants;
		}

		@Override
		public void serializeJSONData(JsonObject json) {
			if (!this.variants.isEmpty()) {
				JsonObject variantsJson = new JsonObject();
				for (Map.Entry<String, VariantBuilder> s : variants.entrySet()) {
					variantsJson.add(s.getKey(), s.getValue().toJson());
				}
				json.add("variants", variantsJson);
			}
		}

		@Override
		public ResourceLocation getId() {
			return this.id;
		}
	}
}