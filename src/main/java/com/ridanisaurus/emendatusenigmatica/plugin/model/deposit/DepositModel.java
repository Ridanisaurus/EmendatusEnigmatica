package com.ridanisaurus.emendatusenigmatica.plugin.model.deposit;

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
import com.ridanisaurus.emendatusenigmatica.loader.EEModelExtension;
import com.ridanisaurus.emendatusenigmatica.plugin.DataRegistry;
import com.ridanisaurus.emendatusenigmatica.plugin.VanillaPlugin;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.MultiStrataRuleTest;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Emendatus Enigmatica Deposits</h1>
 * <h2>Class description</h2>
 * DepositModel is a base class for all deposit types.
 * It contains the most basic information required for successful generation of placed/configured features for minecraft,
 * and utility methods used by other systems, like EMI/JEI World generation plugin.<br><br>
 * <b>All Deposit Types</b> have to extend this class and implement its utility methods.
 *
 * <h2>Creating a custom type</h2>
 * To create a deposit type, few things are required:
 * <ul>
 * <li>Model class, extending DepositModel,
 * properly registered as {@link EEModelExtension} of {@link VanillaPlugin#DEPOSIT_DEFINITION}.</li>
 * <li>Registered implementation of minecraft's {@link Feature}</li>
 * </ul>
 */
public abstract class DepositModel implements FeatureConfiguration {
    /**
     * MAP_CODEC meant to be included in the Extension Codec.
     */
    public static final MapCodec<DepositModel> MAP_CODEC = RecordCodecBuilder.mapCodec(x -> x.group(
        Codec.STRING.fieldOf("registryName").forGetter(i -> i.id),
        Codec.STRING.fieldOf("type").forGetter(i -> i.type),
        Codec.STRING.listOf().fieldOf("biomes").forGetter(i -> i.biomes),
        ResourceLocation.CODEC.fieldOf("dimension").forGetter(i -> i.dimension),
        Codec.list(Codec.STRING).fieldOf("fillerTypes").orElse(List.of()).forGetter(it -> it.fillerTypes)
        ).apply(x, BaseDepositModel::new)
    );

    /**
     * MODEL_CODEC meant to be used as a Codec for EEModelDefinition.
     */
    public static final Codec<DepositModel> MODEL_CODEC = MAP_CODEC.codec();

    /**
     * FEATURE_CODEC meant to be used as a Codec for ConfiguredFeature serialization. Should not be used by EE or Extensions.
     */
    public static <M extends DepositModel> Codec<M> getFeatureCodec(Codec<M> codec) {
        return RecordCodecBuilder.create(x -> x.group(
            codec.fieldOf("model").forGetter(it -> it),
            MultiStrataRuleTest.CODEC.forGetter(it -> it.target)
        ).apply(x, (model, target) -> {
            model.target = target;
            return model;
        }));
    }

    /**
     * List of the acceptable Types by the {@link DepositModel#VALIDATION_MANAGER DepositModel#VALIDATION_MANAGER}.
     */
    public static final List<String> TYPES = new ArrayList<>(List.of(
        "emendatusenigmatica:vanilla_deposit",
        "emendatusenigmatica:sphere_deposit",
        "emendatusenigmatica:geode_deposit",
        "emendatusenigmatica:dike_deposit",
        "emendatusenigmatica:dense_deposit"
    ));

    public static final ValidationManager VALIDATION_MANAGER = ValidationManager.create()
        .addValidator("registryName",   new PluginRegistryValidator<>(VanillaPlugin.class, DataRegistry::isDepositRegistered, PluginRegistryValidator.REGISTRATION_MODE, true))
        .addValidator("type",           new ValuesValidator(TYPES, FilterMode.WHITELIST, true))
        .addValidator("biomes",         new ResourceLocationValidator(true, true, new BiomeRegistryValidator()), ArrayPolicy.REQUIRES_ARRAY.get())
        .addValidator("dimension",      new ResourceLocationValidator(true, new DimensionRegistryValidator()))
        .addValidator("fillerTypes",    new PluginRegistryValidator<>(VanillaPlugin.class, DataRegistry::isStrataRegistered, PluginRegistryValidator.REFERENCE_MODE, "Strata", true), ArrayPolicy.REQUIRES_ARRAY.getNonEmpty())
        .addValidator("config",         new DeprecatedFieldValidator(null));

    public final String id;
    public final String type;
    public final List<String> biomes;
    public final ResourceLocation dimension;
    public final List<String> fillerTypes;

    // Feature Configuration related
    // No need to serialize MSRT as it can be reconstructed from model data.
    public MultiStrataRuleTest target = null;

    public DepositModel(
        String id,
        String type,
        List<String> biomes,
        ResourceLocation dimension,
        List<String> fillerTypes
    ) {
        this.id = id;
        this.type = type;
        this.biomes = biomes;
        this.dimension = dimension;
        this.fillerTypes = fillerTypes;
    }

    @Contract(pure = true)
    public DepositModel(@NotNull DepositModel base) {
        this(base.id, base.type, base.biomes, base.dimension, base.fillerTypes);
        // Not serialized by default.
        this.target = base.target;
    }

    /**
     * Method used to setup {@link MultiStrataRuleTest} of this model and register the Model.
     * @param dataRegistry Vanilla-Plugin Data Registry for Strata lookup.
     * @see DepositModel Deposit Documentation
     * @apiNote NO-OP for non-extended models. See implementation of {@link BaseDepositModel}.
     */
    public void register(DataRegistry dataRegistry) {
        this.target = new MultiStrataRuleTest(this.fillerTypes, dataRegistry);
        dataRegistry.registerDeposit(this);
    }

    /**
     * Used to provide a {@link ConfiguredFeature} for this model.
     * @return ConfiguredFeature ready for registration.
     */
    public abstract ConfiguredFeature<?,?> getConfiguredFeature();

    /**
     * Used to provide ore placement for this model.
     * @return List of {@link PlacementModifier}
     */
    public abstract List<PlacementModifier> getOrePlacement();

    // Emi compat
    /**
     * Used to create Emi Widget of this specific deposit model.
     */
    public abstract void createEmiWidget(WidgetHolder widgets);

    /**
     * Used to provide EMI with the blocks this deposit can generate.
     * @return List of blocks this deposit treats as "output".
     */
    public abstract List<EmiStack> getEmiOutputs();

    // Private
    /**
     * Private Dummy used for serialization. <i>Can't be used for registration!</i>
     */
    @ApiStatus.Internal
    private static class BaseDepositModel extends DepositModel {
        public BaseDepositModel(String id, String type, List<String> biomes, ResourceLocation dimension, List<String> fillerTypes) {
            super(id, type, biomes, dimension, fillerTypes);
        }

        @Override
        public ConfiguredFeature<?, ?> getConfiguredFeature() {
            throw new NotImplementedException("Can't get ConfiguredFeature for a base Deposit Model!");
        }

        @Override
        public List<PlacementModifier> getOrePlacement() {
            throw new NotImplementedException("Can't get OrePlacement for a base Deposit Model!");
        }

        @Override
        public void createEmiWidget(WidgetHolder widgets) {
            throw new NotImplementedException("Can't configure EMI Widget for base Deposit Model!");
        }

        @Override
        public List<EmiStack> getEmiOutputs() {
            throw new NotImplementedException("Can't provide EMI Outputs for base Deposit Model!");
        }

        @Override
        public void register(DataRegistry dataRegistry) {}
    }
}
