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
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.DenseDepositModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.DepositBlockModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.DepositSampleBlockModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EETags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import org.jetbrains.annotations.NotNull;

// Credit: Geolysis
public class DenseOreFeature extends Feature<DenseDepositModel> {
    private static final Codec<DenseDepositModel> CODEC = DenseDepositModel.getFeatureCodec(DenseDepositModel.CODEC);

    public DenseOreFeature() {
        super(CODEC);
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<DenseDepositModel> context) {
        RandomSource rand = context.random();
        BlockPos pos = context.origin();
        WorldGenLevel level = context.level();
        var model = context.config();

        int yTop = model.maxYLevel;
        int yBottom = model.minYLevel;
        // TODO: Fix the size calculation
        int size = model.size;

        int randY = yBottom + level.getRandom().nextInt(yTop - yBottom);

        float ranFlt = level.getRandom().nextFloat() * (float) Math.PI;
        double x1 = (float) (pos.getX() + 8) + Mth.sin(ranFlt) * (float) size / 8.0F;
        double x2 = (float) (pos.getX() + 8) - Mth.sin(ranFlt) * (float) size / 8.0F;
        double z1 = (float) (pos.getZ() + 8) + Mth.cos(ranFlt) * (float) size / 8.0F;
        double z2 = (float) (pos.getZ() + 8) - Mth.cos(ranFlt) * (float) size / 8.0F;
        double y1 = randY + level.getRandom().nextInt(3) - 2;
        double y2 = randY + level.getRandom().nextInt(3) - 2;

        for (int i = 0; i < size; ++i) {
            float radScl = (float) i / (float) size;
            double xn = x1 + (x2 - x1) * (double) radScl;
            double yn = y1 + (y2 - y1) * (double) radScl;
            double zn = z1 + (z2 - z1) * (double) radScl;
            double noise = level.getRandom().nextDouble() * (double) size / 16.0D;
            double radius = (double) (Mth.sin((float) Math.PI * radScl) + 1.0F) * noise + 1.0D;
            int xmin = Mth.floor(xn - radius / 2.0D);
            int ymin = Mth.floor(yn - radius / 2.0D);
            int zmin = Mth.floor(zn - radius / 2.0D);
            int xmax = Mth.floor(xn + radius / 2.0D);
            int ymax = Mth.floor(yn + radius / 2.0D);
            int zmax = Mth.floor(zn + radius / 2.0D);

            for (int x = xmin; x <= xmax; ++x) {
                double layerRadX = ((double) x + 0.5D - xn) / (radius / 2.0D);

                if (layerRadX * layerRadX < 1.0D) {
                    for (int y = ymin; y <= ymax; ++y) {
                        double layerRadY = ((double) y + 0.5D - yn) / (radius / 2.0D);

                        if (layerRadX * layerRadX + layerRadY * layerRadY < 1.0D) {
                            for (int z = zmin; z <= zmax; ++z) {
                                double layerRadZ = ((double) z + 0.5D - zn) / (radius / 2.0D);

                                if (layerRadX * layerRadX + layerRadY * layerRadY + layerRadZ * layerRadZ < 1.0D) {
                                    placeBlock(level, rand, new BlockPos(x, y, z), model);
                                }
                            }
                        }
                    }
                }
            }

        }

        if (rand.nextInt(100) < model.chance && !model.sampleBlocks.isEmpty())
            placeSurfaceSample(rand, pos, level, model);

        return true;
    }

    private void placeBlock(@NotNull WorldGenLevel level, RandomSource rand, BlockPos pos, @NotNull DenseDepositModel model) {
        if (!model.target.test(level.getBlockState(pos), rand)) return;

        //TODO: Rework the weight system
        int index = rand.nextInt(model.blocks.size());
        try {
            DepositBlockModel depositBlockModel = model.blocks.get(index);
            if (depositBlockModel.getBlock() != null) {

                Block block = BuiltInRegistries.BLOCK.get(ResourceLocation.parse(depositBlockModel.getBlock()));
                level.setBlock(pos, block.defaultBlockState(), 2);

            } else if (depositBlockModel.getTag() != null) {

                HolderSet.Named<Block> blockITag = BuiltInRegistries.BLOCK.getTag(EETags.getBlockTag(ResourceLocation.parse(depositBlockModel.getTag()))).get();
                blockITag.getRandomElement(rand).ifPresent(block -> level.setBlock(pos, block.value().defaultBlockState(), 2));

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

    private void placeSampleBlock(WorldGenLevel level, RandomSource rand, BlockPos samplePos, DenseDepositModel config) {
        try {
            int index = rand.nextInt(config.sampleBlocks.size());
            DepositSampleBlockModel depositSampleBlockModel = config.sampleBlocks.get(index);

            if (depositSampleBlockModel.getBlock() != null) {

                Block sampleBlock = BuiltInRegistries.BLOCK.get(ResourceLocation.parse(depositSampleBlockModel.getBlock()));
                level.setBlock(samplePos, sampleBlock.defaultBlockState(), 2);

            } else if (depositSampleBlockModel.getTag() != null) {

                HolderSet.Named<Block> blockITag = BuiltInRegistries.BLOCK.getTag(EETags.getBlockTag(ResourceLocation.parse(depositSampleBlockModel.getTag()))).get();
                blockITag.getRandomElement(rand).ifPresent(block -> level.setBlock(samplePos, block.value().defaultBlockState(), 2));

            } else if (depositSampleBlockModel.getMaterial() != null) {

                Block sampleBlock = EERegistrar.oreSampleBlockTable.get(depositSampleBlockModel.getStrata(), depositSampleBlockModel.getMaterial()).get();
                level.setBlock(samplePos, sampleBlock.defaultBlockState(), 2);

            }
        } catch (Exception e) {
            JsonElement modelJson = JsonOps.INSTANCE.withEncoder(CODEC).apply(config).result().orElseGet(() -> new JsonPrimitive("Failed to serialize model!"));
            EmendatusEnigmatica.logger.error("model: {}", new Gson().toJson(modelJson), e);
        }
    }

    private void placeSurfaceSample(RandomSource rand, @NotNull BlockPos pos, @NotNull WorldGenLevel level, DenseDepositModel config) {
        BlockPos sample = new BlockPos(pos.getX(), level.getHeight(Heightmap.Types.WORLD_SURFACE, pos.getX(), pos.getZ()), pos.getZ());
        if (level.getBlockState(sample.below()).getBlock() == Blocks.WATER)
            sample = new BlockPos(pos.getX(), level.getHeight(Heightmap.Types.OCEAN_FLOOR, pos.getX(), pos.getZ()), pos.getZ());

        if (sample.getY() > level.getMinBuildHeight() + 3 && level.getBlockState(sample.below()).is(BlockTags.LEAVES)) {
            for (int l = 0; l < 3; ++l) {
                int i = rand.nextInt(2);
                int j = rand.nextInt(2);
                int k = rand.nextInt(2);
                float f = (float)(i + j + k) * 0.333F + 0.5F;

                for (BlockPos samplePos : BlockPos.betweenClosed(sample.offset(-i, -j, -k), sample.offset(i, j, k)))
                    if (samplePos.distSqr(sample) <= (double)(f * f) && config.placed)
                        placeSampleBlock(level, rand, samplePos, config);

                sample = sample.offset(-1 + rand.nextInt(2), -rand.nextInt(2), -1 + rand.nextInt(2));
            }

        }

        config.placed = false;
    }
}