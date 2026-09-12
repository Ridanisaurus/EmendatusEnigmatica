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

package com.ridanisaurus.emendatusenigmatica.loader;

import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.api.ISetupContext;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * SetupContext is a class containing references to the classes useful for addons.
 */
public final class SetupContext implements ISetupContext {
    private final EEPluginLoader pluginLoader;
    private final EEModelLoader modelLoader;
    private final EmendatusEnigmatica emendatusEnigmatica;

    /**
     * @param pluginLoader        EEPluginLoader reference.
     * @param modelLoader         EEModelLoader reference.
     * @param emendatusEnigmatica EE instance reference.
     */
    public SetupContext(EEPluginLoader pluginLoader, EEModelLoader modelLoader, EmendatusEnigmatica emendatusEnigmatica) {
        this.pluginLoader = pluginLoader;
        this.modelLoader = modelLoader;
        this.emendatusEnigmatica = emendatusEnigmatica;
    }

    @Contract(pure = true)
    @Override
    public EEPluginLoader getPluginLoader() {
        return pluginLoader;
    }

    @Contract(pure = true)
    @Override
    public EEModelLoader getModelLoader() {
        return modelLoader;
    }

    @Contract(pure = true)
    @Override
    public EmendatusEnigmatica getModReference() {
        return emendatusEnigmatica;
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (SetupContext) obj;
        return Objects.equals(this.pluginLoader, that.pluginLoader) &&
            Objects.equals(this.modelLoader, that.modelLoader) &&
            Objects.equals(this.emendatusEnigmatica, that.emendatusEnigmatica);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pluginLoader, modelLoader, emendatusEnigmatica);
    }

    @Contract(pure = true)
    @Override
    public @NotNull String toString() {
        return "SetupContext[" +
            "pluginLoader=" + pluginLoader + ", " +
            "modelLoader=" + modelLoader + ", " +
            "emendatusEnigmatica=" + emendatusEnigmatica + ']';
    }
}
