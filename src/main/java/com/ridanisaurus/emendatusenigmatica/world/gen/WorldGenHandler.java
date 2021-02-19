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
import com.ridanisaurus.emendatusenigmatica.util.Strata;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.Collection;
import java.util.EnumSet;

public class WorldGenHandler {

  //Ore Blocks
  private static Table<Strata, Materials, ConfiguredFeature<?, ?>> oreFeatures;
  //public static final Supplier<Table<Strata, Materials, ConfiguredFeature<?, ?>>> oreFeatureTable = () -> Optional.ofNullable(oreFeatures).orElse(ImmutableTable.of());

  public static void oreFeatures() {
    Collection<Strata> activeStrata = EnumSet.noneOf(Strata.class);
    Collection<Materials> activeOres = EnumSet.noneOf(Materials.class);

    ImmutableTable.Builder<Strata, Materials, ConfiguredFeature<?, ?>> builder = new ImmutableTable.Builder<>();
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
    for (Table.Cell<Strata, Materials, ConfiguredFeature<?, ?>> cell : oreFeatures.cellSet()) {
      BakedOreProps p = WorldGenConfig.COMMON.ORES.get(cell.getColumnKey());
      if (p.isOverworldListed(event.getName()) == p.OVERWORLD_BIOMELIST_INVERT && event.getCategory() != Biome.Category.NETHER && event.getCategory() != Biome.Category.THEEND) {
        builder.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, cell.getValue());
      }
      if (p.isNetherListed(event.getName()) == p.NETHER_BIOMELIST_INVERT && event.getCategory() == Biome.Category.NETHER) {
        builder.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, cell.getValue());
      }
      if (p.isEndListed(event.getName()) == p.END_BIOMELIST_INVERT && event.getCategory() == Biome.Category.THEEND) {
        builder.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, cell.getValue());
      }
    }
  }

  private static RuleTest getFilter(Strata stratum) {
    return new BlockMatchRuleTest(stratum.block.get());
  }

  private static BlockState getOreBlock(Strata stratum, Materials material) {
    EmendatusEnigmatica.LOGGER.debug("Ores Recorded: {}", stratum + " " + material);
    return OreHandler.backingOreBlockTable.get(stratum, material).get().getDefaultState();
  }

  private static ConfiguredFeature<?, ?> getOreFeature(int count, int size, int baseline, int spread, RuleTest filler, BlockState state) {
    Feature<OreFeatureConfig> oreFeature = Feature.ORE;
    return oreFeature.withConfiguration(new OreFeatureConfig(filler, state, size))
            .withPlacement(Placement.DEPTH_AVERAGE.configure(new DepthAverageConfig(baseline, spread)))
            .square() // square vein
            .func_242731_b(count) // max count per chunk
            ;
  }
}