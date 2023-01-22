package com.ridanisaurus.emendatusenigmatica.world.gen.feature;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
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
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BuddingAmethystBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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

    private static final Direction[] DIRECTIONS = Direction.values();

    @Override
    public boolean place(FeaturePlaceContext<TestOreFeatureConfig> config) {
        RandomSource rand = config.random();
        BlockPos pos = config.origin();
        WorldGenLevel level = config.level();

        int yTop = model.getConfig().getMaxYLevel();
        int yBottom = model.getConfig().getMinYLevel();
//        int yPos = rand.nextInt(yTop);
//        yPos = Math.max(yPos, yBottom);
        int size = model.getConfig().getSize();

        /* Vanilla Geode */
//        UniformInt outerWallDistance = UniformInt.of(4, 6);
//        UniformInt distributionPoint = UniformInt.of(3, 4);
//        UniformInt pointOffset = UniformInt.of(1, 2);
//
//        int invalidBlocksThreshold = 1;
//        double noiseMultiplier = 0.05D;
//        double useAlternateLayer0Chance = 0.083D;
//        double usePotentialPlacementsChance = 0.35D;
//        boolean placementsRequireLayer0Alternate = true;
//
//        List<Pair<BlockPos, Integer>> list = Lists.newLinkedList();
//        int k = distributionPoint.sample(rand);
//        WorldgenRandom worldgenrandom = new WorldgenRandom(new LegacyRandomSource(level.getSeed()));
//        NormalNoise normalnoise = NormalNoise.create(worldgenrandom, -4, 1.0D);
//        List<BlockPos> list1 = Lists.newLinkedList();
//        double d0 = (double)k / (double)outerWallDistance.getMaxValue();
//
//        GeodeLayerSettings geodeLayerSettings = new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D);
//        GeodeCrackSettings geodeCrackSettings = new GeodeCrackSettings(0.0D, 2.0D, 2);
//        GeodeBlockSettings geodeBlockSettings = new GeodeBlockSettings(
//                BlockStateProvider.simple(Blocks.AIR),
//                BlockStateProvider.simple(Blocks.RED_CONCRETE),
//                BlockStateProvider.simple(Blocks.GREEN_CONCRETE),
//                BlockStateProvider.simple(Blocks.CALCITE),
//                BlockStateProvider.simple(Blocks.SMOOTH_BASALT),
//                List.of(
//                        Blocks.YELLOW_CONCRETE.defaultBlockState(),
//                        Blocks.YELLOW_GLAZED_TERRACOTTA.defaultBlockState(),
//                        Blocks.YELLOW_TERRACOTTA.defaultBlockState(),
//                        Blocks.YELLOW_WOOL.defaultBlockState()
//                ),
//                BlockTags.FEATURES_CANNOT_REPLACE,
//                BlockTags.GEODE_INVALID_BLOCKS
//        );
//
//        double d1 = 1.0D / Math.sqrt(geodeLayerSettings.filling);
//        double d2 = 1.0D / Math.sqrt(geodeLayerSettings.innerLayer + d0);
//        double d3 = 1.0D / Math.sqrt(geodeLayerSettings.middleLayer + d0);
//        double d4 = 1.0D / Math.sqrt(geodeLayerSettings.outerLayer + d0);
//        double d5 = 1.0D / Math.sqrt(geodeCrackSettings.baseCrackSize + rand.nextDouble() / 2.0D + (k > 3 ? d0 : 0.0D));
//        boolean flag = (double)rand.nextFloat() < geodeCrackSettings.generateCrackChance;
//        int l = 0;
//
//        for(int i1 = 0; i1 < k; ++i1) {
//            int j1 = outerWallDistance.sample(rand);
//            int k1 = outerWallDistance.sample(rand);
//            int l1 = outerWallDistance.sample(rand);
//            BlockPos blockpos1 = pos.offset(j1, k1, l1);
//            BlockState blockstate = level.getBlockState(blockpos1);
//            if (blockstate.isAir() || blockstate.is(BlockTags.GEODE_INVALID_BLOCKS)) {
//                ++l;
//                if (l > invalidBlocksThreshold) {
//                    return false;
//                }
//            }
//
//            list.add(Pair.of(blockpos1, pointOffset.sample(rand)));
//        }
//
//        if (flag) {
//            int i2 = rand.nextInt(4);
//            int j2 = k * 2 + 1;
//            if (i2 == 0) {
//                list1.add(pos.offset(j2, 7, 0));
//                list1.add(pos.offset(j2, 5, 0));
//                list1.add(pos.offset(j2, 1, 0));
//            } else if (i2 == 1) {
//                list1.add(pos.offset(0, 7, j2));
//                list1.add(pos.offset(0, 5, j2));
//                list1.add(pos.offset(0, 1, j2));
//            } else if (i2 == 2) {
//                list1.add(pos.offset(j2, 7, j2));
//                list1.add(pos.offset(j2, 5, j2));
//                list1.add(pos.offset(j2, 1, j2));
//            } else {
//                list1.add(pos.offset(0, 7, 0));
//                list1.add(pos.offset(0, 5, 0));
//                list1.add(pos.offset(0, 1, 0));
//            }
//        }
//
//        List<BlockPos> list2 = Lists.newArrayList();
//        Predicate<BlockState> predicate = isReplaceable(geodeBlockSettings.cannotReplace);
//
//        for (int dY = yBottom; dY <= yTop; dY++) {
//            for (BlockPos blockpos3 : BlockPos.betweenClosed(pos.offset(-16, -16, -16), pos.offset(16, 16, 16))) {
//                double d8 = normalnoise.getValue((double) blockpos3.getX(), (double) blockpos3.getY(), (double) blockpos3.getZ()) * noiseMultiplier;
//                double d6 = 0.0D;
//                double d7 = 0.0D;
//
//                for (Pair<BlockPos, Integer> pair : list) {
//                    d6 += Mth.fastInvSqrt(blockpos3.distSqr(pair.getFirst()) + (double) pair.getSecond().intValue()) + d8;
//                }
//
//                for (BlockPos blockpos6 : list1) {
//                    d7 += Mth.fastInvSqrt(blockpos3.distSqr(blockpos6) + (double) geodeCrackSettings.crackPointOffset) + d8;
//                }
//
//                if (!(d6 < d4)) {
//                    if (flag && d7 >= d5 && d6 < d1) {
//                        this.safeSetBlock(level, blockpos3, Blocks.AIR.defaultBlockState(), predicate);
//
//                        for (Direction direction1 : DIRECTIONS) {
//                            BlockPos blockpos2 = blockpos3.relative(direction1);
//                            FluidState fluidstate = level.getFluidState(blockpos2);
//                            if (!fluidstate.isEmpty()) {
//                                level.scheduleTick(blockpos2, fluidstate.getType(), 0);
//                            }
//                        }
//                    } else if (d6 >= d1) {
//                        this.safeSetBlock(level, blockpos3, geodeBlockSettings.fillingProvider.getState(rand, blockpos3), predicate);
//                    } else if (d6 >= d2) {
//                        boolean flag1 = (double) rand.nextFloat() < useAlternateLayer0Chance;
//                        if (flag1) {
//                            this.safeSetBlock(level, blockpos3, geodeBlockSettings.alternateInnerLayerProvider.getState(rand, blockpos3), predicate);
//                        } else {
//                            this.safeSetBlock(level, blockpos3, geodeBlockSettings.innerLayerProvider.getState(rand, blockpos3), predicate);
//                        }
//
//                        if ((!placementsRequireLayer0Alternate || flag1) && (double) rand.nextFloat() < usePotentialPlacementsChance) {
//                            list2.add(blockpos3.immutable());
//                        }
//                    } else if (d6 >= d3) {
//                        this.safeSetBlock(level, blockpos3, geodeBlockSettings.middleLayerProvider.getState(rand, blockpos3), predicate);
//                    } else if (d6 >= d4) {
//                        this.safeSetBlock(level, blockpos3, geodeBlockSettings.outerLayerProvider.getState(rand, blockpos3), predicate);
//                    }
//                }
//            }
//        }
//
//        List<BlockState> list3 = geodeBlockSettings.innerPlacements;
//
//        for(BlockPos blockpos4 : list2) {
//            BlockState blockstate1 = Util.getRandom(list3, rand);
//
//            for(Direction direction : DIRECTIONS) {
//                if (blockstate1.hasProperty(BlockStateProperties.FACING)) {
//                    blockstate1 = blockstate1.setValue(BlockStateProperties.FACING, direction);
//                }
//
//                BlockPos blockpos5 = blockpos4.relative(direction);
//                BlockState blockstate2 = level.getBlockState(blockpos5);
//                if (blockstate1.hasProperty(BlockStateProperties.WATERLOGGED)) {
//                    blockstate1 = blockstate1.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(blockstate2.getFluidState().isSource()));
//                }
//
//                if (BuddingAmethystBlock.canClusterGrowAtState(blockstate2)) {
//                    this.safeSetBlock(level, blockpos5, blockstate1, predicate);
//                    break;
//                }
//            }
//        }

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

//        EmendatusEnigmatica.LOGGER.info("Test Feature at: " + pos);
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