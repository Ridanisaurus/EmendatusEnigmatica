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

package com.ridanisaurus.emendatusenigmatica.loader.configs;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.api.config.IDefaultConfigSetupContext;
import com.ridanisaurus.emendatusenigmatica.loader.EEModelDefinition;
import com.ridanisaurus.emendatusenigmatica.loader.EEModelExtension;
import com.ridanisaurus.emendatusenigmatica.loader.EEModelLoader;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class DefaultConfigSetupContext implements IDefaultConfigSetupContext {
    private static final Logger logger = LogUtils.getLogger();
    private static final Gson gson = new Gson();
    private final Map<EEModelDefinition<?,?>, List<DefaultConfig>> configs = new HashMap<>();
    private final EEModelLoader loader;
    private final Path configDir;
    private EmendatusPluginReference plugin = null;
    private boolean valid = true;

    public DefaultConfigSetupContext(Path configDir, EEModelLoader loader) {
        this.loader = Objects.requireNonNull(loader, "EEModelLoader can't be null!");
        this.configDir = Objects.requireNonNull(configDir, "ConfigDir can't be null!");
    }

    private void checkValidity() {
        if (!valid && Objects.isNull(plugin)) throw new IllegalStateException("DefaultConfigSetupContext is invalid!");
    }

    //NOTE FOR DOC WRITING: If internalFile doesn't start with `/`, a check for assets/plugin_namespace/configs happens!
    @Override
    public void register(EEModelDefinition<?, ?> model, String internalFile, String path) {
        checkValidity();
        if (!loader.isDefinitionRegistered(model)) throw new IllegalArgumentException("Provided model definition is not registered! Model" + model);
        Objects.requireNonNull(internalFile, "InternalPath can't be null!");
        if (internalFile.endsWith("/")) throw new IllegalArgumentException("InternalPath points to a directory!");
        if (!internalFile.startsWith("/")) internalFile = "/assets/%s/configs/%s".formatted(plugin.modId(), internalFile);
        internalFile = StringUtils.appendIfMissing(internalFile, ".json");

        if (Objects.nonNull(path)) {
            if (path.endsWith("/")) path += StringUtils.substringAfterLast(internalFile, "/");
            path = StringUtils.appendIfMissing(path, ".json");
            if (!configDir.resolve(path).toAbsolutePath().normalize().startsWith(configDir))
                throw new SecurityException("Requested path for default configuration \"%s\" (\"%s\") points outside of EE configuration directory!".formatted(internalFile, path));
        }

        DefaultConfig config;
        try (var stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(internalFile)) {
            config = new DefaultConfig(
                Objects.isNull(path) ? StringUtils.substringAfterLast(internalFile, "/") : path,
                gson.fromJson(new InputStreamReader(Objects.requireNonNull(stream, "getResourceAsStream returned null")), JsonObject.class)
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed getting internal file under \"%s\" path. Is your Jar corrupted?".formatted(internalFile), e);
        }

        if (config.getRawConfig().isEmpty())
            throw new IllegalArgumentException("Provided internal asset results in an empty JSON object!");

        addData(model, config);
    }

    @Override
    public <M> void register(String path, EEModelDefinition<M, ?> model, M object) {
        checkValidity();
        if (!loader.isDefinitionRegistered(model)) throw new IllegalArgumentException("Provided model definition is not registered! Model" + model);

        if (path.endsWith("/")) throw new IllegalArgumentException("Path points to a directory!");
        path = StringUtils.appendIfMissing(path, ".json");
        if (!configDir.resolve(path).toAbsolutePath().normalize().startsWith(configDir))
            throw new SecurityException("Requested path for default configuration \"%s\" points outside of EE configuration directory!".formatted(path));

        var config = new DefaultConfig(
            path,
            JsonOps.INSTANCE.withEncoder(Objects.requireNonNull(model, "EEModelDefinition can't be null!").codec())
                .apply(Objects.requireNonNull(object, "Config Object can't be null!"))
                .getOrThrow()
                .getAsJsonObject()
        );

        if (config.getRawConfig().isEmpty())
            throw new IllegalArgumentException("Provided model object results in an empty JSON object!");

        addData(model, config);
    }

    @Override
    public <M> void register(String path, EEModelExtension<?, M, ?, ?> extension, M object) {
        checkValidity();
        if (!loader.isExtensionRegistered(extension)) throw new IllegalArgumentException("Provided model getExtension is not registered! Model" + extension);

        if (path.endsWith("/")) throw new IllegalArgumentException("Path points to a directory!");
        path = StringUtils.appendIfMissing(path, ".json");
        if (!configDir.resolve(path).toAbsolutePath().normalize().startsWith(configDir))
            throw new SecurityException("Requested path for default configuration \"%s\" points outside of EE configuration directory!".formatted(path));

        var config = new DefaultConfig(
            path,
            Objects.requireNonNull(Objects.requireNonNull(extension, "Extension can't be null!").encode(Objects.requireNonNull(object,"Config Object can't be null!"))),
            extension
        );

        if (config.getRawConfig().isEmpty())
            throw new IllegalArgumentException("Provided model object results in an empty JSON object!");

        addData(extension.getExtendedDefinition(), config);
    }

    @Override
    public void addProcessingRule(EEModelDefinition<?,?> model, Function<JsonObject, JsonObject> processor) {
        checkValidity();
        //TODO: Implement.
    }

    private void addData(EEModelDefinition<?,?> model, DefaultConfig newConfig) {
        if (!configs.containsKey(model)) {
            configs.computeIfAbsent(model, (it) -> new ArrayList<>()).add(newConfig);
            return;
        }

        var it = configs.get(model).iterator();

        while (it.hasNext()) {
            var config = it.next();
            if (!model.merger().checkForConflicts(config, newConfig)) {
                // Make sure merger doesn't just skip conflict checks, same path is always invalid!
                if (Objects.equals(config.getPath(), newConfig.getPath())) {
                    logger.warn("Found unresolved conflict under path \"{}\" for model \"{}\"! Both files will be kept, which might result in validation errors.", config.getPath(), model);
                    it.remove();
                    handleInvalidResult(model, config, newConfig);
                    return;
                }
                continue;
            }

            var result = model.merger().merge(config, newConfig);

            if (!result.validate(config.getPath())) {
                logger.warn("Got invalid result from merging the conflict on path \"{}\" for model \"{}\"! Both files will be kept, which might result in validation errors.", config.getPath(), model);
                handleInvalidResult(model, config, newConfig);
                return;
            }


            logger.info("Default configuration conflict found for path \"{}\", resolved by {}", config.getPath(), result.getMergeType());
            if (result.merged()) {
                addData(model, new DefaultConfig(config.getPath(), result.result()));
            } else {
                if (!configDir.resolve(result.path()).toAbsolutePath().normalize().startsWith(configDir))
                    throw new SecurityException("Requested path for default configuration \"%s\" from a model \"%s\" config merger points outside of EE configuration directory!".formatted(result.path(), model));

                addData(model, new DefaultConfig(config.getPath(), result.result()));
                addData(model, new DefaultConfig(result.path(), result.additional()));
            }
            return;
        }

        configs.get(model).add(newConfig);
    }

    public IDefaultConfigSetupContext updateCurrentPlugin(EmendatusPluginReference plugin) {
        this.plugin = Objects.requireNonNull(plugin, "Plugin can't be null!");
        return this;
    }

    public List<CompletableFuture<Void>> getIOTasks() {
        checkValidity();
        invalidateContext();
        //TODO: Apply Post-Processor and generate IO tasks for saving the default configurations.
        return null;
    }

    void invalidateContext() {
        this.valid = false;
        this.plugin = null;
    }

    private void handleInvalidResult(EEModelDefinition<?,?> model, DefaultConfig config, DefaultConfig newConfig) {
        logger.error("IConfigMerger of model \"{}\" is not handling path conflicts correctly. This should be reported to the addon developer with minecraft logs attached.", model);
        addData(model, new DefaultConfig(getConflictedPath(config), config.getRawConfig(), config.getExtension()));
        addData(model, new DefaultConfig(getConflictedPath(newConfig), newConfig.getRawConfig(), newConfig.getExtension()));
    }

    private @NotNull String getConflictedPath(@NotNull DefaultConfig config) {
        return StringUtils.substringBeforeLast(config.getPath(), ".json") + "_CONFLICT_%d.json".formatted(System.currentTimeMillis());
    }
}
