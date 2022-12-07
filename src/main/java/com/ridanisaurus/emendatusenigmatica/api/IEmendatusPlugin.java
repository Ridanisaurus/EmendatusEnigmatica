package com.ridanisaurus.emendatusenigmatica.api;

/**
 * Plugin implementation to register things to the API at proper times, see {@link com.ridanisaurus.emendatusenigmatica.plugin.DefaultConfigPlugin} for examples
 */
public interface IEmendatusPlugin {

    /**
     * Method used to register materials, strata and compat
     * @param registry The registry used to register the materials, strata and compat
     */
    void onLoad(EmendatusDataRegistry registry);
}
