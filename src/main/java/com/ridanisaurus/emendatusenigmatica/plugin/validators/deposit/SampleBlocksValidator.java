package com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit;

import com.ridanisaurus.emendatusenigmatica.api.validation.validators.IValidationFunction;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.block.SampleBlockModel;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.FieldTrueValidator;

/**
 * Utility class, which extends {@link FieldTrueValidator}, preconfigured for validation of <code>root.config.sampleBlocks</code> field of deposits.
 */
public class SampleBlocksValidator extends FieldTrueValidator {
    private static final IValidationFunction validator = new WeightedBlocksValidator(SampleBlockModel.VALIDATION_MANAGER.getAsValidator(false));
    public SampleBlocksValidator() {
		super("generateSamples", validator);
    }
}
