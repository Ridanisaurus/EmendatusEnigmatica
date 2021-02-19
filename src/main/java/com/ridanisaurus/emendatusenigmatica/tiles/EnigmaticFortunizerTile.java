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

import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.inventory.EnigmaticFortunizerContainer;
import com.ridanisaurus.emendatusenigmatica.registries.BlockHandler;
import com.ridanisaurus.emendatusenigmatica.registries.ItemHandler;
import com.ridanisaurus.emendatusenigmatica.util.Materials;
import com.ridanisaurus.emendatusenigmatica.util.ProcessedMaterials;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;

public class EnigmaticFortunizerTile extends TileEntityBase implements ITickableTileEntity, INamedContainerProvider {
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

  public EnigmaticFortunizerTile() {
    super(BlockHandler.ENIGMATIC_FORTUNIZER_TILE.get());
  }

  @Override
  public void tick() {
    if (!this.world.isRemote) {
      ItemStack pickaxe = this.itemSH.getStackInSlot(SLOT_PICKAXE);

      // Validate whether the slot has a Pickaxe
      if(pickaxe.getItem().getToolTypes(pickaxe).contains(ToolType.PICKAXE)) {
        ItemStack input = this.itemSH.getStackInSlot(SLOT_INPUT);
        Materials.OreDropInfo oreDropInfo = getDropInfo(input);

        // Does the Chunk have an OreDropInfo (this case, GEM chunks)
        if(oreDropInfo != null) {
          ItemStack output = new ItemStack(oreDropInfo.item.get(), (int) oreDropInfo.min);

          // Check Speed and EfficiencyEnchant and adjust processing time accordingly
          int pickaxeDestroySpeed = (int) pickaxe.getItem().getDestroySpeed(pickaxe, Blocks.STONE.getDefaultState());
          int pickaxeEfficiencyEnchant = EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, pickaxe);
          if (!pickaxe.isEmpty()) {
            MAX_PROGRESS = 500 / (pickaxeDestroySpeed * (pickaxeEfficiencyEnchant+1));
          }

          if(this.itemSH.internal.insertItem(SLOT_OUTPUT, output, true).isEmpty() && pickaxe.getDamage() != pickaxe.getMaxDamage()) {
            progress++;
            if(progress >= MAX_PROGRESS) {
              input.shrink(1);
              pickaxe.attemptDamageItem(1, world.rand, null);
              output.grow(MathHelper.ceil(world.rand.nextFloat() * (oreDropInfo.max - oreDropInfo.min)));
              int fortune = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, pickaxe);
              output.setCount(getFortuneLoot(world.rand, output.getCount(), fortune));
              this.itemSH.internal.insertItem(SLOT_OUTPUT, output, false);
              progress = 0;
            }
            this.sendToClient();
          } else if (pickaxe.getDamage() == pickaxe.getMaxDamage() && this.itemSH.getStackInSlot(SLOT_OUTPUT).isEmpty()) {
            ItemStack outPutPickaxe = pickaxe.copy();
            this.itemSH.internal.insertItem(SLOT_OUTPUT, outPutPickaxe, false);
            pickaxe.shrink(1);
            world.playSound(null, pos, SoundEvents.ENTITY_CAT_AMBIENT, SoundCategory.AMBIENT, 1.0F, 1.0F);
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
  public CompoundNBT write(CompoundNBT compound, boolean forClient) {
    if(!forClient) {
      compound.put("itemSH", itemSH.serializeNBT());
    }
    compound.putInt("progress", progress);
    compound.putInt("max_progress", MAX_PROGRESS);
    return super.write(compound, forClient);
  }

  @Override
  public void read(BlockState state, CompoundNBT compound, boolean forClient) {
    if(!forClient) {
      itemSH.deserializeNBT(compound.getCompound("itemSH"));
    }
    progress = compound.getInt("progress");
    MAX_PROGRESS = compound.getInt("max_progress");
    super.read(state, compound, forClient);
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
  public void remove() {
    super.remove();
    lazyItemStorage.invalidate();
  }

  @Override
  public ITextComponent getDisplayName() {
    return new TranslationTextComponent("container.emendatusenigmatica.enigmatic_fortunizer");
  }

  @Nullable
  @Override
  // Calls container on the Server
  public Container createMenu(int windowID, PlayerInventory inventory, PlayerEntity player) {
    return new EnigmaticFortunizerContainer(windowID, player, this.pos);
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
}
