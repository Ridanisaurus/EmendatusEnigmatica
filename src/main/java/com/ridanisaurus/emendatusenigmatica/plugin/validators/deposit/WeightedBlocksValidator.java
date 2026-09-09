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

package com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit;

import com.google.gson.JsonPrimitive;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationContext;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayHandlingPolicy;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayPolicy;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.IValidationFunction;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.block.BlockModel;

import java.util.Objects;

public class WeightedBlocksValidator implements IValidationFunction {
    private final IValidationFunction validator;

    public WeightedBlocksValidator(IValidationFunction validator) {
        this.validator = Objects.requireNonNull(validator, "Validator can't be null!");
    }

    public WeightedBlocksValidator() {
        this(BlockModel.VALIDATION_MANAGER.getAsValidator(true));
    }

    /**
     * Entry point of the validator.
     *
     * @param ctx ValidationContext record with necessary information to validate the element.
     * @return True if the validation passes, false otherwise.
     */
    @Override
    public Boolean apply(ValidationContext ctx) {
        if (!validator.apply(ctx.getWithAHP(new ArrayHandlingPolicy(ArrayPolicy.REQUIRES_ARRAY, ctx.arrayPolicy().canBeEmpty())))) return false;
        if (ctx.validationElement()
            .getAsJsonArray()
            .asList()
            .stream()
            .mapToLong(it -> Objects.requireNonNullElse(it.getAsJsonObject().get("weight"), new JsonPrimitive(1)).getAsLong())
            .sum() >= Integer.MAX_VALUE
        ) {
            ctx.error("Sum of weights in the Blocks specified can't be above or equal to <code>Integer.MAX_VALUE</code> (<code>%d</code>).".formatted(Integer.MAX_VALUE));
            return false;
        }

        return true;
    }
}
