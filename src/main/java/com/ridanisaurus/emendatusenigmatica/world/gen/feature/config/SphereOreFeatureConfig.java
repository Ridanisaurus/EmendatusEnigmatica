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

package com.ridanisaurus.emendatusenigmatica.world.gen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.common.CommonBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.sample.SampleBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.sphere.SphereDepositModel;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.rule.MultiStrataRuleTest;
import net.minecraft.core.NonNullList;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.ArrayList;
import java.util.List;

public class SphereOreFeatureConfig implements FeatureConfiguration {
	public static final Codec<SphereOreFeatureConfig> CODEC = RecordCodecBuilder.create((x) -> x.group(
		SphereDepositModel.CODEC.fieldOf("model").forGetter(it -> it.model),
		MultiStrataRuleTest.CODEC.fieldOf("target").forGetter(it -> (MultiStrataRuleTest) it.target)
	).apply(x, SphereOreFeatureConfig::new));

	public final RuleTest target;
	public final SphereDepositModel model;
	public final List<CommonBlockDefinitionModel> blocks;
	public final List<SampleBlockDefinitionModel> sampleBlocks;
	public boolean placed = false;

	public SphereOreFeatureConfig(SphereDepositModel model, RuleTest target) {
		this.target = target;
		this.model = model;

		blocks = new ArrayList<>();
		for (CommonBlockDefinitionModel block : model.getBlocks()) {
			NonNullList<CommonBlockDefinitionModel> filled = NonNullList.withSize(block.getWeight(), block);
			blocks.addAll(filled);
		}
		
		sampleBlocks = new ArrayList<>();
		for (SampleBlockDefinitionModel sampleBlock : model.getSampleBlocks()) {
			NonNullList<SampleBlockDefinitionModel> filled = NonNullList.withSize(sampleBlock.getWeight(), sampleBlock);
			sampleBlocks.addAll(filled);
		}
	}

	public SphereOreFeatureConfig(SphereDepositModel model) {
		this(model, new MultiStrataRuleTest(model.getFillerTypes()));
	}
}
