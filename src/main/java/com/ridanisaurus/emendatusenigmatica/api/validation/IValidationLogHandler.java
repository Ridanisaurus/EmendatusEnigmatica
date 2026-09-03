package com.ridanisaurus.emendatusenigmatica.api.validation;

import com.ridanisaurus.emendatusenigmatica.util.summary.SummaryHandler;
import org.jetbrains.annotations.Nullable;

public interface IValidationLogHandler {
    /**
     * Used to add warn messages for the current file.
     * @param msg Message to add
     * @param additional Additional details to be printed after "message". This gets written directly into the file!
     * @param elementPath Path to the element in question.
     * @param jsonPath Path to the JSON file.
     */
    default void warn(String msg, String additional, String elementPath, String jsonPath) {
        SummaryHandler.warn(msg, additional, elementPath, jsonPath);
    }

    /**
     * Used to add error messages for the current file.
     * @param msg Message to add.
     * @param additional Additional details to be printed after "cause". This gets written directly into the file!
     * @param elementPath Path to the element in question.
     * @param jsonPath Obfuscated path to the JSON file.
     */
    default void error(String msg, @Nullable String additional, String elementPath, String jsonPath) {
        SummaryHandler.error(msg, additional, elementPath, jsonPath);
    }
}
