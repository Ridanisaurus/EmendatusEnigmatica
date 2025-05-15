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

package com.ridanisaurus.emendatusenigmatica.plugin.validators;

import com.google.gson.JsonElement;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationData;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationHelper;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.IValidationFunction;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class FieldSetValidator implements IValidationFunction {
    private final IValidationFunction validator;
    private final boolean optional;
    private final String field;
    private final String value;

    /**
     * Constructs FieldSetValidator.
     *
     * @param field     Name of the field to check.
     * @param value     Value of the field.
     * @param validator Validator to run after check.
     * @param optional  Should the validator only issue warnings about field being unnecessary, or mark the field as required?
     * @see FieldSetValidator Documentation of the validator.
     */
    public FieldSetValidator(String field, String value, IValidationFunction validator, boolean optional) {
        this.validator = validator;
        this.optional = optional;
        this.field = field;
        this.value = value;
    }

    /**
     * Constructs FieldSetValidator.
     *
     * @param field     Name of the field to check.
     * @param validator Validator to run after check.
     * @see FieldSetValidator Documentation of the validator.
     */
    public FieldSetValidator(String field, String value, IValidationFunction validator) {
        this(field, value, validator, false);
    }

    /**
     * Entry point of the validator.
     *
     * @param data ValidationData record with necessary information to validate the element.
     * @return True if the validation passes, false otherwise.
     */
    @Override
    public Boolean apply(@NotNull ValidationData data) {
        JsonElement stringField;
        String stringFieldPath;
        if (field.startsWith("root")) {
            stringField = ValidationHelper.getElementFromPathAs(data.rootObject(), field, Types.STRING);
            stringFieldPath = field;
        } else {
            stringField = data.getParentFieldAs(Types.STRING, field);
            stringFieldPath = data.getParentFieldPath(field);
        }

        JsonElement element = data.validationElement();

        if (Objects.isNull(element)) {
            if (!optional && Objects.nonNull(stringField) && stringField.getAsString().equals(value)) {
                Analytics.error(
                    "This field is required!",
                    "Field <code>%s</code> is set to <code>%s</code>, which makes this field necessary.".formatted(stringFieldPath, value)
                    , data
                );
                return false;
            }
            return true;
        }

        if (Objects.isNull(stringField))
            Analytics.warn("This field is unnecessary!", "Field <code>%s</code> needs to be present and set to <code>%s</code> for this field to have any effect.".formatted(stringFieldPath, value), data);
        else if (!stringField.getAsString().equals(value))
            Analytics.warn("This field is unnecessary!", "Field <code>%s</code> needs to be set to <code>%s</code> for this field to have any effect.".formatted(stringFieldPath, value), data);

        return validator.apply(data);
    }
}
