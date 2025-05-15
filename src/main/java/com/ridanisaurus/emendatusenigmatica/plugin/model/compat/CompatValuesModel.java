/*
 * MIT License
 *
 * Copyright (c) 2024. Ridanisaurus
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.ridanisaurus.emendatusenigmatica.plugin.model.compat;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayPolicy;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.compat.CompatTypeValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.compat.CompatValueInputValidator;
import java.util.*;

public class CompatValuesModel {
    public static final Codec<CompatValuesModel> CODEC = RecordCodecBuilder.create(x -> x.group(
            Codec.STRING.fieldOf("type").forGetter(i -> i.type),
            Codec.list(CompatIOModel.CODEC).fieldOf("input").orElse(List.of()).forGetter(i -> i.input),
            Codec.list(CompatIOModel.CODEC).fieldOf("output").orElse(List.of()).forGetter(i -> i.output)
    ).apply(x, CompatValuesModel::new));

    public static final ValidationManager VALIDATION_MANAGER = ValidationManager.create()
        .addValidator("type", new CompatTypeValidator())
        .addValidator("input", new CompatValueInputValidator(), ArrayPolicy.REQUIRES_ARRAY)
        .addValidator("output", CompatIOModel.VALIDATION_MANAGER.getAsValidator(false), ArrayPolicy.REQUIRES_ARRAY);

    private final String type;
    private final List<CompatIOModel> input;
    private final List<CompatIOModel> output;

    CompatValuesModel(String type, List<CompatIOModel> input, List<CompatIOModel> output) {
        this.type = type;
        this.input = input;
        this.output = output;
    }

    public String getType() {
        return type;
    }

    public List<CompatIOModel> getInput() {
        return input;
    }

    public List<CompatIOModel> getOutput() {
        return output;
    }
}
