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
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationData;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.AbstractValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.deposit.DepositType;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.dense.DenseDepositConfigModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.dike.DikeDepositConfigModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.geode.GeodeDepositConfigModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.sphere.SphereDepositConfigModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.vanilla.VanillaDepositConfigModel;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class DepositConfigValidator extends AbstractValidator {
    public static Map<String, AbstractValidator> DEPOSIT_VALIDATORS = new HashMap<>(Map.of(
        DepositType.VANILLA.getType(), VanillaDepositConfigModel.VALIDATION_MANAGER.getAsValidator(false),
        DepositType.SPHERE.getType(), SphereDepositConfigModel.VALIDATION_MANAGER.getAsValidator(false),
        DepositType.DENSE.getType(), DenseDepositConfigModel.VALIDATION_MANAGER.getAsValidator(false),
        DepositType.GEODE.getType(), GeodeDepositConfigModel.VALIDATION_MANAGER.getAsValidator(false),
        DepositType.DIKE.getType(), DikeDepositConfigModel.VALIDATION_MANAGER.getAsValidator(false)
    ));

    /**
     * Constructs DepositConfigValidator.
     *
     * @see DepositConfigValidator Documentation of the validator.
     */
    public DepositConfigValidator() {
        super(true);
    }

    /**
     * Validate method, used to validate passed in object.
     *
     * @param data ValidationData record with necessary information to validate the element.
     * @return True of the validation passes, false otherwise.
     * @apiNote Even tho it's public, this method should <i>never</i> be called directly! Call {@link AbstractValidator#apply(ValidationData)} instead!
     * @implSpec Take a note that the {@link ValidationData#validationElement()} will never return null.
     */
    @Override
    public Boolean validate(@NotNull ValidationData data) {
        JsonElement typeElement = data.getParentFieldAs(Types.STRING, "type");
        if (typeElement == null) return false;
        var validator = DEPOSIT_VALIDATORS.get(typeElement.getAsString());
        if (validator == null) return false;
        return validator.validate(data);
    }
}
