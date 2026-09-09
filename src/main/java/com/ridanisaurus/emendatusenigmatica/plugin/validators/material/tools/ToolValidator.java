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

package com.ridanisaurus.emendatusenigmatica.plugin.validators.material.tools;

import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.ToolsModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.ToolModel;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.material.ProcessedTypesContainValidator;

/**
 * Wrapper of {@link ProcessedTypesContainValidator}, used for validating {@link ToolModel} in {@link ToolsModel}
 */
public class ToolValidator extends ProcessedTypesContainValidator {
    private static final ValidationManager.ObjectValidator validator = ToolModel.VALIDATION_MANAGER.getAsValidator(false);

    /**
     * Constructs ToolValidator, with specified processedType requirement.
     *
     * @param processedType Processed Type required, for this validator to be marked as "Required"
     * @see ToolValidator Documentation of the validator.
     */
    public ToolValidator(String processedType) {
        super(processedType, validator);
    }
}
