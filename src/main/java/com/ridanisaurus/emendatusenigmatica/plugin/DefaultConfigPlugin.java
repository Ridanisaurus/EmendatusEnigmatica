package com.ridanisaurus.emendatusenigmatica.plugin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.api.IEmendatusPlugin;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.config.EEConfig;
import com.ridanisaurus.emendatusenigmatica.datagen.*;
import com.ridanisaurus.emendatusenigmatica.loader.Validator;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.CompatModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.FileHelper;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

//This plugin will be always first
@EmendatusPluginReference(modid = Reference.MOD_ID, name = "config")
public class DefaultConfigPlugin implements IEmendatusPlugin {
    public static final List<MaterialModel> MATERIALS = new ArrayList<>();
    public static final List<String> MATERIAL_IDS = new ArrayList<>();
    public static final List<StrataModel> STRATA = new ArrayList<>();
    public static final List<String> STRATA_IDS = new ArrayList<>();

    @Override
    public void load(EmendatusDataRegistry registry) {
        // Set the path to the defined folder
        Path configDir = FMLPaths.CONFIGDIR.get().resolve("emendatusenigmatica/");

        // Check if the folder exists
        if (!configDir.toFile().exists() && configDir.toFile().mkdirs()) {
            EmendatusEnigmatica.LOGGER.info("Created /config/emendatusenigmatica/");
        }

        File strataDir = configDir.resolve("strata/").toFile();
        if (!strataDir.exists() && strataDir.mkdirs()) {
            EmendatusEnigmatica.LOGGER.info("Created /config/emendatusenigmatica/strata/");
        }

        File materialDir = configDir.resolve("material/").toFile();
        if (!materialDir.exists() && materialDir.mkdirs()) {
            EmendatusEnigmatica.LOGGER.info("Created /config/emendatusenigmatica/material/");
        }

        File compatDir = configDir.resolve("compat/").toFile();
        if (!compatDir.exists() && compatDir.mkdirs()) {
            EmendatusEnigmatica.LOGGER.info("Created /config/emendatusenigmatica/compat/");
        }


        Map<Path, JsonObject> strataDefinition = FileHelper.loadJsonsWithPaths(strataDir.toPath());
        Map<Path, JsonObject> materialDefinition = FileHelper.loadJsonsWithPaths(materialDir.toPath());
        Map<Path, JsonObject> compatDefinition = FileHelper.loadJsonsWithPaths(compatDir.toPath());
        Validator validator = new Validator("Main Validator");
        final boolean log = EEConfig.common.logConfigErrors.get();

        EmendatusEnigmatica.LOGGER.info("Validating and registering data for: Strata");
        strataDefinition.forEach((path, jsonObject) -> {
            if (!validator.validateObject(jsonObject, path, StrataModel.verifiers)) {
                if (log) EmendatusEnigmatica.LOGGER.error("File \"%s\" is not going to be registered due to errors in it's validation.".formatted(path));
                return;
            }

            Optional<Pair<StrataModel, JsonElement>> result = JsonOps.INSTANCE.withDecoder(StrataModel.CODEC).apply(jsonObject).result();
            if (result.isEmpty()) return;

            StrataModel strataModel = result.get().getFirst();
            registry.registerStrata(strataModel);
            STRATA.add(strataModel);
            STRATA_IDS.add(strataModel.getId());
        });

        EmendatusEnigmatica.LOGGER.info("Validating and registering data for: Material");
        materialDefinition.forEach((path, jsonObject) -> {
            if (!validator.validateObject(jsonObject, path, MaterialModel.verifiers)) {
                if (log) EmendatusEnigmatica.LOGGER.error("File \"%s\" is not going to be registered due to errors in it's validation.".formatted(path));
                return;
            }

            Optional<Pair<MaterialModel, JsonElement>> result = JsonOps.INSTANCE.withDecoder(MaterialModel.CODEC).apply(jsonObject).result();
            if (result.isEmpty()) return;

            MaterialModel materialModel = result.get().getFirst();
            registry.getMaterialOrRegister(materialModel.getId(), materialModel);
            MATERIALS.add(materialModel);
            MATERIAL_IDS.add(materialModel.getId());
        });

        EmendatusEnigmatica.LOGGER.info("Validating and registering data for: Compatibility");
        compatDefinition.forEach((path, jsonObject) -> {
            if (!validator.validateObject(jsonObject, path, CompatModel.verifiers)) {
                if (log) EmendatusEnigmatica.LOGGER.error("File \"%s\" is not going to be registered due to errors in it's validation.".formatted(path));
                return;
            }

            Optional<Pair<CompatModel, JsonElement>> result = JsonOps.INSTANCE.withDecoder(CompatModel.CODEC).apply(jsonObject).result();
            if (result.isEmpty()) return;

            CompatModel compatModel = result.get().getFirst();
            registry.registerCompat(compatModel);
        });

        if (log)  {
            EmendatusEnigmatica.LOGGER.info("Finished validation and registration of data files.");
        } else {
            EmendatusEnigmatica.LOGGER.info("Finished registration of data files. Any validation errors that occurred have been hidden due to your current configuration.");
        }
        throw new RuntimeException();
    }

    @Override
    public void registerMinecraft(List<MaterialModel> materialModels, List<StrataModel> strataModels) {
        for (StrataModel strata : strataModels) {
            for (MaterialModel material : materialModels) {
                if (material.getProcessedTypes().contains("ore")) {
                    if (material.getStrata().isEmpty() || material.getStrata().contains(strata.getId())) {
                        EERegistrar.registerOre(strata, material);
                    }
                    if (material.getProcessedTypes().contains("sample")) {
                        if (material.getStrata().isEmpty() || material.getStrata().contains(strata.getId())) {
                            EERegistrar.registerSample(strata, material);
                        }
                    }
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
            if (material.getProcessedTypes().contains("cluster")) {
                EERegistrar.registerSmallBudBlocks(material);
                EERegistrar.registerMediumBudBlocks(material);
                EERegistrar.registerLargeBudBlocks(material);
                EERegistrar.registerClusterBlocks(material);
                EERegistrar.registerBuddingBlocks(material);
                EERegistrar.registerClusterShardBlocks(material);
                EERegistrar.registerClusterShards(material);
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
            if (material.getProcessedTypes().contains("sword")) {
                EERegistrar.registerSwords(material);
            }
            if (material.getProcessedTypes().contains("pickaxe")) {
                EERegistrar.registerPickaxes(material);
            }
            if (material.getProcessedTypes().contains("axe")) {
                EERegistrar.registerAxes(material);
            }
            if (material.getProcessedTypes().contains("shovel")) {
                EERegistrar.registerShovels(material);
            }
            if (material.getProcessedTypes().contains("hoe")) {
                EERegistrar.registerHoes(material);
            }
            if (material.getProcessedTypes().contains("paxel")) {
                EERegistrar.registerPaxels(material);
            }
            if (material.getProcessedTypes().contains("helmet")) {
                EERegistrar.registerHelmets(material);
            }
            if (material.getProcessedTypes().contains("chestplate")) {
                EERegistrar.registerChestplates(material);
            }
            if (material.getProcessedTypes().contains("leggings")) {
                EERegistrar.registerLeggings(material);
            }
            if (material.getProcessedTypes().contains("boots")) {
                EERegistrar.registerBoots(material);
            }
            if (material.getProcessedTypes().contains("shield")) {
                EERegistrar.registerShields(material);
            }
            if (material.getProcessedTypes().contains("fluid")) {
                EERegistrar.registerFluids(material);
            }
//            if (EmendatusEnigmatica.BLOODMAGIC_LOADED) {
//                if (material.getProcessedTypes().contains("fragment")) {
//                    EEBloodMagicRegistrar.registerFragments(material);
//                }
//                if (material.getProcessedTypes().contains("gravel")) {
//                    EEBloodMagicRegistrar.registerGravels(material);
//                }
//            }
        }
    }

    @Override
    public void registerDynamicDataGen(DataGenerator generator, EmendatusDataRegistry registry) {
        generator.addProvider(true, new BlockStatesGen(generator, registry));
        generator.addProvider(true, new BlockModelsGen(generator, registry));
        generator.addProvider(true, new ItemModelsGen(generator, registry));
        generator.addProvider(true, new FluidModelsGen(generator, registry));
        generator.addProvider(true, new LangGen(generator, registry));
//        if (EmendatusEnigmatica.BLOODMAGIC_LOADED) generator.addProvider(true, new BloodMagicDataGen.BloodMagicItemModels(generator, registry));

        generator.addProvider(true, new BlockTagsGen(generator, registry));
        generator.addProvider(true, new ItemTagsGen(generator, registry));
        generator.addProvider(true, new FluidTagsGen(generator, registry));
        generator.addProvider(true, new BlockHarvestTagsGen.BlockHarvestLevelTagsGen(generator, registry));
        generator.addProvider(true, new BlockHarvestTagsGen.BlockHarvestToolTagsGen(generator, registry));
        generator.addProvider(true, new RecipesGen(generator, registry));
        generator.addProvider(true, new LootTablesGen(generator, registry));
        generator.addProvider(true, new OreFeatureDataGen(generator, registry));

//        if (EmendatusEnigmatica.BLOODMAGIC_LOADED) {
//            generator.addProvider(true, new BloodMagicDataGen.BloodMagicItemTags(generator, registry));
//            generator.addProvider(true, new BloodMagicDataGen.BloodMagicRecipes(generator, registry));
//        }
    }

    @Override
    public void finish(EmendatusDataRegistry registry) {

    }
}