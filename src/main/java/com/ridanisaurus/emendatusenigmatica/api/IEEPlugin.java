/*
 * MIT License
 *
 * Copyright (c) 2024-2026. Ridanisaurus
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

import com.ridanisaurus.emendatusenigmatica.loader.ConfigCreationContext;
import com.ridanisaurus.emendatusenigmatica.api.config.DCCreationContext;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.datagen.EEDataGenerator;
import com.ridanisaurus.emendatusenigmatica.loader.SetupContext;
import net.minecraft.core.HolderLookup;
import java.util.concurrent.CompletableFuture;

/**
 * <h3>Emendatus Enigmatica Plugin interface</h3>
 * IEEPlugin is an interface required to be implemented by all EmendatusEnigmatica plugins.
 * For detailed documentation, check the JavaDoc for methods defined by this interface.
 *
 * <h3>Plugin Registry</h3>
 * Each plugin can define their own registry class, which will be constructed and managed by EE.
 *
 * @param <R> Registry class of your plugin.
 * @see EmendatusPluginReference
 * @see com.ridanisaurus.emendatusenigmatica.loader.EEPluginLoader EEPluginLoader
 */
public interface IEEPlugin<R> {

    /**
     *  This method is used to make any necessary changes to other plugins,
     *  register your models and anything else your plugin requires before EE itself starts.
     * @param ctx Setup context, containing references to instances of EE loaders.
     */
    void setup(SetupContext ctx);

    /**
     * Method executed for each EE Configuration file,
     * allowing addons to extend the configuration files with their own options.
     * @param ctx Config Creation Context
     * @apiNote Please make sure you are extending the correct type of the configuration file.
     * This method is executed for Client / Startup configs.
     */
    void extendConfig(ConfigCreationContext ctx);

    /**
     * Method used to provide default configuration data for the mod it supports, if necessary.
     * @param ctx DCCreationContext used to register configs and check for compatibility.
     */
    void provideDefaultConfiguration(DCCreationContext ctx);

    /**
     * Method called after EEDataGenerator is created and ready for registration of providers.
     * @param generator EEDataGenerator instance.
     * @param providers Vanilla Registry Lookup for use with vanilla generators that require it.
     * @param registry The registry class specified in the R parameter of your plugin.
     */
    void registerDynamicDataGen(EEDataGenerator generator, CompletableFuture<HolderLookup.Provider> providers, R registry);


    /**
     * Method called after validation and serialization of the models defined by the plugins.
     * @param registry The registry class specified in the R parameter of your plugin.
     * @apiNote This method is called when it's safe to register objects to Minecraft/Mods registries.
     */
    void register(R registry);
}
