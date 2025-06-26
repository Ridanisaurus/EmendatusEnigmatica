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

import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.api.IEmendatusPlugin;
import com.ridanisaurus.emendatusenigmatica.api.annotation.EmendatusPluginReference;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@EmendatusPluginReference(modid = EEOccultismAddon.MOD_ID, name = "config")
public class EEOccultismPlugin implements IEmendatusPlugin {
	@Override
	public void load(EmendatusDataRegistry emendatusDataRegistry) {

	}

	@Override
	public void registerMinecraft(List<MaterialModel> materialModels, List<StrataModel> strataModels) {

	}

	@Override
	public void registerDynamicDataGen(DataGenerator generator, EmendatusDataRegistry registry, CompletableFuture<HolderLookup.Provider> providers) {
//		generator.addProvider(true, new EEOccultismDataGen.Recipes(generator, registry));
	}

	@Override
	public void finish(EmendatusDataRegistry emendatusDataRegistry) {

	}
}