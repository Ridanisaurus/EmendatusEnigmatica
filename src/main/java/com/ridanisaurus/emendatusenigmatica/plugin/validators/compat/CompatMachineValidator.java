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

import com.google.gson.JsonElement;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationContext;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.TypeValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.ValuesValidator;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CompatMachineValidator extends TypeValidator {
    private static final Map<String, String> valuesAsString = new HashMap<>();

    /**
     * Constructs a CompatMachineValidator, a modified version off the {@link ValuesValidator}.
     * @param isRequired Determines if the field is required. If true, an error will be issued if the field is missing.
     * @see CompatMachineValidator Documentation of the validator
     */
    public CompatMachineValidator(boolean isRequired) {
        super(Types.STRING, isRequired);
    }

    /**
     * Validate method, used to validate passed in object.
     *
     * @param data ValidationContext record with necessary information to validate the element.
     * @return True of the validation passes, false otherwise.
     * @apiNote Even tho it's public, this method should <i>never</i> be called directly! Call {@link CompatMachineValidator#apply(ValidationContext)} instead!
     */
    @Override
    public Boolean validate(@NotNull ValidationContext data) {
        if (!super.validate(data)) return false;
        JsonElement machineElement = data.getParentFieldAs(Types.STRING, "machine");
        String accepted = "None, most likely invalid <code>%s</code> value.".formatted(data.getParentFieldPath("machine"));
        String value = data.validationElement().getAsString();
        boolean contains = false;
        if (Objects.nonNull(machineElement)) {
            var values = CompatModData.CompatMachineMap.get(machineElement.getAsString());
            if (Objects.nonNull(values)) {
                contains = values.contains(value);
                accepted = valuesAsString.computeIfAbsent(machineElement.getAsString(), it -> "<code>%s</code>".formatted(String.join(", ", values)));
            }
        }
        if (contains) return true;
        Analytics.error("Field contains an illegal value!", "Provided: <code>%s</code> , Accepted values: %s".formatted(value, accepted), data);
        return false;
    }
}
