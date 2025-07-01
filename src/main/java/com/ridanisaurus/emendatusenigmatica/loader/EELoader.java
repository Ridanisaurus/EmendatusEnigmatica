/*
 * MIT License
 *
 * Copyright (c) 2020-2024. Ridanisaurus
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

import com.google.common.base.Stopwatch;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.api.AnnotationUtil;
import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.api.IEmendatusPlugin;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.api.config.ConfigCreationContext;
import com.ridanisaurus.emendatusenigmatica.api.config.DCCreationContext;
import com.ridanisaurus.emendatusenigmatica.config.EEConfig;
import com.ridanisaurus.emendatusenigmatica.plugin.VanillaPlugin;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;
import net.minecraft.Util;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.registries.VanillaRegistries;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class EELoader {
    public static final Logger logger = LogManager.getLogger(EELoader.class);
    private final EmendatusDataRegistry dataRegistry;
    private final List<EEPlugin> plugins;
    private boolean finished = false;

    public EELoader() {
        this.dataRegistry = new EmendatusDataRegistry();
        this.plugins = new ArrayList<>();
        this.scanForClasses();
    }

    /**
     * Scans for classes that have the annotation {@link EmendatusPluginReference} and implements the class {@link IEmendatusPlugin} and
     * creates an instance for those classes.
     * <p>
     * If the class is {@link VanillaPlugin} goes at the start of the list as it has priority.
     */
    private void scanForClasses(){
        Stopwatch s = Stopwatch.createStarted();
        for (Class<?> annotatedClass : AnnotationUtil.getAnnotatedClasses(EmendatusPluginReference.class)) {
            if (IEmendatusPlugin.class.isAssignableFrom(annotatedClass)) {
                var annotation = (EmendatusPluginReference) annotatedClass.getAnnotation(EmendatusPluginReference.class);
                logger.info("Registered plugin {}:{}", annotation.modid(), annotation.name());
                try {
                    var plugin = new EEPlugin((IEmendatusPlugin) annotatedClass.getDeclaredConstructor().newInstance(), annotation);
                    if (annotatedClass.equals(VanillaPlugin.class)) {
                        this.plugins.addFirst(plugin);
                    } else {
                        plugins.add(plugin);
                    }
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    logger.error(e);
                }
            } else {
                logger.error("{} has an annotation but it doesn't implement IEmendatusPlugin", annotatedClass.getName());
            }
        }
        s.stop();
        logger.info("Finished scanning for plugins, took {}ms.", s.elapsed(TimeUnit.MILLISECONDS));
        Analytics.addPerformanceAnalytic("Scanning and registration of addons", s);
    }

    public void setupConfig(ModConfigSpec.Builder builder, ModConfig.Type type) {
        var ctx = new ConfigCreationContext(builder, type);
        this.plugins.forEach(it -> it.plugin.extendConfig(ctx.setAddon(it.annotation.name())));
        // Pop out of the last addon category.
        builder.pop();
    }

    public void setup() throws ExecutionException, InterruptedException {
        if (EEConfig.startup.generateDefaultConfigs.get() || EEConfig.startup.regenerateDefaults.get()) {
            if (EEConfig.startup.regenerateDefaults.get()) {
                EmendatusEnigmatica.logger.warn("Regeneration of default configurations triggered! This will wipe your EE Config directory.");
                try {
                    FileUtils.deleteDirectory(Analytics.CONFIG_DIR.toFile());
                } catch (IOException e) {
                    throw new RuntimeException("IO Exception while trying to delete EE Configuration directory.", e);
                }
                EEConfig.startup.regenerateDefaults.set(false);
                EEConfig.saveStartup();
            }
            // We only generate defaults if the Config Dir is not existent.
            if (Files.exists(Analytics.CONFIG_DIR)) return;
            EmendatusEnigmatica.logger.info("Generating default Emendatus Enigmatica configurations...");
            var ctx = new DCCreationContext();
            this.plugins.forEach(it -> it.plugin.provideDefaultConfiguration(ctx.setCurrentAddon(it.annotation.name())));

            CompletableFuture<Void> future = CompletableFuture.allOf(
                ctx.getEntries()
                    .stream()
                    .map(it -> it.save(Util.ioPool()))
                    .toList()
                    .toArray(new CompletableFuture[] {})
            );

            future.get();

            EmendatusEnigmatica.logger.info("Defaults generated.");
        }

        // Call Setup after generation of the default configuration files.
        // Vanilla Plugin setups the EE Config folder, which we use to check if we should generate defaults.
        this.plugins.forEach(it -> it.plugin.setup());
    }

    public void loadData() {
		this.plugins.forEach(it -> it.plugin.load(this.dataRegistry));

		this.plugins.forEach(it -> it.plugin.registerMinecraft(this.dataRegistry.getMaterials(), this.dataRegistry.getStrata()));
    }

    public void registerDatagen(DataGenerator dataGenerator) {
        this.plugins.forEach(it ->
            it.plugin.registerDynamicDataGen(dataGenerator, this.dataRegistry, CompletableFuture.supplyAsync(VanillaRegistries::createLookup, Util.backgroundExecutor()))
        );
    }

    public void finish() {
        this.finished = true;
    }

    public EmendatusDataRegistry getDataRegistry() {
        return dataRegistry;
    }

    public boolean isFinished() {
        return this.finished;
    }

    private record EEPlugin(IEmendatusPlugin plugin, EmendatusPluginReference annotation) {}
}
