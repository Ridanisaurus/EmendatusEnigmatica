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

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.ridanisaurus.emendatusenigmatica.config.EEConfig;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayPolicy;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.AbstractValidator;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import com.google.gson.JsonObject;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

/**
 * ValidationManager is a class that stores and manages Validation Functions - Validators for JSON files.
 */
public class ValidationManager {
    private final Map<String, ValidatorHolder> validators = new HashMap<>();
    private final ObjectValidator rootValidator = new ObjectValidator( this, true, true);
    private ValidationManager() {}

    /**
     * Used to create new instance of the ValidationManager.
     * @return New instance of {@link ValidationManager}.
     * @implNote ValidationManager doesn't contain its own builder,
     * allowing addons to expand / override validators of the Manager after its creation.
     */
    @Contract(" -> new")
    public static @NotNull ValidationManager create() {
        return new ValidationManager();
    }

    /**
     * Used to start the validation of the JsonObject.
     * @param object JsonObject to validate.
     * @param jsonPath Path of the file.
     * @return True if validation passes, false otherwise.
     */
    public boolean validate(@NotNull JsonObject object, Path jsonPath) {
        var path = ValidationHelper.obfuscatePath(jsonPath);

        if (!object.isJsonObject()) {
            Analytics.error("Expected Json Object at root!", "Root of the file is required to be an object. Arrays are not supported.", "root", path);
            return false;
        }

        if (object.isEmpty()) {
            if (!EEConfig.startup.skipEmptyJsons.get()) Analytics.error("Root object is empty!", "root", path);
            return false;
        }

        // Enters automatic validator execution. After this point, stack-traces are a mess!
        return this.rootValidator.apply(new ValidationData(object, object, "root", path, ArrayPolicy.DISALLOWS_ARRAYS));
    }

    /**
     * Used to get an ObjectValidator, wrapped around this ValidationManager.
     * @param isRequired Determines if the field this validator checks is required.
     * @return ObjectValidator wrapped around instance of this ValidationManager.
     */
    public ObjectValidator getAsValidator(boolean isRequired) {
        return new ObjectValidator( this, isRequired, false);
    }

    /**
     * Used to add validator to this ValidationManager,
     * under specified field, with specified ArrayPolicy.
     * @param field Field to add validator for.
     * @param validator - Validation Function.
     * @param arrayPolicy ArrayPolicy of this field.
     * @return {@code this} instance of the {@link ValidationManager}
     */
    @CanIgnoreReturnValue
    public ValidationManager addValidator(@NotNull String field, @NotNull Function<ValidationData, Boolean> validator, @NotNull ArrayPolicy arrayPolicy) {
        this.validators.put(
            Objects.requireNonNull(field, "Field name can't be null!"),
            new ValidatorHolder(
                Objects.requireNonNull(validator, "Validator can't be null!"),
                Objects.requireNonNull(arrayPolicy, "Array Policy can't be null!")
            )
        );
        return this;
    }

    /**
     * Used to add validator to this ValidationManager,
     * under specified field, with default {@link ArrayPolicy#DISALLOWS_ARRAYS};
     * @param field Field to add validator for.
     * @param validator - Validation Function.
     * @return {@code this} instance of the {@link ValidationManager}
     */
    @CanIgnoreReturnValue
    public ValidationManager addValidator(@NotNull String field, @NotNull Function<ValidationData, Boolean> validator) {
        return this.addValidator(field, validator, ArrayPolicy.DISALLOWS_ARRAYS);
    }

    /**
     * ObjectValidator validates JsonObject with use of the {@link ValidationManager} validators.
     * <br><br>
     * It's main purpose is to determine which fields are unknown,
     * and to call all known validators,
     * which are stored in the ValidationManager this validator is acquired from.
     *
     */
    public static final class ObjectValidator extends AbstractValidator {
        private final ValidationManager vManager;
        private final boolean isRootValidator;

        /**
         * Constructs ObjectValidator.
         *
         * @param isRequired Determines if the field is required. If true, an error will be issued if the field is missing.
         * @param objectVManager ValidationManager of the object stored in this field.
         * @see ObjectValidator Documentation of the validator.
         */
        ObjectValidator(ValidationManager objectVManager, boolean isRequired, boolean isRoot) {
            super(isRequired);
            this.vManager = objectVManager;
            this.isRootValidator = isRoot;
        }

        /**
         * Entry point of the validator.
         *
         * @param data ValidationData record with necessary information to validate the element.
         * @return True if the validation passes, false otherwise.
         */
        @Override
        public Boolean apply(@NotNull ValidationData data) {
            // If we validate the root element, null checks and array checks are not required,
            // as those are handled by the manager.
            if (isRootValidator) return validate(data);
            return super.apply(data);
        }

        /**
         * Validate method, used to validate passed in object.
         *
         * @param data ValidationData record with necessary information to validate the element.
         * @return True of the validation passes, false otherwise.
         * @apiNote Even tho it's public, this method should <i>never</i> be called directly! Call {@link ObjectValidator#apply(ValidationData)} instead!
         */
        @Override
        public Boolean validate(@NotNull ValidationData data) {
            var element = data.validationElement();
            var path = data.currentPath();
            var jsonPath = data.jsonFilePath();
            if (!element.isJsonObject()) {
                Analytics.error("Expected element to be a Json Object.", data);
                return false;
            }

            JsonObject object = element.getAsJsonObject();
            AtomicBoolean validation = new AtomicBoolean(true);

            if (Analytics.isEnabled()) object.entrySet().forEach(entry -> {
                if (!(vManager.validators.containsKey(entry.getKey())))
                    Analytics.warn("Unknown key!", path + "." + entry.getKey(), jsonPath);
            });

            vManager.validators.forEach((field, holder) -> {
                if (!holder.validate(data.getWithField(field, holder.arrayPolicy()))) validation.set(false);
            });

            return validation.get();
        }
    }

}
