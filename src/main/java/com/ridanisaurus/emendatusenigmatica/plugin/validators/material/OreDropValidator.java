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

package com.ridanisaurus.emendatusenigmatica.plugin.validators.material;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationHelper;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationData;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.IValidationFunction;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialOreDropModel;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;

import java.util.Objects;

public class OreDropValidator implements IValidationFunction {
    private static final IValidationFunction validator = MaterialOreDropModel.VALIDATION_MANAGER.getAsValidator(false);

    /**
     * Constructs OreDropValidator.
     *
     * @implNote This validator accepts only {@link Types#STRING} values.
     * @see OreDropValidator Documentation of the validator.
     */
    public OreDropValidator() {}

    /**
     * Entry point of the validator.
     *
     * @param data ValidationData record with necessary information to validate the element.
     * @return True if the validation passes, false otherwise.
     */
    @Override
    public Boolean apply(ValidationData data) {
        JsonElement types = ValidationHelper.getElementFromPath(data.rootObject(), "root.processedTypes");
        boolean hasOre = false;
        boolean hasGem = false;
        boolean hasRaw = false;
        boolean hasVal = Objects.nonNull(data.validationElement());
        if (Objects.nonNull(types) && types.isJsonArray()) {
            var array = types.getAsJsonArray();
            hasOre = array.contains(new JsonPrimitive("ore"));
            hasGem = array.contains(new JsonPrimitive("gem"));
            hasRaw = array.contains(new JsonPrimitive("raw"));
        }

        if (hasVal) {
            if (!hasOre) Analytics.warn(
                "This field is unnecessary.",
                "Array <code>root.processedTypes</code> is missing an element <code>ore</code>, which is required for this field to have an effect.",
                data
            );
        } else if (!hasGem && !hasRaw && hasOre) {
            Analytics.error(
                "This field is required!",
                "Array <code>root.processedTypes</code> contains an element <code>ore</code> and misses <code>gem, raw</code>, which marks this field as required.",
                data
            );
            return false;
        }

        return validator.apply(data);
    }
}
