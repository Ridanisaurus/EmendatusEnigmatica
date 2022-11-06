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

package com.ridanisaurus.emendatusenigmatica;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.ridanisaurus.emendatusenigmatica.blocks.BasicStorageBlockItem;
import com.ridanisaurus.emendatusenigmatica.blocks.BlockColorHandler;
import com.ridanisaurus.emendatusenigmatica.blocks.IColorable;
import com.ridanisaurus.emendatusenigmatica.datagen.*;
import com.ridanisaurus.emendatusenigmatica.items.BasicItem;
import com.ridanisaurus.emendatusenigmatica.items.BlockItemColorHandler;
import com.ridanisaurus.emendatusenigmatica.items.ItemColorHandler;
import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.EEDeposits;
import com.ridanisaurus.emendatusenigmatica.registries.EEBloodMagicRegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EECreateRegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EEMekanismRegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.rule.MultiStrataRuleTest;
import net.minecraft.client.Minecraft;
import net.minecraft.data.DataGenerator;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.model.DynamicFluidContainerModel;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Reference.MOD_ID)
public class EmendatusEnigmatica {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    private DataGenerator generator;
    private static boolean hasGenerated = false;

    private static EmendatusEnigmatica instance = null;
    public static boolean MEKANISM_LOADED = false;
    public static boolean CREATE_LOADED = false;
    public static boolean BLOODMAGIC_LOADED = false;
    public static boolean ARSNOUVEAU_LOADED = false;
    public static boolean OCCULTISM_LOADED = false;
    public static boolean THERMALSERIES_LOADED = false;

    public EmendatusEnigmatica() {
        instance = this;
        MEKANISM_LOADED = ModList.get().isLoaded(Reference.MEKANISM);
        CREATE_LOADED = ModList.get().isLoaded(Reference.CREATE);
        BLOODMAGIC_LOADED = ModList.get().isLoaded(Reference.BLOODMAGIC);
        ARSNOUVEAU_LOADED = ModList.get().isLoaded(Reference.ARSNOUVEAU);
        OCCULTISM_LOADED = ModList.get().isLoaded(Reference.OCCULTISM);
        THERMALSERIES_LOADED = ModList.get().isLoaded(Reference.THERMALSERIES);

        // Register Deferred Registers and populate their tables once the mod is done constructing
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        DataGeneratorFactory.init();

        EELoader.load();
        EERegistrar.finalize(modEventBus);

        EEDeposits.load();
        EEDeposits.setup();
        EEDeposits.finalize(modEventBus);

        if (MEKANISM_LOADED) EEMekanismRegistrar.finalize(modEventBus);
        if (CREATE_LOADED) EECreateRegistrar.finalize(modEventBus);
        if (BLOODMAGIC_LOADED) EEBloodMagicRegistrar.finalize(modEventBus);

        modEventBus.addListener(this::commonEvents);
        modEventBus.addListener(this::itemColorEvent);
        modEventBus.addListener(this::blockColorEvent);

        registerDataGen();

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> Minecraft.getInstance().getResourcePackRepository().addPackFinder(new EEPackFinder(PackType.CLIENT_RESOURCES)));
    }

    private void init(final FMLConstructModEvent event) {}

    private void commonEvents(final FMLCommonSetupEvent event) {
        MultiStrataRuleTest.register();
    }

    private void clientEvents(final FMLClientSetupEvent event) {}

    private void itemColorEvent(RegisterColorHandlersEvent.Item event) {
        event.getItemColors().register(new DynamicFluidContainerModel.Colors(), EERegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BucketItem).map(RegistryObject::get).toArray(Item[]::new));
        event.getItemColors().register(new ItemColorHandler(), EERegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BasicItem).map(RegistryObject::get).toArray(Item[]::new));
        event.getItemColors().register(new ItemColorHandler(), EEMekanismRegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BasicItem).map(RegistryObject::get).toArray(Item[]::new));
        event.getItemColors().register(new ItemColorHandler(), EECreateRegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BasicItem).map(RegistryObject::get).toArray(Item[]::new));
        event.getItemColors().register(new ItemColorHandler(), EEBloodMagicRegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BasicItem).map(RegistryObject::get).toArray(Item[]::new));
        event.getItemColors().register(new BlockItemColorHandler(), EERegistrar.ITEMS.getEntries().stream().filter(x -> x.get() instanceof BlockItem || x.get() instanceof BasicStorageBlockItem).map(RegistryObject::get).toArray(Item[]::new));
    }

    private void blockColorEvent(RegisterColorHandlersEvent.Block event) {
        event.getBlockColors().register(new BlockColorHandler(), EERegistrar.BLOCKS.getEntries().stream().filter(x -> x.get() instanceof IColorable).map(RegistryObject::get).toArray(Block[]::new));
    }

    public static final CreativeModeTab TAB = new CreativeModeTab("emendatusenigmatica") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(EERegistrar.ENIGMATIC_HAMMER.get());
        }
    };

    private void registerDataGen() {
        generator = DataGeneratorFactory.createEEDataGenerator();
        ExistingFileHelper existingFileHelper = new ExistingFileHelper(ImmutableList.of(), ImmutableSet.of(), false, null, null);

        generator.addProvider(true, new BlockStatesAndModelsGen(generator, existingFileHelper));
        generator.addProvider(true, new ItemModelsGen(generator, existingFileHelper));
        generator.addProvider(true, new LangGen(generator));
        if (CREATE_LOADED) generator.addProvider(true, new CreateDataGen.CreateItemModels(generator, existingFileHelper));
        if (BLOODMAGIC_LOADED) generator.addProvider(true, new BloodMagicDataGen.BloodMagicItemModels(generator, existingFileHelper));

        BlockTagsGen blockTagsGeneration = new BlockTagsGen(generator, existingFileHelper);
        generator.addProvider(true, new ItemTagsGen(generator, blockTagsGeneration, existingFileHelper));
        generator.addProvider(true, blockTagsGeneration);
        generator.addProvider(true, new FluidTagsGen(generator, existingFileHelper));
        generator.addProvider(true, new RecipesGen(generator));
        generator.addProvider(true, new LootTablesGen(generator));
        generator.addProvider(true, new OreFeatureDataGen(generator));

        if (CREATE_LOADED) {
            generator.addProvider(true, new CreateDataGen.CreateItemTags(generator, blockTagsGeneration, existingFileHelper));
            generator.addProvider(true, new CreateDataGen.CreateRecipes(generator));
        }
        if (BLOODMAGIC_LOADED) {
            generator.addProvider(true, new BloodMagicDataGen.BloodMagicItemTags(generator, blockTagsGeneration, existingFileHelper));
            generator.addProvider(true, new BloodMagicDataGen.BloodMagicRecipes(generator));
        }
        if (ARSNOUVEAU_LOADED) generator.addProvider(true, new ArsNouveauDataGen.ArsNouveauRecipes(generator));
        if (OCCULTISM_LOADED) generator.addProvider(true, new OccultismDataGen.OccultismRecipes(generator));
        if (THERMALSERIES_LOADED) generator.addProvider(true, new ThermalDataGen.ThermalRecipes(generator));

        if (MEKANISM_LOADED) {
            // TODO [RID] Re-add after integrating Mekanism
//            generator.addProvider(new MekanismDataGen.MekanismItemTags(generator, blockTagsGeneration, existingFileHelper));
            // TODO [RID] Fix Slurry Tags
//            generator.addProvider(new MekanismDataGen.MekanismSlurryTags(generator, existingFileHelper));
//            generator.addProvider(new MekanismDataGen.MekanismItemModels(generator, existingFileHelper));
//            generator.addProvider(new MekanismDataGen.MekanismRecipes(generator));
        }
    }

    public static void generate() {
        if (!hasGenerated) {
            try {
                instance.generator.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
            hasGenerated = true;
        }
    }

    public static void injectDatapackFinder(PackRepository resourcePacks) {
       DistExecutor.<Boolean>unsafeRunForDist(() -> () -> {
            if (resourcePacks != Minecraft.getInstance().getResourcePackRepository()) {
                resourcePacks.addPackFinder(new EEPackFinder(PackType.CLIENT_RESOURCES));
                EmendatusEnigmatica.LOGGER.info("Injecting data pack finder.");
            }
            return false;
        }, () -> () -> {
            resourcePacks.addPackFinder(new EEPackFinder(PackType.SERVER_DATA));
            EmendatusEnigmatica.LOGGER.info("Injecting server data pack finder.");
            return false;
        });
    }
}
