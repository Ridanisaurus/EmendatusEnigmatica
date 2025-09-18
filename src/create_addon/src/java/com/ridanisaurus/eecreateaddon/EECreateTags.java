package com.ridanisaurus.eecreateaddon;

import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

import java.util.function.Function;

public class EECreateTags {
	public static final Function<String, TagKey<Item>> MATERIAL_CRUSHED_ORE = material -> getItemTag(new ResourceLocation(EECreateAddon.CREATE, "crushed_ores/" + material));

	public static TagKey<Item> getItemTag(ResourceLocation resourceLocation) {
		return ForgeRegistries.ITEMS.tags().stream().filter(items -> items.getKey().location().equals(resourceLocation)).map(ITag::getKey).findFirst().orElse(ForgeRegistries.ITEMS.tags().createTagKey(resourceLocation));
	}
}
