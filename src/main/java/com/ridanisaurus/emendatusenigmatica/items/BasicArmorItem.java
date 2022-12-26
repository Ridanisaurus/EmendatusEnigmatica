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

package com.ridanisaurus.emendatusenigmatica.items;

import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BasicArmorItem extends ArmorItem {
	private final MaterialModel material;
	public final int highlight2;
	public final int highlight1;
	public final int base;
	public final int shadow1;
	public final int shadow2;
	public final boolean isSet;
	public final MobEffect effect;
	public final int duration;

	public BasicArmorItem(MaterialModel material, ArmorMaterial tier, EquipmentSlot slot, int highlight2, int highlight1, int base, int shadow1, int shadow2) {
		super(tier, slot, new Properties().tab(EmendatusEnigmatica.TAB));
		this.material = material;
		this.highlight2 = highlight2;
		this.highlight1 = highlight1;
		this.base = base;
		this.shadow1 = shadow1;
		this.shadow2 = shadow2;
		this.isSet = material.getArmor().isSetArmor();
		this.effect = material.getArmor().getEffect();
		this.duration = material.getArmor().getDuration();
	}

	@Override
	public void onArmorTick(ItemStack stack, Level level, Player player) {
		super.onArmorTick(stack, level, player);
		if (isSet && isSetActive(player)) {
			player.addEffect(new MobEffectInstance(effect, duration, 0, true, true));
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
		Player player = Minecraft.getInstance().player;
		if (player != null && isSet) {
			ItemStack[] setPieces = getSet();
			components.add(Component.literal(this.material.getArmor().getSetName())
					.append(" (" + getPiecesEquipped(player) + "/" + setPieces.length + ") ").withStyle(ChatFormatting.AQUA));
			if (!Screen.hasShiftDown()) {
				components.add(Component.literal("Press [SHIFT] for more info").withStyle(ChatFormatting.DARK_GRAY));
			}
			if (Screen.hasShiftDown()) {
				for (int i = 0; i < setPieces.length; i++) {
					components.add(setPieces[i].getHoverName().plainCopy().withStyle((hasSetPiece(player, EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, setPieces.length - i - 1)) ? ChatFormatting.GOLD : ChatFormatting.DARK_GRAY)));
				}
				components.add(Component.literal(this.material.getArmor().getSetDesc()).withStyle(isSetActive(player) ? ChatFormatting.GOLD : ChatFormatting.DARK_GRAY));
			}
		}
	}

	public boolean isSetActive(Player player) {
		return player.getItemBySlot(EquipmentSlot.HEAD).getItem() == EERegistrar.helmetMap.get(this.material.getId()).get() &&
				player.getItemBySlot(EquipmentSlot.CHEST).getItem() == EERegistrar.chestplateMap.get(this.material.getId()).get() &&
				player.getItemBySlot(EquipmentSlot.LEGS).getItem() == EERegistrar.leggingsMap.get(this.material.getId()).get() &&
				player.getItemBySlot(EquipmentSlot.FEET).getItem() == EERegistrar.bootsMap.get(this.material.getId()).get();
	}

	public ItemStack[] getSet() {
		return new ItemStack[]{
				new ItemStack(EERegistrar.helmetMap.get(this.material.getId()).get()),
				new ItemStack(EERegistrar.chestplateMap.get(this.material.getId()).get()),
				new ItemStack(EERegistrar.leggingsMap.get(this.material.getId()).get()),
				new ItemStack(EERegistrar.bootsMap.get(this.material.getId()).get())
		};
	}

	public boolean hasSetPiece(Player player, EquipmentSlot slotType) {
		ItemStack stack = player.getItemBySlot(slotType);
		return switch (slotType) {
			case HEAD -> stack.getItem() == EERegistrar.helmetMap.get(this.material.getId()).get();
			case CHEST -> stack.getItem() == EERegistrar.chestplateMap.get(this.material.getId()).get();
			case LEGS -> stack.getItem() == EERegistrar.leggingsMap.get(this.material.getId()).get();
			case FEET -> stack.getItem() == EERegistrar.bootsMap.get(this.material.getId()).get();
			default -> false;
		};
	}

	private int getPiecesEquipped(Player player) {
		int pieces = 0;
		for (EquipmentSlot slotType : EquipmentSlot.values()) {
			if (slotType.getType() == EquipmentSlot.Type.ARMOR && hasSetPiece(player, slotType)) {
				pieces++;
			}
		}
		return pieces;
	}
}