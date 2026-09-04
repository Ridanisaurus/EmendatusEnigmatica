///*
// * MIT License
// *
// * Copyright (c) 2024. Ridanisaurus
// *
// * Permission is hereby granted, free of charge, to any person obtaining a copy
// * of this software and associated documentation files (the "Software"), to deal
// * in the Software without restriction, including without limitation the rights
// * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// * copies of the Software, and to permit persons to whom the Software is
// * furnished to do so, subject to the following conditions:
// *
// * The above copyright notice and this permission notice shall be included in all
// * copies or substantial portions of the Software.
// *
// * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// * SOFTWARE.
// */
//
//package com.ridanisaurus.emendatusenigmatica.world.gen.featureNew;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonElement;
//import com.mojang.serialization.JsonOps;
//import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
//import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.dike.DikeDepositModel;
//import com.ridanisaurus.emendatusenigmatica.plugin.model.depositnew.DepositSampleBlockModel;
//import com.ridanisaurus.emendatusenigmatica.plugin.model.depositnew.DepositBlockModel;
//import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
//import com.ridanisaurus.emendatusenigmatica.registries.EETags;
//import com.ridanisaurus.emendatusenigmatica.world.gen.feature.config.DikeOreFeatureConfig;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.HolderSet;
//import net.minecraft.core.registries.BuiltInRegistries;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.tags.BlockTags;
//import net.minecraft.util.RandomSource;
//import net.minecraft.world.level.ChunkPos;
//import net.minecraft.world.level.WorldGenLevel;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraft.world.level.levelgen.Heightmap;
//import net.minecraft.world.level.levelgen.LegacyRandomSource;
//import net.minecraft.world.level.levelgen.WorldgenRandom;
//import net.minecraft.world.level.levelgen.feature.Feature;
//import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
//import net.minecraft.world.level.levelgen.synth.NormalNoise;
//
//// Credit: Geolysis
//public class DikeOreFeature extends Feature<DikeOreFeatureConfig> {
////    TODO: Rework for new Registry and Deposit System
//    //    private final EmendatusDataRegistry registry;
//
//    public DikeOreFeature() {
//        super(DikeOreFeatureConfig.CODEC);
////        this.registry = EmendatusEnigmatica.getInstance().getDataRegistry();
//    }
//
//    @Override
//    public boolean place(FeaturePlaceContext<DikeOreFeatureConfig> context) {
//        RandomSource rand = context.random();
//        BlockPos pos = context.origin();
//        WorldGenLevel level = context.level();
//        var config = context.config();
//        var model = config.model;
//
//        WorldgenRandom worldgenRandom = new WorldgenRandom(new LegacyRandomSource(level.getSeed()));
//        NormalNoise normalNoise = NormalNoise.create(worldgenRandom, -2, 4.0D); // INT Sparseness - DOUBLE ARRAY Density
//        ChunkPos chunkPos = new ChunkPos(pos);
//        int size = model.getSize();
//        int xPos = chunkPos.getMinBlockX() + level.getRandom().nextInt(16);
//        int zPos = chunkPos.getMinBlockZ() + level.getRandom().nextInt(16);
//
//        int yTop = model.getMaxYLevel();
//        int yBottom = model.getMinYLevel();
//
//        BlockPos basePos = new BlockPos(xPos, yBottom, zPos);
//
//        // TODO: Figure out the Size and other Parameters
//
//        // NOTE: Wait, does this mean this doesn't scale yet?
//
//        for (int dY = yBottom; dY <= yTop; dY++) {
//            for (int dX = -size; dX <= size; dX++) {
//                for (int dZ = -size; dZ <= size; dZ++) {
//                    float dist = (dX * dX) + (dZ * dZ);
//                    if (dist > size) {
//                        continue;
//                    }
//                    if (normalNoise.getValue(dX, dY, dZ) >= 0.5) {
//                        placeBlock(level, rand, new BlockPos(basePos.getX() + dX, dY, basePos.getZ() + dZ), config);
//                    }
//                }
//            }
//        }
////        for (int dY = yBottom; dY <= yTop; dY++) {
////            if (normalNoise.getValue(basePos.getX(), dY, basePos.getZ()) >= 0.5) {
////                placeBlock(level, rand, new BlockPos(basePos.getX(), dY, basePos.getZ()), config);
////            }
////        }
//        if (rand.nextInt(100) < model.getChance() && !config.sampleBlocks.isEmpty()) {
//            placeSurfaceSample(rand, pos, level, config);
//        }
//        return true;
//    }
//
//    private void placeBlock(WorldGenLevel level, RandomSource rand, BlockPos pos, DikeOreFeatureConfig config) {
//        if (!config.target.test(level.getBlockState(pos), rand)) {
//            return;
//        }
//
//        int index = rand.nextInt(config.blocks.size());
//        try {
//            DepositBlockModel depositBlockModel = config.blocks.get(index);
//            if (depositBlockModel.getBlock() != null) {
//                Block block = BuiltInRegistries.BLOCK.get(ResourceLocation.parse(depositBlockModel.getBlock()));
//                if (pos.getY() >= depositBlockModel.getMin() && pos.getY() <= depositBlockModel.getMax() ) {
//                    level.setBlock(pos, block.defaultBlockState(), 2);
//                }
//            } else if (depositBlockModel.getTag() != null) {
//                HolderSet.Named<Block> blockITag = BuiltInRegistries.BLOCK.getTag(EETags.getBlockTag(ResourceLocation.parse(depositBlockModel.getTag()))).get();
//                blockITag.getRandomElement(rand).ifPresent(block -> {
//                    if (pos.getY() >= depositBlockModel.getMin() && pos.getY() <= depositBlockModel.getMax() ) {
//                        level.setBlock(pos, block.value().defaultBlockState(), 2);
//                    }
//                });
//            } else if (depositBlockModel.getMaterial() != null) {
////                StrataModel strata = registry.getStrataFromFiller(BuiltInRegistries.BLOCK.getKey(level.getBlockState(pos).getBlock()));
////                if (strata != null) {
////                    Block block = EERegistrar.oreBlockTable.get(strata.getId(), depositBlockModel.getMaterial()).get();
////                    if (pos.getY() >= depositBlockModel.getMin() && pos.getY() <= depositBlockModel.getMax() ) {
////                        level.setBlock(pos, block.defaultBlockState(), 2);
////                    }
////                }
//            }
//            config.placed = true;
//        } catch (Exception e) {
//            JsonElement modelJson = JsonOps.INSTANCE.withEncoder(DikeDepositModel.CODEC).apply(config.model).result().get();
//            EmendatusEnigmatica.logger.error("index: " + index + ", model: " + new Gson().toJson(modelJson), e);
//        }
//    }
//
//    private void placeSampleBlock(WorldGenLevel level, RandomSource rand, BlockPos samplePos, DikeOreFeatureConfig config) {
//        try {
//            int index = rand.nextInt(config.sampleBlocks.size());
//            DepositSampleBlockModel depositSampleBlockModel = config.sampleBlocks.get(index);
//
//            if (depositSampleBlockModel.getBlock() != null) {
//                Block sampleBlock = BuiltInRegistries.BLOCK.get(ResourceLocation.parse(depositSampleBlockModel.getBlock()));
//                level.setBlock(samplePos, sampleBlock.defaultBlockState(), 2);
//            } else if (depositSampleBlockModel.getTag() != null) {
//                HolderSet.Named<Block> blockITag = BuiltInRegistries.BLOCK.getTag(EETags.getBlockTag(ResourceLocation.parse(depositSampleBlockModel.getTag()))).get();
//                blockITag.getRandomElement(rand).ifPresent(block -> {
//                    level.setBlock(samplePos, block.value().defaultBlockState(), 2);
//                });
//            } else if (depositSampleBlockModel.getMaterial() != null) {
//                Block sampleBlock = EERegistrar.oreSampleBlockTable.get(depositSampleBlockModel.getStrata(), depositSampleBlockModel.getMaterial()).get();
//                level.setBlock(samplePos, sampleBlock.defaultBlockState(), 2);
//            }
//        } catch (Exception e) {
//            JsonElement modelJson = JsonOps.INSTANCE.withEncoder(DikeDepositModel.CODEC).apply(config.model).result().get();
//            EmendatusEnigmatica.logger.error("model: " + new Gson().toJson(modelJson), e);
//        }
//    }
//
//    private void placeSurfaceSample(RandomSource rand, BlockPos pos, WorldGenLevel level, DikeOreFeatureConfig config) {
//        BlockPos sample = new BlockPos(pos.getX(), level.getHeight(Heightmap.Types.WORLD_SURFACE, pos.getX(), pos.getZ()), pos.getZ());
//        if (level.getBlockState(sample.below()).getBlock() == Blocks.WATER) {
//            sample = new BlockPos(pos.getX(), level.getHeight(Heightmap.Types.OCEAN_FLOOR, pos.getX(), pos.getZ()), pos.getZ());
//        }
//        if (sample.getY() > level.getMinBuildHeight() + 3 && level.getBlockState(sample.below()).is(BlockTags.LEAVES)) {
//            for(int l = 0; l < 3; ++l) {
//                int i = rand.nextInt(2);
//                int j = rand.nextInt(2);
//                int k = rand.nextInt(2);
//                float f = (float)(i + j + k) * 0.333F + 0.5F;
//
//                for(BlockPos samplePos : BlockPos.betweenClosed(sample.offset(-i, -j, -k), sample.offset(i, j, k))) {
//                    if (samplePos.distSqr(sample) <= (double)(f * f) && config.placed) {
//                        placeSampleBlock(level, rand, samplePos, config);
//                    }
//                }
//                sample = sample.offset(-1 + rand.nextInt(2), -rand.nextInt(2), -1 + rand.nextInt(2));
//            }
//
//        }
//        config.placed = false;
//    }
//}