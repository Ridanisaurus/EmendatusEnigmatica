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
import com.ridanisaurus.emendatusenigmatica.api.AnnotationUtil;
import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.api.IEmendatusPlugin;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.plugin.DefaultConfigPlugin;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;
import net.minecraft.Util;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.registries.VanillaRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class EELoader {
    public static final Logger logger = LogManager.getLogger(EELoader.class);
    private final EmendatusDataRegistry dataRegistry;
    private final List<IEmendatusPlugin> plugins;
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
     * If the class is {@link DefaultConfigPlugin} goes at the start of the list as it has priority.
     */
    private void scanForClasses(){
        Stopwatch s = Stopwatch.createStarted();
        for (Class<?> annotatedClass : AnnotationUtil.getAnnotatedClasses(EmendatusPluginReference.class)) {
            if (IEmendatusPlugin.class.isAssignableFrom(annotatedClass)) {
                var annotation = (EmendatusPluginReference) annotatedClass.getAnnotation(EmendatusPluginReference.class);
                logger.info("Registered plugin {}:{}", annotation.modid(), annotation.name());
                try {
                    if (annotatedClass.equals(DefaultConfigPlugin.class)) {
                        this.plugins.addFirst((IEmendatusPlugin) annotatedClass.getDeclaredConstructor().newInstance());
                    } else {
                        plugins.add((IEmendatusPlugin) annotatedClass.getDeclaredConstructor().newInstance());
                    }
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
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

    public void loadData() {
		this.plugins.forEach(iEmendatusPlugin -> iEmendatusPlugin.load(this.dataRegistry));

		this.plugins.forEach(iEmendatusPlugin -> iEmendatusPlugin.registerMinecraft(this.dataRegistry.getMaterials(), this.dataRegistry.getStrata()));
    }

    public void registerDatagen(DataGenerator dataGenerator) {
        this.plugins.forEach(iEmendatusPlugin ->
            iEmendatusPlugin.registerDynamicDataGen(dataGenerator, this.dataRegistry, CompletableFuture.supplyAsync(VanillaRegistries::createLookup, Util.backgroundExecutor()))
        );
    }

    public void finish() {
        this.plugins.forEach(iEmendatusPlugin -> iEmendatusPlugin.finish(this.dataRegistry));
        this.finished = true;
    }

    public EmendatusDataRegistry getDataRegistry() {
        return dataRegistry;
    }

    public boolean isFinished() {
        return this.finished;
    }
}
