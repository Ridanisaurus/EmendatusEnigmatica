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

import com.ridanisaurus.emendatusenigmatica.blocks.EEOreBlock;
import com.ridanisaurus.emendatusenigmatica.registries.BlockHandler;
import com.ridanisaurus.emendatusenigmatica.registries.ItemHandler;
import com.ridanisaurus.emendatusenigmatica.registries.OreHandler;
import com.ridanisaurus.emendatusenigmatica.util.Materials;
import com.ridanisaurus.emendatusenigmatica.util.ProcessedMaterials;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import com.ridanisaurus.emendatusenigmatica.util.Strata;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class RecipesGen extends RecipeProvider {

  public RecipesGen(DataGenerator gen) {
    super(gen);
  }

  @Override
  protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

    // Machines
    ShapedRecipeBuilder.shaped(BlockHandler.ENIGMATIC_FORTUNIZER.get())
            .pattern("PGP")
            .pattern("DNE")
            .pattern("RBR")
            .define('P', ItemHandler.backingItemTable.get(ProcessedMaterials.PLATE, Materials.IRON).get())
            .define('G', ItemHandler.backingItemTable.get(ProcessedMaterials.GEAR, Materials.GOLD).get())
            .define('D', ItemHandler.backingItemTable.get(ProcessedMaterials.GEAR, Materials.DIAMOND).get())
            .define('N', Tags.Items.INGOTS_NETHERITE)
            .define('E', ItemHandler.backingItemTable.get(ProcessedMaterials.GEAR, Materials.EMERALD).get())
            .define('R', ItemHandler.backingItemTable.get(ProcessedMaterials.ROD, Materials.ENDERIUM).get())
            .define('B', Tags.Items.STORAGE_BLOCKS_IRON)
            .group("emendatusenigmatica")
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer);

    // Hammer
    ShapedRecipeBuilder.shaped(ItemHandler.ENIGMATIC_HAMMER.get())
            .pattern(" IN")
            .pattern(" SI")
            .pattern("S  ")
            .define('I', Tags.Items.INGOTS_IRON)
            .define('N', Tags.Items.NUGGETS_IRON)
            .define('S', Tags.Items.RODS_WOODEN)
            .group("emendatusenigmatica")
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer);

    /*-- Vanilla Compat --*/
    // Chunk Smelting & Blasting
    SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.COAL).get()), Items.COAL, 0.7F, 200)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/smelting/coal"));
    SimpleCookingRecipeBuilder.blasting(Ingredient.of(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.COAL).get()), Items.COAL, 0.7F, 100)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/blasting/coal"));
    SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.IRON).get()), Items.IRON_INGOT, 0.7F, 200)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_chunk/smelting/iron"));
    SimpleCookingRecipeBuilder.blasting(Ingredient.of(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.IRON).get()), Items.IRON_INGOT, 0.7F, 100)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_chunk/blasting/iron"));
    SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.GOLD).get()), Items.GOLD_INGOT, 0.7F, 200)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_chunk/smelting/gold"));
    SimpleCookingRecipeBuilder.blasting(Ingredient.of(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.GOLD).get()), Items.GOLD_INGOT, 0.7F, 100)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_chunk/blasting/gold"));
    SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.DIAMOND).get()), Items.DIAMOND, 0.7F, 200)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/smelting/diamond"));
    SimpleCookingRecipeBuilder.blasting(Ingredient.of(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.DIAMOND).get()), Items.DIAMOND, 0.7F, 100)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/blasting/diamond"));
    SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.EMERALD).get()), Items.EMERALD, 0.7F, 200)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/smelting/emerald"));
    SimpleCookingRecipeBuilder.blasting(Ingredient.of(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.EMERALD).get()), Items.EMERALD, 0.7F, 100)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/blasting/emerald"));
    SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.LAPIS).get()), Items.LAPIS_LAZULI, 0.7F, 200)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/smelting/lapis"));
    SimpleCookingRecipeBuilder.blasting(Ingredient.of(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.LAPIS).get()), Items.LAPIS_LAZULI, 0.7F, 100)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/blasting/lapis"));
    SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.REDSTONE).get()), Items.REDSTONE, 0.7F, 200)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/smelting/redstone"));
    SimpleCookingRecipeBuilder.blasting(Ingredient.of(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, Materials.REDSTONE).get()), Items.REDSTONE, 0.7F, 100)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/blasting/redstone"));

    // Ore Smelting & Blasting
    SimpleCookingRecipeBuilder.smelting(Ingredient.of(baseOre(Materials.COAL).get()), Items.COAL, 0.7F, 200)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/smelting/coal"));
    SimpleCookingRecipeBuilder.blasting(Ingredient.of(baseOre(Materials.COAL).get()), Items.COAL, 0.7F, 100)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/blasting/coal"));
    SimpleCookingRecipeBuilder.smelting(Ingredient.of(baseOre(Materials.IRON).get()), Items.IRON_INGOT, 0.7F, 200)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/smelting/iron"));
    SimpleCookingRecipeBuilder.blasting(Ingredient.of(baseOre(Materials.IRON).get()), Items.IRON_INGOT, 0.7F, 100)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/blasting/iron"));
    SimpleCookingRecipeBuilder.smelting(Ingredient.of(baseOre(Materials.GOLD).get()), Items.GOLD_INGOT, 0.7F, 200)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/smelting/gold"));
    SimpleCookingRecipeBuilder.blasting(Ingredient.of(baseOre(Materials.GOLD).get()), Items.GOLD_INGOT, 0.7F, 100)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/blasting/gold"));
    SimpleCookingRecipeBuilder.smelting(Ingredient.of(baseOre(Materials.DIAMOND).get()), Items.DIAMOND, 0.7F, 200)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/smelting/diamond"));
    SimpleCookingRecipeBuilder.blasting(Ingredient.of(baseOre(Materials.DIAMOND).get()), Items.DIAMOND, 0.7F, 100)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/blasting/diamond"));
    SimpleCookingRecipeBuilder.smelting(Ingredient.of(baseOre(Materials.EMERALD).get()), Items.EMERALD, 0.7F, 200)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/smelting/emerald"));
    SimpleCookingRecipeBuilder.blasting(Ingredient.of(baseOre(Materials.EMERALD).get()), Items.EMERALD, 0.7F, 100)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/blasting/emerald"));
    SimpleCookingRecipeBuilder.smelting(Ingredient.of(baseOre(Materials.LAPIS).get()), Items.LAPIS_LAZULI, 0.7F, 200)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/smelting/lapis"));
    SimpleCookingRecipeBuilder.blasting(Ingredient.of(baseOre(Materials.LAPIS).get()), Items.LAPIS_LAZULI, 0.7F, 100)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/blasting/lapis"));
    SimpleCookingRecipeBuilder.smelting(Ingredient.of(baseOre(Materials.REDSTONE).get()), Items.REDSTONE, 0.7F, 200)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/smelting/redstone"));
    SimpleCookingRecipeBuilder.blasting(Ingredient.of(baseOre(Materials.REDSTONE).get()), Items.REDSTONE, 0.7F, 100)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/blasting/redstone"));

    // Ingot from Dust
    SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.IRON).get()), Items.IRON_INGOT, 0.7F, 200)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_dust/smelting/iron"));
    SimpleCookingRecipeBuilder.blasting(Ingredient.of(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.IRON).get()), Items.IRON_INGOT, 0.7F, 100)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_dust/blasting/iron"));
    SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.GOLD).get()), Items.GOLD_INGOT, 0.7F, 200)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_dust/smelting/gold"));
    SimpleCookingRecipeBuilder.blasting(Ingredient.of(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.GOLD).get()), Items.GOLD_INGOT, 0.7F, 100)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_dust/blasting/gold"));

    // Silicon from Quartz
    SimpleCookingRecipeBuilder.smelting(Ingredient.of(Tags.Items.GEMS_QUARTZ), ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, Materials.SILICON).get(), 0.7F, 200)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "silicon_from_quartz/smelting/nether_quartz"));

    // Plate from Ingot
    ShapelessRecipeBuilder.shapeless(ItemHandler.backingItemTable.get(ProcessedMaterials.PLATE, Materials.IRON).get(), 1)
            .requires(Items.IRON_INGOT)
            .requires(ItemHandler.ENIGMATIC_HAMMER.get())
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .group(Reference.MOD_ID)
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_ingot/iron"));
    ShapelessRecipeBuilder.shapeless(ItemHandler.backingItemTable.get(ProcessedMaterials.PLATE, Materials.GOLD).get(), 1)
            .requires(Items.GOLD_INGOT)
            .requires(ItemHandler.ENIGMATIC_HAMMER.get())
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .group(Reference.MOD_ID)
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_ingot/gold"));

    // Plate from Gem
    ShapelessRecipeBuilder.shapeless(ItemHandler.backingItemTable.get(ProcessedMaterials.PLATE, Materials.DIAMOND).get(), 1)
            .requires(Items.DIAMOND)
            .requires(ItemHandler.ENIGMATIC_HAMMER.get())
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .group(Reference.MOD_ID)
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_gem/diamond"));
    ShapelessRecipeBuilder.shapeless(ItemHandler.backingItemTable.get(ProcessedMaterials.PLATE, Materials.EMERALD).get(), 1)
            .requires(Items.EMERALD)
            .requires(ItemHandler.ENIGMATIC_HAMMER.get())
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .group(Reference.MOD_ID)
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_gem/emerald"));
    ShapelessRecipeBuilder.shapeless(ItemHandler.backingItemTable.get(ProcessedMaterials.PLATE, Materials.LAPIS).get(), 1)
            .requires(Items.LAPIS_LAZULI)
            .requires(ItemHandler.ENIGMATIC_HAMMER.get())
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .group(Reference.MOD_ID)
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_gem/lapis"));

    // Dust from Ore
    ShapelessRecipeBuilder.shapeless(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.QUARTZ).get(), 1)
            .requires(Tags.Items.ORES_QUARTZ)
            .requires(ItemHandler.ENIGMATIC_HAMMER.get())
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .group(Reference.MOD_ID)
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "dust_from_ore/quartz"));

    // Alloy Dust
    ShapelessRecipeBuilder.shapeless(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.BRONZE).get(), 4)
            .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.COPPER).get())
            .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.COPPER).get())
            .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.COPPER).get())
            .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.TIN).get())
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .group(Reference.MOD_ID)
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/bronze"));
    ShapelessRecipeBuilder.shapeless(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.BRASS).get(), 4)
            .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.COPPER).get())
            .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.COPPER).get())
            .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.COPPER).get())
            .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.ZINC).get())
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .group(Reference.MOD_ID)
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/brass"));
    ShapelessRecipeBuilder.shapeless(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.CONSTANTAN).get(), 2)
            .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.COPPER).get())
            .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.NICKEL).get())
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .group(Reference.MOD_ID)
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/constantan"));
    ShapelessRecipeBuilder.shapeless(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.ELECTRUM).get(), 2)
            .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.GOLD).get())
            .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.SILVER).get())
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .group(Reference.MOD_ID)
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/electrum"));
    ShapelessRecipeBuilder.shapeless(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.INVAR).get(), 3)
            .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.IRON).get())
            .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.IRON).get())
            .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.NICKEL).get())
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .group(Reference.MOD_ID)
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/invar"));
    ShapelessRecipeBuilder.shapeless(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.LUMIUM).get(), 4)
            .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.TIN).get())
            .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.TIN).get())
            .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.TIN).get())
            .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.SILVER).get())
            .requires(Tags.Items.DUSTS_GLOWSTONE)
            .requires(Tags.Items.DUSTS_GLOWSTONE)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .group(Reference.MOD_ID)
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/lumium"));
    ShapelessRecipeBuilder.shapeless(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.SIGNALUM).get(), 4)
            .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.COPPER).get())
            .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.COPPER).get())
            .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.COPPER).get())
            .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.SILVER).get())
            .requires(Tags.Items.DUSTS_REDSTONE)
            .requires(Tags.Items.DUSTS_REDSTONE)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .group(Reference.MOD_ID)
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/signalum"));
    ShapelessRecipeBuilder.shapeless(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.ENDERIUM).get(), 2)
            .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.LEAD).get())
            .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.LEAD).get())
            .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.LEAD).get())
            .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, Materials.DIAMOND).get())
            .requires(Tags.Items.ENDER_PEARLS)
            .requires(Tags.Items.ENDER_PEARLS)
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .group(Reference.MOD_ID)
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/enderium"));

    // Rods from Ingots
    ShapedRecipeBuilder.shaped(ItemHandler.backingItemTable.get(ProcessedMaterials.ROD, Materials.IRON).get(), 2)
            .define('I', Tags.Items.INGOTS_IRON)
            .pattern(" I ")
            .pattern(" I ")
            .pattern("   ")
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .group(Reference.MOD_ID)
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "rod_from_ingot/iron"));
    ShapedRecipeBuilder.shaped(ItemHandler.backingItemTable.get(ProcessedMaterials.ROD, Materials.GOLD).get(), 2)
            .define('I', Tags.Items.INGOTS_GOLD)
            .pattern(" I ")
            .pattern(" I ")
            .pattern("   ")
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .group(Reference.MOD_ID)
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "rod_from_ingot/gold"));

    // Rods from Gems
    ShapedRecipeBuilder.shaped(ItemHandler.backingItemTable.get(ProcessedMaterials.ROD, Materials.DIAMOND).get(), 2)
            .define('G', Tags.Items.GEMS_DIAMOND)
            .pattern(" G ")
            .pattern(" G ")
            .pattern("   ")
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .group(Reference.MOD_ID)
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "rod_from_gem/diamond"));
    ShapedRecipeBuilder.shaped(ItemHandler.backingItemTable.get(ProcessedMaterials.ROD, Materials.EMERALD).get(), 2)
            .define('G', Tags.Items.GEMS_EMERALD)
            .pattern(" G ")
            .pattern(" G ")
            .pattern("   ")
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .group(Reference.MOD_ID)
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "rod_from_gem/emerald"));
    ShapedRecipeBuilder.shaped(ItemHandler.backingItemTable.get(ProcessedMaterials.ROD, Materials.LAPIS).get(), 2)
            .define('G', Tags.Items.GEMS_LAPIS)
            .pattern(" G ")
            .pattern(" G ")
            .pattern("   ")
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .group(Reference.MOD_ID)
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "rod_from_gem/lapis"));

    // Gears from Ingots
    ShapedRecipeBuilder.shaped(ItemHandler.backingItemTable.get(ProcessedMaterials.GEAR, Materials.IRON).get())
            .define('I', Tags.Items.INGOTS_IRON)
            .define('N', Tags.Items.NUGGETS_IRON)
            .pattern(" I ")
            .pattern("INI")
            .pattern(" I ")
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .group(Reference.MOD_ID)
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_ingot/iron"));
    ShapedRecipeBuilder.shaped(ItemHandler.backingItemTable.get(ProcessedMaterials.GEAR, Materials.GOLD).get())
            .define('I', Tags.Items.INGOTS_GOLD)
            .define('N', Tags.Items.NUGGETS_IRON)
            .pattern(" I ")
            .pattern("INI")
            .pattern(" I ")
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .group(Reference.MOD_ID)
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_ingot/gold"));

    // Gears from Gems
    ShapedRecipeBuilder.shaped(ItemHandler.backingItemTable.get(ProcessedMaterials.GEAR, Materials.DIAMOND).get())
            .define('G', Tags.Items.GEMS_DIAMOND)
            .define('N', Tags.Items.NUGGETS_IRON)
            .pattern(" G ")
            .pattern("GNG")
            .pattern(" G ")
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .group(Reference.MOD_ID)
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_gem/diamond"));
    ShapedRecipeBuilder.shaped(ItemHandler.backingItemTable.get(ProcessedMaterials.GEAR, Materials.EMERALD).get())
            .define('G', Tags.Items.GEMS_EMERALD)
            .define('N', Tags.Items.NUGGETS_IRON)
            .pattern(" G ")
            .pattern("GNG")
            .pattern(" G ")
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .group(Reference.MOD_ID)
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_gem/emerald"));
    ShapedRecipeBuilder.shaped(ItemHandler.backingItemTable.get(ProcessedMaterials.GEAR, Materials.LAPIS).get())
            .define('G', Tags.Items.GEMS_LAPIS)
            .define('N', Tags.Items.NUGGETS_IRON)
            .pattern(" G ")
            .pattern("GNG")
            .pattern(" G ")
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .group(Reference.MOD_ID)
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_gem/lapis"));
    ShapedRecipeBuilder.shaped(ItemHandler.backingItemTable.get(ProcessedMaterials.GEAR, Materials.QUARTZ).get())
            .define('G', Tags.Items.GEMS_QUARTZ)
            .define('N', Tags.Items.NUGGETS_IRON)
            .pattern(" G ")
            .pattern("GNG")
            .pattern(" G ")
            .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
            .group(Reference.MOD_ID)
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_gem/quartz"));

    for (Materials material : Materials.values()) {
      List<String> toCreate = Arrays.asList(material.type);

      // Ingot from Block
      if (toCreate.contains("Ingot") && toCreate.contains("Block")) {
        ShapelessRecipeBuilder.shapeless(ItemHandler.backingItemTable.get(ProcessedMaterials.INGOT, material).get(), 9)
                .requires(BlockHandler.backingStorageBlockTable.get(ProcessedMaterials.STORAGE_BLOCK, material).get())
                .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
                .group(Reference.MOD_ID)
                .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_block/" + material.id));
      }

      // Ingot from Nugget
      if (toCreate.contains("Ingot") && toCreate.contains("Nugget")) {
        ShapedRecipeBuilder.shaped(ItemHandler.backingItemTable.get(ProcessedMaterials.INGOT, material).get())
                .define('#', ItemHandler.backingItemTable.get(ProcessedMaterials.NUGGET, material).get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
                .group(Reference.MOD_ID)
                .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_nugget/" + material.id));
      }

      // Nugget from Ingot
      if (toCreate.contains("Ingot") && toCreate.contains("Nugget")) {
        ShapelessRecipeBuilder.shapeless(ItemHandler.backingItemTable.get(ProcessedMaterials.NUGGET, material).get(), 9)
                .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.INGOT, material).get())
                .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
                .group(Reference.MOD_ID)
                .save(consumer, new ResourceLocation(Reference.MOD_ID, "nugget_from_ingot/" + material.id));
      }

      // Ingot from Dust
      if (toCreate.contains("Ingot") && toCreate.contains("Dust")) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, material).get()), ItemHandler.backingItemTable.get(ProcessedMaterials.INGOT, material).get(), 0.7F, 200)
                .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
                .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_dust/smelting/" + material.id));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, material).get()), ItemHandler.backingItemTable.get(ProcessedMaterials.INGOT, material).get(), 0.7F, 100)
                .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
                .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_dust/blasting/" + material.id));
      }

      // Block from Ingot
      if (toCreate.contains("Ingot") && toCreate.contains("Block")) {
        ShapedRecipeBuilder.shaped(BlockHandler.backingStorageBlockTable.get(ProcessedMaterials.STORAGE_BLOCK, material).get())
                .define('#', ItemHandler.backingItemTable.get(ProcessedMaterials.INGOT, material).get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
                .group(Reference.MOD_ID)
                .save(consumer, new ResourceLocation(Reference.MOD_ID, "block_from_ingot/" + material.id));
      }

      // Block from Gem x4
      if (toCreate.contains("Gem") && toCreate.contains("Block") && toCreate.contains("4xRecipe")) {
        ShapedRecipeBuilder.shaped(BlockHandler.backingStorageBlockTable.get(ProcessedMaterials.STORAGE_BLOCK, material).get())
                .define('#', ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, material).get())
                .pattern("##")
                .pattern("##")
                .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
                .group(Reference.MOD_ID)
                .save(consumer, new ResourceLocation(Reference.MOD_ID, "block_from_gem/" + material.id));
      }

      // Block from Gem x9
      if (toCreate.contains("Gem") && toCreate.contains("Block") && !toCreate.contains("4xRecipe")) {
        ShapedRecipeBuilder.shaped(BlockHandler.backingStorageBlockTable.get(ProcessedMaterials.STORAGE_BLOCK, material).get())
                .define('#', ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, material).get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
                .group(Reference.MOD_ID)
                .save(consumer, new ResourceLocation(Reference.MOD_ID, "block_from_gem/" + material.id));
      }

      // Gem from Block x4
      if (toCreate.contains("Gem") && toCreate.contains("Block") && toCreate.contains("4xRecipe")) {
        ShapelessRecipeBuilder.shapeless(ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, material).get(), 4)
                .requires(BlockHandler.backingStorageBlockTable.get(ProcessedMaterials.STORAGE_BLOCK, material).get())
                .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
                .group(Reference.MOD_ID)
                .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_block/" + material.id));
      }

      // Gem from Block x9
      if (toCreate.contains("Gem") && toCreate.contains("Block") && !toCreate.contains("4xRecipe")) {
        ShapelessRecipeBuilder.shapeless(ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, material).get(), 9)
                .requires(BlockHandler.backingStorageBlockTable.get(ProcessedMaterials.STORAGE_BLOCK, material).get())
                .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
                .group(Reference.MOD_ID)
                .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_block/" + material.id));
      }

      // Chunk Smelting & Blasting
      if (toCreate.contains("Ingot") && toCreate.contains("Chunk")) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, material).get()), ItemHandler.backingItemTable.get(ProcessedMaterials.INGOT, material).get(), 0.7F, 200)
                .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
                .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_chunk/smelting/" + material.id));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, material).get()), ItemHandler.backingItemTable.get(ProcessedMaterials.INGOT, material).get(), 0.7F, 100)
                .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
                .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_chunk/blasting/" + material.id));
      }

      // Ore Block from Chunk
      if (toCreate.contains("Ore") && toCreate.contains("Chunk")) {
        ShapelessRecipeBuilder.shapeless(baseOre(material).get())
                .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, material).get())
                .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
                .group(Reference.MOD_ID)
                .save(consumer, new ResourceLocation(Reference.MOD_ID, "ore_from_chunk_crafting/" + material.id));
      }

      // Cluster from Chunk
      if (toCreate.contains("Cluster") && toCreate.contains("Chunk")) {
        ShapelessRecipeBuilder.shapeless(ItemHandler.backingItemTable.get(ProcessedMaterials.CLUSTER, material).get())
                .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, material).get())
                .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, material).get())
                .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, material).get())
                .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, material).get())
                .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
                .group(Reference.MOD_ID)
                .save(consumer, new ResourceLocation(Reference.MOD_ID, "cluster_from_chunk/" + material.id));
      }

      // Chunk from Cluster
      if (toCreate.contains("Chunk") && toCreate.contains("Cluster")) {
        ShapelessRecipeBuilder.shapeless(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, material).get(), 4)
                .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.CLUSTER, material).get())
                .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
                .group(Reference.MOD_ID)
                .save(consumer, new ResourceLocation(Reference.MOD_ID, "chunk_from_cluster/" + material.id));
      }

      // Ore Smelting & Blasting
      if (toCreate.contains("Ore") && toCreate.contains("Ingot")) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(baseOre(material).get()), ItemHandler.backingItemTable.get(ProcessedMaterials.INGOT, material).get(), 0.7F, 200)
                .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
                .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/smelting/" + material.id));

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(baseOre(material).get()), ItemHandler.backingItemTable.get(ProcessedMaterials.INGOT, material).get(), 0.7F, 100)
                .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
                .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/blasting/" + material.id));
      }

      // Ore from Chunk in Stonecutter
      if (toCreate.contains("Chunk") && toCreate.contains("Ore")) {
        for (Strata stratum : Strata.values()) {
          SingleItemRecipeBuilder.stonecutting(Ingredient.of(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, material).get()), OreHandler.backingOreBlockTable.get(stratum, material).get())
                  .unlockedBy("has_stone", has(Blocks.COBBLESTONE))
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "ore_from_chunk_stonecutting/" + material.id + "/" + stratum.id));
        }
      }

      // Dust from Chunk
      if (toCreate.contains("Dust") && toCreate.contains("Chunk")) {
        ShapelessRecipeBuilder.shapeless(ItemHandler.backingItemTable.get(ProcessedMaterials.DUST, material).get(), 1)
                .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.CHUNK, material).get())
                .requires(ItemHandler.ENIGMATIC_HAMMER.get())
                .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
                .group(Reference.MOD_ID)
                .save(consumer, new ResourceLocation(Reference.MOD_ID, "dust_from_chunk/" + material.id));
      }

      // Plate from Ingot
      if (toCreate.contains("Ingot") && toCreate.contains("Plate")) {
        ShapelessRecipeBuilder.shapeless(ItemHandler.backingItemTable.get(ProcessedMaterials.PLATE, material).get(), 1)
                .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.INGOT, material).get())
                .requires(ItemHandler.ENIGMATIC_HAMMER.get())
                .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
                .group(Reference.MOD_ID)
                .save(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_ingot/" + material.id));
      }
      // Plate from Gem
      if (toCreate.contains("Gem") && toCreate.contains("Plate")) {
        ShapelessRecipeBuilder.shapeless(ItemHandler.backingItemTable.get(ProcessedMaterials.PLATE, material).get(), 1)
                .requires(ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, material).get())
                .requires(ItemHandler.ENIGMATIC_HAMMER.get())
                .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
                .group(Reference.MOD_ID)
                .save(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_gem/" + material.id));
      }

      // Gears from Ingots
      if (toCreate.contains("Ingot") && toCreate.contains("Gear")) {
        ShapedRecipeBuilder.shaped(ItemHandler.backingItemTable.get(ProcessedMaterials.GEAR, material).get())
                .define('I', ItemHandler.backingItemTable.get(ProcessedMaterials.INGOT, material).get())
                .define('N', Tags.Items.NUGGETS_IRON)
                .pattern(" I ")
                .pattern("INI")
                .pattern(" I ")
                .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
                .group(Reference.MOD_ID)
                .save(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_ingot/" + material.id));
      }

      // Gears from Gems
      if (toCreate.contains("Gem") && toCreate.contains("Gear")) {
        ShapedRecipeBuilder.shaped(ItemHandler.backingItemTable.get(ProcessedMaterials.GEAR, material).get())
                .define('G', ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, material).get())
                .define('N', Tags.Items.NUGGETS_IRON)
                .pattern(" G ")
                .pattern("GNG")
                .pattern(" G ")
                .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
                .group(Reference.MOD_ID)
                .save(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_gem/" + material.id));
      }

      // Rods from Ingots
      if (toCreate.contains("Ingot") && toCreate.contains("Rod")) {
        ShapedRecipeBuilder.shaped(ItemHandler.backingItemTable.get(ProcessedMaterials.ROD, material).get(), 2)
                .define('I', ItemHandler.backingItemTable.get(ProcessedMaterials.INGOT, material).get())
                .pattern(" I ")
                .pattern(" I ")
                .pattern("   ")
                .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
                .group(Reference.MOD_ID)
                .save(consumer, new ResourceLocation(Reference.MOD_ID, "rod_from_ingot/" + material.id));
      }

      // Rods from Gems
      if (toCreate.contains("Gem") && toCreate.contains("Rod")) {
        ShapedRecipeBuilder.shaped(ItemHandler.backingItemTable.get(ProcessedMaterials.ROD, material).get(), 2)
                .define('G', ItemHandler.backingItemTable.get(ProcessedMaterials.GEM, material).get())
                .pattern(" G ")
                .pattern(" G ")
                .pattern("   ")
                .unlockedBy("cobblestone", has(Blocks.COBBLESTONE))
                .group(Reference.MOD_ID)
                .save(consumer, new ResourceLocation(Reference.MOD_ID, "rod_from_gem/" + material.id));
      }

    }
  }

  static Supplier<EEOreBlock> baseOre(Materials material) {
    return OreHandler.backingOreBlockTable.get(Strata.STONE, material);
  }
}