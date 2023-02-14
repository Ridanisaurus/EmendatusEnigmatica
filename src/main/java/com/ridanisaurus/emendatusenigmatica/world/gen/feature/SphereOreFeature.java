package com.ridanisaurus.emendatusenigmatica.world.gen.feature;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sample.SampleBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sphere.SphereDepositModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EETags;
import com.ridanisaurus.emendatusenigmatica.util.MathHelper;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.config.SphereOreFeatureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

import java.util.ArrayList;
import java.util.List;

public class SphereOreFeature extends Feature<SphereOreFeatureConfig> {
    private SphereDepositModel model;
    private final EmendatusDataRegistry registry;
    private ArrayList<CommonBlockDefinitionModel> blocks;
    private ArrayList<SampleBlockDefinitionModel> sampleBlocks;
    private boolean placed = false;

    public SphereOreFeature(Codec<SphereOreFeatureConfig> codec, SphereDepositModel model, EmendatusDataRegistry registry) {
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
    public boolean place(FeaturePlaceContext<SphereOreFeatureConfig> config) {
        RandomSource rand = config.random();
        BlockPos pos = config.origin();
        WorldGenLevel level = config.level();

        int yTop = model.getMaxYLevel();
        int yBottom = model.getMinYLevel();

        int yPos = rand.nextInt(yTop);
        yPos = Math.max(yPos, yBottom);
        // TODO: Fix the radius calculation
        int radius = model.getRadius();

        radius += 0.5;
        radius += 0.5;
        radius += 0.5;

        final double invRadiusX = 1d / radius;
        final double invRadiusY = 1d / radius;
        final double invRadiusZ = 1d / radius;

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
                            if (y == 0) {
                                break forX;
                            }
                            break forY;
                        }
                        break forZ;
                    }
                    if (y + yPos > yTop || y + yPos < yBottom) {
                        continue;
                    }
                    int randomizer = Math.random() >= 0.5D ? 1 : 0;
                    placeBlock(level, rand, new BlockPos(pos.getX() + x + randomizer, yPos + y + randomizer, pos.getZ() + z + randomizer), config);
                    placeBlock(level, rand, new BlockPos(pos.getX() + -x + randomizer, yPos + y + randomizer, pos.getZ() + z + randomizer), config);
                    placeBlock(level, rand, new BlockPos(pos.getX() + x + randomizer, yPos + -y + randomizer, pos.getZ() + z + randomizer), config);
                    placeBlock(level, rand, new BlockPos(pos.getX() + x + randomizer, yPos + y + randomizer, pos.getZ() + -z + randomizer), config);
                    placeBlock(level, rand, new BlockPos(pos.getX() + -x + randomizer, yPos + -y + randomizer, pos.getZ() + z + randomizer), config);
                    placeBlock(level, rand, new BlockPos(pos.getX() + x + randomizer, yPos + -y + randomizer, pos.getZ() + -z + randomizer), config);
                    placeBlock(level, rand, new BlockPos(pos.getX() + -x + randomizer, yPos + y + randomizer, pos.getZ() + -z + randomizer), config);
                    placeBlock(level, rand, new BlockPos(pos.getX() + -x + randomizer, yPos + -y + randomizer, pos.getZ() + -z + randomizer), config);
                }
            }
        }
        if (rand.nextInt(100) < model.getChance() && !sampleBlocks.isEmpty()) {
            placeSurfaceSample(rand, pos, level);
        }
        return true;
    }

    private void placeBlock(WorldGenLevel level, RandomSource rand, BlockPos pos, FeaturePlaceContext<SphereOreFeatureConfig> config) {
        if (!config.config().target.test(level.getBlockState(pos), rand)) {
            return;
        }

        int index = rand.nextInt(blocks.size());
        try {
            CommonBlockDefinitionModel commonBlockDefinitionModel = blocks.get(index);
            if (commonBlockDefinitionModel.getBlock() != null) {
                Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(commonBlockDefinitionModel.getBlock()));
                level.setBlock(pos, block.defaultBlockState(), 2);
            } else if (commonBlockDefinitionModel.getTag() != null) {
                ITag<Block> blockITag = ForgeRegistries.BLOCKS.tags().getTag(EETags.getBlockTag(new ResourceLocation(commonBlockDefinitionModel.getTag())));
                blockITag.getRandomElement(rand).ifPresent(block -> {
                    level.setBlock(pos, block.defaultBlockState(), 2);
                });
            } else if (commonBlockDefinitionModel.getMaterial() != null) {
                BlockState currentFiller = level.getBlockState(pos);
                String fillerId = ForgeRegistries.BLOCKS.getKey(currentFiller.getBlock()).toString();
                Integer strataIndex = registry.getStrataByIndex().getOrDefault(fillerId, null);
                if (strataIndex != null) {
                    StrataModel stratum = registry.getStrata().get(strataIndex);
                    Block block = EERegistrar.oreBlockTable.get(stratum.getId(), commonBlockDefinitionModel.getMaterial()).get();
                    level.setBlock(pos, block.defaultBlockState(), 2);
                }
            }
            placed = true;
        } catch (Exception e) {
            JsonElement modelJson = JsonOps.INSTANCE.withEncoder(SphereDepositModel.CODEC).apply(model).result().get();
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
            JsonElement modelJson = JsonOps.INSTANCE.withEncoder(SphereDepositModel.CODEC).apply(model).result().get();
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