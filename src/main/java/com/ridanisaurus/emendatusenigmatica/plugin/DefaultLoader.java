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

import com.google.common.base.Stopwatch;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.plugin.deposit.DepositType;
import com.ridanisaurus.emendatusenigmatica.plugin.deposit.DepositValidationManager;
import com.ridanisaurus.emendatusenigmatica.plugin.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.plugin.deposit.processors.*;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.compat.CompatModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;
import com.ridanisaurus.emendatusenigmatica.util.FileHelper;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;

public class DefaultLoader {
    public static final List<String> MATERIAL_IDS = new ArrayList<>();
    public static final List<String> STRATA_IDS = new ArrayList<>();
    public static final Map<String, Function<JsonObject, IDepositProcessor>> DEPOSIT_PROCESSORS = new HashMap<>();
    public static final List<String> DEPOSIT_TYPES = new ArrayList<>();
    public static final List<IDepositProcessor> ACTIVE_PROCESSORS = new ArrayList<>();
    public static final List<String> DEPOSIT_IDS = new ArrayList<>();

    protected static void load(EmendatusDataRegistry registry) {
        // Analytics.
        Stopwatch s = Stopwatch.createStarted();

        // Set the path to the defined folder
        Path configDir = Analytics.CONFIG_DIR;

        // Check if the folder exists
        if (!configDir.toFile().exists() && configDir.toFile().mkdirs()) EmendatusEnigmatica.logger.info("Created /config/emendatusenigmatica/");

        File strataDir = configDir.resolve("strata/").toFile();
        if (!strataDir.exists() && strataDir.mkdirs()) EmendatusEnigmatica.logger.info("Created /config/emendatusenigmatica/strata/");

        File materialDir = configDir.resolve("material/").toFile();
        if (!materialDir.exists() && materialDir.mkdirs()) EmendatusEnigmatica.logger.info("Created /config/emendatusenigmatica/material/");

        File compatDir = configDir.resolve("compat/").toFile();
        if (!compatDir.exists() && compatDir.mkdirs()) EmendatusEnigmatica.logger.info("Created /config/emendatusenigmatica/compat/");

        File depositDir = configDir.resolve("deposit/").toFile();
        if (!depositDir.exists() && depositDir.mkdirs()) EmendatusEnigmatica.logger.info("Created /config/emendatusenigmatica/deposit/");

        Map<Path, JsonObject> strataDefinition = FileHelper.loadJsonsWithPaths(strataDir.toPath());
        Map<Path, JsonObject> materialDefinition = FileHelper.loadJsonsWithPaths(materialDir.toPath());
        Map<Path, JsonObject> compatDefinition = FileHelper.loadJsonsWithPaths(compatDir.toPath());
        Map<Path, JsonObject> depositJsonDefinitionsMap = FileHelper.loadJsonsWithPaths(depositDir.toPath());

        Analytics.addPerformanceAnalytic("Loading and parsing JSON Files", s);

        // This in theory was meant to be later on reworked to be parsed in parallel - which is an issue if we read registry in the validators.
        // For now, a lot of stuff isn't thread-safe to make this work in the parallel. We leave this for the future Kanz / Maintainer to deal with!
        registerStrata(strataDefinition, registry);
        registerMaterials(materialDefinition, registry);
        registerCompat(compatDefinition, registry);
        registerDeposits(depositJsonDefinitionsMap, registry);
    }

    private static void registerStrata(@NotNull Map<Path, JsonObject> definitions, EmendatusDataRegistry registry) {
        Stopwatch s = Stopwatch.createStarted();
        definitions.forEach((path, object) -> {
            if (!StrataModel.VALIDATION_MANAGER.validate(object, path)) return;

            Optional<Pair<StrataModel, JsonElement>> result = JsonOps.INSTANCE.withDecoder(StrataModel.CODEC).apply(object).result();
            if (result.isEmpty()) return;

            StrataModel strataModel = result.get().getFirst();
            registry.registerStrata(strataModel);
            STRATA_IDS.add(strataModel.getId());
        });
        Analytics.addPerformanceAnalytic("Validation: Strata", s);
    }

    private static void registerMaterials(@NotNull Map<Path, JsonObject> definitions, EmendatusDataRegistry registry) {
        Stopwatch s = Stopwatch.createStarted();
        definitions.forEach((path, object) -> {
            if (!MaterialModel.VALIDATION_MANAGER.validate(object, path)) return;

            Optional<Pair<MaterialModel, JsonElement>> result = JsonOps.INSTANCE.withDecoder(MaterialModel.CODEC).apply(object).result();
            if (result.isEmpty()) return;

            MaterialModel materialModel = result.get().getFirst();
            registry.getMaterialOrRegister(materialModel.getId(), materialModel);
            MATERIAL_IDS.add(materialModel.getId());
        });
        Analytics.addPerformanceAnalytic("Validation: Material", s);
    }

    private static void registerCompat(@NotNull Map<Path, JsonObject> definitions, EmendatusDataRegistry registry) {
        Stopwatch s = Stopwatch.createStarted();
        definitions.forEach((path, object) -> {
            if (!CompatModel.VALIDATION_MANAGER.validate(object, path)) return;

            Optional<Pair<CompatModel, JsonElement>> result = JsonOps.INSTANCE.withDecoder(CompatModel.CODEC).apply(object).result();
            if (result.isEmpty()) return;

            CompatModel compatModel = result.get().getFirst();
            registry.registerCompat(compatModel);
        });
        Analytics.addPerformanceAnalytic("Validation: Compat", s);
    }

    private static void registerDeposits(@NotNull Map<Path, JsonObject> definitions, EmendatusDataRegistry registry) {
        Stopwatch s = Stopwatch.createStarted();

        if (DEPOSIT_PROCESSORS.isEmpty()) initProcessors();
        if (DEPOSIT_TYPES.size() != DEPOSIT_PROCESSORS.size()) {
            DEPOSIT_TYPES.clear();
            DEPOSIT_TYPES.addAll(DEPOSIT_PROCESSORS.keySet());
        }

        definitions.forEach((path, object) -> {
            if (!DepositValidationManager.VALIDATION_MANAGER.validate(object, path)) return;

            ACTIVE_PROCESSORS.add(DEPOSIT_PROCESSORS.get(object.get("type").getAsString()).apply(object));
            DEPOSIT_IDS.add(object.get("registryName").getAsString());
        });

        ACTIVE_PROCESSORS.forEach(IDepositProcessor::load);

        Analytics.addPerformanceAnalytic("Validation: Deposits", s);
    }

    private static void initProcessors() {
        DEPOSIT_PROCESSORS.put(DepositType.VANILLA.getType(), VanillaDepositProcessor::new);
        DEPOSIT_PROCESSORS.put(DepositType.SPHERE.getType(), SphereDepositProcessor::new);
        DEPOSIT_PROCESSORS.put(DepositType.GEODE.getType(), GeodeDepositProcessor::new);
        DEPOSIT_PROCESSORS.put(DepositType.DIKE.getType(), DikeDepositProcessor::new);
        DEPOSIT_PROCESSORS.put(DepositType.DENSE.getType(), DenseDepositProcessor::new);
    }
}
