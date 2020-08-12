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

import com.ridanisaurus.emendatusenigmatica.registries.ItemHandler;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

  private static final ResourceLocation PluginID = new ResourceLocation(Reference.MOD_ID, "jei");

  @Override
  public ResourceLocation getPluginUid () {
    return PluginID;
  }

  @Override
  public void registerRecipes (IRecipeRegistration registration) {

    List<Item> modOres = new ArrayList<>();
    modOres.add(ItemHandler.ORE_COAL_ITEM.get());
    modOres.add(ItemHandler.ORE_COAL_ITEM_ANDESITE.get());
    modOres.add(ItemHandler.ORE_COAL_ITEM_GABBRO.get());
    modOres.add(ItemHandler.ORE_COAL_ITEM_C_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_COAL_ITEM_SCORIA.get());
    modOres.add(ItemHandler.ORE_COAL_ITEM_WEATHERED_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_COAL_ITEM_JASPER.get());
    modOres.add(ItemHandler.ORE_COAL_ITEM_Q_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_COAL_ITEM_MARBLE.get());
    modOres.add(ItemHandler.ORE_COAL_ITEM_SLATE.get());
    modOres.add(ItemHandler.ORE_IRON_ITEM.get());
    modOres.add(ItemHandler.ORE_IRON_ITEM_ANDESITE.get());
    modOres.add(ItemHandler.ORE_IRON_ITEM_GABBRO.get());
    modOres.add(ItemHandler.ORE_IRON_ITEM_C_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_IRON_ITEM_SCORIA.get());
    modOres.add(ItemHandler.ORE_IRON_ITEM_WEATHERED_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_IRON_ITEM_JASPER.get());
    modOres.add(ItemHandler.ORE_IRON_ITEM_Q_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_IRON_ITEM_MARBLE.get());
    modOres.add(ItemHandler.ORE_IRON_ITEM_SLATE.get());
    modOres.add(ItemHandler.ORE_GOLD_ITEM.get());
    modOres.add(ItemHandler.ORE_GOLD_ITEM_ANDESITE.get());
    modOres.add(ItemHandler.ORE_GOLD_ITEM_GABBRO.get());
    modOres.add(ItemHandler.ORE_GOLD_ITEM_C_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_GOLD_ITEM_SCORIA.get());
    modOres.add(ItemHandler.ORE_GOLD_ITEM_WEATHERED_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_GOLD_ITEM_JASPER.get());
    modOres.add(ItemHandler.ORE_GOLD_ITEM_Q_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_GOLD_ITEM_MARBLE.get());
    modOres.add(ItemHandler.ORE_GOLD_ITEM_SLATE.get());
    modOres.add(ItemHandler.ORE_DIAMOND_ITEM.get());
    modOres.add(ItemHandler.ORE_DIAMOND_ITEM_ANDESITE.get());
    modOres.add(ItemHandler.ORE_DIAMOND_ITEM_GABBRO.get());
    modOres.add(ItemHandler.ORE_DIAMOND_ITEM_C_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_DIAMOND_ITEM_SCORIA.get());
    modOres.add(ItemHandler.ORE_DIAMOND_ITEM_WEATHERED_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_DIAMOND_ITEM_JASPER.get());
    modOres.add(ItemHandler.ORE_DIAMOND_ITEM_Q_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_DIAMOND_ITEM_MARBLE.get());
    modOres.add(ItemHandler.ORE_DIAMOND_ITEM_SLATE.get());
    modOres.add(ItemHandler.ORE_EMERALD_ITEM.get());
    modOres.add(ItemHandler.ORE_EMERALD_ITEM_ANDESITE.get());
    modOres.add(ItemHandler.ORE_EMERALD_ITEM_GABBRO.get());
    modOres.add(ItemHandler.ORE_EMERALD_ITEM_C_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_EMERALD_ITEM_SCORIA.get());
    modOres.add(ItemHandler.ORE_EMERALD_ITEM_WEATHERED_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_EMERALD_ITEM_JASPER.get());
    modOres.add(ItemHandler.ORE_EMERALD_ITEM_Q_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_EMERALD_ITEM_MARBLE.get());
    modOres.add(ItemHandler.ORE_EMERALD_ITEM_SLATE.get());
    modOres.add(ItemHandler.ORE_LAPIS_ITEM.get());
    modOres.add(ItemHandler.ORE_LAPIS_ITEM_ANDESITE.get());
    modOres.add(ItemHandler.ORE_LAPIS_ITEM_GABBRO.get());
    modOres.add(ItemHandler.ORE_LAPIS_ITEM_C_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_LAPIS_ITEM_SCORIA.get());
    modOres.add(ItemHandler.ORE_LAPIS_ITEM_WEATHERED_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_LAPIS_ITEM_JASPER.get());
    modOres.add(ItemHandler.ORE_LAPIS_ITEM_Q_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_LAPIS_ITEM_MARBLE.get());
    modOres.add(ItemHandler.ORE_LAPIS_ITEM_SLATE.get());
    modOres.add(ItemHandler.ORE_REDSTONE_ITEM.get());
    modOres.add(ItemHandler.ORE_REDSTONE_ITEM_ANDESITE.get());
    modOres.add(ItemHandler.ORE_REDSTONE_ITEM_GABBRO.get());
    modOres.add(ItemHandler.ORE_REDSTONE_ITEM_C_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_REDSTONE_ITEM_SCORIA.get());
    modOres.add(ItemHandler.ORE_REDSTONE_ITEM_WEATHERED_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_REDSTONE_ITEM_JASPER.get());
    modOres.add(ItemHandler.ORE_REDSTONE_ITEM_Q_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_REDSTONE_ITEM_MARBLE.get());
    modOres.add(ItemHandler.ORE_REDSTONE_ITEM_SLATE.get());
    modOres.add(ItemHandler.ORE_COPPER_ITEM.get());
    modOres.add(ItemHandler.ORE_COPPER_ITEM_ANDESITE.get());
    modOres.add(ItemHandler.ORE_COPPER_ITEM_GABBRO.get());
    modOres.add(ItemHandler.ORE_COPPER_ITEM_C_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_COPPER_ITEM_SCORIA.get());
    modOres.add(ItemHandler.ORE_COPPER_ITEM_WEATHERED_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_COPPER_ITEM_JASPER.get());
    modOres.add(ItemHandler.ORE_COPPER_ITEM_Q_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_COPPER_ITEM_MARBLE.get());
    modOres.add(ItemHandler.ORE_COPPER_ITEM_SLATE.get());
    modOres.add(ItemHandler.ORE_ALUMINUM_ITEM.get());
    modOres.add(ItemHandler.ORE_ALUMINUM_ITEM_ANDESITE.get());
    modOres.add(ItemHandler.ORE_ALUMINUM_ITEM_GABBRO.get());
    modOres.add(ItemHandler.ORE_ALUMINUM_ITEM_C_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_ALUMINUM_ITEM_SCORIA.get());
    modOres.add(ItemHandler.ORE_ALUMINUM_ITEM_WEATHERED_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_ALUMINUM_ITEM_JASPER.get());
    modOres.add(ItemHandler.ORE_ALUMINUM_ITEM_Q_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_ALUMINUM_ITEM_MARBLE.get());
    modOres.add(ItemHandler.ORE_ALUMINUM_ITEM_SLATE.get());
    modOres.add(ItemHandler.ORE_SILVER_ITEM.get());
    modOres.add(ItemHandler.ORE_SILVER_ITEM_ANDESITE.get());
    modOres.add(ItemHandler.ORE_SILVER_ITEM_GABBRO.get());
    modOres.add(ItemHandler.ORE_SILVER_ITEM_C_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_SILVER_ITEM_SCORIA.get());
    modOres.add(ItemHandler.ORE_SILVER_ITEM_WEATHERED_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_SILVER_ITEM_JASPER.get());
    modOres.add(ItemHandler.ORE_SILVER_ITEM_Q_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_SILVER_ITEM_MARBLE.get());
    modOres.add(ItemHandler.ORE_SILVER_ITEM_SLATE.get());
    modOres.add(ItemHandler.ORE_LEAD_ITEM.get());
    modOres.add(ItemHandler.ORE_LEAD_ITEM_ANDESITE.get());
    modOres.add(ItemHandler.ORE_LEAD_ITEM_GABBRO.get());
    modOres.add(ItemHandler.ORE_LEAD_ITEM_C_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_LEAD_ITEM_SCORIA.get());
    modOres.add(ItemHandler.ORE_LEAD_ITEM_WEATHERED_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_LEAD_ITEM_JASPER.get());
    modOres.add(ItemHandler.ORE_LEAD_ITEM_Q_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_LEAD_ITEM_MARBLE.get());
    modOres.add(ItemHandler.ORE_LEAD_ITEM_SLATE.get());
    modOres.add(ItemHandler.ORE_NICKEL_ITEM.get());
    modOres.add(ItemHandler.ORE_NICKEL_ITEM_ANDESITE.get());
    modOres.add(ItemHandler.ORE_NICKEL_ITEM_GABBRO.get());
    modOres.add(ItemHandler.ORE_NICKEL_ITEM_C_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_NICKEL_ITEM_SCORIA.get());
    modOres.add(ItemHandler.ORE_NICKEL_ITEM_WEATHERED_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_NICKEL_ITEM_JASPER.get());
    modOres.add(ItemHandler.ORE_NICKEL_ITEM_Q_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_NICKEL_ITEM_MARBLE.get());
    modOres.add(ItemHandler.ORE_NICKEL_ITEM_SLATE.get());
    modOres.add(ItemHandler.ORE_URANIUM_ITEM.get());
    modOres.add(ItemHandler.ORE_URANIUM_ITEM_ANDESITE.get());
    modOres.add(ItemHandler.ORE_URANIUM_ITEM_GABBRO.get());
    modOres.add(ItemHandler.ORE_URANIUM_ITEM_C_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_URANIUM_ITEM_SCORIA.get());
    modOres.add(ItemHandler.ORE_URANIUM_ITEM_WEATHERED_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_URANIUM_ITEM_JASPER.get());
    modOres.add(ItemHandler.ORE_URANIUM_ITEM_Q_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_URANIUM_ITEM_MARBLE.get());
    modOres.add(ItemHandler.ORE_URANIUM_ITEM_SLATE.get());
    modOres.add(ItemHandler.ORE_OSMIUM_ITEM.get());
    modOres.add(ItemHandler.ORE_OSMIUM_ITEM_ANDESITE.get());
    modOres.add(ItemHandler.ORE_OSMIUM_ITEM_GABBRO.get());
    modOres.add(ItemHandler.ORE_OSMIUM_ITEM_C_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_OSMIUM_ITEM_SCORIA.get());
    modOres.add(ItemHandler.ORE_OSMIUM_ITEM_WEATHERED_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_OSMIUM_ITEM_JASPER.get());
    modOres.add(ItemHandler.ORE_OSMIUM_ITEM_Q_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_OSMIUM_ITEM_MARBLE.get());
    modOres.add(ItemHandler.ORE_OSMIUM_ITEM_SLATE.get());
    modOres.add(ItemHandler.ORE_TIN_ITEM.get());
    modOres.add(ItemHandler.ORE_TIN_ITEM_ANDESITE.get());
    modOres.add(ItemHandler.ORE_TIN_ITEM_GABBRO.get());
    modOres.add(ItemHandler.ORE_TIN_ITEM_C_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_TIN_ITEM_SCORIA.get());
    modOres.add(ItemHandler.ORE_TIN_ITEM_WEATHERED_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_TIN_ITEM_JASPER.get());
    modOres.add(ItemHandler.ORE_TIN_ITEM_Q_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_TIN_ITEM_MARBLE.get());
    modOres.add(ItemHandler.ORE_TIN_ITEM_SLATE.get());
    modOres.add(ItemHandler.ORE_ZINC_ITEM.get());
    modOres.add(ItemHandler.ORE_ZINC_ITEM_ANDESITE.get());
    modOres.add(ItemHandler.ORE_ZINC_ITEM_GABBRO.get());
    modOres.add(ItemHandler.ORE_ZINC_ITEM_C_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_ZINC_ITEM_SCORIA.get());
    modOres.add(ItemHandler.ORE_ZINC_ITEM_WEATHERED_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_ZINC_ITEM_JASPER.get());
    modOres.add(ItemHandler.ORE_ZINC_ITEM_Q_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_ZINC_ITEM_MARBLE.get());
    modOres.add(ItemHandler.ORE_ZINC_ITEM_SLATE.get());
    modOres.add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM.get());
    modOres.add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM_ANDESITE.get());
    modOres.add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM_GABBRO.get());
    modOres.add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM_C_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM_SCORIA.get());
    modOres.add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM_WEATHERED_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM_JASPER.get());
    modOres.add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM_Q_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM_MARBLE.get());
    modOres.add(ItemHandler.ORE_CERTUS_QUARTZ_ITEM_SLATE.get());
    modOres.add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM.get());
    modOres.add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM_ANDESITE.get());
    modOres.add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM_GABBRO.get());
    modOres.add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM_C_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM_SCORIA.get());
    modOres.add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM_WEATHERED_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM_JASPER.get());
    modOres.add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM_Q_LIMESTONE.get());
    modOres.add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM_MARBLE.get());
    modOres.add(ItemHandler.ORE_CHARGED_CERTUS_QUARTZ_ITEM_SLATE.get());

    for (final Item item : modOres) {
      registration.addIngredientInfo(new ItemStack(item), VanillaTypes.ITEM, "tooltip.emendatusenigmatica.ores.1", "tooltip.emendatusenigmatica.ores.2", "tooltip.emendatusenigmatica.ores.3");
    }
  }
}
