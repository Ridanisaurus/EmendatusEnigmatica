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

package com.ridanisaurus.eeoccultismaddon;

import com.google.gson.JsonPrimitive;
import com.ridanisaurus.eeoccultismaddon.datagen.OccultismRecipeGen;
import com.ridanisaurus.eeoccultismaddon.datagen.OccultismWorldGen;
import com.ridanisaurus.emendatusenigmatica.api.BasicEEPlugin;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.loader.ConfigCreationContext;
import com.ridanisaurus.emendatusenigmatica.api.config.DCCreationContext;
import com.ridanisaurus.emendatusenigmatica.api.config.types.DepositType;
import com.ridanisaurus.emendatusenigmatica.api.config.types.MaterialType;
import com.ridanisaurus.emendatusenigmatica.datagen.EEDataGenerator;
import com.ridanisaurus.emendatusenigmatica.loader.EEPluginLoader;
import com.ridanisaurus.emendatusenigmatica.loader.SetupContext;
import com.ridanisaurus.emendatusenigmatica.plugin.VanillaPlugin;
import net.minecraft.core.HolderLookup;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.concurrent.CompletableFuture;

@EmendatusPluginReference(modId = EEOccultismPlugin.MOD_ID, name = "occultism-plugin")
@Mod(EEOccultismPlugin.MOD_ID)
public class EEOccultismPlugin extends BasicEEPlugin {
	public static final String MOD_ID = "ee_occultism_addon";
	public static ModConfigSpec.BooleanValue disableSilver = null;
	public static ModConfigSpec.BooleanValue disableIesnium = null;
	private EEPluginLoader pluginLoader = null;

	/**
	 * This method is used to make any necessary changes to other plugins,
	 * register your models and anything else your plugin requires before EE itself starts.
	 *
	 * @param ctx Setup context, containing references to instances of EE loaders.
	 */
	@Override
	public void setup(SetupContext ctx) {
		pluginLoader = ctx.pluginLoader();
	}

	@Override
	public void extendConfig(ConfigCreationContext ctx) {
		if (!ctx.isStartup()) return;
		disableSilver = ctx.getBuilder()
			.comment("Determines if Occultism Silver ore generation should be disabled.")
			.translation("ee_occultism.config.disable_silver_ore")
			.define("disableSilverOre",true);
		disableIesnium = ctx.getBuilder()
			.comment("Determines if Occultism Iesnium ore generation should be disabled.")
			.translation("ee_occultism.config.disable_iesnium_ore")
			.define("disableIesniumOre",true);
	}

	@Override
	public void provideDefaultConfiguration(DCCreationContext ctx) {
		// Add Dust type to default vanilla models.
		MaterialType.getVanillaMaterials(ctx).forEach(data -> {
			var obj = data.getWrappedObject();
			// If it's not JsonArray, something is corrupted, as we should be working here with EE Vanilla Plugin data.
			var types = obj.get("processedTypes").getAsJsonArray();
			if (types.contains(new JsonPrimitive("dust"))) return;
			types.add("dust");
			data.updateWrappedObject(obj);
		});

		MaterialType.addTypesOrRegister(ctx, ctx.getInternalPath("silver"), "common");
		MaterialType.addTypesOrRegister(ctx, ctx.getInternalPath("iesnium"), "occultism");

		DepositType.registerIfAvailable(ctx, ctx.getInternalPath("iesnium_ore"), "occultism");
		DepositType.registerIfAvailable(ctx, ctx.getInternalPath("silver_ore"), "common/silver");
		DepositType.registerIfAvailable(ctx, ctx.getInternalPath("silver_deepslate_ore"), "common/silver");
	}

	/**
	 * Method called after EEDataGenerator is created and ready for registration of providers.
	 *
	 * @param generator EEDataGenerator instance.
	 * @param providers Vanilla Registry Lookup for use with vanilla generators that require it.
	 */
	@Override
	public void registerDynamicDataGen(EEDataGenerator generator, CompletableFuture<HolderLookup.Provider> providers) {
		var vanillaRegistry = pluginLoader.getRegistry(VanillaPlugin.class);
		generator.addProvider(true, new OccultismRecipeGen(generator, vanillaRegistry, providers));
		generator.addProvider(true, new OccultismWorldGen(generator, providers));
	}

	/**
	 * Method called after validation and serialization of the models defined by the plugins.
	 *
	 * @apiNote This method is called when it's safe to register objects to Minecraft/Mods registries.
	 */
	@Override
	public void register() {

	}
}