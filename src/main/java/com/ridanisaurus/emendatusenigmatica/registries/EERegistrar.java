/*
 *  MIT License
 *
 *  Copyright (c) 2020 Ridanisaurus
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package com.ridanisaurus.emendatusenigmatica.registries;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.blocks.BasicOreBlock;
import com.ridanisaurus.emendatusenigmatica.blocks.BasicStorageBlock;
import com.ridanisaurus.emendatusenigmatica.items.BasicItem;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

public class EERegistrar {
  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

  public static Table<String, String, RegistryObject<Block>> oreBlockTable = HashBasedTable.create();
  public static Table<String, String, RegistryObject<Item>> oreBlockItemTable = HashBasedTable.create();
  public static Map<String, RegistryObject<Block>> storageBlockMap = new HashMap<>();
  public static Map<String, RegistryObject<Item>> storageBlockItemMap = new HashMap<>();

  public static Map<String, RegistryObject<Item>> chunkMap = new HashMap<>();
  public static Map<String, RegistryObject<Item>> ingotMap = new HashMap<>();
  public static Map<String, RegistryObject<Item>> nuggetMap = new HashMap<>();
  public static Map<String, RegistryObject<Item>> gemMap = new HashMap<>();
  public static Map<String, RegistryObject<Item>> dustMap = new HashMap<>();
  public static Map<String, RegistryObject<Item>> plateMap = new HashMap<>();
  public static Map<String, RegistryObject<Item>> gearMap = new HashMap<>();
  public static Map<String, RegistryObject<Item>> rodMap = new HashMap<>();

  public static void RegisterOre(StrataModel strata, MaterialModel material) {
    String oreName = material.getId() + (!strata.getId().equals("stone") ? "_" + strata.getSuffix() : "") + "_ore";

    RegistryObject<Block> oreBlock = BLOCKS.register(oreName, () -> new BasicOreBlock(
            Material.ROCK,
            material.getProperties().getHardness(),
            material.getProperties().getResistance(),
            material.getProperties().getHarvestLevel(),
            ToolType.PICKAXE,
            material.getLocalisedName()));

    oreBlockTable.put(strata.getId(), material.getId(), oreBlock);

    oreBlockItemTable.put(strata.getId(), material.getId(), ITEMS.register(oreName, () -> new BlockItem(oreBlock.get(), new Item.Properties().group(EmendatusEnigmatica.TAB))));
  }

  public static void registerStorageBlocks(MaterialModel material) {
    String storageBlockName = material.getId() + "_block";

    RegistryObject<Block> storageBlock = BLOCKS.register(storageBlockName, () -> new BasicStorageBlock(
            Material.ROCK,
            material.getProperties().getHardness(),
            material.getProperties().getResistance(),
            material.getProperties().getHarvestLevel(),
            ToolType.PICKAXE,
            material.getLocalisedName()));

    storageBlockMap.put(material.getId(), storageBlock);

    storageBlockItemMap.put(material.getId(), ITEMS.register(storageBlockName, () -> new BlockItem(storageBlock.get(), new Item.Properties().group(EmendatusEnigmatica.TAB))));
  }

  public static void RegisterChunks(MaterialModel material) {
    String itemName = material.getId() + "_chunk";

    chunkMap.put(material.getId(), ITEMS.register(itemName, BasicItem::new));
  }

  public static void RegisterIngots(MaterialModel material) {
    String itemName = material.getId() + "_ingot";

    ingotMap.put(material.getId(), ITEMS.register(itemName, BasicItem::new));
  }

  public static void RegisterNuggets(MaterialModel material) {
    String itemName = material.getId() + "_nugget";

    nuggetMap.put(material.getId(), ITEMS.register(itemName, BasicItem::new));
  }

  public static void RegisterGems(MaterialModel material) {
    String itemName = material.getId() + "_gem";

    gemMap.put(material.getId(), ITEMS.register(itemName, BasicItem::new));
  }

  public static void RegisterDusts(MaterialModel material) {
    String itemName = material.getId() + "_dust";

    dustMap.put(material.getId(), ITEMS.register(itemName, BasicItem::new));
  }

  public static void RegisterPlates(MaterialModel material) {
    String itemName = material.getId() + "_plate";

    plateMap.put(material.getId(), ITEMS.register(itemName, BasicItem::new));
  }

  public static void RegisterGears(MaterialModel material) {
    String itemName = material.getId() + "_gear";

    gearMap.put(material.getId(), ITEMS.register(itemName, BasicItem::new));
  }

  public static void RegisterRods(MaterialModel material) {
    String itemName = material.getId() + "_rod";

    rodMap.put(material.getId(), ITEMS.register(itemName, BasicItem::new));
  }

  public static void Finalize(IEventBus eventBus) {
    ITEMS.register(eventBus);
    BLOCKS.register(eventBus);
  }
}