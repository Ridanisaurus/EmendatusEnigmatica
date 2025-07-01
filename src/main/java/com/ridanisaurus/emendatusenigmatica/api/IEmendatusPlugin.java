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

import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.api.config.ConfigCreationContext;
import com.ridanisaurus.emendatusenigmatica.api.config.DCCreationContext;
import com.ridanisaurus.emendatusenigmatica.plugin.VanillaPlugin;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * IEmendatusPlugin is an interface required to be implemented by EmendatusEnigmatica plugins.
 * All methods defined by it are optional in implementation, and per-method documentation explains their usage.
 * @see VanillaPlugin for example implementation.
 */
public interface IEmendatusPlugin {

    /**
     * Method executed right after registration of the plugins.
     * Used to set up necessary changes to the default EE behavior or initial configuration of the plugin.
     */
    default void setup() {}

    /**
     * Method executed for each EE Configuration file,
     * allowing addons to extend the configuration files with their own options.
     * @param ctx Config Creation Context
     * @apiNote Please make sure you are extending the correct type of the configuration file.
     * This method is executed for Client / Startup configs.
     */
    default void extendConfig(ConfigCreationContext ctx) {}

    /**
     * Method used to load and register materials, strata, compat, or your custom data.
     * @param registry The EmendatusDataRegistry used to store data used by EE.
     */
    default void load(EmendatusDataRegistry registry) {}

    /**
     * Method used to register minecraft objects like items or blocks.
     * @param materialModels A list of all the available materials
     * @implNote You will only need to register objects if you are adding new types that Emendatus Enigmatica doesn't support.
     */
    default void registerMinecraft(List<MaterialModel> materialModels, List<StrataModel> strataModels) {}

    /**
     * Method used to register dynamic data generators.
     * @param generator DataGenerator to register data providers to.
     * @param registry Emendatus Enigmatica registry with all data parsed from the configuration files.
     * @param providers Vanilla Registry Lookup for use with vanilla generators that require it.
     * @implSpec This will not run at your typical data generation time,
     * it will be executed at runtime and automatically injected into the game,
     * but they function the same as normal data generation.
     */
    default void registerDynamicDataGen(DataGenerator generator, EmendatusDataRegistry registry, CompletableFuture<HolderLookup.Provider> providers) {}

    /**
     * Method used to provide default configuration data for the mod it supports, whenever necessary.
     * @param registry DCCreationContext used to register configs and check for compatibility.
     * @implNote Will be executed right before {@link IEmendatusPlugin#setup()}.
     */
    default void provideDefaultConfiguration(DCCreationContext registry) {}

    /**
     * Method called at the end of all the steps where is safe to store an instance of the {@link EmendatusDataRegistry} in case its needed
     * @param registry A safe instance of the {@link EmendatusDataRegistry}
     * @deprecated Will be removed in 2.2.1 and is currently not called.
     * Use {@link EmendatusEnigmatica#getDataRegistry()} to get the instance of the default EDR.
     */
    @Deprecated(since = "2.2.0", forRemoval = true)
    default void finish(EmendatusDataRegistry registry) {}
}
