package com.ridanisaurus.emendatusenigmatica.plugin.model.depositnew;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayPolicy;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.FilterMode;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.NumberRangeValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.TypeValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.ValuesValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.DataRegistry;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.common.DepositBlockModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.sample.DepositSampleBlockModel;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.EERegistryValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.MaxValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit.DepositValidationManager;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit.SampleBlocksValidator;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class SphereDepositModel extends DepositModel {
    public static final Codec<SphereDepositModel> CODEC = RecordCodecBuilder.create(x -> x.group(
        DepositModel.MAP_CODEC.forGetter(it -> it),
        Codec.list(DepositBlockModel.CODEC).fieldOf("blocks").orElse(List.of()).forGetter(it -> it.blocks),
        Codec.list(Codec.STRING).fieldOf("fillerTypes").orElse(List.of()).forGetter(it -> it.fillerTypes),
        Codec.INT.fieldOf("chance").orElse(0).forGetter(it -> it.chance),
        Codec.INT.fieldOf("radius").orElse(0).forGetter(it -> it.radius),
        Codec.INT.fieldOf("minYLevel").orElse(0).forGetter(it -> it.minYLevel),
        Codec.INT.fieldOf("maxYLevel").orElse(0).forGetter(it -> it.maxYLevel),
        Codec.STRING.fieldOf("placement").orElse("uniform").forGetter(it -> it.placement),
        Codec.STRING.fieldOf("rarity").orElse("rare").forGetter(it -> it.rarity),
        Codec.BOOL.fieldOf("generateSamples").orElse(false).forGetter(it -> it.generateSamples),
        Codec.list(DepositSampleBlockModel.CODEC).fieldOf("sampleBlocks").orElse(List.of()).forGetter(it -> it.sampleBlocks)
    ).apply(x, SphereDepositModel::new));

    public static final ValidationManager VALIDATION_MANAGER = DepositValidationManager.create("emendatusenigmatica:sphere_deposit")
        .addValidator("blocks",          DepositBlockModel.VALIDATION_MANAGER.getAsValidator(true), ArrayPolicy.REQUIRES_ARRAY.getNonEmpty())
        .addValidator("fillerTypes",     new EERegistryValidator(StrataModel.REGISTERED_IDS, EERegistryValidator.REFERENCE, "Strata", true), ArrayPolicy.REQUIRES_ARRAY.getNonEmpty())
        .addValidator("chance",          new NumberRangeValidator(Types.INTEGER, 1, 100, true))
        .addValidator("radius",          new NumberRangeValidator(Types.INTEGER, 1, 16, true))
        .addValidator("minYLevel",       new NumberRangeValidator(Types.INTEGER, -64, 320, true))
        .addValidator("maxYLevel",       new MaxValidator(Types.INTEGER, "minYLevel", -64, 320, true))
        .addValidator("placement",       new ValuesValidator(List.of("uniform", "triangle"), FilterMode.WHITELIST, false))
        .addValidator("rarity",          new ValuesValidator(List.of("common", "rare"), FilterMode.WHITELIST, false))
        .addValidator("generateSamples", new TypeValidator(Types.BOOLEAN, false))
        .addValidator("sampleBlocks",    new SampleBlocksValidator(), ArrayPolicy.REQUIRES_ARRAY.getNonEmpty());

    public final List<DepositBlockModel> blocks;
    public final List<String> fillerTypes;
    public final int chance;
    public final int radius;
    public final int minYLevel;
    public final int maxYLevel;
    public final String placement;
    public final String rarity;
    public final boolean generateSamples;
    public final List<DepositSampleBlockModel> sampleBlocks;

    public SphereDepositModel(
        DepositModel base,
        List<DepositBlockModel> blocks,
        List<String> fillerTypes,
        int chance,
        int radius,
        int minYLevel,
        int maxYLevel,
        String placement,
        String rarity,
        boolean generateSamples,
        List<DepositSampleBlockModel> sampleBlocks
    ) {
        super(base);
        this.blocks = blocks;
        this.chance = chance;
        this.radius = radius;
        this.minYLevel = minYLevel;
        this.maxYLevel = maxYLevel;
        this.fillerTypes = fillerTypes;
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
