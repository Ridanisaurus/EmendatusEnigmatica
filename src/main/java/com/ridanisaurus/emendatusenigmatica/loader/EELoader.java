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

package com.ridanisaurus.emendatusenigmatica.loader;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.CompatModel;
import com.ridanisaurus.emendatusenigmatica.registries.EEBloodMagicRegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EECreateRegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EEMekanismRegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.FileHelper;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.nio.file.Path;
import java.util.*;

public class EELoader {
	public static final List<MaterialModel> MATERIALS = new ArrayList<>();
	public static final List<StrataModel> STRATA = new ArrayList<>();
	public static final List<CompatModel> COMPAT = new ArrayList<>();
	public static final Map<String, Integer> STRATA_INDEX_BY_FILLER = new HashMap<>();

	public static void load() {
		// Set the path to the defined folder
		Path configDir = FMLPaths.CONFIGDIR.get().resolve("emendatusenigmatica/");

		// Check if the folder exists
		if (!configDir.toFile().exists() && configDir.toFile().mkdirs()) {
			EmendatusEnigmatica.LOGGER.info("Created /config/emendatusenigmatica/");
		}

		File strataDir = configDir.resolve("strata/").toFile();
		if (!strataDir.exists() && strataDir.mkdirs()) {
			EmendatusEnigmatica.LOGGER.info("Created /config/emendatusenigmatica/strata/");
		}

		File materialDir = configDir.resolve("material/").toFile();
		if (!materialDir.exists() && materialDir.mkdirs()) {
			EmendatusEnigmatica.LOGGER.info("Created /config/emendatusenigmatica/material/");
		}

		File compatDir = configDir.resolve("compat/").toFile();
		if (!compatDir.exists() && compatDir.mkdirs()) {
			EmendatusEnigmatica.LOGGER.info("Created /config/emendatusenigmatica/compat/");
		}

		ArrayList<JsonObject> strataDefinition = FileHelper.loadFilesAsJsonObjects(strataDir);
		ArrayList<JsonObject> materialDefinition = FileHelper.loadFilesAsJsonObjects(materialDir);
		ArrayList<JsonObject> compatDefinition = FileHelper.loadFilesAsJsonObjects(compatDir);

		ArrayList<StrataModel> strataModels = new ArrayList<>();
		for (JsonObject jsonObject : strataDefinition) {
			Optional<Pair<StrataModel, JsonElement>> result = JsonOps.INSTANCE.withDecoder(StrataModel.CODEC).apply(jsonObject).result();
			if (!result.isPresent()) {
				continue;
			}
			StrataModel strataModel = result.get().getFirst();
			strataModels.add(strataModel);
			STRATA.add(strataModel);
			STRATA_INDEX_BY_FILLER.put(strataModel.getFillerType().toString(), STRATA.size() - 1);
		}

		ArrayList<MaterialModel> materialModels = new ArrayList<>();
		for (JsonObject jsonObject : materialDefinition) {
			Optional<Pair<MaterialModel, JsonElement>> result = JsonOps.INSTANCE.withDecoder(MaterialModel.CODEC).apply(jsonObject).result();
			if (!result.isPresent()) {
				continue;
			}
			MaterialModel materialModel = result.get().getFirst();
			materialModels.add(materialModel);
			MATERIALS.add(materialModel);
		}

		ArrayList<CompatModel> compatModels = new ArrayList<>();
		for (JsonObject jsonObject : compatDefinition) {
			Optional<Pair<CompatModel, JsonElement>> result = JsonOps.INSTANCE.withDecoder(CompatModel.CODEC).apply(jsonObject).result();
			if (!result.isPresent()) {
				continue;
			}
			CompatModel compatModel = result.get().getFirst();
			compatModels.add(compatModel);
			COMPAT.add(compatModel);
		}

		for (StrataModel strata : strataModels) {
			for (MaterialModel material : materialModels) {
				if (material.getProcessedTypes().contains("ore")) {
					EERegistrar.registerOre(strata, material);
				}
			}
		}

		for (MaterialModel material : materialModels) {
			if (material.getProcessedTypes().contains("storage_block")) {
				EERegistrar.registerStorageBlocks(material);
			}
			if (material.getProcessedTypes().contains("raw")) {
				EERegistrar.registerRaws(material);
				EERegistrar.registerRawBlocks(material);
			}
			if (material.getProcessedTypes().contains("ingot")) {
				EERegistrar.registerIngots(material);
			}
			if (material.getProcessedTypes().contains("nugget")) {
				EERegistrar.registerNuggets(material);
			}
			if (material.getProcessedTypes().contains("gem")) {
				EERegistrar.registerGems(material);
			}
			if (material.getProcessedTypes().contains("dust")) {
				EERegistrar.registerDusts(material);
			}
			if (material.getProcessedTypes().contains("plate")) {
				EERegistrar.registerPlates(material);
			}
			if (material.getProcessedTypes().contains("gear")) {
				EERegistrar.registerGears(material);
			}
			if (material.getProcessedTypes().contains("rod")) {
				EERegistrar.registerRods(material);
			}
			if (material.getProcessedTypes().contains("fluid")) {
				EERegistrar.registerFluids(material);
			}
			if (EmendatusEnigmatica.MEKANISM_LOADED) {
				if (material.getProcessedTypes().contains("slurry")) {
					EEMekanismRegistrar.registerSlurries(material);
				}
				if (material.getProcessedTypes().contains("crystal")) {
					EEMekanismRegistrar.registerCrystals(material);
				}
				if (material.getProcessedTypes().contains("shard")) {
					EEMekanismRegistrar.registerShards(material);
				}
				if (material.getProcessedTypes().contains("clump")) {
					EEMekanismRegistrar.registerClumps(material);
				}
				if (material.getProcessedTypes().contains("dirty_dust")) {
					EEMekanismRegistrar.registerDirtyDusts(material);
				}
			}
			if (EmendatusEnigmatica.CREATE_LOADED) {
				if (material.getProcessedTypes().contains("crushed_ore")) {
					EECreateRegistrar.registerCrushedOres(material);
				}
			}
			if (EmendatusEnigmatica.BLOODMAGIC_LOADED) {
				if (material.getProcessedTypes().contains("fragment")) {
					EEBloodMagicRegistrar.registerFragments(material);
				}
				if (material.getProcessedTypes().contains("gravel")) {
					EEBloodMagicRegistrar.registerGravels(material);
				}
			}
		}
	}
}
