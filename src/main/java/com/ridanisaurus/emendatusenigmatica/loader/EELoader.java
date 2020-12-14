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
import com.ridanisaurus.emendatusenigmatica.loader.parser.StrataParser;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
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

    ArrayList<JsonObject> strataDefinition = loadFilesAsJsonObjects(strataDir);

    ArrayList<StrataModel> strataModels = new ArrayList<>();
    for (JsonObject jsonObject : strataDefinition) {
      strataModels.add(STRATA_PARSER.parse(jsonObject));
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
