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

import com.google.gson.JsonElement;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationContext;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayHandlingPolicy;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * AbstractValidator is a template class, which more advanced validators can extend,
 * to have the basic functions already implemented and ready-to-go.
 * <br><br>
 * This validator handles the field requirement and checks the compliance with the {@link ArrayHandlingPolicy},
 * and if the element passes, calls the abstract {@link AbstractValidator#validate(ValidationContext)} method.
 * @implNote  Even tho this validator could be easily done to accept arrays of arrays... - the codecs are not created with such fields in mind!
 * Due to that limitation, this validator will issue errors if an array is found inside another array.<br>
 * @apiNote  This validator doesn't pass an entire array to the {@link AbstractValidator#validate(ValidationContext)} method, only elements found inside it.
 * If your validator requires access to the entire array, use {@link AbstractBasicValidator} instead.
 */
public abstract class AbstractValidator implements IValidationFunction {
    private final boolean isRequired;

    /**
     * Constructs AbstractValidator, which requires implementation of {@link AbstractValidator#validate(ValidationContext)} method.
     * @param isRequired Determines if the field is required. If true, an error will be issued if the field is missing.
     * @see AbstractValidator Documentation of the validator.
     */
    public AbstractValidator(boolean isRequired) {
        this.isRequired = isRequired;
    }

    /**
     * Entry point of the validator.
     * @param data ValidationContext record with necessary information to validate the element.
     * @return True if the validation passes, false otherwise.
     */
    @Override
    public Boolean apply(@NotNull ValidationContext data) {
        boolean requirement = isRequired(data);
        var element = data.validationElement();
        if (Objects.isNull(element)) {
            if (!requirement) return true;
            Analytics.error("This field is required!", getAdditional(data), data);
            return false;
        }

        if (element.isJsonArray()) {
            if (!data.arrayPolicy().allowsArrays()) {
                Analytics.error("Arrays are not allowed for this field!", data);
                return false;
            }

            int index = 0;
            boolean validation = true;
            for (JsonElement entry : element.getAsJsonArray()) {
                if (!this.validate(new ValidationContext(entry, data.rootObject(), "%s[%d]".formatted(data.currentPath(), index), data.jsonFilePath(), data.arrayPolicy())))
                    validation = false;
                index++;
            }

            if (index == 0 && !data.arrayPolicy().canBeEmpty()) {
                Analytics.error("Array for this field can not be empty!", data);
                return false;
            }
            return validation;
        }

        if (data.arrayPolicy().requiresArray()) {
            Analytics.error("This field requires an array!", data);
            return false;
        }
        return this.validate(data);
    }

    /**
     * Method used to determine if the validator is required on runtime.
     * @param data ValidationContext record with necessary information to validate the element.
     * @return True if current element is required, false if not.
     * @implNote By default, returns the value specified in the constructor.
     */
    public boolean isRequired(@NotNull ValidationContext data) {
        return isRequired;
    }

    /**
     * Method used to provide additional field for the "This field is required!" error.
     * @return String with an additional message or null.
     */
    public String getAdditional(@NotNull ValidationContext data) {
        return null;
    }

    /**
     * Validate method, used to validate passed in object.
     * @param data ValidationContext record with necessary information to validate the element.
     * @return True of the validation passes, false otherwise.
     * @apiNote Even tho it's public, this method should <i>never</i> be called directly! Call {@link AbstractValidator#apply(ValidationContext)} instead!
     * @implSpec Take a note that the {@link ValidationContext#validationElement()} will never return null.
     */
    @ApiStatus.Internal
    public abstract Boolean validate(@NotNull ValidationContext data);
}
