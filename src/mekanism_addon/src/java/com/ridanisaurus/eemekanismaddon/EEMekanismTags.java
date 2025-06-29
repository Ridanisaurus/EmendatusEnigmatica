package com.ridanisaurus.eemekanismaddon;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

import java.util.function.Function;

public class EEMekanismTags {
	public static final Function<String, TagKey<Item>> MATERIAL_CRYSTAL = material -> getItemTag(new ResourceLocation(EEMekanismAddon.MEKANISM, "crystals/" + material));
	public static final Function<String, TagKey<Item>> MATERIAL_SHARD = material -> getItemTag(new ResourceLocation(EEMekanismAddon.MEKANISM, "shards/" + material));
	public static final Function<String, TagKey<Item>> MATERIAL_CLUMP = material -> getItemTag(new ResourceLocation(EEMekanismAddon.MEKANISM, "clumps/" + material));
	public static final Function<String, TagKey<Item>> MATERIAL_DIRTY_DUST = material -> getItemTag(new ResourceLocation(EEMekanismAddon.MEKANISM, "dirty_dusts/" + material));
	public static final Function<String, TagKey<Item>> MATERIAL_ENRICHED = material -> getItemTag(new ResourceLocation(EEMekanismAddon.MEKANISM, "enriched/" + material));

	public static TagKey<Item> getItemTag(ResourceLocation resourceLocation) {
		return ForgeRegistries.ITEMS.tags().stream().filter(items -> items.getKey().location().equals(resourceLocation)).map(ITag::getKey).findFirst().orElse(ForgeRegistries.ITEMS.tags().createTagKey(resourceLocation));
	}
}