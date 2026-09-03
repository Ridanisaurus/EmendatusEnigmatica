package com.ridanisaurus.emendatusenigmatica.api.config;

import com.ridanisaurus.emendatusenigmatica.api.config.exceptions.DCValidationException;
import com.ridanisaurus.emendatusenigmatica.util.summary.SummaryHandler;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

/**
 * DCType determines the type and handles the most basic validation of the provided configurations.
 */
public abstract class AbstractDCType {
    private final Path basePath;

    /**
     * Constructs DCType with provided string as a base path from the EE Config directory.
     * @param basePath Path to the folder containing files of this type.
     * @throws IllegalArgumentException if resulting path points outside the EE Config Directory.
     */
    public AbstractDCType(String basePath) {
        this.basePath = SummaryHandler.CONFIG_DIR.resolve(Objects.requireNonNull(basePath)).toAbsolutePath().normalize();
        if (!this.basePath.startsWith(SummaryHandler.CONFIG_DIR.toAbsolutePath())) throw new IllegalArgumentException("Provided path goes outside EmendatusEnigmatica config directory.");
    }

    /**
     * @return Absolute path of this type's config folder.
     */
    public Path getBasePath() {
        return this.basePath;
    }

    /**
     * Determines if provided data is unique.
     * @param data Checked DCData
     * @param registeredData Currently registered configuration under this type.
     * @return True if unique, false otherwise
     * @implSpec It is required that this check guarantees no ID Conflicts occur on the validation step of this type.
     */
    public abstract boolean isUnique(DCData data, List<DCData> registeredData);

    /**
     * Determines if the provided data is valid.
     * @param data Data to validate.
     * @throws DCValidationException when validation fails.
     * @implSpec This method should mostly do basic validation, for {@link AbstractDCType#isUnique(DCData, List)} checks to work.
     * Proper validation will be handled later on by the Validation system.
     */
    public abstract void validate(DCData data) throws DCValidationException;
}
