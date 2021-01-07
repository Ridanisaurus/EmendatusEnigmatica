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
import com.google.gson.JsonParser;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.loader.parser.AlloyParser;
import com.ridanisaurus.emendatusenigmatica.loader.parser.MaterialParser;
import com.ridanisaurus.emendatusenigmatica.loader.parser.StrataParser;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.AlloyModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;

public class EELoader {
  private static final StrataParser STRATA_PARSER = new StrataParser();
  private static final MaterialParser MATERIAL_PARSER = new MaterialParser();
  private static final AlloyParser ALLOY_PARSER = new AlloyParser();

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

    ArrayList<JsonObject> strataDefinition = loadFilesAsJsonObjects(strataDir);
    ArrayList<JsonObject> materialDefinition = loadFilesAsJsonObjects(materialDir);
    ArrayList<JsonObject> alloyDefinition = loadFilesAsJsonObjects(alloyDir);

    ArrayList<StrataModel> strataModels = new ArrayList<>();
    for (JsonObject jsonObject : strataDefinition) {
      strataModels.add(STRATA_PARSER.parse(jsonObject));
    }

    ArrayList<MaterialModel> materialModels = new ArrayList<>();
    for (JsonObject jsonObject : materialDefinition) {
      materialModels.add(MATERIAL_PARSER.parse(jsonObject));
    }

    ArrayList<AlloyModel> alloyModels = new ArrayList<>();
    for (JsonObject jsonObject : alloyDefinition) {
      alloyModels.add(ALLOY_PARSER.parse(jsonObject));
    }

    for (StrataModel strata : strataModels) {
      for (MaterialModel material : materialModels) {
        // Do I need Ore if it's under Material and not Alloy?
        if (material.getProcessedType().contains("ore")) {
          EERegistrar.RegisterOre(strata, material);
        }
      }
    }

    for (MaterialModel material : materialModels) {
      if (material.getProcessedType().contains("storage_block")) {
        EERegistrar.RegisterStorageBlocks(material);
      }
      if (material.getProcessedType().contains("chunk")) {
        EERegistrar.RegisterChunks(material);
      }
      if (material.getProcessedType().contains("ingot")) {
        EERegistrar.RegisterIngots(material);
      }
      if (material.getProcessedType().contains("nugget")) {
        EERegistrar.RegisterNuggets(material);
      }
      if (material.getProcessedType().contains("gem")) {
        EERegistrar.RegisterGems(material);
      }
      if (material.getProcessedType().contains("dust")) {
        EERegistrar.RegisterDusts(material);
      }
      if (material.getProcessedType().contains("plate")) {
        EERegistrar.RegisterPlates(material);
      }
      if (material.getProcessedType().contains("gear")) {
        EERegistrar.RegisterGears(material);
      }
      if (material.getProcessedType().contains("rod")) {
        EERegistrar.RegisterRods(material);
      }
    }
  }

  public static ArrayList<JsonObject> loadFilesAsJsonObjects(File dir) {
    ArrayList<JsonObject> results = new ArrayList<>();
    File[] files = dir.listFiles((FileFilter) FileFilterUtils.suffixFileFilter(".json"));

    if (files == null || files.length <= 0) {
      return new ArrayList<>();
    }
    for (File file : files) {
      JsonObject resultEntry;
      FileReader reader = null;
      try {
        JsonParser parser = new JsonParser();
        reader = new FileReader(file);
        resultEntry = parser.parse(reader).getAsJsonObject();
        results.add(resultEntry);
      } catch (Exception e) {
        EmendatusEnigmatica.LOGGER.error("Failed to load configuration from " + dir.toString() + " in file " + file.getName());
      } finally {
        IOUtils.closeQuietly(reader);
      }
    }
    return results;
  }
}
