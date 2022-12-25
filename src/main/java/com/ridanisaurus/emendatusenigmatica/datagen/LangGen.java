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

import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.datagen.base.EELangProvider;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;

import java.util.List;

public class LangGen extends EELangProvider {
	private final EmendatusDataRegistry registry;

	public LangGen(DataGenerator gen, EmendatusDataRegistry registry) {
		super(gen, Reference.MOD_ID, "en_us");
		this.registry = registry;
	}

	@Override
	protected void addTranslations() {
		// Item Group
		add("itemGroup." + Reference.MOD_ID, Reference.MOD_NAME);

		add(EERegistrar.ENIGMATIC_HAMMER.get(), "Enigmatic Hammer");
		add(EERegistrar.FELINIUM_JAMINITE.get(), "Felinium Jaminite Ingot");

		for (MaterialModel material : registry.getMaterials()) {
			List<String> processedType = material.getProcessedTypes();
			// Storage Blocks
			if (processedType.contains("storage_block")) {
				StringBuilder sb = new StringBuilder();
				sb.append("Block of ");
				sb.append(material.getLocalizedName());
				add(EERegistrar.storageBlockMap.get(material.getId()).get(), sb.toString());
			}

			// Ingots
			if (processedType.contains("ingot")) {
				StringBuilder sb = new StringBuilder();
				sb.append(material.getLocalizedName());
				sb.append(" Ingot");
				add(EERegistrar.ingotMap.get(material.getId()).get(), sb.toString());
			}

			// Gems
			if (processedType.contains("gem")) {
				StringBuilder sb = new StringBuilder();
				sb.append(material.getLocalizedName());
				sb.append(" Gem");
				add(EERegistrar.gemMap.get(material.getId()).get(), sb.toString());
			}

			// Nuggets
			if (processedType.contains("nugget")) {
				StringBuilder sb = new StringBuilder();
				sb.append(material.getLocalizedName());
				sb.append(" Nugget");
				add(EERegistrar.nuggetMap.get(material.getId()).get(), sb.toString());
			}

			// Dusts
			if (processedType.contains("dust")) {
				StringBuilder sb = new StringBuilder();
				sb.append(material.getLocalizedName());
				sb.append(" Dust");
				add(EERegistrar.dustMap.get(material.getId()).get(), sb.toString());
			}

			// Plates
			if (processedType.contains("plate")) {
				StringBuilder sb = new StringBuilder();
				sb.append(material.getLocalizedName());
				sb.append(" Plate");
				add(EERegistrar.plateMap.get(material.getId()).get(), sb.toString());
			}

			// Gears
			if (processedType.contains("gear")) {
				StringBuilder sb = new StringBuilder();
				sb.append(material.getLocalizedName());
				sb.append(" Gear");
				add(EERegistrar.gearMap.get(material.getId()).get(), sb.toString());
			}

			// Rods
			if (processedType.contains("rod")) {
				StringBuilder sb = new StringBuilder();
				sb.append(material.getLocalizedName());
				sb.append(" Rod");
				add(EERegistrar.rodMap.get(material.getId()).get(), sb.toString());
			}

			// Swords
			if (processedType.contains("sword")) {
				StringBuilder sb = new StringBuilder();
				sb.append(material.getLocalizedName());
				sb.append(" Sword");
				add(EERegistrar.swordMap.get(material.getId()).get(), sb.toString());
			}

			// Pickaxes
			if (processedType.contains("pickaxe")) {
				StringBuilder sb = new StringBuilder();
				sb.append(material.getLocalizedName());
				sb.append(" Pickaxe");
				add(EERegistrar.pickaxeMap.get(material.getId()).get(), sb.toString());
			}

			// Axes
			if (processedType.contains("axe")) {
				StringBuilder sb = new StringBuilder();
				sb.append(material.getLocalizedName());
				sb.append(" Axe");
				add(EERegistrar.axeMap.get(material.getId()).get(), sb.toString());
			}

			// Shovels
			if (processedType.contains("shovel")) {
				StringBuilder sb = new StringBuilder();
				sb.append(material.getLocalizedName());
				sb.append(" Shovel");
				add(EERegistrar.shovelMap.get(material.getId()).get(), sb.toString());
			}

			// Hoes
			if (processedType.contains("hoe")) {
				StringBuilder sb = new StringBuilder();
				sb.append(material.getLocalizedName());
				sb.append(" Hoe");
				add(EERegistrar.hoeMap.get(material.getId()).get(), sb.toString());
			}

			// Paxels
			if (processedType.contains("paxel")) {
				StringBuilder sb = new StringBuilder();
				sb.append(material.getLocalizedName());
				sb.append(" Paxel");
				add(EERegistrar.paxelMap.get(material.getId()).get(), sb.toString());
			}

			// Helmet
			if (processedType.contains("helmet")) {
				StringBuilder sb = new StringBuilder();
				sb.append(material.getLocalizedName());
				sb.append(" Helmet");
				add(EERegistrar.helmetMap.get(material.getId()).get(), sb.toString());
			}

			// Chestplate
			if (processedType.contains("chestplate")) {
				StringBuilder sb = new StringBuilder();
				sb.append(material.getLocalizedName());
				sb.append(" Chestplate");
				add(EERegistrar.chestplateMap.get(material.getId()).get(), sb.toString());
			}

			// Leggings
			if (processedType.contains("leggings")) {
				StringBuilder sb = new StringBuilder();
				sb.append(material.getLocalizedName());
				sb.append(" Leggings");
				add(EERegistrar.leggingsMap.get(material.getId()).get(), sb.toString());
			}

			// Boots
			if (processedType.contains("boots")) {
				StringBuilder sb = new StringBuilder();
				sb.append(material.getLocalizedName());
				sb.append(" Boots");
				add(EERegistrar.bootsMap.get(material.getId()).get(), sb.toString());
			}

			// Raw
			if (processedType.contains("raw")) {
				StringBuilder sb = new StringBuilder();
				sb.append("Raw ");
				sb.append(material.getLocalizedName());
				add(EERegistrar.rawMap.get(material.getId()).get(), sb.toString());

				StringBuilder sb2 = new StringBuilder();
				sb2.append("Block of Raw ");
				sb2.append(material.getLocalizedName());
				add(EERegistrar.rawBlockMap.get(material.getId()).get(), sb2.toString());
			}

			// Fluids
			if (processedType.contains("fluid")) {
				StringBuilder sb = new StringBuilder();
				sb.append(material.getLocalizedName());
				add(EERegistrar.fluidTypeMap.get(material.getId()).get().getDescriptionId(), sb.toString());

				StringBuilder sb2 = new StringBuilder();
				sb2.append(material.getLocalizedName());
				sb2.append(" Bucket");
				add(EERegistrar.fluidBucketMap.get(material.getId()).get(), sb2.toString());
			}

//			if (EmendatusEnigmatica.BLOODMAGIC_LOADED) {
//				// Fragment
//				if (processedType.contains("fragment")) {
//					StringBuilder sb = new StringBuilder();
//					sb.append(material.getLocalizedName());
//					sb.append(" Fragment");
//					add(EEBloodMagicRegistrar.fragmentMap.get(material.getId()).get(), sb.toString());
//				}
//				// Gravel
//				if (processedType.contains("gravel")) {
//					StringBuilder sb = new StringBuilder();
//					sb.append(material.getLocalizedName());
//					sb.append(" Gravel");
//					add(EEBloodMagicRegistrar.gravelMap.get(material.getId()).get(), sb.toString());
//				}
//			}

			for (StrataModel stratum : registry.getStrata()) {
				if (processedType.contains("ore")) {
					StringBuilder sb = new StringBuilder();
					sb.append(material.getLocalizedName());
					sb.append(" Ore");

					if (!stratum.getId().equals("minecraft_stone")) {
						sb.append(" - ");
						sb.append(stratum.getLocalizedName());
					}
					add(EERegistrar.oreBlockTable.get(stratum.getId(), material.getId()).get(), sb.toString());
				}
				if (processedType.contains("ore") && stratum.getSampleStrata()) {
					StringBuilder sb = new StringBuilder();
					sb.append(material.getLocalizedName());
					sb.append(" Rich ");
					sb.append(stratum.getLocalizedName());
					add(EERegistrar.oreSampleBlockTable.get(stratum.getId(), material.getId()).get(), sb.toString());
				}
			}
		}
	}

	@Override
	public String getName() {
		return "Emendatus Enigmatica Languages: en_us";
	}
}