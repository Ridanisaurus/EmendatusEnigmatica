package com.ridanisaurus.emendatusenigmatica.world.gen.feature;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.dense.DenseDepositModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.dike.DikeDepositModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sample.SampleBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EETags;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.config.DenseOreFeatureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
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

// Credit: Geolysis
public class DenseOreFeature extends Feature<DenseOreFeatureConfig> {
    private DenseDepositModel model;
    private final EmendatusDataRegistry registry;
    private ArrayList<CommonBlockDefinitionModel> blocks;
    private ArrayList<SampleBlockDefinitionModel> sampleBlocks;
    private boolean placed = false;

    public DenseOreFeature(Codec<DenseOreFeatureConfig> codec, DenseDepositModel model, EmendatusDataRegistry registry) {
        super(codec);
        this.model = model;
        this.registry = registry;
        blocks = new ArrayList<>();
        for (CommonBlockDefinitionModel block : model.getConfig().getBlocks()) {
            NonNullList<CommonBlockDefinitionModel> filled = NonNullList.withSize(block.getWeight(), block);
            blocks.addAll(filled);
        }
        sampleBlocks = new ArrayList<>();
        for (SampleBlockDefinitionModel sampleBlock : model.getConfig().getSampleBlocks()) {
            NonNullList<SampleBlockDefinitionModel> filled = NonNullList.withSize(sampleBlock.getWeight(), sampleBlock);
            sampleBlocks.addAll(filled);
        }
    }

    @Override
    public boolean place(FeaturePlaceContext<DenseOreFeatureConfig> config) {
        RandomSource rand = config.random();
        BlockPos pos = config.origin();
        WorldGenLevel level = config.level();

        int yTop = model.getConfig().getMaxYLevel();
        int yBottom = model.getConfig().getMinYLevel();
        // TODO: Fix the size calculation
        int size = model.getConfig().getSize();

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
        if (rand.nextInt(100) < model.getConfig().getChance()) {
            placeSurfaceSample(rand, pos, level);
        }
        return true;
    }

    private void placeBlock(WorldGenLevel reader, RandomSource rand, BlockPos pos, FeaturePlaceContext<DenseOreFeatureConfig> config) {
        if (!config.config().target.test(reader.getBlockState(pos), rand)) {
            return;
        }

        int index = rand.nextInt(blocks.size());
        try {
            CommonBlockDefinitionModel commonBlockDefinitionModel = blocks.get(index);
            if (commonBlockDefinitionModel.getBlock() != null) {
                Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(commonBlockDefinitionModel.getBlock()));
                reader.setBlock(pos, block.defaultBlockState(), 2);
            } else if (commonBlockDefinitionModel.getTag() != null) {
                ITag<Block> blockITag = ForgeRegistries.BLOCKS.tags().getTag(EETags.getBlockTag(new ResourceLocation(commonBlockDefinitionModel.getTag())));
                blockITag.getRandomElement(rand).ifPresent(block -> {
                    reader.setBlock(pos, block.defaultBlockState(), 2);
                });
            } else if (commonBlockDefinitionModel.getMaterial() != null) {
                BlockState currentFiller = reader.getBlockState(pos);
                String fillerId = ForgeRegistries.BLOCKS.getKey(currentFiller.getBlock()).toString();
                Integer strataIndex = registry.getStrataByIndex().getOrDefault(fillerId, null);
                if (strataIndex != null) {
                    StrataModel stratum = registry.getStrata().get(strataIndex);
                    Block block = EERegistrar.oreBlockTable.get(stratum.getId(), commonBlockDefinitionModel.getMaterial()).get();
                    reader.setBlock(pos, block.defaultBlockState(), 2);
                }
            }
            placed = true;
        } catch (Exception e) {
            JsonElement modelJson = JsonOps.INSTANCE.withEncoder(DenseDepositModel.CODEC).apply(model).result().get();
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
            JsonElement modelJson = JsonOps.INSTANCE.withEncoder(DenseDepositModel.CODEC).apply(model).result().get();
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