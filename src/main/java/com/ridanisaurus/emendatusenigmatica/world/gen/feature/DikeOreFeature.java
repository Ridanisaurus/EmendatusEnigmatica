package com.ridanisaurus.emendatusenigmatica.world.gen.feature;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.dike.DikeDepositModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.geode.GeodeDepositModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sample.SampleBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EETags;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.config.DikeOreFeatureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

import java.util.ArrayList;
import java.util.List;

// Credit: Geolysis
public class DikeOreFeature extends Feature<DikeOreFeatureConfig> {
    private DikeDepositModel model;
    private final EmendatusDataRegistry registry;
    private ArrayList<CommonBlockDefinitionModel> blocks;
    private ArrayList<SampleBlockDefinitionModel> sampleBlocks;
    private boolean placed = false;

    public DikeOreFeature(Codec<DikeOreFeatureConfig> codec, DikeDepositModel model, EmendatusDataRegistry registry) {
        super(codec);
        this.model = model;
        this.registry = registry;
        blocks = new ArrayList<>();
        for (CommonBlockDefinitionModel block : model.getBlocks()) {
            NonNullList<CommonBlockDefinitionModel> filled = NonNullList.withSize(block.getWeight(), block);
            blocks.addAll(filled);
        }
        sampleBlocks = new ArrayList<>();
        for (SampleBlockDefinitionModel sampleBlock : model.getSampleBlocks()) {
            NonNullList<SampleBlockDefinitionModel> filled = NonNullList.withSize(sampleBlock.getWeight(), sampleBlock);
            sampleBlocks.addAll(filled);
        }
    }

    @Override
    public boolean place(FeaturePlaceContext<DikeOreFeatureConfig> config) {
        RandomSource rand = config.random();
        BlockPos pos = config.origin();
        WorldGenLevel level = config.level();

        WorldgenRandom worldgenRandom = new WorldgenRandom(new LegacyRandomSource(level.getSeed()));
        NormalNoise normalNoise = NormalNoise.create(worldgenRandom, -2, 4.0D); // INT Sparseness - DOUBLE ARRAY Density
        ChunkPos chunkPos = new ChunkPos(pos);
        int size = model.getSize();
        int xPos = chunkPos.getMinBlockX() + level.getRandom().nextInt(16);
        int zPos = chunkPos.getMinBlockZ() + level.getRandom().nextInt(16);

        int yTop = model.getMaxYLevel();
        int yBottom = model.getMinYLevel();

        BlockPos basePos = new BlockPos(xPos, yBottom, zPos);

        // TODO: Figure out the Size and other Parameters

        for (int dY = yBottom; dY <= yTop; dY++) {
            for (int dX = -size; dX <= size; dX++) {
                for (int dZ = -size; dZ <= size; dZ++) {
                    float dist = (dX * dX) + (dZ * dZ);
                    if (dist > size) {
                        continue;
                    }
                    if (normalNoise.getValue(dX, dY, dZ) >= 0.5) {
                        placeBlock(level, rand, new BlockPos(basePos.getX() + dX, dY, basePos.getZ() + dZ), config);
                    }
                }
            }
        }
//        for (int dY = yBottom; dY <= yTop; dY++) {
//            if (normalNoise.getValue(basePos.getX(), dY, basePos.getZ()) >= 0.5) {
//                placeBlock(level, rand, new BlockPos(basePos.getX(), dY, basePos.getZ()), config);
//            }
//        }
        if (rand.nextInt(100) < model.getChance() && !sampleBlocks.isEmpty()) {
            placeSurfaceSample(rand, pos, level);
        }
        return true;
    }

    private void placeBlock(WorldGenLevel level, RandomSource rand, BlockPos pos, FeaturePlaceContext<DikeOreFeatureConfig> config) {
        if (!config.config().target.test(level.getBlockState(pos), rand)) {
            return;
        }

        int index = rand.nextInt(blocks.size());
        try {
            CommonBlockDefinitionModel commonBlockDefinitionModel = blocks.get(index);
            if (commonBlockDefinitionModel.getBlock() != null) {
                Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(commonBlockDefinitionModel.getBlock()));
                if (pos.getY() >= commonBlockDefinitionModel.getMin() && pos.getY() <= commonBlockDefinitionModel.getMax() ) {
                    level.setBlock(pos, block.defaultBlockState(), 2);
                }
            } else if (commonBlockDefinitionModel.getTag() != null) {
                ITag<Block> blockITag = ForgeRegistries.BLOCKS.tags().getTag(EETags.getBlockTag(new ResourceLocation(commonBlockDefinitionModel.getTag())));
                blockITag.getRandomElement(rand).ifPresent(block -> {
                    if (pos.getY() >= commonBlockDefinitionModel.getMin() && pos.getY() <= commonBlockDefinitionModel.getMax() ) {
                        level.setBlock(pos, block.defaultBlockState(), 2);
                    }
                });
            } else if (commonBlockDefinitionModel.getMaterial() != null) {
                BlockState currentFiller = level.getBlockState(pos);
                String fillerId = ForgeRegistries.BLOCKS.getKey(currentFiller.getBlock()).toString();
                Integer strataIndex = registry.getStrataByIndex().getOrDefault(fillerId, null);
                if (strataIndex != null) {
                    StrataModel stratum = registry.getStrata().get(strataIndex);
                    Block block = EERegistrar.oreBlockTable.get(stratum.getId(), commonBlockDefinitionModel.getMaterial()).get();
                    if (pos.getY() >= commonBlockDefinitionModel.getMin() && pos.getY() <= commonBlockDefinitionModel.getMax() ) {
                        level.setBlock(pos, block.defaultBlockState(), 2);
                    }
                }
            }
            placed = true;
        } catch (Exception e) {
            JsonElement modelJson = JsonOps.INSTANCE.withEncoder(DikeDepositModel.CODEC).apply(model).result().get();
            EmendatusEnigmatica.LOGGER.error("index: " + index + ", model: " + new Gson().toJson(modelJson));
            e.printStackTrace();
        }
    }

    private void placeSampleBlock(WorldGenLevel level, RandomSource rand, BlockPos samplePos) {
        try {
            int index = rand.nextInt(sampleBlocks.size());
            SampleBlockDefinitionModel sampleBlockDefinitionModel = sampleBlocks.get(index);

            if (sampleBlockDefinitionModel.getBlock() != null) {
                Block sampleBlock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(sampleBlockDefinitionModel.getBlock()));
                level.setBlock(samplePos, sampleBlock.defaultBlockState(), 2);
            } else if (sampleBlockDefinitionModel.getTag() != null) {
                ITag<Block> blockITag = ForgeRegistries.BLOCKS.tags().getTag(EETags.getBlockTag(new ResourceLocation(sampleBlockDefinitionModel.getTag())));
                blockITag.getRandomElement(rand).ifPresent(block -> {
                    level.setBlock(samplePos, block.defaultBlockState(), 2);
                });
            } else if (sampleBlockDefinitionModel.getMaterial() != null) {
                Block sampleBlock = EERegistrar.oreSampleBlockTable.get(sampleBlockDefinitionModel.getStrata(), sampleBlockDefinitionModel.getMaterial()).get();
                level.setBlock(samplePos, sampleBlock.defaultBlockState(), 2);
            }
        } catch (Exception e) {
            JsonElement modelJson = JsonOps.INSTANCE.withEncoder(DikeDepositModel.CODEC).apply(model).result().get();
            EmendatusEnigmatica.LOGGER.error("model: " + new Gson().toJson(modelJson));
            e.printStackTrace();
        }
    }

    private void placeSurfaceSample(RandomSource rand, BlockPos pos, WorldGenLevel level) {
        BlockPos sample = new BlockPos(pos.getX(), level.getHeight(Heightmap.Types.WORLD_SURFACE, pos.getX(), pos.getZ()), pos.getZ());
        if (level.getBlockState(sample.below()).getBlock() == Blocks.WATER) {
            sample = new BlockPos(pos.getX(), level.getHeight(Heightmap.Types.OCEAN_FLOOR, pos.getX(), pos.getZ()), pos.getZ());
        }
        if (sample.getY() > level.getMinBuildHeight() + 3 && level.getBlockState(sample.below()).getMaterial() != Material.LEAVES) {
            for(int l = 0; l < 3; ++l) {
                int i = rand.nextInt(2);
                int j = rand.nextInt(2);
                int k = rand.nextInt(2);
                float f = (float)(i + j + k) * 0.333F + 0.5F;

                for(BlockPos samplePos : BlockPos.betweenClosed(sample.offset(-i, -j, -k), sample.offset(i, j, k))) {
                    if (samplePos.distSqr(sample) <= (double)(f * f) && placed) {
                        placeSampleBlock(level, rand, samplePos);
                    }
                }
                sample = sample.offset(-1 + rand.nextInt(2), -rand.nextInt(2), -1 + rand.nextInt(2));
            }

        }
        placed = false;
    }
}