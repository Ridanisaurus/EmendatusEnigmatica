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
import com.ridanisaurus.emendatusenigmatica.config.Config;
import com.ridanisaurus.emendatusenigmatica.datagen.*;
import com.ridanisaurus.emendatusenigmatica.inventory.EnigmaticFortunizerScreen;
import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.EEDeposits;
import com.ridanisaurus.emendatusenigmatica.registries.ContainerHandler;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.resources.ResourcePackList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
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

	public EmendatusEnigmatica() {
		instance = this;
		MemoryDataGeneratorFactory.init();
		EELoader.load();
		EEDeposits.load();
		// Register Deferred Registers and populate their tables once the mod is done constructing
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ContainerHandler.CONTAINERS.register(modEventBus);

		EERegistrar.Finalize(modEventBus);

		modEventBus.addListener(this::init);
		modEventBus.addListener(this::clientEvents);

		// Register Config
		Config.register();

		// Setup biome loading event for worldgen!
		MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, this::biomesHigh);

		registerDataGen();
		// Resource Pack
		if (FMLEnvironment.dist == Dist.CLIENT) {
			Minecraft.getInstance().getResourcePackList().addPackFinder(new EEPackFinder(PackType.RESOURCE));
		}

		MinecraftForge.EVENT_BUS.addListener(this::onServerStart);
	}

	// Data Pack
	public void onServerStart(final FMLServerAboutToStartEvent event) {
		event.getServer().getResourcePacks().addPackFinder(new EEPackFinder(PackType.DATA));
	}

	public void biomesHigh(final BiomeLoadingEvent event) {
		EEDeposits.generateBiomes(event);
	}

	private void init(final FMLConstructModEvent event) {

	}

	private void clientEvents(final FMLClientSetupEvent event) {
		for (RegistryObject<Block> block : EERegistrar.oreBlockTable.values()) {
			RenderTypeLookup.setRenderLayer(block.get(), layer -> layer == RenderType.getSolid() || layer == RenderType.getTranslucent());
		}

		ScreenManager.registerFactory(ContainerHandler.ENIGMATIC_FORTUNIZER_CONTAINER.get(), EnigmaticFortunizerScreen::new);
	}

	public static final ItemGroup TAB = new ItemGroup("emendatusenigmatica") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(EERegistrar.ENIGMATIC_FORTUNIZER.get());
		}
	};

	private void registerDataGen() {
		generator = MemoryDataGeneratorFactory.createMemoryDataGenerator();
		ExistingFileHelper existingFileHelper = new ExistingFileHelper(ImmutableList.of(), ImmutableSet.of(), false);

		BlockTagsGen blockTagsGeneration = new BlockTagsGen(generator, existingFileHelper);
		generator.addProvider(new RecipesGen(generator));
		generator.addProvider(new ItemTagsGen(generator, blockTagsGeneration, existingFileHelper));
		generator.addProvider(blockTagsGeneration);
		generator.addProvider(new LootTablesGen(generator));
		generator.addProvider(new BlockStatesAndModelsGen(generator, existingFileHelper));
		generator.addProvider(new LangGen(generator));
		generator.addProvider(new ItemModelsGen(generator, existingFileHelper));
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

	public static void injectDatapackFinder(ResourcePackList resourcePacks) {
		if (DistExecutor.unsafeRunForDist(() -> () -> resourcePacks != Minecraft.getInstance().getResourcePackList(), () -> () -> true)) {
			resourcePacks.addPackFinder(new EEPackFinder(PackType.RESOURCE));
			EmendatusEnigmatica.LOGGER.info("Injecting data pack finder.");
		}
	}
}