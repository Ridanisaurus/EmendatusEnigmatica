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
import com.ridanisaurus.emendatusenigmatica.registries.OreHandler;
import com.ridanisaurus.emendatusenigmatica.util.*;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Consumer;

public class RecipesGen extends RecipeProvider {

  public RecipesGen(DataGenerator gen) {
    super(gen);
  }

  @Override
  protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

    // Shaped
    ShapedRecipeBuilder.shapedRecipe(BlockHandler.ENIGMATIC_EXCHANGER.get())
            .patternLine("III")
            .patternLine("RDR")
            .patternLine("BBB")
            .key('I', Tags.Items.INGOTS_IRON)
            .key('R', Tags.Items.DUSTS_REDSTONE)
            .key('D', Tags.Items.GEMS_DIAMOND)
            .key('B', Tags.Items.STORAGE_BLOCKS_IRON)
            .setGroup("emendatusenigmatica")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer);

    /*// Ingot from Block
    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.INGOT_COPPER.get(), 9)
            .addIngredient(BlockHandler.BLOCK_COPPER.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "copper_ingot_from_copper_block"));

    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.INGOT_ALUMINUM.get(), 9)
            .addIngredient(BlockHandler.BLOCK_ALUMINUM.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "aluminum_ingot_from_aluminum_block"));

    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.INGOT_SILVER.get(), 9)
            .addIngredient(BlockHandler.BLOCK_SILVER.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "silver_ingot_from_silver_block"));

    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.INGOT_LEAD.get(), 9)
            .addIngredient(BlockHandler.BLOCK_LEAD.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "lead_ingot_from_lead_block"));

    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.INGOT_NICKEL.get(), 9)
            .addIngredient(BlockHandler.BLOCK_NICKEL.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "nickel_ingot_from_nickel_block"));

    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.INGOT_URANIUM.get(), 9)
            .addIngredient(BlockHandler.BLOCK_URANIUM.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "uranium_ingot_from_uranium_block"));

    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.INGOT_OSMIUM.get(), 9)
            .addIngredient(BlockHandler.BLOCK_OSMIUM.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "osmium_ingot_from_osmium_block"));

    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.INGOT_TIN.get(), 9)
            .addIngredient(BlockHandler.BLOCK_TIN.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "tin_ingot_from_tin_block"));

    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.INGOT_ZINC.get(), 9)
            .addIngredient(BlockHandler.BLOCK_ZINC.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "zinc_ingot_from_zinc_block"));

    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.INGOT_BRONZE.get(), 9)
            .addIngredient(BlockHandler.BLOCK_BRONZE.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "bronze_ingot_from_bronze_block"));

    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.INGOT_BRASS.get(), 9)
            .addIngredient(BlockHandler.BLOCK_BRASS.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "brass_ingot_from_brass_block"));

    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.INGOT_CONSTANTAN.get(), 9)
            .addIngredient(BlockHandler.BLOCK_CONSTANTAN.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "constantan_ingot_from_constantan_block"));

    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.INGOT_ELECTRUM.get(), 9)
            .addIngredient(BlockHandler.BLOCK_ELECTRUM.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "electrum_ingot_from_electrum_block"));

    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.INGOT_STEEL.get(), 9)
            .addIngredient(BlockHandler.BLOCK_STEEL.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "steel_ingot_from_steel_block"));*/

    /*// Ingot from Nuggets
    ShapedRecipeBuilder.shapedRecipe(ItemHandler.INGOT_COPPER.get())
            .key('#', ItemHandler.NUGGET_COPPER.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "copper_ingot_from_nuggets"));

    ShapedRecipeBuilder.shapedRecipe(ItemHandler.INGOT_ALUMINUM.get())
            .key('#', ItemHandler.NUGGET_ALUMINUM.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "aluminum_ingot_from_nuggets"));

    ShapedRecipeBuilder.shapedRecipe(ItemHandler.INGOT_SILVER.get())
            .key('#', ItemHandler.NUGGET_SILVER.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "silver_ingot_from_nuggets"));

    ShapedRecipeBuilder.shapedRecipe(ItemHandler.INGOT_LEAD.get())
            .key('#', ItemHandler.NUGGET_LEAD.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "lead_ingot_from_nuggets"));

    ShapedRecipeBuilder.shapedRecipe(ItemHandler.INGOT_NICKEL.get())
            .key('#', ItemHandler.NUGGET_NICKEL.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "nickel_ingot_from_nuggets"));

    ShapedRecipeBuilder.shapedRecipe(ItemHandler.INGOT_URANIUM.get())
            .key('#', ItemHandler.NUGGET_URANIUM.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "uranium_ingot_from_nuggets"));

    ShapedRecipeBuilder.shapedRecipe(ItemHandler.INGOT_OSMIUM.get())
            .key('#', ItemHandler.NUGGET_OSMIUM.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "osmium_ingot_from_nuggets"));

    ShapedRecipeBuilder.shapedRecipe(ItemHandler.INGOT_TIN.get())
            .key('#', ItemHandler.NUGGET_TIN.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "tin_ingot_from_nuggets"));

    ShapedRecipeBuilder.shapedRecipe(ItemHandler.INGOT_ZINC.get())
            .key('#', ItemHandler.NUGGET_ZINC.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "zinc_ingot_from_nuggets"));

    ShapedRecipeBuilder.shapedRecipe(ItemHandler.INGOT_BRONZE.get())
            .key('#', ItemHandler.NUGGET_BRONZE.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "bronze_ingot_from_nuggets"));

    ShapedRecipeBuilder.shapedRecipe(ItemHandler.INGOT_BRASS.get())
            .key('#', ItemHandler.NUGGET_BRASS.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "brass_ingot_from_nuggets"));

    ShapedRecipeBuilder.shapedRecipe(ItemHandler.INGOT_CONSTANTAN.get())
            .key('#', ItemHandler.NUGGET_CONSTANTAN.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "constantan_ingot_from_nuggets"));

    ShapedRecipeBuilder.shapedRecipe(ItemHandler.INGOT_ELECTRUM.get())
            .key('#', ItemHandler.NUGGET_ELECTRUM.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "electrum_ingot_from_nuggets"));

    ShapedRecipeBuilder.shapedRecipe(ItemHandler.INGOT_STEEL.get())
            .key('#', ItemHandler.NUGGET_STEEL.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "steel_ingot_from_nuggets"));*/

    /*// Ore from Chunk in Stonecutter
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COAL.get()), Blocks.COAL_ORE)
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ore_from_chunk/coal/vanilla"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_IRON.get()), Blocks.IRON_ORE)
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ore_from_chunk/iron/vanilla"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_GOLD.get()), Blocks.GOLD_ORE)
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ore_from_chunk/gold/vanilla"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_DIAMOND.get()), Blocks.DIAMOND_ORE)
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ore_from_chunk/diamond/vanilla"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_EMERALD.get()), Blocks.EMERALD_ORE)
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ore_from_chunk/emerald/vanilla"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LAPIS.get()), Blocks.LAPIS_ORE)
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ore_from_chunk/lapis/vanilla"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_REDSTONE.get()), Blocks.REDSTONE_ORE)
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ore_from_chunk/redstone/vanilla"));*/

    /*for (Strata stratum : Strata.values()) {
      for (Materials material : Materials.values()) {
        SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(material.chunk.get()), OreHandler.oreBlockTable.get().get(stratum, ore).get())
                .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
                .build(consumer, new ResourceLocation(Reference.MOD_ID, "ore_from_chunk/" + ore.id + "/" + stratum.id));
      }
    }*/

    /*// Ingot+Gems from Dusts
    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_COPPER.get()), ItemHandler.INGOT_COPPER.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "copper_ingot_from_dust_from_smelting"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_COPPER.get()), ItemHandler.INGOT_COPPER.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "copper_ingot_from_dust_from_blast"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_ALUMINUM.get()), ItemHandler.INGOT_ALUMINUM.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "aluminum_ingot_from_dust_from_smelting"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_ALUMINUM.get()), ItemHandler.INGOT_ALUMINUM.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "aluminum_ingot_from_dust_from_blast"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_SILVER.get()), ItemHandler.INGOT_SILVER.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "silver_ingot_from_dust_from_smelting"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_SILVER.get()), ItemHandler.INGOT_SILVER.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "silver_ingot_from_dust_from_blast"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_LEAD.get()), ItemHandler.INGOT_LEAD.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "lead_ingot_from_dust_from_smelting"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_LEAD.get()), ItemHandler.INGOT_LEAD.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "lead_ingot_from_dust_from_blast"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_NICKEL.get()), ItemHandler.INGOT_NICKEL.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "nickel_ingot_from_dust_from_smelting"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_NICKEL.get()), ItemHandler.INGOT_NICKEL.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "nickel_ingot_from_dust_from_blast"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_URANIUM.get()), ItemHandler.INGOT_URANIUM.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "uranium_ingot_from_dust_from_smelting"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_URANIUM.get()), ItemHandler.INGOT_URANIUM.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "uranium_ingot_from_dust_from_blast"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_OSMIUM.get()), ItemHandler.INGOT_OSMIUM.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "osmium_ingot_from_dust_from_smelting"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_OSMIUM.get()), ItemHandler.INGOT_OSMIUM.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "osmium_ingot_from_dust_from_blast"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_TIN.get()), ItemHandler.INGOT_TIN.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "tin_ingot_from_dust_from_smelting"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_TIN.get()), ItemHandler.INGOT_TIN.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "tin_ingot_from_dust_from_blast"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_ZINC.get()), ItemHandler.INGOT_ZINC.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "zinc_ingot_from_dust_from_smelting"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_ZINC.get()), ItemHandler.INGOT_ZINC.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "zinc_ingot_from_dust_from_blast"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_BRONZE.get()), ItemHandler.INGOT_BRONZE.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "bronze_ingot_from_dust_from_smelting"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_BRONZE.get()), ItemHandler.INGOT_BRONZE.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "bronze_ingot_from_dust_from_blast"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_BRASS.get()), ItemHandler.INGOT_BRASS.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "brass_ingot_from_dust_from_smelting"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_BRASS.get()), ItemHandler.INGOT_BRASS.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "brass_ingot_from_dust_from_blast"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_CONSTANTAN.get()), ItemHandler.INGOT_CONSTANTAN.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "constantan_ingot_from_dust_from_smelting"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_CONSTANTAN.get()), ItemHandler.INGOT_CONSTANTAN.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "constantan_ingot_from_dust_from_blast"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_ELECTRUM.get()), ItemHandler.INGOT_ELECTRUM.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "electrum_ingot_from_dust_from_smelting"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_ELECTRUM.get()), ItemHandler.INGOT_ELECTRUM.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "electrum_ingot_from_dust_from_blast"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_STEEL.get()), ItemHandler.INGOT_STEEL.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "steel_ingot_from_dust_from_smelting"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_STEEL.get()), ItemHandler.INGOT_STEEL.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "steel_ingot_from_dust_from_blast"));*/

    /*CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_FLUORITE.get()), ItemHandler.GEM_FLUORITE.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "fluorite_gem_from_dust_from_smelting"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_FLUORITE.get()), ItemHandler.GEM_FLUORITE.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "fluorite_gem_from_dust_from_blast"));*/

    /*// Nuggets from Ingot
    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.NUGGET_COPPER.get(), 9)
            .addIngredient(ItemHandler.INGOT_COPPER.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "copper_nuggets"));

    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.NUGGET_ALUMINUM.get(), 9)
            .addIngredient(ItemHandler.INGOT_ALUMINUM.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "aluminum_nuggets"));

    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.NUGGET_SILVER.get(), 9)
            .addIngredient(ItemHandler.INGOT_SILVER.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "silver_nuggets"));

    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.NUGGET_LEAD.get(), 9)
            .addIngredient(ItemHandler.INGOT_LEAD.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "lead_nuggets"));

    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.NUGGET_NICKEL.get(), 9)
            .addIngredient(ItemHandler.INGOT_NICKEL.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "nickel_nuggets"));

    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.NUGGET_URANIUM.get(), 9)
            .addIngredient(ItemHandler.INGOT_URANIUM.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "uranium_nuggets"));

    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.NUGGET_OSMIUM.get(), 9)
            .addIngredient(ItemHandler.INGOT_OSMIUM.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "osmium_nuggets"));

    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.NUGGET_TIN.get(), 9)
            .addIngredient(ItemHandler.INGOT_TIN.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "tin_nuggets"));

    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.NUGGET_ZINC.get(), 9)
            .addIngredient(ItemHandler.INGOT_ZINC.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "zinc_nuggets"));

    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.NUGGET_BRONZE.get(), 9)
            .addIngredient(ItemHandler.INGOT_BRONZE.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "bronze_nuggets"));

    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.NUGGET_BRASS.get(), 9)
            .addIngredient(ItemHandler.INGOT_BRASS.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "brass_nuggets"));

    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.NUGGET_CONSTANTAN.get(), 9)
            .addIngredient(ItemHandler.INGOT_CONSTANTAN.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "constantan_nuggets"));

    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.NUGGET_ELECTRUM.get(), 9)
            .addIngredient(ItemHandler.INGOT_ELECTRUM.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "electrum_nuggets"));

    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.NUGGET_STEEL.get(), 9)
            .addIngredient(ItemHandler.INGOT_STEEL.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "steel_nuggets"));*/

    /*// Blocks
    ShapedRecipeBuilder.shapedRecipe(BlockHandler.BLOCK_COPPER.get())
            .key('#', ItemHandler.INGOT_COPPER.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "copper_block"));

    ShapedRecipeBuilder.shapedRecipe(BlockHandler.BLOCK_ALUMINUM.get())
            .key('#', ItemHandler.INGOT_ALUMINUM.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "aluminum_block"));

    ShapedRecipeBuilder.shapedRecipe(BlockHandler.BLOCK_SILVER.get())
            .key('#', ItemHandler.INGOT_SILVER.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "silver_block"));

    ShapedRecipeBuilder.shapedRecipe(BlockHandler.BLOCK_LEAD.get())
            .key('#', ItemHandler.INGOT_LEAD.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "lead_block"));

    ShapedRecipeBuilder.shapedRecipe(BlockHandler.BLOCK_NICKEL.get())
            .key('#', ItemHandler.INGOT_NICKEL.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "nickel_block"));

    ShapedRecipeBuilder.shapedRecipe(BlockHandler.BLOCK_URANIUM.get())
            .key('#', ItemHandler.INGOT_URANIUM.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "uranium_block"));

    ShapedRecipeBuilder.shapedRecipe(BlockHandler.BLOCK_OSMIUM.get())
            .key('#', ItemHandler.INGOT_OSMIUM.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "osmium_block"));

    ShapedRecipeBuilder.shapedRecipe(BlockHandler.BLOCK_TIN.get())
            .key('#', ItemHandler.INGOT_TIN.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "tin_block"));

    ShapedRecipeBuilder.shapedRecipe(BlockHandler.BLOCK_ZINC.get())
            .key('#', ItemHandler.INGOT_ZINC.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "zinc_block"));

    ShapedRecipeBuilder.shapedRecipe(BlockHandler.BLOCK_BRONZE.get())
            .key('#', ItemHandler.INGOT_BRONZE.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "bronze_block"));

    ShapedRecipeBuilder.shapedRecipe(BlockHandler.BLOCK_BRASS.get())
            .key('#', ItemHandler.INGOT_BRASS.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "brass_block"));

    ShapedRecipeBuilder.shapedRecipe(BlockHandler.BLOCK_CONSTANTAN.get())
            .key('#', ItemHandler.INGOT_CONSTANTAN.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "constantan_block"));

    ShapedRecipeBuilder.shapedRecipe(BlockHandler.BLOCK_ELECTRUM.get())
            .key('#', ItemHandler.INGOT_ELECTRUM.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "electrum_block"));

    ShapedRecipeBuilder.shapedRecipe(BlockHandler.BLOCK_STEEL.get())
            .key('#', ItemHandler.INGOT_STEEL.get())
            .patternLine("###")
            .patternLine("###")
            .patternLine("###")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "steel_block"));*/

    /*// Chunk Smelting
    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COAL.get()), Items.COAL, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_coal_from_smelting_chunk"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_IRON.get()), Items.IRON_INGOT, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "iron_ingot_from_smelting_chunk"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_GOLD.get()), Items.GOLD_INGOT, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gold_ingot_from_smelting_chunk"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_DIAMOND.get()), Items.DIAMOND, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_diamond_from_smelting_chunk"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_EMERALD.get()), Items.EMERALD, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_emerald_from_smelting_chunk"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LAPIS.get()), Items.LAPIS_LAZULI, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_lapis_from_smelting_chunk"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_REDSTONE.get()), Items.REDSTONE, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_redstone_from_smelting_chunk"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COPPER.get()), ItemHandler.INGOT_COPPER.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "copper_ingot_from_smelting_chunk"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ALUMINUM.get()), ItemHandler.INGOT_ALUMINUM.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "aluminum_ingot_from_smelting_chunk"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_SILVER.get()), ItemHandler.INGOT_SILVER.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "silver_ingot_from_smelting_chunk"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LEAD.get()), ItemHandler.INGOT_LEAD.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "lead_ingot_from_smelting_chunk"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_NICKEL.get()), ItemHandler.INGOT_NICKEL.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "nickel_ingot_from_smelting_chunk"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_URANIUM.get()), ItemHandler.INGOT_URANIUM.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "uranium_ingot_from_smelting_chunk"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_OSMIUM.get()), ItemHandler.INGOT_OSMIUM.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "osmium_ingot_from_smelting_chunk"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_TIN.get()), ItemHandler.INGOT_TIN.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "tin_ingot_from_smelting_chunk"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ZINC.get()), ItemHandler.INGOT_ZINC.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "zinc_ingot_from_smelting_chunk"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CERTUS_QUARTZ.get()), ItemHandler.GEM_CERTUS_QUARTZ.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "certus_quartz_gem_from_smelting_chunk"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get()), ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "charged_certus_quartz_gem_from_smelting_chunk"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_FLUORITE.get()), ItemHandler.GEM_FLUORITE.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "fluorite_gem_from_smelting_chunk"));

    // Chunk Blasting
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COAL.get()), Items.COAL, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_coal_from_blasting_chunk"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_IRON.get()), Items.IRON_INGOT, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "iron_ingot_from_blasting_chunk"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_GOLD.get()), Items.GOLD_INGOT, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gold_ingot_from_blasting_chunk"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_DIAMOND.get()), Items.DIAMOND, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_diamond_from_blasting_chunk"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_EMERALD.get()), Items.EMERALD, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_emerald_from_blasting_chunk"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LAPIS.get()), Items.LAPIS_LAZULI, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_lapis_from_blasting_chunk"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_REDSTONE.get()), Items.REDSTONE, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_redstone_from_blasting_chunk"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COPPER.get()), ItemHandler.INGOT_COPPER.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "copper_ingot_from_blasting_chunk"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ALUMINUM.get()), ItemHandler.INGOT_ALUMINUM.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "aluminum_ingot_from_blasting_chunk"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_SILVER.get()), ItemHandler.INGOT_SILVER.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "silver_ingot_from_blasting_chunk"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LEAD.get()), ItemHandler.INGOT_LEAD.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "lead_ingot_from_blasting_chunk"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_NICKEL.get()), ItemHandler.INGOT_NICKEL.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "nickel_ingot_from_blasting_chunk"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_URANIUM.get()), ItemHandler.INGOT_URANIUM.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "uranium_ingot_from_blasting_chunk"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_OSMIUM.get()), ItemHandler.INGOT_OSMIUM.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "osmium_ingot_from_blasting_chunk"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_TIN.get()), ItemHandler.INGOT_TIN.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "tin_ingot_from_blasting_chunk"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ZINC.get()), ItemHandler.INGOT_ZINC.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "zinc_ingot_from_blasting_chunk"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CERTUS_QUARTZ.get()), ItemHandler.GEM_CERTUS_QUARTZ.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "certus_quartz_gem_from_blasting_chunk"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get()), ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "charged_certus_quartz_gem_from_blasting_chunk"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_FLUORITE.get()), ItemHandler.GEM_FLUORITE.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "fluorite_gem_from_blasting_chunk"));*/

   /* // Ore Smelting

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(baseOre(Ores.COAL).get()), Items.COAL, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_coal_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(baseOre(Ores.IRON).get()), Items.IRON_INGOT, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "iron_ingot_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(baseOre(Ores.GOLD).get()), Items.GOLD_INGOT, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gold_ingot_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(baseOre(Ores.DIAMOND).get()), Items.DIAMOND, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_diamond_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(baseOre(Ores.EMERALD).get()), Items.EMERALD, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_emerald_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(baseOre(Ores.LAPIS).get()), Items.LAPIS_LAZULI, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_lapis_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(baseOre(Ores.REDSTONE).get()), Items.REDSTONE, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_redstone_from_smelting_ore"));*/

    /*CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(baseOre(Ores.COPPER).get()), ItemHandler.INGOT_COPPER.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "copper_ingot_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(baseOre(Ores.ALUMINUM).get()), ItemHandler.INGOT_ALUMINUM.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "aluminum_ingot_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(baseOre(Ores.SILVER).get()), ItemHandler.INGOT_SILVER.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "silver_ingot_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(baseOre(Ores.LEAD).get()), ItemHandler.INGOT_LEAD.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "lead_ingot_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(baseOre(Ores.NICKEL).get()), ItemHandler.INGOT_NICKEL.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "nickel_ingot_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(baseOre(Ores.URANIUM).get()), ItemHandler.INGOT_URANIUM.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "uranium_ingot_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(baseOre(Ores.OSMIUM).get()), ItemHandler.INGOT_OSMIUM.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "osmium_ingot_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(baseOre(Ores.TIN).get()), ItemHandler.INGOT_TIN.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "tin_ingot_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(baseOre(Ores.ZINC).get()), ItemHandler.INGOT_ZINC.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "zinc_ingot_from_smelting_ore"));*/

    /*CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(baseOre(Ores.CERTUS_QUARTZ).get()), ItemHandler.GEM_CERTUS_QUARTZ.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "certus_quartz_gem_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(baseOre(Ores.CHARGED_CERTUS_QUARTZ).get()), ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "charged_certus_quartz_gem_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(baseOre(Ores.FLUORITE).get()), ItemHandler.GEM_FLUORITE.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "fluorite_gem_from_smelting_ore"));*/

   /* // Ore Blasting
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(baseOre(Ores.COAL).get()), Items.COAL, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_coal_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(baseOre(Ores.IRON).get()), Items.IRON_INGOT, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "iron_ingot_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(baseOre(Ores.GOLD).get()), Items.GOLD_INGOT, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gold_ingot_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(baseOre(Ores.DIAMOND).get()), Items.DIAMOND, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_diamond_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(baseOre(Ores.EMERALD).get()), Items.EMERALD, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_emerald_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(baseOre(Ores.LAPIS).get()), Items.LAPIS_LAZULI, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_lapis_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(baseOre(Ores.REDSTONE).get()), Items.REDSTONE, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_redstone_from_blasting_ore"));*/

    /*CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(baseOre(Ores.COPPER).get()), ItemHandler.INGOT_COPPER.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "copper_ingot_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(baseOre(Ores.ALUMINUM).get()), ItemHandler.INGOT_ALUMINUM.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "aluminum_ingot_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(baseOre(Ores.SILVER).get()), ItemHandler.INGOT_SILVER.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "silver_ingot_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(baseOre(Ores.LEAD).get()), ItemHandler.INGOT_LEAD.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "lead_ingot_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(baseOre(Ores.NICKEL).get()), ItemHandler.INGOT_NICKEL.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "nickel_ingot_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(baseOre(Ores.URANIUM).get()), ItemHandler.INGOT_URANIUM.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "uranium_ingot_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(baseOre(Ores.OSMIUM).get()), ItemHandler.INGOT_OSMIUM.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "osmium_ingot_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(baseOre(Ores.TIN).get()), ItemHandler.INGOT_TIN.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "tin_ingot_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(baseOre(Ores.ZINC).get()), ItemHandler.INGOT_ZINC.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "zinc_ingot_from_blasting_ore"));*/

    /*CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(baseOre(Ores.CERTUS_QUARTZ).get()), ItemHandler.GEM_CERTUS_QUARTZ.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "certus_quartz_gem_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(baseOre(Ores.CHARGED_CERTUS_QUARTZ).get()), ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "charged_certus_quartz_gem_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(baseOre(Ores.FLUORITE).get()), ItemHandler.GEM_FLUORITE.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "fluorite_gem_from_blasting_ore"));*/

    /*// Ore Block from Chunk
    ShapelessRecipeBuilder.shapelessRecipe(baseOre(Ores.COAL).get())
            .addIngredient(ItemHandler.CHUNK_COAL.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "coal_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(baseOre(Ores.IRON).get())
            .addIngredient(ItemHandler.CHUNK_IRON.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "iron_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(baseOre(Ores.GOLD).get())
            .addIngredient(ItemHandler.CHUNK_GOLD.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gold_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(baseOre(Ores.DIAMOND).get())
            .addIngredient(ItemHandler.CHUNK_DIAMOND.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "diamond_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(baseOre(Ores.EMERALD).get())
            .addIngredient(ItemHandler.CHUNK_EMERALD.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "emerald_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(baseOre(Ores.LAPIS).get())
            .addIngredient(ItemHandler.CHUNK_LAPIS.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "lapis_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(baseOre(Ores.REDSTONE).get())
            .addIngredient(ItemHandler.CHUNK_REDSTONE.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "redstone_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(baseOre(Ores.COPPER).get())
            .addIngredient(ItemHandler.CHUNK_COPPER.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "copper_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(baseOre(Ores.ALUMINUM).get())
            .addIngredient(ItemHandler.CHUNK_ALUMINUM.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "aluminum_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(baseOre(Ores.SILVER).get())
            .addIngredient(ItemHandler.CHUNK_SILVER.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "silver_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(baseOre(Ores.LEAD).get())
            .addIngredient(ItemHandler.CHUNK_LEAD.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "lead_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(baseOre(Ores.NICKEL).get())
            .addIngredient(ItemHandler.CHUNK_NICKEL.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "nickel_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(baseOre(Ores.URANIUM).get())
            .addIngredient(ItemHandler.CHUNK_URANIUM.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "uranium_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(baseOre(Ores.OSMIUM).get())
            .addIngredient(ItemHandler.CHUNK_OSMIUM.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "osmium_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(baseOre(Ores.TIN).get())
            .addIngredient(ItemHandler.CHUNK_TIN.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "tin_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(baseOre(Ores.ZINC).get())
            .addIngredient(ItemHandler.CHUNK_ZINC.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "zinc_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(baseOre(Ores.CERTUS_QUARTZ).get())
            .addIngredient(ItemHandler.CHUNK_CERTUS_QUARTZ.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "certus_quartz_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(baseOre(Ores.CHARGED_CERTUS_QUARTZ).get())
            .addIngredient(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "charged_certus_quartz_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(baseOre(Ores.FLUORITE).get())
            .addIngredient(ItemHandler.CHUNK_FLUORITE.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "fluorite_ore_from_chunk"));*/
  }

  static RegistryObject<Block> baseOre(Materials material) {
    return OreHandler.oreBlockTable.get().get(Strata.STONE, material);
  }
}