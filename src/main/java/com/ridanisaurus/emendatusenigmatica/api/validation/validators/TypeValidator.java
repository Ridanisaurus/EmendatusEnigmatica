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

import com.google.gson.JsonPrimitive;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationContext;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * This validator checks if passed element complies with the specified type, on construct time.
 * @see Types Types Enum for all supported types and more detailed documentation per-type.
 */
public class TypeValidator extends AbstractValidator {
    private final Types type;

    /**
     * Constructs TypeValidator with the specified type.
     *
     * @param isRequired Determines if the field is required. If true, an error will be issued if the field is missing.
     * @param type Determines which type this field should be.
     * @see TypeValidator Documentation of the validator.
     */
    public TypeValidator(Types type, boolean isRequired) {
        super(isRequired);
        this.type = type;
    }

    /**
     * Validate method, used to validate passed in object.
     *
     * @param ctx ValidationContext record with necessary information to validate the element.
     * @return True of the validation passes, false otherwise.
     * @apiNote Even tho it's public, this method should <i>never</i> be called directly! Call {@link TypeValidator#apply(ValidationContext)} instead!
     */
    @Override
    public Boolean validate(@NotNull ValidationContext ctx) {
        var element = ctx.validationElement();
        JsonPrimitive primitive = null;
        if (element.isJsonPrimitive()) primitive = element.getAsJsonPrimitive();
        return switch (this.type) {
            case INTEGER, FLOAT -> {
                if (Objects.isNull(primitive) || !primitive.isNumber()) {
                    ctx.error("Invalid type!", "Required type: <code>%s</code>, got: <code>%s</code>".formatted(type, element.toString()));
                    yield false;
                }

                if (type == Types.INTEGER) {
                    var value = primitive.getAsDouble();
                    var floor = Math.floor(value);
                    if (value > floor) ctx.warn("Floating-point value is not supported for this field, and will be rounded down to " + (int) floor);
                }

                yield true;
            }
            case STRING, STRING_EMPTY -> {
                if (Objects.isNull(primitive) || !primitive.isString()) {
                    ctx.error("Invalid type!", "Required type: <code>%s</code>, got: <code>%s</code>".formatted(type, element.toString()));
                    yield false;
                }

                if (type == Types.STRING && primitive.getAsString().isBlank()) {
                    ctx.error("String for this field can't be empty!");
                    yield false;
                }

                yield true;
            }
            case BOOLEAN -> {
                if (Objects.isNull(primitive) || !primitive.isBoolean()) {
                    ctx.error("Invalid type!", "Required type: <code>%s</code>, got: <code>%s</code>".formatted(type, element.toString()));
                    yield false;
                }
                yield true;
            }
        };
    }
}
