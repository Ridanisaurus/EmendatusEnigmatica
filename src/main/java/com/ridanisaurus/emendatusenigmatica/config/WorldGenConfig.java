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

package com.ridanisaurus.emendatusenigmatica.config;

import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.util.Materials;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import com.ridanisaurus.emendatusenigmatica.util.Strata;
import com.ridanisaurus.emendatusenigmatica.world.gen.WorldGenHandler;
import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WorldGenConfig {

  public static class Common {

    public final OreConfigs ORES;
    public final StrataConfigs STRATA;

    Common(ForgeConfigSpec.Builder builder) {
      ORES = new OreConfigs(builder);
      STRATA = new StrataConfigs(builder);
    }

    void bake() {
      ORES.bake();
      STRATA.bake();
    }
  }

  public static class OreConfigs {
    private final Map<Materials, Properties> configMap = new Object2ObjectOpenHashMap<>();
    private final boolean configured;

    private final Map<Materials, BakedOreProps> bakedMap = new Object2ObjectOpenHashMap<>();

    OreConfigs(ForgeConfigSpec.Builder builder) {

      // start
      builder.comment("World Gen - Ore Generation Configuration");

      for (Materials material : Materials.values()) {
        if (material.oreBlock != null) {

          addValue(material, builder, material.defaultSize, material.defaultCount, material.defaultBaseline, material.defaultSpread, material.netherBase, material.netherSpread, material.netherCount, material.netherSize, material.endBase, material.endSpread,material.endCount, material.endSize);

        }
      }

      configured = true;
    }

    void addValue(Materials material, ForgeConfigSpec.Builder builder, int size, int count, int baseline, int spread, int netherBase, int netherSpread, int netherCount, int netherSize, int endBase, int endSpread, int endCount, int endSize) {
      addValue(material, new Properties(material.localisedName, builder, size, count, baseline, spread, netherBase, netherSpread, netherCount, netherSize, endBase, endSpread, endCount, endSize));
    }

    void addValue(Materials material, Properties props) {
      configMap.put(material, props);
    }

    private void bake() {
      if (!configured) {
        EmendatusEnigmatica.LOGGER.warn("Ore properties have not been configured yet!");
        return;
      }
      bakedMap.clear();
      configMap.forEach((s, p) -> bakedMap.put(s, new BakedOreProps(p)));
    }

    public BakedOreProps get(Materials material) {
      return bakedMap.get(material);
    }

    public static class BakedOreProps {
      public final boolean OVERWORLD_ACTIVE;
      public final int SIZE;
      public final int COUNT;
      public final int BASELINE;
      public final int SPREAD;
      public final boolean NETHER_ACTIVE;
      public final int NETHER_BASE;
      public final int NETHER_SPREAD;
      public final int NETHER_COUNT;
      public final int NETHER_SIZE;
      public final boolean END_ACTIVE;
      public final int END_BASE;
      public final int END_SPREAD;
      public final int END_COUNT;
      public final int END_SIZE;

      BakedOreProps(Properties properties) {
        OVERWORLD_ACTIVE = properties.OVERWORLD_ACTIVE.get();
        SIZE = properties.SIZE.get();
        COUNT = properties.COUNT.get();
        BASELINE = properties.BASELINE.get();
        SPREAD = properties.SPREAD.get();
        NETHER_ACTIVE = properties.NETHER_ACTIVE.get();
        NETHER_BASE = properties.NETHER_BASE.get();
        NETHER_SPREAD = properties.NETHER_SPREAD.get();
        NETHER_COUNT = properties.NETHER_COUNT.get();
        NETHER_SIZE = properties.NETHER_SIZE.get();
        END_ACTIVE = properties.END_ACTIVE.get();
        END_BASE = properties.END_BASE.get();
        END_SPREAD = properties.END_SPREAD.get();
        END_COUNT = properties.END_COUNT.get();
        END_SIZE = properties.END_SIZE.get();
      }
    }

    public static class Properties {
      public final BooleanValue OVERWORLD_ACTIVE;
      public final IntValue SIZE;
      public final IntValue COUNT;
      public final IntValue BASELINE;
      public final IntValue SPREAD;
      public final BooleanValue NETHER_ACTIVE;
      public final IntValue NETHER_BASE;
      public final IntValue NETHER_SPREAD;
      public final IntValue NETHER_COUNT;
      public final IntValue NETHER_SIZE;
      public final BooleanValue END_ACTIVE;
      public final IntValue END_BASE;
      public final IntValue END_SPREAD;
      public final IntValue END_COUNT;
      public final IntValue END_SIZE;

      public Properties(String localisedName, ForgeConfigSpec.Builder builder, int size, int count, int baseline, int spread, int netherBase, int netherSpread, int netherCount, int netherSize, int endBase, int endSpread, int endCount, int endSize) {
        builder.push(localisedName + " Config");
        builder.push("Overworld");
        OVERWORLD_ACTIVE = builder.comment("Activate/Deactivate the Ore Gen in The Overworld [Default: true]")
                .translation(localisedName + ".config.state")
                .worldRestart()
                .define("generate_in_the_overworld", true);
        SIZE = builder.comment(String.format("Configure the ore Vein Size [Default: %d]", size))
                .translation(localisedName + ".config.vein_size")
                .worldRestart()
                .defineInRange("vein_size", size, 0, 64);
        COUNT = builder.comment(String.format("Average Ores per Chunk [Default: %d]", count))
                .translation(localisedName + ".config.count_per_chunk")
                .worldRestart()
                .defineInRange("count", count, 0, 64);
        BASELINE = builder.comment(String.format("Baseline Y-Level [Default: %d]", baseline))
                .translation(localisedName + ".config.baseline")
                .worldRestart()
                .defineInRange("baseline", baseline, 0, 256);
        SPREAD = builder.comment(String.format("Spread Amount (# of Y-Levels above and below the Baseline) [Default: %d]", spread))
                .translation(localisedName + ".config.spread")
                .worldRestart()
                .defineInRange("spread", spread, 0, 256);
        builder.pop();
        builder.push("The Nether");
        NETHER_ACTIVE = builder.comment("Activate/Deactivate the Ore Gen in The Nether [Default: false]")
                .translation(localisedName + ".config.nether_state")
                .worldRestart()
                .define("generate_in_the_nether", false);
        NETHER_BASE = builder.comment(String.format("Baseline Y-Level [Default: %d]", netherBase))
                .translation(localisedName + ".config.nether_base")
                .worldRestart()
                .defineInRange("nether_base", netherBase, 0, 128);
        NETHER_SPREAD = builder.comment(String.format("Spread Amount (# of Y-Levels above and below the Baseline) [Default: %d]", netherSpread))
                .translation(localisedName + ".config.nether_spread")
                .worldRestart()
                .defineInRange("nether_spread", netherSpread, 0, 128);
        NETHER_COUNT = builder.comment(String.format("Average Ores per Chunk [Default: %d]", netherCount))
                .translation(localisedName + ".config.nether_count")
                .worldRestart()
                .defineInRange("nether_count", netherCount, 0, 64);
        NETHER_SIZE = builder.comment(String.format("Ore Vein Size [Default: %d]", netherSize))
                .translation(localisedName + ".config.nether_size")
                .worldRestart()
                .defineInRange("nether_size", netherSize, 0, 64);
        builder.pop();
        builder.push("The End");
        END_ACTIVE = builder.comment("Activate/Deactivate the Ore Gen in The End [Default: false]")
                .translation(localisedName + ".config.end_state")
                .worldRestart()
                .define("generate_in_the_end", false);
        END_BASE = builder.comment(String.format("Baseline Y-Level [Default: %d]", endBase))
                .translation(localisedName + ".config.end_base")
                .worldRestart()
                .defineInRange("end_base", endBase, 0, 256);
        END_SPREAD = builder.comment(String.format("Spread Amount (# of Y-Levels above and below the Baseline) [Default: %d]", endSpread))
                .translation(localisedName + ".config.end_spread")
                .worldRestart()
                .defineInRange("end_spread", endSpread, 0, 256);
        END_COUNT = builder.comment(String.format("Average Ores per Chunk [Default: %d]", endCount))
                .translation(localisedName + ".config.end_count")
                .worldRestart()
                .defineInRange("end_count", endCount, 0, 64);
        END_SIZE = builder.comment(String.format("Ore Vein Size [Default: %d]", endSize))
                .translation(localisedName + ".config.end_size")
                .worldRestart()
                .defineInRange("end_size", endSize, 0, 64);
        builder.pop();
        builder.pop();
      }

    }
  }

  public static class StrataConfigs {
    private final Object2ObjectMap<Strata, BooleanValue> configMap = new Object2ObjectOpenHashMap<>();
    private final boolean configured;

    private final Object2BooleanMap<Strata> bakedMap = new Object2BooleanOpenHashMap<>();

    public StrataConfigs(ForgeConfigSpec.Builder builder) {

      builder.comment("World Gen - Ore Generation on Strata Stone Type");

      for (Strata stratum : Strata.values()) {
        addStone(stratum, builder, stratum.state);
      }

      configured = true;
    }

    public void addStone(Strata stratum, ForgeConfigSpec.Builder builder, boolean state) {
      builder.push("Stratum Config: " + stratum.id);
      configMap.put(stratum,
              builder.translation(String.format("strata.config.%s", stratum.id))
                      .worldRestart()
                      .define("active", state));
      builder.pop();
    }

    private void bake() {
      if (!configured) {
        EmendatusEnigmatica.LOGGER.warn("Strata properties have not been configured yet!");
        return;
      }
      bakedMap.clear();
      configMap.forEach((s, p) -> bakedMap.put(s, p.get().booleanValue()));
    }

    public boolean get(Strata id) {
      return bakedMap.getBoolean(id);
    }
  }

  public static final ForgeConfigSpec COMMON_SPEC;
  public static final Common COMMON;

  static {
    final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
    COMMON_SPEC = specPair.getRight();
    COMMON = specPair.getLeft();
  }

  @SubscribeEvent
  public static void onModConfigEvent(final ModConfig.ModConfigEvent event) {

  }

  @SubscribeEvent
  public static void onLoad(final ModConfig.Loading event) {
    final ModConfig config = event.getConfig();
    // Rebake the configs when they change
    if (config.getSpec() == COMMON_SPEC) {
      COMMON.bake();
      EmendatusEnigmatica.LOGGER.debug("Baked common configs on Load");
      // (re-)make ore features on config load
      WorldGenHandler.oreFeatures();
    }
  }

  @SubscribeEvent
  public static void onReload(final ModConfig.Reloading event) {
    /*final ModConfig config = event.getConfig();
    // Rebake the configs when they change
    if (config.getSpec() == COMMON_SPEC) {
      COMMON.bake();
      EmendatusEnigmatica.LOGGER.debug("Baked common configs on Load");
      // (re-)make ore features on config load
      WorldGenHandler.oreFeatures();
    }*/
  }
}