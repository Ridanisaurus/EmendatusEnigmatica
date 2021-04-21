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

package com.ridanisaurus.emendatusenigmatica.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.server.ServerWorld;
import org.apache.commons.lang3.tuple.Pair;

import java.util.function.Function;
import java.util.stream.Stream;

// Credit: Ellpeck - https://github.com/Ellpeck/PrettyPipes/blob/main/src/main/java/de/ellpeck/prettypipes/Utility.java#L71-L112

public class TileEntityHelper {
	public static ItemStack transferStackInSlot(Container container, IMergeItemStack merge, PlayerEntity player, int slotIndex, Function<ItemStack, Pair<Integer, Integer>> predicate) {
		int inventoryStart = (int) container.inventorySlots.stream().filter(slot -> slot.inventory != player.inventory).count();
		int inventoryEnd = inventoryStart + 26;
		int hotbarStart = inventoryEnd + 1;
		int hotbarEnd = hotbarStart + 8;

		Slot slot = container.inventorySlots.get(slotIndex);
		if (slot != null && slot.getHasStack()) {
			ItemStack newStack = slot.getStack();
			ItemStack currentStack = newStack.copy();

			if (slotIndex >= inventoryStart) {
				// shift into this container here
				// mergeItemStack with the slots that newStack should go into
				// return an empty stack if mergeItemStack fails
				Pair<Integer, Integer> slots = predicate.apply(newStack);
				if (slots != null) {
					if (!merge.mergeItemStack(newStack, slots.getLeft(), slots.getRight(), false))
						return ItemStack.EMPTY;
				}
				// end custom code
				else if (slotIndex <= inventoryEnd) {
					if (!merge.mergeItemStack(newStack, hotbarStart, hotbarEnd + 1, false))
						return ItemStack.EMPTY;
				} else if (slotIndex >= inventoryEnd + 1 && slotIndex < hotbarEnd + 1 && !merge.mergeItemStack(newStack, inventoryStart, inventoryEnd + 1, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!merge.mergeItemStack(newStack, inventoryStart, hotbarEnd + 1, false)) {
				return ItemStack.EMPTY;
			}
			if (newStack.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
			if (newStack.getCount() == currentStack.getCount())
				return ItemStack.EMPTY;
			slot.onTake(player, newStack);
			return currentStack;
		}
		return ItemStack.EMPTY;
	}

	public interface IMergeItemStack {
		boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection);
	}
}
