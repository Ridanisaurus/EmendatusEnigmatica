package com.ridanisaurus.emendatusenigmatica.plugin.model.deposit;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayPolicy;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.FilterMode;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.*;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.registry.BlockRegistryValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.block.BlockModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.block.SampleBlockModel;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.MaxValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit.DepositValidationManager;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit.SampleBlocksValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit.WeightedBlocksValidator;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.WorldGenHelper;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class GeodeDepositModel extends DepositModel {
    public static final Codec<GeodeDepositModel> CODEC = RecordCodecBuilder.create(x -> x.group(
        DepositModel.MAP_CODEC.forGetter(it -> it),
        Codec.list(BlockModel.CODEC).fieldOf("outerShellBlocks").orElse(List.of()).forGetter(i -> i.outerShellBlocks.unwrap()),
        Codec.list(BlockModel.CODEC).fieldOf("innerShellBlocks").orElse(List.of()).forGetter(i -> i.innerShellBlocks.unwrap()),
        Codec.list(BlockModel.CODEC).fieldOf("innerBlocks").orElse(List.of()).forGetter(i -> i.innerBlocks.unwrap()),
        Codec.list(BlockModel.CODEC).fieldOf("fillBlocks").orElse(List.of()).forGetter(i -> i.fillBlocks.unwrap()),
        Codec.list(Codec.STRING).fieldOf("clusters").orElse(List.of()).forGetter(i -> i.clusters),
        Codec.INT.fieldOf("chance").orElse(0).forGetter(it -> it.chance),
        Codec.DOUBLE.fieldOf("crackChance").orElse(0D).forGetter(it -> it.crackChance),
        Codec.INT.fieldOf("minYLevel").orElse(0).forGetter(it -> it.minYLevel),
        Codec.INT.fieldOf("maxYLevel").orElse(0).forGetter(it -> it.maxYLevel),
        Codec.STRING.fieldOf("placement").orElse("uniform").forGetter(it -> it.placement),
        Codec.STRING.fieldOf("rarity").orElse("rare").forGetter(it -> it.rarity),
        Codec.BOOL.fieldOf("generateSamples").orElse(false).forGetter(it -> it.generateSamples),
        Codec.list(SampleBlockModel.CODEC).fieldOf("sampleBlocks").orElse(List.of()).forGetter(it -> it.sampleBlocks)
    ).apply(x, GeodeDepositModel::new));

    public static final ValidationManager VALIDATION_MANAGER = DepositValidationManager.create("emendatusenigmatica:geode_deposit")
        .addValidator("outerShellBlocks", new WeightedBlocksValidator())
        .addValidator("innerShellBlocks", new WeightedBlocksValidator())
        .addValidator("innerBlocks",      new WeightedBlocksValidator())
        .addValidator("fillBlocks",       new WeightedBlocksValidator())
        .addValidator("clusters",         new ResourceLocationValidator(false, new BlockRegistryValidator()), ArrayPolicy.REQUIRES_ARRAY.get())
        .addValidator("chance",           new NumberRangeValidator(Types.INTEGER, 1, 100, true))
        .addValidator("crackChance",      new NumberRangeValidator(Types.FLOAT, 0, 1, false))
        .addValidator("minYLevel",        new NumberRangeValidator(Types.INTEGER, -64, 320, true))
        .addValidator("maxYLevel",        new MaxValidator(Types.INTEGER, "minYLevel", -64, 320, true))
        .addValidator("placement",        new ValuesValidator(List.of("uniform", "triangle"), FilterMode.WHITELIST, false))
        .addValidator("rarity",           new ValuesValidator(List.of("common", "rare"), FilterMode.WHITELIST, false))
        .addValidator("generateSamples",  new TypeValidator(Types.BOOLEAN, false))
        .addValidator("sampleBlocks",     new SampleBlocksValidator(), ArrayPolicy.REQUIRES_ARRAY.getNonEmpty());

    public final WeightedRandomList<BlockModel> outerShellBlocks;
    public final WeightedRandomList<BlockModel> innerShellBlocks;
    public final WeightedRandomList<BlockModel> innerBlocks;
    public final WeightedRandomList<BlockModel> fillBlocks;
    public final List<String> clusters;
    public final int chance;
    public final double crackChance;
    public final int minYLevel;
    public final int maxYLevel;
    public final String placement;
    public final String rarity;
    public final boolean generateSamples;
    public final List<SampleBlockModel> sampleBlocks;

    public GeodeDepositModel(
        DepositModel base,
        List<BlockModel> outerShellBlocks,
        List<BlockModel> innerShellBlocks,
        List<BlockModel> innerBlocks,
        List<BlockModel> fillBlocks,
        List<String> clusters,
        int chance,
        double crackChance,
        int minYLevel,
        int maxYLevel,
        String placement,
        String rarity,
        boolean generateSamples,
        List<SampleBlockModel> sampleBlocks
    ) {
        super(base);
        this.outerShellBlocks = WeightedRandomList.create(outerShellBlocks);
        this.innerShellBlocks = WeightedRandomList.create(innerShellBlocks);
        this.innerBlocks = WeightedRandomList.create(innerBlocks);
        this.fillBlocks = WeightedRandomList.create(fillBlocks);
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
    public ConfiguredFeature<?, ?> getConfiguredFeature() {
        return new ConfiguredFeature<>(EERegistrar.GEODE_ORE_FEATURE.get(), this);
    }

    @Override
    public List<PlacementModifier> getOrePlacement() {
        return WorldGenHelper.getOrePlacement(rarity, chance, WorldGenHelper.getPlacementModifier(placement, minYLevel, maxYLevel));
    }
}
