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

package com.ridanisaurus.emendatusenigmatica.plugin.validators;

import com.google.gson.JsonElement;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationContext;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.NumberRangeValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.TypeValidator;
import com.ridanisaurus.emendatusenigmatica.util.summary.SummaryHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Custom implementation of {@link NumberRangeValidator},
 * which automatically tries to parse the {@code min} value from the same object.
 * @apiNote Take a note that this validator will function as normal NumberRangeValidator
 * if the minimum field range is different from specified in this validator!
 */
public class MaxValidator extends TypeValidator {
    private final String name;
    private final double max;
    private final double min;

    /**
     * Constructs MaxValidator with the specified type and range, with minimum field name of "{@code min}"
     *
     * @param type       Determines which type this field should be.
     * @param max        The Highest number this validator will accept.
     * @param min        The Lowest number this validator will accept, by default.
     * @param isRequired Determines if the field is required. If true, an error will be issued if the field is missing.
     * @throws IllegalArgumentException if type different from {@link Types#INTEGER} or {@link Types#FLOAT} is specified.
     * @see MaxValidator Documentation of the validator.
     * @implSpec The Range of the minimum field is required to be identical to the one specified here.
     */
    public MaxValidator(Types type, double min, double max, boolean isRequired) {
        this(type, "max", min, max, isRequired);
    }

    /**
     * Constructs MaxValidator with the specified type and range.
     *
     * @param type       Determines which type this field should be.
     * @param fieldName  Name of the minimum field.
     * @param max        The Highest number this validator will accept.
     * @param min        The Lowest number this validator will accept, by default.
     * @param isRequired Determines if the field is required. If true, an error will be issued if the field is missing.
     * @throws IllegalArgumentException if type different from {@link Types#INTEGER} or {@link Types#FLOAT} is specified.
     * @see MaxValidator Documentation of the validator.
     * @implSpec The Range of the minimum field is required to be identical to the one specified here.
     */
    public MaxValidator(Types type, String fieldName, double min, double max, boolean isRequired) {
        super(type, isRequired);
        if (type != Types.FLOAT && type != Types.INTEGER) throw new IllegalArgumentException("Invalid type specified! Expected: FLOAT / INTEGER, got: " + type.name());
        this.name = fieldName;
        this.max = max;
        this.min = min;
    }

    @Override
    public Boolean validate(@NotNull ValidationContext ctx) {
        if (!super.validate(ctx)) return false;
        double maxFieldValue = ctx.validationElement().getAsDouble();
        double minFieldValue = min;
        JsonElement minField = ctx.getParentFieldAs(Types.FLOAT, name);
        String additional = "";

        if (Objects.nonNull(minField) && minField.getAsDouble() <= max) {
            minFieldValue = minField.getAsDouble();
            additional = "\n##### Minimum value is taken from the minimum field, at <code>%s</code>.".formatted(ctx.getParentFieldPath(name));
        }

        if (maxFieldValue < minFieldValue || maxFieldValue > max) {
            ctx.error("Number out of range!", "Expected number from %f to %f, got %f.%s".formatted(minFieldValue, max, maxFieldValue, additional));
            return false;
        }

        return true;
    }
}
