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

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.Table;
import com.mojang.math.Vector3f;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.blocks.*;
import com.ridanisaurus.emendatusenigmatica.fluids.BasicFluidType;
import com.ridanisaurus.emendatusenigmatica.items.*;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
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
	public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Reference.MOD_ID);
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Reference.MOD_ID);

	// Ore Blocks
	public static Table<String, String, RegistryObject<Block>> oreBlockTable = HashBasedTable.create();
	public static Table<String, String, RegistryObject<Item>> oreBlockItemTable = HashBasedTable.create();

	// Ore Sample Blocks
	public static Table<String, String, RegistryObject<Block>> oreSampleBlockTable = HashBasedTable.create();
	public static Table<String, String, RegistryObject<Item>> oreSampleBlockItemTable = HashBasedTable.create();

	// Storage Blocks
	public static Map<String, RegistryObject<Block>> storageBlockMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> storageBlockItemMap = new HashMap<>();

	// Weathering Blocks
	public static Map<String, RegistryObject<Block>> exposedBlockMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> exposedBlockItemMap = new HashMap<>();
	public static Map<String, RegistryObject<Block>> weatheredBlockMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> weatheredBlockItemMap = new HashMap<>();
	public static Map<String, RegistryObject<Block>> oxidizedBlockMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> oxidizedBlockItemMap = new HashMap<>();

	// Raw Blocks
	public static Map<String, RegistryObject<Block>> rawBlockMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> rawBlockItemMap = new HashMap<>();

	// Cluster Blocks
	public static Map<String, RegistryObject<Block>> buddingBlockMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> buddingBlockItemMap = new HashMap<>();
	public static Map<String, RegistryObject<Block>> smallBudBlockMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> smallBudBlockItemMap = new HashMap<>();
	public static Map<String, RegistryObject<Block>> mediumBudBlockMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> mediumBudBlockItemMap = new HashMap<>();
	public static Map<String, RegistryObject<Block>> largeBudBlockMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> largeBudBlockItemMap = new HashMap<>();
	public static Map<String, RegistryObject<Block>> clusterBlockMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> clusterBlockItemMap = new HashMap<>();
	public static Map<String, RegistryObject<Block>> clusterShardBlockMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> clusterShardBlockItemMap = new HashMap<>();

	// Items
	public static Map<String, RegistryObject<Item>> rawMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> ingotMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> nuggetMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> gemMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> dustMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> plateMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> gearMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> rodMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> clusterShardMap = new HashMap<>();

	// Tools
	public static Map<String, RegistryObject<SwordItem>> swordMap = new HashMap<>();
	public static Map<String, RegistryObject<PickaxeItem>> pickaxeMap = new HashMap<>();
	public static Map<String, RegistryObject<AxeItem>> axeMap = new HashMap<>();
	public static Map<String, RegistryObject<ShovelItem>> shovelMap = new HashMap<>();
	public static Map<String, RegistryObject<HoeItem>> hoeMap = new HashMap<>();
	public static Map<String, RegistryObject<PaxelItem>> paxelMap = new HashMap<>();

	// Armor
	public static Map<String, RegistryObject<ArmorItem>> helmetMap = new HashMap<>();
	public static Map<String, RegistryObject<ArmorItem>> chestplateMap = new HashMap<>();
	public static Map<String, RegistryObject<ArmorItem>> leggingsMap = new HashMap<>();
	public static Map<String, RegistryObject<ArmorItem>> bootsMap = new HashMap<>();
	public static Map<String, RegistryObject<ShieldItem>> shieldMap = new HashMap<>();

	// Fluids
	public static Map<String, RegistryObject<FluidType>> fluidTypeMap = new HashMap<>();
	public static Map<String, RegistryObject<FlowingFluid>> fluidSourceMap = new HashMap<>();
	public static Map<String, RegistryObject<FlowingFluid>> fluidFlowingMap = new HashMap<>();
	public static Map<String, RegistryObject<LiquidBlock>> fluidBlockMap = new HashMap<>();
	public static Map<String, RegistryObject<Item>> fluidBucketMap = new HashMap<>();

	public static final ResourceLocation FLUID_STILL_RL = new ResourceLocation(Reference.MOD_ID, "fluids/fluid_still");
	public static final ResourceLocation FLUID_FLOWING_RL = new ResourceLocation(Reference.MOD_ID, "fluids/fluid_flow");
	public static final ResourceLocation FLUID_OVERLAY_RL = new ResourceLocation(Reference.MOD_ID, "fluids/fluid_overlay");

	public static RegistryObject<FluidType> fluidType;
	public static RegistryObject<FlowingFluid> fluidSource;
	public static RegistryObject<FlowingFluid> fluidFlowing;
	public static RegistryObject<LiquidBlock> fluidBlock;
	public static RegistryObject<Item> fluidBucket;

	public static void registerOre(StrataModel strata, MaterialModel material) {
		String oreName = material.getId() + (!strata.getId().equals("minecraft_stone") ? "_" + strata.getSuffix() : "") + "_ore";
		RegistryObject<Block> oreBlock;
		if (material.getProperties().getMaterialType().equals("gem")) {
			if(material.getProperties().hasParticles()) {
				oreBlock = BLOCKS.register(oreName, () -> new GemOreBlockWithParticles(strata, material));
			} else {
				oreBlock = BLOCKS.register(oreName, () -> new GemOreBlock(strata, material));
			}
		} else {
			if (material.getProperties().hasParticles()) {
				oreBlock = BLOCKS.register(oreName, () -> new MetalOreBlockWithParticles(strata, material));
			} else {
				oreBlock = BLOCKS.register(oreName, () -> new MetalOreBlock(strata, material));
			}
		}
		oreBlockTable.put(strata.getId(), material.getId(), oreBlock);
		oreBlockItemTable.put(strata.getId(), material.getId(), ITEMS.register(oreName, () -> new BlockItem(oreBlock.get(), new Item.Properties().tab(EmendatusEnigmatica.TAB))));
	}

	public static void registerOreSample(StrataModel strata, MaterialModel material) {
		String oreSampleName = material.getId() + "_" + strata.getSuffix() + "_ore_sample";
		RegistryObject<Block> oreSampleBlock;
		if (strata.getSampleStrata() && material.getProperties().hasParticles()) {
			oreSampleBlock = BLOCKS.register(oreSampleName, () -> new SampleOreBlockWithParticles(strata, material));
		} else {
			oreSampleBlock = BLOCKS.register(oreSampleName, () -> new SampleOreBlock(strata, material));
		}
		oreSampleBlockTable.put(strata.getId(), material.getId(), oreSampleBlock);
		oreSampleBlockItemTable.put(strata.getId(), material.getId(), ITEMS.register(oreSampleName, () -> new BlockItem(oreSampleBlock.get(), new Item.Properties().tab(EmendatusEnigmatica.TAB))));
	}

	public static void registerStorageBlocks(MaterialModel material) {
		if (material.getProperties().hasOxidization()) {
			Supplier<BiMap<Block, Block>> nextByBlock = Suppliers.memoize(
			() -> ImmutableBiMap.<Block, Block>builder()
					.put(storageBlockMap.get(material.getId()).get(), exposedBlockMap.get(material.getId()).get())
					.put(exposedBlockMap.get(material.getId()).get(), weatheredBlockMap.get(material.getId()).get())
					.put(weatheredBlockMap.get(material.getId()).get(), oxidizedBlockMap.get(material.getId()).get())
					.build()
			);
			String storageBlockName = material.getId() + "_block";
			String exposedBlockName = "exposed_" + material.getId();
			String weatheredBlockName = "weathered_" + material.getId();
			String oxidizedBlockName = "oxidized_" + material.getId();
			RegistryObject<Block> storageBlock = BLOCKS.register(storageBlockName, () -> new BasicWeatheringBlock(material, BasicWeatheringBlock.WeatherState.UNAFFECTED, nextByBlock));
			RegistryObject<Block> exposedBlock = BLOCKS.register(exposedBlockName, () -> new BasicWeatheringBlock(material, BasicWeatheringBlock.WeatherState.EXPOSED, nextByBlock));
			RegistryObject<Block> weatheredBlock = BLOCKS.register(weatheredBlockName, () -> new BasicWeatheringBlock(material, BasicWeatheringBlock.WeatherState.WEATHERED, nextByBlock));
			RegistryObject<Block> oxidizedBlock = BLOCKS.register(oxidizedBlockName, () -> new BasicWeatheringBlock(material, BasicWeatheringBlock.WeatherState.OXIDIZED, nextByBlock));

			storageBlockMap.put(material.getId(), storageBlock);
			exposedBlockMap.put(material.getId(), exposedBlock);
			weatheredBlockMap.put(material.getId(), weatheredBlock);
			oxidizedBlockMap.put(material.getId(), oxidizedBlock);
			if (material.getProperties().isBurnable()) {
				storageBlockItemMap.put(material.getId(), ITEMS.register(storageBlockName, () -> new BasicStorageBlockItem(storageBlock.get(),material.getProperties().getBurnTime() * 10)));
				storageBlockItemMap.put(material.getId(), ITEMS.register(exposedBlockName, () -> new BasicStorageBlockItem(exposedBlock.get(),material.getProperties().getBurnTime() * 10)));
				storageBlockItemMap.put(material.getId(), ITEMS.register(weatheredBlockName, () -> new BasicStorageBlockItem(weatheredBlock.get(),material.getProperties().getBurnTime() * 10)));
				storageBlockItemMap.put(material.getId(), ITEMS.register(oxidizedBlockName, () -> new BasicStorageBlockItem(oxidizedBlock.get(),material.getProperties().getBurnTime() * 10)));
			} else {
				storageBlockItemMap.put(material.getId(), ITEMS.register(storageBlockName, () -> new BasicStorageBlockItem(storageBlock.get(), 0)));
				storageBlockItemMap.put(material.getId(), ITEMS.register(exposedBlockName, () -> new BasicStorageBlockItem(exposedBlock.get(), 0)));
				storageBlockItemMap.put(material.getId(), ITEMS.register(weatheredBlockName, () -> new BasicStorageBlockItem(weatheredBlock.get(),0)));
				storageBlockItemMap.put(material.getId(), ITEMS.register(oxidizedBlockName, () -> new BasicStorageBlockItem(oxidizedBlock.get(),0)));
			}
		} else {
			String storageBlockName = material.getId() + "_block";
			RegistryObject<Block> storageBlock = BLOCKS.register(storageBlockName, () -> new BasicStorageBlock(material));
			storageBlockMap.put(material.getId(), storageBlock);
			if (material.getProperties().isBurnable()) {
				storageBlockItemMap.put(material.getId(), ITEMS.register(storageBlockName, () -> new BasicStorageBlockItem(storageBlock.get(),material.getProperties().getBurnTime() * 10)));
			} else {
				storageBlockItemMap.put(material.getId(), ITEMS.register(storageBlockName, () -> new BasicStorageBlockItem(storageBlock.get(), 0)));
			}
		}
	}

	public static void registerRawBlocks(MaterialModel material) {
		String rawBlockName = "raw_" + material.getId() + "_block";
		RegistryObject<Block> rawBlock = BLOCKS.register(rawBlockName, () -> new BasicStorageBlock(material));
		rawBlockMap.put(material.getId(), rawBlock);
		if (material.getProperties().isBurnable()) {
			rawBlockItemMap.put(material.getId(), ITEMS.register(rawBlockName, () -> new BasicStorageBlockItem(rawBlock.get(),material.getProperties().getBurnTime() * 10)));
		} else {
			rawBlockItemMap.put(material.getId(), ITEMS.register(rawBlockName, () -> new BasicStorageBlockItem(rawBlock.get(), 0)));
		}
	}

	// TODO: Fix burnable values
	public static void registerBuddingBlocks(MaterialModel material) {
		String buddingBlockName = "budding_" + material.getId();
		RegistryObject<Block> buddingBlock = BLOCKS.register(buddingBlockName, () -> new BasicBuddingBlock(material, smallBudBlockMap.get(material.getId()), mediumBudBlockMap.get(material.getId()), largeBudBlockMap.get(material.getId()), clusterBlockMap.get(material.getId())));
		buddingBlockMap.put(material.getId(), buddingBlock);
		if (material.getProperties().isBurnable()) {
			buddingBlockItemMap.put(material.getId(), ITEMS.register(buddingBlockName, () -> new BasicStorageBlockItem(buddingBlock.get(),material.getProperties().getBurnTime())));
		} else {
			buddingBlockItemMap.put(material.getId(), ITEMS.register(buddingBlockName, () -> new BasicStorageBlockItem(buddingBlock.get(), 0)));
		}
	}

	public static void registerSmallBudBlocks(MaterialModel material) {
		String smallBudBlockName = "small_" + material.getId() + "_bud";
		RegistryObject<Block> smallBudBlock = BLOCKS.register(smallBudBlockName, () -> new BasicClusterBlock(3, 4, 1, material));
		smallBudBlockMap.put(material.getId(), smallBudBlock);
		if (material.getProperties().isBurnable()) {
			smallBudBlockItemMap.put(material.getId(), ITEMS.register(smallBudBlockName, () -> new BasicStorageBlockItem(smallBudBlock.get(),material.getProperties().getBurnTime())));
		} else {
			smallBudBlockItemMap.put(material.getId(), ITEMS.register(smallBudBlockName, () -> new BasicStorageBlockItem(smallBudBlock.get(), 0)));
		}
	}

	public static void registerMediumBudBlocks(MaterialModel material) {
		String mediumBudBlockName = "medium_" + material.getId() + "_bud";
		RegistryObject<Block> mediumBudBlock = BLOCKS.register(mediumBudBlockName, () -> new BasicClusterBlock(4, 3, 2, material));
		mediumBudBlockMap.put(material.getId(), mediumBudBlock);
		if (material.getProperties().isBurnable()) {
			mediumBudBlockItemMap.put(material.getId(), ITEMS.register(mediumBudBlockName, () -> new BasicStorageBlockItem(mediumBudBlock.get(),material.getProperties().getBurnTime())));
		} else {
			mediumBudBlockItemMap.put(material.getId(), ITEMS.register(mediumBudBlockName, () -> new BasicStorageBlockItem(mediumBudBlock.get(), 0)));
		}
	}

	public static void registerLargeBudBlocks(MaterialModel material) {
		String largeBudBlockName = "large_" + material.getId() + "_bud";
		RegistryObject<Block> largeBudBlock = BLOCKS.register(largeBudBlockName, () -> new BasicClusterBlock(5, 3, 4, material));
		largeBudBlockMap.put(material.getId(), largeBudBlock);
		if (material.getProperties().isBurnable()) {
			largeBudBlockItemMap.put(material.getId(), ITEMS.register(largeBudBlockName, () -> new BasicStorageBlockItem(largeBudBlock.get(),material.getProperties().getBurnTime())));
		} else {
			largeBudBlockItemMap.put(material.getId(), ITEMS.register(largeBudBlockName, () -> new BasicStorageBlockItem(largeBudBlock.get(), 0)));
		}
	}

	public static void registerClusterBlocks(MaterialModel material) {
		String clusterBlockName = material.getId() + "_cluster";
		RegistryObject<Block> clusterBlock = BLOCKS.register(clusterBlockName, () -> new BasicClusterBlock(7, 3, 5, material));
		clusterBlockMap.put(material.getId(), clusterBlock);
		if (material.getProperties().isBurnable()) {
			clusterBlockItemMap.put(material.getId(), ITEMS.register(clusterBlockName, () -> new BasicStorageBlockItem(clusterBlock.get(),material.getProperties().getBurnTime())));
		} else {
			clusterBlockItemMap.put(material.getId(), ITEMS.register(clusterBlockName, () -> new BasicStorageBlockItem(clusterBlock.get(), 0)));
		}
	}

	public static void registerClusterShardBlocks(MaterialModel material) {
		String clusterShardBlockName = material.getId() + "_cluster_shard_block";
		RegistryObject<Block> clusterShardBlock = BLOCKS.register(clusterShardBlockName, () -> new BasicClusterShardBlock(material));
		clusterShardBlockMap.put(material.getId(), clusterShardBlock);
		if (material.getProperties().isBurnable()) {
			clusterShardBlockItemMap.put(material.getId(), ITEMS.register(clusterShardBlockName, () -> new BasicStorageBlockItem(clusterShardBlock.get(),material.getProperties().getBurnTime())));
		} else {
			clusterShardBlockItemMap.put(material.getId(), ITEMS.register(clusterShardBlockName, () -> new BasicStorageBlockItem(clusterShardBlock.get(), 0)));
		}
	}

	public static void registerClusterShards(MaterialModel material) {
		String itemName = material.getId() + "_cluster_shard";
		if (material.getProperties().isBurnable()) {
			clusterShardMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material, material.getProperties().getBurnTime())));
		} else {
			clusterShardMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material)));
		}
	}

	public static void registerRaws(MaterialModel material) {
		String itemName = "raw_" + material.getId();
		if (material.getProperties().isBurnable()) {
			rawMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material, material.getProperties().getBurnTime())));
		} else {
			rawMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material)));
		}
	}

	public static void registerIngots(MaterialModel material) {
		String itemName = material.getId() + "_ingot";
		if (material.getProperties().isBurnable()) {
			ingotMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material, material.getProperties().getBurnTime())));
		} else {
			ingotMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material)));
		}
	}

	public static void registerNuggets(MaterialModel material) {
		String itemName = material.getId() + "_nugget";
		if (material.getProperties().isBurnable()) {
			nuggetMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material, material.getProperties().getBurnTime() / 10)));
		} else {
			nuggetMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material)));
		}
	}

	public static void registerGems(MaterialModel material) {
		String itemName = material.getId() + "_gem";
		if (material.getProperties().isBurnable()) {
			gemMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material, material.getProperties().getBurnTime())));
		} else {
			gemMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material)));
		}
	}

	public static void registerDusts(MaterialModel material) {
		String itemName = material.getId() + "_dust";
		if (material.getProperties().isBurnable()) {
			dustMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material, material.getProperties().getBurnTime())));
		} else {
			dustMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material)));
		}
	}

	public static void registerPlates(MaterialModel material) {
		String itemName = material.getId() + "_plate";
		if (material.getProperties().isBurnable()) {
			plateMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material, material.getProperties().getBurnTime())));
		} else {
			plateMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material)));
		}
	}

	public static void registerGears(MaterialModel material) {
		String itemName = material.getId() + "_gear";
		if (material.getProperties().isBurnable()) {
			gearMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material, material.getProperties().getBurnTime() * 4)));
		} else {
			gearMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material)));
		}
	}

	public static void registerRods(MaterialModel material) {
		String itemName = material.getId() + "_rod";
		if (material.getProperties().isBurnable()) {
			rodMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicBurnableItem(material, material.getProperties().getBurnTime() * 2)));
		} else {
			rodMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicItem(material)));
		}
	}

	// Swords
	public static void registerSwords(MaterialModel material) {
		String itemName = material.getId() + "_sword";
		TagKey<Item> repairItem;
		if (material.getProperties().getMaterialType().equals("metal")) {
			repairItem = EETags.MATERIAL_INGOT.apply(material.getId());
		} else {
			repairItem = EETags.MATERIAL_GEM.apply(material.getId());
		}
		swordMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicSwordItem(material, repairItem)));
	}

	// Pickaxes
	public static void registerPickaxes(MaterialModel material) {
		String itemName = material.getId() + "_pickaxe";
		TagKey<Item> repairItem;
		if (material.getProperties().getMaterialType().equals("metal")) {
			repairItem = EETags.MATERIAL_INGOT.apply(material.getId());
		} else {
			repairItem = EETags.MATERIAL_GEM.apply(material.getId());
		}
		pickaxeMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicPickaxeItem(material, repairItem)));
	}

	// Axes
	public static void registerAxes(MaterialModel material) {
		String itemName = material.getId() + "_axe";
		TagKey<Item> repairItem;
		if (material.getProperties().getMaterialType().equals("metal")) {
			repairItem = EETags.MATERIAL_INGOT.apply(material.getId());
		} else {
			repairItem = EETags.MATERIAL_GEM.apply(material.getId());
		}
		axeMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicAxeItem(material, repairItem)));
	}

	// Shovels
	public static void registerShovels(MaterialModel material) {
		String itemName = material.getId() + "_shovel";
		TagKey<Item> repairItem;
		if (material.getProperties().getMaterialType().equals("metal")) {
			repairItem = EETags.MATERIAL_INGOT.apply(material.getId());
		} else {
			repairItem = EETags.MATERIAL_GEM.apply(material.getId());
		}
		shovelMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicShovelItem(material, repairItem)));
	}

	// Hoes
	public static void registerHoes(MaterialModel material) {
		String itemName = material.getId() + "_hoe";
		TagKey<Item> repairItem;
		if (material.getProperties().getMaterialType().equals("metal")) {
			repairItem = EETags.MATERIAL_INGOT.apply(material.getId());
		} else {
			repairItem = EETags.MATERIAL_GEM.apply(material.getId());
		}
		hoeMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicHoeItem(material, repairItem)));
	}

	// Paxels
	public static void registerPaxels(MaterialModel material) {
		String itemName = material.getId() + "_paxel";
		TagKey<Item> repairItem;
		if (material.getProperties().getMaterialType().equals("metal")) {
			repairItem = EETags.MATERIAL_INGOT.apply(material.getId());
		} else {
			repairItem = EETags.MATERIAL_GEM.apply(material.getId());
		}
		paxelMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicPaxelItem(material, repairItem)));
	}

	// Helmet
	public static void registerHelmets(MaterialModel material) {
		String itemName = material.getId() + "_helmet";
		TagKey<Item> repairItem;
		if (material.getProperties().getMaterialType().equals("metal")) {
			repairItem = EETags.MATERIAL_INGOT.apply(material.getId());
		} else {
			repairItem = EETags.MATERIAL_GEM.apply(material.getId());
		}
		helmetMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicArmorItem(material, repairItem, EquipmentSlot.HEAD, material.getArmor().getHelmet())));
	}

	// Chestplate
	public static void registerChestplates(MaterialModel material) {
		String itemName = material.getId() + "_chestplate";
		TagKey<Item> repairItem;
		if (material.getProperties().getMaterialType().equals("metal")) {
			repairItem = EETags.MATERIAL_INGOT.apply(material.getId());
		} else {
			repairItem = EETags.MATERIAL_GEM.apply(material.getId());
		}
		chestplateMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicArmorItem(material, repairItem, EquipmentSlot.CHEST, material.getArmor().getChestplate())));
	}

	// Leggings
	public static void registerLeggings(MaterialModel material) {
		String itemName = material.getId() + "_leggings";
		TagKey<Item> repairItem;
		if (material.getProperties().getMaterialType().equals("metal")) {
			repairItem = EETags.MATERIAL_INGOT.apply(material.getId());
		} else {
			repairItem = EETags.MATERIAL_GEM.apply(material.getId());
		}
		leggingsMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicArmorItem(material, repairItem, EquipmentSlot.LEGS, material.getArmor().getLeggings())));
	}

	// Boots
	public static void registerBoots(MaterialModel material) {
		String itemName = material.getId() + "_boots";
		TagKey<Item> repairItem;
		if (material.getProperties().getMaterialType().equals("metal")) {
			repairItem = EETags.MATERIAL_INGOT.apply(material.getId());
		} else {
			repairItem = EETags.MATERIAL_GEM.apply(material.getId());
		}
		bootsMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicArmorItem(material, repairItem, EquipmentSlot.FEET, material.getArmor().getBoots())));
	}

	// Shield
	public static void registerShields(MaterialModel material) {
		String itemName = material.getId() + "_shield";
		TagKey<Item> repairItem;
		if (material.getProperties().getMaterialType().equals("metal")) {
			repairItem = EETags.MATERIAL_INGOT.apply(material.getId());
		} else {
			repairItem = EETags.MATERIAL_GEM.apply(material.getId());
		}
		shieldMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicShieldItem(material, repairItem)));
	}

	// Fluids
	public static void registerFluids(MaterialModel material) {
		fluidType = FLUID_TYPES.register(material.getId(),
				() -> new BasicFluidType(FLUID_STILL_RL, FLUID_FLOWING_RL, FLUID_OVERLAY_RL,
						material.getColors().getFluidColor(),
						new Vector3f(Vec3.fromRGB24(material.getColors().getFluidColor())),
						fluidTypeProperties(material)));
		fluidSource = FLUIDS.register(material.getId(),
				() -> new ForgeFlowingFluid.Source(makeProperties(fluidTypeMap.get(material.getId()),
						fluidSourceMap.get(material.getId()),
						fluidFlowingMap.get(material.getId()),
						fluidBlockMap.get(material.getId()),
						fluidBucketMap.get(material.getId()))));
		fluidFlowing = FLUIDS.register("flowing_" + material.getId(),
				() -> new ForgeFlowingFluid.Flowing(makeProperties(fluidTypeMap.get(material.getId()),
						fluidSourceMap.get(material.getId()),
						fluidFlowingMap.get(material.getId()),
						fluidBlockMap.get(material.getId()),
						fluidBucketMap.get(material.getId()))));
		fluidBlock = BLOCKS.register(material.getId(),
				() -> new LiquidBlock(fluidSourceMap.get(material.getId()), BlockBehaviour.Properties.of(Material.LAVA).noCollission().strength(100.0F).noLootTable()));
		fluidBucket = ITEMS.register(material.getId() + "_bucket",
				() -> new BucketItem(fluidSourceMap.get(material.getId()), new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET).tab(EmendatusEnigmatica.TAB)));

		fluidTypeMap.put(material.getId(), fluidType);
		fluidSourceMap.put(material.getId(), fluidSource);
		fluidFlowingMap.put(material.getId(), fluidFlowing);
		fluidBlockMap.put(material.getId(), fluidBlock);
		fluidBucketMap.put(material.getId(), fluidBucket);
	}

	private static FluidType.Properties fluidTypeProperties(MaterialModel material) {
		return FluidType.Properties.create()
				.descriptionId("fluid.emendatusenigmatica." + material.getId())
				.lightLevel(15)
				.density(3000)
				.viscosity(6000)
				.temperature(1300)
				.rarity(Rarity.COMMON)
				.canDrown(false)
				.canSwim(false)
				.pathType(BlockPathTypes.LAVA)
				.adjacentPathType(null)
				.sound(SoundAction.get("bucket_fill"), SoundEvents.BUCKET_FILL_LAVA)
				.sound(SoundAction.get("bucket_empty"), SoundEvents.BUCKET_EMPTY_LAVA);
	}

	public static ForgeFlowingFluid.Properties makeProperties(Supplier<FluidType> type, Supplier<FlowingFluid> source, Supplier<FlowingFluid> flowing, Supplier<LiquidBlock> block, Supplier<Item> bucket) {
		return new ForgeFlowingFluid.Properties(type, source, flowing)
				.slopeFindDistance(2)
				.levelDecreasePerBlock(2)
				.block(block)
				.bucket(bucket);
	}

	// Hammer
	public static final RegistryObject<Item> ENIGMATIC_HAMMER = ITEMS.register("enigmatic_hammer", ItemHammer::new);

	// Felinium Jaminite
	public static final RegistryObject<Item> FELINIUM_JAMINITE = ITEMS.register("felinium_jaminite_ingot", FeliniumJaminiteIngot::new);

	public static void finalize(IEventBus eventBus) {
		ITEMS.register(eventBus);
		BLOCKS.register(eventBus);
		FLUID_TYPES.register(eventBus);
		FLUIDS.register(eventBus);
	}
}