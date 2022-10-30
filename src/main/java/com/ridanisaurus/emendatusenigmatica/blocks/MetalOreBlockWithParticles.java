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

package com.ridanisaurus.emendatusenigmatica.blocks;

import com.mojang.math.Vector3f;
import com.ridanisaurus.emendatusenigmatica.util.ParticleColorHelper;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class MetalOreBlockWithParticles extends OreBlock {
	private final String localisedName;
	private final String particleHex;

	public MetalOreBlockWithParticles(Material material, float hardness, float resistance, String localisedName, String particleHex) {
		super(Properties.of(material)
				.strength(hardness, resistance)
//				.harvestLevel(harvestLevel)
//				.harvestTool(tool)
				.requiresCorrectToolForDrops());
		this.localisedName = localisedName;
		this.particleHex = particleHex;
	}

	@Override
	public MutableComponent getName() {
		return new TranslatableComponent(localisedName);
	}

	@Override
	public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
		super.animateTick(stateIn, worldIn, pos, rand);

//		float red = (float) ParticleColorHelper.HexToColor(particleHex).getRed() / 255;
//		float green = (float) ParticleColorHelper.HexToColor(particleHex).getGreen() / 255;
//		float blue = (float) ParticleColorHelper.HexToColor(particleHex).getBlue() / 255;

		// TODO: [RID] Fix the particle color to read from Hex instead of 16711680
		if(rand.nextInt(10) == 0) {
			for(Direction direction : Direction.values()) {
				BlockPos blockpos = pos.relative(direction);
				if (!worldIn.getBlockState(blockpos).isSolidRender(worldIn, blockpos)) {
					Direction.Axis direction$axis = direction.getAxis();
					double d1 = direction$axis == Direction.Axis.X ? 0.5D + 0.5625D * (double)direction.getStepX() : (double)rand.nextFloat();
					double d2 = direction$axis == Direction.Axis.Y ? 0.5D + 0.5625D * (double)direction.getStepY() : (double)rand.nextFloat();
					double d3 = direction$axis == Direction.Axis.Z ? 0.5D + 0.5625D * (double)direction.getStepZ() : (double)rand.nextFloat();
					worldIn.addParticle(new DustParticleOptions(new Vector3f(Vec3.fromRGB24(16711680)), 1.0F), (double)pos.getX() + d1, (double)pos.getY() + d2, (double)pos.getZ() + d3, 0.0D, 0.0D, 0.0D);
				}
			}
		}
	}
}
