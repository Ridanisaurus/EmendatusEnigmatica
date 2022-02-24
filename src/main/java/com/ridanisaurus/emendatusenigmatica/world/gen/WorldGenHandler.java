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

package com.ridanisaurus.emendatusenigmatica.world.gen;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.config.WorldGenConfig;
import com.ridanisaurus.emendatusenigmatica.config.WorldGenConfig.OreConfigs.BakedOreProps;
import com.ridanisaurus.emendatusenigmatica.registries.OreHandler;
import com.ridanisaurus.emendatusenigmatica.util.Materials;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import com.ridanisaurus.emendatusenigmatica.util.Strata;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

public class WorldGenHandler {

  //Ore Blocks
  private static Table<Strata, Materials, PlacedFeature> oreFeatures;
  //public static final Supplier<Table<Strata, Materials, ConfiguredFeature<?, ?>>> oreFeatureTable = () -> Optional.ofNullable(oreFeatures).orElse(ImmutableTable.of());

  public static void oreFeatures() {
    Collection<Strata> activeStrata = EnumSet.noneOf(Strata.class);
    Collection<Materials> activeOres = EnumSet.noneOf(Materials.class);

    ImmutableTable.Builder<Strata, Materials, PlacedFeature> builder = new ImmutableTable.Builder<>();
    for (Strata stratum : Strata.values()) {
      if (WorldGenConfig.COMMON.STRATA.get(stratum) && stratum.block.get() != null) {
        activeStrata.add(stratum);
        for (Materials material : Materials.values()) {
          if (material.oreBlock != null) {
            BakedOreProps p = WorldGenConfig.COMMON.ORES.get(material);

              if (p.OVERWORLD_ACTIVE && stratum.dim.equals("overworld")) {
                activeOres.add(material);
                builder.put(stratum, material,
                        getOreFeature(
                                p.OVERWORLD_COUNT,
                                p.OVERWORLD_SIZE,
                                p.OVERWORLD_BASE,
                                p.OVERWORLD_SPREAD,
                                p.OVERWORLD_USE_SPREAD,
                                getFilter(stratum),
                                getOreBlock(stratum, material))
                );
              } else if (p.NETHER_ACTIVE && stratum.dim.equals("nether")) {
                activeOres.add(material);
                builder.put(stratum, material,
                        getOreFeature(
                                p.NETHER_COUNT,
                                p.NETHER_SIZE,
                                p.NETHER_BASE,
                                p.NETHER_SPREAD,
                                p.NETHER_USE_SPREAD,
                                getFilter(stratum),
                                getOreBlock(stratum, material))
                );
              } else if (p.END_ACTIVE && stratum.dim.equals("end")) {
                activeOres.add(material);
                builder.put(stratum, material,
                        getOreFeature(
                                p.END_COUNT,
                                p.END_SIZE,
                                p.END_BASE,
                                p.END_SPREAD,
                                p.END_USE_SPREAD,
                                getFilter(stratum),
                                getOreBlock(stratum, material))
                );
              }
          }
        }
      }
    }

    oreFeatures = builder.build();


    EmendatusEnigmatica.LOGGER.debug("Enabled Strata: {}", activeStrata);
    EmendatusEnigmatica.LOGGER.debug("Enabled Ores: {}", activeOres);
  }

  public static void addEEOres(BiomeGenerationSettingsBuilder builder, BiomeLoadingEvent event) {
    for (var cell : oreFeatures.cellSet()) {
      BakedOreProps p = WorldGenConfig.COMMON.ORES.get(cell.getColumnKey());
      if (p.isOverworldListed(event.getName()) == p.OVERWORLD_BIOMELIST_INVERT && event.getCategory() != Biome.BiomeCategory.NETHER && event.getCategory() != Biome.BiomeCategory.THEEND) {
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, cell.getValue());
      }
      if (p.isNetherListed(event.getName()) == p.NETHER_BIOMELIST_INVERT && event.getCategory() == Biome.BiomeCategory.NETHER) {
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, cell.getValue());
      }
      if (p.isEndListed(event.getName()) == p.END_BIOMELIST_INVERT && event.getCategory() == Biome.BiomeCategory.THEEND) {
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, cell.getValue());
      }
    }
  }

  private static RuleTest getFilter(Strata stratum) {
    return new BlockMatchTest(stratum.block.get());
  }

  private static BlockState getOreBlock(Strata stratum, Materials material) {
    EmendatusEnigmatica.LOGGER.debug("Ores Recorded: {}", stratum + " " + material);
    return OreHandler.backingOreBlockTable.get(stratum, material).get().defaultBlockState();
  }

  private static PlacedFeature getOreFeature(int count, int size, int baseline, int spread, boolean useSpread, RuleTest filler, BlockState state) {
    var configuredFeature = Feature.ORE.configured(new OreConfiguration(filler, state, size));
    Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Reference.MOD_ID, state.getBlock().getDescriptionId()), configuredFeature);
    // FIXME: spread doesnt work yet because I havent figured out what the values should be
    // useSpread ? HeightRangePlacement.triangle(VerticalAnchor.absolute(baseline), VerticalAnchor.absolute(spread)) : [the other one[
    return PlacementUtils.register(Reference.MOD_ID + ":" + state.getBlock().getDescriptionId(), configuredFeature.placed(commonOrePlacement(count, HeightRangePlacement.uniform(VerticalAnchor.absolute(baseline - spread), VerticalAnchor.absolute(baseline + spread)))));
  }

  private static List<PlacementModifier> orePlacement(PlacementModifier modifier1, PlacementModifier modifier2) {
    return List.of(modifier1, InSquarePlacement.spread(), modifier2, BiomeFilter.biome());
  }

  private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier modifier) {
    return orePlacement(CountPlacement.of(count), modifier);
  }
}