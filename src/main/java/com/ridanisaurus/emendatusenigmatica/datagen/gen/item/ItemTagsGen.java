/*
 * MIT License
 *
 * Copyright (c) 2024. Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.datagen.gen.item;

import com.ridanisaurus.emendatusenigmatica.plugin.DataRegistry;
import com.ridanisaurus.emendatusenigmatica.datagen.IFinishedGenericJSON;
import com.ridanisaurus.emendatusenigmatica.datagen.builder.TagBuilder;
import com.ridanisaurus.emendatusenigmatica.datagen.provider.EETagProvider;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.data.EEItemMap;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static com.ridanisaurus.emendatusenigmatica.util.Reference.*;

public class ItemTagsGen extends EETagProvider {
    private final DataRegistry registry;
    private final Map<ResourceLocation, List<String>> tags = new HashMap<>();

    public ItemTagsGen(DataGenerator gen, DataRegistry registry) {
        super(gen);
        this.registry = registry;
    }

    @Override
    protected void buildTags(Consumer<IFinishedGenericJSON> consumer) {
        registry.getRegisteredMaterials().forEach(material -> {
            var types = material.getProcessedTypes();
            resourceTags(material, types);
            toolTags(material, types);
            armorTags(material, types);
        });

        tags.forEach((tag, values) -> new TagBuilder(values).save(consumer, tag));
        tags.clear();
    }

    private void resourceTags(@NotNull MaterialModel material, @NotNull List<String> types) {
        if (types.contains("storage_block")) addMaterialSpecificTag(COMMON, "storage_blocks", EERegistrar.storageBlockItemMap, material);
        if (types.contains("ingot"))         addMaterialSpecificTag(COMMON, "ingots", EERegistrar.ingotMap, material);
        if (types.contains("gem"))           addMaterialSpecificTag(COMMON, "gems", EERegistrar.gemMap, material);
        if (types.contains("nugget"))        addMaterialSpecificTag(COMMON, "nuggets", EERegistrar.nuggetMap, material);
        if (types.contains("dust"))          addMaterialSpecificTag(COMMON, "dusts", EERegistrar.dustMap, material);
        if (types.contains("plate"))         addMaterialSpecificTag(COMMON, "plates", EERegistrar.plateMap, material);
        if (types.contains("gear"))          addMaterialSpecificTag(COMMON, "gears", EERegistrar.gearMap, material);
        if (types.contains("rod"))           addMaterialSpecificTag(COMMON, "rods", EERegistrar.rodMap, material);
        if (types.contains("fluid"))         addMaterialSpecificTag(COMMON, "buckets", EERegistrar.fluidBucketMap, material);

        if (types.contains("raw")) {
            addMaterialSpecificTag(COMMON, "raw_materials", EERegistrar.rawMap, material);
            if (types.contains("storage_block"))
                addMaterialSpecificTag(
                    COMMON,
                    "storage_blocks",
                    EERegistrar.rawBlockItemMap.getIdAsString(material),
                    "raw_" + material.getId()
                );
        }

        if (types.contains("cluster")) {
            addTag(COMMON, "buds", EERegistrar.smallBudBlockItemMap.getIdAsString(material));
            addTag(COMMON, "buds", EERegistrar.mediumBudBlockItemMap.getIdAsString(material));
            addTag(COMMON, "buds", EERegistrar.largeBudBlockItemMap.getIdAsString(material));
            addTag(COMMON, "clusters", EERegistrar.clusterBlockItemMap.getIdAsString(material));
            addTag(COMMON, "budding_blocks", EERegistrar.buddingBlockItemMap.getIdAsString(material));
            addMaterialSpecificTag(COMMON, "storage_blocks", EERegistrar.clusterShardBlockItemMap, material);
            addMaterialSpecificTag(COMMON, "gems", EERegistrar.clusterShardMap, material);
        }

        // Ores
        if (!types.contains("ore")) return;
        List<String> strataList = material.getStrata();
        for (StrataModel strata : registry.getRegisteredStrata()) {
            if (!strataList.isEmpty() && !strataList.contains(strata.getId())) continue;
            String id = EERegistrar.oreBlockItemTable.get(strata.getId(), material.getId()).getId().toString();
            addMaterialSpecificTag(COMMON, "ores", id, material);
            addTag(COMMON, "ores_in_ground/" + strata.getSuffix(), id);

            //TODO: Rework Sample System.
//			if (processedType.contains("sample")) {
//				if (material.getStrata().isEmpty() || material.getStrata().contains(strata.getId())) {
//					String sample = EERegistrar.oreSampleBlockItemTable.getIdAsString()(strata.getId(), material.getId()).getId();
//					if (!ores.contains(C_TAG + ":ores/" + material.getId())) ores.add(C_TAG + ":ores/" + material.getId());
//					oresPerMaterial.computeIfAbsent(material.getId(), s -> new ArrayList<>()).add(sample.toString();
//					oresInGround.computeIfAbsent(strata.getSuffix(), s -> new ArrayList<>()).add(sample.toString();
//				}
//			}
        }
    }

    private void armorTags(@NotNull MaterialModel material, @NotNull List<String> types) {
        if (types.contains("armor")) {
            addTag(MINECRAFT, "head_armor", EERegistrar.helmetMap.getIdAsString(material));
            addTag(MINECRAFT, "chest_armor", EERegistrar.chestplateMap.getIdAsString(material));
            addTag(MINECRAFT, "leg_armor", EERegistrar.leggingsMap.getIdAsString(material));
            addTag(MINECRAFT, "foot_armor", EERegistrar.bootsMap.getIdAsString(material));
        }
    }

    private void toolTags(@NotNull MaterialModel material, @NotNull List<String> types) {
        if (types.contains("shovel")) addTag(MINECRAFT, "shovels", EERegistrar.shovelMap.getIdAsString(material));
        if (types.contains("hoe")) addTag(MINECRAFT, "hoes", EERegistrar.hoeMap.getIdAsString(material));

        if (types.contains("sword")) {
            String id = EERegistrar.swordMap.getIdAsString(material);
            addTag(MINECRAFT, "swords", id);
            addTag(COMMON, "tools/melee_weapon", id);
        }

        if (types.contains("axe")) {
            String id = EERegistrar.axeMap.getIdAsString(material);
            addTag(MINECRAFT, "axes", id);
            addTag(COMMON, "tools/melee_weapon", id);
        }

        if (types.contains("pickaxe")) {
            String id = EERegistrar.pickaxeMap.getIdAsString(material);
            addTag(MINECRAFT, "pickaxes", id);
            addTag(MINECRAFT, "cluster_max_harvestables", id);
            addTag(COMMON, "tools/mining_tool", id);
        }

        if (types.contains("paxel")) {
            String id = EERegistrar.paxelMap.getIdAsString(material);
            addTag(MINECRAFT, "axes", id);
            addTag(MINECRAFT, "pickaxes", id);
            addTag(MINECRAFT, "cluster_max_harvestables", id);
            addTag(COMMON, "tools/melee_weapon", id);
            addTag(COMMON, "tools/mining_tool", id);
        }

        if (types.contains("shield")) {
            String id = EERegistrar.shieldMap.getIdAsString(material);
            addTag(MINECRAFT, "enchantable/durability", id);
            addTag(COMMON, "tools/shield", id);
        }
    }

    private void addMaterialSpecificTag(@NotNull String namespace, @NotNull String tag, @NotNull String itemId, @NotNull MaterialModel material) {
        addMaterialSpecificTag(namespace, tag, itemId, material.getId());
    }

    private void addMaterialSpecificTag(@NotNull String namespace, @NotNull String tag, @NotNull EEItemMap<?> registry, @NotNull MaterialModel material) {
        addMaterialSpecificTag(namespace, tag, registry.getIdAsString(material), material.getId());
    }

    private void addMaterialSpecificTag(@NotNull String namespace, @NotNull String tag, @NotNull String itemId, @NotNull String material) {
        addTag(namespace, tag, itemId);
        addTag(namespace, tag + "/" + material, itemId);
    }

    private void addTag(@NotNull String namespace, @NotNull String tag, @NotNull String itemId) {
        tags.computeIfAbsent(ResourceLocation.fromNamespaceAndPath(namespace, "item/" + tag), (resourceLocation -> new ArrayList<>())).add(itemId);
    }

    @Override
    public @NotNull String getName() {
        return "Emendatus Enigmatica: Item Tags";
    }
}
