/*
 * MIT License
 *
 * Copyright (c) 2020 Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.items;

import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.registries.EETags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class BasicPaxelItem extends PaxelItem {
	public final int highlight2;
	public final int highlight1;
	public final int base;
	public final int shadow1;

	public BasicPaxelItem(MaterialModel material, TagKey<Item> repairItem) {
		super(
				new ToolTier(
						material.getTools().getLevel(),
						material.getTools().getPaxel().getDurability(),
						material.getTools().getEfficiency(),
						material.getTools().getAttackDamage(),
						material.getTools().getEnchantability(),
						EETags.MINEABLE_WITH_PAXEL,
						() -> Ingredient.of(repairItem)
				),
				material.getTools().getPaxel().getDamage(),
				material.getTools().getPaxel().getSpeed(),
				new Properties().tab(EmendatusEnigmatica.TAB)
		);
		this.highlight2 = material.getColors().getHighlightColor(3);
		this.highlight1 = material.getColors().getHighlightColor(1);
		this.base = material.getColors().getMaterialColor();
		this.shadow1 = material.getColors().getShadowColor(1);
	}
}