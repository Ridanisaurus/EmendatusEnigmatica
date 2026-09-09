package com.ridanisaurus.emendatusenigmatica.plugin.compat.emi;

import com.ridanisaurus.emendatusenigmatica.plugin.model.DepositModel;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.jarjar.nio.util.Lazy;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class WorldGenRecipe implements EmiRecipe {
    private final DepositModel model;
    private final Lazy<List<EmiStack>> outputs;

    public WorldGenRecipe(DepositModel model) {
        this.model = Objects.requireNonNull(model);
        outputs = Lazy.of(model::getEmiOutputs);
    }


    @Override
    public EmiRecipeCategory getCategory() {
        return EEEMIPlugin.WORLD_GEN_CATEGORY.get();
    }

    @Override
    public @Nullable ResourceLocation getId() {
        return Reference.getPath("/emi/world_gen_recipes/" + model.id);
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return List.of();
    }

    @Override
    public List<EmiStack> getOutputs() {
        return outputs.get();
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
        model.createEmiWidget(widgets);
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
