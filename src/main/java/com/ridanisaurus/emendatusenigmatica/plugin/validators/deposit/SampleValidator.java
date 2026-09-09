package com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit;

import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.RangeBasedValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.sample.SampleConfig;

public class SampleValidator extends RangeBasedValidator {

    public SampleValidator() {
        super(Types.INTEGER, SampleConfig.VALIDATION_MANAGER.getAsValidator(false), "sample.chance", 0d, null, true);
    }
}
