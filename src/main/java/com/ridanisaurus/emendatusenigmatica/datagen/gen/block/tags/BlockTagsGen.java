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

package com.ridanisaurus.emendatusenigmatica.datagen.gen.block.tags;

import com.ridanisaurus.emendatusenigmatica.plugin.DataRegistry;
import com.ridanisaurus.emendatusenigmatica.datagen.IFinishedGenericJSON;
import com.ridanisaurus.emendatusenigmatica.datagen.builder.TagBuilder;
import com.ridanisaurus.emendatusenigmatica.datagen.provider.EETagProvider;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.data.EEBlockMap;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static com.ridanisaurus.emendatusenigmatica.util.Reference.*;

public class BlockTagsGen extends EETagProvider {
	private final DataRegistry registry;
	private final Map<ResourceLocation, List<String>> tags = new HashMap<>();

	public BlockTagsGen(DataGenerator gen, DataRegistry registry) {
		super(gen);
		this.registry = registry;
	}

	@Override
	protected void buildTags(Consumer<IFinishedGenericJSON> consumer) {
		for (MaterialModel material : registry.getRegisteredMaterials()) {
			List<String> types = material.getProcessedTypes();

			if (types.contains("storage_block")) {
				addMaterialSpecificTag(COMMON, "storage_blocks", EERegistrar.storageBlockMap, material);
				if (types.contains("raw"))
					addMaterialSpecificTag(COMMON, "storage_blocks", EERegistrar.rawBlockMap.getIdAsString(material), "raw_" + material.getId());
			}

			// Buds
			if (types.contains("cluster")) {
				addTag(COMMON, "buds", EERegistrar.smallBudBlockMap.getIdAsString(material));
				addTag(COMMON, "buds", EERegistrar.mediumBudBlockMap.getIdAsString(material));
				addTag(COMMON, "buds", EERegistrar.largeBudBlockMap.getIdAsString(material));
				addTag(COMMON, "clusters", EERegistrar.clusterBlockMap.getIdAsString(material));
				addTag(COMMON, "budding_blocks", EERegistrar.buddingBlockMap.getIdAsString(material));
				addMaterialSpecificTag(COMMON, "storage_blocks", EERegistrar.clusterShardBlockMap, material);

				addTag(MINECRAFT, "inside_step_sound_blocks", EERegistrar.smallBudBlockMap.getIdAsString(material));
				addTag(MINECRAFT, "crystal_sound_blocks", EERegistrar.buddingBlockMap.getIdAsString(material));
				addTag(MINECRAFT, "crystal_sound_blocks", EERegistrar.clusterShardBlockMap.getIdAsString(material));
			}

			// Ores
			if (!types.contains("ore")) continue;
			List<String> strataList = material.getStrata();
			for (StrataModel strata : registry.getRegisteredStrata()) {
				if (!strataList.isEmpty() && !strataList.contains(strata.getId())) continue;
				String id = EERegistrar.oreBlockItemTable.get(strata.getId(), material.getId()).getId().toString();
				addMaterialSpecificTag(COMMON, "ores", id, material);
				addTag(COMMON, "ores_in_ground/" + strata.getSuffix(), id);

				//TODO: Rework Sample System.
//				if (types.contains("sample")) {
//					if (material.getStrata().isEmpty() || material.getStrata().contains(strata.getId())) {
//						ResourceLocation sample = EERegistrar.oreSampleBlockTable.get(strata.getId(), material.getId()).getId();
//						if (!forgeOres.contains(C_TAG + ":ores/" + material.getId())) forgeOres.add(C_TAG + ":ores/" + material.getId());
//						oresPerMaterial.computeIfAbsent(material.getId(), s -> new ArrayList<>()).add(sample.toString());
//						oresInGround.computeIfAbsent(strata.getSuffix(), s -> new ArrayList<>()).add(sample.toString());
//					}
//				}
			}
		}

		tags.forEach((tag, values) -> new TagBuilder(values).save(consumer, tag));
		tags.clear();
	}

	private void addMaterialSpecificTag(@NotNull String namespace, @NotNull String tag, @NotNull String blockId, @NotNull MaterialModel material) {
		addMaterialSpecificTag(namespace, tag, blockId, material.getId());
	}

	private void addMaterialSpecificTag(@NotNull String namespace, @NotNull String tag, @NotNull EEBlockMap<?> registry, @NotNull MaterialModel material) {
		addMaterialSpecificTag(namespace, tag, registry.getIdAsString(material), material.getId());
	}

	private void addMaterialSpecificTag(@NotNull String namespace, @NotNull String tag, @NotNull String blockId, @NotNull String material) {
		addTag(namespace, tag, blockId);
		addTag(namespace, tag + "/" + material, blockId);
	}

	private void addTag(@NotNull String namespace, @NotNull String tag, @NotNull String blockId) {
		tags.computeIfAbsent(ResourceLocation.fromNamespaceAndPath(namespace, "block/" + tag), (resourceLocation -> new ArrayList<>())).add(blockId);
	}

	@Override
	public @NotNull String getName() {
		return "Emendatus Enigmatica: Block Tags";
	}
}