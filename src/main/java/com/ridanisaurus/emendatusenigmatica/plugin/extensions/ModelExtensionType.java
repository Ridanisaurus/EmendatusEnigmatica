package com.ridanisaurus.emendatusenigmatica.plugin.extensions;

import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * ModelExtensionType is a record used to determine a ModelExtension Type.
 * It defines the acceptable Model Class to extend, and the registry name of the type.
 * @param getName Registry name of the type.
 * @param getModelClass Class of the model to extend.
 * @apiNote This is a self-registered class. It will register its instance to a private map on object creation.
 */
public record ModelExtensionType(String getName, Class<?> getModelClass) {
    private static final Map<String, ModelExtensionType> registeredTypes = new HashMap<>();
    public static final ModelExtensionType STRATA = new ModelExtensionType("strata", StrataModel.class);
    public static final ModelExtensionType MATERIAL = new ModelExtensionType("material", MaterialModel.class);

    public ModelExtensionType(String getName, Class<?> getModelClass) {
        this.getModelClass = Objects.requireNonNull(getModelClass, "ModelClass can't be null in a ModelExtensionType.");
        this.getName = Objects.requireNonNull(getName, "Name can't be null in a ModelExtensionType.");
        if (registeredTypes.containsKey(getName))
            throw new IllegalArgumentException("Duplicate ID of ModelExtensionType: " + getName);
        ModelExtensionType duplicate = getExtension(getModelClass);
        if (duplicate != null)
            throw new IllegalArgumentException("Type for class \"%s\" is already registered -> %s".formatted(getModelClass, duplicate.getName));
        registeredTypes.put(getName, this);
    }

    public static @Nullable ModelExtensionType getExtension(Class<?> modelClass) {
        var type = registeredTypes.values().stream().filter(it -> it.getModelClass.equals(modelClass)).toList();
        if (type.isEmpty()) return null;
        return type.getFirst();
    }
}
