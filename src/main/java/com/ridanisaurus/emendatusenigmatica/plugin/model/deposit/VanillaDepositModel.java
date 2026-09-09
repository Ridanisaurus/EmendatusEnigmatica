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
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.RequiredValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.ValuesValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.compat.emi.EmiUtils;
import com.ridanisaurus.emendatusenigmatica.plugin.model.DepositModel;
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

public class VanillaDepositModel extends DepositModel {
    public static final Codec<VanillaDepositModel> CODEC = RecordCodecBuilder.create(x -> x.group(
        DepositModel.MAP_CODEC.forGetter(it -> it),
        Codec.STRING.optionalFieldOf("block", null).forGetter(it -> it.block),
        Codec.STRING.optionalFieldOf("material", null).forGetter(it -> it.material),
        Codec.INT.optionalFieldOf("chance", 0).forGetter(it -> it.chance),
        Codec.INT.optionalFieldOf("size", 0).forGetter(it -> it.size),
        Codec.INT.optionalFieldOf("minYLevel", 0).forGetter(it -> it.minYLevel),
        Codec.INT.optionalFieldOf("maxYLevel", 0).forGetter(it -> it.maxYLevel),
        Codec.STRING.optionalFieldOf("placement", "uniform").forGetter(it -> it.placement),
        Codec.STRING.optionalFieldOf("rarity", "common").forGetter(it -> it.rarity)
    ).apply(x, VanillaDepositModel::new)
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
