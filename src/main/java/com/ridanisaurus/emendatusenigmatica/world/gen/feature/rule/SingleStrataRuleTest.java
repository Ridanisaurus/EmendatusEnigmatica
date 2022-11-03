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

package com.ridanisaurus.emendatusenigmatica.world.gen.feature.rule;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.loader.EELoader;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTestType;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class SingleStrataRuleTest extends RuleTest {

	public static final Codec<SingleStrataRuleTest> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.list(Codec.STRING).fieldOf("fillerList").forGetter(it -> it.fillerList)
	).apply(x, SingleStrataRuleTest::new));

	public static void register() {
		TYPE = RuleTestType.register("single_block_test", CODEC);
	}
	public static RuleTestType<SingleStrataRuleTest> TYPE;
	private final List<Block> blockFillerList = new ArrayList<>();
	private List<String> fillerList;

	public SingleStrataRuleTest(List<String> fillerList) {
		this.fillerList = fillerList;
		setup();
	}

	private void setup() {
		for (StrataModel stratum : EELoader.STRATA) {
			if (this.fillerList.contains(stratum.getId())) {
				this.blockFillerList.add(ForgeRegistries.BLOCKS.getValue(stratum.getFillerType()));
			}
		}
	}

	@Override
	public boolean test(BlockState state, RandomSource rand) {
		for (Block block : blockFillerList) {
			if (ForgeRegistries.BLOCKS.getKey(state.getBlock()).toString().equals(ForgeRegistries.BLOCKS.getKey(block).toString())) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected RuleTestType<?> getType() {
		return TYPE;
	}

//	private final Block block;
//
//	public BlockMatchTest(Block p_74067_) {
//		this.block = p_74067_;
//	}
//
//	public boolean test(BlockState p_230277_, RandomSource p_230278_) {
//		return p_230277_.is(this.block);
//	}
//
//	protected RuleTestType<?> getType() {
//		return RuleTestType.BLOCK_TEST;
//	}
//
//	@Override
//	public boolean test(BlockState p_230322_, RandomSource p_230323_) {
//		return false;
//	}
//
//	@Override
//	protected RuleTestType<?> getType() {
//		return null;
//	}
}
