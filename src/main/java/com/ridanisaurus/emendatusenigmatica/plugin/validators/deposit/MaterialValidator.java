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

package com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit;

import com.google.gson.JsonElement;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationData;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationHelper;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.IValidationFunction;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.ResourceLocationValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.RequiredValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.registry.BlockRegistryValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.DefaultLoader;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.EERegistryValidator;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * MaterialValidator is a custom validator,
 * that wraps around {@link ResourceLocationValidator} and {@link EERegistryValidator},
 * and is used to validate <code>material</code>, <code>block</code> and <code>tag</code> fields in the Deposit files.<br>
 * It also validates strata-per-material for each material specified in the deposit.
 * @implSpec <code>block</code> and <code>tag</code> fields should be set to {@link RequiredValidator} as optional fields!
 */
public class MaterialValidator implements IValidationFunction {
    private static final IValidationFunction materialValidator = new EERegistryValidator(DefaultLoader.MATERIAL_IDS, EERegistryValidator.REFERENCE, "Material", false);
    private static final IValidationFunction blockValidator = new ResourceLocationValidator(false, new BlockRegistryValidator());
    private static final IValidationFunction tagValidator = new ResourceLocationValidator(false);
    private final boolean includeTag;
    private final boolean includeBlock;

    /**
     * Constructs MaterialValidator, with <code>tag</code> and <code>block</code> validation enabled.
     *
     * @see MaterialValidator Documentation of the validator.
     */
    public MaterialValidator() {
        this(true, true);
    }

    /**
     * Constructs MaterialValidator.
     *
     * @param includeTag Should <code>tag</code> field be validated of the parent object.
     * @param includeBlock Should <code>block</code> field be validated of the parent object.
     * @see MaterialValidator Documentation of the validator.
     */
    public MaterialValidator(boolean includeTag, boolean includeBlock) {
        this.includeBlock = includeBlock;
        this.includeTag = includeTag;
    }

    /**
     * Entry point of the validator.
     *
     * @param data ValidationData record with necessary information to validate the element.
     * @return True if the validation passes, false otherwise.
     */
    @Override
    public Boolean apply(ValidationData data) {
        JsonElement tagElement = data.getParentField("tag");
        JsonElement blockElement = data.getParentField("block");
        String tagPath = data.getParentFieldPath("tag");
        String blockPath = data.getParentFieldPath("block");
        boolean hasMaterial = data.validationElement() != null;
        boolean hasTag = tagElement != null;
        boolean hasBlock = blockElement != null;

        // If we don't validate tag/block, then act as those are "Unknown keys" and ignore them.
        if (!includeTag && hasTag) {
            Analytics.warn("Unknown key!", tagPath, data.jsonFilePath());
            hasTag = false;
        }

        if (!includeBlock && hasBlock) {
            Analytics.warn("Unknown key!", blockPath, data.jsonFilePath());
            hasBlock = false;
        }

        if (
            (hasMaterial && hasTag) ||
            (hasMaterial && hasBlock) ||
            (hasTag && hasBlock)
        ) {
//            String msg = "Other field with the same purpose is already present!";
//            String additional = """
//                Only one of the fields below can be present at the same time!
//                \t- <code>%s</code>
//                \t- <code>%s</code>
//                \t- <code>%s</code>""".formatted(data.currentPath(), tagPath, blockPath);
//            if (hasMaterial) Analytics.error(msg, additional, data);
//            if (hasTag) Analytics.error(msg, additional, tagPath, data.jsonFilePath());
//            if (hasBlock) Analytics.error(msg, additional, blockPath, data.jsonFilePath());
            Analytics.error(
                "Multiple fields with the same effect found!",
                """
                    Only one of the fields below can be present at the same time!
                    \t- <code>%s</code>%s%s"""
                    .formatted(data.currentPath(), includeBlock? "\n\t- <code>%s</code>".formatted(blockPath): "", includeTag? "\n\t- <code>%s</code>".formatted(tagPath): ""),
                data.getParentPath(), data.jsonFilePath()
            );
            return false;
        }

        if (hasBlock) return blockValidator.apply(new ValidationData(blockElement, data.rootObject(), blockPath, data.jsonFilePath(), data.arrayPolicy()));
        if (hasTag) return tagValidator.apply(new ValidationData(tagElement, data.rootObject(), tagPath, data.jsonFilePath(), data.arrayPolicy()));

        if (hasMaterial) {
            if (materialValidator.apply(data)) {
                String id = data.validationElement().getAsString();
                MaterialModel model = Objects.requireNonNull(EmendatusEnigmatica.getInstance().getDataRegistry().getMaterial(id));
                if (!model.getProcessedTypes().contains("ore")) {
                    Analytics.error(
                        "This material can't be used for ore generation!",
                        "Material <code>%s</code> is missing an <code>ore</code> processed type, which is required for use in the deposits.".formatted(id),
                        data
                    );
                    return false;
                }
                // If no strata are specified in the model,
                // all strata are valid, no need to check if combos are valid.
                if (model.getStrata().isEmpty()) return true;

                var fillerTypes = ValidationHelper.getElementFromPath(data.rootObject(), "root.config.fillerTypes");
                if (fillerTypes == null || !fillerTypes.isJsonArray()) return false;

                List<String> missingStratas = new ArrayList<>();
                for (JsonElement entry : fillerTypes.getAsJsonArray()) {
                    if (!entry.isJsonPrimitive() || !entry.getAsJsonPrimitive().isString()) return false;
                    String strata = entry.getAsString();
                    if (!model.getStrata().contains(strata)) missingStratas.add(strata);
                }
                if (missingStratas.isEmpty()) return true;
                Analytics.error(
                    "Missing Per-Material strata!",
                    """
                    Material <code>%s</code> is missing strata for ids: <code>%s</code>, which makes it illegal for this deposit.<br>
                    Consider adding specified IDs to the <code>%s</code> material, or removing them from <code>root.config.fillerTypes</code> array.
                    """.formatted(id, String.join(", ", missingStratas), id),
                    data
                );
                return false;
            }
        }

        Analytics.error(
            "Missing required fields!",
            """
                One of the fields below is required to be present in this object.
                \t- <code>%s</code>
                \t- <code>%s</code>
                \t- <code>%s</code>""".formatted(data.currentPath(), tagPath, blockPath),
            data.getParentPath(), data.jsonFilePath()
        );
        return false;
    }
}
