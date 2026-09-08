package com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit;

import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationContext;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayHandlingPolicy;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayPolicy;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.IValidationFunction;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.block.BlockModel;

import java.util.Objects;

public class WeightedBlocksValidator implements IValidationFunction {
    private final IValidationFunction validator;

    public WeightedBlocksValidator(IValidationFunction validator) {
        this.validator = Objects.requireNonNull(validator, "Validator can't be null!");
    }

    public WeightedBlocksValidator() {
        this(BlockModel.VALIDATION_MANAGER.getAsValidator(true));
    }

    /**
     * Entry point of the validator.
     *
     * @param ctx ValidationContext record with necessary information to validate the element.
     * @return True if the validation passes, false otherwise.
     */
    @Override
    public Boolean apply(ValidationContext ctx) {
        if (!validator.apply(ctx.getWithAHP(new ArrayHandlingPolicy(ArrayPolicy.REQUIRES_ARRAY, ctx.arrayPolicy().canBeEmpty())))) return false;
        if (ctx.validationElement()
            .getAsJsonArray()
            .asList()
            .stream()
            .mapToLong(it -> it.getAsJsonObject().get("weight").getAsLong())
            .sum() >= Integer.MAX_VALUE
        ) {
            ctx.error("Sum of weights in the Blocks specified can't be above or equal to <code>Integer.MAX_VALUE</code> (<code>%d</code>).".formatted(Integer.MAX_VALUE));
            return false;
        }

        return true;
    }
}
