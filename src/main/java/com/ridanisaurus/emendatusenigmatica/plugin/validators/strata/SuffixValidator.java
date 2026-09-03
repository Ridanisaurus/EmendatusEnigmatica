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

package com.ridanisaurus.emendatusenigmatica.plugin.validators.strata;

import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationContext;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.TypeValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.ValuesValidator;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

/**
 * Custom implementation of {@link ValuesValidator}, used to validate Suffix Field of the Strata.
 * @apiNote Suffixes are required to be compliant with [a-z0-9/._-] rule of ResourceLocations.
 * @implNote This is a stripped-down copy of {@link com.ridanisaurus.emendatusenigmatica.plugin.validators.EERegistryValidator}
 */
public class SuffixValidator extends TypeValidator {

    /**
     * Constructs SuffixValidator.
     */
    public SuffixValidator() {
        super(Types.STRING, true);
    }

    /**
     * Validate method, used to validate passed in object.
     *
     * @param data ValidationContext record with necessary information to validate the element.
     * @return True of the validation passes, false otherwise.
     * @apiNote Even tho it's public, this method should <i>never</i> be called directly! Call {@link TypeValidator#apply(ValidationContext)} instead!
     */
    @Override
    public Boolean validate(@NotNull ValidationContext data) {
        if (!super.validate(data)) return false;
        String value = data.validationElement().getAsString();
        if (!ResourceLocation.isValidNamespace(value)) {
            Analytics.error("Provided suffix <code>%s</code> contains non [a-z0-9/._-] character!".formatted(value), data);
            return false;
        }
        //TODO: Rework for new deposit system
//        if (!ModelLoader.STRATA_SUFFIXES.contains(value)) return true;
//        String ogId = EmendatusEnigmatica.getInstance()
//            .getDataRegistry().getStrata()
//            .stream().filter(it -> it.getSuffix().equals(value))
//            .findFirst().orElseThrow().getId();
//        Analytics.error("Provided suffix <code>%s</code> is already specified in strata with ID <code>%s</code>!".formatted(value, ogId), data);
        return false;
    }
}
