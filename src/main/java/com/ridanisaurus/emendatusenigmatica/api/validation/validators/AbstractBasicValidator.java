/*
 * MIT License
 *
 * Copyright (c) 2024-2026. Ridanisaurus
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
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayHandlingPolicy;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * AbstractBasicValidator is a template class, which more advanced validators can extend,
 * to have the basic functions already implemented and ready-to-go.
 * <br><br>
 * This validator handles the field requirement and if the element passes, calls the abstract {@link AbstractBasicValidator#validate(ValidationContext)} method.
 * @apiNote This validator doesn't handle {@link ArrayHandlingPolicy}, and implementation of the {@link AbstractBasicValidator#validate(ValidationContext)}
 * method needs to handle it manually. If the element is an array, <i>the entire array</i> is passed to the method, instead of each entry individually.
 * If your use case doesn't require access to the entire array, use {@link AbstractValidator} instead.
 */
public abstract class AbstractBasicValidator implements IValidationFunction {
    private final boolean isRequired;

    /**
     * Constructs AbstractBasicValidator, which requires implementation of {@link AbstractBasicValidator#validate(ValidationContext)} method.
     * @param isRequired Determines if the field is required. If true, an error will be issued if the field is missing.
     * @see AbstractBasicValidator Documentation of the validator.
     */
    public AbstractBasicValidator(boolean isRequired) {
        this.isRequired = isRequired;
    }

    /**
     * Entry point of the validator.
     * @param ctx ValidationContext record with necessary information to validate the element.
     * @return True if the validation passes, false otherwise.
     */
    @Override
    public Boolean apply(@NotNull ValidationContext ctx) {
        boolean requirement = isRequired(ctx);
        if (Objects.isNull(ctx.validationElement())) {
            if (!requirement) return true;
            ctx.error("This field is required!");
            return false;
        }
        return this.validate(ctx);
    }

    /**
     * Method used to determine if the validator is required on runtime.
     * @param ctx ValidationContext record with necessary information to validate the element.
     * @return True if current element is required, false if not.
     * @implNote By default, returns the value specified in the constructor.
     */
    public boolean isRequired(@NotNull ValidationContext ctx) {
        return isRequired;
    }

    /**
     * Validate method, used to validate passed in object.
     * @param ctx ValidationContext record with necessary information to validate the element.
     * @return True of the validation passes, false otherwise.
     * @apiNote Even tho it's public, this method should <i>never</i> be called directly! Call {@link AbstractBasicValidator#apply(ValidationContext)} instead!
     */
    public abstract Boolean validate(@NotNull ValidationContext ctx);
}
