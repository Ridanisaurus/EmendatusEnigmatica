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
import com.google.gson.JsonPrimitive;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.DepositBlockModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.GeodeDepositModel;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EETags;
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
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Predicate;

public class GeodeOreFeature extends Feature<GeodeDepositModel> {
	private static final Codec<GeodeDepositModel> CODEC = GeodeDepositModel.getFeatureCodec(GeodeDepositModel.CODEC);
	private static final Direction[] DIRECTIONS = Direction.values();

	public GeodeOreFeature() {
		super(CODEC);
	}

	@Override
	public boolean place(FeaturePlaceContext<GeodeDepositModel> context) {
		RandomSource rand = context.random();
		BlockPos pos = context.origin();
		WorldGenLevel level = context.level();
		var model = context.config();

		//TODO: Add in the Geode Configuration.
		UniformInt outerWallDistances = UniformInt.of(4, 6);
		UniformInt distributionPoints = UniformInt.of(3, 4);
		UniformInt pointOffsets = UniformInt.of(1, 2);

		int invalidBlocksThreshold = 1;
		double noiseMultiplier = 0.05D;

		List<Pair<BlockPos, Integer>> points = Lists.newLinkedList();
		List<BlockPos> crackPoints = Lists.newLinkedList();
		int distributionPoint = distributionPoints.sample(rand);
		WorldgenRandom worldRandom = new WorldgenRandom(new LegacyRandomSource(level.getSeed()));
		NormalNoise noise = NormalNoise.create(worldRandom, -4, 1.0D);
		double radius = (double) distributionPoint / (double) outerWallDistances.getMaxValue();

		double fillDistance = 1.0D / Math.sqrt(1.7D); // Fill
		double innerDistance = 1.0D / Math.sqrt(2.2D + radius); // Inner
		double innerShellDistance = 1.0D / Math.sqrt(3.2D + radius); // Inner Shell
		double outerShellDistance = 1.0D / Math.sqrt(4.2D + radius); // Outer Shell
		double crackPointDistance = 1.0D / Math.sqrt(2.0D + rand.nextDouble() / 2.0D + (distributionPoint > 3 ? radius : 0.0D)); // Crack Size
		boolean cracked = (double)rand.nextFloat() < model.crackChance;
		int invalidBlocks = 0;

		for (int i = 0; i < distributionPoint; ++i) {
			int x = outerWallDistances.sample(rand);
			int y = outerWallDistances.sample(rand);
			int z = outerWallDistances.sample(rand);
			BlockPos pointPos = pos.offset(x, y, z);
			BlockState pointState = level.getBlockState(pointPos);
			if (pointState.isAir() || pointState.is(BlockTags.GEODE_INVALID_BLOCKS)) {
				++invalidBlocks;
				if (invalidBlocks > invalidBlocksThreshold) return false;
			}
			points.add(Pair.of(pointPos, pointOffsets.sample(rand)));
		}

		if (cracked) {
			int crackOffset = distributionPoint * 2 + 1;
            switch (rand.nextInt(4)) {
                case 0 -> {
                    crackPoints.add(pos.offset(crackOffset, 7, 0));
                    crackPoints.add(pos.offset(crackOffset, 5, 0));
                    crackPoints.add(pos.offset(crackOffset, 1, 0));
                }
                case 1 -> {
                    crackPoints.add(pos.offset(0, 7, crackOffset));
                    crackPoints.add(pos.offset(0, 5, crackOffset));
                    crackPoints.add(pos.offset(0, 1, crackOffset));
                }
                case 2 -> {
                    crackPoints.add(pos.offset(crackOffset, 7, crackOffset));
                    crackPoints.add(pos.offset(crackOffset, 5, crackOffset));
                    crackPoints.add(pos.offset(crackOffset, 1, crackOffset));
                }
                default -> {
                    crackPoints.add(pos.offset(0, 7, 0));
                    crackPoints.add(pos.offset(0, 5, 0));
                    crackPoints.add(pos.offset(0, 1, 0));
                }
            }
		}

		List<BlockPos> clusterPlacements = Lists.newArrayList();
		Predicate<BlockState> predicate = isReplaceable(BlockTags.FEATURES_CANNOT_REPLACE);

		for (BlockPos placePos : BlockPos.betweenClosed(pos.offset(-16, -16, -16), pos.offset(16, 16, 16))) {
			double noiseModifier = noise.getValue(placePos.getX(), placePos.getY(), placePos.getZ()) * noiseMultiplier;
			double distance = 0.0D;
			double crackDistance = 0.0D;

			for (Pair<BlockPos, Integer> point : points) {
				distance += Mth.invSqrt(placePos.distSqr(point.getFirst()) + (double) point.getSecond()) + noiseModifier;
			}

			for (BlockPos point : crackPoints) {
				crackDistance += Mth.invSqrt(placePos.distSqr(point) + (double) 2) + noiseModifier; // Crack Point Offset
			}

			if (!(distance < outerShellDistance)) {
				if (cracked && crackDistance >= crackPointDistance && distance < fillDistance) {
					this.safeSetBlock(level, placePos, Blocks.AIR.defaultBlockState(), predicate); // Crack

					for (Direction dir : DIRECTIONS) {
						BlockPos updatePos = placePos.relative(dir);
						FluidState fluid = level.getFluidState(updatePos);
						if (!fluid.isEmpty()) {
							level.scheduleTick(updatePos, fluid.getType(), 0);
						}
					}
				} else if (distance >= fillDistance) {
					placeBlock(level, rand, placePos, model.fillBlocks, predicate, model);
				} else if (distance >= innerDistance) {
					placeBlock(level, rand, placePos, model.innerBlocks, predicate, model);
					if ((double) rand.nextFloat() < 0.35D) { // Potential Placement Chance
						clusterPlacements.add(placePos.immutable());
					}
				} else if (distance >= innerShellDistance) {
					placeBlock(level, rand, placePos, model.innerShellBlocks, predicate, model);
				} else if (distance >= outerShellDistance) {
					placeBlock(level, rand, placePos, model.outerShellBlocks, predicate, model);
				}
			}
		}

		if (!model.clusters.isEmpty()) {
			for (BlockPos clusterPos : clusterPlacements) {
				int index = rand.nextInt(model.clusters.size());
				BlockState state = BuiltInRegistries.BLOCK.get(ResourceLocation.parse(model.clusters.get(index))).defaultBlockState();

				for (Direction dir : DIRECTIONS) {
					if (state.hasProperty(BlockStateProperties.FACING))
						state = state.setValue(BlockStateProperties.FACING, dir);

					BlockPos basePos = clusterPos.relative(dir);
					BlockState baseState = level.getBlockState(basePos);
					if (state.hasProperty(BlockStateProperties.WATERLOGGED))
						state = state.setValue(BlockStateProperties.WATERLOGGED, baseState.getFluidState().isSource());

					if (BuddingAmethystBlock.canClusterGrowAtState(baseState) && level.getBlockState(clusterPos).getBlock() instanceof BuddingAmethystBlock) {
						this.safeSetBlock(level, basePos, state, predicate);
						break;
					}
				}
			}
		}

		if (rand.nextInt(100) < model.chance && !model.sampleBlocks.isEmpty())
			placeSurfaceSample(rand, pos, level, model);

		return true;
	}

	private void placeBlock(@NotNull WorldGenLevel level, RandomSource rand, BlockPos pos, List<DepositBlockModel> blocks, @NotNull Predicate<BlockState> predicate, GeodeDepositModel model) {
		if (!predicate.test(level.getBlockState(pos)) || !model.target.test(level.getBlockState(pos), rand)) return;

		int index = rand.nextInt(blocks.size());
		var depositBlockModel = blocks.get(index);

		if (depositBlockModel.getBlock() != null) {
			Block block = BuiltInRegistries.BLOCK.get(ResourceLocation.parse(depositBlockModel.getBlock()));
			level.setBlock(pos, block.defaultBlockState(), 2);
		} else if (depositBlockModel.getTag() != null) {
			HolderSet.Named<Block> blockITag = BuiltInRegistries.BLOCK.getTag(EETags.getBlockTag(ResourceLocation.parse(depositBlockModel.getTag()))).get();
			blockITag.getRandomElement(rand).ifPresent(block -> {
				level.setBlock(pos, block.value().defaultBlockState(), 2);
			});
		} else if (depositBlockModel.getMaterial() != null) {
			var strata = model.target.getStrataFromFiller(level.getBlockState(pos), rand);
			if (strata != null) {
				Block block = EERegistrar.oreBlockTable.get(strata, depositBlockModel.getMaterial()).get();
				level.setBlock(pos, block.defaultBlockState(), 2);
			}
		}

		model.placed = true;
	}

	private void placeSampleBlock(WorldGenLevel level, RandomSource rand, BlockPos samplePos, GeodeDepositModel model) {
		try {
			int index = rand.nextInt(model.sampleBlocks.size());
			var depositSampleBlockModel = model.sampleBlocks.get(index);

			if (depositSampleBlockModel.getBlock() != null) {
				Block sampleBlock = BuiltInRegistries.BLOCK.get(ResourceLocation.parse(depositSampleBlockModel.getBlock()));
				level.setBlock(samplePos, sampleBlock.defaultBlockState(), 2);
			} else if (depositSampleBlockModel.getTag() != null) {
				HolderSet.Named<Block> blockITag = BuiltInRegistries.BLOCK.getTag(EETags.getBlockTag(ResourceLocation.parse(depositSampleBlockModel.getTag()))).get();
				blockITag.getRandomElement(rand).ifPresent(block -> {
					level.setBlock(samplePos, block.value().defaultBlockState(), 2);
				});
			} else if (depositSampleBlockModel.getMaterial() != null) {
				Block sampleBlock = EERegistrar.oreSampleBlockTable.get(depositSampleBlockModel.getStrata(), depositSampleBlockModel.getMaterial()).get();
				level.setBlock(samplePos, sampleBlock.defaultBlockState(), 2);
			}
		} catch (Exception e) {
			JsonElement modelJson = JsonOps.INSTANCE.withEncoder(CODEC).apply(model).result().orElseGet(() -> new JsonPrimitive("Failed to serialize model!"));
			EmendatusEnigmatica.logger.error("model: " + new Gson().toJson(modelJson), e);
		}
	}

	private void placeSurfaceSample(RandomSource rand, @NotNull BlockPos pos, @NotNull WorldGenLevel level, GeodeDepositModel model) {
		// TODO: Refactor this to be used as a helper method, and add a check if true to generate
		BlockPos sample = new BlockPos(pos.getX(), level.getHeight(Heightmap.Types.WORLD_SURFACE, pos.getX(), pos.getZ()), pos.getZ());
		if (level.getBlockState(sample.below()).getBlock() == Blocks.WATER)
			sample = new BlockPos(pos.getX(), level.getHeight(Heightmap.Types.OCEAN_FLOOR, pos.getX(), pos.getZ()), pos.getZ());

		if (sample.getY() > level.getMinBuildHeight() + 3 && level.getBlockState(sample.below()).is(BlockTags.LEAVES)) {
			for (int l = 0; l < 3; ++l) {
				int i = rand.nextInt(2);
				int j = rand.nextInt(2);
				int k = rand.nextInt(2);
				float f = (float)(i + j + k) * 0.333F + 0.5F;

				for(BlockPos samplePos : BlockPos.betweenClosed(sample.offset(-i, -j, -k), sample.offset(i, j, k))) {
					if (samplePos.distSqr(sample) <= (double)(f * f) && model.placed) {
						placeSampleBlock(level, rand, samplePos, model);
					}
				}
				sample = sample.offset(-1 + rand.nextInt(2), -rand.nextInt(2), -1 + rand.nextInt(2));
			}
		}

		model.placed = false;
	}
}