package com.ridanisaurus.emendatusenigmatica.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;

import javax.annotation.Nullable;

public class BlockColorHandler implements IBlockColor {
    @Override
    public int getColor(BlockState state, @Nullable IBlockDisplayReader reader, @Nullable BlockPos pos, int i) {
        if (state.getBlock() instanceof BasicOreBlock && i == 1) {
            return ((BasicOreBlock) state.getBlock()).color;
        }
        return 0xFFFFFF;
    }
}
