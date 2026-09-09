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
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.block.BlockModel;
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
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SphereDepositModel extends DepositModel {
    public static final Codec<SphereDepositModel> CODEC = RecordCodecBuilder.create(x -> x.group(
        DepositModel.MAP_CODEC.forGetter(it -> it),
        Codec.list(BlockModel.CODEC).optionalFieldOf("blocks", List.of()).forGetter(it -> it.blocks.unwrap()),
        Codec.INT.optionalFieldOf("chance", 0).forGetter(it -> it.chance),
        Codec.INT.optionalFieldOf("radius", 0).forGetter(it -> it.radius),
        Codec.INT.optionalFieldOf("minYLevel", 0).forGetter(it -> it.minYLevel),
        Codec.INT.optionalFieldOf("maxYLevel", 0).forGetter(it -> it.maxYLevel),
        Codec.STRING.optionalFieldOf("placement", "uniform").forGetter(it -> it.placement),
        Codec.STRING.optionalFieldOf("rarity", "rare").forGetter(it -> it.rarity),
        SampleConfig.CODEC.optionalFieldOf("sample", new SampleConfig()).forGetter(it -> it.sample)
    ).apply(x, SphereDepositModel::new));

    public static final ValidationManager VALIDATION_MANAGER = DepositValidationManager.create("emendatusenigmatica:sphere_deposit")
        .addValidator("blocks",          new WeightedBlocksValidator())
        .addValidator("chance",          new NumberRangeValidator(Types.INTEGER, 1, 100, true))
        .addValidator("radius",          new NumberRangeValidator(Types.INTEGER, 1, 16, true))
        .addValidator("minYLevel",       new NumberRangeValidator(Types.INTEGER, -64, 320, true))
        .addValidator("maxYLevel",       new MaxValidator(Types.INTEGER, "minYLevel", -64, 320, true))
        .addValidator("placement",       new ValuesValidator(List.of("uniform", "triangle"), FilterMode.WHITELIST, false))
        .addValidator("rarity",          new ValuesValidator(List.of("common", "rare"), FilterMode.WHITELIST, false))
        .addValidator("sample",          new SampleValidator())
        .addValidator("generateSamples", new DeprecatedFieldValidator("root.sample.chance"))
        .addValidator("sampleBlocks",    new DeprecatedFieldValidator("root.sample.blocks"));

    public final WeightedRandomList<BlockModel> blocks;
    public final int chance;
    public final int radius;
    public final int minYLevel;
    public final int maxYLevel;
    public final String placement;
    public final String rarity;
    public final SampleConfig sample;

    public SphereDepositModel(
        DepositModel base,
        List<BlockModel> blocks,
        int chance,
        int radius,
        int minYLevel,
        int maxYLevel,
        String placement,
        String rarity,
        SampleConfig sample
    ) {
        super(base);
        this.blocks = WeightedRandomList.create(blocks);
        this.chance = chance;
        this.radius = radius;
        this.minYLevel = minYLevel;
        this.maxYLevel = maxYLevel;
        this.placement = placement;
        this.rarity = rarity;
        this.sample = sample;
    }

    @Override
    public @NotNull ConfiguredFeature<?, ?> getConfiguredFeature() {
        return new ConfiguredFeature<>(EERegistrar.SPHERE_ORE_FEATURE.get(), this);
    }

    @Override
    public @NotNull List<PlacementModifier> getOrePlacement() {
        return WorldGenHelper.getOrePlacement(rarity, chance, WorldGenHelper.getPlacementModifier(placement, minYLevel, maxYLevel));
    }

    @Override
    public @NotNull List<EmiStack> getEmiOutputs() {
        return EmiUtils.getRecipeOutputs(blocks.unwrap(), fillerTypes);
    }

    @Override
    public void createEmiWidget(WidgetHolder widgets) {
        String size;
        if (this.radius <= 5)
            size = "Small";
        else if (this.radius <= 10)
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
