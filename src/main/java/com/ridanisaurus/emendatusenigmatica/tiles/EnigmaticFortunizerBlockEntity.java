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

import com.ridanisaurus.emendatusenigmatica.inventory.EnigmaticFortunizerContainer;
import com.ridanisaurus.emendatusenigmatica.registries.BlockHandler;
import com.ridanisaurus.emendatusenigmatica.registries.ItemHandler;
import com.ridanisaurus.emendatusenigmatica.util.Materials;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class EnigmaticFortunizerBlockEntity extends BlockEntityBase implements MenuProvider {
  // Number of slots
  public final EEItemStackHandler itemSH = new EEItemStackHandler(3){
    // Checking valid Pickaxe
    // Can't insert in Output
    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
      if(slot == SLOT_PICKAXE && !stack.getItem().getToolTypes(stack).contains(ToolType.PICKAXE)) {
        return false;
      }
      if(slot == SLOT_OUTPUT) {
        return false;
      }
      if(slot == SLOT_INPUT) {
        return getDropInfo(stack) != null;
      }
      return super.isItemValid(slot, stack);
    }

    // Disable extracting from the input and pickaxe slot automatically
    @Override
    public boolean canExtractItem(int slot, int amount) {
      return slot == SLOT_OUTPUT;
    }
  };

  public static final int SLOT_INPUT = 0;
  public static final int SLOT_PICKAXE = 1;
  public static final int SLOT_OUTPUT = 2;
  public int progress;
  public static int MAX_PROGRESS = 500;
  // Capability
  private final LazyOptional<EEItemStackHandler> lazyItemStorage = LazyOptional.of(() -> itemSH);

  public EnigmaticFortunizerBlockEntity(BlockPos pos, BlockState state) {
    super(BlockHandler.ENIGMATIC_FORTUNIZER_TILE.get(), pos, state);
  }

  @Override
  public void tick() {
    if (!this.level.isClientSide) {
      ItemStack pickaxe = this.itemSH.getStackInSlot(SLOT_PICKAXE);

      // Validate whether the slot has a Pickaxe
      if(pickaxe.getItem().getToolTypes(pickaxe).contains(ToolType.PICKAXE)) {
        ItemStack input = this.itemSH.getStackInSlot(SLOT_INPUT);
        Materials.OreDropInfo oreDropInfo = getDropInfo(input);

        // Does the Chunk have an OreDropInfo (this case, GEM chunks)
        if(oreDropInfo != null) {
          ItemStack output = new ItemStack(oreDropInfo.item.get(), (int) oreDropInfo.min);

          // Check Speed and EfficiencyEnchant and adjust processing time accordingly
          int pickaxeDestroySpeed = (int) pickaxe.getItem().getDestroySpeed(pickaxe, Blocks.STONE.defaultBlockState());
          int pickaxeEfficiencyEnchant = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_EFFICIENCY, pickaxe);
          if (!pickaxe.isEmpty()) {
            MAX_PROGRESS = 500 / (pickaxeDestroySpeed * (pickaxeEfficiencyEnchant+1));
          }

          if(this.itemSH.internal.insertItem(SLOT_OUTPUT, output, true).isEmpty() && pickaxe.getDamageValue() != pickaxe.getMaxDamage()) {
            progress++;
            if(progress >= MAX_PROGRESS) {
              input.shrink(1);
              pickaxe.hurt(1, level.random, null);
              output.grow(Mth.ceil(level.random.nextFloat() * (oreDropInfo.max - oreDropInfo.min)));
              int fortune = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, pickaxe);
              output.setCount(getFortuneLoot(level.random, output.getCount(), fortune));
              this.itemSH.internal.insertItem(SLOT_OUTPUT, output, false);
              progress = 0;
            }
            this.sendToClient();
          } else if (pickaxe.getDamageValue() == pickaxe.getMaxDamage() && this.itemSH.getStackInSlot(SLOT_OUTPUT).isEmpty()) {
            ItemStack outPutPickaxe = pickaxe.copy();
            this.itemSH.internal.insertItem(SLOT_OUTPUT, outPutPickaxe, false);
            pickaxe.shrink(1);
            level.playSound(null, worldPosition, SoundEvents.CAT_AMBIENT, SoundSource.AMBIENT, 1.0F, 1.0F);
          }

        } else if(progress > 0) {
          // Resetting the Progress if Pickaxe is removed from the slot
          progress = 0;
          this.sendToClient();
        }

      } else if(progress > 0) {
        // Resetting the Progress if Pickaxe is removed from the slot
        progress = 0;
        this.sendToClient();
      }

    }
  }

  @Override
  public CompoundTag write(CompoundTag compound, boolean forClient) {
    if(!forClient) {
      compound.put("itemSH", itemSH.serializeNBT());
    }
    compound.putInt("progress", progress);
    compound.putInt("max_progress", MAX_PROGRESS);
    return super.write(compound, forClient);
  }

  @Override
  public void read(CompoundTag compound, boolean forClient) {
    if(!forClient) {
      itemSH.deserializeNBT(compound.getCompound("itemSH"));
    }
    progress = compound.getInt("progress");
    MAX_PROGRESS = compound.getInt("max_progress");
    super.read(compound, forClient);
  }

  @Nonnull
  @Override
  public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
    if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
      return lazyItemStorage.cast();
    }
    return super.getCapability(cap, side);
  }

  @Override
  public void setRemoved() {
    super.setRemoved();
    lazyItemStorage.invalidate();
  }

  @Override
  public Component getDisplayName() {
    return new TranslatableComponent("container.emendatusenigmatica.enigmatic_fortunizer");
  }

  @Nullable
  @Override
  // Calls container on the Server
  public AbstractContainerMenu createMenu(int windowID, Inventory inventory, Player player) {
    return new EnigmaticFortunizerContainer(windowID, player, this.worldPosition);
  }

  public static Materials.OreDropInfo getDropInfo(ItemStack itemStack) {
    if(!itemStack.isEmpty()) {
      Item item = itemStack.getItem();
      /*for(Map.Entry<Materials, RegistryObject<Item>> cell : ItemHandler.backingItemTable.row(ProcessedMaterials.CHUNK).entrySet()) {
        if (cell.getValue().get() == item) {
          return cell.getKey().drop;
        }
      }*/
      Materials material =  ItemHandler.materialsByName.get(item.getRegistryName());
      return material == null ? null : material.drop;
    }
    return null;
  }

  public static int getFortuneLoot(Random random, int itemAmount, int fortuneLevel) {
    if (fortuneLevel > 0) {
      int i = Math.max(random.nextInt(fortuneLevel + 2) - 1, 0);
      return itemAmount * (i + 1);
    } else {
      return itemAmount;
    }
  }

  @OnlyIn(Dist.CLIENT)
  public int getProgress(int scale) {
    int progressMin = progress;
    int progressMax = MAX_PROGRESS;
    return progressMin * scale / progressMax;
  }

  @Override
  public IItemHandlerModifiable getItemHandler() {
    return this.itemSH;
  }

  public static <T extends BlockEntity> void ticker(Level level, BlockPos blockPos, BlockState state, T t) {
    ((EnigmaticFortunizerBlockEntity)t).tick();
  }
}
