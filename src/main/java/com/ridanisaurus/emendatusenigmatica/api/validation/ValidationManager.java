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
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayHandlingPolicy;
import com.ridanisaurus.emendatusenigmatica.config.EEConfig;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayPolicy;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.AbstractValidator;
import com.ridanisaurus.emendatusenigmatica.util.summary.SummaryHandler;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

/**
 * <h1>Validation API</h1>
 * ValidationManager is a root class of the JSON validation system used by EmendatusEnigmatica.
 * It manages the {@link com.ridanisaurus.emendatusenigmatica.api.validation.validators.IValidationFunction IValidationFunctions}
 * specified for it's {@link com.ridanisaurus.emendatusenigmatica.loader.EEModelDefinition Model Definition}.
 *
 * <h2>Usage of the API</h2>
 * The Root of the JSON object is represented by this class, with other validators being fields starting from the root.
 * You can get the instance of ValidationManager with use of {@link ValidationManager#create()} method.
 * <br><br>
 * By using the {@link ValidationManager#addValidator(String, Function)} method,
 * you can define fields of the JSON object, with the assigned validation function of that field.
 * Nested JSON objects are supported with use of {@link #getAsValidator(boolean)} method from the inner-object's ValidationManager.
 * <br><br>
 * Universal validators are available in {@link com.ridanisaurus.emendatusenigmatica.api.validation.validators Validators} package of this API.
 *
 * <h2>Example of usage</h2>
 * Let's create a ValidationManager for this {@link com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel JSON model}:
 * <pre>
 * {
 *   "id": "minecraft_stone",
 *   "baseTexture": "minecraft:block/stone",
 *   "suffix": "stone",
 *   "fillerType": "minecraft:stone",
 *   "localizedName": "Stone"
 * }
 * </pre>
 *
 * Ignoring implementation specifics (ex. Registry Validation),
 * we are required to validate 3 fields of type {@link String},
 * and 2 fields of type {@link net.minecraft.resources.ResourceLocation ResourceLocation}.
 * This can be achieved with usage of {@link com.ridanisaurus.emendatusenigmatica.api.validation.validators.TypeValidator TypeValidator}
 * and {@link com.ridanisaurus.emendatusenigmatica.api.validation.validators.ResourceLocationValidator ResourceLocationValidator} from the API package.
 * <pre>
 * public static final ValidationManager VALIDATION_MANAGER = ValidationManager.create()
 *     .addValidator("id",            new TypeValidator(Types.STRING, true))
 *     .addValidator("baseTexture",   new ResourceLocationValidator(true))
 *     .addValidator("fillerType",    new ResourceLocationValidator(true))
 *     .addValidator("suffix",        new TypeValidator(Types.STRING, true))
 *     .addValidator("localizedName", new TypeValidator(Types.STRING, true))
 * </pre>
 *
 * This ValidationManager will now only allow JSON objects with this specific structure.<br>
 * Take note that most of the Validators have some configuration arguments,
 * like Type (TypeValidator) and requirement of the field in this case.
 *
 * <h2>Creation of custom validators</h2>
 * You might have realized by now that validation of types and some basic schema for the fields is not enough.<br>
 * You can create your own validators with use of the {@link AbstractValidator},
 * which handles the basics for you,
 * or {@link com.ridanisaurus.emendatusenigmatica.api.validation.validators.IValidationFunction IValidationFunction} for fully custom implementation.
 * Utility methods are available for you in {@link ValidationHelper} class.
 * <br>
 * You can find examples of custom validators in the {@link com.ridanisaurus.emendatusenigmatica.plugin.validators Vanilla Plugin Validators} package.
 * @see ValidationHelper Validation Utility class.
 */
public class ValidationManager {
    protected final Map<String, ValidatorHolder> validators = new LinkedHashMap<>();
    protected final ObjectValidator rootValidator = new ObjectValidator( this, true, true);
    protected ValidationManager() {}

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
        validate(object, jsonPath, null);
    }

    /**
     * Used to start the validation of the JsonObject.
     * @param object JsonObject to validate.
     * @param jsonPath Path of the file.
     * @param logHandler Custom LogHandler for this validation run.
     * @return True if validation passes, false otherwise.
     */
    public boolean validate(@NotNull JsonObject object, Path jsonPath, @Nullable IValidationLogHandler logHandler) {
        var path = ValidationHelper.obfuscatePath(jsonPath);
        var ctx = Objects.isNull(logHandler)?
            new ValidationContext(object, object, "root", path, ArrayPolicy.DISALLOWS_ARRAYS.get()):
            new ValidationContext(object, object, "root", path, ArrayPolicy.DISALLOWS_ARRAYS.get(), logHandler);

        if (!object.isJsonObject()) {
            ctx.error("Expected Json Object at root!", "Root of the file is required to be an object. Arrays are not supported.", "root", path);
            return false;
        }

        if (object.isEmpty()) {
            if (!EEConfig.startup.skipEmptyJsons.get()) ctx.error("Root object is empty!", "root", path);
            return false;
        }

        // Enters automatic validator execution. After this point, stack-traces are a slight mess!
        return this.rootValidator.apply(ctx);
    }

    /**
     * @return List of fields with a registered validator.
     */
    public List<String> getRegisteredFields() {
        return List.copyOf(this.validators.keySet());
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
     * under specified field, with specified ArrayHandlingPolicy.
     * @param field Field to add validator for.
     * @param validator - Validation Function.
     * @param arrayPolicy ArrayPolicy of this field.
     * @return {@code this} instance of the {@link ValidationManager}
     */
    @CanIgnoreReturnValue
    public ValidationManager addValidator(@NotNull String field, @NotNull Function<ValidationContext, Boolean> validator, @NotNull ArrayHandlingPolicy arrayPolicy) {
        this.validators.put(
            Objects.requireNonNull(field, "Field name can't be null!"),
            new ValidatorHolder(
                Objects.requireNonNull(validator, "Validator can't be null!"),
                Objects.requireNonNull(arrayPolicy, "Array Handling Policy can't be null!")
            )
        );
        return this;
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
    @Deprecated
    public ValidationManager addValidator(@NotNull String field, @NotNull Function<ValidationContext, Boolean> validator, @NotNull ArrayPolicy arrayPolicy) {
        return addValidator(field, validator, arrayPolicy.get());
    }

    /**
     * Used to add validator to this ValidationManager,
     * under specified field, with default {@link ArrayPolicy#DISALLOWS_ARRAYS};
     * @param field Field to add validator for.
     * @param validator - Validation Function.
     * @return {@code this} instance of the {@link ValidationManager}
     */
    @CanIgnoreReturnValue
    public ValidationManager addValidator(@NotNull String field, @NotNull Function<ValidationContext, Boolean> validator) {
        return this.addValidator(field, validator, ArrayPolicy.DISALLOWS_ARRAYS.get());
    }

    /**
     * ObjectValidator validates JsonObject with use of the {@link ValidationManager} validators.
     * <br><br>
     * It's main purpose is to determine which fields are unknown,
     * and to call all known validators,
     * which are stored in the ValidationManager this validator is acquired from.
     */
    public static class ObjectValidator extends AbstractValidator {
        protected final ValidationManager vManager;
        protected final boolean isRootValidator;

        /**
         * Constructs ObjectValidator.
         *
         * @param isRequired Determines if the field is required. If true, an error will be issued if the field is missing.
         * @param objectVManager ValidationManager of the object stored in this field.
         * @see ObjectValidator Documentation of the validator.
         */
        protected ObjectValidator(ValidationManager objectVManager, boolean isRequired, boolean isRoot) {
            super(isRequired);
            this.vManager = objectVManager;
            this.isRootValidator = isRoot;
        }

        /**
         * Entry point of the validator.
         *
         * @param ctx ValidationContext record with necessary information to validate the element.
         * @return True if the validation passes, false otherwise.
         */
        @Override
        public Boolean apply(@NotNull ValidationContext ctx) {
            // If we validate the root element, null checks and array checks are not required,
            // as those are handled by the manager.
            if (isRootValidator) return validate(ctx);
            return super.apply(ctx);
        }

        /**
         * Validate method, used to validate passed in object.
         *
         * @param ctx ValidationContext record with necessary information to validate the element.
         * @return True of the validation passes, false otherwise.
         * @apiNote Even tho it's public, this method should <i>never</i> be called directly! Call {@link ObjectValidator#apply(ValidationContext)} instead!
         */
        @Override
        public Boolean validate(@NotNull ValidationContext ctx) {
            var element = ctx.validationElement();
            var path = ctx.currentPath();
            var jsonPath = ctx.jsonFilePath();
            if (!element.isJsonObject()) {
                ctx.error("Expected element to be a Json Object.");
                return false;
            }

            JsonObject object = element.getAsJsonObject();
            AtomicBoolean validation = new AtomicBoolean(true);

            object.entrySet().forEach(entry -> {
                if (!(vManager.validators.containsKey(entry.getKey())))
                    ctx.warn("Unknown key!", path + "." + entry.getKey(), jsonPath);
            });

            vManager.validators.forEach((field, holder) -> {
                if (!holder.validate(ctx.getWithField(field, holder.arrayPolicy()))) validation.set(false);
            });

            return validation.get();
        }
    }

}
