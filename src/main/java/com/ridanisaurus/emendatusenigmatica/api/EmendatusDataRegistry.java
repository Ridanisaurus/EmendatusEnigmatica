package com.ridanisaurus.emendatusenigmatica.api;

import com.google.common.collect.ImmutableList;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.CompatModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Registry of all the data loaded from the plugins in {@link com.ridanisaurus.emendatusenigmatica.loader.EELoader} using {@link IEmendatusPlugin}
 *
 * Here are stored all the materials, strata anda compat.
 */
public class EmendatusDataRegistry {

    private final Map<String, MaterialModel> materials;
    private final List<StrataModel> strata;
    private final List<CompatModel> compat;
    private final Map<String, Integer> strataByIndex;

    public EmendatusDataRegistry() {
        this.materials = new HashMap<>();
        this.strata = new ArrayList<>();
        this.compat = new ArrayList<>();
        this.strataByIndex = new HashMap<>();
    }

    /**
     * Used to register new Material model, returning passed defaultModel or previous value under that id.
     * @param material ID of the material.
     * @param defaultModel MaterialModel to register under that id.
     * @return MaterialModel passed to the argument or previous MaterialModel that was registered under that id.
     */
    public MaterialModel getMaterialOrRegister(String material, MaterialModel defaultModel){
        return this.materials.computeIfAbsent(material, s -> defaultModel);
    }

    /**
     * Used to get MaterialModel by its ID.
     * @param materialID MaterialID to get model of.
     * @return MaterialModel under that ID, or null if not registered.
     */
    public @Nullable MaterialModel getMaterialByID(String materialID) {
        return this.materials.get(materialID);
    }

    public void registerStrata(StrataModel strataModel){
        this.strata.add(strataModel);
        this.strataByIndex.put(strataModel.getFillerType().toString(), this.strata.size() - 1);
    }

    public void registerCompat(CompatModel compatModel){
        this.compat.add(compatModel);
    }


    public List<MaterialModel> getMaterials(){
        return ImmutableList.copyOf(materials.values());
    }

    public List<StrataModel> getStrata(){
        return ImmutableList.copyOf(strata);
    }

    public List<CompatModel> getCompat() {
        return ImmutableList.copyOf(compat);
    }

    /**
     * Used to get Map with Strata Filler Types mapped to indexes of {@link EmendatusDataRegistry#strata} list.
     * @return Map with mapping of Strata Filler Type -> Index of the model
     */
    public Map<String, Integer> getStrataByIndex() {
        return strataByIndex;
    }
}
