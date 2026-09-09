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
