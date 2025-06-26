/*
 * MIT License
 *
 * Copyright (c) 2020-2024. Ridanisaurus
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

package com.ridanisaurus.emendatusenigmatica.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
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
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.common.ItemAbilities;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class PaxelItem extends DiggerItem
{
    private static final ItemAbility PAXEL_DIG = ItemAbility.get("paxel_dig");

    public PaxelItem(Tier tier, TagKey<Block> blocks, Properties properties) {
        super(tier, blocks, properties);
    }

    @Override
    public boolean canPerformAction(@NotNull ItemStack stack, @NotNull ItemAbility toolAction) {
        return toolAction == PAXEL_DIG || ItemAbilities.DEFAULT_AXE_ACTIONS.contains(toolAction)
                || ItemAbilities.DEFAULT_PICKAXE_ACTIONS.contains(toolAction) || ItemAbilities.DEFAULT_SHOVEL_ACTIONS.contains(toolAction);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext ctx) {
        Level level = ctx.getLevel();
        BlockPos blockPos = ctx.getClickedPos();
        Player player = ctx.getPlayer();
        BlockState blockState = level.getBlockState(blockPos);
        BlockState resultToSet = useAsAxe(blockState, ctx);

        if (resultToSet == null) {
            //We cannot strip the item that was right-clicked, so attempt to use the paxel as a shovel
            if (ctx.getClickedFace() == Direction.DOWN) return InteractionResult.PASS;

            BlockState foundResult = blockState.getToolModifiedState(ctx, ItemAbilities.SHOVEL_FLATTEN, false);

            if (foundResult != null && level.isEmptyBlock(blockPos.above())) {
                //We can flatten the item as a shovel
                level.playSound(player, blockPos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                resultToSet = foundResult;
            } else if (blockState.getBlock() instanceof CampfireBlock && blockState.getValue(CampfireBlock.LIT)) {
                //We can use the paxel as a shovel to extinguish a campfire
                if (!level.isClientSide) level.levelEvent(null, LevelEvent.SOUND_EXTINGUISH_FIRE, blockPos, 0);
                CampfireBlock.dowse(player, level, blockPos, blockState);
                resultToSet = blockState.setValue(CampfireBlock.LIT, false);
            }
            if (resultToSet == null) return InteractionResult.PASS;
        }

        if (!level.isClientSide) {
            ItemStack stack = ctx.getItemInHand();
            if (player instanceof ServerPlayer serverPlayer) CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, blockPos, stack);
            level.setBlock(blockPos, resultToSet, Block.UPDATE_ALL_IMMEDIATE);
            level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(player, resultToSet));
            if (player != null) stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(ctx.getHand()));
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Nullable
    private BlockState useAsAxe(BlockState state, UseOnContext ctx) {
        Level level = ctx.getLevel();
        BlockPos blockpos = ctx.getClickedPos();
        Player player = ctx.getPlayer();

        BlockState resultToSet = state.getToolModifiedState(ctx, ItemAbilities.AXE_STRIP, false);
        if (resultToSet != null) {
            level.playSound(player, blockpos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
            return resultToSet;
        }

        resultToSet = state.getToolModifiedState(ctx, ItemAbilities.AXE_SCRAPE, false);
        if (resultToSet != null) {
            level.playSound(player, blockpos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.levelEvent(player, LevelEvent.PARTICLES_SCRAPE, blockpos, 0);
            return resultToSet;
        }

        resultToSet = state.getToolModifiedState(ctx, ItemAbilities.AXE_WAX_OFF, false);
        if (resultToSet != null) {
            level.playSound(player, blockpos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.levelEvent(player, LevelEvent.PARTICLES_WAX_OFF, blockpos, 0);
            return resultToSet;
        }

        return null;
    }
}
