package com.ridanisaurus.emendatusenigmatica.world.gen.feature;

import com.mojang.serialization.Codec;
import com.ridanisaurus.emendatusenigmatica.loader.deposit.model.vanilla.VanillaDepositModel;
import com.ridanisaurus.emendatusenigmatica.util.WorldGenHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.Random;

public class VanillaOreFeature extends OreFeature {
	private VanillaDepositModel model;

	public VanillaOreFeature(Codec<OreFeatureConfig> p_i231976_1_, VanillaDepositModel model) {
		super(p_i231976_1_);
		this.model = model;
	}


	@Override
	public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, OreFeatureConfig config) {
		if (!model.getDimensions().contains(WorldGenHelper.getDimensionAsString(reader.getWorld()))) {
			return false;
		}
		return super.generate(reader, generator, rand, pos, config);
	}

	@Override
	protected boolean func_207803_a(IWorld worldIn, Random random, OreFeatureConfig config, double p_207803_4_, double p_207803_6_, double p_207803_8_, double p_207803_10_, double p_207803_12_, double p_207803_14_, int p_207803_16_, int p_207803_17_, int p_207803_18_, int p_207803_19_, int p_207803_20_) {
		return super.func_207803_a(worldIn, random, config, p_207803_4_, p_207803_6_, p_207803_8_, p_207803_10_, p_207803_12_, p_207803_14_, p_207803_16_, p_207803_17_, p_207803_18_, p_207803_19_, p_207803_20_);
	}
}
