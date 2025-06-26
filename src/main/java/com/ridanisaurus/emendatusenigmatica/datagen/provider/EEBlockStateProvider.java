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

package com.ridanisaurus.emendatusenigmatica.datagen.provider;

import com.google.common.collect.Sets;
import com.ridanisaurus.emendatusenigmatica.datagen.IFinishedGenericJSON;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public abstract class EEBlockStateProvider implements DataProvider {
	protected final DataGenerator generator;

	public EEBlockStateProvider(DataGenerator gen) {
		this.generator = gen;
	}

	@Override
	public @NotNull CompletableFuture<?> run(@NotNull CachedOutput directoryCache) {
		Path path = this.generator.getPackOutput().getOutputFolder();
		Set<ResourceLocation> set = Sets.newHashSet();
		List<CompletableFuture<?>> cs = new ArrayList<>();
		buildBlockState((consumer) -> {
			if (!set.add(consumer.getId())) throw new IllegalStateException("Duplicate JSON " + consumer.getId());
			cs.add(DataProvider.saveStable(
				directoryCache,
				consumer.serializeJSON(),
				path.resolve("assets/" + consumer.getId().getNamespace() + "/blockstates/" + consumer.getId().getPath() + ".json")
			));
		});
		return CompletableFuture.allOf(cs.toArray(new CompletableFuture<?>[]{}));
	}

	protected abstract void buildBlockState(Consumer<IFinishedGenericJSON> consumer);

	@Override
	public abstract @NotNull String getName();
}