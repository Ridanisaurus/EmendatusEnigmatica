package com.ridanisaurus.emendatusenigmatica.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileIOHelper {
	public static ArrayList<JsonObject> loadFilesAsJsonObjects(File dir) {
		ArrayList<JsonObject> results = new ArrayList<>();
		File[] files = dir.listFiles((FileFilter) FileFilterUtils.suffixFileFilter(".json"));

		if (files == null || files.length <= 0) {
			return new ArrayList<>();
		}
		for (File file : files) {
			JsonObject resultEntry;
			FileReader reader = null;
			try {
				JsonParser parser = new JsonParser();
				reader = new FileReader(file);
				resultEntry = parser.parse(reader).getAsJsonObject();
				results.add(resultEntry);
			} catch (Exception e) {
				EmendatusEnigmatica.LOGGER.error("Failed to load configuration from " + dir.toString() + " in file " + file.getName());
			} finally {
				IOUtils.closeQuietly(reader);
			}
		}
		return results;
	}
}
