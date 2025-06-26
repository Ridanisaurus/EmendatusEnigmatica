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

package com.ridanisaurus.emendatusenigmatica.world.gen.feature.rule;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.plugin.model.StrataModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTestType;

import java.util.ArrayList;
import java.util.List;

public class MultiStrataRuleTest extends RuleTest {
	public static final MapCodec<MultiStrataRuleTest> CODEC = RecordCodecBuilder.mapCodec(x -> x.group(
			Codec.list(Codec.STRING).fieldOf("fillerList").forGetter(it -> it.fillerList)
	).apply(x, MultiStrataRuleTest::new));

	public static void register() {
		TYPE = RuleTestType.register("multi_block_test", CODEC);
	}
	public static RuleTestType<MultiStrataRuleTest> TYPE;
	private final List<Block> blockFillerList = new ArrayList<>();
	private final List<String> fillerList;

	public MultiStrataRuleTest(List<String> fillerList) {
		this.fillerList = fillerList;
		setup();
	}

	private void setup() {
		for (StrataModel stratum : EmendatusEnigmatica.getInstance().getLoader().getDataRegistry().getStrata()) {
			if (this.fillerList.contains(stratum.getId())) {
				this.blockFillerList.add(BuiltInRegistries.BLOCK.get(stratum.getFillerType()));
			}
		}
	}

	@Override
	public boolean test(BlockState state, RandomSource rand) {
		for (Block block : blockFillerList) {
			if (BuiltInRegistries.BLOCK.getKey(state.getBlock()).toString().equals(BuiltInRegistries.BLOCK.getKey(block).toString())) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected RuleTestType<?> getType() {
		return TYPE;
	}
}
