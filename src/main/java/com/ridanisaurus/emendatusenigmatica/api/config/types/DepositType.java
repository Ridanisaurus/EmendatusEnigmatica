package com.ridanisaurus.emendatusenigmatica.api.config.types;

import com.ridanisaurus.emendatusenigmatica.api.config.AbstractDCType;
import com.ridanisaurus.emendatusenigmatica.api.config.DCCreationContext;
import com.ridanisaurus.emendatusenigmatica.api.config.DCData;
import com.ridanisaurus.emendatusenigmatica.api.config.DCDataBuilder;
import com.ridanisaurus.emendatusenigmatica.api.config.exceptions.DCValidationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class DepositType extends AbstractDCType {
    public static final DepositType INSTANCE = new DepositType();

    private DepositType() {
        super("deposit");
    }

    @Override
    public boolean isUnique(DCData data, List<DCData> registeredData) {
        return registeredData.stream().noneMatch(it -> Objects.equals(it.getWrappedObject().get("registryName"), data.getWrappedObject().get("registryName")));
    }

    @Override
    public void validate(DCData data) {
        var obj = data.getWrappedObject();
        if (Objects.isNull(obj)) throw new DCValidationException("Object is null.");
        if (!obj.has("registryName")) throw new DCValidationException("ID for default deposit config is missing!");
    }

    public static void registerIfAvailable(@NotNull DCCreationContext ctx, String internalPath, String externalPath) {
        var newData = (Objects.isNull(externalPath)? DCDataBuilder.fromInternalFile(internalPath): DCDataBuilder.fromInternalFile(internalPath, externalPath))
            .markAsDeposit()
            .build();
        if (isIdRegistered(ctx, newData.getWrappedObject().get("registryName").getAsString())) return;
        ctx.register(newData);
    }

    public static boolean isIdRegistered(@NotNull DCCreationContext ctx, String id) {
        return ctx.anyMatch(DepositType.INSTANCE, it -> it.getWrappedObject().get("registryName").getAsString().equals(Objects.requireNonNull(id)));
    }

    public static @Nullable DCData getDataForId(@NotNull DCCreationContext ctx, @NotNull String id) {
        var data = ctx.getData(DepositType.INSTANCE, it -> it.getWrappedObject().get("registryName").getAsString().equals(Objects.requireNonNull(id)));
        if (data.isEmpty()) return null;
        // No need to handle size() > 1 - that scenario should be illegal, as isUnique handles ID verification.
        // If somehow we have 2 identical IDs, we have different problems!
        return data.getFirst();
    }
}
