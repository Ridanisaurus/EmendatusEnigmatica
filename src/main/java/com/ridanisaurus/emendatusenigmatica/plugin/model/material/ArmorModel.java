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

package com.ridanisaurus.emendatusenigmatica.plugin.model.material;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.ArrayPolicy;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.NumberRangeValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.TypeValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.material.armor.ArmorValidator;

import java.util.*;

public class ArmorModel {
	public static final Codec<ArmorModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.BOOL.optionalFieldOf("setArmor", false).forGetter(i -> i.setArmor),
			Codec.list(EffectModel.CODEC).optionalFieldOf("effects", List.of()).forGetter(i -> i.effects),
			Codec.STRING.optionalFieldOf("setName", "").forGetter(i -> i.setName),
			Codec.STRING.optionalFieldOf("setDesc", "").forGetter(i -> i.setDesc),
			Codec.FLOAT.optionalFieldOf("toughness",0f).forGetter(i -> i.toughness),
			Codec.FLOAT.optionalFieldOf("knockback", 0f).forGetter(i -> i.knockback),
			Codec.INT.optionalFieldOf("enchantability", 0).forGetter(i -> i.enchantability),
			ArmorPieceModel.CODEC.optionalFieldOf("helmet", new ArmorPieceModel()).forGetter(i -> i.helmet),
			ArmorPieceModel.CODEC.optionalFieldOf("chestplate", new ArmorPieceModel()).forGetter(i -> i.chestplate),
			ArmorPieceModel.CODEC.optionalFieldOf("leggings", new ArmorPieceModel()).forGetter(i -> i.leggings),
			ArmorPieceModel.CODEC.optionalFieldOf("boots", new ArmorPieceModel()).forGetter(i -> i.boots),
			ArmorPieceModel.CODEC.optionalFieldOf("shield", new ArmorPieceModel()).forGetter(i -> i.shield)
	).apply(x, ArmorModel::new));

	private final boolean setArmor;
	private final List<EffectModel> effects;
	private final String setName;
	private final String setDesc;
	private final float toughness;
	private final float knockback;
	private final int enchantability;
	private final ArmorPieceModel helmet;
	private final ArmorPieceModel chestplate;
	private final ArmorPieceModel leggings;
	private final ArmorPieceModel boots;
	private final ArmorPieceModel shield;

	public static final ValidationManager VALIDATION_MANAGER = ValidationManager.create()
		.addValidator("enchantability", new NumberRangeValidator(Types.INTEGER, 0, Integer.MAX_VALUE, false))
		.addValidator("toughness",	new NumberRangeValidator(Types.FLOAT, 0, Float.MAX_VALUE, false))
		.addValidator("knockback",	new NumberRangeValidator(Types.FLOAT, 0, Float.MAX_VALUE, false))
		.addValidator("setName",		new TypeValidator(Types.STRING, false))
		.addValidator("setDesc",		new TypeValidator(Types.STRING, false))
		.addValidator("setArmor",		new TypeValidator(Types.BOOLEAN, false))
		.addValidator("helmet",   	new ArmorValidator())
		.addValidator("chestplate", 	new ArmorValidator())
		.addValidator("leggings",   	new ArmorValidator())
		.addValidator("boots",  		new ArmorValidator())
		.addValidator("shield",     	new ArmorValidator("shield"))
		.addValidator("effects",		EffectModel.VALIDATION_MANAGER.getAsValidator(false), ArrayPolicy.REQUIRES_ARRAY.get());

	public ArmorModel(boolean setArmor, List<EffectModel> effects, String setName, String setDesc,
					  float toughness, float knockback, int enchantability,
					  ArmorPieceModel helmet, ArmorPieceModel chestplate, ArmorPieceModel leggings, ArmorPieceModel boots, ArmorPieceModel shield) {
		this.setArmor = setArmor;
		this.effects = effects;
		this.setName = setName;
		this.setDesc = setDesc;
		this.toughness = toughness;
		this.knockback = knockback;
		this.enchantability = enchantability;
		this.helmet = helmet;
		this.chestplate = chestplate;
		this.leggings = leggings;
		this.boots = boots;
		this.shield = shield;
	}

	public ArmorModel() {
		this.setArmor = false;
		this.effects = Collections.emptyList();
		this.setName = "";
		this.setDesc = "";
		this.toughness = 0.0f;
		this.knockback = 0.0f;
		this.enchantability = 0;
		this.helmet = new ArmorPieceModel();
		this.chestplate = new ArmorPieceModel();
		this.leggings = new ArmorPieceModel();
		this.boots = new ArmorPieceModel();
		this.shield = new ArmorPieceModel();
	}

	public boolean isSetArmor() {
		return setArmor;
	}
	public List<EffectModel> getEffects() {
		return effects;
	}

	public String getSetName() {
		return setName;
	}

	public String getSetDesc() {
		return setDesc;
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

	public ArmorPieceModel getHelmet() {
		return helmet;
	}

	public ArmorPieceModel getChestplate() {
		return chestplate;
	}

	public ArmorPieceModel getLeggings() {
		return leggings;
	}

	public ArmorPieceModel getBoots() {
		return boots;
	}

	public ArmorPieceModel getShield() {
		return shield;
	}
}