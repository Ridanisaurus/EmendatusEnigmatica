package com.ridanisaurus.emendatusenigmatica.world.gen.feature;

import com.mojang.serialization.Codec;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.vanilla.VanillaDepositModel;
import com.ridanisaurus.emendatusenigmatica.util.WorldGenHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

import java.util.Random;

public class VanillaOreFeature extends OreFeature {
	private VanillaDepositModel model;

	public VanillaOreFeature(Codec<OreConfiguration> p_i231976_1_, VanillaDepositModel model) {
		super(p_i231976_1_);
		this.model = model;
	}

	@Override
	public boolean place(OreConfiguration config, WorldGenLevel reader, ChunkGenerator generator, RandomSource rand, BlockPos pos) {
		if (!model.getDimensions().contains(WorldGenHelper.getDimensionAsString(reader.getLevel()))) {
			return false;
		}
		return super.place(config, reader, generator, rand, pos);
	}

	@Override
	protected boolean doPlace(WorldGenLevel worldIn, RandomSource random, OreConfiguration config, double p_207803_4_, double p_207803_6_, double p_207803_8_, double p_207803_10_, double p_207803_12_, double p_207803_14_, int p_207803_16_, int p_207803_17_, int p_207803_18_, int p_207803_19_, int p_207803_20_) {
		return super.doPlace(worldIn, random, config, p_207803_4_, p_207803_6_, p_207803_8_, p_207803_10_, p_207803_12_, p_207803_14_, p_207803_16_, p_207803_17_, p_207803_18_, p_207803_19_, p_207803_20_);
	}
}
