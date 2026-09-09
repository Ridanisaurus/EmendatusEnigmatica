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
