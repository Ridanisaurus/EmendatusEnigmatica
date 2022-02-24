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
import com.ridanisaurus.emendatusenigmatica.proxy.ClientProxy;
import com.ridanisaurus.emendatusenigmatica.proxy.CommonProxy;
import com.ridanisaurus.emendatusenigmatica.registries.BlockHandler;
import com.ridanisaurus.emendatusenigmatica.registries.ContainerHandler;
import com.ridanisaurus.emendatusenigmatica.registries.FluidHandler;
import com.ridanisaurus.emendatusenigmatica.registries.ItemHandler;
import com.ridanisaurus.emendatusenigmatica.registries.OreHandler;
import com.ridanisaurus.emendatusenigmatica.registries.SlurryHandler;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import com.ridanisaurus.emendatusenigmatica.world.gen.WorldGenHandler;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Reference.MOD_ID)
public class EmendatusEnigmatica {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public static EmendatusEnigmatica instance;
    public static CommonProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    public static boolean MEKANISM_LOADED = false;

    public EmendatusEnigmatica() {
        instance = this;
        MEKANISM_LOADED = ModList.get().isLoaded("mekanism");
        // Register Deferred Registers and populate their tables once the mod is done constructing
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FluidHandler.FLUIDS.register(modEventBus);
        BlockHandler.BLOCKS.register(modEventBus);
        BlockHandler.BLOCK_ENTITIES.register(modEventBus);
        ContainerHandler.CONTAINERS.register(modEventBus);
        OreHandler.BLOCKS.register(modEventBus);
        ItemHandler.ITEMS.register(modEventBus);
        if (MEKANISM_LOADED) SlurryHandler.SLURRIES.register(modEventBus);

        modEventBus.addListener(this::construct);

        // Register World Gen Config
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WorldGenConfig.COMMON_SPEC, "emendatusenigmatica-common.toml");

        // Setup biome loading event for worldgen!
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, this::biomesHigh);
    }

    public void biomesHigh(final BiomeLoadingEvent event) {
        WorldGenHandler.addEEOres(event.getGeneration(), event);
    }

    private void construct(final FMLConstructModEvent event) {
        FluidHandler.fluifInit();
        OreHandler.oreBlocks();
        ItemHandler.oreItems();
        BlockHandler.blockInit();
        ItemHandler.itemInit();
        if (MEKANISM_LOADED) SlurryHandler.slurryInit();
    }

    public static final CreativeModeTab TAB = new CreativeModeTab("emendatusenigmatica") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(BlockHandler.ENIGMATIC_FORTUNIZER.get());
        }
    };
}