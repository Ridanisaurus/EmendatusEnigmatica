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
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

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

    // Ingot from Block
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
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "steel_ingot_from_steel_block"));

    // Ingot from Nuggets
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
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "steel_ingot_from_nuggets"));

    // Ore from Chunk in Stonecutter
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COAL.get()), Blocks.COAL_ORE)
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "vanilla_coal_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_IRON.get()), Blocks.IRON_ORE)
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "vanilla_iron_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_GOLD.get()), Blocks.GOLD_ORE)
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "vanilla_gold_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_DIAMOND.get()), Blocks.DIAMOND_ORE)
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "vanilla_diamond_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_EMERALD.get()), Blocks.EMERALD_ORE)
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "vanilla_emerald_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LAPIS.get()), Blocks.LAPIS_ORE)
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "vanilla_lapis_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_REDSTONE.get()), Blocks.REDSTONE_ORE)
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "vanilla_redstone_ore_from_chunk"));

    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COAL.get()), BlockHandler.ORE_COAL.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "stone_coal_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_IRON.get()), BlockHandler.ORE_IRON.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "stone_iron_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_GOLD.get()), BlockHandler.ORE_GOLD.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "stone_gold_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_DIAMOND.get()), BlockHandler.ORE_DIAMOND.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "stone_diamond_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_EMERALD.get()), BlockHandler.ORE_EMERALD.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "stone_emerald_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LAPIS.get()), BlockHandler.ORE_LAPIS.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "stone_lapis_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_REDSTONE.get()), BlockHandler.ORE_REDSTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "stone_redstone_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COPPER.get()), BlockHandler.ORE_COPPER.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "stone_copper_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ALUMINUM.get()), BlockHandler.ORE_ALUMINUM.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "stone_aluminum_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_SILVER.get()), BlockHandler.ORE_SILVER.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "stone_silver_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LEAD.get()), BlockHandler.ORE_LEAD.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "stone_lead_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_NICKEL.get()), BlockHandler.ORE_NICKEL.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "stone_nickel_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_URANIUM.get()), BlockHandler.ORE_URANIUM.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "stone_uranium_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_OSMIUM.get()), BlockHandler.ORE_OSMIUM.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "stone_osmium_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_TIN.get()), BlockHandler.ORE_TIN.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "stone_tin_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ZINC.get()), BlockHandler.ORE_ZINC.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "stone_zinc_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CERTUS_QUARTZ.get()), BlockHandler.ORE_CERTUS_QUARTZ.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "stone_certus_quartz_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get()), BlockHandler.ORE_CHARGED_CERTUS_QUARTZ.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "stone_charged_certus_quartz_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_FLUORITE.get()), BlockHandler.ORE_FLUORITE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "stone_fluorite_ore_from_chunk"));

    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COAL.get()), BlockHandler.ORE_COAL_ANDESITE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "andesite_coal_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_IRON.get()), BlockHandler.ORE_IRON_ANDESITE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "andesite_iron_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_GOLD.get()), BlockHandler.ORE_GOLD_ANDESITE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "andesite_gold_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_DIAMOND.get()), BlockHandler.ORE_DIAMOND_ANDESITE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "andesite_diamond_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_EMERALD.get()), BlockHandler.ORE_EMERALD_ANDESITE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "andesite_emerald_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LAPIS.get()), BlockHandler.ORE_LAPIS_ANDESITE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "andesite_lapis_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_REDSTONE.get()), BlockHandler.ORE_REDSTONE_ANDESITE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "andesite_redstone_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COPPER.get()), BlockHandler.ORE_COPPER_ANDESITE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "andesite_copper_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ALUMINUM.get()), BlockHandler.ORE_ALUMINUM_ANDESITE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "andesite_aluminum_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_SILVER.get()), BlockHandler.ORE_SILVER_ANDESITE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "andesite_silver_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LEAD.get()), BlockHandler.ORE_LEAD_ANDESITE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "andesite_lead_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_NICKEL.get()), BlockHandler.ORE_NICKEL_ANDESITE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "andesite_nickel_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_URANIUM.get()), BlockHandler.ORE_URANIUM_ANDESITE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "andesite_uranium_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_OSMIUM.get()), BlockHandler.ORE_OSMIUM_ANDESITE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "andesite_osmium_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_TIN.get()), BlockHandler.ORE_TIN_ANDESITE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "andesite_tin_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ZINC.get()), BlockHandler.ORE_ZINC_ANDESITE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "andesite_zinc_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CERTUS_QUARTZ.get()), BlockHandler.ORE_CERTUS_QUARTZ_ANDESITE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "andesite_certus_quartz_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get()), BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_ANDESITE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "andesite_charged_certus_quartz_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_FLUORITE.get()), BlockHandler.ORE_FLUORITE_ANDESITE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "andesite_fluorite_ore_from_chunk"));

    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COAL.get()), BlockHandler.ORE_COAL_GABBRO.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gabbro_coal_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_IRON.get()), BlockHandler.ORE_IRON_GABBRO.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gabbro_iron_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_GOLD.get()), BlockHandler.ORE_GOLD_GABBRO.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gabbro_gold_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_DIAMOND.get()), BlockHandler.ORE_DIAMOND_GABBRO.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gabbro_diamond_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_EMERALD.get()), BlockHandler.ORE_EMERALD_GABBRO.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gabbro_emerald_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LAPIS.get()), BlockHandler.ORE_LAPIS_GABBRO.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gabbro_lapis_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_REDSTONE.get()), BlockHandler.ORE_REDSTONE_GABBRO.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gabbro_redstone_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COPPER.get()), BlockHandler.ORE_COPPER_GABBRO.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gabbro_copper_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ALUMINUM.get()), BlockHandler.ORE_ALUMINUM_GABBRO.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gabbro_aluminum_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_SILVER.get()), BlockHandler.ORE_SILVER_GABBRO.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gabbro_silver_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LEAD.get()), BlockHandler.ORE_LEAD_GABBRO.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gabbro_lead_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_NICKEL.get()), BlockHandler.ORE_NICKEL_GABBRO.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gabbro_nickel_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_URANIUM.get()), BlockHandler.ORE_URANIUM_GABBRO.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gabbro_uranium_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_OSMIUM.get()), BlockHandler.ORE_OSMIUM_GABBRO.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gabbro_osmium_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_TIN.get()), BlockHandler.ORE_TIN_GABBRO.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gabbro_tin_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ZINC.get()), BlockHandler.ORE_ZINC_GABBRO.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gabbro_zinc_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CERTUS_QUARTZ.get()), BlockHandler.ORE_CERTUS_QUARTZ_GABBRO.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gabbro_certus_quartz_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get()), BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_GABBRO.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gabbro_charged_certus_quartz_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_FLUORITE.get()), BlockHandler.ORE_FLUORITE_GABBRO.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gabbro_fluorite_ore_from_chunk"));

    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COAL.get()), BlockHandler.ORE_COAL_C_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "c_limestone_coal_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_IRON.get()), BlockHandler.ORE_IRON_C_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "c_limestone_iron_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_GOLD.get()), BlockHandler.ORE_GOLD_C_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "c_limestone_gold_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_DIAMOND.get()), BlockHandler.ORE_DIAMOND_C_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "c_limestone_diamond_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_EMERALD.get()), BlockHandler.ORE_EMERALD_C_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "c_limestone_emerald_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LAPIS.get()), BlockHandler.ORE_LAPIS_C_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "c_limestone_lapis_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_REDSTONE.get()), BlockHandler.ORE_REDSTONE_C_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "c_limestone_redstone_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COPPER.get()), BlockHandler.ORE_COPPER_C_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "c_limestone_copper_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ALUMINUM.get()), BlockHandler.ORE_ALUMINUM_C_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "c_limestone_aluminum_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_SILVER.get()), BlockHandler.ORE_SILVER_C_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "c_limestone_silver_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LEAD.get()), BlockHandler.ORE_LEAD_C_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "c_limestone_lead_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_NICKEL.get()), BlockHandler.ORE_NICKEL_C_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "c_limestone_nickel_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_URANIUM.get()), BlockHandler.ORE_URANIUM_C_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "c_limestone_uranium_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_OSMIUM.get()), BlockHandler.ORE_OSMIUM_C_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "c_limestone_osmium_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_TIN.get()), BlockHandler.ORE_TIN_C_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "c_limestone_tin_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ZINC.get()), BlockHandler.ORE_ZINC_C_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "c_limestone_zinc_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CERTUS_QUARTZ.get()), BlockHandler.ORE_CERTUS_QUARTZ_C_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "c_limestone_certus_quartz_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get()), BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_C_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "c_limestone_charged_certus_quartz_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_FLUORITE.get()), BlockHandler.ORE_FLUORITE_C_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "c_limestone_fluorite_ore_from_chunk"));

    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COAL.get()), BlockHandler.ORE_COAL_SCORIA.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "scoria_coal_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_IRON.get()), BlockHandler.ORE_IRON_SCORIA.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "scoria_iron_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_GOLD.get()), BlockHandler.ORE_GOLD_SCORIA.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "scoria_gold_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_DIAMOND.get()), BlockHandler.ORE_DIAMOND_SCORIA.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "scoria_diamond_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_EMERALD.get()), BlockHandler.ORE_EMERALD_SCORIA.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "scoria_emerald_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LAPIS.get()), BlockHandler.ORE_LAPIS_SCORIA.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "scoria_lapis_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_REDSTONE.get()), BlockHandler.ORE_REDSTONE_SCORIA.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "scoria_redstone_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COPPER.get()), BlockHandler.ORE_COPPER_SCORIA.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "scoria_copper_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ALUMINUM.get()), BlockHandler.ORE_ALUMINUM_SCORIA.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "scoria_aluminum_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_SILVER.get()), BlockHandler.ORE_SILVER_SCORIA.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "scoria_silver_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LEAD.get()), BlockHandler.ORE_LEAD_SCORIA.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "scoria_lead_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_NICKEL.get()), BlockHandler.ORE_NICKEL_SCORIA.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "scoria_nickel_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_URANIUM.get()), BlockHandler.ORE_URANIUM_SCORIA.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "scoria_uranium_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_OSMIUM.get()), BlockHandler.ORE_OSMIUM_SCORIA.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "scoria_osmium_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_TIN.get()), BlockHandler.ORE_TIN_SCORIA.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "scoria_tin_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ZINC.get()), BlockHandler.ORE_ZINC_SCORIA.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "scoria_zinc_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CERTUS_QUARTZ.get()), BlockHandler.ORE_CERTUS_QUARTZ_SCORIA.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "scoria_certus_quartz_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get()), BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_SCORIA.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "scoria_charged_certus_quartz_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_FLUORITE.get()), BlockHandler.ORE_FLUORITE_SCORIA.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "scoria_fluorite_ore_from_chunk"));

    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COAL.get()), BlockHandler.ORE_COAL_WEATHERED_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "weathered_limestone_coal_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_IRON.get()), BlockHandler.ORE_IRON_WEATHERED_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "weathered_limestone_iron_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_GOLD.get()), BlockHandler.ORE_GOLD_WEATHERED_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "weathered_limestone_gold_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_DIAMOND.get()), BlockHandler.ORE_DIAMOND_WEATHERED_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "weathered_limestone_diamond_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_EMERALD.get()), BlockHandler.ORE_EMERALD_WEATHERED_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "weathered_limestone_emerald_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LAPIS.get()), BlockHandler.ORE_LAPIS_WEATHERED_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "weathered_limestone_lapis_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_REDSTONE.get()), BlockHandler.ORE_REDSTONE_WEATHERED_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "weathered_limestone_redstone_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COPPER.get()), BlockHandler.ORE_COPPER_WEATHERED_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "weathered_limestone_copper_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ALUMINUM.get()), BlockHandler.ORE_ALUMINUM_WEATHERED_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "weathered_limestone_aluminum_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_SILVER.get()), BlockHandler.ORE_SILVER_WEATHERED_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "weathered_limestone_silver_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LEAD.get()), BlockHandler.ORE_LEAD_WEATHERED_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "weathered_limestone_lead_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_NICKEL.get()), BlockHandler.ORE_NICKEL_WEATHERED_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "weathered_limestone_nickel_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_URANIUM.get()), BlockHandler.ORE_URANIUM_WEATHERED_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "weathered_limestone_uranium_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_OSMIUM.get()), BlockHandler.ORE_OSMIUM_WEATHERED_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "weathered_limestone_osmium_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_TIN.get()), BlockHandler.ORE_TIN_WEATHERED_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "weathered_limestone_tin_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ZINC.get()), BlockHandler.ORE_ZINC_WEATHERED_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "weathered_limestone_zinc_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CERTUS_QUARTZ.get()), BlockHandler.ORE_CERTUS_QUARTZ_WEATHERED_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "weathered_limestone_certus_quartz_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get()), BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_WEATHERED_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "weathered_limestone_charged_certus_quartz_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_FLUORITE.get()), BlockHandler.ORE_FLUORITE_WEATHERED_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "weathered_limestone_fluorite_ore_from_chunk"));

    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COAL.get()), BlockHandler.ORE_COAL_JASPER.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "jasper_coal_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_IRON.get()), BlockHandler.ORE_IRON_JASPER.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "jasper_iron_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_GOLD.get()), BlockHandler.ORE_GOLD_JASPER.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "jasper_gold_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_DIAMOND.get()), BlockHandler.ORE_DIAMOND_JASPER.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "jasper_diamond_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_EMERALD.get()), BlockHandler.ORE_EMERALD_JASPER.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "jasper_emerald_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LAPIS.get()), BlockHandler.ORE_LAPIS_JASPER.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "jasper_lapis_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_REDSTONE.get()), BlockHandler.ORE_REDSTONE_JASPER.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "jasper_redstone_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COPPER.get()), BlockHandler.ORE_COPPER_JASPER.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "jasper_copper_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ALUMINUM.get()), BlockHandler.ORE_ALUMINUM_JASPER.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "jasper_aluminum_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_SILVER.get()), BlockHandler.ORE_SILVER_JASPER.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "jasper_silver_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LEAD.get()), BlockHandler.ORE_LEAD_JASPER.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "jasper_lead_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_NICKEL.get()), BlockHandler.ORE_NICKEL_JASPER.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "jasper_nickel_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_URANIUM.get()), BlockHandler.ORE_URANIUM_JASPER.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "jasper_uranium_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_OSMIUM.get()), BlockHandler.ORE_OSMIUM_JASPER.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "jasper_osmium_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_TIN.get()), BlockHandler.ORE_TIN_JASPER.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "jasper_tin_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ZINC.get()), BlockHandler.ORE_ZINC_JASPER.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "jasper_zinc_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CERTUS_QUARTZ.get()), BlockHandler.ORE_CERTUS_QUARTZ_JASPER.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "jasper_certus_quartz_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get()), BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_JASPER.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "jasper_charged_certus_quartz_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_FLUORITE.get()), BlockHandler.ORE_FLUORITE_JASPER.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "jasper_fluorite_ore_from_chunk"));

    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COAL.get()), BlockHandler.ORE_COAL_Q_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "q_limestone_coal_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_IRON.get()), BlockHandler.ORE_IRON_Q_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "q_limestone_iron_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_GOLD.get()), BlockHandler.ORE_GOLD_Q_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "q_limestone_gold_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_DIAMOND.get()), BlockHandler.ORE_DIAMOND_Q_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "q_limestone_diamond_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_EMERALD.get()), BlockHandler.ORE_EMERALD_Q_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "q_limestone_emerald_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LAPIS.get()), BlockHandler.ORE_LAPIS_Q_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "q_limestone_lapis_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_REDSTONE.get()), BlockHandler.ORE_REDSTONE_Q_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "q_limestone_redstone_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COPPER.get()), BlockHandler.ORE_COPPER_Q_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "q_limestone_copper_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ALUMINUM.get()), BlockHandler.ORE_ALUMINUM_Q_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "q_limestone_aluminum_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_SILVER.get()), BlockHandler.ORE_SILVER_Q_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "q_limestone_silver_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LEAD.get()), BlockHandler.ORE_LEAD_Q_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "q_limestone_lead_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_NICKEL.get()), BlockHandler.ORE_NICKEL_Q_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "q_limestone_nickel_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_URANIUM.get()), BlockHandler.ORE_URANIUM_Q_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "q_limestone_uranium_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_OSMIUM.get()), BlockHandler.ORE_OSMIUM_Q_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "q_limestone_osmium_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_TIN.get()), BlockHandler.ORE_TIN_Q_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "q_limestone_tin_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ZINC.get()), BlockHandler.ORE_ZINC_Q_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "q_limestone_zinc_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CERTUS_QUARTZ.get()), BlockHandler.ORE_CERTUS_QUARTZ_Q_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "q_limestone_certus_quartz_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get()), BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_Q_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "q_limestone_charged_certus_quartz_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_FLUORITE.get()), BlockHandler.ORE_FLUORITE_Q_LIMESTONE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "q_limestone_fluorite_ore_from_chunk"));

    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COAL.get()), BlockHandler.ORE_COAL_MARBLE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "marble_coal_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_IRON.get()), BlockHandler.ORE_IRON_MARBLE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "marble_iron_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_GOLD.get()), BlockHandler.ORE_GOLD_MARBLE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "marble_gold_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_DIAMOND.get()), BlockHandler.ORE_DIAMOND_MARBLE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "marble_diamond_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_EMERALD.get()), BlockHandler.ORE_EMERALD_MARBLE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "marble_emerald_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LAPIS.get()), BlockHandler.ORE_LAPIS_MARBLE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "marble_lapis_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_REDSTONE.get()), BlockHandler.ORE_REDSTONE_MARBLE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "marble_redstone_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COPPER.get()), BlockHandler.ORE_COPPER_MARBLE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "marble_copper_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ALUMINUM.get()), BlockHandler.ORE_ALUMINUM_MARBLE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "marble_aluminum_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_SILVER.get()), BlockHandler.ORE_SILVER_MARBLE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "marble_silver_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LEAD.get()), BlockHandler.ORE_LEAD_MARBLE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "marble_lead_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_NICKEL.get()), BlockHandler.ORE_NICKEL_MARBLE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "marble_nickel_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_URANIUM.get()), BlockHandler.ORE_URANIUM_MARBLE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "marble_uranium_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_OSMIUM.get()), BlockHandler.ORE_OSMIUM_MARBLE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "marble_osmium_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_TIN.get()), BlockHandler.ORE_TIN_MARBLE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "marble_tin_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ZINC.get()), BlockHandler.ORE_ZINC_MARBLE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "marble_zinc_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CERTUS_QUARTZ.get()), BlockHandler.ORE_CERTUS_QUARTZ_MARBLE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "marble_certus_quartz_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get()), BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_MARBLE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "marble_charged_certus_quartz_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_FLUORITE.get()), BlockHandler.ORE_FLUORITE_MARBLE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "marble_fluorite_ore_from_chunk"));

    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COAL.get()), BlockHandler.ORE_COAL_SLATE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "slate_coal_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_IRON.get()), BlockHandler.ORE_IRON_SLATE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "slate_iron_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_GOLD.get()), BlockHandler.ORE_GOLD_SLATE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "slate_gold_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_DIAMOND.get()), BlockHandler.ORE_DIAMOND_SLATE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "slate_diamond_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_EMERALD.get()), BlockHandler.ORE_EMERALD_SLATE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "slate_emerald_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LAPIS.get()), BlockHandler.ORE_LAPIS_SLATE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "slate_lapis_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_REDSTONE.get()), BlockHandler.ORE_REDSTONE_SLATE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "slate_redstone_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COPPER.get()), BlockHandler.ORE_COPPER_SLATE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "slate_copper_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ALUMINUM.get()), BlockHandler.ORE_ALUMINUM_SLATE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "slate_aluminum_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_SILVER.get()), BlockHandler.ORE_SILVER_SLATE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "slate_silver_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LEAD.get()), BlockHandler.ORE_LEAD_SLATE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "slate_lead_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_NICKEL.get()), BlockHandler.ORE_NICKEL_SLATE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "slate_nickel_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_URANIUM.get()), BlockHandler.ORE_URANIUM_SLATE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "slate_uranium_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_OSMIUM.get()), BlockHandler.ORE_OSMIUM_SLATE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "slate_osmium_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_TIN.get()), BlockHandler.ORE_TIN_SLATE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "slate_tin_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ZINC.get()), BlockHandler.ORE_ZINC_SLATE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "slate_zinc_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CERTUS_QUARTZ.get()), BlockHandler.ORE_CERTUS_QUARTZ_SLATE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "slate_certus_quartz_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get()), BlockHandler.ORE_CHARGED_CERTUS_QUARTZ_SLATE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "slate_charged_certus_quartz_ore_from_chunk"));
    SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_FLUORITE.get()), BlockHandler.ORE_FLUORITE_SLATE.get())
            .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "slate_fluorite_ore_from_chunk"));
    
    // Ingot+Gems from Dusts
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
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "steel_ingot_from_dust_from_blast"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_FLUORITE.get()), ItemHandler.GEM_FLUORITE.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "fluorite_gem_from_dust_from_smelting"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_FLUORITE.get()), ItemHandler.GEM_FLUORITE.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "fluorite_gem_from_dust_from_blast"));

    // Nuggets from Ingot
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
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "steel_nuggets"));

    // Blocks
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
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "steel_block"));

    // Chunk Smelting
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
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "fluorite_gem_from_blasting_chunk"));

    // Ore Smelting
    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockHandler.ORE_COAL.get()), Items.COAL, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_coal_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockHandler.ORE_IRON.get()), Items.IRON_INGOT, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "iron_ingot_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockHandler.ORE_GOLD.get()), Items.GOLD_INGOT, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gold_ingot_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockHandler.ORE_DIAMOND.get()), Items.DIAMOND, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_diamond_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockHandler.ORE_EMERALD.get()), Items.EMERALD, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_emerald_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockHandler.ORE_LAPIS.get()), Items.LAPIS_LAZULI, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_lapis_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockHandler.ORE_REDSTONE.get()), Items.REDSTONE, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_redstone_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockHandler.ORE_COPPER.get()), ItemHandler.INGOT_COPPER.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "copper_ingot_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockHandler.ORE_ALUMINUM.get()), ItemHandler.INGOT_ALUMINUM.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "aluminum_ingot_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockHandler.ORE_SILVER.get()), ItemHandler.INGOT_SILVER.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "silver_ingot_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockHandler.ORE_LEAD.get()), ItemHandler.INGOT_LEAD.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "lead_ingot_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockHandler.ORE_NICKEL.get()), ItemHandler.INGOT_NICKEL.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "nickel_ingot_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockHandler.ORE_URANIUM.get()), ItemHandler.INGOT_URANIUM.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "uranium_ingot_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockHandler.ORE_OSMIUM.get()), ItemHandler.INGOT_OSMIUM.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "osmium_ingot_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockHandler.ORE_TIN.get()), ItemHandler.INGOT_TIN.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "tin_ingot_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockHandler.ORE_ZINC.get()), ItemHandler.INGOT_ZINC.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "zinc_ingot_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockHandler.ORE_CERTUS_QUARTZ.get()), ItemHandler.GEM_CERTUS_QUARTZ.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "certus_quartz_gem_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ.get()), ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "charged_certus_quartz_gem_from_smelting_ore"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockHandler.ORE_FLUORITE.get()), ItemHandler.GEM_FLUORITE.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "fluorite_gem_from_smelting_ore"));

    // Ore Blasting
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(BlockHandler.ORE_COAL.get()), Items.COAL, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_coal_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(BlockHandler.ORE_IRON.get()), Items.IRON_INGOT, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "iron_ingot_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(BlockHandler.ORE_GOLD.get()), Items.GOLD_INGOT, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gold_ingot_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(BlockHandler.ORE_DIAMOND.get()), Items.DIAMOND, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_diamond_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(BlockHandler.ORE_EMERALD.get()), Items.EMERALD, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_emerald_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(BlockHandler.ORE_LAPIS.get()), Items.LAPIS_LAZULI, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_lapis_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(BlockHandler.ORE_REDSTONE.get()), Items.REDSTONE, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_redstone_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(BlockHandler.ORE_COPPER.get()), ItemHandler.INGOT_COPPER.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "copper_ingot_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(BlockHandler.ORE_ALUMINUM.get()), ItemHandler.INGOT_ALUMINUM.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "aluminum_ingot_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(BlockHandler.ORE_SILVER.get()), ItemHandler.INGOT_SILVER.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "silver_ingot_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(BlockHandler.ORE_LEAD.get()), ItemHandler.INGOT_LEAD.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "lead_ingot_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(BlockHandler.ORE_NICKEL.get()), ItemHandler.INGOT_NICKEL.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "nickel_ingot_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(BlockHandler.ORE_URANIUM.get()), ItemHandler.INGOT_URANIUM.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "uranium_ingot_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(BlockHandler.ORE_OSMIUM.get()), ItemHandler.INGOT_OSMIUM.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "osmium_ingot_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(BlockHandler.ORE_TIN.get()), ItemHandler.INGOT_TIN.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "tin_ingot_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(BlockHandler.ORE_ZINC.get()), ItemHandler.INGOT_ZINC.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "zinc_ingot_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(BlockHandler.ORE_CERTUS_QUARTZ.get()), ItemHandler.GEM_CERTUS_QUARTZ.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "certus_quartz_gem_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ.get()), ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "charged_certus_quartz_gem_from_blasting_ore"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(BlockHandler.ORE_FLUORITE.get()), ItemHandler.GEM_FLUORITE.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "fluorite_gem_from_blasting_ore"));

    // Ore Block from Chunk
    ShapelessRecipeBuilder.shapelessRecipe(BlockHandler.ORE_COAL.get())
            .addIngredient(ItemHandler.CHUNK_COAL.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "coal_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(BlockHandler.ORE_IRON.get())
            .addIngredient(ItemHandler.CHUNK_IRON.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "iron_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(BlockHandler.ORE_GOLD.get())
            .addIngredient(ItemHandler.CHUNK_GOLD.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gold_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(BlockHandler.ORE_DIAMOND.get())
            .addIngredient(ItemHandler.CHUNK_DIAMOND.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "diamond_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(BlockHandler.ORE_EMERALD.get())
            .addIngredient(ItemHandler.CHUNK_EMERALD.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "emerald_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(BlockHandler.ORE_LAPIS.get())
            .addIngredient(ItemHandler.CHUNK_LAPIS.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "lapis_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(BlockHandler.ORE_REDSTONE.get())
            .addIngredient(ItemHandler.CHUNK_REDSTONE.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "redstone_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(BlockHandler.ORE_COPPER.get())
            .addIngredient(ItemHandler.CHUNK_COPPER.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "copper_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(BlockHandler.ORE_ALUMINUM.get())
            .addIngredient(ItemHandler.CHUNK_ALUMINUM.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "aluminum_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(BlockHandler.ORE_SILVER.get())
            .addIngredient(ItemHandler.CHUNK_SILVER.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "silver_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(BlockHandler.ORE_LEAD.get())
            .addIngredient(ItemHandler.CHUNK_LEAD.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "lead_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(BlockHandler.ORE_NICKEL.get())
            .addIngredient(ItemHandler.CHUNK_NICKEL.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "nickel_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(BlockHandler.ORE_URANIUM.get())
            .addIngredient(ItemHandler.CHUNK_URANIUM.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "uranium_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(BlockHandler.ORE_OSMIUM.get())
            .addIngredient(ItemHandler.CHUNK_OSMIUM.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "osmium_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(BlockHandler.ORE_TIN.get())
            .addIngredient(ItemHandler.CHUNK_TIN.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "tin_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(BlockHandler.ORE_ZINC.get())
            .addIngredient(ItemHandler.CHUNK_ZINC.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "zinc_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(BlockHandler.ORE_CERTUS_QUARTZ.get())
            .addIngredient(ItemHandler.CHUNK_CERTUS_QUARTZ.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "certus_quartz_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(BlockHandler.ORE_CHARGED_CERTUS_QUARTZ.get())
            .addIngredient(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "charged_certus_quartz_ore_from_chunk"));

    ShapelessRecipeBuilder.shapelessRecipe(BlockHandler.ORE_FLUORITE.get())
            .addIngredient(ItemHandler.CHUNK_FLUORITE.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "fluorite_ore_from_chunk"));
  }
}