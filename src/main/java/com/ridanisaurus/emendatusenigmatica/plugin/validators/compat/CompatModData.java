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

package com.ridanisaurus.emendatusenigmatica.plugin.validators.compat;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.ridanisaurus.emendatusenigmatica.plugin.model.compat.CompatValuesModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.compat.CompatRecipesModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompatModData {
    //TODO: Move addition of entries to addons.
    /**
     * This map holds possible values for {@link CompatValuesModel#type} based on the {@link CompatRecipesModel#mod} value.
     */
    public static final Map<String, List<String>> CompatTypeMap = new HashMap<>(Map.of(
        "thermal", List.of("ore", "raw", "alloy"),
        "create", List.of("ore", "crushed_ore")
    ));

    /**
     * This map holds possible values for {@link CompatRecipesModel#machine} based on the {@link CompatRecipesModel#mod} value.
     */
    public static final Map<String, List<String>> CompatMachineMap = new HashMap<>(Map.of(
        "thermal", List.of("pulverizer", "induction_smelter"),
        "create", List.of("crushing_wheels", "fan_washing")
    ));

    /**
     * This table holds possible combinations, to determine when {@link CompatValuesModel#input} is required.<br>
     * Table Format:<br>
     * - Row Key > {@link CompatRecipesModel#mod}
     * - Column Key > {@link CompatRecipesModel#machine}
     * - Value > {@link CompatValuesModel#type}
     */
    public static final Table<String, String, String> VALUES = HashBasedTable.create(
        ImmutableTable.of("thermal", "induction_smelter", "alloy")
    );
}
