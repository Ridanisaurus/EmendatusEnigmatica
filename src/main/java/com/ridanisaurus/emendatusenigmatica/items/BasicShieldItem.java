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
import com.ridanisaurus.emendatusenigmatica.renderers.ShieldTextureRenderer;
import com.ridanisaurus.emendatusenigmatica.util.Reference;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class BasicShieldItem extends ShieldItem {

	public final int highlight3;
	public final int highlight2;
	public final int highlight1;
	public final int base;
	public final int shadow1;
	public final TagKey<Item> repairItem;
	public final MaterialModel material;

	public BasicShieldItem(MaterialModel material, TagKey<Item> repairItem) {
		super(new Properties().tab(EmendatusEnigmatica.TAB).durability(material.getArmor().getShield().getDurability()));
		this.highlight3 = material.getColors().getHighlightColor(3);
		this.highlight2 = material.getColors().getHighlightColor(2);
		this.highlight1 = material.getColors().getHighlightColor(1);
		this.base = material.getColors().getMaterialColor();
		this.shadow1 = material.getColors().getShadowColor(1);
		this.repairItem = repairItem;
		this.material = material;
	}

	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer) {
		consumer.accept(new IClientItemExtensions() {
			@Override
			public BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return ShieldTextureRenderer.RENDERER;
			}
		});
	}

	public Ingredient getRepairMaterial() {
		return Ingredient.of(repairItem);
	}

	@Override
	public int getMaxDamage(ItemStack stack) {
		return material.getArmor().getShield().getDurability();
	}

	@Override
	public boolean canBeDepleted() {
		return material.getArmor().getShield().getDurability() > 0;
	}

	@Override
	public boolean isValidRepairItem(@NotNull ItemStack toRepair, @NotNull ItemStack repair) {
		return getRepairMaterial().test(repair);
	}

	@Override
	public int getEnchantmentValue() {
		return material.getArmor().getEnchantability();
	}

	public MaterialModel getMaterialModel() {
		return material;
	}

	@Override
	public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
		if (material.getColors().getMaterialColor() == -1) {
			return super.getArmorTexture(stack, entity, slot, type);
		} else {
			return new ResourceLocation(Reference.MOD_ID, "textures/armor/empty.png").toString();
		}
	}

	public int getColorForIndex(int index){
		switch (index){
			case 0: return material.getColors().getHighlightColor(3);
			case 1: return material.getColors().getHighlightColor(2);
			case 2: return material.getColors().getHighlightColor(1);
			case 4: return material.getColors().getShadowColor(1);
			default: material.getColors().getMaterialColor();
		};
		return material.getColors().getMaterialColor();
	}
}