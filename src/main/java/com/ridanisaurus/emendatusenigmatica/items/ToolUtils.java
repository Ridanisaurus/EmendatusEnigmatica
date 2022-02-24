package com.ridanisaurus.emendatusenigmatica.items;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ToolActions;

public class ToolUtils {
	public static boolean isPickaxe(ItemStack stack) {
		return stack.canPerformAction(ToolActions.PICKAXE_DIG);
	}
}
