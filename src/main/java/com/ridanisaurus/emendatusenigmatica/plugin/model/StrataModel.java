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
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.FilterMode;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.*;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.deprecation.DeprecatedFieldValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.registry.BlockRegistryValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.registry.TextureRegistryValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.DataRegistry;
import com.ridanisaurus.emendatusenigmatica.plugin.VanillaPlugin;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.strata.SuffixValidator;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class StrataModel {
	public static final Codec<StrataModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.fieldOf("id").forGetter(i -> i.id),
			ResourceLocation.CODEC.fieldOf("baseTexture").forGetter(i -> i.baseTexture),
			Codec.STRING.fieldOf("suffix").forGetter(i -> i.suffix),
			ResourceLocation.CODEC.fieldOf("fillerType").forGetter(i -> i.fillerType),
			Codec.STRING.fieldOf("localizedName").forGetter(i -> i.localizedName),
			Codec.STRING.optionalFieldOf("harvestTool", "pickaxe").forGetter(i -> i.harvestTool),
			Codec.FLOAT.optionalFieldOf("hardness", 3f).forGetter(i -> i.hardness),
			Codec.FLOAT.optionalFieldOf("resistance", 3f).forGetter(i -> i.resistance)
	).apply(x, StrataModel::new));

	public static final ValidationManager VALIDATION_MANAGER = ValidationManager.create()
		.addValidator("id",				new PluginRegistryValidator<>(VanillaPlugin.class, DataRegistry::isStrataRegistered, PluginRegistryValidator.REGISTRATION_MODE, true))
		.addValidator("baseTexture",		new ResourceLocationValidator(true, new TextureRegistryValidator()))
		.addValidator("fillerType",		new ResourceLocationValidator(true, new BlockRegistryValidator()))
		.addValidator("suffix",			new SuffixValidator())
		.addValidator("localizedName",	new TypeValidator(Types.STRING, true))
		.addValidator("sampleStrata",		new TypeValidator(Types.BOOLEAN, false))
		.addValidator("hardness",			new NumberRangeValidator(Types.FLOAT, 0, Float.MAX_VALUE, false))
		.addValidator("resistance",		new NumberRangeValidator(Types.FLOAT, 0, Float.MAX_VALUE, false))
		.addValidator("harvestTool",		new ValuesValidator(List.of(
			"pickaxe",
			"axe",
			"hoe",
			"shovel"
		), FilterMode.WHITELIST, false))
		.addValidator("sampleStrata", new DeprecatedFieldValidator(null));

	private final String id;
	private final ResourceLocation baseTexture;
	private final String suffix;
	private final ResourceLocation fillerType;
	private final String localizedName;
	private final String harvestTool;
	private final float hardness;
	private final float resistance;

	public StrataModel(String id, ResourceLocation baseTexture, String suffix, ResourceLocation fillerType, String localizedName, String harvestTool, float hardness, float resistance) {
		this.id = id;
		this.baseTexture = baseTexture;
		this.suffix = suffix;
		this.fillerType = fillerType;
		this.localizedName = localizedName;
		this.harvestTool = harvestTool;
		this.hardness = hardness;
		this.resistance = resistance;
	}

	public String getId() {
		return id;
	}

	public ResourceLocation getBaseTexture() {
		return baseTexture;
	}

	public String getSuffix() {
		return suffix;
	}

	public ResourceLocation getFillerType() {
		return fillerType;
	}

	public String getLocalizedName() {
		return localizedName;
	}

	public String getHarvestTool() {
		return harvestTool;
	}

	public float getHardness() {
		return hardness;
	}

	public float getResistance() {
		return resistance;
	}

	public void register(DataRegistry registry) {
		registry.registerStrata(this);
	}
}
