/*
 * MIT License
 *
 * Copyright (c) 2020 Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.datagen;

import com.ridanisaurus.emendatusenigmatica.registries.BlockHandler;
import com.ridanisaurus.emendatusenigmatica.registries.ItemHandler;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class LangGen extends LanguageProvider
{
  public LangGen(DataGenerator gen)
  {
    super(gen, Reference.MOD_ID, "en_us");
  }

  @Override
  protected void addTranslations()
  {
    // Item Group
    add("itemGroup." + Reference.MOD_ID, Reference.MOD_NAME);

    // JEI Compat
    add("tooltip.emendatusenigmatica.ores.1", "Harvesting ores with Silktouch Enchanted tool will always drop a Chunk, while using non-Enchanted, or Fortune Enchanted tools will use the same logic used in Vanilla Minecraft.\n\n");
    add("tooltip.emendatusenigmatica.ores.2", "These Chunks act as an Ore Block where it can be smelted, or have its output doubled/tripled ..etc. through other means of ore processing setups.\n\n\n");
    add("tooltip.emendatusenigmatica.ores.3", "Furthermore, these Chunks can be turned into their Stone Block Ore variant by using the Stonecutter.");

    // Misc
    add(BlockHandler.ENIGMATIC_EXCHANGER.get(), "Enigmatic Exchanger");

    // Storage Blocks
    add(BlockHandler.BLOCK_COPPER.get(), "Copper Block");
    add(BlockHandler.BLOCK_ALUMINUM.get(), "Aluminum Block");
    add(BlockHandler.BLOCK_SILVER.get(), "Silver Block");
    add(BlockHandler.BLOCK_LEAD.get(), "Lead Block");
    add(BlockHandler.BLOCK_NICKEL.get(), "Nickel Block");
    add(BlockHandler.BLOCK_URANIUM.get(), "Uranium Block");
    add(BlockHandler.BLOCK_OSMIUM.get(), "Osmium Block");
    add(BlockHandler.BLOCK_TIN.get(), "Tin Block");
    add(BlockHandler.BLOCK_ZINC.get(), "Zinc Block");
    add(BlockHandler.BLOCK_BRONZE.get(), "Bronze Block");
    add(BlockHandler.BLOCK_BRASS.get(), "Brass Block");
    add(BlockHandler.BLOCK_CONSTANTAN.get(), "Constantan Block");
    add(BlockHandler.BLOCK_ELECTRUM.get(), "Electrum Block");
    add(BlockHandler.BLOCK_STEEL.get(), "Steel Block");

    // Ores
    add(BlockHandler.ORE_COAL.get(), "Coal Ore");
    add(BlockHandler.ORE_IRON.get(), "Iron Ore");
    add(BlockHandler.ORE_GOLD.get(), "Gold Ore");
    add(BlockHandler.ORE_DIAMOND.get(), "Diamond Ore");
    add(BlockHandler.ORE_EMERALD.get(), "Emerald Ore");
    add(BlockHandler.ORE_LAPIS.get(), "Lapis lazuli Ore");
    add(BlockHandler.ORE_REDSTONE.get(), "Redstone Ore");
    add(BlockHandler.ORE_COPPER.get(), "Copper Ore");
    add(BlockHandler.ORE_ALUMINUM.get(), "Aluminum Ore");
    add(BlockHandler.ORE_SILVER.get(), "Silver Ore");
    add(BlockHandler.ORE_LEAD.get(), "Lead Ore");
    add(BlockHandler.ORE_NICKEL.get(), "Nickel Ore");
    add(BlockHandler.ORE_URANIUM.get(), "Uranium Ore");
    add(BlockHandler.ORE_OSMIUM.get(), "Osmium Ore");
    add(BlockHandler.ORE_TIN.get(), "Tin Ore");
    add(BlockHandler.ORE_ZINC.get(), "Zinc Ore");
    add(BlockHandler.ORE_CERTUS_QUARTZ.get(), "Certus Quartz Ore");
    add(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ.get(), "Charged Certus Quartz Ore");
    add(BlockHandler.ORE_FLUORITE.get(), "Fluorite Ore");

    // Strata Ores - Andesite
    add(BlockHandler.ORE_COAL_ANDESITE.get(), "Coal Ore - Andesite");
    add(BlockHandler.ORE_IRON_ANDESITE.get(), "Iron Ore - Andesite");
    add(BlockHandler.ORE_GOLD_ANDESITE.get(), "Gold Ore - Andesite");
    add(BlockHandler.ORE_DIAMOND_ANDESITE.get(), "Diamond Ore - Andesite");
    add(BlockHandler.ORE_EMERALD_ANDESITE.get(), "Emerald Ore - Andesite");
    add(BlockHandler.ORE_LAPIS_ANDESITE.get(), "Lapis lazuli Ore - Andesite");
    add(BlockHandler.ORE_REDSTONE_ANDESITE.get(), "Redstone Ore - Andesite");
    add(BlockHandler.ORE_COPPER_ANDESITE.get(), "Copper Ore - Andesite");
    add(BlockHandler.ORE_ALUMINUM_ANDESITE.get(), "Aluminum Ore - Andesite");
    add(BlockHandler.ORE_SILVER_ANDESITE.get(), "Silver Ore - Andesite");
    add(BlockHandler.ORE_LEAD_ANDESITE.get(), "Lead Ore - Andesite");
    add(BlockHandler.ORE_NICKEL_ANDESITE.get(), "Nickel Ore - Andesite");
    add(BlockHandler.ORE_URANIUM_ANDESITE.get(), "Uranium Ore - Andesite");
    add(BlockHandler.ORE_OSMIUM_ANDESITE.get(), "Osmium Ore - Andesite");
    add(BlockHandler.ORE_TIN_ANDESITE.get(), "Tin Ore - Andesite");
    add(BlockHandler.ORE_ZINC_ANDESITE.get(), "Zinc Ore - Andesite");
    add(BlockHandler.ORE_CERTUS_QUARTZ_ANDESITE.get(), "Certus Quartz Ore - Andesite");
    add(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_ANDESITE.get(), "Charged Certus Quartz Ore - Andesite");
    add(BlockHandler.ORE_FLUORITE_ANDESITE.get(), "Fluorite Ore - Andesite");

    // Strata Ores - Gabbro
    add(BlockHandler.ORE_COAL_GABBRO.get(), "Coal Ore - Gabbro");
    add(BlockHandler.ORE_IRON_GABBRO.get(), "Iron Ore - Gabbro");
    add(BlockHandler.ORE_GOLD_GABBRO.get(), "Gold Ore - Gabbro");
    add(BlockHandler.ORE_DIAMOND_GABBRO.get(), "Diamond Ore - Gabbro");
    add(BlockHandler.ORE_EMERALD_GABBRO.get(), "Emerald Ore - Gabbro");
    add(BlockHandler.ORE_LAPIS_GABBRO.get(), "Lapis lazuli Ore - Gabbro");
    add(BlockHandler.ORE_REDSTONE_GABBRO.get(), "Redstone Ore - Gabbro");
    add(BlockHandler.ORE_COPPER_GABBRO.get(), "Copper Ore - Gabbro");
    add(BlockHandler.ORE_ALUMINUM_GABBRO.get(), "Aluminum Ore - Gabbro");
    add(BlockHandler.ORE_SILVER_GABBRO.get(), "Silver Ore - Gabbro");
    add(BlockHandler.ORE_LEAD_GABBRO.get(), "Lead Ore - Gabbro");
    add(BlockHandler.ORE_NICKEL_GABBRO.get(), "Nickel Ore - Gabbro");
    add(BlockHandler.ORE_URANIUM_GABBRO.get(), "Uranium Ore - Gabbro");
    add(BlockHandler.ORE_OSMIUM_GABBRO.get(), "Osmium Ore - Gabbro");
    add(BlockHandler.ORE_TIN_GABBRO.get(), "Tin Ore - Gabbro");
    add(BlockHandler.ORE_ZINC_GABBRO.get(), "Zinc Ore - Gabbro");
    add(BlockHandler.ORE_CERTUS_QUARTZ_GABBRO.get(), "Certus Quartz Ore - Gabbro");
    add(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_GABBRO.get(), "Charged Certus Quartz Ore - Gabbro");
    add(BlockHandler.ORE_FLUORITE_GABBRO.get(), "Fluorite Ore - Gabbro");

    // Strata Ores - Limestone (Create)
    add(BlockHandler.ORE_COAL_C_LIMESTONE.get(), "Coal Ore - Limestone");
    add(BlockHandler.ORE_IRON_C_LIMESTONE.get(), "Iron Ore - Limestone");
    add(BlockHandler.ORE_GOLD_C_LIMESTONE.get(), "Gold Ore - Limestone");
    add(BlockHandler.ORE_DIAMOND_C_LIMESTONE.get(), "Diamond Ore - Limestone");
    add(BlockHandler.ORE_EMERALD_C_LIMESTONE.get(), "Emerald Ore - Limestone");
    add(BlockHandler.ORE_LAPIS_C_LIMESTONE.get(), "Lapis lazuli Ore - Limestone");
    add(BlockHandler.ORE_REDSTONE_C_LIMESTONE.get(), "Redstone Ore - Limestone");
    add(BlockHandler.ORE_COPPER_C_LIMESTONE.get(), "Copper Ore - Limestone");
    add(BlockHandler.ORE_ALUMINUM_C_LIMESTONE.get(), "Aluminum Ore - Limestone");
    add(BlockHandler.ORE_SILVER_C_LIMESTONE.get(), "Silver Ore - Limestone");
    add(BlockHandler.ORE_LEAD_C_LIMESTONE.get(), "Lead Ore - Limestone");
    add(BlockHandler.ORE_NICKEL_C_LIMESTONE.get(), "Nickel Ore - Limestone");
    add(BlockHandler.ORE_URANIUM_C_LIMESTONE.get(), "Uranium Ore - Limestone");
    add(BlockHandler.ORE_OSMIUM_C_LIMESTONE.get(), "Osmium Ore - Limestone");
    add(BlockHandler.ORE_TIN_C_LIMESTONE.get(), "Tin Ore - Limestone");
    add(BlockHandler.ORE_ZINC_C_LIMESTONE.get(), "Zinc Ore - Limestone");
    add(BlockHandler.ORE_CERTUS_QUARTZ_C_LIMESTONE.get(), "Certus Quartz Ore - Limestone");
    add(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_C_LIMESTONE.get(), "Charged Certus Quartz Ore - Limestone");
    add(BlockHandler.ORE_FLUORITE_C_LIMESTONE.get(), "Fluorite Ore - Limestone");

    // Strata Ores - Scoria
    add(BlockHandler.ORE_COAL_SCORIA.get(), "Coal Ore - Scoria");
    add(BlockHandler.ORE_IRON_SCORIA.get(), "Iron Ore - Scoria");
    add(BlockHandler.ORE_GOLD_SCORIA.get(), "Gold Ore - Scoria");
    add(BlockHandler.ORE_DIAMOND_SCORIA.get(), "Diamond Ore - Scoria");
    add(BlockHandler.ORE_EMERALD_SCORIA.get(), "Emerald Ore - Scoria");
    add(BlockHandler.ORE_LAPIS_SCORIA.get(), "Lapis lazuli Ore - Scoria");
    add(BlockHandler.ORE_REDSTONE_SCORIA.get(), "Redstone Ore - Scoria");
    add(BlockHandler.ORE_COPPER_SCORIA.get(), "Copper Ore - Scoria");
    add(BlockHandler.ORE_ALUMINUM_SCORIA.get(), "Aluminum Ore - Scoria");
    add(BlockHandler.ORE_SILVER_SCORIA.get(), "Silver Ore - Scoria");
    add(BlockHandler.ORE_LEAD_SCORIA.get(), "Lead Ore - Scoria");
    add(BlockHandler.ORE_NICKEL_SCORIA.get(), "Nickel Ore - Scoria");
    add(BlockHandler.ORE_URANIUM_SCORIA.get(), "Uranium Ore - Scoria");
    add(BlockHandler.ORE_OSMIUM_SCORIA.get(), "Osmium Ore - Scoria");
    add(BlockHandler.ORE_TIN_SCORIA.get(), "Tin Ore - Scoria");
    add(BlockHandler.ORE_ZINC_SCORIA.get(), "Zinc Ore - Scoria");
    add(BlockHandler.ORE_CERTUS_QUARTZ_SCORIA.get(), "Certus Quartz Ore - Scoria");
    add(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_SCORIA.get(), "Charged Certus Quartz Ore - Scoria");
    add(BlockHandler.ORE_FLUORITE_SCORIA.get(), "Fluorite Ore - Scoria");

    // Strata Ores - Weathered Limestone
    add(BlockHandler.ORE_COAL_WEATHERED_LIMESTONE.get(), "Coal Ore - Weathered Limestone");
    add(BlockHandler.ORE_IRON_WEATHERED_LIMESTONE.get(), "Iron Ore - Weathered Limestone");
    add(BlockHandler.ORE_GOLD_WEATHERED_LIMESTONE.get(), "Gold Ore - Weathered Limestone");
    add(BlockHandler.ORE_DIAMOND_WEATHERED_LIMESTONE.get(), "Diamond Ore - Weathered Limestone");
    add(BlockHandler.ORE_EMERALD_WEATHERED_LIMESTONE.get(), "Emerald Ore - Weathered Limestone");
    add(BlockHandler.ORE_LAPIS_WEATHERED_LIMESTONE.get(), "Lapis lazuli Ore - Weathered Limestone");
    add(BlockHandler.ORE_REDSTONE_WEATHERED_LIMESTONE.get(), "Redstone Ore - Weathered Limestone");
    add(BlockHandler.ORE_COPPER_WEATHERED_LIMESTONE.get(), "Copper Ore - Weathered Limestone");
    add(BlockHandler.ORE_ALUMINUM_WEATHERED_LIMESTONE.get(), "Aluminum Ore - Weathered Limestone");
    add(BlockHandler.ORE_SILVER_WEATHERED_LIMESTONE.get(), "Silver Ore - Weathered Limestone");
    add(BlockHandler.ORE_LEAD_WEATHERED_LIMESTONE.get(), "Lead Ore - Weathered Limestone");
    add(BlockHandler.ORE_NICKEL_WEATHERED_LIMESTONE.get(), "Nickel Ore - Weathered Limestone");
    add(BlockHandler.ORE_URANIUM_WEATHERED_LIMESTONE.get(), "Uranium Ore - Weathered Limestone");
    add(BlockHandler.ORE_OSMIUM_WEATHERED_LIMESTONE.get(), "Osmium Ore - Weathered Limestone");
    add(BlockHandler.ORE_TIN_WEATHERED_LIMESTONE.get(), "Tin Ore - Weathered Limestone");
    add(BlockHandler.ORE_ZINC_WEATHERED_LIMESTONE.get(), "Zinc Ore - Weathered Limestone");
    add(BlockHandler.ORE_CERTUS_QUARTZ_WEATHERED_LIMESTONE.get(), "Certus Quartz Ore - Weathered Limestone");
    add(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_WEATHERED_LIMESTONE.get(), "Charged Certus Quartz Ore - Weathered Limestone");
    add(BlockHandler.ORE_FLUORITE_WEATHERED_LIMESTONE.get(), "Fluorite Ore - Weathered Limestone");

    // Strata Ores - Jasper
    add(BlockHandler.ORE_COAL_JASPER.get(), "Coal Ore - Jasper");
    add(BlockHandler.ORE_IRON_JASPER.get(), "Iron Ore - Jasper");
    add(BlockHandler.ORE_GOLD_JASPER.get(), "Gold Ore - Jasper");
    add(BlockHandler.ORE_DIAMOND_JASPER.get(), "Diamond Ore - Jasper");
    add(BlockHandler.ORE_EMERALD_JASPER.get(), "Emerald Ore - Jasper");
    add(BlockHandler.ORE_LAPIS_JASPER.get(), "Lapis lazuli Ore - Jasper");
    add(BlockHandler.ORE_REDSTONE_JASPER.get(), "Redstone Ore - Jasper");
    add(BlockHandler.ORE_COPPER_JASPER.get(), "Copper Ore - Jasper");
    add(BlockHandler.ORE_ALUMINUM_JASPER.get(), "Aluminum Ore - Jasper");
    add(BlockHandler.ORE_SILVER_JASPER.get(), "Silver Ore - Jasper");
    add(BlockHandler.ORE_LEAD_JASPER.get(), "Lead Ore - Jasper");
    add(BlockHandler.ORE_NICKEL_JASPER.get(), "Nickel Ore - Jasper");
    add(BlockHandler.ORE_URANIUM_JASPER.get(), "Uranium Ore - Jasper");
    add(BlockHandler.ORE_OSMIUM_JASPER.get(), "Osmium Ore - Jasper");
    add(BlockHandler.ORE_TIN_JASPER.get(), "Tin Ore - Jasper");
    add(BlockHandler.ORE_ZINC_JASPER.get(), "Zinc Ore - Jasper");
    add(BlockHandler.ORE_CERTUS_QUARTZ_JASPER.get(), "Certus Quartz Ore - Jasper");
    add(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_JASPER.get(), "Charged Certus Quartz Ore - Jasper");
    add(BlockHandler.ORE_FLUORITE_JASPER.get(), "Fluorite Ore - Jasper");

    // Strata Ores - Limestone (Quark)
    add(BlockHandler.ORE_COAL_Q_LIMESTONE.get(), "Coal Ore - Limestone");
    add(BlockHandler.ORE_IRON_Q_LIMESTONE.get(), "Iron Ore - Limestone");
    add(BlockHandler.ORE_GOLD_Q_LIMESTONE.get(), "Gold Ore - Limestone");
    add(BlockHandler.ORE_DIAMOND_Q_LIMESTONE.get(), "Diamond Ore - Limestone");
    add(BlockHandler.ORE_EMERALD_Q_LIMESTONE.get(), "Emerald Ore - Limestone");
    add(BlockHandler.ORE_LAPIS_Q_LIMESTONE.get(), "Lapis lazuli Ore - Limestone");
    add(BlockHandler.ORE_REDSTONE_Q_LIMESTONE.get(), "Redstone Ore - Limestone");
    add(BlockHandler.ORE_COPPER_Q_LIMESTONE.get(), "Copper Ore - Limestone");
    add(BlockHandler.ORE_ALUMINUM_Q_LIMESTONE.get(), "Aluminum Ore - Limestone");
    add(BlockHandler.ORE_SILVER_Q_LIMESTONE.get(), "Silver Ore - Limestone");
    add(BlockHandler.ORE_LEAD_Q_LIMESTONE.get(), "Lead Ore - Limestone");
    add(BlockHandler.ORE_NICKEL_Q_LIMESTONE.get(), "Nickel Ore - Limestone");
    add(BlockHandler.ORE_URANIUM_Q_LIMESTONE.get(), "Uranium Ore - Limestone");
    add(BlockHandler.ORE_OSMIUM_Q_LIMESTONE.get(), "Osmium Ore - Limestone");
    add(BlockHandler.ORE_TIN_Q_LIMESTONE.get(), "Tin Ore - Limestone");
    add(BlockHandler.ORE_ZINC_Q_LIMESTONE.get(), "Zinc Ore - Limestone");
    add(BlockHandler.ORE_CERTUS_QUARTZ_Q_LIMESTONE.get(), "Certus Quartz Ore - Limestone");
    add(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_Q_LIMESTONE.get(), "Charged Certus Quartz Ore - Limestone");
    add(BlockHandler.ORE_FLUORITE_Q_LIMESTONE.get(), "Fluorite Ore - Limestone");

    // Strata Ores - Marble
    add(BlockHandler.ORE_COAL_MARBLE.get(), "Coal Ore - Marble");
    add(BlockHandler.ORE_IRON_MARBLE.get(), "Iron Ore - Marble");
    add(BlockHandler.ORE_GOLD_MARBLE.get(), "Gold Ore - Marble");
    add(BlockHandler.ORE_DIAMOND_MARBLE.get(), "Diamond Ore - Marble");
    add(BlockHandler.ORE_EMERALD_MARBLE.get(), "Emerald Ore - Marble");
    add(BlockHandler.ORE_LAPIS_MARBLE.get(), "Lapis lazuli Ore - Marble");
    add(BlockHandler.ORE_REDSTONE_MARBLE.get(), "Redstone Ore - Marble");
    add(BlockHandler.ORE_COPPER_MARBLE.get(), "Copper Ore - Marble");
    add(BlockHandler.ORE_ALUMINUM_MARBLE.get(), "Aluminum Ore - Marble");
    add(BlockHandler.ORE_SILVER_MARBLE.get(), "Silver Ore - Marble");
    add(BlockHandler.ORE_LEAD_MARBLE.get(), "Lead Ore - Marble");
    add(BlockHandler.ORE_NICKEL_MARBLE.get(), "Nickel Ore - Marble");
    add(BlockHandler.ORE_URANIUM_MARBLE.get(), "Uranium Ore - Marble");
    add(BlockHandler.ORE_OSMIUM_MARBLE.get(), "Osmium Ore - Marble");
    add(BlockHandler.ORE_TIN_MARBLE.get(), "Tin Ore - Marble");
    add(BlockHandler.ORE_ZINC_MARBLE.get(), "Zinc Ore - Marble");
    add(BlockHandler.ORE_CERTUS_QUARTZ_MARBLE.get(), "Certus Quartz Ore - Marble");
    add(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_MARBLE.get(), "Charged Certus Quartz Ore - Marble");
    add(BlockHandler.ORE_FLUORITE_MARBLE.get(), "Fluorite Ore - Marble");

    // Strata Ores - Slate
    add(BlockHandler.ORE_COAL_SLATE.get(), "Coal Ore - Slate");
    add(BlockHandler.ORE_IRON_SLATE.get(), "Iron Ore - Slate");
    add(BlockHandler.ORE_GOLD_SLATE.get(), "Gold Ore - Slate");
    add(BlockHandler.ORE_DIAMOND_SLATE.get(), "Diamond Ore - Slate");
    add(BlockHandler.ORE_EMERALD_SLATE.get(), "Emerald Ore - Slate");
    add(BlockHandler.ORE_LAPIS_SLATE.get(), "Lapis lazuli Ore - Slate");
    add(BlockHandler.ORE_REDSTONE_SLATE.get(), "Redstone Ore - Slate");
    add(BlockHandler.ORE_COPPER_SLATE.get(), "Copper Ore - Slate");
    add(BlockHandler.ORE_ALUMINUM_SLATE.get(), "Aluminum Ore - Slate");
    add(BlockHandler.ORE_SILVER_SLATE.get(), "Silver Ore - Slate");
    add(BlockHandler.ORE_LEAD_SLATE.get(), "Lead Ore - Slate");
    add(BlockHandler.ORE_NICKEL_SLATE.get(), "Nickel Ore - Slate");
    add(BlockHandler.ORE_URANIUM_SLATE.get(), "Uranium Ore - Slate");
    add(BlockHandler.ORE_OSMIUM_SLATE.get(), "Osmium Ore - Slate");
    add(BlockHandler.ORE_TIN_SLATE.get(), "Tin Ore - Slate");
    add(BlockHandler.ORE_ZINC_SLATE.get(), "Zinc Ore - Slate");
    add(BlockHandler.ORE_CERTUS_QUARTZ_SLATE.get(), "Certus Quartz Ore - Slate");
    add(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_SLATE.get(), "Charged Certus Quartz Ore - Slate");
    add(BlockHandler.ORE_FLUORITE_SLATE.get(), "Fluorite Ore - Slate");

    // Ingots
    add(ItemHandler.INGOT_COPPER.get(), "Copper Ingot");
    add(ItemHandler.INGOT_ALUMINUM.get(), "Aluminum Ingot");
    add(ItemHandler.INGOT_SILVER.get(), "Silver Ingot");
    add(ItemHandler.INGOT_LEAD.get(), "Lead Ingot");
    add(ItemHandler.INGOT_NICKEL.get(), "Nickel Ingot");
    add(ItemHandler.INGOT_URANIUM.get(), "Uranium Ingot");
    add(ItemHandler.INGOT_OSMIUM.get(), "Osmium Ingot");
    add(ItemHandler.INGOT_TIN.get(), "Tin Ingot");
    add(ItemHandler.INGOT_ZINC.get(), "Zinc Ingot");
    add(ItemHandler.INGOT_BRONZE.get(), "Bronze Ingot");
    add(ItemHandler.INGOT_BRASS.get(), "Brass Ingot");
    add(ItemHandler.INGOT_CONSTANTAN.get(), "Constantan Ingot");
    add(ItemHandler.INGOT_ELECTRUM.get(), "Electrum Ingot");
    add(ItemHandler.INGOT_STEEL.get(), "Steel Ingot");

    // Nuggets
    add(ItemHandler.NUGGET_COPPER.get(), "Copper Nugget");
    add(ItemHandler.NUGGET_ALUMINUM.get(), "Aluminum Nugget");
    add(ItemHandler.NUGGET_SILVER.get(), "Silver Nugget");
    add(ItemHandler.NUGGET_LEAD.get(), "Lead Nugget");
    add(ItemHandler.NUGGET_NICKEL.get(), "Nickel Nugget");
    add(ItemHandler.NUGGET_URANIUM.get(), "Uranium Nugget");
    add(ItemHandler.NUGGET_OSMIUM.get(), "Osmium Nugget");
    add(ItemHandler.NUGGET_TIN.get(), "Tin Nugget");
    add(ItemHandler.NUGGET_ZINC.get(), "Zinc Nugget");
    add(ItemHandler.NUGGET_BRONZE.get(), "Bronze Nugget");
    add(ItemHandler.NUGGET_BRASS.get(), "Brass Nugget");
    add(ItemHandler.NUGGET_CONSTANTAN.get(), "Constantan Nugget");
    add(ItemHandler.NUGGET_ELECTRUM.get(), "Electrum Nugget");
    add(ItemHandler.NUGGET_STEEL.get(), "Steel Nugget");

    // Dusts
    add(ItemHandler.DUST_COPPER.get(), "Copper Dust");
    add(ItemHandler.DUST_ALUMINUM.get(), "Aluminum Dust");
    add(ItemHandler.DUST_SILVER.get(), "Silver Dust");
    add(ItemHandler.DUST_LEAD.get(), "Lead Dust");
    add(ItemHandler.DUST_NICKEL.get(), "Nickel Dust");
    add(ItemHandler.DUST_URANIUM.get(), "Uranium Dust");
    add(ItemHandler.DUST_OSMIUM.get(), "Osmium Dust");
    add(ItemHandler.DUST_TIN.get(), "Tin Dust");
    add(ItemHandler.DUST_ZINC.get(), "Zinc Dust");
    add(ItemHandler.DUST_BRONZE.get(), "Bronze Dust");
    add(ItemHandler.DUST_BRASS.get(), "Brass Dust");
    add(ItemHandler.DUST_CONSTANTAN.get(), "Constantan Dust");
    add(ItemHandler.DUST_ELECTRUM.get(), "Electrum Dust");
    add(ItemHandler.DUST_STEEL.get(), "Steel Dust");
    add(ItemHandler.DUST_CHARCOAL.get(), "Charcoal Dust");
    add(ItemHandler.DUST_COAL.get(), "Coal Dust");
    add(ItemHandler.DUST_DIAMOND.get(), "Diamond Dust");
    add(ItemHandler.DUST_EMERALD.get(), "Emerald Dust");
    add(ItemHandler.DUST_GOLD.get(), "Gold Dust");
    add(ItemHandler.DUST_IRON.get(), "Iron Dust");
    add(ItemHandler.DUST_LAPIS.get(), "Lapis lazuli Dust");
    add(ItemHandler.DUST_OBSIDIAN.get(), "Obsidian Dust");
    add(ItemHandler.DUST_QUARTZ.get(), "Quartz Dust");
    add(ItemHandler.DUST_CERTUS_QUARTZ.get(), "Certus Quartz Dust");
    add(ItemHandler.DUST_FLUIX.get(), "Fluix Dust");
    add(ItemHandler.DUST_ENDER.get(), "Ender Dust");
    add(ItemHandler.DUST_COKE.get(), "Coke Dust");
    add(ItemHandler.DUST_GRAPHITE.get(), "Graphite Dust");
    add(ItemHandler.DUST_LITHIUM.get(), "Lithium Dust");
    add(ItemHandler.DUST_SALTPETER.get(), "Saltpeter Dust");
    add(ItemHandler.DUST_SULFUR.get(), "Sulfur Dust");
    add(ItemHandler.DUST_FLUORITE.get(), "Fluorite Dust");

    // Gems
    add(ItemHandler.GEM_CERTUS_QUARTZ.get(), "Certus Quartz Crystal");
    add(ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get(), "Charged Certus Quartz Crystal");
    add(ItemHandler.GEM_FLUIX.get(), "Fluix Crystal");
    add(ItemHandler.GEM_FLUORITE.get(), "Fluorite");

    // Plates
    add(ItemHandler.PLATE_COPPER.get(), "Copper Plate");
    add(ItemHandler.PLATE_ALUMINUM.get(), "Aluminum Plate");
    add(ItemHandler.PLATE_SILVER.get(), "Silver Plate");
    add(ItemHandler.PLATE_LEAD.get(), "Lead Plate");
    add(ItemHandler.PLATE_NICKEL.get(), "Nickel Plate");
    add(ItemHandler.PLATE_URANIUM.get(), "Uranium Plate");
    add(ItemHandler.PLATE_OSMIUM.get(), "Osmium Plate");
    add(ItemHandler.PLATE_TIN.get(), "Tin Plate");
    add(ItemHandler.PLATE_ZINC.get(), "Zinc Plate");
    add(ItemHandler.PLATE_BRONZE.get(), "Bronze Plate");
    add(ItemHandler.PLATE_BRASS.get(), "Brass Plate");
    add(ItemHandler.PLATE_CONSTANTAN.get(), "Constantan Plate");
    add(ItemHandler.PLATE_ELECTRUM.get(), "Electrum Plate");
    add(ItemHandler.PLATE_STEEL.get(), "Steel Plate");
    add(ItemHandler.PLATE_GOLD.get(), "Gold Plate");
    add(ItemHandler.PLATE_IRON.get(), "Iron Plate");
    add(ItemHandler.PLATE_LAPIS.get(), "Lapis lazuli Plate");

    // Chunks
    add(ItemHandler.CHUNK_COAL.get(), "Coal Chunk");
    add(ItemHandler.CHUNK_IRON.get(), "Iron Chunk");
    add(ItemHandler.CHUNK_GOLD.get(), "Gold Chunk");
    add(ItemHandler.CHUNK_DIAMOND.get(), "Diamond Chunk");
    add(ItemHandler.CHUNK_EMERALD.get(), "Emerald Chunk");
    add(ItemHandler.CHUNK_LAPIS.get(), "Lapis lazuli Chunk");
    add(ItemHandler.CHUNK_REDSTONE.get(), "Redstone Chunk");
    add(ItemHandler.CHUNK_COPPER.get(), "Copper Chunk");
    add(ItemHandler.CHUNK_ALUMINUM.get(), "Aluminum Chunk");
    add(ItemHandler.CHUNK_SILVER.get(), "Silver Chunk");
    add(ItemHandler.CHUNK_LEAD.get(), "Lead Chunk");
    add(ItemHandler.CHUNK_NICKEL.get(), "Nickel Chunk");
    add(ItemHandler.CHUNK_URANIUM.get(), "Uranium Chunk");
    add(ItemHandler.CHUNK_OSMIUM.get(), "Osmium Chunk");
    add(ItemHandler.CHUNK_TIN.get(), "Tin Chunk");
    add(ItemHandler.CHUNK_ZINC.get(), "Zinc Chunk");
    add(ItemHandler.CHUNK_CERTUS_QUARTZ.get(), "Certus Quartz Chunk");
    add(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get(), "Charged Certus Quartz Chunk");
    add(ItemHandler.CHUNK_FLUORITE.get(), "Fluorite Chunk");
  }
}