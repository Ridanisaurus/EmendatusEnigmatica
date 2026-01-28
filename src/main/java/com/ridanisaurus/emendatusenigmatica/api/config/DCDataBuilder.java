package com.ridanisaurus.emendatusenigmatica.api.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.api.config.types.DepositType;
import com.ridanisaurus.emendatusenigmatica.api.config.types.MaterialType;
import com.ridanisaurus.emendatusenigmatica.api.config.types.StrataType;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.common.CommonDepositModelBase;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.InputStreamReader;
import java.util.Objects;

//TODO: Rework this to work based on EEMaterialDefinition.
public class DCDataBuilder {
    private static final Gson gson = new Gson();
    private final JsonObject object;
    private AbstractDCType type;
    private String name;

    private DCDataBuilder(JsonObject obj) {
        this.object = obj;
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull DCDataBuilder fromJsonObject(JsonObject obj) {
        return new DCDataBuilder(obj);
    }

    /**
     * Used to construct DCDataBuilder, which parses the required JSONObject from an internal path.
     * @param internalPath Internal path of the JSON file to parse.
     * @implNote This method automatically sets the ConfigurationName to the file name of the internal asset.<br>
     * If the provided path doesn't contain a dot - automatically adds .json extension to the end.
     */
    public static @NotNull DCDataBuilder fromInternalFile(@NotNull String internalPath) {
        return fromInternalFile(internalPath, StringUtils.substringAfterLast(internalPath, "/"));
    }

    /**
     * Used to construct DCDataBuilder, which parses the required JSONObject from an internal path.
     * @param internalPath Internal path of the JSON file to parse.
     * @param configPath Path of the final file in the types directory.
     * @implNote if the provided path doesn't contain a dot - automatically adds the name of the internal asset at the end.<br>
     * If the provided internalPath doesn't contain a dot - automatically adds .json extension to the end.
     */
    public static @NotNull DCDataBuilder fromInternalFile(@NotNull String internalPath, @NotNull String configPath) {
        Objects.requireNonNull(internalPath, "InternalPath can't be null!");
        if (!internalPath.contains(".")) internalPath += ".json";
        if (!internalPath.startsWith("/")) internalPath = "/" + internalPath;

        DCDataBuilder builder;
        try (var stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(internalPath)) {
            builder = new DCDataBuilder(gson.fromJson(new InputStreamReader(Objects.requireNonNull(stream, "getResourceAsStream returned null")), JsonObject.class));
        } catch (Exception e) {
            throw new RuntimeException("Failed getting internal file under \"%s\" path. Is your Jar corrupted?".formatted(internalPath), e);
        }

        if (!Objects.requireNonNull(configPath, "configPath can't be null!").contains("."))
            configPath += "/" + StringUtils.substringAfterLast(internalPath, "/");

        builder.setName(configPath);
        return builder;
    }

    public static @NotNull DCDataBuilder fromMaterialModel(@NotNull MaterialModel model) {
        DCDataBuilder builder = new DCDataBuilder(JsonOps.INSTANCE.withEncoder(MaterialModel.CODEC).apply(model).getOrThrow().getAsJsonObject());
        builder.markAsMaterial();
        builder.setName(model.getId());
        return builder;
    }

    public static @NotNull DCDataBuilder fromStrataModel(@NotNull StrataModel model) {
        DCDataBuilder builder = new DCDataBuilder(JsonOps.INSTANCE.withEncoder(StrataModel.CODEC).apply(model).getOrThrow().getAsJsonObject());
        builder.markAsStrata();
        builder.setName(model.getId());
        return builder;
    }

    public static <T extends CommonDepositModelBase> @NotNull DCDataBuilder fromDepositModel(@NotNull T model, @NotNull Codec<T> codec) {
        DCDataBuilder builder = new DCDataBuilder(JsonOps.INSTANCE.withEncoder(codec).apply(model).getOrThrow().getAsJsonObject());
        builder.markAsDeposit();
        builder.setName(model.getName());
        return builder;
    }

    public DCDataBuilder setType(@NotNull AbstractDCType type) {
        this.type = type;
        return this;
    }

    public DCDataBuilder setName(@NotNull String name) {
        this.name = name;
        return this;
    }

    public DCDataBuilder markAsMaterial() {
        return this.setType(MaterialType.INSTANCE);
    }

    public DCDataBuilder markAsStrata() {
        return this.setType(StrataType.INSTANCE);
    }

    public DCDataBuilder markAsDeposit() {
        return this.setType(DepositType.INSTANCE);
    }

    /**
     * Constructs DCData object, validates provided data and registers the configuration.
     * @param ctx DefaultConfigurationCreationContext
     */
    public DCData finish(@NotNull DCCreationContext ctx) {
        var data = new DCData(object, name, type, ctx.getOrigin());
        ctx.register(data);
        return data;
    }

    /**
     * Constructs DCData object and validates provided data;
     * @return Constructed, unregistered DCData
     */
    public DCData build() {
        return new DCData(object, name, type, "unregistered");
    }
}
