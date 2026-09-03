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
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.MultiValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.AbstractValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.TypeValidator;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * This validator is used to check if the specified value isn't one of the deprecated ones, and suggests the replacement.
 * <br><br>
 * Take a note that this validator should be removed from the current uses at some time in the future, possibly on the next breaking-change update.
 * @apiNote Should be used with {@link MultiValidator} and an actual validator. This will only issue errors about deprecation!
 * @implNote  Supports: {@link Types#STRING}
 */
public class DeprecatedValueValidator extends AbstractValidator {
    private final Map<String, String> valuesMap = new HashMap<>();
    private final Map<String, String> urlMap = new HashMap<>();

    /**
     * Constructs DeprecatedValueValidator.
     *
     * @param value Deprecated Value
     * @param replacement Replacement Value
     * @param url Url to the wiki page, if any.
     * @see DeprecatedValueValidator Documentation of the validator.
     */
    public DeprecatedValueValidator(String value, String replacement, String url) {
        this(List.of(new ValueHolder(value, replacement, url)));
    }

    /**
     * Constructs DeprecatedValueValidator.
     *
     * @param values List of {@link ValueHolder} with value / replacement / url.
     * @see DeprecatedValueValidator Documentation of the validator.
     */
    public DeprecatedValueValidator(@NotNull List<ValueHolder> values) {
        super(false);
        values.forEach(holder -> {
            valuesMap.put(holder.value, holder.replacement);
            if (Objects.nonNull(holder.url)) urlMap.put(holder.value, holder.url);
        });
    }

    /**
     * Validate method, used to validate passed in object.
     *
     * @param data ValidationContext record with necessary information to validate the element.
     * @return True of the validation passes, false otherwise.
     * @apiNote Even tho it's public, this method should <i>never</i> be called directly! Call {@link TypeValidator#apply(ValidationContext)} instead!
     */
    @Override
    public Boolean validate(@NotNull ValidationContext data) {
        // Modified code of TypeValidator, so no duplicate-errors will be generated when this validator is used.
        var element = data.validationElement();
        if (!element.isJsonPrimitive() || !element.getAsJsonPrimitive().isString()) return false;
        String value = data.validationElement().getAsString();
        String replacement = valuesMap.get(value);
        if (Objects.nonNull(replacement)) {
            String url = urlMap.get(value);
            String additional = url == null? null: "<a href=\"%s\">Click this link for more details.</a>".formatted(url);
            Analytics.error("Specified value <code>%s</code> was deprecated and is replaced by <code>%s</code>.".formatted(value, replacement), additional, data);
            DeprecationAnalytics.increaseDeprecated();
        }
        return true;
    }

    /**
     * ValueHolder record, used to store necessary infromation for {@link DeprecatedValueValidator};
     * @param value Deprecated value
     * @param replacement Replacement value
     * @param url Url to the wiki page, if any.
     */
    public record ValueHolder(String value, String replacement, String url) {}
}
