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
import com.ridanisaurus.emendatusenigmatica.loader.EEPluginLoader;
import com.ridanisaurus.emendatusenigmatica.plugin.DataRegistry;
import com.ridanisaurus.emendatusenigmatica.plugin.model.compat.CompatModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * Registry of all the data loaded from the plugins in {@link EEPluginLoader} using {@link IEmendatusPlugin}
 *
 * Here are stored all the materials, strata and compat.
 * @deprecated EmendatusDataRegistry was deprecated due to plugin system rework.
 * This class is no longer in use, please use {@link DataRegistry} instead for vanilla data.
 */
@Deprecated(since = "2.2.0-Alpha-4", forRemoval = true)
@SuppressWarnings("deprecated removal")
public class EmendatusDataRegistry {
    private final Map<String, MaterialModel> materials;
    private final Map<String, String> strataByFiller;
    private final Map<String, StrataModel> strata;


    @Deprecated(since = "2.2.0-Alpha-1", forRemoval = true)
    @SuppressWarnings("removal")
    private final List<CompatModel> compat;

    public EmendatusDataRegistry() {
        this.strataByFiller = new HashMap<>();
        this.materials = new HashMap<>();
        this.strata = new HashMap<>();
        this.compat = new ArrayList<>();
    }

    /**
     * Used to register a new Material model.
     * @param model MaterialModel to register.
     */
    public void registerMaterial(MaterialModel model) {
        this.materials.put(model.getId(), model);
    }

    /**
     * Used to get MaterialModel by its ID.
     * @param id ID to get model of.
     * @return MaterialModel under that ID, or null if not registered.
     */
    public @Nullable MaterialModel getMaterial(String id) {
        return this.materials.get(id);
    }

    /**
     * @return An immutable list with all registered materials.
     */
    public List<MaterialModel> getMaterials(){
        return ImmutableList.copyOf(materials.values());
    }

    /**
     * Used to register a new Strata model.
     * @param model MaterialModel to register.
     */
    public void registerStrata(@NotNull StrataModel model) {
        this.strataByFiller.put(model.getFillerType().toString(), model.getId());
        this.strata.put(model.getId(), model);
    }

    /**
     * Used to get StrataModel by its ID.
     * @param id ID to get model of.
     * @return StrataModel under that ID, or null if not registered.
     */
    public @Nullable StrataModel getStrata(String id) {
        return this.strata.get(id);
    }

    /**
     * @return An immutable list with all registered strata.
     */
    public List<StrataModel> getStrata(){
        return ImmutableList.copyOf(strata.values());
    }

    /**
     * Used to get StrataModel by the fillerType.
     * @param filler ResourceLocation of the filler.
     * @return StrataModel
     */
    public @Nullable StrataModel getStrataFromFiller(@NotNull ResourceLocation filler) {
        String id = this.strataByFiller.get(filler.toString());
        if (id == null) return null;
        return this.strata.get(id);
    }

    @Deprecated(since = "2.2.0-Alpha-1", forRemoval = true)
    @SuppressWarnings("removal")
    public void registerCompat(CompatModel compatModel){
        this.compat.add(compatModel);
    }

    @Deprecated(since = "2.2.0-Alpha-1", forRemoval = true)
    @SuppressWarnings("removal")
    public List<CompatModel> getCompat() {
        return ImmutableList.copyOf(compat);
    }
}
