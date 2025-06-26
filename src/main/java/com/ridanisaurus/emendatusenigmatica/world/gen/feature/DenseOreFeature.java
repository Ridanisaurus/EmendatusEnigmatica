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
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.dense.DenseDepositModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.sample.SampleBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EETags;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.config.DenseOreFeatureConfig;
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

// Credit: Geolysis
public class DenseOreFeature extends Feature<DenseOreFeatureConfig> {
    private final EmendatusDataRegistry registry;

    public DenseOreFeature() {
        super(DenseOreFeatureConfig.CODEC);
        this.registry = EmendatusEnigmatica.getInstance().getDataRegistry();
    }

    @Override
    public boolean place(FeaturePlaceContext<DenseOreFeatureConfig> context) {
        RandomSource rand = context.random();
        BlockPos pos = context.origin();
        WorldGenLevel level = context.level();
        var config = context.config();
        var model = config.model;

        int yTop = model.getMaxYLevel();
        int yBottom = model.getMinYLevel();
        // TODO: Fix the size calculation
        int size = model.getSize();

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
                                    placeBlock(level, rand, new BlockPos(x, y, z), config);
                                }
                            }
                        }
                    }
                }
            }

        }
        if (rand.nextInt(100) < model.getChance() && !config.sampleBlocks.isEmpty()) {
            placeSurfaceSample(rand, pos, level, config);
        }
        return true;
    }

    private void placeBlock(WorldGenLevel reader, RandomSource rand, BlockPos pos, DenseOreFeatureConfig config) {
        if (!config.target.test(reader.getBlockState(pos), rand)) {
            return;
        }

        int index = rand.nextInt(config.blocks.size());
        try {
            CommonBlockDefinitionModel commonBlockDefinitionModel = config.blocks.get(index);
            if (commonBlockDefinitionModel.getBlock() != null) {
                Block block = BuiltInRegistries.BLOCK.get(ResourceLocation.parse(commonBlockDefinitionModel.getBlock()));
                reader.setBlock(pos, block.defaultBlockState(), 2);
            } else if (commonBlockDefinitionModel.getTag() != null) {
                HolderSet.Named<Block> blockITag = BuiltInRegistries.BLOCK.getTag(EETags.getBlockTag(ResourceLocation.parse(commonBlockDefinitionModel.getTag()))).get();
                blockITag.getRandomElement(rand).ifPresent(block -> {
                    reader.setBlock(pos, block.value().defaultBlockState(), 2);
                });
            } else if (commonBlockDefinitionModel.getMaterial() != null) {
                StrataModel strata = registry.getStrataFromFiller(BuiltInRegistries.BLOCK.getKey(reader.getBlockState(pos).getBlock()));
                if (strata != null) {
                    Block block = EERegistrar.oreBlockTable.get(strata.getId(), commonBlockDefinitionModel.getMaterial()).get();
                    reader.setBlock(pos, block.defaultBlockState(), 2);
                }
            }
            config.placed = true;
        } catch (Exception e) {
            JsonElement modelJson = JsonOps.INSTANCE.withEncoder(DenseDepositModel.CODEC).apply(config.model).result().get();
            EmendatusEnigmatica.logger.error("index: " + index + ", model: " + new Gson().toJson(modelJson), e);
        }
    }

    private void placeSampleBlock(WorldGenLevel level, RandomSource rand, BlockPos samplePos, DenseOreFeatureConfig config) {
        try {
            int index = rand.nextInt(config.sampleBlocks.size());
            SampleBlockDefinitionModel sampleBlockDefinitionModel = config.sampleBlocks.get(index);

            if (sampleBlockDefinitionModel.getBlock() != null) {
                Block sampleBlock = BuiltInRegistries.BLOCK.get(ResourceLocation.parse(sampleBlockDefinitionModel.getBlock()));
                level.setBlock(samplePos, sampleBlock.defaultBlockState(), 2);
            } else if (sampleBlockDefinitionModel.getTag() != null) {
                HolderSet.Named<Block> blockITag = BuiltInRegistries.BLOCK.getTag(EETags.getBlockTag(ResourceLocation.parse(sampleBlockDefinitionModel.getTag()))).get();
                blockITag.getRandomElement(rand).ifPresent(block -> {
                    level.setBlock(samplePos, block.value().defaultBlockState(), 2);
                });
            } else if (sampleBlockDefinitionModel.getMaterial() != null) {
                Block sampleBlock = EERegistrar.oreSampleBlockTable.get(sampleBlockDefinitionModel.getStrata(), sampleBlockDefinitionModel.getMaterial()).get();
                level.setBlock(samplePos, sampleBlock.defaultBlockState(), 2);
            }
        } catch (Exception e) {
            JsonElement modelJson = JsonOps.INSTANCE.withEncoder(DenseDepositModel.CODEC).apply(config.model).result().get();
            EmendatusEnigmatica.logger.error("model: " + new Gson().toJson(modelJson), e);
        }
    }

    private void placeSurfaceSample(RandomSource rand, BlockPos pos, WorldGenLevel level, DenseOreFeatureConfig config) {
        BlockPos sample = new BlockPos(pos.getX(), level.getHeight(Heightmap.Types.WORLD_SURFACE, pos.getX(), pos.getZ()), pos.getZ());
        if (level.getBlockState(sample.below()).getBlock() == Blocks.WATER) {
            sample = new BlockPos(pos.getX(), level.getHeight(Heightmap.Types.OCEAN_FLOOR, pos.getX(), pos.getZ()), pos.getZ());
        }
        if (sample.getY() > level.getMinBuildHeight() + 3 && level.getBlockState(sample.below()).is(BlockTags.LEAVES)) {
            for(int l = 0; l < 3; ++l) {
                int i = rand.nextInt(2);
                int j = rand.nextInt(2);
                int k = rand.nextInt(2);
                float f = (float)(i + j + k) * 0.333F + 0.5F;

                for(BlockPos samplePos : BlockPos.betweenClosed(sample.offset(-i, -j, -k), sample.offset(i, j, k))) {
                    if (samplePos.distSqr(sample) <= (double)(f * f) && config.placed) {
                        placeSampleBlock(level, rand, samplePos, config);
                    }
                }
                sample = sample.offset(-1 + rand.nextInt(2), -rand.nextInt(2), -1 + rand.nextInt(2));
            }

        }
        config.placed = false;
    }
}