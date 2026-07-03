package com.ridanisaurus.emendatusenigmatica.loader;

import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationData;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.IValidationFunction;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;

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
     * @param data ValidationData record with necessary information to validate the element.
     * @return True if the validation passes, false otherwise.
     */
    @Override
    public Boolean apply(ValidationData data) {
        var element = data.validationElement();
        if (Objects.isNull(element)) return true;
        if (data.validationElement().isJsonObject()) return true;

        if (element.isJsonArray()) {
            Analytics.error("Arrays are not allowed for this field!", data);
            return false;
        }

        Analytics.error("This field only accepts an object!", data);
        return false;
    }
}
