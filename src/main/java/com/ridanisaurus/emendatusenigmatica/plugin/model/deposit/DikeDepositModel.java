package com.ridanisaurus.emendatusenigmatica.plugin.model.deposit;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayPolicy;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.FilterMode;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.NumberRangeValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.TypeValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.ValuesValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.compat.emi.EmiUtils;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.block.BlockModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.block.DikeBlockModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.block.SampleBlockModel;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.MaxValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit.DepositValidationManager;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit.SampleBlocksValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit.WeightedBlocksValidator;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.WorldGenHelper;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.*;

public class DikeDepositModel extends DepositModel {
    public static final Codec<DikeDepositModel> CODEC = RecordCodecBuilder.create(x -> x.group(
        DepositModel.MAP_CODEC.forGetter(it -> it),
        Codec.list(DikeBlockModel.CODEC).fieldOf("blocks").orElse(List.of()).forGetter(it -> it.blocks.unwrap()),
        Codec.INT.fieldOf("chance").orElse(0).forGetter(it -> it.chance),
        Codec.INT.fieldOf("size").orElse(0).forGetter(it -> it.size),
        Codec.INT.fieldOf("minYLevel").orElse(0).forGetter(it -> it.minYLevel),
        Codec.INT.fieldOf("maxYLevel").orElse(0).forGetter(it -> it.maxYLevel),
        Codec.STRING.fieldOf("placement").orElse("uniform").forGetter(it -> it.placement),
        Codec.STRING.fieldOf("rarity").orElse("rare").forGetter(it -> it.rarity),
        Codec.BOOL.fieldOf("generateSamples").orElse(false).forGetter(it -> it.generateSamples),
        Codec.list(SampleBlockModel.CODEC).fieldOf("sampleBlocks").orElse(List.of()).forGetter(it -> it.sampleBlocks.unwrap())
    ).apply(x, DikeDepositModel::new));

    public static final ValidationManager VALIDATION_MANAGER = DepositValidationManager.create("emendatusenigmatica:dike_deposit")
        .addValidator("blocks",          new WeightedBlocksValidator(DikeBlockModel.VALIDATION_MANAGER.getAsValidator(true)))
        .addValidator("chance",          new NumberRangeValidator(Types.INTEGER, 1, 100, true))
        .addValidator("size",            new NumberRangeValidator(Types.INTEGER, 1, 64, true))
        .addValidator("minYLevel",       new NumberRangeValidator(Types.INTEGER, -64, 320, true))
        .addValidator("maxYLevel",       new MaxValidator(Types.INTEGER, "minYLevel", -64, 320, true))
        .addValidator("placement",       new ValuesValidator(List.of("uniform", "triangle"), FilterMode.WHITELIST, false))
        .addValidator("rarity",          new ValuesValidator(List.of("common", "rare"), FilterMode.WHITELIST, false))
        .addValidator("generateSamples", new TypeValidator(Types.BOOLEAN, false))
        .addValidator("sampleBlocks",    new SampleBlocksValidator(), ArrayPolicy.REQUIRES_ARRAY.getNonEmpty());

    public final WeightedRandomList<DikeBlockModel> blocks;
    public final Map<Integer, WeightedRandomList<DikeBlockModel>> blocksByY;
    public final int minYLevelRange;
    public final int chance;
    public final int size;
    public final int minYLevel;
    public final int maxYLevel;
    public final String placement;
    public final String rarity;
    public final boolean generateSamples;
    public final WeightedRandomList<SampleBlockModel> sampleBlocks;

    public DikeDepositModel(
        DepositModel base,
        List<DikeBlockModel> blocks,
        int chance,
        int size,
        int minYLevel,
        int maxYLevel,
        String placement,
        String rarity,
        boolean generateSamples,
        List<SampleBlockModel> sampleBlocks
    ) {
        super(base);
        this.blocks = WeightedRandomList.create(blocks);
        this.chance = chance;
        this.size = size;
        this.minYLevel = minYLevel;
        this.maxYLevel = maxYLevel;
        this.placement = placement;
        this.rarity = rarity;
        this.generateSamples = generateSamples;
        this.sampleBlocks = WeightedRandomList.create(sampleBlocks);
        this.blocksByY = new HashMap<>();
        Set<Integer> yRanges = new HashSet<>();

        int minYLevelRange = Integer.MIN_VALUE;
        for (DikeBlockModel it : blocks) {
            if (it.getMinY() < minYLevel || it.getMaxY() < minYLevel)
                minYLevelRange = Math.max(minYLevelRange, it.getMaxY() < minYLevel? it.getMaxY()+1: it.getMinY());
            yRanges.add(it.getMinY());
            yRanges.add(it.getMaxY()+1);
        }
        if (minYLevelRange == Integer.MIN_VALUE) minYLevelRange = minYLevel;

        this.minYLevelRange = minYLevelRange;
        yRanges.forEach(yRange -> {
            if (yRange < this.minYLevelRange || yRange > maxYLevel) return;
            blocksByY.put(yRange, WeightedRandomList.create(blocks.stream().filter(it -> yRange >= it.getMinY() && it.getMaxY() >= yRange).toList()));
        });
    }

    @Override
    public ConfiguredFeature<?, ?> getConfiguredFeature() {
        return new ConfiguredFeature<>(EERegistrar.DIKE_ORE_FEATURE.get(), this);
    }

    @Override
    public List<PlacementModifier> getOrePlacement() {
        return WorldGenHelper.getOrePlacement(rarity, chance, WorldGenHelper.getPlacementModifier(placement, minYLevel, maxYLevel));
    }

    @Override
    public List<EmiStack> getEmiOutputs() {
        return EmiUtils.getRecipeOutputs(blocks.unwrap(), fillerTypes);
    }

    @Override
    public void createEmiWidget(WidgetHolder widgets) {
        String size;
        if (this.size <= 24)
            size = "Small";
        else if (this.size <= 48)
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
