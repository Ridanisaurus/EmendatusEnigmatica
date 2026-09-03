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

package com.ridanisaurus.emendatusenigmatica.api.validation.validators.deprecation;

import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationContext;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.AbstractBasicValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.AcceptsAllValidator;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * This validator acts like {@link AcceptsAllValidator}, however, it is meant to be used to generate warnings about deprecation of this field, if found in a file.<br>
 * Additionally, an url can be specified, pointing to a Wiki page, explaining the new field.
 */
public class DeprecatedFieldValidator extends AbstractBasicValidator {
    private final String replace;
    private final String url;
    /**
     * Constructs DeprecatedFieldValidator.
     *
     * @see DeprecatedFieldValidator Documentation of the validator.
     */
    public DeprecatedFieldValidator(String replacement, String url) {
        super(false);
        this.replace = replacement;
        this.url = url;
    }

    public DeprecatedFieldValidator(String replacement) {
        this(replacement, null);
    }

    public DeprecatedFieldValidator() {
        this(null, null);
    }

    /**
     * Validate method, used to validate passed in object.
     *
     * @param data ValidationContext record with necessary information to validate the element.
     * @return True of the validation passes, false otherwise.
     * @apiNote Even tho it's public, this method should <i>never</i> be called directly! Call {@link DeprecatedFieldValidator#apply(ValidationContext)} instead!
     * @implSpec Take a note that the {@link ValidationContext#validationElement()} will never return null.
     */
    @Override
    public Boolean validate(@NotNull ValidationContext data) {
        DeprecationAnalytics.increaseDeprecated();
        if (Analytics.isEnabled()) {
            String msg = null;
            if (Objects.nonNull(url)) msg = "<a href=\"%s\">Click this link for more details.</a>".formatted(url);
            if (Objects.isNull(replace)) {
                Analytics.error("This field was deprecated and is no longer in use.", msg, data);
            } else {
                Analytics.error("This field was deprecated and replaced by: <code>%s</code>".formatted(replace), msg, data);
            }
        }
        return true;
    }
}
