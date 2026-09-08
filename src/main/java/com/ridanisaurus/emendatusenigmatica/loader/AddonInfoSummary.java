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
