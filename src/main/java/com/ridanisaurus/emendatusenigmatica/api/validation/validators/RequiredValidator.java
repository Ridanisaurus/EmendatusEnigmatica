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
import org.jetbrains.annotations.ApiStatus.Experimental;
import org.jetbrains.annotations.NotNull;

/**
 * This validator is a simple implementation of {@link AbstractBasicValidator}, executing only required validation, and returning true if that passes.
 * <br><br>
 * Usage of this validator is discouraged in production, and it should only be used as a temporary placeholder.
 */
@Experimental
public class RequiredValidator extends AbstractBasicValidator {
    /**
     * Constructs RequiredValidator.
     *
     * @param isRequired Determines if the field is required. If true, an error will be issued if the field is missing.
     * @see RequiredValidator Documentation of the validator.
     */
    @Experimental
    public RequiredValidator(boolean isRequired) {
        super(isRequired);
    }

    /**
     * Validate method, used to validate passed in object.
     *
     * @param data ValidationContext record with necessary information to validate the element.
     * @return True of the validation passes, false otherwise.
     * @apiNote Even tho it's public, this method should <i>never</i> be called directly!
     * Call {@link RequiredValidator#apply(ValidationContext)} instead!
     * @implNote This acts like {@link AcceptsAllValidator}, meaning it only returns {@code true}.
     */
    @Override
    @Experimental
    public Boolean validate(@NotNull ValidationContext data) {
        return true;
    }
}
