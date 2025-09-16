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
import com.ridanisaurus.emendatusenigmatica.api.BasicEmendatusPlugin;
import com.ridanisaurus.emendatusenigmatica.api.config.ConfigCreationContext;
import com.ridanisaurus.emendatusenigmatica.api.config.DCCreationContext;
import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.api.config.DCDataBuilder;
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

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.CompletableFuture;

//This plugin will be always first
@EmendatusPluginReference(modId = Reference.MOD_ID, name = "vanilla-plugin")
public class VanillaPlugin extends BasicEmendatusPlugin {
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
//            Path compatDir = Analytics.CONFIG_DIR.resolve("compat/");
//            if (Files.notExists(compatDir)) {
//                Files.createDirectories(compatDir);
//                EmendatusEnigmatica.logger.info("Created /config/emendatusenigmatica/compat/");
//            }
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
        ModelLoader.load(registry);
    }

    @Override
    public void registerMinecraft(EmendatusDataRegistry registry) {
        for (MaterialModel material : registry.getMaterials()) {
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

            for (StrataModel strata : registry.getStrata()) {
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

    // Even tho it's shipped with EE by default, and could be put into EEConfig class
    // We are doing it here for consistency.
    @Override
    public void extendConfig(ConfigCreationContext ctx) {
        if (!ctx.isStartup()) return;
        NeoFeatureGen.setupConfig(ctx);
    }

    @Override
    public void registerDynamicDataGen(DataGenerator generator, CompletableFuture<HolderLookup.Provider> providers, EmendatusDataRegistry registry) {
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
        generator.addProvider(true, new NeoFeatureGen(generator, providers));
        generator.addProvider(true, new OreFeatureGen(generator, providers));
        generator.addProvider(true, new BiomeTagsGen(generator));
    }

    @Override
    public void provideDefaultConfiguration(DCCreationContext ctx) {
        //TODO: Add to the defaults replacements for the textures,
        // as we don't actually replace vanilla items by default (causes issues).

        // We don't need to check for compatibility while adding vanilla materials, no other addon should add those, and we will be executed first!
        // Materials
        getBuilder("material/coal", "vanilla").markAsMaterial().finish(ctx);
        getBuilder("material/copper", "vanilla").markAsMaterial().finish(ctx);
        getBuilder("material/iron", "vanilla").markAsMaterial().finish(ctx);
        getBuilder("material/gold", "vanilla").markAsMaterial().finish(ctx);
        getBuilder("material/diamond", "vanilla").markAsMaterial().finish(ctx);
        getBuilder("material/emerald", "vanilla").markAsMaterial().finish(ctx);
        getBuilder("material/lapis", "vanilla").markAsMaterial().finish(ctx);
        getBuilder("material/redstone", "vanilla").markAsMaterial().finish(ctx);
        getBuilder("material/quartz", "vanilla").markAsMaterial().finish(ctx);
//        getBuilder("material/netherite", "vanilla").markAsMaterial().finish(ctx);

        // Strata
        getBuilder("strata/stone", "vanilla").markAsStrata().finish(ctx);
        getBuilder("strata/deepslate", "vanilla").markAsStrata().finish(ctx);
        getBuilder("strata/netherrack", "vanilla").markAsStrata().finish(ctx);

        // Deposits
        getBuilder("deposit/coal/coal_lower", "vanilla/coal").markAsDeposit().finish(ctx);
        getBuilder("deposit/coal/coal_upper", "vanilla/coal").markAsDeposit().finish(ctx);

        getBuilder("deposit/copper/copper_normal", "vanilla/copper").markAsDeposit().finish(ctx);
        getBuilder("deposit/copper/copper_large", "vanilla/copper").markAsDeposit().finish(ctx);

        getBuilder("deposit/iron/iron_small", "vanilla/iron").markAsDeposit().finish(ctx);
        getBuilder("deposit/iron/iron_middle", "vanilla/iron").markAsDeposit().finish(ctx);
        getBuilder("deposit/iron/iron_upper", "vanilla/iron").markAsDeposit().finish(ctx);

        getBuilder("deposit/gold/gold_extra", "vanilla/gold").markAsDeposit().finish(ctx);
        getBuilder("deposit/gold/gold_lower", "vanilla/gold").markAsDeposit().finish(ctx);
        getBuilder("deposit/gold/gold_normal", "vanilla/gold").markAsDeposit().finish(ctx);
        getBuilder("deposit/gold/gold_nether", "vanilla/gold").markAsDeposit().finish(ctx);
        getBuilder("deposit/gold/gold_nether_delta", "vanilla/gold").markAsDeposit().finish(ctx);

        getBuilder("deposit/diamond/diamond", "vanilla/diamond").markAsDeposit().finish(ctx);
        getBuilder("deposit/diamond/diamond_small", "vanilla/diamond").markAsDeposit().finish(ctx);
        getBuilder("deposit/diamond/diamond_medium", "vanilla/diamond").markAsDeposit().finish(ctx);
        getBuilder("deposit/diamond/diamond_large", "vanilla/diamond").markAsDeposit().finish(ctx);

        getBuilder("deposit/emerald/emerald", "vanilla/emerald").markAsDeposit().finish(ctx);

        getBuilder("deposit/lapis/lapis_lower", "vanilla/lapis").markAsDeposit().finish(ctx);
        getBuilder("deposit/lapis/lapis_normal", "vanilla/lapis").markAsDeposit().finish(ctx);

        getBuilder("deposit/redstone/redstone_lower", "vanilla/redstone").markAsDeposit().finish(ctx);
        getBuilder("deposit/redstone/redstone_normal", "vanilla/redstone").markAsDeposit().finish(ctx);

        getBuilder("deposit/quartz/quartz", "vanilla/quartz").markAsDeposit().finish(ctx);
        getBuilder("deposit/quartz/quartz_delta", "vanilla/quartz").markAsDeposit().finish(ctx);
    }

    private DCDataBuilder getBuilder(String internal, String external) {
        return DCDataBuilder.fromInternalFile("assets/%s/configs/%s".formatted(Reference.MOD_ID, internal), external);
    }
}