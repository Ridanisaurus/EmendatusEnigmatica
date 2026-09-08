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

package com.ridanisaurus.emendatusenigmatica.plugin.compat.emi;

import com.ridanisaurus.emendatusenigmatica.plugin.DataRegistry;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.DepositModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.neoforged.neoforge.common.util.Lazy;
import org.jetbrains.annotations.ApiStatus;

import java.util.Objects;

//TODO: Add an option to hide all expect one ore based on Strata, so even tho there is like 20 different Stratas, only one is visible in EMI.
@EmiEntrypoint
public class EEEMIPlugin implements EmiPlugin {
	//TODO: Add Switching icon like World-Gen Creative Tab has.
	public static final Lazy<EmiRecipeCategory> WORLD_GEN_CATEGORY = Lazy.of(() -> new EmiRecipeCategory(
		Reference.getPath("compat/emi/worldgen"),
		EmiStack.of(EERegistrar.ENIGMATIC_HAMMER)
	));

	private static DataRegistry dataRegistry = null;

	@ApiStatus.Internal
	public static void provideDataRegistry(DataRegistry registry) {
		EEEMIPlugin.dataRegistry = Objects.requireNonNull(registry, "Can't use null registry for Emi Plugin setup!");
	}

	@Override
	public void register(EmiRegistry registry) {
		registry.addCategory(WORLD_GEN_CATEGORY.get());
		for (DepositModel deposit : dataRegistry.getRegisteredDeposits())
			registry.addRecipe(new WorldGenRecipe(deposit));
	}
}
