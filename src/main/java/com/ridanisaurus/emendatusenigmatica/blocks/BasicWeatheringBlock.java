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
import com.ridanisaurus.emendatusenigmatica.items.PaxelItem;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Optional;
import java.util.function.Supplier;

public class BasicWeatheringBlock extends Block implements ChangeOverTimeBlock<BasicWeatheringBlock.WeatherState>, IColorable {
	private final WeatherState weatherState;
	private final Supplier<BiMap<Block, Block>> nextByBlock;
	private final Supplier<BiMap<Block, Block>> previousByBlock;
	private final Supplier<BiMap<Block, Block>> waxableBlockMap;
	private final String localisedName;
	public final int highlight3;
	public final int highlight2;
	public final int highlight1;
	public final int base;
	public final int shadow1;
	public final int oxidizationColor;

	public BasicWeatheringBlock(MaterialModel material, WeatherState weatherState, Supplier<BiMap<Block, Block>> nextByBlock, Supplier<BiMap<Block, Block>> waxableBlockMap) {
		super(Properties.of(Material.METAL)
				.strength(3f, 3f)
				.requiresCorrectToolForDrops());
		this.weatherState = weatherState;
		this.nextByBlock = nextByBlock;
		this.previousByBlock = Suppliers.memoize(() -> nextByBlock.get().inverse());
		this.waxableBlockMap = waxableBlockMap;
		this.localisedName = material.getLocalizedName();
		this.highlight3 = material.getColors().getHighlightColor(3);
		this.highlight2 = material.getColors().getHighlightColor(2);
		this.highlight1 = material.getColors().getHighlightColor(1);
		this.base = material.getColors().getMaterialColor();
		this.shadow1 = material.getColors().getShadowColor(1);
		this.oxidizationColor = material.getColors().getOxidizationColor();
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

	@Override
	public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		ItemStack stack = player.getItemInHand(hand);

		if (stack.getItem() instanceof AxeItem || stack.getItem() instanceof PaxelItem) {
			Optional<Block> block = getPrevious(blockState.getBlock());
			if (block.isPresent()) {
				level.setBlock(pos, block.map(b -> b.withPropertiesOf(blockState)).get(), 11);
				level.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
				level.levelEvent(player, 3005, pos, 0);
				stack.shrink(0);
				return InteractionResult.SUCCESS;
			}
		}

		if (stack.getItem() == Items.HONEYCOMB) {
			Optional<Block> block = getWaxed(blockState.getBlock());
			if (block.isPresent()) {
				level.setBlock(pos, block.map(b -> b.withPropertiesOf(blockState)).get(), 11);
				level.playSound(player, pos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0F, 1.0F);
				level.levelEvent(player, 3003, pos, 0);
				stack.shrink(1);
				return InteractionResult.SUCCESS;
			}
		}

		return InteractionResult.PASS;
	}

	public Optional<Block> getNext(Block block) {
		return Optional.ofNullable(nextByBlock.get().get(block));
	}

	public BlockState getFirst(BlockState blockState) {
		return getFirst(blockState.getBlock()).withPropertiesOf(blockState);
	}

	private Optional<Block> getWaxed(Block block) {
		return Optional.ofNullable(waxableBlockMap.get().get(block));
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

	public int getOxidizationColor() {
		return oxidizationColor;
	}

	public enum WeatherState {
		UNAFFECTED,
		EXPOSED,
		WEATHERED,
		OXIDIZED;
	}
}
