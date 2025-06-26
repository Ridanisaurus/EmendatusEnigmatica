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

package com.ridanisaurus.emendatusenigmatica.api.validation.validators;

import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationData;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.FilterMode;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @apiNote Name is a subject to change. Currently, this validator only supports {@link Types#INTEGER}.
 */
public class NumberValuesValidator extends TypeValidator {
    private final List<Integer> values;
    private final String valuesAsString;
    private final FilterMode mode;

    /**
     * Constructs a ValuesValidator, with specified values and mode of the operation.
     * @param values Values to check for.
     * @param validatorMode Determines the mode of the operation.
     * @param isRequired Determines if the field is required. If true, an error will be issued if the field is missing.
     * @see NumberValuesValidator Documentation of the validator
     * @see FilterMode Available modes
     */
    public NumberValuesValidator(@NotNull List<Integer> values, FilterMode validatorMode, boolean isRequired) {
        super(Types.INTEGER, isRequired);
        this.values = values;
        this.valuesAsString = StringUtils.join(values, ", ");
        this.mode = validatorMode;
    }

    /**
     * Validate method, used to validate passed in object.
     *
     * @param data ValidationData record with necessary information to validate the element.
     * @return True of the validation passes, false otherwise.
     * @apiNote Even tho it's public, this method should <i>never</i> be called directly! Call {@link NumberValuesValidator#apply(ValidationData)} instead!
     */
    @Override
    public Boolean validate(@NotNull ValidationData data) {
        if (!super.validate(data)) return false;
        int value = data.validationElement().getAsInt();
        boolean contains = values.contains(value);
        if (mode == FilterMode.WHITELIST) {
            if (contains) return true;
            Analytics.error("Field contains an illegal value!", "Provided: <code>%d</code> , Accepted values: <code>%s</code>".formatted(value, valuesAsString), data);
            return false;
        }
        if (!contains) return true;
        Analytics.error("Field contains one of the illegal values!", "Provided: <code>%d</code> , Illegal values: <code>%s</code>".formatted(value, valuesAsString), data);
        return false;
    }
}
