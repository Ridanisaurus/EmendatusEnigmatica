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

package com.ridanisaurus.emendatusenigmatica.config;

import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;


public class EEConfig {
	public static ClientConfig client;
	private static ModConfigSpec clientSpec;
	public static StartupConfig startup;
	private static ModConfigSpec startupSpec;

	public static void registerClient(@NotNull ModContainer container) {
		Pair<ClientConfig, ModConfigSpec> clientSpecPair = new ModConfigSpec.Builder().configure(ClientConfig::new);
		client = clientSpecPair.getLeft();
		clientSpec = clientSpecPair.getRight();
		container.registerConfig(ModConfig.Type.CLIENT, clientSpec);
		EmendatusEnigmatica.logger.info("Emendatus Enigmatica Client Config has been registered.");
	}

	public static void setupStartup(@NotNull ModContainer container) {
		Pair<StartupConfig, ModConfigSpec> startupSpecPair = new ModConfigSpec.Builder().configure(StartupConfig::new);
		startup = startupSpecPair.getLeft();
		startupSpec = startupSpecPair.getRight();
		container.registerConfig(ModConfig.Type.STARTUP, startupSpec);
		EmendatusEnigmatica.logger.info("Emendatus Enigmatica Startup Config has been registered.");
	}

	public static class StartupConfig {
		public final ModConfigSpec.BooleanValue generateSummary;
		public final ModConfigSpec.BooleanValue skipEmptyJsons;
		StartupConfig(ModConfigSpec.@NotNull Builder builder) {
			builder.push("Debug");
			generateSummary = builder
				.comment("Whether Emendatus Enigmatica should generate a Validation Summary in the configuration directory.")
				.translation(Reference.MOD_ID + ".config.startup.generate_summary")
				.define("generateSummary", true);
			skipEmptyJsons = builder
				.comment("Whether Emendatus Enigmatica should silently skip empty JSON files (Either 0 Bytes or empty root object) instead of including them on the summary.")
				.translation(Reference.MOD_ID + ".config.startup.skip_empty")
				.define("skipEmptyJsons", false);
			builder.pop();
		}
	}

	public static class ClientConfig {
		public final ModConfigSpec.BooleanValue showPatreonReward;
		public final ModConfigSpec.BooleanValue oldSchoolGlint;
		ClientConfig(ModConfigSpec.@NotNull Builder builder) {
			builder.push("Patreon Reward");
			showPatreonReward = builder
					.comment("Whether the Patreon Reward should appear floating over the player's head")
					.translation(Reference.MOD_ID + ".config.client.show_reward")
					.define("showReward", true);
			builder.pop();
			builder.push("Rendering");
			oldSchoolGlint = builder
				.comment("Allows for bringing back the old strength of glint for the Emendatus Enigmatica armor.\nNote that this option doesn't affect vanilla armor rendering!")
				.translation(Reference.MOD_ID + ".config.client.old_glint")
				.define("oldSchoolGlint", false);
			builder.pop();
		}
	}
}