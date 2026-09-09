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

package com.ridanisaurus.emendatusenigmatica.plugin.validators.material.properties;

import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationContext;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationHelper;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.NumberRangeValidator;
import com.ridanisaurus.emendatusenigmatica.util.summary.SummaryHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GemTextureValidator extends NumberRangeValidator {
    private static final String value = "gem";
    private static final int min = 0;
    private static final int max = 9;
    /**
     * Constructs GemTextureValidator.
     *
     * @see GemTextureValidator Documentation of the validator.
     */
    public GemTextureValidator() {
        super(Types.INTEGER, min, max, false);
    }

    /**
     * Method used to determine if the validator is required on runtime.
     *
     * @param ctx ValidationContext record with necessary information to validate the element.
     * @return True if current element is required, false if not.
     * @implNote By default, returns the value specified in the constructor.
     */
    @Override
    public boolean isRequired(@NotNull ValidationContext ctx) {
        if (Objects.isNull(ctx.validationElement()) || !SummaryHandler.isEnabled()) return false;
        boolean hasGem = ValidationHelper.doesArrayContain(ctx.rootObject(), "root.processedTypes", value);
        boolean isColorBased = ValidationHelper.isOtherFieldPresent(ctx.rootObject(), "root.colors.materialColor");

        if (hasGem && isColorBased) return false;

        if (!hasGem)
            ctx.warn("This field is unnecessary when <code>gem</code> is missing from the <code>root.processedTypes</code> array.");

        if (!isColorBased)
            ctx.warn("This field is unnecessary when the material isn't tint-based.");

        return false;
    }
}
