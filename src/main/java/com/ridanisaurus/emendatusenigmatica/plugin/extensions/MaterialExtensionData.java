package com.ridanisaurus.emendatusenigmatica.plugin.extensions;

import com.ridanisaurus.emendatusenigmatica.plugin.ModelExtensionData;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import org.jetbrains.annotations.NotNull;

public abstract class MaterialExtensionData extends ModelExtensionData<MaterialModel> {
    @Override
    public @NotNull String getId() {
        return getOriginalModel().getId();
    }
}
