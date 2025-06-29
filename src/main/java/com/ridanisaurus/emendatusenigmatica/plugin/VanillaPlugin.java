/*
 * MIT License
 *
 * Copyright (c) 2024. Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.plugin;

import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.api.config.DefaultConfigRegistry;
import com.ridanisaurus.emendatusenigmatica.api.config.DefaultConfigurationData;
import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.api.IEmendatusPlugin;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.block.*;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.block.tags.BlockHarvestLevelTagsGen;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.block.tags.BlockHarvestToolTagsGen;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.block.tags.BlockTagsGen;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.fluid.FluidModelsGen;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.fluid.FluidTagsGen;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.item.ItemModelsGen;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.item.ItemTagsGen;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.world.BiomeTagsGen;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.world.NeoFeatureGen;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.world.OreFeatureGen;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.CompletableFuture;

//This plugin will be always first
@EmendatusPluginReference(modid = Reference.MOD_ID, name = "vanilla-plugin")
public class VanillaPlugin implements IEmendatusPlugin {

    @Override
    public void setup() {
        try {
            Path strataDir = Analytics.CONFIG_DIR.resolve("strata/");
            if (Files.notExists(strataDir)) {
                Files.createDirectories(strataDir);
                EmendatusEnigmatica.logger.info("Created /config/emendatusenigmatica/strata/");
            }
            Path materialDir = Analytics.CONFIG_DIR.resolve("material/");
            if (Files.notExists(materialDir)) {
                Files.createDirectories(materialDir);
                EmendatusEnigmatica.logger.info("Created /config/emendatusenigmatica/material/");
            }
            Path compatDir = Analytics.CONFIG_DIR.resolve("compat/");
            if (Files.notExists(compatDir)) {
                Files.createDirectories(compatDir);
                EmendatusEnigmatica.logger.info("Created /config/emendatusenigmatica/compat/");
            }
            Path depositDir = Analytics.CONFIG_DIR.resolve("deposit/");
            if (Files.notExists(depositDir)) {
                Files.createDirectories(depositDir);
                EmendatusEnigmatica.logger.info("Created /config/emendatusenigmatica/deposit/");
            }

        } catch (Exception e) {
            throw new RuntimeException("IOException occurred when setting up EE Config Directory!", e);
        }
    }

    @Override
    public void load(EmendatusDataRegistry registry) {
        DefaultLoader.load(registry);
    }

    @Override
    public void registerMinecraft(List<MaterialModel> materialModels, List<StrataModel> strataModels) {
        for (MaterialModel material : materialModels) {
            List<String> types = material.getProcessedTypes();
            if (types.contains("storage_block")) EERegistrar.registerStorageBlocks(material);
            if (types.contains("ingot"))    EERegistrar.registerIngots(material);
            if (types.contains("nugget"))   EERegistrar.registerNuggets(material);
            if (types.contains("gem"))      EERegistrar.registerGems(material);
            if (types.contains("dust"))     EERegistrar.registerDusts(material);
            if (types.contains("plate"))    EERegistrar.registerPlates(material);
            if (types.contains("gear"))     EERegistrar.registerGears(material);
            if (types.contains("rod"))      EERegistrar.registerRods(material);
            if (types.contains("sword"))    EERegistrar.registerSwords(material);
            if (types.contains("pickaxe"))  EERegistrar.registerPickaxes(material);
            if (types.contains("axe"))      EERegistrar.registerAxes(material);
            if (types.contains("shovel"))   EERegistrar.registerShovels(material);
            if (types.contains("hoe"))      EERegistrar.registerHoes(material);
            if (types.contains("paxel"))    EERegistrar.registerPaxels(material);
            if (types.contains("armor"))    EERegistrar.registerArmor(material);
            if (types.contains("shield"))   EERegistrar.registerShields(material);
            if (types.contains("fluid"))    EERegistrar.registerFluids(material);

            if (types.contains("raw")) {
                EERegistrar.registerRaw(material);
                if (types.contains("storage_block")) EERegistrar.registerRawBlocks(material);
            }

            if (types.contains("cluster")) {
                EERegistrar.registerSmallBudBlocks(material);
                EERegistrar.registerMediumBudBlocks(material);
                EERegistrar.registerLargeBudBlocks(material);
                EERegistrar.registerClusterBlocks(material);
                EERegistrar.registerBuddingBlocks(material);
                EERegistrar.registerClusterShardBlocks(material);
                EERegistrar.registerClusterShards(material);
            }

            for (StrataModel strata : strataModels) {
                if (types.contains("ore")) {
                    if (material.getStrata().isEmpty() || material.getStrata().contains(strata.getId())) EERegistrar.registerOre(strata, material);

                    //TODO: Rework Sample System.
//                    if (types.contains("sample")) {
//                        if (material.getStrata().isEmpty() || material.getStrata().contains(strata.getId())) {
//                            EERegistrar.registerSample(strata, material);
//                        }
//                    }
                }
            }
        }
    }

    @Override
    public void registerDynamicDataGen(DataGenerator generator, EmendatusDataRegistry registry, CompletableFuture<HolderLookup.Provider> providers) {
        generator.addProvider(true, new BlockStatesGen(generator, registry));
        generator.addProvider(true, new BlockModelsGen(generator, registry));
        generator.addProvider(true, new BlockTagsGen(generator, registry));
        generator.addProvider(true, new BlockHarvestLevelTagsGen(generator, registry));
        generator.addProvider(true, new BlockHarvestToolTagsGen(generator, registry));
        generator.addProvider(true, new ItemModelsGen(generator, registry));
        generator.addProvider(true, new ItemTagsGen(generator, registry));
        generator.addProvider(true, new FluidModelsGen(generator, registry));
        generator.addProvider(true, new FluidTagsGen(generator, registry));
        generator.addProvider(true, new LangGen(generator, registry));
        generator.addProvider(true, new RecipesGen(generator, registry, providers));
        generator.addProvider(true, new LootGen(generator, registry, providers));
        generator.addProvider(true, new NeoFeatureGen(generator, registry, providers));
        generator.addProvider(true, new OreFeatureGen(generator, providers));
        generator.addProvider(true, new BiomeTagsGen(generator));
    }

    @Override
    public void provideDefaultConfiguration(DefaultConfigRegistry registry) {
        //TODO: Add to the defaults replacements for the textures,
        // as we don't actually replace vanilla items by default (causes issues).
        String path = "assets/" + Reference.MOD_ID + "/configs/";
        // We don't need to check for compatibility while adding vanilla materials, no other addon should add those, and we will be executed first!
        registry.register(new DefaultConfigurationData(path + "material", "material/vanilla", "coal"));
        registry.register(new DefaultConfigurationData(path + "material", "material/vanilla", "copper"));
        registry.register(new DefaultConfigurationData(path + "material", "material/vanilla", "iron"));
        registry.register(new DefaultConfigurationData(path + "material", "material/vanilla", "gold"));
        registry.register(new DefaultConfigurationData(path + "material", "material/vanilla", "diamond"));
        registry.register(new DefaultConfigurationData(path + "material", "material/vanilla", "emerald"));
        registry.register(new DefaultConfigurationData(path + "material", "material/vanilla", "lapis"));
        registry.register(new DefaultConfigurationData(path + "material", "material/vanilla", "redstone"));
        registry.register(new DefaultConfigurationData(path + "material", "material/vanilla", "quartz"));
//      registry.register(new DefaultConfigurationData(path + "material", "material/vanilla", "netherite"));
        registry.register(new DefaultConfigurationData(path + "strata", "strata/vanilla", "stone"));
        registry.register(new DefaultConfigurationData(path + "strata", "strata/vanilla", "deepslate"));
        registry.register(new DefaultConfigurationData(path + "strata", "strata/vanilla", "netherrack"));
        registry.register(new DefaultConfigurationData(path + "deposit/coal", "deposit/vanilla/coal", "coal_lower"));
        registry.register(new DefaultConfigurationData(path + "deposit/coal", "deposit/vanilla/coal", "coal_upper"));
        registry.register(new DefaultConfigurationData(path + "deposit/copper", "deposit/vanilla/copper", "copper_normal"));
        registry.register(new DefaultConfigurationData(path + "deposit/copper", "deposit/vanilla/copper", "copper_large"));
        registry.register(new DefaultConfigurationData(path + "deposit/iron", "deposit/vanilla/iron", "iron_small"));
        registry.register(new DefaultConfigurationData(path + "deposit/iron", "deposit/vanilla/iron", "iron_middle"));
        registry.register(new DefaultConfigurationData(path + "deposit/iron", "deposit/vanilla/iron", "iron_upper"));
        registry.register(new DefaultConfigurationData(path + "deposit/gold", "deposit/vanilla/gold", "gold_extra"));
        registry.register(new DefaultConfigurationData(path + "deposit/gold", "deposit/vanilla/gold", "gold_lower"));
        registry.register(new DefaultConfigurationData(path + "deposit/gold", "deposit/vanilla/gold", "gold_normal"));
        registry.register(new DefaultConfigurationData(path + "deposit/gold", "deposit/vanilla/gold", "gold_nether"));
        registry.register(new DefaultConfigurationData(path + "deposit/gold", "deposit/vanilla/gold", "gold_nether_delta"));
        registry.register(new DefaultConfigurationData(path + "deposit/diamond", "deposit/vanilla/diamond", "diamond"));
        registry.register(new DefaultConfigurationData(path + "deposit/diamond", "deposit/vanilla/diamond", "diamond_small"));
        registry.register(new DefaultConfigurationData(path + "deposit/diamond", "deposit/vanilla/diamond", "diamond_medium"));
        registry.register(new DefaultConfigurationData(path + "deposit/diamond", "deposit/vanilla/diamond", "diamond_large"));
        registry.register(new DefaultConfigurationData(path + "deposit/emerald", "deposit/vanilla/emerald", "emerald"));
        registry.register(new DefaultConfigurationData(path + "deposit/lapis", "deposit/vanilla/lapis", "lapis_lower"));
        registry.register(new DefaultConfigurationData(path + "deposit/lapis", "deposit/vanilla/lapis", "lapis_normal"));
        registry.register(new DefaultConfigurationData(path + "deposit/redstone", "deposit/vanilla/redstone", "redstone_lower"));
        registry.register(new DefaultConfigurationData(path + "deposit/redstone", "deposit/vanilla/redstone", "redstone_normal"));
        registry.register(new DefaultConfigurationData(path + "deposit/quartz", "deposit/vanilla/quartz", "quartz"));
        registry.register(new DefaultConfigurationData(path + "deposit/quartz", "deposit/vanilla/quartz", "quartz_delta"));
    }
}