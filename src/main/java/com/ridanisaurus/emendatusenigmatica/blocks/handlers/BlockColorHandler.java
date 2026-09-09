/*
 * MIT License
 *
 * Copyright (c) 2024-2026. Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.blocks.handlers;

import com.ridanisaurus.emendatusenigmatica.blocks.templates.BasicWaxedBlock;
import com.ridanisaurus.emendatusenigmatica.blocks.templates.BasicWeatheringBlock;
import com.ridanisaurus.emendatusenigmatica.util.IColorable;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class BlockColorHandler implements BlockColor {
    @Override
    public int getColor(@NotNull BlockState state, @Nullable BlockAndTintGetter reader, @Nullable BlockPos pos, int layer) {
        Block block = state.getBlock();
        if (block instanceof IColorable colorable) return switch (layer) {
            case 0 -> colorable.getHighlight2();
            case 1 -> colorable.getHighlight1();
            case 2 -> colorable.getBase();
            case 3 -> colorable.getShadow1();
            case 4 -> colorable.getShadow2();
            case 9 -> {
                if (block instanceof BasicWeatheringBlock oxidizedBlock) yield oxidizedBlock.getOxidizationColor();
                if (block instanceof BasicWaxedBlock oxidizedBlock) yield oxidizedBlock.getOxidizationColor();
                yield 0xFFFFFF;
            }
            default -> 0xFFFFFF;
        };
        return 0xFFFFFF;
    }
}