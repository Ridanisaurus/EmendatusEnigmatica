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

package com.ridanisaurus.emendatusenigmatica.items.handlers;

import com.ridanisaurus.emendatusenigmatica.util.IColorable;
import com.ridanisaurus.emendatusenigmatica.blocks.templates.*;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class BlockItemColorHandler implements ItemColor {
    @Override
    public int getColor(@NotNull ItemStack stack, int layer) {
        if (stack.getItem() instanceof BlockItem item && item.getBlock() instanceof IColorable colorable) return switch (layer) {
            case 0 -> colorable.getHighlight2();
            case 1 -> colorable.getHighlight1();
            case 2 -> colorable.getBase();
            case 3 -> colorable.getShadow1();
            case 4 -> colorable.getShadow2();
            case 9 -> {
                if (item.getBlock() instanceof BasicWeatheringBlock oxidizedBlock) yield oxidizedBlock.getOxidizationColor();
                if (item.getBlock() instanceof BasicWaxedBlock oxidizedBlock) yield oxidizedBlock.getOxidizationColor();
                yield 0xFFFFFF;
            }
            default -> 0xFFFFFF;
        };
        return 0xFFFFFF;
    }
}
