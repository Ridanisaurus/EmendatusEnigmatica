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

    // Ingot+Gems from Dusts
    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_COPPER.get()), ItemHandler.INGOT_COPPER.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "copper_ingot_from_dust"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_COPPER.get()), ItemHandler.INGOT_COPPER.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "copper_ingot_from_dust_from_blast"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_ALUMINUM.get()), ItemHandler.INGOT_ALUMINUM.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "aluminum_ingot_from_dust"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_ALUMINUM.get()), ItemHandler.INGOT_ALUMINUM.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "aluminum_ingot_from_dust_from_blast"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_SILVER.get()), ItemHandler.INGOT_SILVER.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "silver_ingot_from_dust"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_SILVER.get()), ItemHandler.INGOT_SILVER.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "silver_ingot_from_dust_from_blast"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_LEAD.get()), ItemHandler.INGOT_LEAD.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "lead_ingot_from_dust"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_LEAD.get()), ItemHandler.INGOT_LEAD.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "lead_ingot_from_dust_from_blast"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_NICKEL.get()), ItemHandler.INGOT_NICKEL.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "nickel_ingot_from_dust"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_NICKEL.get()), ItemHandler.INGOT_NICKEL.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "nickel_ingot_from_dust_from_blast"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_URANIUM.get()), ItemHandler.INGOT_URANIUM.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "uranium_ingot_from_dust"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_URANIUM.get()), ItemHandler.INGOT_URANIUM.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "uranium_ingot_from_dust_from_blast"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_OSMIUM.get()), ItemHandler.INGOT_OSMIUM.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "osmium_ingot_from_dust"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_OSMIUM.get()), ItemHandler.INGOT_OSMIUM.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "osmium_ingot_from_dust_from_blast"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_TIN.get()), ItemHandler.INGOT_TIN.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "tin_ingot_from_dust"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_TIN.get()), ItemHandler.INGOT_TIN.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "tin_ingot_from_dust_from_blast"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_ZINC.get()), ItemHandler.INGOT_ZINC.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "zinc_ingot_from_dust"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_ZINC.get()), ItemHandler.INGOT_ZINC.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "zinc_ingot_from_dust_from_blast"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_BRONZE.get()), ItemHandler.INGOT_BRONZE.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "bronze_ingot_from_dust"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_BRONZE.get()), ItemHandler.INGOT_BRONZE.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "bronze_ingot_from_dust_from_blast"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_BRASS.get()), ItemHandler.INGOT_BRASS.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "brass_ingot_from_dust"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_BRASS.get()), ItemHandler.INGOT_BRASS.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "brass_ingot_from_dust_from_blast"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_CONSTANTAN.get()), ItemHandler.INGOT_CONSTANTAN.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "constantan_ingot_from_dust"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_CONSTANTAN.get()), ItemHandler.INGOT_CONSTANTAN.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "constantan_ingot_from_dust_from_blast"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_ELECTRUM.get()), ItemHandler.INGOT_ELECTRUM.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "electrum_ingot_from_dust"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_ELECTRUM.get()), ItemHandler.INGOT_ELECTRUM.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "electrum_ingot_from_dust_from_blast"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.DUST_STEEL.get()), ItemHandler.INGOT_STEEL.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "steel_ingot_from_dust"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.DUST_STEEL.get()), ItemHandler.INGOT_STEEL.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "steel_ingot_from_dust_from_blast"));

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
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_coal"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_IRON.get()), Items.IRON_INGOT, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "iron_ingot"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_GOLD.get()), Items.GOLD_INGOT, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gold_ingot"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_DIAMOND.get()), Items.DIAMOND, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_diamond"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_EMERALD.get()), Items.EMERALD, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_emerald"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LAPIS.get()), Items.LAPIS_LAZULI, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_lapis"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_REDSTONE.get()), Items.REDSTONE, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_redstone"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COPPER.get()), ItemHandler.INGOT_COPPER.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "copper_ingot"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ALUMINUM.get()), ItemHandler.INGOT_ALUMINUM.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "aluminum_ingot"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_SILVER.get()), ItemHandler.INGOT_SILVER.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "silver_ingot"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LEAD.get()), ItemHandler.INGOT_LEAD.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "lead_ingot"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_NICKEL.get()), ItemHandler.INGOT_NICKEL.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "nickel_ingot"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_URANIUM.get()), ItemHandler.INGOT_URANIUM.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "uranium_ingot"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_OSMIUM.get()), ItemHandler.INGOT_OSMIUM.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "osmium_ingot"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_TIN.get()), ItemHandler.INGOT_TIN.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "tin_ingot"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ZINC.get()), ItemHandler.INGOT_ZINC.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "zinc_ingot"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CERTUS_QUARTZ.get()), ItemHandler.GEM_CERTUS_QUARTZ.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "certus_quartz_gem"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get()), ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "charged_certus_quartz_gem"));

    // Chunk Blasting
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COAL.get()), Items.COAL, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_coal_from_blasting"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_IRON.get()), Items.IRON_INGOT, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "iron_ingot_from_blasting"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_GOLD.get()), Items.GOLD_INGOT, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gold_ingot_from_blasting"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_DIAMOND.get()), Items.DIAMOND, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_diamond_from_blasting"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_EMERALD.get()), Items.EMERALD, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_emerald_from_blasting"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LAPIS.get()), Items.LAPIS_LAZULI, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_lapis_from_blasting"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_REDSTONE.get()), Items.REDSTONE, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "raw_redstone_from_blasting"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_COPPER.get()), ItemHandler.INGOT_COPPER.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "copper_ingot_from_blasting"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ALUMINUM.get()), ItemHandler.INGOT_ALUMINUM.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "aluminum_ingot_from_blasting"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_SILVER.get()), ItemHandler.INGOT_SILVER.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "silver_ingot_from_blasting"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_LEAD.get()), ItemHandler.INGOT_LEAD.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "lead_ingot_from_blasting"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_NICKEL.get()), ItemHandler.INGOT_NICKEL.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "nickel_ingot_from_blasting"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_URANIUM.get()), ItemHandler.INGOT_URANIUM.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "uranium_ingot_from_blasting"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_OSMIUM.get()), ItemHandler.INGOT_OSMIUM.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "osmium_ingot_from_blasting"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_TIN.get()), ItemHandler.INGOT_TIN.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "tin_ingot_from_blasting"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_ZINC.get()), ItemHandler.INGOT_ZINC.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "zinc_ingot_from_blasting"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CERTUS_QUARTZ.get()), ItemHandler.GEM_CERTUS_QUARTZ.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "certus_quartz_gem_from_blasting"));

    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.CHUNK_CHARGED_CERTUS_QUARTZ.get()), ItemHandler.GEM_CHARGED_CERTUS_QUARTZ.get(), 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "charged_certus_quartz_gem_from_blasting"));
  }
}