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

package com.ridanisaurus.emendatusenigmatica.items;

import com.ridanisaurus.emendatusenigmatica.registries.EETags;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PaxelItem extends DiggerItem {
	private static final ToolAction PAXEL_DIG = ToolAction.get("paxel_dig");

	public PaxelItem(float dmgMod, float speedMod, Tier tier, Properties props) {
		super(dmgMod, speedMod, tier, EETags.MINEABLE_WITH_PAXEL, props);
	}

	@Override
	public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
		return toolAction == PAXEL_DIG || ToolActions.DEFAULT_AXE_ACTIONS.contains(toolAction) || ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction) ||
				ToolActions.DEFAULT_SHOVEL_ACTIONS.contains(toolAction);
	}

	@NotNull
	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level world = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		Player player = context.getPlayer();
		BlockState blockstate = world.getBlockState(blockpos);
		BlockState resultToSet = useAsAxe(blockstate, context);
		if (resultToSet == null) {
			//We cannot strip the item that was right-clicked, so attempt to use the paxel as a shovel
			if (context.getClickedFace() == Direction.DOWN) {
				return InteractionResult.PASS;
			}
			BlockState foundResult = blockstate.getToolModifiedState(context, ToolActions.SHOVEL_FLATTEN, false);
			if (foundResult != null && world.isEmptyBlock(blockpos.above())) {
				//We can flatten the item as a shovel
				world.playSound(player, blockpos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
				resultToSet = foundResult;
			} else if (blockstate.getBlock() instanceof CampfireBlock && blockstate.getValue(CampfireBlock.LIT)) {
				//We can use the paxel as a shovel to extinguish a campfire
				if (!world.isClientSide) {
					world.levelEvent(null, LevelEvent.SOUND_EXTINGUISH_FIRE, blockpos, 0);
				}
				CampfireBlock.dowse(player, world, blockpos, blockstate);
				resultToSet = blockstate.setValue(CampfireBlock.LIT, false);
			}
			if (resultToSet == null) {
				return InteractionResult.PASS;
			}
		}
		if (!world.isClientSide) {
			ItemStack stack = context.getItemInHand();
			if (player instanceof ServerPlayer serverPlayer) {
				CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, blockpos, stack);
			}
			world.setBlock(blockpos, resultToSet, Block.UPDATE_ALL_IMMEDIATE);
			if (player != null) {
				stack.hurtAndBreak(1, player, onBroken -> onBroken.broadcastBreakEvent(context.getHand()));
			}
		}
		return InteractionResult.sidedSuccess(world.isClientSide);
	}

	@Nullable
	private BlockState useAsAxe(BlockState state, UseOnContext context) {
		Level world = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		Player player = context.getPlayer();
		BlockState resultToSet = state.getToolModifiedState(context, ToolActions.AXE_STRIP, false);
		if (resultToSet != null) {
			world.playSound(player, blockpos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
			return resultToSet;
		}
		resultToSet = state.getToolModifiedState(context, ToolActions.AXE_SCRAPE, false);
		if (resultToSet != null) {
			world.playSound(player, blockpos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
			world.levelEvent(player, LevelEvent.PARTICLES_SCRAPE, blockpos, 0);
			return resultToSet;
		}
		resultToSet = state.getToolModifiedState(context, ToolActions.AXE_WAX_OFF, false);
		if (resultToSet != null) {
			world.playSound(player, blockpos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
			world.levelEvent(player, LevelEvent.PARTICLES_WAX_OFF, blockpos, 0);
			return resultToSet;
		}
		return null;
	}
}