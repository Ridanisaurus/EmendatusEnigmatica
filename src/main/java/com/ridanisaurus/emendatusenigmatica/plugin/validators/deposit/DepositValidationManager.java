/*
 * MIT License
 *
 * Copyright (c) 2026. Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit;

import com.google.gson.JsonObject;
import com.ridanisaurus.emendatusenigmatica.api.validation.IValidationLogHandler;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationContext;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationHelper;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayPolicy;
import com.ridanisaurus.emendatusenigmatica.config.EEConfig;
import com.ridanisaurus.emendatusenigmatica.loader.EEPluginLoader;
import com.ridanisaurus.emendatusenigmatica.util.summary.SummaryHandler;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.Objects;

/**
 * Modified version of ValidationManager, for use with the Extension-Based type system for deposits.
 * @implNote Added type validation before running the main root validator.
 * @see ValidationManager ValidationManager documentation.
 */
public class DepositValidationManager extends ValidationManager {
    private final String type;

    private DepositValidationManager(String type) {
        this.type = Objects.requireNonNull(type, "Deposit Type can't be null!");
    }

    @Contract("_ -> new")
    public static @NotNull DepositValidationManager create(String type) {
        return new DepositValidationManager(type);
    }

    public static @NotNull DepositValidationManager createWithStrata(String type) {
        var out = new DepositValidationManager(type);
//        out.addValidator("")
        return out;
    }

    @Override
    public boolean validate(@NotNull JsonObject object, @NotNull Path jsonPath, @NotNull EEPluginLoader pluginLoader, @Nullable IValidationLogHandler logHandler) {
        // This manager should only be used as DepositModel extension ValidationManager.
        // If this is executed, the base validation was valid, and the type has to be present.
        if (!object.get("type").getAsString().equals(type)) return false;
        return super.validate(object, jsonPath, pluginLoader, logHandler);
    }
}
