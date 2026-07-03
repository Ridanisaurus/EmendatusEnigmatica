package com.ridanisaurus.emendatusenigmatica.plugin.model.depositnew;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayPolicy;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.FilterMode;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.NumberRangeValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.RequiredValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.ValuesValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.DataRegistry;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.EERegistryValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.MaxValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit.DepositValidationManager;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit.MaterialValidator;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class VanillaDepositModel extends DepositModel {
    public static final Codec<VanillaDepositModel> CODEC = RecordCodecBuilder.create(x -> x.group(
        DepositModel.MAP_CODEC.forGetter(it -> it),
        Codec.STRING.optionalFieldOf("block").orElse(null).forGetter(it -> Optional.ofNullable(it.block)),
        Codec.STRING.optionalFieldOf("material").orElse(null).forGetter(it -> Optional.ofNullable(it.material)),
        Codec.list(Codec.STRING).fieldOf("fillerTypes").orElse(List.of()).forGetter(it -> it.fillerTypes),
        Codec.INT.fieldOf("chance").orElse(0).forGetter(it -> it.chance),
        Codec.INT.fieldOf("size").orElse(0).forGetter(it -> it.size),
        Codec.INT.fieldOf("minYLevel").orElse(0).forGetter(it -> it.minYLevel),
        Codec.INT.fieldOf("maxYLevel").orElse(0).forGetter(it -> it.maxYLevel),
        Codec.STRING.fieldOf("placement").orElse("uniform").forGetter(it -> it.placement),
        Codec.STRING.fieldOf("rarity").orElse("common").forGetter(it -> it.rarity)
    ).apply(x,(base, block, material, fillerTypes,
               chance, size, minYLevel, maxYLevel, placement, rarity) ->
        new VanillaDepositModel(base, block.orElse(null), material.orElse(null), fillerTypes, chance, size, minYLevel, maxYLevel, placement, rarity))
    );

    public static final ValidationManager VALIDATION_MANAGER = DepositValidationManager.create("emendatusenigmatica:vanilla_deposit")
        .addValidator("material",        new MaterialValidator(false, true))
        .addValidator("block",           new RequiredValidator(false))
        .addValidator("fillerTypes",     new EERegistryValidator(StrataModel.REGISTERED_IDS, EERegistryValidator.REFERENCE, "Strata", true), ArrayPolicy.REQUIRES_ARRAY.getNonEmpty())
        .addValidator("chance",          new NumberRangeValidator(Types.INTEGER, 1, 100, true))
        .addValidator("size",            new NumberRangeValidator(Types.INTEGER, 1, 16, true))
        .addValidator("minYLevel",       new NumberRangeValidator(Types.INTEGER, -64, 320, true))
        .addValidator("maxYLevel",       new MaxValidator(Types.INTEGER, "minYLevel", -64, 320, true))
        .addValidator("placement",       new ValuesValidator(List.of("uniform", "triangle"), FilterMode.WHITELIST, false))
        .addValidator("rarity",          new ValuesValidator(List.of("common", "rare"), FilterMode.WHITELIST, false));

    public final String block;
    public final String material;
    public final List<String> fillerTypes;
    public final int chance;
    public final int size;
    public final int minYLevel;
    public final int maxYLevel;
    public final String placement;
    public final String rarity;

    public VanillaDepositModel(
        DepositModel base,
        @Nullable String block,
        @Nullable String material,
        List<String> fillerTypes,
        int chance,
        int size,
        int minYLevel,
        int maxYLevel,
        String placement,
        String rarity
    ) {
        super(base);
        this.block = block;
        this.material = material;
        this.fillerTypes = fillerTypes;
        this.chance = chance;
        this.size = size;
        this.minYLevel = minYLevel;
        this.maxYLevel = maxYLevel;
        this.placement = placement;
        this.rarity = rarity;
    }

    @Override
    public void register(DataRegistry dataRegistry) {
        super.register(dataRegistry);
    }
}
