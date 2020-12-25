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

package com.ridanisaurus.emendatusenigmatica.jei;

import com.ridanisaurus.emendatusenigmatica.config.WorldGenConfig;
import com.ridanisaurus.emendatusenigmatica.registries.ItemHandler;
import com.ridanisaurus.emendatusenigmatica.util.Materials;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

  private static final ResourceLocation PluginID = new ResourceLocation(Reference.MOD_ID, "jei");

  @Override
  public ResourceLocation getPluginUid() {
    return PluginID;
  }

  @Override
  public void registerRecipes(IRecipeRegistration registration) {

    for (Materials material : Materials.values()) {
      ItemHandler.backingOreItemTable.column(material).values().forEach(item -> {
        ItemStack stack = new ItemStack(item.get());
        WorldGenConfig.OreConfigs.BakedOreProps oreConfig = WorldGenConfig.COMMON.ORES.get(material);
        int min_y = oreConfig.BASELINE_Y - oreConfig.SPREAD_AMOUNT;
        int max_y = oreConfig.BASELINE_Y + oreConfig.SPREAD_AMOUNT;

        String overworldInfo = "Min Y: \u00a77N/A\u00a7r - Max Y: \u00a77N/A\u00a7r";
        String netherInfo = "Min Y: \u00a77N/A\u00a7r - Max Y: \u00a77N/A\u00a7r";
        String endInfo = "Min Y: \u00a77N/A\u00a7r - Max Y: \u00a77N/A\u00a7r";

        if (oreConfig.OVERWORLD_ACTIVE) {
          overworldInfo = "Min Y: \u00a78" + min_y + "\u00a7r - Max Y: \u00a78" + max_y + "\u00a7r";
        }
        if (oreConfig.NETHER_ACTIVE) {
          netherInfo = "Min Y: \u00a78" + (min_y + oreConfig.NETHER_MODIFIER) + "\u00a7r - Max Y: \u00a78" + (max_y + oreConfig.NETHER_MODIFIER) + "\u00a7r";
        }
        if (oreConfig.END_ACTIVE) {
          endInfo = "Min Y: \u00a78" + (min_y + oreConfig.END_MODIFIER) + "\u00a7r - Max Y: \u00a78" + (max_y + oreConfig.END_MODIFIER) + "\u00a7r";
        }

        registration.addIngredientInfo(stack, VanillaTypes.ITEM,
                "Spawns in the Overworld: \u00a78" + oreConfig.OVERWORLD_ACTIVE + "\u00a7r",
                overworldInfo + "\n",
                "Spawns in the Nether: \u00a78" + oreConfig.NETHER_ACTIVE + "\u00a7r",
                netherInfo + "\n",
                "Spawns in the End: \u00a78" + oreConfig.END_ACTIVE + "\u00a7r",
                endInfo + "\n",
                "tooltip.emendatusenigmatica.ores.1",
                "\n",
                "tooltip.emendatusenigmatica.ores.2",
                "\n",
                "tooltip.emendatusenigmatica.ores.3");
      });
    }
  }
}