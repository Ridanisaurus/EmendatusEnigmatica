package com.ridanisaurus.emendatusenigmatica.api;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;

import java.util.concurrent.CompletableFuture;

public abstract class BasicEmendatusPlugin implements IEmendatusPlugin<Void> {

    /**
     * @param registry       The EmendatusDataRegistry used to store data used by EE.
     * @param customRegistry Ignored - null reference due to Void registry type.
     */
    @Override
    public void load(EmendatusDataRegistry registry, Void customRegistry) {
        this.load(registry);
    }

    /**
     * @param registry       The EmendatusDataRegistry used to store data used by EE.
     * @param customRegistry Ignored - null reference due to Void registry type.
     */
    @Override
    public void registerMinecraft(EmendatusDataRegistry registry, Void customRegistry) {
        this.registerMinecraft(registry);
    }

    /**
     * @param generator DataGenerator to register data providers to.
     * @param providers Vanilla Registry Lookup for use with vanilla generators that require it.
     * @param registry  Emendatus Enigmatica registry with all data parsed from the configuration files.
     * @param customRegistry Ignored - null reference due to Void registry type.
     */
    @Override
    public void registerDynamicDataGen(DataGenerator generator, CompletableFuture<HolderLookup.Provider> providers, EmendatusDataRegistry registry, Void customRegistry) {
        this.registerDynamicDataGen(generator, providers, registry);
    }

    public void load(EmendatusDataRegistry registry) {};

    public void registerMinecraft(EmendatusDataRegistry registry) {};

    public void registerDynamicDataGen(DataGenerator generator, CompletableFuture<HolderLookup.Provider> providers, EmendatusDataRegistry registry) {};
}
