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

import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.datagen.base.EEItemModelProvider;
import com.ridanisaurus.emendatusenigmatica.datagen.base.IFinishedGenericJSON;
import com.ridanisaurus.emendatusenigmatica.datagen.base.ItemModelBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.function.Consumer;

public class ItemModelsGen extends EEItemModelProvider {
	private final EmendatusDataRegistry registry;

	public ItemModelsGen(DataGenerator gen, EmendatusDataRegistry registry) {
		super(gen);
		this.registry = registry;
	}

	@Override
	protected void buildItemModels(Consumer<IFinishedGenericJSON> consumer) {
		for (MaterialModel material : registry.getMaterials()) {
			List<String> processedType = material.getProcessedTypes();
			// Storage Blocks
			if (processedType.contains("storage_block")) {
				new ItemModelBuilder(new ResourceLocation(Reference.MOD_ID, "block/" + material.getId() + "_block").toString())
						.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_block"));
			}
			// Ingots
			if (processedType.contains("ingot")) {
				ItemModelBuilder ingotBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (material.getColors().getMaterialColor() == -1) {
					ingotBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_ingot").toString());
				} else {
					ingotBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/ingot/00").toString())
								.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/ingot/01").toString())
								.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/ingot/02").toString())
								.texture("layer3", new ResourceLocation(Reference.MOD_ID, "items/templates/ingot/03").toString())
								.texture("layer4", new ResourceLocation(Reference.MOD_ID, "items/templates/ingot/04").toString());
				}
				ingotBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_ingot"));
			}
			// Nuggets
			if (processedType.contains("nugget")) {
				ItemModelBuilder nuggetBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (material.getColors().getMaterialColor() == -1) {
					nuggetBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_nugget").toString());
				} else {
					nuggetBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/nugget/00").toString())
							.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/nugget/01").toString())
							.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/nugget/02").toString())
							.texture("layer3", new ResourceLocation(Reference.MOD_ID, "items/templates/nugget/03").toString())
							.texture("layer4", new ResourceLocation(Reference.MOD_ID, "items/templates/nugget/04").toString());
				}
				nuggetBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_nugget"));
			}
			// Gems
			if (processedType.contains("gem")) {
				ItemModelBuilder gemBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (material.getColors().getMaterialColor() == -1) {
					gemBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_gem").toString());
				} else {
					gemBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/gem/00").toString())
							.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/gem/01").toString())
							.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/gem/02").toString())
							.texture("layer3", new ResourceLocation(Reference.MOD_ID, "items/templates/gem/03").toString())
							.texture("layer4", new ResourceLocation(Reference.MOD_ID, "items/templates/gem/04").toString());
				}
				gemBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_gem"));
			}
			// Dusts
			if (processedType.contains("dust")) {
				ItemModelBuilder dustBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (material.getColors().getMaterialColor() == -1) {
					dustBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_dust").toString());
				} else {
					dustBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/dust/00").toString())
							.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/dust/01").toString())
							.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/dust/02").toString())
							.texture("layer3", new ResourceLocation(Reference.MOD_ID, "items/templates/dust/03").toString())
							.texture("layer4", new ResourceLocation(Reference.MOD_ID, "items/templates/dust/04").toString());
				}
				dustBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_dust"));
			}
			// Plates
			if (processedType.contains("plate")) {
				ItemModelBuilder plateBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (material.getColors().getMaterialColor() == -1) {
					plateBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_plate").toString());
				} else {
					plateBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/plate/00").toString())
							.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/plate/01").toString())
							.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/plate/02").toString())
							.texture("layer3", new ResourceLocation(Reference.MOD_ID, "items/templates/plate/03").toString())
							.texture("layer4", new ResourceLocation(Reference.MOD_ID, "items/templates/plate/04").toString());
				}
				plateBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_plate"));
			}
			// Gears
			if (processedType.contains("gear")) {
				ItemModelBuilder gearBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (material.getColors().getMaterialColor() == -1) {
					gearBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_gear").toString());
				} else {
					gearBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/gear/00").toString())
							.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/gear/01").toString())
							.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/gear/02").toString())
							.texture("layer3", new ResourceLocation(Reference.MOD_ID, "items/templates/gear/03").toString())
							.texture("layer4", new ResourceLocation(Reference.MOD_ID, "items/templates/gear/04").toString());
				}
				gearBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_gear"));
			}
			// Rods
			if (processedType.contains("rod")) {
				ItemModelBuilder rodBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (material.getColors().getMaterialColor() == -1) {
					rodBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_rod").toString());
				} else {
					rodBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/rod/00").toString())
							.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/rod/01").toString())
							.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/rod/02").toString())
							.texture("layer3", new ResourceLocation(Reference.MOD_ID, "items/templates/rod/03").toString())
							.texture("layer4", new ResourceLocation(Reference.MOD_ID, "items/templates/rod/04").toString());
				}
				rodBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_rod"));
			}
			// Raw Materials
			if (processedType.contains("raw")) {
				ItemModelBuilder rawBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (material.getColors().getMaterialColor() == -1) {
					rawBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/raw_" + material.getId()).toString());
				} else {
					rawBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/raw/00").toString())
							.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/raw/01").toString())
							.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/raw/02").toString())
							.texture("layer3", new ResourceLocation(Reference.MOD_ID, "items/templates/raw/03").toString())
							.texture("layer4", new ResourceLocation(Reference.MOD_ID, "items/templates/raw/04").toString());
				}
				rawBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, "raw_" + material.getId()));

				new ItemModelBuilder(new ResourceLocation(Reference.MOD_ID, "block/raw_" + material.getId() + "_block").toString())
						.save(consumer, new ResourceLocation(Reference.MOD_ID, "raw_" + material.getId() + "_block"));
			}
			// Swords
			if (processedType.contains("sword")) {
				ItemModelBuilder swordBuilder = new ItemModelBuilder("minecraft:item/handheld");
				if (material.getColors().getMaterialColor() == -1) {
					swordBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_sword").toString());
				} else {
					swordBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/sword/00").toString())
							.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/sword/01").toString())
							.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/sword/02").toString())
							.texture("layer3", new ResourceLocation(Reference.MOD_ID, "items/templates/sword/03").toString())
							.texture("layer4", new ResourceLocation(Reference.MOD_ID, "items/templates/sword/grip").toString());
				}
				swordBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_sword"));
			}
			// Pickaxes
			if (processedType.contains("pickaxe")) {
				ItemModelBuilder pickaxeBuilder = new ItemModelBuilder("minecraft:item/handheld");
				if (material.getColors().getMaterialColor() == -1) {
					pickaxeBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_pickaxe").toString());
				} else {
					pickaxeBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/pickaxe/00").toString())
							.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/pickaxe/01").toString())
							.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/pickaxe/02").toString())
							.texture("layer3", new ResourceLocation(Reference.MOD_ID, "items/templates/pickaxe/03").toString())
							.texture("layer4", new ResourceLocation(Reference.MOD_ID, "items/templates/pickaxe/grip").toString());
				}
				pickaxeBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_pickaxe"));
			}
			// Axes
			if (processedType.contains("axe")) {
				ItemModelBuilder axeBuilder = new ItemModelBuilder("minecraft:item/handheld");
				if (material.getColors().getMaterialColor() == -1) {
					axeBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_axe").toString());
				} else {
					axeBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/axe/00").toString())
							.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/axe/01").toString())
							.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/axe/02").toString())
							.texture("layer3", new ResourceLocation(Reference.MOD_ID, "items/templates/axe/03").toString())
							.texture("layer4", new ResourceLocation(Reference.MOD_ID, "items/templates/axe/grip").toString());

				}
				axeBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_axe"));
			}
			// Shovels
			if (processedType.contains("shovel")) {
				ItemModelBuilder shovelBuilder = new ItemModelBuilder("minecraft:item/handheld");
				if (material.getColors().getMaterialColor() == -1) {
					shovelBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_shovel").toString());
				} else {
					shovelBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/shovel/00").toString())
							.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/shovel/01").toString())
							.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/shovel/02").toString())
							.texture("layer3", new ResourceLocation(Reference.MOD_ID, "items/templates/shovel/03").toString())
							.texture("layer4", new ResourceLocation(Reference.MOD_ID, "items/templates/shovel/grip").toString());
				}
				shovelBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_shovel"));
			}
			// Hoes
			if (processedType.contains("hoe")) {
				ItemModelBuilder hoeBuilder = new ItemModelBuilder("minecraft:item/handheld");
				if (material.getColors().getMaterialColor() == -1) {
					hoeBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_hoe").toString());
				} else {
					hoeBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/hoe/00").toString())
							.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/hoe/01").toString())
							.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/hoe/02").toString())
							.texture("layer3", new ResourceLocation(Reference.MOD_ID, "items/templates/hoe/03").toString())
							.texture("layer4", new ResourceLocation(Reference.MOD_ID, "items/templates/hoe/grip").toString());
				}
				hoeBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_hoe"));
			}
			// Paxels
			if (processedType.contains("paxel")) {
				ItemModelBuilder paxelBuilder = new ItemModelBuilder("minecraft:item/handheld");
				if (material.getColors().getMaterialColor() == -1) {
					paxelBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_paxel").toString());
				} else {
					paxelBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/paxel/00").toString())
							.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/paxel/01").toString())
							.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/paxel/02").toString())
							.texture("layer3", new ResourceLocation(Reference.MOD_ID, "items/templates/paxel/03").toString())
							.texture("layer4", new ResourceLocation(Reference.MOD_ID, "items/templates/paxel/grip").toString());
				}
				paxelBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_paxel"));
			}
			// Helmet
			if (processedType.contains("helmet")) {
				ItemModelBuilder helmetBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (material.getColors().getMaterialColor() == -1) {
					helmetBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_helmet").toString());
				} else {
					helmetBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/helmet/00").toString())
							.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/helmet/01").toString())
							.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/helmet/02").toString())
							.texture("layer3", new ResourceLocation(Reference.MOD_ID, "items/templates/helmet/03").toString())
							.texture("layer4", new ResourceLocation(Reference.MOD_ID, "items/templates/helmet/04").toString());
				}
				helmetBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_helmet"));
			}
			// Chestplate
			if (processedType.contains("chestplate")) {
				ItemModelBuilder chestplateBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (material.getColors().getMaterialColor() == -1) {
					chestplateBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_chestplate").toString());
				} else {
					chestplateBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/chestplate/00").toString())
							.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/chestplate/01").toString())
							.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/chestplate/02").toString())
							.texture("layer3", new ResourceLocation(Reference.MOD_ID, "items/templates/chestplate/03").toString())
							.texture("layer4", new ResourceLocation(Reference.MOD_ID, "items/templates/chestplate/04").toString());
				}
				chestplateBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_chestplate"));
			}
			// Leggings
			if (processedType.contains("leggings")) {
				ItemModelBuilder leggingsBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (material.getColors().getMaterialColor() == -1) {
					leggingsBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_leggings").toString());
				} else {
					leggingsBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/leggings/00").toString())
							.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/leggings/01").toString())
							.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/leggings/02").toString())
							.texture("layer3", new ResourceLocation(Reference.MOD_ID, "items/templates/leggings/03").toString())
							.texture("layer4", new ResourceLocation(Reference.MOD_ID, "items/templates/leggings/04").toString());
				}
				leggingsBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_leggings"));
			}
			// Boots
			if (processedType.contains("boots")) {
				ItemModelBuilder bootsBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (material.getColors().getMaterialColor() == -1) {
					bootsBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/" + material.getId() + "_boots").toString());
				} else {
					bootsBuilder.texture("layer0", new ResourceLocation(Reference.MOD_ID, "items/templates/boots/00").toString())
							.texture("layer1", new ResourceLocation(Reference.MOD_ID, "items/templates/boots/01").toString())
							.texture("layer2", new ResourceLocation(Reference.MOD_ID, "items/templates/boots/02").toString())
							.texture("layer3", new ResourceLocation(Reference.MOD_ID, "items/templates/boots/03").toString())
							.texture("layer4", new ResourceLocation(Reference.MOD_ID, "items/templates/boots/04").toString());
				}
				bootsBuilder.save(consumer, new ResourceLocation(Reference.MOD_ID, material.getId() + "_boots"));
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
			for (StrataModel stratum : registry.getStrata()) {
				if (processedType.contains("ore")) {
					new ItemModelBuilder(new ResourceLocation(Reference.MOD_ID, "block/" + getOreModelName(stratum, material)).toString())
							.save(consumer, new ResourceLocation(Reference.MOD_ID, getOreModelName(stratum, material)));
				}
				if (processedType.contains("ore") && stratum.getSampleStrata()) {
					new ItemModelBuilder(new ResourceLocation(Reference.MOD_ID, "block/" + getOreSampleModelName(stratum, material)).toString())
							.save(consumer, new ResourceLocation(Reference.MOD_ID, getOreSampleModelName(stratum, material)));
				}
			}
		}
	}

	public static String getOreModelName(StrataModel stratum, MaterialModel material) {
		return material.getId() + (!stratum.getId().equals("minecraft_stone") ? "_" + stratum.getSuffix() : "") + "_ore";
	}

	public static String getOreSampleModelName(StrataModel stratum, MaterialModel material) {
		return material.getId() + "_" + stratum.getSuffix() + "_ore_sample";
	}

	@Override
	public String getName() {
		return "Emendatus Enigmatica Item Models";
	}
}