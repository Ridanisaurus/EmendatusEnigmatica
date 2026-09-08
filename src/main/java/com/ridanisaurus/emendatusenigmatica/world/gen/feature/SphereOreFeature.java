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

package com.ridanisaurus.emendatusenigmatica.world.gen.feature;

import com.mojang.serialization.Codec;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.SphereDepositModel;
import com.ridanisaurus.emendatusenigmatica.util.MathHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import static com.ridanisaurus.emendatusenigmatica.util.WorldGenHelper.placeBlock;


public class SphereOreFeature extends Feature<SphereDepositModel> {
    private static final Codec<SphereDepositModel> CODEC = SphereDepositModel.getFeatureCodec(SphereDepositModel.CODEC);

    public SphereOreFeature() {
        super(CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<SphereDepositModel> context) {
        RandomSource rand = context.random();
        BlockPos pos = context.origin();
        WorldGenLevel level = context.level();
        var model = context.config();
        boolean placed = false;

        int yTop = model.maxYLevel;
        int yBottom = model.minYLevel;
        //TODO: Use rolled yPos from origin?
        int yPos = yBottom + rand.nextInt(yTop - yBottom);
        // TODO: Fix the radius calculation
        int radius = model.radius;

        // Isn't this like, the same as not adding anything?
        radius += 0.5;
        radius += 0.5;
        radius += 0.5;

        final double invRadiusX = 1d / radius;
        final double invRadiusY = 1d / radius;
        final double invRadiusZ = 1d / radius;

        // CeilRadius is the same as radius? Radius is int!
        final int ceilRadiusX = (int) Math.ceil(radius);
        final int ceilRadiusY = (int) Math.ceil(radius);
        final int ceilRadiusZ = (int) Math.ceil(radius);

        double nextXn = 0;
        forX:
        for (int x = 0; x <= ceilRadiusX; ++x) {
            final double xn = nextXn;
            nextXn = (x + 1) * invRadiusX;
            double nextYn = 0;
            forY:
            for (int y = 0; y <= ceilRadiusY; ++y) {
                final double yn = nextYn;
                nextYn = (y + 1) * invRadiusY;
                double nextZn = 0;
                forZ:
                for (int z = 0; z <= ceilRadiusZ; ++z) {
                    final double zn = nextZn;
                    nextZn = (z + 1) * invRadiusZ;

                    double distanceSq = MathHelper.lengthSq(xn, yn, zn);
                    if (distanceSq > 1) {
                        if (z == 0) {
                            if (y == 0) break forX;
                            break forY;
                        }
                        break forZ;
                    }

                    if (y + yPos > yTop || y + yPos < yBottom) continue;

                    int randomizer = rand.nextDouble() >= 0.5D ? 1 : 0;
                    placed |= placeBlock(level, rand, new BlockPos(pos.getX() + x + randomizer, yPos + y + randomizer, pos.getZ() + z + randomizer), model.blocks, model.target);
                    placed |= placeBlock(level, rand, new BlockPos(pos.getX() - x + randomizer, yPos + y + randomizer, pos.getZ() + z + randomizer), model.blocks, model.target);
                    placed |= placeBlock(level, rand, new BlockPos(pos.getX() + x + randomizer, yPos - y + randomizer, pos.getZ() + z + randomizer), model.blocks, model.target);
                    placed |= placeBlock(level, rand, new BlockPos(pos.getX() + x + randomizer, yPos + y + randomizer, pos.getZ() - z + randomizer), model.blocks, model.target);
                    placed |= placeBlock(level, rand, new BlockPos(pos.getX() - x + randomizer, yPos - y + randomizer, pos.getZ() + z + randomizer), model.blocks, model.target);
                    placed |= placeBlock(level, rand, new BlockPos(pos.getX() + x + randomizer, yPos - y + randomizer, pos.getZ() - z + randomizer), model.blocks, model.target);
                    placed |= placeBlock(level, rand, new BlockPos(pos.getX() - x + randomizer, yPos + y + randomizer, pos.getZ() - z + randomizer), model.blocks, model.target);
                    placed |= placeBlock(level, rand, new BlockPos(pos.getX() - x + randomizer, yPos - y + randomizer, pos.getZ() - z + randomizer), model.blocks, model.target);
                }
            }
        }

//        if (rand.nextInt(100) < model.chance && !model.sampleBlocks.isEmpty())
//            placeSurfaceSample(rand, pos, level, model);
        return placed;
    }
}