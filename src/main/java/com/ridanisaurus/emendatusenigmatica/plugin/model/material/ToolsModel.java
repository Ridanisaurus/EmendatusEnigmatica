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
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.NumberRangeValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.material.tools.ToolValidator;

public class ToolsModel {
	public static final Codec<ToolsModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.FLOAT.optionalFieldOf("attackDamage", 0f).forGetter(i -> i.attackDamage),
			Codec.INT.optionalFieldOf("level", 0).forGetter(i -> i.level),
			Codec.INT.optionalFieldOf("enchantability", 0).forGetter(i -> i.enchantability),
			Codec.FLOAT.optionalFieldOf("efficiency", 0f).forGetter(i -> i.efficiency),
			ToolModel.CODEC.optionalFieldOf("sword", new ToolModel()).forGetter(i -> i.sword),
			ToolModel.CODEC.optionalFieldOf("pickaxe", new ToolModel()).forGetter(i -> i.pickaxe),
			ToolModel.CODEC.optionalFieldOf("axe", new ToolModel()).forGetter(i -> i.axe),
			ToolModel.CODEC.optionalFieldOf("shovel", new ToolModel()).forGetter(i -> i.shovel),
			ToolModel.CODEC.optionalFieldOf("hoe", new ToolModel()).forGetter(i -> i.hoe),
			ToolModel.CODEC.optionalFieldOf("paxel", new ToolModel()).forGetter(i -> i.paxel)
	).apply(x, ToolsModel::new));

	private final int level;
	private final int enchantability;
	private final float attackDamage;
	private final float efficiency;
	private final ToolModel sword;
	private final ToolModel pickaxe;
	private final ToolModel axe;
	private final ToolModel shovel;
	private final ToolModel hoe;
	private final ToolModel paxel;

	public static final ValidationManager VALIDATION_MANAGER = ValidationManager.create()
		.addValidator("enchantability", new NumberRangeValidator(Types.INTEGER, 0, Integer.MAX_VALUE, true))
		.addValidator("level",		new NumberRangeValidator(Types.INTEGER, 0, Integer.MAX_VALUE, true))
		.addValidator("attackDamage",	new NumberRangeValidator(Types.FLOAT, 0, Float.MAX_VALUE, true))
		.addValidator("efficiency",	new NumberRangeValidator(Types.FLOAT, 0, Float.MAX_VALUE, true))
		.addValidator("sword",		new ToolValidator("sword"))
		.addValidator("pickaxe",		new ToolValidator("pickaxe"))
		.addValidator("axe",			new ToolValidator("axe"))
		.addValidator("shovel",		new ToolValidator("shovel"))
		.addValidator("hoe",			new ToolValidator("hoe"))
		.addValidator("paxel",		new ToolValidator("paxel"));

	public ToolsModel(float attackDamage, int level, int enchantability, float efficiency, ToolModel sword, ToolModel pickaxe, ToolModel axe, ToolModel shovel, ToolModel hoe, ToolModel paxel) {
		this.attackDamage = attackDamage;
		this.level = level;
		this.enchantability = enchantability;
		this.efficiency = efficiency;
		this.sword = sword;
		this.pickaxe = pickaxe;
		this.axe = axe;
		this.shovel = shovel;
		this.hoe = hoe;
		this.paxel = paxel;
	}

	public ToolsModel() {
		this.attackDamage = 0.0f;
		this.level = 0;
		this.enchantability = 0;
		this.efficiency = 0.0f;
		this.sword = new ToolModel();
		this.pickaxe = new ToolModel();
		this.axe = new ToolModel();
		this.shovel = new ToolModel();
		this.hoe = new ToolModel();
		this.paxel = new ToolModel();
	}

	public float getAttackDamage() {
		return attackDamage;
	}

	public int getLevel() {
		return level;
	}

	public int getEnchantability() {
		return enchantability;
	}

	public float getEfficiency() {
		return efficiency;
	}

	public ToolModel getSword() {
		return sword;
	}

	public ToolModel getPickaxe() {
		return pickaxe;
	}

	public ToolModel getAxe() {
		return axe;
	}

	public ToolModel getShovel() {
		return shovel;
	}

	public ToolModel getHoe() {
		return hoe;
	}

	public ToolModel getPaxel() {
		return paxel;
	}
}