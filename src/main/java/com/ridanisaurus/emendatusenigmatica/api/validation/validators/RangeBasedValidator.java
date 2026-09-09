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

package com.ridanisaurus.emendatusenigmatica.api.validation.validators;

import com.google.gson.JsonElement;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationContext;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationHelper;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Utility validator, which only validates the field if other, number based field is in the specified range or threshold
 * @see RangeBasedValidator
 */
public class RangeBasedValidator implements IValidationFunction {
    private final IValidationFunction validator;
    private final boolean optional;
    private final String path;
    private final Double min;
    private final Double max;
    private final Types type;

    /**
     * Constructs RangeBasedValidator.
     * @param validator Validator to run if field is present.
     * @param path Path to the number field to check.
     * @param min Minimum range value, or <code>null</code>.
     * @param max Maximum range value, or <code>null</code>.
     * @param isOptional  Determines if this field is optional.
     * @apiNote
     * This validator has few important notes:
     * <ul>
     * <li><code>optional</code> determines if this validator should skip generation of an error, if the validated field is missing, but checked field value is in required range.</li>
     * <li>ArrayHandlingPolicy is going to be modified to disallow empty arrays if <code>optional</code> is set to <code>false</code>,
     * otherwise empty arrays are accepted.</li>
     * <li>If <code>min</code> field is <code>null</code>, the validator checks the provided path for values <b>below</b> the maximum range value.</li>
     * <li>If <code>max</code> field is <code>null</code>, the validator checks the provided path for values <b>above</b> the minimum range value.</li>
     * </ul>
     * @throws IllegalArgumentException when <code>min</code> and <code>max</code> arguments are <code>null</code> at the same time.
     */
    public RangeBasedValidator(Types type, IValidationFunction validator,  String path, Double min, Double max, boolean isOptional) {
        this.validator = Objects.requireNonNull(validator, "Validator can't be null!");
        this.path = Objects.requireNonNull(path, "Path can't be null!");
        this.min = min;
        this.max = max;
        this.type = Objects.requireNonNull(type);
        this.optional = isOptional;
        if (Objects.isNull(min) && Objects.isNull(max)) throw new IllegalArgumentException("Min and Max can't be null at the same time!");
        if (type != Types.FLOAT && type != Types.INTEGER) throw new IllegalArgumentException("Invalid type specified! Expected: FLOAT / INTEGER, got: " + type.name());
    }

    /**
     * Constructs RangeBasedValidator in MinThreshold mode. (Checked value > min)
     * @param validator Validator to run if field is present.
     * @param path Path to the number field to check.
     * @param min Lowest acceptable value.
     * @param isOptional  Determines if this field is optional.
     * @apiNote
     * This validator has few important notes:
     * <ul>
     * <li><code>optional</code> determines if this validator should skip generation of an error, if the validated field is missing, but checked field value is in required range.</li>
     * <li>ArrayHandlingPolicy is going to be modified to disallow empty arrays if <code>optional</code> is set to <code>false</code>,
     * otherwise empty arrays are accepted.</li>
     * </ul>
     */
    @Contract("_, _, _, _, _ -> new")
    public static @NotNull RangeBasedValidator createMinThreshold(Types type, IValidationFunction validator, String path, double min, boolean isOptional) {
        return new RangeBasedValidator(type, validator, path, min, null, isOptional);

    }

    /**
     * Constructs RangeBasedValidator in MaxThreshold mode. (Checked value < max)
     * @param validator Validator to run if field is present.
     * @param path Path to the number field to check.
     * @param max Highest acceptable value.
     * @param isOptional  Determines if this field is optional.
     * @apiNote
     * This validator has few important notes:
     * <ul>
     * <li><code>optional</code> determines if this validator should skip generation of an error, if the validated field is missing, but checked field value is in required range.</li>
     * <li>ArrayHandlingPolicy is going to be modified to disallow empty arrays if <code>optional</code> is set to <code>false</code>,
     * otherwise empty arrays are accepted.</li>
     * </ul>
     */
    @Contract("_, _, _, _, _ -> new")
    public static @NotNull RangeBasedValidator createMaxThreshold(Types type, IValidationFunction validator, String path, double max, boolean isOptional) {
        return new RangeBasedValidator(type, validator, path, null, max, isOptional);
    }

    /**
     * Entry point of the validator.
     *
     * @param ctx ValidationContext record with necessary information to validate the element.
     * @return True if the validation passes, false otherwise.
     */
    @Override
    public Boolean apply(ValidationContext ctx) {
        JsonElement numberField;
        String numberFieldPath;
        if (path.startsWith("root")) {
            numberField = ValidationHelper.getElementFromPathAs(ctx.rootObject(), path, Types.FLOAT);
            numberFieldPath = path;
        } else {
            numberField = ctx.getParentFieldAs(Types.FLOAT, path);
            numberFieldPath = ctx.getParentFieldPath(path);
        }

        JsonElement element = ctx.validationElement();

        if (Objects.isNull(element)) {
            if (!optional && Objects.nonNull(numberField)) {
                double check = numberField.getAsDouble();
                if (Objects.isNull(min)) {
                    if (check >= max) return true;
                    ctx.error(
                        "This field is required!",
                        processFormatting("Field <code>%s</code> is below <code>%f</code>, which makes this field necessary.").formatted(numberFieldPath, max)
                    );
                    return false;
                }
                if (Objects.isNull(max)) {
                    if (check <= min) return true;
                    ctx.error(
                        "This field is required!",
                        processFormatting("Field <code>%s</code> is above <code>%f</code>, which makes this field necessary.").formatted(numberFieldPath, min)
                    );
                    return false;
                }
                if (min < check || check > max) return true;
                ctx.error(
                    "This field is required!",
                    processFormatting("Field <code>%s</code> is in range <code>[%f,%f]</code>, which makes this field necessary.").formatted(numberFieldPath, min, max)
                );
                return false;
            }
            return true;
        }

        String message;

        if (Objects.isNull(min)) {
            message = processFormatting("below <code>%f</code>").formatted(max);
        } else if (Objects.isNull(max)) {
            message = processFormatting("above <code>%f</code>").formatted(min);
        } else {
            message = processFormatting("in range <code>[%f,%f]</code>").formatted(min, max);
        }

        if (Objects.isNull(numberField))
            ctx.warn(
                "This field is unnecessary!",
                processFormatting("Field <code>%s</code> needs to be present and %s, for this field to have any effect.").formatted(numberFieldPath, message)
            );
        else {
            double check = numberField.getAsDouble();
            if (
                (Objects.isNull(min) && check >= max) ||
                (Objects.isNull(max) && check <= min) ||
                (Objects.nonNull(min) && Objects.nonNull(max) && (min < check || check > max))
            ) {
                ctx.warn(
                    "This field is unnecessary!",
                    processFormatting("Field <code>%s</code> needs to be %s, for this field to have any effect.").formatted(numberFieldPath, message)
                );
            } else if (!optional)
                return validator.apply(ctx.getWithAHP(ctx.arrayPolicy().getLegacyArrayPolicy().getNonEmpty()));
        }

        return validator.apply(ctx.getWithAHP(ctx.arrayPolicy().getLegacyArrayPolicy().get()));
    }

    private String processFormatting(String msg) {
        if (type == Types.FLOAT) return msg;
        return msg.replaceAll("%f", "%.0f");
    }
}
