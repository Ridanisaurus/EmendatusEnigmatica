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

          addValue(material, builder, material.defaultSize, material.defaultCount, material.defaultBaseline, material.defaultSpread, material.netherBaseMod, material.netherSpreadMod, material.netherCountMod, material.netherSizeMod, material.endBaseMod, material.endSpreadMod,material.endCountMod, material.endSizeMod);

        }
      }

      configured = true;
    }

    void addValue(Materials material, ForgeConfigSpec.Builder builder, int size, int count, int baseline, int spread, int netherBaseMod, int netherSpreadMod, int netherCountMod, int netherSizeMod, int endBaseMod, int endSpreadMod, int endCountMod, int endSizeMod) {
      addValue(material, new Properties(material.localisedName, builder, size, count, baseline, spread, netherBaseMod, netherSpreadMod, netherCountMod, netherSizeMod, endBaseMod, endSpreadMod, endCountMod, endSizeMod));
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
      public final int VEIN_SIZE;
      public final int COUNT_PER_CHUNK;
      public final int BASELINE_Y;
      public final int SPREAD_AMOUNT;
      public final boolean NETHER_ACTIVE;
      public final int NETHER_BASE_MODIFIER;
      public final int NETHER_SPREAD_MODIFIER;
      public final int NETHER_COUNT_MODIFIER;
      public final int NETHER_SIZE_MODIFIER;
      public final boolean END_ACTIVE;
      public final int END_BASE_MODIFIER;
      public final int END_SPREAD_MODIFIER;
      public final int END_COUNT_MODIFIER;
      public final int END_SIZE_MODIFIER;

      BakedOreProps(Properties properties) {
        OVERWORLD_ACTIVE = properties.OVERWORLD_ACTIVE.get();
        VEIN_SIZE = properties.VEIN_SIZE.get();
        COUNT_PER_CHUNK = properties.COUNT_PER_CHUNK.get();
        BASELINE_Y = properties.BASELINE_Y.get();
        SPREAD_AMOUNT = properties.SPREAD_AMOUNT.get();
        NETHER_ACTIVE = properties.NETHER_ACTIVE.get();
        NETHER_BASE_MODIFIER = properties.NETHER_BASE_MODIFIER.get();
        NETHER_SPREAD_MODIFIER = properties.NETHER_SPREAD_MODIFIER.get();
        NETHER_COUNT_MODIFIER = properties.NETHER_COUNT_MODIFIER.get();
        NETHER_SIZE_MODIFIER = properties.NETHER_SIZE_MODIFIER.get();
        END_ACTIVE = properties.END_ACTIVE.get();
        END_BASE_MODIFIER = properties.END_BASE_MODIFIER.get();
        END_SPREAD_MODIFIER = properties.END_SPREAD_MODIFIER.get();
        END_COUNT_MODIFIER = properties.END_COUNT_MODIFIER.get();
        END_SIZE_MODIFIER = properties.END_SIZE_MODIFIER.get();
      }
    }

    public static class Properties {
      public final BooleanValue OVERWORLD_ACTIVE;
      public final IntValue VEIN_SIZE;
      public final IntValue COUNT_PER_CHUNK;
      public final IntValue BASELINE_Y;
      public final IntValue SPREAD_AMOUNT;
      public final BooleanValue NETHER_ACTIVE;
      public final IntValue NETHER_BASE_MODIFIER;
      public final IntValue NETHER_SPREAD_MODIFIER;
      public final IntValue NETHER_COUNT_MODIFIER;
      public final IntValue NETHER_SIZE_MODIFIER;
      public final BooleanValue END_ACTIVE;
      public final IntValue END_BASE_MODIFIER;
      public final IntValue END_SPREAD_MODIFIER;
      public final IntValue END_COUNT_MODIFIER;
      public final IntValue END_SIZE_MODIFIER;

      public Properties(String localisedName, ForgeConfigSpec.Builder builder, int size, int count, int baseline, int spread, int netherBaseMod, int netherSpreadMod, int netherCountMod, int netherSizeMod, int endBaseMod, int endSpreadMod, int endCountMod, int endSizeMod) {
        builder.push(localisedName + " Config");
        builder.push("Overworld");
        OVERWORLD_ACTIVE = builder.comment("Activate/Deactivate the Ore Gen in The Overworld [Default: true]")
                .translation(localisedName + ".config.state")
                .worldRestart()
                .define("generate_in_the_overworld", true);
        VEIN_SIZE = builder.comment(String.format("Configure the ore Vein Size [Default: %d]", size))
                .translation(localisedName + ".config.vein_size")
                .worldRestart()
                .defineInRange("vein_size", size, 0, 64);
        COUNT_PER_CHUNK = builder.comment(String.format("Average Ores per Chunk [Range: 0 to 64, Default: %d]", count))
                .translation(localisedName + ".config.count_per_chunk")
                .worldRestart()
                .defineInRange("count", count, 0, 64);
        BASELINE_Y = builder.comment(String.format("Baseline Y-Level [Range: 0 to 256, Default: %d]", baseline))
                .translation(localisedName + ".config.baseline")
                .worldRestart()
                .defineInRange("baseline", baseline, 0, 256);
        SPREAD_AMOUNT = builder.comment(String.format("Spread Amount (# of Y-Levels above and below the Baseline) [Range: 0 to 256, Default: %d]", spread))
                .translation(localisedName + ".config.spread")
                .worldRestart()
                .defineInRange("spread", spread, 0, 256);
        builder.pop();
        builder.comment("Ores in The Nether use the same Values of the Overworld +/- the Modifiers").push("The Nether");
        NETHER_ACTIVE = builder.comment("Activate/Deactivate the Ore Gen in The Nether [Default: true]")
                .translation(localisedName + ".config.nether_state")
                .worldRestart()
                .define("generate_in_the_nether", true);
        NETHER_BASE_MODIFIER = builder.comment(String.format("Baseline Modifier [Range: -256 to 256, Default: %d]", netherBaseMod))
                .translation(localisedName + ".config.nether_base_modifier")
                .worldRestart()
                .defineInRange("nether_base_modifier", netherBaseMod, -256, 256);
        NETHER_SPREAD_MODIFIER = builder.comment(String.format("Spread Modifier [Range: -256 to 256, Default: %d]", netherSpreadMod))
                .translation(localisedName + ".config.nether_spread_modifier")
                .worldRestart()
                .defineInRange("nether_spread_modifier", netherSpreadMod, -256, 256);
        NETHER_COUNT_MODIFIER = builder.comment(String.format("Average Ores per Chunk Modifier [Range: -256 to 256, Default: %d]", netherCountMod))
                .translation(localisedName + ".config.nether_count_modifier")
                .worldRestart()
                .defineInRange("nether_count_modifier", netherCountMod, -256, 256);
        NETHER_SIZE_MODIFIER = builder.comment(String.format("Ore Vein Size Modifier [Range: -256 to 256, Default: %d]", netherSizeMod))
                .translation(localisedName + ".config.nether_size_modifier")
                .worldRestart()
                .defineInRange("nether_size_modifier", netherSizeMod, -256, 256);
        builder.pop();
        builder.comment("Ores in The End use the same Values of the Overworld +/- the Modifiers").push("The End");
        END_ACTIVE = builder.comment("Activate/Deactivate the Ore Gen in The End [Default: true]")
                .translation(localisedName + ".config.end_state")
                .worldRestart()
                .define("generate_in_the_end", true);
        END_BASE_MODIFIER = builder.comment(String.format("Baseline Modifier [Range: -256 to 256, Default: %d]", endBaseMod))
                .translation(localisedName + ".config.end_base_modifier")
                .worldRestart()
                .defineInRange("end_base_modifier", endBaseMod, -256, 256);
        END_SPREAD_MODIFIER = builder.comment(String.format("Spread Modifier [Range: -256 to 256, Default: %d]", endSpreadMod))
                .translation(localisedName + ".config.end_spread_modifier")
                .worldRestart()
                .defineInRange("end_spread_modifier", endSpreadMod, -256, 256);
        END_COUNT_MODIFIER = builder.comment(String.format("Average Ores per Chunk Modifier [Range: -256 to 256, Default: %d]", endCountMod))
                .translation(localisedName + ".config.end_count_modifier")
                .worldRestart()
                .defineInRange("end_count_modifier", endCountMod, -256, 256);
        END_SIZE_MODIFIER = builder.comment(String.format("Ore Vein Size Modifier [Range: -256 to 256, Default: %d]", endSizeMod))
                .translation(localisedName + ".config.end_size_modifier")
                .worldRestart()
                .defineInRange("end_size_modifier", endSizeMod, -256, 256);
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