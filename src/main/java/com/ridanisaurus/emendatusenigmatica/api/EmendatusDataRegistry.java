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
import com.google.gson.JsonObject;
import com.ridanisaurus.emendatusenigmatica.plugin.model.compat.CompatModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.ApiStatus;
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
    /**
     * Temporary storage of raw JsonObjects for {@link IEmendatusPlugin#load(EmendatusDataRegistry, Object)} step,
     * so addons can parse custom data.
     */
    private final Map<String, JsonObject> rawJsons;

    @Deprecated(since = "2.2.0", forRemoval = true)
    @SuppressWarnings("removal")
    private final List<CompatModel> compat;

    public EmendatusDataRegistry() {
        this.strataByFiller = new HashMap<>();
        this.materials = new HashMap<>();
        this.strata = new HashMap<>();
        this.rawJsons = new HashMap<>();
        this.compat = new ArrayList<>();
    }

    /**
     * Used to register a new Material model.
     * @param model MaterialModel to register.
     * @param rawJson Raw JsonObject of this material.
     * @apiNote rawJson should be a direct object parsed from the file, for addons to be able to parse custom fields.
     */
    public void registerMaterial(MaterialModel model, JsonObject rawJson) {
        this.materials.put(model.getId(), model);
        this.rawJsons.put("material/" + model.getId(), rawJson);
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
     * @param rawJson Raw JsonObject of this strata.
     * @apiNote rawJson should be a direct object parsed from the file, for addons to be able to parse custom fields.
     */
    public void registerStrata(@NotNull StrataModel model, @NotNull JsonObject rawJson){
        this.strataByFiller.put(model.getFillerType().toString(), model.getId());
        this.strata.put(model.getId(), model);
        this.rawJsons.put("strata/" + model.getId(), rawJson);
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

    /**
     * Used to get a raw JsonObject of strata under specified id.
     * @param id Id of Strata to get.
     * @return Raw JsonObject of the StrataModel under that id, or null if not present.
     * @apiNote RawJson map will get cleared after execution of {@link IEmendatusPlugin#load(EmendatusDataRegistry, Object)};
     */
    public JsonObject getRawStrata(String id) {
        return rawJsons.get("strata/" + id);
    }

    /**
     * Used to get a raw JsonObject of material under specified id.
     * @param id Id of Material to get.
     * @return Raw JsonObject of the MaterialModel under that id, or null if not present.
     * @apiNote RawJson map will get cleared after execution of {@link IEmendatusPlugin#load(EmendatusDataRegistry, Object)};
     */
    public JsonObject getRawMaterial(String id) {
        return rawJsons.get("material/" + id);
    }

    @ApiStatus.Internal
    public void clean() {
        this.rawJsons.clear();
    }

    @Deprecated(since = "2.2.0", forRemoval = true)
    @SuppressWarnings("removal")
    public void registerCompat(CompatModel compatModel){
        this.compat.add(compatModel);
    }

    @Deprecated(since = "2.2.0", forRemoval = true)
    @SuppressWarnings("removal")
    public List<CompatModel> getCompat() {
        return ImmutableList.copyOf(compat);
    }
}
