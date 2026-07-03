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

package com.ridanisaurus.emendatusenigmatica.plugin.model.material;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayPolicy;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.FilterMode;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.TypeValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.ValuesValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.deprecation.DeprecatedFieldValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.DataRegistry;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.EERegistryValidator;
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
			Codec.list(Codec.STRING).optionalFieldOf("strata").forGetter(i -> Optional.of(i.strata)),
			MaterialPropertiesModel.CODEC.optionalFieldOf("properties").forGetter(i -> Optional.of(i.properties)),
			MaterialOreDropModel.CODEC.optionalFieldOf("oreDrop").forGetter(i -> Optional.of(i.oreDrop)),
			MaterialCompatModel.CODEC.optionalFieldOf("compat").forGetter(i -> Optional.of(i.compat)),
			MaterialColorsModel.CODEC.optionalFieldOf("colors").forGetter(i -> Optional.of(i.colors)),
			MaterialToolsModel.CODEC.optionalFieldOf("tools").forGetter(i -> Optional.of(i.tools)),
			MaterialArmorModel.CODEC.optionalFieldOf("armor").forGetter(i -> Optional.of(i.armor))
	).apply(x, (id, source, localizedName, processedTypes, strata, properties, oreDrop, compat, colors, tools, armor) -> new MaterialModel(
			id,
			source,
			localizedName,
			processedTypes,
			strata.orElse(List.of()),
			properties.orElse(new MaterialPropertiesModel()),
			oreDrop.orElse(new MaterialOreDropModel()),
			compat.orElse(new MaterialCompatModel()),
			colors.orElse(new MaterialColorsModel()),
			tools.orElse(new MaterialToolsModel()),
			armor.orElse(new MaterialArmorModel())
	)));

	public static final List<String> REGISTERED_IDS = new ArrayList<>();

	public static final ValidationManager VALIDATION_MANAGER = ValidationManager.create()
		.addValidator("strata",				new EERegistryValidator(StrataModel.REGISTERED_IDS, EERegistryValidator.REFERENCE, "Strata", false), ArrayPolicy.REQUIRES_ARRAY.getNonEmpty())
		.addValidator("id",					new EERegistryValidator(REGISTERED_IDS, EERegistryValidator.REGISTRATION, true))
		.addValidator("source",				new ValuesValidator(List.of("vanilla", "modded"), FilterMode.WHITELIST, true))
		.addValidator("localizedName",		new TypeValidator(Types.STRING, true))
		.addValidator("processedTypes",		new ProcessedTypesValidator(), ArrayPolicy.REQUIRES_ARRAY.getNonEmpty())
		.addValidator("tools",				new ToolsFieldValidator())
		.addValidator("armor",				new ArmorFieldValidator())
		.addValidator("oreDrop",				new OreDropValidator())
		.addValidator("properties",			MaterialPropertiesModel.VALIDATION_MANAGER.getAsValidator(false))
		.addValidator("colors",				MaterialColorsModel.VALIDATION_MANAGER.getAsValidator(false))
		.addValidator("compat",				MaterialCompatModel.VALIDATION_MANAGER.getAsValidator(false))
		.addValidator("disableDefaultOre",	new DeprecatedFieldValidator("Configuration Option", "https://github.com/Ridanisaurus/EmendatusEnigmatica/commit/a782b78a1b2c87ec679ee42235cad0e8b1658679"));


	private final String id;
	private final String source;
	private final String localizedName;
	/**
	 * @deprecated Replaced by configuration option and meant for removal in 2.2.0 Release.
	 */
	@Deprecated(forRemoval = true, since = "2.2.0-Alpha-1")
	private final boolean disableDefaultOre = false;
	private final List<String> processedTypes;
	private final List<String> strata;
	private final MaterialPropertiesModel properties;
	private final MaterialOreDropModel oreDrop;
	private final MaterialCompatModel compat;
	private final MaterialColorsModel colors;
	private final MaterialToolsModel tools;
	private final MaterialArmorModel armor;

	public MaterialModel(
		String id,
		String source,
		String localizedName,
		List<String> processedTypes,
		List<String> strata,
		MaterialPropertiesModel properties,
		MaterialOreDropModel oreDrop,
		MaterialCompatModel compat,
		MaterialColorsModel colors,
		MaterialToolsModel tools,
		MaterialArmorModel armor
	) {
		this.id = id;
		this.source = source;
		this.localizedName = localizedName;
		this.processedTypes = processedTypes;
		this.strata = strata;
		this.properties = properties;
		this.oreDrop = oreDrop;
		this.compat = compat;
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

	/**
	 * @deprecated Replaced by configuration option and meant for removal in 2.2.0 Release.
	 */
	@Deprecated(forRemoval = true, since = "2.2.0-Alpha-1")
	public boolean getDisableDefaultOre() {
		return disableDefaultOre;
	}

	public List<String> getProcessedTypes() {
		return processedTypes;
	}

	public List<String> getStrata() {
		return strata;
	}

	public MaterialPropertiesModel getProperties() {
		return properties;
	}

	public MaterialOreDropModel getOreDrop() {
		return oreDrop;
	}

	public MaterialCompatModel getCompat() {
		return compat;
	}

	public MaterialColorsModel getColors() {
		return colors;
	}

	public MaterialToolsModel getTools() {
		return tools;
	}

	public MaterialArmorModel getArmor() {
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
		REGISTERED_IDS.add(this.id);
		registry.registerMaterial(this);
	}
}