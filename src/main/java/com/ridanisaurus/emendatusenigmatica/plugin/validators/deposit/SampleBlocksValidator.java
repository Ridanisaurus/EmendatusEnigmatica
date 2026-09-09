package com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit;

import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.IValidationFunction;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.RangeBasedValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.block.SampleBlockModel;

public class SampleBlocksValidator extends RangeBasedValidator {
    private static final IValidationFunction validator = new WeightedBlocksValidator(SampleBlockModel.VALIDATION_MANAGER.getAsValidator(false));
    public SampleBlocksValidator() {
		super(Types.INTEGER, validator, "chance", 0d, null, false);
    }
}
