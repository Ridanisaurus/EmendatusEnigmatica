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
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.geode.GeodeDepositModel;
import com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.sample.SampleBlockDefinitionModel;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.rule.MultiStrataRuleTest;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GeodeOreFeatureConfig implements FeatureConfiguration {
	public static final Codec<GeodeOreFeatureConfig> CODEC = RecordCodecBuilder.create((x) -> x.group(
		GeodeDepositModel.CODEC.fieldOf("model").forGetter(it -> it.model),
		MultiStrataRuleTest.CODEC.fieldOf("target").forGetter(it -> (MultiStrataRuleTest) it.target)
	).apply(x, GeodeOreFeatureConfig::new));

	public final RuleTest target;
	public final GeodeDepositModel model;
	public final List<CommonBlockDefinitionModel> outerShellBlocks;
	public final List<CommonBlockDefinitionModel> innerShellBlocks;
	public final List<CommonBlockDefinitionModel> innerBlocks;
	public final List<CommonBlockDefinitionModel> fillBlocks;
	public final List<SampleBlockDefinitionModel> sampleBlocks;
	public final List<BlockState> clusters;
	public boolean placed = false;

	public GeodeOreFeatureConfig(GeodeDepositModel model, RuleTest target) {
		this.target = target;
		this.model = model;

		outerShellBlocks = new ArrayList<>();
		for (CommonBlockDefinitionModel outerShellBlock : model.getOuterShellBlocks()) {
			NonNullList<CommonBlockDefinitionModel> filled = NonNullList.withSize(outerShellBlock.getWeight(), outerShellBlock);
			outerShellBlocks.addAll(filled);
		}

		innerShellBlocks = new ArrayList<>();
		for (CommonBlockDefinitionModel innerShellBlock : model.getInnerShellBlocks()) {
			NonNullList<CommonBlockDefinitionModel> filled = NonNullList.withSize(innerShellBlock.getWeight(), innerShellBlock);
			innerShellBlocks.addAll(filled);
		}

		innerBlocks = new ArrayList<>();
		for (CommonBlockDefinitionModel innerBlock : model.getInnerBlocks()) {
			NonNullList<CommonBlockDefinitionModel> filled = NonNullList.withSize(innerBlock.getWeight(), innerBlock);
			innerBlocks.addAll(filled);
		}

		fillBlocks = new ArrayList<>();
		for (CommonBlockDefinitionModel fillBlock : model.getFillBlocks()) {
			NonNullList<CommonBlockDefinitionModel> filled = NonNullList.withSize(fillBlock.getWeight(), fillBlock);
			fillBlocks.addAll(filled);
		}

		clusters = new ArrayList<>();
		for (String cluster : model.getClusters()) {
			BlockState clusterBlockstate = Objects.requireNonNull(BuiltInRegistries.BLOCK.get(ResourceLocation.parse(cluster))).defaultBlockState();
			NonNullList<BlockState> filled = NonNullList.withSize(1, clusterBlockstate);
			clusters.addAll(filled);
		}

		sampleBlocks = new ArrayList<>();
		for (SampleBlockDefinitionModel sampleBlock : model.getSampleBlocks()) {
			NonNullList<SampleBlockDefinitionModel> filled = NonNullList.withSize(sampleBlock.getWeight(), sampleBlock);
			sampleBlocks.addAll(filled);
		}
	}

	public GeodeOreFeatureConfig(GeodeDepositModel model) {
		this(model, new MultiStrataRuleTest(model.getFillerTypes()));
	}
}
