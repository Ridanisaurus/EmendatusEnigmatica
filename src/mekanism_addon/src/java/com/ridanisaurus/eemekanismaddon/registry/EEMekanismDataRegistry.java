package com.ridanisaurus.eemekanismaddon.registry;

import com.ridanisaurus.eemekanismaddon.extensions.MekanismMaterialExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class EEMekanismDataRegistry {
    private final Map<String, MekanismMaterialExtension> extendedMaterials = new HashMap<>();

    public void registerExtendedMaterial(String id, MekanismMaterialExtension model) {
        if (extendedMaterials.containsKey(id)) throw new IllegalArgumentException("Duplicate material ID (%s) extended!".formatted(id));
        extendedMaterials.put(id, Objects.requireNonNull(model, "Model can't be null."));
    }

    public MekanismMaterialExtension getExtensionFromID(String id) {
        return extendedMaterials.get(Objects.requireNonNull(id, "ID can't be null."));
    }

    public Map<String, MekanismMaterialExtension> getExtensions() {
        return Map.copyOf(extendedMaterials);
    }
}
