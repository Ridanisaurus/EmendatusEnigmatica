/*
 * MIT License
 *
 * Copyright (c) 2026. Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.plugin;

import com.ridanisaurus.emendatusenigmatica.plugin.model.DepositModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.MaterialModel;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * DataRegistry is a class that holds all the information for the Vanilla Plugin gathered from the {@link com.ridanisaurus.emendatusenigmatica.loader.EEModelLoader}.
 */
public class DataRegistry {
    private final Map<String, MaterialModel> materials;
    private final Map<String, StrataModel> strata;
    private final Map<String, DepositModel> deposits;

    public DataRegistry() {
        this.materials = new HashMap<>();
        this.strata = new HashMap<>();
        this.deposits = new HashMap<>();
    }

    public void registerMaterial(MaterialModel model) {
        if (materials.containsKey(Objects.requireNonNull(model,"Registered model can't be null!").getId()))
            throw new IllegalArgumentException("Duplicate material ID \"%s\"!".formatted(model.getId()));
        materials.put(model.getId(), model);
    }

    public void registerStrata(StrataModel model) {
        if (strata.containsKey(Objects.requireNonNull(model,"Registered model can't be null!").getId()))
            throw new IllegalArgumentException("Duplicate strata ID \"%s\"!".formatted(model.getId()));
        strata.put(model.getId(), model);
    }

    public void registerDeposit(DepositModel model) {
        if (deposits.containsKey(Objects.requireNonNull(model, "Registered model can't be null!").id))
            throw new IllegalArgumentException("Duplicate deposit ID \"%s\"!".formatted(model.id));
        deposits.put(model.id, model);
    }

    public List<MaterialModel> getRegisteredMaterials() {
        return List.copyOf(materials.values());
    }

    public List<StrataModel> getRegisteredStrata() {
        return List.copyOf(strata.values());
    }

    public List<DepositModel> getRegisteredDeposits() {
        return List.copyOf(deposits.values());
    }

    public boolean isMaterialRegistered(String id) {
        return materials.containsKey(id);
    }

    public boolean isStrataRegistered(String id) {
        return strata.containsKey(id);
    }

    public boolean isDepositRegistered(String id) {
        return deposits.containsKey(id);
    }

    public @Nullable MaterialModel getMaterialModel(String id) {
        return materials.get(Objects.requireNonNull(id, "ID can't be null."));
    }

    public @Nullable StrataModel getStrataModel(String id) {
        return strata.get(Objects.requireNonNull(id, "ID can't be null."));
    }

    public @Nullable DepositModel getDepositModel(String id) {
        return deposits.get(Objects.requireNonNull(id, "ID can't be null."));
    }

    public List<DepositModel> getDepositsByClass(Class<? extends DepositModel> modelClass) {
        return new ArrayList<>(deposits.values().stream().filter(it -> it.getClass().equals(modelClass)).toList());
    }

    public boolean isStrataSuffixUnique(String suffix) {
        return strata.values().stream().noneMatch(it -> it.getSuffix().equals(suffix));
    }
}
