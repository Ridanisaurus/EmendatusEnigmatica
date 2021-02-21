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

package com.ridanisaurus.emendatusenigmatica.tiles;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class EEItemStackHandler implements IItemHandler, IItemHandlerModifiable, INBTSerializable<CompoundNBT> {
  public final ItemStackHandler internal;

  public EEItemStackHandler(int slots) {
    internal = new ItemStackHandler(slots);
  }

  @Override
  public CompoundNBT serializeNBT() {
    return internal.serializeNBT();
  }

  @Override
  public void deserializeNBT(CompoundNBT nbt) {
    internal.deserializeNBT(nbt);
  }

  @Override
  public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
    internal.setStackInSlot(slot, stack);
  }

  @Override
  public int getSlots() {
    return internal.getSlots();
  }

  @Nonnull
  @Override
  public ItemStack getStackInSlot(int slot) {
    return internal.getStackInSlot(slot);
  }

  @Nonnull
  @Override
  public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
    if(this.isItemValid(slot, stack)) {
      return internal.insertItem(slot, stack, simulate);
    }
    return stack;
  }

  @Nonnull
  @Override
  public ItemStack extractItem(int slot, int amount, boolean simulate) {
    if(canExtractItem(slot, amount)) {
      return internal.extractItem(slot, amount, simulate);
    }
    return ItemStack.EMPTY;
  }

  @Override
  public int getSlotLimit(int slot) {
    return internal.getSlotLimit(slot);
  }

  @Override
  public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
    return true;
  }
  
  public boolean canExtractItem(int slot, int amount) {
    return true;
  }
}
