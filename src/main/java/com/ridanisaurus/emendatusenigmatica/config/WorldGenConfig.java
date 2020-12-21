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
          addValue(material, builder, material.defaultSize, material.defaultCount, material.defaultBaseline, material.defaultSpread);
        }
      }

      configured = true;
    }

    void addValue(Materials material, ForgeConfigSpec.Builder builder, int size, int count, int baseline, int spread) {
      addValue(material, new Properties(material.id, builder, size, count, baseline, spread));
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
      public final boolean ACTIVE;
      public final int VEIN_SIZE;
      public final int COUNT_PER_CHUNK;
      public final int BASELINE_Y;
      public final int SPREAD_AMOUNT;

      BakedOreProps(Properties properties) {
        ACTIVE = properties.ACTIVE.get();
        VEIN_SIZE = properties.VEIN_SIZE.get();
        COUNT_PER_CHUNK = properties.COUNT_PER_CHUNK.get();
        BASELINE_Y = properties.BASELINE_Y.get();
        SPREAD_AMOUNT = properties.SPREAD_AMOUNT.get();
      }
    }

    public static class Properties {
      public final BooleanValue ACTIVE;
      public final IntValue VEIN_SIZE;
      public final IntValue COUNT_PER_CHUNK;
      public final IntValue BASELINE_Y;
      public final IntValue SPREAD_AMOUNT;

      public Properties(String id, ForgeConfigSpec.Builder builder, int size, int count, int baseline, int spread) {
        builder.push("Ore Config: " + id);
        ACTIVE = builder.comment("Activate/Deactivate the World Gen [Default: true]")
                .translation(id + ".config.state")
                .worldRestart()
                .define("active", true);
        VEIN_SIZE = builder.comment(String.format("Configure the ore Vein Size [Default: %d]", size))
                .translation(id + ".config.vein_size")
                .worldRestart()
                .defineInRange("vein_size", size, 0, 64);
        COUNT_PER_CHUNK = builder.comment(String.format("Average ores per Chunk [Range: 0-64, Default: %d]", count))
                .translation(id + ".config.count_per_chunk")
                .worldRestart()
                .defineInRange("count", count, 0, 64);
        BASELINE_Y = builder.comment(String.format("Baseline Y-Level [Range: 0-256, Default: %d]", baseline))
                .translation(id + ".config.bottom_offset")
                .worldRestart()
                .defineInRange("baseline", baseline, 0, 256);
        SPREAD_AMOUNT = builder.comment(String.format("Spread Amount (# of Y-Levels above and below the baseline) [Range: 0-256, Default: %d]", spread))
                .translation(id + ".config.maximum_height")
                .worldRestart()
                .defineInRange("spread", spread, 0, 256);
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
        addStone(stratum, builder);
      }

      configured = true;
    }

    public void addStone(Strata stratum, ForgeConfigSpec.Builder builder) {
      builder.push("Stratum Config: " + stratum.id);
      configMap.put(stratum,
              builder.translation(String.format("strata.config.%s", stratum.id))
                      .worldRestart()
                      .define("active", true));
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