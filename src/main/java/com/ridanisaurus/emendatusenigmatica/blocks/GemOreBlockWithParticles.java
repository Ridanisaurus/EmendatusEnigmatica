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
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.loader.parser.model.StrataModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;

public class GemOreBlockWithParticles extends DropExperienceBlock implements IColorable {
	private final String localisedName;
	private final int minExp;
	private final int maxExp;
	private final int particleColor;
	public final int highlight2;
	public final int highlight1;
	public final int base;
	public final int shadow1;
	public final int shadow2;

	public GemOreBlockWithParticles(StrataModel strata, MaterialModel material) {
		super(Properties.of(Material.STONE)
				.strength(strata.getHardness(), strata.getResistance())
				.requiresCorrectToolForDrops());
		this.localisedName = material.getLocalizedName();
		this.minExp = material.getOreDrop().getMin();
		this.maxExp = material.getOreDrop().getMax();
		this.particleColor = material.getColors().getParticlesColor();
		this.highlight2 = material.getColors().getHighlightColor(3);
		this.highlight1 = material.getColors().getHighlightColor(1);
		this.base = material.getColors().getMaterialColor();
		this.shadow1 = material.getColors().getShadowColor(1);
		this.shadow2 = material.getColors().getShadowColor(2);
	}

	@Override
	public MutableComponent getName() {
		return Component.translatable(localisedName);
	}

	protected int getExperience(RandomSource rand) {
		return Mth.nextInt(rand, minExp, maxExp);
	}

	@Override
	public int getExpDrop(BlockState state, LevelReader reader, RandomSource random, BlockPos pos, int fortune, int silktouch) {
		return silktouch == 0 ? this.getExperience(random) : 0;
	}

	@Override
	public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
		super.animateTick(stateIn, worldIn, pos, rand);

		if(rand.nextInt(10) == 0) {
			for(Direction direction : Direction.values()) {
				BlockPos blockpos = pos.relative(direction);
				if (!worldIn.getBlockState(blockpos).isSolidRender(worldIn, blockpos)) {
					Direction.Axis direction$axis = direction.getAxis();
					double d1 = direction$axis == Direction.Axis.X ? 0.5D + 0.5625D * (double)direction.getStepX() : (double)rand.nextFloat();
					double d2 = direction$axis == Direction.Axis.Y ? 0.5D + 0.5625D * (double)direction.getStepY() : (double)rand.nextFloat();
					double d3 = direction$axis == Direction.Axis.Z ? 0.5D + 0.5625D * (double)direction.getStepZ() : (double)rand.nextFloat();
					worldIn.addParticle(new DustParticleOptions(new Vector3f(Vec3.fromRGB24(particleColor)), 1.0F), (double)pos.getX() + d1, (double)pos.getY() + d2, (double)pos.getZ() + d3, 0.0D, 0.0D, 0.0D);
				}
			}
		}
	}

	@Override
	public int getHighlight2() {
		return highlight2;
	}

	@Override
	public int getHighlight1() {
		return highlight1;
	}

	@Override
	public int getBase() {
		return base;
	}

	@Override
	public int getShadow1() {
		return shadow1;
	}

	@Override
	public int getShadow2() {
		return shadow2;
	}
}