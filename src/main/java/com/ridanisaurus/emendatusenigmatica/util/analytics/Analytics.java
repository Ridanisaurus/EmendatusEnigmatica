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

package com.ridanisaurus.emendatusenigmatica.util.analytics;

import com.google.common.base.Stopwatch;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.config.EEConfig;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationData;
import net.neoforged.fml.loading.FMLPaths;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;

/**
 * A static class, used to gather messages from the validation system and generate a summary.
 * @implSpec Please try to not generate more than a single message for each field. Each message is its own paragraph, with the field path.
 */
public class Analytics {
    /**
     * Used to store messages and necessary data for each file.
     */
    private static final Map<String, Map<String, Messages>> messages = new HashMap<>();

    /**
     * Used to store Categories, which will be used to organize the Validation Summary file.
     */
    private static final Map<String, String> messageCategories = new LinkedHashMap<>();

    /**
     * Used to store Performance Analytics, which will get printed at the end of the Validation Summary file into the table.
     */
    private static final Map<String, String> performanceMap = new LinkedHashMap<>();

    private static final List<Consumer<AnalyticsWriteContext>> addons = new ArrayList<>();

    /**
     * Used to lock the analytics if they were already summarized on this launch of the game.
     */
    private static boolean finalized = false;

    private static String dirSeparator;

    /**
     * Path to the file where all messages will be written to.
     */
    private static Path summaryFile;

    /**
     * Used as a cache of ConfigDir.
     */
    public static Path CONFIG_DIR = null;

    /**
     * Private constructor. This class is static, and no instances of it should be created.
     */
    private Analytics() {}

    /**
     * Used to set up the Analytics paths.
     */
    public static void setup() {
        CONFIG_DIR = FMLPaths.CONFIGDIR.get().resolve("emendatusenigmatica/");
        summaryFile = CONFIG_DIR.resolve("Validation Results.md");
        dirSeparator = FileSystems.getDefault().getSeparator();
    }

    /**
     * Used to get a path to a log file.
     * @return Copy of the Path object, representing the location of the summary file.
     */
    public static Path getSummaryFile() {
        return summaryFile;
    }

    /**
     * Used to check if Analytics were finalized and saved to the file.<br>
     * When {@code true}, any further calls to other methods will result in an exception.
     * @return Status of the analytics.
     */
    public static boolean isFinalized() {
        return finalized;
    }

    /**
     * Used to check if the Analytics are enabled.
     * @return True if analytics summary is going to be generated, false otherwise.
     * @apiNote This is mostly used to skip parts of the validation system, which are only meant to provide additional warnings for the end-user.
     */
    public static boolean isEnabled() {
        return EEConfig.startup.generateSummary.get();
    }

    /**
     * Used to add warn messages for specified file.
     * @param msg Message to add
     * @param data ValidationData, from which all necessary information will be taken.
     * @apiNote The types are determined based on the jsonPath, using format {@code type/folder_if_any/file.json}.
     * @see Analytics#isFinalized()
     */
    public static void warn(String msg, @NotNull ValidationData data) {
        warn(msg, null, data.currentPath(), data.jsonFilePath());
    }

    /**
     * Used to add warn messages for specified file.
     * @param msg Message to add
     * @param additional Additional details to be printed after "message". This gets written directly into the file!
     * @param data ValidationData, from which all necessary information will be taken.
     * @apiNote The types are determined based on the jsonPath, using format {@code type/folder_if_any/file.json}.
     * @see Analytics#isFinalized()
     */
    public static void warn(String msg, String additional, @NotNull ValidationData data) {
        warn(msg, additional, data.currentPath(), data.jsonFilePath());
    }

    /**
     * Used to add warn messages for specified file.
     * @param msg Message to add
     * @param elementPath Path to the element in question.
     * @param jsonPath Path to the json file.
     * @apiNote The types are determined based on the jsonPath, using format {@code type/folder_if_any/file.json}.
     * @see Analytics#isFinalized()
     */
    public static void warn(String msg, String elementPath, String jsonPath) {
        warn(msg, null, elementPath, jsonPath);
    }

    /**
     * Used to add warn messages for specified file.
     * @param msg Message to add
     * @param additional Additional details to be printed after "message". This gets written directly into the file!
     * @param elementPath Path to the element in question.
     * @param jsonPath Path to the json file.
     * @apiNote The types are determined based on the jsonPath, using format {@code type/folder_if_any/file.json}.
     * @see Analytics#isFinalized()
     */
    public static void warn(String msg, String additional, String elementPath, String jsonPath) {
        if (finalized) throw new IllegalStateException("Analytics were already finalized!");
        messages.computeIfAbsent(StringUtils.substringBefore(jsonPath, dirSeparator), it -> new HashMap<>())
            .computeIfAbsent(jsonPath, it -> new Messages(new ArrayList<>(), new ArrayList<>())).warnings().add(new Messages.Message(elementPath, msg, additional));
    }

    /**
     * Used to add error messages for specified file.
     * @param msg Message to add.
     * @param data ValidationData, from which all necessary information will be taken.
     * @apiNote The types are determined based on the jsonPath, using format {@code type/folder_if_any/file.json}.
     * @see Analytics#isFinalized()
     */
    public static void error(String msg, @NotNull ValidationData data) {
        error(msg, null, data.currentPath(), data.jsonFilePath());
    }

    /**
     * Used to add error messages for specified file.
     * @param msg Message to add.
     * @param additional Additional details to be printed after "cause". This gets written directly into the file!
     * @param data ValidationData, from which all necessary information will be taken.
     * @apiNote The types are determined based on the jsonPath, using format {@code type/folder_if_any/file.json}.
     * @see Analytics#isFinalized()
     */
    public static void error(String msg, @Nullable String additional, @NotNull ValidationData data) {
        error(msg, additional, data.currentPath(), data.jsonFilePath());
    }

    /**
     * Used to add error messages for specified file.
     * @param msg Message to add.
     * @param elementPath Path to the element in question.
     * @param jsonPath Obfuscated path to the json file.
     * @apiNote The types are determined based on the jsonPath, using format {@code type/folder_if_any/file.json}.
     * @see Analytics#isFinalized()
     */
    public static void error(String msg, String elementPath, String jsonPath) {
        error(msg, null, elementPath, jsonPath);
    }

    /**
     * Used to add error messages for specified file.
     * @param msg Message to add.
     * @param additional Additional details to be printed after "cause". This gets written directly into the file!
     * @param elementPath Path to the element in question.
     * @param jsonPath Obfuscated path to the json file.
     * @apiNote The types are determined based on the jsonPath, using format {@code type/folder_if_any/file.json}.
     * @see Analytics#isFinalized()
     */
    public static void error(String msg, @Nullable String additional, String elementPath, String jsonPath) {
        if (finalized) throw new IllegalStateException("Analytics were already finalized!");
        messages.computeIfAbsent(StringUtils.substringBefore(jsonPath, dirSeparator), it -> new HashMap<>())
            .computeIfAbsent(jsonPath, it -> new Messages(new ArrayList<>(), new ArrayList<>())).errors().add(new Messages.Message(elementPath, msg, additional));
    }

    /**
     * Used to add a new category to the validation summary file, under which all messages from the provided directory will be printed.
     * <br><br>
     * You can add your own category if your addon provides additional folder for the validation,
     * otherwise the messages from that directory will be printed under {@code Custom Messages} category.
     * @param header The String to print as the Category Name
     * @param directory Directory / Type for messages to print under this category.
     * @apiNote Take a note that categories added by this method, unlike Custom Messages, will appear in the validation summary even if no messages are found for it!
     * @see Analytics#isFinalized()
     */
    public static void addNewCategory(String header, String directory) {
        if (finalized) throw new IllegalStateException("Analytics were already finalized!");
        messageCategories.put(header, directory);
    }

    public static void addPerformanceAnalytic(String category, String time) {
        performanceMap.put(category, time);
    }

    public static void addPerformanceAnalytic(String category, @NotNull Stopwatch s) {
        if (s.isRunning()) s.stop();
        var time = s.elapsed();
        String milis = String.valueOf(time.toMillisPart());
        if (milis.length() < 3) milis = "0".repeat(3 - milis.length()) + milis;
        addPerformanceAnalytic(category, "%d.%ss".formatted(time.getSeconds(), milis));
    }

    public static void registerAddon(Consumer<AnalyticsWriteContext> addon) {
        addons.add(Objects.requireNonNull(addon, "Addon function can't be null!"));
    }

    /**
     * Used to finalize the Validation Analytics and generate a summary file.<br>
     * This method will also lock the analytics instance, and block any further calls to its methods.
     * @see Analytics#isFinalized()
     */
    public static void finalizeAnalytics() {
        if (finalized) throw new IllegalStateException("Analytics were already finalized!");
        AnalyticsWriteContext cx = new AnalyticsWriteContext(summaryFile);
        Stopwatch s = Stopwatch.createStarted();

        try {
            if (Files.exists(summaryFile)) {
                Files.deleteIfExists(summaryFile);
                EmendatusEnigmatica.logger.info("Old Validation Summary file was deleted.");
            }
            // If somehow the config directory doesn't exist.
            Files.createDirectories(summaryFile.getParent());
            Files.createFile(summaryFile);
            cx.writeSpacer();
            cx.writeHeader("Emendatus Enigmatica Validation Results", 1);
            cx.writeLine("Emendatus Enigmatica version: " + EmendatusEnigmatica.VERSION);
            cx.writeLine("File generated at: " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").format(new Date()));
            cx.writeSpacer();

            addNewCategory("Strata", "strata");
            addNewCategory("Materials", "material");
            addNewCategory("Compatibility", "compat");
            addNewCategory("Deposits", "deposit");

            messageCategories.forEach((header, type) -> {
                cx.writeHeader(header, 2);
                printMessages(type, cx);
                messages.remove(type);
            });

            // Print Messages from any custom directories, which don't have their own category, if any.
            if (!messages.isEmpty()) {
                cx.writeHeader("Custom Messages", 2);
                // Clearing already-saved categories was meant to be handled by the printMessages;
                // however, this was causing ConcurrentModificationExceptions
                // when trying to print all messages.
                var it = messages.keySet().iterator();
                while (it.hasNext()) {
                    printMessages(it.next(), cx);
                    it.remove();
                }
            }

            cx.writeHeader("Additional Information", 2);
            executeAddons(cx);
            addPerformanceAnalytic("Generation of Analytics Summary", s);
            printPerformance(cx);
            cx.writeComment("You can disable the generation of this summary and speed up the validation in the configuration file!");

            finalized = true;
        } catch (Exception e) {
            EmendatusEnigmatica.logger.error("Exception caught while summarizing the validation results!", e);
        }
    }

    private static void executeAddons(AnalyticsWriteContext cx) {
        addons.forEach(it -> it.accept(cx));
    }

    private static void printMessages(@NotNull String key, AnalyticsWriteContext cx) {
        messages.computeIfAbsent(key, it -> new HashMap<>()).forEach((file, messages) -> {
            cx.writeHeader("File <code>%s</code>".formatted(file), 3);
            if (!messages.warnings().isEmpty()) {
                cx.writeLine("Warnings:");
                messages.warnings().forEach(warning -> {
                    cx.writeLine("- Element: <code>%s</code>".formatted(warning.element()));
                    cx.writeLine("Message: " + warning.message());
                    if (Objects.nonNull(warning.additionalInfo())) cx.writeLine(warning.additionalInfo());
                    cx.write("\n"); // Additional line to make "space" between list elements.
                });
            }

            if (!messages.errors().isEmpty()) {
                cx.writeLine("Errors:");
                messages.errors().forEach(error -> {
                    cx.writeLine("- Element: <code>%s</code>".formatted(error.element()));
                    cx.writeLine("Cause: " + error.message());
                    if (Objects.nonNull(error.additionalInfo())) cx.writeLine(error.additionalInfo());
                    cx.write("\n"); // Additional line to make "space" between list elements.
                });
            }
        });

        if (messages.get(key).isEmpty()) cx.writeLine("All files were parsed and registered successfully!");
    }

    private static void printPerformance(@NotNull AnalyticsWriteContext cx) {
        cx.writeHeader("Performance", 3);
        StringBuilder table = new StringBuilder();
        table.append("<table>");
        performanceMap.forEach((category, time) -> table
            .append("<tr>")
            .append("<td>")
            .append(category)
            .append("</td>")
            .append("<td>")
            .append(time)
            .append("</td>")
            .append("</tr>"));
        table.append("</table>");
        cx.write(table.toString());
        cx.write("\n");
    }

    /**
     * Setup method that doesn't call NeoForge classes. Shouldn't be used outside of dev env in test classes.
     */
    @ApiStatus.Internal
    public static void devSetup() {
        CONFIG_DIR = Path.of("run/config").resolve("emendatusenigmatica/");
        summaryFile = CONFIG_DIR.resolve("Validation Results.md");
        dirSeparator = FileSystems.getDefault().getSeparator();
    }
}
