package com.ridanisaurus.emendatusenigmatica.loader;

import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;
import com.ridanisaurus.emendatusenigmatica.util.analytics.AnalyticsAddon;
import com.ridanisaurus.emendatusenigmatica.util.analytics.AnalyticsWriteContext;

import java.util.Objects;

public class AddonInfoAnalytics implements AnalyticsAddon {
    private final EEPluginLoader pluginLoader;
    private final EEModelLoader modelLoader;

    public AddonInfoAnalytics(EEPluginLoader pluginLoader, EEModelLoader modelLoader) {
        this.pluginLoader = Objects.requireNonNull(pluginLoader);
        this.modelLoader = Objects.requireNonNull(modelLoader);
    }

    /**
     * Method executed by the {@link Analytics} with write context provided.
     *
     * @param cx Write Context for the file.
     */
    @Override
    public void accept(AnalyticsWriteContext cx) {
        cx.writeHeader("Loaded addons", 3);
        for (EmendatusPluginReference plugin : pluginLoader.getRegisteredPlugins()) {
            cx.writeLine("- <code>%s</code> found in <code>%s</code>".formatted(plugin.name(), plugin.modId()));
        }

        cx.writeHeader("Registered Extensions", 3);
        modelLoader.getRegistryCopy().forEach((def, ext) -> {
            if (ext.isEmpty()) return;
            cx.writeLine("%s (%s)".formatted(def.getRegistryName(), def.getOwningAnnotation().name()));
            for (EEModelExtension<?, ?, ?, ?> extension : ext) {
                cx.writeLine("- <code>%s</code> registered by <code>%s</code>".formatted(extension.getRegistryName(), extension.getOwningAnnotation().name()));
            }
            cx.write("\n\n");
        });
    }
}
