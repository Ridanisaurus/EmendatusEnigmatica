package com.ridanisaurus.emendatusenigmatica.plugin;

import com.ridanisaurus.emendatusenigmatica.plugin.extensions.ModelExtensionType;

import java.util.Objects;

public abstract class ModelExtensionData<O> {
    private ModelExtensionType type;
    private O ogModel;

    protected ModelExtensionData<O> setOriginalModel(O model) {
        this.ogModel = Objects.requireNonNull(model, "Original model is null for extension: " + this.getClass());
        return this;
    }

    protected ModelExtensionData<O> setType(ModelExtensionType type) {
        this.type = Objects.requireNonNull(type, "Type is null for extension: " + this.getClass());
        return this;
    }

    public O getOriginalModel() {
        return ogModel;
    }

    public ModelExtensionType getType() {
        return type;
    }

    public abstract String getId();
}
