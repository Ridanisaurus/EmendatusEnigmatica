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

package com.ridanisaurus.emendatusenigmatica.api;

import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.ridanisaurus.emendatusenigmatica.plugin.model.compat.CompatModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Registry of all the data loaded from the plugins in {@link com.ridanisaurus.emendatusenigmatica.loader.EELoader} using {@link IEmendatusPlugin}
 *
 * Here are stored all the materials, strata and compat.
 */
public class EmendatusDataRegistry {

    private final Map<String, MaterialModel> materials;
    private final Map<String, StrataModel> strata;
    private final Map<String, String> strataByFiller;
    private final List<CompatModel> compat;

    public EmendatusDataRegistry() {
        this.strataByFiller = new HashMap<>();
        this.materials = new HashMap<>();
        this.strata = new HashMap<>();
        this.compat = new ArrayList<>();
    }

    /**
     * Used to register new Material model, returning passed defaultModel or previous value under that id.
     * @param material ID of the material.
     * @param defaultModel MaterialModel to register under that id.
     * @return MaterialModel passed to the argument or previous MaterialModel that was registered under that id.
     */
    @CanIgnoreReturnValue
    public MaterialModel getMaterialOrRegister(String material, MaterialModel defaultModel){
        return this.materials.computeIfAbsent(material, s -> defaultModel);
    }

    /**
     * Used to get MaterialModel by its ID.
     * @param materialID MaterialID to get model of.
     * @return MaterialModel under that ID, or null if not registered.
     */
    public @Nullable MaterialModel getMaterial(String materialID) {
        return this.materials.get(materialID);
    }

    public List<MaterialModel> getMaterials(){
        return ImmutableList.copyOf(materials.values());
    }

    public void registerStrata(@NotNull StrataModel strataModel){
        this.strataByFiller.put(strataModel.getFillerType().toString(), strataModel.getId());
        this.strata.put(strataModel.getId(), strataModel);
    }

    public @Nullable StrataModel getStrata(String strataID) {
        return this.strata.get(strataID);
    }

    public List<StrataModel> getStrata(){
        return ImmutableList.copyOf(strata.values());
    }

    public @Nullable StrataModel getStrataFromFiller(@NotNull ResourceLocation filler) {
        String id = this.strataByFiller.get(filler.toString());
        if (id == null) return null;
        return this.strata.get(id);
    }

    public void registerCompat(CompatModel compatModel){
        this.compat.add(compatModel);
    }

    public List<CompatModel> getCompat() {
        return ImmutableList.copyOf(compat);
    }
}
