package com.ridanisaurus.emendatusenigmatica.blocks;



import net.minecraft.client.color.block.BlockColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class BlockColorHandler implements BlockColor {

    @Override
    public int getColor(BlockState state, @Nullable BlockAndTintGetter reader, @Nullable BlockPos pos, int layer) {
        Block block = state.getBlock();
        if ((block instanceof GemOreBlock || block instanceof MetalOreBlock) && layer == 0) {
            return ((IColorable) state.getBlock()).getHighlightColor();
        }
        if ((block instanceof GemOreBlock || block instanceof MetalOreBlock) && layer == 1) {
            return ((IColorable) state.getBlock()).getBaseColor();
        }
        if ((block instanceof GemOreBlock || block instanceof MetalOreBlock) && layer == 2) {
            return ((IColorable) state.getBlock()).getShadeColor();
        }
        if (state.getBlock() instanceof BasicStorageBlock && layer == 0) {
            return ((BasicStorageBlock) block).highlightColor;
        }
        if (state.getBlock() instanceof BasicStorageBlock && layer == 1) {
            return ((BasicStorageBlock) block).baseColor;
        }
        if (state.getBlock() instanceof BasicStorageBlock && layer == 2) {
            return ((BasicStorageBlock) block).shadeColor;
        }
        return 0xFFFFFF;
    }
}