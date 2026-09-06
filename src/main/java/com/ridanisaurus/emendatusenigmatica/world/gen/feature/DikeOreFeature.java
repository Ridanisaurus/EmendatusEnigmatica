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

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.DikeDepositModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EETags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import org.jetbrains.annotations.NotNull;

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

        WorldgenRandom worldgenRandom = new WorldgenRandom(new LegacyRandomSource(level.getSeed()));
        NormalNoise normalNoise = NormalNoise.create(worldgenRandom, -2, 4.0D); // INT Sparseness - DOUBLE ARRAY Density
        ChunkPos chunkPos = new ChunkPos(pos);
        int size = model.size;
        int xPos = chunkPos.getMinBlockX() + rand.nextInt(16);
        int zPos = chunkPos.getMinBlockZ() + rand.nextInt(16);

        int yTop = model.maxYLevel;
        int yBottom = model.minYLevel;

        BlockPos basePos = new BlockPos(xPos, yBottom, zPos);

        // TODO: Figure out the Size and other Parameters

        // NOTE: Wait, does this mean this doesn't scale yet?

        for (int dY = yBottom; dY <= yTop; dY++) {
            for (int dX = -size; dX <= size; dX++) {
                for (int dZ = -size; dZ <= size; dZ++) {
                    float dist = (dX * dX) + (dZ * dZ);
                    if (dist > size) continue;
                    if (normalNoise.getValue(dX, dY, dZ) >= 0.5) {
                        placeBlock(level, rand, new BlockPos(basePos.getX() + dX, dY, basePos.getZ() + dZ), model);
                    }
                }
            }
        }
//        for (int dY = yBottom; dY <= yTop; dY++) {
//            if (normalNoise.getValue(basePos.getX(), dY, basePos.getZ()) >= 0.5) {
//                placeBlock(level, rand, new BlockPos(basePos.getX(), dY, basePos.getZ()), config);
//            }
//        }
        if (rand.nextInt(100) < model.chance && !model.sampleBlocks.isEmpty())
            placeSurfaceSample(rand, pos, level, model);

        return true;
    }

    private void placeBlock(WorldGenLevel level, RandomSource rand, BlockPos pos, DikeDepositModel model) {
        if (!model.target.test(level.getBlockState(pos), rand)) return;

        int index = rand.nextInt(model.blocks.size());
        try {
            var depositBlockModel = model.blocks.get(index);
            if (depositBlockModel.getBlock() != null) {
                Block block = BuiltInRegistries.BLOCK.get(ResourceLocation.parse(depositBlockModel.getBlock()));
                if (pos.getY() >= depositBlockModel.getMin() && pos.getY() <= depositBlockModel.getMax() ) {
                    level.setBlock(pos, block.defaultBlockState(), 2);
                }
            } else if (depositBlockModel.getTag() != null) {
                HolderSet.Named<Block> blockITag = BuiltInRegistries.BLOCK.getTag(EETags.getBlockTag(ResourceLocation.parse(depositBlockModel.getTag()))).get();
                blockITag.getRandomElement(rand).ifPresent(block -> {
                    if (pos.getY() >= depositBlockModel.getMin() && pos.getY() <= depositBlockModel.getMax() ) {
                        level.setBlock(pos, block.value().defaultBlockState(), 2);
                    }
                });
            } else if (depositBlockModel.getMaterial() != null) {
                var strata = model.target.getStrataFromFiller(level.getBlockState(pos), rand);
                if (strata != null) {
                    Block block = EERegistrar.oreBlockTable.get(strata, depositBlockModel.getMaterial()).get();
                    level.setBlock(pos, block.defaultBlockState(), 2);
                }
            }
            model.placed = true;
        } catch (Exception e) {
            JsonElement modelJson = JsonOps.INSTANCE.withEncoder(CODEC).apply(model).result().orElseGet(() -> new JsonPrimitive("Failed to serialize model!"));
            EmendatusEnigmatica.logger.error("index: {}, model: {}", index, new Gson().toJson(modelJson), e);
        }
    }

    private void placeSampleBlock(WorldGenLevel level, RandomSource rand, BlockPos samplePos, DikeDepositModel model) {
        try {
            int index = rand.nextInt(model.sampleBlocks.size());
            var depositSampleBlockModel = model.sampleBlocks.get(index);

            if (depositSampleBlockModel.getBlock() != null) {
                Block sampleBlock = BuiltInRegistries.BLOCK.get(ResourceLocation.parse(depositSampleBlockModel.getBlock()));
                level.setBlock(samplePos, sampleBlock.defaultBlockState(), 2);
            } else if (depositSampleBlockModel.getTag() != null) {
                HolderSet.Named<Block> blockITag = BuiltInRegistries.BLOCK.getTag(EETags.getBlockTag(ResourceLocation.parse(depositSampleBlockModel.getTag()))).get();
                blockITag.getRandomElement(rand).ifPresent(block -> {
                    level.setBlock(samplePos, block.value().defaultBlockState(), 2);
                });
            } else if (depositSampleBlockModel.getMaterial() != null) {
                Block sampleBlock = EERegistrar.oreSampleBlockTable.get(depositSampleBlockModel.getStrata(), depositSampleBlockModel.getMaterial()).get();
                level.setBlock(samplePos, sampleBlock.defaultBlockState(), 2);
            }
        } catch (Exception e) {
            JsonElement modelJson = JsonOps.INSTANCE.withEncoder(CODEC).apply(model).result().orElseGet(() -> new JsonPrimitive("Failed to serialize model!"));
            EmendatusEnigmatica.logger.error("model: {}", new Gson().toJson(modelJson), e);
        }
    }

    private void placeSurfaceSample(RandomSource rand, @NotNull BlockPos pos, @NotNull WorldGenLevel level, DikeDepositModel model) {
        BlockPos sample = new BlockPos(pos.getX(), level.getHeight(Heightmap.Types.WORLD_SURFACE, pos.getX(), pos.getZ()), pos.getZ());
        if (level.getBlockState(sample.below()).getBlock() == Blocks.WATER)
            sample = new BlockPos(pos.getX(), level.getHeight(Heightmap.Types.OCEAN_FLOOR, pos.getX(), pos.getZ()), pos.getZ());

        if (sample.getY() > level.getMinBuildHeight() + 3 && level.getBlockState(sample.below()).is(BlockTags.LEAVES)) {
            for(int l = 0; l < 3; ++l) {
                int i = rand.nextInt(2);
                int j = rand.nextInt(2);
                int k = rand.nextInt(2);
                float f = (float)(i + j + k) * 0.333F + 0.5F;

                for(BlockPos samplePos : BlockPos.betweenClosed(sample.offset(-i, -j, -k), sample.offset(i, j, k))) {
                    if (samplePos.distSqr(sample) <= (double)(f * f) && model.placed) {
                        placeSampleBlock(level, rand, samplePos, model);
                    }
                }
                sample = sample.offset(-1 + rand.nextInt(2), -rand.nextInt(2), -1 + rand.nextInt(2));
            }
        }

        model.placed = false;
    }
}