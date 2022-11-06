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

package com.ridanisaurus.emendatusenigmatica.datagen;

import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EEBloodMagicRegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EECreateRegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EEMekanismRegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class LangGen extends LanguageProvider {
	public LangGen(DataGenerator gen) {
		super(gen, Reference.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		// Item Group
		add("itemGroup." + Reference.MOD_ID, Reference.MOD_NAME);

		add(EERegistrar.ENIGMATIC_HAMMER.get(), "Enigmatic Hammer");

		for (MaterialModel material : EELoader.MATERIALS) {
			for (String processedType : material.getProcessedType()) {

				// Storage Blocks
				if (processedType.equals("storage_block")) {
					StringBuilder sb = new StringBuilder();
					sb.append("Block of ");
					sb.append(material.getLocalizedName());
					add(EERegistrar.storageBlockMap.get(material.getId()).get(), sb.toString());
				}

				// Ingots
				if (processedType.equals("ingot")) {
					StringBuilder sb = new StringBuilder();
					sb.append(material.getLocalizedName());
					sb.append(" Ingot");
					add(EERegistrar.ingotMap.get(material.getId()).get(), sb.toString());
				}

				// Gems
				if (processedType.equals("gem")) {
					StringBuilder sb = new StringBuilder();
					sb.append(material.getLocalizedName());
					sb.append(" Gem");
					add(EERegistrar.gemMap.get(material.getId()).get(), sb.toString());
				}

				// Nuggets
				if (processedType.equals("nugget")) {
					StringBuilder sb = new StringBuilder();
					sb.append(material.getLocalizedName());
					sb.append(" Nugget");
					add(EERegistrar.nuggetMap.get(material.getId()).get(), sb.toString());
				}

				// Dusts
				if (processedType.equals("dust")) {
					StringBuilder sb = new StringBuilder();
					sb.append(material.getLocalizedName());
					sb.append(" Dust");
					add(EERegistrar.dustMap.get(material.getId()).get(), sb.toString());
				}

				// Plates
				if (processedType.equals("plate")) {
					StringBuilder sb = new StringBuilder();
					sb.append(material.getLocalizedName());
					sb.append(" Plate");
					add(EERegistrar.plateMap.get(material.getId()).get(), sb.toString());
				}

				// Gears
				if (processedType.equals("gear")) {
					StringBuilder sb = new StringBuilder();
					sb.append(material.getLocalizedName());
					sb.append(" Gear");
					add(EERegistrar.gearMap.get(material.getId()).get(), sb.toString());
				}

				// Rods
				if (processedType.equals("rod")) {
					StringBuilder sb = new StringBuilder();
					sb.append(material.getLocalizedName());
					sb.append(" Rod");
					add(EERegistrar.rodMap.get(material.getId()).get(), sb.toString());
				}

				// Raw Chunks
				if (processedType.equals("raw")) {
					StringBuilder sb = new StringBuilder();
					sb.append("Raw ");
					sb.append(material.getLocalizedName());
					add(EERegistrar.rawMap.get(material.getId()).get(), sb.toString());
				}

				// Fluids
				if (processedType.equals("fluid")) {
					StringBuilder sb = new StringBuilder();
					sb.append("Molten ");
					sb.append(material.getLocalizedName());
					add(EERegistrar.fluidTypeMap.get(material.getId()).get().getDescriptionId(), sb.toString());

					StringBuilder sb2 = new StringBuilder();
					sb2.append("Bucket of Molten ");
					sb2.append(material.getLocalizedName());
					add(EERegistrar.fluidBucketMap.get(material.getId()).get(), sb2.toString());
				}

				if (EmendatusEnigmatica.MEKANISM_LOADED) {
					if (processedType.contains("slurry")) {
						StringBuilder sb = new StringBuilder();
						sb.append("Dirty ");
						sb.append(material.getLocalizedName());
						sb.append(" Slurry");
						add(EEMekanismRegistrar.dirtySlurryMap.get(material.getId()).get().getTranslationKey(), sb.toString());

						StringBuilder sb2 = new StringBuilder();
						sb2.append("Clean ");
						sb2.append(material.getLocalizedName());
						sb2.append(" Slurry");
						add(EEMekanismRegistrar.cleanSlurryMap.get(material.getId()).get().getTranslationKey(), sb2.toString());
					}
					// Crystals
					if (processedType.contains("crystal")) {
						StringBuilder sb = new StringBuilder();
						sb.append(material.getLocalizedName());
						sb.append(" Crystal");
						add(EEMekanismRegistrar.crystalMap.get(material.getId()).get(), sb.toString());
					}
					// Shards
					if (processedType.contains("shard")) {
						StringBuilder sb = new StringBuilder();
						sb.append(material.getLocalizedName());
						sb.append(" Shard");
						add(EEMekanismRegistrar.shardMap.get(material.getId()).get(), sb.toString());
					}
					// Clumps
					if (processedType.contains("clump")) {
						StringBuilder sb = new StringBuilder();
						sb.append(material.getLocalizedName());
						sb.append(" Clump");
						add(EEMekanismRegistrar.clumpMap.get(material.getId()).get(), sb.toString());
					}
					// Dirty Dusts
					if (processedType.contains("dirty_dust")) {
						StringBuilder sb = new StringBuilder();
						sb.append("Dirty ");
						sb.append(material.getLocalizedName());
						sb.append(" Dust");
						add(EEMekanismRegistrar.dirtyDustMap.get(material.getId()).get(), sb.toString());
					}
				}
				if (EmendatusEnigmatica.CREATE_LOADED) {
					// Crushed Ore
					if (processedType.contains("crushed_ore")) {
						StringBuilder sb = new StringBuilder();
						sb.append("Crushed ");
						sb.append(material.getLocalizedName());
						sb.append(" ore");
						add(EECreateRegistrar.crushedOreMap.get(material.getId()).get(), sb.toString());
					}
				}
				if (EmendatusEnigmatica.BLOODMAGIC_LOADED) {
					// Fragment
					if (processedType.contains("fragment")) {
						StringBuilder sb = new StringBuilder();
						sb.append(material.getLocalizedName());
						sb.append(" Fragment");
						add(EEBloodMagicRegistrar.fragmentMap.get(material.getId()).get(), sb.toString());
					}
					// Gravel
					if (processedType.contains("gravel")) {
						StringBuilder sb = new StringBuilder();
						sb.append(material.getLocalizedName());
						sb.append(" Gravel");
						add(EEBloodMagicRegistrar.gravelMap.get(material.getId()).get(), sb.toString());
					}
				}
			}
		}

		for (MaterialModel material : EELoader.MATERIALS) {
			for (StrataModel stratum : EELoader.STRATA) {
				if (material.getProcessedType().contains("ore")) {

					StringBuilder sb = new StringBuilder();
					sb.append(material.getLocalizedName());
					sb.append(" Ore");

					if (!stratum.getId().equals("minecraft_stone")) {
						sb.append(" - ");
						sb.append(stratum.getLocalizedName());
					}

					add(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get(), sb.toString());
				}
			}
		}
	}

	@Override
	public String getName() {
		return "Emendatus Enigmatica Languages: en_us";
	}


}