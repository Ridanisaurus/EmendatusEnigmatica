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
import com.ridanisaurus.emendatusenigmatica.api.BasicEmendatusPlugin;
import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.api.IEmendatusPlugin;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.api.config.ConfigCreationContext;
import com.ridanisaurus.emendatusenigmatica.api.config.DCCreationContext;
import com.ridanisaurus.emendatusenigmatica.api.config.types.DepositType;
import com.ridanisaurus.emendatusenigmatica.api.config.types.MaterialType;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

@EmendatusPluginReference(modId = EEOccultismPlugin.MOD_ID, name = "occultism-plugin")
@Mod(EEOccultismPlugin.MOD_ID)
public class EEOccultismPlugin extends BasicEmendatusPlugin {
	public static final String MOD_ID = "ee_occultism_addon";
	public static ModConfigSpec.BooleanValue disableSilver = null;
	public static ModConfigSpec.BooleanValue disableIesnium = null;

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
	public void registerDynamicDataGen(DataGenerator generator, CompletableFuture<HolderLookup.Provider> providers, EmendatusDataRegistry registry) {
		generator.addProvider(true, new OccultismRecipeGen(generator, registry, providers));
		generator.addProvider(true, new OccultismWorldGen(generator, providers));
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

		MaterialType.addTypesOrRegister(ctx, getInternalPath("silver"), "common");
		MaterialType.addTypesOrRegister(ctx, getInternalPath("iesnium"), "occultism");

		DepositType.registerIfAvailable(ctx, getInternalPath("iesnium_ore"), "occultism");
		DepositType.registerIfAvailable(ctx, getInternalPath("silver_ore"), "common/silver");
		DepositType.registerIfAvailable(ctx, getInternalPath("silver_deepslate_ore"), "common/silver");
	}

	@Contract(pure = true)
	private @NotNull String getInternalPath(String path) {
		return "assets/%s/configs/%s".formatted(MOD_ID, path);
	}
}