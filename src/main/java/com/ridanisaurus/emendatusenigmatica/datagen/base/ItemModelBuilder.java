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

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Consumer;

public class ItemModelBuilder {

	private final String parent;
	private final Map<String, String> textures = Maps.newLinkedHashMap();
	private boolean applyTint = false;
	private String fluid;
	private String loader;

	public ItemModelBuilder(String parent) {
		this.parent = parent;
	}

	public ItemModelBuilder texture(String textureKey, String textureValue) {
		this.textures.put(textureKey, textureValue);
		return this;
	}

	public ItemModelBuilder applyTint(boolean tint){
		this.applyTint = tint;
		return this;
	}

	public ItemModelBuilder fluid(String fluid){
		this.fluid = fluid;
		return this;
	}

	public ItemModelBuilder loader(String loader){
		this.loader = loader;
		return this;
	}

	public void save(Consumer<IFinishedGenericJSON> consumer, ResourceLocation jsonResourceLocation) {
		consumer.accept(new Result(jsonResourceLocation, this.parent, this.textures, this.applyTint, this.fluid, this.loader));
	}

	public class Result implements IFinishedGenericJSON {
		private final ResourceLocation id;
		private final String parent;
		private final Map<String, String> textures;
		private final boolean applyTint;
		private final String fluid;
		private final String loader;

		public Result(ResourceLocation id, String parent, Map<String, String> textures, boolean applyTint, @Nullable String fluid, @Nullable String loader) {
			this.id = id;
			this.parent = parent;
			this.textures = textures;
			this.applyTint = applyTint;
			this.fluid = fluid;
			this.loader = loader;
		}

		public void serializeJSONData(JsonObject json) {
			if (!this.parent.isEmpty()) {
				json.addProperty("parent", this.parent);
			}
			if (!this.textures.isEmpty()) {
				JsonObject textures = new JsonObject();
				for (Map.Entry<String, String> e : this.textures.entrySet()) {
					textures.addProperty(e.getKey(), e.getValue());
				}
				json.add("textures", textures);
			}
			if (this.applyTint) {
				json.addProperty("apply_tint", true);
			}
			if (this.fluid != null) {
				json.addProperty("fluid", this.fluid);
			}
			if (this.loader != null) {
				json.addProperty("loader", this.loader);
			}
		}

		public ResourceLocation getId() {
			return this.id;
		}
	}
}