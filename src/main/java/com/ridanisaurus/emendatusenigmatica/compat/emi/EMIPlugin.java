/*
 *  MIT License
 *
 *  Copyright (c) 2020 Ridanisaurus
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package com.ridanisaurus.emendatusenigmatica.compat.emi;

import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.resources.ResourceLocation;

//TODO: Add an option to hide all expect one ore based on Strata, so even tho there is like 20 different Stratas, only one is visible in EMI.
@EmiEntrypoint
public class EMIPlugin implements EmiPlugin {
	public static final ResourceLocation GUI_ASSETS = Reference.getPath("textures/gui/world_gen.png");
	//TODO: Add Switching icon like World-Gen Creative Tab has.
	public static final EmiRecipeCategory WORLD_GEN_CATEGORY = new EmiRecipeCategory(
		Reference.getPath("compat/emi/worldgen"),
		EmiStack.of(EERegistrar.ENIGMATIC_HAMMER)
	);

	@Override
	public void register(EmiRegistry registry) {
		registry.addCategory(WORLD_GEN_CATEGORY);
//TODO: Rework for new Deposit System
//		ModelLoader.ACTIVE_PROCESSORS.forEach(it -> registry.addRecipe(new WorldGenRecipe(it)));
	}
}
