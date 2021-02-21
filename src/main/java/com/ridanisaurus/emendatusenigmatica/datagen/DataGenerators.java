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

package com.ridanisaurus.emendatusenigmatica.datagen;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

  @SubscribeEvent
  public static void gatherCommonData(FMLCommonSetupEvent event) {
    DataGenerator generator = MemoryDataGeneratorFactory.createMemoryDataGenerator();
    ExistingFileHelper existingFileHelper = new ExistingFileHelper(ImmutableList.of(), ImmutableSet.of(), false);

   // BlockTagsGen blockTagsGeneration = new BlockTagsGen(generator, existingFileHelper);
    //generator.addProvider(new RecipesGen(generator));
    //generator.addProvider(new ItemTagsGen(generator, blockTagsGeneration, existingFileHelper));
    //generator.addProvider(blockTagsGeneration);
    //generator.addProvider(new LootTablesGen(generator));
  }

  @SubscribeEvent
  public static void gatherClientData(FMLClientSetupEvent event) {
    DataGenerator generator = MemoryDataGeneratorFactory.createMemoryDataGenerator();
    ExistingFileHelper existingFileHelper = new ExistingFileHelper(ImmutableList.of(), ImmutableSet.of(), false);

    //generator.addProvider(new BlockStatesAndModelsGen(generator, existingFileHelper));
    generator.addProvider(new LangGen(generator));
    //generator.addProvider(new ItemModelsGen(generator, existingFileHelper));
  }
}