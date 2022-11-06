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

import com.ridanisaurus.emendatusenigmatica.loader.deposit.EEDeposits;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonDepositModelBase;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class OreFeatureDataGen extends GenericFeatureProvider {

	public OreFeatureDataGen(DataGenerator gen) {
		super(gen);
	}

	private final List<String> OVERWORLD_COAL_ORE = List.of("minecraft:ore_coal_upper", "minecraft:ore_coal_lower");
	private final List<String> OVERWORLD_COPPER_ORE = List.of("minecraft:ore_copper", "minecraft:ore_copper_large");
	private final List<String> OVERWORLD_IRON_ORE = List.of("minecraft:ore_iron_upper", "minecraft:ore_iron_middle", "minecraft:ore_iron_small");
	private final List<String> OVERWORLD_GOLD_ORE = List.of("minecraft:ore_gold_extra", "minecraft:ore_gold", "minecraft:ore_gold_lower");
	private final List<String> OVERWORLD_REDSTONE_ORE = List.of("minecraft:ore_redstone", "minecraft:ore_redstone_lower");
	private final List<String> OVERWORLD_LAPIS_ORE = List.of("minecraft:ore_lapis", "minecraft:ore_lapis_buried");
	private final List<String> OVERWORLD_DIAMOND_ORE = List.of("minecraft:ore_diamond", "minecraft:ore_diamond_large", "minecraft:ore_diamond_buried");
	private final List<String> OVERWORLD_EMERALD_ORE = List.of("minecraft:ore_emerald");

	private final List<String> NETHER_GOLD_ORE = List.of("minecraft:ore_gold_nether", "minecraft:ore_gold_deltas");
	private final List<String> NETHER_QUARTZ_ORE = List.of("minecraft:ore_quartz_nether", "minecraft:ore_quartz_deltas");

	private final List<String> OVERWORLD_GEODE = List.of("minecraft:amethyst_geode");

	@Override
	protected void buildGenericJSON(Consumer<IFinishedGenericJSON> consumer) {

		if (true) {
			new GenericFeatureBuilder("forge:remove_features", "underground_ores")
					.biome("#minecraft:is_overworld")
					.features(OVERWORLD_COAL_ORE)
					.save(consumer, new ResourceLocation(Reference.MOD_ID, "remove_overworld_coal_ore"));
		}
		if (true) {
			new GenericFeatureBuilder("forge:remove_features", "underground_ores")
					.biome("#minecraft:is_overworld")
					.features(OVERWORLD_COPPER_ORE)
					.save(consumer, new ResourceLocation(Reference.MOD_ID, "remove_overworld_copper_ore"));
		}
		if (true) {
			new GenericFeatureBuilder("forge:remove_features", "underground_ores")
					.biome("#minecraft:is_overworld")
					.features(OVERWORLD_IRON_ORE)
					.save(consumer, new ResourceLocation(Reference.MOD_ID, "remove_overworld_iron_ore"));
		}
		if (true) {
			new GenericFeatureBuilder("forge:remove_features", "underground_ores")
					.biome("#minecraft:is_overworld")
					.features(OVERWORLD_GOLD_ORE)
					.save(consumer, new ResourceLocation(Reference.MOD_ID, "remove_overworld_gold_ore"));
		}
		if (true) {
			new GenericFeatureBuilder("forge:remove_features", "underground_ores")
					.biome("#minecraft:is_overworld")
					.features(OVERWORLD_REDSTONE_ORE)
					.save(consumer, new ResourceLocation(Reference.MOD_ID, "remove_overworld_redstone_ore"));
		}
		if (true) {
			new GenericFeatureBuilder("forge:remove_features", "underground_ores")
					.biome("#minecraft:is_overworld")
					.features(OVERWORLD_LAPIS_ORE)
					.save(consumer, new ResourceLocation(Reference.MOD_ID, "remove_overworld_lapis_ore"));
		}
		if (true) {
			new GenericFeatureBuilder("forge:remove_features", "underground_ores")
					.biome("#minecraft:is_overworld")
					.features(OVERWORLD_DIAMOND_ORE)
					.save(consumer, new ResourceLocation(Reference.MOD_ID, "remove_overworld_diamond_ore"));
		}
		if (true) {
			new GenericFeatureBuilder("forge:remove_features", "underground_ores")
					.biome("#minecraft:is_overworld")
					.features(OVERWORLD_EMERALD_ORE)
					.save(consumer, new ResourceLocation(Reference.MOD_ID, "remove_overworld_emerald_ore"));
		}
		if (true) {
			new GenericFeatureBuilder("forge:remove_features", "underground_ores")
					.biome("#minecraft:is_nether")
					.features(NETHER_GOLD_ORE)
					.save(consumer, new ResourceLocation(Reference.MOD_ID, "remove_nether_gold_ore"));
		}
		if (true) {
			new GenericFeatureBuilder("forge:remove_features", "underground_ores")
					.biome("#minecraft:is_nether")
					.features(NETHER_QUARTZ_ORE)
					.save(consumer, new ResourceLocation(Reference.MOD_ID, "remove_nether_quartz_ore"));
		}
		if (true) {
			new GenericFeatureBuilder("forge:remove_features", "underground_ores")
					.biome("#minecraft:is_overworld")
					.features(OVERWORLD_GEODE)
					.save(consumer, new ResourceLocation(Reference.MOD_ID, "remove_overworld_geode"));
		}


		for (IDepositProcessor processor : EEDeposits.ACTIVE_PROCESSORS) {
			CommonDepositModelBase model = processor.getCommonModel();
			List<String> biomes = new ArrayList<>();
			List<String> features = new ArrayList<>();
			if(model.getDimensions().contains("minecraft:overworld")) {
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
			if(model.getDimensions().contains("minecraft:the_nether")) {
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
			if(model.getDimensions().contains("minecraft:the_end")) {
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
			// TODO: Add mod to Model (i.e. "mod":"undergarden") or obtain its ModID somehow
			if(!model.getDimensions().contains("minecraft:overworld") && !model.getDimensions().contains("minecraft:the_nether") && !model.getDimensions().contains("minecraft:the_end")) {
				if(!model.getBiomes().isEmpty()) {
					biomes.addAll(model.getBiomes());
				} else {
					biomes.add("#undergarden:is_undergarden");
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
}
