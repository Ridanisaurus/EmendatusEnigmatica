package com.ridanisaurus.emendatusenigmatica.plugin.extensions;

import com.mojang.serialization.Codec;
import com.ridanisaurus.emendatusenigmatica.api.IEmendatusPlugin;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.plugin.ModelExtensionData;
import com.ridanisaurus.emendatusenigmatica.plugin.ModelExtensionType;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;

import java.util.Objects;

/**
 * <h1>Model Extensions</h1>
 * {@link ModelExtension ModelExtensions} are used to extend vanilla {@link MaterialModel} and {@link StrataModel} classes provided by EmendatusEnigmatica.<br>
 * Their intended purpose is to allow addition of custom fields to the EE-Defined models, and get parsed by EE using their provided codecs.
 *
 * <h3>Custom Codecs</h3>
 * Emendatus Enigmatica expects all fields of a Codec to be optional. Any errors, mistakes or invalid fields are handled by the <b>Validation System</b>.
 *
 * <h3>Model Extension Types</h3>
 * Model Extensions can be either <b>optional</b>, or <b>mandatory</b>, each requiring a different implementation.
 * <h5>Mandatory Extension</h5>
 * Mandatory extension will be available for every model object it extends, allowing the plugin to iterate over their extended model instead of the basic one.<br>
 * <b>At least a single field needs to be required and enforced by usage of Emendatus Enigmatica's Validation System, and not by marking fields as mandatory in the codec.
 * Failing to do so will cause the vanilla model to get loaded normally, while extension for it will be missing, with Exception reported to the end user!</b>
 *
 * <h5>Optional Extension</h5>
 * Optional extension can be unavailable for a model object it extends. They should be accessed per-id, with iteration over the basic models.
 *
 * @param <O> Model to extend: {@link MaterialModel} or {@link StrataModel}
 * @param <E> Your {@link ModelExtensionData} implementation class.
 * @see ValidationManager
 * @see IEmendatusPlugin#setup()
 * @see StrataModel#VALIDATION_MANAGER
 * @see MaterialModel#VALIDATION_MANAGER
 */
public class ModelExtension<O, E extends ModelExtensionData<O>> {
    private final ModelExtensionType type;
    private final Codec<E> codec;
    private final Class<? extends IEmendatusPlugin<?>> pluginClass;

    public ModelExtension(Codec<E> codec, Class<O> modelClass, Class<? extends IEmendatusPlugin<?>> pluginClass) {
        this.pluginClass = Objects.requireNonNull(pluginClass);
        this.codec = Objects.requireNonNull(codec);

        if (modelClass == MaterialModel.class) {
            type = ModelExtensionType.MATERIAL;
            return;
        }

        if (modelClass == StrataModel.class) {
            type = ModelExtensionType.STRATA;
            return;
        }

        throw new IllegalArgumentException("Invalid class provided for extension. Required: MaterialModel | StrataModel - got: %s".formatted(modelClass));
    }

    public ModelExtensionType getType() {
        return this.type;
    }

    public Codec<E> getCodec() {
        return this.codec;
    }

    public Class<? extends IEmendatusPlugin<?>> getPlugin() {
        return this.pluginClass;
    }
}
