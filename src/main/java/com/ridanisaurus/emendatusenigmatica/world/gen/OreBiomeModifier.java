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

package com.ridanisaurus.emendatusenigmatica.world.gen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.ridanisaurus.emendatusenigmatica.EmendatusEnigmatica;
import com.ridanisaurus.emendatusenigmatica.registries.EERegistrar;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import com.mojang.serialization.codecs.RecordCodecBuilder;

// Credit: FTB-IC
public record OreBiomeModifier(
		HolderSet<Biome> biomeBlacklist,
		GenerationStep.Decoration generationStage,
		HolderSet<PlacedFeature> features
) implements BiomeModifier {

	public static Codec<OreBiomeModifier> CODEC = RecordCodecBuilder.create(builder ->
			builder.group(
					Biome.LIST_CODEC.fieldOf("biome_blacklist").forGetter(OreBiomeModifier::biomeBlacklist),
					Codec.STRING.comapFlatMap(OreBiomeModifier::generationStageFromString, GenerationStep.Decoration::toString)
							.fieldOf("generation_stage").forGetter(OreBiomeModifier::generationStage),
					PlacedFeature.LIST_CODEC.fieldOf("features").forGetter(OreBiomeModifier::features)
			).apply(builder, OreBiomeModifier::new));

	@Override
	public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder)
	{
		if (phase == Phase.ADD && !biomeBlacklist.contains(biome))
		{
			BiomeGenerationSettingsBuilder generation = builder.getGenerationSettings();
			this.features.forEach(holder -> generation.addFeature(this.generationStage, holder));
		}
	}

	@Override
	public Codec<? extends BiomeModifier> codec() {
		return EERegistrar.ORE_BIOME_MODIFIER.get();
	}

	private static DataResult<GenerationStep.Decoration> generationStageFromString(String name)
	{
		try
		{
			return DataResult.success(GenerationStep.Decoration.valueOf(name));
		}
		catch (Exception e)
		{
			return DataResult.error("Not a decoration stage: " + name);
		}
	}
}
