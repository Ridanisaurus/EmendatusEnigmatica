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

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.common.hash.Hashing;
import com.google.common.hash.HashingOutputStream;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.core.Registry;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.*;
import net.minecraft.tags.TagBuilder;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.extensions.IForgeTagAppender;

import javax.annotation.Nullable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class EETagProvider<T> implements DataProvider {

	protected final DataGenerator generator;
	protected final Map<ResourceLocation, TagBuilder> builders = Maps.newLinkedHashMap();
	protected final DataGenerator.PathProvider pathProvider;
	protected final Registry<T> registry;
	private final Multimap<PackType, ResourceLocation> generated = HashMultimap.create();
	private final ResourceType resourceType;
	private final ResourceType elementResourceType;

	public EETagProvider(DataGenerator gen, Registry<T> registry) {
		this.generator = gen;
		this.registry = registry;
		this.pathProvider = gen.createPathProvider(DataGenerator.Target.DATA_PACK, TagManager.getTagDir(registry.key()));
		this.resourceType = new ResourceType(PackType.SERVER_DATA, ".json", TagManager.getTagDir(registry.key()));
		this.elementResourceType = new ResourceType(PackType.SERVER_DATA, ".json", net.minecraftforge.common.ForgeHooks.prefixNamespace(registry.key().location()));
	}

	@Nullable
	protected Path getPath(ResourceLocation id) {
		return this.pathProvider.json(id);
	}

	protected abstract void addTags();

	public void run(CachedOutput directoryCache) {
		this.builders.clear();
		this.addTags();
		this.builders.forEach((resourceLocation, builder) -> {
			List<TagEntry> list = builder.build();
			List<TagEntry> list1 = list.stream().filter((tagEntry) -> {
				return !tagEntry.verifyIfPresent(this.registry::containsKey, this.builders::containsKey);
				// DO I NEED THIS MISSING?
			}).filter(this::missing).collect(Collectors.toList()); // Forge: Add validation via existing resources
			if (!list1.isEmpty()) {
				throw new IllegalArgumentException(String.format(Locale.ROOT, "Couldn't define tag %s as it is missing following references: %s", resourceLocation, list1.stream().map(Objects::toString).collect(Collectors.joining(","))));
			} else {
				JsonElement jsonelement = TagFile.CODEC.encodeStart(JsonOps.INSTANCE, new TagFile(list, false)).getOrThrow(false, EmendatusEnigmatica.LOGGER::error);
				Path path = this.getPath(resourceLocation);
				if (path == null) return; // Forge: Allow running this data provider without writing it. Recipe provider needs valid tags.

				try {
					DataProvider.saveStable(directoryCache, jsonelement, path);
				} catch (IOException ioexception) {
					EmendatusEnigmatica.LOGGER.error("Couldn't save tags to {}", path, ioexception);
				}

			}
		});
	}

//	private boolean missing(TagEntry reference) {
//		if (reference.isRequired()) {
//			return !exists(reference.getId(), reference.isTag() ? resourceType : elementResourceType);
//		}
//		return false;
//	}

	protected TagAppender<T> tag(TagKey<T> tagKey) {
		TagBuilder tagbuilder = this.getOrCreateRawBuilder(tagKey);
		return new TagAppender<>(tagbuilder, this.registry);
	}

	protected TagBuilder getOrCreateRawBuilder(TagKey<T> tagKey) {
		return this.builders.computeIfAbsent(tagKey.location(), (resourceLocation) -> {
			trackGenerated(resourceLocation, resourceType);
			return TagBuilder.create();
		});
	}

	public void trackGenerated(ResourceLocation loc, ResourceType type) {
		this.generated.put(type.getPackType(), getLocation(loc, type.getSuffix(), type.getPrefix()));
	}

	private ResourceLocation getLocation(ResourceLocation base, String suffix, String prefix) {
		return new ResourceLocation(base.getNamespace(), prefix + "/" + base.getPath() + suffix);
	}

	// TODO: Did I just go full circle and back to my own hell of an EFHs?
//	public boolean exists(ResourceLocation loc, ResourceType type) {
//		return exists(getLocation(loc, type.getSuffix(), type.getPrefix()), type.getPackType());
//	}
//
//	public boolean exists(ResourceLocation loc, PackType packType) {
//		if (!enabled) {
//			return true;
//		}
//		return generated.get(packType).contains(loc) || getManager(packType).getResource(loc).isPresent();
//	}
//
//	private ResourceManager getManager(PackType packType) {
//		return packType == PackType.CLIENT_RESOURCES ? clientResources : serverData;
//	}

	public static class ResourceType {
		final PackType packType;
		final String suffix, prefix;
		public ResourceType(PackType type, String suffix, String prefix) {
			this.packType = type;
			this.suffix = suffix;
			this.prefix = prefix;
		}

		public PackType getPackType() { return packType; }

		public String getSuffix() { return suffix; }

		public String getPrefix() { return prefix; }
	}

	public static class TagAppender<T> implements IForgeTagAppender<T> {
		private final TagBuilder builder;
		public final Registry<T> registry;

		TagAppender(TagBuilder tagBuilder, Registry<T> registry) {
			this.builder = tagBuilder;
			this.registry = registry;
		}

		public TagAppender<T> add(T generic) {
			this.builder.addElement(this.registry.getKey(generic));
			return this;
		}

		@SafeVarargs
		public final TagAppender<T> add(ResourceKey<T>... genericResourceKey) {
			for(ResourceKey<T> resourcekey : genericResourceKey) {
				this.builder.addElement(resourcekey.location());
			}
			return this;
		}

		@SafeVarargs
		public final TagAppender<T> add(T... genetic) {
			Stream.<T>of(genetic).map(this.registry::getKey).forEach((resourceLocation) -> {
				this.builder.addElement(resourceLocation);
			});
			return this;
		}

		public TagAppender<T> add(TagEntry tag) {
			builder.add(tag);
			return this;
		}
	}

	@Override
	public String getName() {
		return null;
	}
}