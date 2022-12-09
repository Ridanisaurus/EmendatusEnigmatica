package com.ridanisaurus.emendatusenigmatica.core.mixin;

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


    @Overwrite
    protected static NoiseChunk.BlockStateFiller create(DensityFunction p_209668_, DensityFunction p_209669_, DensityFunction p_209670_, PositionalRandomFactory p_209671_) {
        if ("poopoo" == "poo"){
            return (filler) -> Blocks.DIAMOND_BLOCK.defaultBlockState();
        }
        BlockState blockstate = null;
        return (p_209666_) -> {
            double d0 = p_209668_.compute(p_209666_);
            int i = p_209666_.blockY();
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
                 RandomSource randomsource = p_209671_.at(p_209666_.blockX(), i, p_209666_.blockZ());
                 if (randomsource.nextFloat() > 0.7F) {
                     return blockstate;
                 } else if (p_209669_.compute(p_209666_) >= 0.0D) {
                     return blockstate;
                 } else {
                     double d3 = Mth.clampedMap(d1, (double) 0.4F, (double) 0.6F, (double) 0.1F, (double) 0.3F);
                     if ((double) randomsource.nextFloat() < d3 && p_209670_.compute(p_209666_) > (double) -0.3F) {
                         return randomsource.nextFloat() < 0.02F ? oreveinifier$veintype.rawOreBlock : oreveinifier$veintype.ore;
                     } else {
                         return oreveinifier$veintype.filler;
                     }
                 }
             }
            } else {
             return blockstate;
            }
        };
    }
}
