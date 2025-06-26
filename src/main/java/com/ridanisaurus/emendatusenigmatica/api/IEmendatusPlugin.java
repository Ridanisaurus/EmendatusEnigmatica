/*
 * MIT License
 *
 * Copyright (c) 2024. Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.api;

import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Plugin implementation to register things to the API at proper times, see {@link com.ridanisaurus.emendatusenigmatica.plugin.DefaultConfigPlugin} for examples
 */
public interface IEmendatusPlugin {

    /**
     * Method used to register materials, strata and compat.
     * @param registry The registry used to register the materials, strata and compat
     */
    void load(EmendatusDataRegistry registry);

    /**
     * Method used to register minecraft objects like items or blocks.
     * <p>
     * You will only need to register objects if you are adding new types that Emendatus Enigmatica doesn't support.
     * @param materialModels A list of all the available materials
     */
    void registerMinecraft(List<MaterialModel> materialModels, List<StrataModel> strataModels);

    /**
     * Method used to register dynamic data generators.
     * <p>
     * This will not run at your typical data generation time, it will be executed at runtime and automatically injected into the game, but they function the same as normal data generation.
     * @param generator DataGenerator to register data providers to.
     * @param registry Emendatus Enigmatica registry with all data parsed from the configuration files.
     * @param providers Vanilla Registry Lookup for use with vanilla generators that require it.
     */
    void registerDynamicDataGen(DataGenerator generator, EmendatusDataRegistry registry, CompletableFuture<HolderLookup.Provider> providers);

    /**
     * Method called at the end of all the steps where is safe to store an instance of the {@link EmendatusDataRegistry} in case its needed
     * @param registry A safe instance of the {@link EmendatusDataRegistry}
     */
    void finish(EmendatusDataRegistry registry);
}
