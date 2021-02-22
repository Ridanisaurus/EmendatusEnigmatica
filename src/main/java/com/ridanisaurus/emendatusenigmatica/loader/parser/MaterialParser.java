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

package com.ridanisaurus.emendatusenigmatica.loader.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialDimModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialPropertiesModel;

import java.util.ArrayList;
import java.util.List;

public class MaterialParser {
  public MaterialModel parse(JsonObject object) {
    String id = object.get("id").getAsString();
    String localisedName = object.get("localisedName").getAsString();
    List<String> processedType = new ArrayList<>();
    JsonArray processedTypeJson = object.getAsJsonArray("processedType");
    for (JsonElement element : processedTypeJson) {
      processedType.add(element.getAsString());
    }
    // Will need to get value from Enum
    String oreBlockType = object.get("oreBlockType").getAsString();

    JsonObject propertiesJson = object.get("properties").getAsJsonObject();
    float hardness = propertiesJson.get("hardness").getAsFloat();
    float resistance = propertiesJson.get("resistance").getAsFloat();
    int harvestLevel = propertiesJson.get("harvestLevel").getAsInt();

    MaterialPropertiesModel materialProperties = new MaterialPropertiesModel(hardness, resistance, harvestLevel);

    String defaultItemDrop = "";
    int dropMin = 1;
    int dropMax = 1;
    if(oreBlockType.equals("gem")) {
      defaultItemDrop = object.get("defaultItemDrop").getAsString();
      dropMin = object.get("dropMin").getAsInt();
      dropMax = object.get("dropMax").getAsInt();
    }

    List<MaterialDimModel> dimensions = new ArrayList<>();
    JsonArray dimJson = object.getAsJsonArray("dimensions");
    for (JsonElement element : dimJson) {
      JsonObject dimModelJson = element.getAsJsonObject();
      String dim = dimModelJson.get("dim").getAsString();
      int size = dimModelJson.get("size").getAsInt();
      int count = dimModelJson.get("count").getAsInt();
      int baseline = dimModelJson.get("baseline").getAsInt();
      int spread = dimModelJson.get("spread").getAsInt();

      dimensions.add(new MaterialDimModel(dim, size, count, baseline, spread));
    }
    return new MaterialModel(id, localisedName, processedType, oreBlockType, materialProperties, defaultItemDrop, dropMin, dropMax, dimensions);
  }
}
