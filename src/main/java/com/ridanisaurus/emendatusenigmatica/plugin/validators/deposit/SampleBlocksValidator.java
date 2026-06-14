package com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit;

import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.sample.DepositSampleBlockModel;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.FieldTrueValidator;

/**
 * Utility class, which extends {@link FieldTrueValidator}, preconfigured for validation of <code>root.config.sampleBlocks</code> field of deposits.
 */
public class SampleBlocksValidator extends FieldTrueValidator {
    public SampleBlocksValidator() {
		super("generateSamples", DepositSampleBlockModel.VALIDATION_MANAGER.getAsValidator(false));
    }
}
