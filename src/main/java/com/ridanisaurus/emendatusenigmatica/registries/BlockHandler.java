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

package com.ridanisaurus.emendatusenigmatica.registries;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.ridanisaurus.emendatusenigmatica.blocks.EnigmaticExchanger;
import com.ridanisaurus.emendatusenigmatica.util.*;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Optional;
import java.util.function.Supplier;

public class BlockHandler {

  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);

  // Storage Blocks
  private static Table<ProcessedMaterials, Materials, RegistryObject<Block>> backingStorageBlockTable;
  public static final Supplier<Table<ProcessedMaterials, Materials, RegistryObject<Block>>> storageBlockTable = () -> Optional.ofNullable(backingStorageBlockTable).orElse(ImmutableTable.of());

  public static void blockInit() {
    ImmutableTable.Builder<ProcessedMaterials, Materials, RegistryObject<Block>> builder = new ImmutableTable.Builder<>();
    for (ProcessedMaterials processedMaterial : ProcessedMaterials.values()) {
      for (Materials material : Materials.values()) {
        if (processedMaterial == ProcessedMaterials.STORAGE_BLOCK && !material.isVanilla()) {
            String blockName = material.id + "_block";
            builder.put(processedMaterial, material, BLOCKS.register(blockName, material.block));
        }
      }
    }
    backingStorageBlockTable = builder.build();
  }

  // Machines
  public static final RegistryObject<Block> ENIGMATIC_EXCHANGER = BLOCKS.register("enigmatic_exchanger", EnigmaticExchanger::new);
}