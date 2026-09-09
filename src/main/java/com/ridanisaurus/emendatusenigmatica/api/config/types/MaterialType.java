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

public class MaterialType extends AbstractDCType {
    public static final MaterialType INSTANCE = new MaterialType();

    private MaterialType() {
        super("material");
    }

    @Override
    public boolean isUnique(DCData data, List<DCData> registeredData) {
        return registeredData.stream().noneMatch(it -> Objects.equals(it.getWrappedObject().get("id"), data.getWrappedObject().get("id")));
    }

    @Override
    public void validate(DCData data) {
        var obj = data.getWrappedObject();
        if (Objects.isNull(obj)) throw new DCValidationException("Object is null.");
        if (!obj.has("id")) throw new DCValidationException("ID for default material config is missing!");
    }

    public static boolean isIdRegistered(@NotNull DCCreationContext ctx, String id) {
        return ctx.anyMatch(MaterialType.INSTANCE, it -> it.getWrappedObject().get("id").getAsString().equals(Objects.requireNonNull(id)));
    }

    /**
     * Used to register a new Material configuration, or if already registered -
     * add processedTypes defined in it to the configuration that is already registered.
     * @param ctx DefaultConfigurationCreationContext object
     * @param path Internal path of the material configuration to register.
     * @see DCDataBuilder#fromInternalFile(String) for details
     */
    public static void addTypesOrRegister(@NotNull DCCreationContext ctx, @NotNull String path) {
        addTypesOrRegister(ctx, path, null);
    }

    /**
     * Used to register a new Material configuration, or if already registered -
     * add processedTypes defined in it to the configuration that is already registered.
     * @param ctx DefaultConfigurationCreationContext object
     * @param externalPath External path in the materials directory.
     * @param path Internal path of the material configuration to register.
     * @see DCDataBuilder#fromInternalFile(String, String) for details
     */
    public static void addTypesOrRegister(@NotNull DCCreationContext ctx, @NotNull String path, String externalPath) {
        var newData = (Objects.isNull(externalPath)? DCDataBuilder.fromInternalFile(path): DCDataBuilder.fromInternalFile(path, externalPath)).markAsMaterial().build();
        var newObj = newData.getWrappedObject();
        var defaultData = getDataForId(ctx, newObj.get("id").getAsString());
        if (Objects.isNull(defaultData)) {
            ctx.register(newData);
            return;
        }
        var ogObj = defaultData.getWrappedObject();
        var ogTypes = ogObj.get("processedTypes");
        if (Objects.isNull(ogTypes) || !ogTypes.isJsonArray())
            throw new IllegalStateException(
                "Default configuration for \"%s\" doesn't have array for processedTypes! Please report this to the creator of the plugin the configuration comes from."
                    .formatted(defaultData.getPath())
            );
        var ogTypesArray = ogTypes.getAsJsonArray();

        newObj.get("processedTypes").getAsJsonArray().forEach(type -> {
            if (ogTypesArray.contains(type)) return;
            ogTypesArray.add(type);
        });

        defaultData.updateWrappedObject(ogObj);
    }

    public static List<DCData> getVanillaMaterials(@NotNull DCCreationContext ctx) {
        return ctx.getData(MaterialType.INSTANCE, it -> it.getWrappedObject().get("source").getAsString().equals("vanilla"));
    }

    public static @Nullable DCData getDataForId(@NotNull DCCreationContext ctx, @NotNull String id) {
        var data = ctx.getData(MaterialType.INSTANCE, it -> it.getWrappedObject().get("id").getAsString().equals(Objects.requireNonNull(id)));
        if (data.isEmpty()) return null;
        // No need to handle size() > 1 - that scenario should be illegal, as isUnique handles ID verification.
        // If somehow we have 2 identical IDs, we have different problems!
        return data.getFirst();
    }
}
