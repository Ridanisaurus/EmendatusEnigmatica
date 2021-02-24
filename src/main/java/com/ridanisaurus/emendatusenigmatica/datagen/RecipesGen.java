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

import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.BlockHandler;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
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
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.RegistryObject;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class RecipesGen extends RecipeProvider {

  public RecipesGen(DataGenerator gen) {
    super(gen);
  }

  @Override
  protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

    /*// Machines
    ShapedRecipeBuilder.shapedRecipe(BlockHandler.ENIGMATIC_FORTUNIZER.get())
            .patternLine("PGP")
            .patternLine("DNE")
            .patternLine("RBR")
            .key('P', ItemHandler.backingItemTable.get(ProcessedMaterials.PLATE, Materials.IRON).get())
            .key('G', ItemHandler.backingItemTable.get(ProcessedMaterials.GEAR, Materials.GOLD).get())
            .key('D', ItemHandler.backingItemTable.get(ProcessedMaterials.GEAR, Materials.DIAMOND).get())
            .key('N', Tags.Items.INGOTS_NETHERITE)
            .key('E', ItemHandler.backingItemTable.get(ProcessedMaterials.GEAR, Materials.EMERALD).get())
            .key('R', ItemHandler.backingItemTable.get(ProcessedMaterials.ROD, Materials.ENDERIUM).get())
            .key('B', Tags.Items.STORAGE_BLOCKS_IRON)
            .setGroup("emendatusenigmatica")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer);

    // Hammer
    ShapedRecipeBuilder.shapedRecipe(ItemHandler.ENIGMATIC_HAMMER.get())
            .patternLine(" IN")
            .patternLine(" SI")
            .patternLine("S  ")
            .key('I', Tags.Items.INGOTS_IRON)
            .key('N', Tags.Items.NUGGETS_IRON)
            .key('S', Tags.Items.RODS_WOODEN)
            .setGroup("emendatusenigmatica")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer);*/

    /*-- Vanilla Compat --*/
    /*// Chunk Smelting & Blasting
    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.COAL).get()), Items.COAL, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/smelting/coal"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.COAL).get()), Items.COAL, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/blasting/coal"));
    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.IRON).get()), Items.IRON_INGOT, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_chunk/smelting/iron"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.IRON).get()), Items.IRON_INGOT, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_chunk/blasting/iron"));
    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.GOLD).get()), Items.GOLD_INGOT, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_chunk/smelting/gold"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.GOLD).get()), Items.GOLD_INGOT, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_chunk/blasting/gold"));
    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.DIAMOND).get()), Items.DIAMOND, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/smelting/diamond"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.DIAMOND).get()), Items.DIAMOND, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/blasting/diamond"));
    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.EMERALD).get()), Items.EMERALD, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/smelting/emerald"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.EMERALD).get()), Items.EMERALD, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/blasting/emerald"));
    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.LAPIS).get()), Items.LAPIS_LAZULI, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/smelting/lapis"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.LAPIS).get()), Items.LAPIS_LAZULI, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/blasting/lapis"));
    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.REDSTONE).get()), Items.REDSTONE, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/smelting/redstone"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.REDSTONE).get()), Items.REDSTONE, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/blasting/redstone"));

    // Ore Smelting & Blasting
    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(baseOre(Materials.COAL).get()), Items.COAL, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/smelting/coal"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(baseOre(Materials.COAL).get()), Items.COAL, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/blasting/coal"));
    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(baseOre(Materials.IRON).get()), Items.IRON_INGOT, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/smelting/iron"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(baseOre(Materials.IRON).get()), Items.IRON_INGOT, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/blasting/iron"));
    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(baseOre(Materials.GOLD).get()), Items.GOLD_INGOT, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/smelting/gold"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(baseOre(Materials.GOLD).get()), Items.GOLD_INGOT, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/blasting/gold"));
    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(baseOre(Materials.DIAMOND).get()), Items.DIAMOND, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/smelting/diamond"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(baseOre(Materials.DIAMOND).get()), Items.DIAMOND, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/blasting/diamond"));
    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(baseOre(Materials.EMERALD).get()), Items.EMERALD, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/smelting/emerald"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(baseOre(Materials.EMERALD).get()), Items.EMERALD, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/blasting/emerald"));
    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(baseOre(Materials.LAPIS).get()), Items.LAPIS_LAZULI, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/smelting/lapis"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(baseOre(Materials.LAPIS).get()), Items.LAPIS_LAZULI, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/blasting/lapis"));
    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(baseOre(Materials.REDSTONE).get()), Items.REDSTONE, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/smelting/redstone"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(baseOre(Materials.REDSTONE).get()), Items.REDSTONE, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/blasting/redstone"));

    // Ingot from Dust
    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.IRON).get()), Items.IRON_INGOT, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_dust/smelting/iron"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.IRON).get()), Items.IRON_INGOT, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_dust/blasting/iron"));
    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.GOLD).get()), Items.GOLD_INGOT, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_dust/smelting/gold"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.GOLD).get()), Items.GOLD_INGOT, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_dust/blasting/gold"));

    // Silicon from Quartz
    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(Tags.Items.GEMS_QUARTZ), ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.SILICON).get(), 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "silicon_from_quartz/smelting/nether_quartz"));

    // Plate from Ingot
    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.PLATE, Materials.IRON).get(), 1)
            .addIngredient(Items.IRON_INGOT)
            .addIngredient(ItemHandler.ENIGMATIC_HAMMER.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_ingot/iron"));
    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.PLATE, Materials.GOLD).get(), 1)
            .addIngredient(Items.GOLD_INGOT)
            .addIngredient(ItemHandler.ENIGMATIC_HAMMER.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_ingot/gold"));

    // Plate from Gem
    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.PLATE, Materials.DIAMOND).get(), 1)
            .addIngredient(Items.DIAMOND)
            .addIngredient(ItemHandler.ENIGMATIC_HAMMER.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_gem/diamond"));
    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.PLATE, Materials.EMERALD).get(), 1)
            .addIngredient(Items.EMERALD)
            .addIngredient(ItemHandler.ENIGMATIC_HAMMER.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_gem/emerald"));
    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.PLATE, Materials.LAPIS).get(), 1)
            .addIngredient(Items.LAPIS_LAZULI)
            .addIngredient(ItemHandler.ENIGMATIC_HAMMER.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_gem/lapis"));

    // Dust from Ore
    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.QUARTZ).get(), 1)
            .addIngredient(Tags.Items.ORES_QUARTZ)
            .addIngredient(ItemHandler.ENIGMATIC_HAMMER.get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "dust_from_ore/quartz"));

    // Alloy Dust
    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.BRONZE).get(), 4)
            .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.COPPER).get())
            .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.COPPER).get())
            .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.COPPER).get())
            .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.TIN).get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/bronze"));
    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.BRASS).get(), 4)
            .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.COPPER).get())
            .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.COPPER).get())
            .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.COPPER).get())
            .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.ZINC).get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/brass"));
    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.CONSTANTAN).get(), 2)
            .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.COPPER).get())
            .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.NICKEL).get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/constantan"));
    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.ELECTRUM).get(), 2)
            .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.GOLD).get())
            .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.SILVER).get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/electrum"));
    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.INVAR).get(), 3)
            .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.IRON).get())
            .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.IRON).get())
            .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.NICKEL).get())
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/invar"));
    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.LUMIUM).get(), 4)
            .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.TIN).get())
            .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.TIN).get())
            .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.TIN).get())
            .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.SILVER).get())
            .addIngredient(Tags.Items.DUSTS_GLOWSTONE)
            .addIngredient(Tags.Items.DUSTS_GLOWSTONE)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/lumium"));
    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.SIGNALUM).get(), 4)
            .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.COPPER).get())
            .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.COPPER).get())
            .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.COPPER).get())
            .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.SILVER).get())
            .addIngredient(Tags.Items.DUSTS_REDSTONE)
            .addIngredient(Tags.Items.DUSTS_REDSTONE)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/signalum"));
    ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.ENDERIUM).get(), 2)
            .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.LEAD).get())
            .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.LEAD).get())
            .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.LEAD).get())
            .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.DIAMOND).get())
            .addIngredient(Tags.Items.ENDER_PEARLS)
            .addIngredient(Tags.Items.ENDER_PEARLS)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/enderium"));

    // Rods from Ingots
    ShapedRecipeBuilder.shapedRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.ROD, Materials.IRON).get(), 2)
            .key('I', Tags.Items.INGOTS_IRON)
            .patternLine(" I ")
            .patternLine(" I ")
            .patternLine("   ")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "rod_from_ingot/iron"));
    ShapedRecipeBuilder.shapedRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.ROD, Materials.GOLD).get(), 2)
            .key('I', Tags.Items.INGOTS_GOLD)
            .patternLine(" I ")
            .patternLine(" I ")
            .patternLine("   ")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "rod_from_ingot/gold"));

    // Rods from Gems
    ShapedRecipeBuilder.shapedRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.ROD, Materials.DIAMOND).get(), 2)
            .key('G', Tags.Items.GEMS_DIAMOND)
            .patternLine(" G ")
            .patternLine(" G ")
            .patternLine("   ")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "rod_from_gem/diamond"));
    ShapedRecipeBuilder.shapedRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.ROD, Materials.EMERALD).get(), 2)
            .key('G', Tags.Items.GEMS_EMERALD)
            .patternLine(" G ")
            .patternLine(" G ")
            .patternLine("   ")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "rod_from_gem/emerald"));
    ShapedRecipeBuilder.shapedRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.ROD, Materials.LAPIS).get(), 2)
            .key('G', Tags.Items.GEMS_LAPIS)
            .patternLine(" G ")
            .patternLine(" G ")
            .patternLine("   ")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "rod_from_gem/lapis"));

    // Gears from Ingots
    ShapedRecipeBuilder.shapedRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.GEAR, Materials.IRON).get())
            .key('I', Tags.Items.INGOTS_IRON)
            .key('N', Tags.Items.NUGGETS_IRON)
            .patternLine(" I ")
            .patternLine("INI")
            .patternLine(" I ")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_ingot/iron"));
    ShapedRecipeBuilder.shapedRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.GEAR, Materials.GOLD).get())
            .key('I', Tags.Items.INGOTS_GOLD)
            .key('N', Tags.Items.NUGGETS_IRON)
            .patternLine(" I ")
            .patternLine("INI")
            .patternLine(" I ")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_ingot/gold"));

    // Gears from Gems
    ShapedRecipeBuilder.shapedRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.GEAR, Materials.DIAMOND).get())
            .key('G', Tags.Items.GEMS_DIAMOND)
            .key('N', Tags.Items.NUGGETS_IRON)
            .patternLine(" G ")
            .patternLine("GNG")
            .patternLine(" G ")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_gem/diamond"));
    ShapedRecipeBuilder.shapedRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.GEAR, Materials.EMERALD).get())
            .key('G', Tags.Items.GEMS_EMERALD)
            .key('N', Tags.Items.NUGGETS_IRON)
            .patternLine(" G ")
            .patternLine("GNG")
            .patternLine(" G ")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_gem/emerald"));
    ShapedRecipeBuilder.shapedRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.GEAR, Materials.LAPIS).get())
            .key('G', Tags.Items.GEMS_LAPIS)
            .key('N', Tags.Items.NUGGETS_IRON)
            .patternLine(" G ")
            .patternLine("GNG")
            .patternLine(" G ")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_gem/lapis"));
    ShapedRecipeBuilder.shapedRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.GEAR, Materials.QUARTZ).get())
            .key('G', Tags.Items.GEMS_QUARTZ)
            .key('N', Tags.Items.NUGGETS_IRON)
            .patternLine(" G ")
            .patternLine("GNG")
            .patternLine(" G ")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .setGroup(Reference.MOD_ID)
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_gem/quartz"));*/

    for (MaterialModel material : EELoader.MATERIALS) {
      for (String processedType : material.getProcessedType()) {
        // Ingot from Block
        if (processedType.contains("storage_block") && processedType.contains("ingot")) {
          ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.ingotMap.get(material.getId()).get(), 9)
                  .addIngredient(EERegistrar.storageBlockMap.get(material.getId()).get())
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_block/" + material.getId()));
        }

        // Ingot from Nugget
        if (processedType.contains("nugget") && processedType.contains("ingot")) {
          ShapedRecipeBuilder.shapedRecipe(EERegistrar.ingotMap.get(material.getId()).get())
                  .key('#', EERegistrar.nuggetMap.get(material.getId()).get())
                  .patternLine("###")
                  .patternLine("###")
                  .patternLine("###")
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_nugget/" + material.getId()));
        }

        // Nugget from Ingot
        if (processedType.contains("ingot") && processedType.contains("nugget")) {
          ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.nuggetMap.get(material.getId()).get(), 9)
                  .addIngredient(EERegistrar.ingotMap.get(material.getId()).get())
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "nugget_from_ingot/" + material.getId()));
        }

        // Ingot from Dust
        if (processedType.contains("dust") && processedType.contains("ingot")) {
          CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(EERegistrar.dustMap.get(material.getId()).get()),
                  EERegistrar.ingotMap.get(material.getId()).get(), 0.7F, 200)
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_dust/smelting/" + material.getId()));
          CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(EERegistrar.dustMap.get(material.getId()).get()),
                  EERegistrar.ingotMap.get(material.getId()).get(), 0.7F, 100)
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_dust/blasting/" + material.getId()));
        }

        // Ingot from Chunk Smelting and Blasting
        if (processedType.contains("chunk") && processedType.contains("ingot")) {
          CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(EERegistrar.chunkMap.get(material.getId()).get()),
                  EERegistrar.ingotMap.get(material.getId()).get(), 0.7F, 200)
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_chunk/smelting/" + material.getId()));
          CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(EERegistrar.chunkMap.get(material.getId()).get()),
                  EERegistrar.ingotMap.get(material.getId()).get(), 0.7F, 100)
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_chunk/blasting/" + material.getId()));
        }

        // Block from Ingot
        if (processedType.contains("ingot") && processedType.contains("block")) {
          ShapedRecipeBuilder.shapedRecipe(EERegistrar.storageBlockMap.get(material.getId()).get())
                  .key('#', EERegistrar.ingotMap.get(material.getId()).get())
                  .patternLine("###")
                  .patternLine("###")
                  .patternLine("###")
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "block_from_ingot/" + material.getId()));
        }

        // Block from Gem x4
        if (processedType.contains("gem") && processedType.contains("block") && processedType.contains("4x_block_recipe")) {
          ShapedRecipeBuilder.shapedRecipe(EERegistrar.storageBlockMap.get(material.getId()).get())
                  .key('#', EERegistrar.gemMap.get(material.getId()).get())
                  .patternLine("##")
                  .patternLine("##")
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "block_from_gem/" + material.getId()));
        }
        // Block from Gem x9
        if (processedType.contains("gem") && processedType.contains("block") && !processedType.contains("4x_block_recipe")) {
          ShapedRecipeBuilder.shapedRecipe(EERegistrar.storageBlockMap.get(material.getId()).get())
                  .key('#', EERegistrar.gemMap.get(material.getId()).get())
                  .patternLine("###")
                  .patternLine("###")
                  .patternLine("###")
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "block_from_gem/" + material.getId()));
        }

        // Gem from Block x4
        if (processedType.contains("gem") && processedType.contains("block") && processedType.contains("4x_block_recipe")) {
          ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.gemMap.get(material.getId()).get(), 4)
                  .addIngredient(EERegistrar.storageBlockMap.get(material.getId()).get())
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_block/" + material.getId()));
        }
        // Gem from Block x9
        if (processedType.contains("gem") && processedType.contains("block") && !processedType.contains("4x_block_recipe")) {
          ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.gemMap.get(material.getId()).get(), 9)
                  .addIngredient(EERegistrar.storageBlockMap.get(material.getId()).get())
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_block/" + material.getId()));
        }

        // Dust from Chunk
        if (processedType.contains("chunk") && processedType.contains("dust")) {
          ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.dustMap.get(material.getId()).get(), 1)
                  .addIngredient(EERegistrar.chunkMap.get(material.getId()).get())
                  .addIngredient(ItemHandler.ENIGMATIC_HAMMER.get())
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "dust_from_chunk/" + material.getId()));
        }

        // Plate from Ingot
        if (processedType.contains("ingot") && processedType.contains("plate")) {
          ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.plateMap.get(material.getId()).get(), 1)
                  .addIngredient(EERegistrar.ingotMap.get(material.getId()).get())
                  .addIngredient(ItemHandler.ENIGMATIC_HAMMER.get())
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_ingot/" + material.getId()));
        }
        // Plate from Gem
        if (processedType.contains("gem") && processedType.contains("plate")) {
          ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.plateMap.get(material.getId()).get(), 1)
                  .addIngredient(EERegistrar.gemMap.get(material.getId()).get())
                  .addIngredient(ItemHandler.ENIGMATIC_HAMMER.get())
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_gem/" + material.getId()));
        }

        // Gear from Ingot
        if (processedType.contains("ingot") && processedType.contains("gear")) {
          ShapedRecipeBuilder.shapedRecipe(EERegistrar.gearMap.get(material.getId()).get())
                  .key('I', EERegistrar.ingotMap.get(material.getId()).get())
                  .key('N', Tags.Items.NUGGETS_IRON)
                  .patternLine(" I ")
                  .patternLine("INI")
                  .patternLine(" I ")
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_ingot/" + material.getId()));
        }
        // Gear from Gem
        if (processedType.contains("gem") && processedType.contains("gear")) {
          ShapedRecipeBuilder.shapedRecipe(EERegistrar.gearMap.get(material.getId()).get())
                  .key('G', EERegistrar.gemMap.get(material.getId()).get())
                  .key('N', Tags.Items.NUGGETS_IRON)
                  .patternLine(" G ")
                  .patternLine("GNG")
                  .patternLine(" G ")
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_gem/" + material.getId()));
        }

        // Rod from Ingot
        if (processedType.contains("ingot") && processedType.contains("rod")) {
          ShapedRecipeBuilder.shapedRecipe(EERegistrar.rodMap.get(material.getId()).get(), 2)
                  .key('I', EERegistrar.ingotMap.get(material.getId()).get())
                  .patternLine(" I ")
                  .patternLine(" I ")
                  .patternLine("   ")
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "rod_from_ingot/" + material.getId()));
        }
        // Rod from Gem
        if (processedType.contains("gem") && processedType.contains("rod")) {
          ShapedRecipeBuilder.shapedRecipe(EERegistrar.rodMap.get(material.getId()).get(), 2)
                  .key('G', EERegistrar.gemMap.get(material.getId()).get())
                  .patternLine(" G ")
                  .patternLine(" G ")
                  .patternLine("   ")
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "rod_from_gem/" + material.getId()));
        }
      }
    }

    for (MaterialModel material : EELoader.MATERIALS) {
      for (StrataModel stratum : EELoader.STRATA) {
        for (String processedType : material.getProcessedType()) {
          // Ore from Chunk
          if (processedType.contains("chunk") && processedType.contains("ore")) {
            if (stratum.getId().equals("minecraft_stone")) {
              ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get())
                      .addIngredient(EERegistrar.chunkMap.get(material.getId()).get())
                      .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                      .setGroup(Reference.MOD_ID)
                      .build(consumer, new ResourceLocation(Reference.MOD_ID, "ore_from_chunk_crafting/" + material.getId()));
            }
          }

          // Ore Smelting & Blasting
          if (processedType.contains("ingot") && processedType.contains("ore")) {
            CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get()),
                    EERegistrar.ingotMap.get(material.getId()).get(), 0.7F, 200)
                    .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                    .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/smelting/" + material.getId()));
            CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get()),
                    EERegistrar.ingotMap.get(material.getId()).get(), 0.7F, 100)
                    .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                    .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/blasting/" + material.getId()));
          }

          // Ore from Chunk in Stonecutter
          if (processedType.contains("chunk") && processedType.contains("ore")) {
            CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get()),
                    EERegistrar.ingotMap.get(material.getId()).get(), 0.7F, 200)
                    .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                    .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/smelting/" + material.getId()));
            CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get()),
                    EERegistrar.ingotMap.get(material.getId()).get(), 0.7F, 100)
                    .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                    .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/blasting/" + material.getId()));

            SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(EERegistrar.chunkMap.get(material.getId()).get()),
                    EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get())
                    .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
                    .build(consumer, new ResourceLocation(Reference.MOD_ID, "ore_from_chunk_stonecutting/" + material.getId() + "/" + stratum.getId()));
          }
        }
      }
    }
  }

  @Override
  public String getName() {
    return "Emendatus Enigmatica Recipes";
  }
}