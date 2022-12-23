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

public class MaterialToolsModel {
	public static final Codec<MaterialToolsModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.FLOAT.optionalFieldOf("attackDamage").forGetter(i -> Optional.of(i.attackDamage)),
			Codec.INT.optionalFieldOf("level").forGetter(i -> Optional.of(i.level)),
			Codec.INT.optionalFieldOf("enchantability").forGetter(i -> Optional.of(i.enchantability)),
			Codec.INT.optionalFieldOf("durability").forGetter(i -> Optional.of(i.durability)),
			Codec.FLOAT.optionalFieldOf("efficiency").forGetter(i -> Optional.of(i.efficiency)),
			ToolModel.CODEC.optionalFieldOf("sword").forGetter(i -> Optional.of(i.sword)),
			ToolModel.CODEC.optionalFieldOf("pickaxe").forGetter(i -> Optional.of(i.pickaxe)),
			ToolModel.CODEC.optionalFieldOf("axe").forGetter(i -> Optional.of(i.axe)),
			ToolModel.CODEC.optionalFieldOf("shovel").forGetter(i -> Optional.of(i.shovel)),
			ToolModel.CODEC.optionalFieldOf("hoe").forGetter(i -> Optional.of(i.hoe)),
			ToolModel.CODEC.optionalFieldOf("paxel").forGetter(i -> Optional.of(i.paxel))
	).apply(x, (attackDamage, level, enchantability, durability, efficiency, sword, pickaxe, axe, shovel, hoe, paxel) -> new MaterialToolsModel(
			attackDamage.orElse(1.0f),
			level.orElse(1),
			enchantability.orElse(100),
			durability.orElse(100),
			efficiency.orElse(1.0f),
			sword.orElse(new ToolModel()),
			pickaxe.orElse(new ToolModel()),
			axe.orElse(new ToolModel()),
			shovel.orElse(new ToolModel()),
			hoe.orElse(new ToolModel()),
			paxel.orElse(new ToolModel())
	)));

	private final float attackDamage;
	private final int level;
	private final int enchantability;
	private final int durability;
	private final float efficiency;
	private final ToolModel sword;
	private final ToolModel pickaxe;
	private final ToolModel axe;
	private final ToolModel shovel;
	private final ToolModel hoe;
	private final ToolModel paxel;

	public MaterialToolsModel(float attackDamage, int level, int enchantability, int durability, float efficiency, ToolModel sword, ToolModel pickaxe, ToolModel axe, ToolModel shovel, ToolModel hoe, ToolModel paxel) {
		this.attackDamage = attackDamage;
		this.level = level;
		this.enchantability = enchantability;
		this.durability = durability;
		this.efficiency = efficiency;
		this.sword = sword;
		this.pickaxe = pickaxe;
		this.axe = axe;
		this.shovel = shovel;
		this.hoe = hoe;
		this.paxel = paxel;
	}

	public MaterialToolsModel() {
		this.attackDamage = 1.0f;
		this.level = 1;
		this.enchantability = 100;
		this.durability = 100;
		this.efficiency = 1.0f;
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

	public int getDurability() {
		return durability;
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