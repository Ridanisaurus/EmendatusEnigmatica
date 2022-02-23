/*
 * MIT License
 *
 * Copyright (c) 2020 Ridanisaurus
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.ridanisaurus.emendatusenigmatica.blocks;

import com.ridanisaurus.emendatusenigmatica.tiles.EnigmaticFortunizerBlockEntity;
import com.ridanisaurus.emendatusenigmatica.util.KeyboardHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Stream;

public class EnigmaticFortunizer extends Block implements EntityBlock {
  private static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

  private static final VoxelShape SHAPE_N = Stream.of(
          Block.box(0, 0, 0, 16, 8, 16),
          Block.box(0, 8, 0, 4, 10, 16),
          Block.box(4, 8, 8, 16, 12, 16),
          Block.box(12, 8, 0, 16, 10, 8),
          Block.box(5, 8, 1, 11, 12, 7),
          Block.box(5, 12, 9, 11, 16, 15)
  ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

  private static final VoxelShape SHAPE_E = Stream.of(
          Block.box(0, 0, 0, 16, 8, 16),
          Block.box(0, 8, 0, 16, 10, 4),
          Block.box(0, 8, 4, 8, 12, 16),
          Block.box(8, 8, 12, 16, 10, 16),
          Block.box(9, 8, 5, 15, 12, 11),
          Block.box(1, 12, 5, 7, 16, 11)
  ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

  private static final VoxelShape SHAPE_S = Stream.of(
          Block.box(0, 0, 0, 16, 8, 16),
          Block.box(12, 8, 0, 16, 10, 16),
          Block.box(0, 8, 0, 12, 12, 8),
          Block.box(0, 8, 8, 4, 10, 16),
          Block.box(5, 8, 9, 11, 12, 15),
          Block.box(5, 12, 1, 11, 16, 7)
  ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

  private static final VoxelShape SHAPE_W = Stream.of(
          Block.box(0, 0, 0, 16, 8, 16),
          Block.box(0, 8, 12, 16, 10, 16),
          Block.box(8, 8, 0, 16, 12, 12),
          Block.box(0, 8, 0, 8, 10, 4),
          Block.box(1, 8, 5, 7, 12, 11),
          Block.box(9, 12, 5, 15, 16, 11)
  ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

  public EnigmaticFortunizer() {
    super(Properties.of(Material.METAL)
      .strength(3.5f, 4.0f)
      .sound(SoundType.METAL)
      // FIXME: .harvestLevel(0)
      // FIXME: .harvestTool(ToolType.PICKAXE)
      .requiresCorrectToolForDrops());
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
    return switch (state.getValue(FACING)) {
      case NORTH -> SHAPE_N;
      case EAST -> SHAPE_E;
      case SOUTH -> SHAPE_S;
      case WEST -> SHAPE_W;
      default -> throw new IllegalStateException("Invalid State");
    };
  }

  @Override
  public BlockState getStateForPlacement(BlockPlaceContext context) {
    return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
  }

  @Override
  public BlockState rotate (BlockState state, Rotation rot) {
    return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
  }

  @Override
  public BlockState mirror (BlockState state, Mirror mirrorIn) {
    return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    builder.add(FACING);
  }

  @Override
  public float getShadeBrightness(BlockState state, BlockGetter worldIn, BlockPos pos) {
    return 0.6f;
  }

  @Override
  public void appendHoverText(ItemStack stack, @Nullable BlockGetter worldIn, List<Component> tooltip, TooltipFlag flagIn) {
    if(KeyboardHelper.isHoldingShift()) {
      tooltip.add(new TranslatableComponent("tooltip.emendatusenigmatica.enigmatic_fortunizer.1"));
    } else {
      tooltip.add(new TranslatableComponent("tooltip.emendatusenigmatica.enigmatic_fortunizer.2"));
    }
    super.appendHoverText(stack, worldIn, tooltip, flagIn);
  }

  @Nullable
  @Override
  public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
    return new EnigmaticFortunizerBlockEntity(pos, state);
  }

  @Override
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> p_153214_) {
    return EnigmaticFortunizerBlockEntity::ticker;
  }

  @Override
  public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
    BlockEntity tileEntity = worldIn.getBlockEntity(pos);
    // worldIn.isRemote means it's on the server
    if (tileEntity instanceof EnigmaticFortunizerBlockEntity && !worldIn.isClientSide) {
      NetworkHooks.openGui((ServerPlayer) player, (EnigmaticFortunizerBlockEntity)tileEntity, pos);
      return InteractionResult.SUCCESS;
    }
      return InteractionResult.PASS;
  }

  @Override
  public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
    if (state.getBlock() != newState.getBlock()) {
      BlockEntity tile = worldIn.getBlockEntity(pos);
      if (tile instanceof EnigmaticFortunizerBlockEntity)
        ((EnigmaticFortunizerBlockEntity) tile).dropInventory();
    }
    super.onRemove(state, worldIn, pos, newState, isMoving);
  }
}