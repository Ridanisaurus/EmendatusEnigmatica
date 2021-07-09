package com.ridanisaurus.emendatusenigmatica.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;

import javax.annotation.Nullable;

public class BlockColorHandler implements IBlockColor {
    @Override
    public int getColor(BlockState state, @Nullable IBlockDisplayReader reader, @Nullable BlockPos pos, int i) {
        Block block = state.getBlock();
        if ((block instanceof GemOreBlock || block instanceof MetalOreBlock) && i == 1) {
            return ((IColorable) state.getBlock()).getColor();
        }
        if (state.getBlock() instanceof BasicStorageBlock) {
            return ((BasicStorageBlock) state.getBlock()).color;
        }
        return 0xFFFFFF;
    }
}
