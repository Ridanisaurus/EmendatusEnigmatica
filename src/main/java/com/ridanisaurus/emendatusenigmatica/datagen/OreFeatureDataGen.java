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
import com.ridanisaurus.emendatusenigmatica.util.WorldGenHelper;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class OreFeatureDataGen extends GenericFeatureProvider {

	public OreFeatureDataGen(DataGenerator gen) {
		super(gen);
	}

	// TODO: Once ore generation is done, look into implementing the Dimensions

	@Override
	protected void buildGenericJSON(Consumer<IFinishedGenericJSON> consumer) {
		for (IDepositProcessor processor : EEDeposits.ACTIVE_PROCESSORS) {
			CommonDepositModelBase model = processor.getModel();
			List<String> biomes = new ArrayList<>();
			List<String> features = new ArrayList<>();
			if(model.getDimensions().contains("minecraft:overworld")) {
				if(!model.getWhitelistBiomes().isEmpty()) {
					biomes.addAll(model.getWhitelistBiomes());
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
				if(!model.getWhitelistBiomes().isEmpty()) {
					biomes.addAll(model.getWhitelistBiomes());
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
				if(!model.getWhitelistBiomes().isEmpty()) {
					biomes.addAll(model.getWhitelistBiomes());
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
				if(!model.getWhitelistBiomes().isEmpty()) {
					biomes.addAll(model.getWhitelistBiomes());
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
		return "Emendatus Enigmatica Ore Features";
	}
}
