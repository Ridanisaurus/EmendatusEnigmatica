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

package com.ridanisaurus.emendatusenigmatica.loader.parser.model;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.Optional;

public class MaterialArmorModel {
	public static final Codec<MaterialArmorModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.FLOAT.optionalFieldOf("toughness").forGetter(i -> Optional.of(i.toughness)),
			Codec.FLOAT.optionalFieldOf("knockback").forGetter(i -> Optional.of(i.knockback)),
			Codec.INT.optionalFieldOf("enchantability").forGetter(i -> Optional.of(i.enchantability)),
			ArmorModel.CODEC.optionalFieldOf("helmet").forGetter(i -> Optional.of(i.helmet)),
			ArmorModel.CODEC.optionalFieldOf("chestplate").forGetter(i -> Optional.of(i.chestplate)),
			ArmorModel.CODEC.optionalFieldOf("leggings").forGetter(i -> Optional.of(i.leggings)),
			ArmorModel.CODEC.optionalFieldOf("boots").forGetter(i -> Optional.of(i.boots))
	).apply(x, (toughness, knockback, enchantability, helmet, chestplate, leggings, boots) -> new MaterialArmorModel(
			toughness.orElse(0.0f),
			knockback.orElse(0.0f),
			enchantability.orElse(0),
			helmet.orElse(new ArmorModel()),
			chestplate.orElse(new ArmorModel()),
			leggings.orElse(new ArmorModel()),
			boots.orElse(new ArmorModel())
	)));

	private final float toughness;
	private final float knockback;
	private final int enchantability;
	private final ArmorModel helmet;
	private final ArmorModel chestplate;
	private final ArmorModel leggings;
	private final ArmorModel boots;

	public MaterialArmorModel(float toughness, float knockback, int enchantability, ArmorModel helmet, ArmorModel chestplate, ArmorModel leggings, ArmorModel boots) {
		this.toughness = toughness;
		this.knockback = knockback;
		this.enchantability = enchantability;
		this.helmet = helmet;
		this.chestplate = chestplate;
		this.leggings = leggings;
		this.boots = boots;
	}

	public MaterialArmorModel() {
		this.toughness = 0.0f;
		this.knockback = 0.0f;
		this.enchantability = 0;
		this.helmet = new ArmorModel();
		this.chestplate = new ArmorModel();
		this.leggings = new ArmorModel();
		this.boots = new ArmorModel();
	}


	public float getToughness() {
		return toughness;
	}

	public float getKnockback() {
		return knockback;
	}

	public int getEnchantability() {
		return enchantability;
	}

	public ArmorModel getHelmet() {
		return helmet;
	}

	public ArmorModel getChestplate() {
		return chestplate;
	}

	public ArmorModel getLeggings() {
		return leggings;
	}

	public ArmorModel getBoots() {
		return boots;
	}
}