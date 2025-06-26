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
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.FilterMode;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.ValuesValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.compat.CompatMachineValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.compat.CompatModData;
import java.util.*;

public class CompatRecipesModel {
    public static final Codec<CompatRecipesModel> CODEC = RecordCodecBuilder.create(x -> x.group(
            Codec.STRING.fieldOf("mod").forGetter(i -> i.mod),
            Codec.STRING.fieldOf("machine").forGetter(i -> i.machine),
            Codec.list(CompatValuesModel.CODEC).fieldOf("values").forGetter(i -> i.values)
    ).apply(x, CompatRecipesModel::new));

    public static final ValidationManager VALIDATION_MANAGER = ValidationManager.create()
        .addValidator("mod",        new ValuesValidator(CompatModData.CompatMachineMap.keySet(), FilterMode.WHITELIST, true))
        .addValidator("values",     CompatValuesModel.VALIDATION_MANAGER.getAsValidator(true))
        .addValidator("machine",    new CompatMachineValidator(true));

    private final String mod;
    private final String machine;
    private final List<CompatValuesModel> values;

    public CompatRecipesModel(String mod, String machine, List<CompatValuesModel> values) {
        this.mod = mod;
        this.machine = machine;
        this.values = values;
    }

    public CompatRecipesModel() {
        this.mod = "";
        this.machine = "";
        this.values = List.of();
    }

    public String getMod() {
        return mod;
    }

    public String getMachine() {
        return machine;
    }

    public List<CompatValuesModel> getValues() {
        return values;
    }
}
