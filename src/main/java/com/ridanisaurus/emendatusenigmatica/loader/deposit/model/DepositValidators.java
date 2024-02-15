package com.ridanisaurus.emendatusenigmatica.loader.deposit.model;

import com.google.gson.JsonElement;
import com.ridanisaurus.emendatusenigmatica.loader.Validator;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.EEDeposits;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

public class DepositValidators {
    private static final Map<String, Map<String, BiFunction<JsonElement, Path, Boolean>>> validators = new HashMap<>();
    static {
        Map<String, BiFunction<JsonElement, Path, Boolean>> common = new LinkedHashMap<>();
        common.put("type", new Validator("type").getRequiredAcceptsOnlyValidation(EEDeposits.DEPOSIT_TYPES));
        common.put("dimension", new Validator("dimension").RESOURCE_ID_REQUIRED);
        common.put("biomes", new Validator("biomes").RESOURCE_ID);
        common.put("registryName", new Validator("registryName").getIDValidation(EEDeposits.DEPOSIT_IDS));
        validators.put("common", common);
        // Here you can add validators for specific Deposit Types. Take a note for each "type" all common validators are going to be added.
        // Add entry with the same key to override validator if it's not required for your type.
        //TODO: Add validations for all config models. Those... aren't identical so like, 5 different maps time.
    }
    public static Map<String, BiFunction<JsonElement, Path, Boolean>> get(String type) {
        var commonValidators = new LinkedHashMap<>(validators.get("common"));
        if (!type.equals("common")) {
            var typeValidators = validators.get(type);
            if (Objects.nonNull(typeValidators)) commonValidators.putAll(typeValidators);
        }
        return commonValidators;
    }

    public static Map<String, BiFunction<JsonElement, Path, Boolean>> get(@Nullable JsonElement typeElement) {
        if (Objects.isNull(typeElement) || !typeElement.isJsonPrimitive()) return get("common");
        return get(typeElement.getAsString());
    }
}
