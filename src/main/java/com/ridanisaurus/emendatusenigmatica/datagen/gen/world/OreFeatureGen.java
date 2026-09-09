/*
 * MIT License
 *
 * Copyright (c) 2024-2026. Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.datagen.gen.world;

import com.mojang.datafixers.util.Pair;
import com.ridanisaurus.emendatusenigmatica.plugin.DataRegistry;
import com.ridanisaurus.emendatusenigmatica.plugin.model.DepositModel;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class OreFeatureGen implements DataProvider {
    private final PackOutput output;
    private final CompletableFuture<HolderLookup.Provider> registries;
    private final DataRegistry registry;

    public OreFeatureGen(@NotNull DataGenerator generator, DataRegistry registry, CompletableFuture<HolderLookup.Provider> registries) {
        this.output = generator.getPackOutput();
        this.registries = registries;
        this.registry = registry;
    }

    @Override
    public @NotNull CompletableFuture<?> run(@NotNull CachedOutput output) {
        var builder = new RegistrySetBuilder();
        List<Pair<ResourceKey<PlacedFeature>, PlacedFeature>> PLACED_FEATURES = new ArrayList<>();

        builder.add(Registries.CONFIGURED_FEATURE, ctx -> {
            for (DepositModel model : registry.getRegisteredDeposits()) {
                var key = Reference.getPath(model.id);
                var configuredFeature = ctx.register(ResourceKey.create(Registries.CONFIGURED_FEATURE, key), model.getConfiguredFeature());
                PLACED_FEATURES.add(new Pair<>(
                    ResourceKey.create(Registries.PLACED_FEATURE, key),
                    new PlacedFeature(configuredFeature, model.getOrePlacement())
                ));
            }
        });

        builder.add(Registries.PLACED_FEATURE, bt -> PLACED_FEATURES.forEach(pair -> bt.register(pair.getFirst(), pair.getSecond())));

        return new DatapackBuiltinEntriesProvider(this.output, this.registries, builder, Set.of(Reference.MOD_ID, Reference.MINECRAFT)).run(output);
    }

    @Override
    public @NotNull String getName() {
        return "Emendatus Enigmatica: World Gen Features";
    }
}
