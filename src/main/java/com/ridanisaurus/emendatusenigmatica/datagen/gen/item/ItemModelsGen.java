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

package com.ridanisaurus.emendatusenigmatica.datagen.gen.item;

import com.ridanisaurus.emendatusenigmatica.plugin.DataRegistry;
import com.ridanisaurus.emendatusenigmatica.datagen.provider.EEItemModelProvider;
import com.ridanisaurus.emendatusenigmatica.datagen.IFinishedGenericJSON;
import com.ridanisaurus.emendatusenigmatica.datagen.builder.ItemModelBuilder;
import com.ridanisaurus.emendatusenigmatica.plugin.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class ItemModelsGen extends EEItemModelProvider {
	private final DataRegistry registry;

	public ItemModelsGen(DataGenerator gen, DataRegistry registry) {
		super(gen);
		this.registry = registry;
	}

	@Override
	protected void buildItemModels(Consumer<IFinishedGenericJSON> consumer) {
		for (MaterialModel material : registry.getRegisteredMaterials()) {
			List<String> processedType = material.getProcessedTypes();

			// Storage Blocks
			if (processedType.contains("storage_block")) {
				if (material.getProperties().hasOxidization()) {
					new ItemModelBuilder(Reference.MOD_ID, "block/exposed_" + material.getId())
						.save(consumer, Reference.MOD_ID, "exposed_" + material.getId());
					new ItemModelBuilder(Reference.MOD_ID, "block/weathered_" + material.getId())
						.save(consumer, Reference.MOD_ID, "weathered_" + material.getId());
					new ItemModelBuilder(Reference.MOD_ID, "block/oxidized_" + material.getId())
						.save(consumer, Reference.MOD_ID, "oxidized_" + material.getId());
					new ItemModelBuilder(Reference.MOD_ID, "block/waxed_" + material.getId() + "_block")
						.save(consumer, Reference.MOD_ID, "waxed_" + material.getId() + "_block");
					new ItemModelBuilder(Reference.MOD_ID, "block/waxed_exposed_" + material.getId())
						.save(consumer, Reference.MOD_ID, "waxed_exposed_" + material.getId());
					new ItemModelBuilder(Reference.MOD_ID, "block/waxed_weathered_" + material.getId())
						.save(consumer, Reference.MOD_ID, "waxed_weathered_" + material.getId());
					new ItemModelBuilder(Reference.MOD_ID, "block/waxed_oxidized_" + material.getId())
						.save(consumer, Reference.MOD_ID, "waxed_oxidized_" + material.getId());
				}

				if (processedType.contains("raw"))
					new ItemModelBuilder(Reference.MOD_ID, "block/raw_" + material.getId() + "_block")
						.save(consumer, Reference.MOD_ID, "raw_" + material.getId() + "_block");

				new ItemModelBuilder(Reference.MOD_ID, "block/" + material.getId() + "_block")
						.save(consumer, Reference.MOD_ID, material.getId() + "_block");
			}

			// Shard Blocks
			if (processedType.contains("cluster")) {
				new ItemModelBuilder(Reference.MOD_ID, "block/" + material.getId() + "_cluster_shard_block")
						.save(consumer, Reference.MOD_ID, material.getId() + "_cluster_shard_block");
				new ItemModelBuilder(Reference.MOD_ID, "block/budding_" + material.getId())
						.save(consumer, Reference.MOD_ID, "budding_" + material.getId());

				ItemModelBuilder smallBudBuilder = new ItemModelBuilder("emendatusenigmatica:item/bud");
				if (!material.getColors().hasMaterialColor()) {
					smallBudBuilder.texture("layer0", Reference.MOD_ID, "blocks/small_" + material.getId() + "_bud");
				} else {
					smallBudBuilder.texture("layer0", Reference.MOD_ID, "block/templates/clusters/small_bud/00")
							.texture("layer1", Reference.MOD_ID, "block/templates/clusters/small_bud/01")
							.texture("layer2", Reference.MOD_ID, "block/templates/clusters/small_bud/02")
							.texture("layer3", Reference.MOD_ID, "block/templates/clusters/small_bud/03")
							.texture("layer4", Reference.MOD_ID, "block/templates/clusters/small_bud/04");
				}
				smallBudBuilder.save(consumer, Reference.MOD_ID, "small_" + material.getId() + "_bud");

				ItemModelBuilder mediumBudBuilder = new ItemModelBuilder("emendatusenigmatica:item/bud");
				if (!material.getColors().hasMaterialColor()) {
					mediumBudBuilder.texture("layer0", Reference.MOD_ID, "blocks/medium_" + material.getId() + "_bud");
				} else {
					mediumBudBuilder.texture("layer0", Reference.MOD_ID, "block/templates/clusters/medium_bud/00")
							.texture("layer1", Reference.MOD_ID, "block/templates/clusters/medium_bud/01")
							.texture("layer2", Reference.MOD_ID, "block/templates/clusters/medium_bud/02")
							.texture("layer3", Reference.MOD_ID, "block/templates/clusters/medium_bud/03")
							.texture("layer4", Reference.MOD_ID, "block/templates/clusters/medium_bud/04");
				}
				mediumBudBuilder.save(consumer, Reference.MOD_ID, "medium_" + material.getId() + "_bud");

				ItemModelBuilder largeBudBuilder = new ItemModelBuilder("emendatusenigmatica:item/bud");
				if (!material.getColors().hasMaterialColor()) {
					largeBudBuilder.texture("layer0", Reference.MOD_ID, "blocks/large_" + material.getId() + "_bud");
				} else {
					largeBudBuilder.texture("layer0", Reference.MOD_ID, "block/templates/clusters/large_bud/00")
							.texture("layer1", Reference.MOD_ID, "block/templates/clusters/large_bud/01")
							.texture("layer2", Reference.MOD_ID, "block/templates/clusters/large_bud/02")
							.texture("layer3", Reference.MOD_ID, "block/templates/clusters/large_bud/03")
							.texture("layer4", Reference.MOD_ID, "block/templates/clusters/large_bud/04");
				}
				largeBudBuilder.save(consumer, Reference.MOD_ID, "large_" + material.getId() + "_bud");

				ItemModelBuilder clusterBuilder = new ItemModelBuilder("emendatusenigmatica:item/bud");
				if (!material.getColors().hasMaterialColor()) {
					clusterBuilder.texture("layer0", Reference.MOD_ID, "blocks/" + material.getId() + "_cluster");
				} else {
					clusterBuilder.texture("layer0", Reference.MOD_ID, "block/templates/clusters/cluster/00")
							.texture("layer1", Reference.MOD_ID, "block/templates/clusters/cluster/01")
							.texture("layer2", Reference.MOD_ID, "block/templates/clusters/cluster/02")
							.texture("layer3", Reference.MOD_ID, "block/templates/clusters/cluster/03")
							.texture("layer4", Reference.MOD_ID, "block/templates/clusters/cluster/04");
				}
				clusterBuilder.save(consumer, Reference.MOD_ID, material.getId() + "_cluster");

				ItemModelBuilder clusterShardBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (!material.getColors().hasMaterialColor()) {
					clusterShardBuilder.texture("layer0", Reference.MOD_ID, "item/" + material.getId() + "_cluster_shard");
				} else {
					clusterShardBuilder.texture("layer0", Reference.MOD_ID, "item/templates/cluster_shard/00")
							.texture("layer1", Reference.MOD_ID, "item/templates/cluster_shard/01")
							.texture("layer2", Reference.MOD_ID, "item/templates/cluster_shard/02")
							.texture("layer3", Reference.MOD_ID, "item/templates/cluster_shard/03")
							.texture("layer4", Reference.MOD_ID, "item/templates/cluster_shard/04");
				}
				clusterShardBuilder.save(consumer, Reference.MOD_ID, material.getId() + "_cluster_shard");
			}

			// Ingots
			if (processedType.contains("ingot")) {
				ItemModelBuilder ingotBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (!material.getColors().hasMaterialColor()) {
					ingotBuilder.texture("layer0", Reference.MOD_ID, "item/" + material.getId() + "_ingot");
				} else {
					ingotBuilder.texture("layer0", Reference.MOD_ID, "item/templates/ingot/00")
								.texture("layer1", Reference.MOD_ID, "item/templates/ingot/01")
								.texture("layer2", Reference.MOD_ID, "item/templates/ingot/02")
								.texture("layer3", Reference.MOD_ID, "item/templates/ingot/03")
								.texture("layer4", Reference.MOD_ID, "item/templates/ingot/04");
				}
				ingotBuilder.save(consumer, Reference.MOD_ID, material.getId() + "_ingot");
			}

			// Nuggets
			if (processedType.contains("nugget")) {
				ItemModelBuilder nuggetBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (!material.getColors().hasMaterialColor()) {
					nuggetBuilder.texture("layer0", Reference.MOD_ID, "item/" + material.getId() + "_nugget");
				} else {
					nuggetBuilder.texture("layer0", Reference.MOD_ID, "item/templates/nugget/00")
							.texture("layer1", Reference.MOD_ID, "item/templates/nugget/01")
							.texture("layer2", Reference.MOD_ID, "item/templates/nugget/02")
							.texture("layer3", Reference.MOD_ID, "item/templates/nugget/03")
							.texture("layer4", Reference.MOD_ID, "item/templates/nugget/04");
				}
				nuggetBuilder.save(consumer, Reference.MOD_ID, material.getId() + "_nugget");
			}

			// Gems
			if (processedType.contains("gem")) {
				ItemModelBuilder gemBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (!material.getColors().hasMaterialColor()) {
					gemBuilder.texture("layer0", Reference.MOD_ID, "item/" + material.getId() + "_gem");
				} else {
					gemBuilder.texture("layer0", Reference.MOD_ID, "item/templates/gem/template_" + material.getProperties().getGemTexture() + "/00")
							.texture("layer1", Reference.MOD_ID, "item/templates/gem/template_" + material.getProperties().getGemTexture() + "/01")
							.texture("layer2", Reference.MOD_ID, "item/templates/gem/template_" + material.getProperties().getGemTexture() + "/02")
							.texture("layer3", Reference.MOD_ID, "item/templates/gem/template_" + material.getProperties().getGemTexture() + "/03")
							.texture("layer4", Reference.MOD_ID, "item/templates/gem/template_" + material.getProperties().getGemTexture() + "/04");
				}
				gemBuilder.save(consumer, Reference.MOD_ID, material.getId() + "_gem");
			}

			// Dusts
			if (processedType.contains("dust")) {
				ItemModelBuilder dustBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (!material.getColors().hasMaterialColor()) {
					dustBuilder.texture("layer0", Reference.MOD_ID, "item/" + material.getId() + "_dust");
				} else {
					dustBuilder.texture("layer0", Reference.MOD_ID, "item/templates/dust/00")
							.texture("layer1", Reference.MOD_ID, "item/templates/dust/01")
							.texture("layer2", Reference.MOD_ID, "item/templates/dust/02")
							.texture("layer3", Reference.MOD_ID, "item/templates/dust/03")
							.texture("layer4", Reference.MOD_ID, "item/templates/dust/04");
				}
				dustBuilder.save(consumer, Reference.MOD_ID, material.getId() + "_dust");
			}

			// Plates
			if (processedType.contains("plate")) {
				ItemModelBuilder plateBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (!material.getColors().hasMaterialColor()) {
					plateBuilder.texture("layer0", Reference.MOD_ID, "item/" + material.getId() + "_plate");
				} else {
					plateBuilder.texture("layer0", Reference.MOD_ID, "item/templates/plate/00")
							.texture("layer1", Reference.MOD_ID, "item/templates/plate/01")
							.texture("layer2", Reference.MOD_ID, "item/templates/plate/02")
							.texture("layer3", Reference.MOD_ID, "item/templates/plate/03")
							.texture("layer4", Reference.MOD_ID, "item/templates/plate/04");
				}
				plateBuilder.save(consumer, Reference.MOD_ID, material.getId() + "_plate");
			}

			// Gears
			if (processedType.contains("gear")) {
				ItemModelBuilder gearBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (!material.getColors().hasMaterialColor()) {
					gearBuilder.texture("layer0", Reference.MOD_ID, "item/" + material.getId() + "_gear");
				} else {
					gearBuilder.texture("layer0", Reference.MOD_ID, "item/templates/gear/00")
							.texture("layer1", Reference.MOD_ID, "item/templates/gear/01")
							.texture("layer2", Reference.MOD_ID, "item/templates/gear/02")
							.texture("layer3", Reference.MOD_ID, "item/templates/gear/03")
							.texture("layer4", Reference.MOD_ID, "item/templates/gear/04");
				}
				gearBuilder.save(consumer, Reference.MOD_ID, material.getId() + "_gear");
			}

			// Rods
			if (processedType.contains("rod")) {
				ItemModelBuilder rodBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (!material.getColors().hasMaterialColor()) {
					rodBuilder.texture("layer0", Reference.MOD_ID, "item/" + material.getId() + "_rod");
				} else {
					rodBuilder.texture("layer0", Reference.MOD_ID, "item/templates/rod/00")
							.texture("layer1", Reference.MOD_ID, "item/templates/rod/01")
							.texture("layer2", Reference.MOD_ID, "item/templates/rod/02")
							.texture("layer3", Reference.MOD_ID, "item/templates/rod/03")
							.texture("layer4", Reference.MOD_ID, "item/templates/rod/04");
				}
				rodBuilder.save(consumer, Reference.MOD_ID, material.getId() + "_rod");
			}

			// Raw Materials
			if (processedType.contains("raw")) {
				ItemModelBuilder rawBuilder = new ItemModelBuilder("minecraft:item/generated");
				if (!material.getColors().hasMaterialColor()) {
					rawBuilder.texture("layer0", Reference.MOD_ID, "item/raw_" + material.getId());
				} else {
					rawBuilder.texture("layer0", Reference.MOD_ID, "item/templates/raw/00")
							.texture("layer1", Reference.MOD_ID, "item/templates/raw/01")
							.texture("layer2", Reference.MOD_ID, "item/templates/raw/02")
							.texture("layer3", Reference.MOD_ID, "item/templates/raw/03")
							.texture("layer4", Reference.MOD_ID, "item/templates/raw/04");
				}
				rawBuilder.save(consumer, Reference.MOD_ID, "raw_" + material.getId());
			}

			// Swords
			if (processedType.contains("sword")) {
				ItemModelBuilder swordBuilder = new ItemModelBuilder("minecraft:item/handheld");
				if (!material.getColors().hasMaterialColor()) {
					swordBuilder.texture("layer0", Reference.MOD_ID, "item/" + material.getId() + "_sword");
				} else {
					swordBuilder.texture("layer0", Reference.MOD_ID, "item/templates/sword/00")
							.texture("layer1", Reference.MOD_ID, "item/templates/sword/01")
							.texture("layer2", Reference.MOD_ID, "item/templates/sword/02")
							.texture("layer3", Reference.MOD_ID, "item/templates/sword/03")
							.texture("layer4", Reference.MOD_ID, "item/templates/sword/grip");
				}
				swordBuilder.save(consumer, Reference.MOD_ID, material.getId() + "_sword");
			}

			// Pickaxes
			if (processedType.contains("pickaxe")) {
				ItemModelBuilder pickaxeBuilder = new ItemModelBuilder("minecraft:item/handheld");
				if (!material.getColors().hasMaterialColor()) {
					pickaxeBuilder.texture("layer0", Reference.MOD_ID, "item/" + material.getId() + "_pickaxe");
				} else {
					pickaxeBuilder.texture("layer0", Reference.MOD_ID, "item/templates/pickaxe/00")
							.texture("layer1", Reference.MOD_ID, "item/templates/pickaxe/01")
							.texture("layer2", Reference.MOD_ID, "item/templates/pickaxe/02")
							.texture("layer3", Reference.MOD_ID, "item/templates/pickaxe/03")
							.texture("layer4", Reference.MOD_ID, "item/templates/pickaxe/grip");
				}
				pickaxeBuilder.save(consumer, Reference.MOD_ID, material.getId() + "_pickaxe");
			}

			// Axes
			if (processedType.contains("axe")) {
				ItemModelBuilder axeBuilder = new ItemModelBuilder("minecraft:item/handheld");
				if (!material.getColors().hasMaterialColor()) {
					axeBuilder.texture("layer0", Reference.MOD_ID, "item/" + material.getId() + "_axe");
				} else {
					axeBuilder.texture("layer0", Reference.MOD_ID, "item/templates/axe/00")
							.texture("layer1", Reference.MOD_ID, "item/templates/axe/01")
							.texture("layer2", Reference.MOD_ID, "item/templates/axe/02")
							.texture("layer3", Reference.MOD_ID, "item/templates/axe/03")
							.texture("layer4", Reference.MOD_ID, "item/templates/axe/grip");

				}
				axeBuilder.save(consumer, Reference.MOD_ID, material.getId() + "_axe");
			}

			// Shovels
			if (processedType.contains("shovel")) {
				ItemModelBuilder shovelBuilder = new ItemModelBuilder("minecraft:item/handheld");
				if (!material.getColors().hasMaterialColor()) {
					shovelBuilder.texture("layer0", Reference.MOD_ID, "item/" + material.getId() + "_shovel");
				} else {
					shovelBuilder.texture("layer0", Reference.MOD_ID, "item/templates/shovel/00")
							.texture("layer1", Reference.MOD_ID, "item/templates/shovel/01")
							.texture("layer2", Reference.MOD_ID, "item/templates/shovel/02")
							.texture("layer3", Reference.MOD_ID, "item/templates/shovel/03")
							.texture("layer4", Reference.MOD_ID, "item/templates/shovel/grip");
				}
				shovelBuilder.save(consumer, Reference.MOD_ID, material.getId() + "_shovel");
			}

			// Hoes
			if (processedType.contains("hoe")) {
				ItemModelBuilder hoeBuilder = new ItemModelBuilder("minecraft:item/handheld");
				if (!material.getColors().hasMaterialColor()) {
					hoeBuilder.texture("layer0", Reference.MOD_ID, "item/" + material.getId() + "_hoe");
				} else {
					hoeBuilder.texture("layer0", Reference.MOD_ID, "item/templates/hoe/00")
							.texture("layer1", Reference.MOD_ID, "item/templates/hoe/01")
							.texture("layer2", Reference.MOD_ID, "item/templates/hoe/02")
							.texture("layer3", Reference.MOD_ID, "item/templates/hoe/03")
							.texture("layer4", Reference.MOD_ID, "item/templates/hoe/grip");
				}
				hoeBuilder.save(consumer, Reference.MOD_ID, material.getId() + "_hoe");
			}

			// Paxels
			if (processedType.contains("paxel")) {
				ItemModelBuilder paxelBuilder = new ItemModelBuilder("minecraft:item/handheld");
				if (!material.getColors().hasMaterialColor()) {
					paxelBuilder.texture("layer0", Reference.MOD_ID, "item/" + material.getId() + "_paxel");
				} else {
					paxelBuilder.texture("layer0", Reference.MOD_ID, "item/templates/paxel/00")
							.texture("layer1", Reference.MOD_ID, "item/templates/paxel/01")
							.texture("layer2", Reference.MOD_ID, "item/templates/paxel/02")
							.texture("layer3", Reference.MOD_ID, "item/templates/paxel/03")
							.texture("layer4", Reference.MOD_ID, "item/templates/paxel/grip");
				}
				paxelBuilder.save(consumer, Reference.MOD_ID, material.getId() + "_paxel");
			}

			// Shields
			if (processedType.contains("shield")) {
				ItemModelBuilder shieldBlockingBuilder = new ItemModelBuilder("minecraft:item/shield_blocking")
					.texture("particle", Reference.MINECRAFT, "block/dark_oak_planks");
				shieldBlockingBuilder.save(consumer, Reference.MOD_ID, material.getId() + "_shield_blocking");

				ItemModelBuilder shieldBuilder = new ItemModelBuilder("minecraft:item/shield")
						.texture("particle", Reference.MINECRAFT, "block/dark_oak_planks")
						.override()
						.predicate(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "blocking"), 1)
						.model(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "item/" + material.getId() + "_shield_blocking"))
						.end();
				shieldBuilder.save(consumer, Reference.MOD_ID, material.getId() + "_shield");
			}

			// Fluid Buckets
			if (processedType.contains("fluid")) {
				new ItemModelBuilder("neoforge:item/bucket_drip")
						.applyTint(true)
						.fluid(EERegistrar.fluidSourceMap.getIdAsString(material))
						.loader("neoforge:fluid_container")
						.save(consumer, Reference.MOD_ID, material.getId() + "_bucket");
			}

			// Armor
			if (processedType.contains("armor")) {
				ItemModelBuilder helmetBuilder = new ItemModelBuilder("minecraft:item/generated");
				ItemModelBuilder chestplateBuilder = new ItemModelBuilder("minecraft:item/generated");
				ItemModelBuilder leggingsBuilder = new ItemModelBuilder("minecraft:item/generated");
				ItemModelBuilder bootsBuilder = new ItemModelBuilder("minecraft:item/generated");

				if (!material.getColors().hasMaterialColor()) {
					helmetBuilder.texture("layer0", Reference.MOD_ID, "item/" + material.getId() + "_helmet");
					chestplateBuilder.texture("layer0", Reference.MOD_ID, "item/" + material.getId() + "_chestplate");
					leggingsBuilder.texture("layer0", Reference.MOD_ID, "item/" + material.getId() + "_leggings");
					bootsBuilder.texture("layer0", Reference.MOD_ID, "item/" + material.getId() + "_boots");
				} else {
					helmetBuilder.texture("layer0", Reference.MOD_ID, "item/templates/helmet/00")
						.texture("layer1", Reference.MOD_ID, "item/templates/helmet/01")
						.texture("layer2", Reference.MOD_ID, "item/templates/helmet/02")
						.texture("layer3", Reference.MOD_ID, "item/templates/helmet/03")
						.texture("layer4", Reference.MOD_ID, "item/templates/helmet/04");
					chestplateBuilder.texture("layer0", Reference.MOD_ID, "item/templates/chestplate/00")
						.texture("layer1", Reference.MOD_ID, "item/templates/chestplate/01")
						.texture("layer2", Reference.MOD_ID, "item/templates/chestplate/02")
						.texture("layer3", Reference.MOD_ID, "item/templates/chestplate/03")
						.texture("layer4", Reference.MOD_ID, "item/templates/chestplate/04");
					leggingsBuilder.texture("layer0", Reference.MOD_ID, "item/templates/leggings/00")
						.texture("layer1", Reference.MOD_ID, "item/templates/leggings/01")
						.texture("layer2", Reference.MOD_ID, "item/templates/leggings/02")
						.texture("layer3", Reference.MOD_ID, "item/templates/leggings/03")
						.texture("layer4", Reference.MOD_ID, "item/templates/leggings/04");
					bootsBuilder.texture("layer0", Reference.MOD_ID, "item/templates/boots/00")
						.texture("layer1", Reference.MOD_ID, "item/templates/boots/01")
						.texture("layer2", Reference.MOD_ID, "item/templates/boots/02")
						.texture("layer3", Reference.MOD_ID, "item/templates/boots/03")
						.texture("layer4", Reference.MOD_ID, "item/templates/boots/04");
				}

				helmetBuilder.save(consumer, Reference.MOD_ID, material.getId() + "_helmet");
				chestplateBuilder.save(consumer, Reference.MOD_ID, material.getId() + "_chestplate");
				leggingsBuilder.save(consumer, Reference.MOD_ID, material.getId() + "_leggings");
				bootsBuilder.save(consumer, Reference.MOD_ID, material.getId() + "_boots");
			}

			// Ores
			if (processedType.contains("ore")) {
				for (StrataModel stratum : registry.getRegisteredStrata()) {
					if (!material.getStrata().isEmpty() && !material.getStrata().contains(stratum.getId())) continue;
					new ItemModelBuilder(Reference.MOD_ID, "block/" + getOreModelName(stratum, material))
						.save(consumer, Reference.MOD_ID, getOreModelName(stratum, material));
					//TODO: Rework Sample System
//					if (processedType.contains("sample")) {
//						new ItemModelBuilder(Reference.MOD_ID, "block/" + getSampleModelName(stratum, material))
//						.save(consumer, Reference.MOD_ID, getSampleModelName(stratum, material));
//					}
				}
			}
		}
	}

	public static @NotNull String getOreModelName(@NotNull StrataModel stratum, @NotNull MaterialModel material) {
		return material.getId() + (!stratum.getId().equals("minecraft_stone") ? "_" + stratum.getSuffix() : "") + "_ore";
	}

	public static @NotNull String getSampleModelName(@NotNull StrataModel stratum, @NotNull MaterialModel material) {
		return material.getId() + "_" + stratum.getSuffix() + "_ore_sample";
	}

	@Override
	public @NotNull String getName() {
		return "Emendatus Enigmatica: Item Models";
	}
}