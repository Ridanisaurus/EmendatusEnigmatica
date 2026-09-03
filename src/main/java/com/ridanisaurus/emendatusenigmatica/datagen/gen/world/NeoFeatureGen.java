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

package com.ridanisaurus.emendatusenigmatica.datagen.gen.world;

import com.ridanisaurus.emendatusenigmatica.loader.ConfigCreationContext;
import com.ridanisaurus.emendatusenigmatica.datagen.IFinishedGenericJSON;
import com.ridanisaurus.emendatusenigmatica.datagen.provider.EENeoFeatureProvider;
import com.ridanisaurus.emendatusenigmatica.datagen.builder.FeatureBuilder;
import com.ridanisaurus.emendatusenigmatica.plugin.deposit.IDepositProcessor;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.common.CommonDepositModelBase;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class NeoFeatureGen extends EENeoFeatureProvider {
	private static final List<String> DEFAULT_COAL_ORE = List.of("minecraft:ore_coal_upper", "minecraft:ore_coal_lower");
	private static final List<String> DEFAULT_COPPER_ORE = List.of("minecraft:ore_copper", "minecraft:ore_copper_large");
	private static final List<String> DEFAULT_IRON_ORE = List.of("minecraft:ore_iron_upper", "minecraft:ore_iron_middle", "minecraft:ore_iron_small");
	private static final List<String> DEFAULT_GOLD_ORE = List.of("minecraft:ore_gold_extra", "minecraft:ore_gold", "minecraft:ore_gold_lower");
	private static final List<String> DEFAULT_NETHER_GOLD_ORE = List.of("minecraft:ore_gold_nether", "minecraft:ore_gold_deltas");
	private static final List<String> DEFAULT_REDSTONE_ORE = List.of("minecraft:ore_redstone", "minecraft:ore_redstone_lower");
	private static final List<String> DEFAULT_LAPIS_ORE = List.of("minecraft:ore_lapis", "minecraft:ore_lapis_buried");
	private static final List<String> DEFAULT_DIAMOND_ORE = List.of("minecraft:ore_diamond", "minecraft:ore_diamond_medium", "minecraft:ore_diamond_large", "minecraft:ore_diamond_buried");
	private static final List<String> DEFAULT_EMERALD_ORE = List.of("minecraft:ore_emerald");
	private static final List<String> DEFAULT_QUARTZ_ORE = List.of("minecraft:ore_quartz_nether", "minecraft:ore_quartz_deltas");
	public static ModConfigSpec.BooleanValue disableCoal = null;
	public static ModConfigSpec.BooleanValue disableCopper = null;
	public static ModConfigSpec.BooleanValue disableIron = null;
	public static ModConfigSpec.BooleanValue disableGold = null;
	public static ModConfigSpec.BooleanValue disableRedstone = null;
	public static ModConfigSpec.BooleanValue disableLapis = null;
	public static ModConfigSpec.BooleanValue disableDiamond = null;
	public static ModConfigSpec.BooleanValue disableEmerald = null;
	public static ModConfigSpec.BooleanValue disableQuartz = null;

	public NeoFeatureGen(DataGenerator gen, CompletableFuture<HolderLookup.Provider> providers) {
		super(gen, providers);
	}

	@Override
	protected void buildFeatures(HolderLookup.Provider provider, Consumer<IFinishedGenericJSON> consumer) {
		handleVanillaOres(consumer);
		//TODO: Rework for new Deposit System
//		for (IDepositProcessor processor : ModelLoader.ACTIVE_PROCESSORS) {
//			CommonDepositModelBase model = processor.getCommonModel();
//			List<String> biomes = new ArrayList<>();
//			List<String> features = new ArrayList<>();
//
//			if (!model.getBiomes().isEmpty()) {
//				if (model.getBiomes().stream().anyMatch(it -> it.startsWith("#"))) {
//					biomes.add("#" + Reference.MOD_ID + ":biome/pack/" + processor.getCommonModel().getName());
//				} else {
//					biomes.addAll(model.getBiomes());
//				}
//			} else {
//				var dim = model.getDimension();
//				if (!dim.startsWith("minecraft")) {
//					// Fallback for modded dimensions - most likely not correct as there is no real schema, but it's a good guess!
//					biomes.add("#" + StringUtils.substringBefore(dim, ":") + ":is_" + StringUtils.substringAfter(dim, ":"));
//				} else {
//					biomes.add("#minecraft:is_" + StringUtils.substringAfter(dim, ":").replace("the_", ""));
//				}
//			}
//			features.add(Reference.MOD_ID + ":" + model.getName());
//			new FeatureBuilder("neoforge:add_features", "underground_ores")
//				.biomes(biomes)
//				.features(features)
//				.save(consumer, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, model.getName() + "_ore_features"));
//		}
	}

	@Override
	public @NotNull String getName() {
		return "Emendatus Enigmatica: Neo Features";
	}


	public static void setupConfig(ConfigCreationContext ctx) {
		disableCoal = ctx.getBuilder()
			.comment("Determines if Coal ore generation should be disabled.")
			.translation("emendatusenigmatica.config.disable_coal_ore")
			.define("disableCoalOre",true);

		disableCopper = ctx.getBuilder()
			.comment("Determines if Copper ore generation should be disabled.")
			.translation("emendatusenigmatica.config.disable_copper_ore")
			.define("disableCopperOre",true);

		disableIron = ctx.getBuilder()
			.comment("Determines if Iron ore generation should be disabled.")
			.translation("emendatusenigmatica.config.disable_iron_ore")
			.define("disableIronOre",true);

		disableGold = ctx.getBuilder()
			.comment("Determines if Gold ore generation should be disabled.")
			.translation("emendatusenigmatica.config.disable_gold_ore")
			.define("disableGoldOre",true);

		disableRedstone = ctx.getBuilder()
			.comment("Determines if Redstone ore generation should be disabled.")
			.translation("emendatusenigmatica.config.disable_redstone_ore")
			.define("disableRedstoneOre",true);

		disableLapis = ctx.getBuilder()
			.comment("Determines if Lapis ore generation should be disabled.")
			.translation("emendatusenigmatica.config.disable_lapis_ore")
			.define("disableLapisOre",true);

		disableDiamond = ctx.getBuilder()
			.comment("Determines if Diamond ore generation should be disabled.")
			.translation("emendatusenigmatica.config.disable_diamond_ore")
			.define("disableDiamondOre",true);

		disableEmerald = ctx.getBuilder()
			.comment("Determines if Emerald ore generation should be disabled.")
			.translation("emendatusenigmatica.config.disable_emerald_ore")
			.define("disableEmeraldOre",true);

		disableQuartz = ctx.getBuilder()
			.comment("Determines if Quartz ore generation should be disabled.")
			.translation("emendatusenigmatica.config.disable_quartz_ore")
			.define("disableQuartzOre",true);
	}

	private void handleVanillaOres(Consumer<IFinishedGenericJSON> consumer) {
		if (disableCoal.get())
			new FeatureBuilder("neoforge:remove_features", "underground_ores")
				.biome("#minecraft:is_overworld")
				.features(DEFAULT_COAL_ORE)
				.save(consumer, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "remove_default_coal_ore"));

		if (disableCopper.get())
			new FeatureBuilder("neoforge:remove_features", "underground_ores")
				.biome("#minecraft:is_overworld")
				.features(DEFAULT_COPPER_ORE)
				.save(consumer, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "remove_default_copper_ore"));

		if (disableIron.get())
			new FeatureBuilder("neoforge:remove_features", "underground_ores")
				.biome("#minecraft:is_overworld")
				.features(DEFAULT_IRON_ORE)
				.save(consumer, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "remove_default_iron_ore"));

		if (disableGold.get()) {
			new FeatureBuilder("neoforge:remove_features", "underground_ores")
				.biome("#minecraft:is_overworld")
				.features(DEFAULT_GOLD_ORE)
				.save(consumer, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "remove_default_gold_ore"));
			new FeatureBuilder("neoforge:remove_features", "underground_ores")
				.biome("#minecraft:is_nether")
				.features(DEFAULT_NETHER_GOLD_ORE)
				.save(consumer, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "remove_default_nether_gold_ore"));
		}

		if (disableRedstone.get())
			new FeatureBuilder("neoforge:remove_features", "underground_ores")
				.biome("#minecraft:is_overworld")
				.features(DEFAULT_REDSTONE_ORE)
				.save(consumer, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "remove_default_redstone_ore"));

		if (disableLapis.get())
			new FeatureBuilder("neoforge:remove_features", "underground_ores")
				.biome("#minecraft:is_overworld")
				.features(DEFAULT_LAPIS_ORE)
				.save(consumer, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "remove_default_lapis_ore"));

		if (disableDiamond.get())
			new FeatureBuilder("neoforge:remove_features", "underground_ores")
				.biome("#minecraft:is_overworld")
				.features(DEFAULT_DIAMOND_ORE)
				.save(consumer, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "remove_default_diamond_ore"));

		if (disableEmerald.get())
			new FeatureBuilder("neoforge:remove_features", "underground_ores")
				.biome("#minecraft:is_overworld")
				.features(DEFAULT_EMERALD_ORE)
				.save(consumer, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "remove_default_emerald_ore"));

		if (disableQuartz.get())
			new FeatureBuilder("neoforge:remove_features", "underground_ores")
				.biome("#minecraft:is_nether")
				.features(DEFAULT_QUARTZ_ORE)
				.save(consumer, ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "remove_default_nether_quartz_ore"));

	}
}
