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

import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationData;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;
import org.jetbrains.annotations.NotNull;

/**
 * An extension to the {@link TypeValidator}, accepting only types {@link Types#INTEGER} and {@link Types#FLOAT},
 * which additionally checks if the specified number is in acceptable range.
 * @apiNote Even tho this validator accepts the {@link Types} enum, it will throw an exception if a non-number type is passed to the constructor.
 */
public class NumberRangeValidator extends TypeValidator {
    private final double min;
    private final double max;
    /**
     * Constructs NumberRangeValidator with the specified type and range.
     *
     * @param type Determines which type this field should be.
     * @param min The Lowest number this validator will accept.
     * @param max The Highest number this validator will accept.
     * @param isRequired Determines if the field is required. If true, an error will be issued if the field is missing.
     * @see NumberRangeValidator Documentation of the validator.
     * @throws IllegalArgumentException if type different from {@link Types#INTEGER} or {@link Types#FLOAT} is specified.
     */
    public NumberRangeValidator(Types type, double min, double max, boolean isRequired) {
        super(type, isRequired);
        if (type != Types.FLOAT && type != Types.INTEGER) throw new IllegalArgumentException("Invalid type specified! Expected: FLOAT / INTEGER, got: " + type.name());
        this.min = min;
        this.max = max;
    }

    /**
     * Validate method, used to validate passed in object.
     *
     * @param data ValidationData record with necessary information to validate the element.
     * @return True of the validation passes, false otherwise.
     * @apiNote Even tho it's public, this method should <i>never</i> be called directly!
     * Call {@link NumberRangeValidator#apply(ValidationData)} instead!
     */
    @Override
    public Boolean validate(@NotNull ValidationData data) {
        if (!super.validate(data)) return false;
        double number = data.validationElement().getAsDouble();
        if (number < min || number > max) {
            Analytics.error("Number out of range!", "Expected number from %f to %f, got %f.".formatted(min, max, number), data);
            return false;
        }
        return true;
    }
}
