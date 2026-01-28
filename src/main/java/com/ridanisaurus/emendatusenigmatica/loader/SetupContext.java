package com.ridanisaurus.emendatusenigmatica.loader;

import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;

/**
 * SetupContext is a record containing references to the classes useful for addons.
 * @param pluginLoader EEPluginLoader reference.
 * @param modelLoader EEModelLoader reference.
 * @param emendatusEnigmatica EE instance reference.
 */
public record SetupContext(EEPluginLoader pluginLoader, EEModelLoader modelLoader, EmendatusEnigmatica emendatusEnigmatica) {}
