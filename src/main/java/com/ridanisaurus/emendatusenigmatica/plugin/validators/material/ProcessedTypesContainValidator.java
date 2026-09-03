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

package com.ridanisaurus.emendatusenigmatica.plugin.validators.material;

import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationContext;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationHelper;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.enums.PTCMode;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.IValidationFunction;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.AbstractBasicValidator;
import com.ridanisaurus.emendatusenigmatica.util.summary.SummaryHandler;
import net.neoforged.jarjar.nio.util.Lazy;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

/**
 * Custom Implementation of {@link AbstractBasicValidator},
 * used to check if ProcessedTypes in a material object contains specified value,
 * and determines if the field is required based on that.
 * @see PTCMode
 */
public class ProcessedTypesContainValidator implements IValidationFunction {
    private final IValidationFunction validator;
    private final Lazy<String> valuesAsString;
    private final List<String> values;
    private final PTCMode mode;

    /**
     * Constructs ProcessedTypesContainValidator, with specified processedType requirement and validator to run after.
     *
     * @param processedType Processed Type required to mark this field as required.
     * @param validator     Validator to run.
     * @apiNote Validator should be configured to be NOT required, as requirement errors are handled by this validator.
     * @see ProcessedTypesContainValidator Documentation of the validator.
     */
    public ProcessedTypesContainValidator(String processedType, IValidationFunction validator) {
        this(List.of(processedType), validator, PTCMode.REQUIRED_ALL_VALUE);
    }

    /**
     * Constructs ProcessedTypesContainValidator, with specified processedTypes requirement and validator to run after.
     *
     * @param processedTypes List of Processed Types, from which at least one is required to mark this field as required.
     * @param validator      Validator to run.
     * @apiNote Validator should be configured to be NOT required, as requirement errors are handled by this validator.
     * @see ProcessedTypesContainValidator Documentation of the validator.
     */
    public ProcessedTypesContainValidator(@NotNull List<String> processedTypes, IValidationFunction validator) {
        this(processedTypes, validator, PTCMode.OPTIONAL_ONE_VALUE);
    }

    /**
     * Constructs ProcessedTypesContainValidator, with specified processedType requirement and validator to run after.
     *
     * @param processedTypes List of Processed Types.
     * @param validator      Validator to run.
     * @param operationMode  Determines the mode of operation for this validator.
     * @implSpec Validator should be configured to be NOT required, as requirement errors are handled by this validator.
     * @see ProcessedTypesContainValidator Documentation of the validator.
     */
    public ProcessedTypesContainValidator(@NotNull List<String> processedTypes, IValidationFunction validator, PTCMode operationMode) {
        this.validator = validator;
        this.values = processedTypes;
        this.mode = operationMode;
        this.valuesAsString = Lazy.of(() -> String.join(", ", values));
        if (processedTypes.isEmpty()) throw new IllegalArgumentException("processedTypes argument can't be empty!");
    }


    /**
     * Entry point of the validator.
     *
     * @param ctx ValidationContext record with necessary information to validate the element.
     * @return True if the validation passes, false otherwise.
     */
    @Override
    public Boolean apply(@NotNull ValidationContext ctx) {
        List<String> foundValues = ValidationHelper.getContainedInArray(ctx.rootObject(), "root.processedTypes", values);
        boolean isRequired = Objects.nonNull(foundValues) && (mode.requiresAllValues() ? foundValues.size() == values.size() : !foundValues.isEmpty());

        if (Objects.isNull(ctx.validationElement())) {
            if (mode.isOptional() || !isRequired) return true;

            SummaryHandler.error(
                "This field is required! Array <code>root.processedTypes</code> contains elements, which make this field necessary.",
                mode.requiresAllValues() ?
                    "Values: <code>%s</code>".formatted(valuesAsString.get()) : """
                    Expected values: <code>%s</code><br>
                    Found values: <code>%s</code>""".formatted(valuesAsString.get(), String.join(", ", foundValues)),
                ctx
            );

            return false;
        } else if (!isRequired) {
            // For Optional modes - There will never be a non required situation with some value found!
            if (Objects.isNull(foundValues) || foundValues.isEmpty()) {
                SummaryHandler.warn(
                    "This field is unnecessary. Array <code>root.processedTypes</code> doesn't contain any elements, which are required for this field to have any effect.",
                    " Expected values: <code>%s</code>.".formatted(valuesAsString.get()),
                    ctx
                );
            } else {
                SummaryHandler.warn(
                    "This field is unnecessary. Array <code>root.processedTypes</code> doesn't contain some elements, which are required for this field to have any effect.", """
                        Expected values: <code>%s</code><br>
                        Found values: <code>%s</code>""".formatted(valuesAsString.get(), String.join(", ", foundValues)),
                    ctx
                );
            }
        }

        return validator.apply(ctx);
    }
}