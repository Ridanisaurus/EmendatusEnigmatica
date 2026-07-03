package com.ridanisaurus.eemekanismaddon.extensions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.eemekanismaddon.validators.ChemicalColorValidator;
import com.ridanisaurus.eemekanismaddon.validators.CoolantColorValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.deprecation.DeprecatedFieldValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.material.ProcessedTypesContainValidator;

import java.util.Optional;

public class MekanismMaterialExtension  {
    public static final Codec<MekanismMaterialExtension> CODEC = RecordCodecBuilder.create(x -> x.group(
        ColorExtension.CODEC.optionalFieldOf("colors").forGetter(i -> Optional.of(i.color)),
        GasExtension.CODEC.optionalFieldOf("gas").forGetter(i -> Optional.of(i.gas))
    ).apply(x, (gasColor, gas) -> new MekanismMaterialExtension(
        gasColor.orElse(new ColorExtension(null, null)),
        gas.orElse(new GasExtension())
    )));

    public static final ValidationManager VALIDATION_MANAGER = ValidationManager.create()
        .addValidator("gas", new ProcessedTypesContainValidator("gas", GasExtension.VALIDATION_MANAGER.getAsValidator(false)));

    private final ColorExtension color;
    private final GasExtension gas;

    public MekanismMaterialExtension(ColorExtension color, GasExtension gas) {
        this.gas = gas;
        this.color = color;
    }

    public ColorExtension getColorData() {
        return this.color;
    }

    public GasExtension getGasData() {
        return this.gas;
    }
}
