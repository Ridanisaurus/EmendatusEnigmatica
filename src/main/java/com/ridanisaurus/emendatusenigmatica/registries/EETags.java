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

import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

import java.util.function.Function;

public class EETags {
	public static final Function<String, TagKey<Item>> MATERIAL_INGOT = material -> getItemTag(new ResourceLocation(Reference.FORGE, "ingots/" + material));
	public static final Function<String, TagKey<Item>> MATERIAL_NUGGET = material -> getItemTag(new ResourceLocation(Reference.FORGE, "nuggets/" + material));
	public static final Function<String, TagKey<Item>> MATERIAL_STORAGE_BLOCK = material -> getItemTag(new ResourceLocation(Reference.FORGE, "storage_blocks/" + material));
	public static final Function<String, TagKey<Item>> MATERIAL_GEM = material -> getItemTag(new ResourceLocation(Reference.FORGE, "gems/" + material));
	public static final Function<String, TagKey<Item>> MATERIAL_DUST = material -> getItemTag(new ResourceLocation(Reference.FORGE, "dusts/" + material));
	public static final Function<String, TagKey<Item>> MATERIAL_GEAR = material -> getItemTag(new ResourceLocation(Reference.FORGE, "gears/" + material));
	public static final Function<String, TagKey<Item>> MATERIAL_PLATE = material -> getItemTag(new ResourceLocation(Reference.FORGE, "plates/" + material));
	public static final Function<String, TagKey<Item>> MATERIAL_ROD = material -> getItemTag(new ResourceLocation(Reference.FORGE, "rods/" + material));
	public static final Function<String, TagKey<Item>> MATERIAL_ORE = material -> getItemTag(new ResourceLocation(Reference.FORGE, "ores/" + material));
	public static final Function<String, TagKey<Item>> MATERIAL_RAW = material -> getItemTag(new ResourceLocation(Reference.FORGE, "raws/" + material));
	public static final Function<String, TagKey<Item>> MATERIAL_FLUID = material -> getItemTag(new ResourceLocation(Reference.FORGE, "molten/" + material));


	public static final Function<String, TagKey<Item>> MATERIAL_CRYSTAL = material -> getItemTag(new ResourceLocation(Reference.MEKANISM, "crystals/" + material));
	public static final Function<String, TagKey<Item>> MATERIAL_SHARD = material -> getItemTag(new ResourceLocation(Reference.MEKANISM, "shards/" + material));
	public static final Function<String, TagKey<Item>> MATERIAL_CLUMP = material -> getItemTag(new ResourceLocation(Reference.MEKANISM, "clumps/" + material));
	public static final Function<String, TagKey<Item>> MATERIAL_DIRTY_DUST = material -> getItemTag(new ResourceLocation(Reference.MEKANISM, "dirty_dusts/" + material));

	public static final Function<String, TagKey<Item>> MATERIAL_CRUSHED_ORE = material -> getItemTag(new ResourceLocation(Reference.CREATE, "crushed_ores/" + material));

	public static final Function<String, TagKey<Item>> MATERIAL_FRAGMENT = material -> getItemTag(new ResourceLocation(Reference.BLOODMAGIC, "fragments/" + material));
	public static final Function<String, TagKey<Item>> MATERIAL_GRAVEL = material -> getItemTag(new ResourceLocation(Reference.BLOODMAGIC, "gravels/" + material));
	public static final Function<String, TagKey<Item>> MATERIAL_ARC = material -> getItemTag(new ResourceLocation(Reference.BLOODMAGIC, "arc/" + material));

	public static final Function<String, TagKey<Item>> MATERIAL_NONE = material -> getItemTag(new ResourceLocation(Reference.FORGE, material));

	public static TagKey<Item> getItemTag(ResourceLocation resourceLocation) {
		return ForgeRegistries.ITEMS.tags().stream().filter(items -> items.getKey().location().equals(resourceLocation)).map(ITag::getKey).findFirst().orElse(ForgeRegistries.ITEMS.tags().createTagKey(resourceLocation));
	}

	public static TagKey<Block> getBlockTag(ResourceLocation resourceLocation) {
		return ForgeRegistries.BLOCKS.tags().stream().filter(items -> items.getKey().location().equals(resourceLocation)).map(ITag::getKey).findFirst().orElse(ForgeRegistries.BLOCKS.tags().createTagKey(resourceLocation));
	}
}
