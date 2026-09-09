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

package com.ridanisaurus.emendatusenigmatica.world.gen.feature;

import com.mojang.serialization.Codec;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.DikeDepositModel;
import com.ridanisaurus.emendatusenigmatica.util.WorldGenHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import static com.ridanisaurus.emendatusenigmatica.util.WorldGenHelper.placeBlock;

// Credit: Geolysis
public class DikeOreFeature extends Feature<DikeDepositModel> {
    private static final Codec<DikeDepositModel> CODEC = DikeDepositModel.getFeatureCodec(DikeDepositModel.CODEC);

    public DikeOreFeature() {
        super(CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<DikeDepositModel> context) {
        RandomSource rand = context.random();
        BlockPos pos = context.origin();
        WorldGenLevel level = context.level();
        var model = context.config();
        boolean placed = false;

        WorldgenRandom worldgenRandom = new WorldgenRandom(new LegacyRandomSource(level.getSeed()));
        NormalNoise normalNoise = NormalNoise.create(worldgenRandom, -2, 4.0D); // INT Sparseness - DOUBLE ARRAY Density
        int size = model.size;
        // Would make sense *if* we would center on the rolled chunk, we don't.
        // Just use the minecraft provided x/z.
//        ChunkPos chunkPos = new ChunkPos(pos);
//        int xPos = chunkPos.getMinBlockX() + rand.nextInt(16);
//        int zPos = chunkPos.getMinBlockZ() + rand.nextInt(16);

        int yTop = model.maxYLevel;
        int yBottom = model.minYLevel;

        BlockPos basePos = new BlockPos(pos.getX(), yBottom, pos.getZ());

        // NOTE: Wait, does this mean this doesn't scale yet?
        // TODO: Figure out the Size and other Parameters

        var blocksForY = model.blocksByY.getOrDefault(model.minYLevelRange, WeightedRandomList.create());
        for (int dY = yBottom; dY <= yTop; dY++) {
            if (model.blocksByY.containsKey(dY)) blocksForY = model.blocksByY.get(dY);
            if (blocksForY.isEmpty()) continue;
            for (int dX = -size; dX <= size; dX++) {
                for (int dZ = -size; dZ <= size; dZ++) {
                    float dist = (dX * dX) + (dZ * dZ);
                    if (dist > size) continue;
                    if (normalNoise.getValue(dX, dY, dZ) >= 0.5) {
                        placed |= placeBlock(level, rand, new BlockPos(basePos.getX() + dX, dY, basePos.getZ() + dZ), blocksForY, model.target);
                    }
                }
            }
        }

        WorldGenHelper.placeSurfaceSample(level, rand, pos, model.sample);
        return placed;
    }
}