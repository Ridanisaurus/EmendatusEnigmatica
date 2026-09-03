package com.ridanisaurus.emendatusenigmatica.api.validation.validators;

import com.ridanisaurus.emendatusenigmatica.api.IEEPlugin;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationContext;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

/**
 * Plugin Registry Validator is used to check the uniqueness or existence of an ID based on the registry of the provided plugin.
 * @param <R> The Registry class ID is meant to be valiated against.
 */
public class PluginRegistryValidator<R> extends TypeValidator {
    private final Class<? extends IEEPlugin<R>> pluginClass;
    private final BiFunction<R, String, Boolean> predicate;
    private final Mode mode;
    private final String name;

    /**
     * Will cause {@link PluginRegistryValidator} to check if ID isn't already registered in the specified list of ids.
     */
    public static final Mode REGISTRATION = Mode.REGISTRATION;
    /**
     * Will cause {@link PluginRegistryValidator} to check if ID is a valid reference to a value in the specified list.
     */
    public static final Mode REFERENCE = Mode.REFERENCE;
    /**
     * Constructs EERegistryValidator, without the registry name.
     *
     * @param pluginClass Class of the plugin the registry is from.
     * @param predicate Predicate used to check if provided ID exists in the registry.
     * @param mode Mode of this Validator.
     * @param isRequired Determines if the field is required. If true, an error will be issued if the field is missing.
     * @see PluginRegistryValidator Documentation of the validator
     * @see Mode Available modes
     */
    public PluginRegistryValidator(Class<? extends IEEPlugin<R>> pluginClass, BiFunction<R, String, Boolean> predicate, Mode mode, boolean isRequired) {
        this(pluginClass, predicate, mode, "", isRequired);
    }

    /**
     * Constructs EERegistryValidator.
     *
     * @param pluginClass Class of the plugin the registry is from.
     * @param predicate Predicate used to check if provided ID exists in the registry.
     * @param mode Mode of this Validator.
     * @param registryName Registry name.
     * @param isRequired Determines if the field is required. If true, an error will be issued if the field is missing.
     * @see PluginRegistryValidator Documentation of the validator
     * @see Mode Available modes
     * @apiNote {@code registryName} is only used in {@link Mode#REFERENCE} mode. Format: "{@code registryName} registry..."
     */
    public PluginRegistryValidator(Class<? extends IEEPlugin<R>> pluginClass, BiFunction<R, String, Boolean> predicate, Mode mode, String registryName, boolean isRequired) {
        super(Types.STRING, isRequired);
        this.pluginClass = Objects.requireNonNull(pluginClass, "Plugin class can't be null!");
        this.predicate = Objects.requireNonNull(predicate, "Predicate can't be null!");
        this.mode = Objects.requireNonNull(mode, "Mode can't be null!");
        this.name = " " + registryName;
    }

    /**
     * Validate method, used to validate passed in object.
     *
     * @param ctx ValidationContext record with necessary information to validate the element.
     * @return True of the validation passes, false otherwise.
     * @apiNote Even tho it's public, this method should <i>never</i> be called directly! Call {@link TypeValidator#apply(ValidationContext)} instead!
     */
    @Override
    public Boolean validate(@NotNull ValidationContext ctx) {
        if (!super.validate(ctx)) return false;
        String value = ctx.validationElement().getAsString();
        if (!ResourceLocation.isValidNamespace(value)) {
            ctx.error("Specified value <code>%s</code> contains non [a-z0-9/._-] character!".formatted(value));
            return false;
        }
        boolean contains = predicate.apply(ctx.getPluginRegistry(pluginClass), value);
        if (mode == Mode.REFERENCE) {
            if (contains) return true;
            ctx.error("Specified ID <code>%s</code> is missing from the%s registry!".formatted(value, name));
            return false;
        }
        if (!contains) return true;
        ctx.error("Specified ID <code>%s</code> is already registered!".formatted(value));
        return false;
    }

    /**
     * Simple enum to determine the mode of the {@link PluginRegistryValidator}.
     * @see Mode#REGISTRATION
     * @see Mode#REFERENCE
     */
    public enum Mode {
        /**
         * Will cause {@link PluginRegistryValidator} to check if ID isn't already registered in the specified list of ids.
         */
        REGISTRATION,
        /**
         * Will cause {@link PluginRegistryValidator} to check if ID is a valid reference to a value in the specified list.
         */
        REFERENCE
    }
}
