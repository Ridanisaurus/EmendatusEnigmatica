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
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Optional;
import java.util.function.Supplier;

public class BasicWaxedBlock extends Block implements IColorable {
	private final Supplier<BiMap<Block, Block>> waxableBlockMap;
	private final Supplier<BiMap<Block, Block>> reverseWaxableBlockMap;
	private final String localisedName;
	public final int highlight2;
	public final int highlight1;
	public final int base;
	public final int shadow1;
	public final int shadow2;
	public final int oxidizationColor;

	public BasicWaxedBlock(MaterialModel material, Supplier<BiMap<Block, Block>> waxableBlockMap) {
		super(Properties.of(Material.METAL)
				.strength(3f, 3f)
				.requiresCorrectToolForDrops());
		this.waxableBlockMap = waxableBlockMap;
		this.reverseWaxableBlockMap = Suppliers.memoize(() -> waxableBlockMap.get().inverse());
		this.localisedName = material.getLocalizedName();
		this.highlight2 = material.getColors().getHighlightColor(3);
		this.highlight1 = material.getColors().getHighlightColor(1);
		this.base = material.getColors().getMaterialColor();
		this.shadow1 = material.getColors().getShadowColor(1);
		this.shadow2 = material.getColors().getShadowColor(2);
		this.oxidizationColor = material.getColors().getOxidizationColor();
	}

	@Override
	public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		ItemStack stack = player.getItemInHand(hand);

		if (stack.getItem() instanceof AxeItem || stack.getItem() instanceof PaxelItem) {
			Optional<Block> block = getUnwaxed(blockState.getBlock());
			if (block.isPresent()) {
				level.setBlock(pos, block.map(b -> b.withPropertiesOf(blockState)).get(), 11);
				level.playSound(player, pos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
				level.levelEvent(player, 3004, pos, 0);
				stack.shrink(0);
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.PASS;
	}

	private Optional<Block> getUnwaxed(Block block) {
		return Optional.ofNullable(reverseWaxableBlockMap.get().get(block));
	}

	public Supplier<BiMap<Block, Block>> getWaxable() {
		return waxableBlockMap;
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

	public int getOxidizationColor() {
		return oxidizationColor;
	}
}
