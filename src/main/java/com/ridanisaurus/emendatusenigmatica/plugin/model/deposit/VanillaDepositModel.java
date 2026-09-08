package com.ridanisaurus.emendatusenigmatica.plugin.model.deposit;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.FilterMode;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.NumberRangeValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.RequiredValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.ValuesValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.compat.emi.EmiUtils;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.block.BlockModel;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.MaxValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit.DepositValidationManager;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit.MaterialValidator;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.WorldGenHelper;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class VanillaDepositModel extends DepositModel {
    public static final Codec<VanillaDepositModel> CODEC = RecordCodecBuilder.create(x -> x.group(
        DepositModel.MAP_CODEC.forGetter(it -> it),
        Codec.STRING.optionalFieldOf("block").orElse(null).forGetter(it -> Optional.ofNullable(it.block)),
        Codec.STRING.optionalFieldOf("material").orElse(null).forGetter(it -> Optional.ofNullable(it.material)),
        Codec.INT.fieldOf("chance").orElse(0).forGetter(it -> it.chance),
        Codec.INT.fieldOf("size").orElse(0).forGetter(it -> it.size),
        Codec.INT.fieldOf("minYLevel").orElse(0).forGetter(it -> it.minYLevel),
        Codec.INT.fieldOf("maxYLevel").orElse(0).forGetter(it -> it.maxYLevel),
        Codec.STRING.fieldOf("placement").orElse("uniform").forGetter(it -> it.placement),
        Codec.STRING.fieldOf("rarity").orElse("common").forGetter(it -> it.rarity)
    ).apply(x,(base, block, material,
               chance, size, minYLevel, maxYLevel, placement, rarity) ->
        new VanillaDepositModel(base, block.orElse(null), material.orElse(null), chance, size, minYLevel, maxYLevel, placement, rarity))
    );

    public static final ValidationManager VALIDATION_MANAGER = DepositValidationManager.create("emendatusenigmatica:vanilla_deposit")
        .addValidator("material",        new MaterialValidator(false, true))
        .addValidator("block",           new RequiredValidator(false))
        .addValidator("chance",          new NumberRangeValidator(Types.INTEGER, 1, 100, true))
        .addValidator("size",            new NumberRangeValidator(Types.INTEGER, 1, 16, true))
        .addValidator("minYLevel",       new NumberRangeValidator(Types.INTEGER, -64, 320, true))
        .addValidator("maxYLevel",       new MaxValidator(Types.INTEGER, "minYLevel", -64, 320, true))
        .addValidator("placement",       new ValuesValidator(List.of("uniform", "triangle"), FilterMode.WHITELIST, false))
        .addValidator("rarity",          new ValuesValidator(List.of("common", "rare"), FilterMode.WHITELIST, false));

    public final String block;
    public final String material;
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
        this.chance = chance;
        this.size = size;
        this.minYLevel = minYLevel;
        this.maxYLevel = maxYLevel;
        this.placement = placement;
        this.rarity = rarity;
    }

    @Override
    public ConfiguredFeature<?, ?> getConfiguredFeature() {
        return new ConfiguredFeature<>(EERegistrar.VANILLA_ORE_FEATURE.get(), this);
    }

    @Override
    public List<PlacementModifier> getOrePlacement() {
        return WorldGenHelper.getOrePlacement(rarity, chance, WorldGenHelper.getPlacementModifier(placement, minYLevel, maxYLevel));
    }

    @Override
    public List<EmiStack> getEmiOutputs() {
        if (Objects.nonNull(block))
            return List.of(EmiStack.of(BuiltInRegistries.BLOCK.get(ResourceLocation.parse(block))));
        if (Objects.isNull(material) || fillerTypes.isEmpty()) throw new IllegalStateException("Invalid VanillaDepositModel was registered!");
        return fillerTypes.stream().map(strata -> EmiStack.of(Objects.requireNonNull(EERegistrar.oreBlockItemTable.get(strata, material)))).toList();
    }

    @Override
    public void createEmiWidget(WidgetHolder widgets) {
        String size;
        if (this.size <= 5)
            size = "Small";
        else if (this.size <= 10)
            size = "Medium";
        else
            size = "Big";

        EmiUtils.defaultWorldGenWidget(
            widgets,
            getEmiOutputs(),
            biomes,
            dimension,
            type,
            size,
            placement,
            rarity,
            minYLevel,
            maxYLevel,
            chance
        );
    }
}
