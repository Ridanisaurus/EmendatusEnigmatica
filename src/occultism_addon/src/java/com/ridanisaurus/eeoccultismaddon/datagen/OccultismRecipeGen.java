package com.ridanisaurus.eeoccultismaddon.datagen;

import com.klikli_dev.occultism.datagen.recipe.builders.CrushingRecipeBuilder;
import com.ridanisaurus.emendatusenigmatica.plugin.DataRegistry;
import com.ridanisaurus.emendatusenigmatica.plugin.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EETags;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class OccultismRecipeGen extends RecipeProvider {
    private final DataRegistry registry;
    public OccultismRecipeGen(@NotNull DataGenerator gen, DataRegistry registry, CompletableFuture<HolderLookup.Provider> providers) {
        super(gen.getPackOutput(), providers);
        this.registry = registry;
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output) {
        for (MaterialModel material : registry.getRegisteredMaterials()) {
            List<String> processedType = material.getProcessedTypes();
            //TODO: Think about this-
//            if (material.getCompat().getOccultismCompat()) {
                if (!processedType.contains("dust")) continue;
                if (processedType.contains("ore")) {
                    // Dust from Ore - Crusher Spirit
                    CrushingRecipeBuilder
                        .crushingRecipe(
                            EETags.MATERIAL_ORE.apply(material.getId()),
                            EERegistrar.dustMap.get(material),
                            200
                        ).setResultAmount(2)
                        .group("emendatusenigmatica:compat_recipe")
                        .unlockedBy("ore", has(EETags.MATERIAL_ORE.apply(material.getId())))
                        .save(output, Reference.getPath("dust/from_ore_crusher_spirit/" + material.getId()));
                }

                if (processedType.contains("ingot")) {
                    // Dust from Ingot - Crusher Spirit
                    CrushingRecipeBuilder
                        .crushingRecipe(
                            EETags.MATERIAL_INGOT.apply(material.getId()),
                            EERegistrar.dustMap.get(material),
                            200
                        ).setIgnoreCrushingMultiplier(true)
                        .group("emendatusenigmatica:compat_recipe")
                        .unlockedBy("ingot", has(EETags.MATERIAL_INGOT.apply(material.getId())))
                        .save(output, Reference.getPath("dust/from_ingot_crusher_spirit/" + material.getId()));
                }

                if (processedType.contains("raw")) {
                    // Dust from Raw Material - Crusher Spirit
                    CrushingRecipeBuilder
                        .crushingRecipe(
                            EETags.MATERIAL_RAW.apply(material.getId()),
                            EERegistrar.dustMap.get(material),
                            200
                        ).setResultAmount(2)
                        .group("emendatusenigmatica:compat_recipe")
                        .unlockedBy("raw", has(EETags.MATERIAL_RAW.apply(material.getId())))
                        .save(output, Reference.getPath("dust/from_raw_crusher_spirit/" + material.getId()));
                }
            }
//        }
    }

    @Override
    public @NotNull String getName() {
        return "EE Occultism: Recipes";
    }
}
