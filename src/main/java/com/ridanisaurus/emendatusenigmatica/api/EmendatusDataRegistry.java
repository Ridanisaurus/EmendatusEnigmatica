package com.ridanisaurus.emendatusenigmatica.api;

import com.google.common.collect.ImmutableList;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.CompatModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;

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

    public MaterialModel getMaterialOrRegister(String material, MaterialModel defaultModel){
        return this.materials.computeIfAbsent(material, s -> defaultModel);
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

    public Map<String, Integer> getStrataByIndex() {
        return strataByIndex;
    }
}
