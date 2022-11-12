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

import java.util.List;
import java.util.function.Consumer;

public class BlockTagsGen extends EETagProvider {
	public BlockTagsGen(DataGenerator gen) {
		super(gen);
	}

	private final List<String> forgeBlocks = Lists.newArrayList();
	private final List<String> forgeOres = Lists.newArrayList();
	private final List<String> beaconBlocks = Lists.newArrayList();

	@Override
	protected void buildTags(Consumer<IFinishedGenericJSON> consumer) {
		for (MaterialModel material : EELoader.MATERIALS) {
			List<String> processedType = material.getProcessedType();
			if (processedType.contains("storage_block")) {
				ResourceLocation block = EERegistrar.storageBlockMap.get(material.getId()).getId();
				forgeBlocks.add(block.toString());
				beaconBlocks.add(block.toString());
				new TagBuilder().tag(block.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/blocks/storage_blocks/" + material.getId()));
			}
			if (processedType.contains("raw")) {
				ResourceLocation raw = EERegistrar.rawBlockMap.get(material.getId()).getId();
				forgeBlocks.add(raw.toString());
				new TagBuilder().tag(raw.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/blocks/storage_blocks/raw_" + material.getId()));
			}
			for (StrataModel stratum : EELoader.STRATA) {
				if (processedType.contains("ore")) {
					ResourceLocation ore = EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).getId();
					forgeOres.add(ore.toString());
					new TagBuilder().tag(ore.toString()).save(consumer, new ResourceLocation(Reference.FORGE, "/blocks/ores/" + getModelName(stratum, material)));
				}
			}
		}
		new TagBuilder().tags(forgeBlocks).save(consumer, new ResourceLocation(Reference.FORGE, "/blocks/storage_blocks"));
		new TagBuilder().tags(beaconBlocks).save(consumer, new ResourceLocation(Reference.FORGE, "/blocks/beacon_base_blocks"));
		new TagBuilder().tags(forgeOres).save(consumer, new ResourceLocation(Reference.FORGE, "/blocks/ores"));
	}

	public static String getModelName(StrataModel stratum, MaterialModel material) {
		return material.getId() + (!stratum.getId().equals("minecraft_stone") ? "_" + stratum.getSuffix() : "") + "_ore";
	}

	@Override
	public String getName() {
		return "Emendatus Enigmatica Block Tags";
	}
}
