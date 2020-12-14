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

import com.google.gson.JsonObject;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import net.minecraft.util.ResourceLocation;

public class StrataParser implements IParser<StrataModel> {

  @Override
  public StrataModel parse(JsonObject object) {
    String id = object.get("id").getAsString();
    String baseTexture = object.get("baseTexture").getAsString();
    String suffix = object.get("suffix").getAsString();
    String fillerType = object.get("fillerType").getAsString();

    ResourceLocation baseTextureRL = ResourceLocation.create(baseTexture, ':');
    ResourceLocation fillerTypeRL = ResourceLocation.create(fillerType, ':');

    return new StrataModel(id, baseTextureRL, suffix, fillerTypeRL);
  }
}