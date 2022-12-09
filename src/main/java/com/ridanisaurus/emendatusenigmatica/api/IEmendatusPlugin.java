package com.ridanisaurus.emendatusenigmatica.api;

import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import net.minecraft.data.DataGenerator;

import java.util.List;

/**
 * Plugin implementation to register things to the API at proper times, see {@link com.ridanisaurus.emendatusenigmatica.plugin.DefaultConfigPlugin} for examples
 */
public interface IEmendatusPlugin {

    /**
     * Method used to register materials, strata and compat
     * @param registry The registry used to register the materials, strata and compat
     */
    void load(EmendatusDataRegistry registry);

    /**
     * Method used to register minecraft objects like items or blocks
     * @param materialModels A list of all the available materials
     */
    void registerMinecraft(List<MaterialModel> materialModels, List<StrataModel> strataModels);

    /**
     * Method used to register dynamic datagens
     *
     * This will not run at your typical datagen time, this datagen are executed at runtime and automatically injected into the game but they function the same as normal datagen
     * @param generator
     * @param registry
     */
    void registerDynamicDataGen(DataGenerator generator, EmendatusDataRegistry registry);

    /**
     * Method called at the end of all the steps where is safe to store an instance of the {@link EmendatusDataRegistry} in case its needed
     * @param registry A safe instance of the {@link EmendatusDataRegistry}
     */
    void finish(EmendatusDataRegistry registry);
}
