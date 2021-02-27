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

package com.ridanisaurus.emendatusenigmatica.loader.parser.model;

import java.util.List;

public class MaterialModel {
  private final String id;
  private final String localisedName;
  private final List<String> processedType;
  private final boolean isBurnable;
  private final int burnTime;
  private final String oreBlockType;
  private final MaterialPropertiesModel properties;
  private final String defaultItemDrop;
  private final int dropMin;
  private final int dropMax;
  private final boolean useCustomWorldGen;
  private final List<MaterialDimModel> dimensions;

  public MaterialModel(String id, String localisedName, List<String> processedType, boolean isBurnable, int burnTime, String oreBlockType, MaterialPropertiesModel properties, String defaultItemDrop, int dropMin, int dropMax, boolean useCustomWorldGen, List<MaterialDimModel> dimensions) {

    this.id = id;
    this.localisedName = localisedName;
    this.processedType = processedType;
    this.isBurnable = isBurnable;
    this.burnTime = burnTime;
    this.oreBlockType = oreBlockType;
    this.properties = properties;
    this.defaultItemDrop = defaultItemDrop;
    this.dropMin = dropMin;
    this.dropMax = dropMax;
    this.useCustomWorldGen = useCustomWorldGen;
    this.dimensions = dimensions;
  }

  public String getId() {
    return id;
  }

  public String getLocalisedName() {
    return localisedName;
  }

  public List<String> getProcessedType() {
    return processedType;
  }

  public String getOreBlockType() {
    return oreBlockType;
  }

  public String getDefaultItemDrop() {
    return defaultItemDrop;
  }

  public List<MaterialDimModel> getDimensions() {
    return dimensions;
  }

  public MaterialPropertiesModel getProperties() {
    return properties;
  }

  public int getDropMin() {
    return dropMin;
  }

  public int getDropMax() {
    return dropMax;
  }

  public boolean isBurnable() {
    return isBurnable;
  }

  public int getBurnTime() {
    return burnTime;
  }

  public boolean isUseCustomWorldGen() {
    return useCustomWorldGen;
  }
}
