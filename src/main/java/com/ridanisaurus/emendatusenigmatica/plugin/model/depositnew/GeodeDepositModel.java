package com.ridanisaurus.emendatusenigmatica.plugin.model.depositnew;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayPolicy;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.FilterMode;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.NumberRangeValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.ResourceLocationValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.TypeValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.ValuesValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.registry.BlockRegistryValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.DataRegistry;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.common.DepositBlockModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.sample.DepositSampleBlockModel;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.EERegistryValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.MaxValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit.DepositValidationManager;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit.SampleBlocksValidator;

import java.util.List;

public class GeodeDepositModel extends DepositModel {
    public static final Codec<GeodeDepositModel> CODEC = RecordCodecBuilder.create(x -> x.group(
        DepositModel.MAP_CODEC.forGetter(it -> it),
        Codec.list(DepositBlockModel.CODEC).fieldOf("outerShellBlocks").orElse(List.of()).forGetter(i -> i.outerShellBlocks),
        Codec.list(DepositBlockModel.CODEC).fieldOf("innerShellBlocks").orElse(List.of()).forGetter(i -> i.innerShellBlocks),
        Codec.list(DepositBlockModel.CODEC).fieldOf("innerBlocks").orElse(List.of()).forGetter(i -> i.innerBlocks),
        Codec.list(DepositBlockModel.CODEC).fieldOf("fillBlocks").orElse(List.of()).forGetter(i -> i.fillBlocks),
        Codec.list(Codec.STRING).fieldOf("fillerTypes").orElse(List.of()).forGetter(it -> it.fillerTypes),
        Codec.list(Codec.STRING).fieldOf("clusters").orElse(List.of()).forGetter(i -> i.clusters),
        Codec.INT.fieldOf("chance").orElse(0).forGetter(it -> it.chance),
        Codec.DOUBLE.fieldOf("crackChance").orElse(0D).forGetter(it -> it.crackChance),
        Codec.INT.fieldOf("minYLevel").orElse(0).forGetter(it -> it.minYLevel),
        Codec.INT.fieldOf("maxYLevel").orElse(0).forGetter(it -> it.maxYLevel),
        Codec.STRING.fieldOf("placement").orElse("uniform").forGetter(it -> it.placement),
        Codec.STRING.fieldOf("rarity").orElse("rare").forGetter(it -> it.rarity),
        Codec.BOOL.fieldOf("generateSamples").orElse(false).forGetter(it -> it.generateSamples),
        Codec.list(DepositSampleBlockModel.CODEC).fieldOf("sampleBlocks").orElse(List.of()).forGetter(it -> it.sampleBlocks)
    ).apply(x, GeodeDepositModel::new));

    public static final ValidationManager VALIDATION_MANAGER = DepositValidationManager.create("emendatusenigmatica:geode_deposit")
        .addValidator("outerShellBlocks", DepositBlockModel.VALIDATION_MANAGER.getAsValidator(true), ArrayPolicy.REQUIRES_ARRAY.getNonEmpty())
        .addValidator("innerShellBlocks", DepositBlockModel.VALIDATION_MANAGER.getAsValidator(true), ArrayPolicy.REQUIRES_ARRAY.getNonEmpty())
        .addValidator("innerBlocks",      DepositBlockModel.VALIDATION_MANAGER.getAsValidator(true), ArrayPolicy.REQUIRES_ARRAY.getNonEmpty())
        .addValidator("fillBlocks",       DepositBlockModel.VALIDATION_MANAGER.getAsValidator(true), ArrayPolicy.REQUIRES_ARRAY.getNonEmpty())
        .addValidator("fillerTypes",      new EERegistryValidator(StrataModel.REGISTERED_IDS, EERegistryValidator.REFERENCE, "Strata", true), ArrayPolicy.REQUIRES_ARRAY.getNonEmpty())
        .addValidator("clusters",         new ResourceLocationValidator(false, new BlockRegistryValidator()), ArrayPolicy.REQUIRES_ARRAY.getNonEmpty())
        .addValidator("chance",           new NumberRangeValidator(Types.INTEGER, 1, 100, true))
        .addValidator("crackChance",      new NumberRangeValidator(Types.FLOAT, 0, 1, false))
        .addValidator("minYLevel",        new NumberRangeValidator(Types.INTEGER, -64, 320, true))
        .addValidator("maxYLevel",        new MaxValidator(Types.INTEGER, "minYLevel", -64, 320, true))
        .addValidator("placement",        new ValuesValidator(List.of("uniform", "triangle"), FilterMode.WHITELIST, false))
        .addValidator("rarity",           new ValuesValidator(List.of("common", "rare"), FilterMode.WHITELIST, false))
        .addValidator("generateSamples",  new TypeValidator(Types.BOOLEAN, false))
        .addValidator("sampleBlocks",    	new SampleBlocksValidator(), ArrayPolicy.REQUIRES_ARRAY.getNonEmpty());

    public final List<DepositBlockModel> outerShellBlocks;
    public final List<DepositBlockModel> innerShellBlocks;
    public final List<DepositBlockModel> innerBlocks;
    public final List<DepositBlockModel> fillBlocks;
    public final List<String> fillerTypes;
    public final List<String> clusters;
    public final int chance;
    public final double crackChance;
    public final int minYLevel;
    public final int maxYLevel;
    public final String placement;
    public final String rarity;
    public final boolean generateSamples;
    public final List<DepositSampleBlockModel> sampleBlocks;

    public GeodeDepositModel(
        DepositModel base,
        List<DepositBlockModel> outerShellBlocks,
        List<DepositBlockModel> innerShellBlocks,
        List<DepositBlockModel> innerBlocks,
        List<DepositBlockModel> fillBlocks,
        List<String> fillerTypes,
        List<String> clusters,
        int chance,
        double crackChance,
        int minYLevel,
        int maxYLevel,
        String placement,
        String rarity,
        boolean generateSamples,
        List<DepositSampleBlockModel> sampleBlocks
    ) {
        super(base);
        this.outerShellBlocks = outerShellBlocks;
        this.innerShellBlocks = innerShellBlocks;
        this.innerBlocks = innerBlocks;
        this.fillBlocks = fillBlocks;
        this.fillerTypes = fillerTypes;
        this.clusters = clusters;
        this.chance = chance;
        this.crackChance = crackChance;
        this.minYLevel = minYLevel;
        this.maxYLevel = maxYLevel;
        this.placement = placement;
        this.rarity = rarity;
        this.generateSamples = generateSamples;
        this.sampleBlocks = sampleBlocks;
    }

    @Override
    public void register(DataRegistry dataRegistry) {
        super.register(dataRegistry);
    }
}
