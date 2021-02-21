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

import com.ridanisaurus.emendatusenigmatica.tiles.EnigmaticFortunizerTile;
import com.ridanisaurus.emendatusenigmatica.util.KeyboardHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.BrewingStandTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class EnigmaticFortunizer extends Block {
  private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

  private static final VoxelShape SHAPE_N = Stream.of(
          Block.makeCuboidShape(0, 0, 0, 16, 8, 16),
          Block.makeCuboidShape(0, 8, 0, 4, 10, 16),
          Block.makeCuboidShape(4, 8, 8, 16, 12, 16),
          Block.makeCuboidShape(12, 8, 0, 16, 10, 8),
          Block.makeCuboidShape(5, 8, 1, 11, 12, 7),
          Block.makeCuboidShape(5, 12, 9, 11, 16, 15)
  ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

  private static final VoxelShape SHAPE_E = Stream.of(
          Block.makeCuboidShape(0, 0, 0, 16, 8, 16),
          Block.makeCuboidShape(0, 8, 0, 16, 10, 4),
          Block.makeCuboidShape(0, 8, 4, 8, 12, 16),
          Block.makeCuboidShape(8, 8, 12, 16, 10, 16),
          Block.makeCuboidShape(9, 8, 5, 15, 12, 11),
          Block.makeCuboidShape(1, 12, 5, 7, 16, 11)
  ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

  private static final VoxelShape SHAPE_S = Stream.of(
          Block.makeCuboidShape(0, 0, 0, 16, 8, 16),
          Block.makeCuboidShape(12, 8, 0, 16, 10, 16),
          Block.makeCuboidShape(0, 8, 0, 12, 12, 8),
          Block.makeCuboidShape(0, 8, 8, 4, 10, 16),
          Block.makeCuboidShape(5, 8, 9, 11, 12, 15),
          Block.makeCuboidShape(5, 12, 1, 11, 16, 7)
  ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

  private static final VoxelShape SHAPE_W = Stream.of(
          Block.makeCuboidShape(0, 0, 0, 16, 8, 16),
          Block.makeCuboidShape(0, 8, 12, 16, 10, 16),
          Block.makeCuboidShape(8, 8, 0, 16, 12, 12),
          Block.makeCuboidShape(0, 8, 0, 8, 10, 4),
          Block.makeCuboidShape(1, 8, 5, 7, 12, 11),
          Block.makeCuboidShape(9, 12, 5, 15, 16, 11)
  ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

  public EnigmaticFortunizer() {
    super(Properties.create(Material.IRON)
      .hardnessAndResistance(3.5f, 4.0f)
      .sound(SoundType.METAL)
      .harvestLevel(0)
      .harvestTool(ToolType.PICKAXE)
      .setRequiresTool());
  }

  @Override
  public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
    switch (state.get(FACING)) {
      case NORTH:
        return SHAPE_N;
      case EAST:
        return SHAPE_E;
      case SOUTH:
        return SHAPE_S;
      case WEST:
        return SHAPE_W;
      default:
        throw new IllegalStateException("Invalid State");
    }
  }

  @Override
  public BlockState getStateForPlacement(BlockItemUseContext context) {
    return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
  }

  @Override
  public BlockState rotate (BlockState state, Rotation rot) {
    return state.with(FACING, rot.rotate(state.get(FACING)));
  }

  @Override
  public BlockState mirror (BlockState state, Mirror mirrorIn) {
    return state.rotate(mirrorIn.toRotation(state.get(FACING)));
  }

  @Override
  protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
    builder.add(FACING);
  }

  @Override
  public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
    return 0.6f;
  }

  @Override
  public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
    if(KeyboardHelper.isHoldingShift()) {
      tooltip.add(new TranslationTextComponent("tooltip.emendatusenigmatica.enigmatic_fortunizer.1"));
    } else {
      tooltip.add(new TranslationTextComponent("tooltip.emendatusenigmatica.enigmatic_fortunizer.2"));
    }
    super.addInformation(stack, worldIn, tooltip, flagIn);
  }

  @Nullable
  @Override
  public TileEntity createTileEntity(BlockState state, IBlockReader world) {
    return new EnigmaticFortunizerTile();
  }

  @Override
  public boolean hasTileEntity(BlockState state) {
    return true;
  }

  @Override
  public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
    TileEntity tileEntity = worldIn.getTileEntity(pos);
    // worldIn.isRemote means it's on the server
    if (tileEntity instanceof EnigmaticFortunizerTile && !worldIn.isRemote) {
      NetworkHooks.openGui((ServerPlayerEntity) player, (EnigmaticFortunizerTile)tileEntity, pos);
      return ActionResultType.SUCCESS;
    }
      return ActionResultType.PASS;
  }

  @Override
  public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
    if (state.getBlock() != newState.getBlock()) {
      TileEntity tile = worldIn.getTileEntity(pos);
      if (tile instanceof EnigmaticFortunizerTile)
        ((EnigmaticFortunizerTile) tile).dropInventory();
    }
    super.onReplaced(state, worldIn, pos, newState, isMoving);
  }
}