/*
 * MIT License
 *
 * Copyright (c) 2020-2024. Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.registries;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.Table;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.blocks.*;
import com.ridanisaurus.emendatusenigmatica.blocks.templates.*;
import com.ridanisaurus.emendatusenigmatica.fluids.BasicFluidType;
import com.ridanisaurus.emendatusenigmatica.items.*;
import com.ridanisaurus.emendatusenigmatica.items.templates.*;
import com.ridanisaurus.emendatusenigmatica.registries.data.EEBlockMap;
import com.ridanisaurus.emendatusenigmatica.registries.data.EEDeferredHolderMap;
import com.ridanisaurus.emendatusenigmatica.registries.data.EEItemMap;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.SoundAction;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.*;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class EERegistrar
{
    // Registries
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Reference.MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Reference.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, Reference.MOD_ID);
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, Reference.MOD_ID);
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, Reference.MOD_ID);
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, Reference.MOD_ID);

    // Existing items.
    public static final DeferredItem<Item> ENIGMATIC_HAMMER = ITEMS.register("enigmatic_hammer", HammerItem::new);
    public static final DeferredItem<Item> FELINIUM_JAMINITE = ITEMS.register("felinium_jaminite_ingot", FeliniumJaminiteIngot::new);
    public static final DeferredItem<Item> SHIELD_TEMPLATE = ITEMS.register("shield_upgrade_template", ShieldTemplate::new);

    // Features
    public static final DeferredHolder<Feature<?>, VanillaOreFeature> VANILLA_ORE_FEATURE = FEATURES.register("vanilla_feature", VanillaOreFeature::new);
    public static final DeferredHolder<Feature<?>, SphereOreFeature> SPHERE_ORE_FEATURE = FEATURES.register("sphere_feature", SphereOreFeature::new);
    public static final DeferredHolder<Feature<?>, DenseOreFeature> DENSE_ORE_FEATURE = FEATURES.register("dense_feature", DenseOreFeature::new);
    public static final DeferredHolder<Feature<?>, GeodeOreFeature> GEODE_ORE_FEATURE = FEATURES.register("geode_feature", GeodeOreFeature::new);
    public static final DeferredHolder<Feature<?>, DikeOreFeature> DIKE_ORE_FEATURE = FEATURES.register("dike_feature", DikeOreFeature::new);

    // Ore Blocks
    // QoL changes for tables are not possible without AT or custom table impl - Thanks google!
    public static Table<String, String, DeferredBlock<Block>> oreBlockTable = HashBasedTable.create();
    public static Table<String, String, DeferredItem<Item>> oreBlockItemTable = HashBasedTable.create();

    // Ore Sample Blocks
    public static Table<String, String, DeferredBlock<Block>> oreSampleBlockTable = HashBasedTable.create();
    public static Table<String, String, DeferredItem<Item>> oreSampleBlockItemTable = HashBasedTable.create();

    // Storage Blocks
    public static EEBlockMap<Block> storageBlockMap = new EEBlockMap<>();
    public static EEItemMap<Item> storageBlockItemMap = new EEItemMap<>();

    // Weathering Blocks
    public static EEBlockMap<Block> exposedBlockMap = new EEBlockMap<>();
    public static EEBlockMap<Block> weatheredBlockMap = new EEBlockMap<>();
    public static EEBlockMap<Block> oxidizedBlockMap = new EEBlockMap<>();
    public static EEBlockMap<Block> waxedStorageBlockMap = new EEBlockMap<>();
    public static EEBlockMap<Block> waxedExposedBlockMap = new EEBlockMap<>();
    public static EEBlockMap<Block> waxedWeatheredBlockMap = new EEBlockMap<>();
    public static EEBlockMap<Block> waxedOxidizedBlockMap = new EEBlockMap<>();
    public static EEItemMap<Item> exposedBlockItemMap = new EEItemMap<>();
    public static EEItemMap<Item> weatheredBlockItemMap = new EEItemMap<>();
    public static EEItemMap<Item> oxidizedBlockItemMap = new EEItemMap<>();
    public static EEItemMap<Item> waxedStorageBlockItemMap = new EEItemMap<>();
    public static EEItemMap<Item> waxedExposedBlockItemMap = new EEItemMap<>();
    public static EEItemMap<Item> waxedWeatheredBlockItemMap = new EEItemMap<>();
    public static EEItemMap<Item> waxedOxidizedBlockItemMap = new EEItemMap<>();

    // Raw Blocks
    public static EEBlockMap<Block> rawBlockMap = new EEBlockMap<>();
    public static EEItemMap<Item> rawBlockItemMap = new EEItemMap<>();

    // Cluster Blocks
    public static EEBlockMap<Block> buddingBlockMap = new EEBlockMap<>();
    public static EEBlockMap<Block> smallBudBlockMap = new EEBlockMap<>();
    public static EEBlockMap<Block> mediumBudBlockMap = new EEBlockMap<>();
    public static EEBlockMap<Block> largeBudBlockMap = new EEBlockMap<>();
    public static EEBlockMap<Block> clusterBlockMap = new EEBlockMap<>();
    public static EEBlockMap<Block> clusterShardBlockMap = new EEBlockMap<>();
    public static EEItemMap<Item> buddingBlockItemMap = new EEItemMap<>();
    public static EEItemMap<Item> smallBudBlockItemMap = new EEItemMap<>();
    public static EEItemMap<Item> mediumBudBlockItemMap = new EEItemMap<>();
    public static EEItemMap<Item> largeBudBlockItemMap = new EEItemMap<>();
    public static EEItemMap<Item> clusterBlockItemMap = new EEItemMap<>();
    public static EEItemMap<Item> clusterShardBlockItemMap = new EEItemMap<>();

    // Items
    public static EEItemMap<Item> rawMap = new EEItemMap<>();
    public static EEItemMap<Item> ingotMap = new EEItemMap<>();
    public static EEItemMap<Item> nuggetMap = new EEItemMap<>();
    public static EEItemMap<Item> gemMap = new EEItemMap<>();
    public static EEItemMap<Item> dustMap = new EEItemMap<>();
    public static EEItemMap<Item> plateMap = new EEItemMap<>();
    public static EEItemMap<Item> gearMap = new EEItemMap<>();
    public static EEItemMap<Item> rodMap = new EEItemMap<>();
    public static EEItemMap<Item> clusterShardMap = new EEItemMap<>();

    // Tools
    public static EEItemMap<SwordItem> swordMap = new EEItemMap<>();
    public static EEItemMap<PickaxeItem> pickaxeMap = new EEItemMap<>();
    public static EEItemMap<AxeItem> axeMap = new EEItemMap<>();
    public static EEItemMap<ShovelItem> shovelMap = new EEItemMap<>();
    public static EEItemMap<HoeItem> hoeMap = new EEItemMap<>();
    public static EEItemMap<PaxelItem> paxelMap = new EEItemMap<>();

    // Armor
    public static EEItemMap<ArmorItem> helmetMap = new EEItemMap<>();
    public static EEItemMap<ArmorItem> chestplateMap = new EEItemMap<>();
    public static EEItemMap<ArmorItem> leggingsMap = new EEItemMap<>();
    public static EEItemMap<ArmorItem> bootsMap = new EEItemMap<>();
    public static EEItemMap<ShieldItem> shieldMap = new EEItemMap<>();
    // Armor Materials
    public static Map<String, DeferredHolder<ArmorMaterial, ArmorMaterial>> armorMaterialsMap = new HashMap<>();

    // Fluids
    public static EEDeferredHolderMap<Fluid, BaseFlowingFluid.Source> fluidSourceMap = new EEDeferredHolderMap<>();
    public static EEDeferredHolderMap<Fluid, BaseFlowingFluid.Flowing> fluidFlowingMap = new EEDeferredHolderMap<>();
    public static EEDeferredHolderMap<Block, LiquidBlock> fluidBlockMap = new EEDeferredHolderMap<>();
    public static EEDeferredHolderMap<FluidType, BasicFluidType> fluidTypeMap = new EEDeferredHolderMap<>();
    public static EEItemMap<BucketItem> fluidBucketMap = new EEItemMap<>();

    public static final ResourceLocation FLUID_STILL_RL = ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "block/fluid_still");
    public static final ResourceLocation FLUID_FLOWING_RL = ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "block/fluid_flow");
    public static final ResourceLocation FLUID_OVERLAY_RL = ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "block/fluid_overlay");

    // Finalization
    public static void finalize(IEventBus eventBus) {
        ITEMS.register(eventBus);
        BLOCKS.register(eventBus);
        FLUID_TYPES.register(eventBus);
        FLUIDS.register(eventBus);
        ARMOR_MATERIALS.register(eventBus);
        FEATURES.register(eventBus);
    }

    public static void registerToCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == EmendatusEnigmatica.TOOLS_TAB.get()) {
            swordMap        .values().forEach(event::accept);
            pickaxeMap      .values().forEach(event::accept);
            axeMap          .values().forEach(event::accept);
            shovelMap       .values().forEach(event::accept);
            hoeMap          .values().forEach(event::accept);
            paxelMap        .values().forEach(event::accept);
            helmetMap       .values().forEach(event::accept);
            chestplateMap   .values().forEach(event::accept);
            leggingsMap     .values().forEach(event::accept);
            bootsMap        .values().forEach(event::accept);
            shieldMap       .values().forEach(event::accept);
        }
        if (event.getTab() == EmendatusEnigmatica.RESOURCES_TAB.get()) {
            oreBlockItemTable           .values().forEach(event::accept);
            oreSampleBlockItemTable     .values().forEach(event::accept);
            storageBlockItemMap         .values().forEach(event::accept);
            exposedBlockItemMap         .values().forEach(event::accept);
            weatheredBlockItemMap       .values().forEach(event::accept);
            oxidizedBlockItemMap        .values().forEach(event::accept);
            waxedStorageBlockItemMap    .values().forEach(event::accept);
            waxedExposedBlockItemMap    .values().forEach(event::accept);
            waxedWeatheredBlockItemMap  .values().forEach(event::accept);
            waxedOxidizedBlockItemMap   .values().forEach(event::accept);
            rawBlockItemMap             .values().forEach(event::accept);
            buddingBlockItemMap         .values().forEach(event::accept);
            smallBudBlockItemMap        .values().forEach(event::accept);
            mediumBudBlockItemMap       .values().forEach(event::accept);
            largeBudBlockItemMap        .values().forEach(event::accept);
            clusterBlockItemMap         .values().forEach(event::accept);
            clusterShardBlockItemMap    .values().forEach(event::accept);
            rawMap                      .values().forEach(event::accept);
            ingotMap                    .values().forEach(event::accept);
            nuggetMap                   .values().forEach(event::accept);
            gemMap                      .values().forEach(event::accept);
            dustMap                     .values().forEach(event::accept);
            plateMap                    .values().forEach(event::accept);
            gearMap                     .values().forEach(event::accept);
            rodMap                      .values().forEach(event::accept);
            clusterShardMap             .values().forEach(event::accept);
            fluidBucketMap              .values().forEach(event::accept);
        }
    }

    // Registration Methods
    public static void registerOre(StrataModel strata, MaterialModel material) {
        String oreName = material.getId() + (!strata.getId().equals("minecraft_stone") ? "_" + strata.getSuffix() : "") + "_ore";
        DeferredBlock<Block> oreBlock;
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
        oreBlockItemTable.put(strata.getId(), material.getId(), ITEMS.register(oreName, () -> new BlockItem(oreBlock.get(), new Item.Properties())));
    }

    @Deprecated
    public static void registerSample(StrataModel strata, MaterialModel material) {
        //TODO: Rework Sample System
        String oreSampleName = material.getId() + "_" + strata.getSuffix() + "_ore_sample";
        DeferredBlock<Block> oreSampleBlock;
        if (material.getProperties().getMaterialType().equals("sample") && material.getProperties().hasParticles()) {
            oreSampleBlock = BLOCKS.register(oreSampleName, () -> new SampleOreBlockWithParticles(strata, material));
        } else {
            oreSampleBlock = BLOCKS.register(oreSampleName, () -> new SampleOreBlock(strata, material));
        }
        oreSampleBlockTable.put(strata.getId(), material.getId(), oreSampleBlock);
        oreSampleBlockItemTable.put(strata.getId(), material.getId(), ITEMS.register(oreSampleName, () -> new BlockItem(oreSampleBlock.get(), new Item.Properties())));
    }

    public static void registerStorageBlocks(MaterialModel material) {
        if (!material.getProperties().hasOxidization()) {
            String storageBlockName = material.getId() + "_block";
            DeferredBlock<Block> storageBlock = BLOCKS.register(storageBlockName, () -> new BasicStorageBlock(material));
            storageBlockMap.put(material.getId(), storageBlock);
            storageBlockItemMap.put(material.getId(), ITEMS.register(storageBlockName, () -> new BasicStorageBlockItem(storageBlock.get(), getBurnTime(material, it -> it * 10))));
            return;
        }

        Supplier<BiMap<Block, Block>> oxidizationBlockMap = Suppliers.memoize(
            () -> ImmutableBiMap.<Block, Block>builder()
                .put(storageBlockMap.getValue(material), exposedBlockMap.getValue(material))
                .put(exposedBlockMap.getValue(material), weatheredBlockMap.getValue(material))
                .put(weatheredBlockMap.getValue(material), oxidizedBlockMap.getValue(material))
                .build()
        );
        Supplier<BiMap<Block, Block>> waxableBlockMap = Suppliers.memoize(
            () -> ImmutableBiMap.<Block, Block>builder()
                .put(storageBlockMap.getValue(material), waxedStorageBlockMap.getValue(material))
                .put(exposedBlockMap.getValue(material), waxedExposedBlockMap.getValue(material))
                .put(weatheredBlockMap.getValue(material), waxedWeatheredBlockMap.getValue(material))
                .put(oxidizedBlockMap.getValue(material), waxedOxidizedBlockMap.getValue(material))
                .build()
        );

        String storageBlockName = material.getId() + "_block";
        String exposedBlockName = "exposed_" + material.getId();
        String weatheredBlockName = "weathered_" + material.getId();
        String oxidizedBlockName = "oxidized_" + material.getId();
        String waxedStorageBlockName = "waxed_" + material.getId() + "_block";
        String waxedExposedBlockName = "waxed_exposed_" + material.getId();
        String waxedWeatheredBlockName = "waxed_weathered_" + material.getId();
        String waxedOxidizedBlockName = "waxed_oxidized_" + material.getId();
        DeferredBlock<Block> storageBlock = BLOCKS.register(storageBlockName, () -> new BasicWeatheringBlock(material, BasicWeatheringBlock.WeatherState.UNAFFECTED, oxidizationBlockMap, waxableBlockMap));
        DeferredBlock<Block> exposedBlock = BLOCKS.register(exposedBlockName, () -> new BasicWeatheringBlock(material, BasicWeatheringBlock.WeatherState.EXPOSED, oxidizationBlockMap, waxableBlockMap));
        DeferredBlock<Block> weatheredBlock = BLOCKS.register(weatheredBlockName, () -> new BasicWeatheringBlock(material, BasicWeatheringBlock.WeatherState.WEATHERED, oxidizationBlockMap, waxableBlockMap));
        DeferredBlock<Block> oxidizedBlock = BLOCKS.register(oxidizedBlockName, () -> new BasicWeatheringBlock(material, BasicWeatheringBlock.WeatherState.OXIDIZED, oxidizationBlockMap, waxableBlockMap));
        DeferredBlock<Block> waxedStorageBlock = BLOCKS.register(waxedStorageBlockName, () -> new BasicWaxedBlock(material, waxableBlockMap));
        DeferredBlock<Block> waxedExposedBlock = BLOCKS.register(waxedExposedBlockName, () -> new BasicWaxedBlock(material, waxableBlockMap));
        DeferredBlock<Block> waxedWeatheredBlock = BLOCKS.register(waxedWeatheredBlockName, () -> new BasicWaxedBlock(material, waxableBlockMap));
        DeferredBlock<Block> waxedOxidizedBlock = BLOCKS.register(waxedOxidizedBlockName, () -> new BasicWaxedBlock(material, waxableBlockMap));
        storageBlockMap.put(material.getId(), storageBlock);
        exposedBlockMap.put(material.getId(), exposedBlock);
        weatheredBlockMap.put(material.getId(), weatheredBlock);
        oxidizedBlockMap.put(material.getId(), oxidizedBlock);
        waxedStorageBlockMap.put(material.getId(), waxedStorageBlock);
        waxedExposedBlockMap.put(material.getId(), waxedExposedBlock);
        waxedWeatheredBlockMap.put(material.getId(), waxedWeatheredBlock);
        waxedOxidizedBlockMap.put(material.getId(), waxedOxidizedBlock);

        int burnTime = getBurnTime(material, it -> it * 10);
        storageBlockItemMap         .put(material.getId(), ITEMS.register(storageBlockName,         () -> new BasicStorageBlockItem(storageBlock.get(), burnTime)));
        exposedBlockItemMap         .put(material.getId(), ITEMS.register(exposedBlockName,         () -> new BasicStorageBlockItem(exposedBlock.get(), burnTime)));
        weatheredBlockItemMap       .put(material.getId(), ITEMS.register(weatheredBlockName,       () -> new BasicStorageBlockItem(weatheredBlock.get(), burnTime)));
        oxidizedBlockItemMap        .put(material.getId(), ITEMS.register(oxidizedBlockName,        () -> new BasicStorageBlockItem(oxidizedBlock.get(), burnTime)));
        waxedStorageBlockItemMap    .put(material.getId(), ITEMS.register(waxedStorageBlockName,    () -> new BasicStorageBlockItem(waxedStorageBlock.get(), burnTime)));
        waxedExposedBlockItemMap    .put(material.getId(), ITEMS.register(waxedExposedBlockName,    () -> new BasicStorageBlockItem(waxedExposedBlock.get(), burnTime)));
        waxedWeatheredBlockItemMap  .put(material.getId(), ITEMS.register(waxedWeatheredBlockName,  () -> new BasicStorageBlockItem(waxedWeatheredBlock.get(), burnTime)));
        waxedOxidizedBlockItemMap   .put(material.getId(), ITEMS.register(waxedOxidizedBlockName,   () -> new BasicStorageBlockItem(waxedOxidizedBlock.get(), burnTime)));
    }

    public static void registerRawBlocks(MaterialModel material) {
        String rawBlockName = "raw_" + material.getId() + "_block";
        DeferredBlock<Block> rawBlock = BLOCKS.register(rawBlockName, () -> new BasicStorageBlock(material));
        rawBlockMap.put(material.getId(), rawBlock);
        rawBlockItemMap.put(material.getId(), ITEMS.register(rawBlockName, () -> new BasicStorageBlockItem(rawBlock.get(),getBurnTime(material, it -> it * 10))));
    }

    public static void registerBuddingBlocks(MaterialModel material) {
        String buddingBlockName = "budding_" + material.getId();
        DeferredBlock<Block> buddingBlock = BLOCKS.register(buddingBlockName, () ->
            new BasicBuddingBlock(material,
                smallBudBlockMap.get(material),
                mediumBudBlockMap.get(material),
                largeBudBlockMap.get(material),
                clusterBlockMap.get(material)
        ));

        buddingBlockMap.put(material.getId(), buddingBlock);
        buddingBlockItemMap.put(material.getId(), ITEMS.register(buddingBlockName, () -> new BasicStorageBlockItem(buddingBlock.get(), getBurnTime(material, it -> it * 4))));
    }

    public static void registerSmallBudBlocks(MaterialModel material) {
        String smallBudBlockName = "small_" + material.getId() + "_bud";
        DeferredBlock<Block> smallBudBlock = BLOCKS.register(smallBudBlockName, () -> new BasicClusterBlock(3, 4, 1, material));
        smallBudBlockMap.put(material.getId(), smallBudBlock);
        smallBudBlockItemMap.put(material.getId(), ITEMS.register(smallBudBlockName, () -> new BasicStorageBlockItem(smallBudBlock.get(), getBurnTime(material))));
    }

    public static void registerMediumBudBlocks(MaterialModel material) {
        String mediumBudBlockName = "medium_" + material.getId() + "_bud";
        DeferredBlock<Block> mediumBudBlock = BLOCKS.register(mediumBudBlockName, () -> new BasicClusterBlock(4, 3, 2, material));
        mediumBudBlockMap.put(material.getId(), mediumBudBlock);
        mediumBudBlockItemMap.put(material.getId(), ITEMS.register(mediumBudBlockName, () -> new BasicStorageBlockItem(mediumBudBlock.get(), getBurnTime(material))));
    }

    public static void registerLargeBudBlocks(MaterialModel material) {
        String largeBudBlockName = "large_" + material.getId() + "_bud";
        DeferredBlock<Block> largeBudBlock = BLOCKS.register(largeBudBlockName, () -> new BasicClusterBlock(5, 3, 4, material));
        largeBudBlockMap.put(material.getId(), largeBudBlock);
        largeBudBlockItemMap.put(material.getId(), ITEMS.register(largeBudBlockName, () -> new BasicStorageBlockItem(largeBudBlock.get(), getBurnTime(material))));
    }

    public static void registerClusterBlocks(MaterialModel material) {
        String clusterBlockName = material.getId() + "_cluster";
        DeferredBlock<Block> clusterBlock = BLOCKS.register(clusterBlockName, () -> new BasicClusterBlock(7, 3, 5, material));
        clusterBlockMap.put(material.getId(), clusterBlock);
        clusterBlockItemMap.put(material.getId(), ITEMS.register(clusterBlockName, () -> new BasicStorageBlockItem(clusterBlock.get(), getBurnTime(material))));
    }

    public static void registerClusterShardBlocks(MaterialModel material) {
        String clusterShardBlockName = material.getId() + "_cluster_shard_block";
        DeferredBlock<Block> clusterShardBlock = BLOCKS.register(clusterShardBlockName, () -> new BasicClusterShardBlock(material));
        clusterShardBlockMap.put(material.getId(), clusterShardBlock);
        clusterShardBlockItemMap.put(material.getId(), ITEMS.register(clusterShardBlockName, () -> new BasicStorageBlockItem(clusterShardBlock.get(), getBurnTime(material, it -> it * 4))));
    }

    public static void registerClusterShards(MaterialModel material) {
        registerBurnableItem(clusterShardMap, material, material.getId() + "_cluster_shard");
    }

    public static void registerRaw(MaterialModel material) {
        registerBurnableItem(rawMap, material, "raw_" + material.getId());
    }

    public static void registerIngots(MaterialModel material) {
        registerBurnableItem(ingotMap, material, material.getId() + "_ingot");
    }

    public static void registerNuggets(MaterialModel material) {
        registerBurnableItem(nuggetMap, material, material.getId() + "_nugget", it -> it / 10);
    }

    public static void registerGems(MaterialModel material) {
        registerBurnableItem(gemMap, material, material.getId() + "_gem");
    }

    public static void registerDusts(MaterialModel material) {
        registerBurnableItem(dustMap, material, material.getId() + "_dust");
    }

    public static void registerPlates(MaterialModel material) {
        registerBurnableItem(plateMap, material, material.getId() + "_plate");
    }

    public static void registerGears(MaterialModel material) {
        registerBurnableItem(gearMap, material, material.getId() + "_gear", it -> it * 4);
    }

    public static void registerRods(MaterialModel material) {
        registerBurnableItem(rodMap, material, material.getId() + "_rod", it -> it * 2);
    }

    // Swords
    public static void registerSwords(MaterialModel material) {
        swordMap.put(material.getId(), ITEMS.register(material.getId() + "_sword", () -> new BasicSwordItem(material, getRepairItem(material))));
    }

    // Pickaxes
    public static void registerPickaxes(MaterialModel material) {
        pickaxeMap.put(material.getId(), ITEMS.register(material.getId() + "_pickaxe", () -> new BasicPickaxeItem(material, getRepairItem(material))));
    }

    // Axes
    public static void registerAxes(MaterialModel material) {
        axeMap.put(material.getId(), ITEMS.register(material.getId() + "_axe", () -> new BasicAxeItem(material, getRepairItem(material))));
    }

    // Shovels
    public static void registerShovels(MaterialModel material) {
        shovelMap.put(material.getId(), ITEMS.register(material.getId() + "_shovel", () -> new BasicShovelItem(material, getRepairItem(material))));
    }

    // Hoes
    public static void registerHoes(MaterialModel material) {
        hoeMap.put(material.getId(), ITEMS.register(material.getId() + "_hoe", () -> new BasicHoeItem(material, getRepairItem(material))));
    }

    // Paxels
    public static void registerPaxels(MaterialModel material) {
        paxelMap.put(material.getId(), ITEMS.register(material.getId() + "_paxel", () -> new BasicPaxelItem(material, getRepairItem(material))));
    }

    // Armor
    public static void registerArmor(MaterialModel material) {
        // Helmet
        String itemName = material.getId() + "_helmet";
        helmetMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicArmorItem(material, ArmorItem.Type.HELMET, material.getArmor().getHelmet())));

        // Chestplate
        itemName = material.getId() + "_chestplate";
        chestplateMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicArmorItem(material, ArmorItem.Type.CHESTPLATE, material.getArmor().getChestplate())));

        // Leggings
        itemName = material.getId() + "_leggings";
        leggingsMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicArmorItem(material, ArmorItem.Type.LEGGINGS, material.getArmor().getLeggings())));

        // Boots
        itemName = material.getId() + "_boots";
        bootsMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicArmorItem(material, ArmorItem.Type.BOOTS, material.getArmor().getBoots())));

        // Armor Material
        armorMaterialsMap.put(material.getId(), ARMOR_MATERIALS.register(material.getId() + "_armor_material", () -> new ArmorMaterial(
            Map.of(
                ArmorItem.Type.HELMET,      material.getArmor().getHelmet().getProtection(),
                ArmorItem.Type.CHESTPLATE,  material.getArmor().getChestplate().getProtection(),
                ArmorItem.Type.LEGGINGS,    material.getArmor().getLeggings().getProtection(),
                ArmorItem.Type.BOOTS,       material.getArmor().getBoots().getProtection()
            ),
            material.getArmor().getEnchantability(),
            SoundEvents.ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.of(getRepairItem(material)),
            //TODO: Make sure this can be empty list lol
            List.of(),
            material.getArmor().getToughness(),
            material.getArmor().getKnockback()
        )));
    }

    // Shield
    public static void registerShields(MaterialModel material) {
        String itemName = material.getId() + "_shield";
        TagKey<Item> repairItem = getRepairItem(material);
        shieldMap.put(material.getId(), ITEMS.register(itemName, () -> new BasicShieldItem(material, repairItem)));
    }

    // Fluids
    public static void registerFluids(MaterialModel material) {
        var fluidType = FLUID_TYPES.register(material.getId(), () -> new BasicFluidType(
            FLUID_STILL_RL,
            FLUID_FLOWING_RL,
            FLUID_OVERLAY_RL,
            material.getColors().getFluidColor(),
            new Vector3f(Vec3.fromRGB24(material.getColors().getFluidColor()).toVector3f()),
            fluidTypeProperties(material)
        ));

        var fluidSource = FLUIDS.register(material.getId(), () -> new BaseFlowingFluid.Source(makeProperties(
            fluidTypeMap.get(material),
            fluidSourceMap.get(material),
            fluidFlowingMap.get(material),
            fluidBlockMap.get(material),
            fluidBucketMap.get(material))
        ));

        var fluidFlowing = FLUIDS.register("flowing_" + material.getId(), () -> new BaseFlowingFluid.Flowing(makeProperties(
            fluidTypeMap.get(material),
            fluidSourceMap.get(material),
            fluidFlowingMap.get(material),
            fluidBlockMap.get(material),
            fluidBucketMap.get(material))

        ));

        var fluidBlock = BLOCKS.register(material.getId(), () -> new LiquidBlock(
            fluidSourceMap.getValue(material),
            BlockBehaviour.Properties.ofFullCopy(Blocks.LAVA).liquid().noCollission().strength(100.0F).noLootTable()
        ));

        var fluidBucket = ITEMS.register(material.getId() + "_bucket", () -> new BucketItem(
            fluidSourceMap.getValue(material),
            new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)
        ));

        fluidTypeMap.put(material.getId(), fluidType);
        fluidSourceMap.put(material.getId(), fluidSource);
        fluidFlowingMap.put(material.getId(), fluidFlowing);
        fluidBlockMap.put(material.getId(), fluidBlock);
        fluidBucketMap.put(material.getId(), fluidBucket);
    }

    // Utility
    private static TagKey<Item> getRepairItem(MaterialModel material) {
        // For people that have a gem processedType, yet use metal material type.
        // Gem and Ingot are exclusive, so only one of each can be present at the same time.
        // TODO: Think to limit processedType ingot/gem to the proper type.
        if (material.getProcessedTypes().contains("gem"))
            return EETags.MATERIAL_GEM.apply(material.getId());
        return EETags.MATERIAL_INGOT.apply(material.getId());
    }

    private static int getBurnTime(MaterialModel model, Function<Integer, Integer> modifier) {
        return model.getProperties().isBurnable()? modifier.apply(model.getProperties().getBurnTime()): 0;
    }

    private static int getBurnTime(MaterialModel model) {
        return getBurnTime(model, it -> it);
    }

    private static void registerBurnableItem(EEItemMap<Item> map, MaterialModel material, String name, Function<Integer, Integer> modifier) {
        if (material.getProperties().isBurnable()) {
            map.put(material.getId(), ITEMS.register(name, () -> new BasicBurnableItem(material, getBurnTime(material, modifier))));
        } else {
            map.put(material.getId(), ITEMS.register(name, () -> new BasicItem(material)));
        }
    }

    private static void registerBurnableItem(EEItemMap<Item> map, MaterialModel material, String name) {
        registerBurnableItem(map, material, name, it -> it);
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
            .canSwim(true)
            .pathType(PathType.LAVA)
            .adjacentPathType(null)
            .sound(SoundAction.get("bucket_fill"), SoundEvents.BUCKET_FILL_LAVA)
            .sound(SoundAction.get("bucket_empty"), SoundEvents.BUCKET_EMPTY_LAVA);
    }

    public static BaseFlowingFluid.Properties makeProperties(
        DeferredHolder<FluidType, ? extends FluidType> type,
        DeferredHolder<Fluid, ? extends FlowingFluid> source,
        DeferredHolder<Fluid, ? extends FlowingFluid> flowing,
        DeferredHolder<Block, ? extends LiquidBlock> block,
        DeferredItem<BucketItem> bucket
    ) {
        return new BaseFlowingFluid.Properties(type, source, flowing)
            .slopeFindDistance(2)
            .levelDecreasePerBlock(2)
            .block(block)
            .bucket(bucket);
    }
}
