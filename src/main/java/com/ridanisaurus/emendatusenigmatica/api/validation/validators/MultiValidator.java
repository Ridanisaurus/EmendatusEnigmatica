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

import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

/**
 * This validator takes in multiple validators, and validates all of them parallel for a single field.
 * @apiNote Take a note that, while handy, this should not be used whenever possible.
 */
public class MultiValidator implements IValidationFunction {
    private final List<Function<ValidationContext, Boolean>> validators = new ArrayList<>();

    /**
     * Constructs MultiValidator, with validators specified.
     * @param validators Validators to execute for this field, in parallel.
     */
    @SafeVarargs
    public MultiValidator(Function<ValidationContext, Boolean>... validators) {
        this.validators.addAll(List.of(validators));
    }

    @Override
    public Boolean apply(ValidationContext ctx) {
        List<CompletableFuture<Boolean>> cs = new ArrayList<>();
        validators.forEach(validator -> cs.add(CompletableFuture.supplyAsync(() -> validator.apply(ctx))));
        try {
            CompletableFuture.allOf(cs.toArray(CompletableFuture[]::new)).get();
            for (CompletableFuture<Boolean> c : cs) {
                if (!c.get()) return false;
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(ctx.toString(), e);
        }
        return true;
    }
}
