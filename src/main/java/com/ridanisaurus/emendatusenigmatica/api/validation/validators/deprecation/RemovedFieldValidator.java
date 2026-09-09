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

package com.ridanisaurus.emendatusenigmatica.api.validation.validators.deprecation;

import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationContext;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.AbstractBasicValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.AcceptsAllValidator;
import com.ridanisaurus.emendatusenigmatica.util.summary.SummaryHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * This validator acts like {@link AcceptsAllValidator}, however, it is meant to be used to generate errors about deprecation of this field, if found in a file.<br>
 * Additionally, an url can be specified, pointing to a Wiki page, explaining the new field.
 */
public class RemovedFieldValidator extends AbstractBasicValidator {
    private final String url;
    /**
     * Constructs RemovedFieldValidator.
     *
     * @see RemovedFieldValidator Documentation of the validator.
     */
    public RemovedFieldValidator(String url) {
        super(false);
        this.url = url;
    }

    public RemovedFieldValidator() {
        this(null);
    }

    /**
     * Validate method, used to validate passed in object.
     *
     * @param ctx ValidationContext record with necessary information to validate the element.
     * @return True of the validation passes, false otherwise.
     * @apiNote Even tho it's public, this method should <i>never</i> be called directly! Call {@link RemovedFieldValidator#apply(ValidationContext)} instead!
     * @implSpec Take a note that the {@link ValidationContext#validationElement()} will never return null.
     */
    @Override
    public Boolean validate(@NotNull ValidationContext ctx) {
        if (SummaryHandler.isEnabled()) {
            String msg = null;
            if (Objects.nonNull(url)) msg = "<a href=\"%s\">Click this link for more details.</a>".formatted(url);
            ctx.warn("This field was deprecated and removed, with no replacement.", msg);
        }
        return true;
    }
}
