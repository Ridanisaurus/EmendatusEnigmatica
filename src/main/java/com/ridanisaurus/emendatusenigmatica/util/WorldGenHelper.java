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

package com.ridanisaurus.emendatusenigmatica.util;

import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.block.BlockModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.sample.SampleConfig;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.MultiStrataRuleTest;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class WorldGenHelper {

	/**
	 * Used to place a "Sample" above supported ore feature.
	 * @param level Current level.
	 * @param rand Random Source of the feature.
	 * @param pos Position of the generated feature.
	 * @param sampleConfig SampleConfig of the model.
	 * @return True if placed.
	 */
	public static boolean placeSurfaceSample(@NotNull WorldGenLevel level, RandomSource rand, @NotNull BlockPos pos, @NotNull SampleConfig sampleConfig) {
		if (!sampleConfig.shouldGenerateSample(rand)) return false;
		BlockPos sample = new BlockPos(pos.getX(), level.getHeight(Heightmap.Types.WORLD_SURFACE, pos.getX(), pos.getZ()), pos.getZ());
		boolean placed = false;
		if (level.getBlockState(sample.below()).getBlock() == Blocks.WATER)
			sample = new BlockPos(pos.getX(), level.getHeight(Heightmap.Types.OCEAN_FLOOR, pos.getX(), pos.getZ()), pos.getZ());

		if (sample.getY() > level.getMinBuildHeight() + 3 && !level.getBlockState(sample.below()).is(BlockTags.LEAVES)) {
			for (int l = 0; l < 3; ++l) {
				int x = rand.nextInt(2);
				int y = rand.nextInt(2);
				int z = rand.nextInt(2);
				float radius = (float)(x + y + z) * 0.333F + 0.5F;

				for (BlockPos samplePos : BlockPos.betweenClosed(sample.offset(-x, -y, -z), sample.offset(x, y, z))) {
					if (samplePos.distSqr(sample) <= (double)(radius * radius)) {
						Optional<BlockState> block;
						if ((block = sampleConfig.blocks.getRandom(rand).flatMap(it -> it.getBlockState(rand))).isEmpty()) continue;
						level.setBlock(samplePos, block.get(), 2);
						placed = true;
					}
				}

				sample = sample.offset(-1 + rand.nextInt(2), -rand.nextInt(2), -1 + rand.nextInt(2));
			}
		}

		return placed;
	}

	@Contract("_, _ -> new")
	public static @Unmodifiable List<PlacementModifier> orePlacement(PlacementModifier modifier_1, PlacementModifier modifier_2) {
		return List.of(modifier_1, InSquarePlacement.spread(), modifier_2, BiomeFilter.biome());
	}

	@Contract("_, _ -> new")
	public static @Unmodifiable List<PlacementModifier> commonOrePlacement(int chancePerChunk, PlacementModifier modifier) {
		return orePlacement(CountPlacement.of(chancePerChunk), modifier);
	}

	@Contract("_, _ -> new")
	public static @Unmodifiable List<PlacementModifier> rareOrePlacement(int chancePerChunk, PlacementModifier modifier) {
		return orePlacement(RarityFilter.onAverageOnceEvery(chancePerChunk), modifier);
	}

	public static List<PlacementModifier> getOrePlacement(@NotNull String rarity, int placementChance, PlacementModifier modifier) {
		return rarity.equals("common") ?
			commonOrePlacement(placementChance, modifier):
			rareOrePlacement(placementChance, modifier);
	}

	public static HeightRangePlacement getPlacementModifier(@NotNull String placement, int minY, int maxY) {
		return placement.equals("uniform") ?
			HeightRangePlacement.uniform(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY)) :
			HeightRangePlacement.triangle(VerticalAnchor.absolute(minY), VerticalAnchor.absolute(maxY));
	}

	public static boolean placeBlock(@NotNull WorldGenLevel level, RandomSource rand, BlockPos pos, WeightedRandomList<? extends BlockModel> blocks, @NotNull Predicate<BlockState> predicate, MultiStrataRuleTest target) {
		BlockState targetState = level.getBlockState(pos);
		if (!Objects.requireNonNull(predicate).test(targetState) || !Objects.requireNonNull(target).test(targetState, rand)) return false;
		Optional<BlockState> block;
		if ((block = blocks.getRandom(rand).flatMap(it -> it.getBlockState(target, targetState, rand))).isEmpty()) return false;
		level.setBlock(pos, block.get(), 2);
		return true;
	}

	public static boolean placeBlock(@NotNull WorldGenLevel level, RandomSource rand, BlockPos pos, WeightedRandomList<? extends BlockModel> blocks, MultiStrataRuleTest target) {
		return placeBlock(level, rand, pos, blocks, (it) -> true, target);
	}
}
