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
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.util.Ores;
import com.ridanisaurus.emendatusenigmatica.util.Strata;
import com.ridanisaurus.emendatusenigmatica.blocks.BlockBase;
import com.ridanisaurus.emendatusenigmatica.blocks.EnigmaticExchanger;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;

public class BlockHandler {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);

    //Ore Blocks
    private static Table<Strata, Ores, RegistryObject<Block>> backingOreBlockTable;
    public static final Supplier<Table<Strata, Ores, RegistryObject<Block>>> oreBlockTable = () -> Optional.ofNullable(backingOreBlockTable).orElse(ImmutableTable.of());

    public static void oreBlocks() {
        ImmutableTable.Builder<Strata, Ores, RegistryObject<Block>> builder = new ImmutableTable.Builder<>();
        for (Strata stratum : Strata.values()) {
            for (Ores ore : Ores.values()) {
                String oreName = "ore_" + ore.id + (stratum != Strata.STONE ? "_" + stratum.suffix : "");
                builder.put(stratum, ore, BLOCKS.register(oreName, ore.block));
            }
        }
        backingOreBlockTable = builder.build();
    }

    //Blocks
    public static final RegistryObject<Block> BLOCK_COPPER = BLOCKS.register("block_copper", BlockBase::new);
    public static final RegistryObject<Block> BLOCK_ALUMINUM = BLOCKS.register("block_aluminum", BlockBase::new);
    public static final RegistryObject<Block> BLOCK_SILVER = BLOCKS.register("block_silver", BlockBase::new);
    public static final RegistryObject<Block> BLOCK_LEAD = BLOCKS.register("block_lead", BlockBase::new);
    public static final RegistryObject<Block> BLOCK_NICKEL = BLOCKS.register("block_nickel", BlockBase::new);
    public static final RegistryObject<Block> BLOCK_URANIUM = BLOCKS.register("block_uranium", BlockBase::new);
    public static final RegistryObject<Block> BLOCK_OSMIUM = BLOCKS.register("block_osmium", BlockBase::new);
    public static final RegistryObject<Block> BLOCK_TIN = BLOCKS.register("block_tin", BlockBase::new);
    public static final RegistryObject<Block> BLOCK_ZINC = BLOCKS.register("block_zinc", BlockBase::new);
    public static final RegistryObject<Block> BLOCK_BRONZE = BLOCKS.register("block_bronze", BlockBase::new);
    public static final RegistryObject<Block> BLOCK_BRASS = BLOCKS.register("block_brass", BlockBase::new);
    public static final RegistryObject<Block> BLOCK_CONSTANTAN = BLOCKS.register("block_constantan", BlockBase::new);
    public static final RegistryObject<Block> BLOCK_ELECTRUM = BLOCKS.register("block_electrum", BlockBase::new);
    public static final RegistryObject<Block> BLOCK_STEEL = BLOCKS.register("block_steel", BlockBase::new);

    //Machines
    public static final RegistryObject<Block> ENIGMATIC_EXCHANGER = BLOCKS.register("enigmatic_exchanger", EnigmaticExchanger::new);

}
