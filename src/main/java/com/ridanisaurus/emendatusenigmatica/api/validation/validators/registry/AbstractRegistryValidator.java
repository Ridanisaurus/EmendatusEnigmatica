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

package com.ridanisaurus.emendatusenigmatica.api.validation.validators.registry;

import com.ridanisaurus.emendatusenigmatica.api.validation.RegistryValidationData;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationData;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class AbstractRegistryValidator {
    private final String errorMessage;

    public AbstractRegistryValidator(String errorMessage) {
        this.errorMessage = Objects.requireNonNull(errorMessage, "Error Message can't be null!");
    }

    /**
     * This method is used to check if the resource location is present in the specified registry.
     *
     * @param data Record with ResourceLocation to validate and {@link ValidationData} object from the original json file.
     * @return The result of the validation. See {@link Result} for more details.
     * @apiNote At this stage, all registries were constructed,
     */
    public abstract Result validate(@NotNull RegistryValidationData data);

    public String getErrorMessage() {
        return this.errorMessage;
    }

    /**
     * Simple enum to represent the validation result.
     *
     * @see Result#PASS
     * @see Result#ERROR
     * @see Result#FATAL
     */
    public enum Result {
        /**
         * Registry Validation was successful!
         *
         * @see Result
         * @see Result#ERROR
         * @see Result#FATAL
         */
        PASS,
        /**
         * Registry Validation wasn't successful, but action to prevent issues was taken.
         *
         * @see Result
         * @see Result#PASS
         * @see Result#FATAL
         */
        ERROR,
        /**
         * Registry Validation wasn't successful, and this situation can't be recovered from.
         *
         * @apiNote Take a note that returning this status <i>will cause a game crash</i>!
         * @see Result
         * @see Result#PASS
         * @see Result#ERROR
         * */
        FATAL
    }
}
