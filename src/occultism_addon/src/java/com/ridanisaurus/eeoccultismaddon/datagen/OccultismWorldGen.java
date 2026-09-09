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

import com.klikli_dev.occultism.datagen.worldgen.PlacedFeatures;
import com.ridanisaurus.eeoccultismaddon.EEOccultismPlugin;
import com.ridanisaurus.emendatusenigmatica.datagen.IFinishedGenericJSON;
import com.ridanisaurus.emendatusenigmatica.datagen.builder.FeatureBuilder;
import com.ridanisaurus.emendatusenigmatica.datagen.provider.EENeoFeatureProvider;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class OccultismWorldGen extends EENeoFeatureProvider {
    public OccultismWorldGen(DataGenerator gen, CompletableFuture<HolderLookup.Provider> providers) {
        super(gen, providers);
    }

    @Override
    protected void buildFeatures(HolderLookup.Provider provider, Consumer<IFinishedGenericJSON> consumer) {
        if (EEOccultismPlugin.disableIesnium.get()) {
            //TODO: Make sure iesnium ore is worth removing.
            new FeatureBuilder("neoforge:remove_features", "underground_ores")
                .biome("#minecraft:is_nether")
                .feature(PlacedFeatures.ORE_IESNIUM.location().toString())
                .save(consumer, Reference.getPath("compat/occultism/disable_iesnium_ore"));
        }

        if (EEOccultismPlugin.disableSilver.get()) {
            new FeatureBuilder("neoforge:remove_features", "underground_ores")
                .biome("#minecraft:is_overworld")
                .feature(PlacedFeatures.ORE_SILVER.location().toString())
                .save(consumer, Reference.getPath("compat/occultism/disable_silver_ore"));

            new FeatureBuilder("neoforge:remove_features", "underground_ores")
                .biome("#minecraft:is_overworld")
                .feature(PlacedFeatures.ORE_SILVER_DEEPSLATE.location().toString())
                .save(consumer, Reference.getPath("compat/occultism/disable_deepslate_silver_ore"));
        }
    }

    @Override
    public @NotNull String getName() {
        return "EE Occultism: Neo Features";
    }
}
