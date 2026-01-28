package com.ridanisaurus.emendatusenigmatica.loader;

import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.Objects;

//TODO: Docs;
/**
 * <h1>Model Extensions</h1>
 * {@link EEModelExtension ModelExtensions} are used to extend models provided by EmendatusEnigmatica and other plugins.
 */
//TODO: Figure out the registry situation with the extensions. Technically I have the plugin that added the extension so I can give it it's own registry, but how the heck do I force the usage of it?
public class EEModelExtension<OM, M, OR, R> {
    private final EEModelDefinition<OM, OR> definition;
    private final Codec<M> codec;
    private final ValidationManager rootValidator;
    private final RegisterFunction<OM, M, OR, R> registerFunction;

    public EEModelExtension(EEModelDefinition<OM, OR> definition, Codec<M> codec, @Nullable ValidationManager rootValidator, RegisterFunction<OM, M, OR, R> registerFunction) {
        this.definition = Objects.requireNonNull(definition, "Definition model to extend can't be null.");
        this.codec = Objects.requireNonNull(codec, "Codec can't be null.");
        this.rootValidator = rootValidator;
        this.registerFunction = Objects.requireNonNull(registerFunction, "Register function can't be null.");
    }

    public EEModelExtension(EEModelDefinition<OM, OR> definition, Codec<M> codec, RegisterFunction<OM, M, OR, R> registerFunction) {
        this(definition, codec, null, registerFunction);
    }

    protected EEModelDefinition<OM, OR> getExtendedDefinition() {
        return this.definition;
    }

    protected boolean validate(JsonObject object, Path path) {
        if (Objects.isNull(rootValidator)) return true;
        return rootValidator.validate(object, path);
    }

    protected M serialize(JsonObject object) {
        return JsonOps.INSTANCE.withDecoder(codec).apply(object).result().map(Pair::getFirst).orElse(null);
    }

    protected void register(OM model, M extensionModel, OR originalRegistry, R registry) {
        this.registerFunction.register(model, extensionModel, originalRegistry, registry);
    }

    @SuppressWarnings("unchecked")
    protected void genericRegister(Object model, Object extensionModel, Object originalRegistry, Object registry) {
        register((OM) model, (M) extensionModel, (OR) originalRegistry, (R) registry);
    }

    @FunctionalInterface
    public interface RegisterFunction<OM, M, OR, R> {
        void register(OM originalModel, M model, OR originalRegistry, R registry);
    }
}
