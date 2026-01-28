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
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import java.util.concurrent.CompletableFuture;

/**
 * IEmendatusPlugin is an interface required to be implemented by EmendatusEnigmatica plugins.
 * All methods defined by it are optional in implementation, and per-method documentation explains their usage.
 * @see EmendatusPluginReference
 * @see VanillaPlugin
 * @implSpec The <code>T</code> parameter is an optional custom registry type, which will be passed to the load/register methods by the EEPluginLoader.
 * @deprecated This interface was deprecated and replaced by {@link IEEPlugin}, please port your addon to the new interface before full release of 2.2.0.
 */
@SuppressWarnings("deprecated removal")
@Deprecated(since = "2.2.0-Alpha-4", forRemoval = true)
public interface IEmendatusPlugin<T> {

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
    default void load(EmendatusDataRegistry registry, T customRegistry) {}

    /**
     * Method used to register minecraft objects like items or blocks.
     * @param registry The EmendatusDataRegistry used to store data used by EE.
     * @param customRegistry CustomRegistry object specified in the annotation, or null if {@link Void}
     * @implNote You will only need to register objects if you are adding new types that Emendatus Enigmatica doesn't support.
     */
    default void registerMinecraft(EmendatusDataRegistry registry, T customRegistry) {}

    /**
     * Method used to register dynamic data generators.
     * @param generator DataGenerator to register data providers to.
     * @param registry Emendatus Enigmatica registry with all data parsed from the configuration files.
     * @param providers Vanilla Registry Lookup for use with vanilla generators that require it.
     * @param customRegistry CustomRegistry object specified in the annotation, or null if {@link Void}
     * @implSpec This will not run at your typical data generation time,
     * it will be executed at runtime and automatically injected into the game,
     * but they function the same as normal data generation.
     */
    default void registerDynamicDataGen(DataGenerator generator, CompletableFuture<HolderLookup.Provider> providers, EmendatusDataRegistry registry, T customRegistry) {}

    /**
     * Method used to provide default configuration data for the mod it supports, whenever necessary.
     * @param registry DCCreationContext used to register configs and check for compatibility.
     * @implNote Will be executed right before {@link IEmendatusPlugin#setup()}.
     */
    default void provideDefaultConfiguration(DCCreationContext registry) {}

    /**
     * Method called at the end of all the steps where is safe to store an instance of the {@link EmendatusDataRegistry} in case its needed
     * @param registry A safe instance of the {@link EmendatusDataRegistry}
     * @deprecated Will be removed in 2.2.0 and is currently not called.
     * Use {@link EmendatusEnigmatica#getDataRegistry()} to get the instance of the default EDR.
     */
    @Deprecated(since = "2.2.0-Alpha-1", forRemoval = true)
    default void finish(EmendatusDataRegistry registry) {}
}
