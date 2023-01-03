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

package com.ridanisaurus.emendatusenigmatica.integration;

import com.ridanisaurus.emendatusenigmatica.loader.deposit.EEDeposits;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.plugin.DefaultConfigPlugin;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
	private static final ResourceLocation PluginID = new ResourceLocation(Reference.MOD_ID, "jei");
	private WorldGenRecipeCategory worldGenRecipeCategory;

	@Override
	public ResourceLocation getPluginUid() {
		return PluginID;
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		worldGenRecipeCategory = new WorldGenRecipeCategory(registry.getJeiHelpers().getGuiHelper(), "World Generation Info");
		registry.addRecipeCategories(worldGenRecipeCategory);
	}

	@Override
	public void registerRecipes(@NotNull IRecipeRegistration registration) {
		for (MaterialModel material : DefaultConfigPlugin.MATERIALS) {
			for (IDepositProcessor activeProcessor : EEDeposits.ACTIVE_PROCESSORS) {
				registration.addRecipes(WorldGenRecipeCategory.RECIPE, WorldGenRecipeCategory.getWorldGenRecipes(material, activeProcessor));
			}
		}
	}
}
