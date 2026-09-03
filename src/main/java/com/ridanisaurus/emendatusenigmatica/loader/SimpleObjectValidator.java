package com.ridanisaurus.emendatusenigmatica.loader;

import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationContext;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.IValidationFunction;
import com.ridanisaurus.emendatusenigmatica.util.summary.SummaryHandler;

import java.util.Objects;

/**
 * A simple validator used to check if the field is an object.
 * This validator doesn't respect ArrayPolicy and always acts like {@link com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayPolicy#DISALLOWS_ARRAYS} is provided.
 */
public class SimpleObjectValidator implements IValidationFunction {
    public static SimpleObjectValidator INSTANCE = new SimpleObjectValidator();

    private SimpleObjectValidator() {}

    /**
     * Entry point of the validator.
     *
     * @param ctx ValidationContext record with necessary information to validate the element.
     * @return True if the validation passes, false otherwise.
     */
    @Override
    public Boolean apply(ValidationContext ctx) {
        var element = ctx.validationElement();
        if (Objects.isNull(element)) return true;
        if (ctx.validationElement().isJsonObject()) return true;

        if (element.isJsonArray()) {
            ctx.error("Arrays are not allowed for this field!");
            return false;
        }

        ctx.error("This field only accepts an object!");
        return false;
    }
}
