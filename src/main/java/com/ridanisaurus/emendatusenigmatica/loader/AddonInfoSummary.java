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

import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.util.summary.SummaryHandler;
import com.ridanisaurus.emendatusenigmatica.util.summary.SummaryExtension;
import com.ridanisaurus.emendatusenigmatica.util.summary.SummaryWriteContext;

import java.util.Objects;

public class AddonInfoSummary implements SummaryExtension {
    private final EEPluginLoader pluginLoader;
    private final EEModelLoader modelLoader;

    public AddonInfoSummary(EEPluginLoader pluginLoader, EEModelLoader modelLoader) {
        this.pluginLoader = Objects.requireNonNull(pluginLoader);
        this.modelLoader = Objects.requireNonNull(modelLoader);
    }

    /**
     * Method executed by the {@link SummaryHandler} with write context provided.
     *
     * @param cx Write Context for the file.
     */
    @Override
    public void accept(SummaryWriteContext cx) {
        cx.writeHeader("Loaded addons", 3);
        for (EmendatusPluginReference plugin : pluginLoader.getRegisteredPlugins()) {
            cx.writeLine("- <code>%s</code> found in <code>%s</code>".formatted(plugin.name(), plugin.modId()));
        }


        cx.writeHeader("Registered Definitions and Extensions", 3);
        modelLoader.getRegistryCopy().forEach((def, ext) -> {
            cx.writeHeader("%s (%s)".formatted(def.registryName(), def.getOwningAnnotation().name()), 5);
            if (!ext.isEmpty()) for (EEModelExtension<?, ?, ?, ?> extension : ext) {
                cx.writeLine("- <code>%s</code> registered by <code>%s</code>".formatted(extension.getRegistryName(), extension.getOwningAnnotation().name()));
            }
            cx.write("\n\n");
        });
    }
}
