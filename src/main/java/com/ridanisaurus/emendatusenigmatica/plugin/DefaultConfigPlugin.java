package com.ridanisaurus.emendatusenigmatica.plugin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.api.IEmendatusPlugin;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.datagen.*;
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
import java.util.Optional;

//This plugin will be always first
@EmendatusPluginReference(modid = Reference.MOD_ID, name = "config")
public class DefaultConfigPlugin implements IEmendatusPlugin {
    public static final List<MaterialModel> MATERIALS = new ArrayList<>();

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

        ArrayList<JsonObject> strataDefinition = FileHelper.loadFilesAsJsonObjects(strataDir);
        ArrayList<JsonObject> materialDefinition = FileHelper.loadFilesAsJsonObjects(materialDir);
        ArrayList<JsonObject> compatDefinition = FileHelper.loadFilesAsJsonObjects(compatDir);

        for (JsonObject jsonObject : strataDefinition) {
            Optional<Pair<StrataModel, JsonElement>> result = JsonOps.INSTANCE.withDecoder(StrataModel.CODEC).apply(jsonObject).result();
            if (!result.isPresent()) {
                continue;
            }
            StrataModel strataModel = result.get().getFirst();
            registry.registerStrata(strataModel);
        }

        for (JsonObject jsonObject : materialDefinition) {
            Optional<Pair<MaterialModel, JsonElement>> result = JsonOps.INSTANCE.withDecoder(MaterialModel.CODEC).apply(jsonObject).result();
            if (!result.isPresent()) {
                continue;
            }
            MaterialModel materialModel = result.get().getFirst();
            registry.getMaterialOrRegister(materialModel.getId(), materialModel);
            MATERIALS.add(materialModel);
        }

        ArrayList<CompatModel> compatModels = new ArrayList<>();
        for (JsonObject jsonObject : compatDefinition) {
            Optional<Pair<CompatModel, JsonElement>> result = JsonOps.INSTANCE.withDecoder(CompatModel.CODEC).apply(jsonObject).result();
            if (!result.isPresent()) {
                continue;
            }
            CompatModel compatModel = result.get().getFirst();
            compatModels.add(compatModel);
            registry.registerCompat(compatModel);
        }
    }

    @Override
    public void registerMinecraft(List<MaterialModel> materialModels, List<StrataModel> strataModels) {
        for (StrataModel strata : strataModels) {
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