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

package com.ridanisaurus.emendatusenigmatica.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.config.EEConfig;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationHelper;
import com.ridanisaurus.emendatusenigmatica.util.summary.SummaryHandler;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class FileHelper {

	/**
	 * Used to get only values from the files in specified directory.
	 * @param dir File representing the directory to load from. Subdirectories will also be loaded.
	 * @return ArrayList with JsonObjects parsed from found .json files.
	 * @apiNote It is recommended to use {@link FileHelper#loadJsons(Path)} instead.
	 */
	@Contract("_ -> new")
	@Deprecated(since = "2.2.0-Alpha-4", forRemoval = true)
	public static @NotNull ArrayList<JsonObject> loadFilesAsJsonObjects(@NotNull File dir) {
		return loadJsons(Path.of(dir.toURI()));
	}

	/**
	 * Used to get only values from the files in specified directory.
	 * @param dir Path to the directory to load. Subdirectories will also be loaded.
	 * @return ArrayList with JsonObjects parsed from found .json files.
	 */
	@Contract("_ -> new")
	public static @NotNull ArrayList<JsonObject> loadJsons(Path dir) {
		return new ArrayList<>(loadJsonsWithPaths(dir).values());
	}

	/**
	 * Used to get a map of Paths and values from the files in the specified directory.
	 * @param dir Path to the directory to load. Subdirectories will also be loaded.
	 * @return Map with Path -> JsonObject from the provided path.
	 * @apiNote Will generate errors to {@link SummaryHandler} on exceptions.
	 */
	public static @NotNull Map<Path, JsonObject> loadJsonsWithPaths(Path dir) {
		Map<Path, JsonObject> results = new HashMap<>();
		dir = dir.toAbsolutePath();
		if (Files.notExists(dir) || !Files.isDirectory(dir)) {
			SummaryHandler.error(
				"Provided path doesn't exist!",
				"Tried loading JSON files from non-existing directory. This is most likely a bug in the mod or one of the addons.",
				"None",
				ValidationHelper.obfuscatePath(dir)
			);
			return results;
		}

		try (Stream<Path> files = Files.list(dir)) {
			files.forEach(file -> {
				try {
					if (Files.isDirectory(file)) {
						results.putAll(loadJsonsWithPaths(file));
						return;
					}
					if (EEConfig.startup.skipEmptyJsons.get() && Files.size(file) == 0) return;
					if (file.getFileName().toString().endsWith(".json")) results.put(file, JsonParser.parseReader(Files.newBufferedReader(file)).getAsJsonObject());
				} catch (Exception e) {
					SummaryHandler.error(
						"Failed parsing JSON file!",
						ExceptionHelper.getAsString(e),
						"None",
						ValidationHelper.obfuscatePath(file)
					);
					// Log additionally full exception.
					EmendatusEnigmatica.logger.debug("Failed parsing JSON file at {}.", file.toAbsolutePath(), e);
				}
			});
		} catch (Exception ex) {
			SummaryHandler.error(
				"Failed reading directory containing JSON files!",
				ExceptionHelper.getAsString(ex),
				"None",
				ValidationHelper.obfuscatePath(dir)
			);
			// Log additionally full exception.
			EmendatusEnigmatica.logger.error("Failed reading directory ({}) containing JSON files.", dir, ex);
		}
		return results;
	}
}
