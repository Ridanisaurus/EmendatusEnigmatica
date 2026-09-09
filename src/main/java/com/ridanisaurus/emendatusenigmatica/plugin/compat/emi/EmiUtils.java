package com.ridanisaurus.emendatusenigmatica.plugin.compat.emi;

import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.block.BlockModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EETags;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.TextWidget;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class EmiUtils {
    public static final ResourceLocation GUI_ASSETS = Reference.getPath("textures/gui/world_gen.png");

    public static List<EmiStack> getRecipeOutputs(@NotNull List<? extends BlockModel> blocks, @NotNull List<String> fillerTypes) {
        List<EmiStack> ret = new ArrayList<>();
        for (BlockModel block : blocks) {
            if (Objects.nonNull(block.getBlock())) {
                ret.add(EmiStack.of(BuiltInRegistries.BLOCK.get(ResourceLocation.parse(block.getBlock()))));
                continue;
            }

            if (Objects.nonNull(block.getTag())) {
                ret.addAll(EmiIngredient.of(EETags.getBlockTag(block.getTag())).getEmiStacks());
                continue;
            }

            if (Objects.isNull(block.getMaterial())) throw new IllegalArgumentException("Illegal BlockModel provided!");
            if (fillerTypes.isEmpty()) throw new IllegalArgumentException("Empty FillerTypes provided!");
            for (String strata : fillerTypes)
                ret.add(EmiStack.of(Objects.requireNonNull(EERegistrar.oreBlockItemTable.get(strata, block.getMaterial()))));
        }
        return ret;
    }

    public static FormattedCharSequence getFormattedComponent(String text1, Object text2) {
        return Component.literal(ChatFormatting.DARK_AQUA + text1 +   ChatFormatting.DARK_GRAY + text2).getVisualOrderText();
    }

    public static List<ClientTooltipComponent> createTooltipList(Supplier<List<Component>> supplier) {
        return createTooltipList(supplier.get());
    }

    public static List<ClientTooltipComponent> createTooltipList(List<Component> list) {
        return Objects.requireNonNull(list).stream().map(it -> ClientTooltipComponent.create(it.getVisualOrderText())).toList();
    }

    public static String formatType(@NotNull String input) {
//        TODO: Replace with translation key generation?
        int index = input.indexOf(":");
        if (index != -1) {
            input = input.substring(index + 1);
        }
        input = input.replaceAll("_", " ");
        String[] words = input.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(Character.toUpperCase(word.charAt(0)));
            sb.append(word.substring(1));
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    public static void defaultWorldGenWidget(
        @NotNull WidgetHolder widgets,
        @NotNull List<EmiStack> outputs,
        @NotNull List<String> biomes,
        @NotNull ResourceLocation dimension,
        @NotNull String type,
        @NotNull String size,
        @NotNull String placement,
        @NotNull String rarity,
        int minYLevel,
        int maxYLevel,
        int chance
    ) {
        // Background
        widgets.addTexture(EmiUtils.GUI_ASSETS, 0, 0, 134, 66, 0, 0);

        // "Output" slot
        widgets.addSlot(EmiIngredient.of(outputs), 5, 5);

        // Deposit Data | Text
        //TODO: Translation
        //TODO: Is this really true for all possible rarities/placements :D?
        var yTooltip = EmiUtils.createTooltipList(List.of(
            Component.literal(ChatFormatting.GOLD + "Optimal Y:"),
            Component.literal(String.valueOf((minYLevel + maxYLevel) / 2))
        ));

        widgets.addText(EmiUtils.getFormattedComponent("Type: ", EmiUtils.formatType(type)), 5,  30, 0, false);
        widgets.addText(EmiUtils.getFormattedComponent("Size: ", size),    5,  42, 0, false);
        widgets.addText(EmiUtils.getFormattedComponent("Chance: ", chance + "%"), 5, 54, 0, false);
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
