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

package com.ridanisaurus.emendatusenigmatica.plugin.validators;

import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationData;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.TypeValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.ValuesValidator;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Custom implementation of {@link ValuesValidator}, used to validate ID fields, that point to the EE Registries.
 */
public class EERegistryValidator extends TypeValidator {
    /**
     * Will cause {@link EERegistryValidator} to check if ID isn't already registered in the specified list of ids.
     */
    public static final Mode REGISTRATION = Mode.REGISTRATION;
    /**
     * Will cause {@link EERegistryValidator} to check if ID is a valid reference to a value in the specified list.
     */
    public static final Mode REFERENCE = Mode.REFERENCE;
    private final List<String> values;
    private final Mode mode;
    private final String name;
    /**
     * Constructs EERegistryValidator, without the registry name.
     *
     * @param ids Reference to the list of ids.
     * @param mode Mode of this Validator.
     * @param isRequired Determines if the field is required. If true, an error will be issued if the field is missing.
     * @see EERegistryValidator Documentation of the validator
     * @see Mode Available modes
     */
    public EERegistryValidator(List<String> ids, Mode mode, boolean isRequired) {
        this(ids, mode, "", isRequired);
    }

    /**
     * Constructs EERegistryValidator.
     *
     * @param ids Reference to the list of ids.
     * @param mode Mode of this Validator.
     * @param registryName Registry name.
     * @param isRequired Determines if the field is required. If true, an error will be issued if the field is missing.
     * @see EERegistryValidator Documentation of the validator
     * @see Mode Available modes
     * @apiNote {@code registryName} is only used in {@link Mode#REFERENCE} mode. Format: "{@code registryName} registry..."
     */
    public EERegistryValidator(List<String> ids, Mode mode, String registryName, boolean isRequired) {
        super(Types.STRING, isRequired);
        this.name = registryName;
        this.values = ids;
        this.mode = mode;
    }

    /**
     * Validate method, used to validate passed in object.
     *
     * @param data ValidationData record with necessary information to validate the element.
     * @return True of the validation passes, false otherwise.
     * @apiNote Even tho it's public, this method should <i>never</i> be called directly! Call {@link TypeValidator#apply(ValidationData)} instead!
     */
    @Override
    public Boolean validate(@NotNull ValidationData data) {
        if (!super.validate(data)) return false;
        String value = data.validationElement().getAsString();
        boolean contains = values.contains(value);
        if (mode == Mode.REFERENCE) {
            if (contains) return true;
            Analytics.error("Specified ID <code>%s</code> is missing from the %s registry!".formatted(value, name), data);
            return false;
        }
        if (!contains) return true;
        Analytics.error("Specified ID <code>%s</code> is already registered!".formatted(value), data);
        return false;
    }

    /**
     * Simple enum to determine the mode of the {@link EERegistryValidator}.
     * @see Mode#REGISTRATION
     * @see Mode#REFERENCE
     */
    public enum Mode {
        /**
         * Will cause {@link EERegistryValidator} to check if ID isn't already registered in the specified list of ids.
         */
        REGISTRATION,
        /**
         * Will cause {@link EERegistryValidator} to check if ID is a valid reference to a value in the specified list.
         */
        REFERENCE
    }
}
