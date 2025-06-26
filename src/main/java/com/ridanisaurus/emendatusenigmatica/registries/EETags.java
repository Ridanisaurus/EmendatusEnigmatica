/*
 * MIT License
 *
 * Copyright (c) 2020-2024. Ridanisaurus
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

import com.mojang.datafixers.util.Pair;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;

public class EETags {
    public static final Function<String, TagKey<Item>> MATERIAL_INGOT = material ->
        getItemTag(ResourceLocation.fromNamespaceAndPath(Reference.COMMON, "ingots/" + material));

    public static final Function<String, TagKey<Item>> MATERIAL_NUGGET = material ->
        getItemTag(ResourceLocation.fromNamespaceAndPath(Reference.COMMON, "nuggets/" + material));

    public static final Function<String, TagKey<Item>> MATERIAL_STORAGE_BLOCK = material ->
        getItemTag(ResourceLocation.fromNamespaceAndPath(Reference.COMMON, "storage_blocks/" + material));

    public static final Function<String, TagKey<Item>> MATERIAL_GEM = material ->
        getItemTag(ResourceLocation.fromNamespaceAndPath(Reference.COMMON, "gems/" + material));

    public static final Function<String, TagKey<Item>> MATERIAL_DUST = material ->
        getItemTag(ResourceLocation.fromNamespaceAndPath(Reference.COMMON, "dusts/" + material));

    public static final Function<String, TagKey<Item>> MATERIAL_GEAR = material ->
        getItemTag(ResourceLocation.fromNamespaceAndPath(Reference.COMMON, "gears/" + material));

    public static final Function<String, TagKey<Item>> MATERIAL_PLATE = material ->
        getItemTag(ResourceLocation.fromNamespaceAndPath(Reference.COMMON, "plates/" + material));

    public static final Function<String, TagKey<Item>> MATERIAL_ROD = material ->
        getItemTag(ResourceLocation.fromNamespaceAndPath(Reference.COMMON, "rods/" + material));

    public static final Function<String, TagKey<Item>> MATERIAL_ORE = material ->
        getItemTag(ResourceLocation.fromNamespaceAndPath(Reference.COMMON, "ores/" + material));

    public static final Function<String, TagKey<Item>> MATERIAL_RAW = material ->
        getItemTag(ResourceLocation.fromNamespaceAndPath(Reference.COMMON, "raw_materials/" + material));

    public static final Function<String, TagKey<Item>> MATERIAL_RAW_STORAGE_BLOCK = material ->
        getItemTag(ResourceLocation.fromNamespaceAndPath(Reference.COMMON, "storage_blocks/raw_" + material));

    public static final Function<String, TagKey<Item>> MATERIAL_FLUID = material ->
        getItemTag(ResourceLocation.fromNamespaceAndPath(Reference.COMMON, "molten/" + material));

    public static final TagKey<Block> MINEABLE_WITH_PAXEL = create("mineable/paxel");

    public static TagKey<Item> getItemTag(ResourceLocation resourceLocation) {
        return BuiltInRegistries.ITEM.getTags().filter(pair -> pair.getFirst().location().equals(resourceLocation))
            .map(Pair::getFirst).findFirst().orElse(BuiltInRegistries.ITEM.getOrCreateTag(TagKey.create(Registries.ITEM, resourceLocation)).key());
    }

    public static TagKey<Block> getBlockTag(ResourceLocation resourceLocation) {
        return BuiltInRegistries.BLOCK.getTags().filter(pair -> pair.getFirst().location().equals(resourceLocation))
            .map(Pair::getFirst).findFirst().orElse(BuiltInRegistries.BLOCK.getOrCreateTag(TagKey.create(Registries.BLOCK, resourceLocation)).key());
    }

    public static TagKey<Item> getItemTag(String resourceLocation) {
        return getItemTag(ResourceLocation.parse(resourceLocation));
    }

    public static TagKey<Block> getBlockTag(String resourceLocation) {
        return getBlockTag(ResourceLocation.parse(resourceLocation));
    }

    public static TagKey<Block> create(String location) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Reference.COMMON, location));
    }
}
