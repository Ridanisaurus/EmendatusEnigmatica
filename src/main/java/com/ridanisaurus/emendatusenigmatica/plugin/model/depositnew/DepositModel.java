package com.ridanisaurus.emendatusenigmatica.plugin.model.depositnew;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayPolicy;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.FilterMode;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.PluginRegistryValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.ResourceLocationValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.ValuesValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.deprecation.DeprecatedFieldValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.registry.BiomeRegistryValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.registry.DimensionRegistryValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.DataRegistry;
import com.ridanisaurus.emendatusenigmatica.plugin.VanillaPlugin;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepositModel {
    /**
     * Used for extensions.
     */
    public static final MapCodec<DepositModel> MAP_CODEC = RecordCodecBuilder.mapCodec(x -> x.group(
        Codec.STRING.fieldOf("registryName").forGetter(i -> i.id),
        Codec.STRING.fieldOf("type").forGetter(i -> i.type),
        ResourceLocation.CODEC.listOf().fieldOf("biomes").forGetter(i -> i.biomes),
        ResourceLocation.CODEC.fieldOf("dimension").forGetter(i -> i.dimension)
    ).apply(x, DepositModel::new));

    public static final Codec<DepositModel> CODEC = MAP_CODEC.codec();

    public static final List<String> TYPES = new ArrayList<>(List.of(
        "emendatusenigmatica:vanilla_deposit",
        "emendatusenigmatica:sphere_deposit",
        "emendatusenigmatica:geode_deposit",
        "emendatusenigmatica:dike_deposit",
        "emendatusenigmatica:dense_deposit"
    ));


    public static final ValidationManager VALIDATION_MANAGER = ValidationManager.create()
        .addValidator("registryName", new PluginRegistryValidator<>(VanillaPlugin.class, DataRegistry::isDepositRegistered, PluginRegistryValidator.REGISTRATION, true))
        .addValidator("type", new ValuesValidator(TYPES, FilterMode.WHITELIST, true))
        .addValidator("biomes", new ResourceLocationValidator(true, new BiomeRegistryValidator()), ArrayPolicy.REQUIRES_ARRAY.get())
        .addValidator("dimension", new ResourceLocationValidator(true, new DimensionRegistryValidator()))
        .addValidator("config", new DeprecatedFieldValidator(null));

    public final String id;
    public final String type;
    public final List<ResourceLocation> biomes;
    public final ResourceLocation dimension;

    public DepositModel(String id, String type, List<ResourceLocation> biomes, ResourceLocation dimension) {
        this.id = id;
        this.type = type;
        this.biomes = biomes;
        this.dimension = dimension;
    }

    public DepositModel(DepositModel base) {
        this(base.id, base.type, base.biomes, base.dimension);
    }

    public void register(DataRegistry dataRegistry) {}
}
