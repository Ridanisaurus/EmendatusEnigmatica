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

import com.google.gson.JsonObject;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.loader.parser.AlloyParser;
import com.ridanisaurus.emendatusenigmatica.loader.parser.MaterialParser;
import com.ridanisaurus.emendatusenigmatica.loader.parser.StrataParser;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.AlloyModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.FileIOHelper;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class EELoader {
  private static final StrataParser STRATA_PARSER = new StrataParser();
  private static final MaterialParser MATERIAL_PARSER = new MaterialParser();
  private static final AlloyParser ALLOY_PARSER = new AlloyParser();
  public static final List<MaterialModel> MATERIALS = new ArrayList<>();
  public static final List<StrataModel> STRATA = new ArrayList<>();

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

    File alloyDir = configDir.resolve("alloy/").toFile();
    if (!alloyDir.exists() && alloyDir.mkdirs()) {
      EmendatusEnigmatica.LOGGER.info("Created /config/emendatusenigmatica/alloy/");
    }

    ArrayList<JsonObject> strataDefinition = FileIOHelper.loadFilesAsJsonObjects(strataDir);
    ArrayList<JsonObject> materialDefinition = FileIOHelper.loadFilesAsJsonObjects(materialDir);
    ArrayList<JsonObject> alloyDefinition = FileIOHelper.loadFilesAsJsonObjects(alloyDir);

    ArrayList<StrataModel> strataModels = new ArrayList<>();
    for (JsonObject jsonObject : strataDefinition) {
      StrataModel strataModel = STRATA_PARSER.parse(jsonObject);
      strataModels.add(strataModel);
      STRATA.add(strataModel);
    }

    ArrayList<MaterialModel> materialModels = new ArrayList<>();
    for (JsonObject jsonObject : materialDefinition) {
      MaterialModel materialModel = MATERIAL_PARSER.parse(jsonObject);
      materialModels.add(materialModel);
      MATERIALS.add(materialModel);
    }

    ArrayList<AlloyModel> alloyModels = new ArrayList<>();
    for (JsonObject jsonObject : alloyDefinition) {
      alloyModels.add(ALLOY_PARSER.parse(jsonObject));
    }

    for (StrataModel strata : strataModels) {
      for (MaterialModel material : materialModels) {
        // Do I need Ore if it's under Material and not Alloy?
        if (material.getProcessedType().contains("ore")) {
          EERegistrar.registerOre(strata, material);
        }
      }
    }

    for (MaterialModel material : materialModels) {
      if (material.getProcessedType().contains("storage_block")) {
        EERegistrar.registerStorageBlocks(material);
      }
      if (material.getProcessedType().contains("chunk")) {
        EERegistrar.registerChunks(material);
      }
      if (material.getProcessedType().contains("ingot")) {
        EERegistrar.registerIngots(material);
      }
      if (material.getProcessedType().contains("nugget")) {
        EERegistrar.registerNuggets(material);
      }
      if (material.getProcessedType().contains("gem")) {
        EERegistrar.registerGems(material);
      }
      if (material.getProcessedType().contains("dust")) {
        EERegistrar.registerDusts(material);
      }
      if (material.getProcessedType().contains("plate")) {
        EERegistrar.registerPlates(material);
      }
      if (material.getProcessedType().contains("gear")) {
        EERegistrar.registerGears(material);
      }
      if (material.getProcessedType().contains("rod")) {
        EERegistrar.registerRods(material);
      }
    }
  }


}
