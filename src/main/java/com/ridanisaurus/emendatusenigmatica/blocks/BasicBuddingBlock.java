/*
 *  MIT License
 *
 *  Copyright (c) 2020 Ridanisaurus
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package com.ridanisaurus.emendatusenigmatica.blocks;

import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BuddingAmethystBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;

import java.util.function.Supplier;

public class BasicBuddingBlock extends BuddingAmethystBlock implements IColorable {
	private static final Direction[] DIRECTIONS = Direction.values();
	private final String localisedName;
	public final int highlight3;
	public final int highlight2;
	public final int highlight1;
	public final int base;
	public final int shadow1;
	private final Supplier<Block> small_bud;
	private final Supplier<Block> medium_bud;
	private final Supplier<Block> large_bud;
	private final Supplier<Block> cluster;

	public BasicBuddingBlock(MaterialModel material, Supplier<Block> small_bud, Supplier<Block> medium_bud, Supplier<Block> large_bud, Supplier<Block> cluster) {
		super(Properties.of(Material.AMETHYST)
				.strength(1.5f, 1.5f)
				.randomTicks()
				.requiresCorrectToolForDrops());
		this.localisedName = material.getLocalizedName();
		this.highlight3 = material.getColors().getHighlightColor(3);
		this.highlight2 = material.getColors().getHighlightColor(2);
		this.highlight1 = material.getColors().getHighlightColor(1);
		this.base = material.getColors().getMaterialColor();
		this.shadow1 = material.getColors().getShadowColor(1);
		this.small_bud = small_bud;
		this.medium_bud = medium_bud;
		this.large_bud = large_bud;
		this.cluster = cluster;
	}

	@Override
	public void randomTick(BlockState blockState, ServerLevel level, BlockPos pos, RandomSource rand) {
		if (rand.nextInt(5) == 0) {
			Direction direction = DIRECTIONS[rand.nextInt(DIRECTIONS.length)];
			BlockPos blockpos = pos.relative(direction);
			BlockState blockstate = level.getBlockState(blockpos);
			Block block = null;
			if (canClusterGrowAtState(blockstate)) {
				block = this.small_bud.get();
			} else if (blockstate.is(this.small_bud.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
				block = this.medium_bud.get();
			} else if (blockstate.is(this.medium_bud.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
				block = this.large_bud.get();
			} else if (blockstate.is(this.large_bud.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
				block = this.cluster.get();
			}

			if (block != null) {
				BlockState blockstate1 = block.defaultBlockState().setValue(AmethystClusterBlock.FACING, direction).setValue(AmethystClusterBlock.WATERLOGGED, Boolean.valueOf(blockstate.getFluidState().getType() == Fluids.WATER));
				level.setBlockAndUpdate(blockpos, blockstate1);
			}

		}
	}

	@Override
	public MutableComponent getName() {
		return Component.translatable(localisedName);
	}

	@Override
	public int getHighlight3() {
		return highlight3;
	}

	@Override
	public int getHighlight2() {
		return highlight2;
	}

	@Override
	public int getHighlight1() {
		return highlight1;
	}

	@Override
	public int getBase() {
		return base;
	}

	@Override
	public int getShadow1() {
		return shadow1;
	}
}