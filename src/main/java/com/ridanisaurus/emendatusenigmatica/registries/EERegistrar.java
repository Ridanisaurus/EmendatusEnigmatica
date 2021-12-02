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
import com.ridanisaurus.emendatusenigmatica.util.ColorFromItemHelper;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ColorHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class EERegistrar {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Reference.MOD_ID);
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


    private static RegistryObject<ForgeFlowingFluid> source;
    private static RegistryObject<ForgeFlowingFluid> flowing;
    private static RegistryObject<FlowingFluidBlock> fluidBlock;
    private static RegistryObject<Item> bucket;

    public static Map<String, RegistryObject<ForgeFlowingFluid>> fluidSourceMap = new HashMap<>();
    public static Map<String, RegistryObject<FlowingFluidBlock>> fluidBlockMap = new HashMap<>();
    public static Map<String, RegistryObject<Item>> fluidBucketMap = new HashMap<>();

    public static final ResourceLocation FLUID_STILL_RL = new ResourceLocation(Reference.MOD_ID, "fluids/fluid_still");
    public static final ResourceLocation FLUID_FLOWING_RL = new ResourceLocation(Reference.MOD_ID, "fluids/fluid_flow");
    public static final ResourceLocation FLUID_OVERLAY_RL = new ResourceLocation(Reference.MOD_ID, "fluids/fluid_overlay");

    public static ForgeFlowingFluid.Properties makeProperties(FluidAttributes.Builder attributeBuilder, Supplier<ForgeFlowingFluid> still, Supplier<ForgeFlowingFluid> flowing, Supplier<Item> bucket, Supplier<FlowingFluidBlock> block)
    {
        return new ForgeFlowingFluid.Properties(still, flowing,attributeBuilder)
                .bucket(bucket).block(block);
    }
    // TODO: Fix the fluid color tinting
    public static FluidAttributes.Builder createAttributes(int color) {
        return FluidAttributes.builder(FLUID_STILL_RL, FLUID_FLOWING_RL)
                .color(0xFF+color)
                .overlay(FLUID_OVERLAY_RL)
                .rarity(Rarity.COMMON)
                .density(3000)
                .viscosity(6000)
                .temperature(1300)
                .sound(SoundEvents.ITEM_BUCKET_FILL_LAVA, SoundEvents.ITEM_BUCKET_EMPTY_LAVA)
                .luminosity(15);
    }

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

    public static void registerFluids(MaterialModel material) {
        String itemName = "molten_" + material.getId();
        source = FLUIDS.register(itemName, () -> new ForgeFlowingFluid.Source(
                makeProperties(createAttributes(material.getColor()), source, flowing, bucket, fluidBlock))
        );
        flowing = FLUIDS.register(itemName + "_flowing", () -> new ForgeFlowingFluid.Flowing(
                makeProperties(createAttributes(material.getColor()), source, flowing, bucket, fluidBlock))
        );
        fluidBlock = BLOCKS.register(itemName, () ->
                new FlowingFluidBlock(source,
                        AbstractBlock.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops())
        );
        bucket = ITEMS.register(itemName + "_bucket", () ->
                new BucketItem(source,
                        new Item.Properties().group(EmendatusEnigmatica.TAB).maxStackSize(1))
        );

        fluidSourceMap.put(material.getId(), source);
        fluidBlockMap.put(material.getId(), fluidBlock);
        fluidBucketMap.put(material.getId(), bucket);
    }

    public static void finalize(IEventBus eventBus) {
        ITEMS.register(eventBus);
        BLOCKS.register(eventBus);
        FLUIDS.register(eventBus);
        TILE_ENTITY.register(eventBus);
    }

}