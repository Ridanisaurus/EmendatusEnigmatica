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

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import java.util.Optional;
import java.util.function.Supplier;

public class BasicWeatheringBlock extends Block implements ChangeOverTimeBlock<BasicWeatheringBlock.WeatherState>, IColorable {
	private final WeatherState weatherState;
	private final Supplier<BiMap<Block, Block>> nextByBlock;
	private final Supplier<BiMap<Block, Block>> previousByBlock;
	private final String localisedName;
	public final int highlight2;
	public final int highlight1;
	public final int base;
	public final int shadow1;
	public final int shadow2;

//	private final Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK = Suppliers.memoize(
//			() -> ImmutableBiMap.<Block, Block>builder()
//					.put(Blocks.COPPER_BLOCK, Blocks.EXPOSED_COPPER)
//					.put(Blocks.EXPOSED_COPPER, Blocks.WEATHERED_COPPER)
//					.put(Blocks.WEATHERED_COPPER, Blocks.OXIDIZED_COPPER)
//					.build()
//	);
//	private final Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK = Suppliers.memoize(
//			() -> NEXT_BY_BLOCK.get().inverse()
//	);

	public BasicWeatheringBlock(MaterialModel material, WeatherState weatherState, Supplier<BiMap<Block, Block>> nextByBlock) {
		super(Properties.of(Material.METAL)
				.strength(3f, 3f)
				.requiresCorrectToolForDrops());
		this.weatherState = weatherState;
		this.nextByBlock = nextByBlock;
		this.previousByBlock = Suppliers.memoize(() -> nextByBlock.get().inverse());
		this.localisedName = material.getLocalizedName();
		this.highlight2 = material.getColors().getHighlightColor(3);
		this.highlight1 = material.getColors().getHighlightColor(1);
		this.base = material.getColors().getMaterialColor();
		this.shadow1 = material.getColors().getShadowColor(1);
		this.shadow2 = material.getColors().getShadowColor(2);
	}

	public void randomTick(BlockState blockState, ServerLevel level, BlockPos pos, RandomSource rand) {
		this.onRandomTick(blockState, level, pos, rand);
	}

	public boolean isRandomlyTicking(BlockState blockState) {
		return getNext(blockState.getBlock()).isPresent();
	}

	@Override
	public WeatherState getAge() {
		return this.weatherState;
	}

	public Optional<Block> getPrevious(Block block) {
		return Optional.ofNullable(previousByBlock.get().get(block));
	}

	public Block getFirst(Block block) {
		Block block1 = block;

		for(Block block2 = previousByBlock.get().get(block); block2 != null; block2 = previousByBlock.get().get(block2)) {
			block1 = block2;
		}

		return block1;
	}

	public Optional<Block> getNext(Block block) {
		return Optional.ofNullable(nextByBlock.get().get(block));
	}

	public BlockState getFirst(BlockState blockState) {
		return getFirst(blockState.getBlock()).withPropertiesOf(blockState);
	}

	@Override
	public Optional<BlockState> getNext(BlockState blockState) {
		return getNext(blockState.getBlock()).map((block) -> {
			return block.withPropertiesOf(blockState);
		});
	}

	public Optional<BlockState> getPrevious(BlockState blockState) {
		return getPrevious(blockState.getBlock()).map((block) -> {
			return block.withPropertiesOf(blockState);
		});
	}

	public float getChanceModifier() {
		return this.getAge() == WeatherState.UNAFFECTED ? 0.75F : 1.0F;
	}

	@Override
	public MutableComponent getName() {
		return Component.translatable(localisedName);
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

	@Override
	public int getShadow2() {
		return shadow2;
	}

	public enum WeatherState {
		UNAFFECTED,
		EXPOSED,
		WEATHERED,
		OXIDIZED;
	}
}
