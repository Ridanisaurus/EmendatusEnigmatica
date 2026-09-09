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

package com.ridanisaurus.emendatusenigmatica.loader.dc;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.loader.EEModelDefinition;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class DCCreationContext {
    private static final Gson gson = new Gson();
    private final List<DCData> configs = new ArrayList<>();
    private final Path configDir;
    private EmendatusPluginReference currentPlugin = null;

    public DCCreationContext(Path configDir) {
        this.configDir = Objects.requireNonNull(configDir, "ConfigDir can't be null!");
    }

    void updateCurrentPlugin(EmendatusPluginReference plugin) {
        this.currentPlugin = plugin;
    }

    private void checkValidity() {
        if (!isValid())
            throw new IllegalStateException("DCCreationContext is invalid!");
    }

    public boolean isValid() {
        return Objects.nonNull(this.currentPlugin);
    }

    public void register(EEModelDefinition<?, ?> model, String internalFile) {
        register(null, model, internalFile);
    }

    public void register(String path, EEModelDefinition<?, ?> model, String internalFile) {
        checkValidity();
        Objects.requireNonNull(internalFile, "InternalPath can't be null!");
        if (!internalFile.contains(".")) internalFile += ".json";
        if (!internalFile.startsWith("/")) internalFile = "/" + internalFile;

        if (Objects.nonNull(path) && !configDir.resolve(path).toAbsolutePath().normalize().startsWith(configDir))
            throw new SecurityException("Requested path for default configuration \"%s\" (\"%s\") points outside of EE configuration directory!"
                .formatted(internalFile, path));

        DCData data;
        try (var stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(internalFile)) {
            data = new DCData(
                Objects.isNull(path) ? StringUtils.substringAfterLast(internalFile, "/") : path,
                gson.fromJson(new InputStreamReader(Objects.requireNonNull(stream, "getResourceAsStream returned null")), JsonObject.class),
                model
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed getting internal file under \"%s\" path. Is your Jar corrupted?".formatted(internalFile), e);
        }

    }

    public <M> void register(String path, EEModelDefinition<M, ?> model, M object) {
        checkValidity();
        if (!configDir
                .resolve(Objects.requireNonNull(path, "Path for registration from model object can't be null!"))
                .toAbsolutePath()
                .normalize()
                .startsWith(configDir)
        ) throw new SecurityException("Requested path \"%s\" for default configuration points outside of EE configuration directory!".formatted(path));
        new DCData(
            path,
            JsonOps.INSTANCE.withEncoder(Objects.requireNonNull(model, "Model can't be null!").codec())
                .apply(Objects.requireNonNull(object, "Config Object can't be null!"))
                .getOrThrow()
                .getAsJsonObject(),
            model
        );
    }

    public void addProcessingRule(EEModelDefinition<?,?> model, Function<JsonObject, JsonObject> processor) {
        checkValidity();
    }

    private record DCData(String path, JsonObject config, EEModelDefinition<?, ?> model) {
        private DCData(
            String path,
            JsonObject config,
            EEModelDefinition<?, ?> model
        ) {
            this.config = Objects.requireNonNull(config, "Config object can't be null.");
            this.model = Objects.requireNonNull(model, "EEModelDefinition can't be null.");
            this.path = Objects.requireNonNull(path, "Path can't be null.");
        }
    }
}
