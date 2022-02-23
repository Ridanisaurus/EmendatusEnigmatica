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

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.tuple.Pair;

import java.util.function.Function;

// Credit: Ellpeck - https://github.com/Ellpeck/PrettyPipes/blob/main/src/main/java/de/ellpeck/prettypipes/Utility.java#L71-L112

public class BlockEntityHelper {

  public static ItemStack transferStackInSlot(AbstractContainerMenu container, IMergeItemStack merge, Player player, int slotIndex, Function<ItemStack, Pair<Integer, Integer>> predicate) {
    int inventoryStart = (int) container.slots.stream().filter(slot -> slot.container != player.getInventory()).count();
    int inventoryEnd = inventoryStart + 26;
    int hotbarStart = inventoryEnd + 1;
    int hotbarEnd = hotbarStart + 8;

    Slot slot = container.slots.get(slotIndex);
    if (slot != null && slot.hasItem()) {
      ItemStack newStack = slot.getItem();
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
        slot.set(ItemStack.EMPTY);
      } else {
        slot.setChanged();
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
