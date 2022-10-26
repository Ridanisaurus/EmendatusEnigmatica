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
import com.ridanisaurus.emendatusenigmatica.blocks.BasicStorageBlock;
import com.ridanisaurus.emendatusenigmatica.blocks.BasicStorageBlockItem;
import com.ridanisaurus.emendatusenigmatica.blocks.GemOreBlock;
import com.ridanisaurus.emendatusenigmatica.blocks.MetalOreBlock;
import com.ridanisaurus.emendatusenigmatica.items.BasicBurnableItem;
import com.ridanisaurus.emendatusenigmatica.items.BasicItem;
import com.ridanisaurus.emendatusenigmatica.items.ItemHammer;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class EERegistrar {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Reference.MOD_ID);
//	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Reference.MOD_ID);

	// Blocks
	public static Table<String, String, RegistryObject<Block>> oreBlockTable = HashBasedTable.create();
	public static Table<String, String, RegistryObject<Item>> oreBlockItemTable = HashBasedTable.create();
	public static Map<String, RegistryObject<Block>> storageBlockMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> storageBlockItemMap = new HashMap<>();

	// Items
	public static Map<String, RegistryObject<Item>> rawMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> ingotMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> nuggetMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> gemMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> dustMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> plateMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> gearMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> rodMap = new HashMap<>();

	// Fluids
	public static Map<String, RegistryObject<FlowingFluid>> fluidSourceMap = new HashMap<>();
	public static Map<String, RegistryObject<FlowingFluid>> fluidFlowingMap = new HashMap<>();
	public static Map<String, RegistryObject<LiquidBlock>> fluidBlockMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> fluidBucketMap = new HashMap<>();

	public static final ResourceLocation FLUID_STILL_RL = new ResourceLocation(Reference.MOD_ID, "fluids/fluid_still");
	public static final ResourceLocation FLUID_FLOWING_RL = new ResourceLocation(Reference.MOD_ID, "fluids/fluid_flow");
	public static final ResourceLocation FLUID_OVERLAY_RL = new ResourceLocation(Reference.MOD_ID, "fluids/fluid_overlay");

	public static RegistryObject<FlowingFluid> fluidSource;
	public static RegistryObject<FlowingFluid> fluidFlowing;
	public static RegistryObject<LiquidBlock> fluidBlock;
	public static RegistryObject<Item> fluidBucket;

	public static ForgeFlowingFluid.Properties makeProperties(Supplier<FlowingFluid> source, Supplier<FlowingFluid> flowing, Supplier<LiquidBlock> block, Supplier<Item> bucket, int color) {
		return new ForgeFlowingFluid.Properties(source, flowing, FluidAttributes.builder(FLUID_STILL_RL, FLUID_FLOWING_RL)
				.overlay(FLUID_OVERLAY_RL)
				.color(color)
				.density(3000)
				.viscosity(6000)
				.temperature(1300)
				.luminosity(15)
				.sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA)
				.rarity(Rarity.COMMON)
		)
				.block(block)
				.bucket(bucket);
	}

	public static void registerFluids(MaterialModel material) {
		String fluidName = "molten_" + material.getId();

		fluidSource = FLUIDS.register(fluidName,
				() -> new ForgeFlowingFluid.Source(makeProperties(fluidSourceMap.get(material.getId()), fluidFlowingMap.get(material.getId()), fluidBlockMap.get(material.getId()), fluidBucketMap.get(material.getId()), material.getColors().getFluidColor())));
		fluidFlowing = FLUIDS.register(fluidName + "_flowing",
				() -> new ForgeFlowingFluid.Flowing(makeProperties(fluidSourceMap.get(material.getId()), fluidFlowingMap.get(material.getId()), fluidBlockMap.get(material.getId()), fluidBucketMap.get(material.getId()), material.getColors().getFluidColor())));
		fluidBlock = BLOCKS.register(fluidName,
				() -> new LiquidBlock(fluidSourceMap.get(material.getId()), BlockBehaviour.Properties.of(Material.LAVA).noCollission().strength(100).noDrops()));
		fluidBucket = ITEMS.register(fluidName + "_bucket",
				() -> new BucketItem(fluidSourceMap.get(material.getId()), new Item.Properties().stacksTo(1).tab(EmendatusEnigmatica.TAB)));

		fluidSourceMap.put(material.getId(), fluidSource);
		fluidFlowingMap.put(material.getId(), fluidFlowing);
		fluidBlockMap.put(material.getId(), fluidBlock);
		fluidBucketMap.put(material.getId(), fluidBucket);
	}

	// TODO: [RID] Switch Harvest level and Tool to Tags
	public static void registerOre(StrataModel strata, MaterialModel material) {
		String oreName = material.getId() + (!strata.getId().equals("minecraft_stone") ? "_" + strata.getSuffix() : "") + "_ore";
		RegistryObject<Block> oreBlock;
		if (material.getProperties().getOreBlockType().equals("gem")) {
			oreBlock = BLOCKS.register(oreName, () -> new GemOreBlock(
					Material.STONE,
					strata.getHardness(),
					strata.getResistance(),
//					material.getProperties().getHarvestLevel(),
//					strata.getHarvestTool(),
					material.getLocalizedName(),
					material.getOreDrop().getVanillaMin(),
					material.getOreDrop().getVanillaMax(),
					material.getColors().getHighlightColor(),
					material.getColors().getBaseColor(),
					material.getColors().getShadeColor()));
		} else {
			oreBlock = BLOCKS.register(oreName, () -> new MetalOreBlock(
					Material.STONE,
					strata.getHardness(),
					strata.getResistance(),
//					material.getProperties().getHarvestLevel(),
//					strata.getHarvestTool(),
					material.getLocalizedName(),
					material.getColors().getHighlightColor(),
					material.getColors().getBaseColor(),
					material.getColors().getShadeColor()));
		}

		oreBlockTable.put(strata.getId(), material.getId(), oreBlock);

		oreBlockItemTable.put(strata.getId(), material.getId(), ITEMS.register(oreName, () -> new BlockItem(oreBlock.get(), new Item.Properties().tab(EmendatusEnigmatica.TAB))));
	}

	public static void registerStorageBlocks(MaterialModel material) {
		String storageBlockName = material.getId() + "_block";

		RegistryObject<Block> storageBlock = BLOCKS.register(storageBlockName, () -> new BasicStorageBlock(
				Material.STONE,
				3f,
				3f,
//				material.getProperties().getHarvestLevel(),
//				ToolType.PICKAXE,
				material.getLocalizedName(),
				material.getColors().getHighlightColor(),
				material.getColors().getBaseColor(),
				material.getColors().getShadeColor()));

		storageBlockMap.put(material.getId(), storageBlock);

		if (material.getProperties().isBurnable()) {
			storageBlockItemMap.put(material.getId(), ITEMS.register(storageBlockName, () -> new BasicStorageBlockItem(
					storageBlock.get(),
					material.getProperties().getBurnTime() * 10)));
		} else {
			storageBlockItemMap.put(material.getId(), ITEMS.register(storageBlockName, () -> new BasicStorageBlockItem(
					storageBlock.get(), 0)));
		}
	}

	public static void registerRaws(MaterialModel material) {
		String itemName = "raw_" + material.getId();

		if (material.getProperties().isBurnable()) {
			rawMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material.getProperties().getBurnTime(), material.getColors().getHighlightColor(), material.getColors().getBaseColor(), material.getColors().getShadeColor())));
		} else {
			rawMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material.getColors().getHighlightColor(), material.getColors().getBaseColor(), material.getColors().getShadeColor())));
		}
	}

//	public static void registerClusters(MaterialModel material) {
//		String itemName = material.getId() + "_cluster";
//
//		if (material.getProperties().isBurnable()) {
//			clusterMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material.getProperties().getBurnTime() * 4, material.getColors().getHighlightColor(), material.getColors().getBaseColor(), material.getColors().getShadeColor())));
//		} else {
//			clusterMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material.getColors().getHighlightColor(), material.getColors().getBaseColor(), material.getColors().getShadeColor())));
//		}
//	}

	public static void registerIngots(MaterialModel material) {
		String itemName = material.getId() + "_ingot";

		if (material.getProperties().isBurnable()) {
			ingotMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material.getProperties().getBurnTime(), material.getColors().getHighlightColor(), material.getColors().getBaseColor(), material.getColors().getShadeColor())));
		} else {
			ingotMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material.getColors().getHighlightColor(), material.getColors().getBaseColor(), material.getColors().getShadeColor())));
		}
	}

	public static void registerNuggets(MaterialModel material) {
		String itemName = material.getId() + "_nugget";

		if (material.getProperties().isBurnable()) {
			nuggetMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material.getProperties().getBurnTime() / 10, material.getColors().getHighlightColor(), material.getColors().getBaseColor(), material.getColors().getShadeColor())));
		} else {
			nuggetMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material.getColors().getHighlightColor(), material.getColors().getBaseColor(), material.getColors().getShadeColor())));
		}
	}

	public static void registerGems(MaterialModel material) {
		String itemName = material.getId() + "_gem";

		if (material.getProperties().isBurnable()) {
			gemMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material.getProperties().getBurnTime(), material.getColors().getHighlightColor(), material.getColors().getBaseColor(), material.getColors().getShadeColor())));
		} else {
			gemMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material.getColors().getHighlightColor(), material.getColors().getBaseColor(), material.getColors().getShadeColor())));
		}
	}

	public static void registerDusts(MaterialModel material) {
		String itemName = material.getId() + "_dust";

		if (material.getProperties().isBurnable()) {
			dustMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material.getProperties().getBurnTime(), material.getColors().getHighlightColor(), material.getColors().getBaseColor(), material.getColors().getShadeColor())));
		} else {
			dustMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material.getColors().getHighlightColor(), material.getColors().getBaseColor(), material.getColors().getShadeColor())));
		}
	}

	public static void registerPlates(MaterialModel material) {
		String itemName = material.getId() + "_plate";

		if (material.getProperties().isBurnable()) {
			plateMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material.getProperties().getBurnTime(), material.getColors().getHighlightColor(), material.getColors().getBaseColor(), material.getColors().getShadeColor())));
		} else {
			plateMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material.getColors().getHighlightColor(), material.getColors().getBaseColor(), material.getColors().getShadeColor())));
		}
	}

	public static void registerGears(MaterialModel material) {
		String itemName = material.getId() + "_gear";

		if (material.getProperties().isBurnable()) {
			gearMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material.getProperties().getBurnTime() * 4, material.getColors().getHighlightColor(), material.getColors().getBaseColor(), material.getColors().getShadeColor())));
		} else {
			gearMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material.getColors().getHighlightColor(), material.getColors().getBaseColor(), material.getColors().getShadeColor())));
		}
	}

	public static void registerRods(MaterialModel material) {
		String itemName = material.getId() + "_rod";

		if (material.getProperties().isBurnable()) {
			rodMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material.getProperties().getBurnTime() * 2, material.getColors().getHighlightColor(), material.getColors().getBaseColor(), material.getColors().getShadeColor())));
		} else {
			rodMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material.getColors().getHighlightColor(), material.getColors().getBaseColor(), material.getColors().getShadeColor())));
		}
	}

	// Hammer
	public static final RegistryObject<Item> ENIGMATIC_HAMMER = ITEMS.register("enigmatic_hammer", ItemHammer::new);

	public static void finalize(IEventBus eventBus) {
		ITEMS.register(eventBus);
		BLOCKS.register(eventBus);
		FLUIDS.register(eventBus);
//		TILE_ENTITY.register(eventBus);
	}
}