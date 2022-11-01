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

package com.ridanisaurus.emendatusenigmatica.datagen;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.EEDeposits;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import com.ridanisaurus.emendatusenigmatica.world.gen.EEBiomeTags;
import com.ridanisaurus.emendatusenigmatica.world.gen.OreBiomeModifier;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;

// Credit: FTB-IC
public class OreFeatureDataGen implements DataProvider {
	private static final Logger LOGGER = LogManager.getLogger();
	private final RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, RegistryAccess.BUILTIN.get());
	private final Path modifierPath;
	private final OreBiomeModifier oreBiomeModifier;

	public OreFeatureDataGen(DataGenerator gen) {
		Registry<PlacedFeature> placedFeatures = ops.registry(Registry.PLACED_FEATURE_REGISTRY).get();

		DataGenerator.PathProvider pathProvider = gen.createPathProvider(DataGenerator.Target.DATA_PACK, ForgeRegistries.Keys.BIOME_MODIFIERS.location().toString().replace(":", "/"));
		this.modifierPath = pathProvider.json(new ResourceLocation(Reference.MOD_ID, "ore_biome_modifier"));

		this.oreBiomeModifier = new OreBiomeModifier(
				new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), EEBiomeTags.ORE_SPAWN_BLACKLIST),
				GenerationStep.Decoration.UNDERGROUND_ORES,
				HolderSet.direct(EEDeposits.PLACEMENTS.stream().map(e -> placedFeatures.getOrCreateHolderOrThrow(e.getKey())).toList())
		);
	}

	@Override
	public void run(CachedOutput cache) throws IOException {
		BiomeModifier.DIRECT_CODEC.encodeStart(ops, oreBiomeModifier)
				.resultOrPartial(msg -> LOGGER.error("Failed to encode {}: {}", modifierPath.toAbsolutePath(), msg)) // Log error on encode failure.
				.ifPresent(json -> // Output to file on encode success.
				{
					try
					{
						DataProvider.saveStable(cache, json, modifierPath);
					}
					catch (IOException e) // The throws can't deal with this exception, because we're inside the ifPresent.
					{
						LOGGER.error("Failed to save " + modifierPath.toAbsolutePath(), e);
					}
				});
	}

	@Override
	public String getName() {
		return "Emendatus Enigmatica Biome Modifier";
	}
}
