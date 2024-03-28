package com.ridanisaurus.emendatusenigmatica.util;

import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
	public static ArrayList<JsonObject> loadFilesAsJsonObjects(File dir) {
		return loadJsons(Path.of(dir.toURI()));
	}

	/**
	 * Used to get only values from the files in specified directory.
	 * @param dir Path to the directory to load. Subdirectories will also be loaded.
	 * @return ArrayList with JsonObjects parsed from found .json files.
	 */
	public static ArrayList<JsonObject> loadJsons(Path dir) {
		return new ArrayList<>(loadJsonsWithPaths(dir).values());
	}

	/**
	 * Used to get a map of Paths and values from the files in the specified directory.
	 * @param dir Path to the directory to load. Subdirectories will also be loaded.
	 * @return Map with Path -> JsonObject from the provided path.
	 */
	public static Map<Path, JsonObject> loadJsonsWithPaths(Path dir) {
		Map<Path, JsonObject> results = new HashMap<>();
		dir = dir.toAbsolutePath();
		if (Files.notExists(dir) || !Files.isDirectory(dir)) {
			EmendatusEnigmatica.LOGGER.error("Provided path to load jsons from (" + dir + ") doesn't exist.");
			return results;
		}

		try (Stream<Path> files = Files.list(dir)) {
			files.forEach(file -> {
				try {
					if (Files.isDirectory(file)) {
						results.putAll(loadJsonsWithPaths(file));
						return;
					}
					if (file.getFileName().toString().endsWith(".json")) results.put(file, JsonParser.parseReader(Files.newBufferedReader(file)).getAsJsonObject());
				} catch (Exception e) {
					EmendatusEnigmatica.LOGGER.error("Failed parsing json file at " +file.toAbsolutePath() + ".", e);
				}
			});
		} catch (Exception ex) {
			EmendatusEnigmatica.LOGGER.error("Failed opening provided path (" + dir + ") from which json files were meant to be loaded.", ex);
		}
		return results;
	}
}
