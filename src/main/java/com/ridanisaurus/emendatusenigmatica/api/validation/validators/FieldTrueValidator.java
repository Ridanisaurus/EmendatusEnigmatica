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

import com.google.gson.JsonElement;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationData;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationHelper;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayHandlingPolicy;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * A validator wrapper which handles the requirement and ArrayHandlingPolicy of a field,
 * based on a value of a second boolean field.
 * @see FieldTrueValidator#FieldTrueValidator(String, IValidationFunction, boolean) FieldTrueValidator(...) for more details.
 */
public class FieldTrueValidator implements IValidationFunction {
    private final IValidationFunction validator;
    private final String field;
    private final boolean optional;

    /**
     * Constructs FieldTrueValidator.
     *
     * @param field Name of the field to check.
     * @param validator Validator to run after check.
     * @param optional Determines if this field is optional.
     * @see FieldTrueValidator Documentation of the validator.
     * @apiNote
     * <ul>
     * <li><code>optional</code> determines if this validator should skip generation of an error, if the validated field is missing, but boolean field value is <code>true</code>.</li>
     * <li>If <code>optional</code> is set to <code>false</code>, this validator will update the {@link ArrayHandlingPolicy} to <code>allowEmpty</code> -> <code>false</code>.</li>
     * </ul>
     */
    public FieldTrueValidator(String field, IValidationFunction validator, boolean optional) {
        this.validator = validator;
        this.optional = optional;
        this.field = field;
    }

    /**
     * Constructs FieldTrueValidator.
     *
     * @param field Name of the field to check.
     * @param validator Validator to run after check.
     * @see FieldTrueValidator Documentation of the validator.
     */
    public FieldTrueValidator(String field, IValidationFunction validator) {
        this(field, validator, false);
    }

    /**
     * Entry point of the validator.
     *
     * @param data ValidationData record with necessary information to validate the element.
     * @return True if the validation passes, false otherwise.
     */
    @Override
    public Boolean apply(@NotNull ValidationData data) {
        JsonElement booleanField;
        String booleanFieldPath;
        if (field.startsWith("root")) {
            booleanField = ValidationHelper.getElementFromPathAs(data.rootObject(), field, Types.BOOLEAN);
            booleanFieldPath = field;
        } else {
            booleanField = data.getParentFieldAs(Types.BOOLEAN, field);
            booleanFieldPath = data.getParentFieldPath(field);
        }

        JsonElement element = data.validationElement();

        if (Objects.isNull(element)) {
            if (!optional && Objects.nonNull(booleanField) && booleanField.getAsBoolean()) {
                Analytics.error(
                    "This field is required!",
                    "Field <code>%s</code> is set to <code>true</code>, which makes this field necessary.".formatted(booleanFieldPath)
                    , data
                );
                return false;
            }
            return true;
        }

        if (Objects.isNull(booleanField))
            Analytics.warn(
                "This field is unnecessary!",
                "Field <code>%s</code> needs to be present and set to <code>true</code> for this field to have any effect.".formatted(booleanFieldPath),
                data
            );
        else if (!booleanField.getAsBoolean())
            Analytics.warn(
                "This field is unnecessary!",
                "Field <code>%s</code> needs to be set to <code>true</code> for this field to have any effect.".formatted(booleanFieldPath),
                data
            );
        else if (!optional)
            return validator.apply(new ValidationData(
                data.validationElement(),
                data.rootObject(),
                data.currentPath(),
                data.jsonFilePath(),
                data.arrayPolicy().getLegacyArrayPolicy().getNonEmpty())
            );

        return validator.apply(data);
    }
}
