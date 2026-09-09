/*
 * MIT License
 *
 * Copyright (c) 2026. Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.loader;

import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationContext;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.IValidationFunction;

import java.util.Objects;

/**
 * A simple validator used to check if the field is an object.
 * This validator doesn't respect ArrayPolicy and always acts like {@link com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayPolicy#DISALLOW_ARRAY} is provided.
 */
public class SimpleObjectValidator implements IValidationFunction {
    public static SimpleObjectValidator INSTANCE = new SimpleObjectValidator();

    private SimpleObjectValidator() {}

    /**
     * Entry point of the validator.
     *
     * @param ctx ValidationContext record with necessary information to validate the element.
     * @return True if the validation passes, false otherwise.
     */
    @Override
    public Boolean apply(ValidationContext ctx) {
        var element = ctx.validationElement();
        if (Objects.isNull(element)) return true;
        if (ctx.validationElement().isJsonObject()) return true;

        if (element.isJsonArray()) {
            ctx.error("Arrays are not allowed for this field!");
            return false;
        }

        ctx.error("This field only accepts an object!");
        return false;
    }
}
