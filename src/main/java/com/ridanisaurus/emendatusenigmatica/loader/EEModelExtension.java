/*
 * MIT License
 *
 * Copyright (c) 2026. Ridanisaurus
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.ridanisaurus.emendatusenigmatica.loader;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.api.IEEPlugin;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.Objects;

/**
 * <h1>Model Extensions</h1>
 * ModelExtensions are used to extend models provided by EmendatusEnigmatica and other plugins.
 *
 * <h3>Extension Configuration</h3>
 * Extensions are required to provide correctly configured:
 * <ul>
 *     <li>{@link Class}{@code <? extends}{@link IEEPlugin &nbsp;IEEPLugin}{@code <R>>} Owning plugin class.</li>
 *     <li>{@link String} Registry name, unique per plugin and extended model.</li>
 *     <li>{@link EEModelDefinition}{@code <OM,OR>} Model Definition to extend.</li>
 *     <li>{@link Codec}{@code <M>} Codec of your extended model.</li>
 *     <li>{@link RegisterFunction RegisterFunction}{@code <OM, M, OR, R>} Register Function of your extended model.</li>
 * </ul>
 * Additionally, you can provide a {@link ValidationManager} to be executed on root of the extended model,
 * if your changes collide with the validators of the extended model.
 * If omitted, you should modify the original validator.
 * @see EEModelDefinition Model Definition documentation.
 * @see EEModelLoader Model loader definition.
 * @param <OM> Class of the original model.
 * @param <M> Class of the extension model.
 * @param <OR> Class of the original registry.
 * @param <R> Class of your plugin registry.
 */
public class EEModelExtension<OM, M, OR, R> {
    private final Class<? extends IEEPlugin<R>> pluginClass;
    private final EEModelDefinition<OM, OR> definition;
    private final Codec<M> codec;
    private final ValidationManager rootValidator;
    private final RegisterFunction<OM, M, OR, R> registerFunction;
    private final String registryName;

    /**
     * Constructor of EEModelExtension.
     * @param plugin Owning plugin class.
     * @param definition Model Definition to extend.
     * @param codec Codec of your extended model.
     * @param rootValidator Optional validator of your extended model.
     * @param registerFunction Register Function of your extended model.
     */
    public EEModelExtension(
        Class<? extends IEEPlugin<R>> plugin,
        String registryName,
        EEModelDefinition<OM, OR> definition,
        Codec<M> codec,
        @Nullable ValidationManager rootValidator,
        RegisterFunction<OM, M, OR, R> registerFunction
    ) {
        this.pluginClass = Objects.requireNonNull(plugin, "Owning plugin class can't be null.");
        this.registryName = Objects.requireNonNull(registryName, "Model Extension ID can't be null!");
        this.definition = Objects.requireNonNull(definition, "Definition model to extend can't be null.");
        this.codec = Objects.requireNonNull(codec, "Codec can't be null.");
        this.rootValidator = rootValidator;
        this.registerFunction = Objects.requireNonNull(registerFunction, "Register function can't be null.");

        Objects.requireNonNull(plugin.getAnnotation(EmendatusPluginReference.class), "Plugin annotation not present on the Plugin class.");
    }

    /**
     * Constructor of EEModelExtension.
     * @param plugin Owning plugin class.
     * @param definition Model Definition to extend.
     * @param codec Codec of your extended model.
     * @param registerFunction Register Function of your extended model.
     */
    public EEModelExtension(
        Class<? extends IEEPlugin<R>> plugin,
        String registryName,
        EEModelDefinition<OM, OR> definition,
        Codec<M> codec,
        RegisterFunction<OM, M, OR, R> registerFunction
    ) {
        this(plugin, registryName, definition, codec, null, registerFunction);
    }

    public String getExtensionOverrideField() {
        return getOwningAnnotation().name() + "." + registryName;
    }

    public String getRegistryName() {
        return this.registryName;
    }

    public @NotNull String getFullName() {
        return this.getOwningAnnotation().name() + "#" + this.registryName;
    }

    public Class<? extends IEEPlugin<R>> getOwningPlugin() {
        return this.pluginClass;
    }

    public EmendatusPluginReference getOwningAnnotation() {
        return this.pluginClass.getAnnotation(EmendatusPluginReference.class);
    }

    public EEModelDefinition<OM, OR> getExtendedDefinition() {
        return this.definition;
    }

    public ValidationManager getRootValidator() {
        return this.rootValidator;
    }

    public boolean validate(JsonObject object, Path path, EEPluginLoader pluginLoader) {
        if (Objects.isNull(rootValidator)) return true;
        return rootValidator.validate(object, path, pluginLoader);
    }

    public M decode(JsonObject object) {
        return JsonOps.INSTANCE.withDecoder(codec).apply(object).result().map(Pair::getFirst).orElse(null);
    }

    public JsonObject encode(M object) {
        return JsonOps.INSTANCE.withEncoder(codec).apply(object).result().map(JsonElement::getAsJsonObject).orElse(null);
    }

    void register(OM model, M extensionModel, OR originalRegistry, R registry) {
        this.registerFunction.register(model, extensionModel, originalRegistry, registry);
    }

    @SuppressWarnings("unchecked")
    void genericRegister(Object model, Object extensionModel, Object originalRegistry, Object registry) {
        register((OM) model, (M) extensionModel, (OR) originalRegistry, (R) registry);
    }

    @FunctionalInterface
    public interface RegisterFunction<OM, M, OR, R> {
        void register(OM originalModel, M model, OR originalRegistry, R registry);
    }

    @Override
    public String toString() {
        return getFullName();
    }
}
