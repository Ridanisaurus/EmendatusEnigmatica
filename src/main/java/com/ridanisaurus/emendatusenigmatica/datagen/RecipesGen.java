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
  protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {

		// Enigmatic Fortunizer
		ShapedRecipeBuilder.shaped(EERegistrar.ENIGMATIC_FORTUNIZER_ITEM::get)
				.pattern("PGP")
				.pattern("DNE")
				.pattern("RBR")
				.define('P', EETags.MATERIAL_PLATE.apply("iron"))
				.define('G', EETags.MATERIAL_GEAR.apply("gold"))
				.define('D', EETags.MATERIAL_GEAR.apply("diamond"))
				.define('N', EETags.MATERIAL_INGOT.apply("netherite"))
				.define('E', EETags.MATERIAL_GEAR.apply("emerald"))
				.define('R', EETags.MATERIAL_PLATE.apply("diamond"))
				.define('B', EETags.MATERIAL_STORAGE_BLOCK.apply("iron"))
				.group("emendatusenigmatica")
				.unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
				.save(consumer);

		ShapedRecipeBuilder.shaped(EERegistrar.ENIGMATIC_HAMMER::get)
				.pattern(" IN")
				.pattern(" SI")
				.pattern("S  ")
				.define('I', EETags.MATERIAL_INGOT.apply("iron"))
				.define('N', EETags.MATERIAL_NUGGET.apply("iron"))
				.define('S', EETags.MATERIAL_ROD.apply("wooden"))
				.group("emendatusenigmatica")
				.unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
				.save(consumer);

    /*-- Vanilla Compat --*/
    // Chunk Smelting & Blasting
    CookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_CHUNK.apply("coal")), Items.COAL, 0.7F, 200)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/smelting/coal"));
    CookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_CHUNK.apply("coal")), Items.COAL, 0.7F, 100)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/blasting/coal"));

    CookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_CHUNK.apply("iron")), Items.IRON_INGOT, 0.7F, 200)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_chunk/smelting/iron"));
    CookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_CHUNK.apply("iron")), Items.IRON_INGOT, 0.7F, 100)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_chunk/blasting/iron"));

    CookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_CHUNK.apply("gold")), Items.GOLD_INGOT, 0.7F, 200)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_chunk/smelting/gold"));
    CookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_CHUNK.apply("gold")), Items.GOLD_INGOT, 0.7F, 100)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_chunk/blasting/gold"));

    CookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_CHUNK.apply("diamond")), Items.DIAMOND, 0.7F, 200)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/smelting/diamond"));
    CookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_CHUNK.apply("diamond")), Items.DIAMOND, 0.7F, 100)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/blasting/diamond"));

    CookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_CHUNK.apply("emerald")), Items.EMERALD, 0.7F, 200)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/smelting/emerald"));
    CookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_CHUNK.apply("emerald")), Items.EMERALD, 0.7F, 100)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/blasting/emerald"));

    CookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_CHUNK.apply("lapis")), Items.LAPIS_LAZULI, 0.7F, 200)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/smelting/lapis"));
    CookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_CHUNK.apply("lapis")), Items.LAPIS_LAZULI, 0.7F, 100)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/blasting/lapis"));

    CookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_CHUNK.apply("redstone")), Items.REDSTONE, 0.7F, 200)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/smelting/redstone"));
    CookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_CHUNK.apply("redstone")), Items.REDSTONE, 0.7F, 100)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_chunk/blasting/redstone"));

    // Ingot from Dust
    CookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_DUST.apply("iron")), Items.GOLD_INGOT, 0.7F, 200)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_dust/smelting/iron"));
    CookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_DUST.apply("iron")), Items.GOLD_INGOT, 0.7F, 100)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_dust/blasting/iron"));

    CookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_DUST.apply("gold")), Items.GOLD_INGOT, 0.7F, 200)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_dust/smelting/gold"));
    CookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_DUST.apply("gold")), Items.GOLD_INGOT, 0.7F, 100)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_dust/blasting/gold"));

    // Ore Smelting & Blasting
    CookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("coal")), Items.COAL, 0.7F, 200)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/smelting/coal"));
    CookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("coal")), Items.COAL, 0.7F, 100)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/blasting/coal"));

    CookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("iron")), Items.IRON_INGOT, 0.7F, 200)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/smelting/iron"));
    CookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("iron")), Items.IRON_INGOT, 0.7F, 100)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/blasting/iron"));

    CookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("gold")), Items.GOLD_INGOT, 0.7F, 200)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/smelting/gold"));
    CookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("gold")), Items.GOLD_INGOT, 0.7F, 100)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/blasting/gold"));

    CookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("diamond")), Items.DIAMOND, 0.7F, 200)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/smelting/diamond"));
    CookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("diamond")), Items.DIAMOND, 0.7F, 100)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/blasting/diamond"));

    CookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("emerald")), Items.EMERALD, 0.7F, 200)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/smelting/emerald"));
    CookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("emerald")), Items.EMERALD, 0.7F, 100)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/blasting/emerald"));

    CookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("lapis")), Items.LAPIS_LAZULI, 0.7F, 200)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/smelting/lapis"));
    CookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("lapis")), Items.LAPIS_LAZULI, 0.7F, 100)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/blasting/lapis"));

    CookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_ORE.apply("redstone")), Items.REDSTONE, 0.7F, 200)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/smelting/redstone"));
    CookingRecipeBuilder.blasting(Ingredient.of(EETags.MATERIAL_ORE.apply("redstone")), Items.REDSTONE, 0.7F, 100)
            .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
            .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_ore/blasting/redstone"));

    for (MaterialModel material : EELoader.MATERIALS) {
      List<String> processedType = material.getProcessedType();

      // Ingot from Block
      if (processedType.contains("storage_block") && processedType.contains("ingot")) {
        ShapelessRecipeBuilder.shapeless(EERegistrar.ingotMap.get(material.getId()).get(), 9)
                .requires(EERegistrar.storageBlockItemMap.get(material.getId()).get())
                .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                .group(Reference.MOD_ID)
                .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_block/" + material.getId()));
      }

      // Ingot from Nugget
      if (processedType.contains("nugget") && processedType.contains("ingot")) {
          ShapedRecipeBuilder.shaped(EERegistrar.ingotMap.get(material.getId()).get())
                  .define('#', EERegistrar.nuggetMap.get(material.getId()).get())
                  .pattern("###")
                  .pattern("###")
                  .pattern("###")
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_nugget/" + material.getId()));
        }

        // Nugget from Ingot
        if (processedType.contains("ingot") && processedType.contains("nugget")) {
          ShapelessRecipeBuilder.shapeless(EERegistrar.nuggetMap.get(material.getId()).get(), 9)
                  .requires(EERegistrar.ingotMap.get(material.getId()).get())
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "nugget_from_ingot/" + material.getId()));
        }

        // Ingot from Dust
        if (processedType.contains("dust") && processedType.contains("ingot")) {
          CookingRecipeBuilder.smelting(Ingredient.of(EERegistrar.dustMap.get(material.getId()).get()),
                  EERegistrar.ingotMap.get(material.getId()).get(), 0.7F, 200)
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_dust/smelting/" + material.getId()));
          CookingRecipeBuilder.blasting(Ingredient.of(EERegistrar.dustMap.get(material.getId()).get()),
                  EERegistrar.ingotMap.get(material.getId()).get(), 0.7F, 100)
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_dust/blasting/" + material.getId()));
        }

        // Ingot from Chunk Smelting and Blasting
        if (processedType.contains("chunk") && processedType.contains("ingot")) {
          CookingRecipeBuilder.smelting(Ingredient.of(EERegistrar.chunkMap.get(material.getId()).get()),
                  EERegistrar.ingotMap.get(material.getId()).get(), 0.7F, 200)
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_chunk/smelting/" + material.getId()));
          CookingRecipeBuilder.blasting(Ingredient.of(EERegistrar.chunkMap.get(material.getId()).get()),
                  EERegistrar.ingotMap.get(material.getId()).get(), 0.7F, 100)
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_chunk/blasting/" + material.getId()));
        }

        // Block from Ingot
        if (processedType.contains("ingot") && processedType.contains("storage_block")) {
          ShapedRecipeBuilder.shaped(EERegistrar.storageBlockItemMap.get(material.getId()).get())
                  .define('#', EERegistrar.ingotMap.get(material.getId()).get())
                  .pattern("###")
                  .pattern("###")
                  .pattern("###")
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "block_from_ingot/" + material.getId()));
        }

        // Block from Gem x4
        if (processedType.contains("gem") && processedType.contains("storage_block") && processedType.contains("4x_block_recipe")) {
          ShapedRecipeBuilder.shaped(EERegistrar.storageBlockItemMap.get(material.getId()).get())
                  .define('#', EERegistrar.gemMap.get(material.getId()).get())
                  .pattern("##")
                  .pattern("##")
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "block_from_gem/" + material.getId()));
        }
        // Block from Gem x9
        if (processedType.contains("gem") && processedType.contains("storage_block") && !processedType.contains("4x_block_recipe")) {
          ShapedRecipeBuilder.shaped(EERegistrar.storageBlockItemMap.get(material.getId()).get())
                  .define('#', EERegistrar.gemMap.get(material.getId()).get())
                  .pattern("###")
                  .pattern("###")
                  .pattern("###")
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "block_from_gem/" + material.getId()));
        }

        // Gem from Block x4
        if (processedType.contains("gem") && processedType.contains("storage_block") && processedType.contains("4x_block_recipe")) {
          ShapelessRecipeBuilder.shapeless(EERegistrar.gemMap.get(material.getId()).get(), 4)
                  .requires(EERegistrar.storageBlockItemMap.get(material.getId()).get())
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_block/" + material.getId()));
        }
        // Gem from Block x9
        if (processedType.contains("gem") && processedType.contains("storage_block") && !processedType.contains("4x_block_recipe")) {
          ShapelessRecipeBuilder.shapeless(EERegistrar.gemMap.get(material.getId()).get(), 9)
                  .requires(EERegistrar.storageBlockItemMap.get(material.getId()).get())
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "gem_from_block/" + material.getId()));
        }

        // Cluster from Chunk
        if (processedType.contains("chunk") && processedType.contains("cluster")) {
          ShapelessRecipeBuilder.shapeless(EERegistrar.clusterMap.get(material.getId()).get(), 1)
                  .requires(EERegistrar.chunkMap.get(material.getId()).get())
                  .requires(EERegistrar.chunkMap.get(material.getId()).get())
                  .requires(EERegistrar.chunkMap.get(material.getId()).get())
                  .requires(EERegistrar.chunkMap.get(material.getId()).get())
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "cluster_from_chunk/" + material.getId()));
        }
      // Chunk from Cluster
      if (processedType.contains("cluster") && processedType.contains("chunk")) {
        ShapelessRecipeBuilder.shapeless(EERegistrar.chunkMap.get(material.getId()).get(), 4)
                .requires(EERegistrar.clusterMap.get(material.getId()).get())
                .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                .group(Reference.MOD_ID)
                .save(consumer, new ResourceLocation(Reference.MOD_ID, "chunk_from_cluster/" + material.getId()));
      }

        // Dust from Chunk
        if (processedType.contains("chunk") && processedType.contains("dust")) {
          ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
                  .requires(EERegistrar.chunkMap.get(material.getId()).get())
                  .requires(EERegistrar.ENIGMATIC_HAMMER.get())
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "dust_from_chunk/" + material.getId()));
        }

        // Plate from Ingot
        if (processedType.contains("ingot") && processedType.contains("plate")) {
          ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
                  .requires(EERegistrar.ingotMap.get(material.getId()).get())
                  .requires(EERegistrar.ENIGMATIC_HAMMER.get())
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_ingot/" + material.getId()));
        }
        // Plate from Gem
        if (processedType.contains("gem") && processedType.contains("plate")) {
          ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
                  .requires(EERegistrar.gemMap.get(material.getId()).get())
                  .requires(EERegistrar.ENIGMATIC_HAMMER.get())
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_gem/" + material.getId()));
        }

        // Gear from Ingot
        if (processedType.contains("ingot") && processedType.contains("gear")) {
          ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
                  .define('I', EERegistrar.ingotMap.get(material.getId()).get())
                  .define('N', new MockedNamedTag<>(new ResourceLocation("forge", "nuggets/iron")))
                  .pattern(" I ")
                  .pattern("INI")
                  .pattern(" I ")
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_ingot/" + material.getId()));
        }
        // Gear from Gem
        if (processedType.contains("gem") && processedType.contains("gear")) {
          ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
                  .define('G', EERegistrar.gemMap.get(material.getId()).get())
                  .define('N', new MockedNamedTag<>(new ResourceLocation("forge", "nuggets/iron")))
                  .pattern(" G ")
                  .pattern("GNG")
                  .pattern(" G ")
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_gem/" + material.getId()));
        }

        // Rod from Ingot
        if (processedType.contains("ingot") && processedType.contains("rod")) {
          ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get(), 2)
                  .define('I', EERegistrar.ingotMap.get(material.getId()).get())
                  .pattern(" I ")
                  .pattern(" I ")
                  .pattern("   ")
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "rod_from_ingot/" + material.getId()));
        }
        // Rod from Gem
        if (processedType.contains("gem") && processedType.contains("rod")) {
          ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get(), 2)
                  .define('G', EERegistrar.gemMap.get(material.getId()).get())
                  .pattern(" G ")
                  .pattern(" G ")
                  .pattern("   ")
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "rod_from_gem/" + material.getId()));
        }


        // Gears, Rods and Plates
        if(material.getId().equals("iron")) {
          ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
                  .define('I', EETags.MATERIAL_INGOT.apply("iron"))
                  .define('N', EETags.MATERIAL_NUGGET.apply("iron"))
                  .pattern(" I ")
                  .pattern("INI")
                  .pattern(" I ")
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_ingot/iron"));

          ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
                  .define('I', EETags.MATERIAL_INGOT.apply("iron"))
                  .pattern(" I ")
                  .pattern(" I ")
                  .pattern("   ")
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "rod_from_ingot/iron"));

          ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
                  .requires(EETags.MATERIAL_INGOT.apply("iron"))
                  .requires(EERegistrar.ENIGMATIC_HAMMER.get())
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_ingot/iron"));
        }
        if(material.getId().equals("gold")) {
          ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
                  .define('I', EETags.MATERIAL_INGOT.apply("iron"))
                  .define('N', EETags.MATERIAL_NUGGET.apply("gold"))
                  .pattern(" I ")
                  .pattern("INI")
                  .pattern(" I ")
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_ingot/gold"));

          ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
                  .define('I', EETags.MATERIAL_INGOT.apply("gold"))
                  .pattern(" I ")
                  .pattern(" I ")
                  .pattern("   ")
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "rod_from_ingot/gold"));

          ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
                  .requires(EETags.MATERIAL_INGOT.apply("gold"))
                  .requires(EERegistrar.ENIGMATIC_HAMMER.get())
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_ingot/gold"));
        }
        if(material.getId().equals("diamond")) {
          ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
                  .define('G', EETags.MATERIAL_GEM.apply("diamond"))
                  .define('N', EETags.MATERIAL_NUGGET.apply("iron"))
                  .pattern(" G ")
                  .pattern("GNG")
                  .pattern(" G ")
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_gem/diamond"));

          ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
                  .define('G', EETags.MATERIAL_GEM.apply("diamond"))
                  .pattern(" G ")
                  .pattern(" G ")
                  .pattern("   ")
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "rod_from_gem/diamond"));

          ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
                  .requires(EETags.MATERIAL_GEM.apply("diamond"))
                  .requires(EERegistrar.ENIGMATIC_HAMMER.get())
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_gem/diamond"));
        }
        if(material.getId().equals("emerald")) {
          ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
                  .define('G', EETags.MATERIAL_GEM.apply("emerald"))
                  .define('N', EETags.MATERIAL_NUGGET.apply("iron"))
                  .pattern(" G ")
                  .pattern("GNG")
                  .pattern(" G ")
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_gem/emerald"));

          ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
                  .define('G', EETags.MATERIAL_GEM.apply("emerald"))
                  .pattern(" G ")
                  .pattern(" G ")
                  .pattern("   ")
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "rod_from_gem/emerald"));

          ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
                  .requires(EETags.MATERIAL_GEM.apply("emerald"))
                  .requires(EERegistrar.ENIGMATIC_HAMMER.get())
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_gem/emerald"));
        }
        if(material.getId().equals("lapis")) {
          ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
                  .define('G', EETags.MATERIAL_GEM.apply("lapis"))
                  .define('N', EETags.MATERIAL_NUGGET.apply("iron"))
                  .pattern(" G ")
                  .pattern("GNG")
                  .pattern(" G ")
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_gem/lapis"));

          ShapedRecipeBuilder.shaped(EERegistrar.rodMap.get(material.getId()).get())
                  .define('G', EETags.MATERIAL_GEM.apply("lapis"))
                  .pattern(" G ")
                  .pattern(" G ")
                  .pattern("   ")
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "rod_from_gem/lapis"));

          ShapelessRecipeBuilder.shapeless(EERegistrar.plateMap.get(material.getId()).get(), 1)
                  .requires(EETags.MATERIAL_GEM.apply("lapis"))
                  .requires(EERegistrar.ENIGMATIC_HAMMER.get())
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "plate_from_gem/lapis"));
        }
        if(material.getId().equals("nether_quartz")) {
          ShapedRecipeBuilder.shaped(EERegistrar.gearMap.get(material.getId()).get())
                  .define('G', EETags.MATERIAL_GEM.apply("quartz"))
                  .define('N', EETags.MATERIAL_NUGGET.apply("iron"))
                  .pattern(" G ")
                  .pattern("GNG")
                  .pattern(" G ")
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "gear_from_gem/nether_quartz"));
        }

        // Silicon from Quartz
        if(material.getId().equals("silicon")) {
          CookingRecipeBuilder.smelting(Ingredient.of(EETags.MATERIAL_GEM.apply("quartz")), EERegistrar.gemMap.get(material.getId()).get(), 0.7F, 200)
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "silicon_from_quartz/smelting/nether_quartz"));
        }

        // Alloy Dust
        if(material.getId().equals("bronze")) {
          ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 4)
                  .requires(EETags.MATERIAL_DUST.apply("copper"))
                  .requires(EETags.MATERIAL_DUST.apply("copper"))
                  .requires(EETags.MATERIAL_DUST.apply("copper"))
                  .requires(EETags.MATERIAL_DUST.apply("tin"))
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/bronze"));
        }
        if(material.getId().equals("brass")) {
          ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 4)
                  .requires(EETags.MATERIAL_DUST.apply("copper"))
                  .requires(EETags.MATERIAL_DUST.apply("copper"))
                  .requires(EETags.MATERIAL_DUST.apply("copper"))
                  .requires(EETags.MATERIAL_DUST.apply("zinc"))
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/brass"));
        }
        if(material.getId().equals("constantan")) {
          ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 2)
                  .requires(EETags.MATERIAL_DUST.apply("copper"))
                  .requires(EETags.MATERIAL_DUST.apply("nickel"))
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/constantan"));
        }
        if(material.getId().equals("electrum")) {
          ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 2)
                  .requires(EETags.MATERIAL_DUST.apply("gold"))
                  .requires(EETags.MATERIAL_DUST.apply("silver"))
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/electrum"));
        }
        if(material.getId().equals("invar")) {
          ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 3)
                  .requires(EETags.MATERIAL_DUST.apply("iron"))
                  .requires(EETags.MATERIAL_DUST.apply("iron"))
                  .requires(EETags.MATERIAL_DUST.apply("nickel"))
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/invar"));
        }
        if(material.getId().equals("lumium")) {
          ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 4)
                  .requires(EETags.MATERIAL_DUST.apply("tin"))
                  .requires(EETags.MATERIAL_DUST.apply("tin"))
                  .requires(EETags.MATERIAL_DUST.apply("tin"))
                  .requires(EETags.MATERIAL_DUST.apply("silver"))
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/lumium"));
        }
        if(material.getId().equals("signalum")) {
          ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 4)
                  .requires(EETags.MATERIAL_DUST.apply("copper"))
                  .requires(EETags.MATERIAL_DUST.apply("copper"))
                  .requires(EETags.MATERIAL_DUST.apply("copper"))
                  .requires(EETags.MATERIAL_DUST.apply("silver"))
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/signalum"));
        }
        if(material.getId().equals("enderium")) {
          ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 2)
                  .requires(EETags.MATERIAL_DUST.apply("lead"))
                  .requires(EETags.MATERIAL_DUST.apply("lead"))
                  .requires(EETags.MATERIAL_DUST.apply("lead"))
                  .requires(EETags.MATERIAL_DUST.apply("diamond"))
                  .requires(EETags.MATERIAL_NONE.apply("ender_pearls"))
                  .requires(EETags.MATERIAL_NONE.apply("ender_pearls"))
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "alloy_dust/enderium"));
        }

        // Dust from Ore
        if(material.getId().equals("nether_quartz")) {
          ShapelessRecipeBuilder.shapeless(EERegistrar.dustMap.get(material.getId()).get(), 1)
                  .requires(EETags.MATERIAL_ORE.apply("quartz"))
                  .requires(EERegistrar.ENIGMATIC_HAMMER.get())
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .group(Reference.MOD_ID)
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "dust_from_ore/nether_quartz"));
        }
    }

    for (MaterialModel material : EELoader.MATERIALS) {
      for (StrataModel stratum : EELoader.STRATA) {
          // Ore from Chunk
        List<String> processedType = material.getProcessedType();
        if (processedType.contains("chunk") && processedType.contains("ore")) {
          if (stratum.getId().equals("minecraft_stone")) {
            ShapelessRecipeBuilder.shapeless(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get())
                    .requires(EERegistrar.chunkMap.get(material.getId()).get())
                    .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                    .group(Reference.MOD_ID)
                    .save(consumer, new ResourceLocation(Reference.MOD_ID, "ore_from_chunk_crafting/" + material.getId() + "_" + stratum.getId()));
          }
        }

        // Ore Smelting & Blasting
        if (processedType.contains("ingot") && processedType.contains("ore")) {
          CookingRecipeBuilder.smelting(Ingredient.of(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get()),
                  EERegistrar.ingotMap.get(material.getId()).get(), 0.7F, 200)
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/smelting/" + material.getId() + "_" + stratum.getId()));
          CookingRecipeBuilder.blasting(Ingredient.of(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get()),
                  EERegistrar.ingotMap.get(material.getId()).get(), 0.7F, 100)
                  .unlockedBy("cobblestone", InventoryChangeTrigger.Instance.hasItems(Blocks.COBBLESTONE))
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "ingot_from_ore/blasting/" + material.getId() + "_" + stratum.getId()));
        }

        // Ore from Chunk in Stonecutter
        if (processedType.contains("chunk") && processedType.contains("ore")) {
          SingleItemRecipeBuilder.stonecutting(Ingredient.of(EERegistrar.chunkMap.get(material.getId()).get()),
                  EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get())
                  .unlocks("has_stone", has(Blocks.COBBLESTONE))
                  .save(consumer, new ResourceLocation(Reference.MOD_ID, "ore_from_chunk_stonecutting/" + material.getId() + "/" + stratum.getId()));
        }
      }
    }
  }

  @Override
  public String getName() {
    return "Emendatus Enigmatica Recipes";
  }
}