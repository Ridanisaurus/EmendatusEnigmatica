package com.ridanisaurus.emendatusenigmatica.plugin;

import com.ridanisaurus.emendatusenigmatica.plugin.model.DepositModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * DataRegistry is a class that holds all the information for the Vanilla Plugin gathered from the {@link com.ridanisaurus.emendatusenigmatica.loader.EEModelLoader}.
 */
public class DataRegistry {
    private final Map<String, MaterialModel> materials;
    private final Map<String, StrataModel> strata;
    private final Map<String, DepositModel> deposits;

    // Utility indexes
    private final Map<ResourceLocation, StrataModel> strataByFiller;

    public DataRegistry() {
        this.materials = new HashMap<>();
        this.strata = new HashMap<>();
        this.deposits = new HashMap<>();
        this.strataByFiller = new HashMap<>();
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
        strataByFiller.put(model.getFillerType(), model);
    }

    public void registerDeposit(DepositModel model) {
        //TODO: Implement after deposit rework.
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

    public MaterialModel getMaterialModel(String id) {
        return materials.get(Objects.requireNonNull(id, "ID can't be null."));
    }

    public StrataModel getStrataModel(String id) {
        return strata.get(Objects.requireNonNull(id, "ID can't be null."));
    }

    public DepositModel getDepositModel(String id) {
        return deposits.get(Objects.requireNonNull(id, "ID can't be null."));
    }

    public StrataModel getStrataModelByFiller(ResourceLocation filler) {
        return strataByFiller.get(Objects.requireNonNull(filler, "Filler can't be null."));
    }
}
