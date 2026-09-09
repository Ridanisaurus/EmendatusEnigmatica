/*
 * MIT License
 *
 * Copyright (c) 2026. Ridanisaurus
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.ridanisaurus.emendatusenigmatica.plugin.model.deposit;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.FilterMode;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.NumberRangeValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.ValuesValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.deprecation.DeprecatedFieldValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.compat.emi.EmiUtils;
import com.ridanisaurus.emendatusenigmatica.plugin.model.DepositModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.block.DikeBlockModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.sample.SampleConfig;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.MaxValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit.DepositValidationManager;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit.SampleValidator;
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
        Codec.list(DikeBlockModel.CODEC).optionalFieldOf("blocks", List.of()).forGetter(it -> it.blocks.unwrap()),
        Codec.INT.optionalFieldOf("chance", 0).forGetter(it -> it.chance),
        Codec.INT.optionalFieldOf("size", 0).forGetter(it -> it.size),
        Codec.INT.optionalFieldOf("minYLevel", 0).forGetter(it -> it.minYLevel),
        Codec.INT.optionalFieldOf("maxYLevel", 0).forGetter(it -> it.maxYLevel),
        Codec.STRING.optionalFieldOf("placement", "uniform").forGetter(it -> it.placement),
        Codec.STRING.optionalFieldOf("rarity", "rare").forGetter(it -> it.rarity),
        SampleConfig.CODEC.optionalFieldOf("sample", new SampleConfig()).forGetter(it -> it.sample)
    ).apply(x, DikeDepositModel::new));

    public static final ValidationManager VALIDATION_MANAGER = DepositValidationManager.create("emendatusenigmatica:dike_deposit")
        .addValidator("blocks",          new WeightedBlocksValidator(DikeBlockModel.VALIDATION_MANAGER.getAsValidator(true)))
        .addValidator("chance",          new NumberRangeValidator(Types.INTEGER, 1, 100, true))
        .addValidator("size",            new NumberRangeValidator(Types.INTEGER, 1, 64, true))
        .addValidator("minYLevel",       new NumberRangeValidator(Types.INTEGER, -64, 320, true))
        .addValidator("maxYLevel",       new MaxValidator(Types.INTEGER, "minYLevel", -64, 320, true))
        .addValidator("placement",       new ValuesValidator(List.of("uniform", "triangle"), FilterMode.WHITELIST, false))
        .addValidator("rarity",          new ValuesValidator(List.of("common", "rare"), FilterMode.WHITELIST, false))
        .addValidator("sample",          new SampleValidator())
        .addValidator("generateSamples", new DeprecatedFieldValidator("root.sample.chance"))
        .addValidator("sampleBlocks",    new DeprecatedFieldValidator("root.sample.blocks"));

    public final WeightedRandomList<DikeBlockModel> blocks;
    public final Map<Integer, WeightedRandomList<DikeBlockModel>> blocksByY;
    public final int minYLevelRange;
    public final int chance;
    public final int size;
    public final int minYLevel;
    public final int maxYLevel;
    public final String placement;
    public final String rarity;
    public final SampleConfig sample;

    public DikeDepositModel(
        DepositModel base,
        List<DikeBlockModel> blocks,
        int chance,
        int size,
        int minYLevel,
        int maxYLevel,
        String placement,
        String rarity,
        SampleConfig sample
    ) {
        super(base);
        this.blocks = WeightedRandomList.create(blocks);
        this.chance = chance;
        this.size = size;
        this.minYLevel = minYLevel;
        this.maxYLevel = maxYLevel;
        this.placement = placement;
        this.rarity = rarity;
        this.sample = sample;
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
