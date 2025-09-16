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
import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.api.IEmendatusPlugin;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.api.config.ConfigCreationContext;
import com.ridanisaurus.emendatusenigmatica.api.config.DCCreationContext;
import com.ridanisaurus.emendatusenigmatica.config.EEConfig;
import com.ridanisaurus.emendatusenigmatica.plugin.VanillaPlugin;
import com.ridanisaurus.emendatusenigmatica.util.ClassHelper;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;
import net.minecraft.Util;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InvalidClassException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.util.*;
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
    @SuppressWarnings("unchecked")
    private void scanForClasses(){
        Stopwatch s = Stopwatch.createStarted();
        for (Class<?> annotatedClass : ClassHelper.getAnnotatedClasses(EmendatusPluginReference.class)) {
            if (!IEmendatusPlugin.class.isAssignableFrom(annotatedClass)) {
                logger.error("{} has an annotation but it doesn't implement IEmendatusPlugin", annotatedClass.getName());
                continue;
            }

            var annotation = annotatedClass.getAnnotation(EmendatusPluginReference.class);
            String name = "%s:%s".formatted(annotation.modId(), annotation.name());
            try {
                // Validation
                if (!ResourceLocation.isValidNamespace(annotation.modId()))
                    throw new IllegalArgumentException("ModID of plugin \"%s\" is invalid.".formatted(annotatedClass.getName()));
                if (!ResourceLocation.isValidPath(annotation.name()))
                    throw new IllegalArgumentException("Name of plugin \"%s\" is invalid.".formatted(annotatedClass.getName()));

                var pluginConstructor = ClassHelper.getNoArgConstructor(annotatedClass);
                if (Objects.isNull(pluginConstructor))
                    throw new IllegalStateException("Class of the plugin \"%s\" doesn't have a no-arg constructor.".formatted(name));

                Constructor<?> registryConstructor = ClassHelper.getNoArgConstructor(annotation.registry());
                if (!annotation.registry().equals(Void.class) && Objects.isNull(registryConstructor))
                    throw new IllegalStateException("Registry of the plugin \"%s\" doesn't have a no-arg constructor.".formatted(name));

                var generic = ClassHelper.getGenericInterfaceType(annotatedClass, IEmendatusPlugin.class);
                if (Objects.isNull(generic))
                    throw new InvalidClassException("Class of the plugin \"%s\" implements IEmendatusPlugin interface as a raw type.".formatted(name));
                if (!generic.equals(annotation.registry()))
                    throw new InvalidClassException("Class of the plugin \"%s\" implements IEmendatusPlugin with incorrect registry generic.".formatted(name));

                // Construction of the plugin
                var plugin = new EEPlugin(
                    (IEmendatusPlugin<Object>) pluginConstructor.newInstance(),
                    annotation,
                    Objects.nonNull(registryConstructor)? registryConstructor.newInstance(): null
                );

                if (annotatedClass.equals(VanillaPlugin.class)) {
                    this.plugins.addFirst(plugin);
                } else {
                    plugins.add(plugin);
                }

                logger.info("Registered plugin \"{}\"", name);
            } catch (Throwable e) {
                logger.error("Failed registration of plugin \"{}\"", name, e);
            }
        }

        s.stop();
        logger.info("Finished scanning for plugins, took {}ms.", s.elapsed(TimeUnit.MILLISECONDS));
        Analytics.addPerformanceAnalytic("Scanning and registration of addons", s);
    }

    /**
     * Executes {@link IEmendatusPlugin#extendConfig(ConfigCreationContext)} for each addon, with the context created based on the arguments.
     * @param builder Builder of the configuration.
     * @param type Type of the configuration.
     */
    public void setupConfig(ModConfigSpec.Builder builder, ModConfig.Type type) {
        var ctx = new ConfigCreationContext(builder, type);
        this.plugins.forEach(it -> it.plugin.extendConfig(ctx.setAddon(it.annotation.name())));
        // Pop out of the last addon category.
        builder.pop();
    }

    /**
     * Executes {@link IEmendatusPlugin#provideDefaultConfiguration(DCCreationContext)} and {@link IEmendatusPlugin#setup()} of each plugin.
     * @throws ExecutionException When ExecutionException while saving configuration data occurs.
     * @throws InterruptedException When the saving operation was interrupted.
     */
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
            if (Files.notExists(Analytics.CONFIG_DIR)) {
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
            };
        }

        // Call Setup after generation of the default configuration files.
        // Vanilla Plugin setups the EE Config folder, which we use to check if we should generate defaults.
        this.plugins.forEach(it -> it.plugin.setup());
    }

    /**
     * Executes {@link IEmendatusPlugin#load(EmendatusDataRegistry, Object)} and {@link IEmendatusPlugin#registerMinecraft(EmendatusDataRegistry, Object)} of each plugin.
     */
    public void loadData() {
		this.plugins.forEach(it -> it.plugin.load(this.dataRegistry, it.registry));
		this.plugins.forEach(it -> it.plugin.registerMinecraft(this.dataRegistry, it.registry));
    }

    /**
     * Executes {@link IEmendatusPlugin#registerDynamicDataGen(DataGenerator, CompletableFuture, EmendatusDataRegistry, Object)} of each plugin, for provided DataGenerator.
     * @param dataGenerator DataGenerator used to run the registered providers.
     */
    public void registerDataGen(DataGenerator dataGenerator) {
        this.plugins.forEach(it ->
            it.plugin.registerDynamicDataGen(
                dataGenerator,
                CompletableFuture.supplyAsync(VanillaRegistries::createLookup, Util.backgroundExecutor()),
                this.dataRegistry,
                it.registry
            )
        );
    }

    /**
     * Mark the EELoader as finished.
     */
    public void finish() {
        this.finished = true;
    }

    /**
     * @return EmendatusDataRegistry created on setup.
     */
    public EmendatusDataRegistry getDataRegistry() {
        return dataRegistry;
    }

    /**
     * @return True if EELoader was marked as finished, false otherwise.
     */
    public boolean isFinished() {
        return this.finished;
    }

    /**
     * Private wrapper of the EEPlugin.
     * @param plugin Plugin object
     * @param annotation Plugin Annotation object
     * @param registry Plugin Registry object (Nullable)
     */
    private record EEPlugin(IEmendatusPlugin<Object> plugin, EmendatusPluginReference annotation, @Nullable Object registry) {}
}
