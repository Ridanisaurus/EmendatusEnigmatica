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
import com.ridanisaurus.emendatusenigmatica.blocks.EnigmaticFortunizer;
import com.ridanisaurus.emendatusenigmatica.tiles.EnigmaticFortunizerBlockEntity;
import com.ridanisaurus.emendatusenigmatica.util.Materials;
import com.ridanisaurus.emendatusenigmatica.util.ProcessedMaterials;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class BlockHandler {

  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);
  public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Reference.MOD_ID);

  // Storage Blocks
  public static Table<ProcessedMaterials, Materials, Supplier<Block>> backingStorageBlockTable;
  //public static final Supplier<Table<ProcessedMaterials, Materials, RegistryObject<Block>>> storageBlockTable = () -> Optional.ofNullable(backingStorageBlockTable).orElse(ImmutableTable.of());

  public static void blockInit() {
    ImmutableTable.Builder<ProcessedMaterials, Materials, Supplier<Block>> builder = new ImmutableTable.Builder<>();
    for (ProcessedMaterials processedMaterial : ProcessedMaterials.values()) {
      for (Materials material : Materials.values()) {
        List<String> toCreate = Arrays.asList(material.type);
        if (processedMaterial == ProcessedMaterials.STORAGE_BLOCK && !material.isVanilla() && toCreate.contains("Block")) {
            String blockName = material.id + "_block";
            builder.put(processedMaterial, material, BLOCKS.register(blockName, material.block));
        }
      }
    }
    backingStorageBlockTable = builder.build();
  }

  // Machines
  public static final Supplier<Block> ENIGMATIC_FORTUNIZER = BLOCKS.register("enigmatic_fortunizer", EnigmaticFortunizer::new);

  public static final Supplier<BlockEntityType<?>> ENIGMATIC_FORTUNIZER_TILE = BLOCK_ENTITIES.register("enigmatic_fortunizer", () -> BlockEntityType.Builder.of(EnigmaticFortunizerBlockEntity::new, ENIGMATIC_FORTUNIZER.get()).build(null));
}