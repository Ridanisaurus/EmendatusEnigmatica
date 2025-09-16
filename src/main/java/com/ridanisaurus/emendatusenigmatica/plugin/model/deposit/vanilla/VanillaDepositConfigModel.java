/*
 * MIT License
 *
 * Copyright (c) 2024. Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.vanilla;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayPolicy;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.FilterMode;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.NumberRangeValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.RequiredValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.ValuesValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.ModelLoader;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.EERegistryValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.MaxValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit.MaterialValidator;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class VanillaDepositConfigModel {
	public static final Codec<VanillaDepositConfigModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.optionalFieldOf("block").orElse(null).forGetter(it -> Optional.ofNullable(it.block)),
			Codec.STRING.optionalFieldOf("material").orElse(null).forGetter(it -> Optional.ofNullable(it.material)),
			Codec.list(Codec.STRING).fieldOf("fillerTypes").orElse(List.of()).forGetter(it -> it.fillerTypes),
			Codec.INT.fieldOf("chance").orElse(0).forGetter(it -> it.chance),
			Codec.INT.fieldOf("size").orElse(0).forGetter(it -> it.size),
			Codec.INT.fieldOf("minYLevel").orElse(0).forGetter(it -> it.minYLevel),
			Codec.INT.fieldOf("maxYLevel").orElse(0).forGetter(it -> it.maxYLevel),
			Codec.STRING.fieldOf("placement").orElse("uniform").forGetter(it -> it.placement),
			Codec.STRING.fieldOf("rarity").orElse("common").forGetter(it -> it.rarity)
	).apply(x, (s, s2, l, i, i2, i3, i4, s3, s4) -> new VanillaDepositConfigModel(s.orElse(null), s2.orElse(null), l, i, i2, i3, i4, s3, s4)));

	public static final ValidationManager VALIDATION_MANAGER = ValidationManager.create()
		.addValidator("material",        new MaterialValidator(false, true))
		.addValidator("block",           new RequiredValidator(false))
		.addValidator("fillerTypes",     new EERegistryValidator(ModelLoader.STRATA_IDS, EERegistryValidator.REFERENCE, "Strata", true), ArrayPolicy.REQUIRES_ARRAY.getNonEmpty())
		.addValidator("chance",          new NumberRangeValidator(Types.INTEGER, 1, 100, true))
		.addValidator("size",            new NumberRangeValidator(Types.INTEGER, 1, 16, true))
		.addValidator("minYLevel",       new NumberRangeValidator(Types.INTEGER, -64, 320, true))
		.addValidator("maxYLevel",       new MaxValidator(Types.INTEGER, "minYLevel", -64, 320, true))
		.addValidator("placement",       new ValuesValidator(List.of("uniform", "triangle"), FilterMode.WHITELIST, false))
		.addValidator("rarity",          new ValuesValidator(List.of("common", "rare"), FilterMode.WHITELIST, false));

	public final String block;
	public final String material;
	public final List<String> fillerTypes;
	public final int chance;
	public final int size;
	public final int minYLevel;
	public final int maxYLevel;
	public final String placement;
	public final String rarity;

	public VanillaDepositConfigModel(@Nullable String block, @Nullable String material, List<String> fillerTypes, int chance, int size, int minYLevel, int maxYLevel, String placement, String rarity) {
		this.block = block;
		this.material = material;
		this.fillerTypes = fillerTypes;
		this.chance = chance;
		this.size = size;
		this.minYLevel = minYLevel;
		this.maxYLevel = maxYLevel;
		this.placement = placement;
		this.rarity = rarity;
	}
}
