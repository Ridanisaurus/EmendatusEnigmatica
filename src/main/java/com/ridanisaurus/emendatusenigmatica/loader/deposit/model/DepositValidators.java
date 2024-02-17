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
        // This one should be overridden for each type, it's only as a "default" for an unknown type.
        common.put("config", new Validator("config").REQUIRED);
        validators.put("common", common);
        // Here you can add validators for specific Deposit Types. Take a note for each "type" all common validators are going to be added.
        // Add entry with the same key to override validator if it's not required for your type.
//        validators.put(EEDeposits.DepositType.VANILLA.getType(),new LinkedHashMap<>(Map.of("config", new Validator("config").getRequiredObjectValidation(VanillaDepositConfigModel.validators))));
//        validators.put(EEDeposits.DepositType.DENSE.getType(),  new LinkedHashMap<>(Map.of("config", new Validator("config").getRequiredObjectValidation(DenseDepositConfigModel.validators))));
//        validators.put(EEDeposits.DepositType.DIKE.getType(),   new LinkedHashMap<>(Map.of("config", new Validator("config").getRequiredObjectValidation(DikeDepositConfigModel.validators))));
//        validators.put(EEDeposits.DepositType.GEODE.getType(),  new LinkedHashMap<>(Map.of("config", new Validator("config").getRequiredObjectValidation(GeodeDepositConfigModel.validators))));
//        validators.put(EEDeposits.DepositType.SPHERE.getType(), new LinkedHashMap<>(Map.of("config", new Validator("config").getRequiredObjectValidation(SphereDepositConfigModel.validators))));
//        validators.put(EEDeposits.DepositType.TEST.getType(),   new LinkedHashMap<>(Map.of("config", new Validator("config").getRequiredObjectValidation(TestDepositConfigModel.validators))));
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
