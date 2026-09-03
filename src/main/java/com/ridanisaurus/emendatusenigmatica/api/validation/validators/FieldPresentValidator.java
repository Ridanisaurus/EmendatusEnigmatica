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

import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationContext;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationHelper;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * A validator wrapper which handles the requirement and ArrayHandlingPolicy of a field,
 * based on the presence of another field.
 */
public class FieldPresentValidator implements IValidationFunction {
    private final IValidationFunction validator;
    private final String path;

    /**
     * Constructs FieldPresentValidator.
     *
     * @param path Path to the field to check.
     * @param validator Validator to run after check.
     * @see FieldPresentValidator Documentation of the validator.
     */
    public FieldPresentValidator(String path, IValidationFunction validator) {
        this.validator = validator;
        this.path = path;
    }

    /**
     * Entry point of the validator.
     *
     * @param data ValidationContext record with necessary information to validate the element.
     * @return True if the validation passes, false otherwise.
     */
    @Override
    public Boolean apply(@NotNull ValidationContext data) {
        boolean isRequired = ValidationHelper.isOtherFieldPresent(data.rootObject(), path.startsWith("root")? path: data.getParentFieldPath(path));
        if (Objects.isNull(data.validationElement())) {
            if (!isRequired) return true;
            Analytics.error("This field is required!", "Field <code>%s</code> is present, making this field necessary.".formatted(path), data);
            return false;
        }
        return validator.apply(data);
    }
}
