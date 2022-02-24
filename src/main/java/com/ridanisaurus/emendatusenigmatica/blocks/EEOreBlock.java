package com.ridanisaurus.emendatusenigmatica.blocks;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.material.Material;

public class EEOreBlock extends OreBlock {
	public final int harvestLevel;

	public EEOreBlock(int harvestLevel, UniformInt p_153993_) {
		super(Properties.of(Material.STONE)
				.strength(3.0F, 3.0F)
				// FIXME: .harvestLevel(harvestLevel)
				// FIXME: .harvestTool(ToolType.PICKAXE)
				// TODO: Use harvestLevel in tag datagen to assign proper tool type
				.requiresCorrectToolForDrops(), p_153993_);

		this.harvestLevel = harvestLevel;
	}

	public EEOreBlock(int harvestLevel) {
		this(harvestLevel, UniformInt.of(0, 0));
	}
}
