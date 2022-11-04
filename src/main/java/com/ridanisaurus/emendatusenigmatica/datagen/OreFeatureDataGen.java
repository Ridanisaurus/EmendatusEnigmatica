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
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;

import java.util.Map;
import java.util.function.Consumer;

public class OreFeatureDataGen extends GenericJSONProvider{

	public OreFeatureDataGen(DataGenerator gen) {
		super(gen);
	}

	// TODO: Loop through the registered placed features, and populate the .feature() with them
	// TODO: Implement the Blacklist system
	// TODO: Once ore generation is done, look into implementing the Dimensions

	@Override
	protected void buildGenericJSON(Consumer<IFinishedGenericJSON> consumer) {
		new GenericJSONBuilder("type", "forge:add_features")
				.fieldString("biomes", "#minecraft:is_overworld")
				.feature("emendatusenigmatica:sphere_overworld_galena_ore_deposit")
				.feature("emendatusenigmatica:geode_overworld_galena_ore_deposit")
				.fieldString("step", "underground_ores")
				.save(consumer, new ResourceLocation(Reference.MOD_ID, "add_overworld_ore_features"));

		new GenericJSONBuilder("type", "forge:add_features")
				.fieldString("biomes", "#minecraft:is_nether")
				.feature("emendatusenigmatica:sphere_nether_uranium_ore_deposit")
				.fieldString("step", "underground_ores")
				.save(consumer, new ResourceLocation(Reference.MOD_ID, "add_nether_ore_features"));

		new GenericJSONBuilder("type", "forge:add_features")
				.fieldString("biomes", "#minecraft:is_end")
				.feature("emendatusenigmatica:geode_end_vanilla_ore_deposit")
				.fieldString("step", "underground_ores")
				.save(consumer, new ResourceLocation(Reference.MOD_ID, "add_end_ore_features"));
	}

	@Override
	public String getName() {
		return "Emendatus Enigmatica Ore Features";
	}
}
