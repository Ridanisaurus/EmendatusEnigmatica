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
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.NumberRangeValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.TypeValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.MaxValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.material.oredrop.DropValidator;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.util.Optional;

public class MaterialOreDropModel {
	public static final Codec<MaterialOreDropModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.optionalFieldOf("drop").forGetter(i -> Optional.ofNullable(i.drop)),
			Codec.INT.optionalFieldOf("min").forGetter(i -> Optional.of(i.min)),
			Codec.INT.optionalFieldOf("max").forGetter(i -> Optional.of(i.max)),
			Codec.BOOL.optionalFieldOf("uniformCount").forGetter(i -> Optional.of(i.uniformCount))
	).apply(x, (drop, min, max, uniformCount) -> new MaterialOreDropModel(
			drop.orElse(""),
			min.orElse(1),
			max.orElse(1),
			uniformCount.orElse(false)
	)));

	public static final ValidationManager VALIDATION_MANAGER = ValidationManager.create()
		.addValidator("uniformCount",	new TypeValidator(Types.BOOLEAN, false))
		.addValidator("min",	new NumberRangeValidator(Types.INTEGER, 0, 64, false))
		.addValidator("max",	new MaxValidator(Types.INTEGER, 0, 64, false))
		.addValidator("drop",	new DropValidator());

	private final String drop;
	private final int min;
	private final int max;
	private final boolean uniformCount;

	public MaterialOreDropModel(String drop, int min, int max, boolean uniformCount) {
		this.drop = drop;
		this.min = min;
		this.max = max;
		this.uniformCount = uniformCount;
	}

	public MaterialOreDropModel() {
		this.drop = "";
		this.min = 1;
		this.max = 1;
		this.uniformCount = false;
	}

	public String getDrop() {
		return drop;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public boolean isUniformCount() {
		return uniformCount;
	}

	public ItemLike getDefaultItemDropAsItem() {
		return BuiltInRegistries.ITEM.get(ResourceLocation.parse(drop));
	}
}