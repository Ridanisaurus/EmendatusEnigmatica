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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.LootTableProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.loot.conditions.SurvivesExplosion;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.ExplosionDecay;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseLootTableProvider extends LootTableProvider {

  private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

  // Filled by subclasses
  protected final Map<Block, LootTable.Builder> blockLootTable = new HashMap<>();
  protected final Map<Item, LootTable.Builder> itemLootTable = new HashMap<>();

  private final DataGenerator generator;

  public BaseLootTableProvider(DataGenerator dataGeneratorIn) {
    super(dataGeneratorIn);
    this.generator = dataGeneratorIn;
  }

  @Override
  // Entry point
  public void act(DirectoryCache cache) {
    this.addTables();
    Map<ResourceLocation, LootTable> tables = new HashMap<>();
    for (Map.Entry<Block, LootTable.Builder> entry : this.blockLootTable.entrySet()) {
      tables.put(entry.getKey().getLootTable(),
              entry.getValue().setParameterSet(LootParameterSets.BLOCK).build());
    }

    this.writeTables(cache, tables);
  }

  @Override
  public String getName() {
    return "Emendatus Enigmatica LootTables";
  }

  // Subclasses can override this to fill the 'lootTables' map.
  protected abstract void addTables();

  protected LootTable.Builder createBlockLootTable(Block block) {
    LootPool.Builder builder = LootPool.builder()
            .rolls(ConstantRange.of(1))
            .addEntry(ItemLootEntry.builder(block))
            .acceptCondition(SurvivesExplosion.builder());
    return LootTable.builder().addLootPool(builder);
  }

  protected LootTable.Builder createItemLootTable(Item item) {
    LootPool.Builder builder = LootPool.builder()
            .rolls(ConstantRange.of(1))
            .addEntry(ItemLootEntry.builder(item))
            .acceptCondition(SurvivesExplosion.builder());
    return LootTable.builder().addLootPool(builder);
  }

  protected LootTable.Builder createSpecialTable(Item item, IItemProvider itemProvider) {
    LootPool.Builder builder = LootPool.builder()
            .rolls(ConstantRange.of(1))
            .addEntry(ItemLootEntry.builder(item)
              .acceptCondition(MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1)))))
              .alternatively(ItemLootEntry.builder(itemProvider)
              .acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE))
              .acceptFunction(ExplosionDecay.builder())));
    return LootTable.builder().addLootPool(builder);
  }

  protected LootTable.Builder createCountTable(Item item, IItemProvider itemProvider, float minCount, float maxCount) {
    LootPool.Builder builder = LootPool.builder()
            .rolls(ConstantRange.of(1))
            .addEntry(ItemLootEntry.builder(item)
                    .acceptCondition(MatchTool.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1)))))
                    .alternatively(ItemLootEntry.builder(itemProvider)
                            .acceptFunction(SetCount.builder(RandomValueRange.of(minCount, maxCount)))
                            .acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE))
                            .acceptFunction(ExplosionDecay.builder())));
    return LootTable.builder().addLootPool(builder);
  }

  // Actually write out the tables in the output folder
  private void writeTables(DirectoryCache cache, Map<ResourceLocation, LootTable> tables) {
    Path outputFolder = this.generator.getOutputFolder();
    tables.forEach((key, lootTable) -> {
      Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
      try {
        IDataProvider.save(GSON, cache, LootTableManager.toJson(lootTable), path);
      } catch (IOException e) {
        EmendatusEnigmatica.LOGGER.error("Couldn't write loot table {}", path, e);
      }
    });
  }
}