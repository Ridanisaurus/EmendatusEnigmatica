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

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.api.EmendatusDataRegistry;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.geode.GeodeDepositModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.sample.SampleBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EETags;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.config.GeodeOreFeatureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
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
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraft.world.level.material.FluidState;

import java.util.List;
import java.util.function.Predicate;

public class GeodeOreFeature extends Feature<GeodeOreFeatureConfig> {
	private static final Direction[] DIRECTIONS = Direction.values();
	private final EmendatusDataRegistry registry;

	public GeodeOreFeature() {
		super(GeodeOreFeatureConfig.CODEC);
		this.registry = EmendatusEnigmatica.getInstance().getDataRegistry();
	}

	@Override
	public boolean place(FeaturePlaceContext<GeodeOreFeatureConfig> context) {
		RandomSource rand = context.random();
		BlockPos pos = context.origin();
		WorldGenLevel level = context.level();
		var config = context.config();
		var model = config.model;

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
		boolean flag = (double)rand.nextFloat() < model.getCrackChance();
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
					placeBlock(level, rand, blockpos3, config.fillBlocks, predicate, config);
				} else if (d6 >= d2) {
					placeBlock(level, rand, blockpos3, config.innerBlocks, predicate, config);
					if ((double) rand.nextFloat() < 0.35D) { // Potential Placement Chance
						list2.add(blockpos3.immutable());
					}
				} else if (d6 >= d3) {
					placeBlock(level, rand, blockpos3, config.innerShellBlocks, predicate, config);
				} else if (d6 >= d4) {
					placeBlock(level, rand, blockpos3, config.outerShellBlocks, predicate, config);
				}
			}
		}

		if (!config.clusters.isEmpty()) {
			for(BlockPos blockpos4 : list2) {
				int index = rand.nextInt(config.clusters.size());
				BlockState blockstate1 = config.clusters.get(index);

				for(Direction direction : DIRECTIONS) {
					if (blockstate1.hasProperty(BlockStateProperties.FACING)) {
						blockstate1 = blockstate1.setValue(BlockStateProperties.FACING, direction);
					}

					BlockPos blockpos5 = blockpos4.relative(direction);
					BlockState blockstate2 = level.getBlockState(blockpos5);
					if (blockstate1.hasProperty(BlockStateProperties.WATERLOGGED)) {
						blockstate1 = blockstate1.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(blockstate2.getFluidState().isSource()));
					}

					if (BuddingAmethystBlock.canClusterGrowAtState(blockstate2) && level.getBlockState(blockpos4).getBlock() instanceof BuddingAmethystBlock) {
						this.safeSetBlock(level, blockpos5, blockstate1, predicate);
						break;
					}
				}
			}
		}
		if (rand.nextInt(100) < model.getChance() && !config.sampleBlocks.isEmpty()) {
			placeSurfaceSample(rand, pos, level, config);
		}
		return true;
	}

	private void placeBlock(WorldGenLevel level, RandomSource rand, BlockPos pos, List<CommonBlockDefinitionModel> blocks, Predicate<BlockState> predicate, GeodeOreFeatureConfig config) {
		if (!predicate.test(level.getBlockState(pos))) {
			return;
		}
		if (!config.target.test(level.getBlockState(pos), rand)) {
			return;
		}

		int index = rand.nextInt(blocks.size());
		CommonBlockDefinitionModel commonBlockDefinitionModel = blocks.get(index);
		if (commonBlockDefinitionModel.getBlock() != null) {
			Block block = BuiltInRegistries.BLOCK.get(ResourceLocation.parse(commonBlockDefinitionModel.getBlock()));
			level.setBlock(pos, block.defaultBlockState(), 2);
		} else if (commonBlockDefinitionModel.getTag() != null) {
			HolderSet.Named<Block> blockITag = BuiltInRegistries.BLOCK.getTag(EETags.getBlockTag(ResourceLocation.parse(commonBlockDefinitionModel.getTag()))).get();
			blockITag.getRandomElement(rand).ifPresent(block -> {
				level.setBlock(pos, block.value().defaultBlockState(), 2);
			});
		} else if (commonBlockDefinitionModel.getMaterial() != null) {
			StrataModel strata = registry.getStrataFromFiller(BuiltInRegistries.BLOCK.getKey(level.getBlockState(pos).getBlock()));
			if (strata != null) {
				Block block = EERegistrar.oreBlockTable.get(strata.getId(), commonBlockDefinitionModel.getMaterial()).get();
				level.setBlock(pos, block.defaultBlockState(), 2);
			}
		}
		config.placed = true;
	}

	private void placeSampleBlock(WorldGenLevel level, RandomSource rand, BlockPos samplePos, GeodeOreFeatureConfig config) {
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
			JsonElement modelJson = JsonOps.INSTANCE.withEncoder(GeodeDepositModel.CODEC).apply(config.model).result().get();
			EmendatusEnigmatica.logger.error("model: " + new Gson().toJson(modelJson), e);
		}
	}

	private void placeSurfaceSample(RandomSource rand, BlockPos pos, WorldGenLevel level, GeodeOreFeatureConfig config) {
		// TODO: Refactor this to be used as a helper method, and add a check if true to generate
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