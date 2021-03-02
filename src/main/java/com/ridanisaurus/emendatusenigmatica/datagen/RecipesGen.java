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
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EETags;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

import java.util.List;
import java.util.function.Consumer;

public class RecipesGen extends RecipeProvider {

  public RecipesGen(DataGenerator gen) {
    super(gen);
  }

  @Override
  protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

    // Enigmatic Fortunizer
    ShapedRecipeBuilder.shapedRecipe(EERegistrar.ENIGMATIC_FORTUNIZER.get())
            .patternLine("PGP")
            .patternLine("DNE")
            .patternLine("RBR")
            .key('P', EETags.MATERIAL_PLATE.apply("iron"))
            .key('G', EETags.MATERIAL_GEAR.apply("gold"))
            .key('D', EETags.MATERIAL_GEAR.apply("diamond"))
            .key('N', EETags.MATERIAL_INGOT.apply("netherite"))
            .key('E', EETags.MATERIAL_GEAR.apply("emerald"))
            .key('R', EETags.MATERIAL_ROD.apply("enderium"))
            .key('B', EETags.MATERIAL_STORAGE_BLOCK.apply("iron"))
            .setGroup("emendatusenigmatica")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer);

    ShapedRecipeBuilder.shapedRecipe(EERegistrar.ENIGMATIC_HAMMER.get())
            .patternLine(" IN")
            .patternLine(" SI")
            .patternLine("S  ")
            .key('I', EETags.MATERIAL_INGOT.apply("iron"))
            .key('N', EETags.MATERIAL_NUGGET.apply("iron"))
            .key('S', EETags.MATERIAL_ROD.apply("wooden"))
            .setGroup("emendatusenigmatica")
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer);

    /*-- Vanilla Compat --*/
    // Chunk Smelting & Blasting
    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(EETags.MATERIAL_CHUNK.apply("coal")), Items.COAL, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/smelting/coal"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(EETags.MATERIAL_CHUNK.apply("coal")), Items.COAL, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/blasting/coal"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(EETags.MATERIAL_CHUNK.apply("iron")), Items.IRON_INGOT, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_chunk/smelting/iron"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(EETags.MATERIAL_CHUNK.apply("iron")), Items.IRON_INGOT, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_chunk/blasting/iron"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(EETags.MATERIAL_CHUNK.apply("gold")), Items.GOLD_INGOT, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_chunk/smelting/gold"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(EETags.MATERIAL_CHUNK.apply("gold")), Items.GOLD_INGOT, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_chunk/blasting/gold"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(EETags.MATERIAL_CHUNK.apply("diamond")), Items.DIAMOND, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/smelting/diamond"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(EETags.MATERIAL_CHUNK.apply("diamond")), Items.DIAMOND, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/blasting/diamond"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(EETags.MATERIAL_CHUNK.apply("emerald")), Items.EMERALD, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/smelting/emerald"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(EETags.MATERIAL_CHUNK.apply("emerald")), Items.EMERALD, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/blasting/emerald"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(EETags.MATERIAL_CHUNK.apply("lapis")), Items.LAPIS_LAZULI, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/smelting/lapis"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(EETags.MATERIAL_CHUNK.apply("lapis")), Items.LAPIS_LAZULI, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/blasting/lapis"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(EETags.MATERIAL_CHUNK.apply("redstone")), Items.REDSTONE, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/smelting/redstone"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(EETags.MATERIAL_CHUNK.apply("redstone")), Items.REDSTONE, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/blasting/redstone"));

    // Ingot from Dust
    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(EETags.MATERIAL_DUST.apply("iron")), Items.GOLD_INGOT, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_dust/smelting/iron"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(EETags.MATERIAL_DUST.apply("iron")), Items.GOLD_INGOT, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_dust/blasting/iron"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(EETags.MATERIAL_DUST.apply("gold")), Items.GOLD_INGOT, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_dust/smelting/gold"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(EETags.MATERIAL_DUST.apply("gold")), Items.GOLD_INGOT, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_dust/blasting/gold"));

    // Ore Smelting & Blasting
    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(EETags.MATERIAL_ORE.apply("coal")), Items.COAL, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/smelting/coal"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(EETags.MATERIAL_ORE.apply("coal")), Items.COAL, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/blasting/coal"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(EETags.MATERIAL_ORE.apply("iron")), Items.IRON_INGOT, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/smelting/iron"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(EETags.MATERIAL_ORE.apply("iron")), Items.IRON_INGOT, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/blasting/iron"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(EETags.MATERIAL_ORE.apply("gold")), Items.GOLD_INGOT, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/smelting/gold"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(EETags.MATERIAL_ORE.apply("gold")), Items.GOLD_INGOT, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/blasting/gold"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(EETags.MATERIAL_ORE.apply("diamond")), Items.DIAMOND, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/smelting/diamond"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(EETags.MATERIAL_ORE.apply("diamond")), Items.DIAMOND, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/blasting/diamond"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(EETags.MATERIAL_ORE.apply("emerald")), Items.EMERALD, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/smelting/emerald"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(EETags.MATERIAL_ORE.apply("emerald")), Items.EMERALD, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/blasting/emerald"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(EETags.MATERIAL_ORE.apply("lapis")), Items.LAPIS_LAZULI, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/smelting/lapis"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(EETags.MATERIAL_ORE.apply("lapis")), Items.LAPIS_LAZULI, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/blasting/lapis"));

    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(EETags.MATERIAL_ORE.apply("redstone")), Items.REDSTONE, 0.7F, 200)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/smelting/redstone"));
    CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(EETags.MATERIAL_ORE.apply("redstone")), Items.REDSTONE, 0.7F, 100)
            .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
            .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/blasting/redstone"));
    
    for (MaterialModel material : EELoader.MATERIALS) {
      List<String> processedType = material.getProcessedType();

      // Ingot from Block
      if (processedType.contains("storage_block") && processedType.contains("ingot")) {
        ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.ingotMap.get(material.getId()).get(), 9)
                .addIngredient(EERegistrar.storageBlockItemMap.get(material.getId()).get())
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
        if (processedType.contains("ingot") && processedType.contains("storage_block")) {
          ShapedRecipeBuilder.shapedRecipe(EERegistrar.storageBlockItemMap.get(material.getId()).get())
                  .key('#', EERegistrar.ingotMap.get(material.getId()).get())
                  .patternLine("###")
                  .patternLine("###")
                  .patternLine("###")
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "block_from_ingot/" + material.getId()));
        }

        // Block from Gem x4
        if (processedType.contains("gem") && processedType.contains("storage_block") && processedType.contains("4x_block_recipe")) {
          ShapedRecipeBuilder.shapedRecipe(EERegistrar.storageBlockItemMap.get(material.getId()).get())
                  .key('#', EERegistrar.gemMap.get(material.getId()).get())
                  .patternLine("##")
                  .patternLine("##")
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "block_from_gem/" + material.getId()));
        }
        // Block from Gem x9
        if (processedType.contains("gem") && processedType.contains("storage_block") && !processedType.contains("4x_block_recipe")) {
          ShapedRecipeBuilder.shapedRecipe(EERegistrar.storageBlockItemMap.get(material.getId()).get())
                  .key('#', EERegistrar.gemMap.get(material.getId()).get())
                  .patternLine("###")
                  .patternLine("###")
                  .patternLine("###")
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "block_from_gem/" + material.getId()));
        }

        // Gem from Block x4
        if (processedType.contains("gem") && processedType.contains("storage_block") && processedType.contains("4x_block_recipe")) {
          ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.gemMap.get(material.getId()).get(), 4)
                  .addIngredient(EERegistrar.storageBlockItemMap.get(material.getId()).get())
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_block/" + material.getId()));
        }
        // Gem from Block x9
        if (processedType.contains("gem") && processedType.contains("storage_block") && !processedType.contains("4x_block_recipe")) {
          ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.gemMap.get(material.getId()).get(), 9)
                  .addIngredient(EERegistrar.storageBlockItemMap.get(material.getId()).get())
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_block/" + material.getId()));
        }

        // Dust from Chunk
        if (processedType.contains("chunk") && processedType.contains("dust")) {
          ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.dustMap.get(material.getId()).get(), 1)
                  .addIngredient(EERegistrar.chunkMap.get(material.getId()).get())
                  .addIngredient(EERegistrar.ENIGMATIC_HAMMER.get())
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "dust_from_chunk/" + material.getId()));
        }

        // Plate from Ingot
        if (processedType.contains("ingot") && processedType.contains("plate")) {
          ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.plateMap.get(material.getId()).get(), 1)
                  .addIngredient(EERegistrar.ingotMap.get(material.getId()).get())
                  .addIngredient(EERegistrar.ENIGMATIC_HAMMER.get())
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_ingot/" + material.getId()));
        }
        // Plate from Gem
        if (processedType.contains("gem") && processedType.contains("plate")) {
          ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.plateMap.get(material.getId()).get(), 1)
                  .addIngredient(EERegistrar.gemMap.get(material.getId()).get())
                  .addIngredient(EERegistrar.ENIGMATIC_HAMMER.get())
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_gem/" + material.getId()));
        }

        // Gear from Ingot
        if (processedType.contains("ingot") && processedType.contains("gear")) {
          ShapedRecipeBuilder.shapedRecipe(EERegistrar.gearMap.get(material.getId()).get())
                  .key('I', EERegistrar.ingotMap.get(material.getId()).get())
                  .key('N', new MockedNamedTag<>(new ResourceLocation("forge", "nuggets/iron")))
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
                  .key('N', new MockedNamedTag<>(new ResourceLocation("forge", "nuggets/iron")))
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


        // Gears, Rods and Plates
        if(material.getId().equals("iron")) {
          ShapedRecipeBuilder.shapedRecipe(EERegistrar.gearMap.get(material.getId()).get())
                  .key('I', EETags.MATERIAL_INGOT.apply("iron"))
                  .key('N', EETags.MATERIAL_NUGGET.apply("iron"))
                  .patternLine(" I ")
                  .patternLine("INI")
                  .patternLine(" I ")
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_ingot/iron"));

          ShapedRecipeBuilder.shapedRecipe(EERegistrar.rodMap.get(material.getId()).get())
                  .key('I', EETags.MATERIAL_INGOT.apply("iron"))
                  .patternLine(" I ")
                  .patternLine(" I ")
                  .patternLine("   ")
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "rod_from_ingot/iron"));

          ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.plateMap.get(material.getId()).get(), 1)
                  .addIngredient(EETags.MATERIAL_INGOT.apply("iron"))
                  .addIngredient(EERegistrar.ENIGMATIC_HAMMER.get())
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_ingot/iron"));
        }
        if(material.getId().equals("gold")) {
          ShapedRecipeBuilder.shapedRecipe(EERegistrar.gearMap.get(material.getId()).get())
                  .key('I', EETags.MATERIAL_INGOT.apply("iron"))
                  .key('N', EETags.MATERIAL_NUGGET.apply("gold"))
                  .patternLine(" I ")
                  .patternLine("INI")
                  .patternLine(" I ")
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_ingot/gold"));

          ShapedRecipeBuilder.shapedRecipe(EERegistrar.rodMap.get(material.getId()).get())
                  .key('I', EETags.MATERIAL_INGOT.apply("gold"))
                  .patternLine(" I ")
                  .patternLine(" I ")
                  .patternLine("   ")
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "rod_from_ingot/gold"));

          ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.plateMap.get(material.getId()).get(), 1)
                  .addIngredient(EETags.MATERIAL_INGOT.apply("gold"))
                  .addIngredient(EERegistrar.ENIGMATIC_HAMMER.get())
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_ingot/gold"));
        }
        if(material.getId().equals("diamond")) {
          ShapedRecipeBuilder.shapedRecipe(EERegistrar.gearMap.get(material.getId()).get())
                  .key('G', EETags.MATERIAL_GEM.apply("diamond"))
                  .key('N', EETags.MATERIAL_NUGGET.apply("iron"))
                  .patternLine(" G ")
                  .patternLine("GNG")
                  .patternLine(" G ")
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_gem/diamond"));

          ShapedRecipeBuilder.shapedRecipe(EERegistrar.rodMap.get(material.getId()).get())
                  .key('G', EETags.MATERIAL_GEM.apply("diamond"))
                  .patternLine(" G ")
                  .patternLine(" G ")
                  .patternLine("   ")
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "rod_from_gem/diamond"));

          ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.plateMap.get(material.getId()).get(), 1)
                  .addIngredient(EETags.MATERIAL_GEM.apply("diamond"))
                  .addIngredient(EERegistrar.ENIGMATIC_HAMMER.get())
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_gem/diamond"));
        }
        if(material.getId().equals("emerald")) {
          ShapedRecipeBuilder.shapedRecipe(EERegistrar.gearMap.get(material.getId()).get())
                  .key('G', EETags.MATERIAL_GEM.apply("emerald"))
                  .key('N', EETags.MATERIAL_NUGGET.apply("iron"))
                  .patternLine(" G ")
                  .patternLine("GNG")
                  .patternLine(" G ")
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_gem/emerald"));

          ShapedRecipeBuilder.shapedRecipe(EERegistrar.rodMap.get(material.getId()).get())
                  .key('G', EETags.MATERIAL_GEM.apply("emerald"))
                  .patternLine(" G ")
                  .patternLine(" G ")
                  .patternLine("   ")
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "rod_from_gem/emerald"));

          ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.plateMap.get(material.getId()).get(), 1)
                  .addIngredient(EETags.MATERIAL_GEM.apply("emerald"))
                  .addIngredient(EERegistrar.ENIGMATIC_HAMMER.get())
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_gem/emerald"));
        }
        if(material.getId().equals("lapis")) {
          ShapedRecipeBuilder.shapedRecipe(EERegistrar.gearMap.get(material.getId()).get())
                  .key('G', EETags.MATERIAL_GEM.apply("lapis"))
                  .key('N', EETags.MATERIAL_NUGGET.apply("iron"))
                  .patternLine(" G ")
                  .patternLine("GNG")
                  .patternLine(" G ")
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_gem/lapis"));

          ShapedRecipeBuilder.shapedRecipe(EERegistrar.rodMap.get(material.getId()).get())
                  .key('G', EETags.MATERIAL_GEM.apply("lapis"))
                  .patternLine(" G ")
                  .patternLine(" G ")
                  .patternLine("   ")
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "rod_from_gem/lapis"));

          ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.plateMap.get(material.getId()).get(), 1)
                  .addIngredient(EETags.MATERIAL_GEM.apply("lapis"))
                  .addIngredient(EERegistrar.ENIGMATIC_HAMMER.get())
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_gem/lapis"));
        }
        if(material.getId().equals("nether_quartz")) {
          ShapedRecipeBuilder.shapedRecipe(EERegistrar.gearMap.get(material.getId()).get())
                  .key('G', EETags.MATERIAL_GEM.apply("quartz"))
                  .key('N', EETags.MATERIAL_NUGGET.apply("iron"))
                  .patternLine(" G ")
                  .patternLine("GNG")
                  .patternLine(" G ")
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_gem/nether_quartz"));
        }

        // Silicon from Quartz
        if(material.getId().equals("silicon")) {
          CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(EETags.MATERIAL_GEM.apply("quartz")), EERegistrar.gemMap.get(material.getId()).get(), 0.7F, 200)
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "silicon_from_quartz/smelting/nether_quartz"));
        }

        // Alloy Dust
        if(material.getId().equals("bronze")) {
          ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.dustMap.get(material.getId()).get(), 4)
                  .addIngredient(EETags.MATERIAL_DUST.apply("copper"))
                  .addIngredient(EETags.MATERIAL_DUST.apply("copper"))
                  .addIngredient(EETags.MATERIAL_DUST.apply("copper"))
                  .addIngredient(EETags.MATERIAL_DUST.apply("tin"))
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/bronze"));
        }
        if(material.getId().equals("brass")) {
          ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.dustMap.get(material.getId()).get(), 4)
                  .addIngredient(EETags.MATERIAL_DUST.apply("copper"))
                  .addIngredient(EETags.MATERIAL_DUST.apply("copper"))
                  .addIngredient(EETags.MATERIAL_DUST.apply("copper"))
                  .addIngredient(EETags.MATERIAL_DUST.apply("zinc"))
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/brass"));
        }
        if(material.getId().equals("constantan")) {
          ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.dustMap.get(material.getId()).get(), 2)
                  .addIngredient(EETags.MATERIAL_DUST.apply("copper"))
                  .addIngredient(EETags.MATERIAL_DUST.apply("nickel"))
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/constantan"));
        }
        if(material.getId().equals("electrum")) {
          ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.dustMap.get(material.getId()).get(), 2)
                  .addIngredient(EETags.MATERIAL_DUST.apply("gold"))
                  .addIngredient(EETags.MATERIAL_DUST.apply("silver"))
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/electrum"));
        }
        if(material.getId().equals("invar")) {
          ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.dustMap.get(material.getId()).get(), 3)
                  .addIngredient(EETags.MATERIAL_DUST.apply("iron"))
                  .addIngredient(EETags.MATERIAL_DUST.apply("iron"))
                  .addIngredient(EETags.MATERIAL_DUST.apply("nickel"))
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/invar"));
        }
        if(material.getId().equals("lumium")) {
          ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.dustMap.get(material.getId()).get(), 4)
                  .addIngredient(EETags.MATERIAL_DUST.apply("tin"))
                  .addIngredient(EETags.MATERIAL_DUST.apply("tin"))
                  .addIngredient(EETags.MATERIAL_DUST.apply("tin"))
                  .addIngredient(EETags.MATERIAL_DUST.apply("silver"))
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/lumium"));
        }
        if(material.getId().equals("signalum")) {
          ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.dustMap.get(material.getId()).get(), 4)
                  .addIngredient(EETags.MATERIAL_DUST.apply("copper"))
                  .addIngredient(EETags.MATERIAL_DUST.apply("copper"))
                  .addIngredient(EETags.MATERIAL_DUST.apply("copper"))
                  .addIngredient(EETags.MATERIAL_DUST.apply("silver"))
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/signalum"));
        }
        if(material.getId().equals("enderium")) {
          ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.dustMap.get(material.getId()).get(), 2)
                  .addIngredient(EETags.MATERIAL_DUST.apply("lead"))
                  .addIngredient(EETags.MATERIAL_DUST.apply("lead"))
                  .addIngredient(EETags.MATERIAL_DUST.apply("lead"))
                  .addIngredient(EETags.MATERIAL_DUST.apply("diamond"))
                  .addIngredient(EETags.MATERIAL_GEM.apply("ender"))
                  .addIngredient(EETags.MATERIAL_GEM.apply("ender"))
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/signalum"));
        }

        // Dust from Ore
        if(material.getId().equals("nether_quartz")) {
          ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.dustMap.get(material.getId()).get(), 1)
                  .addIngredient(EETags.MATERIAL_ORE.apply("quartz"))
                  .addIngredient(EERegistrar.ENIGMATIC_HAMMER.get())
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .setGroup(Reference.MOD_ID)
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "dust_from_ore/nether_quartz"));
        }
    }

    for (MaterialModel material : EELoader.MATERIALS) {
      for (StrataModel stratum : EELoader.STRATA) {
          // Ore from Chunk
        List<String> processedType = material.getProcessedType();
        if (processedType.contains("chunk") && processedType.contains("ore")) {
          if (stratum.getId().equals("minecraft_stone")) {
            ShapelessRecipeBuilder.shapelessRecipe(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get())
                    .addIngredient(EERegistrar.chunkMap.get(material.getId()).get())
                    .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                    .setGroup(Reference.MOD_ID)
                    .build(consumer, new ResourceLocation(Reference.MOD_ID, "ore_from_chunk_crafting/" + material.getId() + "_" + stratum.getId()));
          }
        }

        // Ore Smelting & Blasting
        if (processedType.contains("ingot") && processedType.contains("ore")) {
          CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get()),
                  EERegistrar.ingotMap.get(material.getId()).get(), 0.7F, 200)
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/smelting/" + material.getId() + "_" + stratum.getId()));
          CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get()),
                  EERegistrar.ingotMap.get(material.getId()).get(), 0.7F, 100)
                  .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/blasting/" + material.getId() + "_" + stratum.getId()));
        }

        // Ore from Chunk in Stonecutter
        if (processedType.contains("chunk") && processedType.contains("ore")) {
          SingleItemRecipeBuilder.stonecuttingRecipe(Ingredient.fromItems(EERegistrar.chunkMap.get(material.getId()).get()),
                  EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get())
                  .addCriterion("has_stone", hasItem(Blocks.COBBLESTONE))
                  .build(consumer, new ResourceLocation(Reference.MOD_ID, "ore_from_chunk_stonecutting/" + material.getId() + "/" + stratum.getId()));
        }
      }
    }
  }

  @Override
  public String getName() {
    return "Emendatus Enigmatica Recipes";
  }
}