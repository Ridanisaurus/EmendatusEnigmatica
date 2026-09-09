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

package com.ridanisaurus.emendatusenigmatica.api;

import com.ridanisaurus.emendatusenigmatica.datagen.EEDataGenerator;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

/**
 * Basic implementation of some IEEPlugin methods, to remove the Void null argument from methods in a plugin that doesn't use plugin registry.
 */
public abstract class BasicEEPlugin implements IEEPlugin<Void> {
    @Override
    public void registerDynamicDataGen(EEDataGenerator generator, CompletableFuture<HolderLookup.Provider> providers, Void registry) {
        this.registerDynamicDataGen(generator, providers);
    }

    @Override
    public void register(Void registry) {
        this.register();
    }

    /**
     * Method called after EEDataGenerator is created and ready for registration of providers.
     *
     * @param generator EEDataGenerator instance.
     * @param providers Vanilla Registry Lookup for use with vanilla generators that require it.
     */
    public abstract void registerDynamicDataGen(EEDataGenerator generator, CompletableFuture<HolderLookup.Provider> providers);

    /**
     * Method called after validation and serialization of the models defined by the plugins.
     *
     * @apiNote This method is called when it's safe to register objects to Minecraft/Mods registries.
     */
    public abstract void register();
}
