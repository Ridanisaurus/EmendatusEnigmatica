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
import com.ridanisaurus.emendatusenigmatica.api.IEEPlugin;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.api.config.ConfigCreationContext;
import com.ridanisaurus.emendatusenigmatica.api.config.DCCreationContext;
import com.ridanisaurus.emendatusenigmatica.config.EEConfig;
import com.ridanisaurus.emendatusenigmatica.datagen.EEDataGenerator;
import com.ridanisaurus.emendatusenigmatica.plugin.VanillaPlugin;
import com.ridanisaurus.emendatusenigmatica.util.ClassHelper;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;
import net.minecraft.Util;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InvalidClassException;
import java.lang.reflect.Constructor;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * <h1>Plugin loader</h1>
 * EEPluginLoader is a class responsible for discovery, validation,
 * construction and management of plugins and their corresponding registries.
 *
 * <h3>Plugin registration</h3>
 * Plugins are discovered in a classpath by scanning classes for {@link EmendatusPluginReference} annotation,
 * and validating their implementation of {@link IEEPlugin} interface.
 *
 * <h3>Registry handling</h3>
 * Registry class defined in generic type of {@link IEEPlugin} will be constructed for each plugin from their public no-arg constructor,
 * and stored alongside the plugin for later use.
 * Usage of {@link Void} class will result with no registry being constructed for your plugin.
 *
 * @see IEEPlugin IEEPlugin interface documentation.
 * @see EEModelLoader ModelLoader documentation.
 * @see #getRegistry(Class) Accessing registries of different plugins.
 */
public class EEPluginLoader {
    public static final Logger logger = LogManager.getLogger(EEPluginLoader.class);
    private final List<EEPlugin> plugins;
    private boolean finished = false;

    public EEPluginLoader() {
        this.plugins = new ArrayList<>();
        this.scanForClasses();
    }

    /**
     * Scans for classes that have the annotation {@link EmendatusPluginReference} and implements the class {@link IEEPlugin} and
     * creates an instance for those classes.
     * <p>
     * If the class is {@link VanillaPlugin} goes at the start of the list as it has priority.
     */
    @SuppressWarnings("unchecked")
    private void scanForClasses(){
        Stopwatch s = Stopwatch.createStarted();
        for (Class<?> annotatedClass : ClassHelper.getAnnotatedClasses(EmendatusPluginReference.class)) {
            if (!IEEPlugin.class.isAssignableFrom(annotatedClass)) {
                logger.error("\"{}\" has an annotation but it doesn't implement IEEPlugin.", annotatedClass.getName());
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

                var generic = ClassHelper.getGenericInterfaceType(annotatedClass, IEEPlugin.class);
                if (Objects.isNull(generic))
                    throw new InvalidClassException("Class of the plugin \"%s\" implements IEEPlugin interface as a raw type.".formatted(name));
                Constructor<?> registryConstructor = ClassHelper.getNoArgConstructor(generic);
                if (!generic.equals(Void.class) && Objects.isNull(registryConstructor))
                    throw new IllegalStateException("Registry of the plugin \"%s\" doesn't have a no-arg constructor.".formatted(name));

                // Construction of the plugin
                var plugin = new EEPlugin(
                    (IEEPlugin<Object>) pluginConstructor.newInstance(),
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
     * Executes {@link IEEPlugin#extendConfig(ConfigCreationContext)} for each addon,
     * with the context created based on the arguments.
     * @param builder Builder of the configuration.
     * @param type Type of the configuration.
     */
    public void setupConfig(ModConfigSpec.Builder builder, ModConfig.Type type) {
        var ctx = new ConfigCreationContext(builder, type);
        this.plugins.forEach(it -> it.plugin.extendConfig(ctx.setAddon(it.annotation.name())));
        // Pop out of the last addon category.
        if (!plugins.isEmpty()) builder.pop();
    }

    /**
     * Executes {@link IEEPlugin#provideDefaultConfiguration(DCCreationContext)} and {@link IEEPlugin#setup(SetupContext)} of each plugin.
     * @param ctx SetupContext to pass to the addons;
     * @throws ExecutionException When ExecutionException while saving configuration data occurs.
     * @throws InterruptedException When the saving operation was interrupted.
     */
    public void setup(SetupContext ctx) throws ExecutionException, InterruptedException {
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
                var context = new DCCreationContext();
                this.plugins.forEach(it -> it.plugin.provideDefaultConfiguration(context.setCurrentAddon(it.annotation)));

                CompletableFuture<Void> future = CompletableFuture.allOf(
                    context.getEntries()
                        .stream()
                        .map(it -> it.save(Util.ioPool()))
                        .toList()
                        .toArray(new CompletableFuture[] {})
                );

                future.get();

                EmendatusEnigmatica.logger.info("Defaults generated.");
            }
        }

        ctx.modelLoader().startRegistration();
        this.plugins.forEach(it -> {
            ctx.modelLoader().setCurrentPlugin(it.annotation);
            it.plugin.setup(ctx);
        });
        ctx.modelLoader().finishRegistration();
    }

    /**
     * Executes {@link EEModelLoader#load(EEPluginLoader)} and {@link IEEPlugin#register(Object)} of each plugin.
     */
    public void load(@NotNull EEModelLoader loader) {
        loader.load(this);
		this.plugins.forEach(it -> it.plugin.register(it.registry));
    }

    /**
     * Executes {@link IEEPlugin#registerDynamicDataGen(EEDataGenerator, CompletableFuture, Object)} of each plugin, for provided DataGenerator.
     * @param dataGenerator DataGenerator used to run the registered providers.
     */
    public void registerDataGen(EEDataGenerator dataGenerator) {
        this.plugins.forEach(it ->
            it.plugin.registerDynamicDataGen(
                dataGenerator,
                CompletableFuture.supplyAsync(VanillaRegistries::createLookup, Util.backgroundExecutor()),
                it.registry
            )
        );
    }

    /**
     * Mark the EEPluginLoader as finished.
     */
    public void finish() {
        this.finished = true;
    }

    /**
     * @return True if EEPluginLoader was marked as finished, false otherwise.
     */
    public boolean isFinished() {
        return this.finished;
    }

    /**
     * Used for getting access to other plugins' registry.
     * @param plugin Class of the plugin to get registry.
     * @return Registry object of the plugin requested, or null if R is Void.
     * @param <R> Class of the registry of the plugin.
     */
    @SuppressWarnings("unchecked")
    public <R> R getRegistry(Class<? extends IEEPlugin<R>> plugin) {
        for (EEPlugin eePlugin : plugins) {
            if (eePlugin.plugin.getClass().equals(plugin)) return (R) eePlugin.registry;
        }
        return null;
    }

    /**
     * Private wrapper of the EEPlugin.
     * @param plugin Plugin object
     * @param annotation Plugin Annotation object
     * @param registry Plugin Registry object (Nullable)
     */
    private record EEPlugin(IEEPlugin<Object> plugin, EmendatusPluginReference annotation, @Nullable Object registry) {}
}
