package com.ridanisaurus.emendatusenigmatica.api.validation.enums;

/**
 * Wrapper used internally for handling the ArrayPolicy. Utility methods available in ArrayPolicy enums.
 * @see ArrayPolicy
 */
public class ArrayHandlingPolicy {
    private final ArrayPolicy arrayPolicy;
    private final boolean allowEmpty;

    protected ArrayHandlingPolicy(ArrayPolicy arrayPolicy, boolean allowEmpty) {
        this.arrayPolicy = arrayPolicy;
        this.allowEmpty = allowEmpty;
    }

    public boolean allowsArrays() {
        return this.arrayPolicy != ArrayPolicy.DISALLOWS_ARRAYS;
    }

    public boolean requiresArray() {
        return this.arrayPolicy == ArrayPolicy.REQUIRES_ARRAY;
    }

    public boolean canBeEmpty() {
        return allowEmpty;
    }

    public ArrayPolicy getLegacyArrayPolicy() {
        return this.arrayPolicy;
    }
}
