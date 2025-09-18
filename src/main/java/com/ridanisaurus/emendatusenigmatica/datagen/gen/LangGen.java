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

package com.ridanisaurus.emendatusenigmatica.datagen.gen;

import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.datagen.provider.EELangProvider;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LangGen extends EELangProvider {
	private final EmendatusDataRegistry registry;

	public LangGen(DataGenerator gen, EmendatusDataRegistry registry) {
		super(gen, Reference.MOD_ID, "en_us");
		this.registry = registry;
	}

	@Override
	protected void addTranslations() {
		// Default Items
		add(getKey("item", "enigmatic_hammer"), "Enigmatic Hammer");
		add(getKey("item", "felinium_jaminite_ingot"), "Felinium Jaminite Ingot");
		add(getKey("item", "shield_upgrade_template"), "Shield Template");
		// EMI
		add(getKey("emi.category", "compat.emi.worldgen"), "World Generation Info");
		// Tabs
		add(getKey("itemgroup", "tools"), "Emendatus Enigmatica: Equipment");
		add(getKey("itemgroup", "resources"), "Emendatus Enigmatica: Resources");
		// Tooltips
		add(getKey("tooltip", "press_shift"), "Press [SHIFT] for more info");
		add(getKey("tooltip", "armor_effect"), "Effects");
		add(getKey("tooltip", "patreon_reward.desc_long"), "This item is cosmetic, and rewarded as a render on your player's model if you are one of my Patreons.");
		add(getKey("tooltip", "patreon_reward.desc_short"), "This is a token of appreciation, and is not craftable.");
		add(getKey("tooltip", "patreon_reward.hiding"), "You can show/hide this reward from the config file without needing to restart the game.");
		// Smithing Templates
		add(getKey("smithing_template", "shield_upgrade.applies_to"), "Shields");
		add(getKey("smithing_template", "shield_upgrade.ingredients"), "Ingot/Gem");
		add(getKey("smithing_template", "shield_upgrade.upgrade_desc"), "Allows turning vanilla shield into a metal/gem shield.");
		add(getKey("smithing_template", "shield_upgrade.base_slot_desc"), "Place a vanilla shield into this slot.");
		add(getKey("smithing_template", "shield_upgrade.additional_slot_desc"), "Place a material, to which you want to upgrade your shield to, in this slot.");
		// ResourcePack
		add(getKey("resourcepack", "client.title"), "EE: Generated Resource Pack");
		add(getKey("resourcepack", "client.desc"), "Provides textures for Emendatus blocks and items.");
		add(getKey("resourcepack", "server.title"), "EE: Generated Data Pack");
		add(getKey("resourcepack", "server.desc"), "Provides recipes and World Generation Features.");
		// Config Screen
		add("emendatusenigmatica.configuration.title", "Emendatus Enigmatica Configuration");
		// Startup
		add("emendatusenigmatica.configuration.section.emendatusenigmatica.startup.toml", "Startup Configuration");
		add("emendatusenigmatica.configuration.section.emendatusenigmatica.startup.toml.title", "Startup Configuration");
		add("emendatusenigmatica.configuration.Configuration", "Emendatus Enigmatica Options");
		add("emendatusenigmatica.configuration.Configuration.tooltip", "Options related to Emendatus Enigmatica itself.");
		add("emendatusenigmatica.configuration.Configuration.button", "Emendatus Enigmatica");
		add("emendatusenigmatica.configuration.Debug", "Debug Options");
		add("emendatusenigmatica.configuration.Debug.tooltip", "Debug-related options.");
		add("emendatusenigmatica.configuration.Debug.button", "Debug");
		add("emendatusenigmatica.configuration.Addons", "Addons Options");
		add("emendatusenigmatica.configuration.Addons.tooltip", "Addon-related configuration.");
		add("emendatusenigmatica.configuration.Addons.button", "Addons");
		add("emendatusenigmatica.config.startup.skip_empty", "Skip Empty JSON files");
		add("emendatusenigmatica.config.startup.skip_empty.tooltip", "Should EE silently skip empty JSON files in the configuration folder.");
		add("emendatusenigmatica.config.startup.generate_defaults", "Generate Default Configs");
		add("emendatusenigmatica.config.startup.generate_defaults.tooltip", "Should EE generate default configuration for vanilla and all installed addons.");
		add("emendatusenigmatica.config.startup.regenerate_defaults", "Regenerate Default Configs");
		add("emendatusenigmatica.config.startup.regenerate_defaults.tooltip", "Should EE regenerate your configuration files. Note: this WILL delete your current EE configuration.");
		add("emendatusenigmatica.config.startup.generate_summary", "Generate Validation Summary");
		add("emendatusenigmatica.config.startup.generate_summary.tooltip", "Should EE generate a Validation Summary file in it's configuration directory.");
		add("emendatusenigmatica.configuration.vanilla-plugin", "Vanilla Plugin Options");
		add("emendatusenigmatica.configuration.vanilla-plugin.tooltip", "Options for Vanilla Plugin");
		add("emendatusenigmatica.configuration.vanilla-plugin.button", "Minecraft");
		add("emendatusenigmatica.config.disable_coal_ore", "Disable Coal Ore");
		add("emendatusenigmatica.config.disable_copper_ore", "Disable Copper Ore");
		add("emendatusenigmatica.config.disable_iron_ore", "Disable Iron Ore");
		add("emendatusenigmatica.config.disable_gold_ore", "Disable Gold Ore");
		add("emendatusenigmatica.config.disable_redstone_ore", "Disable Redstone Ore");
		add("emendatusenigmatica.config.disable_lapis_ore", "Disable Lapis Ore");
		add("emendatusenigmatica.config.disable_diamond_ore", "Disable Diamond Ore");
		add("emendatusenigmatica.config.disable_emerald_ore", "Disable Emerald Ore");
		add("emendatusenigmatica.config.disable_quartz_ore", "Disable Quartz Ore");
		// Client
		add("emendatusenigmatica.configuration.section.emendatusenigmatica.client.toml", "Client Configuration");
		add("emendatusenigmatica.configuration.section.emendatusenigmatica.client.toml.title", "Client Configuration");
		add("emendatusenigmatica.configuration.Rendering", "Rendering Options");
		add("emendatusenigmatica.configuration.Rendering.tooltip", "Rendering related options.");
		add("emendatusenigmatica.configuration.Rendering.button", "Rendering");
		add("emendatusenigmatica.config.client.old_glint", "Increase Glint Strength");
		add("emendatusenigmatica.config.client.old_glint.tooltip", "Should EE Armor and Tools have increased (old style) Glint Strength.");
		add("emendatusenigmatica.config.client.show_reward", "Show Patreon Reward");
		add("emendatusenigmatica.config.client.show_reward.tooltip", "Should EE render a Felinium Jeminite, the token of appreciation, above Patreons' heads.");
		//TODO: Move from here. It's here temporarily.
		add("emendatusenigmatica.configuration.mekanism-plugin", "Mekanism Plugin Options");
		add("emendatusenigmatica.configuration.mekanism-plugin.tooltip", "Options provided by EE Mekanism plugin.");
		add("emendatusenigmatica.configuration.mekanism-plugin.button", "Mekanism");
		add("ee_mekanism.config.disable_osmium_ore", "Disable Osmium Ore");
		//TODO: Remove when old config screen gets fully removed.
		add("emendatusenigmatica.screen.config.title", "Emendatus Enigmatica Configuration");
		add("emendatusenigmatica.screen.config.enabled", "Enabled");
		add("emendatusenigmatica.screen.config.disabled", "Disabled");
		add("emendatusenigmatica.screen.config.client.title", "Client Configuration");
		add("emendatusenigmatica.screen.config.client.patreon.text", "Show Patreon Reward");
		add("emendatusenigmatica.screen.config.client.patreon.tip", "When enabled, renders the Token of Appreciation above heads of the EE Patreons.");
		add("emendatusenigmatica.screen.config.client.glint.text", "Increase Armor Glint Strength");
		add("emendatusenigmatica.screen.config.client.glint.tip", "When enabled, returns to the old strength of Glint on armor.");
		add("emendatusenigmatica.screen.config.validation.title", "Validation Configuration");
		add("emendatusenigmatica.screen.config.validation.summary.text", "Generate config loading summary");
		add("emendatusenigmatica.screen.config.validation.summary.tip", "When enabled, will generate a MarkDown formatted document on boot, with the summary from the loading and validation stage of the configs.");
		add("emendatusenigmatica.screen.config.validation.empty_files.text", "Skip empty Json Files");
		add("emendatusenigmatica.screen.config.validation.empty_files.tip", "When enabled, will cause EE to silently skip empty JSON files on loading, removing errors generated in the summary.");

		for (MaterialModel material : registry.getMaterials()) {
			List<String> processedType = material.getProcessedTypes();
			// Ingots
			if (processedType.contains("ingot"))
				add(EERegistrar.ingotMap.getValue(material), material.getLocalizedName() + " Ingot");

			// Gems
			if (processedType.contains("gem"))
				add(EERegistrar.gemMap.getValue(material), material.getLocalizedName() + " Gem");

			// Nuggets
			if (processedType.contains("nugget"))
				add(EERegistrar.nuggetMap.getValue(material), material.getLocalizedName() + " Nugget");

			// Dusts
			if (processedType.contains("dust"))
				add(EERegistrar.dustMap.getValue(material), material.getLocalizedName() + " Dust");

			// Plates
			if (processedType.contains("plate"))
				add(EERegistrar.plateMap.getValue(material), material.getLocalizedName() + " Plate");

			// Gears
			if (processedType.contains("gear"))
				add(EERegistrar.gearMap.getValue(material), material.getLocalizedName() + " Gear");

			// Rods
			if (processedType.contains("rod"))
				add(EERegistrar.rodMap.getValue(material), material.getLocalizedName() + " Rod");

			// Swords
			if (processedType.contains("sword"))
				add(EERegistrar.swordMap.getValue(material), material.getLocalizedName() + " Sword");

			// Pickaxes
			if (processedType.contains("pickaxe"))
				add(EERegistrar.pickaxeMap.getValue(material), material.getLocalizedName() + " Pickaxe");

			// Axes
			if (processedType.contains("axe"))
				add(EERegistrar.axeMap.getValue(material), material.getLocalizedName() + " Axe");

			// Shovels
			if (processedType.contains("shovel"))
				add(EERegistrar.shovelMap.getValue(material), material.getLocalizedName() + " Shovel");

			// Hoes
			if (processedType.contains("hoe"))
				add(EERegistrar.hoeMap.getValue(material), material.getLocalizedName() + " Hoe");

			// Paxels
			if (processedType.contains("paxel"))
				add(EERegistrar.paxelMap.getValue(material), material.getLocalizedName() + " Paxel");

			// Shields
			if (processedType.contains("shield"))
				add(EERegistrar.shieldMap.getValue(material), material.getLocalizedName() + " Shield");

			// Raw
			if (processedType.contains("raw")) {
				add(EERegistrar.rawMap.getValue(material), "Raw " + material.getLocalizedName());
				if (processedType.contains("storage_blocks"))
					add(EERegistrar.rawBlockMap.getValue(material), "Block of Raw " + material.getLocalizedName());
			}

			// Fluids
			if (processedType.contains("fluid")) {
				add(EERegistrar.fluidTypeMap.getValue(material).getDescriptionId(), material.getLocalizedName());
				add(EERegistrar.fluidBucketMap.getValue(material), material.getLocalizedName() + " Bucket");
			}

			// Armor
			if (processedType.contains("armor")) {
				// Helmet
				add(EERegistrar.helmetMap.getValue(material), material.getLocalizedName() + " Helmet");
				// Chestplate
				add(EERegistrar.chestplateMap.getValue(material), material.getLocalizedName() + " Chestplate");
				// Leggings
				add(EERegistrar.leggingsMap.getValue(material), material.getLocalizedName() + " Leggings");
				// Boots
				add(EERegistrar.bootsMap.getValue(material), material.getLocalizedName() + " Boots");
			}

			// Storage Blocks
			if (processedType.contains("storage_block")) {
				add(EERegistrar.storageBlockMap.getValue(material), "Block of " + material.getLocalizedName());
				if (material.getProperties().hasOxidization()) {
					add(EERegistrar.exposedBlockMap.getValue(material), "Exposed " + material.getLocalizedName());
					add(EERegistrar.weatheredBlockMap.getValue(material), "Weathered " + material.getLocalizedName());
					add(EERegistrar.oxidizedBlockMap.getValue(material), "Oxidized " + material.getLocalizedName());
					add(EERegistrar.waxedStorageBlockMap.getValue(material), "Waxed Block of " + material.getLocalizedName());
					add(EERegistrar.waxedExposedBlockMap.getValue(material), "Waxed Exposed " + material.getLocalizedName());
					add(EERegistrar.waxedWeatheredBlockMap.getValue(material), "Waxed Weathered " + material.getLocalizedName());
					add(EERegistrar.waxedOxidizedBlockMap.getValue(material), "Waxed Oxidized " + material.getLocalizedName());
				}
			}

			// Cluster
			if (processedType.contains("cluster")) {
				add(EERegistrar.clusterShardBlockMap.getValue(material), "Block of " + material.getLocalizedName() + " Cluster Shard");
				add(EERegistrar.buddingBlockMap.getValue(material), "Budding " + material.getLocalizedName());
				add(EERegistrar.smallBudBlockMap.getValue(material), "Small " + material.getLocalizedName() + " Bud");
				add(EERegistrar.mediumBudBlockMap.getValue(material), "Medium " + material.getLocalizedName() + " Bud");
				add(EERegistrar.largeBudBlockMap.getValue(material), "Large " + material.getLocalizedName() + " Bud");
				add(EERegistrar.clusterBlockMap.getValue(material), material.getLocalizedName() + " Cluster");
				add(EERegistrar.clusterShardMap.getValue(material), material.getLocalizedName() + " Cluster Shard");
			}

			// Ores
			if (processedType.contains("ore")) {
				for (StrataModel strata : registry.getStrata()) {
					if (!material.getStrata().isEmpty() && !material.getStrata().contains(strata.getId())) continue;

					StringBuilder sb = new StringBuilder();
					sb.append(material.getLocalizedName());
					sb.append(" Ore");

					if (!strata.getId().equals("minecraft_stone") && material.getStrata().size() != 1) {
						sb.append(" - ");
						sb.append(strata.getLocalizedName());
					}

					add(EERegistrar.oreBlockTable.get(strata.getId(), material.getId()).get(), sb.toString());

					//TODO: Rework Sample System.
//					if (processedType.contains("sample")) {
//						StringBuilder sb2 = new StringBuilder();
//						sb2.append(material.getLocalizedName());
//						sb2.append(" Rich ");
//						sb2.append(strata.getLocalizedName());
//						if (material.getStrata().isEmpty() || material.getStrata().contains(strata.getId())) {
//							add(EERegistrar.oreSampleBlockTable.get(strata.getId(), material.getId()).get(), sb2.toString());
//						}
//					}
				}
			}
		}
	}

	@Override
	public @NotNull String getName() {
		return "Emendatus Enigmatica Languages: en_us";
	}

	public static @NotNull String getKey(String start, String path) {
		return "%s.%s.%s".formatted(start, Reference.MOD_ID, path);
	}

	@Contract("_, _ -> new")
	public static @NotNull MutableComponent get(String start, String path) {
		return Component.translatable(getKey(start, path));
	}
}