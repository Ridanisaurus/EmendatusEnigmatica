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

package com.ridanisaurus.emendatusenigmatica.api.validation.enums;

import org.jetbrains.annotations.NotNull;

/**
 * Determines the handling policy for Arrays of the validated field.
 * @implNote Methods using this enum directly should be treated as "legacy".
 * They have been updated for backwards compatibility to convert this enum to {@link ArrayHandlingPolicy} with use of {@link #get()}
 * @see ArrayHandlingPolicy
 */
public enum ArrayPolicy {
    /**
     * This field can't accept an array.
     */
    DISALLOW_ARRAY,
    /**
     * This field can accept an array.
     */
    ALLOW_ARRAY,
    /**
     * This field only accepts an array.
     */
    REQUIRES_ARRAY;

    //TODO: Remove ArrayPolicy, add "duplicateHandling" toggle, update relevant validators.
    // Replace ArrayPolicy "utility" with ArrayPolicyBuilder - Refactor ArrayHandlingPolicy back to ArrayPolicy

    // Create instances here, so we don't pollute the memory with instances of this "record".
    private final ArrayHandlingPolicy empty = new ArrayHandlingPolicy(this, true);
    private final ArrayHandlingPolicy nonEmpty = new ArrayHandlingPolicy(this, false);

    /**
     * @return {@link ArrayHandlingPolicy} with this policy set and <code>allowEmpty</code> set to <code>true</code>.
     */
    public @NotNull ArrayHandlingPolicy get() {
        return empty;
    }

    /**
     * @return {@link ArrayHandlingPolicy} with this policy set and <code>allowEmpty</code> set to <code>false</code>.
     */
    public @NotNull ArrayHandlingPolicy getNonEmpty() {
        return nonEmpty;
    }
}
