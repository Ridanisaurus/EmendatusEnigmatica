package com.ridanisaurus.emendatusenigmatica.world.gen.feature;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.sphere.SphereDepositModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.util.MathHelper;
import com.ridanisaurus.emendatusenigmatica.util.WorldGenHelper;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.config.SphereOreFeatureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Random;
// TODO: [BUUZ] Not sure what changed here, but I already have implemented one of the place() methods, but it is still asking for the FeaturePlaceContext one
public class SphereOreFeature extends Feature<SphereOreFeatureConfig> {
    private SphereDepositModel model;
    private ArrayList<CommonBlockDefinitionModel> blocks;

    public SphereOreFeature(Codec<SphereOreFeatureConfig> codec, SphereDepositModel model) {
        super(codec);
        this.model = model;
        blocks = new ArrayList<>();
        for (CommonBlockDefinitionModel block : model.getConfig().getBlocks()) {
            NonNullList<CommonBlockDefinitionModel> filled = NonNullList.withSize(block.getWeight(), block);
            blocks.addAll(filled);
        }
    }

    @Override
    public boolean place(SphereOreFeatureConfig config, WorldGenLevel reader, ChunkGenerator generator, Random rand, BlockPos pos) {
        if (!model.getDimensions().contains(WorldGenHelper.getDimensionAsString(reader.getLevel()))) {
            return false;
        }

        if (rand.nextInt(100) > model.getConfig().getChance()) {
            return false;
        }

        int yTop = model.getConfig().getMaxYLevel();
        int yBottom = model.getConfig().getMinYLevel();

        int yPos = rand.nextInt(yTop);
        yPos = Math.max(yPos, yBottom);

        int radius = model.getConfig().getRadius();

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
                    placeBlock(reader, generator, rand, new BlockPos(pos.getX()+ x, yPos + y, pos.getZ() + z), config);
                    placeBlock(reader, generator, rand, new BlockPos(pos.getX()+ -x, yPos + y, pos.getZ() + z), config);
                    placeBlock(reader, generator, rand, new BlockPos(pos.getX()+ x, yPos + -y, pos.getZ() + z), config);
                    placeBlock(reader, generator, rand, new BlockPos(pos.getX()+ x, yPos + y, pos.getZ() + -z), config);
                    placeBlock(reader, generator, rand, new BlockPos(pos.getX()+ -x, yPos + -y, pos.getZ() + z), config);
                    placeBlock(reader, generator, rand, new BlockPos(pos.getX()+ x, yPos + -y, pos.getZ() + -z), config);
                    placeBlock(reader, generator, rand, new BlockPos(pos.getX()+ -x, yPos + y, pos.getZ() + -z), config);
                    placeBlock(reader, generator, rand, new BlockPos(pos.getX()+ -x, yPos + -y, pos.getZ() + -z), config);
                }
            }
        }
        return true;
    }

    private void placeBlock(WorldGenLevel reader, ChunkGenerator generator, Random rand, BlockPos
            pos, SphereOreFeatureConfig config) {
        if (!config.target.test(reader.getBlockState(pos), rand)) {
            return;
        }
        if (rand.nextInt(100) > model.getConfig().getDensity()) {
            return;
        }
        // TODO: [BUUZ] The .getAllTags() seems to have been completely removed since they moved away from the Tag Collection
        int index = rand.nextInt(blocks.size());
        try {
            CommonBlockDefinitionModel commonBlockDefinitionModel = blocks.get(index);
            if (commonBlockDefinitionModel.getBlock() != null) {
                Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(commonBlockDefinitionModel.getBlock()));
                reader.setBlock(pos, block.defaultBlockState(), 2);
            } else if (commonBlockDefinitionModel.getTag() != null) {
                ITag<Block> blockITag = BlockTags.getAllTags().getTag(new ResourceLocation(commonBlockDefinitionModel.getTag()));
                Block block = blockITag.getRandomElement(rand);
                reader.setBlock(pos, block.defaultBlockState(), 2);
            } else if (commonBlockDefinitionModel.getMaterial() != null) {
                BlockState currentFiller = reader.getBlockState(pos);
                String fillerId = currentFiller.getBlock().getRegistryName().toString();
                Integer strataIndex = EELoader.STRATA_INDEX_BY_FILLER.getOrDefault(fillerId, null);
                if (strataIndex != null) {
                    StrataModel stratum = EELoader.STRATA.get(strataIndex);
                    Block block = EERegistrar.oreBlockTable.get(stratum.getId(), commonBlockDefinitionModel.getMaterial()).get();
                    reader.setBlock(pos, block.defaultBlockState(), 2);
                }
            }
        } catch (Exception e) {
            JsonElement modelJson = JsonOps.INSTANCE.withEncoder(SphereDepositModel.CODEC).apply(model).result().get();
            EmendatusEnigmatica.LOGGER.error("index: " + index + ", model: " + new Gson().toJson(modelJson));
            e.printStackTrace();
        }
    }
}

