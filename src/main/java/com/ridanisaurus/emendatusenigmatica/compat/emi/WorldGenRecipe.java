package com.ridanisaurus.emendatusenigmatica.compat.emi;

import com.ridanisaurus.emendatusenigmatica.plugin.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EETags;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
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
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Supplier;

import static com.ridanisaurus.emendatusenigmatica.compat.emi.EMIPlugin.GUI_ASSETS;

public class WorldGenRecipe implements EmiRecipe {
    private final List<EmiStack> outputs = new ArrayList<>();
    private final IDepositProcessor processor;

    public WorldGenRecipe(IDepositProcessor depositProcessor) {
        processor = Objects.requireNonNull(depositProcessor);
        var blocks = depositProcessor.getBlocks();
        for (CommonBlockDefinitionModel block : blocks) {
            if (Objects.nonNull(block.getBlock())) {
                addBlock(block.getBlock());
                continue;
            }
            if (Objects.nonNull(block.getTag())) {
                addBlockTag(block.getTag());
                continue;
            }
            addMaterialOres(block.getMaterial(), depositProcessor.getFillerTypes());
        }
    }

    private void addBlock(String id) {
        outputs.add(EmiStack.of(BuiltInRegistries.BLOCK.get(ResourceLocation.parse(Objects.requireNonNull(id)))));
    }

    private void addBlockTag(String tag) {
        outputs.addAll(EmiIngredient.of(EETags.getBlockTag(Objects.requireNonNull(tag))).getEmiStacks());
    }

    private void addMaterialOres(String material, List<String> strata) {
        Objects.requireNonNull(material);
        Objects.requireNonNull(strata).forEach(id -> outputs.add(EmiStack.of(Objects.requireNonNull(EERegistrar.oreBlockItemTable.get(id, material)))));
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return EMIPlugin.WORLD_GEN_CATEGORY;
    }

    @Override
    public @Nullable ResourceLocation getId() {
        return Reference.getPath("/emi/world_gen_recipes/" + processor.getName());
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return List.of();
    }

    @Override
    public List<EmiStack> getOutputs() {
        return outputs;
    }

    @Override
    public int getDisplayWidth() {
        return 134;
    }

    @Override
    public int getDisplayHeight() {
        return 66;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        //TODO: Add Additional info for:
        // - Dike Deposits -> Y Range of the generated blocks :D
        // - Geode Deposits -> Crack Chance

        // Background
        widgets.addTexture(GUI_ASSETS, 0, 0, 134, 66, 0, 0);

        // "Output" slot
        widgets.addSlot(EmiIngredient.of(outputs), 5, 5);

        // Deposit Data | Text
        //TODO: Translation
        var yTooltip = createTooltipList(List.of(
            Component.literal(ChatFormatting.GOLD + "Optimal Y:"),
            Component.literal(String.valueOf((processor.getMinY() + processor.getMaxY()) / 2))
        ));

        widgets.addText(getFormattedComponent("Type: ", formatType(processor)), 5,  30, 0, false);
        widgets.addText(getFormattedComponent("Size: ", getSize(processor)),    5,  42, 0, false);
        widgets.addText(getFormattedComponent("Chance: ", processor.getChance() + "%"), 5, 54, 0, false);
        widgets.add(new TextWidget(getFormattedComponent("Min Y: ", processor.getMinY()),  75, 54, 0, false) {
            public List<ClientTooltipComponent> getTooltip(int x, int y) {
                return yTooltip;
            }
        });
        widgets.add(new TextWidget(getFormattedComponent("Max Y: ", processor.getMaxY()),  75, 42, 0, false) {
            public List<ClientTooltipComponent> getTooltip(int x, int y) {
                return yTooltip;
            }
        });

        // Deposit Data | Icons
        // Placement
        widgets.addTexture(GUI_ASSETS, 40, 8, 12, 12, 170, processor.getPlacement().equalsIgnoreCase("Uniform")? 0: 12)
            .tooltip(createTooltipList(List.of(
                Component.literal(ChatFormatting.GOLD + "Placement:"),
                Component.literal(processor.getPlacement()),
                processor.getPlacement().equalsIgnoreCase("Uniform")?
                    Component.literal(ChatFormatting.GRAY + "Even distribution across the spawn range."):
                    Component.literal(ChatFormatting.GRAY + "Higher distribution in the middle of the spawn range.")
            )));
        // Rarity
        widgets.addTexture(GUI_ASSETS, 57, 8, 12, 12, 182, processor.getRarity().equalsIgnoreCase("Common")? 0: 12)
            .tooltip(createTooltipList(List.of(
                Component.literal(ChatFormatting.GOLD + "Rarity:"),
                Component.literal(processor.getRarity())
            )));
        // Dimension
        widgets.addTexture(GUI_ASSETS, 74, 8, 12, 12, 134, switch (processor.getCommonModel().getDimension().toLowerCase(Locale.ROOT)) {
            case "minecraft:overworld" -> 0;
            case "minecraft:the_nether" -> 12;
            case "minecraft:the_end" -> 24;
            default -> 36;
        }).tooltip(createTooltipList(List.of(
            Component.literal(ChatFormatting.GOLD + "Dimension:"),
            Component.literal(processor.getCommonModel().getDimension())
        )));
        // Biome
        widgets.add(new BiomeWidget(91, 8, 12, 12, processor));
        //TODO: Add Sample widget
    }

    private FormattedCharSequence getFormattedComponent(String text1, Object text2) {
        return Component.literal(ChatFormatting.DARK_AQUA + text1 +   ChatFormatting.DARK_GRAY + text2).getVisualOrderText();
    }

    private String getSize(IDepositProcessor processor) {
        return switch (processor.getSize()) {
            case 0 -> "Small";
            case 1 -> "Average";
            case 2 -> "Large";
            default -> "Unknown";
        };
    }

    private List<ClientTooltipComponent> createTooltipList(Supplier<List<Component>> supplier) {
        return createTooltipList(supplier.get());
    }

    private List<ClientTooltipComponent> createTooltipList(List<Component> list) {
        return Objects.requireNonNull(list).stream().map(it -> ClientTooltipComponent.create(it.getVisualOrderText())).toList();
    }

    private String formatType(IDepositProcessor processor) {
        String input = processor.getType();
        //TODO: Replace with translation key generation?
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

    @Override
    public boolean supportsRecipeTree() {
        return false;
    }

    @Override
    public boolean hideCraftable() {
        return true;
    }

    @Override
    public @Nullable RecipeHolder<?> getBackingRecipe() {
        return null;
    }
}
