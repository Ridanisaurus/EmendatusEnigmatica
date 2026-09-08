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

package com.ridanisaurus.emendatusenigmatica.util;

import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.block.BlockModel;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.MultiStrataRuleTest;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
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

//	private void placeSurfaceSample(RandomSource rand, @NotNull BlockPos pos, @NotNull WorldGenLevel level, GeodeDepositModel model) {
//
//		// TODO: Refactor this to be used as a helper method, and add a check if true to generate
//		BlockPos sample = new BlockPos(pos.getX(), level.getHeight(Heightmap.Types.WORLD_SURFACE, pos.getX(), pos.getZ()), pos.getZ());
//		if (level.getBlockState(sample.below()).getBlock() == Blocks.WATER)
//			sample = new BlockPos(pos.getX(), level.getHeight(Heightmap.Types.OCEAN_FLOOR, pos.getX(), pos.getZ()), pos.getZ());
//
//		if (sample.getY() > level.getMinBuildHeight() + 3 && level.getBlockState(sample.below()).is(BlockTags.LEAVES)) {
//			for (int l = 0; l < 3; ++l) {
//				int i = rand.nextInt(2);
//				int j = rand.nextInt(2);
//				int k = rand.nextInt(2);
//				float f = (float)(i + j + k) * 0.333F + 0.5F;
//
//				for(BlockPos samplePos : BlockPos.betweenClosed(sample.offset(-i, -j, -k), sample.offset(i, j, k))) {
//					if (samplePos.distSqr(sample) <= (double)(f * f) && model.placed) {
//						placeSampleBlock(level, rand, samplePos, model);
//					}
//				}
//				sample = sample.offset(-1 + rand.nextInt(2), -rand.nextInt(2), -1 + rand.nextInt(2));
//			}
//		}
//	}
//
//	private void placeSampleBlock(WorldGenLevel level, RandomSource rand, BlockPos samplePos, String material, String strata) {
//		try {
//			int index = rand.nextInt(model.sampleBlocks.size());
//			var depositSampleBlockModel = model.sampleBlocks.get(index);
//
//			if (depositSampleBlockModel.getBlock() != null) {
//				Block sampleBlock = BuiltInRegistries.BLOCK.get(ResourceLocation.parse(depositSampleBlockModel.getBlock()));
//				level.setBlock(samplePos, sampleBlock.defaultBlockState(), 2);
//			} else if (depositSampleBlockModel.getTag() != null) {
//				HolderSet.Named<Block> blockITag = BuiltInRegistries.BLOCK.getTag(EETags.getBlockTag(ResourceLocation.parse(depositSampleBlockModel.getTag()))).get();
//				blockITag.getRandomElement(rand).ifPresent(block -> {
//					level.setBlock(samplePos, block.value().defaultBlockState(), 2);
//				});
//			} else if (depositSampleBlockModel.getMaterial() != null) {
//				Block sampleBlock = EERegistrar.oreSampleBlockTable.get(depositSampleBlockModel.getStrata(), depositSampleBlockModel.getMaterial()).get();
//				level.setBlock(samplePos, sampleBlock.defaultBlockState(), 2);
//			}
//		} catch (Exception e) {
//			JsonElement modelJson = JsonOps.INSTANCE.withEncoder(CODEC).apply(model).result().orElseGet(() -> new JsonPrimitive("Failed to serialize model!"));
//			EmendatusEnigmatica.logger.error("model: " + new Gson().toJson(modelJson), e);
//		}
//	}
}
