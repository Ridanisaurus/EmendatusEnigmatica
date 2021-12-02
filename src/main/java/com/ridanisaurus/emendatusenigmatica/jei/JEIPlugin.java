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

/*

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
          overworldBiomes.add(I18n.format("All Biomes"));
        }

        if(!oreConfig.NETHER_BIOME_BLACKLIST.isEmpty()) {
          netherBiomes.add(oreConfig.NETHER_BIOME_BLACKLIST.toString().replaceAll("[\\[\\]]", ""));
        }
        if(!oreConfig.NETHER_MOD_BLACKLIST.isEmpty()) {
          netherBiomes.add(oreConfig.NETHER_MOD_BLACKLIST.toString().replaceAll("[\\[\\]]", "") + ":ALL");
        }
        if((oreConfig.NETHER_BIOME_BLACKLIST.isEmpty() && oreConfig.NETHER_MOD_BLACKLIST.isEmpty()) && oreConfig.NETHER_ACTIVE) {
          netherBiomes.add(I18n.format("All Biomes"));
        }

        if(!oreConfig.END_BIOME_BLACKLIST.isEmpty()) {
          endBiomes.add(oreConfig.END_BIOME_BLACKLIST.toString().replaceAll("[\\[\\]]", ""));
        }
        if(!oreConfig.END_MOD_BLACKLIST.isEmpty()) {
          endBiomes.add(oreConfig.END_MOD_BLACKLIST.toString().replaceAll("[\\[\\]]", "") + ":ALL");
        }
        if((oreConfig.END_BIOME_BLACKLIST.isEmpty() && oreConfig.END_MOD_BLACKLIST.isEmpty()) && oreConfig.END_ACTIVE) {
          endBiomes.add(I18n.format("All Biomes"));
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
                "Spawns in the Overworld: \u00a78" + oreConfig.OVERWORLD_ACTIVE + "\u00a7r",
                overworldInfo + "\n",
                "Spawns in the Nether: \u00a78" + oreConfig.NETHER_ACTIVE + "\u00a7r",
                netherInfo + "\n",
                "Spawns in the End: \u00a78" + oreConfig.END_ACTIVE + "\u00a7r",
                endInfo + "\n",
                overworldBiomeList,
                netherBiomeList,
                endBiomeList,
                "tooltip.emendatusenigmatica.ores.1",
                "\n",
                "tooltip.emendatusenigmatica.ores.2",
                "\n",
                "tooltip.emendatusenigmatica.ores.3");
      });
    }
  }
}*/
