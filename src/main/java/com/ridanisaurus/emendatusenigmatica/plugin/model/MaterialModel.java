/*
 * MIT License
 *
 * Copyright (c) 2020-2026. Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.plugin.model;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayPolicy;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.FilterMode;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.PluginRegistryValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.TypeValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.ValuesValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.deprecation.DeprecatedFieldValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.DataRegistry;
import com.ridanisaurus.emendatusenigmatica.plugin.VanillaPlugin;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.ArmorModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.ColorsModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.OreDropModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.PropertiesModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.ToolsModel;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.material.*;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.material.armor.ArmorFieldValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.material.tools.ToolsFieldValidator;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.ItemLike;
import java.util.*;

public class MaterialModel {
	public static final Codec<MaterialModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.fieldOf("id").forGetter(i -> i.id),
			Codec.STRING.fieldOf("source").forGetter(i -> i.source),
			Codec.STRING.fieldOf("localizedName").forGetter(i -> i.localizedName),
			Codec.list(Codec.STRING).fieldOf("processedTypes").forGetter(i -> i.processedTypes),
			Codec.list(Codec.STRING).optionalFieldOf("strata", List.of()).forGetter(i -> i.strata),
			PropertiesModel.CODEC.optionalFieldOf("properties", new PropertiesModel()).forGetter(i -> i.properties),
			OreDropModel.CODEC.optionalFieldOf("oreDrop", new OreDropModel()).forGetter(i -> i.oreDrop),
			ColorsModel.CODEC.optionalFieldOf("colors", new ColorsModel()).forGetter(i -> i.colors),
			ToolsModel.CODEC.optionalFieldOf("tools", new ToolsModel()).forGetter(i -> i.tools),
			com.ridanisaurus.emendatusenigmatica.plugin.model.material.ArmorModel.CODEC.optionalFieldOf("armor", new com.ridanisaurus.emendatusenigmatica.plugin.model.material.ArmorModel()).forGetter(i -> i.armor)
	).apply(x, MaterialModel::new));

	public static final ValidationManager VALIDATION_MANAGER = ValidationManager.create()
		.addValidator("strata",				new PluginRegistryValidator<>(VanillaPlugin.class, DataRegistry::isStrataRegistered, PluginRegistryValidator.REFERENCE_MODE, "Strata", false), ArrayPolicy.REQUIRES_ARRAY.getNonEmpty())
		//TODO: Add validation for when source is "vanilla" to enforce vanilla-ids
		.addValidator("id",					new PluginRegistryValidator<>(VanillaPlugin.class, DataRegistry::isMaterialRegistered, PluginRegistryValidator.REGISTRATION_MODE, true))
		.addValidator("source",				new ValuesValidator(List.of("vanilla", "modded"), FilterMode.WHITELIST, true))
		.addValidator("localizedName",		new TypeValidator(Types.STRING, true))
		.addValidator("processedTypes",		new ProcessedTypesValidator(), ArrayPolicy.REQUIRES_ARRAY.getNonEmpty())
		.addValidator("tools",				new ToolsFieldValidator())
		.addValidator("armor",				new ArmorFieldValidator())
		.addValidator("oreDrop",				new OreDropValidator())
		.addValidator("properties",			PropertiesModel.VALIDATION_MANAGER.getAsValidator(false))
		.addValidator("colors",				ColorsModel.VALIDATION_MANAGER.getAsValidator(false))
		.addValidator("compat",				new DeprecatedFieldValidator())
		.addValidator("disableDefaultOre",	new DeprecatedFieldValidator("Configuration Option", "https://github.com/Ridanisaurus/EmendatusEnigmatica/commit/a782b78a1b2c87ec679ee42235cad0e8b1658679"));


	private final String id;
	private final String source;
	private final String localizedName;
	private final List<String> processedTypes;
	private final List<String> strata;
	private final PropertiesModel properties;
	private final OreDropModel oreDrop;
	private final ColorsModel colors;
	private final ToolsModel tools;
	private final com.ridanisaurus.emendatusenigmatica.plugin.model.material.ArmorModel armor;

	public MaterialModel(
		String id,
		String source,
		String localizedName,
		List<String> processedTypes,
		List<String> strata,
		PropertiesModel properties,
		OreDropModel oreDrop,
		ColorsModel colors,
		ToolsModel tools,
		com.ridanisaurus.emendatusenigmatica.plugin.model.material.ArmorModel armor
	) {
		this.id = id;
		this.source = source;
		this.localizedName = localizedName;
		this.processedTypes = processedTypes;
		this.strata = strata;
		this.properties = properties;
		this.oreDrop = oreDrop;
		this.colors = colors;
		this.tools = tools;
		this.armor = armor;
	}

	public String getId() {
		return id;
	}

	public String getSource() {
		return source;
	}

	public boolean isModded() {
		return !getSource().equals("vanilla");
	}

	public boolean isVanilla() {
		return getSource().equals("vanilla");
	}

	public String getLocalizedName() {
		return localizedName;
	}

	public List<String> getProcessedTypes() {
		return processedTypes;
	}

	public List<String> getStrata() {
		return strata;
	}

	public PropertiesModel getProperties() {
		return properties;
	}

	public OreDropModel getOreDrop() {
		return oreDrop;
	}

	public ColorsModel getColors() {
		return colors;
	}

	public ToolsModel getTools() {
		return tools;
	}

	public ArmorModel getArmor() {
		return armor;
	}

	public ItemLike getOreDefaultDrop() {
		if (processedTypes.contains("ore")) {
			if (properties.getMaterialType().equals("gem")) {
				return processedTypes.contains("gem") ? EERegistrar.gemMap.getValue(id) : oreDrop.getDefaultItemDropAsItem();
			} else {
				return processedTypes.contains("raw") ? EERegistrar.rawMap.getValue(id) : oreDrop.getDefaultItemDropAsItem();
			}
		} else {
			return BuiltInRegistries.ITEM.get(Reference.AIR_RS);
		}
	}

	public void register(DataRegistry registry) {
		registry.registerMaterial(this);
	}
}