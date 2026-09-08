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
import com.ridanisaurus.emendatusenigmatica.plugin.compat.emi.BiomeWidget;
import com.ridanisaurus.emendatusenigmatica.plugin.compat.emi.EmiUtils;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.block.BlockModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.block.SampleBlockModel;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.MaxValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit.DepositValidationManager;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit.SampleBlocksValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit.WeightedBlocksValidator;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.WorldGenHelper;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.TextWidget;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class DenseDepositModel extends DepositModel {
    public static final Codec<DenseDepositModel> CODEC = RecordCodecBuilder.create(x -> x.group(
        DepositModel.MAP_CODEC.forGetter(it -> it),
        Codec.list(BlockModel.CODEC).fieldOf("blocks").orElse(List.of()).forGetter(it -> it.blocks.unwrap()),
        Codec.INT.fieldOf("chance").orElse(0).forGetter(it -> it.chance),
        Codec.INT.fieldOf("size").orElse(0).forGetter(it -> it.size),
        Codec.INT.fieldOf("minYLevel").orElse(0).forGetter(it -> it.minYLevel),
        Codec.INT.fieldOf("maxYLevel").orElse(0).forGetter(it -> it.maxYLevel),
        Codec.STRING.fieldOf("placement").orElse("uniform").forGetter(it -> it.placement),
        Codec.STRING.fieldOf("rarity").orElse("rare").forGetter(it -> it.rarity),
        Codec.BOOL.fieldOf("generateSamples").orElse(false).forGetter(it -> it.generateSamples),
        Codec.list(SampleBlockModel.CODEC).fieldOf("sampleBlocks").orElse(List.of()).forGetter(it -> it.sampleBlocks.unwrap())
    ).apply(x, DenseDepositModel::new));

    public static final ValidationManager VALIDATION_MANAGER = DepositValidationManager.create("emendatusenigmatica:dense_deposit")
        .addValidator("blocks",          new WeightedBlocksValidator())
        .addValidator("chance",          new NumberRangeValidator(Types.INTEGER, 1, 100, true))
        .addValidator("size",            new NumberRangeValidator(Types.INTEGER, 1, 48, true))
        .addValidator("minYLevel",       new NumberRangeValidator(Types.INTEGER, -64, 320, true))
        .addValidator("maxYLevel",       new MaxValidator(Types.INTEGER, "minYLevel", -64, 320, true))
        .addValidator("placement",       new ValuesValidator(List.of("uniform", "triangle"), FilterMode.WHITELIST, false))
        .addValidator("rarity",          new ValuesValidator(List.of("common", "rare"), FilterMode.WHITELIST, false))
        .addValidator("generateSamples", new TypeValidator(Types.BOOLEAN, false))
        .addValidator("sampleBlocks",    new SampleBlocksValidator(), ArrayPolicy.REQUIRES_ARRAY.getNonEmpty());

    public final WeightedRandomList<BlockModel> blocks;
    public final int chance;
    public final int size;
    public final int minYLevel;
    public final int maxYLevel;
    public final String placement;
    public final String rarity;
    public final boolean generateSamples;
    public final WeightedRandomList<SampleBlockModel> sampleBlocks;

    public DenseDepositModel(
        DepositModel base,
        List<BlockModel> blocks,
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
    }

    @Override
    public ConfiguredFeature<?, ?> getConfiguredFeature() {
        return new ConfiguredFeature<>(EERegistrar.DENSE_ORE_FEATURE.get(), this);
    }

    @Override
    public List<PlacementModifier> getOrePlacement() {
        return WorldGenHelper.getOrePlacement(this.rarity, this.chance, WorldGenHelper.getPlacementModifier(this.placement, this.minYLevel, this.maxYLevel));
    }

    @Override
    public List<EmiStack> getEmiOutputs() {
        return EmiUtils.getRecipeOutputs(blocks.unwrap(), fillerTypes);
    }

    @Override
    public void createEmiWidget(WidgetHolder widgets) {
        String size;
        if (this.size <= 16)
            size = "Small";
        else if (this.size <= 32)
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
