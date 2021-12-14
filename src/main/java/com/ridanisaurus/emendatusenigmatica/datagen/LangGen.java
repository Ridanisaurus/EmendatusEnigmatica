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
import mekanism.api.chemical.slurry.Slurry;
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

		// JEI Compat
		add("tooltip.emendatusenigmatica.ores.1", "Harvesting ores with Silktouch Enchanted tool will always drop a Chunk. The Vanilla Minecraft logic will apply when using non-Enchanted, or Fortune Enchanted tools.");
		add("tooltip.emendatusenigmatica.ores.2", "These Chunks act as an Ore Block in an Item form, and it can be smelted, doubled, tripled ..etc.");
		add("tooltip.emendatusenigmatica.ores.3", "Furthermore, these Chunks can be turned into their Stone Block Ore variant by using the Stonecutter or in a Crafting Grid.");

		// Misc
		add(EERegistrar.ENIGMATIC_FORTUNIZER.get(), "\u00A7aEnigmatic Fortunizer\u00A7r");
		add("container.emendatusenigmatica.enigmatic_fortunizer", "Enigmatic Fortunizer");
		add("tooltip.emendatusenigmatica.enigmatic_fortunizer.1", "The Enigmatic Fortunizer takes Gem-Based \u00A73\u00A7nChunks/Ores\u00A7r + a \u00A73\u00A7nPickaxe\u00A7r, and will output the appropriate \u00A79\u00A7nItem\u00A7r, applying enchantment effects where applicable such as \u00A7cFortune\u00A7r, \u00A7cUnbreaking\u00A7r, \u00A7cEfficiency\u00A7r... etc.\n" +
				"Once your \u00A73\u00A7nPickaxe\u00A7r reaches \u00A760\u00A7r Durability, the operation will stop, and your Pickaxe can be extracted for repairs.\n\n" +
				"\u00A76Example:\u00A7r Diamond Chunks + Pickaxe = Diamonds");
		add("tooltip.emendatusenigmatica.enigmatic_fortunizer.2", "Hold \u00A7c\u00A7l[SHIFT]\u00A7r for more information.");
		add(EERegistrar.ENIGMATIC_HAMMER.get(), "Enigmatic Hammer");

		for (MaterialModel material : EELoader.MATERIALS) {
			for (String processedType : material.getProcessedType()) {

				// Storage Blocks
				if (processedType.equals("storage_block")) {
					StringBuilder sb = new StringBuilder();
					sb.append("Block of ");
					sb.append(material.getLocalisedName());
					add(EERegistrar.storageBlockMap.get(material.getId()).get(), sb.toString());
				}

				// Ingots
				if (processedType.equals("ingot")) {
					StringBuilder sb = new StringBuilder();
					sb.append(material.getLocalisedName());
					sb.append(" Ingot");
					add(EERegistrar.ingotMap.get(material.getId()).get(), sb.toString());
				}

				// Gems
				if (processedType.equals("gem")) {
					StringBuilder sb = new StringBuilder();
					sb.append(material.getLocalisedName());
					sb.append(" Gem");
					add(EERegistrar.gemMap.get(material.getId()).get(), sb.toString());
				}

				// Nuggets
				if (processedType.equals("nugget")) {
					StringBuilder sb = new StringBuilder();
					sb.append(material.getLocalisedName());
					sb.append(" Nugget");
					add(EERegistrar.nuggetMap.get(material.getId()).get(), sb.toString());
				}

				// Dusts
				if (processedType.equals("dust")) {
					StringBuilder sb = new StringBuilder();
					sb.append(material.getLocalisedName());
					sb.append(" Dust");
					add(EERegistrar.dustMap.get(material.getId()).get(), sb.toString());
				}

				// Plates
				if (processedType.equals("plate")) {
					StringBuilder sb = new StringBuilder();
					sb.append(material.getLocalisedName());
					sb.append(" Plate");
					add(EERegistrar.plateMap.get(material.getId()).get(), sb.toString());
				}

				// Gears
				if (processedType.equals("gear")) {
					StringBuilder sb = new StringBuilder();
					sb.append(material.getLocalisedName());
					sb.append(" Gear");
					add(EERegistrar.gearMap.get(material.getId()).get(), sb.toString());
				}

				// Rods
				if (processedType.equals("rod")) {
					StringBuilder sb = new StringBuilder();
					sb.append(material.getLocalisedName());
					sb.append(" Rod");
					add(EERegistrar.rodMap.get(material.getId()).get(), sb.toString());
				}

				// Raw Chunks
				if (processedType.equals("raw")) {
					StringBuilder sb = new StringBuilder();
					sb.append("Raw ");
					sb.append(material.getLocalisedName());
					add(EERegistrar.rawMap.get(material.getId()).get(), sb.toString());
				}

//				// Clusters
//				if (processedType.equals("cluster")) {
//					StringBuilder sb = new StringBuilder();
//					sb.append(material.getLocalisedName());
//					sb.append(" Cluster");
//					add(EERegistrar.clusterMap.get(material.getId()).get(), sb.toString());
//				}

				// Fluids
				if (processedType.equals("fluid")) {
					StringBuilder sb = new StringBuilder();
					sb.append("Molten ");
					sb.append(material.getLocalisedName());
					add("fluid.emendatusenigmatica.molten_" + material.getId(), sb.toString());

					StringBuilder sb2 = new StringBuilder();
					sb2.append("Bucket of Molten ");
					sb2.append(material.getLocalisedName());
					add(EERegistrar.fluidBucketMap.get(material.getId()).get(), sb2.toString());
				}

				// TODO: Look into moving this to the MekanismDataGen class
				if (EmendatusEnigmatica.MEKANISM_LOADED) {
					// Slurries
					if (processedType.contains("slurry")) {
						StringBuilder sb = new StringBuilder();
						sb.append("Dirty ");
						sb.append(material.getLocalisedName());
						sb.append(" Slurry");
						add(EEMekanismRegistrar.dirtySlurryMap.get(material.getId()).get().getTranslationKey(), sb.toString());

						StringBuilder sb2 = new StringBuilder();
						sb2.append("Clean ");
						sb2.append(material.getLocalisedName());
						sb2.append(" Slurry");
						add(EEMekanismRegistrar.cleanSlurryMap.get(material.getId()).get().getTranslationKey(), sb2.toString());
					}
					// Crystals
					if (processedType.contains("crystal")) {
						StringBuilder sb = new StringBuilder();
						sb.append(material.getLocalisedName());
						sb.append(" Crystal");
						add(EEMekanismRegistrar.crystalMap.get(material.getId()).get(), sb.toString());
					}
					// Shards
					if (processedType.contains("shard")) {
						StringBuilder sb = new StringBuilder();
						sb.append(material.getLocalisedName());
						sb.append(" Shard");
						add(EEMekanismRegistrar.shardMap.get(material.getId()).get(), sb.toString());
					}
					// Clumps
					if (processedType.contains("clump")) {
						StringBuilder sb = new StringBuilder();
						sb.append(material.getLocalisedName());
						sb.append(" Clump");
						add(EEMekanismRegistrar.clumpMap.get(material.getId()).get(), sb.toString());
					}
					// Dirty Dusts
					if (processedType.contains("dirty_dust")) {
						StringBuilder sb = new StringBuilder();
						sb.append("Dirty ");
						sb.append(material.getLocalisedName());
						sb.append(" Dust");
						add(EEMekanismRegistrar.dirtyDustMap.get(material.getId()).get(), sb.toString());
					}
				}
				// TODO: Look into moving this to the CreateDataGen class
				if (EmendatusEnigmatica.CREATE_LOADED) {
					// Crushed Ore
					if (processedType.contains("crushed_ore")) {
						StringBuilder sb = new StringBuilder();
						sb.append("Crushed ");
						sb.append(material.getLocalisedName());
						sb.append(" ore");
						add(EECreateRegistrar.crushedOreMap.get(material.getId()).get(), sb.toString());
					}
				}
				// TODO: Look into moving this to the BloodMagicDataGen class
				if (EmendatusEnigmatica.BLOODMAGIC_LOADED) {
					// Fragment
					if (processedType.contains("fragment")) {
						StringBuilder sb = new StringBuilder();
						sb.append(material.getLocalisedName());
						sb.append(" Fragment");
						add(EEBloodMagicRegistrar.fragmentMap.get(material.getId()).get(), sb.toString());
					}
					// Gravel
					if (processedType.contains("gravel")) {
						StringBuilder sb = new StringBuilder();
						sb.append(material.getLocalisedName());
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
					sb.append(material.getLocalisedName());
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

	private void add(Slurry slurry, String name) {
		add(slurry.getTranslationKey(), name);
	}

	@Override
	public String getName() {
		return "Emendatus Enigmatica Languages: en_us";
	}
}