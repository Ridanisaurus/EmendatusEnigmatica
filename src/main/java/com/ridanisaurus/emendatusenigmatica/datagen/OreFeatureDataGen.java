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

import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.EEDeposits;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonDepositModelBase;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class OreFeatureDataGen extends GenericFeatureProvider {

	public OreFeatureDataGen(DataGenerator gen) {
		super(gen);
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
	protected void buildGenericJSON(Consumer<IFinishedGenericJSON> consumer) {
		for (MaterialModel material : EELoader.MATERIALS) {
			if (material.isVanilla() && material.getId().contains("coal") && material.getRemoveDefaultOre()) {
				new GenericFeatureBuilder("forge:remove_features", "underground_ores")
						.biome("#minecraft:is_overworld")
						.features(DEFAULT_COAL_ORE)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "remove_default_coal_ore"));
			}
			if (material.isVanilla() && material.getId().contains("copper") && material.getRemoveDefaultOre()) {
				new GenericFeatureBuilder("forge:remove_features", "underground_ores")
						.biome("#minecraft:is_overworld")
						.features(DEFAULT_COPPER_ORE)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "remove_default_copper_ore"));
			}
			if (material.isVanilla() && material.getId().contains("iron") && material.getRemoveDefaultOre()) {
				new GenericFeatureBuilder("forge:remove_features", "underground_ores")
						.biome("#minecraft:is_overworld")
						.features(DEFAULT_IRON_ORE)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "remove_default_iron_ore"));
			}
			if (material.isVanilla() && material.getId().contains("gold") && material.getRemoveDefaultOre()) {
				new GenericFeatureBuilder("forge:remove_features", "underground_ores")
						.biome("#minecraft:is_overworld")
						.features(DEFAULT_GOLD_ORE)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "remove_default_gold_ore"));
				new GenericFeatureBuilder("forge:remove_features", "underground_ores")
						.biome("#minecraft:is_nether")
						.features(DEFAULT_NETHER_GOLD_ORE)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "remove_default_nether_gold_ore"));
			}
			if (material.isVanilla() && material.getId().contains("redstone") && material.getRemoveDefaultOre()) {
				new GenericFeatureBuilder("forge:remove_features", "underground_ores")
						.biome("#minecraft:is_overworld")
						.features(DEFAULT_REDSTONE_ORE)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "remove_default_redstone_ore"));
			}
			if (material.isVanilla() && material.getId().contains("lapis") && material.getRemoveDefaultOre()) {
				new GenericFeatureBuilder("forge:remove_features", "underground_ores")
						.biome("#minecraft:is_overworld")
						.features(DEFAULT_LAPIS_ORE)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "remove_default_lapis_ore"));
			}
			if (material.isVanilla() && material.getId().contains("diamond") && material.getRemoveDefaultOre()) {
				new GenericFeatureBuilder("forge:remove_features", "underground_ores")
						.biome("#minecraft:is_overworld")
						.features(DEFAULT_DIAMOND_ORE)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "remove_default_diamond_ore"));
			}
			if (material.isVanilla() && material.getId().contains("emerald") && material.getRemoveDefaultOre()) {
				new GenericFeatureBuilder("forge:remove_features", "underground_ores")
						.biome("#minecraft:is_overworld")
						.features(DEFAULT_EMERALD_ORE)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "remove_default_emerald_ore"));
			}
			if (material.isVanilla() && material.getId().contains("nether_quartz") && material.getRemoveDefaultOre()) {
				new GenericFeatureBuilder("forge:remove_features", "underground_ores")
						.biome("#minecraft:is_nether")
						.features(DEFAULT_QUARTZ_ORE)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "remove_default_nether_quartz_ore"));
			}
		}

		for (IDepositProcessor processor : EEDeposits.ACTIVE_PROCESSORS) {
			CommonDepositModelBase model = processor.getCommonModel();
			List<String> biomes = new ArrayList<>();
			List<String> features = new ArrayList<>();
			if(model.getDimension().equals("minecraft:overworld")) {
				if(!model.getBiomes().isEmpty()) {
					biomes.addAll(model.getBiomes());
				} else {
					biomes.add("#minecraft:is_overworld");
				}
				features.add(Reference.MOD_ID + ":" + model.getName());
				new GenericFeatureBuilder("forge:add_features", "underground_ores")
						.biomes(biomes)
						.features(features)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, model.getName() + "_ore_features"));
			}
			if(model.getDimension().equals("minecraft:the_nether")) {
				if(!model.getBiomes().isEmpty()) {
					biomes.addAll(model.getBiomes());
				} else {
					biomes.add("#minecraft:is_nether");
				}
				features.add(Reference.MOD_ID + ":" + model.getName());
				new GenericFeatureBuilder("forge:add_features", "underground_ores")
						.biomes(biomes)
						.features(features)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, model.getName() + "_ore_features"));
			}
			if(model.getDimension().equals("minecraft:the_end")) {
				if(!model.getBiomes().isEmpty()) {
					biomes.addAll(model.getBiomes());
				} else {
					biomes.add("#minecraft:is_end");
				}
				features.add(Reference.MOD_ID + ":" + model.getName());
				new GenericFeatureBuilder("forge:add_features", "underground_ores")
						.biomes(biomes)
						.features(features)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, model.getName() + "_ore_features"));
			}
			if(Stream.of("minecraft:overworld", "minecraft:the_nether", "minecraft:the_end").noneMatch(s -> model.getDimension().equals(s))) {
				if(!model.getBiomes().isEmpty()) {
					biomes.addAll(model.getBiomes());
				} else {
					biomes.add("#" + getModdedDim(model.getDimension()) + ":is_" + getModdedDim(model.getDimension()));
				}
				features.add(Reference.MOD_ID + ":" + model.getName());
				new GenericFeatureBuilder("forge:add_features", "underground_ores")
						.biomes(biomes)
						.features(features)
						.save(consumer, new ResourceLocation(Reference.MOD_ID, model.getName() + "_ore_features"));
			}
		}
	}

	@Override
	public String getName() {
		return "Emendatus Enigmatica Features";
	}

	private String getModdedDim(String dim) {
		return StringUtils.substringBefore(dim, ":");
	}
}
