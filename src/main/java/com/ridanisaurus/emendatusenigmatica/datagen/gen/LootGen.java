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

package com.ridanisaurus.emendatusenigmatica.datagen.gen;

import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.block.BlockLootGen;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

// Credits: AE2 Impl.
public class LootGen extends LootTableProvider {
	private static final Function<EmendatusDataRegistry, List<SubProviderEntry>> SUB_PROVIDERS = (registry) -> List.of(
		new SubProviderEntry((provider) -> new BlockLootGen(provider, registry), LootContextParamSets.BLOCK)
	);

	public LootGen(@NotNull DataGenerator gen, EmendatusDataRegistry registry, CompletableFuture<HolderLookup.Provider> provider) {
		super(gen.getPackOutput(), Set.of(), SUB_PROVIDERS.apply(registry), provider);
	}

	@Override
	protected void validate(@NotNull WritableRegistry<LootTable> writableregistry, @NotNull ValidationContext validationcontext, ProblemReporter.@NotNull Collector collector) {
		// Do not validate against all registered loot tables
	}

	@Override
	public @NotNull String getName() {
		return "Emendatus Enigmatica: Loot Tables";
	}
}