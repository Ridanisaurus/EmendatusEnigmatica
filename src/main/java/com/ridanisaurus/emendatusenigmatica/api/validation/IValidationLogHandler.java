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

package com.ridanisaurus.emendatusenigmatica.api.validation;

import com.ridanisaurus.emendatusenigmatica.util.summary.SummaryHandler;
import org.jetbrains.annotations.Nullable;

public interface IValidationLogHandler {
    /**
     * Used to add warn messages for the current file.
     * @param msg Message to add
     * @param additional Additional details to be printed after "message". This gets written directly into the file!
     * @param elementPath Path to the element in question.
     * @param jsonPath Path to the JSON file.
     */
    default void warn(String msg, String additional, String elementPath, String jsonPath) {
        SummaryHandler.warn(msg, additional, elementPath, jsonPath);
    }

    /**
     * Used to add error messages for the current file.
     * @param msg Message to add.
     * @param additional Additional details to be printed after "cause". This gets written directly into the file!
     * @param elementPath Path to the element in question.
     * @param jsonPath Obfuscated path to the JSON file.
     */
    default void error(String msg, @Nullable String additional, String elementPath, String jsonPath) {
        SummaryHandler.error(msg, additional, elementPath, jsonPath);
    }
}
