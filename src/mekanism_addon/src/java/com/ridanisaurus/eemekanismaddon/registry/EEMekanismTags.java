package com.ridanisaurus.eemekanismaddon.registry;

import mekanism.api.MekanismAPI;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.function.Function;

import static com.ridanisaurus.emendatusenigmatica.registries.EETags.getItemTag;

public class EEMekanismTags {
	public static final Function<String, TagKey<Item>> MATERIAL_CRYSTAL =
        material -> getItemTag(MekanismAPI.MEKANISM_MODID + "/crystals/" + material);
	public static final Function<String, TagKey<Item>> MATERIAL_SHARD =
        material -> getItemTag(MekanismAPI.MEKANISM_MODID + "/shards/" + material);
	public static final Function<String, TagKey<Item>> MATERIAL_CLUMP =
        material -> getItemTag(MekanismAPI.MEKANISM_MODID + "/clumps/" + material);
	public static final Function<String, TagKey<Item>> MATERIAL_DIRTY_DUST =
        material -> getItemTag(MekanismAPI.MEKANISM_MODID + "/dirty_dusts/" + material);
	public static final Function<String, TagKey<Item>> MATERIAL_ENRICHED =
        material -> getItemTag(MekanismAPI.MEKANISM_MODID + "/enriched/" + material);
}