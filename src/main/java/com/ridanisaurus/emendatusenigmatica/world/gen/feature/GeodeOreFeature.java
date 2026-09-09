/*
 * MIT License
 *
 * Copyright (c) 2024-2026. Ridanisaurus
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
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.GeodeDepositModel;
import com.ridanisaurus.emendatusenigmatica.util.WorldGenHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BuddingAmethystBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraft.world.level.material.FluidState;

import java.util.List;
import java.util.function.Predicate;

import static com.ridanisaurus.emendatusenigmatica.util.WorldGenHelper.placeBlock;

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
		boolean placed = false;

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

			if (distance >= outerShellDistance) {
				if (cracked && crackDistance >= crackPointDistance && distance < fillDistance) {
					this.safeSetBlock(level, placePos, Blocks.AIR.defaultBlockState(), predicate); // Crack

					for (Direction dir : DIRECTIONS) {
						BlockPos updatePos = placePos.relative(dir);
						FluidState fluid = level.getFluidState(updatePos);
						if (!fluid.isEmpty())
							level.scheduleTick(updatePos, fluid.getType(), 0);
					}
				} else if (distance >= fillDistance) {
					placed |= placeBlock(level, rand, placePos, model.fillBlocks, predicate, model.target);
				} else if (distance >= innerDistance) {
					placed |= placeBlock(level, rand, placePos, model.innerBlocks, predicate, model.target);
					if ((double) rand.nextFloat() < 0.35D) { // Potential Placement Chance
						clusterPlacements.add(placePos.immutable());
					}
				} else if (distance >= innerShellDistance) {
					placed |= placeBlock(level, rand, placePos, model.innerShellBlocks, predicate, model.target);
				} else if (distance >= outerShellDistance) {
					placed |= placeBlock(level, rand, placePos, model.outerShellBlocks, predicate, model.target);
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

		WorldGenHelper.placeSurfaceSample(level, rand, pos, model.sample);
		return placed;
	}
}