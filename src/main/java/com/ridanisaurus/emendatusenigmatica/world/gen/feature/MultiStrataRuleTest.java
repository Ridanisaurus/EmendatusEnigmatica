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

package com.ridanisaurus.emendatusenigmatica.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.plugin.DataRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTestType;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class MultiStrataRuleTest extends RuleTest {
	public static final MapCodec<MultiStrataRuleTest> CODEC = RecordCodecBuilder.mapCodec(x -> x.group(
		Codec.dispatchedMap(ResourceLocation.CODEC, (__) -> Codec.STRING.listOf()).fieldOf("fillers").forGetter(it -> it.strataByFiller)
	).apply(x, MultiStrataRuleTest::new));

	public static RuleTestType<MultiStrataRuleTest> TYPE;
	private final Map<ResourceLocation, List<String>> strataByFiller;
	private final Set<ResourceLocation> fillerIds;

	public MultiStrataRuleTest(List<String> fillerList, DataRegistry reg) {
		this.strataByFiller = new HashMap<>();
		this.fillerIds = new HashSet<>();
		for (String id : fillerList) {
			var model = Objects.requireNonNull(reg.getStrataModel(id));
			this.strataByFiller.computeIfAbsent(model.getFillerType(), it -> new ArrayList<>()).add(model.getId());
			this.fillerIds.add(model.getFillerType());
		}
	}

	public MultiStrataRuleTest(Map<ResourceLocation, List<String>> fillerList) {
		this.strataByFiller = fillerList;
		this.fillerIds = Set.copyOf(fillerList.keySet());
	}

	@Override
	public boolean test(@NotNull BlockState state, @NotNull RandomSource rand) {
		return fillerIds.contains(BuiltInRegistries.BLOCK.getKey(state.getBlock()));
	}

	public String getStrataFromFiller(@NotNull BlockState state, @NotNull RandomSource rand) {
		var fillerKey = BuiltInRegistries.BLOCK.getKey(state.getBlock());
		var ret = strataByFiller.getOrDefault(fillerKey, List.of());
		if (ret.size() == 1) return ret.getFirst();
		if (ret.isEmpty()) return null;
		return ret.get(rand.nextInt(ret.size()));
	}

	// Where in the world was this used?
	public static void register() {
		TYPE = RuleTestType.register("multi_block_test", CODEC);
	}

	@Override
	protected @NotNull RuleTestType<?> getType() {
		return TYPE;
	}
}
