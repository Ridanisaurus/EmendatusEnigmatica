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

package com.ridanisaurus.emendatusenigmatica.plugin.validators.enums;

import com.ridanisaurus.emendatusenigmatica.plugin.validators.material.ProcessedTypesContainValidator;

/**
 * Simple enum, providing all possible modes for {@link ProcessedTypesContainValidator}.
 *
 * @see PTCMode#REQUIRED_ONE_VALUE
 * @see PTCMode#OPTIONAL_ONE_VALUE
 * @see PTCMode#REQUIRED_ALL_VALUE
 * @see PTCMode#OPTIONAL_ALL_VALUE
 */
public enum PTCMode {
    /**
     * If at least one value is found, marks the field as required.
     */
    REQUIRED_ONE_VALUE(false, false),
    /**
     * If at least one value is found, marks the field as recommended.
     */
    OPTIONAL_ONE_VALUE(true, false),
    /**
     * If all provided values are found, marks the field as required.
     */
    REQUIRED_ALL_VALUE(false, true),
    /**
     * If all provided values are found, marks the field as recommended.
     */
    OPTIONAL_ALL_VALUE(true, true);

    private final boolean optional;
    private final boolean values;

    PTCMode(boolean optional, boolean allValues) {
        this.optional = optional;
        this.values = allValues;
    }

    public boolean isOptional() {
        return optional;
    }

    public boolean requiresAllValues() {
        return values;
    }
}
