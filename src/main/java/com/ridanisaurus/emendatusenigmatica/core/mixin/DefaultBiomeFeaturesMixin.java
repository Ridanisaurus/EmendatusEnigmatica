package com.ridanisaurus.emendatusenigmatica.core.mixin;

import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BiomeDefaultFeatures.class)
public class DefaultBiomeFeaturesMixin {

    // TODO: (maybe) add config option to overwrite vanilla ores

    @Inject(method = "addDefaultOres(Lnet/minecraft/world/level/biome/BiomeGenerationSettings$Builder;Z)V", at = @At("HEAD"), cancellable = true)
    private static void withOverworldOres(BiomeGenerationSettings.Builder builder, boolean b, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "addExtraGold", at = @At("HEAD"), cancellable = true)
    private static void withExtraGoldOre(BiomeGenerationSettings.Builder builder, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "addExtraEmeralds", at = @At("HEAD"), cancellable = true)
    private static void withEmeraldOre(BiomeGenerationSettings.Builder builder, CallbackInfo ci) {
        ci.cancel();
    }

//    @Inject(method = "withDebrisOre", at = @At("HEAD"), cancellable = true)
//    private static void withDebrisOre(BiomeGenerationSettings.Builder builder, CallbackInfo ci) {
//        ci.cancel();
//    }

    @Inject(method = "addNetherDefaultOres", at = @At("HEAD"), cancellable = true)
    private static void withCommonNetherBlocks(BiomeGenerationSettings.Builder builder, CallbackInfo ci) {
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_GRAVEL_NETHER);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_BLACKSTONE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_ANCIENT_DEBRIS_LARGE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_ANCIENT_DEBRIS_SMALL);
        ci.cancel();
    }
}