package com.ridanisaurus.emendatusenigmatica.api.config.types;

import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.api.config.AbstractDCType;
import com.ridanisaurus.emendatusenigmatica.api.config.DCCreationContext;
import com.ridanisaurus.emendatusenigmatica.api.config.DCData;
import com.ridanisaurus.emendatusenigmatica.api.config.exceptions.DCValidationException;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class StrataType extends AbstractDCType {
    public static final StrataType INSTANCE = new StrataType();

    private StrataType() {
        super("strata");
    }

    @Override
    public boolean isUnique(DCData data, List<DCData> registeredData) {
        return registeredData.stream().noneMatch(it -> Objects.equals(it.getWrappedObject().get("id"), data.getWrappedObject().get("id")));
    }

    @Override
    public void validate(DCData data) {
        var obj = data.getWrappedObject();
        if (Objects.isNull(obj)) throw new DCValidationException("Object is null.");
        if (!obj.has("id")) throw new DCValidationException("ID for default strata config is missing!");
    }

    public static boolean isIdRegistered(@NotNull DCCreationContext ctx, String id) {
        return ctx.anyMatch(StrataType.INSTANCE, it -> it.getWrappedObject().get("id").getAsString().equals(Objects.requireNonNull(id)));
    }

    public static @Nullable StrataModel getDefaultModelForId(@NotNull DCCreationContext ctx, @NotNull String id) {
        var data = getDataForId(ctx, id);
        if (Objects.isNull(data)) return null;
        return JsonOps.INSTANCE.withDecoder(StrataModel.CODEC).apply(data.getWrappedObject()).getOrThrow().getFirst();
    }

    public static void updateModel(@NotNull DCCreationContext ctx, @NotNull StrataModel model) {
        var data = getDataForId(ctx, model.getId());
        if (Objects.isNull(data)) throw new IllegalArgumentException("Provided model isn't registered as a default configuration!");
        data.updateWrappedObject(JsonOps.INSTANCE.withEncoder(StrataModel.CODEC).apply(model).getOrThrow().getAsJsonObject());
    }

    private static @Nullable DCData getDataForId(@NotNull DCCreationContext ctx, @NotNull String id) {
        var data = ctx.getData(StrataType.INSTANCE, it -> it.getWrappedObject().get("id").getAsString().equals(Objects.requireNonNull(id)));
        if (data.isEmpty()) return null;
        // No need to handle size() > 1 - that scenario should be illegal, as isUnique handles ID verification.
        // If somehow we have 2 identical IDs, we have different problems!
        return data.getFirst();
    }
}
