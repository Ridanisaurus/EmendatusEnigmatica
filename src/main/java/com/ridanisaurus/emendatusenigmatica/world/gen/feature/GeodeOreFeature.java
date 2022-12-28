package com.ridanisaurus.emendatusenigmatica.world.gen.feature;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.geode.GeodeDepositModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EETags;
import com.ridanisaurus.emendatusenigmatica.util.MathHelper;
import com.ridanisaurus.emendatusenigmatica.util.WorldGenHelper;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.config.GeodeOreFeatureConfig;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.config.SphereOreFeatureConfig;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BuddingAmethystBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITag;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Predicate;

public class GeodeOreFeature extends Feature<GeodeOreFeatureConfig> {
	private static final Direction[] DIRECTIONS = Direction.values();
	private final List<CommonBlockDefinitionModel> outerShellBlocks;
	private final List<CommonBlockDefinitionModel> innerShellBlocks;
	private final List<CommonBlockDefinitionModel> innerBlocks;
	private final List<CommonBlockDefinitionModel> fillBlocks;
	private final List<BlockState> clusters;
	private final GeodeDepositModel model;
	private final EmendatusDataRegistry registry;

	public GeodeOreFeature(Codec<GeodeOreFeatureConfig> codec, GeodeDepositModel model, EmendatusDataRegistry registry) {
		super(codec);
		this.model = model;
		this.registry = registry;
		outerShellBlocks = new ArrayList<>();
		for (CommonBlockDefinitionModel outerShellBlock : model.getConfig().getOuterShellBlocks()) {
			NonNullList<CommonBlockDefinitionModel> filled = NonNullList.withSize(outerShellBlock.getWeight(), outerShellBlock);
			outerShellBlocks.addAll(filled);
		}
		innerShellBlocks = new ArrayList<>();
		for (CommonBlockDefinitionModel innerShellBlock : model.getConfig().getInnerShellBlocks()) {
			NonNullList<CommonBlockDefinitionModel> filled = NonNullList.withSize(innerShellBlock.getWeight(), innerShellBlock);
			innerShellBlocks.addAll(filled);
		}
		innerBlocks = new ArrayList<>();
		for (CommonBlockDefinitionModel innerBlock : model.getConfig().getInnerBlocks()) {
			NonNullList<CommonBlockDefinitionModel> filled = NonNullList.withSize(innerBlock.getWeight(), innerBlock);
			innerBlocks.addAll(filled);
		}
		fillBlocks = new ArrayList<>();
		for (CommonBlockDefinitionModel fillBlock : model.getConfig().getFillBlocks()) {
			NonNullList<CommonBlockDefinitionModel> filled = NonNullList.withSize(fillBlock.getWeight(), fillBlock);
			fillBlocks.addAll(filled);
		}
		clusters = new ArrayList<>();
		for (String cluster : model.getConfig().getClusters()) {
			BlockState clusterBlockstate = Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(cluster))).defaultBlockState();
			NonNullList<BlockState> filled = NonNullList.withSize(1, clusterBlockstate);
			clusters.addAll(filled);
		}
	}

	@Override
	public boolean place(FeaturePlaceContext<GeodeOreFeatureConfig> config) {
		RandomSource rand = config.random();
		BlockPos pos = config.origin();
		WorldGenLevel level = config.level();

		UniformInt outerWallDistance = UniformInt.of(4, 6);
		UniformInt distributionPoint = UniformInt.of(3, 4);
		UniformInt pointOffset = UniformInt.of(1, 2);

		int invalidBlocksThreshold = 1;
		double noiseMultiplier = 0.05D;

		List<Pair<BlockPos, Integer>> list = Lists.newLinkedList();
		int k = distributionPoint.sample(rand);
		WorldgenRandom worldgenrandom = new WorldgenRandom(new LegacyRandomSource(level.getSeed()));
		NormalNoise normalnoise = NormalNoise.create(worldgenrandom, -4, 1.0D);
		List<BlockPos> list1 = Lists.newLinkedList();
		double d0 = (double)k / (double)outerWallDistance.getMaxValue();

		double d1 = 1.0D / Math.sqrt(1.7D); // Fill
		double d2 = 1.0D / Math.sqrt(2.2D + d0); // Inner
		double d3 = 1.0D / Math.sqrt(3.2D + d0); // Inner Shell
		double d4 = 1.0D / Math.sqrt(4.2D + d0); // Outer Shell
		double d5 = 1.0D / Math.sqrt(2.0D + rand.nextDouble() / 2.0D + (k > 3 ? d0 : 0.0D)); // Crack Size
		boolean flag = (double)rand.nextFloat() < model.getConfig().getCrackChance();
		int l = 0;

		for(int i1 = 0; i1 < k; ++i1) {
			int j1 = outerWallDistance.sample(rand);
			int k1 = outerWallDistance.sample(rand);
			int l1 = outerWallDistance.sample(rand);
			BlockPos blockpos1 = pos.offset(j1, k1, l1);
			BlockState blockstate = level.getBlockState(blockpos1);
			if (blockstate.isAir() || blockstate.is(BlockTags.GEODE_INVALID_BLOCKS)) {
				++l;
				if (l > invalidBlocksThreshold) {
					return false;
				}
			}
			list.add(Pair.of(blockpos1, pointOffset.sample(rand)));
		}

		if (flag) {
			int i2 = rand.nextInt(4);
			int j2 = k * 2 + 1;
			if (i2 == 0) {
				list1.add(pos.offset(j2, 7, 0));
				list1.add(pos.offset(j2, 5, 0));
				list1.add(pos.offset(j2, 1, 0));
			} else if (i2 == 1) {
				list1.add(pos.offset(0, 7, j2));
				list1.add(pos.offset(0, 5, j2));
				list1.add(pos.offset(0, 1, j2));
			} else if (i2 == 2) {
				list1.add(pos.offset(j2, 7, j2));
				list1.add(pos.offset(j2, 5, j2));
				list1.add(pos.offset(j2, 1, j2));
			} else {
				list1.add(pos.offset(0, 7, 0));
				list1.add(pos.offset(0, 5, 0));
				list1.add(pos.offset(0, 1, 0));
			}
		}

		List<BlockPos> list2 = Lists.newArrayList();
		Predicate<BlockState> predicate = isReplaceable(BlockTags.FEATURES_CANNOT_REPLACE);

		for (BlockPos blockpos3 : BlockPos.betweenClosed(pos.offset(-16, -16, -16), pos.offset(16, 16, 16))) {
			double d8 = normalnoise.getValue((double) blockpos3.getX(), (double) blockpos3.getY(), (double) blockpos3.getZ()) * noiseMultiplier;
			double d6 = 0.0D;
			double d7 = 0.0D;

			for (Pair<BlockPos, Integer> pair : list) {
				d6 += Mth.fastInvSqrt(blockpos3.distSqr(pair.getFirst()) + (double) pair.getSecond().intValue()) + d8;
			}

			for (BlockPos blockpos6 : list1) {
				d7 += Mth.fastInvSqrt(blockpos3.distSqr(blockpos6) + (double) 2) + d8; // Crack Point Offset
			}

			if (!(d6 < d4)) {
				if (flag && d7 >= d5 && d6 < d1) {
					this.safeSetBlock(level, blockpos3, Blocks.AIR.defaultBlockState(), predicate); // Crack

					for (Direction direction1 : DIRECTIONS) {
						BlockPos blockpos2 = blockpos3.relative(direction1);
						FluidState fluidstate = level.getFluidState(blockpos2);
						if (!fluidstate.isEmpty()) {
							level.scheduleTick(blockpos2, fluidstate.getType(), 0);
						}
					}
				} else if (d6 >= d1) {
					placeBlock(level, rand, blockpos3, fillBlocks, predicate, config);
				} else if (d6 >= d2) {
					placeBlock(level, rand, blockpos3, innerBlocks, predicate, config);
					if ((double) rand.nextFloat() < 0.35D) { // Potential Placement Chance
						list2.add(blockpos3.immutable());
					}
				} else if (d6 >= d3) {
					placeBlock(level, rand, blockpos3, innerShellBlocks, predicate, config);
				} else if (d6 >= d4) {
					placeBlock(level, rand, blockpos3, outerShellBlocks, predicate, config);
				}
			}
		}

		for(BlockPos blockpos4 : list2) {
			int index = rand.nextInt(clusters.size());
			BlockState blockstate1 = clusters.get(index);

			for(Direction direction : DIRECTIONS) {
				if (blockstate1.hasProperty(BlockStateProperties.FACING)) {
					blockstate1 = blockstate1.setValue(BlockStateProperties.FACING, direction);
				}

				BlockPos blockpos5 = blockpos4.relative(direction);
				BlockState blockstate2 = level.getBlockState(blockpos5);
				if (blockstate1.hasProperty(BlockStateProperties.WATERLOGGED)) {
					blockstate1 = blockstate1.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(blockstate2.getFluidState().isSource()));
				}

				if (BuddingAmethystBlock.canClusterGrowAtState(blockstate2) && level.getBlockState(blockpos4).getBlock() == Blocks.BUDDING_AMETHYST) {
					this.safeSetBlock(level, blockpos5, blockstate1, predicate);
					break;
				}
			}
		}
		return true;
	}

	private void placeBlock(WorldGenLevel level, RandomSource rand, BlockPos pos, List<CommonBlockDefinitionModel> blocks, Predicate<BlockState> predicate, FeaturePlaceContext<GeodeOreFeatureConfig> config) {
		if (!predicate.test(level.getBlockState(pos))) {
			return;
		}
		if (!config.config().target.test(level.getBlockState(pos), rand)) {
			return;
		}

		int index = rand.nextInt(blocks.size());
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
	}
}