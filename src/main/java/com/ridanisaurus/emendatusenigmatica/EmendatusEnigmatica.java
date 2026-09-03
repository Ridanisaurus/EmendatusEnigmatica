/*
 * MIT License
 *
 * Copyright (c) 2024. Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica;

import com.mojang.logging.LogUtils;
import com.ridanisaurus.emendatusenigmatica.api.IEEPlugin;
import com.ridanisaurus.emendatusenigmatica.config.EEConfig;
import com.ridanisaurus.emendatusenigmatica.datagen.DataGeneratorFactory;
import com.ridanisaurus.emendatusenigmatica.datagen.EEDataGenerator;
import com.ridanisaurus.emendatusenigmatica.datagen.EEPackFinder;
import com.ridanisaurus.emendatusenigmatica.datagen.gen.LangGen;
import com.ridanisaurus.emendatusenigmatica.loader.EEModelLoader;
import com.ridanisaurus.emendatusenigmatica.loader.EEPluginLoader;
import com.ridanisaurus.emendatusenigmatica.api.validation.RegistryValidationManager;
import com.ridanisaurus.emendatusenigmatica.loader.AddonInfoAnalytics;
import com.ridanisaurus.emendatusenigmatica.loader.SetupContext;
import com.ridanisaurus.emendatusenigmatica.util.analytics.Analytics;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.tabs.EECreativeTab;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.progress.StartupNotificationManager;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.registries.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.concurrent.ExecutionException;

@Mod(Reference.MOD_ID)
public class EmendatusEnigmatica {
    public static final Logger logger = LogUtils.getLogger();
    public static String VERSION = "0.0.0";
    private static EmendatusEnigmatica instance;
    private final EEModelLoader modelLoader;
    private final EEPluginLoader pluginLoader;
    private final EEDataGenerator generator;

    // Creative Tabs Registration
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Reference.MOD_ID);
    public static final DeferredHolder<CreativeModeTab, EECreativeTab> TOOLS_TAB = CREATIVE_MODE_TABS.register("ee_tools_tab", () -> new EECreativeTab(
        CreativeModeTab.builder()
            .title(LangGen.get("itemgroup", "tools"))
            // Fallback
            .icon(() -> EERegistrar.ENIGMATIC_HAMMER.get().getDefaultInstance())
            .displayItems((parameters, output) -> output.accept(EERegistrar.ENIGMATIC_HAMMER))
    ));
    public static final DeferredHolder<CreativeModeTab, EECreativeTab> RESOURCES_TAB = CREATIVE_MODE_TABS.register("ee_resources_tab", () -> new EECreativeTab(
        CreativeModeTab.builder()
            .title(LangGen.get("itemgroup", "resources"))
            .withTabsBefore(TOOLS_TAB.getId())
            // Fallback
            .icon(() -> EERegistrar.FELINIUM_JAMINITE.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(EERegistrar.FELINIUM_JAMINITE);
                output.accept(EERegistrar.SHIELD_TEMPLATE);
            })
    ));

    public EmendatusEnigmatica(@NotNull IEventBus modEventBus, @NotNull ModContainer modContainer) throws ExecutionException, InterruptedException {
            instance = this;
            VERSION = modContainer.getModInfo().getVersion().toString();
            Analytics.setup();

            DataGeneratorFactory.init();
            this.generator = DataGeneratorFactory.createEEDataGenerator();
    
            this.pluginLoader = new EEPluginLoader();
            this.modelLoader = new EEModelLoader();
            Analytics.registerAddon(new AddonInfoAnalytics(this.pluginLoader, this.modelLoader));
            EEConfig.setupConfigs(modContainer, pluginLoader);
            this.pluginLoader.setup(new SetupContext(pluginLoader, modelLoader, this));
            this.pluginLoader.load(modelLoader);

            EERegistrar.finalize(modEventBus);
            CREATIVE_MODE_TABS.register(modEventBus);

            this.pluginLoader.registerDataGen(this.generator);
            this.pluginLoader.finish();

            // Creative Tab Item Registration.
            modEventBus.addListener(this::populateCreativeTab);
            // Virtual ResourcePack
            modEventBus.addListener(this::addPackFinder);
            // Generator check, we can't launch the game if the generator wasn't executed!
            modEventBus.addListener(this::clientDataGenCheck);
            NeoForge.EVENT_BUS.addListener(this::serverDataGenCheck);
            // Registry Validation
            modEventBus.addListener(this::commonSetup);
    }

    public static EmendatusEnigmatica getInstance() {
        return instance;
    }

    public EEPluginLoader getPluginLoader() {
        return pluginLoader;
    }

    public EEModelLoader getModelLoader() {
        return modelLoader;
    }

    /**
     * Utility method to get access to plugin registry instance.
     * @param plugin Plugin class the requested registry belongs to.
     * @return Registry instance of the provided plugin, or null if the plugin doesn't have it's own registry.
     * @param <R> Registry class.
     */
    public static <R> R getPluginRegistry(Class<? extends IEEPlugin<R>> plugin) {
        return getInstance().getPluginLoader().getRegistry(plugin);
    }

    private void populateCreativeTab(BuildCreativeModeTabContentsEvent event) {
        EERegistrar.registerToCreativeTabs(event);
    }

    private void addPackFinder(@NotNull AddPackFindersEvent event) {
        event.addRepositorySource(new EEPackFinder(event.getPackType()));
        if (!pluginLoader.isFinished()) {
            logger.error("Something is populating Pack Repository too early! Skipping running Data Generation.");
            return;
        }
        generator.run();
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        boolean result = RegistryValidationManager.validate();
        Analytics.finalizeAnalytics();
        if (!result)
            throw new IllegalStateException("Registry validation failed! %s Validation Summary for more details.".formatted(EEConfig.startup.generateSummary.get()? "Check the": "Enable"));
    }

    private void clientDataGenCheck(FMLLoadCompleteEvent event) {
        // AddPackFindersEvent is executed after FMLLoadCompleteEvent on the server side.
        if (FMLEnvironment.dist.isDedicatedServer() || generator.hasExecuted()) return;
        StartupNotificationManager.addModMessage("Emendatus Enigmatica - Missing Data Generation!");
        throw new IllegalStateException("Mod loading finished, but Data Generation wasn't executed!");
    }

    private void serverDataGenCheck(ServerStartedEvent event) {
        if (!generator.hasExecuted()) throw new IllegalStateException("Server has started, but Data Generation wasn't executed!");
    }
}
