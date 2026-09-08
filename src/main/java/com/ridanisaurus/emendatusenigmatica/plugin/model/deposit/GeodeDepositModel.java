package com.ridanisaurus.emendatusenigmatica.plugin.model.deposit;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayPolicy;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.FilterMode;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.*;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.registry.BlockRegistryValidator;
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
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.ArrayList;
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

    @Override
    public List<EmiStack> getEmiOutputs() {
        List<EmiStack> outputs = new ArrayList<>();
        outputs.addAll(EmiUtils.getRecipeOutputs(outerShellBlocks.unwrap(), fillerTypes));
        outputs.addAll(EmiUtils.getRecipeOutputs(innerShellBlocks.unwrap(), fillerTypes));
        outputs.addAll(EmiUtils.getRecipeOutputs(innerBlocks.unwrap(), fillerTypes));
        outputs.addAll(EmiUtils.getRecipeOutputs(fillBlocks.unwrap(), fillerTypes));
        for (String cluster : clusters)
            outputs.add(EmiStack.of(BuiltInRegistries.BLOCK.get(ResourceLocation.parse(cluster))));
        return outputs;
    }

    @Override
    public void createEmiWidget(WidgetHolder widgets) {
        // Background
        widgets.addTexture(EmiUtils.GUI_ASSETS, 0, 0, 134, 66, 0, 0);

        // "Output" slot
        widgets.addSlot(EmiIngredient.of(getEmiOutputs()), 5, 5);

        // Deposit Data | Text
        //TODO: Translation
        //TODO: Is this really true for all possible rarities/placements :D?
        var yTooltip = EmiUtils.createTooltipList(List.of(
            Component.literal(ChatFormatting.GOLD + "Optimal Y:"),
            Component.literal(String.valueOf((minYLevel + maxYLevel) / 2))
        ));

        widgets.addText(EmiUtils.getFormattedComponent("Type: ", EmiUtils.formatType(type)), 5,  30, 0, false);
        widgets.addText(EmiUtils.getFormattedComponent("Chance: ", chance + "%"), 5, 42, 0, false);
        widgets.addText(EmiUtils.getFormattedComponent("Cracked: ", crackChance + "%"), 5, 54, 0, false);
        widgets.add(new TextWidget(EmiUtils.getFormattedComponent("Min Y: ", minYLevel),  75, 54, 0, false) {
            public List<ClientTooltipComponent> getTooltip(int x, int y) {
                return yTooltip;
            }
        });
        widgets.add(new TextWidget(EmiUtils.getFormattedComponent("Max Y: ", maxYLevel),  75, 42, 0, false) {
            public List<ClientTooltipComponent> getTooltip(int x, int y) {
                return yTooltip;
            }
        });

        // Deposit Data | Icons
        // Placement
        widgets.addTexture(EmiUtils.GUI_ASSETS, 40, 8, 12, 12, 170, placement.equalsIgnoreCase("Uniform")? 0: 12)
            .tooltip(EmiUtils.createTooltipList(List.of(
                Component.literal(ChatFormatting.GOLD + "Placement:"),
                Component.literal(placement),
                placement.equalsIgnoreCase("Uniform")?
                    Component.literal(ChatFormatting.GRAY + "Even distribution across the spawn range."):
                    Component.literal(ChatFormatting.GRAY + "Higher distribution in the middle of the spawn range.")
            )));

        // Rarity
        widgets.addTexture(EmiUtils.GUI_ASSETS, 57, 8, 12, 12, 182, rarity.equalsIgnoreCase("Common")? 0: 12)
            .tooltip(EmiUtils.createTooltipList(List.of(
                Component.literal(ChatFormatting.GOLD + "Rarity:"),
                Component.literal(rarity)
            )));

        // Dimension
        // TODO: Figure out how to possibly extend this?
        widgets.addTexture(EmiUtils.GUI_ASSETS, 74, 8, 12, 12, 134, switch (dimension.toString()) {
            case "minecraft:overworld" -> 0;
            case "minecraft:the_nether" -> 12;
            case "minecraft:the_end" -> 24;
            default -> 36;
        }).tooltip(EmiUtils.createTooltipList(List.of(
            Component.literal(ChatFormatting.GOLD + "Dimension:"),
            Component.literal(dimension.toString())
        )));
//         Biome
        widgets.add(new BiomeWidget(91, 8, 12, 12, biomes));
        //TODO: Add Sample widget
    }
}
