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

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.Registry;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class TagBuilder {

	private final List<String> tags = Lists.newArrayList();

	public TagBuilder() {}

	public TagBuilder tag(String tag) {
		this.tags.add(tag);
		return this;
	}

	public void save(Consumer<IFinishedGenericJSON> consumer, ResourceLocation jsonResourceLocation) {
		consumer.accept(new TagBuilder.Result(jsonResourceLocation, this.tags));
	}

	public class Result implements IFinishedGenericJSON {
		private final ResourceLocation id;
		private final List<String> tags;

		public Result(ResourceLocation id, List<String> tags) {
			this.id = id;
			this.tags = tags;
		}

		public void serializeJSONData(JsonObject recipeJson) {
			if (this.tags.size() > 0) {
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