/*
 * MIT License
 *
 * Copyright (c) 2024. Ridanisaurus
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
import com.ridanisaurus.emendatusenigmatica.plugin.deposit.DepositType;
import com.ridanisaurus.emendatusenigmatica.plugin.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.plugin.deposit.processors.*;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import com.ridanisaurus.emendatusenigmatica.util.WorldGenHelper;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.config.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static com.ridanisaurus.emendatusenigmatica.plugin.DefaultLoader.ACTIVE_PROCESSORS;

public class OreFeatureGen implements DataProvider {
    private final PackOutput output;
    private final CompletableFuture<HolderLookup.Provider> registries;

    public OreFeatureGen(@NotNull DataGenerator generator, CompletableFuture<HolderLookup.Provider> registries) {
        this.output = generator.getPackOutput();
        this.registries = registries;
    }

    @Override
    public @NotNull CompletableFuture<?> run(@NotNull CachedOutput output) {
        var builder = new RegistrySetBuilder();
        List<Pair<ResourceKey<PlacedFeature>, PlacedFeature>> PLACED_FEATURES = new ArrayList<>();

        //TODO:
        // Add rest of the generation types
        // Test this a bit more.
        builder.add(Registries.CONFIGURED_FEATURE, bt -> {
            for (IDepositProcessor activeProcessor : ACTIVE_PROCESSORS) {
                // If Type is not recognized - Skip. It's from an addon.
                if (DepositType.typeOf(activeProcessor.getType()) == null) continue;

                var configuredFeature = bt.register(ResourceKey.create(
                        Registries.CONFIGURED_FEATURE,
                        ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, activeProcessor.getName())),
                    getConfiguredFeature(activeProcessor)
                );

                PLACED_FEATURES.add(new Pair<>(
                    ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, activeProcessor.getName())),
                    new PlacedFeature(configuredFeature, WorldGenHelper.getFullOrePlacement(activeProcessor)
                )));
            }
        });

        builder.add(Registries.PLACED_FEATURE, bt -> PLACED_FEATURES.forEach(pair -> bt.register(pair.getFirst(), pair.getSecond())));

        return new DatapackBuiltinEntriesProvider(this.output, this.registries, builder, Set.of(Reference.MOD_ID, Reference.MINECRAFT)).run(output);
    }

    @Override
    public @NotNull String getName() {
        return "Emendatus Enigmatica: World Gen Features";
    }

    private static ConfiguredFeature<?, ?> getConfiguredFeature(IDepositProcessor processor) {
        return switch (DepositType.typeOf(processor.getType())) {
            case VANILLA -> new ConfiguredFeature<>(EERegistrar.VANILLA_ORE_FEATURE.get(), new VanillaOreFeatureConfig(((VanillaDepositProcessor) processor).getVanillaModel()));
            case SPHERE ->  new ConfiguredFeature<>(EERegistrar.SPHERE_ORE_FEATURE.get(),  new SphereOreFeatureConfig(((SphereDepositProcessor) processor).getSphereModel()));
            case GEODE ->   new ConfiguredFeature<>(EERegistrar.GEODE_ORE_FEATURE.get(),   new GeodeOreFeatureConfig(((GeodeDepositProcessor) processor).getGeodeModel()));
            case DIKE ->    new ConfiguredFeature<>(EERegistrar.DIKE_ORE_FEATURE.get(),    new DikeOreFeatureConfig(((DikeDepositProcessor) processor).getDikeModel()));
            case DENSE ->   new ConfiguredFeature<>(EERegistrar.DENSE_ORE_FEATURE.get(),   new DenseOreFeatureConfig(((DenseDepositProcessor) processor).getDenseModel()));
            case null -> null;
        };
    }
}
