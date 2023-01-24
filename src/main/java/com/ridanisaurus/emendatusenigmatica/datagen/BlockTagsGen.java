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

package com.ridanisaurus.emendatusenigmatica.datagen;

import com.google.common.collect.Lists;
import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.datagen.base.EETagProvider;
import com.ridanisaurus.emendatusenigmatica.datagen.base.IFinishedGenericJSON;
import com.ridanisaurus.emendatusenigmatica.datagen.base.TagBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;

import java.util.*;
import java.util.function.Consumer;

public class BlockTagsGen extends EETagProvider {
	private final EmendatusDataRegistry registry;

	public BlockTagsGen(DataGenerator gen, EmendatusDataRegistry registry) {
		super(gen);
		this.registry = registry;
	}

	private final List<String> forgeBlocks = Lists.newArrayList();
	private final List<String> forgeOres = Lists.newArrayList();
	private final Map<String, List<String>> oresPerMaterial = new HashMap<>();
	private final Map<String, List<String>> oresInGround = new HashMap<>();

	@Override
	protected void buildTags(Consumer<IFinishedGenericJSON> consumer) {
		for (MaterialModel material : registry.getMaterials()) {
			List<String> processedType = material.getProcessedTypes();
			// Storage Blocks
			if (processedType.contains("storage_block")) {
				ResourceLocation block = EERegistrar.storageBlockMap.get(material.getId()).getId();
				if (!forgeBlocks.contains("#forge:storage_blocks/" + material.getId())) forgeBlocks.add("#forge:storage_blocks/" + material.getId());
				new TagBuilder().tag(block.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/blocks/storage_blocks/" + material.getId()));
			}
			// Raw Materials
			if (processedType.contains("raw")) {
				ResourceLocation raw = EERegistrar.rawBlockMap.get(material.getId()).getId();
				if (!forgeBlocks.contains("#forge:storage_blocks/raw_" + material.getId())) forgeBlocks.add("#forge:storage_blocks/raw_" + material.getId());
				new TagBuilder().tag(raw.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/blocks/storage_blocks/raw_" + material.getId()));
			}
			// Ores
			for (StrataModel stratum : registry.getStrata()) {
				if (processedType.contains("ore")) {
					ResourceLocation ore = EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).getId();
					if (!forgeOres.contains("#forge:ores/" + material.getId())) forgeOres.add("#forge:ores/" + material.getId());
					oresPerMaterial.computeIfAbsent(material.getId(), s -> new ArrayList<>()).add(ore.toString());
					oresInGround.computeIfAbsent(stratum.getSuffix(), s -> new ArrayList<>()).add(ore.toString());
				}
				if (processedType.contains("ore") && stratum.getSampleStrata()) {
					ResourceLocation ore = EERegistrar.oreSampleBlockTable.get(stratum.getId(), material.getId()).getId();
					if (!forgeOres.contains("#forge:ores/" + material.getId())) forgeOres.add("#forge:ores/" + material.getId());
					oresPerMaterial.computeIfAbsent(material.getId(), s -> new ArrayList<>()).add(ore.toString());
					oresInGround.computeIfAbsent(stratum.getSuffix(), s -> new ArrayList<>()).add(ore.toString());
				}
			}

		}
		oresPerMaterial.forEach((material, oreList) -> new TagBuilder().tags(oreList).save(consumer, new ResourceLocation(Reference.FORGE, "/blocks/ores/" + material)));
		oresInGround.forEach((strataPrefix, oreType) -> new TagBuilder().tags(oreType).save(consumer, new ResourceLocation(Reference.FORGE, "/blocks/ores_in_ground/" + strataPrefix)));

		new TagBuilder().tags(forgeBlocks).save(consumer, new ResourceLocation(Reference.FORGE, "/blocks/storage_blocks"));
		new TagBuilder().tags(forgeOres).save(consumer, new ResourceLocation(Reference.FORGE, "/blocks/ores"));
	}

	@Override
	public String getName() {
		return "Emendatus Enigmatica Block Tags";
	}
}