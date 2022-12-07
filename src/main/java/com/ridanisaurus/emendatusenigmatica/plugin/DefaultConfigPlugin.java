package com.ridanisaurus.emendatusenigmatica.plugin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.api.IEmendatusPlugin;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.CompatModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.util.FileHelper;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;

//This plugin will be always first
@EmendatusPluginReference(modid = Reference.MOD_ID, name = "config")
public class DefaultConfigPlugin implements IEmendatusPlugin {
    @Override
    public void onLoad(EmendatusDataRegistry registry) {
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
}
