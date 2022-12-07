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
            if (annotatedClass.isAssignableFrom(IEmendatusPlugin.class)) {
                var annotation = (EmendatusPluginReference) annotatedClass.getAnnotation(EmendatusPluginReference.class);
                LOADER_LOGGER.debug("Registered plugin " + annotation.modid() + ":" + annotation.name());
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
    }

    public void load() {
		this.plugins.forEach(iEmendatusPlugin -> iEmendatusPlugin.onLoad(this.registry));

		var materialModels = this.registry.getMaterials();
        for (StrataModel strata : this.registry.getStrata()) {
            for (MaterialModel material : materialModels) {
                if (material.getProcessedTypes().contains("ore")) {
                    EERegistrar.registerOre(strata, material);
                }
            }
        }

        for (MaterialModel material : materialModels) {
            if (material.getProcessedTypes().contains("storage_block")) {
                EERegistrar.registerStorageBlocks(material);
            }
            if (material.getProcessedTypes().contains("raw")) {
                EERegistrar.registerRaws(material);
                EERegistrar.registerRawBlocks(material);
            }
            if (material.getProcessedTypes().contains("ingot")) {
                EERegistrar.registerIngots(material);
            }
            if (material.getProcessedTypes().contains("nugget")) {
                EERegistrar.registerNuggets(material);
            }
            if (material.getProcessedTypes().contains("gem")) {
                EERegistrar.registerGems(material);
            }
            if (material.getProcessedTypes().contains("dust")) {
                EERegistrar.registerDusts(material);
            }
            if (material.getProcessedTypes().contains("plate")) {
                EERegistrar.registerPlates(material);
            }
            if (material.getProcessedTypes().contains("gear")) {
                EERegistrar.registerGears(material);
            }
            if (material.getProcessedTypes().contains("rod")) {
                EERegistrar.registerRods(material);
            }
            if (material.getProcessedTypes().contains("fluid")) {
                EERegistrar.registerFluids(material);
            }
            if (EmendatusEnigmatica.MEKANISM_LOADED) {
                if (material.getProcessedTypes().contains("slurry")) {
                    EEMekanismRegistrar.registerSlurries(material);
                }
                if (material.getProcessedTypes().contains("crystal")) {
                    EEMekanismRegistrar.registerCrystals(material);
                }
                if (material.getProcessedTypes().contains("shard")) {
                    EEMekanismRegistrar.registerShards(material);
                }
                if (material.getProcessedTypes().contains("clump")) {
                    EEMekanismRegistrar.registerClumps(material);
                }
                if (material.getProcessedTypes().contains("dirty_dust")) {
                    EEMekanismRegistrar.registerDirtyDusts(material);
                }
            }
            if (EmendatusEnigmatica.CREATE_LOADED) {
                if (material.getProcessedTypes().contains("crushed_ore")) {
                    EECreateRegistrar.registerCrushedOres(material);
                }
            }
            if (EmendatusEnigmatica.BLOODMAGIC_LOADED) {
                if (material.getProcessedTypes().contains("fragment")) {
                    EEBloodMagicRegistrar.registerFragments(material);
                }
                if (material.getProcessedTypes().contains("gravel")) {
                    EEBloodMagicRegistrar.registerGravels(material);
                }
            }
        }
    }
}
