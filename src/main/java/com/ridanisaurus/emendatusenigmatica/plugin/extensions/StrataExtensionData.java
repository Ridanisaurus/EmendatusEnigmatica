package com.ridanisaurus.emendatusenigmatica.plugin.extensions;

import com.ridanisaurus.emendatusenigmatica.plugin.ModelExtensionData;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import org.jetbrains.annotations.NotNull;

public abstract class StrataExtensionData extends ModelExtensionData<StrataModel> {
    @Override
    public @NotNull String getId() {
        return getOriginalModel().getId();
    }
}
