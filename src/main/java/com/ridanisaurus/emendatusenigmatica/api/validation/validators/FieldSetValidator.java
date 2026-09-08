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
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationContext;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationHelper;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayHandlingPolicy;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * A validator wrapper which handles the requirement and ArrayHandlingPolicy of a field,
 * based on a value of a second string field.
 * @see FieldSetValidator#FieldSetValidator(String, String, IValidationFunction, boolean) FieldSetValidator(...) for more details.
 */
public class FieldSetValidator implements IValidationFunction {
    private final IValidationFunction validator;
    private final boolean optional;
    private final String field;
    private final String value;

    /**
     * Constructs FieldSetValidator.
     *
     * @param field     Name of the field to check.
     * @param value     Required value of the field.
     * @param validator Validator to run after check.
     * @param optional  Determines if this field is optional.
     * @see FieldSetValidator Documentation of the validator.
     * @apiNote
     * <ul>
     * <li><code>optional</code> determines if this validator should skip generation of an error, if the validated field is missing, but boolean field value is <code>true</code>.</li>
     * <li>ArrayHandlingPolicy is going to be modified to disallow empty arrays if <code>optional</code> is set to <code>false</code>,
     * otherwise empty arrays are accepted.</li>
     * </ul>
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
     * @param value     Required value of the field.
     * @param validator Validator to run after check.
     * @see FieldSetValidator Documentation of the validator.
     */
    public FieldSetValidator(String field, String value, IValidationFunction validator) {
        this(field, value, validator, false);
    }

    /**
     * Entry point of the validator.
     *
     * @param ctx ValidationContext record with necessary information to validate the element.
     * @return True if the validation passes, false otherwise.
     */
    @Override
    public Boolean apply(@NotNull ValidationContext ctx) {
        JsonElement stringField;
        String stringFieldPath;
        if (field.startsWith("root")) {
            stringField = ValidationHelper.getElementFromPathAs(ctx.rootObject(), field, Types.STRING);
            stringFieldPath = field;
        } else {
            stringField = ctx.getParentFieldAs(Types.STRING, field);
            stringFieldPath = ctx.getParentFieldPath(field);
        }

        JsonElement element = ctx.validationElement();

        if (Objects.isNull(element)) {
            if (!optional && Objects.nonNull(stringField) && stringField.getAsString().equals(value)) {
                ctx.error(
                    "This field is required!",
                    "Field <code>%s</code> is set to <code>%s</code>, which makes this field necessary.".formatted(stringFieldPath, value)
                );
                return false;
            }
            return true;
        }

        if (Objects.isNull(stringField))
            ctx.warn(
                "This field is unnecessary!",
                "Field <code>%s</code> needs to be present and set to <code>%s</code> for this field to have any effect.".formatted(stringFieldPath, value)
            );
        else if (!stringField.getAsString().equals(value))
            ctx.warn(
                "This field is unnecessary!",
                "Field <code>%s</code> needs to be set to <code>%s</code> for this field to have any effect.".formatted(stringFieldPath, value)
            );
        else if (!optional)
            return validator.apply(ctx.getWithAHP(ctx.arrayPolicy().getLegacyArrayPolicy().getNonEmpty()));

        return validator.apply(ctx.getWithAHP(ctx.arrayPolicy().getLegacyArrayPolicy().get()));
    }
}
