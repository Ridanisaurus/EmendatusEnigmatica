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

package com.ridanisaurus.emendatusenigmatica.plugin.model.deposit.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ridanisaurus.emendatusenigmatica.api.validation.ValidationManager;
import com.ridanisaurus.emendatusenigmatica.api.validation.enums.Types;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.PluginRegistryValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.RequiredValidator;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.TypeValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.DataRegistry;
import com.ridanisaurus.emendatusenigmatica.plugin.VanillaPlugin;
import com.ridanisaurus.emendatusenigmatica.api.validation.validators.FieldPresentValidator;
import com.ridanisaurus.emendatusenigmatica.plugin.validators.deposit.MaterialValidator;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import com.ridanisaurus.emendatusenigmatica.registries.EETags;
import com.ridanisaurus.emendatusenigmatica.world.gen.feature.MultiStrataRuleTest;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.Weight;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

public class SampleBlockModel extends BlockModel {
	public static final Codec<SampleBlockModel> CODEC = RecordCodecBuilder.create(x -> x.group(
			Codec.STRING.optionalFieldOf("block", null).forGetter(BlockModel::getBlock),
			Codec.STRING.optionalFieldOf("tag", null).forGetter(BlockModel::getTag),
			Codec.STRING.optionalFieldOf("material", null).forGetter(BlockModel::getMaterial),
			Codec.INT.optionalFieldOf("weight", 1).forGetter(it -> it.getWeight().asInt()),
			Codec.STRING.optionalFieldOf("strata", null).forGetter(it -> it.strata)
	).apply(x, SampleBlockModel::new));
	private final String strata;

	public static final ValidationManager VALIDATION_MANAGER = ValidationManager.create()
		.addValidator("block",    new RequiredValidator(false))
		.addValidator("tag",      new RequiredValidator(false))
		.addValidator("material", new MaterialValidator())
		.addValidator("weight",   new TypeValidator(Types.INTEGER, false))
		.addValidator("strata",   new FieldPresentValidator(
			"material",
			new PluginRegistryValidator<>(VanillaPlugin.class, DataRegistry::isStrataRegistered, PluginRegistryValidator.REFERENCE_MODE, "Strata", false))
		);

	public SampleBlockModel(@Nullable String block, @Nullable String tag, @Nullable String material, int weight, @Nullable String strata) {
		super(block, tag, material, weight);
		this.strata = strata;
	}

	public @Nullable String getStrata() {
		return strata;
	}

	@Override
	public Optional<BlockState> getBlockState(MultiStrataRuleTest target, BlockState targetState, RandomSource rand) {
		return getBlockState(rand);
	}

	public Optional<BlockState> getBlockState(RandomSource rand) {
		if (Objects.nonNull(getBlock()))
			return Optional.of(BuiltInRegistries.BLOCK.get(ResourceLocation.parse(getBlock())).defaultBlockState());

		if (Objects.nonNull(getTag())) {
			Optional<HolderSet.Named<Block>> blockITag = BuiltInRegistries.BLOCK.getTag(EETags.getBlockTag(ResourceLocation.parse(getTag())));
			return blockITag.flatMap(holders -> holders.getRandomElement(rand).map(blockHolder -> blockHolder.value().defaultBlockState()));
		}

		DeferredBlock<Block> ret;
		if (Objects.isNull(getMaterial()) || Objects.isNull(strata) || Objects.isNull(ret = EERegistrar.oreBlockTable.get(strata, getMaterial()))) return Optional.empty();
		return Optional.of(ret.get().defaultBlockState());
	}
}