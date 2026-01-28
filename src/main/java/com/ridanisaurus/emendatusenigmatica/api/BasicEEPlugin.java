package com.ridanisaurus.emendatusenigmatica.api;

import com.ridanisaurus.emendatusenigmatica.datagen.EEDataGenerator;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

/**
 * Basic implementation of some IEEPlugin methods, to remove the Void null argument from methods in a plugin that doesn't use plugin registry.
 */
public abstract class BasicEEPlugin implements IEEPlugin<Void> {
    @Override
    public void registerDynamicDataGen(EEDataGenerator generator, CompletableFuture<HolderLookup.Provider> providers, Void registry) {
        this.registerDynamicDataGen(generator, providers);
    }

    @Override
    public void register(Void registry) {
        this.register();
    }

    /**
     * Method called after EEDataGenerator is created and ready for registration of providers.
     *
     * @param generator EEDataGenerator instance.
     * @param providers Vanilla Registry Lookup for use with vanilla generators that require it.
     */
    public abstract void registerDynamicDataGen(EEDataGenerator generator, CompletableFuture<HolderLookup.Provider> providers);

    /**
     * Method called after validation and serialization of the models defined by the plugins.
     *
     * @apiNote This method is called when it's safe to register objects to Minecraft/Mods registries.
     */
    public abstract void register();
}
