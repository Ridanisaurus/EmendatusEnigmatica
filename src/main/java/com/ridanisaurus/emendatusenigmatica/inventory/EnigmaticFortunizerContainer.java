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

package com.ridanisaurus.emendatusenigmatica.inventory;

import com.ridanisaurus.emendatusenigmatica.items.ToolUtils;
import com.ridanisaurus.emendatusenigmatica.registries.ContainerHandler;
import com.ridanisaurus.emendatusenigmatica.tiles.EnigmaticFortunizerBlockEntity;
import com.ridanisaurus.emendatusenigmatica.util.BlockEntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.tuple.Pair;

public class EnigmaticFortunizerContainer extends AbstractContainerMenu {
  public final EnigmaticFortunizerBlockEntity tileEntity;
  public EnigmaticFortunizerContainer(int id, Player player, BlockPos pos) {
    super(ContainerHandler.ENIGMATIC_FORTUNIZER_CONTAINER.get(), id);
    // Container Slots comes before Player Slots
    tileEntity = (EnigmaticFortunizerBlockEntity) player.level.getBlockEntity(pos);
    this.addSlot(new SlotItemHandlerTakeable(tileEntity.itemSH, EnigmaticFortunizerBlockEntity.SLOT_INPUT, 26, 47));
    this.addSlot(new SlotItemHandlerTakeable(tileEntity.itemSH, EnigmaticFortunizerBlockEntity.SLOT_PICKAXE, 80, 19));
    this.addSlot(new SlotItemHandlerTakeable(tileEntity.itemSH, EnigmaticFortunizerBlockEntity.SLOT_OUTPUT, 134, 47));

    // Inv
    for(int i = 0; i < 3; ++i) {
      for(int j = 0; j < 9; ++j) {
        this.addSlot(new Slot(player.getInventory(), j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
      }
    }

    // Hotbar
    for(int k = 0; k < 9; ++k) {
      this.addSlot(new Slot(player.getInventory(), k, 8 + k * 18, 142));
    }
  }

  // TODO Close container when away
  @Override
  public boolean stillValid(Player playerIn) {
    return true;
  }

  @Override
  public ItemStack quickMoveStack(Player playerIn, int index) {
    return BlockEntityHelper.transferStackInSlot(this, this::moveItemStackTo, playerIn, index, stack -> {
      if(EnigmaticFortunizerBlockEntity.getDropInfo(stack) != null) {
        return Pair.of(EnigmaticFortunizerBlockEntity.SLOT_INPUT, EnigmaticFortunizerBlockEntity.SLOT_INPUT+1);
      }
      if(ToolUtils.isPickaxe(stack)) {
        return Pair.of(EnigmaticFortunizerBlockEntity.SLOT_PICKAXE, EnigmaticFortunizerBlockEntity.SLOT_PICKAXE+1);
      }
      return null;
    });
  }
}
