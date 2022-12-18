package com.ridanisaurus.emendatusenigmatica.world.gen.feature;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.dense.DenseDepositModel;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

import java.util.ArrayList;

// Credit: Geolysis
public class DenseOreFeature extends Feature<DenseOreFeatureConfig> {
    private DenseDepositModel model;
    private final EmendatusDataRegistry registry;
    private ArrayList<CommonBlockDefinitionModel> blocks;

    public DenseOreFeature(Codec<DenseOreFeatureConfig> codec, DenseDepositModel model, EmendatusDataRegistry registry) {
        super(codec);
        this.model = model;
        this.registry = registry;
        blocks = new ArrayList<>();
        for (CommonBlockDefinitionModel block : model.getConfig().getBlocks()) {
            NonNullList<CommonBlockDefinitionModel> filled = NonNullList.withSize(block.getWeight(), block);
            blocks.addAll(filled);
        }
    }

    @Override
    public boolean place(FeaturePlaceContext<DenseOreFeatureConfig> config) {
        RandomSource rand = config.random();
        BlockPos pos = config.origin();
        WorldGenLevel reader = config.level();

        int yTop = model.getConfig().getMaxYLevel();
        int yBottom = model.getConfig().getMinYLevel();
        // TODO: Fix the size calculation
        int size = model.getConfig().getSize();

        int randY = yBottom + reader.getRandom().nextInt(yTop - yBottom);

        float ranFlt = reader.getRandom().nextFloat() * (float) Math.PI;
        double x1 = (float) (pos.getX() + 8) + Mth.sin(ranFlt) * (float) size / 8.0F;
        double x2 = (float) (pos.getX() + 8) - Mth.sin(ranFlt) * (float) size / 8.0F;
        double z1 = (float) (pos.getZ() + 8) + Mth.cos(ranFlt) * (float) size / 8.0F;
        double z2 = (float) (pos.getZ() + 8) - Mth.cos(ranFlt) * (float) size / 8.0F;
        double y1 = randY + reader.getRandom().nextInt(3) - 2;
        double y2 = randY + reader.getRandom().nextInt(3) - 2;

        for (int i = 0; i < size; ++i) {
            float radScl = (float) i / (float) size;
            double xn = x1 + (x2 - x1) * (double) radScl;
            double yn = y1 + (y2 - y1) * (double) radScl;
            double zn = z1 + (z2 - z1) * (double) radScl;
            double noise = reader.getRandom().nextDouble() * (double) size / 16.0D;
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
                                    placeBlock(reader, rand, new BlockPos(x, y, z), config);
                                }
                            }
                        }
                    }
                }
            }

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
        } catch (Exception e) {
            JsonElement modelJson = JsonOps.INSTANCE.withEncoder(DenseDepositModel.CODEC).apply(model).result().get();
            EmendatusEnigmatica.LOGGER.error("index: " + index + ", model: " + new Gson().toJson(modelJson));
            e.printStackTrace();
        }
    }
}