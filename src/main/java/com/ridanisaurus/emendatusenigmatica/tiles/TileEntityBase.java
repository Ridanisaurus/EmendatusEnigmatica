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

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class TileEntityBase extends TileEntity {
  public TileEntityBase(TileEntityType<?> tileEntityTypeIn) {
    super(tileEntityTypeIn);
  }

  @Override
  public final CompoundNBT write(CompoundNBT compound) {
    return this.write(compound, false);
  }

  @Override
  public final void read(BlockState state, CompoundNBT nbt) {
    this.read(state, nbt, false);
  }

  public CompoundNBT write(CompoundNBT compound, boolean forClient) {
    return super.write(compound);
  }

  public void read(BlockState state, CompoundNBT nbt, boolean forClient) {
    super.read(state, nbt);
  }

  // Sent all the data to the client
  public void sendToClient() {
    ServerWorld world = (ServerWorld) this.getWorld();
    Stream<ServerPlayerEntity> entities = world.getChunkProvider().chunkManager.getTrackingPlayers(new ChunkPos(this.getPos()), false);
    SUpdateTileEntityPacket packet = getUpdatePacket();
    entities.forEach(e -> e.connection.sendPacket(packet));
  }

  // Sent when Entity is Loaded
  @Nullable
  @Override
  public SUpdateTileEntityPacket getUpdatePacket() {
    return new SUpdateTileEntityPacket(this.getPos(), -1, getUpdateTag());
  }

  // Receiving
  @Override
  public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
    handleUpdateTag(this.getBlockState(), pkt.getNbtCompound());
  }

  // Sent when Entity is Added
  @Override
  public CompoundNBT getUpdateTag() {
    return this.write(new CompoundNBT(), true);
  }

  @Override
  public void handleUpdateTag(BlockState state, CompoundNBT tag) {
    this.read(state, tag, true);
  }
}
