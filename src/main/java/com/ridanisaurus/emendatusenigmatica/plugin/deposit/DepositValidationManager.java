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

package com.ridanisaurus.emendatusenigmatica.plugin.deposit;

import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayPolicy;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.FilterMode;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.ResourceLocationValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.ValuesValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.registry.BiomeRegistryValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.registry.DimensionRegistryValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.DefaultLoader;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.EERegistryValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit.DepositConfigValidator;

/**
 * This class is only used to hold the ValidationManager used for the Deposit JSON validation. Really. That's it.
 */
public class DepositValidationManager {
    public static final ValidationManager VALIDATION_MANAGER = ValidationManager.create()
        .addValidator("biomes",       new ResourceLocationValidator(false, new BiomeRegistryValidator()), ArrayPolicy.REQUIRES_ARRAY)
        .addValidator("registryName", new EERegistryValidator(DefaultLoader.DEPOSIT_IDS, EERegistryValidator.REGISTRATION, true))
        .addValidator("type",         new ValuesValidator(DefaultLoader.DEPOSIT_TYPES, FilterMode.WHITELIST, true))
        .addValidator("dimension",    new ResourceLocationValidator(true, new DimensionRegistryValidator()))
        .addValidator("config",       new DepositConfigValidator());

}
