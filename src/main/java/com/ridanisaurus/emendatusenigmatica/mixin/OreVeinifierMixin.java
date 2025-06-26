/*
 * MIT License
 *
 * Copyright (c) 2024. Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.mixin;

import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.plugin.model.material.MaterialModel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.NoiseChunk;
import net.minecraft.world.level.levelgen.OreVeinifier;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Objects;

@Mixin(OreVeinifier.class)
public class OreVeinifierMixin {

    /**
     * @author Buuz135 and Ridanisaurus
     * @reason A way to disable Vanilla Ore Veins and/or spawn modded ore in a similar fashion
     */

    @Overwrite(remap = false)
    protected static NoiseChunk.BlockStateFiller create(DensityFunction dFunction1, DensityFunction dFunction2, DensityFunction dFunction3, PositionalRandomFactory randomFactory) {
        BlockState blockstate = null;
        return (filler) -> {
            double d0 = dFunction1.compute(filler);
            int i = filler.blockY();
            OreVeinifier.VeinType oreveinifier$veintype = d0 > 0.0D ? OreVeinifier.VeinType.COPPER : OreVeinifier.VeinType.IRON;
            double d1 = Math.abs(d0);
            int j = oreveinifier$veintype.maxY - i;
            int k = i - oreveinifier$veintype.minY;

            if (k >= 0 && j >= 0) {
                int l = Math.min(j, k);
                double d2 = Mth.clampedMap((double)l, 0.0D, 20.0D, -0.2D, 0.0D);
                if (d1 + d2 < (double)0.4F) {
                    return blockstate;
                } else {
                    RandomSource randomsource = randomFactory.at(filler.blockX(), i, filler.blockZ());
                    if (randomsource.nextFloat() > 0.7F) {
                        return blockstate;
                    } else if (dFunction2.compute(filler) >= 0.0D) {
                        return blockstate;
                    } else {
                        double d3 = Mth.clampedMap(d1, (double)0.4F, (double)0.6F, (double)0.1F, (double)0.3F);
                        if ((double)randomsource.nextFloat() < d3 && dFunction3.compute(filler) > (double)-0.3F) {
                            BlockState mixinRawOreBlock = oreveinifier$veintype.rawOreBlock;
                            BlockState mixinOreBlock = oreveinifier$veintype.ore;

                            EmendatusDataRegistry registry = EmendatusEnigmatica.getInstance().getLoader().getDataRegistry();
                            MaterialModel copper = registry.getMaterial("copper");
                            MaterialModel iron = registry.getMaterial("iron");

                            if (oreveinifier$veintype == OreVeinifier.VeinType.COPPER && Objects.nonNull(copper) && copper.getDisableDefaultOre()) {
                                mixinRawOreBlock = Blocks.STONE.defaultBlockState();
                                mixinOreBlock = Blocks.STONE.defaultBlockState();
                            } else if (oreveinifier$veintype == OreVeinifier.VeinType.IRON && Objects.nonNull(iron) && iron.getDisableDefaultOre()) {
                                mixinRawOreBlock = Blocks.DEEPSLATE.defaultBlockState();
                                mixinOreBlock = Blocks.DEEPSLATE.defaultBlockState();
                            }

                            return randomsource.nextFloat() < 0.02F ? mixinRawOreBlock : mixinOreBlock;
                        } else {
                            return oreveinifier$veintype.filler;
                        }
                    }
                }
            } else {
                return blockstate;
            }
        };
    }
}