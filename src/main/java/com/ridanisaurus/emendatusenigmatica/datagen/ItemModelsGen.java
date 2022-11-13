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

import com.ridanisaurus.emendatusenigmatica.datagen.base.EEItemModelProvider;
import com.ridanisaurus.emendatusenigmatica.datagen.base.IFinishedGenericJSON;
import com.ridanisaurus.emendatusenigmatica.datagen.base.ItemModelBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.function.Consumer;

public class ItemModelsGen extends EEItemModelProvider {
	public ItemModelsGen(DataGenerator gen) {
		super(gen);
	}

	@Override
	protected void buildItemModels(Consumer<IFinishedGenericJSON> consumer) {
		for (MaterialModel material : EELoader.MATERIALS) {
			List<String> processedType = material.getProcessedType();
			// Storage Blocks
			if (processedType.contains("storage_block")) {
				new ItemModelBuilder(new ResourceLocation(Reference.MOD_ID, "block/" + material.getId() + "_block").toString())
						.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_block"));
			}
			// Ingots
			if (processedType.contains("ingot")) {
				ItemModelBuilder ingotBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (material.getColors().getHighlightColor() == -1) {
					ingotBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_ingot").toString());
				} else {
					ingotBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/ingot_0").toString())
							.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/ingot_1").toString())
							.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/ingot_2").toString());
				}
				ingotBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_ingot"));
			}
			// Nuggets
			if (processedType.contains("nugget")) {
				ItemModelBuilder nuggetBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (material.getColors().getHighlightColor() == -1) {
					nuggetBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_nugget").toString());
				} else {
					nuggetBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/nugget_0").toString())
							.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/nugget_1").toString())
							.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/nugget_2").toString());
				}
				nuggetBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_nugget"));
			}
			// Gems
			if (processedType.contains("gem")) {
				ItemModelBuilder gemBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (material.getColors().getHighlightColor() == -1) {
					gemBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_gem").toString());
				} else {
					gemBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/gem_0").toString())
							.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/gem_1").toString())
							.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/gem_2").toString());
				}
				gemBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_gem"));
			}
			// Dusts
			if (processedType.contains("dust")) {
				ItemModelBuilder dustBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (material.getColors().getHighlightColor() == -1) {
					dustBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_dust").toString());
				} else {
					dustBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/dust_0").toString())
							.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/dust_1").toString())
							.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/dust_2").toString());
				}
				dustBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_dust"));
			}
			// Plates
			if (processedType.contains("plate")) {
				ItemModelBuilder plateBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (material.getColors().getHighlightColor() == -1) {
					plateBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_plate").toString());
				} else {
					plateBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/plate_0").toString())
							.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/plate_1").toString())
							.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/plate_2").toString());
				}
				plateBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_plate"));
			}
			// Gears
			if (processedType.contains("gear")) {
				ItemModelBuilder gearBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (material.getColors().getHighlightColor() == -1) {
					gearBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_gear").toString());
				} else {
					gearBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/gear_0").toString())
							.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/gear_1").toString())
							.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/gear_2").toString());
				}
				gearBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_gear"));
			}
			// Rods
			if (processedType.contains("rod")) {
				ItemModelBuilder rodBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (material.getColors().getHighlightColor() == -1) {
					rodBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_rod").toString());
				} else {
					rodBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/rod_0").toString())
							.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/rod_1").toString())
							.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/rod_2").toString());
				}
				rodBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_rod"));
			}
			// Raw Materials
			if (processedType.contains("raw")) {
				ItemModelBuilder rawBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (material.getColors().getHighlightColor() == -1) {
					rawBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/raw_" + material.getId()).toString());
				} else {
					rawBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/raw_0").toString())
							.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/raw_1").toString())
							.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/raw_2").toString());
				}
				rawBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, "raw_" + material.getId()));

				new ItemModelBuilder(new ResourceLocation(Reference.MOD_ID, "block/raw_" + material.getId() + "_block").toString())
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "raw_" + material.getId() + "_block"));
			}
			// Fluid Buckets
			if (processedType.contains("fluid")) {
				new ItemModelBuilder("forge:item/bucket_drip")
						.applyTint(true)
						.fluid(EERegistrar.fluidSourceMap.get(material.getId()).getId().toString())
						.loader("forge:fluid_container")
						.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_bucket"));
			}
			// Ores
			for (StrataModel stratum : EELoader.STRATA) {
				if (processedType.contains("ore")) {
					new ItemModelBuilder(new ResourceLocation(Reference.MOD_ID, "block/" + getModelName(stratum, material)).toString())
							.save(consumer, new ResourceLocation(Reference.MOD_ID, getModelName(stratum, material)));
				}
			}
		}
	}

	public static String getModelName(StrataModel stratum, MaterialModel material) {
		return material.getId() + (!stratum.getId().equals("minecraft_stone") ? "_" + stratum.getSuffix() : "") + "_ore";
	}

	@Override
	public String getName() {
		return "Emendatus Enigmatica Item Models";
	}
}