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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ridanisaurus.emendatusenigmatica.datagen.IFinishedGenericJSON;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;

public class TagBuilder {

	private final List<String> tags = new ArrayList<>();

	public TagBuilder() {}

	public TagBuilder(String tag) {
		this.tags.add(tag);
	}

	public TagBuilder(Collection<String> tags) {
		this.tags.addAll(tags);
	}

	public TagBuilder tag(String tag) {
		this.tags.add(tag);
		return this;
	}

	public TagBuilder tags(Collection<String> tags) {
		this.tags.addAll(tags);
		return this;
	}

	public void save(@NotNull Consumer<IFinishedGenericJSON> consumer, ResourceLocation jsonResourceLocation) {
		consumer.accept(new Result(jsonResourceLocation, this.tags));
	}

	public void save(@NotNull Consumer<IFinishedGenericJSON> consumer, String resourceLocation) {
		this.save(consumer, ResourceLocation.parse(resourceLocation));
	}

	public void save(@NotNull Consumer<IFinishedGenericJSON> consumer, String namespace, String path) {
		this.save(consumer, ResourceLocation.fromNamespaceAndPath(namespace, path));
	}

	public static class Result implements IFinishedGenericJSON {
		private final ResourceLocation id;
		private final List<String> tags;

		public Result(ResourceLocation id, List<String> tags) {
			this.id = id;
			this.tags = tags;
		}

		public void serializeJSONData(JsonObject recipeJson) {
			if (!this.tags.isEmpty()) {
				JsonArray jsonarray = new JsonArray();
				for (String b : this.tags) {
					jsonarray.add(b);
				}
				recipeJson.add("values", jsonarray);
			}
		}

		public ResourceLocation getId() {
			return this.id;
		}
	}


}