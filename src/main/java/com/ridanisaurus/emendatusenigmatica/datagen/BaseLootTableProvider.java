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
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

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
  public void run(HashCache cache) {
    this.addTables();
    Map<ResourceLocation, LootTable> tables = new HashMap<>();
    for (Map.Entry<Block, LootTable.Builder> entry : this.blockLootTable.entrySet()) {
      tables.put(entry.getKey().getLootTable(),
              entry.getValue().setParamSet(LootContextParamSets.BLOCK).build());
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
    LootPool.Builder builder = LootPool.lootPool()
            .setRolls(ConstantValue.exactly(1))
            .add(LootItem.lootTableItem(block))
            .when(ExplosionCondition.survivesExplosion());
    return LootTable.lootTable().withPool(builder);
  }

  protected LootTable.Builder createItemLootTable(Item item) {
    LootPool.Builder builder = LootPool.lootPool()
            .setRolls(ConstantValue.exactly(1))
            .add(LootItem.lootTableItem(item))
            .when(ExplosionCondition.survivesExplosion());
    return LootTable.lootTable().withPool(builder);
  }

  protected LootTable.Builder createSpecialTable(Item item, ItemLike itemProvider) {
    LootPool.Builder builder = LootPool.lootPool()
            .setRolls(ConstantValue.exactly(1))
            .add(LootItem.lootTableItem(item)
              .when(MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1)))))
              .otherwise(LootItem.lootTableItem(itemProvider)
              .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
              .apply(ApplyExplosionDecay.explosionDecay())));
    return LootTable.lootTable().withPool(builder);
  }

  protected LootTable.Builder createCountTable(Item item, ItemLike itemProvider, float minCount, float maxCount) {
    LootPool.Builder builder = LootPool.lootPool()
            .setRolls(ConstantValue.exactly(1))
            .add(LootItem.lootTableItem(item)
                    .when(MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1)))))
                    .otherwise(LootItem.lootTableItem(itemProvider)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(minCount, maxCount)))
                            .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
                            .apply(ApplyExplosionDecay.explosionDecay())));
    return LootTable.lootTable().withPool(builder);
  }

  // Actually write out the tables in the output folder
  private void writeTables(HashCache cache, Map<ResourceLocation, LootTable> tables) {
    Path outputFolder = this.generator.getOutputFolder();
    tables.forEach((key, lootTable) -> {
      Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
      try {
        DataProvider.save(GSON, cache, LootTables.serialize(lootTable), path);
      } catch (IOException e) {
        EmendatusEnigmatica.LOGGER.error("Couldn't write loot table {}", path, e);
      }
    });
  }
}