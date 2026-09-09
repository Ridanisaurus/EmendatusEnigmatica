/*
 * MIT License
 *
 * Copyright (c) 2024-2026. Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.plugin.validators.material.oredrop;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationContext;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationHelper;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.ResourceLocationValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.registry.ItemRegistryValidator;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class DropValidator extends ResourceLocationValidator {
    /**
     * Constructs ResourceLocationValidator.
     *
     * @see ResourceLocationValidator Documentation of the validator.
     */
    public DropValidator() {
        super(false, new ItemRegistryValidator());
    }

    /**
     * Method used to determine if the validator is required on runtime.
     *
     * @param ctx ValidationContext record with necessary information to validate the element.
     * @return True if current element is required, false if not.
     * @implNote By default, returns the value specified in the constructor.
     */
    @Override
    public boolean isRequired(@NotNull ValidationContext ctx) {
        JsonElement types = ValidationHelper.getElementFromPath(ctx.rootObject(), "root.processedTypes");
        boolean hasOre = false;
        boolean hasGem = false;
        boolean hasRaw = false;
        boolean hasVal = Objects.nonNull(ctx.validationElement());
        if (Objects.nonNull(types) && types.isJsonArray()) {
            var array = types.getAsJsonArray();
            hasOre = array.contains(new JsonPrimitive("ore"));
            hasGem = array.contains(new JsonPrimitive("gem"));
            hasRaw = array.contains(new JsonPrimitive("raw"));
        }

        return !hasVal && !hasGem && !hasRaw && hasOre;
    }

    /**
     * Method used to provide additional field for the "This field is required!" error.
     *
     * @return String with an additional message or null.
     */
    @Override
    public String getAdditional(@NotNull ValidationContext ctx) {
        return "Array <code>root.processedTypes</code> contains an element <code>ore</code> and misses <code>gem, raw</code>, which makes this field necessary.";
    }
}
