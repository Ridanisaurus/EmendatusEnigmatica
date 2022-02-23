package com.ridanisaurus.emendatusenigmatica.items;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;

public class ToolUtils {
	public static boolean isPickaxe(ItemStack stack) {
		return stack.getItem() instanceof PickaxeItem;
	}
}
