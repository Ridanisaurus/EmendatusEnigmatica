package com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit;

import com.google.gson.JsonObject;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationContext;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationHelper;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayPolicy;
import com.ridanisaurus.emendatusenigmatica.config.EEConfig;
import com.ridanisaurus.emendatusenigmatica.util.summary.SummaryHandler;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

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

    @Override
    public boolean validate(@NotNull JsonObject object, Path jsonPath) {
        var path = ValidationHelper.obfuscatePath(jsonPath);

        if (!object.isJsonObject()) {
            SummaryHandler.error("Expected Json Object at root!", "Root of the file is required to be an object. Arrays are not supported.", "root", path);
            return false;
        }

        if (object.isEmpty()) {
            if (!EEConfig.startup.skipEmptyJsons.get()) SummaryHandler.error("Root object is empty!", "root", path);
            return false;
        }

        // This manager should only be used as DepositModel extension ValidationManager.
        // If this is executed, the base validation was valid, and the type has to be present.
        if (!object.get("type").getAsString().equals(type)) {
            return false;
        }

        return this.rootValidator.apply(new ValidationContext(object, object, "root", path, ArrayPolicy.DISALLOWS_ARRAYS));
    }
}
