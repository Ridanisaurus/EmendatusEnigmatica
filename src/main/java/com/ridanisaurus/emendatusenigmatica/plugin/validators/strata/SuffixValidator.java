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

import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationContext;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.TypeValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.ValuesValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.VanillaPlugin;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

/**
 * Custom implementation of {@link ValuesValidator}, used to validate Suffix Field of the Strata.
 * @apiNote Suffixes are required to be compliant with [a-z0-9/._-] rule of ResourceLocations.
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
     * @param ctx ValidationContext record with necessary information to validate the element.
     * @return True of the validation passes, false otherwise.
     * @apiNote Even tho it's public, this method should <i>never</i> be called directly! Call {@link TypeValidator#apply(ValidationContext)} instead!
     */
    @Override
    public Boolean validate(@NotNull ValidationContext ctx) {
        if (!super.validate(ctx)) return false;
        String value = ctx.validationElement().getAsString();
        if (!ResourceLocation.isValidNamespace(value)) {
            ctx.error("Provided suffix <code>%s</code> contains non [a-z0-9/._-] character!".formatted(value));
            return false;
        }
        var reg = ctx.getPluginRegistry(VanillaPlugin.class);
        if (reg.isStrataSuffixUnique(value)) return true;
        String ogId = reg.getRegisteredStrata()
            .stream().filter(it -> it.getSuffix().equals(value))
            .findFirst().orElseThrow().getId();
        ctx.error("Provided suffix <code>%s</code> is already specified in strata with ID <code>%s</code>!".formatted(value, ogId));
        return false;
    }
}
