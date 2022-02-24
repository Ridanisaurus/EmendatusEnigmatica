package com.ridanisaurus.emendatusenigmatica.blocks;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.material.Material;

public class EEOreBlock extends OreBlock {
	public final int harvestLevel;

	public EEOreBlock(int harvestLevel, UniformInt xp) {
		super(Properties.of(Material.STONE).strength(3.0F, 3.0F).requiresCorrectToolForDrops(), xp);
		this.harvestLevel = harvestLevel;
	}

	public EEOreBlock(int harvestLevel) {
		this(harvestLevel, UniformInt.of(0, 0));
	}
}
