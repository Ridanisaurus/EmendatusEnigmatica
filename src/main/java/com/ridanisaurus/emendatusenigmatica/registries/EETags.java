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

import com.ridanisaurus.emendatusenigmatica.datagen.MockedNamedTag;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import java.util.function.Function;

public class EETags {
	public static final Function<String, MockedNamedTag<Item>> MATERIAL_INGOT = material -> new MockedNamedTag<Item>(new ResourceLocation(Reference.FORGE, "ingots/" + material));
	public static final Function<String, MockedNamedTag<Item>> MATERIAL_NUGGET = material -> new MockedNamedTag<Item>(new ResourceLocation(Reference.FORGE, "nuggets/" + material));
	public static final Function<String, MockedNamedTag<Item>> MATERIAL_STORAGE_BLOCK = material -> new MockedNamedTag<Item>(new ResourceLocation(Reference.FORGE, "storage_blocks/" + material));
	public static final Function<String, MockedNamedTag<Item>> MATERIAL_GEM = material -> new MockedNamedTag<Item>(new ResourceLocation(Reference.FORGE, "gems/" + material));
	public static final Function<String, MockedNamedTag<Item>> MATERIAL_DUST = material -> new MockedNamedTag<Item>(new ResourceLocation(Reference.FORGE, "dusts/" + material));
	public static final Function<String, MockedNamedTag<Item>> MATERIAL_GEAR = material -> new MockedNamedTag<Item>(new ResourceLocation(Reference.FORGE, "gears/" + material));
	public static final Function<String, MockedNamedTag<Item>> MATERIAL_PLATE = material -> new MockedNamedTag<Item>(new ResourceLocation(Reference.FORGE, "plates/" + material));
	public static final Function<String, MockedNamedTag<Item>> MATERIAL_ROD = material -> new MockedNamedTag<Item>(new ResourceLocation(Reference.FORGE, "rods/" + material));
	public static final Function<String, MockedNamedTag<Item>> MATERIAL_ORE = material -> new MockedNamedTag<Item>(new ResourceLocation(Reference.FORGE, "ores/" + material));
	public static final Function<String, MockedNamedTag<Item>> MATERIAL_CHUNK = material -> new MockedNamedTag<Item>(new ResourceLocation(Reference.FORGE, "chunks/" + material));
	public static final Function<String, MockedNamedTag<Item>> MATERIAL_CLUSTER = material -> new MockedNamedTag<Item>(new ResourceLocation(Reference.FORGE, "clusters/" + material));
	public static final Function<String, MockedNamedTag<Item>> MATERIAL_FLUID = material -> new MockedNamedTag<Item>(new ResourceLocation(Reference.FORGE, "molten/" + material));
	public static final Function<String, MockedNamedTag<Item>> MATERIAL_NONE = material -> new MockedNamedTag<Item>(new ResourceLocation(Reference.FORGE, material));

	public static final Function<String, MockedNamedTag<Item>> MATERIAL_CRYSTAL = material -> new MockedNamedTag<Item>(new ResourceLocation(Reference.MEKANISM, "crystals/" + material));
	public static final Function<String, MockedNamedTag<Item>> MATERIAL_SHARD = material -> new MockedNamedTag<Item>(new ResourceLocation(Reference.MEKANISM, "shards/" + material));
	public static final Function<String, MockedNamedTag<Item>> MATERIAL_CLUMP = material -> new MockedNamedTag<Item>(new ResourceLocation(Reference.MEKANISM, "clumps/" + material));
	public static final Function<String, MockedNamedTag<Item>> MATERIAL_DIRTY_DUST = material -> new MockedNamedTag<Item>(new ResourceLocation(Reference.MEKANISM, "dirty_dusts/" + material));
}
