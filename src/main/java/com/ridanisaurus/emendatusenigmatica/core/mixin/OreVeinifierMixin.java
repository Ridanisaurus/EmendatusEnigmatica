package com.ridanisaurus.emendatusenigmatica.core.mixin;

import com.ridanisaurus.emendatusenigmatica.loader.parser.model.MaterialModel;
import com.ridanisaurus.emendatusenigmatica.plugin.DefaultConfigPlugin;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.NoiseChunk;
import net.minecraft.world.level.levelgen.OreVeinifier;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(OreVeinifier.class)
public class OreVeinifierMixin {

    /**
     * @author Buuz135 and Ridanisaurus
     * @reason A way to disable Vanilla Ore Veins and/or spawn modded ore in a similar fashion
     */

    @Overwrite
    protected static NoiseChunk.BlockStateFiller create(DensityFunction dFunction1, DensityFunction dFunction2, DensityFunction dFunction3, PositionalRandomFactory randomFactory) {
        BlockState blockstate = null;
        return (filler) -> {
            double d0 = dFunction1.compute(filler);
            int i = filler.blockY();
            OreVeinifier.VeinType oreveinifier$veintype = d0 > 0.0D ? OreVeinifier.VeinType.COPPER : OreVeinifier.VeinType.IRON;
            double d1 = Math.abs(d0);
            int j = oreveinifier$veintype.maxY - i;
            int k = i - oreveinifier$veintype.minY;
            if (k >= 0 && j >= 0) {
                int l = Math.min(j, k);
                double d2 = Mth.clampedMap((double) l, 0.0D, 20.0D, -0.2D, 0.0D);
                if (d1 + d2 < (double) 0.4F) {
                    return blockstate;
                } else {
                    RandomSource randomsource = randomFactory.at(filler.blockX(), i, filler.blockZ());
                    if (randomsource.nextFloat() > 0.7F) { // DENSITY
                        return blockstate;
                    } else if (dFunction2.compute(filler) >= 0.0D) {
                        return blockstate;
                    } else {
                        double d3 = Mth.clampedMap(d1, (double) 0.4F, (double) 0.6F, (double) 0.1F, (double) 0.3F);
//                        double d3 = (double) 0.05F;
//                        if ((double) randomsource.nextFloat() < d3 && dFunction3.compute(filler) < (double) 1.0F) { // CHANCE
                        if (dFunction3.compute(filler) < (double) 1.0F) {
                            BlockState mixinRawOreBlock = oreveinifier$veintype.rawOreBlock;
                            BlockState mixinOreBlock = oreveinifier$veintype.ore;
                            for (MaterialModel material : DefaultConfigPlugin.MATERIALS) {
                                if (oreveinifier$veintype == OreVeinifier.VeinType.COPPER && material.getId().equals("copper") && material.getDisableDefaultOre()) {
                                    mixinRawOreBlock = Blocks.GREEN_CONCRETE.defaultBlockState();
                                    mixinOreBlock = Blocks.LIME_CONCRETE.defaultBlockState();
                                }
                                if (oreveinifier$veintype == OreVeinifier.VeinType.IRON && material.getId().equals("iron") && material.getDisableDefaultOre()) {
                                    mixinRawOreBlock = Blocks.BLUE_CONCRETE.defaultBlockState();
                                    mixinOreBlock = Blocks.LIGHT_BLUE_CONCRETE.defaultBlockState();
                                }
                            }
                            return randomsource.nextFloat() < 0.02F ? mixinRawOreBlock : mixinOreBlock; // Chance of Raw Ore Block
                        } else {
//                            return oreveinifier$veintype.filler;
                            return Blocks.RED_CONCRETE.defaultBlockState();
                        }
                    }
                }
            } else {
                return blockstate;
            }
        };
    }
}