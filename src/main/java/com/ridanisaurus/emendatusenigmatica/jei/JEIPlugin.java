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
import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;

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
        int min_y = oreConfig.OVERWORLD_BASE - oreConfig.OVERWORLD_SPREAD;
        int max_y = oreConfig.OVERWORLD_BASE + oreConfig.OVERWORLD_SPREAD;

        int nether_min_y = oreConfig.NETHER_BASE - oreConfig.NETHER_SPREAD;
        int nether_max_y = oreConfig.NETHER_BASE + oreConfig.NETHER_SPREAD;

        int end_min_y = oreConfig.END_BASE - oreConfig.END_SPREAD;
        int end_max_y = oreConfig.END_BASE + oreConfig.END_SPREAD;

        String overworldInfo = "Min Y: \u00a77N/A\u00a7r - Max Y: \u00a77N/A\u00a7r";
        String netherInfo = "Min Y: \u00a77N/A\u00a7r - Max Y: \u00a77N/A\u00a7r";
        String endInfo = "Min Y: \u00a77N/A\u00a7r - Max Y: \u00a77N/A\u00a7r";

        if (oreConfig.OVERWORLD_ACTIVE) {
          overworldInfo = "Min Y: \u00a78" + min_y + "\u00a7r - Max Y: \u00a78" + max_y + "\u00a7r";
        }
        if (oreConfig.NETHER_ACTIVE) {
          netherInfo = "Min Y: \u00a78" + nether_min_y + "\u00a7r - Max Y: \u00a78" + nether_max_y + "\u00a7r";
        }
        if (oreConfig.END_ACTIVE) {
          endInfo = "Min Y: \u00a78" + end_min_y + "\u00a7r - Max Y: \u00a78" + end_max_y + "\u00a7r";
        }

        String overworldBiomeList = "";
        String netherBiomeList = "";
        String endBiomeList = "";
        ArrayList<String> overworldBiomes = new ArrayList<>();
        ArrayList<String> netherBiomes = new ArrayList<>();
        ArrayList<String> endBiomes = new ArrayList<>();

        if(!oreConfig.OVERWORLD_BIOME_BLACKLIST.isEmpty()) {
          overworldBiomes.add(oreConfig.OVERWORLD_BIOME_BLACKLIST.toString().replaceAll("[\\[\\]]", ""));
        }
        if(!oreConfig.OVERWORLD_MOD_BLACKLIST.isEmpty()) {
          overworldBiomes.add(oreConfig.OVERWORLD_MOD_BLACKLIST.toString().replaceAll("[\\[\\]]", "") + ":ALL");
        }
        if((oreConfig.OVERWORLD_BIOME_BLACKLIST.isEmpty() && oreConfig.OVERWORLD_MOD_BLACKLIST.isEmpty()) && oreConfig.OVERWORLD_ACTIVE) {
          overworldBiomes.add(I18n.get("All Biomes"));
        }

        if(!oreConfig.NETHER_BIOME_BLACKLIST.isEmpty()) {
          netherBiomes.add(oreConfig.NETHER_BIOME_BLACKLIST.toString().replaceAll("[\\[\\]]", ""));
        }
        if(!oreConfig.NETHER_MOD_BLACKLIST.isEmpty()) {
          netherBiomes.add(oreConfig.NETHER_MOD_BLACKLIST.toString().replaceAll("[\\[\\]]", "") + ":ALL");
        }
        if((oreConfig.NETHER_BIOME_BLACKLIST.isEmpty() && oreConfig.NETHER_MOD_BLACKLIST.isEmpty()) && oreConfig.NETHER_ACTIVE) {
          netherBiomes.add(I18n.get("All Biomes"));
        }

        if(!oreConfig.END_BIOME_BLACKLIST.isEmpty()) {
          endBiomes.add(oreConfig.END_BIOME_BLACKLIST.toString().replaceAll("[\\[\\]]", ""));
        }
        if(!oreConfig.END_MOD_BLACKLIST.isEmpty()) {
          endBiomes.add(oreConfig.END_MOD_BLACKLIST.toString().replaceAll("[\\[\\]]", "") + ":ALL");
        }
        if((oreConfig.END_BIOME_BLACKLIST.isEmpty() && oreConfig.END_MOD_BLACKLIST.isEmpty()) && oreConfig.END_ACTIVE) {
          endBiomes.add(I18n.get("All Biomes"));
        }

        if (!overworldBiomes.isEmpty()) {
          if (!oreConfig.OVERWORLD_BIOMELIST_INVERT) {
            overworldBiomeList = "Overworld Biomes Blacklist: \u00a78" + overworldBiomes + "\u00a7r\n";
          } else {
            overworldBiomeList = "Overworld Biomes Whitelist: \u00a78" + overworldBiomes + "\u00a7r\n";
          }
        }
        if (!overworldBiomes.isEmpty() && (oreConfig.OVERWORLD_BIOME_BLACKLIST.isEmpty() || oreConfig.OVERWORLD_MOD_BLACKLIST.isEmpty())) {
          overworldBiomeList = "Overworld Biomes Whitelist: \u00a78" + overworldBiomes + "\u00a7r\n";
        }

        if (!netherBiomes.isEmpty()) {
          if (!oreConfig.NETHER_BIOMELIST_INVERT) {
            netherBiomeList = "Nether Biomes Blacklist: \u00a78" + netherBiomes + "\u00a7r\n";
          } else {
            netherBiomeList = "Nether Biomes Whitelist: \u00a78" + netherBiomes + "\u00a7r\n";
          }
        }
        if (!netherBiomes.isEmpty() && (oreConfig.NETHER_BIOME_BLACKLIST.isEmpty() || oreConfig.NETHER_MOD_BLACKLIST.isEmpty())) {
          netherBiomeList = "Nether Biomes Whitelist: \u00a78" + netherBiomes + "\u00a7r\n";
        }

        if (!endBiomes.isEmpty()) {
          if (!oreConfig.END_BIOMELIST_INVERT) {
            endBiomeList = "End Biomes Blacklist: \u00a78" + endBiomes + "\u00a7r\n";
          } else {
            endBiomeList = "End Biomes Whitelist: \u00a78" + endBiomes + "\u00a7r\n";
          }
        }
        if (!endBiomes.isEmpty() && (oreConfig.END_BIOME_BLACKLIST.isEmpty() || oreConfig.END_MOD_BLACKLIST.isEmpty())) {
          endBiomeList = "End Biomes Whitelist: \u00a78" + endBiomes + "\u00a7r\n";
        }

        registration.addIngredientInfo(stack, VanillaTypes.ITEM,
                new TextComponent("Spawns in the Overworld: ").append(String.valueOf(oreConfig.OVERWORLD_ACTIVE)).withStyle(ChatFormatting.DARK_GRAY),
                new TextComponent(overworldInfo),
                new TextComponent("Spawns in the Nether: ").append(String.valueOf(oreConfig.NETHER_ACTIVE)).withStyle(ChatFormatting.DARK_GRAY),
                new TextComponent(netherInfo),
                new TextComponent("Spawns in the End: ").append(String.valueOf(oreConfig.END_ACTIVE)).withStyle(ChatFormatting.DARK_GRAY),
                new TextComponent(endInfo),
                new TextComponent(overworldBiomeList),
                new TextComponent(netherBiomeList),
                new TextComponent(endBiomeList),
                new TextComponent("tooltip.emendatusenigmatica.ores.1"),
                TextComponent.EMPTY,
                new TextComponent("tooltip.emendatusenigmatica.ores.2"),
                TextComponent.EMPTY,
                new TextComponent("tooltip.emendatusenigmatica.ores.3")
        );
      });
    }
  }
}