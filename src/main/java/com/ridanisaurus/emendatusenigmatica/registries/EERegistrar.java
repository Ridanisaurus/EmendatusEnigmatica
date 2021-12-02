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
import com.ridanisaurus.emendatusenigmatica.blocks.*;
import com.ridanisaurus.emendatusenigmatica.items.BasicBurnableItem;
import com.ridanisaurus.emendatusenigmatica.items.BasicItem;
import com.ridanisaurus.emendatusenigmatica.items.ItemHammer;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.tiles.EnigmaticFortunizerTile;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
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
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Reference.MOD_ID);

    public static Table<String, String, RegistryObject<Block>> oreBlockTable = HashBasedTable.create();
    public static Table<String, String, RegistryObject<Item>> oreBlockItemTable = HashBasedTable.create();
    public static Map<String, RegistryObject<Block>> storageBlockMap = new HashMap<>();
    public static Map<String, RegistryObject<Item>> storageBlockItemMap = new HashMap<>();

    public static Map<String, RegistryObject<Item>> chunkMap = new HashMap<>();
    public static Map<String, RegistryObject<Item>> clusterMap = new HashMap<>();
    public static Map<String, RegistryObject<Item>> ingotMap = new HashMap<>();
    public static Map<String, RegistryObject<Item>> nuggetMap = new HashMap<>();
    public static Map<String, RegistryObject<Item>> gemMap = new HashMap<>();
    public static Map<String, RegistryObject<Item>> dustMap = new HashMap<>();
    public static Map<String, RegistryObject<Item>> plateMap = new HashMap<>();
    public static Map<String, RegistryObject<Item>> gearMap = new HashMap<>();
    public static Map<String, RegistryObject<Item>> rodMap = new HashMap<>();

    // Machine Items
    public static final RegistryObject<Block> ENIGMATIC_FORTUNIZER = BLOCKS.register("enigmatic_fortunizer", EnigmaticFortunizer::new);
    public static final RegistryObject<TileEntityType<?>> ENIGMATIC_FORTUNIZER_TILE = TILE_ENTITY.register("enigmatic_fortunizer", () -> TileEntityType.Builder.create(EnigmaticFortunizerTile::new, ENIGMATIC_FORTUNIZER.get()).build(null));
    public static final RegistryObject<Item> ENIGMATIC_FORTUNIZER_ITEM = ITEMS.register("enigmatic_fortunizer", () -> new BasicBlockItem(ENIGMATIC_FORTUNIZER.get()));

    // Hammer
    public static final RegistryObject<Item> ENIGMATIC_HAMMER = ITEMS.register("enigmatic_hammer", ItemHammer::new);


    public static void registerOre(StrataModel strata, MaterialModel material) {
        String oreName = material.getId() + (!strata.getId().equals("minecraft_stone") ? "_" + strata.getSuffix() : "") + "_ore";
        RegistryObject<Block> oreBlock;
        if (material.getOreBlockType().equals("gem")) {
            oreBlock = BLOCKS.register(oreName, () -> new GemOreBlock(
                    Material.ROCK,
                    material.getProperties().getHardness(),
                    material.getProperties().getResistance(),
                    material.getProperties().getHarvestLevel(),
                    strata.getHarvestTool(),
                    material.getLocalisedName(),
                    material.getDropMin(),
                    material.getDropMax(),
                    material.getColor()));
        } else {
            oreBlock = BLOCKS.register(oreName, () -> new MetalOreBlock(
                    Material.ROCK,
                    material.getProperties().getHardness(),
                    material.getProperties().getResistance(),
                    material.getProperties().getHarvestLevel(),
                    strata.getHarvestTool(),
                    material.getLocalisedName(),
                    material.getColor()));
        }

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
                material.getLocalisedName(),
                material.getColor()));

        storageBlockMap.put(material.getId(), storageBlock);

        if (material.isBurnable()) {
            storageBlockItemMap.put(material.getId(), ITEMS.register(storageBlockName, () -> new BasicStorageBlockItem(
                    storageBlock.get(),
                    material.getBurnTime() * 10)));
        } else {
            storageBlockItemMap.put(material.getId(), ITEMS.register(storageBlockName, () -> new BasicStorageBlockItem(
                    storageBlock.get(), 0)));
        }
    }

    public static void registerChunks(MaterialModel material) {
        String itemName = material.getId() + "_chunk";

        if (material.isBurnable()) {
            chunkMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material.getBurnTime(), material.getColor())));
        } else {
            chunkMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material.getColor())));
        }
    }

    public static void registerClusters(MaterialModel material) {
        String itemName = material.getId() + "_cluster";

        if (material.isBurnable()) {
            clusterMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material.getBurnTime() * 4, material.getColor())));
        } else {
            clusterMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material.getColor())));
        }
    }

    public static void registerIngots(MaterialModel material) {
        String itemName = material.getId() + "_ingot";

        if (material.isBurnable()) {
            ingotMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material.getBurnTime(), material.getColor())));
        } else {
            ingotMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material.getColor())));
        }
    }

    public static void registerNuggets(MaterialModel material) {
        String itemName = material.getId() + "_nugget";

        if (material.isBurnable()) {
            nuggetMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material.getBurnTime() / 10, material.getColor())));
        } else {
            nuggetMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material.getColor())));
        }
    }

    public static void registerGems(MaterialModel material) {
        String itemName = material.getId() + "_gem";

        if (material.isBurnable()) {
            gemMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material.getBurnTime(), material.getColor())));
        } else {
            gemMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material.getColor())));
        }
    }

    public static void registerDusts(MaterialModel material) {
        String itemName = material.getId() + "_dust";

        if (material.isBurnable()) {
            dustMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material.getBurnTime(), material.getColor())));
        } else {
            dustMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material.getColor())));
        }
    }

    public static void registerPlates(MaterialModel material) {
        String itemName = material.getId() + "_plate";

        if (material.isBurnable()) {
            plateMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material.getBurnTime(), material.getColor())));
        } else {
            plateMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material.getColor())));
        }
    }

    public static void registerGears(MaterialModel material) {
        String itemName = material.getId() + "_gear";

        if (material.isBurnable()) {
            gearMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material.getBurnTime() * 4, material.getColor())));
        } else {
            gearMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material.getColor())));
        }
    }

    public static void registerRods(MaterialModel material) {
        String itemName = material.getId() + "_rod";

        if (material.isBurnable()) {
            rodMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material.getBurnTime() * 2, material.getColor())));
        } else {
            rodMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material.getColor())));
        }
    }

    public static void finalize(IEventBus eventBus) {
        ITEMS.register(eventBus);
        BLOCKS.register(eventBus);
        TILE_ENTITY.register(eventBus);
    }

}