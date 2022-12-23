package com.ridanisaurus.emendatusenigmatica.world.gen.feature;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.test.TestDepositModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EETags;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.config.TestOreFeatureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

import java.util.ArrayList;

// Credit: Geolysis
public class TestOreFeature extends Feature<TestOreFeatureConfig> {
    private TestDepositModel model;
    private final EmendatusDataRegistry registry;
    private ArrayList<CommonBlockDefinitionModel> blocks;

    public TestOreFeature(Codec<TestOreFeatureConfig> codec, TestDepositModel model, EmendatusDataRegistry registry) {
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
    public boolean place(FeaturePlaceContext<TestOreFeatureConfig> config) {
        RandomSource rand = config.random();
        BlockPos pos = config.origin();
        WorldGenLevel level = config.level();

        int yTop = model.getConfig().getMaxYLevel();
        int yBottom = model.getConfig().getMinYLevel();
        int yPos = rand.nextInt(yTop);
        yPos = Math.max(yPos, yBottom);
        int size = model.getConfig().getSize();

        /* DISK */
//        int i = pos.getY();
//        int halfHeight = rand.nextInt(4);
//        int j = i + halfHeight;
//        int k = i - halfHeight - 1;
//        int l = rand.nextInt(8);
//        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
//
//        for(BlockPos blockpos1 : BlockPos.betweenClosed(pos.offset(-l, 0, -l), pos.offset(l, 0, l))) {
//            int i1 = blockpos1.getX() - pos.getX();
//            int j1 = blockpos1.getZ() - pos.getZ();
//
//            if (i1 * i1 + j1 * j1 <= l * l) {
////                flag |= this.placeColumn(diskconfiguration, worldgenlevel, randomsource, j, k, blockpos$mutableblockpos.set(blockpos1));
//                for(int i2 = j; i2 > k; --i2) {
//                    BlockPos.MutableBlockPos mutableBlockPos = blockpos$mutableblockpos.set(blockpos1).setY(i2);
//                    placeBlock(level, rand, mutableBlockPos, config);
//                }
//            }
//        }

        /* END ISLAND */
//        float f = (float)rand.nextInt(3) + 4.0F;
//
//        for(int i = 0; f > 0.5F; --i) {
//            for(int j = Mth.floor(-f); j <= Mth.ceil(f); ++j) {
//                for(int k = Mth.floor(-f); k <= Mth.ceil(f); ++k) {
//                    if ((float)(j * j + k * k) <= (f + 1.0F) * (f + 1.0F)) {
////                        this.setBlock(worldgenlevel, blockpos.offset(j, i, k), Blocks.END_STONE.defaultBlockState());
//                        placeBlock(level, rand, new BlockPos(pos.getX() + j, pos.getY() + i, pos.getZ() + k), config);
//                    }
//                }
//            }
//
//            f -= (float)rand.nextInt(2) + 0.5F;
//        }

        /* DELTA */
//        boolean flag1 = rand.nextDouble() < 0.9D;
//        int i = flag1 ? rand.nextInt(16) : 0;
//        int j = flag1 ? rand.nextInt(16) : 0;
//        boolean flag2 = flag1 && i != 0 && j != 0;
//        int k = rand.nextInt(16);
//        int l = rand.nextInt(16);
//        int i1 = Math.max(k, l);
//
//        for (int dY = yBottom; dY <= yTop; dY++) {
//            for (BlockPos blockpos1 : BlockPos.withinManhattan(pos, k, dY, l)) {
//                if (blockpos1.distManhattan(pos) > i1) {
//                    break;
//                }
//
//                if (flag2) {
//                    placeBlock(level, rand, blockpos1, config);
//                }
//
//                BlockPos blockpos2 = blockpos1.offset(i, dY, j);
//                placeBlock(level, rand, blockpos2, config);
//
//            }
//        }

        /* COLUMNS */
//        int centerX = pos.getX() + 8;
//        int centerY = pos.getY() + 32;
//        int centerZ = pos.getZ() + 8;
//        BlockPos pos1 = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
//        // Generate the blocks of the feature
//        for (int y = yBottom; y <= yTop; y++) {
//            for (int x = 0; x < 16; x++) {
//                for (int z = 0; z < 16; z++) {
//                    // Calculate the distance from the center of the feature
//                    double distance = Math.sqrt(Math.pow(centerX - x - pos.getX(), 2) + Math.pow(y - pos.getY(), 2) + Math.pow(centerZ - z - pos.getZ(), 2));
//
//                    // Check if the block is within the radius of the tendon shape
////                    if (distance < yPos) {
//                        // Check if the block is within the height range of the tendon shape
//                            // Set the block to the desired block state (e.g. stone)
////                            level.setBlockState(new BlockPos(x + pos.getX(), y + pos.getY(), z + pos.getZ()), Blocks.STONE.getDefaultState(), 2);
//                        if (rand.nextDouble() < 0.5) {
//                            pos1 = new BlockPos(pos.getX() + x, y, pos.getZ() + z);
//                            placeBlock(level, rand, pos1, config);
//                        }
////                    }
//                }
//            }
//        }

        /* SPIRAL */
//        int NUM_SEGMENTS = 16;
//        float RADIUS = 16.0f;
//        int MIN_HEIGHT = -64;
//        int MAX_HEIGHT = 128;
//
//        // Set the initial angle to 0
//        float angle = 0;
//
//        // Set the angle increment to 2*PI/NUM_SEGMENTS
//        float angleIncrement = (float)(2*Math.PI/NUM_SEGMENTS);
//
//        // Set the initial radius to 0
//        float r = 0;
//
//        // Set the radius increment to RADIUS/NUM_SEGMENTS
//        float radiusIncrement = RADIUS/NUM_SEGMENTS;
//
//        // Set the initial y coordinate to a random value between MIN_HEIGHT and MAX_HEIGHT
//        int y = MIN_HEIGHT + rand.nextInt() * (MAX_HEIGHT - MIN_HEIGHT);
//
//        // Set the y increment to (MAX_HEIGHT - MIN_HEIGHT)/NUM_SEGMENTS
//        int yIncrement = (MAX_HEIGHT - MIN_HEIGHT)/NUM_SEGMENTS;
//
//        BlockPos pos1 = pos;
//
//        // Create the spiral shape
//        for (int i = 0; i < NUM_SEGMENTS; i++) {
//
//            // Calculate the next point in the spiral
//            int xP = pos.getX() + (int) (r * Math.cos(angle));
//            int zP = pos.getZ() + (int) (r * Math.sin(angle));
//            int yP = pos.getY() + y;
//
//            // Set the block at the current position to stone
////            world.setBlockState(new BlockPos(x, y, z), Blocks.STONE.getDefaultState(), 2);
//            for (int dY = yBottom; dY <= yTop; dY++) {
//                pos1 = new BlockPos(xP + 3, dY, zP + 3);
//                placeBlock(level, rand, pos1, config);
//                placeBlock(level, rand, pos1.north(), config);
//                placeBlock(level, rand, pos1.south(), config);
//                placeBlock(level, rand, pos1.east(), config);
//                placeBlock(level, rand, pos1.west(), config);
//            }
//
//            // Increment the angle and radius
//            angle += angleIncrement;
//            r += radiusIncrement;
//            y += yIncrement;
//        }

//        EmendatusEnigmatica.LOGGER.info("Test Feature at: " + pos1);

//        placeBlock(reader, rand, new BlockPos(x, y, z), config);

        return true;
    }

    private void placeBlock(WorldGenLevel reader, RandomSource rand, BlockPos pos, FeaturePlaceContext<TestOreFeatureConfig> config) {
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
            JsonElement modelJson = JsonOps.INSTANCE.withEncoder(TestDepositModel.CODEC).apply(model).result().get();
            EmendatusEnigmatica.LOGGER.error("index: " + index + ", model: " + new Gson().toJson(modelJson));
            e.printStackTrace();
        }
    }
}