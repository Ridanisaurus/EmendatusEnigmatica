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
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelFile;
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

    // Machines
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

    /*-- Vanilla Compat --*/
    // Chunk Smelting & Blasting
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

    for (Materials material : Materials.values()) {
      List<String> toCreate = Arrays.asList(material.type);

      // Ingot from Block
      if (toCreate.contains("Ingot") && toCreate.contains("Block")) {
        ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.INGOT, material).get(), 9)
                .addIngredient(BlockHandler.backingStorageBlockTable.get(ProcessedMaterials.STORAGE_BLOCK, material).get())
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .setGroup(Reference.MOD_ID)
                .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_block/" + material.id));
      }

      // Ingot from Nugget
      if (toCreate.contains("Ingot") && toCreate.contains("Nugget")) {
        ShapedRecipeBuilder.shapedRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.INGOT, material).get())
                .key('#', ItemHandler.backingItemTable.get(ProcessedMaterials.NUGGET, material).get())
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .setGroup(Reference.MOD_ID)
                .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_nugget/" + material.id));
      }

      // Nugget from Ingot
      if (toCreate.contains("Ingot") && toCreate.contains("Nugget")) {
        ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.NUGGET, material).get(), 9)
                .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.INGOT, material).get())
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .setGroup(Reference.MOD_ID)
                .build(consumer, new ResourceLocation(Reference.MOD_ID, "nugget_from_ingot/" + material.id));
      }

      // Ingot from Dust
      if (toCreate.contains("Ingot") && toCreate.contains("Dust")) {
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, material).get()), ItemHandler.backingItemTable.get(ProcessedMaterials.INGOT, material).get(), 0.7F, 200)
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_dust/smelting/" + material.id));
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, material).get()), ItemHandler.backingItemTable.get(ProcessedMaterials.INGOT, material).get(), 0.7F, 100)
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_dust/blasting/" + material.id));
      }

      // Block from Ingot
      if (toCreate.contains("Ingot") && toCreate.contains("Block")) {
        ShapedRecipeBuilder.shapedRecipe(BlockHandler.backingStorageBlockTable.get(ProcessedMaterials.STORAGE_BLOCK, material).get())
                .key('#', ItemHandler.backingItemTable.get(ProcessedMaterials.INGOT, material).get())
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .setGroup(Reference.MOD_ID)
                .build(consumer, new ResourceLocation(Reference.MOD_ID, "block_from_ingot/" + material.id));
      }

      // Block from Gem x4
      if (toCreate.contains("Gem") && toCreate.contains("Block") && toCreate.contains("4xRecipe")) {
        ShapedRecipeBuilder.shapedRecipe(BlockHandler.backingStorageBlockTable.get(ProcessedMaterials.STORAGE_BLOCK, material).get())
                .key('#', ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, material).get())
                .patternLine("##")
                .patternLine("##")
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .setGroup(Reference.MOD_ID)
                .build(consumer, new ResourceLocation(Reference.MOD_ID, "block_from_gem/" + material.id));
      }

      // Block from Gem x9
      if (toCreate.contains("Gem") && toCreate.contains("Block") && !toCreate.contains("4xRecipe")) {
        ShapedRecipeBuilder.shapedRecipe(BlockHandler.backingStorageBlockTable.get(ProcessedMaterials.STORAGE_BLOCK, material).get())
                .key('#', ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, material).get())
                .patternLine("###")
                .patternLine("###")
                .patternLine("###")
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .setGroup(Reference.MOD_ID)
                .build(consumer, new ResourceLocation(Reference.MOD_ID, "block_from_gem/" + material.id));
      }

      // Gem from Block x4
      if (toCreate.contains("Gem") && toCreate.contains("Block") && toCreate.contains("4xRecipe")) {
        ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, material).get(), 4)
                .addIngredient(BlockHandler.backingStorageBlockTable.get(ProcessedMaterials.STORAGE_BLOCK, material).get())
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .setGroup(Reference.MOD_ID)
                .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_block/" + material.id));
      }

      // Gem from Block x9
      if (toCreate.contains("Gem") && toCreate.contains("Block") && !toCreate.contains("4xRecipe")) {
        ShapelessRecipeBuilder.shapelessRecipe(ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, material).get(), 9)
                .addIngredient(BlockHandler.backingStorageBlockTable.get(ProcessedMaterials.STORAGE_BLOCK, material).get())
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .setGroup(Reference.MOD_ID)
                .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_block/" + material.id));
      }

      // Chunk Smelting & Blasting
      if (toCreate.contains("Ingot") && toCreate.contains("Chunk")) {
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, material).get()), ItemHandler.backingItemTable.get(ProcessedMaterials.INGOT, material).get(), 0.7F, 200)
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_chunk/smelting/" + material.id));
        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, material).get()), ItemHandler.backingItemTable.get(ProcessedMaterials.INGOT, material).get(), 0.7F, 100)
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_chunk/blasting/" + material.id));
      }

      // Ore Block from Chunk
      if (toCreate.contains("Ore") && toCreate.contains("Chunk")) {
        ShapelessRecipeBuilder.shapelessRecipe(baseOre(material).get())
                .addIngredient(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, material).get())
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .setGroup(Reference.MOD_ID)
                .build(consumer, new ResourceLocation(Reference.MOD_ID, "ore_from_chunk_crafting/" + material.id));
      }

      // Ore Smelting & Blasting
      if (toCreate.contains("Ore") && toCreate.contains("Ingot")) {
        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(baseOre(material).get()), ItemHandler.backingItemTable.get(ProcessedMaterials.INGOT, material).get(), 0.7F, 200)
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/smelting/" + material.id));

        CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(baseOre(material).get()), ItemHandler.backingItemTable.get(ProcessedMaterials.INGOT, material).get(), 0.7F, 100)
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/blasting/" + material.id));
      }

      // Ore from Chunk in Stonecutter
      if (toCreate.contains("Chunk") && toCreate.contains("Ore")) {
        for (Strata stratum : Strata.values()) {
          SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, material).get()), OreHandler.backingOreBlockTable.get(stratum, material).get())
                  .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "ore_from_chunk_stonecutting/" + material.id + "/" + stratum.id));
        }
      }

    }
  }

  static RegistryObject<Block> baseOre(Materials material) {
    return OreHandler.backingOreBlockTable.get(Strata.STONE, material);
  }
}