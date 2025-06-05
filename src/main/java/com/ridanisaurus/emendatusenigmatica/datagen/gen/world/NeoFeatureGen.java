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

package com.ridanisaurus.emendatusenigmatica.datagen.gen.world;

import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.datagen.IFinishedGenericJSON;
import com.ridanisaurus.emendatusenigmatica.datagen.provider.EENeoFeatureProvider;
import com.ridanisaurus.emendatusenigmatica.datagen.builder.FeatureBuilder;
import com.ridanisaurus.emendatusenigmatica.plugin.DefaultLoader;
import com.ridanisaurus.emendatusenigmatica.plugin.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.common.CommonDepositModelBase;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.neoforged.neoforge.common.Tags;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NeoFeatureGen extends EENeoFeatureProvider {
	private final EmendatusDataRegistry registry;

	public NeoFeatureGen(DataGenerator gen, EmendatusDataRegistry registry, CompletableFuture<HolderLookup.Provider> providers) {
		super(gen, providers);
		this.registry = registry;
	}

	private final List<String> DEFAULT_COAL_ORE = List.of("minecraft:ore_coal_upper", "minecraft:ore_coal_lower");
	private final List<String> DEFAULT_COPPER_ORE = List.of("minecraft:ore_copper", "minecraft:ore_copper_large");
	private final List<String> DEFAULT_IRON_ORE = List.of("minecraft:ore_iron_upper", "minecraft:ore_iron_middle", "minecraft:ore_iron_small");
	private final List<String> DEFAULT_GOLD_ORE = List.of("minecraft:ore_gold_extra", "minecraft:ore_gold", "minecraft:ore_gold_lower");
	private final List<String> DEFAULT_NETHER_GOLD_ORE = List.of("minecraft:ore_gold_nether", "minecraft:ore_gold_deltas");
	private final List<String> DEFAULT_REDSTONE_ORE = List.of("minecraft:ore_redstone", "minecraft:ore_redstone_lower");
	private final List<String> DEFAULT_LAPIS_ORE = List.of("minecraft:ore_lapis", "minecraft:ore_lapis_buried");
	private final List<String> DEFAULT_DIAMOND_ORE = List.of("minecraft:ore_diamond", "minecraft:ore_diamond_large", "minecraft:ore_diamond_buried");
	private final List<String> DEFAULT_EMERALD_ORE = List.of("minecraft:ore_emerald");
	private final List<String> DEFAULT_QUARTZ_ORE = List.of("minecraft:ore_quartz_nether", "minecraft:ore_quartz_deltas");

	@Override
	protected void buildFeatures(HolderLookup.Provider provider, Consumer<IFinishedGenericJSON> consumer) {
		// Work-around for multiple files asking to disable the same ore causing a crash.
		// Won't be required after vanilla material support rework, for now tho, it will prevent random issues to pop up.
		//TODO: Rework vanilla stuff here!
		List<String> disabledFeatures = new ArrayList<>();
		for (MaterialModel material : registry.getMaterials()) {
            if (!material.isVanilla() || !material.getDisableDefaultOre()) continue;
			String id = material.getId();

			// Else If to skip other checks when match is found.
            if (id.contains("coal") && !disabledFeatures.contains("coal")) {
				disabledFeatures.add("coal");
				new FeatureBuilder("neoforge:remove_features", "underground_ores")
						.biome("#minecraft:is_overworld")
						.features(DEFAULT_COAL_ORE)
						.save(consumer, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "remove_default_coal_ore"));
			} else if (id.contains("copper") && !disabledFeatures.contains("copper")) {
				disabledFeatures.add("copper");
				new FeatureBuilder("neoforge:remove_features", "underground_ores")
						.biome("#minecraft:is_overworld")
						.features(DEFAULT_COPPER_ORE)
						.save(consumer, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "remove_default_copper_ore"));
			} else if (id.contains("iron") && !disabledFeatures.contains("iron")) {
				disabledFeatures.add("iron");
				new FeatureBuilder("neoforge:remove_features", "underground_ores")
						.biome("#minecraft:is_overworld")
						.features(DEFAULT_IRON_ORE)
						.save(consumer, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "remove_default_iron_ore"));
			} else if (id.contains("gold") && !disabledFeatures.contains("gold")) {
				disabledFeatures.add("gold");
				new FeatureBuilder("neoforge:remove_features", "underground_ores")
						.biome("#minecraft:is_overworld")
						.features(DEFAULT_GOLD_ORE)
						.save(consumer, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "remove_default_gold_ore"));
				new FeatureBuilder("neoforge:remove_features", "underground_ores")
						.biome("#minecraft:is_nether")
						.features(DEFAULT_NETHER_GOLD_ORE)
						.save(consumer, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "remove_default_nether_gold_ore"));
			} else if (id.contains("redstone") && !disabledFeatures.contains("redstone")) {
				disabledFeatures.add("redstone");
				new FeatureBuilder("neoforge:remove_features", "underground_ores")
						.biome("#minecraft:is_overworld")
						.features(DEFAULT_REDSTONE_ORE)
						.save(consumer, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "remove_default_redstone_ore"));
			} else if (id.contains("lapis") && !disabledFeatures.contains("lapis")) {
				disabledFeatures.add("lapis");
				new FeatureBuilder("neoforge:remove_features", "underground_ores")
						.biome("#minecraft:is_overworld")
						.features(DEFAULT_LAPIS_ORE)
						.save(consumer, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "remove_default_lapis_ore"));
			} else if (id.contains("diamond") && !disabledFeatures.contains("diamond")) {
				disabledFeatures.add("diamond");
				new FeatureBuilder("neoforge:remove_features", "underground_ores")
						.biome("#minecraft:is_overworld")
						.features(DEFAULT_DIAMOND_ORE)
						.save(consumer, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "remove_default_diamond_ore"));
			} else if (id.contains("emerald") && !disabledFeatures.contains("emerald")) {
				disabledFeatures.add("emerald");
				new FeatureBuilder("neoforge:remove_features", "underground_ores")
						.biome("#minecraft:is_overworld")
						.features(DEFAULT_EMERALD_ORE)
						.save(consumer, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "remove_default_emerald_ore"));
			} else if (id.contains("quartz") && !disabledFeatures.contains("quartz")) {
				disabledFeatures.add("quartz");
				new FeatureBuilder("neoforge:remove_features", "underground_ores")
						.biome("#minecraft:is_nether")
						.features(DEFAULT_QUARTZ_ORE)
						.save(consumer, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "remove_default_nether_quartz_ore"));
			}
		}

		for (IDepositProcessor processor : DefaultLoader.ACTIVE_PROCESSORS) {
			CommonDepositModelBase model = processor.getCommonModel();
			List<String> biomes = new ArrayList<>();
			List<String> features = new ArrayList<>();

			if (!model.getBiomes().isEmpty()) {
				if (model.getBiomes().stream().anyMatch(it -> it.startsWith("#"))) {
					biomes.add("#" + Reference.MOD_ID + ":biome/pack/" + processor.getCommonModel().getName());
				} else {
					biomes.addAll(model.getBiomes());
				}
			} else {
				var dim = model.getDimension();
				if (!dim.startsWith("minecraft")) {
					// Fallback for modded dimensions - most likely not correct as there is no real schema, but it's a good guess!
					biomes.add("#" + StringUtils.substringBefore(dim, ":") + ":is_" + StringUtils.substringAfter(dim, ":"));
				} else {
					biomes.add("#minecraft:is_" + StringUtils.substringAfter(dim, ":").replace("the_", ""));
				}
			}
			features.add(Reference.MOD_ID + ":" + model.getName());
			new FeatureBuilder("neoforge:add_features", "underground_ores")
				.biomes(biomes)
				.features(features)
				.save(consumer, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, model.getName() + "_ore_features"));
		}
	}

	@Override
	public @NotNull String getName() {
		return "Emendatus Enigmatica: Neo Features";
	}
}
