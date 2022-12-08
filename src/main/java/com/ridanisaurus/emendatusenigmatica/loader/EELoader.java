/*
 *  MIT License
 *
 *  Copyright (c) 2020 Ridanisaurus
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package com.ridanisaurus.emendatusenigmatica.loader;

import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.api.AnnotationUtil;
import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.api.IEmendatusPlugin;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.plugin.DefaultConfigPlugin;
import com.ridanisaurus.emendatusenigmatica.registries.EEBloodMagicRegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EECreateRegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EEMekanismRegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import net.minecraft.data.DataGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class EELoader {

    public static final Logger LOADER_LOGGER = LogManager.getLogger(EELoader.class);
    private final EmendatusDataRegistry registry;

    private List<IEmendatusPlugin> plugins;

    public EELoader() {
        this.registry = new EmendatusDataRegistry();
        this.plugins = new ArrayList<>();
        this.scanForClasses();
    }

    /**
     * Scans for classes that have the annotation {@link EmendatusPluginReference} and implements the class {@link IEmendatusPlugin} and
     * creates an instance for those classes.
     *
     * If the class is {@link DefaultConfigPlugin} goes at the start of the list as it has priority.
     */
    private void scanForClasses(){
        for (Class annotatedClass : AnnotationUtil.getAnnotatedClasses(EmendatusPluginReference.class)) {
            if (IEmendatusPlugin.class.isAssignableFrom(annotatedClass)) {
                var annotation = (EmendatusPluginReference) annotatedClass.getAnnotation(EmendatusPluginReference.class);
                LOADER_LOGGER.info("Registered plugin " + annotation.modid() + ":" + annotation.name());
                try {
                    if (annotatedClass.equals(DefaultConfigPlugin.class)) {
                        this.plugins.add(0, (IEmendatusPlugin) annotatedClass.getDeclaredConstructor().newInstance());
                    } else {
                        plugins.add((IEmendatusPlugin) annotatedClass.getDeclaredConstructor().newInstance());
                    }
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    LOADER_LOGGER.error(e);
                }
            } else {
                LOADER_LOGGER.error(annotatedClass.getName() + " has an annotation but it doesn't implement IEmendatusPlugin");
            }
        }
        LOADER_LOGGER.info("Finished scanning for plugins");
    }

    public void load() {
		this.plugins.forEach(iEmendatusPlugin -> iEmendatusPlugin.load(this.registry));

		this.plugins.forEach(iEmendatusPlugin -> iEmendatusPlugin.registerMinecraft(this.registry.getMaterials(), this.registry.getStrata()));
    }

    public void datagen(DataGenerator dataGenerator){
        this.plugins.forEach(iEmendatusPlugin -> iEmendatusPlugin.registerDynamicDataGen(dataGenerator, this.registry));
    }

    public void finish(){
        this.plugins.forEach(iEmendatusPlugin -> iEmendatusPlugin.finish(this.registry));
    }

    public EmendatusDataRegistry getRegistry() {
        return registry;
    }
}
