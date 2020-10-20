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

import com.ridanisaurus.emendatusenigmatica.config.WorldGenConfig;
import com.ridanisaurus.emendatusenigmatica.registries.BlockHandler;
import com.ridanisaurus.emendatusenigmatica.registries.ItemHandler;
import com.ridanisaurus.emendatusenigmatica.registries.OreHandler;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import com.ridanisaurus.emendatusenigmatica.world.gen.WorldGenHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Reference.MOD_ID)
public class EmendatusEnigmatica {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public EmendatusEnigmatica() {
        // Register Deferred Registers and populate their tables once the mod is done constructing
        BlockHandler.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        OreHandler.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ItemHandler.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientEvents);

        // Register World Gen Config
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WorldGenConfig.COMMON_SPEC, "emendatusenigmatica-common.toml");

        // Setup biome loading event for worldgen!
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, this::biomesHigh);
    }

    public void biomesHigh(final BiomeLoadingEvent event) {
        WorldGenHandler.addEEOres(event.getGeneration());
    }

    private void init(final FMLConstructModEvent event) {
        OreHandler.oreBlocks();
        ItemHandler.oreItems();

        BlockHandler.blockInit();
        ItemHandler.itemInit();
    }

    private void clientEvents(final FMLClientSetupEvent event) {
        for (RegistryObject<Block> block : OreHandler.BLOCKS.getEntries()) {
            RenderTypeLookup.setRenderLayer(block.get(), RenderType.getTranslucent());
        }
    }

    public static final ItemGroup TAB = new ItemGroup("emendatusenigmatica") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BlockHandler.ENIGMATIC_EXCHANGER.get());
        }
    };
}