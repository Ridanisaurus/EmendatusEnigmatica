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

package com.ridanisaurus.emendatusenigmatica.datagen.gen.world;

import com.ridanisaurus.emendatusenigmatica.datagen.IFinishedGenericJSON;
import com.ridanisaurus.emendatusenigmatica.datagen.builder.TagBuilder;
import com.ridanisaurus.emendatusenigmatica.datagen.provider.EETagProvider;
import com.ridanisaurus.emendatusenigmatica.plugin.DataRegistry;
import com.ridanisaurus.emendatusenigmatica.plugin.model.DepositModel;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class BiomeTagsGen extends EETagProvider {
    private final DataRegistry registry;

    public BiomeTagsGen(DataGenerator gen, DataRegistry registry) {
        super(gen);
        this.registry = registry;
    }

    @Override
    protected void buildTags(Consumer<IFinishedGenericJSON> consumer) {
        for (DepositModel model : registry.getRegisteredDeposits()) {
            // Generate packed tags for deposits which contain more than a single tag in the biome array.
            if (model.biomes.size() > 1 && model.biomes.stream().anyMatch(it -> it.startsWith("#")))
                new TagBuilder(model.biomes).save(consumer, Reference.getPath("biome/pack/" + model.id));
        }
    }

    @Override
    public @NotNull String getName() {
        return "Emendatus Enigmatica: Biome Tags";
    }
}
