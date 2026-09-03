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

package com.ridanisaurus.emendatusenigmatica.api.validation;

import com.google.common.base.Stopwatch;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.registry.AbstractRegistryValidator;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class RegistryValidationManager {
    private static final Map<AbstractRegistryValidator, List<RegistryValidationData>> validators = new HashMap<>();
    private RegistryValidationManager() {}

    /**
     * Used to execute Post-Registration validation, checking if specified Resource Locations point to valid registry objects.
     * @return False if at least one fatal error is found, true otherwise.
     * @implNote This will clear references to the ValidationContext objects after execution!
     */
    public static boolean validate() {
        //TODO: Run in parallel.
        Stopwatch s = Stopwatch.createStarted();
        AtomicBoolean result = new AtomicBoolean(true);
        validators.forEach((validator, list) -> list.forEach(registryData -> {
            var data = registryData.validationContext();
            switch (validator.validate(registryData)) {
                case PASS -> {
                    // Nothing, it passed successfully.
                }
                case ERROR -> Analytics.warn(validator.getErrorMessage(), "Problematic location: <code>%s</code>".formatted(data.validationElement().getAsString()), data);
                case FATAL -> {
                    Analytics.error(validator.getErrorMessage(), "Problematic location: <code>%s</code>".formatted(data.validationElement().getAsString()), data);
                    result.set(false);
                }
            }
        }));
        // Clearing validator's map, which holds references to the ValidationContext objects.
        validators.clear();
        Analytics.addPerformanceAnalytic("Validation: Registry", s);
        return result.get();
    }

    /**
     * Used to add validator to this RegistryValidationManager, with provided List of ResourceLocations.
     * @param validator - Validation Function.
     * @param resourceLocations List of resource locations to validate in parallel.
     * @apiNote Take a note that a reference to the list isn't possible to be retrieved from the manager later,
     * and should be stored separately if you want to modify it after registration of the validator.
     */
    public static void addValidator(AbstractRegistryValidator validator, List<RegistryValidationData> resourceLocations) {
        validators.put(
            Objects.requireNonNull(validator, "Validator can't be null!"),
            Objects.requireNonNull(resourceLocations, "ResourceLocations list can't be null!")
        );
    }
}
