package com.ridanisaurus.emendatusenigmatica.blocks;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.material.Material;

public class EEOreBlock extends OreBlock {
	public final int harvestLevel;

	public EEOreBlock(float strength, float resistance, int harvestLevel, UniformInt p_153993_) {
		super(Properties.of(Material.STONE)
				.strength(strength, resistance)
				// FIXME: .harvestLevel(harvestLevel)
				// FIXME: .harvestTool(ToolType.PICKAXE)
				.requiresCorrectToolForDrops(), p_153993_);

		this.harvestLevel = harvestLevel;
	}

	public EEOreBlock(float strength, float resistance, int harvestLevel) {
		this(strength, resistance, harvestLevel, UniformInt.of(0, 0));
	}
}
