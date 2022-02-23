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

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;
import java.util.List;

// Credit: Ellpeck - https://github.com/Ellpeck/NaturesAura/blob/main/src/main/java/de/ellpeck/naturesaura/blocks/tiles/TileEntityImpl.java

public class BlockEntityBase extends BlockEntity {
  public BlockEntityBase(BlockEntityType<?> tileEntityTypeIn, BlockPos pos, BlockState state) {
    super(tileEntityTypeIn, pos, state);
  }

  @Override
  public final CompoundTag save(CompoundTag compound) {
    return this.write(compound, false);
  }

  @Override
  public final void load(CompoundTag nbt) {
    this.read(nbt, false);
  }

  public CompoundTag write(CompoundTag compound, boolean forClient) {
    return super.save(compound);
  }

  public void read(CompoundTag nbt, boolean forClient) {
    super.load(nbt);
  }

  // Sent all the data to the client
  public void sendToClient() {
    ServerLevel world = (ServerLevel) this.getLevel();
    List<ServerPlayer> entities = world.getChunkSource().chunkMap.getPlayers(new ChunkPos(this.getBlockPos()), false);
    Packet<ClientGamePacketListener> packet = getUpdatePacket();
    entities.forEach(e -> e.connection.send(packet));
  }

  // Sent when Entity is Loaded
  @Nullable
  @Override
  public Packet<ClientGamePacketListener> getUpdatePacket() {
    return ClientboundBlockEntityDataPacket.create(this, BlockEntity::getUpdateTag);
  }

  // Receiving
  @Override
  public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
    handleUpdateTag(pkt.getTag());
  }

  // Sent when Entity is Added
  @Override
  public CompoundTag getUpdateTag() {
    return this.write(new CompoundTag(), true);
  }

  @Override
  public void handleUpdateTag(CompoundTag tag) {
    this.read( tag, true);
  }

  public IItemHandlerModifiable getItemHandler() {
    return null;
  }

  public void dropInventory() {
    IItemHandler handler = this.getItemHandler();
    if (handler != null) {
      for (int i = 0; i < handler.getSlots(); i++) {
        ItemStack stack = handler.getStackInSlot(i);
        if (!stack.isEmpty()) {
          ItemEntity item = new ItemEntity(this.level,
                  this.worldPosition.getX() + 0.5, this.worldPosition.getY() + 0.5, this.worldPosition.getZ() + 0.5,
                  stack);
          this.level.addFreshEntity(item);
        }
      }
    }
  }
}
