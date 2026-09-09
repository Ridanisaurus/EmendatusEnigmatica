/*
 * MIT License
 *
 * Copyright (c) 2024-2026. Ridanisaurus
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

import com.ridanisaurus.emendatusenigmatica.api.validation.validators.TypeValidator;

/**
 * Holds the JSON Types. Mostly used to determine the type of the field for Multi-Type Validators.
 * @see TypeValidator
 * @see Types#BOOLEAN
 * @see Types#INTEGER
 * @see Types#FLOAT
 * @see Types#STRING
 */
public enum Types {
    /**
     * Marks the field to require a boolean value.
     */
    BOOLEAN("Boolean"),
    /**
     * Marks the field to require an integer value.
     * @implNote Take a note that {@link TypeValidator} will issue a warning
     * if the field marked as {@code INTEGER} contains a {@link Types#FLOAT} value.
     * @see Types#FLOAT
     */
    INTEGER("Integer"),
    /**
     * Marks the field to require an integer / float / double value.
     * @see Types#INTEGER
     */
    FLOAT("Float"),
    /**
     * Marks the field to require a string value.
     * @implNote Take a note that {@link TypeValidator} will additionally perform a check
     * if the string provided is not empty ({@link String#isBlank()}).<br>
     * @see Types#STRING_EMPTY
     */
    STRING("String"),
    /**
     * Marks the field to require a string value.
     * @implNote Opposite to {@link Types#STRING}, this type will cause {@link TypeValidator} to skip the value check,
     * allowing this field to be just an empty string.
     * @see Types#STRING
     */
    STRING_EMPTY("String");

    private final String name;
    Types(String localizedName) {
        this.name = localizedName;
    }

    @Override
    public String toString() {
        return name;
    }
}
