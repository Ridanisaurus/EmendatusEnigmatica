package com.ridanisaurus.emendatusenigmatica.loader.deposit;

import com.google.gson.JsonObject;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors.GeodeDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors.SphereDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.processsors.VanillaDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.util.FileIOHelper;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.RegistryObject;

import java.io.File;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;

public class EEDeposits {
	public static final Map<String, Function<JsonObject, IDepositProcessor>> DEPOSIT_PROCESSORS = new HashMap<>();
	public static final List<IDepositProcessor> ACTIVE_PROCESSORS = new ArrayList<>();

	public static void initProcessors() {
		DEPOSIT_PROCESSORS.put("emendatusenigmatica:vanilla_deposit", VanillaDepositProcessor::new);
		DEPOSIT_PROCESSORS.put("emendatusenigmatica:sphere_deposit", SphereDepositProcessor::new);
		DEPOSIT_PROCESSORS.put("emendatusenigmatica:geode_deposit", GeodeDepositProcessor::new);
	}

	public static void load() {
		if (DEPOSIT_PROCESSORS.isEmpty()) {
			initProcessors();
		}

		Path configDir = FMLPaths.CONFIGDIR.get().resolve("emendatusenigmatica/");

		// Check if the folder exists
		if (!configDir.toFile().exists() && configDir.toFile().mkdirs()) {
			EmendatusEnigmatica.LOGGER.info("Created /config/emendatusenigmatica/");
		}

		File depositDir = configDir.resolve("deposit/").toFile();
		if (!depositDir.exists() && depositDir.mkdirs()) {
			EmendatusEnigmatica.LOGGER.info("Created /config/emendatusenigmatica/deposit/");
		}

		ArrayList<JsonObject> depositJsonDefinitions = FileIOHelper.loadFilesAsJsonObjects(depositDir);

		for (JsonObject depositJsonDefinition : depositJsonDefinitions) {
			if (!depositJsonDefinition.has("type")) {
				continue;
			}
			String type = depositJsonDefinition.get("type").getAsString();
			Function<JsonObject, IDepositProcessor> processor = DEPOSIT_PROCESSORS.getOrDefault(type, null);
			if (processor == null) {
				continue;
			}

			ACTIVE_PROCESSORS.add(processor.apply(depositJsonDefinition));
		}

		for (IDepositProcessor activeProcessor : ACTIVE_PROCESSORS) {
			activeProcessor.load();
		}
	}

	public static void setup() {
		for (IDepositProcessor activeProcessor : ACTIVE_PROCESSORS) {
			activeProcessor.setup();
		}
	}
}
